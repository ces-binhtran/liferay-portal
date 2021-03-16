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

package com.liferay.commerce.data.integration.model.impl;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLogModel;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLogSoap;
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
 * The base model implementation for the CommerceDataIntegrationProcessLog service. Represents a row in the &quot;CDataIntegrationProcessLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceDataIntegrationProcessLogModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceDataIntegrationProcessLogImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceDataIntegrationProcessLogModelImpl
	extends BaseModelImpl<CommerceDataIntegrationProcessLog>
	implements CommerceDataIntegrationProcessLogModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce data integration process log model instance should use the <code>CommerceDataIntegrationProcessLog</code> interface instead.
	 */
	public static final String TABLE_NAME = "CDataIntegrationProcessLog";

	public static final Object[][] TABLE_COLUMNS = {
		{"CDataIntegrationProcessLogId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"CDataIntegrationProcessId", Types.BIGINT}, {"error", Types.CLOB},
		{"output_", Types.CLOB}, {"startDate", Types.TIMESTAMP},
		{"endDate", Types.TIMESTAMP}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CDataIntegrationProcessLogId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CDataIntegrationProcessId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("error", Types.CLOB);
		TABLE_COLUMNS_MAP.put("output_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CDataIntegrationProcessLog (CDataIntegrationProcessLogId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CDataIntegrationProcessId LONG,error TEXT null,output_ TEXT null,startDate DATE null,endDate DATE null,status INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CDataIntegrationProcessLog";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceDataIntegrationProcessLog.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CDataIntegrationProcessLog.modifiedDate DESC";

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
	public static final long CDATAINTEGRATIONPROCESSID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceDataIntegrationProcessLog toModel(
		CommerceDataIntegrationProcessLogSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceDataIntegrationProcessLog model =
			new CommerceDataIntegrationProcessLogImpl();

		model.setCommerceDataIntegrationProcessLogId(
			soapModel.getCommerceDataIntegrationProcessLogId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCDataIntegrationProcessId(
			soapModel.getCDataIntegrationProcessId());
		model.setError(soapModel.getError());
		model.setOutput(soapModel.getOutput());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setStatus(soapModel.getStatus());

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
	public static List<CommerceDataIntegrationProcessLog> toModels(
		CommerceDataIntegrationProcessLogSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceDataIntegrationProcessLog> models =
			new ArrayList<CommerceDataIntegrationProcessLog>(soapModels.length);

		for (CommerceDataIntegrationProcessLogSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.data.integration.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog"));

	public CommerceDataIntegrationProcessLogModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceDataIntegrationProcessLogId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceDataIntegrationProcessLogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceDataIntegrationProcessLogId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceDataIntegrationProcessLog.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceDataIntegrationProcessLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceDataIntegrationProcessLog, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommerceDataIntegrationProcessLog, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDataIntegrationProcessLog, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceDataIntegrationProcessLog)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceDataIntegrationProcessLog, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceDataIntegrationProcessLog, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceDataIntegrationProcessLog)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceDataIntegrationProcessLog, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceDataIntegrationProcessLog, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function
		<InvocationHandler, CommerceDataIntegrationProcessLog>
			_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceDataIntegrationProcessLog.class.getClassLoader(),
			CommerceDataIntegrationProcessLog.class, ModelWrapper.class);

		try {
			Constructor<CommerceDataIntegrationProcessLog> constructor =
				(Constructor<CommerceDataIntegrationProcessLog>)
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
		<String, Function<CommerceDataIntegrationProcessLog, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceDataIntegrationProcessLog, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceDataIntegrationProcessLog, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceDataIntegrationProcessLog, Object>>();
		Map<String, BiConsumer<CommerceDataIntegrationProcessLog, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String,
					 BiConsumer<CommerceDataIntegrationProcessLog, ?>>();

		attributeGetterFunctions.put(
			"commerceDataIntegrationProcessLogId",
			CommerceDataIntegrationProcessLog::
				getCommerceDataIntegrationProcessLogId);
		attributeSetterBiConsumers.put(
			"commerceDataIntegrationProcessLogId",
			(BiConsumer<CommerceDataIntegrationProcessLog, Long>)
				CommerceDataIntegrationProcessLog::
					setCommerceDataIntegrationProcessLogId);
		attributeGetterFunctions.put(
			"companyId", CommerceDataIntegrationProcessLog::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceDataIntegrationProcessLog, Long>)
				CommerceDataIntegrationProcessLog::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceDataIntegrationProcessLog::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceDataIntegrationProcessLog, Long>)
				CommerceDataIntegrationProcessLog::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceDataIntegrationProcessLog::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceDataIntegrationProcessLog, String>)
				CommerceDataIntegrationProcessLog::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceDataIntegrationProcessLog::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceDataIntegrationProcessLog, Date>)
				CommerceDataIntegrationProcessLog::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceDataIntegrationProcessLog::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceDataIntegrationProcessLog, Date>)
				CommerceDataIntegrationProcessLog::setModifiedDate);
		attributeGetterFunctions.put(
			"CDataIntegrationProcessId",
			CommerceDataIntegrationProcessLog::getCDataIntegrationProcessId);
		attributeSetterBiConsumers.put(
			"CDataIntegrationProcessId",
			(BiConsumer<CommerceDataIntegrationProcessLog, Long>)
				CommerceDataIntegrationProcessLog::
					setCDataIntegrationProcessId);
		attributeGetterFunctions.put(
			"error", CommerceDataIntegrationProcessLog::getError);
		attributeSetterBiConsumers.put(
			"error",
			(BiConsumer<CommerceDataIntegrationProcessLog, String>)
				CommerceDataIntegrationProcessLog::setError);
		attributeGetterFunctions.put(
			"output", CommerceDataIntegrationProcessLog::getOutput);
		attributeSetterBiConsumers.put(
			"output",
			(BiConsumer<CommerceDataIntegrationProcessLog, String>)
				CommerceDataIntegrationProcessLog::setOutput);
		attributeGetterFunctions.put(
			"startDate", CommerceDataIntegrationProcessLog::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate",
			(BiConsumer<CommerceDataIntegrationProcessLog, Date>)
				CommerceDataIntegrationProcessLog::setStartDate);
		attributeGetterFunctions.put(
			"endDate", CommerceDataIntegrationProcessLog::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate",
			(BiConsumer<CommerceDataIntegrationProcessLog, Date>)
				CommerceDataIntegrationProcessLog::setEndDate);
		attributeGetterFunctions.put(
			"status", CommerceDataIntegrationProcessLog::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<CommerceDataIntegrationProcessLog, Integer>)
				CommerceDataIntegrationProcessLog::setStatus);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceDataIntegrationProcessLogId() {
		return _commerceDataIntegrationProcessLogId;
	}

	@Override
	public void setCommerceDataIntegrationProcessLogId(
		long commerceDataIntegrationProcessLogId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceDataIntegrationProcessLogId =
			commerceDataIntegrationProcessLogId;
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
	public long getCDataIntegrationProcessId() {
		return _CDataIntegrationProcessId;
	}

	@Override
	public void setCDataIntegrationProcessId(long CDataIntegrationProcessId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CDataIntegrationProcessId = CDataIntegrationProcessId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCDataIntegrationProcessId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CDataIntegrationProcessId"));
	}

	@JSON
	@Override
	public String getError() {
		if (_error == null) {
			return "";
		}
		else {
			return _error;
		}
	}

	@Override
	public void setError(String error) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_error = error;
	}

	@JSON
	@Override
	public String getOutput() {
		if (_output == null) {
			return "";
		}
		else {
			return _output;
		}
	}

	@Override
	public void setOutput(String output) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_output = output;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_endDate = endDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
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
			getCompanyId(), CommerceDataIntegrationProcessLog.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceDataIntegrationProcessLog toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceDataIntegrationProcessLog>
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
		CommerceDataIntegrationProcessLogImpl
			commerceDataIntegrationProcessLogImpl =
				new CommerceDataIntegrationProcessLogImpl();

		commerceDataIntegrationProcessLogImpl.
			setCommerceDataIntegrationProcessLogId(
				getCommerceDataIntegrationProcessLogId());
		commerceDataIntegrationProcessLogImpl.setCompanyId(getCompanyId());
		commerceDataIntegrationProcessLogImpl.setUserId(getUserId());
		commerceDataIntegrationProcessLogImpl.setUserName(getUserName());
		commerceDataIntegrationProcessLogImpl.setCreateDate(getCreateDate());
		commerceDataIntegrationProcessLogImpl.setModifiedDate(
			getModifiedDate());
		commerceDataIntegrationProcessLogImpl.setCDataIntegrationProcessId(
			getCDataIntegrationProcessId());
		commerceDataIntegrationProcessLogImpl.setError(getError());
		commerceDataIntegrationProcessLogImpl.setOutput(getOutput());
		commerceDataIntegrationProcessLogImpl.setStartDate(getStartDate());
		commerceDataIntegrationProcessLogImpl.setEndDate(getEndDate());
		commerceDataIntegrationProcessLogImpl.setStatus(getStatus());

		commerceDataIntegrationProcessLogImpl.resetOriginalValues();

		return commerceDataIntegrationProcessLogImpl;
	}

	@Override
	public int compareTo(
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog) {

		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(),
			commerceDataIntegrationProcessLog.getModifiedDate());

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

		if (!(object instanceof CommerceDataIntegrationProcessLog)) {
			return false;
		}

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			(CommerceDataIntegrationProcessLog)object;

		long primaryKey = commerceDataIntegrationProcessLog.getPrimaryKey();

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
	public CacheModel<CommerceDataIntegrationProcessLog> toCacheModel() {
		CommerceDataIntegrationProcessLogCacheModel
			commerceDataIntegrationProcessLogCacheModel =
				new CommerceDataIntegrationProcessLogCacheModel();

		commerceDataIntegrationProcessLogCacheModel.
			commerceDataIntegrationProcessLogId =
				getCommerceDataIntegrationProcessLogId();

		commerceDataIntegrationProcessLogCacheModel.companyId = getCompanyId();

		commerceDataIntegrationProcessLogCacheModel.userId = getUserId();

		commerceDataIntegrationProcessLogCacheModel.userName = getUserName();

		String userName = commerceDataIntegrationProcessLogCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceDataIntegrationProcessLogCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceDataIntegrationProcessLogCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceDataIntegrationProcessLogCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceDataIntegrationProcessLogCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceDataIntegrationProcessLogCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceDataIntegrationProcessLogCacheModel.CDataIntegrationProcessId =
			getCDataIntegrationProcessId();

		commerceDataIntegrationProcessLogCacheModel.error = getError();

		String error = commerceDataIntegrationProcessLogCacheModel.error;

		if ((error != null) && (error.length() == 0)) {
			commerceDataIntegrationProcessLogCacheModel.error = null;
		}

		commerceDataIntegrationProcessLogCacheModel.output = getOutput();

		String output = commerceDataIntegrationProcessLogCacheModel.output;

		if ((output != null) && (output.length() == 0)) {
			commerceDataIntegrationProcessLogCacheModel.output = null;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			commerceDataIntegrationProcessLogCacheModel.startDate =
				startDate.getTime();
		}
		else {
			commerceDataIntegrationProcessLogCacheModel.startDate =
				Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			commerceDataIntegrationProcessLogCacheModel.endDate =
				endDate.getTime();
		}
		else {
			commerceDataIntegrationProcessLogCacheModel.endDate =
				Long.MIN_VALUE;
		}

		commerceDataIntegrationProcessLogCacheModel.status = getStatus();

		return commerceDataIntegrationProcessLogCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceDataIntegrationProcessLog, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommerceDataIntegrationProcessLog, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDataIntegrationProcessLog, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceDataIntegrationProcessLog)this));
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
		Map<String, Function<CommerceDataIntegrationProcessLog, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String, Function<CommerceDataIntegrationProcessLog, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDataIntegrationProcessLog, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceDataIntegrationProcessLog)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceDataIntegrationProcessLog>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceDataIntegrationProcessLogId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CDataIntegrationProcessId;
	private String _error;
	private String _output;
	private Date _startDate;
	private Date _endDate;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceDataIntegrationProcessLog, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceDataIntegrationProcessLog)this);
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
			"CDataIntegrationProcessLogId",
			_commerceDataIntegrationProcessLogId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"CDataIntegrationProcessId", _CDataIntegrationProcessId);
		_columnOriginalValues.put("error", _error);
		_columnOriginalValues.put("output_", _output);
		_columnOriginalValues.put("startDate", _startDate);
		_columnOriginalValues.put("endDate", _endDate);
		_columnOriginalValues.put("status", _status);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"CDataIntegrationProcessLogId",
			"commerceDataIntegrationProcessLogId");
		attributeNames.put("output_", "output");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("CDataIntegrationProcessLogId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("CDataIntegrationProcessId", 64L);

		columnBitmasks.put("error", 128L);

		columnBitmasks.put("output_", 256L);

		columnBitmasks.put("startDate", 512L);

		columnBitmasks.put("endDate", 1024L);

		columnBitmasks.put("status", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceDataIntegrationProcessLog _escapedModel;

}