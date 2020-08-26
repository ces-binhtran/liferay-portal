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
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CPDefinitionSpecificationOptionValue service. Represents a row in the &quot;CPDSpecificationOptionValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CPDefinitionSpecificationOptionValueModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CPDefinitionSpecificationOptionValueImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionSpecificationOptionValue
 * @generated
 */
@ProviderType
public interface CPDefinitionSpecificationOptionValueModel
	extends BaseModel<CPDefinitionSpecificationOptionValue>, LocalizedModel,
			ShardedModel, StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp definition specification option value model instance should use the {@link CPDefinitionSpecificationOptionValue} interface instead.
	 */

	/**
	 * Returns the primary key of this cp definition specification option value.
	 *
	 * @return the primary key of this cp definition specification option value
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp definition specification option value.
	 *
	 * @param primaryKey the primary key of this cp definition specification option value
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp definition specification option value.
	 *
	 * @return the uuid of this cp definition specification option value
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp definition specification option value.
	 *
	 * @param uuid the uuid of this cp definition specification option value
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp definition specification option value ID of this cp definition specification option value.
	 *
	 * @return the cp definition specification option value ID of this cp definition specification option value
	 */
	public long getCPDefinitionSpecificationOptionValueId();

	/**
	 * Sets the cp definition specification option value ID of this cp definition specification option value.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the cp definition specification option value ID of this cp definition specification option value
	 */
	public void setCPDefinitionSpecificationOptionValueId(
		long CPDefinitionSpecificationOptionValueId);

	/**
	 * Returns the group ID of this cp definition specification option value.
	 *
	 * @return the group ID of this cp definition specification option value
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp definition specification option value.
	 *
	 * @param groupId the group ID of this cp definition specification option value
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp definition specification option value.
	 *
	 * @return the company ID of this cp definition specification option value
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp definition specification option value.
	 *
	 * @param companyId the company ID of this cp definition specification option value
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp definition specification option value.
	 *
	 * @return the user ID of this cp definition specification option value
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp definition specification option value.
	 *
	 * @param userId the user ID of this cp definition specification option value
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp definition specification option value.
	 *
	 * @return the user uuid of this cp definition specification option value
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp definition specification option value.
	 *
	 * @param userUuid the user uuid of this cp definition specification option value
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp definition specification option value.
	 *
	 * @return the user name of this cp definition specification option value
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp definition specification option value.
	 *
	 * @param userName the user name of this cp definition specification option value
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp definition specification option value.
	 *
	 * @return the create date of this cp definition specification option value
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp definition specification option value.
	 *
	 * @param createDate the create date of this cp definition specification option value
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp definition specification option value.
	 *
	 * @return the modified date of this cp definition specification option value
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp definition specification option value.
	 *
	 * @param modifiedDate the modified date of this cp definition specification option value
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition ID of this cp definition specification option value.
	 *
	 * @return the cp definition ID of this cp definition specification option value
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cp definition specification option value.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp definition specification option value
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the cp specification option ID of this cp definition specification option value.
	 *
	 * @return the cp specification option ID of this cp definition specification option value
	 */
	public long getCPSpecificationOptionId();

	/**
	 * Sets the cp specification option ID of this cp definition specification option value.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID of this cp definition specification option value
	 */
	public void setCPSpecificationOptionId(long CPSpecificationOptionId);

	/**
	 * Returns the cp option category ID of this cp definition specification option value.
	 *
	 * @return the cp option category ID of this cp definition specification option value
	 */
	public long getCPOptionCategoryId();

	/**
	 * Sets the cp option category ID of this cp definition specification option value.
	 *
	 * @param CPOptionCategoryId the cp option category ID of this cp definition specification option value
	 */
	public void setCPOptionCategoryId(long CPOptionCategoryId);

	/**
	 * Returns the value of this cp definition specification option value.
	 *
	 * @return the value of this cp definition specification option value
	 */
	public String getValue();

	/**
	 * Returns the localized value of this cp definition specification option value in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized value of this cp definition specification option value
	 */
	@AutoEscape
	public String getValue(Locale locale);

	/**
	 * Returns the localized value of this cp definition specification option value in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized value of this cp definition specification option value. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getValue(Locale locale, boolean useDefault);

	/**
	 * Returns the localized value of this cp definition specification option value in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized value of this cp definition specification option value
	 */
	@AutoEscape
	public String getValue(String languageId);

	/**
	 * Returns the localized value of this cp definition specification option value in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized value of this cp definition specification option value
	 */
	@AutoEscape
	public String getValue(String languageId, boolean useDefault);

	@AutoEscape
	public String getValueCurrentLanguageId();

	@AutoEscape
	public String getValueCurrentValue();

	/**
	 * Returns a map of the locales and localized values of this cp definition specification option value.
	 *
	 * @return the locales and localized values of this cp definition specification option value
	 */
	public Map<Locale, String> getValueMap();

	/**
	 * Sets the value of this cp definition specification option value.
	 *
	 * @param value the value of this cp definition specification option value
	 */
	public void setValue(String value);

	/**
	 * Sets the localized value of this cp definition specification option value in the language.
	 *
	 * @param value the localized value of this cp definition specification option value
	 * @param locale the locale of the language
	 */
	public void setValue(String value, Locale locale);

	/**
	 * Sets the localized value of this cp definition specification option value in the language, and sets the default locale.
	 *
	 * @param value the localized value of this cp definition specification option value
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setValue(String value, Locale locale, Locale defaultLocale);

	public void setValueCurrentLanguageId(String languageId);

	/**
	 * Sets the localized values of this cp definition specification option value from the map of locales and localized values.
	 *
	 * @param valueMap the locales and localized values of this cp definition specification option value
	 */
	public void setValueMap(Map<Locale, String> valueMap);

	/**
	 * Sets the localized values of this cp definition specification option value from the map of locales and localized values, and sets the default locale.
	 *
	 * @param valueMap the locales and localized values of this cp definition specification option value
	 * @param defaultLocale the default locale
	 */
	public void setValueMap(Map<Locale, String> valueMap, Locale defaultLocale);

	/**
	 * Returns the priority of this cp definition specification option value.
	 *
	 * @return the priority of this cp definition specification option value
	 */
	public double getPriority();

	/**
	 * Sets the priority of this cp definition specification option value.
	 *
	 * @param priority the priority of this cp definition specification option value
	 */
	public void setPriority(double priority);

	/**
	 * Returns the last publish date of this cp definition specification option value.
	 *
	 * @return the last publish date of this cp definition specification option value
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this cp definition specification option value.
	 *
	 * @param lastPublishDate the last publish date of this cp definition specification option value
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

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
	public int compareTo(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPDefinitionSpecificationOptionValue> toCacheModel();

	@Override
	public CPDefinitionSpecificationOptionValue toEscapedModel();

	@Override
	public CPDefinitionSpecificationOptionValue toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}