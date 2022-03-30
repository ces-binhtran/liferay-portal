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

package com.liferay.object.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ObjectLayoutColumn service. Represents a row in the &quot;ObjectLayoutColumn&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.object.model.impl.ObjectLayoutColumnModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.object.model.impl.ObjectLayoutColumnImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectLayoutColumn
 * @generated
 */
@ProviderType
public interface ObjectLayoutColumnModel
	extends BaseModel<ObjectLayoutColumn>, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a object layout column model instance should use the {@link ObjectLayoutColumn} interface instead.
	 */

	/**
	 * Returns the primary key of this object layout column.
	 *
	 * @return the primary key of this object layout column
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this object layout column.
	 *
	 * @param primaryKey the primary key of this object layout column
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this object layout column.
	 *
	 * @return the mvcc version of this object layout column
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this object layout column.
	 *
	 * @param mvccVersion the mvcc version of this object layout column
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this object layout column.
	 *
	 * @return the uuid of this object layout column
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this object layout column.
	 *
	 * @param uuid the uuid of this object layout column
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the object layout column ID of this object layout column.
	 *
	 * @return the object layout column ID of this object layout column
	 */
	public long getObjectLayoutColumnId();

	/**
	 * Sets the object layout column ID of this object layout column.
	 *
	 * @param objectLayoutColumnId the object layout column ID of this object layout column
	 */
	public void setObjectLayoutColumnId(long objectLayoutColumnId);

	/**
	 * Returns the company ID of this object layout column.
	 *
	 * @return the company ID of this object layout column
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this object layout column.
	 *
	 * @param companyId the company ID of this object layout column
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this object layout column.
	 *
	 * @return the user ID of this object layout column
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this object layout column.
	 *
	 * @param userId the user ID of this object layout column
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this object layout column.
	 *
	 * @return the user uuid of this object layout column
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this object layout column.
	 *
	 * @param userUuid the user uuid of this object layout column
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this object layout column.
	 *
	 * @return the user name of this object layout column
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this object layout column.
	 *
	 * @param userName the user name of this object layout column
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this object layout column.
	 *
	 * @return the create date of this object layout column
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this object layout column.
	 *
	 * @param createDate the create date of this object layout column
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this object layout column.
	 *
	 * @return the modified date of this object layout column
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this object layout column.
	 *
	 * @param modifiedDate the modified date of this object layout column
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the object field ID of this object layout column.
	 *
	 * @return the object field ID of this object layout column
	 */
	public long getObjectFieldId();

	/**
	 * Sets the object field ID of this object layout column.
	 *
	 * @param objectFieldId the object field ID of this object layout column
	 */
	public void setObjectFieldId(long objectFieldId);

	/**
	 * Returns the object layout row ID of this object layout column.
	 *
	 * @return the object layout row ID of this object layout column
	 */
	public long getObjectLayoutRowId();

	/**
	 * Sets the object layout row ID of this object layout column.
	 *
	 * @param objectLayoutRowId the object layout row ID of this object layout column
	 */
	public void setObjectLayoutRowId(long objectLayoutRowId);

	/**
	 * Returns the priority of this object layout column.
	 *
	 * @return the priority of this object layout column
	 */
	public int getPriority();

	/**
	 * Sets the priority of this object layout column.
	 *
	 * @param priority the priority of this object layout column
	 */
	public void setPriority(int priority);

	/**
	 * Returns the size of this object layout column.
	 *
	 * @return the size of this object layout column
	 */
	public int getSize();

	/**
	 * Sets the size of this object layout column.
	 *
	 * @param size the size of this object layout column
	 */
	public void setSize(int size);

	@Override
	public ObjectLayoutColumn cloneWithOriginalValues();

}