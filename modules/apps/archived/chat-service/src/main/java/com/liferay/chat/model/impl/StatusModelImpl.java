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

package com.liferay.chat.model.impl;

import com.liferay.chat.model.Status;
import com.liferay.chat.model.StatusModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Status service. Represents a row in the &quot;Chat_Status&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>StatusModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StatusImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StatusImpl
 * @generated
 */
public class StatusModelImpl
	extends BaseModelImpl<Status> implements StatusModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a status model instance should use the <code>Status</code> interface instead.
	 */
	public static final String TABLE_NAME = "Chat_Status";

	public static final Object[][] TABLE_COLUMNS = {
		{"statusId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"modifiedDate", Types.BIGINT}, {"online_", Types.BOOLEAN},
		{"awake", Types.BOOLEAN}, {"activePanelIds", Types.VARCHAR},
		{"message", Types.VARCHAR}, {"playSound", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("statusId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("online_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("awake", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("activePanelIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("message", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("playSound", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Chat_Status (statusId LONG not null primary key,userId LONG,modifiedDate LONG,online_ BOOLEAN,awake BOOLEAN,activePanelIds STRING null,message STRING null,playSound BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table Chat_Status";

	public static final String ORDER_BY_JPQL = " ORDER BY status.statusId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Chat_Status.statusId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ONLINE_COLUMN_BITMASK = 2L;

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
	public static final long STATUSID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public StatusModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _statusId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStatusId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _statusId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Status.class;
	}

	@Override
	public String getModelClassName() {
		return Status.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Status, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Status, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Status, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Status)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Status, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Status, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Status)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Status, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Status, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Status>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Status.class.getClassLoader(), Status.class, ModelWrapper.class);

		try {
			Constructor<Status> constructor =
				(Constructor<Status>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Status, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Status, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Status, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Status, Object>>();
		Map<String, BiConsumer<Status, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Status, ?>>();

		attributeGetterFunctions.put("statusId", Status::getStatusId);
		attributeSetterBiConsumers.put(
			"statusId", (BiConsumer<Status, Long>)Status::setStatusId);
		attributeGetterFunctions.put("userId", Status::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Status, Long>)Status::setUserId);
		attributeGetterFunctions.put("modifiedDate", Status::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Status, Long>)Status::setModifiedDate);
		attributeGetterFunctions.put("online", Status::getOnline);
		attributeSetterBiConsumers.put(
			"online", (BiConsumer<Status, Boolean>)Status::setOnline);
		attributeGetterFunctions.put("awake", Status::getAwake);
		attributeSetterBiConsumers.put(
			"awake", (BiConsumer<Status, Boolean>)Status::setAwake);
		attributeGetterFunctions.put(
			"activePanelIds", Status::getActivePanelIds);
		attributeSetterBiConsumers.put(
			"activePanelIds",
			(BiConsumer<Status, String>)Status::setActivePanelIds);
		attributeGetterFunctions.put("message", Status::getMessage);
		attributeSetterBiConsumers.put(
			"message", (BiConsumer<Status, String>)Status::setMessage);
		attributeGetterFunctions.put("playSound", Status::getPlaySound);
		attributeSetterBiConsumers.put(
			"playSound", (BiConsumer<Status, Boolean>)Status::setPlaySound);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusId = statusId;
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
	public long getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(long modifiedDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalModifiedDate() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("modifiedDate"));
	}

	@Override
	public boolean getOnline() {
		return _online;
	}

	@Override
	public boolean isOnline() {
		return _online;
	}

	@Override
	public void setOnline(boolean online) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_online = online;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalOnline() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("online_"));
	}

	@Override
	public boolean getAwake() {
		return _awake;
	}

	@Override
	public boolean isAwake() {
		return _awake;
	}

	@Override
	public void setAwake(boolean awake) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_awake = awake;
	}

	@Override
	public String getActivePanelIds() {
		if (_activePanelIds == null) {
			return "";
		}
		else {
			return _activePanelIds;
		}
	}

	@Override
	public void setActivePanelIds(String activePanelIds) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_activePanelIds = activePanelIds;
	}

	@Override
	public String getMessage() {
		if (_message == null) {
			return "";
		}
		else {
			return _message;
		}
	}

	@Override
	public void setMessage(String message) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_message = message;
	}

	@Override
	public boolean getPlaySound() {
		return _playSound;
	}

	@Override
	public boolean isPlaySound() {
		return _playSound;
	}

	@Override
	public void setPlaySound(boolean playSound) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_playSound = playSound;
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

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Status.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Status toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Status>
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
		StatusImpl statusImpl = new StatusImpl();

		statusImpl.setStatusId(getStatusId());
		statusImpl.setUserId(getUserId());
		statusImpl.setModifiedDate(getModifiedDate());
		statusImpl.setOnline(isOnline());
		statusImpl.setAwake(isAwake());
		statusImpl.setActivePanelIds(getActivePanelIds());
		statusImpl.setMessage(getMessage());
		statusImpl.setPlaySound(isPlaySound());

		statusImpl.resetOriginalValues();

		return statusImpl;
	}

	@Override
	public int compareTo(Status status) {
		long primaryKey = status.getPrimaryKey();

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

		if (!(object instanceof Status)) {
			return false;
		}

		Status status = (Status)object;

		long primaryKey = status.getPrimaryKey();

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
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Status> toCacheModel() {
		StatusCacheModel statusCacheModel = new StatusCacheModel();

		statusCacheModel.statusId = getStatusId();

		statusCacheModel.userId = getUserId();

		statusCacheModel.modifiedDate = getModifiedDate();

		statusCacheModel.online = isOnline();

		statusCacheModel.awake = isAwake();

		statusCacheModel.activePanelIds = getActivePanelIds();

		String activePanelIds = statusCacheModel.activePanelIds;

		if ((activePanelIds != null) && (activePanelIds.length() == 0)) {
			statusCacheModel.activePanelIds = null;
		}

		statusCacheModel.message = getMessage();

		String message = statusCacheModel.message;

		if ((message != null) && (message.length() == 0)) {
			statusCacheModel.message = null;
		}

		statusCacheModel.playSound = isPlaySound();

		return statusCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Status, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Status, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Status, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Status)this));
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
		Map<String, Function<Status, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Status, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Status, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Status)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Status>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _statusId;
	private long _userId;
	private long _modifiedDate;
	private boolean _online;
	private boolean _awake;
	private String _activePanelIds;
	private String _message;
	private boolean _playSound;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Status, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Status)this);
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

		_columnOriginalValues.put("statusId", _statusId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("online_", _online);
		_columnOriginalValues.put("awake", _awake);
		_columnOriginalValues.put("activePanelIds", _activePanelIds);
		_columnOriginalValues.put("message", _message);
		_columnOriginalValues.put("playSound", _playSound);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("online_", "online");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("statusId", 1L);

		columnBitmasks.put("userId", 2L);

		columnBitmasks.put("modifiedDate", 4L);

		columnBitmasks.put("online_", 8L);

		columnBitmasks.put("awake", 16L);

		columnBitmasks.put("activePanelIds", 32L);

		columnBitmasks.put("message", 64L);

		columnBitmasks.put("playSound", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Status _escapedModel;

}