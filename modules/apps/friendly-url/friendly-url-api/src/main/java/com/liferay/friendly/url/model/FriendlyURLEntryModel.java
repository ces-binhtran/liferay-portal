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

package com.liferay.friendly.url.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FriendlyURLEntry service. Represents a row in the &quot;FriendlyURLEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntry
 * @generated
 */
@ProviderType
public interface FriendlyURLEntryModel
	extends AttachedModel, BaseModel<FriendlyURLEntry>,
			CTModel<FriendlyURLEntry>, MVCCModel, ShardedModel, StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a friendly url entry model instance should use the {@link FriendlyURLEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this friendly url entry.
	 *
	 * @return the primary key of this friendly url entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this friendly url entry.
	 *
	 * @param primaryKey the primary key of this friendly url entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this friendly url entry.
	 *
	 * @return the mvcc version of this friendly url entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this friendly url entry.
	 *
	 * @param mvccVersion the mvcc version of this friendly url entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this friendly url entry.
	 *
	 * @return the ct collection ID of this friendly url entry
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this friendly url entry.
	 *
	 * @param ctCollectionId the ct collection ID of this friendly url entry
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the uuid of this friendly url entry.
	 *
	 * @return the uuid of this friendly url entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this friendly url entry.
	 *
	 * @param uuid the uuid of this friendly url entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the default language ID of this friendly url entry.
	 *
	 * @return the default language ID of this friendly url entry
	 */
	@AutoEscape
	public String getDefaultLanguageId();

	/**
	 * Sets the default language ID of this friendly url entry.
	 *
	 * @param defaultLanguageId the default language ID of this friendly url entry
	 */
	public void setDefaultLanguageId(String defaultLanguageId);

	/**
	 * Returns the friendly url entry ID of this friendly url entry.
	 *
	 * @return the friendly url entry ID of this friendly url entry
	 */
	public long getFriendlyURLEntryId();

	/**
	 * Sets the friendly url entry ID of this friendly url entry.
	 *
	 * @param friendlyURLEntryId the friendly url entry ID of this friendly url entry
	 */
	public void setFriendlyURLEntryId(long friendlyURLEntryId);

	/**
	 * Returns the group ID of this friendly url entry.
	 *
	 * @return the group ID of this friendly url entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this friendly url entry.
	 *
	 * @param groupId the group ID of this friendly url entry
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this friendly url entry.
	 *
	 * @return the company ID of this friendly url entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this friendly url entry.
	 *
	 * @param companyId the company ID of this friendly url entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this friendly url entry.
	 *
	 * @return the create date of this friendly url entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this friendly url entry.
	 *
	 * @param createDate the create date of this friendly url entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this friendly url entry.
	 *
	 * @return the modified date of this friendly url entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this friendly url entry.
	 *
	 * @param modifiedDate the modified date of this friendly url entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this friendly url entry.
	 *
	 * @return the fully qualified class name of this friendly url entry
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this friendly url entry.
	 *
	 * @return the class name ID of this friendly url entry
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this friendly url entry.
	 *
	 * @param classNameId the class name ID of this friendly url entry
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this friendly url entry.
	 *
	 * @return the class pk of this friendly url entry
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this friendly url entry.
	 *
	 * @param classPK the class pk of this friendly url entry
	 */
	@Override
	public void setClassPK(long classPK);

	public String[] getAvailableLanguageIds();

	public String getUrlTitle();

	public String getUrlTitle(String languageId);

	public String getUrlTitle(String languageId, boolean useDefault);

	public String getUrlTitleMapAsXML();

	public Map<String, String> getLanguageIdToUrlTitleMap();

	@Override
	public FriendlyURLEntry cloneWithOriginalValues();

}