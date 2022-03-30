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

package com.liferay.change.tracking.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CTAutoResolutionInfo service. Represents a row in the &quot;CTAutoResolutionInfo&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.change.tracking.model.impl.CTAutoResolutionInfoModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.change.tracking.model.impl.CTAutoResolutionInfoImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTAutoResolutionInfo
 * @generated
 */
@ProviderType
public interface CTAutoResolutionInfoModel
	extends BaseModel<CTAutoResolutionInfo>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ct auto resolution info model instance should use the {@link CTAutoResolutionInfo} interface instead.
	 */

	/**
	 * Returns the primary key of this ct auto resolution info.
	 *
	 * @return the primary key of this ct auto resolution info
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ct auto resolution info.
	 *
	 * @param primaryKey the primary key of this ct auto resolution info
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ct auto resolution info.
	 *
	 * @return the mvcc version of this ct auto resolution info
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ct auto resolution info.
	 *
	 * @param mvccVersion the mvcc version of this ct auto resolution info
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct auto resolution info ID of this ct auto resolution info.
	 *
	 * @return the ct auto resolution info ID of this ct auto resolution info
	 */
	public long getCtAutoResolutionInfoId();

	/**
	 * Sets the ct auto resolution info ID of this ct auto resolution info.
	 *
	 * @param ctAutoResolutionInfoId the ct auto resolution info ID of this ct auto resolution info
	 */
	public void setCtAutoResolutionInfoId(long ctAutoResolutionInfoId);

	/**
	 * Returns the company ID of this ct auto resolution info.
	 *
	 * @return the company ID of this ct auto resolution info
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ct auto resolution info.
	 *
	 * @param companyId the company ID of this ct auto resolution info
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this ct auto resolution info.
	 *
	 * @return the create date of this ct auto resolution info
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this ct auto resolution info.
	 *
	 * @param createDate the create date of this ct auto resolution info
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the ct collection ID of this ct auto resolution info.
	 *
	 * @return the ct collection ID of this ct auto resolution info
	 */
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ct auto resolution info.
	 *
	 * @param ctCollectionId the ct collection ID of this ct auto resolution info
	 */
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the model class name ID of this ct auto resolution info.
	 *
	 * @return the model class name ID of this ct auto resolution info
	 */
	public long getModelClassNameId();

	/**
	 * Sets the model class name ID of this ct auto resolution info.
	 *
	 * @param modelClassNameId the model class name ID of this ct auto resolution info
	 */
	public void setModelClassNameId(long modelClassNameId);

	/**
	 * Returns the source model class pk of this ct auto resolution info.
	 *
	 * @return the source model class pk of this ct auto resolution info
	 */
	public long getSourceModelClassPK();

	/**
	 * Sets the source model class pk of this ct auto resolution info.
	 *
	 * @param sourceModelClassPK the source model class pk of this ct auto resolution info
	 */
	public void setSourceModelClassPK(long sourceModelClassPK);

	/**
	 * Returns the target model class pk of this ct auto resolution info.
	 *
	 * @return the target model class pk of this ct auto resolution info
	 */
	public long getTargetModelClassPK();

	/**
	 * Sets the target model class pk of this ct auto resolution info.
	 *
	 * @param targetModelClassPK the target model class pk of this ct auto resolution info
	 */
	public void setTargetModelClassPK(long targetModelClassPK);

	/**
	 * Returns the conflict identifier of this ct auto resolution info.
	 *
	 * @return the conflict identifier of this ct auto resolution info
	 */
	@AutoEscape
	public String getConflictIdentifier();

	/**
	 * Sets the conflict identifier of this ct auto resolution info.
	 *
	 * @param conflictIdentifier the conflict identifier of this ct auto resolution info
	 */
	public void setConflictIdentifier(String conflictIdentifier);

	@Override
	public CTAutoResolutionInfo cloneWithOriginalValues();

}