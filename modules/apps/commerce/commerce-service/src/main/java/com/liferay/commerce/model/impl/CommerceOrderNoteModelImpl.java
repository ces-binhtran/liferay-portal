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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceOrderNoteModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
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
 * The base model implementation for the CommerceOrderNote service. Represents a row in the &quot;CommerceOrderNote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceOrderNoteModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceOrderNoteImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderNoteImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceOrderNoteModelImpl
	extends BaseModelImpl<CommerceOrderNote> implements CommerceOrderNoteModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce order note model instance should use the <code>CommerceOrderNote</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceOrderNote";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"externalReferenceCode", Types.VARCHAR},
		{"commerceOrderNoteId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"commerceOrderId", Types.BIGINT},
		{"content", Types.VARCHAR}, {"restricted", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceOrderNoteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceOrderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("restricted", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceOrderNote (mvccVersion LONG default 0 not null,externalReferenceCode VARCHAR(75) null,commerceOrderNoteId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceOrderId LONG,content STRING null,restricted BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CommerceOrderNote";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceOrderNote.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceOrderNote.createDate DESC";

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
	public static final long COMMERCEORDERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RESTRICTED_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.model.CommerceOrderNote"));

	public CommerceOrderNoteModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceOrderNoteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceOrderNote.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderNote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceOrderNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderNote, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceOrderNote)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceOrderNote, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceOrderNote, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceOrderNote)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceOrderNote, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceOrderNote, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceOrderNote>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceOrderNote.class.getClassLoader(), CommerceOrderNote.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceOrderNote> constructor =
				(Constructor<CommerceOrderNote>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceOrderNote, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceOrderNote, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceOrderNote, Object>>();
		Map<String, BiConsumer<CommerceOrderNote, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CommerceOrderNote, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommerceOrderNote::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setMvccVersion);
		attributeGetterFunctions.put(
			"externalReferenceCode",
			CommerceOrderNote::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CommerceOrderNote, String>)
				CommerceOrderNote::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"commerceOrderNoteId", CommerceOrderNote::getCommerceOrderNoteId);
		attributeSetterBiConsumers.put(
			"commerceOrderNoteId",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setCommerceOrderNoteId);
		attributeGetterFunctions.put("groupId", CommerceOrderNote::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CommerceOrderNote, Long>)CommerceOrderNote::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CommerceOrderNote::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setCompanyId);
		attributeGetterFunctions.put("userId", CommerceOrderNote::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceOrderNote, Long>)CommerceOrderNote::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceOrderNote::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceOrderNote, String>)
				CommerceOrderNote::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceOrderNote::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceOrderNote, Date>)
				CommerceOrderNote::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceOrderNote::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceOrderNote, Date>)
				CommerceOrderNote::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceOrderId", CommerceOrderNote::getCommerceOrderId);
		attributeSetterBiConsumers.put(
			"commerceOrderId",
			(BiConsumer<CommerceOrderNote, Long>)
				CommerceOrderNote::setCommerceOrderId);
		attributeGetterFunctions.put("content", CommerceOrderNote::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<CommerceOrderNote, String>)
				CommerceOrderNote::setContent);
		attributeGetterFunctions.put(
			"restricted", CommerceOrderNote::getRestricted);
		attributeSetterBiConsumers.put(
			"restricted",
			(BiConsumer<CommerceOrderNote, Boolean>)
				CommerceOrderNote::setRestricted);

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
	public long getCommerceOrderNoteId() {
		return _commerceOrderNoteId;
	}

	@Override
	public void setCommerceOrderNoteId(long commerceOrderNoteId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderNoteId = commerceOrderNoteId;
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
	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	@Override
	public void setCommerceOrderId(long commerceOrderId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceOrderId = commerceOrderId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceOrderId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceOrderId"));
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
	}

	@JSON
	@Override
	public boolean getRestricted() {
		return _restricted;
	}

	@JSON
	@Override
	public boolean isRestricted() {
		return _restricted;
	}

	@Override
	public void setRestricted(boolean restricted) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_restricted = restricted;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalRestricted() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("restricted"));
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
			getCompanyId(), CommerceOrderNote.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceOrderNote toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceOrderNote>
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
		CommerceOrderNoteImpl commerceOrderNoteImpl =
			new CommerceOrderNoteImpl();

		commerceOrderNoteImpl.setMvccVersion(getMvccVersion());
		commerceOrderNoteImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceOrderNoteImpl.setCommerceOrderNoteId(getCommerceOrderNoteId());
		commerceOrderNoteImpl.setGroupId(getGroupId());
		commerceOrderNoteImpl.setCompanyId(getCompanyId());
		commerceOrderNoteImpl.setUserId(getUserId());
		commerceOrderNoteImpl.setUserName(getUserName());
		commerceOrderNoteImpl.setCreateDate(getCreateDate());
		commerceOrderNoteImpl.setModifiedDate(getModifiedDate());
		commerceOrderNoteImpl.setCommerceOrderId(getCommerceOrderId());
		commerceOrderNoteImpl.setContent(getContent());
		commerceOrderNoteImpl.setRestricted(isRestricted());

		commerceOrderNoteImpl.resetOriginalValues();

		return commerceOrderNoteImpl;
	}

	@Override
	public CommerceOrderNote cloneWithOriginalValues() {
		CommerceOrderNoteImpl commerceOrderNoteImpl =
			new CommerceOrderNoteImpl();

		commerceOrderNoteImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceOrderNoteImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		commerceOrderNoteImpl.setCommerceOrderNoteId(
			this.<Long>getColumnOriginalValue("commerceOrderNoteId"));
		commerceOrderNoteImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		commerceOrderNoteImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceOrderNoteImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceOrderNoteImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceOrderNoteImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceOrderNoteImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceOrderNoteImpl.setCommerceOrderId(
			this.<Long>getColumnOriginalValue("commerceOrderId"));
		commerceOrderNoteImpl.setContent(
			this.<String>getColumnOriginalValue("content"));
		commerceOrderNoteImpl.setRestricted(
			this.<Boolean>getColumnOriginalValue("restricted"));

		return commerceOrderNoteImpl;
	}

	@Override
	public int compareTo(CommerceOrderNote commerceOrderNote) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceOrderNote.getCreateDate());

		value = value * -1;

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

		if (!(object instanceof CommerceOrderNote)) {
			return false;
		}

		CommerceOrderNote commerceOrderNote = (CommerceOrderNote)object;

		long primaryKey = commerceOrderNote.getPrimaryKey();

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
	public CacheModel<CommerceOrderNote> toCacheModel() {
		CommerceOrderNoteCacheModel commerceOrderNoteCacheModel =
			new CommerceOrderNoteCacheModel();

		commerceOrderNoteCacheModel.mvccVersion = getMvccVersion();

		commerceOrderNoteCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceOrderNoteCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceOrderNoteCacheModel.externalReferenceCode = null;
		}

		commerceOrderNoteCacheModel.commerceOrderNoteId =
			getCommerceOrderNoteId();

		commerceOrderNoteCacheModel.groupId = getGroupId();

		commerceOrderNoteCacheModel.companyId = getCompanyId();

		commerceOrderNoteCacheModel.userId = getUserId();

		commerceOrderNoteCacheModel.userName = getUserName();

		String userName = commerceOrderNoteCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceOrderNoteCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceOrderNoteCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceOrderNoteCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceOrderNoteCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceOrderNoteCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceOrderNoteCacheModel.commerceOrderId = getCommerceOrderId();

		commerceOrderNoteCacheModel.content = getContent();

		String content = commerceOrderNoteCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			commerceOrderNoteCacheModel.content = null;
		}

		commerceOrderNoteCacheModel.restricted = isRestricted();

		return commerceOrderNoteCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceOrderNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(CommerceOrderNote)this);

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
		Map<String, Function<CommerceOrderNote, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceOrderNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceOrderNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceOrderNote)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceOrderNote>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _externalReferenceCode;
	private long _commerceOrderNoteId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceOrderId;
	private String _content;
	private boolean _restricted;

	public <T> T getColumnValue(String columnName) {
		Function<CommerceOrderNote, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceOrderNote)this);
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
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("commerceOrderNoteId", _commerceOrderNoteId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceOrderId", _commerceOrderId);
		_columnOriginalValues.put("content", _content);
		_columnOriginalValues.put("restricted", _restricted);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("externalReferenceCode", 2L);

		columnBitmasks.put("commerceOrderNoteId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("userId", 32L);

		columnBitmasks.put("userName", 64L);

		columnBitmasks.put("createDate", 128L);

		columnBitmasks.put("modifiedDate", 256L);

		columnBitmasks.put("commerceOrderId", 512L);

		columnBitmasks.put("content", 1024L);

		columnBitmasks.put("restricted", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceOrderNote _escapedModel;

}