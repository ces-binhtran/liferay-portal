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

package com.liferay.friendly.url.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalizationModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
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
 * The base model implementation for the FriendlyURLEntryLocalization service. Represents a row in the &quot;FriendlyURLEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FriendlyURLEntryLocalizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FriendlyURLEntryLocalizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryLocalizationImpl
 * @generated
 */
public class FriendlyURLEntryLocalizationModelImpl
	extends BaseModelImpl<FriendlyURLEntryLocalization>
	implements FriendlyURLEntryLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a friendly url entry localization model instance should use the <code>FriendlyURLEntryLocalization</code> interface instead.
	 */
	public static final String TABLE_NAME = "FriendlyURLEntryLocalization";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"friendlyURLEntryLocalizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"friendlyURLEntryId", Types.BIGINT},
		{"languageId", Types.VARCHAR}, {"urlTitle", Types.VARCHAR},
		{"groupId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("urlTitle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FriendlyURLEntryLocalization (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,friendlyURLEntryLocalizationId LONG not null,companyId LONG,friendlyURLEntryId LONG,languageId VARCHAR(75) null,urlTitle VARCHAR(255) null,groupId LONG,classNameId LONG,classPK LONG,primary key (friendlyURLEntryLocalizationId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table FriendlyURLEntryLocalization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY friendlyURLEntryLocalization.friendlyURLEntryLocalizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FriendlyURLEntryLocalization.friendlyURLEntryLocalizationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

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
	public static final long FRIENDLYURLENTRYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LANGUAGEID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long URLTITLE_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FRIENDLYURLENTRYLOCALIZATIONID_COLUMN_BITMASK =
		64L;

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

	public FriendlyURLEntryLocalizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _friendlyURLEntryLocalizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFriendlyURLEntryLocalizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _friendlyURLEntryLocalizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FriendlyURLEntryLocalization.class;
	}

	@Override
	public String getModelClassName() {
		return FriendlyURLEntryLocalization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FriendlyURLEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(FriendlyURLEntryLocalization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FriendlyURLEntryLocalization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FriendlyURLEntryLocalization, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FriendlyURLEntryLocalization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FriendlyURLEntryLocalization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FriendlyURLEntryLocalization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, FriendlyURLEntryLocalization>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			FriendlyURLEntryLocalization.class.getClassLoader(),
			FriendlyURLEntryLocalization.class, ModelWrapper.class);

		try {
			Constructor<FriendlyURLEntryLocalization> constructor =
				(Constructor<FriendlyURLEntryLocalization>)
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
		<String, Function<FriendlyURLEntryLocalization, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<FriendlyURLEntryLocalization, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<FriendlyURLEntryLocalization, Object>>();
		Map<String, BiConsumer<FriendlyURLEntryLocalization, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<FriendlyURLEntryLocalization, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", FriendlyURLEntryLocalization::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", FriendlyURLEntryLocalization::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setCtCollectionId);
		attributeGetterFunctions.put(
			"friendlyURLEntryLocalizationId",
			FriendlyURLEntryLocalization::getFriendlyURLEntryLocalizationId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryLocalizationId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::
					setFriendlyURLEntryLocalizationId);
		attributeGetterFunctions.put(
			"companyId", FriendlyURLEntryLocalization::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setCompanyId);
		attributeGetterFunctions.put(
			"friendlyURLEntryId",
			FriendlyURLEntryLocalization::getFriendlyURLEntryId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setFriendlyURLEntryId);
		attributeGetterFunctions.put(
			"languageId", FriendlyURLEntryLocalization::getLanguageId);
		attributeSetterBiConsumers.put(
			"languageId",
			(BiConsumer<FriendlyURLEntryLocalization, String>)
				FriendlyURLEntryLocalization::setLanguageId);
		attributeGetterFunctions.put(
			"urlTitle", FriendlyURLEntryLocalization::getUrlTitle);
		attributeSetterBiConsumers.put(
			"urlTitle",
			(BiConsumer<FriendlyURLEntryLocalization, String>)
				FriendlyURLEntryLocalization::setUrlTitle);
		attributeGetterFunctions.put(
			"groupId", FriendlyURLEntryLocalization::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setGroupId);
		attributeGetterFunctions.put(
			"classNameId", FriendlyURLEntryLocalization::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", FriendlyURLEntryLocalization::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setClassPK);

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
	public long getFriendlyURLEntryLocalizationId() {
		return _friendlyURLEntryLocalizationId;
	}

	@Override
	public void setFriendlyURLEntryLocalizationId(
		long friendlyURLEntryLocalizationId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friendlyURLEntryLocalizationId = friendlyURLEntryLocalizationId;
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

	@Override
	public long getFriendlyURLEntryId() {
		return _friendlyURLEntryId;
	}

	@Override
	public void setFriendlyURLEntryId(long friendlyURLEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friendlyURLEntryId = friendlyURLEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalFriendlyURLEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("friendlyURLEntryId"));
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_languageId = languageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLanguageId() {
		return getColumnOriginalValue("languageId");
	}

	@Override
	public String getUrlTitle() {
		if (_urlTitle == null) {
			return "";
		}
		else {
			return _urlTitle;
		}
	}

	@Override
	public void setUrlTitle(String urlTitle) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_urlTitle = urlTitle;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUrlTitle() {
		return getColumnOriginalValue("urlTitle");
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
			getCompanyId(), FriendlyURLEntryLocalization.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FriendlyURLEntryLocalization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FriendlyURLEntryLocalization>
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
		FriendlyURLEntryLocalizationImpl friendlyURLEntryLocalizationImpl =
			new FriendlyURLEntryLocalizationImpl();

		friendlyURLEntryLocalizationImpl.setMvccVersion(getMvccVersion());
		friendlyURLEntryLocalizationImpl.setCtCollectionId(getCtCollectionId());
		friendlyURLEntryLocalizationImpl.setFriendlyURLEntryLocalizationId(
			getFriendlyURLEntryLocalizationId());
		friendlyURLEntryLocalizationImpl.setCompanyId(getCompanyId());
		friendlyURLEntryLocalizationImpl.setFriendlyURLEntryId(
			getFriendlyURLEntryId());
		friendlyURLEntryLocalizationImpl.setLanguageId(getLanguageId());
		friendlyURLEntryLocalizationImpl.setUrlTitle(getUrlTitle());
		friendlyURLEntryLocalizationImpl.setGroupId(getGroupId());
		friendlyURLEntryLocalizationImpl.setClassNameId(getClassNameId());
		friendlyURLEntryLocalizationImpl.setClassPK(getClassPK());

		friendlyURLEntryLocalizationImpl.resetOriginalValues();

		return friendlyURLEntryLocalizationImpl;
	}

	@Override
	public int compareTo(
		FriendlyURLEntryLocalization friendlyURLEntryLocalization) {

		long primaryKey = friendlyURLEntryLocalization.getPrimaryKey();

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

		if (!(object instanceof FriendlyURLEntryLocalization)) {
			return false;
		}

		FriendlyURLEntryLocalization friendlyURLEntryLocalization =
			(FriendlyURLEntryLocalization)object;

		long primaryKey = friendlyURLEntryLocalization.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<FriendlyURLEntryLocalization> toCacheModel() {
		FriendlyURLEntryLocalizationCacheModel
			friendlyURLEntryLocalizationCacheModel =
				new FriendlyURLEntryLocalizationCacheModel();

		friendlyURLEntryLocalizationCacheModel.mvccVersion = getMvccVersion();

		friendlyURLEntryLocalizationCacheModel.ctCollectionId =
			getCtCollectionId();

		friendlyURLEntryLocalizationCacheModel.friendlyURLEntryLocalizationId =
			getFriendlyURLEntryLocalizationId();

		friendlyURLEntryLocalizationCacheModel.companyId = getCompanyId();

		friendlyURLEntryLocalizationCacheModel.friendlyURLEntryId =
			getFriendlyURLEntryId();

		friendlyURLEntryLocalizationCacheModel.languageId = getLanguageId();

		String languageId = friendlyURLEntryLocalizationCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			friendlyURLEntryLocalizationCacheModel.languageId = null;
		}

		friendlyURLEntryLocalizationCacheModel.urlTitle = getUrlTitle();

		String urlTitle = friendlyURLEntryLocalizationCacheModel.urlTitle;

		if ((urlTitle != null) && (urlTitle.length() == 0)) {
			friendlyURLEntryLocalizationCacheModel.urlTitle = null;
		}

		friendlyURLEntryLocalizationCacheModel.groupId = getGroupId();

		friendlyURLEntryLocalizationCacheModel.classNameId = getClassNameId();

		friendlyURLEntryLocalizationCacheModel.classPK = getClassPK();

		return friendlyURLEntryLocalizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FriendlyURLEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(FriendlyURLEntryLocalization)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

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
		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<FriendlyURLEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(FriendlyURLEntryLocalization)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, FriendlyURLEntryLocalization>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _friendlyURLEntryLocalizationId;
	private long _companyId;
	private long _friendlyURLEntryId;
	private String _languageId;
	private String _urlTitle;
	private long _groupId;
	private long _classNameId;
	private long _classPK;

	public <T> T getColumnValue(String columnName) {
		Function<FriendlyURLEntryLocalization, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((FriendlyURLEntryLocalization)this);
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
			"friendlyURLEntryLocalizationId", _friendlyURLEntryLocalizationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("friendlyURLEntryId", _friendlyURLEntryId);
		_columnOriginalValues.put("languageId", _languageId);
		_columnOriginalValues.put("urlTitle", _urlTitle);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
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

		columnBitmasks.put("friendlyURLEntryLocalizationId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("friendlyURLEntryId", 16L);

		columnBitmasks.put("languageId", 32L);

		columnBitmasks.put("urlTitle", 64L);

		columnBitmasks.put("groupId", 128L);

		columnBitmasks.put("classNameId", 256L);

		columnBitmasks.put("classPK", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private FriendlyURLEntryLocalization _escapedModel;

}