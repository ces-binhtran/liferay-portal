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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLink;
import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceLinkModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMDataProviderInstanceLink service. Represents a row in the &quot;DDMDataProviderInstanceLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMDataProviderInstanceLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMDataProviderInstanceLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMDataProviderInstanceLinkImpl
 * @generated
 */
public class DDMDataProviderInstanceLinkModelImpl
	extends BaseModelImpl<DDMDataProviderInstanceLink>
	implements DDMDataProviderInstanceLinkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm data provider instance link model instance should use the <code>DDMDataProviderInstanceLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMDataProviderInstanceLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"dataProviderInstanceLinkId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"dataProviderInstanceId", Types.BIGINT},
		{"structureId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dataProviderInstanceLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dataProviderInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMDataProviderInstanceLink (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,dataProviderInstanceLinkId LONG not null,companyId LONG,dataProviderInstanceId LONG,structureId LONG,primary key (dataProviderInstanceLinkId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DDMDataProviderInstanceLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmDataProviderInstanceLink.dataProviderInstanceLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMDataProviderInstanceLink.dataProviderInstanceLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DATAPROVIDERINSTANCEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STRUCTUREID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DATAPROVIDERINSTANCELINKID_COLUMN_BITMASK = 4L;

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

	public DDMDataProviderInstanceLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dataProviderInstanceLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDataProviderInstanceLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dataProviderInstanceLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMDataProviderInstanceLink.class;
	}

	@Override
	public String getModelClassName() {
		return DDMDataProviderInstanceLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMDataProviderInstanceLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMDataProviderInstanceLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMDataProviderInstanceLink, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(DDMDataProviderInstanceLink)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMDataProviderInstanceLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMDataProviderInstanceLink, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMDataProviderInstanceLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMDataProviderInstanceLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMDataProviderInstanceLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMDataProviderInstanceLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMDataProviderInstanceLink.class.getClassLoader(),
			DDMDataProviderInstanceLink.class, ModelWrapper.class);

		try {
			Constructor<DDMDataProviderInstanceLink> constructor =
				(Constructor<DDMDataProviderInstanceLink>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<DDMDataProviderInstanceLink, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<DDMDataProviderInstanceLink, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMDataProviderInstanceLink, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DDMDataProviderInstanceLink, Object>>();
		Map<String, BiConsumer<DDMDataProviderInstanceLink, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<DDMDataProviderInstanceLink, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDMDataProviderInstanceLink::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMDataProviderInstanceLink, Long>)
				DDMDataProviderInstanceLink::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMDataProviderInstanceLink::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMDataProviderInstanceLink, Long>)
				DDMDataProviderInstanceLink::setCtCollectionId);
		attributeGetterFunctions.put(
			"dataProviderInstanceLinkId",
			DDMDataProviderInstanceLink::getDataProviderInstanceLinkId);
		attributeSetterBiConsumers.put(
			"dataProviderInstanceLinkId",
			(BiConsumer<DDMDataProviderInstanceLink, Long>)
				DDMDataProviderInstanceLink::setDataProviderInstanceLinkId);
		attributeGetterFunctions.put(
			"companyId", DDMDataProviderInstanceLink::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMDataProviderInstanceLink, Long>)
				DDMDataProviderInstanceLink::setCompanyId);
		attributeGetterFunctions.put(
			"dataProviderInstanceId",
			DDMDataProviderInstanceLink::getDataProviderInstanceId);
		attributeSetterBiConsumers.put(
			"dataProviderInstanceId",
			(BiConsumer<DDMDataProviderInstanceLink, Long>)
				DDMDataProviderInstanceLink::setDataProviderInstanceId);
		attributeGetterFunctions.put(
			"structureId", DDMDataProviderInstanceLink::getStructureId);
		attributeSetterBiConsumers.put(
			"structureId",
			(BiConsumer<DDMDataProviderInstanceLink, Long>)
				DDMDataProviderInstanceLink::setStructureId);

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

	@Override
	public long getDataProviderInstanceLinkId() {
		return _dataProviderInstanceLinkId;
	}

	@Override
	public void setDataProviderInstanceLinkId(long dataProviderInstanceLinkId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dataProviderInstanceLinkId = dataProviderInstanceLinkId;
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
	public long getDataProviderInstanceId() {
		return _dataProviderInstanceId;
	}

	@Override
	public void setDataProviderInstanceId(long dataProviderInstanceId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dataProviderInstanceId = dataProviderInstanceId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalDataProviderInstanceId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("dataProviderInstanceId"));
	}

	@Override
	public long getStructureId() {
		return _structureId;
	}

	@Override
	public void setStructureId(long structureId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_structureId = structureId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalStructureId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("structureId"));
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
			getCompanyId(), DDMDataProviderInstanceLink.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMDataProviderInstanceLink toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMDataProviderInstanceLink>
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
		DDMDataProviderInstanceLinkImpl ddmDataProviderInstanceLinkImpl =
			new DDMDataProviderInstanceLinkImpl();

		ddmDataProviderInstanceLinkImpl.setMvccVersion(getMvccVersion());
		ddmDataProviderInstanceLinkImpl.setCtCollectionId(getCtCollectionId());
		ddmDataProviderInstanceLinkImpl.setDataProviderInstanceLinkId(
			getDataProviderInstanceLinkId());
		ddmDataProviderInstanceLinkImpl.setCompanyId(getCompanyId());
		ddmDataProviderInstanceLinkImpl.setDataProviderInstanceId(
			getDataProviderInstanceId());
		ddmDataProviderInstanceLinkImpl.setStructureId(getStructureId());

		ddmDataProviderInstanceLinkImpl.resetOriginalValues();

		return ddmDataProviderInstanceLinkImpl;
	}

	@Override
	public int compareTo(
		DDMDataProviderInstanceLink ddmDataProviderInstanceLink) {

		long primaryKey = ddmDataProviderInstanceLink.getPrimaryKey();

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

		if (!(object instanceof DDMDataProviderInstanceLink)) {
			return false;
		}

		DDMDataProviderInstanceLink ddmDataProviderInstanceLink =
			(DDMDataProviderInstanceLink)object;

		long primaryKey = ddmDataProviderInstanceLink.getPrimaryKey();

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
	public CacheModel<DDMDataProviderInstanceLink> toCacheModel() {
		DDMDataProviderInstanceLinkCacheModel
			ddmDataProviderInstanceLinkCacheModel =
				new DDMDataProviderInstanceLinkCacheModel();

		ddmDataProviderInstanceLinkCacheModel.mvccVersion = getMvccVersion();

		ddmDataProviderInstanceLinkCacheModel.ctCollectionId =
			getCtCollectionId();

		ddmDataProviderInstanceLinkCacheModel.dataProviderInstanceLinkId =
			getDataProviderInstanceLinkId();

		ddmDataProviderInstanceLinkCacheModel.companyId = getCompanyId();

		ddmDataProviderInstanceLinkCacheModel.dataProviderInstanceId =
			getDataProviderInstanceId();

		ddmDataProviderInstanceLinkCacheModel.structureId = getStructureId();

		return ddmDataProviderInstanceLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMDataProviderInstanceLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMDataProviderInstanceLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMDataProviderInstanceLink, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(DDMDataProviderInstanceLink)this));
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
		Map<String, Function<DDMDataProviderInstanceLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMDataProviderInstanceLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMDataProviderInstanceLink, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(DDMDataProviderInstanceLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, DDMDataProviderInstanceLink>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _dataProviderInstanceLinkId;
	private long _companyId;
	private long _dataProviderInstanceId;
	private long _structureId;

	public <T> T getColumnValue(String columnName) {
		Function<DDMDataProviderInstanceLink, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DDMDataProviderInstanceLink)this);
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
		_columnOriginalValues.put(
			"dataProviderInstanceLinkId", _dataProviderInstanceLinkId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put(
			"dataProviderInstanceId", _dataProviderInstanceId);
		_columnOriginalValues.put("structureId", _structureId);
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

		columnBitmasks.put("dataProviderInstanceLinkId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("dataProviderInstanceId", 16L);

		columnBitmasks.put("structureId", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DDMDataProviderInstanceLink _escapedModel;

}