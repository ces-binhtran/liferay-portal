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

import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.model.ExpandoValueModel;
import com.liferay.expando.kernel.model.ExpandoValueSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the ExpandoValue service. Represents a row in the &quot;ExpandoValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ExpandoValueModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExpandoValueImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoValueImpl
 * @generated
 */
@JSON(strict = true)
public class ExpandoValueModelImpl
	extends BaseModelImpl<ExpandoValue> implements ExpandoValueModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a expando value model instance should use the <code>ExpandoValue</code> interface instead.
	 */
	public static final String TABLE_NAME = "ExpandoValue";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"valueId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"tableId", Types.BIGINT}, {"columnId", Types.BIGINT},
		{"rowId_", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"data_", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("valueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("tableId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("columnId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("rowId_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("data_", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ExpandoValue (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,valueId LONG not null,companyId LONG,tableId LONG,columnId LONG,rowId_ LONG,classNameId LONG,classPK LONG,data_ TEXT null,primary key (valueId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table ExpandoValue";

	public static final String ORDER_BY_JPQL =
		" ORDER BY expandoValue.tableId ASC, expandoValue.rowId ASC, expandoValue.columnId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ExpandoValue.tableId ASC, ExpandoValue.rowId_ ASC, ExpandoValue.columnId ASC";

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
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COLUMNID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DATA_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ROWID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TABLEID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static ExpandoValue toModel(ExpandoValueSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ExpandoValue model = new ExpandoValueImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setValueId(soapModel.getValueId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setTableId(soapModel.getTableId());
		model.setColumnId(soapModel.getColumnId());
		model.setRowId(soapModel.getRowId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setData(soapModel.getData());

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
	public static List<ExpandoValue> toModels(ExpandoValueSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ExpandoValue> models = new ArrayList<ExpandoValue>(
			soapModels.length);

		for (ExpandoValueSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.expando.kernel.model.ExpandoValue"));

	public ExpandoValueModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _valueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setValueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _valueId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ExpandoValue.class;
	}

	@Override
	public String getModelClassName() {
		return ExpandoValue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ExpandoValue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ExpandoValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoValue, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ExpandoValue)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ExpandoValue, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ExpandoValue, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ExpandoValue)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ExpandoValue, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ExpandoValue, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ExpandoValue>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ExpandoValue.class.getClassLoader(), ExpandoValue.class,
			ModelWrapper.class);

		try {
			Constructor<ExpandoValue> constructor =
				(Constructor<ExpandoValue>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ExpandoValue, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ExpandoValue, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ExpandoValue, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ExpandoValue, Object>>();
		Map<String, BiConsumer<ExpandoValue, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ExpandoValue, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ExpandoValue::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", ExpandoValue::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setCtCollectionId);
		attributeGetterFunctions.put("valueId", ExpandoValue::getValueId);
		attributeSetterBiConsumers.put(
			"valueId",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setValueId);
		attributeGetterFunctions.put("companyId", ExpandoValue::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setCompanyId);
		attributeGetterFunctions.put("tableId", ExpandoValue::getTableId);
		attributeSetterBiConsumers.put(
			"tableId",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setTableId);
		attributeGetterFunctions.put("columnId", ExpandoValue::getColumnId);
		attributeSetterBiConsumers.put(
			"columnId",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setColumnId);
		attributeGetterFunctions.put("rowId", ExpandoValue::getRowId);
		attributeSetterBiConsumers.put(
			"rowId", (BiConsumer<ExpandoValue, Long>)ExpandoValue::setRowId);
		attributeGetterFunctions.put(
			"classNameId", ExpandoValue::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setClassNameId);
		attributeGetterFunctions.put("classPK", ExpandoValue::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<ExpandoValue, Long>)ExpandoValue::setClassPK);
		attributeGetterFunctions.put("data", ExpandoValue::getData);
		attributeSetterBiConsumers.put(
			"data", (BiConsumer<ExpandoValue, String>)ExpandoValue::setData);

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
	public long getValueId() {
		return _valueId;
	}

	@Override
	public void setValueId(long valueId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_valueId = valueId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalColumnId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("columnId"));
	}

	@JSON
	@Override
	public long getRowId() {
		return _rowId;
	}

	@Override
	public void setRowId(long rowId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_rowId = rowId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRowId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("rowId_"));
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@JSON
	@Override
	public String getData() {
		if (_data == null) {
			return "";
		}
		else {
			return _data;
		}
	}

	@Override
	public void setData(String data) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_data = data;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalData() {
		return getColumnOriginalValue("data_");
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
	public ExpandoValue toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ExpandoValue>
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
		ExpandoValueImpl expandoValueImpl = new ExpandoValueImpl();

		expandoValueImpl.setMvccVersion(getMvccVersion());
		expandoValueImpl.setCtCollectionId(getCtCollectionId());
		expandoValueImpl.setValueId(getValueId());
		expandoValueImpl.setCompanyId(getCompanyId());
		expandoValueImpl.setTableId(getTableId());
		expandoValueImpl.setColumnId(getColumnId());
		expandoValueImpl.setRowId(getRowId());
		expandoValueImpl.setClassNameId(getClassNameId());
		expandoValueImpl.setClassPK(getClassPK());
		expandoValueImpl.setData(getData());

		expandoValueImpl.resetOriginalValues();

		return expandoValueImpl;
	}

	@Override
	public int compareTo(ExpandoValue expandoValue) {
		int value = 0;

		if (getTableId() < expandoValue.getTableId()) {
			value = -1;
		}
		else if (getTableId() > expandoValue.getTableId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getRowId() < expandoValue.getRowId()) {
			value = -1;
		}
		else if (getRowId() > expandoValue.getRowId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getColumnId() < expandoValue.getColumnId()) {
			value = -1;
		}
		else if (getColumnId() > expandoValue.getColumnId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof ExpandoValue)) {
			return false;
		}

		ExpandoValue expandoValue = (ExpandoValue)object;

		long primaryKey = expandoValue.getPrimaryKey();

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
	public CacheModel<ExpandoValue> toCacheModel() {
		ExpandoValueCacheModel expandoValueCacheModel =
			new ExpandoValueCacheModel();

		expandoValueCacheModel.mvccVersion = getMvccVersion();

		expandoValueCacheModel.ctCollectionId = getCtCollectionId();

		expandoValueCacheModel.valueId = getValueId();

		expandoValueCacheModel.companyId = getCompanyId();

		expandoValueCacheModel.tableId = getTableId();

		expandoValueCacheModel.columnId = getColumnId();

		expandoValueCacheModel.rowId = getRowId();

		expandoValueCacheModel.classNameId = getClassNameId();

		expandoValueCacheModel.classPK = getClassPK();

		expandoValueCacheModel.data = getData();

		String data = expandoValueCacheModel.data;

		if ((data != null) && (data.length() == 0)) {
			expandoValueCacheModel.data = null;
		}

		return expandoValueCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ExpandoValue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ExpandoValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoValue, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((ExpandoValue)this);

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
		Map<String, Function<ExpandoValue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ExpandoValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ExpandoValue, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ExpandoValue)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ExpandoValue>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _valueId;
	private long _companyId;
	private long _tableId;
	private long _columnId;
	private long _rowId;
	private long _classNameId;
	private long _classPK;
	private String _data;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<ExpandoValue, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ExpandoValue)this);
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
		_columnOriginalValues.put("valueId", _valueId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("tableId", _tableId);
		_columnOriginalValues.put("columnId", _columnId);
		_columnOriginalValues.put("rowId_", _rowId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("data_", _data);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("rowId_", "rowId");
		attributeNames.put("data_", "data");

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

		columnBitmasks.put("valueId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("tableId", 16L);

		columnBitmasks.put("columnId", 32L);

		columnBitmasks.put("rowId_", 64L);

		columnBitmasks.put("classNameId", 128L);

		columnBitmasks.put("classPK", 256L);

		columnBitmasks.put("data_", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ExpandoValue _escapedModel;

}