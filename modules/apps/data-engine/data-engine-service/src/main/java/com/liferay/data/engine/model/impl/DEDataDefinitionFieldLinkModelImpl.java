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
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
		"create table DEDataDefinitionFieldLink (uuid_ VARCHAR(75) null,deDataDefinitionFieldLinkId LONG not null primary key,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,ddmStructureId LONG,fieldName VARCHAR(75) null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table DEDataDefinitionFieldLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY deDataDefinitionFieldLink.deDataDefinitionFieldLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DEDataDefinitionFieldLink.deDataDefinitionFieldLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long DDMSTRUCTUREID_COLUMN_BITMASK = 8L;

	public static final long FIELDNAME_COLUMN_BITMASK = 16L;

	public static final long GROUPID_COLUMN_BITMASK = 32L;

	public static final long UUID_COLUMN_BITMASK = 64L;

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
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getDeDataDefinitionFieldLinkId() {
		return _deDataDefinitionFieldLinkId;
	}

	@Override
	public void setDeDataDefinitionFieldLinkId(
		long deDataDefinitionFieldLinkId) {

		_deDataDefinitionFieldLinkId = deDataDefinitionFieldLinkId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public long getDdmStructureId() {
		return _ddmStructureId;
	}

	@Override
	public void setDdmStructureId(long ddmStructureId) {
		_columnBitmask |= DDMSTRUCTUREID_COLUMN_BITMASK;

		if (!_setOriginalDdmStructureId) {
			_setOriginalDdmStructureId = true;

			_originalDdmStructureId = _ddmStructureId;
		}

		_ddmStructureId = ddmStructureId;
	}

	public long getOriginalDdmStructureId() {
		return _originalDdmStructureId;
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
		_columnBitmask |= FIELDNAME_COLUMN_BITMASK;

		if (_originalFieldName == null) {
			_originalFieldName = _fieldName;
		}

		_fieldName = fieldName;
	}

	public String getOriginalFieldName() {
		return GetterUtil.getString(_originalFieldName);
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
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
		DEDataDefinitionFieldLinkModelImpl deDataDefinitionFieldLinkModelImpl =
			this;

		deDataDefinitionFieldLinkModelImpl._originalUuid =
			deDataDefinitionFieldLinkModelImpl._uuid;

		deDataDefinitionFieldLinkModelImpl._originalGroupId =
			deDataDefinitionFieldLinkModelImpl._groupId;

		deDataDefinitionFieldLinkModelImpl._setOriginalGroupId = false;

		deDataDefinitionFieldLinkModelImpl._originalCompanyId =
			deDataDefinitionFieldLinkModelImpl._companyId;

		deDataDefinitionFieldLinkModelImpl._setOriginalCompanyId = false;

		deDataDefinitionFieldLinkModelImpl._setModifiedDate = false;

		deDataDefinitionFieldLinkModelImpl._originalClassNameId =
			deDataDefinitionFieldLinkModelImpl._classNameId;

		deDataDefinitionFieldLinkModelImpl._setOriginalClassNameId = false;

		deDataDefinitionFieldLinkModelImpl._originalClassPK =
			deDataDefinitionFieldLinkModelImpl._classPK;

		deDataDefinitionFieldLinkModelImpl._setOriginalClassPK = false;

		deDataDefinitionFieldLinkModelImpl._originalDdmStructureId =
			deDataDefinitionFieldLinkModelImpl._ddmStructureId;

		deDataDefinitionFieldLinkModelImpl._setOriginalDdmStructureId = false;

		deDataDefinitionFieldLinkModelImpl._originalFieldName =
			deDataDefinitionFieldLinkModelImpl._fieldName;

		deDataDefinitionFieldLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DEDataDefinitionFieldLink> toCacheModel() {
		DEDataDefinitionFieldLinkCacheModel
			deDataDefinitionFieldLinkCacheModel =
				new DEDataDefinitionFieldLinkCacheModel();

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
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DEDataDefinitionFieldLink, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DEDataDefinitionFieldLink, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((DEDataDefinitionFieldLink)this));
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
			5 * attributeGetterFunctions.size() + 4);

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

	private String _uuid;
	private String _originalUuid;
	private long _deDataDefinitionFieldLinkId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _ddmStructureId;
	private long _originalDdmStructureId;
	private boolean _setOriginalDdmStructureId;
	private String _fieldName;
	private String _originalFieldName;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private DEDataDefinitionFieldLink _escapedModel;

}