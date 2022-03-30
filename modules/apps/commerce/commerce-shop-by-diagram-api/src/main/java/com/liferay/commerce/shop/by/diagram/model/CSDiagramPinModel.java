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

package com.liferay.commerce.shop.by.diagram.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CSDiagramPin service. Represents a row in the &quot;CSDiagramPin&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.shop.by.diagram.model.impl.CSDiagramPinImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CSDiagramPin
 * @generated
 */
@ProviderType
public interface CSDiagramPinModel
	extends AuditedModel, BaseModel<CSDiagramPin>, CTModel<CSDiagramPin>,
			MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cs diagram pin model instance should use the {@link CSDiagramPin} interface instead.
	 */

	/**
	 * Returns the primary key of this cs diagram pin.
	 *
	 * @return the primary key of this cs diagram pin
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cs diagram pin.
	 *
	 * @param primaryKey the primary key of this cs diagram pin
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this cs diagram pin.
	 *
	 * @return the mvcc version of this cs diagram pin
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this cs diagram pin.
	 *
	 * @param mvccVersion the mvcc version of this cs diagram pin
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this cs diagram pin.
	 *
	 * @return the ct collection ID of this cs diagram pin
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this cs diagram pin.
	 *
	 * @param ctCollectionId the ct collection ID of this cs diagram pin
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the cs diagram pin ID of this cs diagram pin.
	 *
	 * @return the cs diagram pin ID of this cs diagram pin
	 */
	public long getCSDiagramPinId();

	/**
	 * Sets the cs diagram pin ID of this cs diagram pin.
	 *
	 * @param CSDiagramPinId the cs diagram pin ID of this cs diagram pin
	 */
	public void setCSDiagramPinId(long CSDiagramPinId);

	/**
	 * Returns the company ID of this cs diagram pin.
	 *
	 * @return the company ID of this cs diagram pin
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cs diagram pin.
	 *
	 * @param companyId the company ID of this cs diagram pin
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cs diagram pin.
	 *
	 * @return the user ID of this cs diagram pin
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cs diagram pin.
	 *
	 * @param userId the user ID of this cs diagram pin
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cs diagram pin.
	 *
	 * @return the user uuid of this cs diagram pin
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cs diagram pin.
	 *
	 * @param userUuid the user uuid of this cs diagram pin
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cs diagram pin.
	 *
	 * @return the user name of this cs diagram pin
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cs diagram pin.
	 *
	 * @param userName the user name of this cs diagram pin
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cs diagram pin.
	 *
	 * @return the create date of this cs diagram pin
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cs diagram pin.
	 *
	 * @param createDate the create date of this cs diagram pin
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cs diagram pin.
	 *
	 * @return the modified date of this cs diagram pin
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cs diagram pin.
	 *
	 * @param modifiedDate the modified date of this cs diagram pin
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the cp definition ID of this cs diagram pin.
	 *
	 * @return the cp definition ID of this cs diagram pin
	 */
	public long getCPDefinitionId();

	/**
	 * Sets the cp definition ID of this cs diagram pin.
	 *
	 * @param CPDefinitionId the cp definition ID of this cs diagram pin
	 */
	public void setCPDefinitionId(long CPDefinitionId);

	/**
	 * Returns the position x of this cs diagram pin.
	 *
	 * @return the position x of this cs diagram pin
	 */
	public double getPositionX();

	/**
	 * Sets the position x of this cs diagram pin.
	 *
	 * @param positionX the position x of this cs diagram pin
	 */
	public void setPositionX(double positionX);

	/**
	 * Returns the position y of this cs diagram pin.
	 *
	 * @return the position y of this cs diagram pin
	 */
	public double getPositionY();

	/**
	 * Sets the position y of this cs diagram pin.
	 *
	 * @param positionY the position y of this cs diagram pin
	 */
	public void setPositionY(double positionY);

	/**
	 * Returns the sequence of this cs diagram pin.
	 *
	 * @return the sequence of this cs diagram pin
	 */
	@AutoEscape
	public String getSequence();

	/**
	 * Sets the sequence of this cs diagram pin.
	 *
	 * @param sequence the sequence of this cs diagram pin
	 */
	public void setSequence(String sequence);

	@Override
	public CSDiagramPin cloneWithOriginalValues();

}