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

package com.liferay.commerce.product.demo.data.creator.internal.util;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CPAttachmentFileEntryDemoDataCreatorHelper.class)
public class CPAttachmentFileEntryDemoDataCreatorHelper
	extends BaseCPDemoDataCreatorHelper {

	public void addCPAttachmentFileEntries(
			long userId, long groupId, long cpDefinitionId, JSONArray jsonArray)
		throws Exception {

		for (int i = 0; i < jsonArray.length(); i++) {
			String fileName = jsonArray.getString(i);

			Map<Locale, String> titleMap = Collections.singletonMap(
				Locale.US, fileName);

			Folder folder =
				_cpAttachmentFileEntryLocalService.getAttachmentsFolder(
					userId, groupId, CPDefinition.class.getName(),
					cpDefinitionId);

			String uniqueFileName = PortletFileRepositoryUtil.getUniqueFileName(
				groupId, folder.getFolderId(), fileName);

			Class<?> clazz = getClass();

			String filePath =
				"com/liferay/commerce/product/demo/data/creator/internal" +
					"/dependencies/images/" + fileName;

			ClassLoader classLoader = clazz.getClassLoader();

			Enumeration<URL> enu = classLoader.getResources(filePath);

			while (enu.hasMoreElements()) {
				URL url = enu.nextElement();

				InputStream is = url.openStream();

				if (is == null) {
					throw new IOException(
						"Unable to open resource at " + url.toString());
				}

				try {
					FileEntry fileEntry =
						PortletFileRepositoryUtil.addPortletFileEntry(
							groupId, userId, CPDefinition.class.getName(),
							cpDefinitionId, CPConstants.SERVICE_NAME,
							folder.getFolderId(), is, uniqueFileName,
							"image/jpeg", true);

					long classeNameId = PortalUtil.getClassNameId(
						CPDefinition.class);

					createCPAttachmentFileEntry(
						userId, groupId, classeNameId, cpDefinitionId,
						fileEntry.getFileEntryId(), titleMap, null, 0,
						CPConstants.ATTACHMENT_FILE_ENTRY_TYPE_IMAGES);
				}
				finally {
					StreamUtil.cleanUp(is);
				}
			}
		}
	}

	public CPAttachmentFileEntry createCPAttachmentFileEntry(
			long userId, long groupId, long classNameId, long classPK,
			long fileEntryId, Map<Locale, String> titleMap, String json,
			int priority, int type)
		throws PortalException {

		ServiceContext serviceContext = getServiceContext(userId, groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH) - 1;
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH) + 1;
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		return _cpAttachmentFileEntryLocalService.addCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, true, titleMap, json,
			priority, type, serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPAttachmentFileEntryDemoDataCreatorHelper.class);

	@Reference
	private CPAttachmentFileEntryLocalService
		_cpAttachmentFileEntryLocalService;

}