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

package com.liferay.mail.reader.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.mail.reader.model.Attachment;
import com.liferay.mail.reader.model.AttachmentModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Attachment service. Represents a row in the &quot;Mail_Attachment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AttachmentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AttachmentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentImpl
 * @generated
 */
public class AttachmentModelImpl
	extends BaseModelImpl<Attachment> implements AttachmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a attachment model instance should use the <code>Attachment</code> interface instead.
	 */
	public static final String TABLE_NAME = "Mail_Attachment";

	public static final Object[][] TABLE_COLUMNS = {
		{"attachmentId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"accountId", Types.BIGINT},
		{"folderId", Types.BIGINT}, {"messageId", Types.BIGINT},
		{"contentPath", Types.VARCHAR}, {"fileName", Types.VARCHAR},
		{"size_", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("attachmentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("folderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("messageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contentPath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("size_", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Mail_Attachment (attachmentId LONG not null primary key,companyId LONG,userId LONG,accountId LONG,folderId LONG,messageId LONG,contentPath VARCHAR(75) null,fileName VARCHAR(75) null,size_ LONG)";

	public static final String TABLE_SQL_DROP = "drop table Mail_Attachment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY attachment.attachmentId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Mail_Attachment.attachmentId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long MESSAGEID_COLUMN_BITMASK = 1L;

	public static final long ATTACHMENTID_COLUMN_BITMASK = 2L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public AttachmentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _attachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _attachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Attachment.class;
	}

	@Override
	public String getModelClassName() {
		return Attachment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Attachment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Attachment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Attachment, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Attachment)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Attachment, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Attachment, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Attachment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Attachment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Attachment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Attachment>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Attachment.class.getClassLoader(), Attachment.class,
			ModelWrapper.class);

		try {
			Constructor<Attachment> constructor =
				(Constructor<Attachment>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Attachment, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Attachment, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Attachment, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Attachment, Object>>();
		Map<String, BiConsumer<Attachment, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Attachment, ?>>();

		attributeGetterFunctions.put(
			"attachmentId", Attachment::getAttachmentId);
		attributeSetterBiConsumers.put(
			"attachmentId",
			(BiConsumer<Attachment, Long>)Attachment::setAttachmentId);
		attributeGetterFunctions.put("companyId", Attachment::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<Attachment, Long>)Attachment::setCompanyId);
		attributeGetterFunctions.put("userId", Attachment::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Attachment, Long>)Attachment::setUserId);
		attributeGetterFunctions.put("accountId", Attachment::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId",
			(BiConsumer<Attachment, Long>)Attachment::setAccountId);
		attributeGetterFunctions.put("folderId", Attachment::getFolderId);
		attributeSetterBiConsumers.put(
			"folderId", (BiConsumer<Attachment, Long>)Attachment::setFolderId);
		attributeGetterFunctions.put("messageId", Attachment::getMessageId);
		attributeSetterBiConsumers.put(
			"messageId",
			(BiConsumer<Attachment, Long>)Attachment::setMessageId);
		attributeGetterFunctions.put("contentPath", Attachment::getContentPath);
		attributeSetterBiConsumers.put(
			"contentPath",
			(BiConsumer<Attachment, String>)Attachment::setContentPath);
		attributeGetterFunctions.put("fileName", Attachment::getFileName);
		attributeSetterBiConsumers.put(
			"fileName",
			(BiConsumer<Attachment, String>)Attachment::setFileName);
		attributeGetterFunctions.put("size", Attachment::getSize);
		attributeSetterBiConsumers.put(
			"size", (BiConsumer<Attachment, Long>)Attachment::setSize);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getAttachmentId() {
		return _attachmentId;
	}

	@Override
	public void setAttachmentId(long attachmentId) {
		_attachmentId = attachmentId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	@Override
	public long getMessageId() {
		return _messageId;
	}

	@Override
	public void setMessageId(long messageId) {
		_columnBitmask |= MESSAGEID_COLUMN_BITMASK;

		if (!_setOriginalMessageId) {
			_setOriginalMessageId = true;

			_originalMessageId = _messageId;
		}

		_messageId = messageId;
	}

	public long getOriginalMessageId() {
		return _originalMessageId;
	}

	@Override
	public String getContentPath() {
		if (_contentPath == null) {
			return "";
		}
		else {
			return _contentPath;
		}
	}

	@Override
	public void setContentPath(String contentPath) {
		_contentPath = contentPath;
	}

	@Override
	public String getFileName() {
		if (_fileName == null) {
			return "";
		}
		else {
			return _fileName;
		}
	}

	@Override
	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		_size = size;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Attachment.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Attachment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Attachment>
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
		AttachmentImpl attachmentImpl = new AttachmentImpl();

		attachmentImpl.setAttachmentId(getAttachmentId());
		attachmentImpl.setCompanyId(getCompanyId());
		attachmentImpl.setUserId(getUserId());
		attachmentImpl.setAccountId(getAccountId());
		attachmentImpl.setFolderId(getFolderId());
		attachmentImpl.setMessageId(getMessageId());
		attachmentImpl.setContentPath(getContentPath());
		attachmentImpl.setFileName(getFileName());
		attachmentImpl.setSize(getSize());

		attachmentImpl.resetOriginalValues();

		return attachmentImpl;
	}

	@Override
	public int compareTo(Attachment attachment) {
		long primaryKey = attachment.getPrimaryKey();

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

		if (!(object instanceof Attachment)) {
			return false;
		}

		Attachment attachment = (Attachment)object;

		long primaryKey = attachment.getPrimaryKey();

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
		_originalMessageId = _messageId;

		_setOriginalMessageId = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Attachment> toCacheModel() {
		AttachmentCacheModel attachmentCacheModel = new AttachmentCacheModel();

		attachmentCacheModel.attachmentId = getAttachmentId();

		attachmentCacheModel.companyId = getCompanyId();

		attachmentCacheModel.userId = getUserId();

		attachmentCacheModel.accountId = getAccountId();

		attachmentCacheModel.folderId = getFolderId();

		attachmentCacheModel.messageId = getMessageId();

		attachmentCacheModel.contentPath = getContentPath();

		String contentPath = attachmentCacheModel.contentPath;

		if ((contentPath != null) && (contentPath.length() == 0)) {
			attachmentCacheModel.contentPath = null;
		}

		attachmentCacheModel.fileName = getFileName();

		String fileName = attachmentCacheModel.fileName;

		if ((fileName != null) && (fileName.length() == 0)) {
			attachmentCacheModel.fileName = null;
		}

		attachmentCacheModel.size = getSize();

		return attachmentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Attachment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Attachment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Attachment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Attachment)this));
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
		Map<String, Function<Attachment, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Attachment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Attachment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Attachment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Attachment>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _attachmentId;
	private long _companyId;
	private long _userId;
	private long _accountId;
	private long _folderId;
	private long _messageId;
	private long _originalMessageId;
	private boolean _setOriginalMessageId;
	private String _contentPath;
	private String _fileName;
	private long _size;
	private long _columnBitmask;
	private Attachment _escapedModel;

}