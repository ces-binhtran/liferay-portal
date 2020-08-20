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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialActivitySetting;
import com.liferay.social.kernel.model.SocialActivitySettingModel;
import com.liferay.social.kernel.model.SocialActivitySettingSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SocialActivitySetting service. Represents a row in the &quot;SocialActivitySetting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SocialActivitySettingModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivitySettingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySettingImpl
 * @generated
 */
@JSON(strict = true)
public class SocialActivitySettingModelImpl
	extends BaseModelImpl<SocialActivitySetting>
	implements SocialActivitySettingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity setting model instance should use the <code>SocialActivitySetting</code> interface instead.
	 */
	public static final String TABLE_NAME = "SocialActivitySetting";

	public static final Object[][] TABLE_COLUMNS = {
		{"activitySettingId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"activityType", Types.INTEGER}, {"name", Types.VARCHAR},
		{"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("activitySettingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("activityType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SocialActivitySetting (activitySettingId LONG not null primary key,groupId LONG,companyId LONG,classNameId LONG,activityType INTEGER,name VARCHAR(75) null,value VARCHAR(1024) null)";

	public static final String TABLE_SQL_DROP =
		"drop table SocialActivitySetting";

	public static final String ORDER_BY_JPQL =
		" ORDER BY socialActivitySetting.activitySettingId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SocialActivitySetting.activitySettingId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.social.kernel.model.SocialActivitySetting"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.social.kernel.model.SocialActivitySetting"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.social.kernel.model.SocialActivitySetting"),
		true);

	public static final long ACTIVITYTYPE_COLUMN_BITMASK = 1L;

	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long ACTIVITYSETTINGID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SocialActivitySetting toModel(
		SocialActivitySettingSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		SocialActivitySetting model = new SocialActivitySettingImpl();

		model.setActivitySettingId(soapModel.getActivitySettingId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setActivityType(soapModel.getActivityType());
		model.setName(soapModel.getName());
		model.setValue(soapModel.getValue());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SocialActivitySetting> toModels(
		SocialActivitySettingSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SocialActivitySetting> models =
			new ArrayList<SocialActivitySetting>(soapModels.length);

		for (SocialActivitySettingSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.social.kernel.model.SocialActivitySetting"));

	public SocialActivitySettingModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _activitySettingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActivitySettingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activitySettingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialActivitySetting.class;
	}

	@Override
	public String getModelClassName() {
		return SocialActivitySetting.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SocialActivitySetting, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SocialActivitySetting, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivitySetting, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SocialActivitySetting)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SocialActivitySetting, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SocialActivitySetting, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SocialActivitySetting)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SocialActivitySetting, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SocialActivitySetting, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SocialActivitySetting>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SocialActivitySetting.class.getClassLoader(),
			SocialActivitySetting.class, ModelWrapper.class);

		try {
			Constructor<SocialActivitySetting> constructor =
				(Constructor<SocialActivitySetting>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SocialActivitySetting, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SocialActivitySetting, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SocialActivitySetting, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SocialActivitySetting, Object>>();
		Map<String, BiConsumer<SocialActivitySetting, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<SocialActivitySetting, ?>>();

		attributeGetterFunctions.put(
			"activitySettingId", SocialActivitySetting::getActivitySettingId);
		attributeSetterBiConsumers.put(
			"activitySettingId",
			(BiConsumer<SocialActivitySetting, Long>)
				SocialActivitySetting::setActivitySettingId);
		attributeGetterFunctions.put(
			"groupId", SocialActivitySetting::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<SocialActivitySetting, Long>)
				SocialActivitySetting::setGroupId);
		attributeGetterFunctions.put(
			"companyId", SocialActivitySetting::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SocialActivitySetting, Long>)
				SocialActivitySetting::setCompanyId);
		attributeGetterFunctions.put(
			"classNameId", SocialActivitySetting::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SocialActivitySetting, Long>)
				SocialActivitySetting::setClassNameId);
		attributeGetterFunctions.put(
			"activityType", SocialActivitySetting::getActivityType);
		attributeSetterBiConsumers.put(
			"activityType",
			(BiConsumer<SocialActivitySetting, Integer>)
				SocialActivitySetting::setActivityType);
		attributeGetterFunctions.put("name", SocialActivitySetting::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<SocialActivitySetting, String>)
				SocialActivitySetting::setName);
		attributeGetterFunctions.put("value", SocialActivitySetting::getValue);
		attributeSetterBiConsumers.put(
			"value",
			(BiConsumer<SocialActivitySetting, String>)
				SocialActivitySetting::setValue);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getActivitySettingId() {
		return _activitySettingId;
	}

	@Override
	public void setActivitySettingId(long activitySettingId) {
		_activitySettingId = activitySettingId;
	}

	@JSON
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

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
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
			getCompanyId(), SocialActivitySetting.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialActivitySetting toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SocialActivitySetting>
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
		SocialActivitySettingImpl socialActivitySettingImpl =
			new SocialActivitySettingImpl();

		socialActivitySettingImpl.setActivitySettingId(getActivitySettingId());
		socialActivitySettingImpl.setGroupId(getGroupId());
		socialActivitySettingImpl.setCompanyId(getCompanyId());
		socialActivitySettingImpl.setClassNameId(getClassNameId());
		socialActivitySettingImpl.setActivityType(getActivityType());
		socialActivitySettingImpl.setName(getName());
		socialActivitySettingImpl.setValue(getValue());

		socialActivitySettingImpl.resetOriginalValues();

		return socialActivitySettingImpl;
	}

	@Override
	public int compareTo(SocialActivitySetting socialActivitySetting) {
		long primaryKey = socialActivitySetting.getPrimaryKey();

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

		if (!(object instanceof SocialActivitySetting)) {
			return false;
		}

		SocialActivitySetting socialActivitySetting =
			(SocialActivitySetting)object;

		long primaryKey = socialActivitySetting.getPrimaryKey();

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

		_originalClassNameId = _classNameId;

		_setOriginalClassNameId = false;

		_originalActivityType = _activityType;

		_setOriginalActivityType = false;

		_originalName = _name;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SocialActivitySetting> toCacheModel() {
		SocialActivitySettingCacheModel socialActivitySettingCacheModel =
			new SocialActivitySettingCacheModel();

		socialActivitySettingCacheModel.activitySettingId =
			getActivitySettingId();

		socialActivitySettingCacheModel.groupId = getGroupId();

		socialActivitySettingCacheModel.companyId = getCompanyId();

		socialActivitySettingCacheModel.classNameId = getClassNameId();

		socialActivitySettingCacheModel.activityType = getActivityType();

		socialActivitySettingCacheModel.name = getName();

		String name = socialActivitySettingCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			socialActivitySettingCacheModel.name = null;
		}

		socialActivitySettingCacheModel.value = getValue();

		String value = socialActivitySettingCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			socialActivitySettingCacheModel.value = null;
		}

		return socialActivitySettingCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SocialActivitySetting, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SocialActivitySetting, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivitySetting, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((SocialActivitySetting)this));
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
		Map<String, Function<SocialActivitySetting, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SocialActivitySetting, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivitySetting, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((SocialActivitySetting)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SocialActivitySetting>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _activitySettingId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private int _activityType;
	private int _originalActivityType;
	private boolean _setOriginalActivityType;
	private String _name;
	private String _originalName;
	private String _value;
	private long _columnBitmask;
	private SocialActivitySetting _escapedModel;

}