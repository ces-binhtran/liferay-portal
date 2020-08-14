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

import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMContentModel;
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

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DDMContent service. Represents a row in the &quot;DDMContent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DDMContentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMContentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMContentImpl
 * @generated
 */
public class DDMContentModelImpl
	extends BaseModelImpl<DDMContent> implements DDMContentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm content model instance should use the <code>DDMContent</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMContent";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"contentId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"data_", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("contentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("data_", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMContent (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,contentId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,description STRING null,data_ TEXT null,primary key (contentId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table DDMContent";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmContent.contentId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMContent.contentId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long CONTENTID_COLUMN_BITMASK = 8L;

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

	public DDMContentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMContent.class;
	}

	@Override
	public String getModelClassName() {
		return DDMContent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMContent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMContent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMContent, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((DDMContent)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMContent, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMContent, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMContent)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMContent, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMContent, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMContent>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMContent.class.getClassLoader(), DDMContent.class,
			ModelWrapper.class);

		try {
			Constructor<DDMContent> constructor =
				(Constructor<DDMContent>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DDMContent, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMContent, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMContent, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<DDMContent, Object>>();
		Map<String, BiConsumer<DDMContent, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DDMContent, ?>>();

		attributeGetterFunctions.put("mvccVersion", DDMContent::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DDMContent, Long>)DDMContent::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DDMContent::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DDMContent, Long>)DDMContent::setCtCollectionId);
		attributeGetterFunctions.put("uuid", DDMContent::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<DDMContent, String>)DDMContent::setUuid);
		attributeGetterFunctions.put("contentId", DDMContent::getContentId);
		attributeSetterBiConsumers.put(
			"contentId",
			(BiConsumer<DDMContent, Long>)DDMContent::setContentId);
		attributeGetterFunctions.put("groupId", DDMContent::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<DDMContent, Long>)DDMContent::setGroupId);
		attributeGetterFunctions.put("companyId", DDMContent::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DDMContent, Long>)DDMContent::setCompanyId);
		attributeGetterFunctions.put("userId", DDMContent::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<DDMContent, Long>)DDMContent::setUserId);
		attributeGetterFunctions.put("userName", DDMContent::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<DDMContent, String>)DDMContent::setUserName);
		attributeGetterFunctions.put("createDate", DDMContent::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<DDMContent, Date>)DDMContent::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", DDMContent::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<DDMContent, Date>)DDMContent::setModifiedDate);
		attributeGetterFunctions.put("name", DDMContent::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<DDMContent, String>)DDMContent::setName);
		attributeGetterFunctions.put("description", DDMContent::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<DDMContent, String>)DDMContent::setDescription);
		attributeGetterFunctions.put("data", DDMContent::getData);
		attributeSetterBiConsumers.put(
			"data", (BiConsumer<DDMContent, String>)DDMContent::setData);

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
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_ctCollectionId = ctCollectionId;
	}

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

	@Override
	public long getContentId() {
		return _contentId;
	}

	@Override
	public void setContentId(long contentId) {
		_contentId = contentId;
	}

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

	@Override
	public String getData() {
		if (_data == null) {
			return "";
		}
		else {
			return _data;
		}
	}

	@Override
	public void setData(String data) {
		_data = data;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(DDMContent.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMContent.class.getName(), getPrimaryKey());
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
			DDMContent.class.getName(), getPrimaryKey(), defaultLocale,
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
	}

	@Override
	public DDMContent toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMContent>
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
		DDMContentImpl ddmContentImpl = new DDMContentImpl();

		ddmContentImpl.setMvccVersion(getMvccVersion());
		ddmContentImpl.setCtCollectionId(getCtCollectionId());
		ddmContentImpl.setUuid(getUuid());
		ddmContentImpl.setContentId(getContentId());
		ddmContentImpl.setGroupId(getGroupId());
		ddmContentImpl.setCompanyId(getCompanyId());
		ddmContentImpl.setUserId(getUserId());
		ddmContentImpl.setUserName(getUserName());
		ddmContentImpl.setCreateDate(getCreateDate());
		ddmContentImpl.setModifiedDate(getModifiedDate());
		ddmContentImpl.setName(getName());
		ddmContentImpl.setDescription(getDescription());
		ddmContentImpl.setData(getData());

		ddmContentImpl.resetOriginalValues();

		return ddmContentImpl;
	}

	@Override
	public int compareTo(DDMContent ddmContent) {
		long primaryKey = ddmContent.getPrimaryKey();

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

		if (!(object instanceof DDMContent)) {
			return false;
		}

		DDMContent ddmContent = (DDMContent)object;

		long primaryKey = ddmContent.getPrimaryKey();

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
		DDMContentModelImpl ddmContentModelImpl = this;

		ddmContentModelImpl._originalUuid = ddmContentModelImpl._uuid;

		ddmContentModelImpl._originalGroupId = ddmContentModelImpl._groupId;

		ddmContentModelImpl._setOriginalGroupId = false;

		ddmContentModelImpl._originalCompanyId = ddmContentModelImpl._companyId;

		ddmContentModelImpl._setOriginalCompanyId = false;

		ddmContentModelImpl._setModifiedDate = false;

		ddmContentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMContent> toCacheModel() {
		DDMContentCacheModel ddmContentCacheModel = new DDMContentCacheModel();

		ddmContentCacheModel.mvccVersion = getMvccVersion();

		ddmContentCacheModel.ctCollectionId = getCtCollectionId();

		ddmContentCacheModel.uuid = getUuid();

		String uuid = ddmContentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmContentCacheModel.uuid = null;
		}

		ddmContentCacheModel.contentId = getContentId();

		ddmContentCacheModel.groupId = getGroupId();

		ddmContentCacheModel.companyId = getCompanyId();

		ddmContentCacheModel.userId = getUserId();

		ddmContentCacheModel.userName = getUserName();

		String userName = ddmContentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddmContentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddmContentCacheModel.createDate = createDate.getTime();
		}
		else {
			ddmContentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ddmContentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ddmContentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ddmContentCacheModel.name = getName();

		String name = ddmContentCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			ddmContentCacheModel.name = null;
		}

		ddmContentCacheModel.description = getDescription();

		String description = ddmContentCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			ddmContentCacheModel.description = null;
		}

		ddmContentCacheModel.data = getData();

		String data = ddmContentCacheModel.data;

		if ((data != null) && (data.length() == 0)) {
			ddmContentCacheModel.data = null;
		}

		return ddmContentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMContent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMContent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMContent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DDMContent)this));
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
		Map<String, Function<DDMContent, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMContent, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMContent, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DDMContent)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDMContent>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _originalUuid;
	private long _contentId;
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
	private String _data;
	private long _columnBitmask;
	private DDMContent _escapedModel;

}