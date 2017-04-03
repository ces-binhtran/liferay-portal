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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.product.service.http.CommerceProductDefinitionServiceSoap}.
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.http.CommerceProductDefinitionServiceSoap
 * @generated
 */
@ProviderType
public class CommerceProductDefinitionSoap implements Serializable {
	public static CommerceProductDefinitionSoap toSoapModel(
		CommerceProductDefinition model) {
		CommerceProductDefinitionSoap soapModel = new CommerceProductDefinitionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCommerceProductDefinitionId(model.getCommerceProductDefinitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setUrlTitle(model.getUrlTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setProductTypeName(model.getProductTypeName());
		soapModel.setAvailableIndividually(model.getAvailableIndividually());
		soapModel.setDDMStructureKey(model.getDDMStructureKey());
		soapModel.setBaseSKU(model.getBaseSKU());
		soapModel.setDisplayDate(model.getDisplayDate());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static CommerceProductDefinitionSoap[] toSoapModels(
		CommerceProductDefinition[] models) {
		CommerceProductDefinitionSoap[] soapModels = new CommerceProductDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceProductDefinitionSoap[][] toSoapModels(
		CommerceProductDefinition[][] models) {
		CommerceProductDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceProductDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceProductDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceProductDefinitionSoap[] toSoapModels(
		List<CommerceProductDefinition> models) {
		List<CommerceProductDefinitionSoap> soapModels = new ArrayList<CommerceProductDefinitionSoap>(models.size());

		for (CommerceProductDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceProductDefinitionSoap[soapModels.size()]);
	}

	public CommerceProductDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _commerceProductDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceProductDefinitionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCommerceProductDefinitionId() {
		return _commerceProductDefinitionId;
	}

	public void setCommerceProductDefinitionId(long commerceProductDefinitionId) {
		_commerceProductDefinitionId = commerceProductDefinitionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		_urlTitle = urlTitle;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getProductTypeName() {
		return _productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	public boolean getAvailableIndividually() {
		return _availableIndividually;
	}

	public boolean isAvailableIndividually() {
		return _availableIndividually;
	}

	public void setAvailableIndividually(boolean availableIndividually) {
		_availableIndividually = availableIndividually;
	}

	public String getDDMStructureKey() {
		return _DDMStructureKey;
	}

	public void setDDMStructureKey(String DDMStructureKey) {
		_DDMStructureKey = DDMStructureKey;
	}

	public String getBaseSKU() {
		return _baseSKU;
	}

	public void setBaseSKU(String baseSKU) {
		_baseSKU = baseSKU;
	}

	public Date getDisplayDate() {
		return _displayDate;
	}

	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private long _commerceProductDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _urlTitle;
	private String _description;
	private String _productTypeName;
	private boolean _availableIndividually;
	private String _DDMStructureKey;
	private String _baseSKU;
	private Date _displayDate;
	private Date _expirationDate;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}