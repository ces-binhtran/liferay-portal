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
import com.liferay.portal.kernel.model.AttachedModel;
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
 * The base model interface for the SegmentsExperience service. Represents a row in the &quot;SegmentsExperience&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.segments.model.impl.SegmentsExperienceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.segments.model.impl.SegmentsExperienceImpl</code>.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsExperience
 * @generated
 */
@ProviderType
public interface SegmentsExperienceModel
	extends AttachedModel, BaseModel<SegmentsExperience>,
			CTModel<SegmentsExperience>, LocalizedModel, MVCCModel,
			ShardedModel, StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a segments experience model instance should use the {@link SegmentsExperience} interface instead.
	 */

	/**
	 * Returns the primary key of this segments experience.
	 *
	 * @return the primary key of this segments experience
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this segments experience.
	 *
	 * @param primaryKey the primary key of this segments experience
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this segments experience.
	 *
	 * @return the mvcc version of this segments experience
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this segments experience.
	 *
	 * @param mvccVersion the mvcc version of this segments experience
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this segments experience.
	 *
	 * @return the ct collection ID of this segments experience
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this segments experience.
	 *
	 * @param ctCollectionId the ct collection ID of this segments experience
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this segments experience.
	 *
	 * @return the uuid of this segments experience
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this segments experience.
	 *
	 * @param uuid the uuid of this segments experience
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the segments experience ID of this segments experience.
	 *
	 * @return the segments experience ID of this segments experience
	 */
	public long getSegmentsExperienceId();

	/**
	 * Sets the segments experience ID of this segments experience.
	 *
	 * @param segmentsExperienceId the segments experience ID of this segments experience
	 */
	public void setSegmentsExperienceId(long segmentsExperienceId);

	/**
	 * Returns the group ID of this segments experience.
	 *
	 * @return the group ID of this segments experience
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this segments experience.
	 *
	 * @param groupId the group ID of this segments experience
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this segments experience.
	 *
	 * @return the company ID of this segments experience
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this segments experience.
	 *
	 * @param companyId the company ID of this segments experience
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this segments experience.
	 *
	 * @return the user ID of this segments experience
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this segments experience.
	 *
	 * @param userId the user ID of this segments experience
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this segments experience.
	 *
	 * @return the user uuid of this segments experience
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this segments experience.
	 *
	 * @param userUuid the user uuid of this segments experience
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this segments experience.
	 *
	 * @return the user name of this segments experience
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this segments experience.
	 *
	 * @param userName the user name of this segments experience
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this segments experience.
	 *
	 * @return the create date of this segments experience
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this segments experience.
	 *
	 * @param createDate the create date of this segments experience
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this segments experience.
	 *
	 * @return the modified date of this segments experience
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this segments experience.
	 *
	 * @param modifiedDate the modified date of this segments experience
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the segments entry ID of this segments experience.
	 *
	 * @return the segments entry ID of this segments experience
	 */
	public long getSegmentsEntryId();

	/**
	 * Sets the segments entry ID of this segments experience.
	 *
	 * @param segmentsEntryId the segments entry ID of this segments experience
	 */
	public void setSegmentsEntryId(long segmentsEntryId);

	/**
	 * Returns the segments experience key of this segments experience.
	 *
	 * @return the segments experience key of this segments experience
	 */
	@AutoEscape
	public String getSegmentsExperienceKey();

	/**
	 * Sets the segments experience key of this segments experience.
	 *
	 * @param segmentsExperienceKey the segments experience key of this segments experience
	 */
	public void setSegmentsExperienceKey(String segmentsExperienceKey);

	/**
	 * Returns the fully qualified class name of this segments experience.
	 *
	 * @return the fully qualified class name of this segments experience
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this segments experience.
	 *
	 * @return the class name ID of this segments experience
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this segments experience.
	 *
	 * @param classNameId the class name ID of this segments experience
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this segments experience.
	 *
	 * @return the class pk of this segments experience
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this segments experience.
	 *
	 * @param classPK the class pk of this segments experience
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the name of this segments experience.
	 *
	 * @return the name of this segments experience
	 */
	public String getName();

	/**
	 * Returns the localized name of this segments experience in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this segments experience
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this segments experience in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this segments experience. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this segments experience in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this segments experience
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this segments experience in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this segments experience
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this segments experience.
	 *
	 * @return the locales and localized names of this segments experience
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this segments experience.
	 *
	 * @param name the name of this segments experience
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this segments experience in the language.
	 *
	 * @param name the localized name of this segments experience
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this segments experience in the language, and sets the default locale.
	 *
	 * @param name the localized name of this segments experience
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this segments experience from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this segments experience
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this segments experience from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this segments experience
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the priority of this segments experience.
	 *
	 * @return the priority of this segments experience
	 */
	public int getPriority();

	/**
	 * Sets the priority of this segments experience.
	 *
	 * @param priority the priority of this segments experience
	 */
	public void setPriority(int priority);

	/**
	 * Returns the active of this segments experience.
	 *
	 * @return the active of this segments experience
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this segments experience is active.
	 *
	 * @return <code>true</code> if this segments experience is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this segments experience is active.
	 *
	 * @param active the active of this segments experience
	 */
	public void setActive(boolean active);

	/**
	 * Returns the last publish date of this segments experience.
	 *
	 * @return the last publish date of this segments experience
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this segments experience.
	 *
	 * @param lastPublishDate the last publish date of this segments experience
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