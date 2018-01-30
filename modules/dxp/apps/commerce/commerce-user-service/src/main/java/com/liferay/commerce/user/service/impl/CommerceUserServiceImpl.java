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

package com.liferay.commerce.user.service.impl;

import com.liferay.commerce.user.service.base.CommerceUserServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.impl.AddressImpl;
import com.liferay.portal.model.impl.EmailAddressImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CommerceUserServiceImpl extends CommerceUserServiceBaseImpl {

	public Organization addOrganization(
			long parentOrganization, String name, String type,
			ServiceContext serviceContext)
		throws PortalException {

		return organizationLocalService.addOrganization(
			serviceContext.getUserId(), parentOrganization, name, type, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, serviceContext);
	}

	@Override
	public Address getOrganizationPrimaryAddress(long organizationId)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organization, ActionKeys.VIEW);

		List<Address> addresses = organization.getAddresses();

		for (Address address : addresses) {
			if (address.isPrimary()) {
				return address;
			}
		}

		return new AddressImpl();
	}

	@Override
	public EmailAddress getOrganizationPrimaryEmailAddress(long organizationId)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organization, ActionKeys.VIEW);

		List<EmailAddress> emailAddresses =
			emailAddressService.getEmailAddresses(
				Organization.class.getName(), organizationId);

		for (EmailAddress emailAddress : emailAddresses) {
			if (emailAddress.isPrimary()) {
				return emailAddress;
			}
		}

		return new EmailAddressImpl();
	}

	@Override
	public BaseModelSearchResult<Organization> searchOrganizations(
			Organization organization, String type, String keywords, int start,
			int end, Sort[] sorts)
		throws PortalException {

		List<Organization> organizations = new ArrayList<>();

		Indexer<Organization> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			Organization.class);

		SearchContext searchContext = buildSearchContext(
			organization, keywords, type, start, end, sorts);

		Hits hits = indexer.search(searchContext);

		Document[] documents = hits.getDocs();

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ORGANIZATION_ID));

			organizations.add(organizationService.getOrganization(classPK));
		}

		return new BaseModelSearchResult<>(organizations, hits.getLength());
	}

	protected SearchContext buildSearchContext(
		Organization organization, String keywords, String type, int start,
		int end, Sort[] sorts) {

		SearchContext searchContext = new SearchContext();

		boolean andSearch = true;

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("treePath", organization.getTreePath());

		List<Long> excludedOrganizationIds = Collections.singletonList(
			organization.getOrganizationId());

		params.put("excludedOrganizationIds", excludedOrganizationIds);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put("params", params);

		attributes.put(
			"parentOrganizationId",
			String.valueOf(OrganizationConstants.ANY_PARENT_ORGANIZATION_ID));

		if (Validator.isNotNull(type)) {
			attributes.put("type", "account");
		}

		if (Validator.isNotNull(keywords)) {
			attributes.put("city", keywords);
			attributes.put("country", keywords);
			attributes.put("name", keywords);
			attributes.put("region", keywords);
			attributes.put("street", keywords);
			attributes.put("type", keywords);
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