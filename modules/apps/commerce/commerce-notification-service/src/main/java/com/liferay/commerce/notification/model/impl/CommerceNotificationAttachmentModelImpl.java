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

package com.liferay.commerce.notification.model.impl;

import com.liferay.commerce.notification.model.CommerceNotificationAttachment;
import com.liferay.commerce.notification.model.CommerceNotificationAttachmentModel;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceNotificationAttachment service. Represents a row in the &quot;CNotificationAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceNotificationAttachmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceNotificationAttachmentImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceNotificationAttachmentImpl
 * @generated
 */
public class CommerceNotificationAttachmentModelImpl
	extends BaseModelImpl<CommerceNotificationAttachment>
	implements CommerceNotificationAttachmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce notification attachment model instance should use the <code>CommerceNotificationAttachment</code> interface instead.
	 */
	public static final String TABLE_NAME = "CNotificationAttachment";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"CNotificationAttachmentId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CNotificationQueueEntryId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT}, {"deleteOnSend", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CNotificationAttachmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CNotificationQueueEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("deleteOnSend", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CNotificationAttachment (uuid_ VARCHAR(75) null,CNotificationAttachmentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CNotificationQueueEntryId LONG,fileEntryId LONG,deleteOnSend BOOLEAN)";

	public static final String TABLE_SQL_DROP =
		"drop table CNotificationAttachment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceNotificationAttachment.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CNotificationAttachment.createDate DESC";

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
	public static final long COMMERCENOTIFICATIONQUEUEENTRYID_COLUMN_BITMASK =
		1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.notification.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.notification.model.CommerceNotificationAttachment"));

	public CommerceNotificationAttachmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceNotificationAttachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceNotificationAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceNotificationAttachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceNotificationAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceNotificationAttachment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceNotificationAttachment, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceNotificationAttachment, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceNotificationAttachment)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceNotificationAttachment, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceNotificationAttachment, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceNotificationAttachment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceNotificationAttachment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceNotificationAttachment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceNotificationAttachment>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceNotificationAttachment.class.getClassLoader(),
			CommerceNotificationAttachment.class, ModelWrapper.class);

		try {
			Constructor<CommerceNotificationAttachment> constructor =
				(Constructor<CommerceNotificationAttachment>)
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
		<String, Function<CommerceNotificationAttachment, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceNotificationAttachment, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceNotificationAttachment, Object>>();
		Map<String, BiConsumer<CommerceNotificationAttachment, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceNotificationAttachment, ?>>();

		attributeGetterFunctions.put(
			"uuid", CommerceNotificationAttachment::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<CommerceNotificationAttachment, String>)
				CommerceNotificationAttachment::setUuid);
		attributeGetterFunctions.put(
			"commerceNotificationAttachmentId",
			CommerceNotificationAttachment::
				getCommerceNotificationAttachmentId);
		attributeSetterBiConsumers.put(
			"commerceNotificationAttachmentId",
			(BiConsumer<CommerceNotificationAttachment, Long>)
				CommerceNotificationAttachment::
					setCommerceNotificationAttachmentId);
		attributeGetterFunctions.put(
			"groupId", CommerceNotificationAttachment::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<CommerceNotificationAttachment, Long>)
				CommerceNotificationAttachment::setGroupId);
		attributeGetterFunctions.put(
			"companyId", CommerceNotificationAttachment::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceNotificationAttachment, Long>)
				CommerceNotificationAttachment::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceNotificationAttachment::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceNotificationAttachment, Long>)
				CommerceNotificationAttachment::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceNotificationAttachment::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceNotificationAttachment, String>)
				CommerceNotificationAttachment::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceNotificationAttachment::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceNotificationAttachment, Date>)
				CommerceNotificationAttachment::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceNotificationAttachment::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceNotificationAttachment, Date>)
				CommerceNotificationAttachment::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceNotificationQueueEntryId",
			CommerceNotificationAttachment::
				getCommerceNotificationQueueEntryId);
		attributeSetterBiConsumers.put(
			"commerceNotificationQueueEntryId",
			(BiConsumer<CommerceNotificationAttachment, Long>)
				CommerceNotificationAttachment::
					setCommerceNotificationQueueEntryId);
		attributeGetterFunctions.put(
			"fileEntryId", CommerceNotificationAttachment::getFileEntryId);
		attributeSetterBiConsumers.put(
			"fileEntryId",
			(BiConsumer<CommerceNotificationAttachment, Long>)
				CommerceNotificationAttachment::setFileEntryId);
		attributeGetterFunctions.put(
			"deleteOnSend", CommerceNotificationAttachment::getDeleteOnSend);
		attributeSetterBiConsumers.put(
			"deleteOnSend",
			(BiConsumer<CommerceNotificationAttachment, Boolean>)
				CommerceNotificationAttachment::setDeleteOnSend);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getCommerceNotificationAttachmentId() {
		return _commerceNotificationAttachmentId;
	}

	@Override
	public void setCommerceNotificationAttachmentId(
		long commerceNotificationAttachmentId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceNotificationAttachmentId = commerceNotificationAttachmentId;
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
	public long getCommerceNotificationQueueEntryId() {
		return _commerceNotificationQueueEntryId;
	}

	@Override
	public void setCommerceNotificationQueueEntryId(
		long commerceNotificationQueueEntryId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceNotificationQueueEntryId = commerceNotificationQueueEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceNotificationQueueEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("CNotificationQueueEntryId"));
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileEntryId = fileEntryId;
	}

	@Override
	public boolean getDeleteOnSend() {
		return _deleteOnSend;
	}

	@Override
	public boolean isDeleteOnSend() {
		return _deleteOnSend;
	}

	@Override
	public void setDeleteOnSend(boolean deleteOnSend) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_deleteOnSend = deleteOnSend;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(
				CommerceNotificationAttachment.class.getName()));
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

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceNotificationAttachment.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceNotificationAttachment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceNotificationAttachment>
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
		CommerceNotificationAttachmentImpl commerceNotificationAttachmentImpl =
			new CommerceNotificationAttachmentImpl();

		commerceNotificationAttachmentImpl.setUuid(getUuid());
		commerceNotificationAttachmentImpl.setCommerceNotificationAttachmentId(
			getCommerceNotificationAttachmentId());
		commerceNotificationAttachmentImpl.setGroupId(getGroupId());
		commerceNotificationAttachmentImpl.setCompanyId(getCompanyId());
		commerceNotificationAttachmentImpl.setUserId(getUserId());
		commerceNotificationAttachmentImpl.setUserName(getUserName());
		commerceNotificationAttachmentImpl.setCreateDate(getCreateDate());
		commerceNotificationAttachmentImpl.setModifiedDate(getModifiedDate());
		commerceNotificationAttachmentImpl.setCommerceNotificationQueueEntryId(
			getCommerceNotificationQueueEntryId());
		commerceNotificationAttachmentImpl.setFileEntryId(getFileEntryId());
		commerceNotificationAttachmentImpl.setDeleteOnSend(isDeleteOnSend());

		commerceNotificationAttachmentImpl.resetOriginalValues();

		return commerceNotificationAttachmentImpl;
	}

	@Override
	public int compareTo(
		CommerceNotificationAttachment commerceNotificationAttachment) {

		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceNotificationAttachment.getCreateDate());

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

		if (!(object instanceof CommerceNotificationAttachment)) {
			return false;
		}

		CommerceNotificationAttachment commerceNotificationAttachment =
			(CommerceNotificationAttachment)object;

		long primaryKey = commerceNotificationAttachment.getPrimaryKey();

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
	public CacheModel<CommerceNotificationAttachment> toCacheModel() {
		CommerceNotificationAttachmentCacheModel
			commerceNotificationAttachmentCacheModel =
				new CommerceNotificationAttachmentCacheModel();

		commerceNotificationAttachmentCacheModel.uuid = getUuid();

		String uuid = commerceNotificationAttachmentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			commerceNotificationAttachmentCacheModel.uuid = null;
		}

		commerceNotificationAttachmentCacheModel.
			commerceNotificationAttachmentId =
				getCommerceNotificationAttachmentId();

		commerceNotificationAttachmentCacheModel.groupId = getGroupId();

		commerceNotificationAttachmentCacheModel.companyId = getCompanyId();

		commerceNotificationAttachmentCacheModel.userId = getUserId();

		commerceNotificationAttachmentCacheModel.userName = getUserName();

		String userName = commerceNotificationAttachmentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceNotificationAttachmentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceNotificationAttachmentCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceNotificationAttachmentCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceNotificationAttachmentCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceNotificationAttachmentCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceNotificationAttachmentCacheModel.
			commerceNotificationQueueEntryId =
				getCommerceNotificationQueueEntryId();

		commerceNotificationAttachmentCacheModel.fileEntryId = getFileEntryId();

		commerceNotificationAttachmentCacheModel.deleteOnSend =
			isDeleteOnSend();

		return commerceNotificationAttachmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceNotificationAttachment, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceNotificationAttachment, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceNotificationAttachment)this));
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
		Map<String, Function<CommerceNotificationAttachment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceNotificationAttachment, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceNotificationAttachment, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceNotificationAttachment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceNotificationAttachment>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private String _uuid;
	private long _commerceNotificationAttachmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceNotificationQueueEntryId;
	private long _fileEntryId;
	private boolean _deleteOnSend;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceNotificationAttachment, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceNotificationAttachment)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put(
			"CNotificationAttachmentId", _commerceNotificationAttachmentId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"CNotificationQueueEntryId", _commerceNotificationQueueEntryId);
		_columnOriginalValues.put("fileEntryId", _fileEntryId);
		_columnOriginalValues.put("deleteOnSend", _deleteOnSend);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put(
			"CNotificationAttachmentId", "commerceNotificationAttachmentId");
		attributeNames.put(
			"CNotificationQueueEntryId", "commerceNotificationQueueEntryId");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("CNotificationAttachmentId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("CNotificationQueueEntryId", 256L);

		columnBitmasks.put("fileEntryId", 512L);

		columnBitmasks.put("deleteOnSend", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceNotificationAttachment _escapedModel;

}