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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserTracker;
import com.liferay.portal.kernel.model.UserTrackerModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
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
 * The base model implementation for the UserTracker service. Represents a row in the &quot;UserTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>UserTrackerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserTrackerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerImpl
 * @generated
 */
public class UserTrackerModelImpl
	extends BaseModelImpl<UserTracker> implements UserTrackerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user tracker model instance should use the <code>UserTracker</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserTracker";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"userTrackerId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"modifiedDate", Types.TIMESTAMP}, {"sessionId", Types.VARCHAR},
		{"remoteAddr", Types.VARCHAR}, {"remoteHost", Types.VARCHAR},
		{"userAgent", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userTrackerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sessionId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("remoteAddr", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("remoteHost", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userAgent", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserTracker (mvccVersion LONG default 0 not null,userTrackerId LONG not null primary key,companyId LONG,userId LONG,modifiedDate DATE null,sessionId VARCHAR(200) null,remoteAddr VARCHAR(75) null,remoteHost VARCHAR(75) null,userAgent VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table UserTracker";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userTracker.userTrackerId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserTracker.userTrackerId ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SESSIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERTRACKERID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserTracker"));

	public UserTrackerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userTrackerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserTrackerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userTrackerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserTracker.class;
	}

	@Override
	public String getModelClassName() {
		return UserTracker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserTracker, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserTracker, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserTracker, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserTracker)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserTracker, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserTracker, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserTracker)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserTracker, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserTracker, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserTracker>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserTracker.class.getClassLoader(), UserTracker.class,
			ModelWrapper.class);

		try {
			Constructor<UserTracker> constructor =
				(Constructor<UserTracker>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserTracker, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserTracker, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserTracker, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserTracker, Object>>();
		Map<String, BiConsumer<UserTracker, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserTracker, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", UserTracker::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<UserTracker, Long>)UserTracker::setMvccVersion);
		attributeGetterFunctions.put(
			"userTrackerId", UserTracker::getUserTrackerId);
		attributeSetterBiConsumers.put(
			"userTrackerId",
			(BiConsumer<UserTracker, Long>)UserTracker::setUserTrackerId);
		attributeGetterFunctions.put("companyId", UserTracker::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<UserTracker, Long>)UserTracker::setCompanyId);
		attributeGetterFunctions.put("userId", UserTracker::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<UserTracker, Long>)UserTracker::setUserId);
		attributeGetterFunctions.put(
			"modifiedDate", UserTracker::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<UserTracker, Date>)UserTracker::setModifiedDate);
		attributeGetterFunctions.put("sessionId", UserTracker::getSessionId);
		attributeSetterBiConsumers.put(
			"sessionId",
			(BiConsumer<UserTracker, String>)UserTracker::setSessionId);
		attributeGetterFunctions.put("remoteAddr", UserTracker::getRemoteAddr);
		attributeSetterBiConsumers.put(
			"remoteAddr",
			(BiConsumer<UserTracker, String>)UserTracker::setRemoteAddr);
		attributeGetterFunctions.put("remoteHost", UserTracker::getRemoteHost);
		attributeSetterBiConsumers.put(
			"remoteHost",
			(BiConsumer<UserTracker, String>)UserTracker::setRemoteHost);
		attributeGetterFunctions.put("userAgent", UserTracker::getUserAgent);
		attributeSetterBiConsumers.put(
			"userAgent",
			(BiConsumer<UserTracker, String>)UserTracker::setUserAgent);

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
	public long getUserTrackerId() {
		return _userTrackerId;
	}

	@Override
	public void setUserTrackerId(long userTrackerId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userTrackerId = userTrackerId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getSessionId() {
		if (_sessionId == null) {
			return "";
		}
		else {
			return _sessionId;
		}
	}

	@Override
	public void setSessionId(String sessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sessionId = sessionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalSessionId() {
		return getColumnOriginalValue("sessionId");
	}

	@Override
	public String getRemoteAddr() {
		if (_remoteAddr == null) {
			return "";
		}
		else {
			return _remoteAddr;
		}
	}

	@Override
	public void setRemoteAddr(String remoteAddr) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_remoteAddr = remoteAddr;
	}

	@Override
	public String getRemoteHost() {
		if (_remoteHost == null) {
			return "";
		}
		else {
			return _remoteHost;
		}
	}

	@Override
	public void setRemoteHost(String remoteHost) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_remoteHost = remoteHost;
	}

	@Override
	public String getUserAgent() {
		if (_userAgent == null) {
			return "";
		}
		else {
			return _userAgent;
		}
	}

	@Override
	public void setUserAgent(String userAgent) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userAgent = userAgent;
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
			getCompanyId(), UserTracker.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserTracker toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserTracker>
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
		UserTrackerImpl userTrackerImpl = new UserTrackerImpl();

		userTrackerImpl.setMvccVersion(getMvccVersion());
		userTrackerImpl.setUserTrackerId(getUserTrackerId());
		userTrackerImpl.setCompanyId(getCompanyId());
		userTrackerImpl.setUserId(getUserId());
		userTrackerImpl.setModifiedDate(getModifiedDate());
		userTrackerImpl.setSessionId(getSessionId());
		userTrackerImpl.setRemoteAddr(getRemoteAddr());
		userTrackerImpl.setRemoteHost(getRemoteHost());
		userTrackerImpl.setUserAgent(getUserAgent());

		userTrackerImpl.resetOriginalValues();

		return userTrackerImpl;
	}

	@Override
	public int compareTo(UserTracker userTracker) {
		long primaryKey = userTracker.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserTracker)) {
			return false;
		}

		UserTracker userTracker = (UserTracker)object;

		long primaryKey = userTracker.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<UserTracker> toCacheModel() {
		UserTrackerCacheModel userTrackerCacheModel =
			new UserTrackerCacheModel();

		userTrackerCacheModel.mvccVersion = getMvccVersion();

		userTrackerCacheModel.userTrackerId = getUserTrackerId();

		userTrackerCacheModel.companyId = getCompanyId();

		userTrackerCacheModel.userId = getUserId();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			userTrackerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			userTrackerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		userTrackerCacheModel.sessionId = getSessionId();

		String sessionId = userTrackerCacheModel.sessionId;

		if ((sessionId != null) && (sessionId.length() == 0)) {
			userTrackerCacheModel.sessionId = null;
		}

		userTrackerCacheModel.remoteAddr = getRemoteAddr();

		String remoteAddr = userTrackerCacheModel.remoteAddr;

		if ((remoteAddr != null) && (remoteAddr.length() == 0)) {
			userTrackerCacheModel.remoteAddr = null;
		}

		userTrackerCacheModel.remoteHost = getRemoteHost();

		String remoteHost = userTrackerCacheModel.remoteHost;

		if ((remoteHost != null) && (remoteHost.length() == 0)) {
			userTrackerCacheModel.remoteHost = null;
		}

		userTrackerCacheModel.userAgent = getUserAgent();

		String userAgent = userTrackerCacheModel.userAgent;

		if ((userAgent != null) && (userAgent.length() == 0)) {
			userTrackerCacheModel.userAgent = null;
		}

		return userTrackerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserTracker, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserTracker, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserTracker, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((UserTracker)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

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
		Map<String, Function<UserTracker, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserTracker, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserTracker, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserTracker)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserTracker>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _userTrackerId;
	private long _companyId;
	private long _userId;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _sessionId;
	private String _remoteAddr;
	private String _remoteHost;
	private String _userAgent;

	public <T> T getColumnValue(String columnName) {
		Function<UserTracker, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((UserTracker)this);
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
		_columnOriginalValues.put("userTrackerId", _userTrackerId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("sessionId", _sessionId);
		_columnOriginalValues.put("remoteAddr", _remoteAddr);
		_columnOriginalValues.put("remoteHost", _remoteHost);
		_columnOriginalValues.put("userAgent", _userAgent);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("userTrackerId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("modifiedDate", 16L);

		columnBitmasks.put("sessionId", 32L);

		columnBitmasks.put("remoteAddr", 64L);

		columnBitmasks.put("remoteHost", 128L);

		columnBitmasks.put("userAgent", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private UserTracker _escapedModel;

}