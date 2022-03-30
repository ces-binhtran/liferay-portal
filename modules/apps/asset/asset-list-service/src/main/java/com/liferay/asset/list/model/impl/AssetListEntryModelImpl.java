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

package com.liferay.asset.list.model.impl;

import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntryModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

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
 * The base model implementation for the AssetListEntry service. Represents a row in the &quot;AssetListEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AssetListEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetListEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryImpl
 * @generated
 */
@JSON(strict = true)
public class AssetListEntryModelImpl
	extends BaseModelImpl<AssetListEntry> implements AssetListEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset list entry model instance should use the <code>AssetListEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "AssetListEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"assetListEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"assetListEntryKey", Types.VARCHAR}, {"title", Types.VARCHAR},
		{"type_", Types.INTEGER}, {"assetEntrySubtype", Types.VARCHAR},
		{"assetEntryType", Types.VARCHAR}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assetListEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assetListEntryKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("assetEntrySubtype", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assetEntryType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AssetListEntry (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,assetListEntryId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,assetListEntryKey VARCHAR(75) null,title VARCHAR(75) null,type_ INTEGER,assetEntrySubtype VARCHAR(255) null,assetEntryType VARCHAR(255) null,lastPublishDate DATE null,primary key (assetListEntryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table AssetListEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY assetListEntry.assetListEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AssetListEntry.assetListEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETENTRYSUBTYPE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETENTRYTYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETLISTENTRYKEY_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TITLE_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 128L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ASSETLISTENTRYID_COLUMN_BITMASK = 256L;

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

	public AssetListEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetListEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetListEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetListEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetListEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AssetListEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AssetListEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AssetListEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AssetListEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AssetListEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AssetListEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AssetListEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AssetListEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AssetListEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AssetListEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AssetListEntry.class.getClassLoader(), AssetListEntry.class,
			ModelWrapper.class);

		try {
			Constructor<AssetListEntry> constructor =
				(Constructor<AssetListEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<AssetListEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AssetListEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AssetListEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<AssetListEntry, Object>>();
		Map<String, BiConsumer<AssetListEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<AssetListEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AssetListEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AssetListEntry, Long>)AssetListEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", AssetListEntry::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<AssetListEntry, Long>)
				AssetListEntry::setCtCollectionId);
		attributeGetterFunctions.put("uuid", AssetListEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<AssetListEntry, String>)AssetListEntry::setUuid);
		attributeGetterFunctions.put(
			"assetListEntryId", AssetListEntry::getAssetListEntryId);
		attributeSetterBiConsumers.put(
			"assetListEntryId",
			(BiConsumer<AssetListEntry, Long>)
				AssetListEntry::setAssetListEntryId);
		attributeGetterFunctions.put("groupId", AssetListEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<AssetListEntry, Long>)AssetListEntry::setGroupId);
		attributeGetterFunctions.put("companyId", AssetListEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AssetListEntry, Long>)AssetListEntry::setCompanyId);
		attributeGetterFunctions.put("userId", AssetListEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<AssetListEntry, Long>)AssetListEntry::setUserId);
		attributeGetterFunctions.put("userName", AssetListEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<AssetListEntry, String>)AssetListEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", AssetListEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AssetListEntry, Date>)AssetListEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AssetListEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AssetListEntry, Date>)AssetListEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"assetListEntryKey", AssetListEntry::getAssetListEntryKey);
		attributeSetterBiConsumers.put(
			"assetListEntryKey",
			(BiConsumer<AssetListEntry, String>)
				AssetListEntry::setAssetListEntryKey);
		attributeGetterFunctions.put("title", AssetListEntry::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<AssetListEntry, String>)AssetListEntry::setTitle);
		attributeGetterFunctions.put("type", AssetListEntry::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<AssetListEntry, Integer>)AssetListEntry::setType);
		attributeGetterFunctions.put(
			"assetEntrySubtype", AssetListEntry::getAssetEntrySubtype);
		attributeSetterBiConsumers.put(
			"assetEntrySubtype",
			(BiConsumer<AssetListEntry, String>)
				AssetListEntry::setAssetEntrySubtype);
		attributeGetterFunctions.put(
			"assetEntryType", AssetListEntry::getAssetEntryType);
		attributeSetterBiConsumers.put(
			"assetEntryType",
			(BiConsumer<AssetListEntry, String>)
				AssetListEntry::setAssetEntryType);
		attributeGetterFunctions.put(
			"lastPublishDate", AssetListEntry::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<AssetListEntry, Date>)
				AssetListEntry::setLastPublishDate);

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
	public long getAssetListEntryId() {
		return _assetListEntryId;
	}

	@Override
	public void setAssetListEntryId(long assetListEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntryId = assetListEntryId;
	}

	@JSON
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
	public String getAssetListEntryKey() {
		if (_assetListEntryKey == null) {
			return "";
		}
		else {
			return _assetListEntryKey;
		}
	}

	@Override
	public void setAssetListEntryKey(String assetListEntryKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetListEntryKey = assetListEntryKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalAssetListEntryKey() {
		return getColumnOriginalValue("assetListEntryKey");
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalTitle() {
		return getColumnOriginalValue("title");
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
	}

	@JSON
	@Override
	public String getAssetEntrySubtype() {
		if (_assetEntrySubtype == null) {
			return "";
		}
		else {
			return _assetEntrySubtype;
		}
	}

	@Override
	public void setAssetEntrySubtype(String assetEntrySubtype) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetEntrySubtype = assetEntrySubtype;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalAssetEntrySubtype() {
		return getColumnOriginalValue("assetEntrySubtype");
	}

	@JSON
	@Override
	public String getAssetEntryType() {
		if (_assetEntryType == null) {
			return "";
		}
		else {
			return _assetEntryType;
		}
	}

	@Override
	public void setAssetEntryType(String assetEntryType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_assetEntryType = assetEntryType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalAssetEntryType() {
		return getColumnOriginalValue("assetEntryType");
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AssetListEntry.class.getName()));
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
			getCompanyId(), AssetListEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AssetListEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AssetListEntry>
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
		AssetListEntryImpl assetListEntryImpl = new AssetListEntryImpl();

		assetListEntryImpl.setMvccVersion(getMvccVersion());
		assetListEntryImpl.setCtCollectionId(getCtCollectionId());
		assetListEntryImpl.setUuid(getUuid());
		assetListEntryImpl.setAssetListEntryId(getAssetListEntryId());
		assetListEntryImpl.setGroupId(getGroupId());
		assetListEntryImpl.setCompanyId(getCompanyId());
		assetListEntryImpl.setUserId(getUserId());
		assetListEntryImpl.setUserName(getUserName());
		assetListEntryImpl.setCreateDate(getCreateDate());
		assetListEntryImpl.setModifiedDate(getModifiedDate());
		assetListEntryImpl.setAssetListEntryKey(getAssetListEntryKey());
		assetListEntryImpl.setTitle(getTitle());
		assetListEntryImpl.setType(getType());
		assetListEntryImpl.setAssetEntrySubtype(getAssetEntrySubtype());
		assetListEntryImpl.setAssetEntryType(getAssetEntryType());
		assetListEntryImpl.setLastPublishDate(getLastPublishDate());

		assetListEntryImpl.resetOriginalValues();

		return assetListEntryImpl;
	}

	@Override
	public AssetListEntry cloneWithOriginalValues() {
		AssetListEntryImpl assetListEntryImpl = new AssetListEntryImpl();

		assetListEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		assetListEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		assetListEntryImpl.setUuid(
			this.<String>getColumnOriginalValue("uuid_"));
		assetListEntryImpl.setAssetListEntryId(
			this.<Long>getColumnOriginalValue("assetListEntryId"));
		assetListEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		assetListEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		assetListEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		assetListEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		assetListEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		assetListEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		assetListEntryImpl.setAssetListEntryKey(
			this.<String>getColumnOriginalValue("assetListEntryKey"));
		assetListEntryImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));
		assetListEntryImpl.setType(
			this.<Integer>getColumnOriginalValue("type_"));
		assetListEntryImpl.setAssetEntrySubtype(
			this.<String>getColumnOriginalValue("assetEntrySubtype"));
		assetListEntryImpl.setAssetEntryType(
			this.<String>getColumnOriginalValue("assetEntryType"));
		assetListEntryImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return assetListEntryImpl;
	}

	@Override
	public int compareTo(AssetListEntry assetListEntry) {
		long primaryKey = assetListEntry.getPrimaryKey();

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

		if (!(object instanceof AssetListEntry)) {
			return false;
		}

		AssetListEntry assetListEntry = (AssetListEntry)object;

		long primaryKey = assetListEntry.getPrimaryKey();

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
	public CacheModel<AssetListEntry> toCacheModel() {
		AssetListEntryCacheModel assetListEntryCacheModel =
			new AssetListEntryCacheModel();

		assetListEntryCacheModel.mvccVersion = getMvccVersion();

		assetListEntryCacheModel.ctCollectionId = getCtCollectionId();

		assetListEntryCacheModel.uuid = getUuid();

		String uuid = assetListEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetListEntryCacheModel.uuid = null;
		}

		assetListEntryCacheModel.assetListEntryId = getAssetListEntryId();

		assetListEntryCacheModel.groupId = getGroupId();

		assetListEntryCacheModel.companyId = getCompanyId();

		assetListEntryCacheModel.userId = getUserId();

		assetListEntryCacheModel.userName = getUserName();

		String userName = assetListEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetListEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetListEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			assetListEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetListEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			assetListEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetListEntryCacheModel.assetListEntryKey = getAssetListEntryKey();

		String assetListEntryKey = assetListEntryCacheModel.assetListEntryKey;

		if ((assetListEntryKey != null) && (assetListEntryKey.length() == 0)) {
			assetListEntryCacheModel.assetListEntryKey = null;
		}

		assetListEntryCacheModel.title = getTitle();

		String title = assetListEntryCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			assetListEntryCacheModel.title = null;
		}

		assetListEntryCacheModel.type = getType();

		assetListEntryCacheModel.assetEntrySubtype = getAssetEntrySubtype();

		String assetEntrySubtype = assetListEntryCacheModel.assetEntrySubtype;

		if ((assetEntrySubtype != null) && (assetEntrySubtype.length() == 0)) {
			assetListEntryCacheModel.assetEntrySubtype = null;
		}

		assetListEntryCacheModel.assetEntryType = getAssetEntryType();

		String assetEntryType = assetListEntryCacheModel.assetEntryType;

		if ((assetEntryType != null) && (assetEntryType.length() == 0)) {
			assetListEntryCacheModel.assetEntryType = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			assetListEntryCacheModel.lastPublishDate =
				lastPublishDate.getTime();
		}
		else {
			assetListEntryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return assetListEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AssetListEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AssetListEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((AssetListEntry)this);

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
		Map<String, Function<AssetListEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AssetListEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetListEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AssetListEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AssetListEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _assetListEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _assetListEntryKey;
	private String _title;
	private int _type;
	private String _assetEntrySubtype;
	private String _assetEntryType;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AssetListEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AssetListEntry)this);
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
		_columnOriginalValues.put("assetListEntryId", _assetListEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("assetListEntryKey", _assetListEntryKey);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("assetEntrySubtype", _assetEntrySubtype);
		_columnOriginalValues.put("assetEntryType", _assetEntryType);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("assetListEntryId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("assetListEntryKey", 1024L);

		columnBitmasks.put("title", 2048L);

		columnBitmasks.put("type_", 4096L);

		columnBitmasks.put("assetEntrySubtype", 8192L);

		columnBitmasks.put("assetEntryType", 16384L);

		columnBitmasks.put("lastPublishDate", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AssetListEntry _escapedModel;

}