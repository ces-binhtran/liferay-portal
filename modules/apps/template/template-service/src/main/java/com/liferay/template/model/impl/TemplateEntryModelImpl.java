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

package com.liferay.template.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.template.model.TemplateEntry;
import com.liferay.template.model.TemplateEntryModel;

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
 * The base model implementation for the TemplateEntry service. Represents a row in the &quot;TemplateEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>TemplateEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TemplateEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TemplateEntryImpl
 * @generated
 */
public class TemplateEntryModelImpl
	extends BaseModelImpl<TemplateEntry> implements TemplateEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a template entry model instance should use the <code>TemplateEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "TemplateEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"templateEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"ddmTemplateId", Types.BIGINT}, {"infoItemClassName", Types.VARCHAR},
		{"infoItemFormVariationKey", Types.VARCHAR},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("templateEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ddmTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("infoItemClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("infoItemFormVariationKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table TemplateEntry (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,templateEntryId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,ddmTemplateId LONG,infoItemClassName VARCHAR(75) null,infoItemFormVariationKey VARCHAR(75) null,lastPublishDate DATE null,primary key (templateEntryId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table TemplateEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY templateEntry.templateEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY TemplateEntry.templateEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long DDMTEMPLATEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long INFOITEMCLASSNAME_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long INFOITEMFORMVARIATIONKEY_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TEMPLATEENTRYID_COLUMN_BITMASK = 64L;

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

	public TemplateEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _templateEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTemplateEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _templateEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return TemplateEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TemplateEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<TemplateEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<TemplateEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TemplateEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((TemplateEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<TemplateEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<TemplateEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(TemplateEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<TemplateEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<TemplateEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, TemplateEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			TemplateEntry.class.getClassLoader(), TemplateEntry.class,
			ModelWrapper.class);

		try {
			Constructor<TemplateEntry> constructor =
				(Constructor<TemplateEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<TemplateEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<TemplateEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<TemplateEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<TemplateEntry, Object>>();
		Map<String, BiConsumer<TemplateEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<TemplateEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", TemplateEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", TemplateEntry::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setCtCollectionId);
		attributeGetterFunctions.put("uuid", TemplateEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<TemplateEntry, String>)TemplateEntry::setUuid);
		attributeGetterFunctions.put(
			"templateEntryId", TemplateEntry::getTemplateEntryId);
		attributeSetterBiConsumers.put(
			"templateEntryId",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setTemplateEntryId);
		attributeGetterFunctions.put("groupId", TemplateEntry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setGroupId);
		attributeGetterFunctions.put("companyId", TemplateEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setCompanyId);
		attributeGetterFunctions.put("userId", TemplateEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setUserId);
		attributeGetterFunctions.put("userName", TemplateEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<TemplateEntry, String>)TemplateEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", TemplateEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<TemplateEntry, Date>)TemplateEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", TemplateEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<TemplateEntry, Date>)TemplateEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"ddmTemplateId", TemplateEntry::getDDMTemplateId);
		attributeSetterBiConsumers.put(
			"ddmTemplateId",
			(BiConsumer<TemplateEntry, Long>)TemplateEntry::setDDMTemplateId);
		attributeGetterFunctions.put(
			"infoItemClassName", TemplateEntry::getInfoItemClassName);
		attributeSetterBiConsumers.put(
			"infoItemClassName",
			(BiConsumer<TemplateEntry, String>)
				TemplateEntry::setInfoItemClassName);
		attributeGetterFunctions.put(
			"infoItemFormVariationKey",
			TemplateEntry::getInfoItemFormVariationKey);
		attributeSetterBiConsumers.put(
			"infoItemFormVariationKey",
			(BiConsumer<TemplateEntry, String>)
				TemplateEntry::setInfoItemFormVariationKey);
		attributeGetterFunctions.put(
			"lastPublishDate", TemplateEntry::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<TemplateEntry, Date>)TemplateEntry::setLastPublishDate);

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

	@Override
	public long getTemplateEntryId() {
		return _templateEntryId;
	}

	@Override
	public void setTemplateEntryId(long templateEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_templateEntryId = templateEntryId;
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
	public long getDDMTemplateId() {
		return _ddmTemplateId;
	}

	@Override
	public void setDDMTemplateId(long ddmTemplateId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ddmTemplateId = ddmTemplateId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalDDMTemplateId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("ddmTemplateId"));
	}

	@Override
	public String getInfoItemClassName() {
		if (_infoItemClassName == null) {
			return "";
		}
		else {
			return _infoItemClassName;
		}
	}

	@Override
	public void setInfoItemClassName(String infoItemClassName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_infoItemClassName = infoItemClassName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalInfoItemClassName() {
		return getColumnOriginalValue("infoItemClassName");
	}

	@Override
	public String getInfoItemFormVariationKey() {
		if (_infoItemFormVariationKey == null) {
			return "";
		}
		else {
			return _infoItemFormVariationKey;
		}
	}

	@Override
	public void setInfoItemFormVariationKey(String infoItemFormVariationKey) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_infoItemFormVariationKey = infoItemFormVariationKey;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalInfoItemFormVariationKey() {
		return getColumnOriginalValue("infoItemFormVariationKey");
	}

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
			PortalUtil.getClassNameId(TemplateEntry.class.getName()));
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
			getCompanyId(), TemplateEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public TemplateEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, TemplateEntry>
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
		TemplateEntryImpl templateEntryImpl = new TemplateEntryImpl();

		templateEntryImpl.setMvccVersion(getMvccVersion());
		templateEntryImpl.setCtCollectionId(getCtCollectionId());
		templateEntryImpl.setUuid(getUuid());
		templateEntryImpl.setTemplateEntryId(getTemplateEntryId());
		templateEntryImpl.setGroupId(getGroupId());
		templateEntryImpl.setCompanyId(getCompanyId());
		templateEntryImpl.setUserId(getUserId());
		templateEntryImpl.setUserName(getUserName());
		templateEntryImpl.setCreateDate(getCreateDate());
		templateEntryImpl.setModifiedDate(getModifiedDate());
		templateEntryImpl.setDDMTemplateId(getDDMTemplateId());
		templateEntryImpl.setInfoItemClassName(getInfoItemClassName());
		templateEntryImpl.setInfoItemFormVariationKey(
			getInfoItemFormVariationKey());
		templateEntryImpl.setLastPublishDate(getLastPublishDate());

		templateEntryImpl.resetOriginalValues();

		return templateEntryImpl;
	}

	@Override
	public TemplateEntry cloneWithOriginalValues() {
		TemplateEntryImpl templateEntryImpl = new TemplateEntryImpl();

		templateEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		templateEntryImpl.setCtCollectionId(
			this.<Long>getColumnOriginalValue("ctCollectionId"));
		templateEntryImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		templateEntryImpl.setTemplateEntryId(
			this.<Long>getColumnOriginalValue("templateEntryId"));
		templateEntryImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		templateEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		templateEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		templateEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		templateEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		templateEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		templateEntryImpl.setDDMTemplateId(
			this.<Long>getColumnOriginalValue("ddmTemplateId"));
		templateEntryImpl.setInfoItemClassName(
			this.<String>getColumnOriginalValue("infoItemClassName"));
		templateEntryImpl.setInfoItemFormVariationKey(
			this.<String>getColumnOriginalValue("infoItemFormVariationKey"));
		templateEntryImpl.setLastPublishDate(
			this.<Date>getColumnOriginalValue("lastPublishDate"));

		return templateEntryImpl;
	}

	@Override
	public int compareTo(TemplateEntry templateEntry) {
		long primaryKey = templateEntry.getPrimaryKey();

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

		if (!(object instanceof TemplateEntry)) {
			return false;
		}

		TemplateEntry templateEntry = (TemplateEntry)object;

		long primaryKey = templateEntry.getPrimaryKey();

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
	public CacheModel<TemplateEntry> toCacheModel() {
		TemplateEntryCacheModel templateEntryCacheModel =
			new TemplateEntryCacheModel();

		templateEntryCacheModel.mvccVersion = getMvccVersion();

		templateEntryCacheModel.ctCollectionId = getCtCollectionId();

		templateEntryCacheModel.uuid = getUuid();

		String uuid = templateEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			templateEntryCacheModel.uuid = null;
		}

		templateEntryCacheModel.templateEntryId = getTemplateEntryId();

		templateEntryCacheModel.groupId = getGroupId();

		templateEntryCacheModel.companyId = getCompanyId();

		templateEntryCacheModel.userId = getUserId();

		templateEntryCacheModel.userName = getUserName();

		String userName = templateEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			templateEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			templateEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			templateEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			templateEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			templateEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		templateEntryCacheModel.ddmTemplateId = getDDMTemplateId();

		templateEntryCacheModel.infoItemClassName = getInfoItemClassName();

		String infoItemClassName = templateEntryCacheModel.infoItemClassName;

		if ((infoItemClassName != null) && (infoItemClassName.length() == 0)) {
			templateEntryCacheModel.infoItemClassName = null;
		}

		templateEntryCacheModel.infoItemFormVariationKey =
			getInfoItemFormVariationKey();

		String infoItemFormVariationKey =
			templateEntryCacheModel.infoItemFormVariationKey;

		if ((infoItemFormVariationKey != null) &&
			(infoItemFormVariationKey.length() == 0)) {

			templateEntryCacheModel.infoItemFormVariationKey = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			templateEntryCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			templateEntryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return templateEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<TemplateEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<TemplateEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TemplateEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((TemplateEntry)this);

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
		Map<String, Function<TemplateEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<TemplateEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TemplateEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((TemplateEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, TemplateEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _templateEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _ddmTemplateId;
	private String _infoItemClassName;
	private String _infoItemFormVariationKey;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<TemplateEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((TemplateEntry)this);
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
		_columnOriginalValues.put("templateEntryId", _templateEntryId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("ddmTemplateId", _ddmTemplateId);
		_columnOriginalValues.put("infoItemClassName", _infoItemClassName);
		_columnOriginalValues.put(
			"infoItemFormVariationKey", _infoItemFormVariationKey);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

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

		columnBitmasks.put("templateEntryId", 8L);

		columnBitmasks.put("groupId", 16L);

		columnBitmasks.put("companyId", 32L);

		columnBitmasks.put("userId", 64L);

		columnBitmasks.put("userName", 128L);

		columnBitmasks.put("createDate", 256L);

		columnBitmasks.put("modifiedDate", 512L);

		columnBitmasks.put("ddmTemplateId", 1024L);

		columnBitmasks.put("infoItemClassName", 2048L);

		columnBitmasks.put("infoItemFormVariationKey", 4096L);

		columnBitmasks.put("lastPublishDate", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private TemplateEntry _escapedModel;

}