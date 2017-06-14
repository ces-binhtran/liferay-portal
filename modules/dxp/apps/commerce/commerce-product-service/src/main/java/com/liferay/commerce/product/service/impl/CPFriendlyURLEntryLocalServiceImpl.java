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

import com.liferay.commerce.product.exception.CPFriendlyURLLengthException;
import com.liferay.commerce.product.exception.DuplicateCPFriendlyURLEntryException;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.service.base.CPFriendlyURLEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPFriendlyURLEntryLocalServiceImpl
	extends CPFriendlyURLEntryLocalServiceBaseImpl {

	@Override
	public void addCPFriendlyURLEntry(
			long groupId, long companyId, Class<?> clazz, long classPK,
			Map<Locale, String> urlTitleMap)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(clazz);

		addCPFriendlyURLEntry(
			groupId, companyId, classNameId, classPK, urlTitleMap);
	}

	@Override
	public CPFriendlyURLEntry addCPFriendlyURLEntry(
			long groupId, long companyId, Class<?> clazz, long classPK,
			String languageId, String urlTitle)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(clazz);

		return addCPFriendlyURLEntry(
			groupId, companyId, classNameId, classPK, languageId, urlTitle);
	}

	@Override
	public void addCPFriendlyURLEntry(
			long groupId, long companyId, long classNameId, long classPK,
			Map<Locale, String> urlTitleMap)
		throws PortalException {

		for (Map.Entry<Locale, String> urlTitleEntry : urlTitleMap.entrySet()) {
			String languageId = LanguageUtil.getLanguageId(
				urlTitleEntry.getKey());

			if (Validator.isNull(urlTitleEntry.getValue())) {
				continue;
			}

			addCPFriendlyURLEntry(
				groupId, companyId, classNameId, classPK, languageId,
				urlTitleEntry.getValue());
		}
	}

	@Override
	public CPFriendlyURLEntry addCPFriendlyURLEntry(
			long groupId, long companyId, long classNameId, long classPK,
			String languageId, String urlTitle)
		throws PortalException {

		String normalizedUrlTitle = FriendlyURLNormalizerUtil.normalize(
			urlTitle);

		validate(
			groupId, companyId, classNameId, classPK, languageId,
			normalizedUrlTitle);

		CPFriendlyURLEntry mainCPFriendlyURLEntry =
			cpFriendlyURLEntryPersistence.fetchByG_C_C_C_L_M(
				groupId, companyId, classNameId, classPK, languageId, true);

		if (mainCPFriendlyURLEntry != null) {
			mainCPFriendlyURLEntry.setMain(false);

			cpFriendlyURLEntryPersistence.update(mainCPFriendlyURLEntry);
		}

		CPFriendlyURLEntry oldCPFriendlyURLEntry =
			cpFriendlyURLEntryPersistence.fetchByG_C_C_C_L_U(
				groupId, companyId, classNameId, classPK, languageId,
				normalizedUrlTitle);

		if (oldCPFriendlyURLEntry != null) {
			oldCPFriendlyURLEntry.setMain(true);

			return cpFriendlyURLEntryPersistence.update(oldCPFriendlyURLEntry);
		}

		long cpFriendlyURLEntryId = counterLocalService.increment();

		CPFriendlyURLEntry cpFriendlyURLEntry = createCPFriendlyURLEntry(
			cpFriendlyURLEntryId);

		cpFriendlyURLEntry.setCompanyId(companyId);
		cpFriendlyURLEntry.setGroupId(groupId);
		cpFriendlyURLEntry.setClassNameId(classNameId);
		cpFriendlyURLEntry.setClassPK(classPK);
		cpFriendlyURLEntry.setLanguageId(languageId);
		cpFriendlyURLEntry.setUrlTitle(normalizedUrlTitle);
		cpFriendlyURLEntry.setMain(true);

		return cpFriendlyURLEntryPersistence.update(cpFriendlyURLEntry);
	}

	@Override
	public String buildUrlTitle(
			long groupId, long companyId, long classNameId, long classPK,
			String languageId, String newTitle)
		throws PortalException {

		if (newTitle == null) {
			return String.valueOf(classPK);
		}

		newTitle = StringUtil.toLowerCase(newTitle.trim());

		if (Validator.isNull(newTitle) || Validator.isNumber(newTitle)) {
			newTitle = String.valueOf(classPK);
		}
		else {
			newTitle = FriendlyURLNormalizerUtil.normalizeWithPeriodsAndSlashes(
				newTitle);
		}

		String urlTitle = ModelHintsUtil.trimString(
			CPFriendlyURLEntry.class.getName(), "urlTitle", newTitle);

		return cpFriendlyURLEntryLocalService.getUniqueUrlTitle(
			groupId, companyId, classNameId, classPK, languageId, urlTitle);
	}

	@Override
	public void deleteCPFriendlyURLEntries(
		long groupId, long companyId, Class<?> clazz, long classPK) {

		long classNameId = classNameLocalService.getClassNameId(clazz);

		cpFriendlyURLEntryPersistence.removeByG_C_C_C(
			groupId, companyId, classNameId, classPK);
	}

	@Override
	public List<CPFriendlyURLEntry> getCPFriendlyURLEntries(
		long groupId, long companyId, long classNameId, long classPK) {

		return cpFriendlyURLEntryPersistence.findByG_C_C_C(
			groupId, companyId, classNameId, classPK);
	}

	@Override
	public CPFriendlyURLEntry getCPFriendlyURLEntry(
			long groupId, long companyId, long classNameId, long classPK,
			String languageId, boolean main)
		throws PortalException
	{

		return cpFriendlyURLEntryPersistence.fetchByG_C_C_C_L_M(
			groupId, companyId, classNameId, classPK, languageId, main);
	}

	@Override
	public CPFriendlyURLEntry getCPFriendlyURLEntry(
			long groupId, long companyId, long classNameId, String languageId,
			String urlTitle)
		throws PortalException
	{

		return cpFriendlyURLEntryPersistence.findByG_C_C_L_U(
			groupId, companyId, classNameId, languageId, urlTitle);
	}

	@Override
	public Map<String, String> getLanguageIdToUrlTitleMap(
		long groupId, long companyId, long classNameId, long classPK) {

		Map<String, String> languageIdToUrlTitleMap = new HashMap<>();

		List<CPFriendlyURLEntry> cpFriendlyURLEntries =
			cpFriendlyURLEntryPersistence.findByG_C_C_C_M(
				groupId, companyId, classNameId, classPK, true);

		for (CPFriendlyURLEntry cpFriendlyURLEntry : cpFriendlyURLEntries) {
			languageIdToUrlTitleMap.put(
				cpFriendlyURLEntry.getLanguageId(),
				cpFriendlyURLEntry.getUrlTitle());
		}

		return languageIdToUrlTitleMap;
	}

	@Override
	public String getUniqueUrlTitle(
		long groupId, long companyId, long classNameId, long classPK,
		String languageId, String urlTitle) {

		String normalizedUrlTitle = FriendlyURLNormalizerUtil.normalize(
			urlTitle);

		int maxLength = ModelHintsUtil.getMaxLength(
			CPFriendlyURLEntry.class.getName(), "urlTitle");

		String curUrlTitle = normalizedUrlTitle.substring(
			0, Math.min(maxLength, normalizedUrlTitle.length()));

		for (int i = 1;; i++) {
			CPFriendlyURLEntry curCPFriendlyURLEntry =
				cpFriendlyURLEntryPersistence.fetchByG_C_C_L_U(
					groupId, companyId, classNameId, languageId, curUrlTitle);

			if ((curCPFriendlyURLEntry == null) ||
				(curCPFriendlyURLEntry.getClassPK() == classPK)) {

				break;
			}

			String suffix = StringPool.DASH + i;

			String prefix = normalizedUrlTitle.substring(
				0,
				Math.min(
					maxLength - suffix.length(), normalizedUrlTitle.length()));

			curUrlTitle = prefix + suffix;
		}

		return curUrlTitle;
	}

	@Override
	public Map<Locale, String> getUrlTitleMap(
		long groupId, long companyId, Class<?> clazz, long classPK) {

		long classNameId = classNameLocalService.getClassNameId(clazz);

		return getUrlTitleMap(groupId, companyId, classNameId, classPK);
	}

	@Override
	public Map<Locale, String> getUrlTitleMap(
		long groupId, long companyId, long classNameId, long classPK) {

		Map<Locale, String> urlTitleMap = new HashMap<>();

		List<CPFriendlyURLEntry> cpFriendlyURLEntries =
			cpFriendlyURLEntryPersistence.findByG_C_C_C_M(
				groupId, companyId, classNameId, classPK, true);

		for (CPFriendlyURLEntry cpFriendlyURLEntry : cpFriendlyURLEntries) {
			Locale locale = LocaleUtil.fromLanguageId(
				cpFriendlyURLEntry.getLanguageId());

			urlTitleMap.put(locale, cpFriendlyURLEntry.getUrlTitle());
		}

		return urlTitleMap;
	}

	@Override
	public String getUrlTitleMapAsXML(
		long groupId, long companyId, long classNameId, long classPK,
		String defaultLanguageId) {

		Map<String, String> languageIdToUrlTitleMap =
			getLanguageIdToUrlTitleMap(
				groupId, companyId, classNameId, classPK);

		return LocalizationUtil.getXml(
			languageIdToUrlTitleMap, defaultLanguageId, "urlTitle");
	}

	@Override
	public void validate(
			long groupId, long companyId, long classNameId, long classPK,
			String languageId, String urlTitle)
		throws PortalException {

		int maxLength = ModelHintsUtil.getMaxLength(
			CPFriendlyURLEntry.class.getName(), "urlTitle");

		if (urlTitle.length() > maxLength) {
			throw new CPFriendlyURLLengthException();
		}

		String normalizedUrlTitle = FriendlyURLNormalizerUtil.normalize(
			urlTitle);

		if (classPK > 0) {
			CPFriendlyURLEntry cpFriendlyURLEntry =
				cpFriendlyURLEntryPersistence.fetchByG_C_C_C_L_U(
					groupId, companyId, classNameId, classPK, languageId,
					normalizedUrlTitle);

			if (cpFriendlyURLEntry != null) {
				return;
			}
		}

		int count = cpFriendlyURLEntryPersistence.countByG_C_C_U(
			groupId, companyId, classNameId, normalizedUrlTitle);

		if (count > 0) {
			throw new DuplicateCPFriendlyURLEntryException();
		}
	}

}