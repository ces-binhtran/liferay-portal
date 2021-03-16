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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.ClassNameModel;
import com.liferay.portal.kernel.model.ClassNameSoap;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ClassName service. Represents a row in the &quot;ClassName_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ClassNameModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ClassNameImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ClassNameImpl
 * @generated
 */
@JSON(strict = true)
public class ClassNameModelImpl
	extends BaseModelImpl<ClassName> implements ClassNameModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a class name model instance should use the <code>ClassName</code> interface instead.
	 */
	public static final String TABLE_NAME = "ClassName_";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ClassName_ (mvccVersion LONG default 0 not null,classNameId LONG not null primary key,value VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table ClassName_";

	public static final String ORDER_BY_JPQL =
		" ORDER BY className.classNameId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ClassName_.classNameId ASC";

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
	public static final long VALUE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static ClassName toModel(ClassNameSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ClassName model = new ClassNameImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setClassNameId(soapModel.getClassNameId());
		model.setValue(soapModel.getValue());

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
	public static List<ClassName> toModels(ClassNameSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ClassName> models = new ArrayList<ClassName>(soapModels.length);

		for (ClassNameSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.ClassName"));

	public ClassNameModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _classNameId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setClassNameId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _classNameId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ClassName.class;
	}

	@Override
	public String getModelClassName() {
		return ClassName.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ClassName, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ClassName, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ClassName, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((ClassName)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ClassName, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ClassName, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ClassName)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ClassName, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ClassName, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ClassName>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ClassName.class.getClassLoader(), ClassName.class,
			ModelWrapper.class);

		try {
			Constructor<ClassName> constructor =
				(Constructor<ClassName>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ClassName, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ClassName, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ClassName, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ClassName, Object>>();
		Map<String, BiConsumer<ClassName, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ClassName, ?>>();

		attributeGetterFunctions.put("mvccVersion", ClassName::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ClassName, Long>)ClassName::setMvccVersion);
		attributeGetterFunctions.put("classNameId", ClassName::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<ClassName, Long>)ClassName::setClassNameId);
		attributeGetterFunctions.put("value", ClassName::getValue);
		attributeSetterBiConsumers.put(
			"value", (BiConsumer<ClassName, String>)ClassName::setValue);

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

	@JSON
	@Override
	public String getValue() {
		if (_value == null) {
			return "";
		}
		else {
			return _value;
		}
	}

	@Override
	public void setValue(String value) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_value = value;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalValue() {
		return getColumnOriginalValue("value");
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
			0, ClassName.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ClassName toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ClassName>
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
		ClassNameImpl classNameImpl = new ClassNameImpl();

		classNameImpl.setMvccVersion(getMvccVersion());
		classNameImpl.setClassNameId(getClassNameId());
		classNameImpl.setValue(getValue());

		classNameImpl.resetOriginalValues();

		return classNameImpl;
	}

	@Override
	public int compareTo(ClassName className) {
		long primaryKey = className.getPrimaryKey();

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

		if (!(object instanceof ClassName)) {
			return false;
		}

		ClassName className = (ClassName)object;

		long primaryKey = className.getPrimaryKey();

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
	public CacheModel<ClassName> toCacheModel() {
		ClassNameCacheModel classNameCacheModel = new ClassNameCacheModel();

		classNameCacheModel.mvccVersion = getMvccVersion();

		classNameCacheModel.classNameId = getClassNameId();

		classNameCacheModel.value = getValue();

		String value = classNameCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			classNameCacheModel.value = null;
		}

		return classNameCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ClassName, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ClassName, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ClassName, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ClassName)this));
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
		Map<String, Function<ClassName, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ClassName, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ClassName, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ClassName)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ClassName>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _classNameId;
	private String _value;

	public <T> T getColumnValue(String columnName) {
		Function<ClassName, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((ClassName)this);
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
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("value", _value);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("classNameId", 2L);

		columnBitmasks.put("value", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private ClassName _escapedModel;

}