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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.model.MembershipRequestModel;
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
 * The base model implementation for the MembershipRequest service. Represents a row in the &quot;MembershipRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MembershipRequestModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MembershipRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestImpl
 * @generated
 */
@JSON(strict = true)
public class MembershipRequestModelImpl
	extends BaseModelImpl<MembershipRequest> implements MembershipRequestModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a membership request model instance should use the <code>MembershipRequest</code> interface instead.
	 */
	public static final String TABLE_NAME = "MembershipRequest";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"membershipRequestId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"comments", Types.VARCHAR}, {"replyComments", Types.VARCHAR},
		{"replyDate", Types.TIMESTAMP}, {"replierUserId", Types.BIGINT},
		{"statusId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("membershipRequestId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("replyComments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("replyDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("replierUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MembershipRequest (mvccVersion LONG default 0 not null,membershipRequestId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,comments STRING null,replyComments STRING null,replyDate DATE null,replierUserId LONG,statusId LONG)";

	public static final String TABLE_SQL_DROP = "drop table MembershipRequest";

	public static final String ORDER_BY_JPQL =
		" ORDER BY membershipRequest.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MembershipRequest.createDate DESC";

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
	public static final long GROUPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long STATUSID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.MembershipRequest"));

	public MembershipRequestModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _membershipRequestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMembershipRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _membershipRequestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MembershipRequest.class;
	}

	@Override
	public String getModelClassName() {
		return MembershipRequest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MembershipRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MembershipRequest, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MembershipRequest)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MembershipRequest, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MembershipRequest, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MembershipRequest)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MembershipRequest, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MembershipRequest, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MembershipRequest>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MembershipRequest.class.getClassLoader(), MembershipRequest.class,
			ModelWrapper.class);

		try {
			Constructor<MembershipRequest> constructor =
				(Constructor<MembershipRequest>)proxyClass.getConstructor(
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

	private static final Map<String, Function<MembershipRequest, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MembershipRequest, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<MembershipRequest, Object>>();
		Map<String, BiConsumer<MembershipRequest, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<MembershipRequest, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", MembershipRequest::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setMvccVersion);
		attributeGetterFunctions.put(
			"membershipRequestId", MembershipRequest::getMembershipRequestId);
		attributeSetterBiConsumers.put(
			"membershipRequestId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setMembershipRequestId);
		attributeGetterFunctions.put("groupId", MembershipRequest::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<MembershipRequest, Long>)MembershipRequest::setGroupId);
		attributeGetterFunctions.put(
			"companyId", MembershipRequest::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setCompanyId);
		attributeGetterFunctions.put("userId", MembershipRequest::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<MembershipRequest, Long>)MembershipRequest::setUserId);
		attributeGetterFunctions.put(
			"createDate", MembershipRequest::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MembershipRequest, Date>)
				MembershipRequest::setCreateDate);
		attributeGetterFunctions.put(
			"comments", MembershipRequest::getComments);
		attributeSetterBiConsumers.put(
			"comments",
			(BiConsumer<MembershipRequest, String>)
				MembershipRequest::setComments);
		attributeGetterFunctions.put(
			"replyComments", MembershipRequest::getReplyComments);
		attributeSetterBiConsumers.put(
			"replyComments",
			(BiConsumer<MembershipRequest, String>)
				MembershipRequest::setReplyComments);
		attributeGetterFunctions.put(
			"replyDate", MembershipRequest::getReplyDate);
		attributeSetterBiConsumers.put(
			"replyDate",
			(BiConsumer<MembershipRequest, Date>)
				MembershipRequest::setReplyDate);
		attributeGetterFunctions.put(
			"replierUserId", MembershipRequest::getReplierUserId);
		attributeSetterBiConsumers.put(
			"replierUserId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setReplierUserId);
		attributeGetterFunctions.put(
			"statusId", MembershipRequest::getStatusId);
		attributeSetterBiConsumers.put(
			"statusId",
			(BiConsumer<MembershipRequest, Long>)
				MembershipRequest::setStatusId);

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
	public long getMembershipRequestId() {
		return _membershipRequestId;
	}

	@Override
	public void setMembershipRequestId(long membershipRequestId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_membershipRequestId = membershipRequestId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
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
	public String getComments() {
		if (_comments == null) {
			return "";
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_comments = comments;
	}

	@JSON
	@Override
	public String getReplyComments() {
		if (_replyComments == null) {
			return "";
		}
		else {
			return _replyComments;
		}
	}

	@Override
	public void setReplyComments(String replyComments) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_replyComments = replyComments;
	}

	@JSON
	@Override
	public Date getReplyDate() {
		return _replyDate;
	}

	@Override
	public void setReplyDate(Date replyDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_replyDate = replyDate;
	}

	@JSON
	@Override
	public long getReplierUserId() {
		return _replierUserId;
	}

	@Override
	public void setReplierUserId(long replierUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_replierUserId = replierUserId;
	}

	@Override
	public String getReplierUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getReplierUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setReplierUserUuid(String replierUserUuid) {
	}

	@JSON
	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statusId = statusId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalStatusId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("statusId"));
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
			getCompanyId(), MembershipRequest.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MembershipRequest toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MembershipRequest>
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
		MembershipRequestImpl membershipRequestImpl =
			new MembershipRequestImpl();

		membershipRequestImpl.setMvccVersion(getMvccVersion());
		membershipRequestImpl.setMembershipRequestId(getMembershipRequestId());
		membershipRequestImpl.setGroupId(getGroupId());
		membershipRequestImpl.setCompanyId(getCompanyId());
		membershipRequestImpl.setUserId(getUserId());
		membershipRequestImpl.setCreateDate(getCreateDate());
		membershipRequestImpl.setComments(getComments());
		membershipRequestImpl.setReplyComments(getReplyComments());
		membershipRequestImpl.setReplyDate(getReplyDate());
		membershipRequestImpl.setReplierUserId(getReplierUserId());
		membershipRequestImpl.setStatusId(getStatusId());

		membershipRequestImpl.resetOriginalValues();

		return membershipRequestImpl;
	}

	@Override
	public MembershipRequest cloneWithOriginalValues() {
		MembershipRequestImpl membershipRequestImpl =
			new MembershipRequestImpl();

		membershipRequestImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		membershipRequestImpl.setMembershipRequestId(
			this.<Long>getColumnOriginalValue("membershipRequestId"));
		membershipRequestImpl.setGroupId(
			this.<Long>getColumnOriginalValue("groupId"));
		membershipRequestImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		membershipRequestImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		membershipRequestImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		membershipRequestImpl.setComments(
			this.<String>getColumnOriginalValue("comments"));
		membershipRequestImpl.setReplyComments(
			this.<String>getColumnOriginalValue("replyComments"));
		membershipRequestImpl.setReplyDate(
			this.<Date>getColumnOriginalValue("replyDate"));
		membershipRequestImpl.setReplierUserId(
			this.<Long>getColumnOriginalValue("replierUserId"));
		membershipRequestImpl.setStatusId(
			this.<Long>getColumnOriginalValue("statusId"));

		return membershipRequestImpl;
	}

	@Override
	public int compareTo(MembershipRequest membershipRequest) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), membershipRequest.getCreateDate());

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

		if (!(object instanceof MembershipRequest)) {
			return false;
		}

		MembershipRequest membershipRequest = (MembershipRequest)object;

		long primaryKey = membershipRequest.getPrimaryKey();

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

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<MembershipRequest> toCacheModel() {
		MembershipRequestCacheModel membershipRequestCacheModel =
			new MembershipRequestCacheModel();

		membershipRequestCacheModel.mvccVersion = getMvccVersion();

		membershipRequestCacheModel.membershipRequestId =
			getMembershipRequestId();

		membershipRequestCacheModel.groupId = getGroupId();

		membershipRequestCacheModel.companyId = getCompanyId();

		membershipRequestCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			membershipRequestCacheModel.createDate = createDate.getTime();
		}
		else {
			membershipRequestCacheModel.createDate = Long.MIN_VALUE;
		}

		membershipRequestCacheModel.comments = getComments();

		String comments = membershipRequestCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			membershipRequestCacheModel.comments = null;
		}

		membershipRequestCacheModel.replyComments = getReplyComments();

		String replyComments = membershipRequestCacheModel.replyComments;

		if ((replyComments != null) && (replyComments.length() == 0)) {
			membershipRequestCacheModel.replyComments = null;
		}

		Date replyDate = getReplyDate();

		if (replyDate != null) {
			membershipRequestCacheModel.replyDate = replyDate.getTime();
		}
		else {
			membershipRequestCacheModel.replyDate = Long.MIN_VALUE;
		}

		membershipRequestCacheModel.replierUserId = getReplierUserId();

		membershipRequestCacheModel.statusId = getStatusId();

		return membershipRequestCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MembershipRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MembershipRequest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(MembershipRequest)this);

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
		Map<String, Function<MembershipRequest, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MembershipRequest, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MembershipRequest, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((MembershipRequest)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MembershipRequest>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _membershipRequestId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private String _comments;
	private String _replyComments;
	private Date _replyDate;
	private long _replierUserId;
	private long _statusId;

	public <T> T getColumnValue(String columnName) {
		Function<MembershipRequest, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((MembershipRequest)this);
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
		_columnOriginalValues.put("membershipRequestId", _membershipRequestId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("comments", _comments);
		_columnOriginalValues.put("replyComments", _replyComments);
		_columnOriginalValues.put("replyDate", _replyDate);
		_columnOriginalValues.put("replierUserId", _replierUserId);
		_columnOriginalValues.put("statusId", _statusId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("membershipRequestId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("comments", 64L);

		columnBitmasks.put("replyComments", 128L);

		columnBitmasks.put("replyDate", 256L);

		columnBitmasks.put("replierUserId", 512L);

		columnBitmasks.put("statusId", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private MembershipRequest _escapedModel;

}