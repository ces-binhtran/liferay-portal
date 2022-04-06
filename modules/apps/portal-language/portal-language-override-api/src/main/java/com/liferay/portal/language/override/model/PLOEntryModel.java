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

package com.liferay.portal.language.override.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PLOEntry service. Represents a row in the &quot;PLOEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.language.override.model.impl.PLOEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.language.override.model.impl.PLOEntryImpl</code>.
 * </p>
 *
 * @author Drew Brokke
 * @see PLOEntry
 * @generated
 */
@ProviderType
public interface PLOEntryModel
	extends BaseModel<PLOEntry>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a plo entry model instance should use the {@link PLOEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this plo entry.
	 *
	 * @return the primary key of this plo entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this plo entry.
	 *
	 * @param primaryKey the primary key of this plo entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this plo entry.
	 *
	 * @return the mvcc version of this plo entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this plo entry.
	 *
	 * @param mvccVersion the mvcc version of this plo entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the plo entry ID of this plo entry.
	 *
	 * @return the plo entry ID of this plo entry
	 */
	public long getPloEntryId();

	/**
	 * Sets the plo entry ID of this plo entry.
	 *
	 * @param ploEntryId the plo entry ID of this plo entry
	 */
	public void setPloEntryId(long ploEntryId);

	/**
	 * Returns the company ID of this plo entry.
	 *
	 * @return the company ID of this plo entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this plo entry.
	 *
	 * @param companyId the company ID of this plo entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this plo entry.
	 *
	 * @return the user ID of this plo entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this plo entry.
	 *
	 * @param userId the user ID of this plo entry
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this plo entry.
	 *
	 * @return the user uuid of this plo entry
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this plo entry.
	 *
	 * @param userUuid the user uuid of this plo entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this plo entry.
	 *
	 * @return the create date of this plo entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this plo entry.
	 *
	 * @param createDate the create date of this plo entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this plo entry.
	 *
	 * @return the modified date of this plo entry
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this plo entry.
	 *
	 * @param modifiedDate the modified date of this plo entry
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the key of this plo entry.
	 *
	 * @return the key of this plo entry
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this plo entry.
	 *
	 * @param key the key of this plo entry
	 */
	public void setKey(String key);

	/**
	 * Returns the language ID of this plo entry.
	 *
	 * @return the language ID of this plo entry
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this plo entry.
	 *
	 * @param languageId the language ID of this plo entry
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the value of this plo entry.
	 *
	 * @return the value of this plo entry
	 */
	@AutoEscape
	public String getValue();

	/**
	 * Sets the value of this plo entry.
	 *
	 * @param value the value of this plo entry
	 */
	public void setValue(String value);

	@Override
	public PLOEntry cloneWithOriginalValues();

}