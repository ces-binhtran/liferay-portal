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

package com.liferay.friendly.url.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.friendly.url.model.FriendlyURLEntryMapping;
import com.liferay.friendly.url.model.FriendlyURLEntryMappingModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the FriendlyURLEntryMapping service. Represents a row in the &quot;FriendlyURLEntryMapping&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>FriendlyURLEntryMappingModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FriendlyURLEntryMappingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryMappingImpl
 * @generated
 */
public class FriendlyURLEntryMappingModelImpl
	extends BaseModelImpl<FriendlyURLEntryMapping>
	implements FriendlyURLEntryMappingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a friendly url entry mapping model instance should use the <code>FriendlyURLEntryMapping</code> interface instead.
	 */
	public static final String TABLE_NAME = "FriendlyURLEntryMapping";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"friendlyURLEntryMappingId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"friendlyURLEntryId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryMappingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FriendlyURLEntryMapping (mvccVersion LONG default 0 not null,friendlyURLEntryMappingId LONG not null primary key,classNameId LONG,classPK LONG,friendlyURLEntryId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table FriendlyURLEntryMapping";

	public static final String ORDER_BY_JPQL =
		" ORDER BY friendlyURLEntryMapping.friendlyURLEntryMappingId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FriendlyURLEntryMapping.friendlyURLEntryMappingId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long FRIENDLYURLENTRYMAPPINGID_COLUMN_BITMASK = 4L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public FriendlyURLEntryMappingModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _friendlyURLEntryMappingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFriendlyURLEntryMappingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _friendlyURLEntryMappingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FriendlyURLEntryMapping.class;
	}

	@Override
	public String getModelClassName() {
		return FriendlyURLEntryMapping.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FriendlyURLEntryMapping, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryMapping, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((FriendlyURLEntryMapping)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FriendlyURLEntryMapping, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FriendlyURLEntryMapping, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FriendlyURLEntryMapping)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FriendlyURLEntryMapping, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FriendlyURLEntryMapping, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, FriendlyURLEntryMapping>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			FriendlyURLEntryMapping.class.getClassLoader(),
			FriendlyURLEntryMapping.class, ModelWrapper.class);

		try {
			Constructor<FriendlyURLEntryMapping> constructor =
				(Constructor<FriendlyURLEntryMapping>)proxyClass.getConstructor(
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

	private static final Map<String, Function<FriendlyURLEntryMapping, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<FriendlyURLEntryMapping, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<FriendlyURLEntryMapping, Object>>();
		Map<String, BiConsumer<FriendlyURLEntryMapping, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<FriendlyURLEntryMapping, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", FriendlyURLEntryMapping::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setMvccVersion);
		attributeGetterFunctions.put(
			"friendlyURLEntryMappingId",
			FriendlyURLEntryMapping::getFriendlyURLEntryMappingId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryMappingId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setFriendlyURLEntryMappingId);
		attributeGetterFunctions.put(
			"classNameId", FriendlyURLEntryMapping::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", FriendlyURLEntryMapping::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setClassPK);
		attributeGetterFunctions.put(
			"friendlyURLEntryId",
			FriendlyURLEntryMapping::getFriendlyURLEntryId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryId",
			(BiConsumer<FriendlyURLEntryMapping, Long>)
				FriendlyURLEntryMapping::setFriendlyURLEntryId);

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
	public long getFriendlyURLEntryMappingId() {
		return _friendlyURLEntryMappingId;
	}

	@Override
	public void setFriendlyURLEntryMappingId(long friendlyURLEntryMappingId) {
		_friendlyURLEntryMappingId = friendlyURLEntryMappingId;
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
	public long getFriendlyURLEntryId() {
		return _friendlyURLEntryId;
	}

	@Override
	public void setFriendlyURLEntryId(long friendlyURLEntryId) {
		_friendlyURLEntryId = friendlyURLEntryId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, FriendlyURLEntryMapping.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FriendlyURLEntryMapping toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, FriendlyURLEntryMapping>
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
		FriendlyURLEntryMappingImpl friendlyURLEntryMappingImpl =
			new FriendlyURLEntryMappingImpl();

		friendlyURLEntryMappingImpl.setMvccVersion(getMvccVersion());
		friendlyURLEntryMappingImpl.setFriendlyURLEntryMappingId(
			getFriendlyURLEntryMappingId());
		friendlyURLEntryMappingImpl.setClassNameId(getClassNameId());
		friendlyURLEntryMappingImpl.setClassPK(getClassPK());
		friendlyURLEntryMappingImpl.setFriendlyURLEntryId(
			getFriendlyURLEntryId());

		friendlyURLEntryMappingImpl.resetOriginalValues();

		return friendlyURLEntryMappingImpl;
	}

	@Override
	public int compareTo(FriendlyURLEntryMapping friendlyURLEntryMapping) {
		long primaryKey = friendlyURLEntryMapping.getPrimaryKey();

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

		if (!(object instanceof FriendlyURLEntryMapping)) {
			return false;
		}

		FriendlyURLEntryMapping friendlyURLEntryMapping =
			(FriendlyURLEntryMapping)object;

		long primaryKey = friendlyURLEntryMapping.getPrimaryKey();

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
		_originalClassNameId = _classNameId;

		_setOriginalClassNameId = false;

		_originalClassPK = _classPK;

		_setOriginalClassPK = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<FriendlyURLEntryMapping> toCacheModel() {
		FriendlyURLEntryMappingCacheModel friendlyURLEntryMappingCacheModel =
			new FriendlyURLEntryMappingCacheModel();

		friendlyURLEntryMappingCacheModel.mvccVersion = getMvccVersion();

		friendlyURLEntryMappingCacheModel.friendlyURLEntryMappingId =
			getFriendlyURLEntryMappingId();

		friendlyURLEntryMappingCacheModel.classNameId = getClassNameId();

		friendlyURLEntryMappingCacheModel.classPK = getClassPK();

		friendlyURLEntryMappingCacheModel.friendlyURLEntryId =
			getFriendlyURLEntryId();

		return friendlyURLEntryMappingCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FriendlyURLEntryMapping, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryMapping, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((FriendlyURLEntryMapping)this));
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
		Map<String, Function<FriendlyURLEntryMapping, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<FriendlyURLEntryMapping, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryMapping, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((FriendlyURLEntryMapping)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, FriendlyURLEntryMapping>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _friendlyURLEntryMappingId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _friendlyURLEntryId;
	private long _columnBitmask;
	private FriendlyURLEntryMapping _escapedModel;

}