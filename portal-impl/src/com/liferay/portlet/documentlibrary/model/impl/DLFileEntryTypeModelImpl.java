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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileEntryTypeModel;
import com.liferay.document.library.kernel.model.DLFileEntryTypeSoap;
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
 * The base model implementation for the DLFileEntryType service. Represents a row in the &quot;DLFileEntryType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DLFileEntryTypeModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileEntryTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryTypeImpl
 * @generated
 */
@JSON(strict = true)
public class DLFileEntryTypeModelImpl
	extends BaseModelImpl<DLFileEntryType> implements DLFileEntryTypeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file entry type model instance should use the <code>DLFileEntryType</code> interface instead.
	 */
	public static final String TABLE_NAME = "DLFileEntryType";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"fileEntryTypeId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"dataDefinitionId", Types.BIGINT}, {"fileEntryTypeKey", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("dataDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryTypeKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DLFileEntryType (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,fileEntryTypeId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,dataDefinitionId LONG,fileEntryTypeKey VARCHAR(75) null,name STRING null,description STRING null,lastPublishDate DATE null,primary key (fileEntryTypeId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table DLFileEntryType";

	public static final String ORDER_BY_JPQL =
		" ORDER BY dlFileEntryType.fileEntryTypeId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DLFileEntryType.fileEntryTypeId ASC";

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

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long FILEENTRYTYPEKEY_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long FILEENTRYTYPEID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static DLFileEntryType toModel(DLFileEntryTypeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		DLFileEntryType model = new DLFileEntryTypeImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setCtCollectionId(soapModel.getCtCollectionId());
		model.setUuid(soapModel.getUuid());
		model.setFileEntryTypeId(soapModel.getFileEntryTypeId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDataDefinitionId(soapModel.getDataDefinitionId());
		model.setFileEntryTypeKey(soapModel.getFileEntryTypeKey());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
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
	public static List<DLFileEntryType> toModels(
		DLFileEntryTypeSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<DLFileEntryType> models = new ArrayList<DLFileEntryType>(
			soapModels.length);

		for (DLFileEntryTypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_DLFILEENTRYTYPES_DLFOLDERS_NAME =
		"DLFileEntryTypes_DLFolders";

	public static final Object[][]
		MAPPING_TABLE_DLFILEENTRYTYPES_DLFOLDERS_COLUMNS = {
			{"companyId", Types.BIGINT}, {"fileEntryTypeId", Types.BIGINT},
			{"folderId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_DLFILEENTRYTYPES_DLFOLDERS_SQL_CREATE =
			"create table DLFileEntryTypes_DLFolders (companyId LONG not null,fileEntryTypeId LONG not null,folderId LONG not null,ctCollectionId LONG default 0 not null,ctChangeType BOOLEAN,primary key (fileEntryTypeId, folderId, ctCollectionId))";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean
		FINDER_CACHE_ENABLED_DLFILEENTRYTYPES_DLFOLDERS = true;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.document.library.kernel.model.DLFileEntryType"));

	public DLFileEntryTypeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryTypeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFileEntryTypeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileEntryTypeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileEntryType.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileEntryType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DLFileEntryType, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DLFileEntryType, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryType, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DLFileEntryType)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DLFileEntryType, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DLFileEntryType, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DLFileEntryType)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DLFileEntryType, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DLFileEntryType, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DLFileEntryType>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DLFileEntryType.class.getClassLoader(), DLFileEntryType.class,
			ModelWrapper.class);

		try {
			Constructor<DLFileEntryType> constructor =
				(Constructor<DLFileEntryType>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DLFileEntryType, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DLFileEntryType, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DLFileEntryType, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<DLFileEntryType, Object>>();
		Map<String, BiConsumer<DLFileEntryType, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DLFileEntryType, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DLFileEntryType::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DLFileEntryType, Long>)DLFileEntryType::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DLFileEntryType::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DLFileEntryType, Long>)
				DLFileEntryType::setCtCollectionId);
		attributeGetterFunctions.put("uuid", DLFileEntryType::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<DLFileEntryType, String>)DLFileEntryType::setUuid);
		attributeGetterFunctions.put(
			"fileEntryTypeId", DLFileEntryType::getFileEntryTypeId);
		attributeSetterBiConsumers.put(
			"fileEntryTypeId",
			(BiConsumer<DLFileEntryType, Long>)
				DLFileEntryType::setFileEntryTypeId);
		attributeGetterFunctions.put("groupId", DLFileEntryType::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<DLFileEntryType, Long>)DLFileEntryType::setGroupId);
		attributeGetterFunctions.put(
			"companyId", DLFileEntryType::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DLFileEntryType, Long>)DLFileEntryType::setCompanyId);
		attributeGetterFunctions.put("userId", DLFileEntryType::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<DLFileEntryType, Long>)DLFileEntryType::setUserId);
		attributeGetterFunctions.put("userName", DLFileEntryType::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DLFileEntryType, String>)DLFileEntryType::setUserName);
		attributeGetterFunctions.put(
			"createDate", DLFileEntryType::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DLFileEntryType, Date>)DLFileEntryType::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DLFileEntryType::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DLFileEntryType, Date>)
				DLFileEntryType::setModifiedDate);
		attributeGetterFunctions.put(
			"dataDefinitionId", DLFileEntryType::getDataDefinitionId);
		attributeSetterBiConsumers.put(
			"dataDefinitionId",
			(BiConsumer<DLFileEntryType, Long>)
				DLFileEntryType::setDataDefinitionId);
		attributeGetterFunctions.put(
			"fileEntryTypeKey", DLFileEntryType::getFileEntryTypeKey);
		attributeSetterBiConsumers.put(
			"fileEntryTypeKey",
			(BiConsumer<DLFileEntryType, String>)
				DLFileEntryType::setFileEntryTypeKey);
		attributeGetterFunctions.put("name", DLFileEntryType::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<DLFileEntryType, String>)DLFileEntryType::setName);
		attributeGetterFunctions.put(
			"description", DLFileEntryType::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<DLFileEntryType, String>)
				DLFileEntryType::setDescription);
		attributeGetterFunctions.put(
			"lastPublishDate", DLFileEntryType::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<DLFileEntryType, Date>)
				DLFileEntryType::setLastPublishDate);

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
	public long getFileEntryTypeId() {
		return _fileEntryTypeId;
	}

	@Override
	public void setFileEntryTypeId(long fileEntryTypeId) {
		_fileEntryTypeId = fileEntryTypeId;
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
	public long getDataDefinitionId() {
		return _dataDefinitionId;
	}

	@Override
	public void setDataDefinitionId(long dataDefinitionId) {
		_dataDefinitionId = dataDefinitionId;
	}

	@JSON
	@Override
	public String getFileEntryTypeKey() {
		if (_fileEntryTypeKey == null) {
			return "";
		}
		else {
			return _fileEntryTypeKey;
		}
	}

	@Override
	public void setFileEntryTypeKey(String fileEntryTypeKey) {
		_columnBitmask |= FILEENTRYTYPEKEY_COLUMN_BITMASK;

		if (_originalFileEntryTypeKey == null) {
			_originalFileEntryTypeKey = _fileEntryTypeKey;
		}

		_fileEntryTypeKey = fileEntryTypeKey;
	}

	public String getOriginalFileEntryTypeKey() {
		return GetterUtil.getString(_originalFileEntryTypeKey);
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
			PortalUtil.getClassNameId(DLFileEntryType.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DLFileEntryType.class.getName(), getPrimaryKey());
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
			DLFileEntryType.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

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
	public DLFileEntryType toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DLFileEntryType>
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
		DLFileEntryTypeImpl dlFileEntryTypeImpl = new DLFileEntryTypeImpl();

		dlFileEntryTypeImpl.setMvccVersion(getMvccVersion());
		dlFileEntryTypeImpl.setCtCollectionId(getCtCollectionId());
		dlFileEntryTypeImpl.setUuid(getUuid());
		dlFileEntryTypeImpl.setFileEntryTypeId(getFileEntryTypeId());
		dlFileEntryTypeImpl.setGroupId(getGroupId());
		dlFileEntryTypeImpl.setCompanyId(getCompanyId());
		dlFileEntryTypeImpl.setUserId(getUserId());
		dlFileEntryTypeImpl.setUserName(getUserName());
		dlFileEntryTypeImpl.setCreateDate(getCreateDate());
		dlFileEntryTypeImpl.setModifiedDate(getModifiedDate());
		dlFileEntryTypeImpl.setDataDefinitionId(getDataDefinitionId());
		dlFileEntryTypeImpl.setFileEntryTypeKey(getFileEntryTypeKey());
		dlFileEntryTypeImpl.setName(getName());
		dlFileEntryTypeImpl.setDescription(getDescription());
		dlFileEntryTypeImpl.setLastPublishDate(getLastPublishDate());

		dlFileEntryTypeImpl.resetOriginalValues();

		return dlFileEntryTypeImpl;
	}

	@Override
	public int compareTo(DLFileEntryType dlFileEntryType) {
		long primaryKey = dlFileEntryType.getPrimaryKey();

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

		if (!(object instanceof DLFileEntryType)) {
			return false;
		}

		DLFileEntryType dlFileEntryType = (DLFileEntryType)object;

		long primaryKey = dlFileEntryType.getPrimaryKey();

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
		DLFileEntryTypeModelImpl dlFileEntryTypeModelImpl = this;

		dlFileEntryTypeModelImpl._originalUuid = dlFileEntryTypeModelImpl._uuid;

		dlFileEntryTypeModelImpl._originalGroupId =
			dlFileEntryTypeModelImpl._groupId;

		dlFileEntryTypeModelImpl._setOriginalGroupId = false;

		dlFileEntryTypeModelImpl._originalCompanyId =
			dlFileEntryTypeModelImpl._companyId;

		dlFileEntryTypeModelImpl._setOriginalCompanyId = false;

		dlFileEntryTypeModelImpl._setModifiedDate = false;

		dlFileEntryTypeModelImpl._originalFileEntryTypeKey =
			dlFileEntryTypeModelImpl._fileEntryTypeKey;

		dlFileEntryTypeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLFileEntryType> toCacheModel() {
		DLFileEntryTypeCacheModel dlFileEntryTypeCacheModel =
			new DLFileEntryTypeCacheModel();

		dlFileEntryTypeCacheModel.mvccVersion = getMvccVersion();

		dlFileEntryTypeCacheModel.ctCollectionId = getCtCollectionId();

		dlFileEntryTypeCacheModel.uuid = getUuid();

		String uuid = dlFileEntryTypeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dlFileEntryTypeCacheModel.uuid = null;
		}

		dlFileEntryTypeCacheModel.fileEntryTypeId = getFileEntryTypeId();

		dlFileEntryTypeCacheModel.groupId = getGroupId();

		dlFileEntryTypeCacheModel.companyId = getCompanyId();

		dlFileEntryTypeCacheModel.userId = getUserId();

		dlFileEntryTypeCacheModel.userName = getUserName();

		String userName = dlFileEntryTypeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			dlFileEntryTypeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			dlFileEntryTypeCacheModel.createDate = createDate.getTime();
		}
		else {
			dlFileEntryTypeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			dlFileEntryTypeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			dlFileEntryTypeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		dlFileEntryTypeCacheModel.dataDefinitionId = getDataDefinitionId();

		dlFileEntryTypeCacheModel.fileEntryTypeKey = getFileEntryTypeKey();

		String fileEntryTypeKey = dlFileEntryTypeCacheModel.fileEntryTypeKey;

		if ((fileEntryTypeKey != null) && (fileEntryTypeKey.length() == 0)) {
			dlFileEntryTypeCacheModel.fileEntryTypeKey = null;
		}

		dlFileEntryTypeCacheModel.name = getName();

		String name = dlFileEntryTypeCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			dlFileEntryTypeCacheModel.name = null;
		}

		dlFileEntryTypeCacheModel.description = getDescription();

		String description = dlFileEntryTypeCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			dlFileEntryTypeCacheModel.description = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			dlFileEntryTypeCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			dlFileEntryTypeCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return dlFileEntryTypeCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DLFileEntryType, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DLFileEntryType, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryType, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DLFileEntryType)this));
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
		Map<String, Function<DLFileEntryType, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DLFileEntryType, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryType, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DLFileEntryType)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DLFileEntryType>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _originalUuid;
	private long _fileEntryTypeId;
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
	private long _dataDefinitionId;
	private String _fileEntryTypeKey;
	private String _originalFileEntryTypeKey;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private DLFileEntryType _escapedModel;

}