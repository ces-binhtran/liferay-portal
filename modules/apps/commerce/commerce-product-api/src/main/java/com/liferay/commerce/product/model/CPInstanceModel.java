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
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

/**
 * The base model interface for the CPInstance service. Represents a row in the &quot;CPInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.product.model.impl.CPInstanceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.product.model.impl.CPInstanceImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CPInstance
 * @generated
 */
@ProviderType
public interface CPInstanceModel
	extends BaseModel<CPInstance>, ShardedModel, StagedGroupedModel,
			WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp instance model instance should use the {@link CPInstance} interface instead.
	 */

	/**
	 * Returns the primary key of this cp instance.
	 *
	 * @return the primary key of this cp instance
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp instance.
	 *
	 * @param primaryKey the primary key of this cp instance
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp instance.
	 *
	 * @return the uuid of this cp instance
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp instance.
	 *
	 * @param uuid the uuid of this cp instance
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this cp instance.
	 *
	 * @return the external reference code of this cp instance
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this cp instance.
	 *
	 * @param externalReferenceCode the external reference code of this cp instance
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the cp instance ID of this cp instance.
	 *
	 * @return the cp instance ID of this cp instance
	 */
	public long getCPInstanceId();

	/**
	 * Sets the cp instance ID of this cp instance.
	 *
	 * @param CPInstanceId the cp instance ID of this cp instance
	 */
	public void setCPInstanceId(long CPInstanceId);

	/**
	 * Returns the group ID of this cp instance.
	 *
	 * @return the group ID of this cp instance
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp instance.
	 *
	 * @param groupId the group ID of this cp instance
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp instance.
	 *
	 * @return the company ID of this cp instance
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp instance.
	 *
	 * @param companyId the company ID of this cp instance
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp instance.
	 *
	 * @return the user ID of this cp instance
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp instance.
	 *
	 * @param userId the user ID of this cp instance
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp instance.
	 *
	 * @return the user uuid of this cp instance
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp instance.
	 *
	 * @param userUuid the user uuid of this cp instance
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp instance.
	 *
	 * @return the user name of this cp instance
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp instance.
	 *
	 * @param userName the user name of this cp instance
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp instance.
	 *
	 * @return the create date of this cp instance
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp instance.
	 *
	 * @param createDate the create date of this cp instance
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp instance.
	 *
	 * @return the modified date of this cp instance
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp instance.
	 *
	 * @param modifiedDate the modified date of this cp instance
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition ID of this cp instance.
	 *
	 * @return the cp definition ID of this cp instance
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cp instance.
	 *
	 * @param CPDefinitionId the cp definition ID of this cp instance
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the cp instance uuid of this cp instance.
	 *
	 * @return the cp instance uuid of this cp instance
	 */
	@AutoEscape
	public String getCPInstanceUuid();

	/**
	 * Sets the cp instance uuid of this cp instance.
	 *
	 * @param CPInstanceUuid the cp instance uuid of this cp instance
	 */
	public void setCPInstanceUuid(String CPInstanceUuid);

	/**
	 * Returns the sku of this cp instance.
	 *
	 * @return the sku of this cp instance
	 */
	@AutoEscape
	public String getSku();

	/**
	 * Sets the sku of this cp instance.
	 *
	 * @param sku the sku of this cp instance
	 */
	public void setSku(String sku);

	/**
	 * Returns the gtin of this cp instance.
	 *
	 * @return the gtin of this cp instance
	 */
	@AutoEscape
	public String getGtin();

	/**
	 * Sets the gtin of this cp instance.
	 *
	 * @param gtin the gtin of this cp instance
	 */
	public void setGtin(String gtin);

	/**
	 * Returns the manufacturer part number of this cp instance.
	 *
	 * @return the manufacturer part number of this cp instance
	 */
	@AutoEscape
	public String getManufacturerPartNumber();

	/**
	 * Sets the manufacturer part number of this cp instance.
	 *
	 * @param manufacturerPartNumber the manufacturer part number of this cp instance
	 */
	public void setManufacturerPartNumber(String manufacturerPartNumber);

	/**
	 * Returns the purchasable of this cp instance.
	 *
	 * @return the purchasable of this cp instance
	 */
	public boolean getPurchasable();

	/**
	 * Returns <code>true</code> if this cp instance is purchasable.
	 *
	 * @return <code>true</code> if this cp instance is purchasable; <code>false</code> otherwise
	 */
	public boolean isPurchasable();

	/**
	 * Sets whether this cp instance is purchasable.
	 *
	 * @param purchasable the purchasable of this cp instance
	 */
	public void setPurchasable(boolean purchasable);

	/**
	 * Returns the width of this cp instance.
	 *
	 * @return the width of this cp instance
	 */
	public double getWidth();

	/**
	 * Sets the width of this cp instance.
	 *
	 * @param width the width of this cp instance
	 */
	public void setWidth(double width);

	/**
	 * Returns the height of this cp instance.
	 *
	 * @return the height of this cp instance
	 */
	public double getHeight();

	/**
	 * Sets the height of this cp instance.
	 *
	 * @param height the height of this cp instance
	 */
	public void setHeight(double height);

	/**
	 * Returns the depth of this cp instance.
	 *
	 * @return the depth of this cp instance
	 */
	public double getDepth();

	/**
	 * Sets the depth of this cp instance.
	 *
	 * @param depth the depth of this cp instance
	 */
	public void setDepth(double depth);

	/**
	 * Returns the weight of this cp instance.
	 *
	 * @return the weight of this cp instance
	 */
	public double getWeight();

	/**
	 * Sets the weight of this cp instance.
	 *
	 * @param weight the weight of this cp instance
	 */
	public void setWeight(double weight);

	/**
	 * Returns the price of this cp instance.
	 *
	 * @return the price of this cp instance
	 */
	public BigDecimal getPrice();

	/**
	 * Sets the price of this cp instance.
	 *
	 * @param price the price of this cp instance
	 */
	public void setPrice(BigDecimal price);

	/**
	 * Returns the promo price of this cp instance.
	 *
	 * @return the promo price of this cp instance
	 */
	public BigDecimal getPromoPrice();

	/**
	 * Sets the promo price of this cp instance.
	 *
	 * @param promoPrice the promo price of this cp instance
	 */
	public void setPromoPrice(BigDecimal promoPrice);

	/**
	 * Returns the cost of this cp instance.
	 *
	 * @return the cost of this cp instance
	 */
	public BigDecimal getCost();

	/**
	 * Sets the cost of this cp instance.
	 *
	 * @param cost the cost of this cp instance
	 */
	public void setCost(BigDecimal cost);

	/**
	 * Returns the published of this cp instance.
	 *
	 * @return the published of this cp instance
	 */
	public boolean getPublished();

	/**
	 * Returns <code>true</code> if this cp instance is published.
	 *
	 * @return <code>true</code> if this cp instance is published; <code>false</code> otherwise
	 */
	public boolean isPublished();

	/**
	 * Sets whether this cp instance is published.
	 *
	 * @param published the published of this cp instance
	 */
	public void setPublished(boolean published);

	/**
	 * Returns the display date of this cp instance.
	 *
	 * @return the display date of this cp instance
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this cp instance.
	 *
	 * @param displayDate the display date of this cp instance
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this cp instance.
	 *
	 * @return the expiration date of this cp instance
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this cp instance.
	 *
	 * @param expirationDate the expiration date of this cp instance
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the last publish date of this cp instance.
	 *
	 * @return the last publish date of this cp instance
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this cp instance.
	 *
	 * @param lastPublishDate the last publish date of this cp instance
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the override subscription info of this cp instance.
	 *
	 * @return the override subscription info of this cp instance
	 */
	public boolean getOverrideSubscriptionInfo();

	/**
	 * Returns <code>true</code> if this cp instance is override subscription info.
	 *
	 * @return <code>true</code> if this cp instance is override subscription info; <code>false</code> otherwise
	 */
	public boolean isOverrideSubscriptionInfo();

	/**
	 * Sets whether this cp instance is override subscription info.
	 *
	 * @param overrideSubscriptionInfo the override subscription info of this cp instance
	 */
	public void setOverrideSubscriptionInfo(boolean overrideSubscriptionInfo);

	/**
	 * Returns the subscription enabled of this cp instance.
	 *
	 * @return the subscription enabled of this cp instance
	 */
	public boolean getSubscriptionEnabled();

	/**
	 * Returns <code>true</code> if this cp instance is subscription enabled.
	 *
	 * @return <code>true</code> if this cp instance is subscription enabled; <code>false</code> otherwise
	 */
	public boolean isSubscriptionEnabled();

	/**
	 * Sets whether this cp instance is subscription enabled.
	 *
	 * @param subscriptionEnabled the subscription enabled of this cp instance
	 */
	public void setSubscriptionEnabled(boolean subscriptionEnabled);

	/**
	 * Returns the subscription length of this cp instance.
	 *
	 * @return the subscription length of this cp instance
	 */
	public int getSubscriptionLength();

	/**
	 * Sets the subscription length of this cp instance.
	 *
	 * @param subscriptionLength the subscription length of this cp instance
	 */
	public void setSubscriptionLength(int subscriptionLength);

	/**
	 * Returns the subscription type of this cp instance.
	 *
	 * @return the subscription type of this cp instance
	 */
	@AutoEscape
	public String getSubscriptionType();

	/**
	 * Sets the subscription type of this cp instance.
	 *
	 * @param subscriptionType the subscription type of this cp instance
	 */
	public void setSubscriptionType(String subscriptionType);

	/**
	 * Returns the subscription type settings of this cp instance.
	 *
	 * @return the subscription type settings of this cp instance
	 */
	@AutoEscape
	public String getSubscriptionTypeSettings();

	/**
	 * Sets the subscription type settings of this cp instance.
	 *
	 * @param subscriptionTypeSettings the subscription type settings of this cp instance
	 */
	public void setSubscriptionTypeSettings(String subscriptionTypeSettings);

	/**
	 * Returns the max subscription cycles of this cp instance.
	 *
	 * @return the max subscription cycles of this cp instance
	 */
	public long getMaxSubscriptionCycles();

	/**
	 * Sets the max subscription cycles of this cp instance.
	 *
	 * @param maxSubscriptionCycles the max subscription cycles of this cp instance
	 */
	public void setMaxSubscriptionCycles(long maxSubscriptionCycles);

	/**
	 * Returns the delivery subscription enabled of this cp instance.
	 *
	 * @return the delivery subscription enabled of this cp instance
	 */
	public boolean getDeliverySubscriptionEnabled();

	/**
	 * Returns <code>true</code> if this cp instance is delivery subscription enabled.
	 *
	 * @return <code>true</code> if this cp instance is delivery subscription enabled; <code>false</code> otherwise
	 */
	public boolean isDeliverySubscriptionEnabled();

	/**
	 * Sets whether this cp instance is delivery subscription enabled.
	 *
	 * @param deliverySubscriptionEnabled the delivery subscription enabled of this cp instance
	 */
	public void setDeliverySubscriptionEnabled(
		boolean deliverySubscriptionEnabled);

	/**
	 * Returns the delivery subscription length of this cp instance.
	 *
	 * @return the delivery subscription length of this cp instance
	 */
	public int getDeliverySubscriptionLength();

	/**
	 * Sets the delivery subscription length of this cp instance.
	 *
	 * @param deliverySubscriptionLength the delivery subscription length of this cp instance
	 */
	public void setDeliverySubscriptionLength(int deliverySubscriptionLength);

	/**
	 * Returns the delivery subscription type of this cp instance.
	 *
	 * @return the delivery subscription type of this cp instance
	 */
	@AutoEscape
	public String getDeliverySubscriptionType();

	/**
	 * Sets the delivery subscription type of this cp instance.
	 *
	 * @param deliverySubscriptionType the delivery subscription type of this cp instance
	 */
	public void setDeliverySubscriptionType(String deliverySubscriptionType);

	/**
	 * Returns the delivery subscription type settings of this cp instance.
	 *
	 * @return the delivery subscription type settings of this cp instance
	 */
	@AutoEscape
	public String getDeliverySubscriptionTypeSettings();

	/**
	 * Sets the delivery subscription type settings of this cp instance.
	 *
	 * @param deliverySubscriptionTypeSettings the delivery subscription type settings of this cp instance
	 */
	public void setDeliverySubscriptionTypeSettings(
		String deliverySubscriptionTypeSettings);

	/**
	 * Returns the delivery max subscription cycles of this cp instance.
	 *
	 * @return the delivery max subscription cycles of this cp instance
	 */
	public long getDeliveryMaxSubscriptionCycles();

	/**
	 * Sets the delivery max subscription cycles of this cp instance.
	 *
	 * @param deliveryMaxSubscriptionCycles the delivery max subscription cycles of this cp instance
	 */
	public void setDeliveryMaxSubscriptionCycles(
		long deliveryMaxSubscriptionCycles);

	/**
	 * Returns the unspsc of this cp instance.
	 *
	 * @return the unspsc of this cp instance
	 */
	@AutoEscape
	public String getUnspsc();

	/**
	 * Sets the unspsc of this cp instance.
	 *
	 * @param unspsc the unspsc of this cp instance
	 */
	public void setUnspsc(String unspsc);

	/**
	 * Returns the status of this cp instance.
	 *
	 * @return the status of this cp instance
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this cp instance.
	 *
	 * @param status the status of this cp instance
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this cp instance.
	 *
	 * @return the status by user ID of this cp instance
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this cp instance.
	 *
	 * @param statusByUserId the status by user ID of this cp instance
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this cp instance.
	 *
	 * @return the status by user uuid of this cp instance
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this cp instance.
	 *
	 * @param statusByUserUuid the status by user uuid of this cp instance
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this cp instance.
	 *
	 * @return the status by user name of this cp instance
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this cp instance.
	 *
	 * @param statusByUserName the status by user name of this cp instance
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this cp instance.
	 *
	 * @return the status date of this cp instance
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this cp instance.
	 *
	 * @param statusDate the status date of this cp instance
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this cp instance is approved.
	 *
	 * @return <code>true</code> if this cp instance is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this cp instance is denied.
	 *
	 * @return <code>true</code> if this cp instance is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this cp instance is a draft.
	 *
	 * @return <code>true</code> if this cp instance is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this cp instance is expired.
	 *
	 * @return <code>true</code> if this cp instance is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this cp instance is inactive.
	 *
	 * @return <code>true</code> if this cp instance is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this cp instance is incomplete.
	 *
	 * @return <code>true</code> if this cp instance is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this cp instance is pending.
	 *
	 * @return <code>true</code> if this cp instance is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this cp instance is scheduled.
	 *
	 * @return <code>true</code> if this cp instance is scheduled; <code>false</code> otherwise
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
	public int compareTo(CPInstance cpInstance);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPInstance> toCacheModel();

	@Override
	public CPInstance toEscapedModel();

	@Override
	public CPInstance toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}