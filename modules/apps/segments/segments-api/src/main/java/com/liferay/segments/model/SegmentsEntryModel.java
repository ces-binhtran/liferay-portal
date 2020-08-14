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

package com.liferay.segments.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SegmentsEntry service. Represents a row in the &quot;SegmentsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.segments.model.impl.SegmentsEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.segments.model.impl.SegmentsEntryImpl</code>.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsEntry
 * @generated
 */
@ProviderType
public interface SegmentsEntryModel
	extends BaseModel<SegmentsEntry>, CTModel<SegmentsEntry>, LocalizedModel,
			MVCCModel, ShardedModel, StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a segments entry model instance should use the {@link SegmentsEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this segments entry.
	 *
	 * @return the primary key of this segments entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this segments entry.
	 *
	 * @param primaryKey the primary key of this segments entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this segments entry.
	 *
	 * @return the mvcc version of this segments entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this segments entry.
	 *
	 * @param mvccVersion the mvcc version of this segments entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this segments entry.
	 *
	 * @return the ct collection ID of this segments entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this segments entry.
	 *
	 * @param ctCollectionId the ct collection ID of this segments entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this segments entry.
	 *
	 * @return the uuid of this segments entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this segments entry.
	 *
	 * @param uuid the uuid of this segments entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the segments entry ID of this segments entry.
	 *
	 * @return the segments entry ID of this segments entry
	 */
	public long getSegmentsEntryId();

	/**
	 * Sets the segments entry ID of this segments entry.
	 *
	 * @param segmentsEntryId the segments entry ID of this segments entry
	 */
	public void setSegmentsEntryId(long segmentsEntryId);

	/**
	 * Returns the group ID of this segments entry.
	 *
	 * @return the group ID of this segments entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this segments entry.
	 *
	 * @param groupId the group ID of this segments entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this segments entry.
	 *
	 * @return the company ID of this segments entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this segments entry.
	 *
	 * @param companyId the company ID of this segments entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this segments entry.
	 *
	 * @return the user ID of this segments entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this segments entry.
	 *
	 * @param userId the user ID of this segments entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this segments entry.
	 *
	 * @return the user uuid of this segments entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this segments entry.
	 *
	 * @param userUuid the user uuid of this segments entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this segments entry.
	 *
	 * @return the user name of this segments entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this segments entry.
	 *
	 * @param userName the user name of this segments entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this segments entry.
	 *
	 * @return the create date of this segments entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this segments entry.
	 *
	 * @param createDate the create date of this segments entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this segments entry.
	 *
	 * @return the modified date of this segments entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this segments entry.
	 *
	 * @param modifiedDate the modified date of this segments entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the segments entry key of this segments entry.
	 *
	 * @return the segments entry key of this segments entry
	 */
	@AutoEscape
	public String getSegmentsEntryKey();

	/**
	 * Sets the segments entry key of this segments entry.
	 *
	 * @param segmentsEntryKey the segments entry key of this segments entry
	 */
	public void setSegmentsEntryKey(String segmentsEntryKey);

	/**
	 * Returns the name of this segments entry.
	 *
	 * @return the name of this segments entry
	 */
	public String getName();

	/**
	 * Returns the localized name of this segments entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this segments entry
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this segments entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this segments entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this segments entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this segments entry
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this segments entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this segments entry
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this segments entry.
	 *
	 * @return the locales and localized names of this segments entry
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this segments entry.
	 *
	 * @param name the name of this segments entry
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this segments entry in the language.
	 *
	 * @param name the localized name of this segments entry
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this segments entry in the language, and sets the default locale.
	 *
	 * @param name the localized name of this segments entry
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this segments entry from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this segments entry
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this segments entry from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this segments entry
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this segments entry.
	 *
	 * @return the description of this segments entry
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this segments entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this segments entry
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this segments entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this segments entry. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this segments entry in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this segments entry
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this segments entry in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this segments entry
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this segments entry.
	 *
	 * @return the locales and localized descriptions of this segments entry
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this segments entry.
	 *
	 * @param description the description of this segments entry
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this segments entry in the language.
	 *
	 * @param description the localized description of this segments entry
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this segments entry in the language, and sets the default locale.
	 *
	 * @param description the localized description of this segments entry
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this segments entry from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this segments entry
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this segments entry from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this segments entry
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the active of this segments entry.
	 *
	 * @return the active of this segments entry
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this segments entry is active.
	 *
	 * @return <code>true</code> if this segments entry is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this segments entry is active.
	 *
	 * @param active the active of this segments entry
	 */
	public void setActive(boolean active);

	/**
	 * Returns the criteria of this segments entry.
	 *
	 * @return the criteria of this segments entry
	 */
	@AutoEscape
	public String getCriteria();

	/**
	 * Sets the criteria of this segments entry.
	 *
	 * @param criteria the criteria of this segments entry
	 */
	public void setCriteria(String criteria);

	/**
	 * Returns the source of this segments entry.
	 *
	 * @return the source of this segments entry
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this segments entry.
	 *
	 * @param source the source of this segments entry
	 */
	public void setSource(String source);

	/**
	 * Returns the type of this segments entry.
	 *
	 * @return the type of this segments entry
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this segments entry.
	 *
	 * @param type the type of this segments entry
	 */
	public void setType(String type);

	/**
	 * Returns the last publish date of this segments entry.
	 *
	 * @return the last publish date of this segments entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this segments entry.
	 *
	 * @param lastPublishDate the last publish date of this segments entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

}