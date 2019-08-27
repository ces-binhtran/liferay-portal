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

package com.liferay.layout.seo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.layout.seo.service.http.LayoutCanonicalURLServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LayoutCanonicalURLSoap implements Serializable {

	public static LayoutCanonicalURLSoap toSoapModel(LayoutCanonicalURL model) {
		LayoutCanonicalURLSoap soapModel = new LayoutCanonicalURLSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLayoutCanonicalURLId(model.getLayoutCanonicalURLId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCanonicalURL(model.getCanonicalURL());
		soapModel.setEnabled(model.isEnabled());
		soapModel.setPrivateLayout(model.isPrivateLayout());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setLayoutId(model.getLayoutId());

		return soapModel;
	}

	public static LayoutCanonicalURLSoap[] toSoapModels(
		LayoutCanonicalURL[] models) {

		LayoutCanonicalURLSoap[] soapModels =
			new LayoutCanonicalURLSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutCanonicalURLSoap[][] toSoapModels(
		LayoutCanonicalURL[][] models) {

		LayoutCanonicalURLSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LayoutCanonicalURLSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutCanonicalURLSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutCanonicalURLSoap[] toSoapModels(
		List<LayoutCanonicalURL> models) {

		List<LayoutCanonicalURLSoap> soapModels =
			new ArrayList<LayoutCanonicalURLSoap>(models.size());

		for (LayoutCanonicalURL model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LayoutCanonicalURLSoap[soapModels.size()]);
	}

	public LayoutCanonicalURLSoap() {
	}

	public long getPrimaryKey() {
		return _layoutCanonicalURLId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutCanonicalURLId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLayoutCanonicalURLId() {
		return _layoutCanonicalURLId;
	}

	public void setLayoutCanonicalURLId(long layoutCanonicalURLId) {
		_layoutCanonicalURLId = layoutCanonicalURLId;
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

	public String getCanonicalURL() {
		return _canonicalURL;
	}

	public void setCanonicalURL(String canonicalURL) {
		_canonicalURL = canonicalURL;
	}

	public boolean getEnabled() {
		return _enabled;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	public boolean getPrivateLayout() {
		return _privateLayout;
	}

	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public long getLayoutId() {
		return _layoutId;
	}

	public void setLayoutId(long layoutId) {
		_layoutId = layoutId;
	}

	private String _uuid;
	private long _layoutCanonicalURLId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _canonicalURL;
	private boolean _enabled;
	private boolean _privateLayout;
	private Date _lastPublishDate;
	private long _layoutId;

}