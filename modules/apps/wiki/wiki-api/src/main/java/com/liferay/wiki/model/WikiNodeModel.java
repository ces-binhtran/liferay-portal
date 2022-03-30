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

package com.liferay.wiki.model;

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
 * The base model interface for the WikiNode service. Represents a row in the &quot;WikiNode&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.wiki.model.impl.WikiNodeModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.wiki.model.impl.WikiNodeImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WikiNode
 * @generated
 */
@ProviderType
public interface WikiNodeModel
	extends BaseModel<WikiNode>, ContainerModel, MVCCModel, ShardedModel,
			StagedGroupedModel, TrashedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a wiki node model instance should use the {@link WikiNode} interface instead.
	 */

	/**
	 * Returns the primary key of this wiki node.
	 *
	 * @return the primary key of this wiki node
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this wiki node.
	 *
	 * @param primaryKey the primary key of this wiki node
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this wiki node.
	 *
	 * @return the mvcc version of this wiki node
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this wiki node.
	 *
	 * @param mvccVersion the mvcc version of this wiki node
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this wiki node.
	 *
	 * @return the uuid of this wiki node
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this wiki node.
	 *
	 * @param uuid the uuid of this wiki node
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this wiki node.
	 *
	 * @return the external reference code of this wiki node
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this wiki node.
	 *
	 * @param externalReferenceCode the external reference code of this wiki node
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the node ID of this wiki node.
	 *
	 * @return the node ID of this wiki node
	 */
	public long getNodeId();

	/**
	 * Sets the node ID of this wiki node.
	 *
	 * @param nodeId the node ID of this wiki node
	 */
	public void setNodeId(long nodeId);

	/**
	 * Returns the group ID of this wiki node.
	 *
	 * @return the group ID of this wiki node
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this wiki node.
	 *
	 * @param groupId the group ID of this wiki node
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this wiki node.
	 *
	 * @return the company ID of this wiki node
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this wiki node.
	 *
	 * @param companyId the company ID of this wiki node
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this wiki node.
	 *
	 * @return the user ID of this wiki node
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this wiki node.
	 *
	 * @param userId the user ID of this wiki node
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this wiki node.
	 *
	 * @return the user uuid of this wiki node
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this wiki node.
	 *
	 * @param userUuid the user uuid of this wiki node
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this wiki node.
	 *
	 * @return the user name of this wiki node
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this wiki node.
	 *
	 * @param userName the user name of this wiki node
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this wiki node.
	 *
	 * @return the create date of this wiki node
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this wiki node.
	 *
	 * @param createDate the create date of this wiki node
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this wiki node.
	 *
	 * @return the modified date of this wiki node
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this wiki node.
	 *
	 * @param modifiedDate the modified date of this wiki node
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this wiki node.
	 *
	 * @return the name of this wiki node
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this wiki node.
	 *
	 * @param name the name of this wiki node
	 */
	public void setName(String name);

	/**
	 * Returns the description of this wiki node.
	 *
	 * @return the description of this wiki node
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this wiki node.
	 *
	 * @param description the description of this wiki node
	 */
	public void setDescription(String description);

	/**
	 * Returns the last post date of this wiki node.
	 *
	 * @return the last post date of this wiki node
	 */
	public Date getLastPostDate();

	/**
	 * Sets the last post date of this wiki node.
	 *
	 * @param lastPostDate the last post date of this wiki node
	 */
	public void setLastPostDate(Date lastPostDate);

	/**
	 * Returns the last publish date of this wiki node.
	 *
	 * @return the last publish date of this wiki node
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this wiki node.
	 *
	 * @param lastPublishDate the last publish date of this wiki node
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this wiki node.
	 *
	 * @return the status of this wiki node
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this wiki node.
	 *
	 * @param status the status of this wiki node
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this wiki node.
	 *
	 * @return the status by user ID of this wiki node
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this wiki node.
	 *
	 * @param statusByUserId the status by user ID of this wiki node
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this wiki node.
	 *
	 * @return the status by user uuid of this wiki node
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this wiki node.
	 *
	 * @param statusByUserUuid the status by user uuid of this wiki node
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this wiki node.
	 *
	 * @return the status by user name of this wiki node
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this wiki node.
	 *
	 * @param statusByUserName the status by user name of this wiki node
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this wiki node.
	 *
	 * @return the status date of this wiki node
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this wiki node.
	 *
	 * @param statusDate the status date of this wiki node
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the trash entry created when this wiki node was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this wiki node.
	 *
	 * @return the trash entry created when this wiki node was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this wiki node.
	 *
	 * @return the class primary key of the trash entry for this wiki node
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this wiki node.
	 *
	 * @return the trash handler for this wiki node
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this wiki node is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this wiki node is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this wiki node is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this wiki node is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	/**
	 * Returns <code>true</code> if this wiki node is approved.
	 *
	 * @return <code>true</code> if this wiki node is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this wiki node is denied.
	 *
	 * @return <code>true</code> if this wiki node is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this wiki node is a draft.
	 *
	 * @return <code>true</code> if this wiki node is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this wiki node is expired.
	 *
	 * @return <code>true</code> if this wiki node is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this wiki node is inactive.
	 *
	 * @return <code>true</code> if this wiki node is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this wiki node is incomplete.
	 *
	 * @return <code>true</code> if this wiki node is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this wiki node is pending.
	 *
	 * @return <code>true</code> if this wiki node is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this wiki node is scheduled.
	 *
	 * @return <code>true</code> if this wiki node is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	/**
	 * Returns the container model ID of this wiki node.
	 *
	 * @return the container model ID of this wiki node
	 */
	@Override
	public long getContainerModelId();

	/**
	 * Sets the container model ID of this wiki node.
	 *
	 * @param containerModelId the container model ID of this wiki node
	 */
	@Override
	public void setContainerModelId(long containerModelId);

	/**
	 * Returns the container name of this wiki node.
	 *
	 * @return the container name of this wiki node
	 */
	@Override
	public String getContainerModelName();

	/**
	 * Returns the parent container model ID of this wiki node.
	 *
	 * @return the parent container model ID of this wiki node
	 */
	@Override
	public long getParentContainerModelId();

	/**
	 * Sets the parent container model ID of this wiki node.
	 *
	 * @param parentContainerModelId the parent container model ID of this wiki node
	 */
	@Override
	public void setParentContainerModelId(long parentContainerModelId);

	@Override
	public WikiNode cloneWithOriginalValues();

}