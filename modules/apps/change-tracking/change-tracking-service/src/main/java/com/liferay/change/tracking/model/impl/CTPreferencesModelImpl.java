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

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTPreferences;
import com.liferay.change.tracking.model.CTPreferencesModel;
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
 * The base model implementation for the CTPreferences service. Represents a row in the &quot;CTPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CTPreferencesModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTPreferencesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTPreferencesImpl
 * @generated
 */
public class CTPreferencesModelImpl
	extends BaseModelImpl<CTPreferences> implements CTPreferencesModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct preferences model instance should use the <code>CTPreferences</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTPreferences";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctPreferencesId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"ctCollectionId", Types.BIGINT},
		{"previousCtCollectionId", Types.BIGINT},
		{"confirmationEnabled", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctPreferencesId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("previousCtCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("confirmationEnabled", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTPreferences (mvccVersion LONG default 0 not null,ctPreferencesId LONG not null primary key,companyId LONG,userId LONG,ctCollectionId LONG,previousCtCollectionId LONG,confirmationEnabled BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CTPreferences";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctPreferences.ctPreferencesId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CTPreferences.ctPreferencesId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CTCOLLECTIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PREVIOUSCTCOLLECTIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CTPREFERENCESID_COLUMN_BITMASK = 16L;

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

	public CTPreferencesModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ctPreferencesId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCtPreferencesId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ctPreferencesId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTPreferences.class;
	}

	@Override
	public String getModelClassName() {
		return CTPreferences.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTPreferences, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTPreferences, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTPreferences, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CTPreferences)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTPreferences, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTPreferences, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTPreferences)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTPreferences, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTPreferences, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CTPreferences>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CTPreferences.class.getClassLoader(), CTPreferences.class,
			ModelWrapper.class);

		try {
			Constructor<CTPreferences> constructor =
				(Constructor<CTPreferences>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CTPreferences, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CTPreferences, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CTPreferences, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CTPreferences, Object>>();
		Map<String, BiConsumer<CTPreferences, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CTPreferences, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CTPreferences::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CTPreferences, Long>)CTPreferences::setMvccVersion);
		attributeGetterFunctions.put(
			"ctPreferencesId", CTPreferences::getCtPreferencesId);
		attributeSetterBiConsumers.put(
			"ctPreferencesId",
			(BiConsumer<CTPreferences, Long>)CTPreferences::setCtPreferencesId);
		attributeGetterFunctions.put("companyId", CTPreferences::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CTPreferences, Long>)CTPreferences::setCompanyId);
		attributeGetterFunctions.put("userId", CTPreferences::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CTPreferences, Long>)CTPreferences::setUserId);
		attributeGetterFunctions.put(
			"ctCollectionId", CTPreferences::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CTPreferences, Long>)CTPreferences::setCtCollectionId);
		attributeGetterFunctions.put(
			"previousCtCollectionId", CTPreferences::getPreviousCtCollectionId);
		attributeSetterBiConsumers.put(
			"previousCtCollectionId",
			(BiConsumer<CTPreferences, Long>)
				CTPreferences::setPreviousCtCollectionId);
		attributeGetterFunctions.put(
			"confirmationEnabled", CTPreferences::getConfirmationEnabled);
		attributeSetterBiConsumers.put(
			"confirmationEnabled",
			(BiConsumer<CTPreferences, Boolean>)
				CTPreferences::setConfirmationEnabled);

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
	public long getCtPreferencesId() {
		return _ctPreferencesId;
	}

	@Override
	public void setCtPreferencesId(long ctPreferencesId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctPreferencesId = ctPreferencesId;
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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCtCollectionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
	}

	@Override
	public long getPreviousCtCollectionId() {
		return _previousCtCollectionId;
	}

	@Override
	public void setPreviousCtCollectionId(long previousCtCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_previousCtCollectionId = previousCtCollectionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalPreviousCtCollectionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("previousCtCollectionId"));
	}

	@Override
	public boolean getConfirmationEnabled() {
		return _confirmationEnabled;
	}

	@Override
	public boolean isConfirmationEnabled() {
		return _confirmationEnabled;
	}

	@Override
	public void setConfirmationEnabled(boolean confirmationEnabled) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_confirmationEnabled = confirmationEnabled;
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
			getCompanyId(), CTPreferences.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTPreferences toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTPreferences>
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
		CTPreferencesImpl ctPreferencesImpl = new CTPreferencesImpl();

		ctPreferencesImpl.setMvccVersion(getMvccVersion());
		ctPreferencesImpl.setCtPreferencesId(getCtPreferencesId());
		ctPreferencesImpl.setCompanyId(getCompanyId());
		ctPreferencesImpl.setUserId(getUserId());
		ctPreferencesImpl.setCtCollectionId(getCtCollectionId());
		ctPreferencesImpl.setPreviousCtCollectionId(
			getPreviousCtCollectionId());
		ctPreferencesImpl.setConfirmationEnabled(isConfirmationEnabled());

		ctPreferencesImpl.resetOriginalValues();

		return ctPreferencesImpl;
	}

	@Override
	public int compareTo(CTPreferences ctPreferences) {
		long primaryKey = ctPreferences.getPrimaryKey();

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

		if (!(object instanceof CTPreferences)) {
			return false;
		}

		CTPreferences ctPreferences = (CTPreferences)object;

		long primaryKey = ctPreferences.getPrimaryKey();

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
	public CacheModel<CTPreferences> toCacheModel() {
		CTPreferencesCacheModel ctPreferencesCacheModel =
			new CTPreferencesCacheModel();

		ctPreferencesCacheModel.mvccVersion = getMvccVersion();

		ctPreferencesCacheModel.ctPreferencesId = getCtPreferencesId();

		ctPreferencesCacheModel.companyId = getCompanyId();

		ctPreferencesCacheModel.userId = getUserId();

		ctPreferencesCacheModel.ctCollectionId = getCtCollectionId();

		ctPreferencesCacheModel.previousCtCollectionId =
			getPreviousCtCollectionId();

		ctPreferencesCacheModel.confirmationEnabled = isConfirmationEnabled();

		return ctPreferencesCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTPreferences, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTPreferences, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTPreferences, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CTPreferences)this));
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
		Map<String, Function<CTPreferences, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CTPreferences, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTPreferences, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CTPreferences)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CTPreferences>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctPreferencesId;
	private long _companyId;
	private long _userId;
	private long _ctCollectionId;
	private long _previousCtCollectionId;
	private boolean _confirmationEnabled;

	public <T> T getColumnValue(String columnName) {
		Function<CTPreferences, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CTPreferences)this);
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
		_columnOriginalValues.put("ctPreferencesId", _ctPreferencesId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put(
			"previousCtCollectionId", _previousCtCollectionId);
		_columnOriginalValues.put("confirmationEnabled", _confirmationEnabled);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctPreferencesId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("ctCollectionId", 16L);

		columnBitmasks.put("previousCtCollectionId", 32L);

		columnBitmasks.put("confirmationEnabled", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CTPreferences _escapedModel;

}