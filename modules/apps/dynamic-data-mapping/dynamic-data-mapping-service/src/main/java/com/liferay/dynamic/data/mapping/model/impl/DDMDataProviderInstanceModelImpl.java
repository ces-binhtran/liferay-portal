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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceModel;
import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstanceSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMDataProviderInstance service. Represents a row in the &quot;DDMDataProviderInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMDataProviderInstanceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMDataProviderInstanceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMDataProviderInstanceImpl
 * @generated
 */
@JSON(strict = true)
public class DDMDataProviderInstanceModelImpl
	extends BaseModelImpl<DDMDataProviderInstance>
	implements DDMDataProviderInstanceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm data provider instance model instance should use the <code>DDMDataProviderInstance</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMDataProviderInstance";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"dataProviderInstanceId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"description", Types.CLOB},
		{"definition", Types.CLOB}, {"type_", Types.VARCHAR},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dataProviderInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.CLOB);
		TABLE_COLUMNS_MAP.put("definition", Types.CLOB);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMDataProviderInstance (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,dataProviderInstanceId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,description TEXT null,definition TEXT null,type_ VARCHAR(75) null,lastPublishDate DATE null,primary key (dataProviderInstanceId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DDMDataProviderInstance";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmDataProviderInstance.dataProviderInstanceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMDataProviderInstance.dataProviderInstanceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long DATAPROVIDERINSTANCEID_COLUMN_BITMASK = 8L;

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

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static DDMDataProviderInstance toModel(
		DDMDataProviderInstanceSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		DDMDataProviderInstance model = new DDMDataProviderInstanceImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setUuid(soapModel.getUuid());
		model.setDataProviderInstanceId(soapModel.getDataProviderInstanceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setDefinition(soapModel.getDefinition());
		model.setType(soapModel.getType());
		model.setLastPublishDate(soapModel.getLastPublishDate());

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
	public static List<DDMDataProviderInstance> toModels(
		DDMDataProviderInstanceSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<DDMDataProviderInstance> models =
			new ArrayList<DDMDataProviderInstance>(soapModels.length);

		for (DDMDataProviderInstanceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public DDMDataProviderInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dataProviderInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDataProviderInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dataProviderInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMDataProviderInstance.class;
	}

	@Override
	public String getModelClassName() {
		return DDMDataProviderInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMDataProviderInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMDataProviderInstance, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMDataProviderInstance, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMDataProviderInstance)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMDataProviderInstance, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMDataProviderInstance, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMDataProviderInstance)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMDataProviderInstance, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMDataProviderInstance, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMDataProviderInstance>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMDataProviderInstance.class.getClassLoader(),
			DDMDataProviderInstance.class, ModelWrapper.class);

		try {
			Constructor<DDMDataProviderInstance> constructor =
				(Constructor<DDMDataProviderInstance>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DDMDataProviderInstance, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<DDMDataProviderInstance, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMDataProviderInstance, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DDMDataProviderInstance, Object>>();
		Map<String, BiConsumer<DDMDataProviderInstance, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<DDMDataProviderInstance, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DDMDataProviderInstance::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMDataProviderInstance, Long>)
				DDMDataProviderInstance::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMDataProviderInstance::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMDataProviderInstance, Long>)
				DDMDataProviderInstance::setCtCollectionId);
		attributeGetterFunctions.put("uuid", DDMDataProviderInstance::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<DDMDataProviderInstance, String>)
				DDMDataProviderInstance::setUuid);
		attributeGetterFunctions.put(
			"dataProviderInstanceId",
			DDMDataProviderInstance::getDataProviderInstanceId);
		attributeSetterBiConsumers.put(
			"dataProviderInstanceId",
			(BiConsumer<DDMDataProviderInstance, Long>)
				DDMDataProviderInstance::setDataProviderInstanceId);
		attributeGetterFunctions.put(
			"groupId", DDMDataProviderInstance::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DDMDataProviderInstance, Long>)
				DDMDataProviderInstance::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DDMDataProviderInstance::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMDataProviderInstance, Long>)
				DDMDataProviderInstance::setCompanyId);
		attributeGetterFunctions.put(
			"userId", DDMDataProviderInstance::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<DDMDataProviderInstance, Long>)
				DDMDataProviderInstance::setUserId);
		attributeGetterFunctions.put(
			"userName", DDMDataProviderInstance::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DDMDataProviderInstance, String>)
				DDMDataProviderInstance::setUserName);
		attributeGetterFunctions.put(
			"createDate", DDMDataProviderInstance::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DDMDataProviderInstance, Date>)
				DDMDataProviderInstance::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DDMDataProviderInstance::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DDMDataProviderInstance, Date>)
				DDMDataProviderInstance::setModifiedDate);
		attributeGetterFunctions.put("name", DDMDataProviderInstance::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<DDMDataProviderInstance, String>)
				DDMDataProviderInstance::setName);
		attributeGetterFunctions.put(
			"description", DDMDataProviderInstance::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<DDMDataProviderInstance, String>)
				DDMDataProviderInstance::setDescription);
		attributeGetterFunctions.put(
			"definition", DDMDataProviderInstance::getDefinition);
		attributeSetterBiConsumers.put(
			"definition",
			(BiConsumer<DDMDataProviderInstance, String>)
				DDMDataProviderInstance::setDefinition);
		attributeGetterFunctions.put("type", DDMDataProviderInstance::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<DDMDataProviderInstance, String>)
				DDMDataProviderInstance::setType);
		attributeGetterFunctions.put(
			"lastPublishDate", DDMDataProviderInstance::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<DDMDataProviderInstance, Date>)
				DDMDataProviderInstance::setLastPublishDate);

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
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
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
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getDataProviderInstanceId() {
		return _dataProviderInstanceId;
	}

	@Override
	public void setDataProviderInstanceId(long dataProviderInstanceId) {
		_dataProviderInstanceId = dataProviderInstanceId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
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
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
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
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDefinition() {
		if (_definition == null) {
			return "";
		}
		else {
			return _definition;
		}
	}

	@Override
	public void setDefinition(String definition) {
		_definition = definition;
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
		_type = type;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(DDMDataProviderInstance.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMDataProviderInstance.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			DDMDataProviderInstance.class.getName(), getPrimaryKey(),
			defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public DDMDataProviderInstance toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMDataProviderInstance>
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
		DDMDataProviderInstanceImpl ddmDataProviderInstanceImpl =
			new DDMDataProviderInstanceImpl();

		ddmDataProviderInstanceImpl.setMvccVersion(getMvccVersion());
		ddmDataProviderInstanceImpl.setCtCollectionId(getCtCollectionId());
		ddmDataProviderInstanceImpl.setUuid(getUuid());
		ddmDataProviderInstanceImpl.setDataProviderInstanceId(
			getDataProviderInstanceId());
		ddmDataProviderInstanceImpl.setGroupId(getGroupId());
		ddmDataProviderInstanceImpl.setCompanyId(getCompanyId());
		ddmDataProviderInstanceImpl.setUserId(getUserId());
		ddmDataProviderInstanceImpl.setUserName(getUserName());
		ddmDataProviderInstanceImpl.setCreateDate(getCreateDate());
		ddmDataProviderInstanceImpl.setModifiedDate(getModifiedDate());
		ddmDataProviderInstanceImpl.setName(getName());
		ddmDataProviderInstanceImpl.setDescription(getDescription());
		ddmDataProviderInstanceImpl.setDefinition(getDefinition());
		ddmDataProviderInstanceImpl.setType(getType());
		ddmDataProviderInstanceImpl.setLastPublishDate(getLastPublishDate());

		ddmDataProviderInstanceImpl.resetOriginalValues();

		return ddmDataProviderInstanceImpl;
	}

	@Override
	public int compareTo(DDMDataProviderInstance ddmDataProviderInstance) {
		long primaryKey = ddmDataProviderInstance.getPrimaryKey();

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

		if (!(object instanceof DDMDataProviderInstance)) {
			return false;
		}

		DDMDataProviderInstance ddmDataProviderInstance =
			(DDMDataProviderInstance)object;

		long primaryKey = ddmDataProviderInstance.getPrimaryKey();

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
		DDMDataProviderInstanceModelImpl ddmDataProviderInstanceModelImpl =
			this;

		ddmDataProviderInstanceModelImpl._originalUuid =
			ddmDataProviderInstanceModelImpl._uuid;

		ddmDataProviderInstanceModelImpl._originalGroupId =
			ddmDataProviderInstanceModelImpl._groupId;

		ddmDataProviderInstanceModelImpl._setOriginalGroupId = false;

		ddmDataProviderInstanceModelImpl._originalCompanyId =
			ddmDataProviderInstanceModelImpl._companyId;

		ddmDataProviderInstanceModelImpl._setOriginalCompanyId = false;

		ddmDataProviderInstanceModelImpl._setModifiedDate = false;

		ddmDataProviderInstanceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMDataProviderInstance> toCacheModel() {
		DDMDataProviderInstanceCacheModel ddmDataProviderInstanceCacheModel =
			new DDMDataProviderInstanceCacheModel();

		ddmDataProviderInstanceCacheModel.mvccVersion = getMvccVersion();

		ddmDataProviderInstanceCacheModel.ctCollectionId = getCtCollectionId();

		ddmDataProviderInstanceCacheModel.uuid = getUuid();

		String uuid = ddmDataProviderInstanceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmDataProviderInstanceCacheModel.uuid = null;
		}

		ddmDataProviderInstanceCacheModel.dataProviderInstanceId =
			getDataProviderInstanceId();

		ddmDataProviderInstanceCacheModel.groupId = getGroupId();

		ddmDataProviderInstanceCacheModel.companyId = getCompanyId();

		ddmDataProviderInstanceCacheModel.userId = getUserId();

		ddmDataProviderInstanceCacheModel.userName = getUserName();

		String userName = ddmDataProviderInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddmDataProviderInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddmDataProviderInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			ddmDataProviderInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ddmDataProviderInstanceCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			ddmDataProviderInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ddmDataProviderInstanceCacheModel.name = getName();

		String name = ddmDataProviderInstanceCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			ddmDataProviderInstanceCacheModel.name = null;
		}

		ddmDataProviderInstanceCacheModel.description = getDescription();

		String description = ddmDataProviderInstanceCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			ddmDataProviderInstanceCacheModel.description = null;
		}

		ddmDataProviderInstanceCacheModel.definition = getDefinition();

		String definition = ddmDataProviderInstanceCacheModel.definition;

		if ((definition != null) && (definition.length() == 0)) {
			ddmDataProviderInstanceCacheModel.definition = null;
		}

		ddmDataProviderInstanceCacheModel.type = getType();

		String type = ddmDataProviderInstanceCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			ddmDataProviderInstanceCacheModel.type = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			ddmDataProviderInstanceCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			ddmDataProviderInstanceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return ddmDataProviderInstanceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMDataProviderInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMDataProviderInstance, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMDataProviderInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((DDMDataProviderInstance)this));
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
		Map<String, Function<DDMDataProviderInstance, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMDataProviderInstance, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMDataProviderInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((DDMDataProviderInstance)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, DDMDataProviderInstance>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _originalUuid;
	private long _dataProviderInstanceId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _definition;
	private String _type;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private DDMDataProviderInstance _escapedModel;

}