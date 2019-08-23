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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TypedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMStructure service. Represents a row in the &quot;DDMStructure&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructure
 * @generated
 */
@ProviderType
public interface DDMStructureModel
	extends BaseModel<DDMStructure>, LocalizedModel, MVCCModel, ShardedModel,
			StagedGroupedModel, TypedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm structure model instance should use the {@link DDMStructure} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm structure.
	 *
	 * @return the primary key of this ddm structure
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm structure.
	 *
	 * @param primaryKey the primary key of this ddm structure
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm structure.
	 *
	 * @return the mvcc version of this ddm structure
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm structure.
	 *
	 * @param mvccVersion the mvcc version of this ddm structure
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this ddm structure.
	 *
	 * @return the uuid of this ddm structure
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this ddm structure.
	 *
	 * @param uuid the uuid of this ddm structure
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the structure ID of this ddm structure.
	 *
	 * @return the structure ID of this ddm structure
	 */
	public long getStructureId();

	/**
	 * Sets the structure ID of this ddm structure.
	 *
	 * @param structureId the structure ID of this ddm structure
	 */
	public void setStructureId(long structureId);

	/**
	 * Returns the group ID of this ddm structure.
	 *
	 * @return the group ID of this ddm structure
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm structure.
	 *
	 * @param groupId the group ID of this ddm structure
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm structure.
	 *
	 * @return the company ID of this ddm structure
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm structure.
	 *
	 * @param companyId the company ID of this ddm structure
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddm structure.
	 *
	 * @return the user ID of this ddm structure
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this ddm structure.
	 *
	 * @param userId the user ID of this ddm structure
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddm structure.
	 *
	 * @return the user uuid of this ddm structure
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddm structure.
	 *
	 * @param userUuid the user uuid of this ddm structure
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddm structure.
	 *
	 * @return the user name of this ddm structure
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this ddm structure.
	 *
	 * @param userName the user name of this ddm structure
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the version user ID of this ddm structure.
	 *
	 * @return the version user ID of this ddm structure
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this ddm structure.
	 *
	 * @param versionUserId the version user ID of this ddm structure
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this ddm structure.
	 *
	 * @return the version user uuid of this ddm structure
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this ddm structure.
	 *
	 * @param versionUserUuid the version user uuid of this ddm structure
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the version user name of this ddm structure.
	 *
	 * @return the version user name of this ddm structure
	 */
	@AutoEscape
	public String getVersionUserName();

	/**
	 * Sets the version user name of this ddm structure.
	 *
	 * @param versionUserName the version user name of this ddm structure
	 */
	public void setVersionUserName(String versionUserName);

	/**
	 * Returns the create date of this ddm structure.
	 *
	 * @return the create date of this ddm structure
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm structure.
	 *
	 * @param createDate the create date of this ddm structure
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ddm structure.
	 *
	 * @return the modified date of this ddm structure
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ddm structure.
	 *
	 * @param modifiedDate the modified date of this ddm structure
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent structure ID of this ddm structure.
	 *
	 * @return the parent structure ID of this ddm structure
	 */
	public long getParentStructureId();

	/**
	 * Sets the parent structure ID of this ddm structure.
	 *
	 * @param parentStructureId the parent structure ID of this ddm structure
	 */
	public void setParentStructureId(long parentStructureId);

	/**
	 * Returns the fully qualified class name of this ddm structure.
	 *
	 * @return the fully qualified class name of this ddm structure
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this ddm structure.
	 *
	 * @return the class name ID of this ddm structure
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this ddm structure.
	 *
	 * @param classNameId the class name ID of this ddm structure
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the structure key of this ddm structure.
	 *
	 * @return the structure key of this ddm structure
	 */
	public String getStructureKey();

	/**
	 * Sets the structure key of this ddm structure.
	 *
	 * @param structureKey the structure key of this ddm structure
	 */
	public void setStructureKey(String structureKey);

	/**
	 * Returns the version of this ddm structure.
	 *
	 * @return the version of this ddm structure
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddm structure.
	 *
	 * @param version the version of this ddm structure
	 */
	public void setVersion(String version);

	/**
	 * Returns the name of this ddm structure.
	 *
	 * @return the name of this ddm structure
	 */
	public String getName();

	/**
	 * Returns the localized name of this ddm structure in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this ddm structure
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this ddm structure in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm structure. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this ddm structure in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this ddm structure
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this ddm structure in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm structure
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this ddm structure.
	 *
	 * @return the locales and localized names of this ddm structure
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this ddm structure.
	 *
	 * @param name the name of this ddm structure
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this ddm structure in the language.
	 *
	 * @param name the localized name of this ddm structure
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this ddm structure in the language, and sets the default locale.
	 *
	 * @param name the localized name of this ddm structure
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this ddm structure from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this ddm structure
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this ddm structure from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this ddm structure
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this ddm structure.
	 *
	 * @return the description of this ddm structure
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this ddm structure in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this ddm structure
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this ddm structure in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm structure. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this ddm structure in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this ddm structure
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this ddm structure in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm structure
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this ddm structure.
	 *
	 * @return the locales and localized descriptions of this ddm structure
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this ddm structure.
	 *
	 * @param description the description of this ddm structure
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this ddm structure in the language.
	 *
	 * @param description the localized description of this ddm structure
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this ddm structure in the language, and sets the default locale.
	 *
	 * @param description the localized description of this ddm structure
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this ddm structure from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm structure
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this ddm structure from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm structure
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the definition of this ddm structure.
	 *
	 * @return the definition of this ddm structure
	 */
	@AutoEscape
	public String getDefinition();

	/**
	 * Sets the definition of this ddm structure.
	 *
	 * @param definition the definition of this ddm structure
	 */
	public void setDefinition(String definition);

	/**
	 * Returns the storage type of this ddm structure.
	 *
	 * @return the storage type of this ddm structure
	 */
	@AutoEscape
	public String getStorageType();

	/**
	 * Sets the storage type of this ddm structure.
	 *
	 * @param storageType the storage type of this ddm structure
	 */
	public void setStorageType(String storageType);

	/**
	 * Returns the type of this ddm structure.
	 *
	 * @return the type of this ddm structure
	 */
	public int getType();

	/**
	 * Sets the type of this ddm structure.
	 *
	 * @param type the type of this ddm structure
	 */
	public void setType(int type);

	/**
	 * Returns the last publish date of this ddm structure.
	 *
	 * @return the last publish date of this ddm structure
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this ddm structure.
	 *
	 * @param lastPublishDate the last publish date of this ddm structure
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