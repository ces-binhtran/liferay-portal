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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PluginSetting service. Represents a row in the &quot;PluginSetting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.PluginSettingModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.PluginSettingImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PluginSetting
 * @generated
 */
@ProviderType
public interface PluginSettingModel
	extends BaseModel<PluginSetting>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a plugin setting model instance should use the {@link PluginSetting} interface instead.
	 */

	/**
	 * Returns the primary key of this plugin setting.
	 *
	 * @return the primary key of this plugin setting
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this plugin setting.
	 *
	 * @param primaryKey the primary key of this plugin setting
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this plugin setting.
	 *
	 * @return the mvcc version of this plugin setting
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this plugin setting.
	 *
	 * @param mvccVersion the mvcc version of this plugin setting
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the plugin setting ID of this plugin setting.
	 *
	 * @return the plugin setting ID of this plugin setting
	 */
	public long getPluginSettingId();

	/**
	 * Sets the plugin setting ID of this plugin setting.
	 *
	 * @param pluginSettingId the plugin setting ID of this plugin setting
	 */
	public void setPluginSettingId(long pluginSettingId);

	/**
	 * Returns the company ID of this plugin setting.
	 *
	 * @return the company ID of this plugin setting
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this plugin setting.
	 *
	 * @param companyId the company ID of this plugin setting
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the plugin ID of this plugin setting.
	 *
	 * @return the plugin ID of this plugin setting
	 */
	@AutoEscape
	public String getPluginId();

	/**
	 * Sets the plugin ID of this plugin setting.
	 *
	 * @param pluginId the plugin ID of this plugin setting
	 */
	public void setPluginId(String pluginId);

	/**
	 * Returns the plugin type of this plugin setting.
	 *
	 * @return the plugin type of this plugin setting
	 */
	@AutoEscape
	public String getPluginType();

	/**
	 * Sets the plugin type of this plugin setting.
	 *
	 * @param pluginType the plugin type of this plugin setting
	 */
	public void setPluginType(String pluginType);

	/**
	 * Returns the roles of this plugin setting.
	 *
	 * @return the roles of this plugin setting
	 */
	@AutoEscape
	public String getRoles();

	/**
	 * Sets the roles of this plugin setting.
	 *
	 * @param roles the roles of this plugin setting
	 */
	public void setRoles(String roles);

	/**
	 * Returns the active of this plugin setting.
	 *
	 * @return the active of this plugin setting
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this plugin setting is active.
	 *
	 * @return <code>true</code> if this plugin setting is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this plugin setting is active.
	 *
	 * @param active the active of this plugin setting
	 */
	public void setActive(boolean active);

	@Override
	public PluginSetting cloneWithOriginalValues();

}