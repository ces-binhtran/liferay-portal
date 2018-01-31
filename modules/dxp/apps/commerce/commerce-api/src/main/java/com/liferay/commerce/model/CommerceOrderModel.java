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
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
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
	GroupedModel, ShardedModel, StagedAuditedModel, WorkflowedModel {
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
	 * Returns the uuid of this commerce order.
	 *
	 * @return the uuid of this commerce order
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce order.
	 *
	 * @param uuid the uuid of this commerce order
	 */
	@Override
	public void setUuid(String uuid);

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
	 * Returns the order organization ID of this commerce order.
	 *
	 * @return the order organization ID of this commerce order
	 */
	public long getOrderOrganizationId();

	/**
	 * Sets the order organization ID of this commerce order.
	 *
	 * @param orderOrganizationId the order organization ID of this commerce order
	 */
	public void setOrderOrganizationId(long orderOrganizationId);

	/**
	 * Returns the order root organization ID of this commerce order.
	 *
	 * @return the order root organization ID of this commerce order
	 */
	public long getOrderRootOrganizationId();

	/**
	 * Sets the order root organization ID of this commerce order.
	 *
	 * @param orderRootOrganizationId the order root organization ID of this commerce order
	 */
	public void setOrderRootOrganizationId(long orderRootOrganizationId);

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
	 * Returns the billing address ID of this commerce order.
	 *
	 * @return the billing address ID of this commerce order
	 */
	public long getBillingAddressId();

	/**
	 * Sets the billing address ID of this commerce order.
	 *
	 * @param billingAddressId the billing address ID of this commerce order
	 */
	public void setBillingAddressId(long billingAddressId);

	/**
	 * Returns the shipping address ID of this commerce order.
	 *
	 * @return the shipping address ID of this commerce order
	 */
	public long getShippingAddressId();

	/**
	 * Sets the shipping address ID of this commerce order.
	 *
	 * @param shippingAddressId the shipping address ID of this commerce order
	 */
	public void setShippingAddressId(long shippingAddressId);

	/**
	 * Returns the commerce payment method ID of this commerce order.
	 *
	 * @return the commerce payment method ID of this commerce order
	 */
	public long getCommercePaymentMethodId();

	/**
	 * Sets the commerce payment method ID of this commerce order.
	 *
	 * @param commercePaymentMethodId the commerce payment method ID of this commerce order
	 */
	public void setCommercePaymentMethodId(long commercePaymentMethodId);

	/**
	 * Returns the commerce shipping method ID of this commerce order.
	 *
	 * @return the commerce shipping method ID of this commerce order
	 */
	public long getCommerceShippingMethodId();

	/**
	 * Sets the commerce shipping method ID of this commerce order.
	 *
	 * @param commerceShippingMethodId the commerce shipping method ID of this commerce order
	 */
	public void setCommerceShippingMethodId(long commerceShippingMethodId);

	/**
	 * Returns the shipping option name of this commerce order.
	 *
	 * @return the shipping option name of this commerce order
	 */
	@AutoEscape
	public String getShippingOptionName();

	/**
	 * Sets the shipping option name of this commerce order.
	 *
	 * @param shippingOptionName the shipping option name of this commerce order
	 */
	public void setShippingOptionName(String shippingOptionName);

	/**
	 * Returns the purchase order number of this commerce order.
	 *
	 * @return the purchase order number of this commerce order
	 */
	@AutoEscape
	public String getPurchaseOrderNumber();

	/**
	 * Sets the purchase order number of this commerce order.
	 *
	 * @param purchaseOrderNumber the purchase order number of this commerce order
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber);

	/**
	 * Returns the subtotal of this commerce order.
	 *
	 * @return the subtotal of this commerce order
	 */
	public double getSubtotal();

	/**
	 * Sets the subtotal of this commerce order.
	 *
	 * @param subtotal the subtotal of this commerce order
	 */
	public void setSubtotal(double subtotal);

	/**
	 * Returns the shipping price of this commerce order.
	 *
	 * @return the shipping price of this commerce order
	 */
	public double getShippingPrice();

	/**
	 * Sets the shipping price of this commerce order.
	 *
	 * @param shippingPrice the shipping price of this commerce order
	 */
	public void setShippingPrice(double shippingPrice);

	/**
	 * Returns the total of this commerce order.
	 *
	 * @return the total of this commerce order
	 */
	public double getTotal();

	/**
	 * Sets the total of this commerce order.
	 *
	 * @param total the total of this commerce order
	 */
	public void setTotal(double total);

	/**
	 * Returns the payment status of this commerce order.
	 *
	 * @return the payment status of this commerce order
	 */
	public int getPaymentStatus();

	/**
	 * Sets the payment status of this commerce order.
	 *
	 * @param paymentStatus the payment status of this commerce order
	 */
	public void setPaymentStatus(int paymentStatus);

	/**
	 * Returns the shipping status of this commerce order.
	 *
	 * @return the shipping status of this commerce order
	 */
	public int getShippingStatus();

	/**
	 * Sets the shipping status of this commerce order.
	 *
	 * @param shippingStatus the shipping status of this commerce order
	 */
	public void setShippingStatus(int shippingStatus);

	/**
	 * Returns the order status of this commerce order.
	 *
	 * @return the order status of this commerce order
	 */
	public int getOrderStatus();

	/**
	 * Sets the order status of this commerce order.
	 *
	 * @param orderStatus the order status of this commerce order
	 */
	public void setOrderStatus(int orderStatus);

	/**
	 * Returns the status of this commerce order.
	 *
	 * @return the status of this commerce order
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this commerce order.
	 *
	 * @param status the status of this commerce order
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this commerce order.
	 *
	 * @return the status by user ID of this commerce order
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this commerce order.
	 *
	 * @param statusByUserId the status by user ID of this commerce order
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this commerce order.
	 *
	 * @return the status by user uuid of this commerce order
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this commerce order.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce order
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this commerce order.
	 *
	 * @return the status by user name of this commerce order
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this commerce order.
	 *
	 * @param statusByUserName the status by user name of this commerce order
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this commerce order.
	 *
	 * @return the status date of this commerce order
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this commerce order.
	 *
	 * @param statusDate the status date of this commerce order
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this commerce order is approved.
	 *
	 * @return <code>true</code> if this commerce order is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this commerce order is denied.
	 *
	 * @return <code>true</code> if this commerce order is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this commerce order is a draft.
	 *
	 * @return <code>true</code> if this commerce order is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this commerce order is expired.
	 *
	 * @return <code>true</code> if this commerce order is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this commerce order is inactive.
	 *
	 * @return <code>true</code> if this commerce order is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this commerce order is incomplete.
	 *
	 * @return <code>true</code> if this commerce order is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this commerce order is pending.
	 *
	 * @return <code>true</code> if this commerce order is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this commerce order is scheduled.
	 *
	 * @return <code>true</code> if this commerce order is scheduled; <code>false</code> otherwise
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