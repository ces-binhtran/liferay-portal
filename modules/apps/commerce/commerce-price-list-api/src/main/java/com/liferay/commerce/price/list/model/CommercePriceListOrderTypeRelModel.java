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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommercePriceListOrderTypeRel service. Represents a row in the &quot;CommercePriceListOrderTypeRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.price.list.model.impl.CommercePriceListOrderTypeRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.price.list.model.impl.CommercePriceListOrderTypeRelImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListOrderTypeRel
 * @generated
 */
@ProviderType
public interface CommercePriceListOrderTypeRelModel
	extends BaseModel<CommercePriceListOrderTypeRel>,
			CTModel<CommercePriceListOrderTypeRel>, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce price list order type rel model instance should use the {@link CommercePriceListOrderTypeRel} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce price list order type rel.
	 *
	 * @return the primary key of this commerce price list order type rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce price list order type rel.
	 *
	 * @param primaryKey the primary key of this commerce price list order type rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce price list order type rel.
	 *
	 * @return the mvcc version of this commerce price list order type rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce price list order type rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce price list order type rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this commerce price list order type rel.
	 *
	 * @return the ct collection ID of this commerce price list order type rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this commerce price list order type rel.
	 *
	 * @param ctCollectionId the ct collection ID of this commerce price list order type rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this commerce price list order type rel.
	 *
	 * @return the uuid of this commerce price list order type rel
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this commerce price list order type rel.
	 *
	 * @param uuid the uuid of this commerce price list order type rel
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the commerce price list order type rel ID of this commerce price list order type rel.
	 *
	 * @return the commerce price list order type rel ID of this commerce price list order type rel
	 */
	public long getCommercePriceListOrderTypeRelId();

	/**
	 * Sets the commerce price list order type rel ID of this commerce price list order type rel.
	 *
	 * @param commercePriceListOrderTypeRelId the commerce price list order type rel ID of this commerce price list order type rel
	 */
	public void setCommercePriceListOrderTypeRelId(
		long commercePriceListOrderTypeRelId);

	/**
	 * Returns the company ID of this commerce price list order type rel.
	 *
	 * @return the company ID of this commerce price list order type rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce price list order type rel.
	 *
	 * @param companyId the company ID of this commerce price list order type rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce price list order type rel.
	 *
	 * @return the user ID of this commerce price list order type rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce price list order type rel.
	 *
	 * @param userId the user ID of this commerce price list order type rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce price list order type rel.
	 *
	 * @return the user uuid of this commerce price list order type rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce price list order type rel.
	 *
	 * @param userUuid the user uuid of this commerce price list order type rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce price list order type rel.
	 *
	 * @return the user name of this commerce price list order type rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce price list order type rel.
	 *
	 * @param userName the user name of this commerce price list order type rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce price list order type rel.
	 *
	 * @return the create date of this commerce price list order type rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce price list order type rel.
	 *
	 * @param createDate the create date of this commerce price list order type rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce price list order type rel.
	 *
	 * @return the modified date of this commerce price list order type rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce price list order type rel.
	 *
	 * @param modifiedDate the modified date of this commerce price list order type rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce price list ID of this commerce price list order type rel.
	 *
	 * @return the commerce price list ID of this commerce price list order type rel
	 */
	public long getCommercePriceListId();

	/**
	 * Sets the commerce price list ID of this commerce price list order type rel.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price list order type rel
	 */
	public void setCommercePriceListId(long commercePriceListId);

	/**
	 * Returns the commerce order type ID of this commerce price list order type rel.
	 *
	 * @return the commerce order type ID of this commerce price list order type rel
	 */
	public long getCommerceOrderTypeId();

	/**
	 * Sets the commerce order type ID of this commerce price list order type rel.
	 *
	 * @param commerceOrderTypeId the commerce order type ID of this commerce price list order type rel
	 */
	public void setCommerceOrderTypeId(long commerceOrderTypeId);

	/**
	 * Returns the priority of this commerce price list order type rel.
	 *
	 * @return the priority of this commerce price list order type rel
	 */
	public int getPriority();

	/**
	 * Sets the priority of this commerce price list order type rel.
	 *
	 * @param priority the priority of this commerce price list order type rel
	 */
	public void setPriority(int priority);

	/**
	 * Returns the last publish date of this commerce price list order type rel.
	 *
	 * @return the last publish date of this commerce price list order type rel
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce price list order type rel.
	 *
	 * @param lastPublishDate the last publish date of this commerce price list order type rel
	 */
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public CommercePriceListOrderTypeRel cloneWithOriginalValues();

}