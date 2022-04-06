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

package com.liferay.changeset.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ChangesetEntry service. Represents a row in the &quot;ChangesetEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.changeset.model.impl.ChangesetEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.changeset.model.impl.ChangesetEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChangesetEntry
 * @generated
 */
@ProviderType
public interface ChangesetEntryModel
	extends AttachedModel, BaseModel<ChangesetEntry>, GroupedModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a changeset entry model instance should use the {@link ChangesetEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this changeset entry.
	 *
	 * @return the primary key of this changeset entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this changeset entry.
	 *
	 * @param primaryKey the primary key of this changeset entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the changeset entry ID of this changeset entry.
	 *
	 * @return the changeset entry ID of this changeset entry
	 */
	public long getChangesetEntryId();

	/**
	 * Sets the changeset entry ID of this changeset entry.
	 *
	 * @param changesetEntryId the changeset entry ID of this changeset entry
	 */
	public void setChangesetEntryId(long changesetEntryId);

	/**
	 * Returns the group ID of this changeset entry.
	 *
	 * @return the group ID of this changeset entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this changeset entry.
	 *
	 * @param groupId the group ID of this changeset entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this changeset entry.
	 *
	 * @return the company ID of this changeset entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this changeset entry.
	 *
	 * @param companyId the company ID of this changeset entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this changeset entry.
	 *
	 * @return the user ID of this changeset entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this changeset entry.
	 *
	 * @param userId the user ID of this changeset entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this changeset entry.
	 *
	 * @return the user uuid of this changeset entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this changeset entry.
	 *
	 * @param userUuid the user uuid of this changeset entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this changeset entry.
	 *
	 * @return the user name of this changeset entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this changeset entry.
	 *
	 * @param userName the user name of this changeset entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this changeset entry.
	 *
	 * @return the create date of this changeset entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this changeset entry.
	 *
	 * @param createDate the create date of this changeset entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this changeset entry.
	 *
	 * @return the modified date of this changeset entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this changeset entry.
	 *
	 * @param modifiedDate the modified date of this changeset entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the changeset collection ID of this changeset entry.
	 *
	 * @return the changeset collection ID of this changeset entry
	 */
	public long getChangesetCollectionId();

	/**
	 * Sets the changeset collection ID of this changeset entry.
	 *
	 * @param changesetCollectionId the changeset collection ID of this changeset entry
	 */
	public void setChangesetCollectionId(long changesetCollectionId);

	/**
	 * Returns the fully qualified class name of this changeset entry.
	 *
	 * @return the fully qualified class name of this changeset entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this changeset entry.
	 *
	 * @return the class name ID of this changeset entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this changeset entry.
	 *
	 * @param classNameId the class name ID of this changeset entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this changeset entry.
	 *
	 * @return the class pk of this changeset entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this changeset entry.
	 *
	 * @param classPK the class pk of this changeset entry
	 */
	@Override
	public void setClassPK(long classPK);

	@Override
	public ChangesetEntry cloneWithOriginalValues();

}