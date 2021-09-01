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

package com.liferay.marketplace.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.model.ModuleModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the Module service. Represents a row in the &quot;Marketplace_Module&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ModuleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ModuleImpl}.
 * </p>
 *
 * @author Ryan Park
 * @see ModuleImpl
 * @generated
 */
public class ModuleModelImpl
	extends BaseModelImpl<Module> implements ModuleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a module model instance should use the <code>Module</code> interface instead.
	 */
	public static final String TABLE_NAME = "Marketplace_Module";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"moduleId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"appId", Types.BIGINT},
		{"bundleSymbolicName", Types.VARCHAR}, {"bundleVersion", Types.VARCHAR},
		{"contextName", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("moduleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("appId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("bundleSymbolicName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bundleVersion", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contextName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Marketplace_Module (uuid_ VARCHAR(75) null,moduleId LONG not null primary key,companyId LONG,appId LONG,bundleSymbolicName VARCHAR(500) null,bundleVersion VARCHAR(75) null,contextName VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Marketplace_Module";

	public static final String ORDER_BY_JPQL = " ORDER BY module.moduleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Marketplace_Module.moduleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long APPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long BUNDLESYMBOLICNAME_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long BUNDLEVERSION_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CONTEXTNAME_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODULEID_COLUMN_BITMASK = 64L;

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

	public ModuleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _moduleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setModuleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _moduleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Module.class;
	}

	@Override
	public String getModelClassName() {
		return Module.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Module, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Module, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Module, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Module)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Module, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Module, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Module)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Module, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Module, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Module>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Module.class.getClassLoader(), Module.class, ModelWrapper.class);

		try {
			Constructor<Module> constructor =
				(Constructor<Module>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Module, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Module, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Module, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Module, Object>>();
		Map<String, BiConsumer<Module, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Module, ?>>();

		attributeGetterFunctions.put("uuid", Module::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Module, String>)Module::setUuid);
		attributeGetterFunctions.put("moduleId", Module::getModuleId);
		attributeSetterBiConsumers.put(
			"moduleId", (BiConsumer<Module, Long>)Module::setModuleId);
		attributeGetterFunctions.put("companyId", Module::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Module, Long>)Module::setCompanyId);
		attributeGetterFunctions.put("appId", Module::getAppId);
		attributeSetterBiConsumers.put(
			"appId", (BiConsumer<Module, Long>)Module::setAppId);
		attributeGetterFunctions.put(
			"bundleSymbolicName", Module::getBundleSymbolicName);
		attributeSetterBiConsumers.put(
			"bundleSymbolicName",
			(BiConsumer<Module, String>)Module::setBundleSymbolicName);
		attributeGetterFunctions.put("bundleVersion", Module::getBundleVersion);
		attributeSetterBiConsumers.put(
			"bundleVersion",
			(BiConsumer<Module, String>)Module::setBundleVersion);
		attributeGetterFunctions.put("contextName", Module::getContextName);
		attributeSetterBiConsumers.put(
			"contextName", (BiConsumer<Module, String>)Module::setContextName);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@Override
	public long getModuleId() {
		return _moduleId;
	}

	@Override
	public void setModuleId(long moduleId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_moduleId = moduleId;
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
	public long getAppId() {
		return _appId;
	}

	@Override
	public void setAppId(long appId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_appId = appId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAppId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("appId"));
	}

	@Override
	public String getBundleSymbolicName() {
		if (_bundleSymbolicName == null) {
			return "";
		}
		else {
			return _bundleSymbolicName;
		}
	}

	@Override
	public void setBundleSymbolicName(String bundleSymbolicName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bundleSymbolicName = bundleSymbolicName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalBundleSymbolicName() {
		return getColumnOriginalValue("bundleSymbolicName");
	}

	@Override
	public String getBundleVersion() {
		if (_bundleVersion == null) {
			return "";
		}
		else {
			return _bundleVersion;
		}
	}

	@Override
	public void setBundleVersion(String bundleVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bundleVersion = bundleVersion;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalBundleVersion() {
		return getColumnOriginalValue("bundleVersion");
	}

	@Override
	public String getContextName() {
		if (_contextName == null) {
			return "";
		}
		else {
			return _contextName;
		}
	}

	@Override
	public void setContextName(String contextName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_contextName = contextName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalContextName() {
		return getColumnOriginalValue("contextName");
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
			getCompanyId(), Module.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Module toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Module>
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
		ModuleImpl moduleImpl = new ModuleImpl();

		moduleImpl.setUuid(getUuid());
		moduleImpl.setModuleId(getModuleId());
		moduleImpl.setCompanyId(getCompanyId());
		moduleImpl.setAppId(getAppId());
		moduleImpl.setBundleSymbolicName(getBundleSymbolicName());
		moduleImpl.setBundleVersion(getBundleVersion());
		moduleImpl.setContextName(getContextName());

		moduleImpl.resetOriginalValues();

		return moduleImpl;
	}

	@Override
	public int compareTo(Module module) {
		long primaryKey = module.getPrimaryKey();

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

		if (!(object instanceof Module)) {
			return false;
		}

		Module module = (Module)object;

		long primaryKey = module.getPrimaryKey();

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
	public CacheModel<Module> toCacheModel() {
		ModuleCacheModel moduleCacheModel = new ModuleCacheModel();

		moduleCacheModel.uuid = getUuid();

		String uuid = moduleCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			moduleCacheModel.uuid = null;
		}

		moduleCacheModel.moduleId = getModuleId();

		moduleCacheModel.companyId = getCompanyId();

		moduleCacheModel.appId = getAppId();

		moduleCacheModel.bundleSymbolicName = getBundleSymbolicName();

		String bundleSymbolicName = moduleCacheModel.bundleSymbolicName;

		if ((bundleSymbolicName != null) &&
			(bundleSymbolicName.length() == 0)) {

			moduleCacheModel.bundleSymbolicName = null;
		}

		moduleCacheModel.bundleVersion = getBundleVersion();

		String bundleVersion = moduleCacheModel.bundleVersion;

		if ((bundleVersion != null) && (bundleVersion.length() == 0)) {
			moduleCacheModel.bundleVersion = null;
		}

		moduleCacheModel.contextName = getContextName();

		String contextName = moduleCacheModel.contextName;

		if ((contextName != null) && (contextName.length() == 0)) {
			moduleCacheModel.contextName = null;
		}

		return moduleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Module, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Module, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Module, Object> attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Module)this);

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
		Map<String, Function<Module, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Module, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Module, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Module)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Module>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _moduleId;
	private long _companyId;
	private long _appId;
	private String _bundleSymbolicName;
	private String _bundleVersion;
	private String _contextName;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Module, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Module)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("moduleId", _moduleId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("appId", _appId);
		_columnOriginalValues.put("bundleSymbolicName", _bundleSymbolicName);
		_columnOriginalValues.put("bundleVersion", _bundleVersion);
		_columnOriginalValues.put("contextName", _contextName);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("moduleId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("appId", 8L);

		columnBitmasks.put("bundleSymbolicName", 16L);

		columnBitmasks.put("bundleVersion", 32L);

		columnBitmasks.put("contextName", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Module _escapedModel;

}