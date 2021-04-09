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

import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.model.CommerceAccountOrganizationRelModel;
import com.liferay.commerce.account.model.CommerceAccountOrganizationRelSoap;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
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
 * The base model implementation for the CommerceAccountOrganizationRel service. Represents a row in the &quot;CommerceAccountOrganizationRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceAccountOrganizationRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAccountOrganizationRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountOrganizationRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceAccountOrganizationRelModelImpl
	extends BaseModelImpl<CommerceAccountOrganizationRel>
	implements CommerceAccountOrganizationRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce account organization rel model instance should use the <code>CommerceAccountOrganizationRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceAccountOrganizationRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceAccountId", Types.BIGINT}, {"organizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("organizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceAccountOrganizationRel (commerceAccountId LONG not null,organizationId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,primary key (commerceAccountId, organizationId))";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceAccountOrganizationRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceAccountOrganizationRel.userId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceAccountOrganizationRel.userId ASC";

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
	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ORGANIZATIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceAccountOrganizationRel toModel(
		CommerceAccountOrganizationRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceAccountOrganizationRel model =
			new CommerceAccountOrganizationRelImpl();

		model.setCommerceAccountId(soapModel.getCommerceAccountId());
		model.setOrganizationId(soapModel.getOrganizationId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());

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
	public static List<CommerceAccountOrganizationRel> toModels(
		CommerceAccountOrganizationRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceAccountOrganizationRel> models =
			new ArrayList<CommerceAccountOrganizationRel>(soapModels.length);

		for (CommerceAccountOrganizationRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.account.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.account.model.CommerceAccountOrganizationRel"));

	public CommerceAccountOrganizationRelModelImpl() {
	}

	@Override
	public CommerceAccountOrganizationRelPK getPrimaryKey() {
		return new CommerceAccountOrganizationRelPK(
			_commerceAccountId, _organizationId);
	}

	@Override
	public void setPrimaryKey(CommerceAccountOrganizationRelPK primaryKey) {
		setCommerceAccountId(primaryKey.commerceAccountId);
		setOrganizationId(primaryKey.organizationId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new CommerceAccountOrganizationRelPK(
			_commerceAccountId, _organizationId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((CommerceAccountOrganizationRelPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccountOrganizationRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccountOrganizationRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceAccountOrganizationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceAccountOrganizationRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountOrganizationRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceAccountOrganizationRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceAccountOrganizationRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceAccountOrganizationRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceAccountOrganizationRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceAccountOrganizationRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceAccountOrganizationRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceAccountOrganizationRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceAccountOrganizationRel.class.getClassLoader(),
			CommerceAccountOrganizationRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceAccountOrganizationRel> constructor =
				(Constructor<CommerceAccountOrganizationRel>)
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
		<String, Function<CommerceAccountOrganizationRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceAccountOrganizationRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceAccountOrganizationRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceAccountOrganizationRel, Object>>();
		Map<String, BiConsumer<CommerceAccountOrganizationRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceAccountOrganizationRel, ?>>();

		attributeGetterFunctions.put(
			"commerceAccountId",
			CommerceAccountOrganizationRel::getCommerceAccountId);
		attributeSetterBiConsumers.put(
			"commerceAccountId",
			(BiConsumer<CommerceAccountOrganizationRel, Long>)
				CommerceAccountOrganizationRel::setCommerceAccountId);
		attributeGetterFunctions.put(
			"organizationId",
			CommerceAccountOrganizationRel::getOrganizationId);
		attributeSetterBiConsumers.put(
			"organizationId",
			(BiConsumer<CommerceAccountOrganizationRel, Long>)
				CommerceAccountOrganizationRel::setOrganizationId);
		attributeGetterFunctions.put(
			"companyId", CommerceAccountOrganizationRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceAccountOrganizationRel, Long>)
				CommerceAccountOrganizationRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceAccountOrganizationRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceAccountOrganizationRel, Long>)
				CommerceAccountOrganizationRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceAccountOrganizationRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceAccountOrganizationRel, String>)
				CommerceAccountOrganizationRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceAccountOrganizationRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceAccountOrganizationRel, Date>)
				CommerceAccountOrganizationRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceAccountOrganizationRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceAccountOrganizationRel, Date>)
				CommerceAccountOrganizationRel::setModifiedDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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

	@JSON
	@Override
	public long getOrganizationId() {
		return _organizationId;
	}

	@Override
	public void setOrganizationId(long organizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_organizationId = organizationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOrganizationId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("organizationId"));
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
	public CommerceAccountOrganizationRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceAccountOrganizationRel>
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
		CommerceAccountOrganizationRelImpl commerceAccountOrganizationRelImpl =
			new CommerceAccountOrganizationRelImpl();

		commerceAccountOrganizationRelImpl.setCommerceAccountId(
			getCommerceAccountId());
		commerceAccountOrganizationRelImpl.setOrganizationId(
			getOrganizationId());
		commerceAccountOrganizationRelImpl.setCompanyId(getCompanyId());
		commerceAccountOrganizationRelImpl.setUserId(getUserId());
		commerceAccountOrganizationRelImpl.setUserName(getUserName());
		commerceAccountOrganizationRelImpl.setCreateDate(getCreateDate());
		commerceAccountOrganizationRelImpl.setModifiedDate(getModifiedDate());

		commerceAccountOrganizationRelImpl.resetOriginalValues();

		return commerceAccountOrganizationRelImpl;
	}

	@Override
	public int compareTo(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel) {

		int value = 0;

		if (getUserId() < commerceAccountOrganizationRel.getUserId()) {
			value = -1;
		}
		else if (getUserId() > commerceAccountOrganizationRel.getUserId()) {
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

		if (!(object instanceof CommerceAccountOrganizationRel)) {
			return false;
		}

		CommerceAccountOrganizationRel commerceAccountOrganizationRel =
			(CommerceAccountOrganizationRel)object;

		CommerceAccountOrganizationRelPK primaryKey =
			commerceAccountOrganizationRel.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
	public CacheModel<CommerceAccountOrganizationRel> toCacheModel() {
		CommerceAccountOrganizationRelCacheModel
			commerceAccountOrganizationRelCacheModel =
				new CommerceAccountOrganizationRelCacheModel();

		commerceAccountOrganizationRelCacheModel.
			commerceAccountOrganizationRelPK = getPrimaryKey();

		commerceAccountOrganizationRelCacheModel.commerceAccountId =
			getCommerceAccountId();

		commerceAccountOrganizationRelCacheModel.organizationId =
			getOrganizationId();

		commerceAccountOrganizationRelCacheModel.companyId = getCompanyId();

		commerceAccountOrganizationRelCacheModel.userId = getUserId();

		commerceAccountOrganizationRelCacheModel.userName = getUserName();

		String userName = commerceAccountOrganizationRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAccountOrganizationRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAccountOrganizationRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceAccountOrganizationRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAccountOrganizationRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceAccountOrganizationRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		return commerceAccountOrganizationRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceAccountOrganizationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceAccountOrganizationRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountOrganizationRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceAccountOrganizationRel)this));
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
		Map<String, Function<CommerceAccountOrganizationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceAccountOrganizationRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceAccountOrganizationRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceAccountOrganizationRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceAccountOrganizationRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceAccountId;
	private long _organizationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;

	public <T> T getColumnValue(String columnName) {
		Function<CommerceAccountOrganizationRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceAccountOrganizationRel)this);
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

		_columnOriginalValues.put("commerceAccountId", _commerceAccountId);
		_columnOriginalValues.put("organizationId", _organizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("commerceAccountId", 1L);

		columnBitmasks.put("organizationId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceAccountOrganizationRel _escapedModel;

}