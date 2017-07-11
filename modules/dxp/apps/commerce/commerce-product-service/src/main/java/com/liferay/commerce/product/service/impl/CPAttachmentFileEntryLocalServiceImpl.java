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

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.exception.CPAttachmentFileEntryFileEntryIdException;
import com.liferay.commerce.product.exception.CPDefinitionDisplayDateException;
import com.liferay.commerce.product.exception.CPDefinitionExpirationDateException;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.service.base.CPAttachmentFileEntryLocalServiceBaseImpl;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPAttachmentFileEntryLocalServiceImpl
	extends CPAttachmentFileEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry addCPAttachmentFileEntry(
			long classNameId, long classPK, long fileEntryId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, Map<Locale, String> titleMap, String json,
			double priority, int type, ServiceContext serviceContext)
		throws PortalException {

		// Commerce product attachment file entry

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		Locale locale = LocaleUtil.getSiteDefault();

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

		validate(fileEntryId);

		if (titleMap.get(locale).isEmpty()) {
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			titleMap.put(locale, fileEntry.getFileName());
		}

		long cpAttachmentFileEntryId = counterLocalService.increment();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.create(cpAttachmentFileEntryId);

		cpAttachmentFileEntry.setUuid(serviceContext.getUuid());
		cpAttachmentFileEntry.setGroupId(groupId);
		cpAttachmentFileEntry.setCompanyId(user.getCompanyId());
		cpAttachmentFileEntry.setUserId(user.getUserId());
		cpAttachmentFileEntry.setUserName(user.getFullName());
		cpAttachmentFileEntry.setClassNameId(classNameId);
		cpAttachmentFileEntry.setClassPK(classPK);
		cpAttachmentFileEntry.setFileEntryId(fileEntryId);
		cpAttachmentFileEntry.setDisplayDate(displayDate);
		cpAttachmentFileEntry.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpAttachmentFileEntry.setTitleMap(titleMap);
		cpAttachmentFileEntry.setJson(json);
		cpAttachmentFileEntry.setPriority(priority);
		cpAttachmentFileEntry.setType(type);
		cpAttachmentFileEntry.setExpandoBridgeAttributes(serviceContext);

		cpAttachmentFileEntryPersistence.update(cpAttachmentFileEntry);

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpAttachmentFileEntry, serviceContext);
	}

	@Override
	public void checkCPAttachmentFileEntries() throws PortalException {
		checkCPAttachmentFileEntriesByDisplayDate();

		checkCPAttachmentFileEntriesByExpirationDate();
	}

	@Override
	public void deleteCPAttachmentFileEntries(String className, long classPK)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(className);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryPersistence.findByC_C(classNameId, classPK);

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntry(
				cpAttachmentFileEntry);
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPAttachmentFileEntry deleteCPAttachmentFileEntry(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws PortalException {

		// Commerce product attachment file entry

		cpAttachmentFileEntryPersistence.remove(cpAttachmentFileEntry);

		// Expando

		expandoRowLocalService.deleteRows(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());

		return cpAttachmentFileEntry;
	}

	@Override
	public CPAttachmentFileEntry deleteCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.findByPrimaryKey(
				cpAttachmentFileEntryId);

		return cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntry);
	}

	@Override
	public Folder getAttachmentsFolder(
			long userId, long groupId, String className, long classPK)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			groupId, CPConstants.SERVICE_NAME, serviceContext);

		Folder classNameFolder = PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, className,
			serviceContext);

		Folder entityFolder = PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(), classNameFolder.getFolderId(),
			String.valueOf(classPK), serviceContext);

		return entityFolder;
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpAttachmentFileEntryPersistence.findByC_C_T_NotST(
				classNameId, classPK, type, WorkflowConstants.STATUS_IN_TRASH,
				start, end);
		}

		return cpAttachmentFileEntryPersistence.findByC_C_T_ST(
			classNameId, classPK, type, status, start, end);
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, long classPK, int type, int status, int start,
			int end, OrderByComparator<CPAttachmentFileEntry> orderByComparator)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpAttachmentFileEntryPersistence.findByC_C_T_NotST(
				classNameId, classPK, type, WorkflowConstants.STATUS_IN_TRASH,
				start, end, orderByComparator);
		}

		return cpAttachmentFileEntryPersistence.findByC_C_T_ST(
			classNameId, classPK, type, status, start, end, orderByComparator);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(
		long classNameId, long classPK, int type, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return cpAttachmentFileEntryPersistence.countByC_C_T_NotST(
				classNameId, classPK, type, WorkflowConstants.STATUS_IN_TRASH);
		}

		return cpAttachmentFileEntryPersistence.countByC_C_T_ST(
			classNameId, classPK, type, status);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry updateCPAttachmentFileEntry(
			long cpAttachmentFileEntryId, long fileEntryId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, Map<Locale, String> titleMap, String json,
			double priority, int type, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		Locale locale = LocaleUtil.getSiteDefault();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.findByPrimaryKey(
				cpAttachmentFileEntryId);

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

		validate(fileEntryId);

		if (titleMap.get(locale).isEmpty()) {
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			titleMap.put(locale, fileEntry.getFileName());
		}

		cpAttachmentFileEntry.setFileEntryId(fileEntryId);
		cpAttachmentFileEntry.setDisplayDate(displayDate);
		cpAttachmentFileEntry.setExpirationDate(expirationDate);

		if ((expirationDate == null) || expirationDate.after(now)) {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		else {
			cpAttachmentFileEntry.setStatus(WorkflowConstants.STATUS_EXPIRED);
		}

		cpAttachmentFileEntry.setTitleMap(titleMap);
		cpAttachmentFileEntry.setJson(json);
		cpAttachmentFileEntry.setPriority(priority);
		cpAttachmentFileEntry.setType(type);
		cpAttachmentFileEntry.setExpandoBridgeAttributes(serviceContext);

		cpAttachmentFileEntryPersistence.update(cpAttachmentFileEntry);

		// Workflow

		return startWorkflowInstance(
			user.getUserId(), cpAttachmentFileEntry, serviceContext);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry updateStatus(
			long userId, long cpAttachmentFileEntryId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryPersistence.findByPrimaryKey(
				cpAttachmentFileEntryId);

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(cpAttachmentFileEntry.getDisplayDate() != null) &&
			now.before(cpAttachmentFileEntry.getDisplayDate())) {

			status = WorkflowConstants.STATUS_SCHEDULED;
		}

		Date modifiedDate = serviceContext.getModifiedDate(now);

		cpAttachmentFileEntry.setModifiedDate(modifiedDate);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			Date expirationDate = cpAttachmentFileEntry.getExpirationDate();

			if ((expirationDate != null) && expirationDate.before(now)) {
				cpAttachmentFileEntry.setExpirationDate(null);
			}
		}

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			cpAttachmentFileEntry.setExpirationDate(now);
		}

		cpAttachmentFileEntry.setStatus(status);
		cpAttachmentFileEntry.setStatusByUserId(user.getUserId());
		cpAttachmentFileEntry.setStatusByUserName(user.getFullName());
		cpAttachmentFileEntry.setStatusDate(modifiedDate);

		cpAttachmentFileEntryPersistence.update(cpAttachmentFileEntry);

		return cpAttachmentFileEntry;
	}

	protected void checkCPAttachmentFileEntriesByDisplayDate()
		throws PortalException {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryPersistence.findByLtD_S(
				new Date(), WorkflowConstants.STATUS_SCHEDULED);

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			long userId = PortalUtil.getValidUserId(
				cpAttachmentFileEntry.getCompanyId(),
				cpAttachmentFileEntry.getUserId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCommand(Constants.UPDATE);
			serviceContext.setScopeGroupId(cpAttachmentFileEntry.getGroupId());

			cpAttachmentFileEntryLocalService.updateStatus(
				userId, cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				new HashMap<String, Serializable>());
		}
	}

	protected void checkCPAttachmentFileEntriesByExpirationDate()
		throws PortalException {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryFinder.findByExpirationDate(
				new Date(),
				new QueryDefinition<>(WorkflowConstants.STATUS_APPROVED));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Expiring " + cpAttachmentFileEntries.size() +
					" commerce product attachment file entries");
		}

		if ((cpAttachmentFileEntries != null) &&
			!cpAttachmentFileEntries.isEmpty()) {

			for (CPAttachmentFileEntry cpAttachmentFileEntry :
					cpAttachmentFileEntries) {

				long userId = PortalUtil.getValidUserId(
					cpAttachmentFileEntry.getCompanyId(),
					cpAttachmentFileEntry.getUserId());

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setCommand(Constants.UPDATE);
				serviceContext.setScopeGroupId(
					cpAttachmentFileEntry.getGroupId());

				cpAttachmentFileEntryLocalService.updateStatus(
					userId, cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
					WorkflowConstants.STATUS_EXPIRED, serviceContext,
					new HashMap<String, Serializable>());
			}
		}
	}

	protected CPAttachmentFileEntry startWorkflowInstance(
			long userId, CPAttachmentFileEntry cpAttachmentFileEntry,
			ServiceContext serviceContext)
		throws PortalException {

		Map<String, Serializable> workflowContext = new HashMap<>();

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			cpAttachmentFileEntry.getCompanyId(),
			cpAttachmentFileEntry.getGroupId(), userId,
			CPAttachmentFileEntry.class.getName(),
			cpAttachmentFileEntry.getCPAttachmentFileEntryId(),
			cpAttachmentFileEntry, serviceContext, workflowContext);
	}

	protected void validate(long fileEntryId) throws PortalException {
		if (fileEntryId <= 0) {
			throw new CPAttachmentFileEntryFileEntryIdException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPAttachmentFileEntryLocalServiceImpl.class);

}
