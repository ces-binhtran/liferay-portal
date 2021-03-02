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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for CPDefinition. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CPDefinitionService
 * @generated
 */
public class CPDefinitionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CPDefinition addCPDefinition(
			long groupId, long userId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> shortDescriptionMap,
			Map<java.util.Locale, String> descriptionMap,
			Map<java.util.Locale, String> urlTitleMap,
			Map<java.util.Locale, String> metaTitleMap,
			Map<java.util.Locale, String> metaDescriptionMap,
			Map<java.util.Locale, String> metaKeywordsMap,
			String productTypeName, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsUnicodeProperties,
			long maxSubscriptionCycles, boolean deliverySubscriptionEnabled,
			int deliverySubscriptionLength, String deliverySubscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				deliverySubscriptionTypeSettingsUnicodeProperties,
			long deliveryMaxSubscriptionCycles, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPDefinition(
			groupId, userId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			productTypeName, ignoreSKUCombinations, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			cpTaxCategoryId, taxExempt, telcoOrElectronics, ddmStructureKey,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, defaultSku, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsUnicodeProperties, maxSubscriptionCycles,
			deliverySubscriptionEnabled, deliverySubscriptionLength,
			deliverySubscriptionType,
			deliverySubscriptionTypeSettingsUnicodeProperties,
			deliveryMaxSubscriptionCycles, externalReferenceCode,
			serviceContext);
	}

	public static CPDefinition addCPDefinition(
			long groupId, long userId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> shortDescriptionMap,
			Map<java.util.Locale, String> descriptionMap,
			Map<java.util.Locale, String> urlTitleMap,
			Map<java.util.Locale, String> metaTitleMap,
			Map<java.util.Locale, String> metaDescriptionMap,
			Map<java.util.Locale, String> metaKeywordsMap,
			String productTypeName, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsUnicodeProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCPDefinition(
			groupId, userId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			productTypeName, ignoreSKUCombinations, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			cpTaxCategoryId, taxExempt, telcoOrElectronics, ddmStructureKey,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, defaultSku, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsUnicodeProperties, maxSubscriptionCycles,
			externalReferenceCode, serviceContext);
	}

	public static CPDefinition copyCPDefinition(
			long cpDefinitionId, long groupId)
		throws PortalException {

		return getService().copyCPDefinition(cpDefinitionId, groupId);
	}

	public static void deleteAssetCategoryCPDefinition(
			long cpDefinitionId, long categoryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().deleteAssetCategoryCPDefinition(
			cpDefinitionId, categoryId, serviceContext);
	}

	public static void deleteCPDefinition(long cpDefinitionId)
		throws PortalException {

		getService().deleteCPDefinition(cpDefinitionId);
	}

	public static CPDefinition fetchCPDefinition(long cpDefinitionId)
		throws PortalException {

		return getService().fetchCPDefinition(cpDefinitionId);
	}

	public static CPDefinition fetchCPDefinitionByCProductExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().fetchCPDefinitionByCProductExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static CPDefinition fetchCPDefinitionByCProductId(long cProductId)
		throws PortalException {

		return getService().fetchCPDefinitionByCProductId(cProductId);
	}

	public static CPDefinition getCPDefinition(long cpDefinitionId)
		throws PortalException {

		return getService().getCPDefinition(cpDefinitionId);
	}

	public static List<CPDefinition> getCPDefinitions(
			long groupId, int status, int start, int end,
			OrderByComparator<CPDefinition> orderByComparator)
		throws PortalException {

		return getService().getCPDefinitions(
			groupId, status, start, end, orderByComparator);
	}

	public static int getCPDefinitionsCount(long groupId, int status)
		throws PortalException {

		return getService().getCPDefinitionsCount(groupId, status);
	}

	public static String getLayoutUuid(long cpDefinitionId)
		throws PortalException {

		return getService().getLayoutUuid(cpDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static String getUrlTitleMapAsXML(long cpDefinitionId)
		throws PortalException {

		return getService().getUrlTitleMapAsXML(cpDefinitionId);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CPDefinition> searchCPDefinitions(
				long companyId, String keywords, int status, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchCPDefinitions(
			companyId, keywords, status, start, end, sort);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CPDefinition> searchCPDefinitions(
				long companyId, String keywords, String filterFields,
				String filterValues, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchCPDefinitions(
			companyId, keywords, filterFields, filterValues, start, end, sort);
	}

	public static CPDefinition updateCPDefinition(
			long cpDefinitionId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> shortDescriptionMap,
			Map<java.util.Locale, String> descriptionMap,
			Map<java.util.Locale, String> urlTitleMap,
			Map<java.util.Locale, String> metaTitleMap,
			Map<java.util.Locale, String> metaDescriptionMap,
			Map<java.util.Locale, String> metaKeywordsMap,
			boolean ignoreSKUCombinations, String ddmStructureKey,
			boolean published, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPDefinition(
			cpDefinitionId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			ignoreSKUCombinations, ddmStructureKey, published, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	public static CPDefinition updateCPDefinitionAccountGroupFilter(
			long cpDefinitionId, boolean enable)
		throws PortalException {

		return getService().updateCPDefinitionAccountGroupFilter(
			cpDefinitionId, enable);
	}

	public static CPDefinition updateCPDefinitionCategorization(
			long cpDefinitionId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCPDefinitionCategorization(
			cpDefinitionId, serviceContext);
	}

	public static CPDefinition updateCPDefinitionChannelFilter(
			long cpDefinitionId, boolean enable)
		throws PortalException {

		return getService().updateCPDefinitionChannelFilter(
			cpDefinitionId, enable);
	}

	public static void updateCPDisplayLayout(
			long cpDefinitionId, String layoutUuid,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().updateCPDisplayLayout(
			cpDefinitionId, layoutUuid, serviceContext);
	}

	public static CPDefinition updateShippingInfo(
			long cpDefinitionId, boolean shippable, boolean freeShipping,
			boolean shipSeparately, double shippingExtraPrice, double width,
			double height, double depth, double weight,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateShippingInfo(
			cpDefinitionId, shippable, freeShipping, shipSeparately,
			shippingExtraPrice, width, height, depth, weight, serviceContext);
	}

	public static CPDefinition updateStatus(
			long userId, long cpDefinitionId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		return getService().updateStatus(
			userId, cpDefinitionId, status, serviceContext, workflowContext);
	}

	public static CPDefinition updateSubscriptionInfo(
			long cpDefinitionId, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsUnicodeProperties,
			long maxSubscriptionCycles, boolean deliverySubscriptionEnabled,
			int deliverySubscriptionLength, String deliverySubscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				deliverySubscriptionTypeSettingsUnicodeProperties,
			long deliveryMaxSubscriptionCycles)
		throws PortalException {

		return getService().updateSubscriptionInfo(
			cpDefinitionId, subscriptionEnabled, subscriptionLength,
			subscriptionType, subscriptionTypeSettingsUnicodeProperties,
			maxSubscriptionCycles, deliverySubscriptionEnabled,
			deliverySubscriptionLength, deliverySubscriptionType,
			deliverySubscriptionTypeSettingsUnicodeProperties,
			deliveryMaxSubscriptionCycles);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static CPDefinition updateSubscriptionInfo(
			long cpDefinitionId, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsUnicodeProperties,
			long maxSubscriptionCycles,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateSubscriptionInfo(
			cpDefinitionId, subscriptionEnabled, subscriptionLength,
			subscriptionType, subscriptionTypeSettingsUnicodeProperties,
			maxSubscriptionCycles, serviceContext);
	}

	public static CPDefinition updateTaxCategoryInfo(
			long cpDefinitionId, long cpTaxCategoryId, boolean taxExempt,
			boolean telcoOrElectronics)
		throws PortalException {

		return getService().updateTaxCategoryInfo(
			cpDefinitionId, cpTaxCategoryId, taxExempt, telcoOrElectronics);
	}

	public static CPDefinition upsertCPDefinition(
			long groupId, long userId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> shortDescriptionMap,
			Map<java.util.Locale, String> descriptionMap,
			Map<java.util.Locale, String> urlTitleMap,
			Map<java.util.Locale, String> metaTitleMap,
			Map<java.util.Locale, String> metaDescriptionMap,
			Map<java.util.Locale, String> metaKeywordsMap,
			String productTypeName, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsUnicodeProperties,
			long maxSubscriptionCycles, boolean deliverySubscriptionEnabled,
			int deliverySubscriptionLength, String deliverySubscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				deliverySubscriptionTypeSettingsUnicodeProperties,
			long deliveryMaxSubscriptionCycles, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().upsertCPDefinition(
			groupId, userId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			productTypeName, ignoreSKUCombinations, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			cpTaxCategoryId, taxExempt, telcoOrElectronics, ddmStructureKey,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, defaultSku, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsUnicodeProperties, maxSubscriptionCycles,
			deliverySubscriptionEnabled, deliverySubscriptionLength,
			deliverySubscriptionType,
			deliverySubscriptionTypeSettingsUnicodeProperties,
			deliveryMaxSubscriptionCycles, externalReferenceCode,
			serviceContext);
	}

	public static CPDefinition upsertCPDefinition(
			long groupId, long userId, Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> shortDescriptionMap,
			Map<java.util.Locale, String> descriptionMap,
			Map<java.util.Locale, String> urlTitleMap,
			Map<java.util.Locale, String> metaTitleMap,
			Map<java.util.Locale, String> metaDescriptionMap,
			Map<java.util.Locale, String> metaKeywordsMap,
			String productTypeName, boolean ignoreSKUCombinations,
			boolean shippable, boolean freeShipping, boolean shipSeparately,
			double shippingExtraPrice, double width, double height,
			double depth, double weight, long cpTaxCategoryId,
			boolean taxExempt, boolean telcoOrElectronics,
			String ddmStructureKey, boolean published, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String defaultSku, boolean subscriptionEnabled,
			int subscriptionLength, String subscriptionType,
			com.liferay.portal.kernel.util.UnicodeProperties
				subscriptionTypeSettingsUnicodeProperties,
			long maxSubscriptionCycles, String externalReferenceCode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().upsertCPDefinition(
			groupId, userId, nameMap, shortDescriptionMap, descriptionMap,
			urlTitleMap, metaTitleMap, metaDescriptionMap, metaKeywordsMap,
			productTypeName, ignoreSKUCombinations, shippable, freeShipping,
			shipSeparately, shippingExtraPrice, width, height, depth, weight,
			cpTaxCategoryId, taxExempt, telcoOrElectronics, ddmStructureKey,
			published, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, defaultSku, subscriptionEnabled,
			subscriptionLength, subscriptionType,
			subscriptionTypeSettingsUnicodeProperties, maxSubscriptionCycles,
			externalReferenceCode, serviceContext);
	}

	public static CPDefinitionService getService() {
		return _service;
	}

	private static volatile CPDefinitionService _service;

}