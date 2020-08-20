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

package com.liferay.powwow.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.model.PowwowServerModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the PowwowServer service. Represents a row in the &quot;PowwowServer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PowwowServerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PowwowServerImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see PowwowServerImpl
 * @generated
 */
public class PowwowServerModelImpl
	extends BaseModelImpl<PowwowServer> implements PowwowServerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a powwow server model instance should use the <code>PowwowServer</code> interface instead.
	 */
	public static final String TABLE_NAME = "PowwowServer";

	public static final Object[][] TABLE_COLUMNS = {
		{"powwowServerId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"providerType", Types.VARCHAR},
		{"url", Types.VARCHAR}, {"apiKey", Types.VARCHAR},
		{"secret", Types.VARCHAR}, {"active_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("powwowServerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("providerType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("apiKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("secret", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PowwowServer (powwowServerId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,providerType VARCHAR(75) null,url STRING null,apiKey VARCHAR(75) null,secret VARCHAR(75) null,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table PowwowServer";

	public static final String ORDER_BY_JPQL =
		" ORDER BY powwowServer.name ASC";

	public static final String ORDER_BY_SQL = " ORDER BY PowwowServer.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.powwow.model.PowwowServer"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.powwow.model.PowwowServer"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.powwow.model.PowwowServer"),
		true);

	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	public static final long PROVIDERTYPE_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.util.service.ServiceProps.get(
			"lock.expiration.time.com.liferay.powwow.model.PowwowServer"));

	public PowwowServerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _powwowServerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPowwowServerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _powwowServerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PowwowServer.class;
	}

	@Override
	public String getModelClassName() {
		return PowwowServer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PowwowServer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PowwowServer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PowwowServer, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PowwowServer)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PowwowServer, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PowwowServer, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PowwowServer)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PowwowServer, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PowwowServer, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PowwowServer>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PowwowServer.class.getClassLoader(), PowwowServer.class,
			ModelWrapper.class);

		try {
			Constructor<PowwowServer> constructor =
				(Constructor<PowwowServer>)proxyClass.getConstructor(
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

	private static final Map<String, Function<PowwowServer, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PowwowServer, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PowwowServer, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<PowwowServer, Object>>();
		Map<String, BiConsumer<PowwowServer, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PowwowServer, ?>>();

		attributeGetterFunctions.put(
			"powwowServerId", PowwowServer::getPowwowServerId);
		attributeSetterBiConsumers.put(
			"powwowServerId",
			(BiConsumer<PowwowServer, Long>)PowwowServer::setPowwowServerId);
		attributeGetterFunctions.put("companyId", PowwowServer::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<PowwowServer, Long>)PowwowServer::setCompanyId);
		attributeGetterFunctions.put("userId", PowwowServer::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<PowwowServer, Long>)PowwowServer::setUserId);
		attributeGetterFunctions.put("userName", PowwowServer::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<PowwowServer, String>)PowwowServer::setUserName);
		attributeGetterFunctions.put("createDate", PowwowServer::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<PowwowServer, Date>)PowwowServer::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", PowwowServer::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<PowwowServer, Date>)PowwowServer::setModifiedDate);
		attributeGetterFunctions.put("name", PowwowServer::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<PowwowServer, String>)PowwowServer::setName);
		attributeGetterFunctions.put(
			"providerType", PowwowServer::getProviderType);
		attributeSetterBiConsumers.put(
			"providerType",
			(BiConsumer<PowwowServer, String>)PowwowServer::setProviderType);
		attributeGetterFunctions.put("url", PowwowServer::getUrl);
		attributeSetterBiConsumers.put(
			"url", (BiConsumer<PowwowServer, String>)PowwowServer::setUrl);
		attributeGetterFunctions.put("apiKey", PowwowServer::getApiKey);
		attributeSetterBiConsumers.put(
			"apiKey",
			(BiConsumer<PowwowServer, String>)PowwowServer::setApiKey);
		attributeGetterFunctions.put("secret", PowwowServer::getSecret);
		attributeSetterBiConsumers.put(
			"secret",
			(BiConsumer<PowwowServer, String>)PowwowServer::setSecret);
		attributeGetterFunctions.put("active", PowwowServer::getActive);
		attributeSetterBiConsumers.put(
			"active",
			(BiConsumer<PowwowServer, Boolean>)PowwowServer::setActive);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getPowwowServerId() {
		return _powwowServerId;
	}

	@Override
	public void setPowwowServerId(long powwowServerId) {
		_powwowServerId = powwowServerId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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

		_modifiedDate = modifiedDate;
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
		_name = name;
	}

	@Override
	public String getProviderType() {
		if (_providerType == null) {
			return "";
		}
		else {
			return _providerType;
		}
	}

	@Override
	public void setProviderType(String providerType) {
		_columnBitmask |= PROVIDERTYPE_COLUMN_BITMASK;

		if (_originalProviderType == null) {
			_originalProviderType = _providerType;
		}

		_providerType = providerType;
	}

	public String getOriginalProviderType() {
		return GetterUtil.getString(_originalProviderType);
	}

	@Override
	public String getUrl() {
		if (_url == null) {
			return "";
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_url = url;
	}

	@Override
	public String getApiKey() {
		if (_apiKey == null) {
			return "";
		}
		else {
			return _apiKey;
		}
	}

	@Override
	public void setApiKey(String apiKey) {
		_apiKey = apiKey;
	}

	@Override
	public String getSecret() {
		if (_secret == null) {
			return "";
		}
		else {
			return _secret;
		}
	}

	@Override
	public void setSecret(String secret) {
		_secret = secret;
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PowwowServer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PowwowServer toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PowwowServer>
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
		PowwowServerImpl powwowServerImpl = new PowwowServerImpl();

		powwowServerImpl.setPowwowServerId(getPowwowServerId());
		powwowServerImpl.setCompanyId(getCompanyId());
		powwowServerImpl.setUserId(getUserId());
		powwowServerImpl.setUserName(getUserName());
		powwowServerImpl.setCreateDate(getCreateDate());
		powwowServerImpl.setModifiedDate(getModifiedDate());
		powwowServerImpl.setName(getName());
		powwowServerImpl.setProviderType(getProviderType());
		powwowServerImpl.setUrl(getUrl());
		powwowServerImpl.setApiKey(getApiKey());
		powwowServerImpl.setSecret(getSecret());
		powwowServerImpl.setActive(isActive());

		powwowServerImpl.resetOriginalValues();

		return powwowServerImpl;
	}

	@Override
	public int compareTo(PowwowServer powwowServer) {
		int value = 0;

		value = getName().compareToIgnoreCase(powwowServer.getName());

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

		if (!(object instanceof PowwowServer)) {
			return false;
		}

		PowwowServer powwowServer = (PowwowServer)object;

		long primaryKey = powwowServer.getPrimaryKey();

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

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_setModifiedDate = false;

		_originalProviderType = _providerType;

		_originalActive = _active;

		_setOriginalActive = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<PowwowServer> toCacheModel() {
		PowwowServerCacheModel powwowServerCacheModel =
			new PowwowServerCacheModel();

		powwowServerCacheModel.powwowServerId = getPowwowServerId();

		powwowServerCacheModel.companyId = getCompanyId();

		powwowServerCacheModel.userId = getUserId();

		powwowServerCacheModel.userName = getUserName();

		String userName = powwowServerCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			powwowServerCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			powwowServerCacheModel.createDate = createDate.getTime();
		}
		else {
			powwowServerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			powwowServerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			powwowServerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		powwowServerCacheModel.name = getName();

		String name = powwowServerCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			powwowServerCacheModel.name = null;
		}

		powwowServerCacheModel.providerType = getProviderType();

		String providerType = powwowServerCacheModel.providerType;

		if ((providerType != null) && (providerType.length() == 0)) {
			powwowServerCacheModel.providerType = null;
		}

		powwowServerCacheModel.url = getUrl();

		String url = powwowServerCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			powwowServerCacheModel.url = null;
		}

		powwowServerCacheModel.apiKey = getApiKey();

		String apiKey = powwowServerCacheModel.apiKey;

		if ((apiKey != null) && (apiKey.length() == 0)) {
			powwowServerCacheModel.apiKey = null;
		}

		powwowServerCacheModel.secret = getSecret();

		String secret = powwowServerCacheModel.secret;

		if ((secret != null) && (secret.length() == 0)) {
			powwowServerCacheModel.secret = null;
		}

		powwowServerCacheModel.active = isActive();

		return powwowServerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PowwowServer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PowwowServer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PowwowServer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PowwowServer)this));
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
		Map<String, Function<PowwowServer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PowwowServer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PowwowServer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PowwowServer)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PowwowServer>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _powwowServerId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _providerType;
	private String _originalProviderType;
	private String _url;
	private String _apiKey;
	private String _secret;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private PowwowServer _escapedModel;

}