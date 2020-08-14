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

package com.liferay.segments.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SegmentsEntryRel service. Represents a row in the &quot;SegmentsEntryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.segments.model.impl.SegmentsEntryRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.segments.model.impl.SegmentsEntryRelImpl</code>.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryRel
 * @generated
 */
@ProviderType
public interface SegmentsEntryRelModel
	extends AttachedModel, BaseModel<SegmentsEntryRel>,
			CTModel<SegmentsEntryRel>, GroupedModel, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a segments entry rel model instance should use the {@link SegmentsEntryRel} interface instead.
	 */

	/**
	 * Returns the primary key of this segments entry rel.
	 *
	 * @return the primary key of this segments entry rel
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this segments entry rel.
	 *
	 * @param primaryKey the primary key of this segments entry rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this segments entry rel.
	 *
	 * @return the mvcc version of this segments entry rel
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this segments entry rel.
	 *
	 * @param mvccVersion the mvcc version of this segments entry rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this segments entry rel.
	 *
	 * @return the ct collection ID of this segments entry rel
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this segments entry rel.
	 *
	 * @param ctCollectionId the ct collection ID of this segments entry rel
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the segments entry rel ID of this segments entry rel.
	 *
	 * @return the segments entry rel ID of this segments entry rel
	 */
	public long getSegmentsEntryRelId();

	/**
	 * Sets the segments entry rel ID of this segments entry rel.
	 *
	 * @param segmentsEntryRelId the segments entry rel ID of this segments entry rel
	 */
	public void setSegmentsEntryRelId(long segmentsEntryRelId);

	/**
	 * Returns the group ID of this segments entry rel.
	 *
	 * @return the group ID of this segments entry rel
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this segments entry rel.
	 *
	 * @param groupId the group ID of this segments entry rel
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this segments entry rel.
	 *
	 * @return the company ID of this segments entry rel
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this segments entry rel.
	 *
	 * @param companyId the company ID of this segments entry rel
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this segments entry rel.
	 *
	 * @return the user ID of this segments entry rel
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this segments entry rel.
	 *
	 * @param userId the user ID of this segments entry rel
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this segments entry rel.
	 *
	 * @return the user uuid of this segments entry rel
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this segments entry rel.
	 *
	 * @param userUuid the user uuid of this segments entry rel
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this segments entry rel.
	 *
	 * @return the user name of this segments entry rel
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this segments entry rel.
	 *
	 * @param userName the user name of this segments entry rel
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this segments entry rel.
	 *
	 * @return the create date of this segments entry rel
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this segments entry rel.
	 *
	 * @param createDate the create date of this segments entry rel
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this segments entry rel.
	 *
	 * @return the modified date of this segments entry rel
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this segments entry rel.
	 *
	 * @param modifiedDate the modified date of this segments entry rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the segments entry ID of this segments entry rel.
	 *
	 * @return the segments entry ID of this segments entry rel
	 */
	public long getSegmentsEntryId();

	/**
	 * Sets the segments entry ID of this segments entry rel.
	 *
	 * @param segmentsEntryId the segments entry ID of this segments entry rel
	 */
	public void setSegmentsEntryId(long segmentsEntryId);

	/**
	 * Returns the fully qualified class name of this segments entry rel.
	 *
	 * @return the fully qualified class name of this segments entry rel
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this segments entry rel.
	 *
	 * @return the class name ID of this segments entry rel
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this segments entry rel.
	 *
	 * @param classNameId the class name ID of this segments entry rel
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this segments entry rel.
	 *
	 * @return the class pk of this segments entry rel
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this segments entry rel.
	 *
	 * @param classPK the class pk of this segments entry rel
	 */
	@Override
	public void setClassPK(long classPK);

}