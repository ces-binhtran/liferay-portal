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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

/**
 * The base model interface for the SamlIdpSpConnection service. Represents a row in the &quot;SamlIdpSpConnection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.saml.persistence.model.impl.SamlIdpSpConnectionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.saml.persistence.model.impl.SamlIdpSpConnectionImpl</code>.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpConnection
 * @generated
 */
@ProviderType
public interface SamlIdpSpConnectionModel
	extends AuditedModel, BaseModel<SamlIdpSpConnection>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml idp sp connection model instance should use the {@link SamlIdpSpConnection} interface instead.
	 */

	/**
	 * Returns the primary key of this saml idp sp connection.
	 *
	 * @return the primary key of this saml idp sp connection
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml idp sp connection.
	 *
	 * @param primaryKey the primary key of this saml idp sp connection
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml idp sp connection ID of this saml idp sp connection.
	 *
	 * @return the saml idp sp connection ID of this saml idp sp connection
	 */
	public long getSamlIdpSpConnectionId();

	/**
	 * Sets the saml idp sp connection ID of this saml idp sp connection.
	 *
	 * @param samlIdpSpConnectionId the saml idp sp connection ID of this saml idp sp connection
	 */
	public void setSamlIdpSpConnectionId(long samlIdpSpConnectionId);

	/**
	 * Returns the company ID of this saml idp sp connection.
	 *
	 * @return the company ID of this saml idp sp connection
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml idp sp connection.
	 *
	 * @param companyId the company ID of this saml idp sp connection
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this saml idp sp connection.
	 *
	 * @return the user ID of this saml idp sp connection
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this saml idp sp connection.
	 *
	 * @param userId the user ID of this saml idp sp connection
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this saml idp sp connection.
	 *
	 * @return the user uuid of this saml idp sp connection
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this saml idp sp connection.
	 *
	 * @param userUuid the user uuid of this saml idp sp connection
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this saml idp sp connection.
	 *
	 * @return the user name of this saml idp sp connection
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this saml idp sp connection.
	 *
	 * @param userName the user name of this saml idp sp connection
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this saml idp sp connection.
	 *
	 * @return the create date of this saml idp sp connection
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml idp sp connection.
	 *
	 * @param createDate the create date of this saml idp sp connection
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this saml idp sp connection.
	 *
	 * @return the modified date of this saml idp sp connection
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this saml idp sp connection.
	 *
	 * @param modifiedDate the modified date of this saml idp sp connection
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the saml sp entity ID of this saml idp sp connection.
	 *
	 * @return the saml sp entity ID of this saml idp sp connection
	 */
	@AutoEscape
	public String getSamlSpEntityId();

	/**
	 * Sets the saml sp entity ID of this saml idp sp connection.
	 *
	 * @param samlSpEntityId the saml sp entity ID of this saml idp sp connection
	 */
	public void setSamlSpEntityId(String samlSpEntityId);

	/**
	 * Returns the assertion lifetime of this saml idp sp connection.
	 *
	 * @return the assertion lifetime of this saml idp sp connection
	 */
	public int getAssertionLifetime();

	/**
	 * Sets the assertion lifetime of this saml idp sp connection.
	 *
	 * @param assertionLifetime the assertion lifetime of this saml idp sp connection
	 */
	public void setAssertionLifetime(int assertionLifetime);

	/**
	 * Returns the attribute names of this saml idp sp connection.
	 *
	 * @return the attribute names of this saml idp sp connection
	 */
	@AutoEscape
	public String getAttributeNames();

	/**
	 * Sets the attribute names of this saml idp sp connection.
	 *
	 * @param attributeNames the attribute names of this saml idp sp connection
	 */
	public void setAttributeNames(String attributeNames);

	/**
	 * Returns the attributes enabled of this saml idp sp connection.
	 *
	 * @return the attributes enabled of this saml idp sp connection
	 */
	public boolean getAttributesEnabled();

	/**
	 * Returns <code>true</code> if this saml idp sp connection is attributes enabled.
	 *
	 * @return <code>true</code> if this saml idp sp connection is attributes enabled; <code>false</code> otherwise
	 */
	public boolean isAttributesEnabled();

	/**
	 * Sets whether this saml idp sp connection is attributes enabled.
	 *
	 * @param attributesEnabled the attributes enabled of this saml idp sp connection
	 */
	public void setAttributesEnabled(boolean attributesEnabled);

	/**
	 * Returns the attributes namespace enabled of this saml idp sp connection.
	 *
	 * @return the attributes namespace enabled of this saml idp sp connection
	 */
	public boolean getAttributesNamespaceEnabled();

	/**
	 * Returns <code>true</code> if this saml idp sp connection is attributes namespace enabled.
	 *
	 * @return <code>true</code> if this saml idp sp connection is attributes namespace enabled; <code>false</code> otherwise
	 */
	public boolean isAttributesNamespaceEnabled();

	/**
	 * Sets whether this saml idp sp connection is attributes namespace enabled.
	 *
	 * @param attributesNamespaceEnabled the attributes namespace enabled of this saml idp sp connection
	 */
	public void setAttributesNamespaceEnabled(
		boolean attributesNamespaceEnabled);

	/**
	 * Returns the enabled of this saml idp sp connection.
	 *
	 * @return the enabled of this saml idp sp connection
	 */
	public boolean getEnabled();

	/**
	 * Returns <code>true</code> if this saml idp sp connection is enabled.
	 *
	 * @return <code>true</code> if this saml idp sp connection is enabled; <code>false</code> otherwise
	 */
	public boolean isEnabled();

	/**
	 * Sets whether this saml idp sp connection is enabled.
	 *
	 * @param enabled the enabled of this saml idp sp connection
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Returns the encryption forced of this saml idp sp connection.
	 *
	 * @return the encryption forced of this saml idp sp connection
	 */
	public boolean getEncryptionForced();

	/**
	 * Returns <code>true</code> if this saml idp sp connection is encryption forced.
	 *
	 * @return <code>true</code> if this saml idp sp connection is encryption forced; <code>false</code> otherwise
	 */
	public boolean isEncryptionForced();

	/**
	 * Sets whether this saml idp sp connection is encryption forced.
	 *
	 * @param encryptionForced the encryption forced of this saml idp sp connection
	 */
	public void setEncryptionForced(boolean encryptionForced);

	/**
	 * Returns the metadata url of this saml idp sp connection.
	 *
	 * @return the metadata url of this saml idp sp connection
	 */
	@AutoEscape
	public String getMetadataUrl();

	/**
	 * Sets the metadata url of this saml idp sp connection.
	 *
	 * @param metadataUrl the metadata url of this saml idp sp connection
	 */
	public void setMetadataUrl(String metadataUrl);

	/**
	 * Returns the metadata xml of this saml idp sp connection.
	 *
	 * @return the metadata xml of this saml idp sp connection
	 */
	@AutoEscape
	public String getMetadataXml();

	/**
	 * Sets the metadata xml of this saml idp sp connection.
	 *
	 * @param metadataXml the metadata xml of this saml idp sp connection
	 */
	public void setMetadataXml(String metadataXml);

	/**
	 * Returns the metadata updated date of this saml idp sp connection.
	 *
	 * @return the metadata updated date of this saml idp sp connection
	 */
	public Date getMetadataUpdatedDate();

	/**
	 * Sets the metadata updated date of this saml idp sp connection.
	 *
	 * @param metadataUpdatedDate the metadata updated date of this saml idp sp connection
	 */
	public void setMetadataUpdatedDate(Date metadataUpdatedDate);

	/**
	 * Returns the name of this saml idp sp connection.
	 *
	 * @return the name of this saml idp sp connection
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this saml idp sp connection.
	 *
	 * @param name the name of this saml idp sp connection
	 */
	public void setName(String name);

	/**
	 * Returns the name ID attribute of this saml idp sp connection.
	 *
	 * @return the name ID attribute of this saml idp sp connection
	 */
	@AutoEscape
	public String getNameIdAttribute();

	/**
	 * Sets the name ID attribute of this saml idp sp connection.
	 *
	 * @param nameIdAttribute the name ID attribute of this saml idp sp connection
	 */
	public void setNameIdAttribute(String nameIdAttribute);

	/**
	 * Returns the name ID format of this saml idp sp connection.
	 *
	 * @return the name ID format of this saml idp sp connection
	 */
	@AutoEscape
	public String getNameIdFormat();

	/**
	 * Sets the name ID format of this saml idp sp connection.
	 *
	 * @param nameIdFormat the name ID format of this saml idp sp connection
	 */
	public void setNameIdFormat(String nameIdFormat);

}