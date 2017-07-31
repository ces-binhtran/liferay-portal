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

package com.liferay.commerce.address.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.address.service.http.CommerceCountryServiceSoap}.
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.address.service.http.CommerceCountryServiceSoap
 * @generated
 */
@ProviderType
public class CommerceCountrySoap implements Serializable {
	public static CommerceCountrySoap toSoapModel(CommerceCountry model) {
		CommerceCountrySoap soapModel = new CommerceCountrySoap();

		soapModel.setCommerceCountryId(model.getCommerceCountryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setAllowsBilling(model.getAllowsBilling());
		soapModel.setAllowsShipping(model.getAllowsShipping());
		soapModel.setTwoLettersISOCode(model.getTwoLettersISOCode());
		soapModel.setThreeLettersISOCode(model.getThreeLettersISOCode());
		soapModel.setNumericISOCode(model.getNumericISOCode());
		soapModel.setPriority(model.getPriority());
		soapModel.setPublished(model.getPublished());

		return soapModel;
	}

	public static CommerceCountrySoap[] toSoapModels(CommerceCountry[] models) {
		CommerceCountrySoap[] soapModels = new CommerceCountrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceCountrySoap[][] toSoapModels(
		CommerceCountry[][] models) {
		CommerceCountrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceCountrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceCountrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceCountrySoap[] toSoapModels(
		List<CommerceCountry> models) {
		List<CommerceCountrySoap> soapModels = new ArrayList<CommerceCountrySoap>(models.size());

		for (CommerceCountry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceCountrySoap[soapModels.size()]);
	}

	public CommerceCountrySoap() {
	}

	public long getPrimaryKey() {
		return _commerceCountryId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceCountryId(pk);
	}

	public long getCommerceCountryId() {
		return _commerceCountryId;
	}

	public void setCommerceCountryId(long commerceCountryId) {
		_commerceCountryId = commerceCountryId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public boolean getAllowsBilling() {
		return _allowsBilling;
	}

	public boolean isAllowsBilling() {
		return _allowsBilling;
	}

	public void setAllowsBilling(boolean allowsBilling) {
		_allowsBilling = allowsBilling;
	}

	public boolean getAllowsShipping() {
		return _allowsShipping;
	}

	public boolean isAllowsShipping() {
		return _allowsShipping;
	}

	public void setAllowsShipping(boolean allowsShipping) {
		_allowsShipping = allowsShipping;
	}

	public String getTwoLettersISOCode() {
		return _twoLettersISOCode;
	}

	public void setTwoLettersISOCode(String twoLettersISOCode) {
		_twoLettersISOCode = twoLettersISOCode;
	}

	public String getThreeLettersISOCode() {
		return _threeLettersISOCode;
	}

	public void setThreeLettersISOCode(String threeLettersISOCode) {
		_threeLettersISOCode = threeLettersISOCode;
	}

	public int getNumericISOCode() {
		return _numericISOCode;
	}

	public void setNumericISOCode(int numericISOCode) {
		_numericISOCode = numericISOCode;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public boolean getPublished() {
		return _published;
	}

	public boolean isPublished() {
		return _published;
	}

	public void setPublished(boolean published) {
		_published = published;
	}

	private long _commerceCountryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private boolean _allowsBilling;
	private boolean _allowsShipping;
	private String _twoLettersISOCode;
	private String _threeLettersISOCode;
	private int _numericISOCode;
	private int _priority;
	private boolean _published;
}