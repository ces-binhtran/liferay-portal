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
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationModel;
import com.liferay.portal.kernel.model.OrganizationSoap;
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
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Organization service. Represents a row in the &quot;Organization_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OrganizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrganizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrganizationImpl
 * @generated
 */
@JSON(strict = true)
public class OrganizationModelImpl
	extends BaseModelImpl<Organization> implements OrganizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a organization model instance should use the <code>Organization</code> interface instead.
	 */
	public static final String TABLE_NAME = "Organization_";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"organizationId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"parentOrganizationId", Types.BIGINT}, {"treePath", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"recursable", Types.BOOLEAN}, {"regionId", Types.BIGINT},
		{"countryId", Types.BIGINT}, {"statusId", Types.BIGINT},
		{"comments", Types.VARCHAR}, {"logoId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("organizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentOrganizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("treePath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("recursable", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("regionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("countryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Organization_ (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,organizationId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentOrganizationId LONG,treePath STRING null,name VARCHAR(100) null,type_ VARCHAR(75) null,recursable BOOLEAN,regionId LONG,countryId LONG,statusId LONG,comments STRING null,logoId LONG,primary key (organizationId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table Organization_";

	public static final String ORDER_BY_JPQL =
		" ORDER BY organization.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Organization_.name ASC";

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
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ORGANIZATIONID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long PARENTORGANIZATIONID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TREEPATH_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Organization toModel(OrganizationSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Organization model = new OrganizationImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setOrganizationId(soapModel.getOrganizationId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentOrganizationId(soapModel.getParentOrganizationId());
		model.setTreePath(soapModel.getTreePath());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setRecursable(soapModel.isRecursable());
		model.setRegionId(soapModel.getRegionId());
		model.setCountryId(soapModel.getCountryId());
		model.setStatusId(soapModel.getStatusId());
		model.setComments(soapModel.getComments());
		model.setLogoId(soapModel.getLogoId());

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
	public static List<Organization> toModels(OrganizationSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Organization> models = new ArrayList<Organization>(
			soapModels.length);

		for (OrganizationSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_GROUPS_ORGS_NAME = "Groups_Orgs";

	public static final Object[][] MAPPING_TABLE_GROUPS_ORGS_COLUMNS = {
		{"companyId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"organizationId", Types.BIGINT}
	};

	public static final String MAPPING_TABLE_GROUPS_ORGS_SQL_CREATE =
		"create table Groups_Orgs (companyId LONG not null,groupId LONG not null,organizationId LONG not null,ctCollectionId LONG default 0 not null,ctChangeType BOOLEAN,primary key (groupId, organizationId, ctCollectionId))";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED_GROUPS_ORGS = true;

	public static final String MAPPING_TABLE_USERS_ORGS_NAME = "Users_Orgs";

	public static final Object[][] MAPPING_TABLE_USERS_ORGS_COLUMNS = {
		{"companyId", Types.BIGINT}, {"organizationId", Types.BIGINT},
		{"userId", Types.BIGINT}
	};

	public static final String MAPPING_TABLE_USERS_ORGS_SQL_CREATE =
		"create table Users_Orgs (companyId LONG not null,organizationId LONG not null,userId LONG not null,ctCollectionId LONG default 0 not null,ctChangeType BOOLEAN,primary key (organizationId, userId, ctCollectionId))";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED_USERS_ORGS = true;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Organization"));

	public OrganizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _organizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrganizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _organizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Organization.class;
	}

	@Override
	public String getModelClassName() {
		return Organization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Organization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Organization, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Organization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Organization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Organization, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Organization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Organization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Organization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Organization>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Organization.class.getClassLoader(), Organization.class,
			ModelWrapper.class);

		try {
			Constructor<Organization> constructor =
				(Constructor<Organization>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Organization, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Organization, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Organization, Object>>();
		Map<String, BiConsumer<Organization, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Organization, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", Organization::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<Organization, Long>)Organization::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", Organization::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<Organization, Long>)Organization::setCtCollectionId);
		attributeGetterFunctions.put("uuid", Organization::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Organization, String>)Organization::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode", Organization::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<Organization, String>)
				Organization::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"organizationId", Organization::getOrganizationId);
		attributeSetterBiConsumers.put(
			"organizationId",
			(BiConsumer<Organization, Long>)Organization::setOrganizationId);
		attributeGetterFunctions.put("companyId", Organization::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<Organization, Long>)Organization::setCompanyId);
		attributeGetterFunctions.put("userId", Organization::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Organization, Long>)Organization::setUserId);
		attributeGetterFunctions.put("userName", Organization::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<Organization, String>)Organization::setUserName);
		attributeGetterFunctions.put("createDate", Organization::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<Organization, Date>)Organization::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", Organization::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Organization, Date>)Organization::setModifiedDate);
		attributeGetterFunctions.put(
			"parentOrganizationId", Organization::getParentOrganizationId);
		attributeSetterBiConsumers.put(
			"parentOrganizationId",
			(BiConsumer<Organization, Long>)
				Organization::setParentOrganizationId);
		attributeGetterFunctions.put("treePath", Organization::getTreePath);
		attributeSetterBiConsumers.put(
			"treePath",
			(BiConsumer<Organization, String>)Organization::setTreePath);
		attributeGetterFunctions.put("name", Organization::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Organization, String>)Organization::setName);
		attributeGetterFunctions.put("type", Organization::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<Organization, String>)Organization::setType);
		attributeGetterFunctions.put("recursable", Organization::getRecursable);
		attributeSetterBiConsumers.put(
			"recursable",
			(BiConsumer<Organization, Boolean>)Organization::setRecursable);
		attributeGetterFunctions.put("regionId", Organization::getRegionId);
		attributeSetterBiConsumers.put(
			"regionId",
			(BiConsumer<Organization, Long>)Organization::setRegionId);
		attributeGetterFunctions.put("countryId", Organization::getCountryId);
		attributeSetterBiConsumers.put(
			"countryId",
			(BiConsumer<Organization, Long>)Organization::setCountryId);
		attributeGetterFunctions.put("statusId", Organization::getStatusId);
		attributeSetterBiConsumers.put(
			"statusId",
			(BiConsumer<Organization, Long>)Organization::setStatusId);
		attributeGetterFunctions.put("comments", Organization::getComments);
		attributeSetterBiConsumers.put(
			"comments",
			(BiConsumer<Organization, String>)Organization::setComments);
		attributeGetterFunctions.put("logoId", Organization::getLogoId);
		attributeSetterBiConsumers.put(
			"logoId", (BiConsumer<Organization, Long>)Organization::setLogoId);

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
	public long getParentOrganizationId() {
		return _parentOrganizationId;
	}

	@Override
	public void setParentOrganizationId(long parentOrganizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentOrganizationId = parentOrganizationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalParentOrganizationId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("parentOrganizationId"));
	}

	@JSON
	@Override
	public String getTreePath() {
		if (_treePath == null) {
			return "";
		}
		else {
			return _treePath;
		}
	}

	@Override
	public void setTreePath(String treePath) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_treePath = treePath;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalTreePath() {
		return getColumnOriginalValue("treePath");
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@JSON
	@Override
	public boolean getRecursable() {
		return _recursable;
	}

	@JSON
	@Override
	public boolean isRecursable() {
		return _recursable;
	}

	@Override
	public void setRecursable(boolean recursable) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_recursable = recursable;
	}

	@JSON
	@Override
	public long getRegionId() {
		return _regionId;
	}

	@Override
	public void setRegionId(long regionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_regionId = regionId;
	}

	@JSON
	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_countryId = countryId;
	}

	@JSON
	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusId = statusId;
	}

	@JSON
	@Override
	public String getComments() {
		if (_comments == null) {
			return "";
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_comments = comments;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logoId = logoId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Organization.class.getName()));
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
			getCompanyId(), Organization.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Organization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Organization>
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
		OrganizationImpl organizationImpl = new OrganizationImpl();

		organizationImpl.setMvccVersion(getMvccVersion());
		organizationImpl.setCtCollectionId(getCtCollectionId());
		organizationImpl.setUuid(getUuid());
		organizationImpl.setExternalReferenceCode(getExternalReferenceCode());
		organizationImpl.setOrganizationId(getOrganizationId());
		organizationImpl.setCompanyId(getCompanyId());
		organizationImpl.setUserId(getUserId());
		organizationImpl.setUserName(getUserName());
		organizationImpl.setCreateDate(getCreateDate());
		organizationImpl.setModifiedDate(getModifiedDate());
		organizationImpl.setParentOrganizationId(getParentOrganizationId());
		organizationImpl.setTreePath(getTreePath());
		organizationImpl.setName(getName());
		organizationImpl.setType(getType());
		organizationImpl.setRecursable(isRecursable());
		organizationImpl.setRegionId(getRegionId());
		organizationImpl.setCountryId(getCountryId());
		organizationImpl.setStatusId(getStatusId());
		organizationImpl.setComments(getComments());
		organizationImpl.setLogoId(getLogoId());

		organizationImpl.resetOriginalValues();

		return organizationImpl;
	}

	@Override
	public int compareTo(Organization organization) {
		int value = 0;

		value = getName().compareTo(organization.getName());

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

		if (!(object instanceof Organization)) {
			return false;
		}

		Organization organization = (Organization)object;

		long primaryKey = organization.getPrimaryKey();

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
	public CacheModel<Organization> toCacheModel() {
		OrganizationCacheModel organizationCacheModel =
			new OrganizationCacheModel();

		organizationCacheModel.mvccVersion = getMvccVersion();

		organizationCacheModel.ctCollectionId = getCtCollectionId();

		organizationCacheModel.uuid = getUuid();

		String uuid = organizationCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			organizationCacheModel.uuid = null;
		}

		organizationCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			organizationCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			organizationCacheModel.externalReferenceCode = null;
		}

		organizationCacheModel.organizationId = getOrganizationId();

		organizationCacheModel.companyId = getCompanyId();

		organizationCacheModel.userId = getUserId();

		organizationCacheModel.userName = getUserName();

		String userName = organizationCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			organizationCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			organizationCacheModel.createDate = createDate.getTime();
		}
		else {
			organizationCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			organizationCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			organizationCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		organizationCacheModel.parentOrganizationId = getParentOrganizationId();

		organizationCacheModel.treePath = getTreePath();

		String treePath = organizationCacheModel.treePath;

		if ((treePath != null) && (treePath.length() == 0)) {
			organizationCacheModel.treePath = null;
		}

		organizationCacheModel.name = getName();

		String name = organizationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			organizationCacheModel.name = null;
		}

		organizationCacheModel.type = getType();

		String type = organizationCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			organizationCacheModel.type = null;
		}

		organizationCacheModel.recursable = isRecursable();

		organizationCacheModel.regionId = getRegionId();

		organizationCacheModel.countryId = getCountryId();

		organizationCacheModel.statusId = getStatusId();

		organizationCacheModel.comments = getComments();

		String comments = organizationCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			organizationCacheModel.comments = null;
		}

		organizationCacheModel.logoId = getLogoId();

		return organizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Organization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Organization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Organization)this));
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
		Map<String, Function<Organization, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Organization, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Organization, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Organization)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Organization>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _externalReferenceCode;
	private long _organizationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentOrganizationId;
	private String _treePath;
	private String _name;
	private String _type;
	private boolean _recursable;
	private long _regionId;
	private long _countryId;
	private long _statusId;
	private String _comments;
	private long _logoId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Organization, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Organization)this);
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
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("organizationId", _organizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"parentOrganizationId", _parentOrganizationId);
		_columnOriginalValues.put("treePath", _treePath);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("recursable", _recursable);
		_columnOriginalValues.put("regionId", _regionId);
		_columnOriginalValues.put("countryId", _countryId);
		_columnOriginalValues.put("statusId", _statusId);
		_columnOriginalValues.put("comments", _comments);
		_columnOriginalValues.put("logoId", _logoId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");
		attributeNames.put("groups_", "groups");

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

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("uuid_", 4L);

		columnBitmasks.put("externalReferenceCode", 8L);

		columnBitmasks.put("organizationId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("parentOrganizationId", 1024L);

		columnBitmasks.put("treePath", 2048L);

		columnBitmasks.put("name", 4096L);

		columnBitmasks.put("type_", 8192L);

		columnBitmasks.put("recursable", 16384L);

		columnBitmasks.put("regionId", 32768L);

		columnBitmasks.put("countryId", 65536L);

		columnBitmasks.put("statusId", 131072L);

		columnBitmasks.put("comments", 262144L);

		columnBitmasks.put("logoId", 524288L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Organization _escapedModel;

}