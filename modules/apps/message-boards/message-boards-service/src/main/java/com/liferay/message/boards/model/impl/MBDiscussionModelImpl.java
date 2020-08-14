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

package com.liferay.message.boards.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.message.boards.model.MBDiscussionModel;
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
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the MBDiscussion service. Represents a row in the &quot;MBDiscussion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MBDiscussionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MBDiscussionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussionImpl
 * @generated
 */
public class MBDiscussionModelImpl
	extends BaseModelImpl<MBDiscussion> implements MBDiscussionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message boards discussion model instance should use the <code>MBDiscussion</code> interface instead.
	 */
	public static final String TABLE_NAME = "MBDiscussion";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"discussionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"threadId", Types.BIGINT}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("discussionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("threadId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MBDiscussion (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,discussionId LONG not null,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,threadId LONG,lastPublishDate DATE null,primary key (discussionId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table MBDiscussion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY mbDiscussion.discussionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MBDiscussion.discussionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long THREADID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long DISCUSSIONID_COLUMN_BITMASK = 64L;

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

	public MBDiscussionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _discussionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDiscussionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _discussionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MBDiscussion.class;
	}

	@Override
	public String getModelClassName() {
		return MBDiscussion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MBDiscussion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MBDiscussion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MBDiscussion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MBDiscussion)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MBDiscussion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MBDiscussion, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MBDiscussion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MBDiscussion, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MBDiscussion, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MBDiscussion>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MBDiscussion.class.getClassLoader(), MBDiscussion.class,
			ModelWrapper.class);

		try {
			Constructor<MBDiscussion> constructor =
				(Constructor<MBDiscussion>)proxyClass.getConstructor(
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

	private static final Map<String, Function<MBDiscussion, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MBDiscussion, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MBDiscussion, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<MBDiscussion, Object>>();
		Map<String, BiConsumer<MBDiscussion, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<MBDiscussion, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", MBDiscussion::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", MBDiscussion::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setCtCollectionId);
		attributeGetterFunctions.put("uuid", MBDiscussion::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<MBDiscussion, String>)MBDiscussion::setUuid);
		attributeGetterFunctions.put(
			"discussionId", MBDiscussion::getDiscussionId);
		attributeSetterBiConsumers.put(
			"discussionId",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setDiscussionId);
		attributeGetterFunctions.put("groupId", MBDiscussion::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setGroupId);
		attributeGetterFunctions.put("companyId", MBDiscussion::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setCompanyId);
		attributeGetterFunctions.put("userId", MBDiscussion::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<MBDiscussion, Long>)MBDiscussion::setUserId);
		attributeGetterFunctions.put("userName", MBDiscussion::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<MBDiscussion, String>)MBDiscussion::setUserName);
		attributeGetterFunctions.put("createDate", MBDiscussion::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MBDiscussion, Date>)MBDiscussion::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", MBDiscussion::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<MBDiscussion, Date>)MBDiscussion::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", MBDiscussion::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setClassNameId);
		attributeGetterFunctions.put("classPK", MBDiscussion::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setClassPK);
		attributeGetterFunctions.put("threadId", MBDiscussion::getThreadId);
		attributeSetterBiConsumers.put(
			"threadId",
			(BiConsumer<MBDiscussion, Long>)MBDiscussion::setThreadId);
		attributeGetterFunctions.put(
			"lastPublishDate", MBDiscussion::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<MBDiscussion, Date>)MBDiscussion::setLastPublishDate);

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
	public long getDiscussionId() {
		return _discussionId;
	}

	@Override
	public void setDiscussionId(long discussionId) {
		_discussionId = discussionId;
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
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public long getThreadId() {
		return _threadId;
	}

	@Override
	public void setThreadId(long threadId) {
		_columnBitmask |= THREADID_COLUMN_BITMASK;

		if (!_setOriginalThreadId) {
			_setOriginalThreadId = true;

			_originalThreadId = _threadId;
		}

		_threadId = threadId;
	}

	public long getOriginalThreadId() {
		return _originalThreadId;
	}

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
			PortalUtil.getClassNameId(MBDiscussion.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), MBDiscussion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MBDiscussion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MBDiscussion>
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
		MBDiscussionImpl mbDiscussionImpl = new MBDiscussionImpl();

		mbDiscussionImpl.setMvccVersion(getMvccVersion());
		mbDiscussionImpl.setCtCollectionId(getCtCollectionId());
		mbDiscussionImpl.setUuid(getUuid());
		mbDiscussionImpl.setDiscussionId(getDiscussionId());
		mbDiscussionImpl.setGroupId(getGroupId());
		mbDiscussionImpl.setCompanyId(getCompanyId());
		mbDiscussionImpl.setUserId(getUserId());
		mbDiscussionImpl.setUserName(getUserName());
		mbDiscussionImpl.setCreateDate(getCreateDate());
		mbDiscussionImpl.setModifiedDate(getModifiedDate());
		mbDiscussionImpl.setClassNameId(getClassNameId());
		mbDiscussionImpl.setClassPK(getClassPK());
		mbDiscussionImpl.setThreadId(getThreadId());
		mbDiscussionImpl.setLastPublishDate(getLastPublishDate());

		mbDiscussionImpl.resetOriginalValues();

		return mbDiscussionImpl;
	}

	@Override
	public int compareTo(MBDiscussion mbDiscussion) {
		long primaryKey = mbDiscussion.getPrimaryKey();

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

		if (!(object instanceof MBDiscussion)) {
			return false;
		}

		MBDiscussion mbDiscussion = (MBDiscussion)object;

		long primaryKey = mbDiscussion.getPrimaryKey();

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
		MBDiscussionModelImpl mbDiscussionModelImpl = this;

		mbDiscussionModelImpl._originalUuid = mbDiscussionModelImpl._uuid;

		mbDiscussionModelImpl._originalGroupId = mbDiscussionModelImpl._groupId;

		mbDiscussionModelImpl._setOriginalGroupId = false;

		mbDiscussionModelImpl._originalCompanyId =
			mbDiscussionModelImpl._companyId;

		mbDiscussionModelImpl._setOriginalCompanyId = false;

		mbDiscussionModelImpl._setModifiedDate = false;

		mbDiscussionModelImpl._originalClassNameId =
			mbDiscussionModelImpl._classNameId;

		mbDiscussionModelImpl._setOriginalClassNameId = false;

		mbDiscussionModelImpl._originalClassPK = mbDiscussionModelImpl._classPK;

		mbDiscussionModelImpl._setOriginalClassPK = false;

		mbDiscussionModelImpl._originalThreadId =
			mbDiscussionModelImpl._threadId;

		mbDiscussionModelImpl._setOriginalThreadId = false;

		mbDiscussionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MBDiscussion> toCacheModel() {
		MBDiscussionCacheModel mbDiscussionCacheModel =
			new MBDiscussionCacheModel();

		mbDiscussionCacheModel.mvccVersion = getMvccVersion();

		mbDiscussionCacheModel.ctCollectionId = getCtCollectionId();

		mbDiscussionCacheModel.uuid = getUuid();

		String uuid = mbDiscussionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mbDiscussionCacheModel.uuid = null;
		}

		mbDiscussionCacheModel.discussionId = getDiscussionId();

		mbDiscussionCacheModel.groupId = getGroupId();

		mbDiscussionCacheModel.companyId = getCompanyId();

		mbDiscussionCacheModel.userId = getUserId();

		mbDiscussionCacheModel.userName = getUserName();

		String userName = mbDiscussionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mbDiscussionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mbDiscussionCacheModel.createDate = createDate.getTime();
		}
		else {
			mbDiscussionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mbDiscussionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			mbDiscussionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mbDiscussionCacheModel.classNameId = getClassNameId();

		mbDiscussionCacheModel.classPK = getClassPK();

		mbDiscussionCacheModel.threadId = getThreadId();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mbDiscussionCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			mbDiscussionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return mbDiscussionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MBDiscussion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MBDiscussion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MBDiscussion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((MBDiscussion)this));
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
		Map<String, Function<MBDiscussion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MBDiscussion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MBDiscussion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((MBDiscussion)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MBDiscussion>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private String _originalUuid;
	private long _discussionId;
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
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _threadId;
	private long _originalThreadId;
	private boolean _setOriginalThreadId;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private MBDiscussion _escapedModel;

}