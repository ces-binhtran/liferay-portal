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

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.commerce.product.exception.CPDefinitionDisplayDateException;
import com.liferay.commerce.product.exception.CPDefinitionExpirationDateException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLocalization;
import com.liferay.commerce.product.service.base.CPDefinitionLocalServiceBaseImpl;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Marco Leo
 */
@ProviderType
public class CPDefinitionLocalServiceImpl
	extends CPDefinitionLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinition addCPDefinition(
			String baseSKU, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String productTypeName,
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

		validateReferences(groupId, ddmStructureKey);

		long cpDefinitionId = counterLocalService.increment();

		CPDefinition cpDefinition = cpDefinitionPersistence.create(
			cpDefinitionId);

		cpDefinition.setUuid(serviceContext.getUuid());
		cpDefinition.setGroupId(groupId);
		cpDefinition.setCompanyId(user.getCompanyId());
		cpDefinition.setUserId(user.getUserId());
		cpDefinition.setUserName(user.getFullName());
		cpDefinition.setBaseSKU(baseSKU);
		cpDefinition.setProductTypeName(productTypeName);
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

		// Commerce product definition localization

		_addCPDefinitionLocalizedFields(
			user.getCompanyId(), cpDefinitionId, titleMap, descriptionMap);

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

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDefinition deleteCPDefinition(CPDefinition cpDefinition)
		throws PortalException {

		// Commerce product definition

		cpDefinitionPersistence.remove(cpDefinition);

		// Commerce product definition localization

		cpDefinitionLocalizationPersistence.removeByCPDefinitionPK(
			cpDefinition.getCPDefinitionId());

		// Commerce product definition option rels

		cpDefinitionOptionRelLocalService.deleteCPDefinitionOptionRels(
			cpDefinition.getCPDefinitionId());

		// Commerce product instances

		cpInstanceLocalService.deleteCPInstances(
			cpDefinition.getCPDefinitionId());

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
	public List<String> getCPDefinitionLocalizationLanguageIds(
		long cpDefinitionId) {

		List<CPDefinitionLocalization> cpDefinitionLocalizationList =
			cpDefinitionLocalizationPersistence.findByCPDefinitionPK(
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
	public List<CPDefinition> getCPDefinitions(
		long groupId, int start, int end) {

		return cpDefinitionPersistence.findByGroupId(groupId, start, end);
	}

	@Override
	public List<CPDefinition> getCPDefinitions(
		long groupId, int start, int end,
		OrderByComparator<CPDefinition> orderByComparator) {

		return cpDefinitionPersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getCPDefinitionsCount(long groupId) {
		return cpDefinitionPersistence.countByGroupId(groupId);
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
			long cpDefinitionId, String baseSKU, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String productTypeName,
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

		validateReferences(groupId, ddmStructureKey);

		cpDefinition.setBaseSKU(baseSKU);
		cpDefinition.setProductTypeName(productTypeName);
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

		// Commerce product definition localization

		_updateCPDefinitionLocalizedFields(
			cpDefinition.getCompanyId(), cpDefinition.getCPDefinitionId(),
			titleMap, descriptionMap);

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
		}

		return cpDefinition;
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

	protected void validateReferences(long groupId, String ddmStructureKey)
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
	}

	@ServiceReference(type = DDMStructureLocalService.class)
	protected DDMStructureLocalService ddmStructureLocalService;

	private List<CPDefinitionLocalization> _addCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap)
		throws PortalException {

		Set<Locale> localeSet = new HashSet<>();

		localeSet.addAll(titleMap.keySet());

		if (descriptionMap != null) {
			localeSet.addAll(descriptionMap.keySet());
		}

		List<CPDefinitionLocalization> cpDefinitionLocalizations =
			new ArrayList<>();

		for (Locale locale : localeSet) {
			String title = titleMap.get(locale);
			String description = null;

			if (descriptionMap != null) {
				description = descriptionMap.get(locale);
			}

			if (Validator.isNull(title) && Validator.isNull(description)) {
				continue;
			}

			CPDefinitionLocalization cpDefinitionLocalization =
				_addCPDefinitionLocalizedFields(
					companyId, cpDefinitionId, title, description,
					LocaleUtil.toLanguageId(locale));

			cpDefinitionLocalizations.add(cpDefinitionLocalization);
		}

		return cpDefinitionLocalizations;
	}

	private CPDefinitionLocalization _addCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, String title,
			String description, String languageId)
		throws PortalException {

		CPDefinitionLocalization cpDefinitionLocalization =
			cpDefinitionLocalizationPersistence.fetchByCPD_L(
				cpDefinitionId, languageId);

		if (cpDefinitionLocalization == null) {
			long cpDefinitionLocalizationId = counterLocalService.increment();

			cpDefinitionLocalization =
				cpDefinitionLocalizationPersistence.create(
					cpDefinitionLocalizationId);

			cpDefinitionLocalization.setCompanyId(companyId);
			cpDefinitionLocalization.setCpDefinitionPK(cpDefinitionId);
			cpDefinitionLocalization.setTitle(title);
			cpDefinitionLocalization.setDescription(description);
			cpDefinitionLocalization.setLanguageId(languageId);
		}
		else {
			cpDefinitionLocalization.setTitle(title);
			cpDefinitionLocalization.setDescription(description);
		}

		return cpDefinitionLocalizationPersistence.update(
			cpDefinitionLocalization);
	}

	private List<CPDefinitionLocalization> _updateCPDefinitionLocalizedFields(
			long companyId, long cpDefinitionId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap)
		throws PortalException {

		List<CPDefinitionLocalization> oldCPDefinitionLocalizations =
			new ArrayList<>(
				cpDefinitionLocalizationPersistence.findByCPDefinitionPK(
					cpDefinitionId));

		List<CPDefinitionLocalization> newCPDefinitionLocalizations =
			_addCPDefinitionLocalizedFields(
				companyId, cpDefinitionId, titleMap, descriptionMap);

		oldCPDefinitionLocalizations.removeAll(newCPDefinitionLocalizations);

		for (CPDefinitionLocalization
				oldCPDefinitionLocalization :
					oldCPDefinitionLocalizations) {

			cpDefinitionLocalizationPersistence.remove(
				oldCPDefinitionLocalization);
		}

		return newCPDefinitionLocalizations;
	}

}