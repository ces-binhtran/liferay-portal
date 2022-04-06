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
 * The base model interface for the Contact service. Represents a row in the &quot;Contact_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ContactModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ContactImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Contact
 * @generated
 */
@ProviderType
public interface ContactModel
	extends AttachedModel, AuditedModel, BaseModel<Contact>, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a contact model instance should use the {@link Contact} interface instead.
	 */

	/**
	 * Returns the primary key of this contact.
	 *
	 * @return the primary key of this contact
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this contact.
	 *
	 * @param primaryKey the primary key of this contact
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this contact.
	 *
	 * @return the mvcc version of this contact
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this contact.
	 *
	 * @param mvccVersion the mvcc version of this contact
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the contact ID of this contact.
	 *
	 * @return the contact ID of this contact
	 */
	public long getContactId();

	/**
	 * Sets the contact ID of this contact.
	 *
	 * @param contactId the contact ID of this contact
	 */
	public void setContactId(long contactId);

	/**
	 * Returns the company ID of this contact.
	 *
	 * @return the company ID of this contact
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this contact.
	 *
	 * @param companyId the company ID of this contact
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this contact.
	 *
	 * @return the user ID of this contact
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this contact.
	 *
	 * @param userId the user ID of this contact
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this contact.
	 *
	 * @return the user uuid of this contact
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this contact.
	 *
	 * @param userUuid the user uuid of this contact
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this contact.
	 *
	 * @return the user name of this contact
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this contact.
	 *
	 * @param userName the user name of this contact
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this contact.
	 *
	 * @return the create date of this contact
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this contact.
	 *
	 * @param createDate the create date of this contact
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this contact.
	 *
	 * @return the modified date of this contact
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this contact.
	 *
	 * @param modifiedDate the modified date of this contact
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this contact.
	 *
	 * @return the fully qualified class name of this contact
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this contact.
	 *
	 * @return the class name ID of this contact
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this contact.
	 *
	 * @param classNameId the class name ID of this contact
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this contact.
	 *
	 * @return the class pk of this contact
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this contact.
	 *
	 * @param classPK the class pk of this contact
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the parent contact ID of this contact.
	 *
	 * @return the parent contact ID of this contact
	 */
	public long getParentContactId();

	/**
	 * Sets the parent contact ID of this contact.
	 *
	 * @param parentContactId the parent contact ID of this contact
	 */
	public void setParentContactId(long parentContactId);

	/**
	 * Returns the email address of this contact.
	 *
	 * @return the email address of this contact
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this contact.
	 *
	 * @param emailAddress the email address of this contact
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Returns the first name of this contact.
	 *
	 * @return the first name of this contact
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this contact.
	 *
	 * @param firstName the first name of this contact
	 */
	public void setFirstName(String firstName);

	/**
	 * Returns the middle name of this contact.
	 *
	 * @return the middle name of this contact
	 */
	@AutoEscape
	public String getMiddleName();

	/**
	 * Sets the middle name of this contact.
	 *
	 * @param middleName the middle name of this contact
	 */
	public void setMiddleName(String middleName);

	/**
	 * Returns the last name of this contact.
	 *
	 * @return the last name of this contact
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this contact.
	 *
	 * @param lastName the last name of this contact
	 */
	public void setLastName(String lastName);

	/**
	 * Returns the prefix ID of this contact.
	 *
	 * @return the prefix ID of this contact
	 */
	public long getPrefixId();

	/**
	 * Sets the prefix ID of this contact.
	 *
	 * @param prefixId the prefix ID of this contact
	 */
	public void setPrefixId(long prefixId);

	/**
	 * Returns the suffix ID of this contact.
	 *
	 * @return the suffix ID of this contact
	 */
	public long getSuffixId();

	/**
	 * Sets the suffix ID of this contact.
	 *
	 * @param suffixId the suffix ID of this contact
	 */
	public void setSuffixId(long suffixId);

	/**
	 * Returns the male of this contact.
	 *
	 * @return the male of this contact
	 */
	public boolean getMale();

	/**
	 * Returns <code>true</code> if this contact is male.
	 *
	 * @return <code>true</code> if this contact is male; <code>false</code> otherwise
	 */
	public boolean isMale();

	/**
	 * Sets whether this contact is male.
	 *
	 * @param male the male of this contact
	 */
	public void setMale(boolean male);

	/**
	 * Returns the birthday of this contact.
	 *
	 * @return the birthday of this contact
	 */
	public Date getBirthday();

	/**
	 * Sets the birthday of this contact.
	 *
	 * @param birthday the birthday of this contact
	 */
	public void setBirthday(Date birthday);

	/**
	 * Returns the sms sn of this contact.
	 *
	 * @return the sms sn of this contact
	 */
	@AutoEscape
	public String getSmsSn();

	/**
	 * Sets the sms sn of this contact.
	 *
	 * @param smsSn the sms sn of this contact
	 */
	public void setSmsSn(String smsSn);

	/**
	 * Returns the facebook sn of this contact.
	 *
	 * @return the facebook sn of this contact
	 */
	@AutoEscape
	public String getFacebookSn();

	/**
	 * Sets the facebook sn of this contact.
	 *
	 * @param facebookSn the facebook sn of this contact
	 */
	public void setFacebookSn(String facebookSn);

	/**
	 * Returns the jabber sn of this contact.
	 *
	 * @return the jabber sn of this contact
	 */
	@AutoEscape
	public String getJabberSn();

	/**
	 * Sets the jabber sn of this contact.
	 *
	 * @param jabberSn the jabber sn of this contact
	 */
	public void setJabberSn(String jabberSn);

	/**
	 * Returns the skype sn of this contact.
	 *
	 * @return the skype sn of this contact
	 */
	@AutoEscape
	public String getSkypeSn();

	/**
	 * Sets the skype sn of this contact.
	 *
	 * @param skypeSn the skype sn of this contact
	 */
	public void setSkypeSn(String skypeSn);

	/**
	 * Returns the twitter sn of this contact.
	 *
	 * @return the twitter sn of this contact
	 */
	@AutoEscape
	public String getTwitterSn();

	/**
	 * Sets the twitter sn of this contact.
	 *
	 * @param twitterSn the twitter sn of this contact
	 */
	public void setTwitterSn(String twitterSn);

	/**
	 * Returns the employee status ID of this contact.
	 *
	 * @return the employee status ID of this contact
	 */
	@AutoEscape
	public String getEmployeeStatusId();

	/**
	 * Sets the employee status ID of this contact.
	 *
	 * @param employeeStatusId the employee status ID of this contact
	 */
	public void setEmployeeStatusId(String employeeStatusId);

	/**
	 * Returns the employee number of this contact.
	 *
	 * @return the employee number of this contact
	 */
	@AutoEscape
	public String getEmployeeNumber();

	/**
	 * Sets the employee number of this contact.
	 *
	 * @param employeeNumber the employee number of this contact
	 */
	public void setEmployeeNumber(String employeeNumber);

	/**
	 * Returns the job title of this contact.
	 *
	 * @return the job title of this contact
	 */
	@AutoEscape
	public String getJobTitle();

	/**
	 * Sets the job title of this contact.
	 *
	 * @param jobTitle the job title of this contact
	 */
	public void setJobTitle(String jobTitle);

	/**
	 * Returns the job class of this contact.
	 *
	 * @return the job class of this contact
	 */
	@AutoEscape
	public String getJobClass();

	/**
	 * Sets the job class of this contact.
	 *
	 * @param jobClass the job class of this contact
	 */
	public void setJobClass(String jobClass);

	/**
	 * Returns the hours of operation of this contact.
	 *
	 * @return the hours of operation of this contact
	 */
	@AutoEscape
	public String getHoursOfOperation();

	/**
	 * Sets the hours of operation of this contact.
	 *
	 * @param hoursOfOperation the hours of operation of this contact
	 */
	public void setHoursOfOperation(String hoursOfOperation);

	@Override
	public Contact cloneWithOriginalValues();

}