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

package com.liferay.site.navigation.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SiteNavigationMenuItem service. Represents a row in the &quot;SiteNavigationMenuItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.site.navigation.model.impl.SiteNavigationMenuItemModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.site.navigation.model.impl.SiteNavigationMenuItemImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenuItem
 * @generated
 */
@ProviderType
public interface SiteNavigationMenuItemModel
	extends BaseModel<SiteNavigationMenuItem>, CTModel<SiteNavigationMenuItem>,
			MVCCModel, ShardedModel, StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a site navigation menu item model instance should use the {@link SiteNavigationMenuItem} interface instead.
	 */

	/**
	 * Returns the primary key of this site navigation menu item.
	 *
	 * @return the primary key of this site navigation menu item
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this site navigation menu item.
	 *
	 * @param primaryKey the primary key of this site navigation menu item
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this site navigation menu item.
	 *
	 * @return the mvcc version of this site navigation menu item
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this site navigation menu item.
	 *
	 * @param mvccVersion the mvcc version of this site navigation menu item
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this site navigation menu item.
	 *
	 * @return the ct collection ID of this site navigation menu item
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this site navigation menu item.
	 *
	 * @param ctCollectionId the ct collection ID of this site navigation menu item
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this site navigation menu item.
	 *
	 * @return the uuid of this site navigation menu item
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this site navigation menu item.
	 *
	 * @param uuid the uuid of this site navigation menu item
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the site navigation menu item ID of this site navigation menu item.
	 *
	 * @return the site navigation menu item ID of this site navigation menu item
	 */
	public long getSiteNavigationMenuItemId();

	/**
	 * Sets the site navigation menu item ID of this site navigation menu item.
	 *
	 * @param siteNavigationMenuItemId the site navigation menu item ID of this site navigation menu item
	 */
	public void setSiteNavigationMenuItemId(long siteNavigationMenuItemId);

	/**
	 * Returns the group ID of this site navigation menu item.
	 *
	 * @return the group ID of this site navigation menu item
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this site navigation menu item.
	 *
	 * @param groupId the group ID of this site navigation menu item
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this site navigation menu item.
	 *
	 * @return the company ID of this site navigation menu item
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this site navigation menu item.
	 *
	 * @param companyId the company ID of this site navigation menu item
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this site navigation menu item.
	 *
	 * @return the user ID of this site navigation menu item
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this site navigation menu item.
	 *
	 * @param userId the user ID of this site navigation menu item
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this site navigation menu item.
	 *
	 * @return the user uuid of this site navigation menu item
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this site navigation menu item.
	 *
	 * @param userUuid the user uuid of this site navigation menu item
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this site navigation menu item.
	 *
	 * @return the user name of this site navigation menu item
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this site navigation menu item.
	 *
	 * @param userName the user name of this site navigation menu item
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this site navigation menu item.
	 *
	 * @return the create date of this site navigation menu item
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this site navigation menu item.
	 *
	 * @param createDate the create date of this site navigation menu item
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this site navigation menu item.
	 *
	 * @return the modified date of this site navigation menu item
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this site navigation menu item.
	 *
	 * @param modifiedDate the modified date of this site navigation menu item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the site navigation menu ID of this site navigation menu item.
	 *
	 * @return the site navigation menu ID of this site navigation menu item
	 */
	public long getSiteNavigationMenuId();

	/**
	 * Sets the site navigation menu ID of this site navigation menu item.
	 *
	 * @param siteNavigationMenuId the site navigation menu ID of this site navigation menu item
	 */
	public void setSiteNavigationMenuId(long siteNavigationMenuId);

	/**
	 * Returns the parent site navigation menu item ID of this site navigation menu item.
	 *
	 * @return the parent site navigation menu item ID of this site navigation menu item
	 */
	public long getParentSiteNavigationMenuItemId();

	/**
	 * Sets the parent site navigation menu item ID of this site navigation menu item.
	 *
	 * @param parentSiteNavigationMenuItemId the parent site navigation menu item ID of this site navigation menu item
	 */
	public void setParentSiteNavigationMenuItemId(
		long parentSiteNavigationMenuItemId);

	/**
	 * Returns the name of this site navigation menu item.
	 *
	 * @return the name of this site navigation menu item
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this site navigation menu item.
	 *
	 * @param name the name of this site navigation menu item
	 */
	public void setName(String name);

	/**
	 * Returns the type of this site navigation menu item.
	 *
	 * @return the type of this site navigation menu item
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this site navigation menu item.
	 *
	 * @param type the type of this site navigation menu item
	 */
	public void setType(String type);

	/**
	 * Returns the type settings of this site navigation menu item.
	 *
	 * @return the type settings of this site navigation menu item
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this site navigation menu item.
	 *
	 * @param typeSettings the type settings of this site navigation menu item
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the order of this site navigation menu item.
	 *
	 * @return the order of this site navigation menu item
	 */
	public int getOrder();

	/**
	 * Sets the order of this site navigation menu item.
	 *
	 * @param order the order of this site navigation menu item
	 */
	public void setOrder(int order);

	/**
	 * Returns the last publish date of this site navigation menu item.
	 *
	 * @return the last publish date of this site navigation menu item
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this site navigation menu item.
	 *
	 * @param lastPublishDate the last publish date of this site navigation menu item
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	@Override
	public SiteNavigationMenuItem cloneWithOriginalValues();

}