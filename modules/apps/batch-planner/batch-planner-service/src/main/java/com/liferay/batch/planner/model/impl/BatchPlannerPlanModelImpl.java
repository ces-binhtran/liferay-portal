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

package com.liferay.batch.planner.model.impl;

import com.liferay.batch.planner.model.BatchPlannerPlan;
import com.liferay.batch.planner.model.BatchPlannerPlanModel;
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
import com.liferay.portal.kernel.util.DateUtil;
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
 * The base model implementation for the BatchPlannerPlan service. Represents a row in the &quot;BatchPlannerPlan&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>BatchPlannerPlanModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BatchPlannerPlanImpl}.
 * </p>
 *
 * @author Igor Beslic
 * @see BatchPlannerPlanImpl
 * @generated
 */
@JSON(strict = true)
public class BatchPlannerPlanModelImpl
	extends BaseModelImpl<BatchPlannerPlan> implements BatchPlannerPlanModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a batch planner plan model instance should use the <code>BatchPlannerPlan</code> interface instead.
	 */
	public static final String TABLE_NAME = "BatchPlannerPlan";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"batchPlannerPlanId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"active_", Types.BOOLEAN},
		{"export", Types.BOOLEAN}, {"externalType", Types.VARCHAR},
		{"externalURL", Types.VARCHAR}, {"internalClassName", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"taskItemDelegateName", Types.VARCHAR},
		{"template", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("batchPlannerPlanId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("export", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("externalType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("internalClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("taskItemDelegateName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("template", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BatchPlannerPlan (mvccVersion LONG default 0 not null,batchPlannerPlanId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,active_ BOOLEAN,export BOOLEAN,externalType VARCHAR(75) null,externalURL STRING null,internalClassName VARCHAR(75) null,name VARCHAR(75) null,taskItemDelegateName VARCHAR(75) null,template BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table BatchPlannerPlan";

	public static final String ORDER_BY_JPQL =
		" ORDER BY batchPlannerPlan.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BatchPlannerPlan.modifiedDate DESC";

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
	public static final long EXPORT_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TEMPLATE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 32L;

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

	public BatchPlannerPlanModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _batchPlannerPlanId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBatchPlannerPlanId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _batchPlannerPlanId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BatchPlannerPlan.class;
	}

	@Override
	public String getModelClassName() {
		return BatchPlannerPlan.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BatchPlannerPlan, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BatchPlannerPlan, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchPlannerPlan, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BatchPlannerPlan)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BatchPlannerPlan, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BatchPlannerPlan, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BatchPlannerPlan)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BatchPlannerPlan, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BatchPlannerPlan, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, BatchPlannerPlan>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			BatchPlannerPlan.class.getClassLoader(), BatchPlannerPlan.class,
			ModelWrapper.class);

		try {
			Constructor<BatchPlannerPlan> constructor =
				(Constructor<BatchPlannerPlan>)proxyClass.getConstructor(
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

	private static final Map<String, Function<BatchPlannerPlan, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<BatchPlannerPlan, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<BatchPlannerPlan, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<BatchPlannerPlan, Object>>();
		Map<String, BiConsumer<BatchPlannerPlan, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<BatchPlannerPlan, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", BatchPlannerPlan::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<BatchPlannerPlan, Long>)
				BatchPlannerPlan::setMvccVersion);
		attributeGetterFunctions.put(
			"batchPlannerPlanId", BatchPlannerPlan::getBatchPlannerPlanId);
		attributeSetterBiConsumers.put(
			"batchPlannerPlanId",
			(BiConsumer<BatchPlannerPlan, Long>)
				BatchPlannerPlan::setBatchPlannerPlanId);
		attributeGetterFunctions.put(
			"companyId", BatchPlannerPlan::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<BatchPlannerPlan, Long>)BatchPlannerPlan::setCompanyId);
		attributeGetterFunctions.put("userId", BatchPlannerPlan::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<BatchPlannerPlan, Long>)BatchPlannerPlan::setUserId);
		attributeGetterFunctions.put("userName", BatchPlannerPlan::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<BatchPlannerPlan, String>)
				BatchPlannerPlan::setUserName);
		attributeGetterFunctions.put(
			"createDate", BatchPlannerPlan::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<BatchPlannerPlan, Date>)
				BatchPlannerPlan::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", BatchPlannerPlan::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<BatchPlannerPlan, Date>)
				BatchPlannerPlan::setModifiedDate);
		attributeGetterFunctions.put("active", BatchPlannerPlan::getActive);
		attributeSetterBiConsumers.put(
			"active",
			(BiConsumer<BatchPlannerPlan, Boolean>)BatchPlannerPlan::setActive);
		attributeGetterFunctions.put("export", BatchPlannerPlan::getExport);
		attributeSetterBiConsumers.put(
			"export",
			(BiConsumer<BatchPlannerPlan, Boolean>)BatchPlannerPlan::setExport);
		attributeGetterFunctions.put(
			"externalType", BatchPlannerPlan::getExternalType);
		attributeSetterBiConsumers.put(
			"externalType",
			(BiConsumer<BatchPlannerPlan, String>)
				BatchPlannerPlan::setExternalType);
		attributeGetterFunctions.put(
			"externalURL", BatchPlannerPlan::getExternalURL);
		attributeSetterBiConsumers.put(
			"externalURL",
			(BiConsumer<BatchPlannerPlan, String>)
				BatchPlannerPlan::setExternalURL);
		attributeGetterFunctions.put(
			"internalClassName", BatchPlannerPlan::getInternalClassName);
		attributeSetterBiConsumers.put(
			"internalClassName",
			(BiConsumer<BatchPlannerPlan, String>)
				BatchPlannerPlan::setInternalClassName);
		attributeGetterFunctions.put("name", BatchPlannerPlan::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<BatchPlannerPlan, String>)BatchPlannerPlan::setName);
		attributeGetterFunctions.put(
			"taskItemDelegateName", BatchPlannerPlan::getTaskItemDelegateName);
		attributeSetterBiConsumers.put(
			"taskItemDelegateName",
			(BiConsumer<BatchPlannerPlan, String>)
				BatchPlannerPlan::setTaskItemDelegateName);
		attributeGetterFunctions.put("template", BatchPlannerPlan::getTemplate);
		attributeSetterBiConsumers.put(
			"template",
			(BiConsumer<BatchPlannerPlan, Boolean>)
				BatchPlannerPlan::setTemplate);

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
	public long getBatchPlannerPlanId() {
		return _batchPlannerPlanId;
	}

	@Override
	public void setBatchPlannerPlanId(long batchPlannerPlanId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_batchPlannerPlanId = batchPlannerPlanId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_active = active;
	}

	@JSON
	@Override
	public boolean getExport() {
		return _export;
	}

	@JSON
	@Override
	public boolean isExport() {
		return _export;
	}

	@Override
	public void setExport(boolean export) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_export = export;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalExport() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("export"));
	}

	@JSON
	@Override
	public String getExternalType() {
		if (_externalType == null) {
			return "";
		}
		else {
			return _externalType;
		}
	}

	@Override
	public void setExternalType(String externalType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalType = externalType;
	}

	@JSON
	@Override
	public String getExternalURL() {
		if (_externalURL == null) {
			return "";
		}
		else {
			return _externalURL;
		}
	}

	@Override
	public void setExternalURL(String externalURL) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalURL = externalURL;
	}

	@JSON
	@Override
	public String getInternalClassName() {
		if (_internalClassName == null) {
			return "";
		}
		else {
			return _internalClassName;
		}
	}

	@Override
	public void setInternalClassName(String internalClassName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_internalClassName = internalClassName;
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
	public String getTaskItemDelegateName() {
		if (_taskItemDelegateName == null) {
			return "";
		}
		else {
			return _taskItemDelegateName;
		}
	}

	@Override
	public void setTaskItemDelegateName(String taskItemDelegateName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_taskItemDelegateName = taskItemDelegateName;
	}

	@JSON
	@Override
	public boolean getTemplate() {
		return _template;
	}

	@JSON
	@Override
	public boolean isTemplate() {
		return _template;
	}

	@Override
	public void setTemplate(boolean template) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_template = template;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalTemplate() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("template"));
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
			getCompanyId(), BatchPlannerPlan.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BatchPlannerPlan toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, BatchPlannerPlan>
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
		BatchPlannerPlanImpl batchPlannerPlanImpl = new BatchPlannerPlanImpl();

		batchPlannerPlanImpl.setMvccVersion(getMvccVersion());
		batchPlannerPlanImpl.setBatchPlannerPlanId(getBatchPlannerPlanId());
		batchPlannerPlanImpl.setCompanyId(getCompanyId());
		batchPlannerPlanImpl.setUserId(getUserId());
		batchPlannerPlanImpl.setUserName(getUserName());
		batchPlannerPlanImpl.setCreateDate(getCreateDate());
		batchPlannerPlanImpl.setModifiedDate(getModifiedDate());
		batchPlannerPlanImpl.setActive(isActive());
		batchPlannerPlanImpl.setExport(isExport());
		batchPlannerPlanImpl.setExternalType(getExternalType());
		batchPlannerPlanImpl.setExternalURL(getExternalURL());
		batchPlannerPlanImpl.setInternalClassName(getInternalClassName());
		batchPlannerPlanImpl.setName(getName());
		batchPlannerPlanImpl.setTaskItemDelegateName(getTaskItemDelegateName());
		batchPlannerPlanImpl.setTemplate(isTemplate());

		batchPlannerPlanImpl.resetOriginalValues();

		return batchPlannerPlanImpl;
	}

	@Override
	public BatchPlannerPlan cloneWithOriginalValues() {
		BatchPlannerPlanImpl batchPlannerPlanImpl = new BatchPlannerPlanImpl();

		batchPlannerPlanImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		batchPlannerPlanImpl.setBatchPlannerPlanId(
			this.<Long>getColumnOriginalValue("batchPlannerPlanId"));
		batchPlannerPlanImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		batchPlannerPlanImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		batchPlannerPlanImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		batchPlannerPlanImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		batchPlannerPlanImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		batchPlannerPlanImpl.setActive(
			this.<Boolean>getColumnOriginalValue("active_"));
		batchPlannerPlanImpl.setExport(
			this.<Boolean>getColumnOriginalValue("export"));
		batchPlannerPlanImpl.setExternalType(
			this.<String>getColumnOriginalValue("externalType"));
		batchPlannerPlanImpl.setExternalURL(
			this.<String>getColumnOriginalValue("externalURL"));
		batchPlannerPlanImpl.setInternalClassName(
			this.<String>getColumnOriginalValue("internalClassName"));
		batchPlannerPlanImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		batchPlannerPlanImpl.setTaskItemDelegateName(
			this.<String>getColumnOriginalValue("taskItemDelegateName"));
		batchPlannerPlanImpl.setTemplate(
			this.<Boolean>getColumnOriginalValue("template"));

		return batchPlannerPlanImpl;
	}

	@Override
	public int compareTo(BatchPlannerPlan batchPlannerPlan) {
		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(), batchPlannerPlan.getModifiedDate());

		value = value * -1;

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

		if (!(object instanceof BatchPlannerPlan)) {
			return false;
		}

		BatchPlannerPlan batchPlannerPlan = (BatchPlannerPlan)object;

		long primaryKey = batchPlannerPlan.getPrimaryKey();

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
	public CacheModel<BatchPlannerPlan> toCacheModel() {
		BatchPlannerPlanCacheModel batchPlannerPlanCacheModel =
			new BatchPlannerPlanCacheModel();

		batchPlannerPlanCacheModel.mvccVersion = getMvccVersion();

		batchPlannerPlanCacheModel.batchPlannerPlanId = getBatchPlannerPlanId();

		batchPlannerPlanCacheModel.companyId = getCompanyId();

		batchPlannerPlanCacheModel.userId = getUserId();

		batchPlannerPlanCacheModel.userName = getUserName();

		String userName = batchPlannerPlanCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			batchPlannerPlanCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			batchPlannerPlanCacheModel.createDate = createDate.getTime();
		}
		else {
			batchPlannerPlanCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			batchPlannerPlanCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			batchPlannerPlanCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		batchPlannerPlanCacheModel.active = isActive();

		batchPlannerPlanCacheModel.export = isExport();

		batchPlannerPlanCacheModel.externalType = getExternalType();

		String externalType = batchPlannerPlanCacheModel.externalType;

		if ((externalType != null) && (externalType.length() == 0)) {
			batchPlannerPlanCacheModel.externalType = null;
		}

		batchPlannerPlanCacheModel.externalURL = getExternalURL();

		String externalURL = batchPlannerPlanCacheModel.externalURL;

		if ((externalURL != null) && (externalURL.length() == 0)) {
			batchPlannerPlanCacheModel.externalURL = null;
		}

		batchPlannerPlanCacheModel.internalClassName = getInternalClassName();

		String internalClassName = batchPlannerPlanCacheModel.internalClassName;

		if ((internalClassName != null) && (internalClassName.length() == 0)) {
			batchPlannerPlanCacheModel.internalClassName = null;
		}

		batchPlannerPlanCacheModel.name = getName();

		String name = batchPlannerPlanCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			batchPlannerPlanCacheModel.name = null;
		}

		batchPlannerPlanCacheModel.taskItemDelegateName =
			getTaskItemDelegateName();

		String taskItemDelegateName =
			batchPlannerPlanCacheModel.taskItemDelegateName;

		if ((taskItemDelegateName != null) &&
			(taskItemDelegateName.length() == 0)) {

			batchPlannerPlanCacheModel.taskItemDelegateName = null;
		}

		batchPlannerPlanCacheModel.template = isTemplate();

		return batchPlannerPlanCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<BatchPlannerPlan, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<BatchPlannerPlan, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchPlannerPlan, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(BatchPlannerPlan)this);

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
		Map<String, Function<BatchPlannerPlan, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<BatchPlannerPlan, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BatchPlannerPlan, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((BatchPlannerPlan)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, BatchPlannerPlan>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _batchPlannerPlanId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _active;
	private boolean _export;
	private String _externalType;
	private String _externalURL;
	private String _internalClassName;
	private String _name;
	private String _taskItemDelegateName;
	private boolean _template;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<BatchPlannerPlan, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((BatchPlannerPlan)this);
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
		_columnOriginalValues.put("batchPlannerPlanId", _batchPlannerPlanId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("active_", _active);
		_columnOriginalValues.put("export", _export);
		_columnOriginalValues.put("externalType", _externalType);
		_columnOriginalValues.put("externalURL", _externalURL);
		_columnOriginalValues.put("internalClassName", _internalClassName);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put(
			"taskItemDelegateName", _taskItemDelegateName);
		_columnOriginalValues.put("template", _template);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("active_", "active");

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

		columnBitmasks.put("batchPlannerPlanId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("active_", 128L);

		columnBitmasks.put("export", 256L);

		columnBitmasks.put("externalType", 512L);

		columnBitmasks.put("externalURL", 1024L);

		columnBitmasks.put("internalClassName", 2048L);

		columnBitmasks.put("name", 4096L);

		columnBitmasks.put("taskItemDelegateName", 8192L);

		columnBitmasks.put("template", 16384L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private BatchPlannerPlan _escapedModel;

}