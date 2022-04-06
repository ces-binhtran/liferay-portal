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

package com.liferay.bookmarks.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the BookmarksFolder service. Represents a row in the &quot;BookmarksFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.bookmarks.model.impl.BookmarksFolderModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.bookmarks.model.impl.BookmarksFolderImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolder
 * @generated
 */
@ProviderType
public interface BookmarksFolderModel
	extends BaseModel<BookmarksFolder>, ContainerModel, MVCCModel, ShardedModel,
			StagedGroupedModel, TrashedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a bookmarks folder model instance should use the {@link BookmarksFolder} interface instead.
	 */

	/**
	 * Returns the primary key of this bookmarks folder.
	 *
	 * @return the primary key of this bookmarks folder
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this bookmarks folder.
	 *
	 * @param primaryKey the primary key of this bookmarks folder
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this bookmarks folder.
	 *
	 * @return the mvcc version of this bookmarks folder
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this bookmarks folder.
	 *
	 * @param mvccVersion the mvcc version of this bookmarks folder
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this bookmarks folder.
	 *
	 * @return the uuid of this bookmarks folder
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this bookmarks folder.
	 *
	 * @param uuid the uuid of this bookmarks folder
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the folder ID of this bookmarks folder.
	 *
	 * @return the folder ID of this bookmarks folder
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this bookmarks folder.
	 *
	 * @param folderId the folder ID of this bookmarks folder
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the group ID of this bookmarks folder.
	 *
	 * @return the group ID of this bookmarks folder
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this bookmarks folder.
	 *
	 * @param groupId the group ID of this bookmarks folder
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this bookmarks folder.
	 *
	 * @return the company ID of this bookmarks folder
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this bookmarks folder.
	 *
	 * @param companyId the company ID of this bookmarks folder
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this bookmarks folder.
	 *
	 * @return the user ID of this bookmarks folder
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this bookmarks folder.
	 *
	 * @param userId the user ID of this bookmarks folder
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this bookmarks folder.
	 *
	 * @return the user uuid of this bookmarks folder
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this bookmarks folder.
	 *
	 * @param userUuid the user uuid of this bookmarks folder
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this bookmarks folder.
	 *
	 * @return the user name of this bookmarks folder
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this bookmarks folder.
	 *
	 * @param userName the user name of this bookmarks folder
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this bookmarks folder.
	 *
	 * @return the create date of this bookmarks folder
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this bookmarks folder.
	 *
	 * @param createDate the create date of this bookmarks folder
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this bookmarks folder.
	 *
	 * @return the modified date of this bookmarks folder
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this bookmarks folder.
	 *
	 * @param modifiedDate the modified date of this bookmarks folder
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the parent folder ID of this bookmarks folder.
	 *
	 * @return the parent folder ID of this bookmarks folder
	 */
	public long getParentFolderId();

	/**
	 * Sets the parent folder ID of this bookmarks folder.
	 *
	 * @param parentFolderId the parent folder ID of this bookmarks folder
	 */
	public void setParentFolderId(long parentFolderId);

	/**
	 * Returns the tree path of this bookmarks folder.
	 *
	 * @return the tree path of this bookmarks folder
	 */
	@AutoEscape
	public String getTreePath();

	/**
	 * Sets the tree path of this bookmarks folder.
	 *
	 * @param treePath the tree path of this bookmarks folder
	 */
	public void setTreePath(String treePath);

	/**
	 * Returns the name of this bookmarks folder.
	 *
	 * @return the name of this bookmarks folder
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this bookmarks folder.
	 *
	 * @param name the name of this bookmarks folder
	 */
	public void setName(String name);

	/**
	 * Returns the description of this bookmarks folder.
	 *
	 * @return the description of this bookmarks folder
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this bookmarks folder.
	 *
	 * @param description the description of this bookmarks folder
	 */
	public void setDescription(String description);

	/**
	 * Returns the last publish date of this bookmarks folder.
	 *
	 * @return the last publish date of this bookmarks folder
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this bookmarks folder.
	 *
	 * @param lastPublishDate the last publish date of this bookmarks folder
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this bookmarks folder.
	 *
	 * @return the status of this bookmarks folder
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this bookmarks folder.
	 *
	 * @param status the status of this bookmarks folder
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this bookmarks folder.
	 *
	 * @return the status by user ID of this bookmarks folder
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this bookmarks folder.
	 *
	 * @param statusByUserId the status by user ID of this bookmarks folder
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this bookmarks folder.
	 *
	 * @return the status by user uuid of this bookmarks folder
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this bookmarks folder.
	 *
	 * @param statusByUserUuid the status by user uuid of this bookmarks folder
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this bookmarks folder.
	 *
	 * @return the status by user name of this bookmarks folder
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this bookmarks folder.
	 *
	 * @param statusByUserName the status by user name of this bookmarks folder
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this bookmarks folder.
	 *
	 * @return the status date of this bookmarks folder
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this bookmarks folder.
	 *
	 * @param statusDate the status date of this bookmarks folder
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the trash entry created when this bookmarks folder was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this bookmarks folder.
	 *
	 * @return the trash entry created when this bookmarks folder was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this bookmarks folder.
	 *
	 * @return the class primary key of the trash entry for this bookmarks folder
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this bookmarks folder.
	 *
	 * @return the trash handler for this bookmarks folder
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this bookmarks folder is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this bookmarks folder is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this bookmarks folder is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this bookmarks folder is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	/**
	 * Returns <code>true</code> if this bookmarks folder is approved.
	 *
	 * @return <code>true</code> if this bookmarks folder is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this bookmarks folder is denied.
	 *
	 * @return <code>true</code> if this bookmarks folder is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this bookmarks folder is a draft.
	 *
	 * @return <code>true</code> if this bookmarks folder is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this bookmarks folder is expired.
	 *
	 * @return <code>true</code> if this bookmarks folder is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this bookmarks folder is inactive.
	 *
	 * @return <code>true</code> if this bookmarks folder is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this bookmarks folder is incomplete.
	 *
	 * @return <code>true</code> if this bookmarks folder is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this bookmarks folder is pending.
	 *
	 * @return <code>true</code> if this bookmarks folder is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this bookmarks folder is scheduled.
	 *
	 * @return <code>true</code> if this bookmarks folder is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	/**
	 * Returns the container model ID of this bookmarks folder.
	 *
	 * @return the container model ID of this bookmarks folder
	 */
	@Override
	public long getContainerModelId();

	/**
	 * Sets the container model ID of this bookmarks folder.
	 *
	 * @param containerModelId the container model ID of this bookmarks folder
	 */
	@Override
	public void setContainerModelId(long containerModelId);

	/**
	 * Returns the container name of this bookmarks folder.
	 *
	 * @return the container name of this bookmarks folder
	 */
	@Override
	public String getContainerModelName();

	/**
	 * Returns the parent container model ID of this bookmarks folder.
	 *
	 * @return the parent container model ID of this bookmarks folder
	 */
	@Override
	public long getParentContainerModelId();

	/**
	 * Sets the parent container model ID of this bookmarks folder.
	 *
	 * @param parentContainerModelId the parent container model ID of this bookmarks folder
	 */
	@Override
	public void setParentContainerModelId(long parentContainerModelId);

	@Override
	public BookmarksFolder cloneWithOriginalValues();

}