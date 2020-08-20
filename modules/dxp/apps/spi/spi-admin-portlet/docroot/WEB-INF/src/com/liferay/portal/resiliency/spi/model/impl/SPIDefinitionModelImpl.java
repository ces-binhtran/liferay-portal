/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.resiliency.spi.model.impl;

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
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.model.SPIDefinitionModel;
import com.liferay.portal.resiliency.spi.model.SPIDefinitionSoap;

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
 * The base model implementation for the SPIDefinition service. Represents a row in the &quot;SPIDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SPIDefinitionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SPIDefinitionImpl}.
 * </p>
 *
 * @author Michael C. Han
 * @see SPIDefinitionImpl
 * @generated
 */
@JSON(strict = true)
public class SPIDefinitionModelImpl
	extends BaseModelImpl<SPIDefinition> implements SPIDefinitionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a spi definition model instance should use the <code>SPIDefinition</code> interface instead.
	 */
	public static final String TABLE_NAME = "SPIDefinition";

	public static final Object[][] TABLE_COLUMNS = {
		{"spiDefinitionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"connectorAddress", Types.VARCHAR},
		{"connectorPort", Types.INTEGER}, {"description", Types.VARCHAR},
		{"jvmArguments", Types.VARCHAR}, {"portletIds", Types.VARCHAR},
		{"servletContextNames", Types.VARCHAR}, {"typeSettings", Types.CLOB},
		{"status", Types.INTEGER}, {"statusMessage", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("spiDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("connectorAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("connectorPort", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("jvmArguments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("servletContextNames", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusMessage", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SPIDefinition (spiDefinitionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(200) null,connectorAddress VARCHAR(200) null,connectorPort INTEGER,description STRING null,jvmArguments STRING null,portletIds STRING null,servletContextNames STRING null,typeSettings TEXT null,status INTEGER,statusMessage STRING null)";

	public static final String TABLE_SQL_DROP = "drop table SPIDefinition";

	public static final String ORDER_BY_JPQL =
		" ORDER BY spiDefinition.spiDefinitionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SPIDefinition.spiDefinitionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.resiliency.spi.model.SPIDefinition"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.resiliency.spi.model.SPIDefinition"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.util.service.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.resiliency.spi.model.SPIDefinition"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long CONNECTORADDRESS_COLUMN_BITMASK = 2L;

	public static final long CONNECTORPORT_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long STATUS_COLUMN_BITMASK = 16L;

	public static final long SPIDEFINITIONID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SPIDefinition toModel(SPIDefinitionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SPIDefinition model = new SPIDefinitionImpl();

		model.setSpiDefinitionId(soapModel.getSpiDefinitionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setConnectorAddress(soapModel.getConnectorAddress());
		model.setConnectorPort(soapModel.getConnectorPort());
		model.setDescription(soapModel.getDescription());
		model.setJvmArguments(soapModel.getJvmArguments());
		model.setPortletIds(soapModel.getPortletIds());
		model.setServletContextNames(soapModel.getServletContextNames());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setStatus(soapModel.getStatus());
		model.setStatusMessage(soapModel.getStatusMessage());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SPIDefinition> toModels(SPIDefinitionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SPIDefinition> models = new ArrayList<SPIDefinition>(
			soapModels.length);

		for (SPIDefinitionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.util.service.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.resiliency.spi.model.SPIDefinition"));

	public SPIDefinitionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _spiDefinitionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSpiDefinitionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _spiDefinitionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SPIDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return SPIDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SPIDefinition, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SPIDefinition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SPIDefinition, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SPIDefinition)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SPIDefinition, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SPIDefinition, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SPIDefinition)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SPIDefinition, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SPIDefinition, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SPIDefinition>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SPIDefinition.class.getClassLoader(), SPIDefinition.class,
			ModelWrapper.class);

		try {
			Constructor<SPIDefinition> constructor =
				(Constructor<SPIDefinition>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SPIDefinition, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SPIDefinition, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SPIDefinition, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SPIDefinition, Object>>();
		Map<String, BiConsumer<SPIDefinition, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SPIDefinition, ?>>();

		attributeGetterFunctions.put(
			"spiDefinitionId", SPIDefinition::getSpiDefinitionId);
		attributeSetterBiConsumers.put(
			"spiDefinitionId",
			(BiConsumer<SPIDefinition, Long>)SPIDefinition::setSpiDefinitionId);
		attributeGetterFunctions.put("companyId", SPIDefinition::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SPIDefinition, Long>)SPIDefinition::setCompanyId);
		attributeGetterFunctions.put("userId", SPIDefinition::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<SPIDefinition, Long>)SPIDefinition::setUserId);
		attributeGetterFunctions.put("userName", SPIDefinition::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<SPIDefinition, String>)SPIDefinition::setUserName);
		attributeGetterFunctions.put(
			"createDate", SPIDefinition::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SPIDefinition, Date>)SPIDefinition::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", SPIDefinition::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<SPIDefinition, Date>)SPIDefinition::setModifiedDate);
		attributeGetterFunctions.put("name", SPIDefinition::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<SPIDefinition, String>)SPIDefinition::setName);
		attributeGetterFunctions.put(
			"connectorAddress", SPIDefinition::getConnectorAddress);
		attributeSetterBiConsumers.put(
			"connectorAddress",
			(BiConsumer<SPIDefinition, String>)
				SPIDefinition::setConnectorAddress);
		attributeGetterFunctions.put(
			"connectorPort", SPIDefinition::getConnectorPort);
		attributeSetterBiConsumers.put(
			"connectorPort",
			(BiConsumer<SPIDefinition, Integer>)
				SPIDefinition::setConnectorPort);
		attributeGetterFunctions.put(
			"description", SPIDefinition::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<SPIDefinition, String>)SPIDefinition::setDescription);
		attributeGetterFunctions.put(
			"jvmArguments", SPIDefinition::getJvmArguments);
		attributeSetterBiConsumers.put(
			"jvmArguments",
			(BiConsumer<SPIDefinition, String>)SPIDefinition::setJvmArguments);
		attributeGetterFunctions.put(
			"portletIds", SPIDefinition::getPortletIds);
		attributeSetterBiConsumers.put(
			"portletIds",
			(BiConsumer<SPIDefinition, String>)SPIDefinition::setPortletIds);
		attributeGetterFunctions.put(
			"servletContextNames", SPIDefinition::getServletContextNames);
		attributeSetterBiConsumers.put(
			"servletContextNames",
			(BiConsumer<SPIDefinition, String>)
				SPIDefinition::setServletContextNames);
		attributeGetterFunctions.put(
			"typeSettings", SPIDefinition::getTypeSettings);
		attributeSetterBiConsumers.put(
			"typeSettings",
			(BiConsumer<SPIDefinition, String>)SPIDefinition::setTypeSettings);
		attributeGetterFunctions.put("status", SPIDefinition::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<SPIDefinition, Integer>)SPIDefinition::setStatus);
		attributeGetterFunctions.put(
			"statusMessage", SPIDefinition::getStatusMessage);
		attributeSetterBiConsumers.put(
			"statusMessage",
			(BiConsumer<SPIDefinition, String>)SPIDefinition::setStatusMessage);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getSpiDefinitionId() {
		return _spiDefinitionId;
	}

	@Override
	public void setSpiDefinitionId(long spiDefinitionId) {
		_spiDefinitionId = spiDefinitionId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
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
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getConnectorAddress() {
		if (_connectorAddress == null) {
			return "";
		}
		else {
			return _connectorAddress;
		}
	}

	@Override
	public void setConnectorAddress(String connectorAddress) {
		_columnBitmask |= CONNECTORADDRESS_COLUMN_BITMASK;

		if (_originalConnectorAddress == null) {
			_originalConnectorAddress = _connectorAddress;
		}

		_connectorAddress = connectorAddress;
	}

	public String getOriginalConnectorAddress() {
		return GetterUtil.getString(_originalConnectorAddress);
	}

	@JSON
	@Override
	public int getConnectorPort() {
		return _connectorPort;
	}

	@Override
	public void setConnectorPort(int connectorPort) {
		_columnBitmask |= CONNECTORPORT_COLUMN_BITMASK;

		if (!_setOriginalConnectorPort) {
			_setOriginalConnectorPort = true;

			_originalConnectorPort = _connectorPort;
		}

		_connectorPort = connectorPort;
	}

	public int getOriginalConnectorPort() {
		return _originalConnectorPort;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getJvmArguments() {
		if (_jvmArguments == null) {
			return "";
		}
		else {
			return _jvmArguments;
		}
	}

	@Override
	public void setJvmArguments(String jvmArguments) {
		_jvmArguments = jvmArguments;
	}

	@JSON
	@Override
	public String getPortletIds() {
		if (_portletIds == null) {
			return "";
		}
		else {
			return _portletIds;
		}
	}

	@Override
	public void setPortletIds(String portletIds) {
		_portletIds = portletIds;
	}

	@JSON
	@Override
	public String getServletContextNames() {
		if (_servletContextNames == null) {
			return "";
		}
		else {
			return _servletContextNames;
		}
	}

	@Override
	public void setServletContextNames(String servletContextNames) {
		_servletContextNames = servletContextNames;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public String getStatusMessage() {
		if (_statusMessage == null) {
			return "";
		}
		else {
			return _statusMessage;
		}
	}

	@Override
	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SPIDefinition.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SPIDefinition toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SPIDefinition>
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
		SPIDefinitionImpl spiDefinitionImpl = new SPIDefinitionImpl();

		spiDefinitionImpl.setSpiDefinitionId(getSpiDefinitionId());
		spiDefinitionImpl.setCompanyId(getCompanyId());
		spiDefinitionImpl.setUserId(getUserId());
		spiDefinitionImpl.setUserName(getUserName());
		spiDefinitionImpl.setCreateDate(getCreateDate());
		spiDefinitionImpl.setModifiedDate(getModifiedDate());
		spiDefinitionImpl.setName(getName());
		spiDefinitionImpl.setConnectorAddress(getConnectorAddress());
		spiDefinitionImpl.setConnectorPort(getConnectorPort());
		spiDefinitionImpl.setDescription(getDescription());
		spiDefinitionImpl.setJvmArguments(getJvmArguments());
		spiDefinitionImpl.setPortletIds(getPortletIds());
		spiDefinitionImpl.setServletContextNames(getServletContextNames());
		spiDefinitionImpl.setTypeSettings(getTypeSettings());
		spiDefinitionImpl.setStatus(getStatus());
		spiDefinitionImpl.setStatusMessage(getStatusMessage());

		spiDefinitionImpl.resetOriginalValues();

		return spiDefinitionImpl;
	}

	@Override
	public int compareTo(SPIDefinition spiDefinition) {
		int value = 0;

		if (getSpiDefinitionId() < spiDefinition.getSpiDefinitionId()) {
			value = -1;
		}
		else if (getSpiDefinitionId() > spiDefinition.getSpiDefinitionId()) {
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

		if (!(object instanceof SPIDefinition)) {
			return false;
		}

		SPIDefinition spiDefinition = (SPIDefinition)object;

		long primaryKey = spiDefinition.getPrimaryKey();

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
		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;
		_originalName = _name;

		_originalConnectorAddress = _connectorAddress;

		_originalConnectorPort = _connectorPort;

		_setOriginalConnectorPort = false;

		_originalStatus = _status;

		_setOriginalStatus = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SPIDefinition> toCacheModel() {
		SPIDefinitionCacheModel spiDefinitionCacheModel =
			new SPIDefinitionCacheModel();

		spiDefinitionCacheModel.spiDefinitionId = getSpiDefinitionId();

		spiDefinitionCacheModel.companyId = getCompanyId();

		spiDefinitionCacheModel.userId = getUserId();

		spiDefinitionCacheModel.userName = getUserName();

		String userName = spiDefinitionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			spiDefinitionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			spiDefinitionCacheModel.createDate = createDate.getTime();
		}
		else {
			spiDefinitionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			spiDefinitionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			spiDefinitionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		spiDefinitionCacheModel.name = getName();

		String name = spiDefinitionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			spiDefinitionCacheModel.name = null;
		}

		spiDefinitionCacheModel.connectorAddress = getConnectorAddress();

		String connectorAddress = spiDefinitionCacheModel.connectorAddress;

		if ((connectorAddress != null) && (connectorAddress.length() == 0)) {
			spiDefinitionCacheModel.connectorAddress = null;
		}

		spiDefinitionCacheModel.connectorPort = getConnectorPort();

		spiDefinitionCacheModel.description = getDescription();

		String description = spiDefinitionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			spiDefinitionCacheModel.description = null;
		}

		spiDefinitionCacheModel.jvmArguments = getJvmArguments();

		String jvmArguments = spiDefinitionCacheModel.jvmArguments;

		if ((jvmArguments != null) && (jvmArguments.length() == 0)) {
			spiDefinitionCacheModel.jvmArguments = null;
		}

		spiDefinitionCacheModel.portletIds = getPortletIds();

		String portletIds = spiDefinitionCacheModel.portletIds;

		if ((portletIds != null) && (portletIds.length() == 0)) {
			spiDefinitionCacheModel.portletIds = null;
		}

		spiDefinitionCacheModel.servletContextNames = getServletContextNames();

		String servletContextNames =
			spiDefinitionCacheModel.servletContextNames;

		if ((servletContextNames != null) &&
			(servletContextNames.length() == 0)) {

			spiDefinitionCacheModel.servletContextNames = null;
		}

		spiDefinitionCacheModel.typeSettings = getTypeSettings();

		String typeSettings = spiDefinitionCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			spiDefinitionCacheModel.typeSettings = null;
		}

		spiDefinitionCacheModel.status = getStatus();

		spiDefinitionCacheModel.statusMessage = getStatusMessage();

		String statusMessage = spiDefinitionCacheModel.statusMessage;

		if ((statusMessage != null) && (statusMessage.length() == 0)) {
			spiDefinitionCacheModel.statusMessage = null;
		}

		return spiDefinitionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SPIDefinition, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SPIDefinition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SPIDefinition, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SPIDefinition)this));
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
		Map<String, Function<SPIDefinition, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SPIDefinition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SPIDefinition, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SPIDefinition)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SPIDefinition>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _spiDefinitionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private String _connectorAddress;
	private String _originalConnectorAddress;
	private int _connectorPort;
	private int _originalConnectorPort;
	private boolean _setOriginalConnectorPort;
	private String _description;
	private String _jvmArguments;
	private String _portletIds;
	private String _servletContextNames;
	private String _typeSettings;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private String _statusMessage;
	private long _columnBitmask;
	private SPIDefinition _escapedModel;

}