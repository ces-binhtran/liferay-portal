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

import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStorageLinkModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMStorageLink service. Represents a row in the &quot;DDMStorageLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMStorageLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMStorageLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStorageLinkImpl
 * @generated
 */
public class DDMStorageLinkModelImpl
	extends BaseModelImpl<DDMStorageLink> implements DDMStorageLinkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm storage link model instance should use the <code>DDMStorageLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMStorageLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"storageLinkId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"structureId", Types.BIGINT},
		{"structureVersionId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("storageLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureVersionId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMStorageLink (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,storageLinkId LONG not null,companyId LONG,classNameId LONG,classPK LONG,structureId LONG,structureVersionId LONG,primary key (storageLinkId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table DDMStorageLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmStorageLink.storageLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMStorageLink.storageLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STRUCTUREID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STRUCTUREVERSIONID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STORAGELINKID_COLUMN_BITMASK = 32L;

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

	public DDMStorageLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _storageLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStorageLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _storageLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMStorageLink.class;
	}

	@Override
	public String getModelClassName() {
		return DDMStorageLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMStorageLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStorageLink, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMStorageLink)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMStorageLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMStorageLink, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMStorageLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMStorageLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMStorageLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMStorageLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMStorageLink.class.getClassLoader(), DDMStorageLink.class,
			ModelWrapper.class);

		try {
			Constructor<DDMStorageLink> constructor =
				(Constructor<DDMStorageLink>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DDMStorageLink, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMStorageLink, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<DDMStorageLink, Object>>();
		Map<String, BiConsumer<DDMStorageLink, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DDMStorageLink, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDMStorageLink::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMStorageLink, Long>)DDMStorageLink::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMStorageLink::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMStorageLink, Long>)
				DDMStorageLink::setCtCollectionId);
		attributeGetterFunctions.put("uuid", DDMStorageLink::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<DDMStorageLink, String>)DDMStorageLink::setUuid);
		attributeGetterFunctions.put(
			"storageLinkId", DDMStorageLink::getStorageLinkId);
		attributeSetterBiConsumers.put(
			"storageLinkId",
			(BiConsumer<DDMStorageLink, Long>)DDMStorageLink::setStorageLinkId);
		attributeGetterFunctions.put("companyId", DDMStorageLink::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMStorageLink, Long>)DDMStorageLink::setCompanyId);
		attributeGetterFunctions.put(
			"classNameId", DDMStorageLink::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<DDMStorageLink, Long>)DDMStorageLink::setClassNameId);
		attributeGetterFunctions.put("classPK", DDMStorageLink::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<DDMStorageLink, Long>)DDMStorageLink::setClassPK);
		attributeGetterFunctions.put(
			"structureId", DDMStorageLink::getStructureId);
		attributeSetterBiConsumers.put(
			"structureId",
			(BiConsumer<DDMStorageLink, Long>)DDMStorageLink::setStructureId);
		attributeGetterFunctions.put(
			"structureVersionId", DDMStorageLink::getStructureVersionId);
		attributeSetterBiConsumers.put(
			"structureVersionId",
			(BiConsumer<DDMStorageLink, Long>)
				DDMStorageLink::setStructureVersionId);

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
	public long getStorageLinkId() {
		return _storageLinkId;
	}

	@Override
	public void setStorageLinkId(long storageLinkId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_storageLinkId = storageLinkId;
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

	@Override
	public long getStructureVersionId() {
		return _structureVersionId;
	}

	@Override
	public void setStructureVersionId(long structureVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_structureVersionId = structureVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalStructureVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("structureVersionId"));
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
			getCompanyId(), DDMStorageLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMStorageLink toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMStorageLink>
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
		DDMStorageLinkImpl ddmStorageLinkImpl = new DDMStorageLinkImpl();

		ddmStorageLinkImpl.setMvccVersion(getMvccVersion());
		ddmStorageLinkImpl.setCtCollectionId(getCtCollectionId());
		ddmStorageLinkImpl.setUuid(getUuid());
		ddmStorageLinkImpl.setStorageLinkId(getStorageLinkId());
		ddmStorageLinkImpl.setCompanyId(getCompanyId());
		ddmStorageLinkImpl.setClassNameId(getClassNameId());
		ddmStorageLinkImpl.setClassPK(getClassPK());
		ddmStorageLinkImpl.setStructureId(getStructureId());
		ddmStorageLinkImpl.setStructureVersionId(getStructureVersionId());

		ddmStorageLinkImpl.resetOriginalValues();

		return ddmStorageLinkImpl;
	}

	@Override
	public int compareTo(DDMStorageLink ddmStorageLink) {
		long primaryKey = ddmStorageLink.getPrimaryKey();

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

		if (!(object instanceof DDMStorageLink)) {
			return false;
		}

		DDMStorageLink ddmStorageLink = (DDMStorageLink)object;

		long primaryKey = ddmStorageLink.getPrimaryKey();

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
	public CacheModel<DDMStorageLink> toCacheModel() {
		DDMStorageLinkCacheModel ddmStorageLinkCacheModel =
			new DDMStorageLinkCacheModel();

		ddmStorageLinkCacheModel.mvccVersion = getMvccVersion();

		ddmStorageLinkCacheModel.ctCollectionId = getCtCollectionId();

		ddmStorageLinkCacheModel.uuid = getUuid();

		String uuid = ddmStorageLinkCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmStorageLinkCacheModel.uuid = null;
		}

		ddmStorageLinkCacheModel.storageLinkId = getStorageLinkId();

		ddmStorageLinkCacheModel.companyId = getCompanyId();

		ddmStorageLinkCacheModel.classNameId = getClassNameId();

		ddmStorageLinkCacheModel.classPK = getClassPK();

		ddmStorageLinkCacheModel.structureId = getStructureId();

		ddmStorageLinkCacheModel.structureVersionId = getStructureVersionId();

		return ddmStorageLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMStorageLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStorageLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DDMStorageLink)this));
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
		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMStorageLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStorageLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DDMStorageLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDMStorageLink>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _storageLinkId;
	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private long _structureId;
	private long _structureVersionId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DDMStorageLink, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DDMStorageLink)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("storageLinkId", _storageLinkId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("structureId", _structureId);
		_columnOriginalValues.put("structureVersionId", _structureVersionId);
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

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("storageLinkId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("classNameId", 32L);

		columnBitmasks.put("classPK", 64L);

		columnBitmasks.put("structureId", 128L);

		columnBitmasks.put("structureVersionId", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DDMStorageLink _escapedModel;

}