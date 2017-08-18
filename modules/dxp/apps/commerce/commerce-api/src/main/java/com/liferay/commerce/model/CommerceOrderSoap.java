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

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.service.http.CommerceOrderServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.http.CommerceOrderServiceSoap
 * @generated
 */
@ProviderType
public class CommerceOrderSoap implements Serializable {
	public static CommerceOrderSoap toSoapModel(CommerceOrder model) {
		CommerceOrderSoap soapModel = new CommerceOrderSoap();

		soapModel.setCommerceOrderId(model.getCommerceOrderId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrderUserId(model.getOrderUserId());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CommerceOrderSoap[] toSoapModels(CommerceOrder[] models) {
		CommerceOrderSoap[] soapModels = new CommerceOrderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderSoap[][] toSoapModels(CommerceOrder[][] models) {
		CommerceOrderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceOrderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceOrderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderSoap[] toSoapModels(List<CommerceOrder> models) {
		List<CommerceOrderSoap> soapModels = new ArrayList<CommerceOrderSoap>(models.size());

		for (CommerceOrder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceOrderSoap[soapModels.size()]);
	}

	public CommerceOrderSoap() {
	}

	public long getPrimaryKey() {
		return _commerceOrderId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceOrderId(pk);
	}

	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public void setCommerceOrderId(long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
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

	public long getOrderUserId() {
		return _orderUserId;
	}

	public void setOrderUserId(long orderUserId) {
		_orderUserId = orderUserId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _commerceOrderId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _orderUserId;
	private int _status;
}