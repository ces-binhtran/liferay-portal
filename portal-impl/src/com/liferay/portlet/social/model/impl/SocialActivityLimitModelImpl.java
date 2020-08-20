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

package com.liferay.portlet.social.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.social.kernel.model.SocialActivityLimit;
import com.liferay.social.kernel.model.SocialActivityLimitModel;

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
 * The base model implementation for the SocialActivityLimit service. Represents a row in the &quot;SocialActivityLimit&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SocialActivityLimitModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivityLimitImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityLimitImpl
 * @generated
 */
public class SocialActivityLimitModelImpl
	extends BaseModelImpl<SocialActivityLimit>
	implements SocialActivityLimitModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity limit model instance should use the <code>SocialActivityLimit</code> interface instead.
	 */
	public static final String TABLE_NAME = "SocialActivityLimit";

	public static final Object[][] TABLE_COLUMNS = {
		{"activityLimitId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"activityType", Types.INTEGER}, {"activityCounterName", Types.VARCHAR},
		{"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("activityLimitId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("activityType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("activityCounterName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SocialActivityLimit (activityLimitId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,classNameId LONG,classPK LONG,activityType INTEGER,activityCounterName VARCHAR(75) null,value VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table SocialActivityLimit";

	public static final String ORDER_BY_JPQL =
		" ORDER BY socialActivityLimit.activityLimitId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SocialActivityLimit.activityLimitId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.social.kernel.model.SocialActivityLimit"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.social.kernel.model.SocialActivityLimit"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.social.kernel.model.SocialActivityLimit"),
		true);

	public static final long ACTIVITYCOUNTERNAME_COLUMN_BITMASK = 1L;

	public static final long ACTIVITYTYPE_COLUMN_BITMASK = 2L;

	public static final long CLASSNAMEID_COLUMN_BITMASK = 4L;

	public static final long CLASSPK_COLUMN_BITMASK = 8L;

	public static final long GROUPID_COLUMN_BITMASK = 16L;

	public static final long USERID_COLUMN_BITMASK = 32L;

	public static final long ACTIVITYLIMITID_COLUMN_BITMASK = 64L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.social.kernel.model.SocialActivityLimit"));

	public SocialActivityLimitModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _activityLimitId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActivityLimitId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityLimitId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialActivityLimit.class;
	}

	@Override
	public String getModelClassName() {
		return SocialActivityLimit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SocialActivityLimit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SocialActivityLimit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivityLimit, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SocialActivityLimit)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SocialActivityLimit, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SocialActivityLimit, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SocialActivityLimit)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SocialActivityLimit, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SocialActivityLimit, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SocialActivityLimit>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SocialActivityLimit.class.getClassLoader(),
			SocialActivityLimit.class, ModelWrapper.class);

		try {
			Constructor<SocialActivityLimit> constructor =
				(Constructor<SocialActivityLimit>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SocialActivityLimit, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SocialActivityLimit, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SocialActivityLimit, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SocialActivityLimit, Object>>();
		Map<String, BiConsumer<SocialActivityLimit, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SocialActivityLimit, ?>>();

		attributeGetterFunctions.put(
			"activityLimitId", SocialActivityLimit::getActivityLimitId);
		attributeSetterBiConsumers.put(
			"activityLimitId",
			(BiConsumer<SocialActivityLimit, Long>)
				SocialActivityLimit::setActivityLimitId);
		attributeGetterFunctions.put(
			"groupId", SocialActivityLimit::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<SocialActivityLimit, Long>)
				SocialActivityLimit::setGroupId);
		attributeGetterFunctions.put(
			"companyId", SocialActivityLimit::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SocialActivityLimit, Long>)
				SocialActivityLimit::setCompanyId);
		attributeGetterFunctions.put("userId", SocialActivityLimit::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<SocialActivityLimit, Long>)
				SocialActivityLimit::setUserId);
		attributeGetterFunctions.put(
			"classNameId", SocialActivityLimit::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SocialActivityLimit, Long>)
				SocialActivityLimit::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", SocialActivityLimit::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<SocialActivityLimit, Long>)
				SocialActivityLimit::setClassPK);
		attributeGetterFunctions.put(
			"activityType", SocialActivityLimit::getActivityType);
		attributeSetterBiConsumers.put(
			"activityType",
			(BiConsumer<SocialActivityLimit, Integer>)
				SocialActivityLimit::setActivityType);
		attributeGetterFunctions.put(
			"activityCounterName", SocialActivityLimit::getActivityCounterName);
		attributeSetterBiConsumers.put(
			"activityCounterName",
			(BiConsumer<SocialActivityLimit, String>)
				SocialActivityLimit::setActivityCounterName);
		attributeGetterFunctions.put("value", SocialActivityLimit::getValue);
		attributeSetterBiConsumers.put(
			"value",
			(BiConsumer<SocialActivityLimit, String>)
				SocialActivityLimit::setValue);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getActivityLimitId() {
		return _activityLimitId;
	}

	@Override
	public void setActivityLimitId(long activityLimitId) {
		_activityLimitId = activityLimitId;
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
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
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

	public long getOriginalUserId() {
		return _originalUserId;
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
	public int getActivityType() {
		return _activityType;
	}

	@Override
	public void setActivityType(int activityType) {
		_columnBitmask |= ACTIVITYTYPE_COLUMN_BITMASK;

		if (!_setOriginalActivityType) {
			_setOriginalActivityType = true;

			_originalActivityType = _activityType;
		}

		_activityType = activityType;
	}

	public int getOriginalActivityType() {
		return _originalActivityType;
	}

	@Override
	public String getActivityCounterName() {
		if (_activityCounterName == null) {
			return "";
		}
		else {
			return _activityCounterName;
		}
	}

	@Override
	public void setActivityCounterName(String activityCounterName) {
		_columnBitmask |= ACTIVITYCOUNTERNAME_COLUMN_BITMASK;

		if (_originalActivityCounterName == null) {
			_originalActivityCounterName = _activityCounterName;
		}

		_activityCounterName = activityCounterName;
	}

	public String getOriginalActivityCounterName() {
		return GetterUtil.getString(_originalActivityCounterName);
	}

	@Override
	public String getValue() {
		if (_value == null) {
			return "";
		}
		else {
			return _value;
		}
	}

	@Override
	public void setValue(String value) {
		_value = value;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SocialActivityLimit.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialActivityLimit toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SocialActivityLimit>
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
		SocialActivityLimitImpl socialActivityLimitImpl =
			new SocialActivityLimitImpl();

		socialActivityLimitImpl.setActivityLimitId(getActivityLimitId());
		socialActivityLimitImpl.setGroupId(getGroupId());
		socialActivityLimitImpl.setCompanyId(getCompanyId());
		socialActivityLimitImpl.setUserId(getUserId());
		socialActivityLimitImpl.setClassNameId(getClassNameId());
		socialActivityLimitImpl.setClassPK(getClassPK());
		socialActivityLimitImpl.setActivityType(getActivityType());
		socialActivityLimitImpl.setActivityCounterName(
			getActivityCounterName());
		socialActivityLimitImpl.setValue(getValue());

		socialActivityLimitImpl.resetOriginalValues();

		return socialActivityLimitImpl;
	}

	@Override
	public int compareTo(SocialActivityLimit socialActivityLimit) {
		long primaryKey = socialActivityLimit.getPrimaryKey();

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

		if (!(object instanceof SocialActivityLimit)) {
			return false;
		}

		SocialActivityLimit socialActivityLimit = (SocialActivityLimit)object;

		long primaryKey = socialActivityLimit.getPrimaryKey();

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
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_originalGroupId = _groupId;

		_setOriginalGroupId = false;

		_originalUserId = _userId;

		_setOriginalUserId = false;

		_originalClassNameId = _classNameId;

		_setOriginalClassNameId = false;

		_originalClassPK = _classPK;

		_setOriginalClassPK = false;

		_originalActivityType = _activityType;

		_setOriginalActivityType = false;

		_originalActivityCounterName = _activityCounterName;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SocialActivityLimit> toCacheModel() {
		SocialActivityLimitCacheModel socialActivityLimitCacheModel =
			new SocialActivityLimitCacheModel();

		socialActivityLimitCacheModel.activityLimitId = getActivityLimitId();

		socialActivityLimitCacheModel.groupId = getGroupId();

		socialActivityLimitCacheModel.companyId = getCompanyId();

		socialActivityLimitCacheModel.userId = getUserId();

		socialActivityLimitCacheModel.classNameId = getClassNameId();

		socialActivityLimitCacheModel.classPK = getClassPK();

		socialActivityLimitCacheModel.activityType = getActivityType();

		socialActivityLimitCacheModel.activityCounterName =
			getActivityCounterName();

		String activityCounterName =
			socialActivityLimitCacheModel.activityCounterName;

		if ((activityCounterName != null) &&
			(activityCounterName.length() == 0)) {

			socialActivityLimitCacheModel.activityCounterName = null;
		}

		socialActivityLimitCacheModel.value = getValue();

		String value = socialActivityLimitCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			socialActivityLimitCacheModel.value = null;
		}

		return socialActivityLimitCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SocialActivityLimit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SocialActivityLimit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivityLimit, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SocialActivityLimit)this));
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
		Map<String, Function<SocialActivityLimit, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SocialActivityLimit, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivityLimit, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SocialActivityLimit)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SocialActivityLimit>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _activityLimitId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private int _activityType;
	private int _originalActivityType;
	private boolean _setOriginalActivityType;
	private String _activityCounterName;
	private String _originalActivityCounterName;
	private String _value;
	private long _columnBitmask;
	private SocialActivityLimit _escapedModel;

}