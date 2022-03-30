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

import com.liferay.change.tracking.model.CTEntry;
import com.liferay.change.tracking.model.CTEntryModel;
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
 * The base model implementation for the CTEntry service. Represents a row in the &quot;CTEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CTEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTEntryImpl
 * @generated
 */
public class CTEntryModelImpl
	extends BaseModelImpl<CTEntry> implements CTEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct entry model instance should use the <code>CTEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"ctCollectionId", Types.BIGINT}, {"modelClassNameId", Types.BIGINT},
		{"modelClassPK", Types.BIGINT}, {"modelMvccVersion", Types.BIGINT},
		{"changeType", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modelClassNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modelClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modelMvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("changeType", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTEntry (mvccVersion LONG default 0 not null,ctEntryId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,ctCollectionId LONG,modelClassNameId LONG,modelClassPK LONG,modelMvccVersion LONG,changeType INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table CTEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctEntry.ctEntryId ASC";

	public static final String ORDER_BY_SQL = " ORDER BY CTEntry.ctEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CTCOLLECTIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODELCLASSNAMEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODELCLASSPK_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CTENTRYID_COLUMN_BITMASK = 8L;

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

	public CTEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ctEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCtEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ctEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CTEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((CTEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTEntry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CTEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CTEntry.class.getClassLoader(), CTEntry.class, ModelWrapper.class);

		try {
			Constructor<CTEntry> constructor =
				(Constructor<CTEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CTEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CTEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CTEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CTEntry, Object>>();
		Map<String, BiConsumer<CTEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CTEntry, ?>>();

		attributeGetterFunctions.put("mvccVersion", CTEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion", (BiConsumer<CTEntry, Long>)CTEntry::setMvccVersion);
		attributeGetterFunctions.put("ctEntryId", CTEntry::getCtEntryId);
		attributeSetterBiConsumers.put(
			"ctEntryId", (BiConsumer<CTEntry, Long>)CTEntry::setCtEntryId);
		attributeGetterFunctions.put("companyId", CTEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<CTEntry, Long>)CTEntry::setCompanyId);
		attributeGetterFunctions.put("userId", CTEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<CTEntry, Long>)CTEntry::setUserId);
		attributeGetterFunctions.put("createDate", CTEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<CTEntry, Date>)CTEntry::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", CTEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CTEntry, Date>)CTEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"ctCollectionId", CTEntry::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CTEntry, Long>)CTEntry::setCtCollectionId);
		attributeGetterFunctions.put(
			"modelClassNameId", CTEntry::getModelClassNameId);
		attributeSetterBiConsumers.put(
			"modelClassNameId",
			(BiConsumer<CTEntry, Long>)CTEntry::setModelClassNameId);
		attributeGetterFunctions.put("modelClassPK", CTEntry::getModelClassPK);
		attributeSetterBiConsumers.put(
			"modelClassPK",
			(BiConsumer<CTEntry, Long>)CTEntry::setModelClassPK);
		attributeGetterFunctions.put(
			"modelMvccVersion", CTEntry::getModelMvccVersion);
		attributeSetterBiConsumers.put(
			"modelMvccVersion",
			(BiConsumer<CTEntry, Long>)CTEntry::setModelMvccVersion);
		attributeGetterFunctions.put("changeType", CTEntry::getChangeType);
		attributeSetterBiConsumers.put(
			"changeType", (BiConsumer<CTEntry, Integer>)CTEntry::setChangeType);

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
	public long getCtEntryId() {
		return _ctEntryId;
	}

	@Override
	public void setCtEntryId(long ctEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctEntryId = ctEntryId;
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
	public long getModelClassNameId() {
		return _modelClassNameId;
	}

	@Override
	public void setModelClassNameId(long modelClassNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modelClassNameId = modelClassNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalModelClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("modelClassNameId"));
	}

	@Override
	public long getModelClassPK() {
		return _modelClassPK;
	}

	@Override
	public void setModelClassPK(long modelClassPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modelClassPK = modelClassPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalModelClassPK() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("modelClassPK"));
	}

	@Override
	public long getModelMvccVersion() {
		return _modelMvccVersion;
	}

	@Override
	public void setModelMvccVersion(long modelMvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modelMvccVersion = modelMvccVersion;
	}

	@Override
	public int getChangeType() {
		return _changeType;
	}

	@Override
	public void setChangeType(int changeType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_changeType = changeType;
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
			getCompanyId(), CTEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTEntry>
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
		CTEntryImpl ctEntryImpl = new CTEntryImpl();

		ctEntryImpl.setMvccVersion(getMvccVersion());
		ctEntryImpl.setCtEntryId(getCtEntryId());
		ctEntryImpl.setCompanyId(getCompanyId());
		ctEntryImpl.setUserId(getUserId());
		ctEntryImpl.setCreateDate(getCreateDate());
		ctEntryImpl.setModifiedDate(getModifiedDate());
		ctEntryImpl.setCtCollectionId(getCtCollectionId());
		ctEntryImpl.setModelClassNameId(getModelClassNameId());
		ctEntryImpl.setModelClassPK(getModelClassPK());
		ctEntryImpl.setModelMvccVersion(getModelMvccVersion());
		ctEntryImpl.setChangeType(getChangeType());

		ctEntryImpl.resetOriginalValues();

		return ctEntryImpl;
	}

	@Override
	public CTEntry cloneWithOriginalValues() {
		CTEntryImpl ctEntryImpl = new CTEntryImpl();

		ctEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		ctEntryImpl.setCtEntryId(
			this.<Long>getColumnOriginalValue("ctEntryId"));
		ctEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		ctEntryImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		ctEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		ctEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		ctEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		ctEntryImpl.setModelClassNameId(
			this.<Long>getColumnOriginalValue("modelClassNameId"));
		ctEntryImpl.setModelClassPK(
			this.<Long>getColumnOriginalValue("modelClassPK"));
		ctEntryImpl.setModelMvccVersion(
			this.<Long>getColumnOriginalValue("modelMvccVersion"));
		ctEntryImpl.setChangeType(
			this.<Integer>getColumnOriginalValue("changeType"));

		return ctEntryImpl;
	}

	@Override
	public int compareTo(CTEntry ctEntry) {
		long primaryKey = ctEntry.getPrimaryKey();

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

		if (!(object instanceof CTEntry)) {
			return false;
		}

		CTEntry ctEntry = (CTEntry)object;

		long primaryKey = ctEntry.getPrimaryKey();

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
	public CacheModel<CTEntry> toCacheModel() {
		CTEntryCacheModel ctEntryCacheModel = new CTEntryCacheModel();

		ctEntryCacheModel.mvccVersion = getMvccVersion();

		ctEntryCacheModel.ctEntryId = getCtEntryId();

		ctEntryCacheModel.companyId = getCompanyId();

		ctEntryCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			ctEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			ctEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ctEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ctEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ctEntryCacheModel.ctCollectionId = getCtCollectionId();

		ctEntryCacheModel.modelClassNameId = getModelClassNameId();

		ctEntryCacheModel.modelClassPK = getModelClassPK();

		ctEntryCacheModel.modelMvccVersion = getModelMvccVersion();

		ctEntryCacheModel.changeType = getChangeType();

		return ctEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CTEntry)this);

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
		Map<String, Function<CTEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CTEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CTEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CTEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctEntryId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _ctCollectionId;
	private long _modelClassNameId;
	private long _modelClassPK;
	private long _modelMvccVersion;
	private int _changeType;

	public <T> T getColumnValue(String columnName) {
		Function<CTEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CTEntry)this);
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
		_columnOriginalValues.put("ctEntryId", _ctEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("modelClassNameId", _modelClassNameId);
		_columnOriginalValues.put("modelClassPK", _modelClassPK);
		_columnOriginalValues.put("modelMvccVersion", _modelMvccVersion);
		_columnOriginalValues.put("changeType", _changeType);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("ctCollectionId", 64L);

		columnBitmasks.put("modelClassNameId", 128L);

		columnBitmasks.put("modelClassPK", 256L);

		columnBitmasks.put("modelMvccVersion", 512L);

		columnBitmasks.put("changeType", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CTEntry _escapedModel;

}