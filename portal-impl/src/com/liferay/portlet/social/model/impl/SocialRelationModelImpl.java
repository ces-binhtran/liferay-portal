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
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.social.kernel.model.SocialRelation;
import com.liferay.social.kernel.model.SocialRelationModel;

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
 * The base model implementation for the SocialRelation service. Represents a row in the &quot;SocialRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SocialRelationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialRelationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialRelationImpl
 * @generated
 */
public class SocialRelationModelImpl
	extends BaseModelImpl<SocialRelation> implements SocialRelationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social relation model instance should use the <code>SocialRelation</code> interface instead.
	 */
	public static final String TABLE_NAME = "SocialRelation";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"relationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"createDate", Types.BIGINT},
		{"userId1", Types.BIGINT}, {"userId2", Types.BIGINT},
		{"type_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("relationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId1", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId2", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SocialRelation (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,relationId LONG not null,companyId LONG,createDate LONG,userId1 LONG,userId2 LONG,type_ INTEGER,primary key (relationId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table SocialRelation";

	public static final String ORDER_BY_JPQL =
		" ORDER BY socialRelation.relationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SocialRelation.relationId ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID1_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID2_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long RELATIONID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.social.kernel.model.SocialRelation"));

	public SocialRelationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _relationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRelationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _relationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialRelation.class;
	}

	@Override
	public String getModelClassName() {
		return SocialRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SocialRelation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SocialRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialRelation, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SocialRelation)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SocialRelation, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SocialRelation, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SocialRelation)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SocialRelation, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SocialRelation, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SocialRelation>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SocialRelation.class.getClassLoader(), SocialRelation.class,
			ModelWrapper.class);

		try {
			Constructor<SocialRelation> constructor =
				(Constructor<SocialRelation>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SocialRelation, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SocialRelation, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SocialRelation, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<SocialRelation, Object>>();
		Map<String, BiConsumer<SocialRelation, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<SocialRelation, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", SocialRelation::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<SocialRelation, Long>)SocialRelation::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", SocialRelation::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<SocialRelation, Long>)
				SocialRelation::setCtCollectionId);
		attributeGetterFunctions.put("uuid", SocialRelation::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<SocialRelation, String>)SocialRelation::setUuid);
		attributeGetterFunctions.put(
			"relationId", SocialRelation::getRelationId);
		attributeSetterBiConsumers.put(
			"relationId",
			(BiConsumer<SocialRelation, Long>)SocialRelation::setRelationId);
		attributeGetterFunctions.put("companyId", SocialRelation::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SocialRelation, Long>)SocialRelation::setCompanyId);
		attributeGetterFunctions.put(
			"createDate", SocialRelation::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SocialRelation, Long>)SocialRelation::setCreateDate);
		attributeGetterFunctions.put("userId1", SocialRelation::getUserId1);
		attributeSetterBiConsumers.put(
			"userId1",
			(BiConsumer<SocialRelation, Long>)SocialRelation::setUserId1);
		attributeGetterFunctions.put("userId2", SocialRelation::getUserId2);
		attributeSetterBiConsumers.put(
			"userId2",
			(BiConsumer<SocialRelation, Long>)SocialRelation::setUserId2);
		attributeGetterFunctions.put("type", SocialRelation::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<SocialRelation, Integer>)SocialRelation::setType);

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
	public long getRelationId() {
		return _relationId;
	}

	@Override
	public void setRelationId(long relationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_relationId = relationId;
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
	public long getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(long createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public long getUserId1() {
		return _userId1;
	}

	@Override
	public void setUserId1(long userId1) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId1 = userId1;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId1() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId1"));
	}

	@Override
	public long getUserId2() {
		return _userId2;
	}

	@Override
	public void setUserId2(long userId2) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId2 = userId2;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId2() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId2"));
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalType() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("type_"));
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
			getCompanyId(), SocialRelation.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialRelation toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SocialRelation>
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
		SocialRelationImpl socialRelationImpl = new SocialRelationImpl();

		socialRelationImpl.setMvccVersion(getMvccVersion());
		socialRelationImpl.setCtCollectionId(getCtCollectionId());
		socialRelationImpl.setUuid(getUuid());
		socialRelationImpl.setRelationId(getRelationId());
		socialRelationImpl.setCompanyId(getCompanyId());
		socialRelationImpl.setCreateDate(getCreateDate());
		socialRelationImpl.setUserId1(getUserId1());
		socialRelationImpl.setUserId2(getUserId2());
		socialRelationImpl.setType(getType());

		socialRelationImpl.resetOriginalValues();

		return socialRelationImpl;
	}

	@Override
	public int compareTo(SocialRelation socialRelation) {
		long primaryKey = socialRelation.getPrimaryKey();

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

		if (!(object instanceof SocialRelation)) {
			return false;
		}

		SocialRelation socialRelation = (SocialRelation)object;

		long primaryKey = socialRelation.getPrimaryKey();

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
	public CacheModel<SocialRelation> toCacheModel() {
		SocialRelationCacheModel socialRelationCacheModel =
			new SocialRelationCacheModel();

		socialRelationCacheModel.mvccVersion = getMvccVersion();

		socialRelationCacheModel.ctCollectionId = getCtCollectionId();

		socialRelationCacheModel.uuid = getUuid();

		String uuid = socialRelationCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			socialRelationCacheModel.uuid = null;
		}

		socialRelationCacheModel.relationId = getRelationId();

		socialRelationCacheModel.companyId = getCompanyId();

		socialRelationCacheModel.createDate = getCreateDate();

		socialRelationCacheModel.userId1 = getUserId1();

		socialRelationCacheModel.userId2 = getUserId2();

		socialRelationCacheModel.type = getType();

		return socialRelationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SocialRelation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SocialRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialRelation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((SocialRelation)this);

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
		Map<String, Function<SocialRelation, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SocialRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialRelation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SocialRelation)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SocialRelation>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _relationId;
	private long _companyId;
	private long _createDate;
	private long _userId1;
	private long _userId2;
	private int _type;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SocialRelation, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SocialRelation)this);
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
		_columnOriginalValues.put("relationId", _relationId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("userId1", _userId1);
		_columnOriginalValues.put("userId2", _userId2);
		_columnOriginalValues.put("type_", _type);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

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

		columnBitmasks.put("relationId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("userId1", 64L);

		columnBitmasks.put("userId2", 128L);

		columnBitmasks.put("type_", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SocialRelation _escapedModel;

}