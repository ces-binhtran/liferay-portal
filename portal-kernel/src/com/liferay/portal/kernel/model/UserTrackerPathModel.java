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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the UserTrackerPath service. Represents a row in the &quot;UserTrackerPath&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.UserTrackerPathModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.UserTrackerPathImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerPath
 * @generated
 */
@ProviderType
public interface UserTrackerPathModel
	extends BaseModel<UserTrackerPath>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user tracker path model instance should use the {@link UserTrackerPath} interface instead.
	 */

	/**
	 * Returns the primary key of this user tracker path.
	 *
	 * @return the primary key of this user tracker path
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this user tracker path.
	 *
	 * @param primaryKey the primary key of this user tracker path
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this user tracker path.
	 *
	 * @return the mvcc version of this user tracker path
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this user tracker path.
	 *
	 * @param mvccVersion the mvcc version of this user tracker path
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the user tracker path ID of this user tracker path.
	 *
	 * @return the user tracker path ID of this user tracker path
	 */
	public long getUserTrackerPathId();

	/**
	 * Sets the user tracker path ID of this user tracker path.
	 *
	 * @param userTrackerPathId the user tracker path ID of this user tracker path
	 */
	public void setUserTrackerPathId(long userTrackerPathId);

	/**
	 * Returns the company ID of this user tracker path.
	 *
	 * @return the company ID of this user tracker path
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this user tracker path.
	 *
	 * @param companyId the company ID of this user tracker path
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user tracker ID of this user tracker path.
	 *
	 * @return the user tracker ID of this user tracker path
	 */
	public long getUserTrackerId();

	/**
	 * Sets the user tracker ID of this user tracker path.
	 *
	 * @param userTrackerId the user tracker ID of this user tracker path
	 */
	public void setUserTrackerId(long userTrackerId);

	/**
	 * Returns the path of this user tracker path.
	 *
	 * @return the path of this user tracker path
	 */
	@AutoEscape
	public String getPath();

	/**
	 * Sets the path of this user tracker path.
	 *
	 * @param path the path of this user tracker path
	 */
	public void setPath(String path);

	/**
	 * Returns the path date of this user tracker path.
	 *
	 * @return the path date of this user tracker path
	 */
	public Date getPathDate();

	/**
	 * Sets the path date of this user tracker path.
	 *
	 * @param pathDate the path date of this user tracker path
	 */
	public void setPathDate(Date pathDate);

	@Override
	public UserTrackerPath cloneWithOriginalValues();

}