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
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommercePriceListDiscountRel service. Represents a row in the &quot;CommercePriceListDiscountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.price.list.model.impl.CommercePriceListDiscountRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.price.list.model.impl.CommercePriceListDiscountRelImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListDiscountRel
 * @generated
 */
@ProviderType
public interface CommercePriceListDiscountRelModel
	extends BaseModel<CommercePriceListDiscountRel>, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce price list discount rel model instance should use the {@link CommercePriceListDiscountRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce price list discount rel.
	 *
	 * @return the primary key of this commerce price list discount rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce price list discount rel.
	 *
	 * @param primaryKey the primary key of this commerce price list discount rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this commerce price list discount rel.
	 *
	 * @return the uuid of this commerce price list discount rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce price list discount rel.
	 *
	 * @param uuid the uuid of this commerce price list discount rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the commerce price list discount rel ID of this commerce price list discount rel.
	 *
	 * @return the commerce price list discount rel ID of this commerce price list discount rel
	 */
	public long getCommercePriceListDiscountRelId();

	/**
	 * Sets the commerce price list discount rel ID of this commerce price list discount rel.
	 *
	 * @param commercePriceListDiscountRelId the commerce price list discount rel ID of this commerce price list discount rel
	 */
	public void setCommercePriceListDiscountRelId(
		long commercePriceListDiscountRelId);

	/**
	 * Returns the company ID of this commerce price list discount rel.
	 *
	 * @return the company ID of this commerce price list discount rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce price list discount rel.
	 *
	 * @param companyId the company ID of this commerce price list discount rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce price list discount rel.
	 *
	 * @return the user ID of this commerce price list discount rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce price list discount rel.
	 *
	 * @param userId the user ID of this commerce price list discount rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce price list discount rel.
	 *
	 * @return the user uuid of this commerce price list discount rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce price list discount rel.
	 *
	 * @param userUuid the user uuid of this commerce price list discount rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce price list discount rel.
	 *
	 * @return the user name of this commerce price list discount rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce price list discount rel.
	 *
	 * @param userName the user name of this commerce price list discount rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce price list discount rel.
	 *
	 * @return the create date of this commerce price list discount rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce price list discount rel.
	 *
	 * @param createDate the create date of this commerce price list discount rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce price list discount rel.
	 *
	 * @return the modified date of this commerce price list discount rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce price list discount rel.
	 *
	 * @param modifiedDate the modified date of this commerce price list discount rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce discount ID of this commerce price list discount rel.
	 *
	 * @return the commerce discount ID of this commerce price list discount rel
	 */
	public long getCommerceDiscountId();

	/**
	 * Sets the commerce discount ID of this commerce price list discount rel.
	 *
	 * @param commerceDiscountId the commerce discount ID of this commerce price list discount rel
	 */
	public void setCommerceDiscountId(long commerceDiscountId);

	/**
	 * Returns the commerce price list ID of this commerce price list discount rel.
	 *
	 * @return the commerce price list ID of this commerce price list discount rel
	 */
	public long getCommercePriceListId();

	/**
	 * Sets the commerce price list ID of this commerce price list discount rel.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price list discount rel
	 */
	public void setCommercePriceListId(long commercePriceListId);

	/**
	 * Returns the order of this commerce price list discount rel.
	 *
	 * @return the order of this commerce price list discount rel
	 */
	public int getOrder();

	/**
	 * Sets the order of this commerce price list discount rel.
	 *
	 * @param order the order of this commerce price list discount rel
	 */
	public void setOrder(int order);

	/**
	 * Returns the last publish date of this commerce price list discount rel.
	 *
	 * @return the last publish date of this commerce price list discount rel
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce price list discount rel.
	 *
	 * @param lastPublishDate the last publish date of this commerce price list discount rel
	 */
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
	public int compareTo(
		CommercePriceListDiscountRel commercePriceListDiscountRel);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommercePriceListDiscountRel> toCacheModel();

	@Override
	public CommercePriceListDiscountRel toEscapedModel();

	@Override
	public CommercePriceListDiscountRel toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}