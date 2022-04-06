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

package com.liferay.commerce.term.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CommerceTermEntry service. Represents a row in the &quot;CommerceTermEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.term.model.impl.CommerceTermEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.term.model.impl.CommerceTermEntryImpl</code>.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceTermEntry
 * @generated
 */
@ProviderType
public interface CommerceTermEntryModel
	extends AuditedModel, BaseModel<CommerceTermEntry>, MVCCModel, ShardedModel,
			WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce term entry model instance should use the {@link CommerceTermEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce term entry.
	 *
	 * @return the primary key of this commerce term entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce term entry.
	 *
	 * @param primaryKey the primary key of this commerce term entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce term entry.
	 *
	 * @return the mvcc version of this commerce term entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce term entry.
	 *
	 * @param mvccVersion the mvcc version of this commerce term entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the external reference code of this commerce term entry.
	 *
	 * @return the external reference code of this commerce term entry
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce term entry.
	 *
	 * @param externalReferenceCode the external reference code of this commerce term entry
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the default language ID of this commerce term entry.
	 *
	 * @return the default language ID of this commerce term entry
	 */
	@AutoEscape
	public String getDefaultLanguageId();

	/**
	 * Sets the default language ID of this commerce term entry.
	 *
	 * @param defaultLanguageId the default language ID of this commerce term entry
	 */
	public void setDefaultLanguageId(String defaultLanguageId);

	/**
	 * Returns the commerce term entry ID of this commerce term entry.
	 *
	 * @return the commerce term entry ID of this commerce term entry
	 */
	public long getCommerceTermEntryId();

	/**
	 * Sets the commerce term entry ID of this commerce term entry.
	 *
	 * @param commerceTermEntryId the commerce term entry ID of this commerce term entry
	 */
	public void setCommerceTermEntryId(long commerceTermEntryId);

	/**
	 * Returns the company ID of this commerce term entry.
	 *
	 * @return the company ID of this commerce term entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce term entry.
	 *
	 * @param companyId the company ID of this commerce term entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce term entry.
	 *
	 * @return the user ID of this commerce term entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce term entry.
	 *
	 * @param userId the user ID of this commerce term entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce term entry.
	 *
	 * @return the user uuid of this commerce term entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce term entry.
	 *
	 * @param userUuid the user uuid of this commerce term entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce term entry.
	 *
	 * @return the user name of this commerce term entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce term entry.
	 *
	 * @param userName the user name of this commerce term entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce term entry.
	 *
	 * @return the create date of this commerce term entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce term entry.
	 *
	 * @param createDate the create date of this commerce term entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce term entry.
	 *
	 * @return the modified date of this commerce term entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce term entry.
	 *
	 * @param modifiedDate the modified date of this commerce term entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the active of this commerce term entry.
	 *
	 * @return the active of this commerce term entry
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this commerce term entry is active.
	 *
	 * @return <code>true</code> if this commerce term entry is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this commerce term entry is active.
	 *
	 * @param active the active of this commerce term entry
	 */
	public void setActive(boolean active);

	/**
	 * Returns the display date of this commerce term entry.
	 *
	 * @return the display date of this commerce term entry
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this commerce term entry.
	 *
	 * @param displayDate the display date of this commerce term entry
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this commerce term entry.
	 *
	 * @return the expiration date of this commerce term entry
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this commerce term entry.
	 *
	 * @param expirationDate the expiration date of this commerce term entry
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the name of this commerce term entry.
	 *
	 * @return the name of this commerce term entry
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this commerce term entry.
	 *
	 * @param name the name of this commerce term entry
	 */
	public void setName(String name);

	/**
	 * Returns the priority of this commerce term entry.
	 *
	 * @return the priority of this commerce term entry
	 */
	public double getPriority();

	/**
	 * Sets the priority of this commerce term entry.
	 *
	 * @param priority the priority of this commerce term entry
	 */
	public void setPriority(double priority);

	/**
	 * Returns the type of this commerce term entry.
	 *
	 * @return the type of this commerce term entry
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this commerce term entry.
	 *
	 * @param type the type of this commerce term entry
	 */
	public void setType(String type);

	/**
	 * Returns the type settings of this commerce term entry.
	 *
	 * @return the type settings of this commerce term entry
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this commerce term entry.
	 *
	 * @param typeSettings the type settings of this commerce term entry
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the last publish date of this commerce term entry.
	 *
	 * @return the last publish date of this commerce term entry
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this commerce term entry.
	 *
	 * @param lastPublishDate the last publish date of this commerce term entry
	 */
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this commerce term entry.
	 *
	 * @return the status of this commerce term entry
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this commerce term entry.
	 *
	 * @param status the status of this commerce term entry
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this commerce term entry.
	 *
	 * @return the status by user ID of this commerce term entry
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this commerce term entry.
	 *
	 * @param statusByUserId the status by user ID of this commerce term entry
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this commerce term entry.
	 *
	 * @return the status by user uuid of this commerce term entry
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this commerce term entry.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce term entry
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this commerce term entry.
	 *
	 * @return the status by user name of this commerce term entry
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this commerce term entry.
	 *
	 * @param statusByUserName the status by user name of this commerce term entry
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this commerce term entry.
	 *
	 * @return the status date of this commerce term entry
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this commerce term entry.
	 *
	 * @param statusDate the status date of this commerce term entry
	 */
	@Override
	public void setStatusDate(Date statusDate);

	public String[] getAvailableLanguageIds();

	public String getDescription();

	public String getDescription(String languageId);

	public String getDescription(String languageId, boolean useDefault);

	public String getDescriptionMapAsXML();

	public Map<String, String> getLanguageIdToDescriptionMap();

	public String getLabel();

	public String getLabel(String languageId);

	public String getLabel(String languageId, boolean useDefault);

	public String getLabelMapAsXML();

	public Map<String, String> getLanguageIdToLabelMap();

	/**
	 * Returns <code>true</code> if this commerce term entry is approved.
	 *
	 * @return <code>true</code> if this commerce term entry is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this commerce term entry is denied.
	 *
	 * @return <code>true</code> if this commerce term entry is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this commerce term entry is a draft.
	 *
	 * @return <code>true</code> if this commerce term entry is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this commerce term entry is expired.
	 *
	 * @return <code>true</code> if this commerce term entry is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this commerce term entry is inactive.
	 *
	 * @return <code>true</code> if this commerce term entry is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this commerce term entry is incomplete.
	 *
	 * @return <code>true</code> if this commerce term entry is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this commerce term entry is pending.
	 *
	 * @return <code>true</code> if this commerce term entry is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this commerce term entry is scheduled.
	 *
	 * @return <code>true</code> if this commerce term entry is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public CommerceTermEntry cloneWithOriginalValues();

}