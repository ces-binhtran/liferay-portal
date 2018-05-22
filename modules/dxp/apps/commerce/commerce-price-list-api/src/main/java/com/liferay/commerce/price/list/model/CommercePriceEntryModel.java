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

package com.liferay.commerce.price.list.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

/**
 * The base model interface for the CommercePriceEntry service. Represents a row in the &quot;CommercePriceEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.price.list.model.impl.CommercePriceEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.price.list.model.impl.CommercePriceEntryImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceEntry
 * @see com.liferay.commerce.price.list.model.impl.CommercePriceEntryImpl
 * @see com.liferay.commerce.price.list.model.impl.CommercePriceEntryModelImpl
 * @generated
 */
@ProviderType
public interface CommercePriceEntryModel extends BaseModel<CommercePriceEntry>,
	ShardedModel, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce price entry model instance should use the {@link CommercePriceEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce price entry.
	 *
	 * @return the primary key of this commerce price entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce price entry.
	 *
	 * @param primaryKey the primary key of this commerce price entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this commerce price entry.
	 *
	 * @return the uuid of this commerce price entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce price entry.
	 *
	 * @param uuid the uuid of this commerce price entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the commerce price entry ID of this commerce price entry.
	 *
	 * @return the commerce price entry ID of this commerce price entry
	 */
	public long getCommercePriceEntryId();

	/**
	 * Sets the commerce price entry ID of this commerce price entry.
	 *
	 * @param commercePriceEntryId the commerce price entry ID of this commerce price entry
	 */
	public void setCommercePriceEntryId(long commercePriceEntryId);

	/**
	 * Returns the group ID of this commerce price entry.
	 *
	 * @return the group ID of this commerce price entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce price entry.
	 *
	 * @param groupId the group ID of this commerce price entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce price entry.
	 *
	 * @return the company ID of this commerce price entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce price entry.
	 *
	 * @param companyId the company ID of this commerce price entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce price entry.
	 *
	 * @return the user ID of this commerce price entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce price entry.
	 *
	 * @param userId the user ID of this commerce price entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce price entry.
	 *
	 * @return the user uuid of this commerce price entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce price entry.
	 *
	 * @param userUuid the user uuid of this commerce price entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce price entry.
	 *
	 * @return the user name of this commerce price entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce price entry.
	 *
	 * @param userName the user name of this commerce price entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce price entry.
	 *
	 * @return the create date of this commerce price entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce price entry.
	 *
	 * @param createDate the create date of this commerce price entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce price entry.
	 *
	 * @return the modified date of this commerce price entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce price entry.
	 *
	 * @param modifiedDate the modified date of this commerce price entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp instance ID of this commerce price entry.
	 *
	 * @return the cp instance ID of this commerce price entry
	 */
	public long getCPInstanceId();

	/**
	 * Sets the cp instance ID of this commerce price entry.
	 *
	 * @param CPInstanceId the cp instance ID of this commerce price entry
	 */
	public void setCPInstanceId(long CPInstanceId);

	/**
	 * Returns the commerce price list ID of this commerce price entry.
	 *
	 * @return the commerce price list ID of this commerce price entry
	 */
	public long getCommercePriceListId();

	/**
	 * Sets the commerce price list ID of this commerce price entry.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price entry
	 */
	public void setCommercePriceListId(long commercePriceListId);

	/**
	 * Returns the price of this commerce price entry.
	 *
	 * @return the price of this commerce price entry
	 */
	public BigDecimal getPrice();

	/**
	 * Sets the price of this commerce price entry.
	 *
	 * @param price the price of this commerce price entry
	 */
	public void setPrice(BigDecimal price);

	/**
	 * Returns the promo price of this commerce price entry.
	 *
	 * @return the promo price of this commerce price entry
	 */
	public BigDecimal getPromoPrice();

	/**
	 * Sets the promo price of this commerce price entry.
	 *
	 * @param promoPrice the promo price of this commerce price entry
	 */
	public void setPromoPrice(BigDecimal promoPrice);

	/**
	 * Returns the has tier price of this commerce price entry.
	 *
	 * @return the has tier price of this commerce price entry
	 */
	public boolean getHasTierPrice();

	/**
	 * Returns <code>true</code> if this commerce price entry is has tier price.
	 *
	 * @return <code>true</code> if this commerce price entry is has tier price; <code>false</code> otherwise
	 */
	public boolean isHasTierPrice();

	/**
	 * Sets whether this commerce price entry is has tier price.
	 *
	 * @param hasTierPrice the has tier price of this commerce price entry
	 */
	public void setHasTierPrice(boolean hasTierPrice);

	/**
	 * Returns the external reference code of this commerce price entry.
	 *
	 * @return the external reference code of this commerce price entry
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce price entry.
	 *
	 * @param externalReferenceCode the external reference code of this commerce price entry
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the last publish date of this commerce price entry.
	 *
	 * @return the last publish date of this commerce price entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce price entry.
	 *
	 * @param lastPublishDate the last publish date of this commerce price entry
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
	public Object clone();

	@Override
	public int compareTo(CommercePriceEntry commercePriceEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommercePriceEntry> toCacheModel();

	@Override
	public CommercePriceEntry toEscapedModel();

	@Override
	public CommercePriceEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}