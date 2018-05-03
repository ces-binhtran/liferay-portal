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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * The base model interface for the CPDefinition service. Represents a row in the &quot;CPDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CPDefinitionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CPDefinitionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPDefinition
 * @see com.liferay.commerce.product.model.impl.CPDefinitionImpl
 * @see com.liferay.commerce.product.model.impl.CPDefinitionModelImpl
 * @generated
 */
@ProviderType
public interface CPDefinitionModel extends BaseModel<CPDefinition>, ShardedModel,
	StagedGroupedModel, TrashedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp definition model instance should use the {@link CPDefinition} interface instead.
	 */

	/**
	 * Returns the primary key of this cp definition.
	 *
	 * @return the primary key of this cp definition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp definition.
	 *
	 * @param primaryKey the primary key of this cp definition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp definition.
	 *
	 * @return the uuid of this cp definition
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp definition.
	 *
	 * @param uuid the uuid of this cp definition
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp definition ID of this cp definition.
	 *
	 * @return the cp definition ID of this cp definition
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cp definition.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp definition
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the group ID of this cp definition.
	 *
	 * @return the group ID of this cp definition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp definition.
	 *
	 * @param groupId the group ID of this cp definition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp definition.
	 *
	 * @return the company ID of this cp definition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp definition.
	 *
	 * @param companyId the company ID of this cp definition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp definition.
	 *
	 * @return the user ID of this cp definition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp definition.
	 *
	 * @param userId the user ID of this cp definition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp definition.
	 *
	 * @return the user uuid of this cp definition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp definition.
	 *
	 * @param userUuid the user uuid of this cp definition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp definition.
	 *
	 * @return the user name of this cp definition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp definition.
	 *
	 * @param userName the user name of this cp definition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp definition.
	 *
	 * @return the create date of this cp definition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp definition.
	 *
	 * @param createDate the create date of this cp definition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp definition.
	 *
	 * @return the modified date of this cp definition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp definition.
	 *
	 * @param modifiedDate the modified date of this cp definition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the product type name of this cp definition.
	 *
	 * @return the product type name of this cp definition
	 */
	@AutoEscape
	public String getProductTypeName();

	/**
	 * Sets the product type name of this cp definition.
	 *
	 * @param productTypeName the product type name of this cp definition
	 */
	public void setProductTypeName(String productTypeName);

	/**
	 * Returns the available individually of this cp definition.
	 *
	 * @return the available individually of this cp definition
	 */
	public boolean getAvailableIndividually();

	/**
	 * Returns <code>true</code> if this cp definition is available individually.
	 *
	 * @return <code>true</code> if this cp definition is available individually; <code>false</code> otherwise
	 */
	public boolean isAvailableIndividually();

	/**
	 * Sets whether this cp definition is available individually.
	 *
	 * @param availableIndividually the available individually of this cp definition
	 */
	public void setAvailableIndividually(boolean availableIndividually);

	/**
	 * Returns the ignore sku combinations of this cp definition.
	 *
	 * @return the ignore sku combinations of this cp definition
	 */
	public boolean getIgnoreSKUCombinations();

	/**
	 * Returns <code>true</code> if this cp definition is ignore sku combinations.
	 *
	 * @return <code>true</code> if this cp definition is ignore sku combinations; <code>false</code> otherwise
	 */
	public boolean isIgnoreSKUCombinations();

	/**
	 * Sets whether this cp definition is ignore sku combinations.
	 *
	 * @param ignoreSKUCombinations the ignore sku combinations of this cp definition
	 */
	public void setIgnoreSKUCombinations(boolean ignoreSKUCombinations);

	/**
	 * Returns the shippable of this cp definition.
	 *
	 * @return the shippable of this cp definition
	 */
	public boolean getShippable();

	/**
	 * Returns <code>true</code> if this cp definition is shippable.
	 *
	 * @return <code>true</code> if this cp definition is shippable; <code>false</code> otherwise
	 */
	public boolean isShippable();

	/**
	 * Sets whether this cp definition is shippable.
	 *
	 * @param shippable the shippable of this cp definition
	 */
	public void setShippable(boolean shippable);

	/**
	 * Returns the free shipping of this cp definition.
	 *
	 * @return the free shipping of this cp definition
	 */
	public boolean getFreeShipping();

	/**
	 * Returns <code>true</code> if this cp definition is free shipping.
	 *
	 * @return <code>true</code> if this cp definition is free shipping; <code>false</code> otherwise
	 */
	public boolean isFreeShipping();

	/**
	 * Sets whether this cp definition is free shipping.
	 *
	 * @param freeShipping the free shipping of this cp definition
	 */
	public void setFreeShipping(boolean freeShipping);

	/**
	 * Returns the ship separately of this cp definition.
	 *
	 * @return the ship separately of this cp definition
	 */
	public boolean getShipSeparately();

	/**
	 * Returns <code>true</code> if this cp definition is ship separately.
	 *
	 * @return <code>true</code> if this cp definition is ship separately; <code>false</code> otherwise
	 */
	public boolean isShipSeparately();

	/**
	 * Sets whether this cp definition is ship separately.
	 *
	 * @param shipSeparately the ship separately of this cp definition
	 */
	public void setShipSeparately(boolean shipSeparately);

	/**
	 * Returns the shipping extra price of this cp definition.
	 *
	 * @return the shipping extra price of this cp definition
	 */
	public double getShippingExtraPrice();

	/**
	 * Sets the shipping extra price of this cp definition.
	 *
	 * @param shippingExtraPrice the shipping extra price of this cp definition
	 */
	public void setShippingExtraPrice(double shippingExtraPrice);

	/**
	 * Returns the width of this cp definition.
	 *
	 * @return the width of this cp definition
	 */
	public double getWidth();

	/**
	 * Sets the width of this cp definition.
	 *
	 * @param width the width of this cp definition
	 */
	public void setWidth(double width);

	/**
	 * Returns the height of this cp definition.
	 *
	 * @return the height of this cp definition
	 */
	public double getHeight();

	/**
	 * Sets the height of this cp definition.
	 *
	 * @param height the height of this cp definition
	 */
	public void setHeight(double height);

	/**
	 * Returns the depth of this cp definition.
	 *
	 * @return the depth of this cp definition
	 */
	public double getDepth();

	/**
	 * Sets the depth of this cp definition.
	 *
	 * @param depth the depth of this cp definition
	 */
	public void setDepth(double depth);

	/**
	 * Returns the weight of this cp definition.
	 *
	 * @return the weight of this cp definition
	 */
	public double getWeight();

	/**
	 * Sets the weight of this cp definition.
	 *
	 * @param weight the weight of this cp definition
	 */
	public void setWeight(double weight);

	/**
	 * Returns the cp tax category ID of this cp definition.
	 *
	 * @return the cp tax category ID of this cp definition
	 */
	public long getCPTaxCategoryId();

	/**
	 * Sets the cp tax category ID of this cp definition.
	 *
	 * @param CPTaxCategoryId the cp tax category ID of this cp definition
	 */
	public void setCPTaxCategoryId(long CPTaxCategoryId);

	/**
	 * Returns the tax exempt of this cp definition.
	 *
	 * @return the tax exempt of this cp definition
	 */
	public boolean getTaxExempt();

	/**
	 * Returns <code>true</code> if this cp definition is tax exempt.
	 *
	 * @return <code>true</code> if this cp definition is tax exempt; <code>false</code> otherwise
	 */
	public boolean isTaxExempt();

	/**
	 * Sets whether this cp definition is tax exempt.
	 *
	 * @param taxExempt the tax exempt of this cp definition
	 */
	public void setTaxExempt(boolean taxExempt);

	/**
	 * Returns the telco or electronics of this cp definition.
	 *
	 * @return the telco or electronics of this cp definition
	 */
	public boolean getTelcoOrElectronics();

	/**
	 * Returns <code>true</code> if this cp definition is telco or electronics.
	 *
	 * @return <code>true</code> if this cp definition is telco or electronics; <code>false</code> otherwise
	 */
	public boolean isTelcoOrElectronics();

	/**
	 * Sets whether this cp definition is telco or electronics.
	 *
	 * @param telcoOrElectronics the telco or electronics of this cp definition
	 */
	public void setTelcoOrElectronics(boolean telcoOrElectronics);

	/**
	 * Returns the ddm structure key of this cp definition.
	 *
	 * @return the ddm structure key of this cp definition
	 */
	@AutoEscape
	public String getDDMStructureKey();

	/**
	 * Sets the ddm structure key of this cp definition.
	 *
	 * @param DDMStructureKey the ddm structure key of this cp definition
	 */
	public void setDDMStructureKey(String DDMStructureKey);

	/**
	 * Returns the published of this cp definition.
	 *
	 * @return the published of this cp definition
	 */
	public boolean getPublished();

	/**
	 * Returns <code>true</code> if this cp definition is published.
	 *
	 * @return <code>true</code> if this cp definition is published; <code>false</code> otherwise
	 */
	public boolean isPublished();

	/**
	 * Sets whether this cp definition is published.
	 *
	 * @param published the published of this cp definition
	 */
	public void setPublished(boolean published);

	/**
	 * Returns the external reference code of this cp definition.
	 *
	 * @return the external reference code of this cp definition
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this cp definition.
	 *
	 * @param externalReferenceCode the external reference code of this cp definition
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the display date of this cp definition.
	 *
	 * @return the display date of this cp definition
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this cp definition.
	 *
	 * @param displayDate the display date of this cp definition
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this cp definition.
	 *
	 * @return the expiration date of this cp definition
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this cp definition.
	 *
	 * @param expirationDate the expiration date of this cp definition
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the last publish date of this cp definition.
	 *
	 * @return the last publish date of this cp definition
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this cp definition.
	 *
	 * @param lastPublishDate the last publish date of this cp definition
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this cp definition.
	 *
	 * @return the status of this cp definition
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this cp definition.
	 *
	 * @param status the status of this cp definition
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this cp definition.
	 *
	 * @return the status by user ID of this cp definition
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this cp definition.
	 *
	 * @param statusByUserId the status by user ID of this cp definition
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this cp definition.
	 *
	 * @return the status by user uuid of this cp definition
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this cp definition.
	 *
	 * @param statusByUserUuid the status by user uuid of this cp definition
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this cp definition.
	 *
	 * @return the status by user name of this cp definition
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this cp definition.
	 *
	 * @param statusByUserName the status by user name of this cp definition
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this cp definition.
	 *
	 * @return the status date of this cp definition
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this cp definition.
	 *
	 * @param statusDate the status date of this cp definition
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the default language ID of this cp definition.
	 *
	 * @return the default language ID of this cp definition
	 */
	@AutoEscape
	public String getDefaultLanguageId();

	/**
	 * Sets the default language ID of this cp definition.
	 *
	 * @param defaultLanguageId the default language ID of this cp definition
	 */
	public void setDefaultLanguageId(String defaultLanguageId);

	public String[] getAvailableLanguageIds();

	public String getTitle();

	public String getTitle(String languageId);

	public String getTitle(String languageId, boolean useDefault);

	public String getTitleMapAsXML();

	public Map<String, String> getLanguageIdToTitleMap();

	public String getShortDescription();

	public String getShortDescription(String languageId);

	public String getShortDescription(String languageId, boolean useDefault);

	public String getShortDescriptionMapAsXML();

	public Map<String, String> getLanguageIdToShortDescriptionMap();

	public String getDescription();

	public String getDescription(String languageId);

	public String getDescription(String languageId, boolean useDefault);

	public String getDescriptionMapAsXML();

	public Map<String, String> getLanguageIdToDescriptionMap();

	public String getMetaTitle();

	public String getMetaTitle(String languageId);

	public String getMetaTitle(String languageId, boolean useDefault);

	public String getMetaTitleMapAsXML();

	public Map<String, String> getLanguageIdToMetaTitleMap();

	public String getMetaKeywords();

	public String getMetaKeywords(String languageId);

	public String getMetaKeywords(String languageId, boolean useDefault);

	public String getMetaKeywordsMapAsXML();

	public Map<String, String> getLanguageIdToMetaKeywordsMap();

	public String getMetaDescription();

	public String getMetaDescription(String languageId);

	public String getMetaDescription(String languageId, boolean useDefault);

	public String getMetaDescriptionMapAsXML();

	public Map<String, String> getLanguageIdToMetaDescriptionMap();

	/**
	 * Returns the trash entry created when this cp definition was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this cp definition.
	 *
	 * @return the trash entry created when this cp definition was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this cp definition.
	 *
	 * @return the class primary key of the trash entry for this cp definition
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this cp definition.
	 *
	 * @return the trash handler for this cp definition
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this cp definition is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this cp definition is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this cp definition is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this cp definition is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	/**
	 * Returns <code>true</code> if this cp definition is approved.
	 *
	 * @return <code>true</code> if this cp definition is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this cp definition is denied.
	 *
	 * @return <code>true</code> if this cp definition is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this cp definition is a draft.
	 *
	 * @return <code>true</code> if this cp definition is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this cp definition is expired.
	 *
	 * @return <code>true</code> if this cp definition is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this cp definition is inactive.
	 *
	 * @return <code>true</code> if this cp definition is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this cp definition is incomplete.
	 *
	 * @return <code>true</code> if this cp definition is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this cp definition is pending.
	 *
	 * @return <code>true</code> if this cp definition is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this cp definition is scheduled.
	 *
	 * @return <code>true</code> if this cp definition is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

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
	public Object clone();

	@Override
	public int compareTo(CPDefinition cpDefinition);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPDefinition> toCacheModel();

	@Override
	public CPDefinition toEscapedModel();

	@Override
	public CPDefinition toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}