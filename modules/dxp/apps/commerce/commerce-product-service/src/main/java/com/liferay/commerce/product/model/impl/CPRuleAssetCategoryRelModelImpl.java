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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPRuleAssetCategoryRel;
import com.liferay.commerce.product.model.CPRuleAssetCategoryRelModel;
import com.liferay.commerce.product.model.CPRuleAssetCategoryRelSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CPRuleAssetCategoryRel service. Represents a row in the &quot;CPRuleAssetCategoryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CPRuleAssetCategoryRelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPRuleAssetCategoryRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleAssetCategoryRelImpl
 * @see CPRuleAssetCategoryRel
 * @see CPRuleAssetCategoryRelModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CPRuleAssetCategoryRelModelImpl extends BaseModelImpl<CPRuleAssetCategoryRel>
	implements CPRuleAssetCategoryRelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp rule asset category rel model instance should use the {@link CPRuleAssetCategoryRel} interface instead.
	 */
	public static final String TABLE_NAME = "CPRuleAssetCategoryRel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "CPRuleAssetCategoryRelId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "CPRuleId", Types.BIGINT },
			{ "assetCategoryId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CPRuleAssetCategoryRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPRuleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("assetCategoryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table CPRuleAssetCategoryRel (CPRuleAssetCategoryRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPRuleId LONG,assetCategoryId LONG)";
	public static final String TABLE_SQL_DROP = "drop table CPRuleAssetCategoryRel";
	public static final String ORDER_BY_JPQL = " ORDER BY cpRuleAssetCategoryRel.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CPRuleAssetCategoryRel.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.product.model.CPRuleAssetCategoryRel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.product.model.CPRuleAssetCategoryRel"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.product.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.product.model.CPRuleAssetCategoryRel"),
			true);
	public static final long CPRULEID_COLUMN_BITMASK = 1L;
	public static final long ASSETCATEGORYID_COLUMN_BITMASK = 2L;
	public static final long CREATEDATE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CPRuleAssetCategoryRel toModel(
		CPRuleAssetCategoryRelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CPRuleAssetCategoryRel model = new CPRuleAssetCategoryRelImpl();

		model.setCPRuleAssetCategoryRelId(soapModel.getCPRuleAssetCategoryRelId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCPRuleId(soapModel.getCPRuleId());
		model.setAssetCategoryId(soapModel.getAssetCategoryId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CPRuleAssetCategoryRel> toModels(
		CPRuleAssetCategoryRelSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CPRuleAssetCategoryRel> models = new ArrayList<CPRuleAssetCategoryRel>(soapModels.length);

		for (CPRuleAssetCategoryRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.product.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.product.model.CPRuleAssetCategoryRel"));

	public CPRuleAssetCategoryRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPRuleAssetCategoryRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPRuleAssetCategoryRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPRuleAssetCategoryRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPRuleAssetCategoryRel.class;
	}

	@Override
	public String getModelClassName() {
		return CPRuleAssetCategoryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("CPRuleAssetCategoryRelId", getCPRuleAssetCategoryRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("CPRuleId", getCPRuleId());
		attributes.put("assetCategoryId", getAssetCategoryId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long CPRuleAssetCategoryRelId = (Long)attributes.get(
				"CPRuleAssetCategoryRelId");

		if (CPRuleAssetCategoryRelId != null) {
			setCPRuleAssetCategoryRelId(CPRuleAssetCategoryRelId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long CPRuleId = (Long)attributes.get("CPRuleId");

		if (CPRuleId != null) {
			setCPRuleId(CPRuleId);
		}

		Long assetCategoryId = (Long)attributes.get("assetCategoryId");

		if (assetCategoryId != null) {
			setAssetCategoryId(assetCategoryId);
		}
	}

	@JSON
	@Override
	public long getCPRuleAssetCategoryRelId() {
		return _CPRuleAssetCategoryRelId;
	}

	@Override
	public void setCPRuleAssetCategoryRelId(long CPRuleAssetCategoryRelId) {
		_CPRuleAssetCategoryRelId = CPRuleAssetCategoryRelId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCPRuleId() {
		return _CPRuleId;
	}

	@Override
	public void setCPRuleId(long CPRuleId) {
		_columnBitmask |= CPRULEID_COLUMN_BITMASK;

		if (!_setOriginalCPRuleId) {
			_setOriginalCPRuleId = true;

			_originalCPRuleId = _CPRuleId;
		}

		_CPRuleId = CPRuleId;
	}

	public long getOriginalCPRuleId() {
		return _originalCPRuleId;
	}

	@JSON
	@Override
	public long getAssetCategoryId() {
		return _assetCategoryId;
	}

	@Override
	public void setAssetCategoryId(long assetCategoryId) {
		_columnBitmask |= ASSETCATEGORYID_COLUMN_BITMASK;

		if (!_setOriginalAssetCategoryId) {
			_setOriginalAssetCategoryId = true;

			_originalAssetCategoryId = _assetCategoryId;
		}

		_assetCategoryId = assetCategoryId;
	}

	public long getOriginalAssetCategoryId() {
		return _originalAssetCategoryId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CPRuleAssetCategoryRel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CPRuleAssetCategoryRel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CPRuleAssetCategoryRel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CPRuleAssetCategoryRelImpl cpRuleAssetCategoryRelImpl = new CPRuleAssetCategoryRelImpl();

		cpRuleAssetCategoryRelImpl.setCPRuleAssetCategoryRelId(getCPRuleAssetCategoryRelId());
		cpRuleAssetCategoryRelImpl.setGroupId(getGroupId());
		cpRuleAssetCategoryRelImpl.setCompanyId(getCompanyId());
		cpRuleAssetCategoryRelImpl.setUserId(getUserId());
		cpRuleAssetCategoryRelImpl.setUserName(getUserName());
		cpRuleAssetCategoryRelImpl.setCreateDate(getCreateDate());
		cpRuleAssetCategoryRelImpl.setModifiedDate(getModifiedDate());
		cpRuleAssetCategoryRelImpl.setCPRuleId(getCPRuleId());
		cpRuleAssetCategoryRelImpl.setAssetCategoryId(getAssetCategoryId());

		cpRuleAssetCategoryRelImpl.resetOriginalValues();

		return cpRuleAssetCategoryRelImpl;
	}

	@Override
	public int compareTo(CPRuleAssetCategoryRel cpRuleAssetCategoryRel) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				cpRuleAssetCategoryRel.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPRuleAssetCategoryRel)) {
			return false;
		}

		CPRuleAssetCategoryRel cpRuleAssetCategoryRel = (CPRuleAssetCategoryRel)obj;

		long primaryKey = cpRuleAssetCategoryRel.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CPRuleAssetCategoryRelModelImpl cpRuleAssetCategoryRelModelImpl = this;

		cpRuleAssetCategoryRelModelImpl._setModifiedDate = false;

		cpRuleAssetCategoryRelModelImpl._originalCPRuleId = cpRuleAssetCategoryRelModelImpl._CPRuleId;

		cpRuleAssetCategoryRelModelImpl._setOriginalCPRuleId = false;

		cpRuleAssetCategoryRelModelImpl._originalAssetCategoryId = cpRuleAssetCategoryRelModelImpl._assetCategoryId;

		cpRuleAssetCategoryRelModelImpl._setOriginalAssetCategoryId = false;

		cpRuleAssetCategoryRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CPRuleAssetCategoryRel> toCacheModel() {
		CPRuleAssetCategoryRelCacheModel cpRuleAssetCategoryRelCacheModel = new CPRuleAssetCategoryRelCacheModel();

		cpRuleAssetCategoryRelCacheModel.CPRuleAssetCategoryRelId = getCPRuleAssetCategoryRelId();

		cpRuleAssetCategoryRelCacheModel.groupId = getGroupId();

		cpRuleAssetCategoryRelCacheModel.companyId = getCompanyId();

		cpRuleAssetCategoryRelCacheModel.userId = getUserId();

		cpRuleAssetCategoryRelCacheModel.userName = getUserName();

		String userName = cpRuleAssetCategoryRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpRuleAssetCategoryRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpRuleAssetCategoryRelCacheModel.createDate = createDate.getTime();
		}
		else {
			cpRuleAssetCategoryRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpRuleAssetCategoryRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			cpRuleAssetCategoryRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpRuleAssetCategoryRelCacheModel.CPRuleId = getCPRuleId();

		cpRuleAssetCategoryRelCacheModel.assetCategoryId = getAssetCategoryId();

		return cpRuleAssetCategoryRelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{CPRuleAssetCategoryRelId=");
		sb.append(getCPRuleAssetCategoryRelId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", CPRuleId=");
		sb.append(getCPRuleId());
		sb.append(", assetCategoryId=");
		sb.append(getAssetCategoryId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.product.model.CPRuleAssetCategoryRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>CPRuleAssetCategoryRelId</column-name><column-value><![CDATA[");
		sb.append(getCPRuleAssetCategoryRelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>CPRuleId</column-name><column-value><![CDATA[");
		sb.append(getCPRuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetCategoryId</column-name><column-value><![CDATA[");
		sb.append(getAssetCategoryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CPRuleAssetCategoryRel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CPRuleAssetCategoryRel.class
		};
	private long _CPRuleAssetCategoryRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPRuleId;
	private long _originalCPRuleId;
	private boolean _setOriginalCPRuleId;
	private long _assetCategoryId;
	private long _originalAssetCategoryId;
	private boolean _setOriginalAssetCategoryId;
	private long _columnBitmask;
	private CPRuleAssetCategoryRel _escapedModel;
}