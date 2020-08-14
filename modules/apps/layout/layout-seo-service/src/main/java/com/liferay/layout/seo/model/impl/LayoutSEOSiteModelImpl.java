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

package com.liferay.layout.seo.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.layout.seo.model.LayoutSEOSite;
import com.liferay.layout.seo.model.LayoutSEOSiteModel;
import com.liferay.layout.seo.model.LayoutSEOSiteSoap;
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
 * The base model implementation for the LayoutSEOSite service. Represents a row in the &quot;LayoutSEOSite&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutSEOSiteModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutSEOSiteImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOSiteImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutSEOSiteModelImpl
	extends BaseModelImpl<LayoutSEOSite> implements LayoutSEOSiteModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout seo site model instance should use the <code>LayoutSEOSite</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutSEOSite";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"layoutSEOSiteId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"openGraphEnabled", Types.BOOLEAN},
		{"openGraphImageAlt", Types.VARCHAR},
		{"openGraphImageFileEntryId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutSEOSiteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("openGraphEnabled", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("openGraphImageAlt", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("openGraphImageFileEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutSEOSite (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,layoutSEOSiteId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,openGraphEnabled BOOLEAN,openGraphImageAlt STRING null,openGraphImageFileEntryId LONG)";

	public static final String TABLE_SQL_DROP = "drop table LayoutSEOSite";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutSEOSite.layoutSEOSiteId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutSEOSite.layoutSEOSiteId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long LAYOUTSEOSITEID_COLUMN_BITMASK = 8L;

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
	public static LayoutSEOSite toModel(LayoutSEOSiteSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutSEOSite model = new LayoutSEOSiteImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setLayoutSEOSiteId(soapModel.getLayoutSEOSiteId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setOpenGraphEnabled(soapModel.isOpenGraphEnabled());
		model.setOpenGraphImageAlt(soapModel.getOpenGraphImageAlt());
		model.setOpenGraphImageFileEntryId(
			soapModel.getOpenGraphImageFileEntryId());

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
	public static List<LayoutSEOSite> toModels(LayoutSEOSiteSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<LayoutSEOSite> models = new ArrayList<LayoutSEOSite>(
			soapModels.length);

		for (LayoutSEOSiteSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public LayoutSEOSiteModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutSEOSiteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutSEOSiteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutSEOSiteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutSEOSite.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutSEOSite.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutSEOSite, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutSEOSite, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSEOSite, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LayoutSEOSite)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutSEOSite, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutSEOSite, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutSEOSite)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutSEOSite, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutSEOSite, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutSEOSite>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutSEOSite.class.getClassLoader(), LayoutSEOSite.class,
			ModelWrapper.class);

		try {
			Constructor<LayoutSEOSite> constructor =
				(Constructor<LayoutSEOSite>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LayoutSEOSite, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutSEOSite, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutSEOSite, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LayoutSEOSite, Object>>();
		Map<String, BiConsumer<LayoutSEOSite, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LayoutSEOSite, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", LayoutSEOSite::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutSEOSite, Long>)LayoutSEOSite::setMvccVersion);
		attributeGetterFunctions.put("uuid", LayoutSEOSite::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<LayoutSEOSite, String>)LayoutSEOSite::setUuid);
		attributeGetterFunctions.put(
			"layoutSEOSiteId", LayoutSEOSite::getLayoutSEOSiteId);
		attributeSetterBiConsumers.put(
			"layoutSEOSiteId",
			(BiConsumer<LayoutSEOSite, Long>)LayoutSEOSite::setLayoutSEOSiteId);
		attributeGetterFunctions.put("groupId", LayoutSEOSite::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<LayoutSEOSite, Long>)LayoutSEOSite::setGroupId);
		attributeGetterFunctions.put("companyId", LayoutSEOSite::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<LayoutSEOSite, Long>)LayoutSEOSite::setCompanyId);
		attributeGetterFunctions.put("userId", LayoutSEOSite::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<LayoutSEOSite, Long>)LayoutSEOSite::setUserId);
		attributeGetterFunctions.put("userName", LayoutSEOSite::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<LayoutSEOSite, String>)LayoutSEOSite::setUserName);
		attributeGetterFunctions.put(
			"createDate", LayoutSEOSite::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutSEOSite, Date>)LayoutSEOSite::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutSEOSite::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutSEOSite, Date>)LayoutSEOSite::setModifiedDate);
		attributeGetterFunctions.put(
			"openGraphEnabled", LayoutSEOSite::getOpenGraphEnabled);
		attributeSetterBiConsumers.put(
			"openGraphEnabled",
			(BiConsumer<LayoutSEOSite, Boolean>)
				LayoutSEOSite::setOpenGraphEnabled);
		attributeGetterFunctions.put(
			"openGraphImageAlt", LayoutSEOSite::getOpenGraphImageAlt);
		attributeSetterBiConsumers.put(
			"openGraphImageAlt",
			(BiConsumer<LayoutSEOSite, String>)
				LayoutSEOSite::setOpenGraphImageAlt);
		attributeGetterFunctions.put(
			"openGraphImageFileEntryId",
			LayoutSEOSite::getOpenGraphImageFileEntryId);
		attributeSetterBiConsumers.put(
			"openGraphImageFileEntryId",
			(BiConsumer<LayoutSEOSite, Long>)
				LayoutSEOSite::setOpenGraphImageFileEntryId);

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
	public long getLayoutSEOSiteId() {
		return _layoutSEOSiteId;
	}

	@Override
	public void setLayoutSEOSiteId(long layoutSEOSiteId) {
		_layoutSEOSiteId = layoutSEOSiteId;
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
	public boolean getOpenGraphEnabled() {
		return _openGraphEnabled;
	}

	@JSON
	@Override
	public boolean isOpenGraphEnabled() {
		return _openGraphEnabled;
	}

	@Override
	public void setOpenGraphEnabled(boolean openGraphEnabled) {
		_openGraphEnabled = openGraphEnabled;
	}

	@JSON
	@Override
	public String getOpenGraphImageAlt() {
		if (_openGraphImageAlt == null) {
			return "";
		}
		else {
			return _openGraphImageAlt;
		}
	}

	@Override
	public String getOpenGraphImageAlt(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getOpenGraphImageAlt(languageId);
	}

	@Override
	public String getOpenGraphImageAlt(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getOpenGraphImageAlt(languageId, useDefault);
	}

	@Override
	public String getOpenGraphImageAlt(String languageId) {
		return LocalizationUtil.getLocalization(
			getOpenGraphImageAlt(), languageId);
	}

	@Override
	public String getOpenGraphImageAlt(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getOpenGraphImageAlt(), languageId, useDefault);
	}

	@Override
	public String getOpenGraphImageAltCurrentLanguageId() {
		return _openGraphImageAltCurrentLanguageId;
	}

	@JSON
	@Override
	public String getOpenGraphImageAltCurrentValue() {
		Locale locale = getLocale(_openGraphImageAltCurrentLanguageId);

		return getOpenGraphImageAlt(locale);
	}

	@Override
	public Map<Locale, String> getOpenGraphImageAltMap() {
		return LocalizationUtil.getLocalizationMap(getOpenGraphImageAlt());
	}

	@Override
	public void setOpenGraphImageAlt(String openGraphImageAlt) {
		_openGraphImageAlt = openGraphImageAlt;
	}

	@Override
	public void setOpenGraphImageAlt(String openGraphImageAlt, Locale locale) {
		setOpenGraphImageAlt(
			openGraphImageAlt, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setOpenGraphImageAlt(
		String openGraphImageAlt, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(openGraphImageAlt)) {
			setOpenGraphImageAlt(
				LocalizationUtil.updateLocalization(
					getOpenGraphImageAlt(), "OpenGraphImageAlt",
					openGraphImageAlt, languageId, defaultLanguageId));
		}
		else {
			setOpenGraphImageAlt(
				LocalizationUtil.removeLocalization(
					getOpenGraphImageAlt(), "OpenGraphImageAlt", languageId));
		}
	}

	@Override
	public void setOpenGraphImageAltCurrentLanguageId(String languageId) {
		_openGraphImageAltCurrentLanguageId = languageId;
	}

	@Override
	public void setOpenGraphImageAltMap(
		Map<Locale, String> openGraphImageAltMap) {

		setOpenGraphImageAltMap(
			openGraphImageAltMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setOpenGraphImageAltMap(
		Map<Locale, String> openGraphImageAltMap, Locale defaultLocale) {

		if (openGraphImageAltMap == null) {
			return;
		}

		setOpenGraphImageAlt(
			LocalizationUtil.updateLocalization(
				openGraphImageAltMap, getOpenGraphImageAlt(),
				"OpenGraphImageAlt", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public long getOpenGraphImageFileEntryId() {
		return _openGraphImageFileEntryId;
	}

	@Override
	public void setOpenGraphImageFileEntryId(long openGraphImageFileEntryId) {
		_openGraphImageFileEntryId = openGraphImageFileEntryId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(LayoutSEOSite.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutSEOSite.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> openGraphImageAltMap = getOpenGraphImageAltMap();

		for (Map.Entry<Locale, String> entry :
				openGraphImageAltMap.entrySet()) {

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
		String xml = getOpenGraphImageAlt();

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
			LayoutSEOSite.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String openGraphImageAlt = getOpenGraphImageAlt(defaultLocale);

		if (Validator.isNull(openGraphImageAlt)) {
			setOpenGraphImageAlt(
				getOpenGraphImageAlt(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setOpenGraphImageAlt(
				getOpenGraphImageAlt(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public LayoutSEOSite toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutSEOSite>
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
		LayoutSEOSiteImpl layoutSEOSiteImpl = new LayoutSEOSiteImpl();

		layoutSEOSiteImpl.setMvccVersion(getMvccVersion());
		layoutSEOSiteImpl.setUuid(getUuid());
		layoutSEOSiteImpl.setLayoutSEOSiteId(getLayoutSEOSiteId());
		layoutSEOSiteImpl.setGroupId(getGroupId());
		layoutSEOSiteImpl.setCompanyId(getCompanyId());
		layoutSEOSiteImpl.setUserId(getUserId());
		layoutSEOSiteImpl.setUserName(getUserName());
		layoutSEOSiteImpl.setCreateDate(getCreateDate());
		layoutSEOSiteImpl.setModifiedDate(getModifiedDate());
		layoutSEOSiteImpl.setOpenGraphEnabled(isOpenGraphEnabled());
		layoutSEOSiteImpl.setOpenGraphImageAlt(getOpenGraphImageAlt());
		layoutSEOSiteImpl.setOpenGraphImageFileEntryId(
			getOpenGraphImageFileEntryId());

		layoutSEOSiteImpl.resetOriginalValues();

		return layoutSEOSiteImpl;
	}

	@Override
	public int compareTo(LayoutSEOSite layoutSEOSite) {
		long primaryKey = layoutSEOSite.getPrimaryKey();

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

		if (!(object instanceof LayoutSEOSite)) {
			return false;
		}

		LayoutSEOSite layoutSEOSite = (LayoutSEOSite)object;

		long primaryKey = layoutSEOSite.getPrimaryKey();

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
		LayoutSEOSiteModelImpl layoutSEOSiteModelImpl = this;

		layoutSEOSiteModelImpl._originalUuid = layoutSEOSiteModelImpl._uuid;

		layoutSEOSiteModelImpl._originalGroupId =
			layoutSEOSiteModelImpl._groupId;

		layoutSEOSiteModelImpl._setOriginalGroupId = false;

		layoutSEOSiteModelImpl._originalCompanyId =
			layoutSEOSiteModelImpl._companyId;

		layoutSEOSiteModelImpl._setOriginalCompanyId = false;

		layoutSEOSiteModelImpl._setModifiedDate = false;

		layoutSEOSiteModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutSEOSite> toCacheModel() {
		LayoutSEOSiteCacheModel layoutSEOSiteCacheModel =
			new LayoutSEOSiteCacheModel();

		layoutSEOSiteCacheModel.mvccVersion = getMvccVersion();

		layoutSEOSiteCacheModel.uuid = getUuid();

		String uuid = layoutSEOSiteCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			layoutSEOSiteCacheModel.uuid = null;
		}

		layoutSEOSiteCacheModel.layoutSEOSiteId = getLayoutSEOSiteId();

		layoutSEOSiteCacheModel.groupId = getGroupId();

		layoutSEOSiteCacheModel.companyId = getCompanyId();

		layoutSEOSiteCacheModel.userId = getUserId();

		layoutSEOSiteCacheModel.userName = getUserName();

		String userName = layoutSEOSiteCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutSEOSiteCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutSEOSiteCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutSEOSiteCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutSEOSiteCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutSEOSiteCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutSEOSiteCacheModel.openGraphEnabled = isOpenGraphEnabled();

		layoutSEOSiteCacheModel.openGraphImageAlt = getOpenGraphImageAlt();

		String openGraphImageAlt = layoutSEOSiteCacheModel.openGraphImageAlt;

		if ((openGraphImageAlt != null) && (openGraphImageAlt.length() == 0)) {
			layoutSEOSiteCacheModel.openGraphImageAlt = null;
		}

		layoutSEOSiteCacheModel.openGraphImageFileEntryId =
			getOpenGraphImageFileEntryId();

		return layoutSEOSiteCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutSEOSite, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutSEOSite, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSEOSite, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LayoutSEOSite)this));
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
		Map<String, Function<LayoutSEOSite, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutSEOSite, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSEOSite, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LayoutSEOSite)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, LayoutSEOSite>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _layoutSEOSiteId;
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
	private boolean _openGraphEnabled;
	private String _openGraphImageAlt;
	private String _openGraphImageAltCurrentLanguageId;
	private long _openGraphImageFileEntryId;
	private long _columnBitmask;
	private LayoutSEOSite _escapedModel;

}