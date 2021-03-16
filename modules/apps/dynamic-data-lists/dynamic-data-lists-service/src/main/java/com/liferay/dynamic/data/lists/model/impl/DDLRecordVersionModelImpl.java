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

package com.liferay.dynamic.data.lists.model.impl;

import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.model.DDLRecordVersionModel;
import com.liferay.dynamic.data.lists.model.DDLRecordVersionSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDLRecordVersion service. Represents a row in the &quot;DDLRecordVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDLRecordVersionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDLRecordVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordVersionImpl
 * @generated
 */
@JSON(strict = true)
public class DDLRecordVersionModelImpl
	extends BaseModelImpl<DDLRecordVersion> implements DDLRecordVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddl record version model instance should use the <code>DDLRecordVersion</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDLRecordVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"recordVersionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"DDMStorageId", Types.BIGINT},
		{"recordSetId", Types.BIGINT}, {"recordSetVersion", Types.VARCHAR},
		{"recordId", Types.BIGINT}, {"version", Types.VARCHAR},
		{"displayIndex", Types.INTEGER}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recordVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("DDMStorageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recordSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recordSetVersion", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("recordId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("version", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("displayIndex", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDLRecordVersion (mvccVersion LONG default 0 not null,recordVersionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,DDMStorageId LONG,recordSetId LONG,recordSetVersion VARCHAR(75) null,recordId LONG,version VARCHAR(75) null,displayIndex INTEGER,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table DDLRecordVersion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddlRecordVersion.recordVersionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDLRecordVersion.recordVersionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RECORDID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RECORDSETID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RECORDSETVERSION_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long VERSION_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RECORDVERSIONID_COLUMN_BITMASK = 64L;

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

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static DDLRecordVersion toModel(DDLRecordVersionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		DDLRecordVersion model = new DDLRecordVersionImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setRecordVersionId(soapModel.getRecordVersionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setDDMStorageId(soapModel.getDDMStorageId());
		model.setRecordSetId(soapModel.getRecordSetId());
		model.setRecordSetVersion(soapModel.getRecordSetVersion());
		model.setRecordId(soapModel.getRecordId());
		model.setVersion(soapModel.getVersion());
		model.setDisplayIndex(soapModel.getDisplayIndex());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());

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
	public static List<DDLRecordVersion> toModels(
		DDLRecordVersionSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<DDLRecordVersion> models = new ArrayList<DDLRecordVersion>(
			soapModels.length);

		for (DDLRecordVersionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public DDLRecordVersionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _recordVersionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRecordVersionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _recordVersionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDLRecordVersion.class;
	}

	@Override
	public String getModelClassName() {
		return DDLRecordVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDLRecordVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDLRecordVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDLRecordVersion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDLRecordVersion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDLRecordVersion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDLRecordVersion, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDLRecordVersion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDLRecordVersion, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDLRecordVersion, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDLRecordVersion>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDLRecordVersion.class.getClassLoader(), DDLRecordVersion.class,
			ModelWrapper.class);

		try {
			Constructor<DDLRecordVersion> constructor =
				(Constructor<DDLRecordVersion>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DDLRecordVersion, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDLRecordVersion, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDLRecordVersion, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<DDLRecordVersion, Object>>();
		Map<String, BiConsumer<DDLRecordVersion, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<DDLRecordVersion, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDLRecordVersion::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDLRecordVersion, Long>)
				DDLRecordVersion::setMvccVersion);
		attributeGetterFunctions.put(
			"recordVersionId", DDLRecordVersion::getRecordVersionId);
		attributeSetterBiConsumers.put(
			"recordVersionId",
			(BiConsumer<DDLRecordVersion, Long>)
				DDLRecordVersion::setRecordVersionId);
		attributeGetterFunctions.put("groupId", DDLRecordVersion::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DDLRecordVersion, Long>)DDLRecordVersion::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DDLRecordVersion::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDLRecordVersion, Long>)DDLRecordVersion::setCompanyId);
		attributeGetterFunctions.put("userId", DDLRecordVersion::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<DDLRecordVersion, Long>)DDLRecordVersion::setUserId);
		attributeGetterFunctions.put("userName", DDLRecordVersion::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DDLRecordVersion, String>)
				DDLRecordVersion::setUserName);
		attributeGetterFunctions.put(
			"createDate", DDLRecordVersion::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DDLRecordVersion, Date>)
				DDLRecordVersion::setCreateDate);
		attributeGetterFunctions.put(
			"DDMStorageId", DDLRecordVersion::getDDMStorageId);
		attributeSetterBiConsumers.put(
			"DDMStorageId",
			(BiConsumer<DDLRecordVersion, Long>)
				DDLRecordVersion::setDDMStorageId);
		attributeGetterFunctions.put(
			"recordSetId", DDLRecordVersion::getRecordSetId);
		attributeSetterBiConsumers.put(
			"recordSetId",
			(BiConsumer<DDLRecordVersion, Long>)
				DDLRecordVersion::setRecordSetId);
		attributeGetterFunctions.put(
			"recordSetVersion", DDLRecordVersion::getRecordSetVersion);
		attributeSetterBiConsumers.put(
			"recordSetVersion",
			(BiConsumer<DDLRecordVersion, String>)
				DDLRecordVersion::setRecordSetVersion);
		attributeGetterFunctions.put("recordId", DDLRecordVersion::getRecordId);
		attributeSetterBiConsumers.put(
			"recordId",
			(BiConsumer<DDLRecordVersion, Long>)DDLRecordVersion::setRecordId);
		attributeGetterFunctions.put("version", DDLRecordVersion::getVersion);
		attributeSetterBiConsumers.put(
			"version",
			(BiConsumer<DDLRecordVersion, String>)DDLRecordVersion::setVersion);
		attributeGetterFunctions.put(
			"displayIndex", DDLRecordVersion::getDisplayIndex);
		attributeSetterBiConsumers.put(
			"displayIndex",
			(BiConsumer<DDLRecordVersion, Integer>)
				DDLRecordVersion::setDisplayIndex);
		attributeGetterFunctions.put("status", DDLRecordVersion::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<DDLRecordVersion, Integer>)DDLRecordVersion::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", DDLRecordVersion::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<DDLRecordVersion, Long>)
				DDLRecordVersion::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", DDLRecordVersion::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<DDLRecordVersion, String>)
				DDLRecordVersion::setStatusByUserName);
		attributeGetterFunctions.put(
			"statusDate", DDLRecordVersion::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate",
			(BiConsumer<DDLRecordVersion, Date>)
				DDLRecordVersion::setStatusDate);

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
	public long getRecordVersionId() {
		return _recordVersionId;
	}

	@Override
	public void setRecordVersionId(long recordVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_recordVersionId = recordVersionId;
	}

	@JSON
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getDDMStorageId() {
		return _DDMStorageId;
	}

	@Override
	public void setDDMStorageId(long DDMStorageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_DDMStorageId = DDMStorageId;
	}

	@JSON
	@Override
	public long getRecordSetId() {
		return _recordSetId;
	}

	@Override
	public void setRecordSetId(long recordSetId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_recordSetId = recordSetId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRecordSetId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("recordSetId"));
	}

	@JSON
	@Override
	public String getRecordSetVersion() {
		if (_recordSetVersion == null) {
			return "";
		}
		else {
			return _recordSetVersion;
		}
	}

	@Override
	public void setRecordSetVersion(String recordSetVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_recordSetVersion = recordSetVersion;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalRecordSetVersion() {
		return getColumnOriginalValue("recordSetVersion");
	}

	@JSON
	@Override
	public long getRecordId() {
		return _recordId;
	}

	@Override
	public void setRecordId(long recordId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_recordId = recordId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRecordId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("recordId"));
	}

	@JSON
	@Override
	public String getVersion() {
		if (_version == null) {
			return "";
		}
		else {
			return _version;
		}
	}

	@Override
	public void setVersion(String version) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_version = version;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalVersion() {
		return getColumnOriginalValue("version");
	}

	@JSON
	@Override
	public int getDisplayIndex() {
		return _displayIndex;
	}

	@Override
	public void setDisplayIndex(int displayIndex) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_displayIndex = displayIndex;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusDate = statusDate;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
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
			getCompanyId(), DDLRecordVersion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDLRecordVersion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDLRecordVersion>
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
		DDLRecordVersionImpl ddlRecordVersionImpl = new DDLRecordVersionImpl();

		ddlRecordVersionImpl.setMvccVersion(getMvccVersion());
		ddlRecordVersionImpl.setRecordVersionId(getRecordVersionId());
		ddlRecordVersionImpl.setGroupId(getGroupId());
		ddlRecordVersionImpl.setCompanyId(getCompanyId());
		ddlRecordVersionImpl.setUserId(getUserId());
		ddlRecordVersionImpl.setUserName(getUserName());
		ddlRecordVersionImpl.setCreateDate(getCreateDate());
		ddlRecordVersionImpl.setDDMStorageId(getDDMStorageId());
		ddlRecordVersionImpl.setRecordSetId(getRecordSetId());
		ddlRecordVersionImpl.setRecordSetVersion(getRecordSetVersion());
		ddlRecordVersionImpl.setRecordId(getRecordId());
		ddlRecordVersionImpl.setVersion(getVersion());
		ddlRecordVersionImpl.setDisplayIndex(getDisplayIndex());
		ddlRecordVersionImpl.setStatus(getStatus());
		ddlRecordVersionImpl.setStatusByUserId(getStatusByUserId());
		ddlRecordVersionImpl.setStatusByUserName(getStatusByUserName());
		ddlRecordVersionImpl.setStatusDate(getStatusDate());

		ddlRecordVersionImpl.resetOriginalValues();

		return ddlRecordVersionImpl;
	}

	@Override
	public int compareTo(DDLRecordVersion ddlRecordVersion) {
		long primaryKey = ddlRecordVersion.getPrimaryKey();

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

		if (!(object instanceof DDLRecordVersion)) {
			return false;
		}

		DDLRecordVersion ddlRecordVersion = (DDLRecordVersion)object;

		long primaryKey = ddlRecordVersion.getPrimaryKey();

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
	public CacheModel<DDLRecordVersion> toCacheModel() {
		DDLRecordVersionCacheModel ddlRecordVersionCacheModel =
			new DDLRecordVersionCacheModel();

		ddlRecordVersionCacheModel.mvccVersion = getMvccVersion();

		ddlRecordVersionCacheModel.recordVersionId = getRecordVersionId();

		ddlRecordVersionCacheModel.groupId = getGroupId();

		ddlRecordVersionCacheModel.companyId = getCompanyId();

		ddlRecordVersionCacheModel.userId = getUserId();

		ddlRecordVersionCacheModel.userName = getUserName();

		String userName = ddlRecordVersionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddlRecordVersionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddlRecordVersionCacheModel.createDate = createDate.getTime();
		}
		else {
			ddlRecordVersionCacheModel.createDate = Long.MIN_VALUE;
		}

		ddlRecordVersionCacheModel.DDMStorageId = getDDMStorageId();

		ddlRecordVersionCacheModel.recordSetId = getRecordSetId();

		ddlRecordVersionCacheModel.recordSetVersion = getRecordSetVersion();

		String recordSetVersion = ddlRecordVersionCacheModel.recordSetVersion;

		if ((recordSetVersion != null) && (recordSetVersion.length() == 0)) {
			ddlRecordVersionCacheModel.recordSetVersion = null;
		}

		ddlRecordVersionCacheModel.recordId = getRecordId();

		ddlRecordVersionCacheModel.version = getVersion();

		String version = ddlRecordVersionCacheModel.version;

		if ((version != null) && (version.length() == 0)) {
			ddlRecordVersionCacheModel.version = null;
		}

		ddlRecordVersionCacheModel.displayIndex = getDisplayIndex();

		ddlRecordVersionCacheModel.status = getStatus();

		ddlRecordVersionCacheModel.statusByUserId = getStatusByUserId();

		ddlRecordVersionCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = ddlRecordVersionCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			ddlRecordVersionCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			ddlRecordVersionCacheModel.statusDate = statusDate.getTime();
		}
		else {
			ddlRecordVersionCacheModel.statusDate = Long.MIN_VALUE;
		}

		return ddlRecordVersionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDLRecordVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDLRecordVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDLRecordVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DDLRecordVersion)this));
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
		Map<String, Function<DDLRecordVersion, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDLRecordVersion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDLRecordVersion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DDLRecordVersion)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDLRecordVersion>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _recordVersionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _DDMStorageId;
	private long _recordSetId;
	private String _recordSetVersion;
	private long _recordId;
	private String _version;
	private int _displayIndex;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

	public <T> T getColumnValue(String columnName) {
		Function<DDLRecordVersion, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DDLRecordVersion)this);
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
		_columnOriginalValues.put("recordVersionId", _recordVersionId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("DDMStorageId", _DDMStorageId);
		_columnOriginalValues.put("recordSetId", _recordSetId);
		_columnOriginalValues.put("recordSetVersion", _recordSetVersion);
		_columnOriginalValues.put("recordId", _recordId);
		_columnOriginalValues.put("version", _version);
		_columnOriginalValues.put("displayIndex", _displayIndex);
		_columnOriginalValues.put("status", _status);
		_columnOriginalValues.put("statusByUserId", _statusByUserId);
		_columnOriginalValues.put("statusByUserName", _statusByUserName);
		_columnOriginalValues.put("statusDate", _statusDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("recordVersionId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("DDMStorageId", 128L);

		columnBitmasks.put("recordSetId", 256L);

		columnBitmasks.put("recordSetVersion", 512L);

		columnBitmasks.put("recordId", 1024L);

		columnBitmasks.put("version", 2048L);

		columnBitmasks.put("displayIndex", 4096L);

		columnBitmasks.put("status", 8192L);

		columnBitmasks.put("statusByUserId", 16384L);

		columnBitmasks.put("statusByUserName", 32768L);

		columnBitmasks.put("statusDate", 65536L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DDLRecordVersion _escapedModel;

}