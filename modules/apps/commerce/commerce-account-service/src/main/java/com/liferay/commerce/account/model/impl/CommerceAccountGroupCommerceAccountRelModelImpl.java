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

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRelModel;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRelSoap;
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
 * The base model implementation for the CommerceAccountGroupCommerceAccountRel service. Represents a row in the &quot;CAccountGroupCAccountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceAccountGroupCommerceAccountRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAccountGroupCommerceAccountRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountGroupCommerceAccountRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceAccountGroupCommerceAccountRelModelImpl
	extends BaseModelImpl<CommerceAccountGroupCommerceAccountRel>
	implements CommerceAccountGroupCommerceAccountRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce account group commerce account rel model instance should use the <code>CommerceAccountGroupCommerceAccountRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CAccountGroupCAccountRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"externalReferenceCode", Types.VARCHAR},
		{"CAccountGroupCAccountRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commerceAccountGroupId", Types.BIGINT},
		{"commerceAccountId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CAccountGroupCAccountRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceAccountGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CAccountGroupCAccountRel (externalReferenceCode VARCHAR(75) null,CAccountGroupCAccountRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceAccountGroupId LONG,commerceAccountId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CAccountGroupCAccountRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceAccountGroupCommerceAccountRel.commerceAccountGroupCommerceAccountRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CAccountGroupCAccountRel.CAccountGroupCAccountRelId ASC";

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
	public static final long COMMERCEACCOUNTGROUPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long
		COMMERCEACCOUNTGROUPCOMMERCEACCOUNTRELID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceAccountGroupCommerceAccountRel toModel(
		CommerceAccountGroupCommerceAccountRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceAccountGroupCommerceAccountRel model =
			new CommerceAccountGroupCommerceAccountRelImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceAccountGroupCommerceAccountRelId(
			soapModel.getCommerceAccountGroupCommerceAccountRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceAccountGroupId(soapModel.getCommerceAccountGroupId());
		model.setCommerceAccountId(soapModel.getCommerceAccountId());

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
	public static List<CommerceAccountGroupCommerceAccountRel> toModels(
		CommerceAccountGroupCommerceAccountRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceAccountGroupCommerceAccountRel> models =
			new ArrayList<CommerceAccountGroupCommerceAccountRel>(
				soapModels.length);

		for (CommerceAccountGroupCommerceAccountRelSoap soapModel :
				soapModels) {

			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel"));

	public CommerceAccountGroupCommerceAccountRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceAccountGroupCommerceAccountRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceAccountGroupCommerceAccountRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceAccountGroupCommerceAccountRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccountGroupCommerceAccountRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccountGroupCommerceAccountRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceAccountGroupCommerceAccountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String,
				 Function<CommerceAccountGroupCommerceAccountRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroupCommerceAccountRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceAccountGroupCommerceAccountRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceAccountGroupCommerceAccountRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceAccountGroupCommerceAccountRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceAccountGroupCommerceAccountRel)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceAccountGroupCommerceAccountRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map
		<String, BiConsumer<CommerceAccountGroupCommerceAccountRel, Object>>
			getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function
		<InvocationHandler, CommerceAccountGroupCommerceAccountRel>
			_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceAccountGroupCommerceAccountRel.class.getClassLoader(),
			CommerceAccountGroupCommerceAccountRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceAccountGroupCommerceAccountRel> constructor =
				(Constructor<CommerceAccountGroupCommerceAccountRel>)
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
		<String, Function<CommerceAccountGroupCommerceAccountRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceAccountGroupCommerceAccountRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceAccountGroupCommerceAccountRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function
						 <CommerceAccountGroupCommerceAccountRel, Object>>();
		Map<String, BiConsumer<CommerceAccountGroupCommerceAccountRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String,
					 BiConsumer<CommerceAccountGroupCommerceAccountRel, ?>>();

		attributeGetterFunctions.put(
			"externalReferenceCode",
			CommerceAccountGroupCommerceAccountRel::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, String>)
				CommerceAccountGroupCommerceAccountRel::
					setExternalReferenceCode);
		attributeGetterFunctions.put(
			"commerceAccountGroupCommerceAccountRelId",
			CommerceAccountGroupCommerceAccountRel::
				getCommerceAccountGroupCommerceAccountRelId);
		attributeSetterBiConsumers.put(
			"commerceAccountGroupCommerceAccountRelId",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Long>)
				CommerceAccountGroupCommerceAccountRel::
					setCommerceAccountGroupCommerceAccountRelId);
		attributeGetterFunctions.put(
			"companyId", CommerceAccountGroupCommerceAccountRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Long>)
				CommerceAccountGroupCommerceAccountRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceAccountGroupCommerceAccountRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Long>)
				CommerceAccountGroupCommerceAccountRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceAccountGroupCommerceAccountRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, String>)
				CommerceAccountGroupCommerceAccountRel::setUserName);
		attributeGetterFunctions.put(
			"createDate",
			CommerceAccountGroupCommerceAccountRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Date>)
				CommerceAccountGroupCommerceAccountRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate",
			CommerceAccountGroupCommerceAccountRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Date>)
				CommerceAccountGroupCommerceAccountRel::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceAccountGroupId",
			CommerceAccountGroupCommerceAccountRel::getCommerceAccountGroupId);
		attributeSetterBiConsumers.put(
			"commerceAccountGroupId",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Long>)
				CommerceAccountGroupCommerceAccountRel::
					setCommerceAccountGroupId);
		attributeGetterFunctions.put(
			"commerceAccountId",
			CommerceAccountGroupCommerceAccountRel::getCommerceAccountId);
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			(BiConsumer<CommerceAccountGroupCommerceAccountRel, Long>)
				CommerceAccountGroupCommerceAccountRel::setCommerceAccountId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getCommerceAccountGroupCommerceAccountRelId() {
		return _commerceAccountGroupCommerceAccountRelId;
	}

	@Override
	public void setCommerceAccountGroupCommerceAccountRelId(
		long commerceAccountGroupCommerceAccountRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountGroupCommerceAccountRelId =
			commerceAccountGroupCommerceAccountRelId;
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
	public long getCommerceAccountGroupId() {
		return _commerceAccountGroupId;
	}

	@Override
	public void setCommerceAccountGroupId(long commerceAccountGroupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountGroupId = commerceAccountGroupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAccountGroupId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAccountGroupId"));
	}

	@JSON
	@Override
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceAccountId = commerceAccountId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceAccountId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceAccountId"));
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
			getCompanyId(),
			CommerceAccountGroupCommerceAccountRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceAccountGroupCommerceAccountRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceAccountGroupCommerceAccountRel>
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
		CommerceAccountGroupCommerceAccountRelImpl
			commerceAccountGroupCommerceAccountRelImpl =
				new CommerceAccountGroupCommerceAccountRelImpl();

		commerceAccountGroupCommerceAccountRelImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceAccountGroupCommerceAccountRelImpl.
			setCommerceAccountGroupCommerceAccountRelId(
				getCommerceAccountGroupCommerceAccountRelId());
		commerceAccountGroupCommerceAccountRelImpl.setCompanyId(getCompanyId());
		commerceAccountGroupCommerceAccountRelImpl.setUserId(getUserId());
		commerceAccountGroupCommerceAccountRelImpl.setUserName(getUserName());
		commerceAccountGroupCommerceAccountRelImpl.setCreateDate(
			getCreateDate());
		commerceAccountGroupCommerceAccountRelImpl.setModifiedDate(
			getModifiedDate());
		commerceAccountGroupCommerceAccountRelImpl.setCommerceAccountGroupId(
			getCommerceAccountGroupId());
		commerceAccountGroupCommerceAccountRelImpl.setCommerceAccountId(
			getCommerceAccountId());

		commerceAccountGroupCommerceAccountRelImpl.resetOriginalValues();

		return commerceAccountGroupCommerceAccountRelImpl;
	}

	@Override
	public int compareTo(
		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel) {

		long primaryKey =
			commerceAccountGroupCommerceAccountRel.getPrimaryKey();

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

		if (!(object instanceof CommerceAccountGroupCommerceAccountRel)) {
			return false;
		}

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				(CommerceAccountGroupCommerceAccountRel)object;

		long primaryKey =
			commerceAccountGroupCommerceAccountRel.getPrimaryKey();

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
	public CacheModel<CommerceAccountGroupCommerceAccountRel> toCacheModel() {
		CommerceAccountGroupCommerceAccountRelCacheModel
			commerceAccountGroupCommerceAccountRelCacheModel =
				new CommerceAccountGroupCommerceAccountRelCacheModel();

		commerceAccountGroupCommerceAccountRelCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceAccountGroupCommerceAccountRelCacheModel.
				externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceAccountGroupCommerceAccountRelCacheModel.
				externalReferenceCode = null;
		}

		commerceAccountGroupCommerceAccountRelCacheModel.
			commerceAccountGroupCommerceAccountRelId =
				getCommerceAccountGroupCommerceAccountRelId();

		commerceAccountGroupCommerceAccountRelCacheModel.companyId =
			getCompanyId();

		commerceAccountGroupCommerceAccountRelCacheModel.userId = getUserId();

		commerceAccountGroupCommerceAccountRelCacheModel.userName =
			getUserName();

		String userName =
			commerceAccountGroupCommerceAccountRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAccountGroupCommerceAccountRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAccountGroupCommerceAccountRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceAccountGroupCommerceAccountRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAccountGroupCommerceAccountRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceAccountGroupCommerceAccountRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceAccountGroupCommerceAccountRelCacheModel.
			commerceAccountGroupId = getCommerceAccountGroupId();

		commerceAccountGroupCommerceAccountRelCacheModel.commerceAccountId =
			getCommerceAccountId();

		return commerceAccountGroupCommerceAccountRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceAccountGroupCommerceAccountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry
				<String,
				 Function<CommerceAccountGroupCommerceAccountRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroupCommerceAccountRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceAccountGroupCommerceAccountRel)this));
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
		Map<String, Function<CommerceAccountGroupCommerceAccountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String,
				 Function<CommerceAccountGroupCommerceAccountRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountGroupCommerceAccountRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceAccountGroupCommerceAccountRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceAccountGroupCommerceAccountRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _externalReferenceCode;
	private long _commerceAccountGroupCommerceAccountRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceAccountGroupId;
	private long _commerceAccountId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceAccountGroupCommerceAccountRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceAccountGroupCommerceAccountRel)this);
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
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put(
			"CAccountGroupCAccountRelId",
			_commerceAccountGroupCommerceAccountRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"commerceAccountGroupId", _commerceAccountGroupId);
		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"CAccountGroupCAccountRelId",
			"commerceAccountGroupCommerceAccountRelId");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("externalReferenceCode", 1L);

		columnBitmasks.put("CAccountGroupCAccountRelId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("commerceAccountGroupId", 128L);

		columnBitmasks.put("commerceAccountId", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceAccountGroupCommerceAccountRel _escapedModel;

}