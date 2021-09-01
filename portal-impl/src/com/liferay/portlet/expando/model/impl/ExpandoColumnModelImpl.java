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

package com.liferay.portlet.expando.model.impl;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnModel;
import com.liferay.expando.kernel.model.ExpandoColumnSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ExpandoColumn service. Represents a row in the &quot;ExpandoColumn&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ExpandoColumnModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExpandoColumnImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoColumnImpl
 * @generated
 */
@JSON(strict = true)
public class ExpandoColumnModelImpl
	extends BaseModelImpl<ExpandoColumn> implements ExpandoColumnModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a expando column model instance should use the <code>ExpandoColumn</code> interface instead.
	 */
	public static final String TABLE_NAME = "ExpandoColumn";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"columnId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"tableId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"type_", Types.INTEGER}, {"defaultData", Types.CLOB},
		{"typeSettings", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("columnId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("tableId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("defaultData", Types.CLOB);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ExpandoColumn (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,columnId LONG not null,companyId LONG,tableId LONG,name VARCHAR(75) null,type_ INTEGER,defaultData TEXT null,typeSettings TEXT null,primary key (columnId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table ExpandoColumn";

	public static final String ORDER_BY_JPQL =
		" ORDER BY expandoColumn.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ExpandoColumn.name ASC";

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
	public static final long NAME_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TABLEID_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static ExpandoColumn toModel(ExpandoColumnSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ExpandoColumn model = new ExpandoColumnImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setColumnId(soapModel.getColumnId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setTableId(soapModel.getTableId());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setDefaultData(soapModel.getDefaultData());
		model.setTypeSettings(soapModel.getTypeSettings());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<ExpandoColumn> toModels(ExpandoColumnSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ExpandoColumn> models = new ArrayList<ExpandoColumn>(
			soapModels.length);

		for (ExpandoColumnSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.expando.kernel.model.ExpandoColumn"));

	public ExpandoColumnModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _columnId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setColumnId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _columnId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ExpandoColumn.class;
	}

	@Override
	public String getModelClassName() {
		return ExpandoColumn.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ExpandoColumn, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ExpandoColumn, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoColumn, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ExpandoColumn)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ExpandoColumn, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ExpandoColumn, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ExpandoColumn)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ExpandoColumn, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ExpandoColumn, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ExpandoColumn>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ExpandoColumn.class.getClassLoader(), ExpandoColumn.class,
			ModelWrapper.class);

		try {
			Constructor<ExpandoColumn> constructor =
				(Constructor<ExpandoColumn>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ExpandoColumn, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ExpandoColumn, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ExpandoColumn, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ExpandoColumn, Object>>();
		Map<String, BiConsumer<ExpandoColumn, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ExpandoColumn, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ExpandoColumn::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ExpandoColumn, Long>)ExpandoColumn::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", ExpandoColumn::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<ExpandoColumn, Long>)ExpandoColumn::setCtCollectionId);
		attributeGetterFunctions.put("columnId", ExpandoColumn::getColumnId);
		attributeSetterBiConsumers.put(
			"columnId",
			(BiConsumer<ExpandoColumn, Long>)ExpandoColumn::setColumnId);
		attributeGetterFunctions.put("companyId", ExpandoColumn::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ExpandoColumn, Long>)ExpandoColumn::setCompanyId);
		attributeGetterFunctions.put("tableId", ExpandoColumn::getTableId);
		attributeSetterBiConsumers.put(
			"tableId",
			(BiConsumer<ExpandoColumn, Long>)ExpandoColumn::setTableId);
		attributeGetterFunctions.put("name", ExpandoColumn::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<ExpandoColumn, String>)ExpandoColumn::setName);
		attributeGetterFunctions.put("type", ExpandoColumn::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<ExpandoColumn, Integer>)ExpandoColumn::setType);
		attributeGetterFunctions.put(
			"defaultData", ExpandoColumn::getDefaultData);
		attributeSetterBiConsumers.put(
			"defaultData",
			(BiConsumer<ExpandoColumn, String>)ExpandoColumn::setDefaultData);
		attributeGetterFunctions.put(
			"typeSettings", ExpandoColumn::getTypeSettings);
		attributeSetterBiConsumers.put(
			"typeSettings",
			(BiConsumer<ExpandoColumn, String>)ExpandoColumn::setTypeSettings);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getColumnId() {
		return _columnId;
	}

	@Override
	public void setColumnId(long columnId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_columnId = columnId;
	}

	@JSON
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

	@JSON
	@Override
	public long getTableId() {
		return _tableId;
	}

	@Override
	public void setTableId(long tableId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tableId = tableId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTableId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("tableId"));
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@JSON
	@Override
	public String getDefaultData() {
		if (_defaultData == null) {
			return "";
		}
		else {
			return _defaultData;
		}
	}

	@Override
	public void setDefaultData(String defaultData) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_defaultData = defaultData;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_typeSettings = typeSettings;
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
	public ExpandoColumn toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ExpandoColumn>
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
		ExpandoColumnImpl expandoColumnImpl = new ExpandoColumnImpl();

		expandoColumnImpl.setMvccVersion(getMvccVersion());
		expandoColumnImpl.setCtCollectionId(getCtCollectionId());
		expandoColumnImpl.setColumnId(getColumnId());
		expandoColumnImpl.setCompanyId(getCompanyId());
		expandoColumnImpl.setTableId(getTableId());
		expandoColumnImpl.setName(getName());
		expandoColumnImpl.setType(getType());
		expandoColumnImpl.setDefaultData(getDefaultData());
		expandoColumnImpl.setTypeSettings(getTypeSettings());

		expandoColumnImpl.resetOriginalValues();

		return expandoColumnImpl;
	}

	@Override
	public int compareTo(ExpandoColumn expandoColumn) {
		int value = 0;

		value = getName().compareTo(expandoColumn.getName());

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

		if (!(object instanceof ExpandoColumn)) {
			return false;
		}

		ExpandoColumn expandoColumn = (ExpandoColumn)object;

		long primaryKey = expandoColumn.getPrimaryKey();

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
	public CacheModel<ExpandoColumn> toCacheModel() {
		ExpandoColumnCacheModel expandoColumnCacheModel =
			new ExpandoColumnCacheModel();

		expandoColumnCacheModel.mvccVersion = getMvccVersion();

		expandoColumnCacheModel.ctCollectionId = getCtCollectionId();

		expandoColumnCacheModel.columnId = getColumnId();

		expandoColumnCacheModel.companyId = getCompanyId();

		expandoColumnCacheModel.tableId = getTableId();

		expandoColumnCacheModel.name = getName();

		String name = expandoColumnCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			expandoColumnCacheModel.name = null;
		}

		expandoColumnCacheModel.type = getType();

		expandoColumnCacheModel.defaultData = getDefaultData();

		String defaultData = expandoColumnCacheModel.defaultData;

		if ((defaultData != null) && (defaultData.length() == 0)) {
			expandoColumnCacheModel.defaultData = null;
		}

		expandoColumnCacheModel.typeSettings = getTypeSettings();

		String typeSettings = expandoColumnCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			expandoColumnCacheModel.typeSettings = null;
		}

		return expandoColumnCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ExpandoColumn, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ExpandoColumn, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoColumn, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ExpandoColumn)this);

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
		Map<String, Function<ExpandoColumn, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ExpandoColumn, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoColumn, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ExpandoColumn)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ExpandoColumn>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _columnId;
	private long _companyId;
	private long _tableId;
	private String _name;
	private int _type;
	private String _defaultData;
	private String _typeSettings;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ExpandoColumn, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ExpandoColumn)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("columnId", _columnId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("tableId", _tableId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("defaultData", _defaultData);
		_columnOriginalValues.put("typeSettings", _typeSettings);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("type_", "type");

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

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("columnId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("tableId", 16L);

		columnBitmasks.put("name", 32L);

		columnBitmasks.put("type_", 64L);

		columnBitmasks.put("defaultData", 128L);

		columnBitmasks.put("typeSettings", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ExpandoColumn _escapedModel;

}