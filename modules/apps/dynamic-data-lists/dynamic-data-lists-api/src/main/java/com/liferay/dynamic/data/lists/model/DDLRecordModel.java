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

package com.liferay.dynamic.data.lists.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDLRecord service. Represents a row in the &quot;DDLRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecord
 * @generated
 */
@ProviderType
public interface DDLRecordModel
	extends BaseModel<DDLRecord>, CTModel<DDLRecord>, MVCCModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddl record model instance should use the {@link DDLRecord} interface instead.
	 */

	/**
	 * Returns the primary key of this ddl record.
	 *
	 * @return the primary key of this ddl record
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddl record.
	 *
	 * @param primaryKey the primary key of this ddl record
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddl record.
	 *
	 * @return the mvcc version of this ddl record
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddl record.
	 *
	 * @param mvccVersion the mvcc version of this ddl record
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this ddl record.
	 *
	 * @return the ct collection ID of this ddl record
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ddl record.
	 *
	 * @param ctCollectionId the ct collection ID of this ddl record
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this ddl record.
	 *
	 * @return the uuid of this ddl record
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this ddl record.
	 *
	 * @param uuid the uuid of this ddl record
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the record ID of this ddl record.
	 *
	 * @return the record ID of this ddl record
	 */
	public long getRecordId();

	/**
	 * Sets the record ID of this ddl record.
	 *
	 * @param recordId the record ID of this ddl record
	 */
	public void setRecordId(long recordId);

	/**
	 * Returns the group ID of this ddl record.
	 *
	 * @return the group ID of this ddl record
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this ddl record.
	 *
	 * @param groupId the group ID of this ddl record
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddl record.
	 *
	 * @return the company ID of this ddl record
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddl record.
	 *
	 * @param companyId the company ID of this ddl record
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddl record.
	 *
	 * @return the user ID of this ddl record
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this ddl record.
	 *
	 * @param userId the user ID of this ddl record
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddl record.
	 *
	 * @return the user uuid of this ddl record
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddl record.
	 *
	 * @param userUuid the user uuid of this ddl record
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddl record.
	 *
	 * @return the user name of this ddl record
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this ddl record.
	 *
	 * @param userName the user name of this ddl record
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the version user ID of this ddl record.
	 *
	 * @return the version user ID of this ddl record
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this ddl record.
	 *
	 * @param versionUserId the version user ID of this ddl record
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this ddl record.
	 *
	 * @return the version user uuid of this ddl record
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this ddl record.
	 *
	 * @param versionUserUuid the version user uuid of this ddl record
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the version user name of this ddl record.
	 *
	 * @return the version user name of this ddl record
	 */
	@AutoEscape
	public String getVersionUserName();

	/**
	 * Sets the version user name of this ddl record.
	 *
	 * @param versionUserName the version user name of this ddl record
	 */
	public void setVersionUserName(String versionUserName);

	/**
	 * Returns the create date of this ddl record.
	 *
	 * @return the create date of this ddl record
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddl record.
	 *
	 * @param createDate the create date of this ddl record
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this ddl record.
	 *
	 * @return the modified date of this ddl record
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this ddl record.
	 *
	 * @param modifiedDate the modified date of this ddl record
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ddm storage ID of this ddl record.
	 *
	 * @return the ddm storage ID of this ddl record
	 */
	public long getDDMStorageId();

	/**
	 * Sets the ddm storage ID of this ddl record.
	 *
	 * @param DDMStorageId the ddm storage ID of this ddl record
	 */
	public void setDDMStorageId(long DDMStorageId);

	/**
	 * Returns the record set ID of this ddl record.
	 *
	 * @return the record set ID of this ddl record
	 */
	public long getRecordSetId();

	/**
	 * Sets the record set ID of this ddl record.
	 *
	 * @param recordSetId the record set ID of this ddl record
	 */
	public void setRecordSetId(long recordSetId);

	/**
	 * Returns the record set version of this ddl record.
	 *
	 * @return the record set version of this ddl record
	 */
	@AutoEscape
	public String getRecordSetVersion();

	/**
	 * Sets the record set version of this ddl record.
	 *
	 * @param recordSetVersion the record set version of this ddl record
	 */
	public void setRecordSetVersion(String recordSetVersion);

	/**
	 * Returns the class name of this ddl record.
	 *
	 * @return the class name of this ddl record
	 */
	@AutoEscape
	public String getClassName();

	/**
	 * Sets the class name of this ddl record.
	 *
	 * @param className the class name of this ddl record
	 */
	public void setClassName(String className);

	/**
	 * Returns the class pk of this ddl record.
	 *
	 * @return the class pk of this ddl record
	 */
	public long getClassPK();

	/**
	 * Sets the class pk of this ddl record.
	 *
	 * @param classPK the class pk of this ddl record
	 */
	public void setClassPK(long classPK);

	/**
	 * Returns the version of this ddl record.
	 *
	 * @return the version of this ddl record
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddl record.
	 *
	 * @param version the version of this ddl record
	 */
	public void setVersion(String version);

	/**
	 * Returns the display index of this ddl record.
	 *
	 * @return the display index of this ddl record
	 */
	public int getDisplayIndex();

	/**
	 * Sets the display index of this ddl record.
	 *
	 * @param displayIndex the display index of this ddl record
	 */
	public void setDisplayIndex(int displayIndex);

	/**
	 * Returns the last publish date of this ddl record.
	 *
	 * @return the last publish date of this ddl record
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this ddl record.
	 *
	 * @param lastPublishDate the last publish date of this ddl record
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public DDLRecord cloneWithOriginalValues();

}