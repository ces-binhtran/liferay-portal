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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMFormInstanceRecord service. Represents a row in the &quot;DDMFormInstanceRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceRecordImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceRecord
 * @generated
 */
@ProviderType
public interface DDMFormInstanceRecordModel
	extends BaseModel<DDMFormInstanceRecord>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm form instance record model instance should use the {@link DDMFormInstanceRecord} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm form instance record.
	 *
	 * @return the primary key of this ddm form instance record
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm form instance record.
	 *
	 * @param primaryKey the primary key of this ddm form instance record
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm form instance record.
	 *
	 * @return the mvcc version of this ddm form instance record
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm form instance record.
	 *
	 * @param mvccVersion the mvcc version of this ddm form instance record
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this ddm form instance record.
	 *
	 * @return the uuid of this ddm form instance record
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this ddm form instance record.
	 *
	 * @param uuid the uuid of this ddm form instance record
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the form instance record ID of this ddm form instance record.
	 *
	 * @return the form instance record ID of this ddm form instance record
	 */
	public long getFormInstanceRecordId();

	/**
	 * Sets the form instance record ID of this ddm form instance record.
	 *
	 * @param formInstanceRecordId the form instance record ID of this ddm form instance record
	 */
	public void setFormInstanceRecordId(long formInstanceRecordId);

	/**
	 * Returns the group ID of this ddm form instance record.
	 *
	 * @return the group ID of this ddm form instance record
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm form instance record.
	 *
	 * @param groupId the group ID of this ddm form instance record
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm form instance record.
	 *
	 * @return the company ID of this ddm form instance record
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm form instance record.
	 *
	 * @param companyId the company ID of this ddm form instance record
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddm form instance record.
	 *
	 * @return the user ID of this ddm form instance record
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this ddm form instance record.
	 *
	 * @param userId the user ID of this ddm form instance record
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddm form instance record.
	 *
	 * @return the user uuid of this ddm form instance record
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddm form instance record.
	 *
	 * @param userUuid the user uuid of this ddm form instance record
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddm form instance record.
	 *
	 * @return the user name of this ddm form instance record
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this ddm form instance record.
	 *
	 * @param userName the user name of this ddm form instance record
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the version user ID of this ddm form instance record.
	 *
	 * @return the version user ID of this ddm form instance record
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this ddm form instance record.
	 *
	 * @param versionUserId the version user ID of this ddm form instance record
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this ddm form instance record.
	 *
	 * @return the version user uuid of this ddm form instance record
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this ddm form instance record.
	 *
	 * @param versionUserUuid the version user uuid of this ddm form instance record
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the version user name of this ddm form instance record.
	 *
	 * @return the version user name of this ddm form instance record
	 */
	@AutoEscape
	public String getVersionUserName();

	/**
	 * Sets the version user name of this ddm form instance record.
	 *
	 * @param versionUserName the version user name of this ddm form instance record
	 */
	public void setVersionUserName(String versionUserName);

	/**
	 * Returns the create date of this ddm form instance record.
	 *
	 * @return the create date of this ddm form instance record
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm form instance record.
	 *
	 * @param createDate the create date of this ddm form instance record
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ddm form instance record.
	 *
	 * @return the modified date of this ddm form instance record
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ddm form instance record.
	 *
	 * @param modifiedDate the modified date of this ddm form instance record
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the form instance ID of this ddm form instance record.
	 *
	 * @return the form instance ID of this ddm form instance record
	 */
	public long getFormInstanceId();

	/**
	 * Sets the form instance ID of this ddm form instance record.
	 *
	 * @param formInstanceId the form instance ID of this ddm form instance record
	 */
	public void setFormInstanceId(long formInstanceId);

	/**
	 * Returns the form instance version of this ddm form instance record.
	 *
	 * @return the form instance version of this ddm form instance record
	 */
	@AutoEscape
	public String getFormInstanceVersion();

	/**
	 * Sets the form instance version of this ddm form instance record.
	 *
	 * @param formInstanceVersion the form instance version of this ddm form instance record
	 */
	public void setFormInstanceVersion(String formInstanceVersion);

	/**
	 * Returns the storage ID of this ddm form instance record.
	 *
	 * @return the storage ID of this ddm form instance record
	 */
	public long getStorageId();

	/**
	 * Sets the storage ID of this ddm form instance record.
	 *
	 * @param storageId the storage ID of this ddm form instance record
	 */
	public void setStorageId(long storageId);

	/**
	 * Returns the version of this ddm form instance record.
	 *
	 * @return the version of this ddm form instance record
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddm form instance record.
	 *
	 * @param version the version of this ddm form instance record
	 */
	public void setVersion(String version);

	/**
	 * Returns the last publish date of this ddm form instance record.
	 *
	 * @return the last publish date of this ddm form instance record
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this ddm form instance record.
	 *
	 * @param lastPublishDate the last publish date of this ddm form instance record
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

}