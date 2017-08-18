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

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceOrder service. Represents a row in the &quot;CommerceOrder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.model.impl.CommerceOrderModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.model.impl.CommerceOrderImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrder
 * @see com.liferay.commerce.model.impl.CommerceOrderImpl
 * @see com.liferay.commerce.model.impl.CommerceOrderModelImpl
 * @generated
 */
@ProviderType
public interface CommerceOrderModel extends BaseModel<CommerceOrder>,
	GroupedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce order model instance should use the {@link CommerceOrder} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce order.
	 *
	 * @return the primary key of this commerce order
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce order.
	 *
	 * @param primaryKey the primary key of this commerce order
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce order ID of this commerce order.
	 *
	 * @return the commerce order ID of this commerce order
	 */
	public long getCommerceOrderId();

	/**
	 * Sets the commerce order ID of this commerce order.
	 *
	 * @param commerceOrderId the commerce order ID of this commerce order
	 */
	public void setCommerceOrderId(long commerceOrderId);

	/**
	 * Returns the group ID of this commerce order.
	 *
	 * @return the group ID of this commerce order
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce order.
	 *
	 * @param groupId the group ID of this commerce order
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce order.
	 *
	 * @return the company ID of this commerce order
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce order.
	 *
	 * @param companyId the company ID of this commerce order
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce order.
	 *
	 * @return the user ID of this commerce order
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce order.
	 *
	 * @param userId the user ID of this commerce order
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce order.
	 *
	 * @return the user uuid of this commerce order
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce order.
	 *
	 * @param userUuid the user uuid of this commerce order
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce order.
	 *
	 * @return the user name of this commerce order
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce order.
	 *
	 * @param userName the user name of this commerce order
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce order.
	 *
	 * @return the create date of this commerce order
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce order.
	 *
	 * @param createDate the create date of this commerce order
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce order.
	 *
	 * @return the modified date of this commerce order
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce order.
	 *
	 * @param modifiedDate the modified date of this commerce order
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the order user ID of this commerce order.
	 *
	 * @return the order user ID of this commerce order
	 */
	public long getOrderUserId();

	/**
	 * Sets the order user ID of this commerce order.
	 *
	 * @param orderUserId the order user ID of this commerce order
	 */
	public void setOrderUserId(long orderUserId);

	/**
	 * Returns the order user uuid of this commerce order.
	 *
	 * @return the order user uuid of this commerce order
	 */
	public String getOrderUserUuid();

	/**
	 * Sets the order user uuid of this commerce order.
	 *
	 * @param orderUserUuid the order user uuid of this commerce order
	 */
	public void setOrderUserUuid(String orderUserUuid);

	/**
	 * Returns the status of this commerce order.
	 *
	 * @return the status of this commerce order
	 */
	public int getStatus();

	/**
	 * Sets the status of this commerce order.
	 *
	 * @param status the status of this commerce order
	 */
	public void setStatus(int status);

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
	public int compareTo(CommerceOrder commerceOrder);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceOrder> toCacheModel();

	@Override
	public CommerceOrder toEscapedModel();

	@Override
	public CommerceOrder toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}