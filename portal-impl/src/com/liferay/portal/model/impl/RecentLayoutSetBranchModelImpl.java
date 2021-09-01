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
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.RecentLayoutSetBranch;
import com.liferay.portal.kernel.model.RecentLayoutSetBranchModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
 * The base model implementation for the RecentLayoutSetBranch service. Represents a row in the &quot;RecentLayoutSetBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RecentLayoutSetBranchModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RecentLayoutSetBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetBranchImpl
 * @generated
 */
public class RecentLayoutSetBranchModelImpl
	extends BaseModelImpl<RecentLayoutSetBranch>
	implements RecentLayoutSetBranchModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a recent layout set branch model instance should use the <code>RecentLayoutSetBranch</code> interface instead.
	 */
	public static final String TABLE_NAME = "RecentLayoutSetBranch";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"recentLayoutSetBranchId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"layoutSetBranchId", Types.BIGINT}, {"layoutSetId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recentLayoutSetBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutSetBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutSetId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table RecentLayoutSetBranch (mvccVersion LONG default 0 not null,recentLayoutSetBranchId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,layoutSetBranchId LONG,layoutSetId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table RecentLayoutSetBranch";

	public static final String ORDER_BY_JPQL =
		" ORDER BY recentLayoutSetBranch.recentLayoutSetBranchId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY RecentLayoutSetBranch.recentLayoutSetBranchId ASC";

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
	public static final long LAYOUTSETBRANCHID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LAYOUTSETID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RECENTLAYOUTSETBRANCHID_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.RecentLayoutSetBranch"));

	public RecentLayoutSetBranchModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _recentLayoutSetBranchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRecentLayoutSetBranchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _recentLayoutSetBranchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RecentLayoutSetBranch.class;
	}

	@Override
	public String getModelClassName() {
		return RecentLayoutSetBranch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RecentLayoutSetBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RecentLayoutSetBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecentLayoutSetBranch, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((RecentLayoutSetBranch)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RecentLayoutSetBranch, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RecentLayoutSetBranch, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RecentLayoutSetBranch)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RecentLayoutSetBranch, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RecentLayoutSetBranch, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RecentLayoutSetBranch>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RecentLayoutSetBranch.class.getClassLoader(),
			RecentLayoutSetBranch.class, ModelWrapper.class);

		try {
			Constructor<RecentLayoutSetBranch> constructor =
				(Constructor<RecentLayoutSetBranch>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RecentLayoutSetBranch, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RecentLayoutSetBranch, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RecentLayoutSetBranch, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<RecentLayoutSetBranch, Object>>();
		Map<String, BiConsumer<RecentLayoutSetBranch, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<RecentLayoutSetBranch, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", RecentLayoutSetBranch::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setMvccVersion);
		attributeGetterFunctions.put(
			"recentLayoutSetBranchId",
			RecentLayoutSetBranch::getRecentLayoutSetBranchId);
		attributeSetterBiConsumers.put(
			"recentLayoutSetBranchId",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setRecentLayoutSetBranchId);
		attributeGetterFunctions.put(
			"groupId", RecentLayoutSetBranch::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setGroupId);
		attributeGetterFunctions.put(
			"companyId", RecentLayoutSetBranch::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setCompanyId);
		attributeGetterFunctions.put(
			"userId", RecentLayoutSetBranch::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setUserId);
		attributeGetterFunctions.put(
			"layoutSetBranchId", RecentLayoutSetBranch::getLayoutSetBranchId);
		attributeSetterBiConsumers.put(
			"layoutSetBranchId",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setLayoutSetBranchId);
		attributeGetterFunctions.put(
			"layoutSetId", RecentLayoutSetBranch::getLayoutSetId);
		attributeSetterBiConsumers.put(
			"layoutSetId",
			(BiConsumer<RecentLayoutSetBranch, Long>)
				RecentLayoutSetBranch::setLayoutSetId);

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
	public long getRecentLayoutSetBranchId() {
		return _recentLayoutSetBranchId;
	}

	@Override
	public void setRecentLayoutSetBranchId(long recentLayoutSetBranchId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_recentLayoutSetBranchId = recentLayoutSetBranchId;
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

	@Override
	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_layoutSetBranchId = layoutSetBranchId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLayoutSetBranchId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("layoutSetBranchId"));
	}

	@Override
	public long getLayoutSetId() {
		return _layoutSetId;
	}

	@Override
	public void setLayoutSetId(long layoutSetId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_layoutSetId = layoutSetId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLayoutSetId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("layoutSetId"));
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
			getCompanyId(), RecentLayoutSetBranch.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RecentLayoutSetBranch toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RecentLayoutSetBranch>
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
		RecentLayoutSetBranchImpl recentLayoutSetBranchImpl =
			new RecentLayoutSetBranchImpl();

		recentLayoutSetBranchImpl.setMvccVersion(getMvccVersion());
		recentLayoutSetBranchImpl.setRecentLayoutSetBranchId(
			getRecentLayoutSetBranchId());
		recentLayoutSetBranchImpl.setGroupId(getGroupId());
		recentLayoutSetBranchImpl.setCompanyId(getCompanyId());
		recentLayoutSetBranchImpl.setUserId(getUserId());
		recentLayoutSetBranchImpl.setLayoutSetBranchId(getLayoutSetBranchId());
		recentLayoutSetBranchImpl.setLayoutSetId(getLayoutSetId());

		recentLayoutSetBranchImpl.resetOriginalValues();

		return recentLayoutSetBranchImpl;
	}

	@Override
	public int compareTo(RecentLayoutSetBranch recentLayoutSetBranch) {
		long primaryKey = recentLayoutSetBranch.getPrimaryKey();

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

		if (!(object instanceof RecentLayoutSetBranch)) {
			return false;
		}

		RecentLayoutSetBranch recentLayoutSetBranch =
			(RecentLayoutSetBranch)object;

		long primaryKey = recentLayoutSetBranch.getPrimaryKey();

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
	public CacheModel<RecentLayoutSetBranch> toCacheModel() {
		RecentLayoutSetBranchCacheModel recentLayoutSetBranchCacheModel =
			new RecentLayoutSetBranchCacheModel();

		recentLayoutSetBranchCacheModel.mvccVersion = getMvccVersion();

		recentLayoutSetBranchCacheModel.recentLayoutSetBranchId =
			getRecentLayoutSetBranchId();

		recentLayoutSetBranchCacheModel.groupId = getGroupId();

		recentLayoutSetBranchCacheModel.companyId = getCompanyId();

		recentLayoutSetBranchCacheModel.userId = getUserId();

		recentLayoutSetBranchCacheModel.layoutSetBranchId =
			getLayoutSetBranchId();

		recentLayoutSetBranchCacheModel.layoutSetId = getLayoutSetId();

		return recentLayoutSetBranchCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RecentLayoutSetBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RecentLayoutSetBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecentLayoutSetBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(RecentLayoutSetBranch)this);

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
		Map<String, Function<RecentLayoutSetBranch, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RecentLayoutSetBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecentLayoutSetBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((RecentLayoutSetBranch)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, RecentLayoutSetBranch>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _recentLayoutSetBranchId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _layoutSetBranchId;
	private long _layoutSetId;

	public <T> T getColumnValue(String columnName) {
		Function<RecentLayoutSetBranch, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((RecentLayoutSetBranch)this);
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
			"recentLayoutSetBranchId", _recentLayoutSetBranchId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("layoutSetBranchId", _layoutSetBranchId);
		_columnOriginalValues.put("layoutSetId", _layoutSetId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("recentLayoutSetBranchId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("layoutSetBranchId", 32L);

		columnBitmasks.put("layoutSetId", 64L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private RecentLayoutSetBranch _escapedModel;

}