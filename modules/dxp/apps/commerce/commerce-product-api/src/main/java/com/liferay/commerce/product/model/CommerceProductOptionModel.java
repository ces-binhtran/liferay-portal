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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CommerceProductOption service. Represents a row in the &quot;CommerceProductOption&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CommerceProductOptionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CommerceProductOptionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceProductOption
 * @see com.liferay.commerce.product.model.impl.CommerceProductOptionImpl
 * @see com.liferay.commerce.product.model.impl.CommerceProductOptionModelImpl
 * @generated
 */
@ProviderType
public interface CommerceProductOptionModel extends BaseModel<CommerceProductOption>,
	GroupedModel, LocalizedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce product option model instance should use the {@link CommerceProductOption} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce product option.
	 *
	 * @return the primary key of this commerce product option
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce product option.
	 *
	 * @param primaryKey the primary key of this commerce product option
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce product option ID of this commerce product option.
	 *
	 * @return the commerce product option ID of this commerce product option
	 */
	public long getCommerceProductOptionId();

	/**
	 * Sets the commerce product option ID of this commerce product option.
	 *
	 * @param commerceProductOptionId the commerce product option ID of this commerce product option
	 */
	public void setCommerceProductOptionId(long commerceProductOptionId);

	/**
	 * Returns the group ID of this commerce product option.
	 *
	 * @return the group ID of this commerce product option
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce product option.
	 *
	 * @param groupId the group ID of this commerce product option
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce product option.
	 *
	 * @return the company ID of this commerce product option
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce product option.
	 *
	 * @param companyId the company ID of this commerce product option
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce product option.
	 *
	 * @return the user ID of this commerce product option
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce product option.
	 *
	 * @param userId the user ID of this commerce product option
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce product option.
	 *
	 * @return the user uuid of this commerce product option
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce product option.
	 *
	 * @param userUuid the user uuid of this commerce product option
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce product option.
	 *
	 * @return the user name of this commerce product option
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce product option.
	 *
	 * @param userName the user name of this commerce product option
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce product option.
	 *
	 * @return the create date of this commerce product option
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce product option.
	 *
	 * @param createDate the create date of this commerce product option
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce product option.
	 *
	 * @return the modified date of this commerce product option
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce product option.
	 *
	 * @param modifiedDate the modified date of this commerce product option
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this commerce product option.
	 *
	 * @return the name of this commerce product option
	 */
	public String getName();

	/**
	 * Returns the localized name of this commerce product option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this commerce product option
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this commerce product option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce product option. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this commerce product option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this commerce product option
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this commerce product option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this commerce product option
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this commerce product option.
	 *
	 * @return the locales and localized names of this commerce product option
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this commerce product option.
	 *
	 * @param name the name of this commerce product option
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this commerce product option in the language.
	 *
	 * @param name the localized name of this commerce product option
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this commerce product option in the language, and sets the default locale.
	 *
	 * @param name the localized name of this commerce product option
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this commerce product option from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this commerce product option
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this commerce product option from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this commerce product option
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this commerce product option.
	 *
	 * @return the description of this commerce product option
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this commerce product option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this commerce product option
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this commerce product option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce product option. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this commerce product option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this commerce product option
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this commerce product option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce product option
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this commerce product option.
	 *
	 * @return the locales and localized descriptions of this commerce product option
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this commerce product option.
	 *
	 * @param description the description of this commerce product option
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this commerce product option in the language.
	 *
	 * @param description the localized description of this commerce product option
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this commerce product option in the language, and sets the default locale.
	 *
	 * @param description the localized description of this commerce product option
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this commerce product option from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce product option
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this commerce product option from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce product option
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the ddm form field type name of this commerce product option.
	 *
	 * @return the ddm form field type name of this commerce product option
	 */
	@AutoEscape
	public String getDDMFormFieldTypeName();

	/**
	 * Sets the ddm form field type name of this commerce product option.
	 *
	 * @param DDMFormFieldTypeName the ddm form field type name of this commerce product option
	 */
	public void setDDMFormFieldTypeName(String DDMFormFieldTypeName);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceProductOption commerceProductOption);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceProductOption> toCacheModel();

	@Override
	public CommerceProductOption toEscapedModel();

	@Override
	public CommerceProductOption toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}