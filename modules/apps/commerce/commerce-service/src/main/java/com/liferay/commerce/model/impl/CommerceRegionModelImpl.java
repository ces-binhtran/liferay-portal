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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceRegionModel;
import com.liferay.commerce.model.CommerceRegionSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
 * The base model implementation for the CommerceRegion service. Represents a row in the &quot;CommerceRegion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceRegionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceRegionImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceRegionImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceRegionModelImpl
	extends BaseModelImpl<CommerceRegion> implements CommerceRegionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce region model instance should use the <code>CommerceRegion</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceRegion";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"commerceRegionId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceCountryId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"code_", Types.VARCHAR},
		{"priority", Types.DOUBLE}, {"active_", Types.BOOLEAN},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceRegionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceCountryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("code_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceRegion (uuid_ VARCHAR(75) null,commerceRegionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceCountryId LONG,name VARCHAR(75) null,code_ VARCHAR(75) null,priority DOUBLE,active_ BOOLEAN,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table CommerceRegion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceRegion.priority ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceRegion.priority ASC";

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
	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCECOUNTRYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

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
	public static final long PRIORITY_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceRegion toModel(CommerceRegionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceRegion model = new CommerceRegionImpl();

		model.setUuid(soapModel.getUuid());
		model.setCommerceRegionId(soapModel.getCommerceRegionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceCountryId(soapModel.getCommerceCountryId());
		model.setName(soapModel.getName());
		model.setCode(soapModel.getCode());
		model.setPriority(soapModel.getPriority());
		model.setActive(soapModel.isActive());
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
	public static List<CommerceRegion> toModels(
		CommerceRegionSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceRegion> models = new ArrayList<CommerceRegion>(
			soapModels.length);

		for (CommerceRegionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceRegion"));

	public CommerceRegionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceRegionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceRegionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceRegionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceRegion.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceRegion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceRegion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceRegion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceRegion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceRegion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceRegion, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceRegion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceRegion, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceRegion, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceRegion>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceRegion.class.getClassLoader(), CommerceRegion.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceRegion> constructor =
				(Constructor<CommerceRegion>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceRegion, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceRegion, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CommerceRegion, Object>>();
		Map<String, BiConsumer<CommerceRegion, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CommerceRegion, ?>>();

		attributeGetterFunctions.put("uuid", CommerceRegion::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CommerceRegion, String>)CommerceRegion::setUuid);
		attributeGetterFunctions.put(
			"commerceRegionId", CommerceRegion::getCommerceRegionId);
		attributeSetterBiConsumers.put(
			"commerceRegionId",
			(BiConsumer<CommerceRegion, Long>)
				CommerceRegion::setCommerceRegionId);
		attributeGetterFunctions.put("companyId", CommerceRegion::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceRegion, Long>)CommerceRegion::setCompanyId);
		attributeGetterFunctions.put("userId", CommerceRegion::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceRegion, Long>)CommerceRegion::setUserId);
		attributeGetterFunctions.put("userName", CommerceRegion::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceRegion, String>)CommerceRegion::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceRegion::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceRegion, Date>)CommerceRegion::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceRegion::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceRegion, Date>)CommerceRegion::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceCountryId", CommerceRegion::getCommerceCountryId);
		attributeSetterBiConsumers.put(
			"commerceCountryId",
			(BiConsumer<CommerceRegion, Long>)
				CommerceRegion::setCommerceCountryId);
		attributeGetterFunctions.put("name", CommerceRegion::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<CommerceRegion, String>)CommerceRegion::setName);
		attributeGetterFunctions.put("code", CommerceRegion::getCode);
		attributeSetterBiConsumers.put(
			"code",
			(BiConsumer<CommerceRegion, String>)CommerceRegion::setCode);
		attributeGetterFunctions.put("priority", CommerceRegion::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<CommerceRegion, Double>)CommerceRegion::setPriority);
		attributeGetterFunctions.put("active", CommerceRegion::getActive);
		attributeSetterBiConsumers.put(
			"active",
			(BiConsumer<CommerceRegion, Boolean>)CommerceRegion::setActive);
		attributeGetterFunctions.put(
			"lastPublishDate", CommerceRegion::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<CommerceRegion, Date>)
				CommerceRegion::setLastPublishDate);

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
	public long getCommerceRegionId() {
		return _commerceRegionId;
	}

	@Override
	public void setCommerceRegionId(long commerceRegionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceRegionId = commerceRegionId;
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
	public long getCommerceCountryId() {
		return _commerceCountryId;
	}

	@Override
	public void setCommerceCountryId(long commerceCountryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceCountryId = commerceCountryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceCountryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceCountryId"));
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

	@JSON
	@Override
	public String getCode() {
		if (_code == null) {
			return "";
		}
		else {
			return _code;
		}
	}

	@Override
	public void setCode(String code) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_code = code;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalCode() {
		return getColumnOriginalValue("code_");
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalActive() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("active_"));
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
			PortalUtil.getClassNameId(CommerceRegion.class.getName()));
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
			getCompanyId(), CommerceRegion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceRegion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceRegion>
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
		CommerceRegionImpl commerceRegionImpl = new CommerceRegionImpl();

		commerceRegionImpl.setUuid(getUuid());
		commerceRegionImpl.setCommerceRegionId(getCommerceRegionId());
		commerceRegionImpl.setCompanyId(getCompanyId());
		commerceRegionImpl.setUserId(getUserId());
		commerceRegionImpl.setUserName(getUserName());
		commerceRegionImpl.setCreateDate(getCreateDate());
		commerceRegionImpl.setModifiedDate(getModifiedDate());
		commerceRegionImpl.setCommerceCountryId(getCommerceCountryId());
		commerceRegionImpl.setName(getName());
		commerceRegionImpl.setCode(getCode());
		commerceRegionImpl.setPriority(getPriority());
		commerceRegionImpl.setActive(isActive());
		commerceRegionImpl.setLastPublishDate(getLastPublishDate());

		commerceRegionImpl.resetOriginalValues();

		return commerceRegionImpl;
	}

	@Override
	public int compareTo(CommerceRegion commerceRegion) {
		int value = 0;

		if (getPriority() < commerceRegion.getPriority()) {
			value = -1;
		}
		else if (getPriority() > commerceRegion.getPriority()) {
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

		if (!(object instanceof CommerceRegion)) {
			return false;
		}

		CommerceRegion commerceRegion = (CommerceRegion)object;

		long primaryKey = commerceRegion.getPrimaryKey();

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
	public CacheModel<CommerceRegion> toCacheModel() {
		CommerceRegionCacheModel commerceRegionCacheModel =
			new CommerceRegionCacheModel();

		commerceRegionCacheModel.uuid = getUuid();

		String uuid = commerceRegionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceRegionCacheModel.uuid = null;
		}

		commerceRegionCacheModel.commerceRegionId = getCommerceRegionId();

		commerceRegionCacheModel.companyId = getCompanyId();

		commerceRegionCacheModel.userId = getUserId();

		commerceRegionCacheModel.userName = getUserName();

		String userName = commerceRegionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceRegionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceRegionCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceRegionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceRegionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceRegionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceRegionCacheModel.commerceCountryId = getCommerceCountryId();

		commerceRegionCacheModel.name = getName();

		String name = commerceRegionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceRegionCacheModel.name = null;
		}

		commerceRegionCacheModel.code = getCode();

		String code = commerceRegionCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			commerceRegionCacheModel.code = null;
		}

		commerceRegionCacheModel.priority = getPriority();

		commerceRegionCacheModel.active = isActive();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			commerceRegionCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			commerceRegionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return commerceRegionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceRegion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceRegion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CommerceRegion)this));
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
		Map<String, Function<CommerceRegion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceRegion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceRegion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceRegion)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceRegion>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _commerceRegionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceCountryId;
	private String _name;
	private String _code;
	private double _priority;
	private boolean _active;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceRegion, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceRegion)this);
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
		_columnOriginalValues.put("commerceRegionId", _commerceRegionId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceCountryId", _commerceCountryId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("code_", _code);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("active_", _active);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("code_", "code");
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

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("commerceRegionId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("commerceCountryId", 128L);

		columnBitmasks.put("name", 256L);

		columnBitmasks.put("code_", 512L);

		columnBitmasks.put("priority", 1024L);

		columnBitmasks.put("active_", 2048L);

		columnBitmasks.put("lastPublishDate", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceRegion _escapedModel;

}