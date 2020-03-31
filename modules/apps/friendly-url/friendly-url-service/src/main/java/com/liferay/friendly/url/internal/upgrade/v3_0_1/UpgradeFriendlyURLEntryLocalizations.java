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

package com.liferay.friendly.url.internal.upgrade.v3_0_1;

import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ricardo Couso
 */
public class UpgradeFriendlyURLEntryLocalizations extends UpgradeProcess {

	protected void addMissingFriendlyURLEntryLocalizations() {
		StringBundler sb1 = new StringBundler(8);

		sb1.append("select JournalArticle.id_, ");
		sb1.append("JournalArticle.resourcePrimKey, JournalArticle.groupId, ");
		sb1.append("JournalArticle.companyId from (select resourcePrimKey, ");
		sb1.append("max(version) as latestVersion from JournalArticle group ");
		sb1.append("by resourcePrimKey) LatestVersion inner join ");
		sb1.append("JournalArticle on JournalArticle.resourcePrimKey = ");
		sb1.append("LatestVersion.resourcePrimKey and JournalArticle.version ");
		sb1.append("= LatestVersion.latestVersion");

		try (Statement statement1 = connection.createStatement();
			ResultSet rs1 = statement1.executeQuery(sb1.toString())) {

			while (rs1.next()) {
				long id = rs1.getLong(1);
				long resourcePrimKey = rs1.getLong(2);

				StringBundler sb2 = new StringBundler(13);

				sb2.append("select JAL.title, JAL.languageId from (select ");
				sb2.append("title, languageId from ");
				sb2.append("JournalArticleLocalization where articlePK = ");
				sb2.append(id);
				sb2.append(") JAL left join (select ");
				sb2.append("friendlyURLEntryLocalizationId, languageId from ");
				sb2.append("FriendlyURLEntryLocalization where classPK = ");
				sb2.append(resourcePrimKey);
				sb2.append(" and classNameId = ");
				sb2.append(_classNameIdJournalArticle);
				sb2.append(") FURLEL on JAL.languageId = FURLEL.languageId ");
				sb2.append("where FURLEL.friendlyURLEntryLocalizationId is ");
				sb2.append("null");

				Map<String, String> urlTitleMap = new HashMap<>();

				try (Statement statement2 = connection.createStatement();
					ResultSet rs2 = statement2.executeQuery(sb2.toString())) {

					while (rs2.next()) {
						String title = rs2.getString(1);
						String languageId = rs2.getString(2);

						urlTitleMap.put(languageId, title);
					}

					if (urlTitleMap.isEmpty()) {
						continue;
					}

					long groupId = rs1.getLong(3);
					long companyId = rs1.getLong(4);

					long friendlyURLEntryId = _getFriendlyURLEntryId(
						resourcePrimKey);

					if (friendlyURLEntryId != -1) {
						urlTitleMap = _sortUrlTitleMapByGroupLocaleSettings(
							groupId, urlTitleMap);

						for (Map.Entry<String, String> entry :
								urlTitleMap.entrySet()) {

							_addMissingFriendlyURLEntryLocalization(
								companyId, friendlyURLEntryId, entry.getKey(),
								entry.getValue(), groupId, resourcePrimKey);
						}
					}
					else {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Journal Article with id " + id +
									" has no associated FriendlyURLEntry.");
						}
					}
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		setUpClassNameIds();
		addMissingFriendlyURLEntryLocalizations();
	}

	protected void setUpClassNameIds() {
		_classNameIdJournalArticle = PortalUtil.getClassNameId(
			"com.liferay.journal.model.JournalArticle");
	}

	private void _addMissingFriendlyURLEntryLocalization(
			long companyId, long friendlyURLEntryId, String languageId,
			String urlTitle, long groupId, long classPK)
		throws Exception {

		long friendlyURLEntryLocalizationId = increment(
			FriendlyURLEntryLocalization.class.getName());

		String uniqueURLTitle = _getUniqueURLTitle(urlTitle, groupId);

		StringBundler sb = new StringBundler(4);

		sb.append("insert into FriendlyURLEntryLocalization (mvccVersion, ");
		sb.append("friendlyURLEntryLocalizationId, companyId, ");
		sb.append("friendlyURLEntryId, languageId, urlTitle, groupId, ");
		sb.append("classNameId, classPK) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			ps.setLong(1, 0);
			ps.setLong(2, friendlyURLEntryLocalizationId);
			ps.setLong(3, companyId);
			ps.setLong(4, friendlyURLEntryId);
			ps.setString(5, languageId);
			ps.setString(6, uniqueURLTitle);
			ps.setLong(7, groupId);
			ps.setLong(8, _classNameIdJournalArticle);
			ps.setLong(9, classPK);

			ps.executeUpdate();
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to add friendly url entry localization", exception);
			}
		}
	}

	private int _countLocalizations(String urlTitle, long groupId)
		throws Exception {

		int count = 0;

		StringBundler sb = new StringBundler(7);

		sb.append("select count(*) from FriendlyURLEntryLocalization where ");
		sb.append("urlTitle = '");
		sb.append(urlTitle);
		sb.append("' and groupId = ");
		sb.append(groupId);
		sb.append(" and classNameId = ");
		sb.append(_classNameIdJournalArticle);

		try (PreparedStatement ps = connection.prepareStatement(sb.toString());
			ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt(1);
			}
		}

		return count;
	}

	private long _getFriendlyURLEntryId(long resourcePrimKey)
		throws SQLException {

		StringBundler sb = new StringBundler(5);

		sb.append("select friendlyURLEntryId from FriendlyURLEntryMapping ");
		sb.append("where classnameId = ");
		sb.append(_classNameIdJournalArticle);
		sb.append(" and classPK = ");
		sb.append(resourcePrimKey);

		try (Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sb.toString())) {

			if (rs.next()) {
				return rs.getLong(1);
			}
		}

		return -1;
	}

	private String _getUniqueURLTitle(String urlTitle, long groupId)
		throws Exception {

		String normalizedUrlTitle =
			FriendlyURLNormalizerUtil.normalizeWithEncoding(urlTitle);

		int maxLength = 255;

		String curUrlTitle = _getURLEncodedSubstring(
			urlTitle, normalizedUrlTitle, maxLength);

		String prefix = curUrlTitle;

		for (int i = 1;; i++) {
			int count = _countLocalizations(curUrlTitle, groupId);

			if (count == 0) {
				break;
			}

			String suffix = StringPool.DASH + i;

			prefix = _getURLEncodedSubstring(
				urlTitle, prefix, maxLength - suffix.length());

			curUrlTitle = FriendlyURLNormalizerUtil.normalizeWithEncoding(
				prefix + suffix);
		}

		return curUrlTitle;
	}

	private String _getURLEncodedSubstring(
		String decodedString, String encodedString, int maxLength) {

		int endPos = decodedString.length();

		while (encodedString.length() > maxLength) {
			endPos--;

			if ((endPos > 0) &&
				Character.isHighSurrogate(decodedString.charAt(endPos - 1))) {

				endPos--;
			}

			encodedString = FriendlyURLNormalizerUtil.normalizeWithEncoding(
				decodedString.substring(0, endPos));
		}

		return encodedString;
	}

	private Map<String, String> _sortUrlTitleMapByGroupLocaleSettings(
		long groupId, Map<String, String> urlTitleMap) {

		Map<String, String> sortedUrlTitleMap = new LinkedHashMap<>();

		for (Locale locale : LanguageUtil.getAvailableLocales(groupId)) {
			String languageId = LocaleUtil.toLanguageId(locale);

			String value = urlTitleMap.get(languageId);

			if (value == null) {
				continue;
			}

			sortedUrlTitleMap.put(languageId, value);
		}

		return sortedUrlTitleMap;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeFriendlyURLEntryLocalizations.class);

	private long _classNameIdJournalArticle;

}