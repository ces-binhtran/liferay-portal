/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.reports.engine.console.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Entry service. Represents a row in the &quot;Reports_Entry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.reports.engine.console.model.impl.EntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.reports.engine.console.model.impl.EntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @see com.liferay.portal.reports.engine.console.model.impl.EntryImpl
 * @see com.liferay.portal.reports.engine.console.model.impl.EntryModelImpl
 * @generated
 */
@ProviderType
public interface EntryModel extends BaseModel<Entry>, GroupedModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a entry model instance should use the {@link Entry} interface instead.
	 */

	/**
	 * Returns the primary key of this entry.
	 *
	 * @return the primary key of this entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this entry.
	 *
	 * @param primaryKey the primary key of this entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the entry ID of this entry.
	 *
	 * @return the entry ID of this entry
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this entry.
	 *
	 * @param entryId the entry ID of this entry
	 */
	public void setEntryId(long entryId);

	/**
	 * Returns the group ID of this entry.
	 *
	 * @return the group ID of this entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this entry.
	 *
	 * @param groupId the group ID of this entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this entry.
	 *
	 * @return the company ID of this entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this entry.
	 *
	 * @param companyId the company ID of this entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this entry.
	 *
	 * @return the user ID of this entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this entry.
	 *
	 * @param userId the user ID of this entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this entry.
	 *
	 * @return the user uuid of this entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this entry.
	 *
	 * @param userUuid the user uuid of this entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this entry.
	 *
	 * @return the user name of this entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this entry.
	 *
	 * @param userName the user name of this entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this entry.
	 *
	 * @return the create date of this entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this entry.
	 *
	 * @param createDate the create date of this entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this entry.
	 *
	 * @return the modified date of this entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this entry.
	 *
	 * @param modifiedDate the modified date of this entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the definition ID of this entry.
	 *
	 * @return the definition ID of this entry
	 */
	public long getDefinitionId();

	/**
	 * Sets the definition ID of this entry.
	 *
	 * @param definitionId the definition ID of this entry
	 */
	public void setDefinitionId(long definitionId);

	/**
	 * Returns the format of this entry.
	 *
	 * @return the format of this entry
	 */
	@AutoEscape
	public String getFormat();

	/**
	 * Sets the format of this entry.
	 *
	 * @param format the format of this entry
	 */
	public void setFormat(String format);

	/**
	 * Returns the schedule request of this entry.
	 *
	 * @return the schedule request of this entry
	 */
	public boolean getScheduleRequest();

	/**
	 * Returns <code>true</code> if this entry is schedule request.
	 *
	 * @return <code>true</code> if this entry is schedule request; <code>false</code> otherwise
	 */
	public boolean isScheduleRequest();

	/**
	 * Sets whether this entry is schedule request.
	 *
	 * @param scheduleRequest the schedule request of this entry
	 */
	public void setScheduleRequest(boolean scheduleRequest);

	/**
	 * Returns the start date of this entry.
	 *
	 * @return the start date of this entry
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this entry.
	 *
	 * @param startDate the start date of this entry
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this entry.
	 *
	 * @return the end date of this entry
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this entry.
	 *
	 * @param endDate the end date of this entry
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the repeating of this entry.
	 *
	 * @return the repeating of this entry
	 */
	public boolean getRepeating();

	/**
	 * Returns <code>true</code> if this entry is repeating.
	 *
	 * @return <code>true</code> if this entry is repeating; <code>false</code> otherwise
	 */
	public boolean isRepeating();

	/**
	 * Sets whether this entry is repeating.
	 *
	 * @param repeating the repeating of this entry
	 */
	public void setRepeating(boolean repeating);

	/**
	 * Returns the recurrence of this entry.
	 *
	 * @return the recurrence of this entry
	 */
	@AutoEscape
	public String getRecurrence();

	/**
	 * Sets the recurrence of this entry.
	 *
	 * @param recurrence the recurrence of this entry
	 */
	public void setRecurrence(String recurrence);

	/**
	 * Returns the email notifications of this entry.
	 *
	 * @return the email notifications of this entry
	 */
	@AutoEscape
	public String getEmailNotifications();

	/**
	 * Sets the email notifications of this entry.
	 *
	 * @param emailNotifications the email notifications of this entry
	 */
	public void setEmailNotifications(String emailNotifications);

	/**
	 * Returns the email delivery of this entry.
	 *
	 * @return the email delivery of this entry
	 */
	@AutoEscape
	public String getEmailDelivery();

	/**
	 * Sets the email delivery of this entry.
	 *
	 * @param emailDelivery the email delivery of this entry
	 */
	public void setEmailDelivery(String emailDelivery);

	/**
	 * Returns the portlet ID of this entry.
	 *
	 * @return the portlet ID of this entry
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this entry.
	 *
	 * @param portletId the portlet ID of this entry
	 */
	public void setPortletId(String portletId);

	/**
	 * Returns the page url of this entry.
	 *
	 * @return the page url of this entry
	 */
	@AutoEscape
	public String getPageURL();

	/**
	 * Sets the page url of this entry.
	 *
	 * @param pageURL the page url of this entry
	 */
	public void setPageURL(String pageURL);

	/**
	 * Returns the report parameters of this entry.
	 *
	 * @return the report parameters of this entry
	 */
	@AutoEscape
	public String getReportParameters();

	/**
	 * Sets the report parameters of this entry.
	 *
	 * @param reportParameters the report parameters of this entry
	 */
	public void setReportParameters(String reportParameters);

	/**
	 * Returns the status of this entry.
	 *
	 * @return the status of this entry
	 */
	@AutoEscape
	public String getStatus();

	/**
	 * Sets the status of this entry.
	 *
	 * @param status the status of this entry
	 */
	public void setStatus(String status);

	/**
	 * Returns the error message of this entry.
	 *
	 * @return the error message of this entry
	 */
	@AutoEscape
	public String getErrorMessage();

	/**
	 * Sets the error message of this entry.
	 *
	 * @param errorMessage the error message of this entry
	 */
	public void setErrorMessage(String errorMessage);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Entry entry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Entry> toCacheModel();

	@Override
	public Entry toEscapedModel();

	@Override
	public Entry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}