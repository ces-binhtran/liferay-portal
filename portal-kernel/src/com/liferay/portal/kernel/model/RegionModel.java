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
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Region service. Represents a row in the &quot;Region&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.RegionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.RegionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Region
 * @generated
 */
@ProviderType
public interface RegionModel
	extends BaseModel<Region>, MVCCModel, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a region model instance should use the {@link Region} interface instead.
	 */

	/**
	 * Returns the primary key of this region.
	 *
	 * @return the primary key of this region
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this region.
	 *
	 * @param primaryKey the primary key of this region
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this region.
	 *
	 * @return the mvcc version of this region
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this region.
	 *
	 * @param mvccVersion the mvcc version of this region
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this region.
	 *
	 * @return the uuid of this region
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this region.
	 *
	 * @param uuid the uuid of this region
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the default language ID of this region.
	 *
	 * @return the default language ID of this region
	 */
	@AutoEscape
	public String getDefaultLanguageId();

	/**
	 * Sets the default language ID of this region.
	 *
	 * @param defaultLanguageId the default language ID of this region
	 */
	public void setDefaultLanguageId(String defaultLanguageId);

	/**
	 * Returns the region ID of this region.
	 *
	 * @return the region ID of this region
	 */
	public long getRegionId();

	/**
	 * Sets the region ID of this region.
	 *
	 * @param regionId the region ID of this region
	 */
	public void setRegionId(long regionId);

	/**
	 * Returns the company ID of this region.
	 *
	 * @return the company ID of this region
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this region.
	 *
	 * @param companyId the company ID of this region
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this region.
	 *
	 * @return the user ID of this region
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this region.
	 *
	 * @param userId the user ID of this region
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this region.
	 *
	 * @return the user uuid of this region
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this region.
	 *
	 * @param userUuid the user uuid of this region
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this region.
	 *
	 * @return the user name of this region
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this region.
	 *
	 * @param userName the user name of this region
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this region.
	 *
	 * @return the create date of this region
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this region.
	 *
	 * @param createDate the create date of this region
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this region.
	 *
	 * @return the modified date of this region
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this region.
	 *
	 * @param modifiedDate the modified date of this region
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the country ID of this region.
	 *
	 * @return the country ID of this region
	 */
	public long getCountryId();

	/**
	 * Sets the country ID of this region.
	 *
	 * @param countryId the country ID of this region
	 */
	public void setCountryId(long countryId);

	/**
	 * Returns the active of this region.
	 *
	 * @return the active of this region
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this region is active.
	 *
	 * @return <code>true</code> if this region is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this region is active.
	 *
	 * @param active the active of this region
	 */
	public void setActive(boolean active);

	/**
	 * Returns the name of this region.
	 *
	 * @return the name of this region
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this region.
	 *
	 * @param name the name of this region
	 */
	public void setName(String name);

	/**
	 * Returns the position of this region.
	 *
	 * @return the position of this region
	 */
	public double getPosition();

	/**
	 * Sets the position of this region.
	 *
	 * @param position the position of this region
	 */
	public void setPosition(double position);

	/**
	 * Returns the region code of this region.
	 *
	 * @return the region code of this region
	 */
	@AutoEscape
	public String getRegionCode();

	/**
	 * Sets the region code of this region.
	 *
	 * @param regionCode the region code of this region
	 */
	public void setRegionCode(String regionCode);

	/**
	 * Returns the last publish date of this region.
	 *
	 * @return the last publish date of this region
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this region.
	 *
	 * @param lastPublishDate the last publish date of this region
	 */
	public void setLastPublishDate(Date lastPublishDate);

	public String[] getAvailableLanguageIds();

	public String getTitle();

	public String getTitle(String languageId);

	public String getTitle(String languageId, boolean useDefault);

	public String getTitleMapAsXML();

	public Map<String, String> getLanguageIdToTitleMap();

	@Override
	public Region cloneWithOriginalValues();

}