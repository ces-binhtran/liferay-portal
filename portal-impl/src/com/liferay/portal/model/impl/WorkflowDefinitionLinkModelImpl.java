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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.model.WorkflowDefinitionLinkModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the WorkflowDefinitionLink service. Represents a row in the &quot;WorkflowDefinitionLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>WorkflowDefinitionLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WorkflowDefinitionLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLinkImpl
 * @generated
 */
public class WorkflowDefinitionLinkModelImpl
	extends BaseModelImpl<WorkflowDefinitionLink>
	implements WorkflowDefinitionLinkModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a workflow definition link model instance should use the <code>WorkflowDefinitionLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "WorkflowDefinitionLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"workflowDefinitionLinkId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"typePK", Types.BIGINT},
		{"workflowDefinitionName", Types.VARCHAR},
		{"workflowDefinitionVersion", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("workflowDefinitionLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("typePK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("workflowDefinitionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("workflowDefinitionVersion", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table WorkflowDefinitionLink (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,workflowDefinitionLinkId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,typePK LONG,workflowDefinitionName VARCHAR(75) null,workflowDefinitionVersion INTEGER,primary key (workflowDefinitionLinkId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table WorkflowDefinitionLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY workflowDefinitionLink.workflowDefinitionName ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY WorkflowDefinitionLink.workflowDefinitionName ASC";

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
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPEPK_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long WORKFLOWDEFINITIONNAME_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long WORKFLOWDEFINITIONVERSION_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.WorkflowDefinitionLink"));

	public WorkflowDefinitionLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _workflowDefinitionLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWorkflowDefinitionLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workflowDefinitionLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowDefinitionLink.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowDefinitionLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<WorkflowDefinitionLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<WorkflowDefinitionLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<WorkflowDefinitionLink, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((WorkflowDefinitionLink)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<WorkflowDefinitionLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<WorkflowDefinitionLink, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(WorkflowDefinitionLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<WorkflowDefinitionLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<WorkflowDefinitionLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, WorkflowDefinitionLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			WorkflowDefinitionLink.class.getClassLoader(),
			WorkflowDefinitionLink.class, ModelWrapper.class);

		try {
			Constructor<WorkflowDefinitionLink> constructor =
				(Constructor<WorkflowDefinitionLink>)proxyClass.getConstructor(
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

	private static final Map<String, Function<WorkflowDefinitionLink, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<WorkflowDefinitionLink, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<WorkflowDefinitionLink, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<WorkflowDefinitionLink, Object>>();
		Map<String, BiConsumer<WorkflowDefinitionLink, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<WorkflowDefinitionLink, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", WorkflowDefinitionLink::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", WorkflowDefinitionLink::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setCtCollectionId);
		attributeGetterFunctions.put(
			"workflowDefinitionLinkId",
			WorkflowDefinitionLink::getWorkflowDefinitionLinkId);
		attributeSetterBiConsumers.put(
			"workflowDefinitionLinkId",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setWorkflowDefinitionLinkId);
		attributeGetterFunctions.put(
			"groupId", WorkflowDefinitionLink::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setGroupId);
		attributeGetterFunctions.put(
			"companyId", WorkflowDefinitionLink::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setCompanyId);
		attributeGetterFunctions.put(
			"userId", WorkflowDefinitionLink::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setUserId);
		attributeGetterFunctions.put(
			"userName", WorkflowDefinitionLink::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<WorkflowDefinitionLink, String>)
				WorkflowDefinitionLink::setUserName);
		attributeGetterFunctions.put(
			"createDate", WorkflowDefinitionLink::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<WorkflowDefinitionLink, Date>)
				WorkflowDefinitionLink::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", WorkflowDefinitionLink::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<WorkflowDefinitionLink, Date>)
				WorkflowDefinitionLink::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", WorkflowDefinitionLink::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", WorkflowDefinitionLink::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setClassPK);
		attributeGetterFunctions.put(
			"typePK", WorkflowDefinitionLink::getTypePK);
		attributeSetterBiConsumers.put(
			"typePK",
			(BiConsumer<WorkflowDefinitionLink, Long>)
				WorkflowDefinitionLink::setTypePK);
		attributeGetterFunctions.put(
			"workflowDefinitionName",
			WorkflowDefinitionLink::getWorkflowDefinitionName);
		attributeSetterBiConsumers.put(
			"workflowDefinitionName",
			(BiConsumer<WorkflowDefinitionLink, String>)
				WorkflowDefinitionLink::setWorkflowDefinitionName);
		attributeGetterFunctions.put(
			"workflowDefinitionVersion",
			WorkflowDefinitionLink::getWorkflowDefinitionVersion);
		attributeSetterBiConsumers.put(
			"workflowDefinitionVersion",
			(BiConsumer<WorkflowDefinitionLink, Integer>)
				WorkflowDefinitionLink::setWorkflowDefinitionVersion);

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
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getWorkflowDefinitionLinkId() {
		return _workflowDefinitionLinkId;
	}

	@Override
	public void setWorkflowDefinitionLinkId(long workflowDefinitionLinkId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_workflowDefinitionLinkId = workflowDefinitionLinkId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
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
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@Override
	public long getTypePK() {
		return _typePK;
	}

	@Override
	public void setTypePK(long typePK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_typePK = typePK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTypePK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("typePK"));
	}

	@Override
	public String getWorkflowDefinitionName() {
		if (_workflowDefinitionName == null) {
			return "";
		}
		else {
			return _workflowDefinitionName;
		}
	}

	@Override
	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_workflowDefinitionName = workflowDefinitionName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalWorkflowDefinitionName() {
		return getColumnOriginalValue("workflowDefinitionName");
	}

	@Override
	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	@Override
	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_workflowDefinitionVersion = workflowDefinitionVersion;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalWorkflowDefinitionVersion() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("workflowDefinitionVersion"));
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
			getCompanyId(), WorkflowDefinitionLink.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public WorkflowDefinitionLink toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, WorkflowDefinitionLink>
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
		WorkflowDefinitionLinkImpl workflowDefinitionLinkImpl =
			new WorkflowDefinitionLinkImpl();

		workflowDefinitionLinkImpl.setMvccVersion(getMvccVersion());
		workflowDefinitionLinkImpl.setCtCollectionId(getCtCollectionId());
		workflowDefinitionLinkImpl.setWorkflowDefinitionLinkId(
			getWorkflowDefinitionLinkId());
		workflowDefinitionLinkImpl.setGroupId(getGroupId());
		workflowDefinitionLinkImpl.setCompanyId(getCompanyId());
		workflowDefinitionLinkImpl.setUserId(getUserId());
		workflowDefinitionLinkImpl.setUserName(getUserName());
		workflowDefinitionLinkImpl.setCreateDate(getCreateDate());
		workflowDefinitionLinkImpl.setModifiedDate(getModifiedDate());
		workflowDefinitionLinkImpl.setClassNameId(getClassNameId());
		workflowDefinitionLinkImpl.setClassPK(getClassPK());
		workflowDefinitionLinkImpl.setTypePK(getTypePK());
		workflowDefinitionLinkImpl.setWorkflowDefinitionName(
			getWorkflowDefinitionName());
		workflowDefinitionLinkImpl.setWorkflowDefinitionVersion(
			getWorkflowDefinitionVersion());

		workflowDefinitionLinkImpl.resetOriginalValues();

		return workflowDefinitionLinkImpl;
	}

	@Override
	public int compareTo(WorkflowDefinitionLink workflowDefinitionLink) {
		int value = 0;

		value = getWorkflowDefinitionName().compareTo(
			workflowDefinitionLink.getWorkflowDefinitionName());

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

		if (!(object instanceof WorkflowDefinitionLink)) {
			return false;
		}

		WorkflowDefinitionLink workflowDefinitionLink =
			(WorkflowDefinitionLink)object;

		long primaryKey = workflowDefinitionLink.getPrimaryKey();

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
	public CacheModel<WorkflowDefinitionLink> toCacheModel() {
		WorkflowDefinitionLinkCacheModel workflowDefinitionLinkCacheModel =
			new WorkflowDefinitionLinkCacheModel();

		workflowDefinitionLinkCacheModel.mvccVersion = getMvccVersion();

		workflowDefinitionLinkCacheModel.ctCollectionId = getCtCollectionId();

		workflowDefinitionLinkCacheModel.workflowDefinitionLinkId =
			getWorkflowDefinitionLinkId();

		workflowDefinitionLinkCacheModel.groupId = getGroupId();

		workflowDefinitionLinkCacheModel.companyId = getCompanyId();

		workflowDefinitionLinkCacheModel.userId = getUserId();

		workflowDefinitionLinkCacheModel.userName = getUserName();

		String userName = workflowDefinitionLinkCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			workflowDefinitionLinkCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			workflowDefinitionLinkCacheModel.createDate = createDate.getTime();
		}
		else {
			workflowDefinitionLinkCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			workflowDefinitionLinkCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			workflowDefinitionLinkCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		workflowDefinitionLinkCacheModel.classNameId = getClassNameId();

		workflowDefinitionLinkCacheModel.classPK = getClassPK();

		workflowDefinitionLinkCacheModel.typePK = getTypePK();

		workflowDefinitionLinkCacheModel.workflowDefinitionName =
			getWorkflowDefinitionName();

		String workflowDefinitionName =
			workflowDefinitionLinkCacheModel.workflowDefinitionName;

		if ((workflowDefinitionName != null) &&
			(workflowDefinitionName.length() == 0)) {

			workflowDefinitionLinkCacheModel.workflowDefinitionName = null;
		}

		workflowDefinitionLinkCacheModel.workflowDefinitionVersion =
			getWorkflowDefinitionVersion();

		return workflowDefinitionLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<WorkflowDefinitionLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<WorkflowDefinitionLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<WorkflowDefinitionLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((WorkflowDefinitionLink)this));
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
		Map<String, Function<WorkflowDefinitionLink, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<WorkflowDefinitionLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<WorkflowDefinitionLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((WorkflowDefinitionLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, WorkflowDefinitionLink>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _workflowDefinitionLinkId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _typePK;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;

	public <T> T getColumnValue(String columnName) {
		Function<WorkflowDefinitionLink, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((WorkflowDefinitionLink)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put(
			"workflowDefinitionLinkId", _workflowDefinitionLinkId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("typePK", _typePK);
		_columnOriginalValues.put(
			"workflowDefinitionName", _workflowDefinitionName);
		_columnOriginalValues.put(
			"workflowDefinitionVersion", _workflowDefinitionVersion);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("workflowDefinitionLinkId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("classNameId", 512L);

		columnBitmasks.put("classPK", 1024L);

		columnBitmasks.put("typePK", 2048L);

		columnBitmasks.put("workflowDefinitionName", 4096L);

		columnBitmasks.put("workflowDefinitionVersion", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private WorkflowDefinitionLink _escapedModel;

}