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

package com.liferay.portal.reports.engine.console.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.model.SourceModel;
import com.liferay.portal.reports.engine.console.model.SourceSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the Source service. Represents a row in the &quot;Reports_Source&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SourceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SourceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SourceImpl
 * @see Source
 * @see SourceModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SourceModelImpl extends BaseModelImpl<Source>
	implements SourceModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a source model instance should use the {@link Source} interface instead.
	 */
	public static final String TABLE_NAME = "Reports_Source";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "sourceId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "lastPublishDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "driverClassName", Types.VARCHAR },
			{ "driverUrl", Types.VARCHAR },
			{ "driverUserName", Types.VARCHAR },
			{ "driverPassword", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sourceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverUrl", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("driverPassword", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table Reports_Source (uuid_ VARCHAR(75) null,sourceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,name STRING null,driverClassName VARCHAR(75) null,driverUrl STRING null,driverUserName VARCHAR(75) null,driverPassword VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Reports_Source";
	public static final String ORDER_BY_JPQL = " ORDER BY source.sourceId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Reports_Source.sourceId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.reports.engine.console.model.Source"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.reports.engine.console.model.Source"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.reports.engine.console.model.Source"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long SOURCEID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Source toModel(SourceSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Source model = new SourceImpl();

		model.setUuid(soapModel.getUuid());
		model.setSourceId(soapModel.getSourceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setName(soapModel.getName());
		model.setDriverClassName(soapModel.getDriverClassName());
		model.setDriverUrl(soapModel.getDriverUrl());
		model.setDriverUserName(soapModel.getDriverUserName());
		model.setDriverPassword(soapModel.getDriverPassword());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Source> toModels(SourceSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Source> models = new ArrayList<Source>(soapModels.length);

		for (SourceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.reports.engine.console.model.Source"));

	public SourceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sourceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSourceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sourceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Source.class;
	}

	@Override
	public String getModelClassName() {
		return Source.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sourceId", getSourceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("name", getName());
		attributes.put("driverClassName", getDriverClassName());
		attributes.put("driverUrl", getDriverUrl());
		attributes.put("driverUserName", getDriverUserName());
		attributes.put("driverPassword", getDriverPassword());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long sourceId = (Long)attributes.get("sourceId");

		if (sourceId != null) {
			setSourceId(sourceId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String driverClassName = (String)attributes.get("driverClassName");

		if (driverClassName != null) {
			setDriverClassName(driverClassName);
		}

		String driverUrl = (String)attributes.get("driverUrl");

		if (driverUrl != null) {
			setDriverUrl(driverUrl);
		}

		String driverUserName = (String)attributes.get("driverUserName");

		if (driverUserName != null) {
			setDriverUserName(driverUserName);
		}

		String driverPassword = (String)attributes.get("driverPassword");

		if (driverPassword != null) {
			setDriverPassword(driverPassword);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
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
	public long getSourceId() {
		return _sourceId;
	}

	@Override
	public void setSourceId(long sourceId) {
		_sourceId = sourceId;
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
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
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
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
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
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
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
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
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

		setName(LocalizationUtil.updateLocalization(nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDriverClassName() {
		if (_driverClassName == null) {
			return StringPool.BLANK;
		}
		else {
			return _driverClassName;
		}
	}

	@Override
	public void setDriverClassName(String driverClassName) {
		_driverClassName = driverClassName;
	}

	@JSON
	@Override
	public String getDriverUrl() {
		if (_driverUrl == null) {
			return StringPool.BLANK;
		}
		else {
			return _driverUrl;
		}
	}

	@Override
	public void setDriverUrl(String driverUrl) {
		_driverUrl = driverUrl;
	}

	@JSON
	@Override
	public String getDriverUserName() {
		if (_driverUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _driverUserName;
		}
	}

	@Override
	public void setDriverUserName(String driverUserName) {
		_driverUserName = driverUserName;
	}

	@JSON
	@Override
	public String getDriverPassword() {
		if (_driverPassword == null) {
			return StringPool.BLANK;
		}
		else {
			return _driverPassword;
		}
	}

	@Override
	public void setDriverPassword(String driverPassword) {
		_driverPassword = driverPassword;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Source.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Source.class.getName(), getPrimaryKey());
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

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(Source.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

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
	public Source toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Source)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SourceImpl sourceImpl = new SourceImpl();

		sourceImpl.setUuid(getUuid());
		sourceImpl.setSourceId(getSourceId());
		sourceImpl.setGroupId(getGroupId());
		sourceImpl.setCompanyId(getCompanyId());
		sourceImpl.setUserId(getUserId());
		sourceImpl.setUserName(getUserName());
		sourceImpl.setCreateDate(getCreateDate());
		sourceImpl.setModifiedDate(getModifiedDate());
		sourceImpl.setLastPublishDate(getLastPublishDate());
		sourceImpl.setName(getName());
		sourceImpl.setDriverClassName(getDriverClassName());
		sourceImpl.setDriverUrl(getDriverUrl());
		sourceImpl.setDriverUserName(getDriverUserName());
		sourceImpl.setDriverPassword(getDriverPassword());

		sourceImpl.resetOriginalValues();

		return sourceImpl;
	}

	@Override
	public int compareTo(Source source) {
		long primaryKey = source.getPrimaryKey();

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

		if (!(obj instanceof Source)) {
			return false;
		}

		Source source = (Source)obj;

		long primaryKey = source.getPrimaryKey();

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
		SourceModelImpl sourceModelImpl = this;

		sourceModelImpl._originalUuid = sourceModelImpl._uuid;

		sourceModelImpl._originalGroupId = sourceModelImpl._groupId;

		sourceModelImpl._setOriginalGroupId = false;

		sourceModelImpl._originalCompanyId = sourceModelImpl._companyId;

		sourceModelImpl._setOriginalCompanyId = false;

		sourceModelImpl._setModifiedDate = false;

		sourceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Source> toCacheModel() {
		SourceCacheModel sourceCacheModel = new SourceCacheModel();

		sourceCacheModel.uuid = getUuid();

		String uuid = sourceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			sourceCacheModel.uuid = null;
		}

		sourceCacheModel.sourceId = getSourceId();

		sourceCacheModel.groupId = getGroupId();

		sourceCacheModel.companyId = getCompanyId();

		sourceCacheModel.userId = getUserId();

		sourceCacheModel.userName = getUserName();

		String userName = sourceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			sourceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			sourceCacheModel.createDate = createDate.getTime();
		}
		else {
			sourceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			sourceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			sourceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			sourceCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			sourceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		sourceCacheModel.name = getName();

		String name = sourceCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			sourceCacheModel.name = null;
		}

		sourceCacheModel.driverClassName = getDriverClassName();

		String driverClassName = sourceCacheModel.driverClassName;

		if ((driverClassName != null) && (driverClassName.length() == 0)) {
			sourceCacheModel.driverClassName = null;
		}

		sourceCacheModel.driverUrl = getDriverUrl();

		String driverUrl = sourceCacheModel.driverUrl;

		if ((driverUrl != null) && (driverUrl.length() == 0)) {
			sourceCacheModel.driverUrl = null;
		}

		sourceCacheModel.driverUserName = getDriverUserName();

		String driverUserName = sourceCacheModel.driverUserName;

		if ((driverUserName != null) && (driverUserName.length() == 0)) {
			sourceCacheModel.driverUserName = null;
		}

		sourceCacheModel.driverPassword = getDriverPassword();

		String driverPassword = sourceCacheModel.driverPassword;

		if ((driverPassword != null) && (driverPassword.length() == 0)) {
			sourceCacheModel.driverPassword = null;
		}

		return sourceCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", sourceId=");
		sb.append(getSourceId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", driverClassName=");
		sb.append(getDriverClassName());
		sb.append(", driverUrl=");
		sb.append(getDriverUrl());
		sb.append(", driverUserName=");
		sb.append(getDriverUserName());
		sb.append(", driverPassword=");
		sb.append(getDriverPassword());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.reports.engine.console.model.Source");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceId</column-name><column-value><![CDATA[");
		sb.append(getSourceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverClassName</column-name><column-value><![CDATA[");
		sb.append(getDriverClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverUrl</column-name><column-value><![CDATA[");
		sb.append(getDriverUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverUserName</column-name><column-value><![CDATA[");
		sb.append(getDriverUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverPassword</column-name><column-value><![CDATA[");
		sb.append(getDriverPassword());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Source.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Source.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _sourceId;
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
	private Date _lastPublishDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _driverClassName;
	private String _driverUrl;
	private String _driverUserName;
	private String _driverPassword;
	private long _columnBitmask;
	private Source _escapedModel;
}