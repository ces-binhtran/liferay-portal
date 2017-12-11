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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceOrderPayment;
import com.liferay.commerce.model.CommerceOrderPaymentModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CommerceOrderPayment service. Represents a row in the &quot;CommerceOrderPayment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceOrderPaymentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderPaymentImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderPaymentImpl
 * @see CommerceOrderPayment
 * @see CommerceOrderPaymentModel
 * @generated
 */
@ProviderType
public class CommerceOrderPaymentModelImpl extends BaseModelImpl<CommerceOrderPayment>
	implements CommerceOrderPaymentModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order payment model instance should use the {@link CommerceOrderPayment} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceOrderPayment";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceOrderPaymentId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "commerceOrderId", Types.BIGINT },
			{ "commercePaymentMethodId", Types.BIGINT },
			{ "status", Types.INTEGER },
			{ "content", Types.CLOB }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceOrderPaymentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceOrderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commercePaymentMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("content", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceOrderPayment (commerceOrderPaymentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceOrderId LONG,commercePaymentMethodId LONG,status INTEGER,content TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table CommerceOrderPayment";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceOrderPayment.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceOrderPayment.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.model.CommerceOrderPayment"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.model.CommerceOrderPayment"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.model.CommerceOrderPayment"),
			true);
	public static final long COMMERCEORDERID_COLUMN_BITMASK = 1L;
	public static final long CREATEDATE_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.model.CommerceOrderPayment"));

	public CommerceOrderPaymentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceOrderPaymentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceOrderPaymentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceOrderPaymentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceOrderPayment.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderPayment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceOrderPaymentId", getCommerceOrderPaymentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commerceOrderId", getCommerceOrderId());
		attributes.put("commercePaymentMethodId", getCommercePaymentMethodId());
		attributes.put("status", getStatus());
		attributes.put("content", getContent());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceOrderPaymentId = (Long)attributes.get(
				"commerceOrderPaymentId");

		if (commerceOrderPaymentId != null) {
			setCommerceOrderPaymentId(commerceOrderPaymentId);
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

		Long commerceOrderId = (Long)attributes.get("commerceOrderId");

		if (commerceOrderId != null) {
			setCommerceOrderId(commerceOrderId);
		}

		Long commercePaymentMethodId = (Long)attributes.get(
				"commercePaymentMethodId");

		if (commercePaymentMethodId != null) {
			setCommercePaymentMethodId(commercePaymentMethodId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	@Override
	public long getCommerceOrderPaymentId() {
		return _commerceOrderPaymentId;
	}

	@Override
	public void setCommerceOrderPaymentId(long commerceOrderPaymentId) {
		_commerceOrderPaymentId = commerceOrderPaymentId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

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

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

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

	@Override
	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	@Override
	public void setCommerceOrderId(long commerceOrderId) {
		_columnBitmask |= COMMERCEORDERID_COLUMN_BITMASK;

		if (!_setOriginalCommerceOrderId) {
			_setOriginalCommerceOrderId = true;

			_originalCommerceOrderId = _commerceOrderId;
		}

		_commerceOrderId = commerceOrderId;
	}

	public long getOriginalCommerceOrderId() {
		return _originalCommerceOrderId;
	}

	@Override
	public long getCommercePaymentMethodId() {
		return _commercePaymentMethodId;
	}

	@Override
	public void setCommercePaymentMethodId(long commercePaymentMethodId) {
		_commercePaymentMethodId = commercePaymentMethodId;
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			CommerceOrderPayment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceOrderPayment toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceOrderPayment)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceOrderPaymentImpl commerceOrderPaymentImpl = new CommerceOrderPaymentImpl();

		commerceOrderPaymentImpl.setCommerceOrderPaymentId(getCommerceOrderPaymentId());
		commerceOrderPaymentImpl.setGroupId(getGroupId());
		commerceOrderPaymentImpl.setCompanyId(getCompanyId());
		commerceOrderPaymentImpl.setUserId(getUserId());
		commerceOrderPaymentImpl.setUserName(getUserName());
		commerceOrderPaymentImpl.setCreateDate(getCreateDate());
		commerceOrderPaymentImpl.setModifiedDate(getModifiedDate());
		commerceOrderPaymentImpl.setCommerceOrderId(getCommerceOrderId());
		commerceOrderPaymentImpl.setCommercePaymentMethodId(getCommercePaymentMethodId());
		commerceOrderPaymentImpl.setStatus(getStatus());
		commerceOrderPaymentImpl.setContent(getContent());

		commerceOrderPaymentImpl.resetOriginalValues();

		return commerceOrderPaymentImpl;
	}

	@Override
	public int compareTo(CommerceOrderPayment commerceOrderPayment) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				commerceOrderPayment.getCreateDate());

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

		if (!(obj instanceof CommerceOrderPayment)) {
			return false;
		}

		CommerceOrderPayment commerceOrderPayment = (CommerceOrderPayment)obj;

		long primaryKey = commerceOrderPayment.getPrimaryKey();

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
		CommerceOrderPaymentModelImpl commerceOrderPaymentModelImpl = this;

		commerceOrderPaymentModelImpl._setModifiedDate = false;

		commerceOrderPaymentModelImpl._originalCommerceOrderId = commerceOrderPaymentModelImpl._commerceOrderId;

		commerceOrderPaymentModelImpl._setOriginalCommerceOrderId = false;

		commerceOrderPaymentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceOrderPayment> toCacheModel() {
		CommerceOrderPaymentCacheModel commerceOrderPaymentCacheModel = new CommerceOrderPaymentCacheModel();

		commerceOrderPaymentCacheModel.commerceOrderPaymentId = getCommerceOrderPaymentId();

		commerceOrderPaymentCacheModel.groupId = getGroupId();

		commerceOrderPaymentCacheModel.companyId = getCompanyId();

		commerceOrderPaymentCacheModel.userId = getUserId();

		commerceOrderPaymentCacheModel.userName = getUserName();

		String userName = commerceOrderPaymentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceOrderPaymentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceOrderPaymentCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceOrderPaymentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceOrderPaymentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceOrderPaymentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceOrderPaymentCacheModel.commerceOrderId = getCommerceOrderId();

		commerceOrderPaymentCacheModel.commercePaymentMethodId = getCommercePaymentMethodId();

		commerceOrderPaymentCacheModel.status = getStatus();

		commerceOrderPaymentCacheModel.content = getContent();

		String content = commerceOrderPaymentCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			commerceOrderPaymentCacheModel.content = null;
		}

		return commerceOrderPaymentCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{commerceOrderPaymentId=");
		sb.append(getCommerceOrderPaymentId());
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
		sb.append(", commerceOrderId=");
		sb.append(getCommerceOrderId());
		sb.append(", commercePaymentMethodId=");
		sb.append(getCommercePaymentMethodId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", content=");
		sb.append(getContent());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.model.CommerceOrderPayment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceOrderPaymentId</column-name><column-value><![CDATA[");
		sb.append(getCommerceOrderPaymentId());
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
			"<column><column-name>commerceOrderId</column-name><column-value><![CDATA[");
		sb.append(getCommerceOrderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commercePaymentMethodId</column-name><column-value><![CDATA[");
		sb.append(getCommercePaymentMethodId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceOrderPayment.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceOrderPayment.class
		};
	private long _commerceOrderPaymentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceOrderId;
	private long _originalCommerceOrderId;
	private boolean _setOriginalCommerceOrderId;
	private long _commercePaymentMethodId;
	private int _status;
	private String _content;
	private long _columnBitmask;
	private CommerceOrderPayment _escapedModel;
}