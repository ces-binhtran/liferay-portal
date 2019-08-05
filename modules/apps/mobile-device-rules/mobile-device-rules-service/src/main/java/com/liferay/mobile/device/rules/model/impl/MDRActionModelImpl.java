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

package com.liferay.mobile.device.rules.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.mobile.device.rules.model.MDRActionModel;
import com.liferay.mobile.device.rules.model.MDRActionSoap;
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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the MDRAction service. Represents a row in the &quot;MDRAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>MDRActionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MDRActionImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRActionImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class MDRActionModelImpl
	extends BaseModelImpl<MDRAction> implements MDRActionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a mdr action model instance should use the <code>MDRAction</code> interface instead.
	 */
	public static final String TABLE_NAME = "MDRAction";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"actionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"ruleGroupInstanceId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"typeSettings", Types.CLOB}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("actionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ruleGroupInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MDRAction (uuid_ VARCHAR(75) null,actionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,ruleGroupInstanceId LONG,name STRING null,description STRING null,type_ VARCHAR(255) null,typeSettings TEXT null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table MDRAction";

	public static final String ORDER_BY_JPQL =
		" ORDER BY mdrAction.actionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MDRAction.actionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long RULEGROUPINSTANCEID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long ACTIONID_COLUMN_BITMASK = 16L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MDRAction toModel(MDRActionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		MDRAction model = new MDRActionImpl();

		model.setUuid(soapModel.getUuid());
		model.setActionId(soapModel.getActionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setRuleGroupInstanceId(soapModel.getRuleGroupInstanceId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setType(soapModel.getType());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MDRAction> toModels(MDRActionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<MDRAction> models = new ArrayList<MDRAction>(soapModels.length);

		for (MDRActionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public MDRActionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _actionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _actionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MDRAction.class;
	}

	@Override
	public String getModelClassName() {
		return MDRAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MDRAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MDRAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRAction, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((MDRAction)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MDRAction, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MDRAction, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MDRAction)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MDRAction, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MDRAction, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MDRAction>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MDRAction.class.getClassLoader(), MDRAction.class,
			ModelWrapper.class);

		try {
			Constructor<MDRAction> constructor =
				(Constructor<MDRAction>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<MDRAction, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MDRAction, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MDRAction, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<MDRAction, Object>>();
		Map<String, BiConsumer<MDRAction, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<MDRAction, ?>>();

		attributeGetterFunctions.put("uuid", MDRAction::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<MDRAction, String>)MDRAction::setUuid);
		attributeGetterFunctions.put("actionId", MDRAction::getActionId);
		attributeSetterBiConsumers.put(
			"actionId", (BiConsumer<MDRAction, Long>)MDRAction::setActionId);
		attributeGetterFunctions.put("groupId", MDRAction::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<MDRAction, Long>)MDRAction::setGroupId);
		attributeGetterFunctions.put("companyId", MDRAction::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<MDRAction, Long>)MDRAction::setCompanyId);
		attributeGetterFunctions.put("userId", MDRAction::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<MDRAction, Long>)MDRAction::setUserId);
		attributeGetterFunctions.put("userName", MDRAction::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<MDRAction, String>)MDRAction::setUserName);
		attributeGetterFunctions.put("createDate", MDRAction::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MDRAction, Date>)MDRAction::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", MDRAction::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<MDRAction, Date>)MDRAction::setModifiedDate);
		attributeGetterFunctions.put("classNameId", MDRAction::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<MDRAction, Long>)MDRAction::setClassNameId);
		attributeGetterFunctions.put("classPK", MDRAction::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK", (BiConsumer<MDRAction, Long>)MDRAction::setClassPK);
		attributeGetterFunctions.put(
			"ruleGroupInstanceId", MDRAction::getRuleGroupInstanceId);
		attributeSetterBiConsumers.put(
			"ruleGroupInstanceId",
			(BiConsumer<MDRAction, Long>)MDRAction::setRuleGroupInstanceId);
		attributeGetterFunctions.put("name", MDRAction::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<MDRAction, String>)MDRAction::setName);
		attributeGetterFunctions.put("description", MDRAction::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<MDRAction, String>)MDRAction::setDescription);
		attributeGetterFunctions.put("type", MDRAction::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<MDRAction, String>)MDRAction::setType);
		attributeGetterFunctions.put(
			"typeSettings", MDRAction::getTypeSettings);
		attributeSetterBiConsumers.put(
			"typeSettings",
			(BiConsumer<MDRAction, String>)MDRAction::setTypeSettings);
		attributeGetterFunctions.put(
			"lastPublishDate", MDRAction::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<MDRAction, Date>)MDRAction::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getActionId() {
		return _actionId;
	}

	@Override
	public void setActionId(long actionId) {
		_actionId = actionId;
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
		catch (PortalException pe) {
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

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	@JSON
	@Override
	public long getRuleGroupInstanceId() {
		return _ruleGroupInstanceId;
	}

	@Override
	public void setRuleGroupInstanceId(long ruleGroupInstanceId) {
		_columnBitmask |= RULEGROUPINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalRuleGroupInstanceId) {
			_setOriginalRuleGroupInstanceId = true;

			_originalRuleGroupInstanceId = _ruleGroupInstanceId;
		}

		_ruleGroupInstanceId = ruleGroupInstanceId;
	}

	public long getOriginalRuleGroupInstanceId() {
		return _originalRuleGroupInstanceId;
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
			PortalUtil.getClassNameId(MDRAction.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), MDRAction.class.getName(), getPrimaryKey());
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
			MDRAction.class.getName(), getPrimaryKey(), defaultLocale,
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
	public MDRAction toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MDRAction>
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
		MDRActionImpl mdrActionImpl = new MDRActionImpl();

		mdrActionImpl.setUuid(getUuid());
		mdrActionImpl.setActionId(getActionId());
		mdrActionImpl.setGroupId(getGroupId());
		mdrActionImpl.setCompanyId(getCompanyId());
		mdrActionImpl.setUserId(getUserId());
		mdrActionImpl.setUserName(getUserName());
		mdrActionImpl.setCreateDate(getCreateDate());
		mdrActionImpl.setModifiedDate(getModifiedDate());
		mdrActionImpl.setClassNameId(getClassNameId());
		mdrActionImpl.setClassPK(getClassPK());
		mdrActionImpl.setRuleGroupInstanceId(getRuleGroupInstanceId());
		mdrActionImpl.setName(getName());
		mdrActionImpl.setDescription(getDescription());
		mdrActionImpl.setType(getType());
		mdrActionImpl.setTypeSettings(getTypeSettings());
		mdrActionImpl.setLastPublishDate(getLastPublishDate());

		mdrActionImpl.resetOriginalValues();

		return mdrActionImpl;
	}

	@Override
	public int compareTo(MDRAction mdrAction) {
		long primaryKey = mdrAction.getPrimaryKey();

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MDRAction)) {
			return false;
		}

		MDRAction mdrAction = (MDRAction)obj;

		long primaryKey = mdrAction.getPrimaryKey();

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
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		MDRActionModelImpl mdrActionModelImpl = this;

		mdrActionModelImpl._originalUuid = mdrActionModelImpl._uuid;

		mdrActionModelImpl._originalGroupId = mdrActionModelImpl._groupId;

		mdrActionModelImpl._setOriginalGroupId = false;

		mdrActionModelImpl._originalCompanyId = mdrActionModelImpl._companyId;

		mdrActionModelImpl._setOriginalCompanyId = false;

		mdrActionModelImpl._setModifiedDate = false;

		mdrActionModelImpl._originalRuleGroupInstanceId =
			mdrActionModelImpl._ruleGroupInstanceId;

		mdrActionModelImpl._setOriginalRuleGroupInstanceId = false;

		mdrActionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MDRAction> toCacheModel() {
		MDRActionCacheModel mdrActionCacheModel = new MDRActionCacheModel();

		mdrActionCacheModel.uuid = getUuid();

		String uuid = mdrActionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mdrActionCacheModel.uuid = null;
		}

		mdrActionCacheModel.actionId = getActionId();

		mdrActionCacheModel.groupId = getGroupId();

		mdrActionCacheModel.companyId = getCompanyId();

		mdrActionCacheModel.userId = getUserId();

		mdrActionCacheModel.userName = getUserName();

		String userName = mdrActionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mdrActionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mdrActionCacheModel.createDate = createDate.getTime();
		}
		else {
			mdrActionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mdrActionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			mdrActionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mdrActionCacheModel.classNameId = getClassNameId();

		mdrActionCacheModel.classPK = getClassPK();

		mdrActionCacheModel.ruleGroupInstanceId = getRuleGroupInstanceId();

		mdrActionCacheModel.name = getName();

		String name = mdrActionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			mdrActionCacheModel.name = null;
		}

		mdrActionCacheModel.description = getDescription();

		String description = mdrActionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			mdrActionCacheModel.description = null;
		}

		mdrActionCacheModel.type = getType();

		String type = mdrActionCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			mdrActionCacheModel.type = null;
		}

		mdrActionCacheModel.typeSettings = getTypeSettings();

		String typeSettings = mdrActionCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			mdrActionCacheModel.typeSettings = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mdrActionCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			mdrActionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return mdrActionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MDRAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MDRAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRAction, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((MDRAction)this));
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
		Map<String, Function<MDRAction, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MDRAction, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MDRAction, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((MDRAction)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MDRAction>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _actionId;
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
	private long _classNameId;
	private long _classPK;
	private long _ruleGroupInstanceId;
	private long _originalRuleGroupInstanceId;
	private boolean _setOriginalRuleGroupInstanceId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _type;
	private String _typeSettings;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private MDRAction _escapedModel;

}