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

package com.liferay.sharepoint.rest.oauth2.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SharepointOAuth2TokenEntry service. Represents a row in the &quot;SharepointOAuth2TokenEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.sharepoint.rest.oauth2.model.impl.SharepointOAuth2TokenEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.sharepoint.rest.oauth2.model.impl.SharepointOAuth2TokenEntryImpl</code>.
 * </p>
 *
 * @author Adolfo Pérez
 * @see SharepointOAuth2TokenEntry
 * @generated
 */
@ProviderType
public interface SharepointOAuth2TokenEntryModel
	extends BaseModel<SharepointOAuth2TokenEntry>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a sharepoint o auth2 token entry model instance should use the {@link SharepointOAuth2TokenEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this sharepoint o auth2 token entry.
	 *
	 * @return the primary key of this sharepoint o auth2 token entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this sharepoint o auth2 token entry.
	 *
	 * @param primaryKey the primary key of this sharepoint o auth2 token entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the sharepoint o auth2 token entry ID of this sharepoint o auth2 token entry.
	 *
	 * @return the sharepoint o auth2 token entry ID of this sharepoint o auth2 token entry
	 */
	public long getSharepointOAuth2TokenEntryId();

	/**
	 * Sets the sharepoint o auth2 token entry ID of this sharepoint o auth2 token entry.
	 *
	 * @param sharepointOAuth2TokenEntryId the sharepoint o auth2 token entry ID of this sharepoint o auth2 token entry
	 */
	public void setSharepointOAuth2TokenEntryId(
		long sharepointOAuth2TokenEntryId);

	/**
	 * Returns the company ID of this sharepoint o auth2 token entry.
	 *
	 * @return the company ID of this sharepoint o auth2 token entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this sharepoint o auth2 token entry.
	 *
	 * @param companyId the company ID of this sharepoint o auth2 token entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this sharepoint o auth2 token entry.
	 *
	 * @return the user ID of this sharepoint o auth2 token entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this sharepoint o auth2 token entry.
	 *
	 * @param userId the user ID of this sharepoint o auth2 token entry
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this sharepoint o auth2 token entry.
	 *
	 * @return the user uuid of this sharepoint o auth2 token entry
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this sharepoint o auth2 token entry.
	 *
	 * @param userUuid the user uuid of this sharepoint o auth2 token entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this sharepoint o auth2 token entry.
	 *
	 * @return the user name of this sharepoint o auth2 token entry
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this sharepoint o auth2 token entry.
	 *
	 * @param userName the user name of this sharepoint o auth2 token entry
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this sharepoint o auth2 token entry.
	 *
	 * @return the create date of this sharepoint o auth2 token entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this sharepoint o auth2 token entry.
	 *
	 * @param createDate the create date of this sharepoint o auth2 token entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the access token of this sharepoint o auth2 token entry.
	 *
	 * @return the access token of this sharepoint o auth2 token entry
	 */
	@AutoEscape
	public String getAccessToken();

	/**
	 * Sets the access token of this sharepoint o auth2 token entry.
	 *
	 * @param accessToken the access token of this sharepoint o auth2 token entry
	 */
	public void setAccessToken(String accessToken);

	/**
	 * Returns the configuration pid of this sharepoint o auth2 token entry.
	 *
	 * @return the configuration pid of this sharepoint o auth2 token entry
	 */
	@AutoEscape
	public String getConfigurationPid();

	/**
	 * Sets the configuration pid of this sharepoint o auth2 token entry.
	 *
	 * @param configurationPid the configuration pid of this sharepoint o auth2 token entry
	 */
	public void setConfigurationPid(String configurationPid);

	/**
	 * Returns the expiration date of this sharepoint o auth2 token entry.
	 *
	 * @return the expiration date of this sharepoint o auth2 token entry
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this sharepoint o auth2 token entry.
	 *
	 * @param expirationDate the expiration date of this sharepoint o auth2 token entry
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the refresh token of this sharepoint o auth2 token entry.
	 *
	 * @return the refresh token of this sharepoint o auth2 token entry
	 */
	@AutoEscape
	public String getRefreshToken();

	/**
	 * Sets the refresh token of this sharepoint o auth2 token entry.
	 *
	 * @param refreshToken the refresh token of this sharepoint o auth2 token entry
	 */
	public void setRefreshToken(String refreshToken);

	@Override
	public SharepointOAuth2TokenEntry cloneWithOriginalValues();

}