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

package com.liferay.mobile.device.rules.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstanceModel;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstanceSoap;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MDRRuleGroupInstanceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MDRRuleGroupInstanceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceImpl
 * @generated
 */
@JSON(strict = true)
public class MDRRuleGroupInstanceModelImpl
	extends BaseModelImpl<MDRRuleGroupInstance>
	implements MDRRuleGroupInstanceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a mdr rule group instance model instance should use the <code>MDRRuleGroupInstance</code> interface instead.
	 */
	public static final String TABLE_NAME = "MDRRuleGroupInstance";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"ruleGroupInstanceId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"ruleGroupId", Types.BIGINT},
		{"priority", Types.INTEGER}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ruleGroupInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ruleGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MDRRuleGroupInstance (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,ruleGroupInstanceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,ruleGroupId LONG,priority INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table MDRRuleGroupInstance";

	public static final String ORDER_BY_JPQL =
		" ORDER BY mdrRuleGroupInstance.ruleGroupInstanceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MDRRuleGroupInstance.ruleGroupInstanceId ASC";

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
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RULEGROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RULEGROUPINSTANCEID_COLUMN_BITMASK = 64L;

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
	public static MDRRuleGroupInstance toModel(
		MDRRuleGroupInstanceSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		MDRRuleGroupInstance model = new MDRRuleGroupInstanceImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setRuleGroupInstanceId(soapModel.getRuleGroupInstanceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setRuleGroupId(soapModel.getRuleGroupId());
		model.setPriority(soapModel.getPriority());
		model.setLastPublishDate(soapModel.getLastPublishDate());

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
	public static List<MDRRuleGroupInstance> toModels(
		MDRRuleGroupInstanceSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<MDRRuleGroupInstance> models = new ArrayList<MDRRuleGroupInstance>(
			soapModels.length);

		for (MDRRuleGroupInstanceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public MDRRuleGroupInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRuleGroupInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MDRRuleGroupInstance.class;
	}

	@Override
	public String getModelClassName() {
		return MDRRuleGroupInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MDRRuleGroupInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRRuleGroupInstance, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MDRRuleGroupInstance)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MDRRuleGroupInstance, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MDRRuleGroupInstance, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MDRRuleGroupInstance)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MDRRuleGroupInstance, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MDRRuleGroupInstance, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MDRRuleGroupInstance>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MDRRuleGroupInstance.class.getClassLoader(),
			MDRRuleGroupInstance.class, ModelWrapper.class);

		try {
			Constructor<MDRRuleGroupInstance> constructor =
				(Constructor<MDRRuleGroupInstance>)proxyClass.getConstructor(
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

	private static final Map<String, Function<MDRRuleGroupInstance, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MDRRuleGroupInstance, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<MDRRuleGroupInstance, Object>>();
		Map<String, BiConsumer<MDRRuleGroupInstance, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<MDRRuleGroupInstance, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", MDRRuleGroupInstance::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setMvccVersion);
		attributeGetterFunctions.put("uuid", MDRRuleGroupInstance::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<MDRRuleGroupInstance, String>)
				MDRRuleGroupInstance::setUuid);
		attributeGetterFunctions.put(
			"ruleGroupInstanceId",
			MDRRuleGroupInstance::getRuleGroupInstanceId);
		attributeSetterBiConsumers.put(
			"ruleGroupInstanceId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setRuleGroupInstanceId);
		attributeGetterFunctions.put(
			"groupId", MDRRuleGroupInstance::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setGroupId);
		attributeGetterFunctions.put(
			"companyId", MDRRuleGroupInstance::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setCompanyId);
		attributeGetterFunctions.put("userId", MDRRuleGroupInstance::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setUserId);
		attributeGetterFunctions.put(
			"userName", MDRRuleGroupInstance::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<MDRRuleGroupInstance, String>)
				MDRRuleGroupInstance::setUserName);
		attributeGetterFunctions.put(
			"createDate", MDRRuleGroupInstance::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MDRRuleGroupInstance, Date>)
				MDRRuleGroupInstance::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", MDRRuleGroupInstance::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<MDRRuleGroupInstance, Date>)
				MDRRuleGroupInstance::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", MDRRuleGroupInstance::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", MDRRuleGroupInstance::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setClassPK);
		attributeGetterFunctions.put(
			"ruleGroupId", MDRRuleGroupInstance::getRuleGroupId);
		attributeSetterBiConsumers.put(
			"ruleGroupId",
			(BiConsumer<MDRRuleGroupInstance, Long>)
				MDRRuleGroupInstance::setRuleGroupId);
		attributeGetterFunctions.put(
			"priority", MDRRuleGroupInstance::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<MDRRuleGroupInstance, Integer>)
				MDRRuleGroupInstance::setPriority);
		attributeGetterFunctions.put(
			"lastPublishDate", MDRRuleGroupInstance::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<MDRRuleGroupInstance, Date>)
				MDRRuleGroupInstance::setLastPublishDate);

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

	@JSON
	@Override
	public long getRuleGroupInstanceId() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setRuleGroupInstanceId(long ruleGroupInstanceId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ruleGroupInstanceId = ruleGroupInstanceId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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
	public long getRuleGroupId() {
		return _ruleGroupId;
	}

	@Override
	public void setRuleGroupId(long ruleGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ruleGroupId = ruleGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalRuleGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("ruleGroupId"));
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@JSON
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
			PortalUtil.getClassNameId(MDRRuleGroupInstance.class.getName()),
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
			getCompanyId(), MDRRuleGroupInstance.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MDRRuleGroupInstance toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MDRRuleGroupInstance>
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
		MDRRuleGroupInstanceImpl mdrRuleGroupInstanceImpl =
			new MDRRuleGroupInstanceImpl();

		mdrRuleGroupInstanceImpl.setMvccVersion(getMvccVersion());
		mdrRuleGroupInstanceImpl.setUuid(getUuid());
		mdrRuleGroupInstanceImpl.setRuleGroupInstanceId(
			getRuleGroupInstanceId());
		mdrRuleGroupInstanceImpl.setGroupId(getGroupId());
		mdrRuleGroupInstanceImpl.setCompanyId(getCompanyId());
		mdrRuleGroupInstanceImpl.setUserId(getUserId());
		mdrRuleGroupInstanceImpl.setUserName(getUserName());
		mdrRuleGroupInstanceImpl.setCreateDate(getCreateDate());
		mdrRuleGroupInstanceImpl.setModifiedDate(getModifiedDate());
		mdrRuleGroupInstanceImpl.setClassNameId(getClassNameId());
		mdrRuleGroupInstanceImpl.setClassPK(getClassPK());
		mdrRuleGroupInstanceImpl.setRuleGroupId(getRuleGroupId());
		mdrRuleGroupInstanceImpl.setPriority(getPriority());
		mdrRuleGroupInstanceImpl.setLastPublishDate(getLastPublishDate());

		mdrRuleGroupInstanceImpl.resetOriginalValues();

		return mdrRuleGroupInstanceImpl;
	}

	@Override
	public int compareTo(MDRRuleGroupInstance mdrRuleGroupInstance) {
		long primaryKey = mdrRuleGroupInstance.getPrimaryKey();

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

		if (!(object instanceof MDRRuleGroupInstance)) {
			return false;
		}

		MDRRuleGroupInstance mdrRuleGroupInstance =
			(MDRRuleGroupInstance)object;

		long primaryKey = mdrRuleGroupInstance.getPrimaryKey();

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
	public CacheModel<MDRRuleGroupInstance> toCacheModel() {
		MDRRuleGroupInstanceCacheModel mdrRuleGroupInstanceCacheModel =
			new MDRRuleGroupInstanceCacheModel();

		mdrRuleGroupInstanceCacheModel.mvccVersion = getMvccVersion();

		mdrRuleGroupInstanceCacheModel.uuid = getUuid();

		String uuid = mdrRuleGroupInstanceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mdrRuleGroupInstanceCacheModel.uuid = null;
		}

		mdrRuleGroupInstanceCacheModel.ruleGroupInstanceId =
			getRuleGroupInstanceId();

		mdrRuleGroupInstanceCacheModel.groupId = getGroupId();

		mdrRuleGroupInstanceCacheModel.companyId = getCompanyId();

		mdrRuleGroupInstanceCacheModel.userId = getUserId();

		mdrRuleGroupInstanceCacheModel.userName = getUserName();

		String userName = mdrRuleGroupInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mdrRuleGroupInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mdrRuleGroupInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mdrRuleGroupInstanceCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mdrRuleGroupInstanceCacheModel.classNameId = getClassNameId();

		mdrRuleGroupInstanceCacheModel.classPK = getClassPK();

		mdrRuleGroupInstanceCacheModel.ruleGroupId = getRuleGroupId();

		mdrRuleGroupInstanceCacheModel.priority = getPriority();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mdrRuleGroupInstanceCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			mdrRuleGroupInstanceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return mdrRuleGroupInstanceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MDRRuleGroupInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRRuleGroupInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((MDRRuleGroupInstance)this));
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
		Map<String, Function<MDRRuleGroupInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MDRRuleGroupInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRRuleGroupInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((MDRRuleGroupInstance)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MDRRuleGroupInstance>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private long _ruleGroupInstanceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _ruleGroupId;
	private int _priority;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<MDRRuleGroupInstance, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((MDRRuleGroupInstance)this);
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
		_columnOriginalValues.put("ruleGroupInstanceId", _ruleGroupInstanceId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("ruleGroupId", _ruleGroupId);
		_columnOriginalValues.put("priority", _priority);
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

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("ruleGroupInstanceId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("ruleGroupId", 2048L);

		columnBitmasks.put("priority", 4096L);

		columnBitmasks.put("lastPublishDate", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private MDRRuleGroupInstance _escapedModel;

}