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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CPDefinition. This utility wraps
 * {@link com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPDefinitionService
 * @see com.liferay.commerce.product.service.base.CPDefinitionServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl
 * @generated
 */
@ProviderType
public class CPDefinitionServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.product.model.CPDefinition addCPDefinition(
		java.lang.String baseSKU,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String productTypeName, java.lang.String ddmStructureKey,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCPDefinition(baseSKU, titleMap, descriptionMap,
			productTypeName, ddmStructureKey, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinition deleteCPDefinition(
		com.liferay.commerce.product.model.CPDefinition cpDefinition)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCPDefinition(cpDefinition);
	}

	public static com.liferay.commerce.product.model.CPDefinition deleteCPDefinition(
		long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCPDefinition(cpDefinitionId);
	}

	public static com.liferay.commerce.product.model.CPDefinition fetchCPDefinition(
		long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCPDefinition(cpDefinitionId);
	}

	public static com.liferay.commerce.product.model.CPDefinition getCPDefinition(
		long cpDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPDefinition(cpDefinitionId);
	}

	public static com.liferay.commerce.product.model.CPDefinition updateCPDefinition(
		long cpDefinitionId, java.lang.String baseSKU,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String productTypeName, java.lang.String ddmStructureKey,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCPDefinition(cpDefinitionId, baseSKU, titleMap,
			descriptionMap, productTypeName, ddmStructureKey, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	public static com.liferay.commerce.product.model.CPDefinition updateStatus(
		long userId, long cpDefinitionId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, cpDefinitionId, status,
			serviceContext, workflowContext);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.product.model.CPDefinition> searchCPDefinitions(
		long companyId, long groupId, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCPDefinitions(companyId, groupId, keywords, start,
			end, sort);
	}

	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext) {
		return getService().search(searchContext);
	}

	public static int getCPDefinitionsCount(long groupId) {
		return getService().getCPDefinitionsCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.commerce.product.model.CPDefinition> getCPDefinitions(
		long groupId, int start, int end) {
		return getService().getCPDefinitions(groupId, start, end);
	}

	public static java.util.List<com.liferay.commerce.product.model.CPDefinition> getCPDefinitions(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPDefinition> orderByComparator) {
		return getService()
				   .getCPDefinitions(groupId, start, end, orderByComparator);
	}

	public static CPDefinitionService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPDefinitionService, CPDefinitionService> _serviceTracker =
		ServiceTrackerFactory.open(CPDefinitionService.class);
}