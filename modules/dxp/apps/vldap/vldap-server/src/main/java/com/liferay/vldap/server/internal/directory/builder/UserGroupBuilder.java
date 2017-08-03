/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.vldap.server.internal.directory.FilterConstraint;
import com.liferay.vldap.server.internal.directory.SearchBase;
import com.liferay.vldap.server.internal.directory.ldap.Directory;
import com.liferay.vldap.server.internal.directory.ldap.UserGroupDirectory;
import com.liferay.vldap.server.internal.util.LdapUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.api.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.api.ldap.model.name.Dn;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class UserGroupBuilder extends DirectoryBuilder {

	public UserGroupBuilder() {
		attributeValidator.addAlwaysValidAttribute("cn");
		attributeValidator.addAlwaysValidAttribute("description");
		attributeValidator.addAlwaysValidAttribute("member");
		attributeValidator.addAlwaysValidAttribute("ou");

		attributeValidator.addValidAttributeValues(
			"objectclass", "groupOfNames", "liferayUserGroup",
			"organizationalUnit", "top", "*");
	}

	@Override
	public List<Directory> buildDirectories(
			SearchBase searchBase, List<FilterConstraint> filterConstraints)
		throws LdapInvalidDnException {

		List<Directory> directories = new ArrayList<>();

		for (Company company : searchBase.getCompanies()) {
			List<UserGroup> userGroups = getUserGroups(
				company, filterConstraints, (int)searchBase.getSizeLimit());

			for (UserGroup userGroup : userGroups) {
				Directory directory = new UserGroupDirectory(
					searchBase.getTop(), company, userGroup);

				directories.add(directory);
			}
		}

		return directories;
	}

	protected List<UserGroup> getUserGroups(
			Company company, List<FilterConstraint> filterConstraints,
			int sizeLimit)
		throws LdapInvalidDnException {

		if (filterConstraints.isEmpty()) {
			return getUserGroups(company.getCompanyId(), null, null, sizeLimit);
		}

		List<UserGroup> userGroups = new ArrayList<>();

		for (FilterConstraint filterConstraint : filterConstraints) {
			if (!isValidFilterConstraint(filterConstraint)) {
				continue;
			}

			String name = filterConstraint.getValue("ou");

			if (name == null) {
				name = filterConstraint.getValue("cn");
			}

			String description = filterConstraint.getValue("description");

			String member = filterConstraint.getValue("member");

			String screenName = LdapUtil.getRdnValue(new Dn(member), 3);

			if (screenName != null) {
				User user = UserLocalServiceUtil.fetchUserByScreenName(
					company.getCompanyId(), screenName);

				if (user == null) {
					continue;
				}

				for (UserGroup userGroup : user.getUserGroups()) {
					if ((name != null) && !name.equals(userGroup.getName())) {
						continue;
					}

					if ((description != null) &&
						!description.equals(userGroup.getDescription())) {

						continue;
					}

					userGroups.add(userGroup);
				}
			}
			else {
				userGroups.addAll(
					getUserGroups(
						company.getCompanyId(), name, description, sizeLimit));
			}
		}

		return ListUtil.unique(userGroups);
	}

	protected List<UserGroup> getUserGroups(
		long companyId, String name, String description, int sizeLimit) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserGroup.class, PortalClassLoaderUtil.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyId", companyId));

		if (name != null) {
			dynamicQuery.add(RestrictionsFactoryUtil.ilike("name", name));
		}

		if (description != null) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.ilike("description", name));
		}

		dynamicQuery.setLimit(0, sizeLimit);

		return UserGroupLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

}