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

package com.liferay.portal.workflow.kaleo.model.impl;

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
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.model.KaleoConditionModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the KaleoCondition service. Represents a row in the &quot;KaleoCondition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>KaleoConditionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoConditionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoConditionImpl
 * @generated
 */
public class KaleoConditionModelImpl
	extends BaseModelImpl<KaleoCondition> implements KaleoConditionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo condition model instance should use the <code>KaleoCondition</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoCondition";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"kaleoConditionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"kaleoDefinitionId", Types.BIGINT},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoNodeId", Types.BIGINT}, {"script", Types.CLOB},
		{"scriptLanguage", Types.VARCHAR},
		{"scriptRequiredContexts", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoConditionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("script", Types.CLOB);
		TABLE_COLUMNS_MAP.put("scriptLanguage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("scriptRequiredContexts", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoCondition (mvccVersion LONG default 0 not null,kaleoConditionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionId LONG,kaleoDefinitionVersionId LONG,kaleoNodeId LONG,script TEXT null,scriptLanguage VARCHAR(75) null,scriptRequiredContexts STRING null)";

	public static final String TABLE_SQL_DROP = "drop table KaleoCondition";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoCondition.kaleoConditionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoCondition.kaleoConditionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEONODEID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long KALEOCONDITIONID_COLUMN_BITMASK = 8L;

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

	public KaleoConditionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoConditionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoConditionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoConditionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoCondition.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoCondition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoCondition, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoCondition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoCondition, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoCondition)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoCondition, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoCondition, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoCondition)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoCondition, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoCondition, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, KaleoCondition>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			KaleoCondition.class.getClassLoader(), KaleoCondition.class,
			ModelWrapper.class);

		try {
			Constructor<KaleoCondition> constructor =
				(Constructor<KaleoCondition>)proxyClass.getConstructor(
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

	private static final Map<String, Function<KaleoCondition, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoCondition, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoCondition, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<KaleoCondition, Object>>();
		Map<String, BiConsumer<KaleoCondition, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<KaleoCondition, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", KaleoCondition::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<KaleoCondition, Long>)KaleoCondition::setMvccVersion);
		attributeGetterFunctions.put(
			"kaleoConditionId", KaleoCondition::getKaleoConditionId);
		attributeSetterBiConsumers.put(
			"kaleoConditionId",
			(BiConsumer<KaleoCondition, Long>)
				KaleoCondition::setKaleoConditionId);
		attributeGetterFunctions.put("groupId", KaleoCondition::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<KaleoCondition, Long>)KaleoCondition::setGroupId);
		attributeGetterFunctions.put("companyId", KaleoCondition::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<KaleoCondition, Long>)KaleoCondition::setCompanyId);
		attributeGetterFunctions.put("userId", KaleoCondition::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<KaleoCondition, Long>)KaleoCondition::setUserId);
		attributeGetterFunctions.put("userName", KaleoCondition::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<KaleoCondition, String>)KaleoCondition::setUserName);
		attributeGetterFunctions.put(
			"createDate", KaleoCondition::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<KaleoCondition, Date>)KaleoCondition::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", KaleoCondition::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<KaleoCondition, Date>)KaleoCondition::setModifiedDate);
		attributeGetterFunctions.put(
			"kaleoDefinitionId", KaleoCondition::getKaleoDefinitionId);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionId",
			(BiConsumer<KaleoCondition, Long>)
				KaleoCondition::setKaleoDefinitionId);
		attributeGetterFunctions.put(
			"kaleoDefinitionVersionId",
			KaleoCondition::getKaleoDefinitionVersionId);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersionId",
			(BiConsumer<KaleoCondition, Long>)
				KaleoCondition::setKaleoDefinitionVersionId);
		attributeGetterFunctions.put(
			"kaleoNodeId", KaleoCondition::getKaleoNodeId);
		attributeSetterBiConsumers.put(
			"kaleoNodeId",
			(BiConsumer<KaleoCondition, Long>)KaleoCondition::setKaleoNodeId);
		attributeGetterFunctions.put("script", KaleoCondition::getScript);
		attributeSetterBiConsumers.put(
			"script",
			(BiConsumer<KaleoCondition, String>)KaleoCondition::setScript);
		attributeGetterFunctions.put(
			"scriptLanguage", KaleoCondition::getScriptLanguage);
		attributeSetterBiConsumers.put(
			"scriptLanguage",
			(BiConsumer<KaleoCondition, String>)
				KaleoCondition::setScriptLanguage);
		attributeGetterFunctions.put(
			"scriptRequiredContexts",
			KaleoCondition::getScriptRequiredContexts);
		attributeSetterBiConsumers.put(
			"scriptRequiredContexts",
			(BiConsumer<KaleoCondition, String>)
				KaleoCondition::setScriptRequiredContexts);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public long getKaleoConditionId() {
		return _kaleoConditionId;
	}

	@Override
	public void setKaleoConditionId(long kaleoConditionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoConditionId = kaleoConditionId;
	}

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
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoDefinitionId = kaleoDefinitionId;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalKaleoDefinitionVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("kaleoDefinitionVersionId"));
	}

	@Override
	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_kaleoNodeId = kaleoNodeId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalKaleoNodeId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("kaleoNodeId"));
	}

	@Override
	public String getScript() {
		if (_script == null) {
			return "";
		}
		else {
			return _script;
		}
	}

	@Override
	public void setScript(String script) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_script = script;
	}

	@Override
	public String getScriptLanguage() {
		if (_scriptLanguage == null) {
			return "";
		}
		else {
			return _scriptLanguage;
		}
	}

	@Override
	public void setScriptLanguage(String scriptLanguage) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_scriptLanguage = scriptLanguage;
	}

	@Override
	public String getScriptRequiredContexts() {
		if (_scriptRequiredContexts == null) {
			return "";
		}
		else {
			return _scriptRequiredContexts;
		}
	}

	@Override
	public void setScriptRequiredContexts(String scriptRequiredContexts) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_scriptRequiredContexts = scriptRequiredContexts;
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
			getCompanyId(), KaleoCondition.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoCondition toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, KaleoCondition>
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
		KaleoConditionImpl kaleoConditionImpl = new KaleoConditionImpl();

		kaleoConditionImpl.setMvccVersion(getMvccVersion());
		kaleoConditionImpl.setKaleoConditionId(getKaleoConditionId());
		kaleoConditionImpl.setGroupId(getGroupId());
		kaleoConditionImpl.setCompanyId(getCompanyId());
		kaleoConditionImpl.setUserId(getUserId());
		kaleoConditionImpl.setUserName(getUserName());
		kaleoConditionImpl.setCreateDate(getCreateDate());
		kaleoConditionImpl.setModifiedDate(getModifiedDate());
		kaleoConditionImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoConditionImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoConditionImpl.setKaleoNodeId(getKaleoNodeId());
		kaleoConditionImpl.setScript(getScript());
		kaleoConditionImpl.setScriptLanguage(getScriptLanguage());
		kaleoConditionImpl.setScriptRequiredContexts(
			getScriptRequiredContexts());

		kaleoConditionImpl.resetOriginalValues();

		return kaleoConditionImpl;
	}

	@Override
	public int compareTo(KaleoCondition kaleoCondition) {
		int value = 0;

		if (getKaleoConditionId() < kaleoCondition.getKaleoConditionId()) {
			value = -1;
		}
		else if (getKaleoConditionId() > kaleoCondition.getKaleoConditionId()) {
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

		if (!(object instanceof KaleoCondition)) {
			return false;
		}

		KaleoCondition kaleoCondition = (KaleoCondition)object;

		long primaryKey = kaleoCondition.getPrimaryKey();

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
	public CacheModel<KaleoCondition> toCacheModel() {
		KaleoConditionCacheModel kaleoConditionCacheModel =
			new KaleoConditionCacheModel();

		kaleoConditionCacheModel.mvccVersion = getMvccVersion();

		kaleoConditionCacheModel.kaleoConditionId = getKaleoConditionId();

		kaleoConditionCacheModel.groupId = getGroupId();

		kaleoConditionCacheModel.companyId = getCompanyId();

		kaleoConditionCacheModel.userId = getUserId();

		kaleoConditionCacheModel.userName = getUserName();

		String userName = kaleoConditionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoConditionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoConditionCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoConditionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoConditionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoConditionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoConditionCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoConditionCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoConditionCacheModel.kaleoNodeId = getKaleoNodeId();

		kaleoConditionCacheModel.script = getScript();

		String script = kaleoConditionCacheModel.script;

		if ((script != null) && (script.length() == 0)) {
			kaleoConditionCacheModel.script = null;
		}

		kaleoConditionCacheModel.scriptLanguage = getScriptLanguage();

		String scriptLanguage = kaleoConditionCacheModel.scriptLanguage;

		if ((scriptLanguage != null) && (scriptLanguage.length() == 0)) {
			kaleoConditionCacheModel.scriptLanguage = null;
		}

		kaleoConditionCacheModel.scriptRequiredContexts =
			getScriptRequiredContexts();

		String scriptRequiredContexts =
			kaleoConditionCacheModel.scriptRequiredContexts;

		if ((scriptRequiredContexts != null) &&
			(scriptRequiredContexts.length() == 0)) {

			kaleoConditionCacheModel.scriptRequiredContexts = null;
		}

		return kaleoConditionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoCondition, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoCondition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoCondition, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((KaleoCondition)this));
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
		Map<String, Function<KaleoCondition, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoCondition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoCondition, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((KaleoCondition)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, KaleoCondition>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _kaleoConditionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoDefinitionVersionId;
	private long _kaleoNodeId;
	private String _script;
	private String _scriptLanguage;
	private String _scriptRequiredContexts;

	public <T> T getColumnValue(String columnName) {
		Function<KaleoCondition, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((KaleoCondition)this);
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
		_columnOriginalValues.put("kaleoConditionId", _kaleoConditionId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("kaleoDefinitionId", _kaleoDefinitionId);
		_columnOriginalValues.put(
			"kaleoDefinitionVersionId", _kaleoDefinitionVersionId);
		_columnOriginalValues.put("kaleoNodeId", _kaleoNodeId);
		_columnOriginalValues.put("script", _script);
		_columnOriginalValues.put("scriptLanguage", _scriptLanguage);
		_columnOriginalValues.put(
			"scriptRequiredContexts", _scriptRequiredContexts);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("kaleoConditionId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("kaleoDefinitionId", 256L);

		columnBitmasks.put("kaleoDefinitionVersionId", 512L);

		columnBitmasks.put("kaleoNodeId", 1024L);

		columnBitmasks.put("script", 2048L);

		columnBitmasks.put("scriptLanguage", 4096L);

		columnBitmasks.put("scriptRequiredContexts", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private KaleoCondition _escapedModel;

}