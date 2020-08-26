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

package com.liferay.commerce.discount.model;

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
 * The base model interface for the CommerceDiscountAccountRel service. Represents a row in the &quot;CommerceDiscountAccountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.discount.model.impl.CommerceDiscountAccountRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.discount.model.impl.CommerceDiscountAccountRelImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountAccountRel
 * @generated
 */
@ProviderType
public interface CommerceDiscountAccountRelModel
	extends BaseModel<CommerceDiscountAccountRel>, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce discount account rel model instance should use the {@link CommerceDiscountAccountRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce discount account rel.
	 *
	 * @return the primary key of this commerce discount account rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce discount account rel.
	 *
	 * @param primaryKey the primary key of this commerce discount account rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this commerce discount account rel.
	 *
	 * @return the uuid of this commerce discount account rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce discount account rel.
	 *
	 * @param uuid the uuid of this commerce discount account rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the commerce discount account rel ID of this commerce discount account rel.
	 *
	 * @return the commerce discount account rel ID of this commerce discount account rel
	 */
	public long getCommerceDiscountAccountRelId();

	/**
	 * Sets the commerce discount account rel ID of this commerce discount account rel.
	 *
	 * @param commerceDiscountAccountRelId the commerce discount account rel ID of this commerce discount account rel
	 */
	public void setCommerceDiscountAccountRelId(
		long commerceDiscountAccountRelId);

	/**
	 * Returns the company ID of this commerce discount account rel.
	 *
	 * @return the company ID of this commerce discount account rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce discount account rel.
	 *
	 * @param companyId the company ID of this commerce discount account rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce discount account rel.
	 *
	 * @return the user ID of this commerce discount account rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce discount account rel.
	 *
	 * @param userId the user ID of this commerce discount account rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce discount account rel.
	 *
	 * @return the user uuid of this commerce discount account rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce discount account rel.
	 *
	 * @param userUuid the user uuid of this commerce discount account rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce discount account rel.
	 *
	 * @return the user name of this commerce discount account rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce discount account rel.
	 *
	 * @param userName the user name of this commerce discount account rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce discount account rel.
	 *
	 * @return the create date of this commerce discount account rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce discount account rel.
	 *
	 * @param createDate the create date of this commerce discount account rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce discount account rel.
	 *
	 * @return the modified date of this commerce discount account rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce discount account rel.
	 *
	 * @param modifiedDate the modified date of this commerce discount account rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce account ID of this commerce discount account rel.
	 *
	 * @return the commerce account ID of this commerce discount account rel
	 */
	public long getCommerceAccountId();

	/**
	 * Sets the commerce account ID of this commerce discount account rel.
	 *
	 * @param commerceAccountId the commerce account ID of this commerce discount account rel
	 */
	public void setCommerceAccountId(long commerceAccountId);

	/**
	 * Returns the commerce discount ID of this commerce discount account rel.
	 *
	 * @return the commerce discount ID of this commerce discount account rel
	 */
	public long getCommerceDiscountId();

	/**
	 * Sets the commerce discount ID of this commerce discount account rel.
	 *
	 * @param commerceDiscountId the commerce discount ID of this commerce discount account rel
	 */
	public void setCommerceDiscountId(long commerceDiscountId);

	/**
	 * Returns the order of this commerce discount account rel.
	 *
	 * @return the order of this commerce discount account rel
	 */
	public int getOrder();

	/**
	 * Sets the order of this commerce discount account rel.
	 *
	 * @param order the order of this commerce discount account rel
	 */
	public void setOrder(int order);

	/**
	 * Returns the last publish date of this commerce discount account rel.
	 *
	 * @return the last publish date of this commerce discount account rel
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce discount account rel.
	 *
	 * @param lastPublishDate the last publish date of this commerce discount account rel
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
	public int compareTo(CommerceDiscountAccountRel commerceDiscountAccountRel);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceDiscountAccountRel> toCacheModel();

	@Override
	public CommerceDiscountAccountRel toEscapedModel();

	@Override
	public CommerceDiscountAccountRel toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}