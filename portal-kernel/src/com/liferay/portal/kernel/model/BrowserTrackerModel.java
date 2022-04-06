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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the BrowserTracker service. Represents a row in the &quot;BrowserTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.BrowserTrackerModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.BrowserTrackerImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BrowserTracker
 * @generated
 */
@ProviderType
public interface BrowserTrackerModel
	extends BaseModel<BrowserTracker>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a browser tracker model instance should use the {@link BrowserTracker} interface instead.
	 */

	/**
	 * Returns the primary key of this browser tracker.
	 *
	 * @return the primary key of this browser tracker
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this browser tracker.
	 *
	 * @param primaryKey the primary key of this browser tracker
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this browser tracker.
	 *
	 * @return the mvcc version of this browser tracker
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this browser tracker.
	 *
	 * @param mvccVersion the mvcc version of this browser tracker
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the browser tracker ID of this browser tracker.
	 *
	 * @return the browser tracker ID of this browser tracker
	 */
	public long getBrowserTrackerId();

	/**
	 * Sets the browser tracker ID of this browser tracker.
	 *
	 * @param browserTrackerId the browser tracker ID of this browser tracker
	 */
	public void setBrowserTrackerId(long browserTrackerId);

	/**
	 * Returns the company ID of this browser tracker.
	 *
	 * @return the company ID of this browser tracker
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this browser tracker.
	 *
	 * @param companyId the company ID of this browser tracker
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this browser tracker.
	 *
	 * @return the user ID of this browser tracker
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this browser tracker.
	 *
	 * @param userId the user ID of this browser tracker
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this browser tracker.
	 *
	 * @return the user uuid of this browser tracker
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this browser tracker.
	 *
	 * @param userUuid the user uuid of this browser tracker
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the browser key of this browser tracker.
	 *
	 * @return the browser key of this browser tracker
	 */
	public long getBrowserKey();

	/**
	 * Sets the browser key of this browser tracker.
	 *
	 * @param browserKey the browser key of this browser tracker
	 */
	public void setBrowserKey(long browserKey);

	@Override
	public BrowserTracker cloneWithOriginalValues();

}