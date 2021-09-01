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

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantityModel;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantitySoap;
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
 * The base model implementation for the CommerceInventoryBookedQuantity service. Represents a row in the &quot;CIBookedQuantity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceInventoryBookedQuantityModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceInventoryBookedQuantityImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantityImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceInventoryBookedQuantityModelImpl
	extends BaseModelImpl<CommerceInventoryBookedQuantity>
	implements CommerceInventoryBookedQuantityModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce inventory booked quantity model instance should use the <code>CommerceInventoryBookedQuantity</code> interface instead.
	 */
	public static final String TABLE_NAME = "CIBookedQuantity";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"CIBookedQuantityId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"sku", Types.VARCHAR},
		{"quantity", Types.INTEGER}, {"expirationDate", Types.TIMESTAMP},
		{"bookedNote", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CIBookedQuantityId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("bookedNote", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CIBookedQuantity (mvccVersion LONG default 0 not null,CIBookedQuantityId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,sku VARCHAR(75) null,quantity INTEGER,expirationDate DATE null,bookedNote VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table CIBookedQuantity";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceInventoryBookedQuantity.commerceInventoryBookedQuantityId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CIBookedQuantity.CIBookedQuantityId ASC";

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
	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SKU_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEINVENTORYBOOKEDQUANTITYID_COLUMN_BITMASK =
		8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceInventoryBookedQuantity toModel(
		CommerceInventoryBookedQuantitySoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceInventoryBookedQuantity model =
			new CommerceInventoryBookedQuantityImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCommerceInventoryBookedQuantityId(
			soapModel.getCommerceInventoryBookedQuantityId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setSku(soapModel.getSku());
		model.setQuantity(soapModel.getQuantity());
		model.setExpirationDate(soapModel.getExpirationDate());
		model.setBookedNote(soapModel.getBookedNote());

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
	public static List<CommerceInventoryBookedQuantity> toModels(
		CommerceInventoryBookedQuantitySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceInventoryBookedQuantity> models =
			new ArrayList<CommerceInventoryBookedQuantity>(soapModels.length);

		for (CommerceInventoryBookedQuantitySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity"));

	public CommerceInventoryBookedQuantityModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceInventoryBookedQuantityId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceInventoryBookedQuantityId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceInventoryBookedQuantityId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceInventoryBookedQuantity.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceInventoryBookedQuantity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommerceInventoryBookedQuantity, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryBookedQuantity, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceInventoryBookedQuantity)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceInventoryBookedQuantity, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceInventoryBookedQuantity, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceInventoryBookedQuantity)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceInventoryBookedQuantity, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceInventoryBookedQuantity, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceInventoryBookedQuantity>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceInventoryBookedQuantity.class.getClassLoader(),
			CommerceInventoryBookedQuantity.class, ModelWrapper.class);

		try {
			Constructor<CommerceInventoryBookedQuantity> constructor =
				(Constructor<CommerceInventoryBookedQuantity>)
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
		<String, Function<CommerceInventoryBookedQuantity, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceInventoryBookedQuantity, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceInventoryBookedQuantity, Object>>();
		Map<String, BiConsumer<CommerceInventoryBookedQuantity, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceInventoryBookedQuantity, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommerceInventoryBookedQuantity::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommerceInventoryBookedQuantity, Long>)
				CommerceInventoryBookedQuantity::setMvccVersion);
		attributeGetterFunctions.put(
			"commerceInventoryBookedQuantityId",
			CommerceInventoryBookedQuantity::
				getCommerceInventoryBookedQuantityId);
		attributeSetterBiConsumers.put(
			"commerceInventoryBookedQuantityId",
			(BiConsumer<CommerceInventoryBookedQuantity, Long>)
				CommerceInventoryBookedQuantity::
					setCommerceInventoryBookedQuantityId);
		attributeGetterFunctions.put(
			"companyId", CommerceInventoryBookedQuantity::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceInventoryBookedQuantity, Long>)
				CommerceInventoryBookedQuantity::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceInventoryBookedQuantity::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceInventoryBookedQuantity, Long>)
				CommerceInventoryBookedQuantity::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceInventoryBookedQuantity::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceInventoryBookedQuantity, String>)
				CommerceInventoryBookedQuantity::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceInventoryBookedQuantity::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceInventoryBookedQuantity, Date>)
				CommerceInventoryBookedQuantity::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceInventoryBookedQuantity::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceInventoryBookedQuantity, Date>)
				CommerceInventoryBookedQuantity::setModifiedDate);
		attributeGetterFunctions.put(
			"sku", CommerceInventoryBookedQuantity::getSku);
		attributeSetterBiConsumers.put(
			"sku",
			(BiConsumer<CommerceInventoryBookedQuantity, String>)
				CommerceInventoryBookedQuantity::setSku);
		attributeGetterFunctions.put(
			"quantity", CommerceInventoryBookedQuantity::getQuantity);
		attributeSetterBiConsumers.put(
			"quantity",
			(BiConsumer<CommerceInventoryBookedQuantity, Integer>)
				CommerceInventoryBookedQuantity::setQuantity);
		attributeGetterFunctions.put(
			"expirationDate",
			CommerceInventoryBookedQuantity::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate",
			(BiConsumer<CommerceInventoryBookedQuantity, Date>)
				CommerceInventoryBookedQuantity::setExpirationDate);
		attributeGetterFunctions.put(
			"bookedNote", CommerceInventoryBookedQuantity::getBookedNote);
		attributeSetterBiConsumers.put(
			"bookedNote",
			(BiConsumer<CommerceInventoryBookedQuantity, String>)
				CommerceInventoryBookedQuantity::setBookedNote);

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
	public long getCommerceInventoryBookedQuantityId() {
		return _commerceInventoryBookedQuantityId;
	}

	@Override
	public void setCommerceInventoryBookedQuantityId(
		long commerceInventoryBookedQuantityId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceInventoryBookedQuantityId = commerceInventoryBookedQuantityId;
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
	public String getSku() {
		if (_sku == null) {
			return "";
		}
		else {
			return _sku;
		}
	}

	@Override
	public void setSku(String sku) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sku = sku;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalSku() {
		return getColumnOriginalValue("sku");
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_quantity = quantity;
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expirationDate = expirationDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalExpirationDate() {
		return getColumnOriginalValue("expirationDate");
	}

	@JSON
	@Override
	public String getBookedNote() {
		if (_bookedNote == null) {
			return "";
		}
		else {
			return _bookedNote;
		}
	}

	@Override
	public void setBookedNote(String bookedNote) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_bookedNote = bookedNote;
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
			getCompanyId(), CommerceInventoryBookedQuantity.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceInventoryBookedQuantity toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceInventoryBookedQuantity>
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
		CommerceInventoryBookedQuantityImpl
			commerceInventoryBookedQuantityImpl =
				new CommerceInventoryBookedQuantityImpl();

		commerceInventoryBookedQuantityImpl.setMvccVersion(getMvccVersion());
		commerceInventoryBookedQuantityImpl.
			setCommerceInventoryBookedQuantityId(
				getCommerceInventoryBookedQuantityId());
		commerceInventoryBookedQuantityImpl.setCompanyId(getCompanyId());
		commerceInventoryBookedQuantityImpl.setUserId(getUserId());
		commerceInventoryBookedQuantityImpl.setUserName(getUserName());
		commerceInventoryBookedQuantityImpl.setCreateDate(getCreateDate());
		commerceInventoryBookedQuantityImpl.setModifiedDate(getModifiedDate());
		commerceInventoryBookedQuantityImpl.setSku(getSku());
		commerceInventoryBookedQuantityImpl.setQuantity(getQuantity());
		commerceInventoryBookedQuantityImpl.setExpirationDate(
			getExpirationDate());
		commerceInventoryBookedQuantityImpl.setBookedNote(getBookedNote());

		commerceInventoryBookedQuantityImpl.resetOriginalValues();

		return commerceInventoryBookedQuantityImpl;
	}

	@Override
	public int compareTo(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {

		long primaryKey = commerceInventoryBookedQuantity.getPrimaryKey();

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

		if (!(object instanceof CommerceInventoryBookedQuantity)) {
			return false;
		}

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			(CommerceInventoryBookedQuantity)object;

		long primaryKey = commerceInventoryBookedQuantity.getPrimaryKey();

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
	public CacheModel<CommerceInventoryBookedQuantity> toCacheModel() {
		CommerceInventoryBookedQuantityCacheModel
			commerceInventoryBookedQuantityCacheModel =
				new CommerceInventoryBookedQuantityCacheModel();

		commerceInventoryBookedQuantityCacheModel.mvccVersion =
			getMvccVersion();

		commerceInventoryBookedQuantityCacheModel.
			commerceInventoryBookedQuantityId =
				getCommerceInventoryBookedQuantityId();

		commerceInventoryBookedQuantityCacheModel.companyId = getCompanyId();

		commerceInventoryBookedQuantityCacheModel.userId = getUserId();

		commerceInventoryBookedQuantityCacheModel.userName = getUserName();

		String userName = commerceInventoryBookedQuantityCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceInventoryBookedQuantityCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceInventoryBookedQuantityCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceInventoryBookedQuantityCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceInventoryBookedQuantityCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceInventoryBookedQuantityCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceInventoryBookedQuantityCacheModel.sku = getSku();

		String sku = commerceInventoryBookedQuantityCacheModel.sku;

		if ((sku != null) && (sku.length() == 0)) {
			commerceInventoryBookedQuantityCacheModel.sku = null;
		}

		commerceInventoryBookedQuantityCacheModel.quantity = getQuantity();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			commerceInventoryBookedQuantityCacheModel.expirationDate =
				expirationDate.getTime();
		}
		else {
			commerceInventoryBookedQuantityCacheModel.expirationDate =
				Long.MIN_VALUE;
		}

		commerceInventoryBookedQuantityCacheModel.bookedNote = getBookedNote();

		String bookedNote =
			commerceInventoryBookedQuantityCacheModel.bookedNote;

		if ((bookedNote != null) && (bookedNote.length() == 0)) {
			commerceInventoryBookedQuantityCacheModel.bookedNote = null;
		}

		return commerceInventoryBookedQuantityCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommerceInventoryBookedQuantity, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryBookedQuantity, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceInventoryBookedQuantity)this);

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
		Map<String, Function<CommerceInventoryBookedQuantity, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String, Function<CommerceInventoryBookedQuantity, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryBookedQuantity, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryBookedQuantity)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceInventoryBookedQuantity>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _commerceInventoryBookedQuantityId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _sku;
	private int _quantity;
	private Date _expirationDate;
	private String _bookedNote;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceInventoryBookedQuantity, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceInventoryBookedQuantity)this);
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
		_columnOriginalValues.put(
			"CIBookedQuantityId", _commerceInventoryBookedQuantityId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("sku", _sku);
		_columnOriginalValues.put("quantity", _quantity);
		_columnOriginalValues.put("expirationDate", _expirationDate);
		_columnOriginalValues.put("bookedNote", _bookedNote);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"CIBookedQuantityId", "commerceInventoryBookedQuantityId");

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

		columnBitmasks.put("CIBookedQuantityId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("sku", 128L);

		columnBitmasks.put("quantity", 256L);

		columnBitmasks.put("expirationDate", 512L);

		columnBitmasks.put("bookedNote", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceInventoryBookedQuantity _escapedModel;

}