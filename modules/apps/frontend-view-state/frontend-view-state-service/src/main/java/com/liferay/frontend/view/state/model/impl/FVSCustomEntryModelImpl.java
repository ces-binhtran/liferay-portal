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

package com.liferay.frontend.view.state.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.frontend.view.state.model.FVSCustomEntry;
import com.liferay.frontend.view.state.model.FVSCustomEntryModel;
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
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the FVSCustomEntry service. Represents a row in the &quot;FVSCustomEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FVSCustomEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FVSCustomEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FVSCustomEntryImpl
 * @generated
 */
public class FVSCustomEntryModelImpl
	extends BaseModelImpl<FVSCustomEntry> implements FVSCustomEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a fvs custom entry model instance should use the <code>FVSCustomEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "FVSCustomEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"fvsCustomEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"fvsEntryId", Types.BIGINT}, {"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fvsCustomEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fvsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FVSCustomEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,fvsCustomEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,fvsEntryId LONG,name VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table FVSCustomEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY fvsCustomEntry.fvsCustomEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FVSCustomEntry.fvsCustomEntryId ASC";

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
	public static final long UUID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FVSCUSTOMENTRYID_COLUMN_BITMASK = 4L;

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

	public FVSCustomEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fvsCustomEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFvsCustomEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fvsCustomEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FVSCustomEntry.class;
	}

	@Override
	public String getModelClassName() {
		return FVSCustomEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FVSCustomEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FVSCustomEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSCustomEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FVSCustomEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FVSCustomEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FVSCustomEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FVSCustomEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FVSCustomEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FVSCustomEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, FVSCustomEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			FVSCustomEntry.class.getClassLoader(), FVSCustomEntry.class,
			ModelWrapper.class);

		try {
			Constructor<FVSCustomEntry> constructor =
				(Constructor<FVSCustomEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<FVSCustomEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<FVSCustomEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<FVSCustomEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<FVSCustomEntry, Object>>();
		Map<String, BiConsumer<FVSCustomEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<FVSCustomEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", FVSCustomEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<FVSCustomEntry, Long>)FVSCustomEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", FVSCustomEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<FVSCustomEntry, String>)FVSCustomEntry::setUuid);
		attributeGetterFunctions.put(
			"fvsCustomEntryId", FVSCustomEntry::getFvsCustomEntryId);
		attributeSetterBiConsumers.put(
			"fvsCustomEntryId",
			(BiConsumer<FVSCustomEntry, Long>)
				FVSCustomEntry::setFvsCustomEntryId);
		attributeGetterFunctions.put("companyId", FVSCustomEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<FVSCustomEntry, Long>)FVSCustomEntry::setCompanyId);
		attributeGetterFunctions.put("userId", FVSCustomEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<FVSCustomEntry, Long>)FVSCustomEntry::setUserId);
		attributeGetterFunctions.put("userName", FVSCustomEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<FVSCustomEntry, String>)FVSCustomEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", FVSCustomEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<FVSCustomEntry, Date>)FVSCustomEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", FVSCustomEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<FVSCustomEntry, Date>)FVSCustomEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"fvsEntryId", FVSCustomEntry::getFvsEntryId);
		attributeSetterBiConsumers.put(
			"fvsEntryId",
			(BiConsumer<FVSCustomEntry, Long>)FVSCustomEntry::setFvsEntryId);
		attributeGetterFunctions.put("name", FVSCustomEntry::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<FVSCustomEntry, String>)FVSCustomEntry::setName);

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
	public long getFvsCustomEntryId() {
		return _fvsCustomEntryId;
	}

	@Override
	public void setFvsCustomEntryId(long fvsCustomEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fvsCustomEntryId = fvsCustomEntryId;
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

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
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
	public long getFvsEntryId() {
		return _fvsEntryId;
	}

	@Override
	public void setFvsEntryId(long fvsEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fvsEntryId = fvsEntryId;
	}

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

	@Override
	public long getContainerModelId() {
		return getFvsCustomEntryId();
	}

	@Override
	public void setContainerModelId(long containerModelId) {
		_fvsCustomEntryId = containerModelId;
	}

	@Override
	public String getContainerModelName() {
		return String.valueOf(getName());
	}

	@Override
	public long getParentContainerModelId() {
		return 0;
	}

	@Override
	public void setParentContainerModelId(long parentContainerModelId) {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(FVSCustomEntry.class.getName()));
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
			getCompanyId(), FVSCustomEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FVSCustomEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FVSCustomEntry>
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
		FVSCustomEntryImpl fvsCustomEntryImpl = new FVSCustomEntryImpl();

		fvsCustomEntryImpl.setMvccVersion(getMvccVersion());
		fvsCustomEntryImpl.setUuid(getUuid());
		fvsCustomEntryImpl.setFvsCustomEntryId(getFvsCustomEntryId());
		fvsCustomEntryImpl.setCompanyId(getCompanyId());
		fvsCustomEntryImpl.setUserId(getUserId());
		fvsCustomEntryImpl.setUserName(getUserName());
		fvsCustomEntryImpl.setCreateDate(getCreateDate());
		fvsCustomEntryImpl.setModifiedDate(getModifiedDate());
		fvsCustomEntryImpl.setFvsEntryId(getFvsEntryId());
		fvsCustomEntryImpl.setName(getName());

		fvsCustomEntryImpl.resetOriginalValues();

		return fvsCustomEntryImpl;
	}

	@Override
	public FVSCustomEntry cloneWithOriginalValues() {
		FVSCustomEntryImpl fvsCustomEntryImpl = new FVSCustomEntryImpl();

		fvsCustomEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		fvsCustomEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		fvsCustomEntryImpl.setFvsCustomEntryId(
			this.<Long>getColumnOriginalValue("fvsCustomEntryId"));
		fvsCustomEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		fvsCustomEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		fvsCustomEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		fvsCustomEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		fvsCustomEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		fvsCustomEntryImpl.setFvsEntryId(
			this.<Long>getColumnOriginalValue("fvsEntryId"));
		fvsCustomEntryImpl.setName(this.<String>getColumnOriginalValue("name"));

		return fvsCustomEntryImpl;
	}

	@Override
	public int compareTo(FVSCustomEntry fvsCustomEntry) {
		long primaryKey = fvsCustomEntry.getPrimaryKey();

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

		if (!(object instanceof FVSCustomEntry)) {
			return false;
		}

		FVSCustomEntry fvsCustomEntry = (FVSCustomEntry)object;

		long primaryKey = fvsCustomEntry.getPrimaryKey();

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
	public CacheModel<FVSCustomEntry> toCacheModel() {
		FVSCustomEntryCacheModel fvsCustomEntryCacheModel =
			new FVSCustomEntryCacheModel();

		fvsCustomEntryCacheModel.mvccVersion = getMvccVersion();

		fvsCustomEntryCacheModel.uuid = getUuid();

		String uuid = fvsCustomEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			fvsCustomEntryCacheModel.uuid = null;
		}

		fvsCustomEntryCacheModel.fvsCustomEntryId = getFvsCustomEntryId();

		fvsCustomEntryCacheModel.companyId = getCompanyId();

		fvsCustomEntryCacheModel.userId = getUserId();

		fvsCustomEntryCacheModel.userName = getUserName();

		String userName = fvsCustomEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			fvsCustomEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			fvsCustomEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			fvsCustomEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			fvsCustomEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			fvsCustomEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		fvsCustomEntryCacheModel.fvsEntryId = getFvsEntryId();

		fvsCustomEntryCacheModel.name = getName();

		String name = fvsCustomEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			fvsCustomEntryCacheModel.name = null;
		}

		return fvsCustomEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FVSCustomEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FVSCustomEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSCustomEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((FVSCustomEntry)this);

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
		Map<String, Function<FVSCustomEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<FVSCustomEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FVSCustomEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((FVSCustomEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, FVSCustomEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private long _fvsCustomEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _fvsEntryId;
	private String _name;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<FVSCustomEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FVSCustomEntry)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("fvsCustomEntryId", _fvsCustomEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("fvsEntryId", _fvsEntryId);
		_columnOriginalValues.put("name", _name);
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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("fvsCustomEntryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("fvsEntryId", 256L);

		columnBitmasks.put("name", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FVSCustomEntry _escapedModel;

}