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

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.base.CPAttachmentFileEntryServiceBaseImpl;
import com.liferay.commerce.product.service.permission.CPDefinitionPermission;
import com.liferay.commerce.product.service.permission.CPPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
public class CPAttachmentFileEntryServiceImpl
	extends CPAttachmentFileEntryServiceBaseImpl {

	@Override
	public CPAttachmentFileEntry addCPAttachmentFileEntry(
			long classNameId, long classPK, long fileEntryId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String json, int priority, int type,
			ServiceContext serviceContext)
		throws PortalException {

		checkCPAttachmentFileEntryPermissions(
			serviceContext.getScopeGroupId(), classNameId, classPK, type);

		return cpAttachmentFileEntryLocalService.addCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, json,
			priority, type, serviceContext);
	}

	@Override
	public CPAttachmentFileEntry deleteCPAttachmentFileEntry(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws PortalException {

		checkCPAttachmentFileEntryPermissions(cpAttachmentFileEntry);

		return cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntry);
	}

	@Override
	public CPAttachmentFileEntry deleteCPAttachmentFileEntry(
			long cpAttachmentFileEntryId)
		throws PortalException {

		checkCPAttachmentFileEntryPermissions(cpAttachmentFileEntryId);

		return cpAttachmentFileEntryLocalService.deleteCPAttachmentFileEntry(
			cpAttachmentFileEntryId);
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, int classPK, int start, int end)
		throws PortalException {

		return cpAttachmentFileEntryLocalService.getCPAttachmentFileEntries(
			classNameId, classPK, start, end);
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long classNameId, int classPK, int start, int end,
			OrderByComparator<CPAttachmentFileEntry> orderByComparator)
		throws PortalException {

		return cpAttachmentFileEntryLocalService.getCPAttachmentFileEntries(
			classNameId, classPK, start, end, orderByComparator);
	}

	@Override
	public int getCPAttachmentFileEntriesCount(long classNameId, int classPK) {
		return
			cpAttachmentFileEntryLocalService.getCPAttachmentFileEntriesCount(
				classNameId, classPK);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPAttachmentFileEntry updateCPAttachmentFileEntry(
			long cpAttachmentFileEntryId, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, String json, int priority, int type,
			ServiceContext serviceContext)
		throws PortalException {

		checkCPAttachmentFileEntryPermissions(cpAttachmentFileEntryId);

		return cpAttachmentFileEntryLocalService.updateCPAttachmentFileEntry(
			cpAttachmentFileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, json,
			priority, type, serviceContext);
	}

	protected void checkCPAttachmentFileEntryPermissions(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws PortalException {

		checkCPAttachmentFileEntryPermissions(
			cpAttachmentFileEntry.getGroupId(),
			cpAttachmentFileEntry.getClassNameId(),
			cpAttachmentFileEntry.getClassPK(),
			cpAttachmentFileEntry.getType());
	}

	protected void checkCPAttachmentFileEntryPermissions(
			long cpAttachmentFileEntryId)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntryLocalService.getCPAttachmentFileEntry(
				cpAttachmentFileEntryId);

		checkCPAttachmentFileEntryPermissions(cpAttachmentFileEntry);
	}

	protected void checkCPAttachmentFileEntryPermissions(
			long scopeGroupId, long classNameId, long classPK, int type)
		throws PortalException {

		String actionKey = getActionKeyByCPAttachmentFileEntryType(type);

		CPPermission.check(getPermissionChecker(), scopeGroupId, actionKey);

		long cpDefinitionClassNameId = _portal.getClassNameId(
			CPDefinition.class);

		if (classNameId == cpDefinitionClassNameId) {
			CPDefinitionPermission.check(
				getPermissionChecker(), classPK, ActionKeys.UPDATE);
		}
	}

	protected String getActionKeyByCPAttachmentFileEntryType(int type) {
		if (type == CPConstants.ATTACHMENT_FILE_ENTRY_TYPE_ATTACHMENTS) {
			return CPActionKeys.MANAGE_COMMERCE_PRODUCT_ATTACHMENTS;
		}

		return CPActionKeys.MANAGE_COMMERCE_PRODUCT_IMAGES;
	}

	@Reference
	private Portal _portal;

}