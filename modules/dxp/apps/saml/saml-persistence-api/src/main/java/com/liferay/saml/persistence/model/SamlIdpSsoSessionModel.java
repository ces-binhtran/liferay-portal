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

package com.liferay.saml.persistence.model;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

/**
 * The base model interface for the SamlIdpSsoSession service. Represents a row in the &quot;SamlIdpSsoSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.saml.persistence.model.impl.SamlIdpSsoSessionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.saml.persistence.model.impl.SamlIdpSsoSessionImpl</code>.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSsoSession
 * @generated
 */
@ProviderType
public interface SamlIdpSsoSessionModel
	extends AuditedModel, BaseModel<SamlIdpSsoSession>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml idp sso session model instance should use the {@link SamlIdpSsoSession} interface instead.
	 */

	/**
	 * Returns the primary key of this saml idp sso session.
	 *
	 * @return the primary key of this saml idp sso session
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml idp sso session.
	 *
	 * @param primaryKey the primary key of this saml idp sso session
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml idp sso session ID of this saml idp sso session.
	 *
	 * @return the saml idp sso session ID of this saml idp sso session
	 */
	public long getSamlIdpSsoSessionId();

	/**
	 * Sets the saml idp sso session ID of this saml idp sso session.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID of this saml idp sso session
	 */
	public void setSamlIdpSsoSessionId(long samlIdpSsoSessionId);

	/**
	 * Returns the company ID of this saml idp sso session.
	 *
	 * @return the company ID of this saml idp sso session
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml idp sso session.
	 *
	 * @param companyId the company ID of this saml idp sso session
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this saml idp sso session.
	 *
	 * @return the user ID of this saml idp sso session
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this saml idp sso session.
	 *
	 * @param userId the user ID of this saml idp sso session
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this saml idp sso session.
	 *
	 * @return the user uuid of this saml idp sso session
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this saml idp sso session.
	 *
	 * @param userUuid the user uuid of this saml idp sso session
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this saml idp sso session.
	 *
	 * @return the user name of this saml idp sso session
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this saml idp sso session.
	 *
	 * @param userName the user name of this saml idp sso session
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this saml idp sso session.
	 *
	 * @return the create date of this saml idp sso session
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml idp sso session.
	 *
	 * @param createDate the create date of this saml idp sso session
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this saml idp sso session.
	 *
	 * @return the modified date of this saml idp sso session
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this saml idp sso session.
	 *
	 * @param modifiedDate the modified date of this saml idp sso session
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the saml idp sso session key of this saml idp sso session.
	 *
	 * @return the saml idp sso session key of this saml idp sso session
	 */
	@AutoEscape
	public String getSamlIdpSsoSessionKey();

	/**
	 * Sets the saml idp sso session key of this saml idp sso session.
	 *
	 * @param samlIdpSsoSessionKey the saml idp sso session key of this saml idp sso session
	 */
	public void setSamlIdpSsoSessionKey(String samlIdpSsoSessionKey);

}