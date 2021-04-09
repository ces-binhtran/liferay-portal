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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.PasswordTracker;
import com.liferay.portal.kernel.model.PasswordTrackerModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the PasswordTracker service. Represents a row in the &quot;PasswordTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PasswordTrackerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PasswordTrackerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordTrackerImpl
 * @generated
 */
public class PasswordTrackerModelImpl
	extends BaseModelImpl<PasswordTracker> implements PasswordTrackerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a password tracker model instance should use the <code>PasswordTracker</code> interface instead.
	 */
	public static final String TABLE_NAME = "PasswordTracker";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"passwordTrackerId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"password_", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("passwordTrackerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("password_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PasswordTracker (mvccVersion LONG default 0 not null,passwordTrackerId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,password_ VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table PasswordTracker";

	public static final String ORDER_BY_JPQL =
		" ORDER BY passwordTracker.userId DESC, passwordTracker.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PasswordTracker.userId DESC, PasswordTracker.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.PasswordTracker"));

	public PasswordTrackerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _passwordTrackerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPasswordTrackerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _passwordTrackerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PasswordTracker.class;
	}

	@Override
	public String getModelClassName() {
		return PasswordTracker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PasswordTracker, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PasswordTracker, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PasswordTracker, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PasswordTracker)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PasswordTracker, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PasswordTracker, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PasswordTracker)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PasswordTracker, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PasswordTracker, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PasswordTracker>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PasswordTracker.class.getClassLoader(), PasswordTracker.class,
			ModelWrapper.class);

		try {
			Constructor<PasswordTracker> constructor =
				(Constructor<PasswordTracker>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<PasswordTracker, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PasswordTracker, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PasswordTracker, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<PasswordTracker, Object>>();
		Map<String, BiConsumer<PasswordTracker, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PasswordTracker, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", PasswordTracker::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<PasswordTracker, Long>)PasswordTracker::setMvccVersion);
		attributeGetterFunctions.put(
			"passwordTrackerId", PasswordTracker::getPasswordTrackerId);
		attributeSetterBiConsumers.put(
			"passwordTrackerId",
			(BiConsumer<PasswordTracker, Long>)
				PasswordTracker::setPasswordTrackerId);
		attributeGetterFunctions.put(
			"companyId", PasswordTracker::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<PasswordTracker, Long>)PasswordTracker::setCompanyId);
		attributeGetterFunctions.put("userId", PasswordTracker::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<PasswordTracker, Long>)PasswordTracker::setUserId);
		attributeGetterFunctions.put(
			"createDate", PasswordTracker::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<PasswordTracker, Date>)PasswordTracker::setCreateDate);
		attributeGetterFunctions.put("password", PasswordTracker::getPassword);
		attributeSetterBiConsumers.put(
			"password",
			(BiConsumer<PasswordTracker, String>)PasswordTracker::setPassword);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@Override
	public long getPasswordTrackerId() {
		return _passwordTrackerId;
	}

	@Override
	public void setPasswordTrackerId(long passwordTrackerId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_passwordTrackerId = passwordTrackerId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public String getPassword() {
		if (_password == null) {
			return "";
		}
		else {
			return _password;
		}
	}

	@Override
	public void setPassword(String password) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_password = password;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PasswordTracker.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PasswordTracker toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PasswordTracker>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PasswordTrackerImpl passwordTrackerImpl = new PasswordTrackerImpl();

		passwordTrackerImpl.setMvccVersion(getMvccVersion());
		passwordTrackerImpl.setPasswordTrackerId(getPasswordTrackerId());
		passwordTrackerImpl.setCompanyId(getCompanyId());
		passwordTrackerImpl.setUserId(getUserId());
		passwordTrackerImpl.setCreateDate(getCreateDate());
		passwordTrackerImpl.setPassword(getPassword());

		passwordTrackerImpl.resetOriginalValues();

		return passwordTrackerImpl;
	}

	@Override
	public int compareTo(PasswordTracker passwordTracker) {
		int value = 0;

		if (getUserId() < passwordTracker.getUserId()) {
			value = -1;
		}
		else if (getUserId() > passwordTracker.getUserId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(
			getCreateDate(), passwordTracker.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PasswordTracker)) {
			return false;
		}

		PasswordTracker passwordTracker = (PasswordTracker)object;

		long primaryKey = passwordTracker.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<PasswordTracker> toCacheModel() {
		PasswordTrackerCacheModel passwordTrackerCacheModel =
			new PasswordTrackerCacheModel();

		passwordTrackerCacheModel.mvccVersion = getMvccVersion();

		passwordTrackerCacheModel.passwordTrackerId = getPasswordTrackerId();

		passwordTrackerCacheModel.companyId = getCompanyId();

		passwordTrackerCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			passwordTrackerCacheModel.createDate = createDate.getTime();
		}
		else {
			passwordTrackerCacheModel.createDate = Long.MIN_VALUE;
		}

		passwordTrackerCacheModel.password = getPassword();

		String password = passwordTrackerCacheModel.password;

		if ((password != null) && (password.length() == 0)) {
			passwordTrackerCacheModel.password = null;
		}

		return passwordTrackerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PasswordTracker, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PasswordTracker, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PasswordTracker, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PasswordTracker)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<PasswordTracker, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PasswordTracker, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PasswordTracker, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PasswordTracker)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PasswordTracker>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _passwordTrackerId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private String _password;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<PasswordTracker, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((PasswordTracker)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("passwordTrackerId", _passwordTrackerId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("password_", _password);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("password_", "password");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("passwordTrackerId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("password_", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private PasswordTracker _escapedModel;

}