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
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CPDefinitionOptionValueRel service. Represents a row in the &quot;CPDefinitionOptionValueRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CPDefinitionOptionValueRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CPDefinitionOptionValueRelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinitionOptionValueRel
 * @generated
 */
@ProviderType
public interface CPDefinitionOptionValueRelModel
	extends BaseModel<CPDefinitionOptionValueRel>, GroupedModel, LocalizedModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp definition option value rel model instance should use the {@link CPDefinitionOptionValueRel} interface instead.
	 */

	/**
	 * Returns the primary key of this cp definition option value rel.
	 *
	 * @return the primary key of this cp definition option value rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp definition option value rel.
	 *
	 * @param primaryKey the primary key of this cp definition option value rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp definition option value rel.
	 *
	 * @return the uuid of this cp definition option value rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp definition option value rel.
	 *
	 * @param uuid the uuid of this cp definition option value rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp definition option value rel ID of this cp definition option value rel.
	 *
	 * @return the cp definition option value rel ID of this cp definition option value rel
	 */
	public long getCPDefinitionOptionValueRelId();

	/**
	 * Sets the cp definition option value rel ID of this cp definition option value rel.
	 *
	 * @param CPDefinitionOptionValueRelId the cp definition option value rel ID of this cp definition option value rel
	 */
	public void setCPDefinitionOptionValueRelId(
		long CPDefinitionOptionValueRelId);

	/**
	 * Returns the group ID of this cp definition option value rel.
	 *
	 * @return the group ID of this cp definition option value rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp definition option value rel.
	 *
	 * @param groupId the group ID of this cp definition option value rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp definition option value rel.
	 *
	 * @return the company ID of this cp definition option value rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp definition option value rel.
	 *
	 * @param companyId the company ID of this cp definition option value rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp definition option value rel.
	 *
	 * @return the user ID of this cp definition option value rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp definition option value rel.
	 *
	 * @param userId the user ID of this cp definition option value rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp definition option value rel.
	 *
	 * @return the user uuid of this cp definition option value rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp definition option value rel.
	 *
	 * @param userUuid the user uuid of this cp definition option value rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp definition option value rel.
	 *
	 * @return the user name of this cp definition option value rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp definition option value rel.
	 *
	 * @param userName the user name of this cp definition option value rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp definition option value rel.
	 *
	 * @return the create date of this cp definition option value rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp definition option value rel.
	 *
	 * @param createDate the create date of this cp definition option value rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp definition option value rel.
	 *
	 * @return the modified date of this cp definition option value rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp definition option value rel.
	 *
	 * @param modifiedDate the modified date of this cp definition option value rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition option rel ID of this cp definition option value rel.
	 *
	 * @return the cp definition option rel ID of this cp definition option value rel
	 */
	public long getCPDefinitionOptionRelId();

	/**
	 * Sets the cp definition option rel ID of this cp definition option value rel.
	 *
	 * @param CPDefinitionOptionRelId the cp definition option rel ID of this cp definition option value rel
	 */
	public void setCPDefinitionOptionRelId(long CPDefinitionOptionRelId);

	/**
	 * Returns the cp instance uuid of this cp definition option value rel.
	 *
	 * @return the cp instance uuid of this cp definition option value rel
	 */
	@AutoEscape
	public String getCPInstanceUuid();

	/**
	 * Sets the cp instance uuid of this cp definition option value rel.
	 *
	 * @param CPInstanceUuid the cp instance uuid of this cp definition option value rel
	 */
	public void setCPInstanceUuid(String CPInstanceUuid);

	/**
	 * Returns the c product ID of this cp definition option value rel.
	 *
	 * @return the c product ID of this cp definition option value rel
	 */
	public long getCProductId();

	/**
	 * Sets the c product ID of this cp definition option value rel.
	 *
	 * @param CProductId the c product ID of this cp definition option value rel
	 */
	public void setCProductId(long CProductId);

	/**
	 * Returns the name of this cp definition option value rel.
	 *
	 * @return the name of this cp definition option value rel
	 */
	public String getName();

	/**
	 * Returns the localized name of this cp definition option value rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this cp definition option value rel
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this cp definition option value rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this cp definition option value rel. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this cp definition option value rel in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this cp definition option value rel
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this cp definition option value rel in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this cp definition option value rel
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this cp definition option value rel.
	 *
	 * @return the locales and localized names of this cp definition option value rel
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this cp definition option value rel.
	 *
	 * @param name the name of this cp definition option value rel
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this cp definition option value rel in the language.
	 *
	 * @param name the localized name of this cp definition option value rel
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this cp definition option value rel in the language, and sets the default locale.
	 *
	 * @param name the localized name of this cp definition option value rel
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this cp definition option value rel from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this cp definition option value rel
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this cp definition option value rel from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this cp definition option value rel
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the priority of this cp definition option value rel.
	 *
	 * @return the priority of this cp definition option value rel
	 */
	public double getPriority();

	/**
	 * Sets the priority of this cp definition option value rel.
	 *
	 * @param priority the priority of this cp definition option value rel
	 */
	public void setPriority(double priority);

	/**
	 * Returns the key of this cp definition option value rel.
	 *
	 * @return the key of this cp definition option value rel
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this cp definition option value rel.
	 *
	 * @param key the key of this cp definition option value rel
	 */
	public void setKey(String key);

	/**
	 * Returns the quantity of this cp definition option value rel.
	 *
	 * @return the quantity of this cp definition option value rel
	 */
	public int getQuantity();

	/**
	 * Sets the quantity of this cp definition option value rel.
	 *
	 * @param quantity the quantity of this cp definition option value rel
	 */
	public void setQuantity(int quantity);

	/**
	 * Returns the preselected of this cp definition option value rel.
	 *
	 * @return the preselected of this cp definition option value rel
	 */
	public boolean getPreselected();

	/**
	 * Returns <code>true</code> if this cp definition option value rel is preselected.
	 *
	 * @return <code>true</code> if this cp definition option value rel is preselected; <code>false</code> otherwise
	 */
	public boolean isPreselected();

	/**
	 * Sets whether this cp definition option value rel is preselected.
	 *
	 * @param preselected the preselected of this cp definition option value rel
	 */
	public void setPreselected(boolean preselected);

	/**
	 * Returns the price of this cp definition option value rel.
	 *
	 * @return the price of this cp definition option value rel
	 */
	public BigDecimal getPrice();

	/**
	 * Sets the price of this cp definition option value rel.
	 *
	 * @param price the price of this cp definition option value rel
	 */
	public void setPrice(BigDecimal price);

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
	public int compareTo(CPDefinitionOptionValueRel cpDefinitionOptionValueRel);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPDefinitionOptionValueRel> toCacheModel();

	@Override
	public CPDefinitionOptionValueRel toEscapedModel();

	@Override
	public CPDefinitionOptionValueRel toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}