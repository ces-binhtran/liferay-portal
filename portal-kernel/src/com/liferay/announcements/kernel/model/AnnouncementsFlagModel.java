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

package com.liferay.announcements.kernel.model;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AnnouncementsFlag service. Represents a row in the &quot;AnnouncementsFlag&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.announcements.model.impl.AnnouncementsFlagImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsFlag
 * @generated
 */
@ProviderType
public interface AnnouncementsFlagModel
	extends BaseModel<AnnouncementsFlag>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a announcements flag model instance should use the {@link AnnouncementsFlag} interface instead.
	 */

	/**
	 * Returns the primary key of this announcements flag.
	 *
	 * @return the primary key of this announcements flag
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this announcements flag.
	 *
	 * @param primaryKey the primary key of this announcements flag
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this announcements flag.
	 *
	 * @return the mvcc version of this announcements flag
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this announcements flag.
	 *
	 * @param mvccVersion the mvcc version of this announcements flag
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the flag ID of this announcements flag.
	 *
	 * @return the flag ID of this announcements flag
	 */
	public long getFlagId();

	/**
	 * Sets the flag ID of this announcements flag.
	 *
	 * @param flagId the flag ID of this announcements flag
	 */
	public void setFlagId(long flagId);

	/**
	 * Returns the company ID of this announcements flag.
	 *
	 * @return the company ID of this announcements flag
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this announcements flag.
	 *
	 * @param companyId the company ID of this announcements flag
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this announcements flag.
	 *
	 * @return the user ID of this announcements flag
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this announcements flag.
	 *
	 * @param userId the user ID of this announcements flag
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this announcements flag.
	 *
	 * @return the user uuid of this announcements flag
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this announcements flag.
	 *
	 * @param userUuid the user uuid of this announcements flag
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this announcements flag.
	 *
	 * @return the create date of this announcements flag
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this announcements flag.
	 *
	 * @param createDate the create date of this announcements flag
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the entry ID of this announcements flag.
	 *
	 * @return the entry ID of this announcements flag
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this announcements flag.
	 *
	 * @param entryId the entry ID of this announcements flag
	 */
	public void setEntryId(long entryId);

	/**
	 * Returns the value of this announcements flag.
	 *
	 * @return the value of this announcements flag
	 */
	public int getValue();

	/**
	 * Sets the value of this announcements flag.
	 *
	 * @param value the value of this announcements flag
	 */
	public void setValue(int value);

	@Override
	public AnnouncementsFlag cloneWithOriginalValues();

}