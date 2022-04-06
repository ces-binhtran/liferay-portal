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

package com.liferay.portal.tools.service.builder.test.model.impl;

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
import com.liferay.portal.tools.service.builder.test.model.LocalizedEntryLocalization;
import com.liferay.portal.tools.service.builder.test.model.LocalizedEntryLocalizationModel;

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
 * The base model implementation for the LocalizedEntryLocalization service. Represents a row in the &quot;LocalizedEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LocalizedEntryLocalizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LocalizedEntryLocalizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LocalizedEntryLocalizationImpl
 * @generated
 */
public class LocalizedEntryLocalizationModelImpl
	extends BaseModelImpl<LocalizedEntryLocalization>
	implements LocalizedEntryLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a localized entry localization model instance should use the <code>LocalizedEntryLocalization</code> interface instead.
	 */
	public static final String TABLE_NAME = "LocalizedEntryLocalization";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"localizedEntryLocalizationId", Types.BIGINT},
		{"localizedEntryId", Types.BIGINT}, {"languageId", Types.VARCHAR},
		{"title", Types.VARCHAR}, {"content", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("localizedEntryLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("localizedEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LocalizedEntryLocalization (mvccVersion LONG default 0 not null,localizedEntryLocalizationId LONG not null primary key,localizedEntryId LONG,languageId VARCHAR(75) null,title VARCHAR(75) null,content VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table LocalizedEntryLocalization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY localizedEntryLocalization.localizedEntryLocalizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LocalizedEntryLocalization.localizedEntryLocalizationId ASC";

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
	public static final long LANGUAGEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LOCALIZEDENTRYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LOCALIZEDENTRYLOCALIZATIONID_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.LocalizedEntryLocalization"));

	public LocalizedEntryLocalizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _localizedEntryLocalizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLocalizedEntryLocalizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _localizedEntryLocalizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LocalizedEntryLocalization.class;
	}

	@Override
	public String getModelClassName() {
		return LocalizedEntryLocalization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LocalizedEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LocalizedEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LocalizedEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(LocalizedEntryLocalization)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LocalizedEntryLocalization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LocalizedEntryLocalization, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LocalizedEntryLocalization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LocalizedEntryLocalization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LocalizedEntryLocalization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LocalizedEntryLocalization>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LocalizedEntryLocalization.class.getClassLoader(),
			LocalizedEntryLocalization.class, ModelWrapper.class);

		try {
			Constructor<LocalizedEntryLocalization> constructor =
				(Constructor<LocalizedEntryLocalization>)
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
		<String, Function<LocalizedEntryLocalization, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<LocalizedEntryLocalization, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<LocalizedEntryLocalization, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<LocalizedEntryLocalization, Object>>();
		Map<String, BiConsumer<LocalizedEntryLocalization, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<LocalizedEntryLocalization, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", LocalizedEntryLocalization::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LocalizedEntryLocalization, Long>)
				LocalizedEntryLocalization::setMvccVersion);
		attributeGetterFunctions.put(
			"localizedEntryLocalizationId",
			LocalizedEntryLocalization::getLocalizedEntryLocalizationId);
		attributeSetterBiConsumers.put(
			"localizedEntryLocalizationId",
			(BiConsumer<LocalizedEntryLocalization, Long>)
				LocalizedEntryLocalization::setLocalizedEntryLocalizationId);
		attributeGetterFunctions.put(
			"localizedEntryId",
			LocalizedEntryLocalization::getLocalizedEntryId);
		attributeSetterBiConsumers.put(
			"localizedEntryId",
			(BiConsumer<LocalizedEntryLocalization, Long>)
				LocalizedEntryLocalization::setLocalizedEntryId);
		attributeGetterFunctions.put(
			"languageId", LocalizedEntryLocalization::getLanguageId);
		attributeSetterBiConsumers.put(
			"languageId",
			(BiConsumer<LocalizedEntryLocalization, String>)
				LocalizedEntryLocalization::setLanguageId);
		attributeGetterFunctions.put(
			"title", LocalizedEntryLocalization::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<LocalizedEntryLocalization, String>)
				LocalizedEntryLocalization::setTitle);
		attributeGetterFunctions.put(
			"content", LocalizedEntryLocalization::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<LocalizedEntryLocalization, String>)
				LocalizedEntryLocalization::setContent);

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
	public long getLocalizedEntryLocalizationId() {
		return _localizedEntryLocalizationId;
	}

	@Override
	public void setLocalizedEntryLocalizationId(
		long localizedEntryLocalizationId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_localizedEntryLocalizationId = localizedEntryLocalizationId;
	}

	@Override
	public long getLocalizedEntryId() {
		return _localizedEntryId;
	}

	@Override
	public void setLocalizedEntryId(long localizedEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_localizedEntryId = localizedEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalLocalizedEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("localizedEntryId"));
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_languageId = languageId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalLanguageId() {
		return getColumnOriginalValue("languageId");
	}

	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

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
			0, LocalizedEntryLocalization.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LocalizedEntryLocalization toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LocalizedEntryLocalization>
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
		LocalizedEntryLocalizationImpl localizedEntryLocalizationImpl =
			new LocalizedEntryLocalizationImpl();

		localizedEntryLocalizationImpl.setMvccVersion(getMvccVersion());
		localizedEntryLocalizationImpl.setLocalizedEntryLocalizationId(
			getLocalizedEntryLocalizationId());
		localizedEntryLocalizationImpl.setLocalizedEntryId(
			getLocalizedEntryId());
		localizedEntryLocalizationImpl.setLanguageId(getLanguageId());
		localizedEntryLocalizationImpl.setTitle(getTitle());
		localizedEntryLocalizationImpl.setContent(getContent());

		localizedEntryLocalizationImpl.resetOriginalValues();

		return localizedEntryLocalizationImpl;
	}

	@Override
	public LocalizedEntryLocalization cloneWithOriginalValues() {
		LocalizedEntryLocalizationImpl localizedEntryLocalizationImpl =
			new LocalizedEntryLocalizationImpl();

		localizedEntryLocalizationImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		localizedEntryLocalizationImpl.setLocalizedEntryLocalizationId(
			this.<Long>getColumnOriginalValue("localizedEntryLocalizationId"));
		localizedEntryLocalizationImpl.setLocalizedEntryId(
			this.<Long>getColumnOriginalValue("localizedEntryId"));
		localizedEntryLocalizationImpl.setLanguageId(
			this.<String>getColumnOriginalValue("languageId"));
		localizedEntryLocalizationImpl.setTitle(
			this.<String>getColumnOriginalValue("title"));
		localizedEntryLocalizationImpl.setContent(
			this.<String>getColumnOriginalValue("content"));

		return localizedEntryLocalizationImpl;
	}

	@Override
	public int compareTo(
		LocalizedEntryLocalization localizedEntryLocalization) {

		long primaryKey = localizedEntryLocalization.getPrimaryKey();

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

		if (!(object instanceof LocalizedEntryLocalization)) {
			return false;
		}

		LocalizedEntryLocalization localizedEntryLocalization =
			(LocalizedEntryLocalization)object;

		long primaryKey = localizedEntryLocalization.getPrimaryKey();

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
	public CacheModel<LocalizedEntryLocalization> toCacheModel() {
		LocalizedEntryLocalizationCacheModel
			localizedEntryLocalizationCacheModel =
				new LocalizedEntryLocalizationCacheModel();

		localizedEntryLocalizationCacheModel.mvccVersion = getMvccVersion();

		localizedEntryLocalizationCacheModel.localizedEntryLocalizationId =
			getLocalizedEntryLocalizationId();

		localizedEntryLocalizationCacheModel.localizedEntryId =
			getLocalizedEntryId();

		localizedEntryLocalizationCacheModel.languageId = getLanguageId();

		String languageId = localizedEntryLocalizationCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			localizedEntryLocalizationCacheModel.languageId = null;
		}

		localizedEntryLocalizationCacheModel.title = getTitle();

		String title = localizedEntryLocalizationCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			localizedEntryLocalizationCacheModel.title = null;
		}

		localizedEntryLocalizationCacheModel.content = getContent();

		String content = localizedEntryLocalizationCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			localizedEntryLocalizationCacheModel.content = null;
		}

		return localizedEntryLocalizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LocalizedEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LocalizedEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LocalizedEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(LocalizedEntryLocalization)this);

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
		Map<String, Function<LocalizedEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LocalizedEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LocalizedEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(LocalizedEntryLocalization)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, LocalizedEntryLocalization>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _localizedEntryLocalizationId;
	private long _localizedEntryId;
	private String _languageId;
	private String _title;
	private String _content;

	public <T> T getColumnValue(String columnName) {
		Function<LocalizedEntryLocalization, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((LocalizedEntryLocalization)this);
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
			"localizedEntryLocalizationId", _localizedEntryLocalizationId);
		_columnOriginalValues.put("localizedEntryId", _localizedEntryId);
		_columnOriginalValues.put("languageId", _languageId);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("localizedEntryLocalizationId", 2L);

		columnBitmasks.put("localizedEntryId", 4L);

		columnBitmasks.put("languageId", 8L);

		columnBitmasks.put("title", 16L);

		columnBitmasks.put("content", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private LocalizedEntryLocalization _escapedModel;

}