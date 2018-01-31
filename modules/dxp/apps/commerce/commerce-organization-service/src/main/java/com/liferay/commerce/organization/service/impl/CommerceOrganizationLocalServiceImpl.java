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

package com.liferay.commerce.organization.service.impl;

import com.liferay.commerce.organization.service.base.CommerceOrganizationLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocalCloseable;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 * @author Andrea Di Giorgi
 */
public class CommerceOrganizationLocalServiceImpl
	extends CommerceOrganizationLocalServiceBaseImpl {

	@Override
	public Organization addOrganization(
			long parentOrganizationId, String name, String type,
			ServiceContext serviceContext)
		throws PortalException {

		return organizationLocalService.addOrganization(
			serviceContext.getUserId(), parentOrganizationId, name, type, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, serviceContext);
	}

	@Override
	public void addOrganizationUsers(
			long organizationId, String[] emailAddresses,
			ServiceContext serviceContext)
		throws PortalException {

		if (emailAddresses.length == 0) {
			return;
		}

		long companyId = serviceContext.getCompanyId();
		Locale locale = serviceContext.getLocale();

		try (ProxyModeThreadLocalCloseable proxyModeThreadLocalCloseable =
				new ProxyModeThreadLocalCloseable()) {

			ProxyModeThreadLocal.setForceSync(true);

			long[] userIds = new long[emailAddresses.length];

			for (int i = 0; i < emailAddresses.length; i++) {
				String emailAddress = emailAddresses[i];

				User user = userLocalService.fetchUserByEmailAddress(
					companyId, emailAddress);

				if (user == null) {
					user = userLocalService.addUserWithWorkflow(
						serviceContext.getUserId(), companyId, true,
						StringPool.BLANK, StringPool.BLANK, true,
						StringPool.BLANK, emailAddress, 0, StringPool.BLANK,
						locale, emailAddress, StringPool.BLANK, emailAddress, 0,
						0, true, 1, 1, 1970, StringPool.BLANK, null,
						new long[] {organizationId}, null, null, true,
						serviceContext);
				}

				userIds[i] = user.getUserId();
			}

			userLocalService.addOrganizationUsers(organizationId, userIds);
		}
	}

	@Override
	public Address getOrganizationPrimaryAddress(long organizationId)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		List<Address> addresses = organization.getAddresses();

		for (Address address : addresses) {
			if (address.isPrimary()) {
				return address;
			}
		}

		return addressPersistence.create(0);
	}

	@Override
	public EmailAddress getOrganizationPrimaryEmailAddress(long organizationId)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		List<EmailAddress> emailAddresses =
			emailAddressLocalService.getEmailAddresses(
				organization.getCompanyId(), Organization.class.getName(),
				organization.getOrganizationId());

		for (EmailAddress emailAddress : emailAddresses) {
			if (emailAddress.isPrimary()) {
				return emailAddress;
			}
		}

		return emailAddressPersistence.create(0);
	}

	@Override
	public BaseModelSearchResult<Organization> searchOrganizations(
			long organizationId, String type, String keywords, int start,
			int end, Sort[] sorts)
		throws PortalException {

		List<Organization> organizations = new ArrayList<>();

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		Indexer<Organization> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			Organization.class);

		SearchContext searchContext = buildSearchContext(
			organization, type, keywords, start, end, sorts);

		Hits hits = indexer.search(searchContext);

		Document[] documents = hits.getDocs();

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ORGANIZATION_ID));

			organizations.add(
				organizationLocalService.getOrganization(classPK));
		}

		return new BaseModelSearchResult<>(organizations, hits.getLength());
	}

	@Override
	public void unsetOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		try (ProxyModeThreadLocalCloseable proxyModeThreadLocalCloseable =
				new ProxyModeThreadLocalCloseable()) {

			ProxyModeThreadLocal.setForceSync(true);

			userLocalService.unsetOrganizationUsers(organizationId, userIds);
		}
	}

	protected SearchContext buildSearchContext(
		Organization organization, String type, String keywords, int start,
		int end, Sort[] sorts) {

		SearchContext searchContext = new SearchContext();

		boolean andSearch = true;

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put(Field.TREE_PATH, organization.getTreePath());

		List<Long> excludedOrganizationIds = Collections.singletonList(
			organization.getOrganizationId());

		params.put("excludedOrganizationIds", excludedOrganizationIds);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put("params", params);

		attributes.put(
			"parentOrganizationId",
			String.valueOf(OrganizationConstants.ANY_PARENT_ORGANIZATION_ID));

		if (Validator.isNotNull(type)) {
			attributes.put(Field.TYPE, type);
		}

		if (Validator.isNotNull(keywords)) {
			attributes.put("city", keywords);
			attributes.put("country", keywords);
			attributes.put("name", keywords);
			attributes.put("region", keywords);
			attributes.put("street", keywords);
			attributes.put("zip", keywords);

			andSearch = false;

			searchContext.setKeywords(keywords);
		}

		searchContext.setAndSearch(andSearch);
		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(organization.getCompanyId());
		searchContext.setEnd(end);

		if (sorts != null) {
			searchContext.setSorts(sorts);
		}

		searchContext.setStart(start);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}

}