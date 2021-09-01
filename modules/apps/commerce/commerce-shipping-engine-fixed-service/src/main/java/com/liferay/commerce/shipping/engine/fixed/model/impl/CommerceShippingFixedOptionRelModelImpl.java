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

package com.liferay.commerce.shipping.engine.fixed.model.impl;

import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRel;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRelModel;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRelSoap;
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
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.math.BigDecimal;

import java.sql.Blob;
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
 * The base model implementation for the CommerceShippingFixedOptionRel service. Represents a row in the &quot;CShippingFixedOptionRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceShippingFixedOptionRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceShippingFixedOptionRelImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceShippingFixedOptionRelModelImpl
	extends BaseModelImpl<CommerceShippingFixedOptionRel>
	implements CommerceShippingFixedOptionRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce shipping fixed option rel model instance should use the <code>CommerceShippingFixedOptionRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CShippingFixedOptionRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"CShippingFixedOptionRelId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commerceShippingMethodId", Types.BIGINT},
		{"commerceShippingFixedOptionId", Types.BIGINT},
		{"commerceInventoryWarehouseId", Types.BIGINT},
		{"commerceCountryId", Types.BIGINT}, {"commerceRegionId", Types.BIGINT},
		{"zip", Types.VARCHAR}, {"weightFrom", Types.DOUBLE},
		{"weightTo", Types.DOUBLE}, {"fixedPrice", Types.DECIMAL},
		{"rateUnitWeightPrice", Types.DECIMAL}, {"ratePercentage", Types.DOUBLE}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CShippingFixedOptionRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceShippingMethodId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceShippingFixedOptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceInventoryWarehouseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceCountryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceRegionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("zip", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("weightFrom", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("weightTo", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("fixedPrice", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("rateUnitWeightPrice", Types.DECIMAL);
		TABLE_COLUMNS_MAP.put("ratePercentage", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CShippingFixedOptionRel (CShippingFixedOptionRelId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceShippingMethodId LONG,commerceShippingFixedOptionId LONG,commerceInventoryWarehouseId LONG,commerceCountryId LONG,commerceRegionId LONG,zip VARCHAR(75) null,weightFrom DOUBLE,weightTo DOUBLE,fixedPrice DECIMAL(30, 16) null,rateUnitWeightPrice DECIMAL(30, 16) null,ratePercentage DOUBLE)";

	public static final String TABLE_SQL_DROP =
		"drop table CShippingFixedOptionRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceShippingFixedOptionRel.commerceCountryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CShippingFixedOptionRel.commerceCountryId ASC";

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
	public static final long COMMERCESHIPPINGFIXEDOPTIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCESHIPPINGMETHODID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCECOUNTRYID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceShippingFixedOptionRel toModel(
		CommerceShippingFixedOptionRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceShippingFixedOptionRel model =
			new CommerceShippingFixedOptionRelImpl();

		model.setCommerceShippingFixedOptionRelId(
			soapModel.getCommerceShippingFixedOptionRelId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceShippingMethodId(
			soapModel.getCommerceShippingMethodId());
		model.setCommerceShippingFixedOptionId(
			soapModel.getCommerceShippingFixedOptionId());
		model.setCommerceInventoryWarehouseId(
			soapModel.getCommerceInventoryWarehouseId());
		model.setCommerceCountryId(soapModel.getCommerceCountryId());
		model.setCommerceRegionId(soapModel.getCommerceRegionId());
		model.setZip(soapModel.getZip());
		model.setWeightFrom(soapModel.getWeightFrom());
		model.setWeightTo(soapModel.getWeightTo());
		model.setFixedPrice(soapModel.getFixedPrice());
		model.setRateUnitWeightPrice(soapModel.getRateUnitWeightPrice());
		model.setRatePercentage(soapModel.getRatePercentage());

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
	public static List<CommerceShippingFixedOptionRel> toModels(
		CommerceShippingFixedOptionRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceShippingFixedOptionRel> models =
			new ArrayList<CommerceShippingFixedOptionRel>(soapModels.length);

		for (CommerceShippingFixedOptionRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.shipping.engine.fixed.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRel"));

	public CommerceShippingFixedOptionRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceShippingFixedOptionRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceShippingFixedOptionRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceShippingFixedOptionRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceShippingFixedOptionRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceShippingFixedOptionRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceShippingFixedOptionRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceShippingFixedOptionRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShippingFixedOptionRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceShippingFixedOptionRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceShippingFixedOptionRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceShippingFixedOptionRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceShippingFixedOptionRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceShippingFixedOptionRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceShippingFixedOptionRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceShippingFixedOptionRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceShippingFixedOptionRel.class.getClassLoader(),
			CommerceShippingFixedOptionRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceShippingFixedOptionRel> constructor =
				(Constructor<CommerceShippingFixedOptionRel>)
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
		<String, Function<CommerceShippingFixedOptionRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceShippingFixedOptionRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceShippingFixedOptionRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceShippingFixedOptionRel, Object>>();
		Map<String, BiConsumer<CommerceShippingFixedOptionRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceShippingFixedOptionRel, ?>>();

		attributeGetterFunctions.put(
			"commerceShippingFixedOptionRelId",
			CommerceShippingFixedOptionRel::
				getCommerceShippingFixedOptionRelId);
		attributeSetterBiConsumers.put(
			"commerceShippingFixedOptionRelId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::
					setCommerceShippingFixedOptionRelId);
		attributeGetterFunctions.put(
			"groupId", CommerceShippingFixedOptionRel::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CommerceShippingFixedOptionRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceShippingFixedOptionRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceShippingFixedOptionRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceShippingFixedOptionRel, String>)
				CommerceShippingFixedOptionRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceShippingFixedOptionRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceShippingFixedOptionRel, Date>)
				CommerceShippingFixedOptionRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceShippingFixedOptionRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceShippingFixedOptionRel, Date>)
				CommerceShippingFixedOptionRel::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceShippingMethodId",
			CommerceShippingFixedOptionRel::getCommerceShippingMethodId);
		attributeSetterBiConsumers.put(
			"commerceShippingMethodId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::setCommerceShippingMethodId);
		attributeGetterFunctions.put(
			"commerceShippingFixedOptionId",
			CommerceShippingFixedOptionRel::getCommerceShippingFixedOptionId);
		attributeSetterBiConsumers.put(
			"commerceShippingFixedOptionId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::
					setCommerceShippingFixedOptionId);
		attributeGetterFunctions.put(
			"commerceInventoryWarehouseId",
			CommerceShippingFixedOptionRel::getCommerceInventoryWarehouseId);
		attributeSetterBiConsumers.put(
			"commerceInventoryWarehouseId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::
					setCommerceInventoryWarehouseId);
		attributeGetterFunctions.put(
			"commerceCountryId",
			CommerceShippingFixedOptionRel::getCommerceCountryId);
		attributeSetterBiConsumers.put(
			"commerceCountryId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::setCommerceCountryId);
		attributeGetterFunctions.put(
			"commerceRegionId",
			CommerceShippingFixedOptionRel::getCommerceRegionId);
		attributeSetterBiConsumers.put(
			"commerceRegionId",
			(BiConsumer<CommerceShippingFixedOptionRel, Long>)
				CommerceShippingFixedOptionRel::setCommerceRegionId);
		attributeGetterFunctions.put(
			"zip", CommerceShippingFixedOptionRel::getZip);
		attributeSetterBiConsumers.put(
			"zip",
			(BiConsumer<CommerceShippingFixedOptionRel, String>)
				CommerceShippingFixedOptionRel::setZip);
		attributeGetterFunctions.put(
			"weightFrom", CommerceShippingFixedOptionRel::getWeightFrom);
		attributeSetterBiConsumers.put(
			"weightFrom",
			(BiConsumer<CommerceShippingFixedOptionRel, Double>)
				CommerceShippingFixedOptionRel::setWeightFrom);
		attributeGetterFunctions.put(
			"weightTo", CommerceShippingFixedOptionRel::getWeightTo);
		attributeSetterBiConsumers.put(
			"weightTo",
			(BiConsumer<CommerceShippingFixedOptionRel, Double>)
				CommerceShippingFixedOptionRel::setWeightTo);
		attributeGetterFunctions.put(
			"fixedPrice", CommerceShippingFixedOptionRel::getFixedPrice);
		attributeSetterBiConsumers.put(
			"fixedPrice",
			(BiConsumer<CommerceShippingFixedOptionRel, BigDecimal>)
				CommerceShippingFixedOptionRel::setFixedPrice);
		attributeGetterFunctions.put(
			"rateUnitWeightPrice",
			CommerceShippingFixedOptionRel::getRateUnitWeightPrice);
		attributeSetterBiConsumers.put(
			"rateUnitWeightPrice",
			(BiConsumer<CommerceShippingFixedOptionRel, BigDecimal>)
				CommerceShippingFixedOptionRel::setRateUnitWeightPrice);
		attributeGetterFunctions.put(
			"ratePercentage",
			CommerceShippingFixedOptionRel::getRatePercentage);
		attributeSetterBiConsumers.put(
			"ratePercentage",
			(BiConsumer<CommerceShippingFixedOptionRel, Double>)
				CommerceShippingFixedOptionRel::setRatePercentage);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceShippingFixedOptionRelId() {
		return _commerceShippingFixedOptionRelId;
	}

	@Override
	public void setCommerceShippingFixedOptionRelId(
		long commerceShippingFixedOptionRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShippingFixedOptionRelId = commerceShippingFixedOptionRelId;
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
	public long getCommerceShippingMethodId() {
		return _commerceShippingMethodId;
	}

	@Override
	public void setCommerceShippingMethodId(long commerceShippingMethodId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShippingMethodId = commerceShippingMethodId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceShippingMethodId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceShippingMethodId"));
	}

	@JSON
	@Override
	public long getCommerceShippingFixedOptionId() {
		return _commerceShippingFixedOptionId;
	}

	@Override
	public void setCommerceShippingFixedOptionId(
		long commerceShippingFixedOptionId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceShippingFixedOptionId = commerceShippingFixedOptionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceShippingFixedOptionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceShippingFixedOptionId"));
	}

	@JSON
	@Override
	public long getCommerceInventoryWarehouseId() {
		return _commerceInventoryWarehouseId;
	}

	@Override
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceInventoryWarehouseId = commerceInventoryWarehouseId;
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
	public String getZip() {
		if (_zip == null) {
			return "";
		}
		else {
			return _zip;
		}
	}

	@Override
	public void setZip(String zip) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_zip = zip;
	}

	@JSON
	@Override
	public double getWeightFrom() {
		return _weightFrom;
	}

	@Override
	public void setWeightFrom(double weightFrom) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_weightFrom = weightFrom;
	}

	@JSON
	@Override
	public double getWeightTo() {
		return _weightTo;
	}

	@Override
	public void setWeightTo(double weightTo) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_weightTo = weightTo;
	}

	@JSON
	@Override
	public BigDecimal getFixedPrice() {
		return _fixedPrice;
	}

	@Override
	public void setFixedPrice(BigDecimal fixedPrice) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fixedPrice = fixedPrice;
	}

	@JSON
	@Override
	public BigDecimal getRateUnitWeightPrice() {
		return _rateUnitWeightPrice;
	}

	@Override
	public void setRateUnitWeightPrice(BigDecimal rateUnitWeightPrice) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_rateUnitWeightPrice = rateUnitWeightPrice;
	}

	@JSON
	@Override
	public double getRatePercentage() {
		return _ratePercentage;
	}

	@Override
	public void setRatePercentage(double ratePercentage) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ratePercentage = ratePercentage;
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
			getCompanyId(), CommerceShippingFixedOptionRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceShippingFixedOptionRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceShippingFixedOptionRel>
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
		CommerceShippingFixedOptionRelImpl commerceShippingFixedOptionRelImpl =
			new CommerceShippingFixedOptionRelImpl();

		commerceShippingFixedOptionRelImpl.setCommerceShippingFixedOptionRelId(
			getCommerceShippingFixedOptionRelId());
		commerceShippingFixedOptionRelImpl.setGroupId(getGroupId());
		commerceShippingFixedOptionRelImpl.setCompanyId(getCompanyId());
		commerceShippingFixedOptionRelImpl.setUserId(getUserId());
		commerceShippingFixedOptionRelImpl.setUserName(getUserName());
		commerceShippingFixedOptionRelImpl.setCreateDate(getCreateDate());
		commerceShippingFixedOptionRelImpl.setModifiedDate(getModifiedDate());
		commerceShippingFixedOptionRelImpl.setCommerceShippingMethodId(
			getCommerceShippingMethodId());
		commerceShippingFixedOptionRelImpl.setCommerceShippingFixedOptionId(
			getCommerceShippingFixedOptionId());
		commerceShippingFixedOptionRelImpl.setCommerceInventoryWarehouseId(
			getCommerceInventoryWarehouseId());
		commerceShippingFixedOptionRelImpl.setCommerceCountryId(
			getCommerceCountryId());
		commerceShippingFixedOptionRelImpl.setCommerceRegionId(
			getCommerceRegionId());
		commerceShippingFixedOptionRelImpl.setZip(getZip());
		commerceShippingFixedOptionRelImpl.setWeightFrom(getWeightFrom());
		commerceShippingFixedOptionRelImpl.setWeightTo(getWeightTo());
		commerceShippingFixedOptionRelImpl.setFixedPrice(getFixedPrice());
		commerceShippingFixedOptionRelImpl.setRateUnitWeightPrice(
			getRateUnitWeightPrice());
		commerceShippingFixedOptionRelImpl.setRatePercentage(
			getRatePercentage());

		commerceShippingFixedOptionRelImpl.resetOriginalValues();

		return commerceShippingFixedOptionRelImpl;
	}

	@Override
	public int compareTo(
		CommerceShippingFixedOptionRel commerceShippingFixedOptionRel) {

		int value = 0;

		if (getCommerceCountryId() <
				commerceShippingFixedOptionRel.getCommerceCountryId()) {

			value = -1;
		}
		else if (getCommerceCountryId() >
					commerceShippingFixedOptionRel.getCommerceCountryId()) {

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

		if (!(object instanceof CommerceShippingFixedOptionRel)) {
			return false;
		}

		CommerceShippingFixedOptionRel commerceShippingFixedOptionRel =
			(CommerceShippingFixedOptionRel)object;

		long primaryKey = commerceShippingFixedOptionRel.getPrimaryKey();

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
	public CacheModel<CommerceShippingFixedOptionRel> toCacheModel() {
		CommerceShippingFixedOptionRelCacheModel
			commerceShippingFixedOptionRelCacheModel =
				new CommerceShippingFixedOptionRelCacheModel();

		commerceShippingFixedOptionRelCacheModel.
			commerceShippingFixedOptionRelId =
				getCommerceShippingFixedOptionRelId();

		commerceShippingFixedOptionRelCacheModel.groupId = getGroupId();

		commerceShippingFixedOptionRelCacheModel.companyId = getCompanyId();

		commerceShippingFixedOptionRelCacheModel.userId = getUserId();

		commerceShippingFixedOptionRelCacheModel.userName = getUserName();

		String userName = commerceShippingFixedOptionRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceShippingFixedOptionRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceShippingFixedOptionRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceShippingFixedOptionRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceShippingFixedOptionRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceShippingFixedOptionRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceShippingFixedOptionRelCacheModel.commerceShippingMethodId =
			getCommerceShippingMethodId();

		commerceShippingFixedOptionRelCacheModel.commerceShippingFixedOptionId =
			getCommerceShippingFixedOptionId();

		commerceShippingFixedOptionRelCacheModel.commerceInventoryWarehouseId =
			getCommerceInventoryWarehouseId();

		commerceShippingFixedOptionRelCacheModel.commerceCountryId =
			getCommerceCountryId();

		commerceShippingFixedOptionRelCacheModel.commerceRegionId =
			getCommerceRegionId();

		commerceShippingFixedOptionRelCacheModel.zip = getZip();

		String zip = commerceShippingFixedOptionRelCacheModel.zip;

		if ((zip != null) && (zip.length() == 0)) {
			commerceShippingFixedOptionRelCacheModel.zip = null;
		}

		commerceShippingFixedOptionRelCacheModel.weightFrom = getWeightFrom();

		commerceShippingFixedOptionRelCacheModel.weightTo = getWeightTo();

		commerceShippingFixedOptionRelCacheModel.fixedPrice = getFixedPrice();

		commerceShippingFixedOptionRelCacheModel.rateUnitWeightPrice =
			getRateUnitWeightPrice();

		commerceShippingFixedOptionRelCacheModel.ratePercentage =
			getRatePercentage();

		return commerceShippingFixedOptionRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceShippingFixedOptionRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceShippingFixedOptionRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShippingFixedOptionRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceShippingFixedOptionRel)this);

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
		Map<String, Function<CommerceShippingFixedOptionRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceShippingFixedOptionRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceShippingFixedOptionRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceShippingFixedOptionRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceShippingFixedOptionRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceShippingFixedOptionRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceShippingMethodId;
	private long _commerceShippingFixedOptionId;
	private long _commerceInventoryWarehouseId;
	private long _commerceCountryId;
	private long _commerceRegionId;
	private String _zip;
	private double _weightFrom;
	private double _weightTo;
	private BigDecimal _fixedPrice;
	private BigDecimal _rateUnitWeightPrice;
	private double _ratePercentage;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceShippingFixedOptionRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceShippingFixedOptionRel)this);
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

		_columnOriginalValues.put(
			"CShippingFixedOptionRelId", _commerceShippingFixedOptionRelId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"commerceShippingMethodId", _commerceShippingMethodId);
		_columnOriginalValues.put(
			"commerceShippingFixedOptionId", _commerceShippingFixedOptionId);
		_columnOriginalValues.put(
			"commerceInventoryWarehouseId", _commerceInventoryWarehouseId);
		_columnOriginalValues.put("commerceCountryId", _commerceCountryId);
		_columnOriginalValues.put("commerceRegionId", _commerceRegionId);
		_columnOriginalValues.put("zip", _zip);
		_columnOriginalValues.put("weightFrom", _weightFrom);
		_columnOriginalValues.put("weightTo", _weightTo);
		_columnOriginalValues.put("fixedPrice", _fixedPrice);
		_columnOriginalValues.put("rateUnitWeightPrice", _rateUnitWeightPrice);
		_columnOriginalValues.put("ratePercentage", _ratePercentage);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"CShippingFixedOptionRelId", "commerceShippingFixedOptionRelId");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("CShippingFixedOptionRelId", 1L);

		columnBitmasks.put("groupId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("commerceShippingMethodId", 128L);

		columnBitmasks.put("commerceShippingFixedOptionId", 256L);

		columnBitmasks.put("commerceInventoryWarehouseId", 512L);

		columnBitmasks.put("commerceCountryId", 1024L);

		columnBitmasks.put("commerceRegionId", 2048L);

		columnBitmasks.put("zip", 4096L);

		columnBitmasks.put("weightFrom", 8192L);

		columnBitmasks.put("weightTo", 16384L);

		columnBitmasks.put("fixedPrice", 32768L);

		columnBitmasks.put("rateUnitWeightPrice", 65536L);

		columnBitmasks.put("ratePercentage", 131072L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceShippingFixedOptionRel _escapedModel;

}