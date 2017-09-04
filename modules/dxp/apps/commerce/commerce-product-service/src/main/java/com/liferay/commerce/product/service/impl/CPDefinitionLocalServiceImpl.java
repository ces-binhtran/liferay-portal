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

package com.liferay.commerce.product.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.exception.CPDefinitionDisplayDateException;
import com.liferay.commerce.product.exception.CPDefinitionExpirationDateException;
import com.liferay.commerce.product.exception.CPDefinitionProductTypeNameException;
import com.liferay.commerce.product.exception.CPDefinitionStatusException;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLocalization;
import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.base.CPDefinitionLocalServiceBaseImpl;
import com.liferay.commerce.product.type.CPType;
import com.liferay.commerce.product.type.CPTypeServicesTracker;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.trash.kernel.exception.RestoreEntryException;
import com.liferay.trash.kernel.exception.TrashEntryException;
import com.liferay.trash.kernel.model.TrashEntry;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionLocalServiceImpl
	extends CPDefinitionLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> titleMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaKeywordsMap,
			Map<Locale, String> metaDescriptionMap, String layoutUuid,
			String productTypeName, int minCartQuantity, int maxCartQuantity,
			String allowedCartQuantities, int multipleCartQuantity,
			double width, double height, double depth, double weight,
			String ddmStructureKey, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPDefinitionDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPDefinitionExpirationDateException.class);
		}

		validate(groupId, ddmStructureKey, productTypeName);

		long cpDefinitionId = counterLocalService.increment();

		CPDefinition cpDefinition = cpDefinitionPersistence.create(
			cpDefinitionId);

		Locale locale = LocaleUtil.getSiteDefault();

		cpDefinition.setUuid(serviceContext.getUuid());
		cpDefinition.setGroupId(groupId);
		cpDefinition.setCompanyId(user.getCompanyId());
		cpDefinition.setUserId(user.getUserId());
		cpDefinition.setUserName(user.getFullName());
		cpDefinition.setProductTypeName(productTypeName);
		cpDefinition.setMinCartQuantity(minCartQuantity);
		cpDefinition.setMaxCartQuantity(maxCartQuantity);
		cpDefinition.setAllowedCartQuantities(allowedCartQuantities);
		cpDefinition.setMultipleCartQuantity(multipleCartQuantity);
		cpDefinition.setWidth(width);
		cpDefinition.setHeight(height);
		cpDefinition.setDepth(depth);
		cpDefinition.setWeight(weight);
		cpDefinition.setDDMStructureKey(ddmStructureKey);
		cpDefinition.setDefaultLanguageId(LocaleUtil.toLanguageId(locale));
		cpDefinition.setDisplayDate(displayDate);
		cpDefinition.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpDefinition.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpDefinition.setStatusByUserId(user.getUserId());
		cpDefinition.setStatusDate(serviceContext.getModifiedDate(now));
		cpDefinition.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionPersistence.update(cpDefinition);

		// Commerce product definition localization

		_addCPDefinitionLocalizedFields(
			user.getCompanyId(), cpDefinitionId, titleMap, shortDescriptionMap,
			descriptionMap, metaTitleMap, metaKeywordsMap, metaDescriptionMap);

		// Commerce product instance

		cpInstanceLocalService.addCPInstance(
			cpDefinitionId, CPConstants.INSTANCE_DEFAULT_SKU, null, null, null,
			false, minCartQuantity, maxCartQuantity, allowedCartQuantities,
			multipleCartQuantity, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);

		// Commerce product friendly URL

		urlTitleMap = _getUniqueUrlTitles(cpDefinition, titleMap);

		cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
			groupId, serviceContext.getCompanyId(), CPDefinition.class,
			cpDefinitionId, urlTitleMap);

		// Commerce product display layout

		cpDisplayLayoutLocalService.addCPDisplayLayout(
			CPDefinition.class, cpDefinitionId, layoutUuid, serviceContext);

		// Resources

		resourceLocalService.addModelResources(cpDefinition, serviceContext);

		// Asset

		updateAsset(
			user.getUserId(), cpDefinition,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpDefinition, serviceContext);
	}

	@Override
	public CPDefinition addCPDefinition(
			Map<Locale, String> titleMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, String layoutUuid,
			String productTypeName, int minCartQuantity, int maxCartQuantity,
			String allowedCartQuantities, int multipleCartQuantity,
			String ddmStructureKey, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return addCPDefinition(
			titleMap, shortDescriptionMap, descriptionMap, null, null, null,
			null, layoutUuid, productTypeName, minCartQuantity, maxCartQuantity,
			allowedCartQuantities, multipleCartQuantity, 0, 0, 0, 0,
			ddmStructureKey, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, serviceContext);
	}

	@Override
	public void checkCPDefinitions() throws PortalException {
		checkCPDefinitionsByDisplayDate();
		checkCPDefinitionsByExpirationDate();
	}

	public void checkCPDefinitionStatus(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		if (cpDefinition == null) {
			return;
		}

		List<CPInstance> cpInstances = cpInstanceLocalService.getCPInstances(
			cpDefinitionId);

		if (cpInstances.isEmpty()) {
			cpDefinitionLocalService.updateStatus(
				cpDefinition.getUserId(), cpDefinitionId,
				WorkflowConstants.STATUS_INCOMPLETE, new ServiceContext(),
				new HashMap<>());
		}
	}

	@Override
	public void deleteAssetCategoryCPDefinition(
			long cpDefinitionId, long categoryId)
		throws PortalException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			CPDefinition.class.getName(), cpDefinitionId);

		assetEntryLocalService.deleteAssetCategoryAssetEntry(
			categoryId, assetEntry.getEntryId());
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDefinition deleteCPDefinition(CPDefinition cpDefinition)
		throws PortalException {

		// Commerce product definition

		cpDefinitionPersistence.remove(cpDefinition);

		// Commerce product definition localization

		cpDefinitionLocalizationPersistence.removeByCPDefinitionId(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition option rels

		cpDefinitionOptionRelLocalService.deleteCPDefinitionOptionRels(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition specification option values

		cpDefinitionSpecificationOptionValueLocalService.
			deleteCPDefinitionSpecificationOptionValues(
				cpDefinition.getCPDefinitionId());

		// Commerce product instances

		cpInstanceLocalService.deleteCPInstances(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition attachment file entries

		cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntries(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		// Commerce product definition links

		cpDefinitionLinkLocalService.deleteCPDefinitionLinks(
			cpDefinition.getCPDefinitionId());

		// Commerce product type

		CPType cpType = cpTypeServicesTracker.getCPType(
			cpDefinition.getProductTypeName());

		if (cpType != null) {
			cpType.deleteCPDefinition(cpDefinition.getCPDefinitionId());
		}

		// Commerce product friendly URL entries

		cpFriendlyURLEntryLocalService.deleteCPFriendlyURLEntries(
			cpDefinition.getGroupId(), cpDefinition.getCompanyId(),
			CPDefinition.class, cpDefinition.getCPDefinitionId());

		// Commerce product display layout

		cpDisplayLayoutLocalService.deleteCPDisplayLayout(
			CPDefinition.class, cpDefinition.getCPDefinitionId());

		// Resources

		resourceLocalService.deleteResource(
			cpDefinition.getCompanyId(), CPDefinition.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			cpDefinition.getCPDefinitionId());

		// Asset

		assetEntryLocalService.deleteEntry(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		// Expando

		expandoRowLocalService.deleteRows(cpDefinition.getCPDefinitionId());

		// Trash

		trashEntryLocalService.deleteEntry(
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			cpDefinition.getCompanyId(), cpDefinition.getGroupId(),
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

		return cpDefinition;
	}

	@Override
	public CPDefinition deleteCPDefinition(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		return cpDefinitionLocalService.deleteCPDefinition(cpDefinition);
	}

	@Override
	public void deleteCPDefinitions(long groupId) throws PortalException {
		List<CPDefinition> cpDefinitions =
			cpDefinitionPersistence.findByGroupId(groupId);

		for (CPDefinition cpDefinition : cpDefinitions) {
			cpDefinitionLocalService.deleteCPDefinition(cpDefinition);
		}
	}

	@Override
	public Map<Locale, String> getCPDefinitionDescriptionMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationDescriptionMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationDescriptionMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getDescription());
		}

		return cpDefinitionLocalizationDescriptionMap;
	}

	@Override
	public List<String> getCPDefinitionLocalizationLanguageIds(
		long cpDefinitionId) {

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		List<String> availableLanguageIds = new ArrayList<>();

		for (CPDefinitionLocalization
				cpDefinitionLocalization :
					cpDefinitionLocalizationList) {

			availableLanguageIds.add(cpDefinitionLocalization.getLanguageId());
		}

		return availableLanguageIds;
	}

	@Override
	public Map<Locale, String> getCPDefinitionMetaDescriptionMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationMetaDescriptionMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationMetaDescriptionMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getMetaDescription());
		}

		return cpDefinitionLocalizationMetaDescriptionMap;
	}

	@Override
	public Map<Locale, String> getCPDefinitionMetaKeywordsMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationMetaKeywordsMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationMetaKeywordsMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getMetaKeywords());
		}

		return cpDefinitionLocalizationMetaKeywordsMap;
	}

	@Override
	public Map<Locale, String> getCPDefinitionMetaTitleMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationMetaTitleMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationMetaTitleMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getMetaTitle());
		}

		return cpDefinitionLocalizationMetaTitleMap;
	}

	@Override
	public List<CPDefinition> getCPDefinitionsByCategoryId(
		long categoryId, int start, int end) {

		List<CPDefinition> cpDefinitions = new ArrayList<>();

		long[] categoryIds = {categoryId};

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setClassName(CPDefinition.class.getName());
		assetEntryQuery.setAllCategoryIds(categoryIds);
		assetEntryQuery.setStart(start);
		assetEntryQuery.setEnd(end);

		List<AssetEntry> assetEntries = assetEntryLocalService.getEntries(
			assetEntryQuery);

		for (AssetEntry assetEntry : assetEntries) {
			long classPK = assetEntry.getClassPK();

			CPDefinition cpDefinition = fetchCPDefinition(classPK);

			if (cpDefinition != null) {
				cpDefinitions.add(cpDefinition);
			}
		}

		return cpDefinitions;
	}

	@Override
	public int getCPDefinitionsCountByCategoryId(long categoryId) {
		return assetEntryLocalService.getAssetCategoryAssetEntriesCount(
			categoryId);
	}

	@Override
	public Map<Locale, String> getCPDefinitionShortDescriptionMap(
		long cpDefinitionId) {

		Map<Locale, String> cpDefinitionLocalizationShortDescriptionMap =
			new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationShortDescriptionMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getShortDescription());
		}

		return cpDefinitionLocalizationShortDescriptionMap;
	}

	@Override
	public Map<Locale, String> getCPDefinitionTitleMap(long cpDefinitionId) {
		Map<Locale, String> cpDefinitionLocalizationTitleMap = new HashMap<>();

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionId(
				cpDefinitionId);

		for (CPDefinitionLocalization cpDefinitionLocalization :
				cpDefinitionLocalizationList) {

			cpDefinitionLocalizationTitleMap.put(
				LocaleUtil.fromLanguageId(
					cpDefinitionLocalization.getLanguageId()),
				cpDefinitionLocalization.getTitle());
		}

		return cpDefinitionLocalizationTitleMap;
	}

	@Override
	public CPAttachmentFileEntry getDefaultImage(long cpDefinitionId)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryLocalService.getCPAttachmentFileEntries(
				classNameId, cpDefinitionId,
				CPConstants.ATTACHMENT_FILE_ENTRY_TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED, 0, 1);

		if (cpAttachmentFileEntries.isEmpty()) {
			return null;
		}

		return cpAttachmentFileEntries.get(0);
	}

	@Override
	public String getLayoutUuid(long cpDefinitionId) {
		CPDisplayLayout cpDisplayLayout =
			cpDisplayLayoutLocalService.fetchCPDisplayLayout(
				CPDefinition.class, cpDefinitionId);

		if (cpDisplayLayout == null) {
			return null;
		}

		return cpDisplayLayout.getLayoutUuid();
	}

	@Override
	public Map<Locale, String> getUrlTitleMap(long cpDefinitionId) {
		CPDefinition cpDefinition = cpDefinitionPersistence.fetchByPrimaryKey(
			cpDefinitionId);

		if (cpDefinition == null) {
			return Collections.emptyMap();
		}

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		return cpFriendlyURLEntryLocalService.getUrlTitleMap(
			cpDefinition.getGroupId(), cpDefinition.getCompanyId(), classNameId,
			cpDefinition.getCPDefinitionId());
	}

	@Override
	public String getUrlTitleMapAsXML(long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LanguageUtil.getLanguageId(defaultLocale);

		return cpFriendlyURLEntryLocalService.getUrlTitleMapAsXML(
			cpDefinition.getGroupId(), cpDefinition.getCompanyId(), classNameId,
			cpDefinition.getCPDefinitionId(), defaultLanguageId);
	}

	@Override
	public void moveCPDefinitionsToTrash(long groupId, long userId)
		throws PortalException {

		List<CPDefinition> cpDefinitions =
			cpDefinitionPersistence.findByGroupId(groupId);

		for (CPDefinition cpDefinition : cpDefinitions) {
			cpDefinitionLocalService.moveCPDefinitionToTrash(
				userId, cpDefinition);
		}
	}

	/**
	 * Moves the commerce product definition to the recycle bin.
	 *
	 * @param  userId the primary key of the user moving the commerce product definition
	 * @param  cpDefinition the commerce product definition to be moved
	 * @return the moved commerce product definition
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition moveCPDefinitionToTrash(
			long userId, CPDefinition cpDefinition)
		throws PortalException {

		// Commerce product definition

		if (cpDefinition.isInTrash()) {
			throw new TrashEntryException();
		}

		int oldStatus = cpDefinition.getStatus();

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			cpDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);

			cpDefinitionPersistence.update(cpDefinition);
		}

		cpDefinition = updateStatus(
			userId, cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_IN_TRASH, new ServiceContext(),
			new HashMap<>());

		// Workflow

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
				cpDefinition.getCompanyId(), cpDefinition.getGroupId(),
				CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());
		}

		return cpDefinition;
	}

	/**
	 * Moves the commerce product definition with the ID to the recycle bin.
	 *
	 * @param  userId the primary key of the user moving the commerce product definition
	 * @param  cpDefinitionId the primary key of the commerce product definition to be moved
	 * @return the moved commerce product definition
	 */
	@Override
	public CPDefinition moveCPDefinitionToTrash(
			long userId, long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		return cpDefinitionLocalService.moveCPDefinitionToTrash(
			userId, cpDefinition);
	}

	/**
	 * Restores the commerce product definition with the ID from the recycle bin.
	 *
	 * @param  userId the primary key of the user restoring the commerce product definition
	 * @param  cpDefinitionId the primary key of the commerce product definition to be restored
	 * @return the restored commerce product definition from the recycle bin
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition restoreCPDefinitionFromTrash(
			long userId, long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		if (!cpDefinition.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		TrashEntry trashEntry = trashEntryLocalService.getEntry(
			CPDefinition.class.getName(), cpDefinitionId);

		cpDefinition = updateStatus(
			userId, cpDefinitionId, trashEntry.getStatus(),
			new ServiceContext(), new HashMap<String, Serializable>());

		return cpDefinition;
	}

	@Override
	public Hits search(SearchContext searchContext) {
		try {
			Indexer<CPDefinition> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(CPDefinition.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			long companyId, long groupId, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		SearchContext searchContext = buildSearchContext(
			companyId, groupId, keywords, status, start, end, sort);

		return searchCPDefinitions(searchContext);
	}

	@Override
	public void updateAsset(
			long userId, CPDefinition cpDefinition, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, cpDefinition.getGroupId(), cpDefinition.getCreateDate(),
			cpDefinition.getModifiedDate(), CPDefinition.class.getName(),
			cpDefinition.getCPDefinitionId(), cpDefinition.getUuid(), 0,
			assetCategoryIds, assetTagNames, true, true, null, null,
			cpDefinition.getCreateDate(), null, ContentTypes.TEXT_PLAIN,
			cpDefinition.getTitleMapAsXML(),
			cpDefinition.getDescriptionMapAsXML(), null, null, null, 0, 0,
			priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateCPDefinition(
			long cpDefinitionId, Map<Locale, String> titleMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaKeywordsMap,
			Map<Locale, String> metaDescriptionMap, String layoutUuid,
			int minCartQuantity, int maxCartQuantity,
			String allowedCartQuantities, int multipleCartQuantity,
			double width, double height, double depth, double weight,
			String ddmStructureKey, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		// Commerce product definition

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();
		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		Date displayDate = null;
		Date expirationDate = null;
		Date now = new Date();

		displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, user.getTimeZone(),
			CPDefinitionDisplayDateException.class);

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				CPDefinitionExpirationDateException.class);
		}

		validate(groupId, ddmStructureKey, cpDefinition.getProductTypeName());

		cpDefinition.setMinCartQuantity(minCartQuantity);
		cpDefinition.setMaxCartQuantity(maxCartQuantity);
		cpDefinition.setAllowedCartQuantities(allowedCartQuantities);
		cpDefinition.setMultipleCartQuantity(multipleCartQuantity);
		cpDefinition.setWidth(width);
		cpDefinition.setHeight(height);
		cpDefinition.setDepth(depth);
		cpDefinition.setWeight(weight);
		cpDefinition.setDDMStructureKey(ddmStructureKey);
		cpDefinition.setDisplayDate(displayDate);
		cpDefinition.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpDefinition.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpDefinition.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpDefinition.setStatusByUserId(user.getUserId());
		cpDefinition.setStatusDate(serviceContext.getModifiedDate(now));
		cpDefinition.setExpandoBridgeAttributes(serviceContext);

		cpDefinitionPersistence.update(cpDefinition);

		if (Validator.isNull(urlTitleMap)) {
			urlTitleMap = _getUniqueUrlTitles(cpDefinition, urlTitleMap);
		}

		// Commerce product definition localization

		_updateCPDefinitionLocalizedFields(
			cpDefinition.getCompanyId(), cpDefinition.getCPDefinitionId(),
			titleMap, shortDescriptionMap, descriptionMap, metaTitleMap,
			metaKeywordsMap, metaDescriptionMap);

		// Commerce product friendly URL entries

		cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
			groupId, serviceContext.getCompanyId(), CPDefinition.class,
			cpDefinitionId, urlTitleMap);

		// Commerce product display layout

		cpDisplayLayoutLocalService.addCPDisplayLayout(
			CPDefinition.class, cpDefinitionId, layoutUuid, serviceContext);

		// Asset

		updateAsset(
			user.getUserId(), cpDefinition,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds(),
			serviceContext.getAssetPriority());

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpDefinition, serviceContext);
	}

	@Override
	public CPDefinition updateCPDefinition(
			long cpDefinitionId, Map<Locale, String> titleMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap, String layoutUuid,
			int minCartQuantity, int maxCartQuantity,
			String allowedCartQuantities, int multipleCartQuantity,
			String ddmStructureKey, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		return updateCPDefinition(
			cpDefinitionId, titleMap, shortDescriptionMap, descriptionMap,
			cpDefinition.getUrlTitleMap(), cpDefinition.getMetaTitleMap(),
			cpDefinition.getMetaKeywordsMap(),
			cpDefinition.getMetaDescriptionMap(), layoutUuid, minCartQuantity,
			maxCartQuantity, allowedCartQuantities, multipleCartQuantity,
			cpDefinition.getWidth(), cpDefinition.getHeight(),
			cpDefinition.getDepth(), cpDefinition.getWeight(), ddmStructureKey,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public CPDefinition updateSEOInfo(
			long cpDefinitionId, Map<Locale, String> urlTitleMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaKeywordsMap,
			Map<Locale, String> metaDescriptionMap,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		// Commerce product definition localization

		_updateCPDefinitionLocalizedFields(
			cpDefinition.getCompanyId(), cpDefinitionId,
			cpDefinition.getTitleMap(), cpDefinition.getShortDescriptionMap(),
			cpDefinition.getDescriptionMap(), metaTitleMap, metaKeywordsMap,
			metaDescriptionMap);

		if (Validator.isNull(urlTitleMap)) {
			urlTitleMap = _getUniqueUrlTitles(cpDefinition, urlTitleMap);
		}

		// Commerce product friendly URL entries

		cpFriendlyURLEntryLocalService.addCPFriendlyURLEntries(
			cpDefinition.getGroupId(), cpDefinition.getCompanyId(),
			CPDefinition.class, cpDefinitionId, urlTitleMap);

		Date now = new Date();

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpDefinition.setModifiedDate(modifiedDate);

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateShippingInfo(
			long cpDefinitionId, double width, double height, double depth,
			double weight, ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		Date now = new Date();

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpDefinition.setModifiedDate(modifiedDate);

		cpDefinition.setWidth(width);
		cpDefinition.setHeight(height);
		cpDefinition.setDepth(depth);
		cpDefinition.setWeight(weight);

		return cpDefinitionPersistence.update(cpDefinition);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition updateStatus(
			long userId, long cpDefinitionId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		// Commerce product definition

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CPDefinition cpDefinition = cpDefinitionPersistence.findByPrimaryKey(
			cpDefinitionId);

		int oldStatus = cpDefinition.getStatus();

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(cpDefinition.getDisplayDate() != null) &&
			now.before(cpDefinition.getDisplayDate())) {

			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpDefinition.setModifiedDate(modifiedDate);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			Date expirationDate = cpDefinition.getExpirationDate();

			if ((expirationDate != null) && expirationDate.before(now)) {
				cpDefinition.setExpirationDate(null);
			}

			List<CPInstance> cpInstances =
				cpInstanceLocalService.getCPInstances(cpDefinitionId);

			if (cpInstances.isEmpty()) {
				throw new CPDefinitionStatusException();
			}
		}

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			cpDefinition.setExpirationDate(now);
		}

		cpDefinition.setStatus(status);
		cpDefinition.setStatusByUserId(user.getUserId());
		cpDefinition.setStatusByUserName(user.getFullName());
		cpDefinition.setStatusDate(modifiedDate);

		cpDefinitionPersistence.update(cpDefinition);

		if (status == WorkflowConstants.STATUS_APPROVED) {

			// Asset

			assetEntryLocalService.updateEntry(
				CPDefinition.class.getName(), cpDefinition.getCPDefinitionId(),
				cpDefinition.getDisplayDate(), cpDefinition.getExpirationDate(),
				true, true);

			// Trash

			if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.deleteEntry(
					CPDefinition.class.getName(), cpDefinitionId);
			}
		}
		else {

			// Asset

			assetEntryLocalService.updateVisible(
				CPDefinition.class.getName(), cpDefinitionId, false);

			// Trash

			if (status == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.addTrashEntry(
					userId, cpDefinition.getGroupId(),
					CPDefinition.class.getName(),
					cpDefinition.getCPDefinitionId(), cpDefinition.getUuid(),
					null, oldStatus, null, null);
			}
			else if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.deleteEntry(
					CPDefinition.class.getName(), cpDefinitionId);
			}
		}

		return cpDefinition;
	}

	protected SearchContext buildSearchContext(
		long companyId, long groupId, String keywords, int status, int start,
		int end, Sort sort) {

		SearchContext searchContext = new SearchContext();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("keywords", keywords);

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, keywords);
		attributes.put(Field.TITLE, keywords);
		attributes.put(Field.DESCRIPTION, keywords);
		attributes.put(Field.CONTENT, keywords);
		attributes.put(Field.STATUS, status);
		attributes.put("params", params);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {groupId});

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		QueryConfig queryConfig = new QueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		searchContext.setQueryConfig(queryConfig);

		if (sort != null) {
			searchContext.setSorts(sort);
		}

		return searchContext;
	}

	protected void checkCPDefinitionsByDisplayDate() throws PortalException {
		List<CPDefinition> cpDefinitions = cpDefinitionPersistence.findByLtD_S(
			new Date(), WorkflowConstants.STATUS_SCHEDULED);

		for (CPDefinition cpDefinition : cpDefinitions) {
			long userId = PortalUtil.getValidUserId(
				cpDefinition.getCompanyId(), cpDefinition.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCommand(Constants.UPDATE);
			serviceContext.setScopeGroupId(cpDefinition.getGroupId());

			cpDefinitionLocalService.updateStatus(
				userId, cpDefinition.getCPDefinitionId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());
		}
	}

	protected void checkCPDefinitionsByExpirationDate() throws PortalException {
		List<CPDefinition> cpDefinitions =
			cpDefinitionFinder.findByExpirationDate(
				new Date(),
				new QueryDefinition<>(WorkflowConstants.STATUS_APPROVED));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Expiring " + cpDefinitions.size() +
					" commerce product definitions");
		}

		if ((cpDefinitions != null) && !cpDefinitions.isEmpty()) {
			for (CPDefinition cpDefinition : cpDefinitions) {
				long userId = PortalUtil.getValidUserId(
					cpDefinition.getCompanyId(), cpDefinition.getUserId());

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCommand(Constants.UPDATE);
				serviceContext.setScopeGroupId(cpDefinition.getGroupId());

				cpDefinitionLocalService.updateStatus(
					userId, cpDefinition.getCPDefinitionId(),
					WorkflowConstants.STATUS_EXPIRED, serviceContext,
					new HashMap<String, Serializable>());
			}
		}
	}

	protected BaseModelSearchResult<CPDefinition> searchCPDefinitions(
			SearchContext searchContext)
		throws PortalException {

		Indexer<CPDefinition> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPDefinition.class);

		List<CPDefinition> cpDefinitions = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Hits hits = indexer.search(searchContext, _SELECTED_FIELD_NAMES);

			Document[] documents = hits.getDocs();

			for (Document document : documents) {
				long classPK = GetterUtil.getLong(
					document.get(Field.ENTRY_CLASS_PK));

				CPDefinition cpDefinition = getCPDefinition(classPK);

				cpDefinitions.add(cpDefinition);
			}

			if (cpDefinitions != null) {
				return new BaseModelSearchResult<>(
					cpDefinitions, hits.getLength());
			}
		}

		throw new SearchException(
			"Unable to fix the search index after 10 attempts");
	}

	protected CPDefinition startWorkflowInstance(
			long userId, CPDefinition cpDefinition,
			ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			cpDefinition.getCompanyId(), cpDefinition.getGroupId(), userId,
			CPDefinition.class.getName(), cpDefinition.getCPDefinitionId(),
			cpDefinition, serviceContext, workflowContext);
	}

	protected void validate(
			long groupId, String ddmStructureKey, String productTypeName)
		throws PortalException {

		if (Validator.isNotNull(ddmStructureKey)) {
			long classNameId = classNameLocalService.getClassNameId(
				CPDefinition.class.getName());

			DDMStructure ddmStructure = ddmStructureLocalService.fetchStructure(
				groupId, classNameId, ddmStructureKey, true);

			if (ddmStructure == null) {
				throw new NoSuchStructureException();
			}
		}

		CPType cpType = cpTypeServicesTracker.getCPType(productTypeName);

		if (cpType == null) {
			throw new CPDefinitionProductTypeNameException();
		}
	}

	@ServiceReference(type = CPTypeServicesTracker.class)
	protected CPTypeServicesTracker cpTypeServicesTracker;

	@ServiceReference(type = DDMStructureLocalService.class)
	protected DDMStructureLocalService ddmStructureLocalService;

	private List<CPDefinitionLocalization> _addCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, Map<Locale, String> titleMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaKeywordsMap,
			Map<Locale, String> metaDescriptionMap)
		throws PortalException {

		Set<Locale> localeSet = new HashSet<>();

		localeSet.addAll(titleMap.keySet());

		if (shortDescriptionMap != null) {
			localeSet.addAll(shortDescriptionMap.keySet());
		}

		if (descriptionMap != null) {
			localeSet.addAll(descriptionMap.keySet());
		}

		if (metaTitleMap != null) {
			localeSet.addAll(metaTitleMap.keySet());
		}

		if (metaKeywordsMap != null) {
			localeSet.addAll(metaKeywordsMap.keySet());
		}

		if (metaDescriptionMap != null) {
			localeSet.addAll(metaDescriptionMap.keySet());
		}

		List<CPDefinitionLocalization> cpDefinitionLocalizations =
			new ArrayList<>();

		for (Locale locale : localeSet) {
			String title = titleMap.get(locale);
			String shortDescription = null;
			String description = null;
			String metaTitle = null;
			String metaKeywords = null;
			String metaDescription = null;

			if (shortDescriptionMap != null) {
				shortDescription = shortDescriptionMap.get(locale);
			}

			if (descriptionMap != null) {
				description = descriptionMap.get(locale);
			}

			if (metaTitleMap != null) {
				metaTitle = metaTitleMap.get(locale);
			}

			if (metaKeywordsMap != null) {
				metaKeywords = metaKeywordsMap.get(locale);
			}

			if (metaDescriptionMap != null) {
				metaDescription = metaDescriptionMap.get(locale);
			}

			if (Validator.isNull(title) && Validator.isNull(shortDescription) &&
				Validator.isNull(description) && Validator.isNull(metaTitle) &&
				Validator.isNull(metaKeywords) &&
				Validator.isNull(metaDescription)) {

				continue;
			}

			CPDefinitionLocalization cpDefinitionLocalization =
				_addCPDefinitionLocalizedFields(
					companyId, cpDefinitionId, title, shortDescription,
					description, metaTitle, metaKeywords, metaDescription,
					LocaleUtil.toLanguageId(locale));

			cpDefinitionLocalizations.add(cpDefinitionLocalization);
		}

		return cpDefinitionLocalizations;
	}

	private CPDefinitionLocalization _addCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, String title,
			String shortDescription, String description, String metaTitle,
			String metaKeywords, String metaDescription, String languageId)
		throws PortalException {

		CPDefinitionLocalization cpDefinitionLocalization =
			cpDefinitionLocalizationPersistence.
				fetchByCPDefinitionId_LanguageId(cpDefinitionId, languageId);

		if (cpDefinitionLocalization == null) {
			long cpDefinitionLocalizationId = counterLocalService.increment();

			cpDefinitionLocalization =
				cpDefinitionLocalizationPersistence.create(
					cpDefinitionLocalizationId);

			cpDefinitionLocalization.setCompanyId(companyId);
			cpDefinitionLocalization.setCPDefinitionId(cpDefinitionId);
			cpDefinitionLocalization.setTitle(title);
			cpDefinitionLocalization.setShortDescription(shortDescription);
			cpDefinitionLocalization.setDescription(description);
			cpDefinitionLocalization.setMetaTitle(metaTitle);
			cpDefinitionLocalization.setMetaKeywords(metaKeywords);
			cpDefinitionLocalization.setMetaDescription(metaDescription);
			cpDefinitionLocalization.setLanguageId(languageId);
		}
		else {
			cpDefinitionLocalization.setTitle(title);
			cpDefinitionLocalization.setShortDescription(shortDescription);
			cpDefinitionLocalization.setDescription(description);
			cpDefinitionLocalization.setMetaTitle(metaTitle);
			cpDefinitionLocalization.setMetaKeywords(metaKeywords);
			cpDefinitionLocalization.setMetaDescription(metaDescription);
		}

		return cpDefinitionLocalizationPersistence.update(
			cpDefinitionLocalization);
	}

	private Map<Locale, String> _getUniqueUrlTitles(
			CPDefinition cpDefinition, Map<Locale, String> urlTitleMap)
		throws PortalException {

		Map<Locale, String> newUrlTitleMap = new HashMap<>();

		Map<Locale, String> titleMap = cpDefinition.getTitleMap();

		long classNameId = classNameLocalService.getClassNameId(
			CPDefinition.class);

		for (Map.Entry<Locale, String> titleEntry : titleMap.entrySet()) {
			String urlTitle = urlTitleMap.get(titleEntry.getKey());

			if (Validator.isNull(urlTitle)) {
				urlTitle = titleEntry.getValue();
			}

			String languageId = LanguageUtil.getLanguageId(titleEntry.getKey());

			urlTitle = cpFriendlyURLEntryLocalService.buildUrlTitle(
				cpDefinition.getGroupId(), cpDefinition.getCompanyId(),
				classNameId, cpDefinition.getCPDefinitionId(), languageId,
				urlTitle);

			newUrlTitleMap.put(titleEntry.getKey(), urlTitle);
		}

		return newUrlTitleMap;
	}

	private List<CPDefinitionLocalization> _updateCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, Map<Locale, String> titleMap,
			Map<Locale, String> shortDescriptionMap,
			Map<Locale, String> descriptionMap,
			Map<Locale, String> metaTitleMap,
			Map<Locale, String> metaKeywordsMap,
			Map<Locale, String> metaDescriptionMap)
		throws PortalException {

		List<CPDefinitionLocalization> oldCPDefinitionLocalizations =
			new ArrayList<>(
				cpDefinitionLocalizationPersistence.findByCPDefinitionId(
					cpDefinitionId));

		List<CPDefinitionLocalization> newCPDefinitionLocalizations =
			_addCPDefinitionLocalizedFields(
				companyId, cpDefinitionId, titleMap, shortDescriptionMap,
				descriptionMap, metaTitleMap, metaKeywordsMap,
				metaDescriptionMap);

		oldCPDefinitionLocalizations.removeAll(newCPDefinitionLocalizations);

		for (CPDefinitionLocalization
				oldCPDefinitionLocalization :
					oldCPDefinitionLocalizations) {

			cpDefinitionLocalizationPersistence.remove(
				oldCPDefinitionLocalization);
		}

		return newCPDefinitionLocalizations;
	}

	private static final String[] _SELECTED_FIELD_NAMES =
		{Field.ENTRY_CLASS_PK, Field.COMPANY_ID, Field.GROUP_ID, Field.UID};

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionLocalServiceImpl.class);

}