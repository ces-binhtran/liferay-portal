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

package com.liferay.data.engine.model.impl;

import com.liferay.data.engine.model.DEDataDefinitionFieldLink;
import com.liferay.data.engine.model.DEDataDefinitionFieldLinkModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DEDataDefinitionFieldLink service. Represents a row in the &quot;DEDataDefinitionFieldLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DEDataDefinitionFieldLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DEDataDefinitionFieldLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DEDataDefinitionFieldLinkImpl
 * @generated
 */
public class DEDataDefinitionFieldLinkModelImpl
	extends BaseModelImpl<DEDataDefinitionFieldLink>
	implements DEDataDefinitionFieldLinkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a de data definition field link model instance should use the <code>DEDataDefinitionFieldLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "DEDataDefinitionFieldLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"deDataDefinitionFieldLinkId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"ddmStructureId", Types.BIGINT}, {"fieldName", Types.VARCHAR},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("deDataDefinitionFieldLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ddmStructureId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fieldName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DEDataDefinitionFieldLink (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,deDataDefinitionFieldLinkId LONG not null,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,ddmStructureId LONG,fieldName VARCHAR(255) null,lastPublishDate DATE null,primary key (deDataDefinitionFieldLinkId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DEDataDefinitionFieldLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY deDataDefinitionFieldLink.deDataDefinitionFieldLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DEDataDefinitionFieldLink.deDataDefinitionFieldLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

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
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DDMSTRUCTUREID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FIELDNAME_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DEDATADEFINITIONFIELDLINKID_COLUMN_BITMASK = 128L;

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

	public DEDataDefinitionFieldLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _deDataDefinitionFieldLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDeDataDefinitionFieldLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deDataDefinitionFieldLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DEDataDefinitionFieldLink.class;
	}

	@Override
	public String getModelClassName() {
		return DEDataDefinitionFieldLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DEDataDefinitionFieldLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DEDataDefinitionFieldLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DEDataDefinitionFieldLink, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DEDataDefinitionFieldLink)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DEDataDefinitionFieldLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DEDataDefinitionFieldLink, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DEDataDefinitionFieldLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DEDataDefinitionFieldLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DEDataDefinitionFieldLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DEDataDefinitionFieldLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DEDataDefinitionFieldLink.class.getClassLoader(),
			DEDataDefinitionFieldLink.class, ModelWrapper.class);

		try {
			Constructor<DEDataDefinitionFieldLink> constructor =
				(Constructor<DEDataDefinitionFieldLink>)
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
		<String, Function<DEDataDefinitionFieldLink, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<DEDataDefinitionFieldLink, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<DEDataDefinitionFieldLink, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DEDataDefinitionFieldLink, Object>>();
		Map<String, BiConsumer<DEDataDefinitionFieldLink, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<DEDataDefinitionFieldLink, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DEDataDefinitionFieldLink::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DEDataDefinitionFieldLink::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setCtCollectionId);
		attributeGetterFunctions.put(
			"uuid", DEDataDefinitionFieldLink::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<DEDataDefinitionFieldLink, String>)
				DEDataDefinitionFieldLink::setUuid);
		attributeGetterFunctions.put(
			"deDataDefinitionFieldLinkId",
			DEDataDefinitionFieldLink::getDeDataDefinitionFieldLinkId);
		attributeSetterBiConsumers.put(
			"deDataDefinitionFieldLinkId",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setDeDataDefinitionFieldLinkId);
		attributeGetterFunctions.put(
			"groupId", DEDataDefinitionFieldLink::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DEDataDefinitionFieldLink::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setCompanyId);
		attributeGetterFunctions.put(
			"createDate", DEDataDefinitionFieldLink::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DEDataDefinitionFieldLink, Date>)
				DEDataDefinitionFieldLink::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DEDataDefinitionFieldLink::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DEDataDefinitionFieldLink, Date>)
				DEDataDefinitionFieldLink::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", DEDataDefinitionFieldLink::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", DEDataDefinitionFieldLink::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setClassPK);
		attributeGetterFunctions.put(
			"ddmStructureId", DEDataDefinitionFieldLink::getDdmStructureId);
		attributeSetterBiConsumers.put(
			"ddmStructureId",
			(BiConsumer<DEDataDefinitionFieldLink, Long>)
				DEDataDefinitionFieldLink::setDdmStructureId);
		attributeGetterFunctions.put(
			"fieldName", DEDataDefinitionFieldLink::getFieldName);
		attributeSetterBiConsumers.put(
			"fieldName",
			(BiConsumer<DEDataDefinitionFieldLink, String>)
				DEDataDefinitionFieldLink::setFieldName);
		attributeGetterFunctions.put(
			"lastPublishDate", DEDataDefinitionFieldLink::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<DEDataDefinitionFieldLink, Date>)
				DEDataDefinitionFieldLink::setLastPublishDate);

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
	public long getDeDataDefinitionFieldLinkId() {
		return _deDataDefinitionFieldLinkId;
	}

	@Override
	public void setDeDataDefinitionFieldLinkId(
		long deDataDefinitionFieldLinkId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_deDataDefinitionFieldLinkId = deDataDefinitionFieldLinkId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
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
	public long getDdmStructureId() {
		return _ddmStructureId;
	}

	@Override
	public void setDdmStructureId(long ddmStructureId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ddmStructureId = ddmStructureId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalDdmStructureId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("ddmStructureId"));
	}

	@Override
	public String getFieldName() {
		if (_fieldName == null) {
			return "";
		}
		else {
			return _fieldName;
		}
	}

	@Override
	public void setFieldName(String fieldName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fieldName = fieldName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalFieldName() {
		return getColumnOriginalValue("fieldName");
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				DEDataDefinitionFieldLink.class.getName()),
			getClassNameId());
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
			getCompanyId(), DEDataDefinitionFieldLink.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DEDataDefinitionFieldLink toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DEDataDefinitionFieldLink>
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
		DEDataDefinitionFieldLinkImpl deDataDefinitionFieldLinkImpl =
			new DEDataDefinitionFieldLinkImpl();

		deDataDefinitionFieldLinkImpl.setMvccVersion(getMvccVersion());
		deDataDefinitionFieldLinkImpl.setCtCollectionId(getCtCollectionId());
		deDataDefinitionFieldLinkImpl.setUuid(getUuid());
		deDataDefinitionFieldLinkImpl.setDeDataDefinitionFieldLinkId(
			getDeDataDefinitionFieldLinkId());
		deDataDefinitionFieldLinkImpl.setGroupId(getGroupId());
		deDataDefinitionFieldLinkImpl.setCompanyId(getCompanyId());
		deDataDefinitionFieldLinkImpl.setCreateDate(getCreateDate());
		deDataDefinitionFieldLinkImpl.setModifiedDate(getModifiedDate());
		deDataDefinitionFieldLinkImpl.setClassNameId(getClassNameId());
		deDataDefinitionFieldLinkImpl.setClassPK(getClassPK());
		deDataDefinitionFieldLinkImpl.setDdmStructureId(getDdmStructureId());
		deDataDefinitionFieldLinkImpl.setFieldName(getFieldName());
		deDataDefinitionFieldLinkImpl.setLastPublishDate(getLastPublishDate());

		deDataDefinitionFieldLinkImpl.resetOriginalValues();

		return deDataDefinitionFieldLinkImpl;
	}

	@Override
	public DEDataDefinitionFieldLink cloneWithOriginalValues() {
		DEDataDefinitionFieldLinkImpl deDataDefinitionFieldLinkImpl =
			new DEDataDefinitionFieldLinkImpl();

		deDataDefinitionFieldLinkImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		deDataDefinitionFieldLinkImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		deDataDefinitionFieldLinkImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		deDataDefinitionFieldLinkImpl.setDeDataDefinitionFieldLinkId(
			this.<Long>getColumnOriginalValue("deDataDefinitionFieldLinkId"));
		deDataDefinitionFieldLinkImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		deDataDefinitionFieldLinkImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		deDataDefinitionFieldLinkImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		deDataDefinitionFieldLinkImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		deDataDefinitionFieldLinkImpl.setClassNameId(
			this.<Long>getColumnOriginalValue("classNameId"));
		deDataDefinitionFieldLinkImpl.setClassPK(
			this.<Long>getColumnOriginalValue("classPK"));
		deDataDefinitionFieldLinkImpl.setDdmStructureId(
			this.<Long>getColumnOriginalValue("ddmStructureId"));
		deDataDefinitionFieldLinkImpl.setFieldName(
			this.<String>getColumnOriginalValue("fieldName"));
		deDataDefinitionFieldLinkImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return deDataDefinitionFieldLinkImpl;
	}

	@Override
	public int compareTo(DEDataDefinitionFieldLink deDataDefinitionFieldLink) {
		long primaryKey = deDataDefinitionFieldLink.getPrimaryKey();

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

		if (!(object instanceof DEDataDefinitionFieldLink)) {
			return false;
		}

		DEDataDefinitionFieldLink deDataDefinitionFieldLink =
			(DEDataDefinitionFieldLink)object;

		long primaryKey = deDataDefinitionFieldLink.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<DEDataDefinitionFieldLink> toCacheModel() {
		DEDataDefinitionFieldLinkCacheModel
			deDataDefinitionFieldLinkCacheModel =
				new DEDataDefinitionFieldLinkCacheModel();

		deDataDefinitionFieldLinkCacheModel.mvccVersion = getMvccVersion();

		deDataDefinitionFieldLinkCacheModel.ctCollectionId =
			getCtCollectionId();

		deDataDefinitionFieldLinkCacheModel.uuid = getUuid();

		String uuid = deDataDefinitionFieldLinkCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			deDataDefinitionFieldLinkCacheModel.uuid = null;
		}

		deDataDefinitionFieldLinkCacheModel.deDataDefinitionFieldLinkId =
			getDeDataDefinitionFieldLinkId();

		deDataDefinitionFieldLinkCacheModel.groupId = getGroupId();

		deDataDefinitionFieldLinkCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			deDataDefinitionFieldLinkCacheModel.createDate =
				createDate.getTime();
		}
		else {
			deDataDefinitionFieldLinkCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			deDataDefinitionFieldLinkCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			deDataDefinitionFieldLinkCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		deDataDefinitionFieldLinkCacheModel.classNameId = getClassNameId();

		deDataDefinitionFieldLinkCacheModel.classPK = getClassPK();

		deDataDefinitionFieldLinkCacheModel.ddmStructureId =
			getDdmStructureId();

		deDataDefinitionFieldLinkCacheModel.fieldName = getFieldName();

		String fieldName = deDataDefinitionFieldLinkCacheModel.fieldName;

		if ((fieldName != null) && (fieldName.length() == 0)) {
			deDataDefinitionFieldLinkCacheModel.fieldName = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			deDataDefinitionFieldLinkCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			deDataDefinitionFieldLinkCacheModel.lastPublishDate =
				Long.MIN_VALUE;
		}

		return deDataDefinitionFieldLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DEDataDefinitionFieldLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DEDataDefinitionFieldLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DEDataDefinitionFieldLink, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(DEDataDefinitionFieldLink)this);

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
		Map<String, Function<DEDataDefinitionFieldLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DEDataDefinitionFieldLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DEDataDefinitionFieldLink, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((DEDataDefinitionFieldLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, DEDataDefinitionFieldLink>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _deDataDefinitionFieldLinkId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _ddmStructureId;
	private String _fieldName;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DEDataDefinitionFieldLink, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DEDataDefinitionFieldLink)this);
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
		_columnOriginalValues.put(
			"deDataDefinitionFieldLinkId", _deDataDefinitionFieldLinkId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("ddmStructureId", _ddmStructureId);
		_columnOriginalValues.put("fieldName", _fieldName);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
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

		columnBitmasks.put("deDataDefinitionFieldLinkId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("classNameId", 256L);

		columnBitmasks.put("classPK", 512L);

		columnBitmasks.put("ddmStructureId", 1024L);

		columnBitmasks.put("fieldName", 2048L);

		columnBitmasks.put("lastPublishDate", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DEDataDefinitionFieldLink _escapedModel;

}