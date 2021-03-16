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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.CPMeasurementUnitModel;
import com.liferay.commerce.product.model.CPMeasurementUnitSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CPMeasurementUnit service. Represents a row in the &quot;CPMeasurementUnit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CPMeasurementUnitModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPMeasurementUnitImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPMeasurementUnitImpl
 * @generated
 */
@JSON(strict = true)
public class CPMeasurementUnitModelImpl
	extends BaseModelImpl<CPMeasurementUnit> implements CPMeasurementUnitModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp measurement unit model instance should use the <code>CPMeasurementUnit</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPMeasurementUnit";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"CPMeasurementUnitId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"key_", Types.VARCHAR},
		{"rate", Types.DOUBLE}, {"primary_", Types.BOOLEAN},
		{"priority", Types.DOUBLE}, {"type_", Types.INTEGER},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPMeasurementUnitId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("rate", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("primary_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPMeasurementUnit (uuid_ VARCHAR(75) null,CPMeasurementUnitId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,key_ VARCHAR(75) null,rate DOUBLE,primary_ BOOLEAN,priority DOUBLE,type_ INTEGER,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table CPMeasurementUnit";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cpMeasurementUnit.priority ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPMeasurementUnit.priority ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KEY_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PRIMARY_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 16L;

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
	public static final long PRIORITY_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CPMeasurementUnit toModel(CPMeasurementUnitSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CPMeasurementUnit model = new CPMeasurementUnitImpl();

		model.setUuid(soapModel.getUuid());
		model.setCPMeasurementUnitId(soapModel.getCPMeasurementUnitId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setKey(soapModel.getKey());
		model.setRate(soapModel.getRate());
		model.setPrimary(soapModel.isPrimary());
		model.setPriority(soapModel.getPriority());
		model.setType(soapModel.getType());
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
	public static List<CPMeasurementUnit> toModels(
		CPMeasurementUnitSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CPMeasurementUnit> models = new ArrayList<CPMeasurementUnit>(
			soapModels.length);

		for (CPMeasurementUnitSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.product.model.CPMeasurementUnit"));

	public CPMeasurementUnitModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPMeasurementUnitId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPMeasurementUnitId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPMeasurementUnitId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPMeasurementUnit.class;
	}

	@Override
	public String getModelClassName() {
		return CPMeasurementUnit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPMeasurementUnit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPMeasurementUnit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPMeasurementUnit, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CPMeasurementUnit)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPMeasurementUnit, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPMeasurementUnit, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPMeasurementUnit)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPMeasurementUnit, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPMeasurementUnit, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CPMeasurementUnit>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CPMeasurementUnit.class.getClassLoader(), CPMeasurementUnit.class,
			ModelWrapper.class);

		try {
			Constructor<CPMeasurementUnit> constructor =
				(Constructor<CPMeasurementUnit>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CPMeasurementUnit, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CPMeasurementUnit, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CPMeasurementUnit, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CPMeasurementUnit, Object>>();
		Map<String, BiConsumer<CPMeasurementUnit, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CPMeasurementUnit, ?>>();

		attributeGetterFunctions.put("uuid", CPMeasurementUnit::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CPMeasurementUnit, String>)CPMeasurementUnit::setUuid);
		attributeGetterFunctions.put(
			"CPMeasurementUnitId", CPMeasurementUnit::getCPMeasurementUnitId);
		attributeSetterBiConsumers.put(
			"CPMeasurementUnitId",
			(BiConsumer<CPMeasurementUnit, Long>)
				CPMeasurementUnit::setCPMeasurementUnitId);
		attributeGetterFunctions.put("groupId", CPMeasurementUnit::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CPMeasurementUnit, Long>)CPMeasurementUnit::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CPMeasurementUnit::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CPMeasurementUnit, Long>)
				CPMeasurementUnit::setCompanyId);
		attributeGetterFunctions.put("userId", CPMeasurementUnit::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CPMeasurementUnit, Long>)CPMeasurementUnit::setUserId);
		attributeGetterFunctions.put(
			"userName", CPMeasurementUnit::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CPMeasurementUnit, String>)
				CPMeasurementUnit::setUserName);
		attributeGetterFunctions.put(
			"createDate", CPMeasurementUnit::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CPMeasurementUnit, Date>)
				CPMeasurementUnit::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CPMeasurementUnit::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CPMeasurementUnit, Date>)
				CPMeasurementUnit::setModifiedDate);
		attributeGetterFunctions.put("name", CPMeasurementUnit::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<CPMeasurementUnit, String>)CPMeasurementUnit::setName);
		attributeGetterFunctions.put("key", CPMeasurementUnit::getKey);
		attributeSetterBiConsumers.put(
			"key",
			(BiConsumer<CPMeasurementUnit, String>)CPMeasurementUnit::setKey);
		attributeGetterFunctions.put("rate", CPMeasurementUnit::getRate);
		attributeSetterBiConsumers.put(
			"rate",
			(BiConsumer<CPMeasurementUnit, Double>)CPMeasurementUnit::setRate);
		attributeGetterFunctions.put("primary", CPMeasurementUnit::getPrimary);
		attributeSetterBiConsumers.put(
			"primary",
			(BiConsumer<CPMeasurementUnit, Boolean>)
				CPMeasurementUnit::setPrimary);
		attributeGetterFunctions.put(
			"priority", CPMeasurementUnit::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<CPMeasurementUnit, Double>)
				CPMeasurementUnit::setPriority);
		attributeGetterFunctions.put("type", CPMeasurementUnit::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<CPMeasurementUnit, Integer>)CPMeasurementUnit::setType);
		attributeGetterFunctions.put(
			"lastPublishDate", CPMeasurementUnit::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<CPMeasurementUnit, Date>)
				CPMeasurementUnit::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getCPMeasurementUnitId() {
		return _CPMeasurementUnitId;
	}

	@Override
	public void setCPMeasurementUnitId(long CPMeasurementUnitId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPMeasurementUnitId = CPMeasurementUnitId;
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
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_key = key;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalKey() {
		return getColumnOriginalValue("key_");
	}

	@JSON
	@Override
	public double getRate() {
		return _rate;
	}

	@Override
	public void setRate(double rate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_rate = rate;
	}

	@JSON
	@Override
	public boolean getPrimary() {
		return _primary;
	}

	@JSON
	@Override
	public boolean isPrimary() {
		return _primary;
	}

	@Override
	public void setPrimary(boolean primary) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_primary = primary;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalPrimary() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("primary_"));
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
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
			PortalUtil.getClassNameId(CPMeasurementUnit.class.getName()));
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
			getCompanyId(), CPMeasurementUnit.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			CPMeasurementUnit.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public CPMeasurementUnit toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPMeasurementUnit>
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
		CPMeasurementUnitImpl cpMeasurementUnitImpl =
			new CPMeasurementUnitImpl();

		cpMeasurementUnitImpl.setUuid(getUuid());
		cpMeasurementUnitImpl.setCPMeasurementUnitId(getCPMeasurementUnitId());
		cpMeasurementUnitImpl.setGroupId(getGroupId());
		cpMeasurementUnitImpl.setCompanyId(getCompanyId());
		cpMeasurementUnitImpl.setUserId(getUserId());
		cpMeasurementUnitImpl.setUserName(getUserName());
		cpMeasurementUnitImpl.setCreateDate(getCreateDate());
		cpMeasurementUnitImpl.setModifiedDate(getModifiedDate());
		cpMeasurementUnitImpl.setName(getName());
		cpMeasurementUnitImpl.setKey(getKey());
		cpMeasurementUnitImpl.setRate(getRate());
		cpMeasurementUnitImpl.setPrimary(isPrimary());
		cpMeasurementUnitImpl.setPriority(getPriority());
		cpMeasurementUnitImpl.setType(getType());
		cpMeasurementUnitImpl.setLastPublishDate(getLastPublishDate());

		cpMeasurementUnitImpl.resetOriginalValues();

		return cpMeasurementUnitImpl;
	}

	@Override
	public int compareTo(CPMeasurementUnit cpMeasurementUnit) {
		int value = 0;

		if (getPriority() < cpMeasurementUnit.getPriority()) {
			value = -1;
		}
		else if (getPriority() > cpMeasurementUnit.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof CPMeasurementUnit)) {
			return false;
		}

		CPMeasurementUnit cpMeasurementUnit = (CPMeasurementUnit)object;

		long primaryKey = cpMeasurementUnit.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CPMeasurementUnit> toCacheModel() {
		CPMeasurementUnitCacheModel cpMeasurementUnitCacheModel =
			new CPMeasurementUnitCacheModel();

		cpMeasurementUnitCacheModel.uuid = getUuid();

		String uuid = cpMeasurementUnitCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpMeasurementUnitCacheModel.uuid = null;
		}

		cpMeasurementUnitCacheModel.CPMeasurementUnitId =
			getCPMeasurementUnitId();

		cpMeasurementUnitCacheModel.groupId = getGroupId();

		cpMeasurementUnitCacheModel.companyId = getCompanyId();

		cpMeasurementUnitCacheModel.userId = getUserId();

		cpMeasurementUnitCacheModel.userName = getUserName();

		String userName = cpMeasurementUnitCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpMeasurementUnitCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpMeasurementUnitCacheModel.createDate = createDate.getTime();
		}
		else {
			cpMeasurementUnitCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpMeasurementUnitCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			cpMeasurementUnitCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpMeasurementUnitCacheModel.name = getName();

		String name = cpMeasurementUnitCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			cpMeasurementUnitCacheModel.name = null;
		}

		cpMeasurementUnitCacheModel.key = getKey();

		String key = cpMeasurementUnitCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			cpMeasurementUnitCacheModel.key = null;
		}

		cpMeasurementUnitCacheModel.rate = getRate();

		cpMeasurementUnitCacheModel.primary = isPrimary();

		cpMeasurementUnitCacheModel.priority = getPriority();

		cpMeasurementUnitCacheModel.type = getType();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			cpMeasurementUnitCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			cpMeasurementUnitCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return cpMeasurementUnitCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPMeasurementUnit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPMeasurementUnit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPMeasurementUnit, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CPMeasurementUnit)this));
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
		Map<String, Function<CPMeasurementUnit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CPMeasurementUnit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPMeasurementUnit, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CPMeasurementUnit)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CPMeasurementUnit>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _CPMeasurementUnitId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _key;
	private double _rate;
	private boolean _primary;
	private double _priority;
	private int _type;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CPMeasurementUnit, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CPMeasurementUnit)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("CPMeasurementUnitId", _CPMeasurementUnitId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("key_", _key);
		_columnOriginalValues.put("rate", _rate);
		_columnOriginalValues.put("primary_", _primary);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("key_", "key");
		attributeNames.put("primary_", "primary");
		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("CPMeasurementUnitId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("name", 256L);

		columnBitmasks.put("key_", 512L);

		columnBitmasks.put("rate", 1024L);

		columnBitmasks.put("primary_", 2048L);

		columnBitmasks.put("priority", 4096L);

		columnBitmasks.put("type_", 8192L);

		columnBitmasks.put("lastPublishDate", 16384L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CPMeasurementUnit _escapedModel;

}