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

package com.liferay.sync.model.impl;

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
import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.model.SyncDLFileVersionDiffModel;

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
 * The base model implementation for the SyncDLFileVersionDiff service. Represents a row in the &quot;SyncDLFileVersionDiff&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SyncDLFileVersionDiffModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SyncDLFileVersionDiffImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffImpl
 * @generated
 */
public class SyncDLFileVersionDiffModelImpl
	extends BaseModelImpl<SyncDLFileVersionDiff>
	implements SyncDLFileVersionDiffModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sync dl file version diff model instance should use the <code>SyncDLFileVersionDiff</code> interface instead.
	 */
	public static final String TABLE_NAME = "SyncDLFileVersionDiff";

	public static final Object[][] TABLE_COLUMNS = {
		{"syncDLFileVersionDiffId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT}, {"sourceFileVersionId", Types.BIGINT},
		{"targetFileVersionId", Types.BIGINT},
		{"dataFileEntryId", Types.BIGINT}, {"size_", Types.BIGINT},
		{"expirationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("syncDLFileVersionDiffId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sourceFileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("targetFileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("dataFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("size_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SyncDLFileVersionDiff (syncDLFileVersionDiffId LONG not null primary key,companyId LONG,fileEntryId LONG,sourceFileVersionId LONG,targetFileVersionId LONG,dataFileEntryId LONG,size_ LONG,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table SyncDLFileVersionDiff";

	public static final String ORDER_BY_JPQL =
		" ORDER BY syncDLFileVersionDiff.syncDLFileVersionDiffId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SyncDLFileVersionDiff.syncDLFileVersionDiffId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXPIRATIONDATE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FILEENTRYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SOURCEFILEVERSIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TARGETFILEVERSIONID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SYNCDLFILEVERSIONDIFFID_COLUMN_BITMASK = 16L;

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

	public SyncDLFileVersionDiffModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSyncDLFileVersionDiffId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDLFileVersionDiff.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDLFileVersionDiff.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SyncDLFileVersionDiff, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDLFileVersionDiff, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SyncDLFileVersionDiff)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SyncDLFileVersionDiff, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SyncDLFileVersionDiff, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SyncDLFileVersionDiff)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SyncDLFileVersionDiff, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SyncDLFileVersionDiff, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SyncDLFileVersionDiff>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SyncDLFileVersionDiff.class.getClassLoader(),
			SyncDLFileVersionDiff.class, ModelWrapper.class);

		try {
			Constructor<SyncDLFileVersionDiff> constructor =
				(Constructor<SyncDLFileVersionDiff>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SyncDLFileVersionDiff, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SyncDLFileVersionDiff, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SyncDLFileVersionDiff, Object>>();
		Map<String, BiConsumer<SyncDLFileVersionDiff, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<SyncDLFileVersionDiff, ?>>();

		attributeGetterFunctions.put(
			"syncDLFileVersionDiffId",
			SyncDLFileVersionDiff::getSyncDLFileVersionDiffId);
		attributeSetterBiConsumers.put(
			"syncDLFileVersionDiffId",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setSyncDLFileVersionDiffId);
		attributeGetterFunctions.put(
			"companyId", SyncDLFileVersionDiff::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setCompanyId);
		attributeGetterFunctions.put(
			"fileEntryId", SyncDLFileVersionDiff::getFileEntryId);
		attributeSetterBiConsumers.put(
			"fileEntryId",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setFileEntryId);
		attributeGetterFunctions.put(
			"sourceFileVersionId",
			SyncDLFileVersionDiff::getSourceFileVersionId);
		attributeSetterBiConsumers.put(
			"sourceFileVersionId",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setSourceFileVersionId);
		attributeGetterFunctions.put(
			"targetFileVersionId",
			SyncDLFileVersionDiff::getTargetFileVersionId);
		attributeSetterBiConsumers.put(
			"targetFileVersionId",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setTargetFileVersionId);
		attributeGetterFunctions.put(
			"dataFileEntryId", SyncDLFileVersionDiff::getDataFileEntryId);
		attributeSetterBiConsumers.put(
			"dataFileEntryId",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setDataFileEntryId);
		attributeGetterFunctions.put("size", SyncDLFileVersionDiff::getSize);
		attributeSetterBiConsumers.put(
			"size",
			(BiConsumer<SyncDLFileVersionDiff, Long>)
				SyncDLFileVersionDiff::setSize);
		attributeGetterFunctions.put(
			"expirationDate", SyncDLFileVersionDiff::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate",
			(BiConsumer<SyncDLFileVersionDiff, Date>)
				SyncDLFileVersionDiff::setExpirationDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getSyncDLFileVersionDiffId() {
		return _syncDLFileVersionDiffId;
	}

	@Override
	public void setSyncDLFileVersionDiffId(long syncDLFileVersionDiffId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_syncDLFileVersionDiffId = syncDLFileVersionDiffId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalFileEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("fileEntryId"));
	}

	@Override
	public long getSourceFileVersionId() {
		return _sourceFileVersionId;
	}

	@Override
	public void setSourceFileVersionId(long sourceFileVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sourceFileVersionId = sourceFileVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalSourceFileVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("sourceFileVersionId"));
	}

	@Override
	public long getTargetFileVersionId() {
		return _targetFileVersionId;
	}

	@Override
	public void setTargetFileVersionId(long targetFileVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_targetFileVersionId = targetFileVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalTargetFileVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("targetFileVersionId"));
	}

	@Override
	public long getDataFileEntryId() {
		return _dataFileEntryId;
	}

	@Override
	public void setDataFileEntryId(long dataFileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dataFileEntryId = dataFileEntryId;
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_size = size;
	}

	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expirationDate = expirationDate;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Date getOriginalExpirationDate() {
		return getColumnOriginalValue("expirationDate");
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
			getCompanyId(), SyncDLFileVersionDiff.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SyncDLFileVersionDiff toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SyncDLFileVersionDiff>
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
		SyncDLFileVersionDiffImpl syncDLFileVersionDiffImpl =
			new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiffImpl.setSyncDLFileVersionDiffId(
			getSyncDLFileVersionDiffId());
		syncDLFileVersionDiffImpl.setCompanyId(getCompanyId());
		syncDLFileVersionDiffImpl.setFileEntryId(getFileEntryId());
		syncDLFileVersionDiffImpl.setSourceFileVersionId(
			getSourceFileVersionId());
		syncDLFileVersionDiffImpl.setTargetFileVersionId(
			getTargetFileVersionId());
		syncDLFileVersionDiffImpl.setDataFileEntryId(getDataFileEntryId());
		syncDLFileVersionDiffImpl.setSize(getSize());
		syncDLFileVersionDiffImpl.setExpirationDate(getExpirationDate());

		syncDLFileVersionDiffImpl.resetOriginalValues();

		return syncDLFileVersionDiffImpl;
	}

	@Override
	public SyncDLFileVersionDiff cloneWithOriginalValues() {
		SyncDLFileVersionDiffImpl syncDLFileVersionDiffImpl =
			new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiffImpl.setSyncDLFileVersionDiffId(
			this.<Long>getColumnOriginalValue("syncDLFileVersionDiffId"));
		syncDLFileVersionDiffImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		syncDLFileVersionDiffImpl.setFileEntryId(
			this.<Long>getColumnOriginalValue("fileEntryId"));
		syncDLFileVersionDiffImpl.setSourceFileVersionId(
			this.<Long>getColumnOriginalValue("sourceFileVersionId"));
		syncDLFileVersionDiffImpl.setTargetFileVersionId(
			this.<Long>getColumnOriginalValue("targetFileVersionId"));
		syncDLFileVersionDiffImpl.setDataFileEntryId(
			this.<Long>getColumnOriginalValue("dataFileEntryId"));
		syncDLFileVersionDiffImpl.setSize(
			this.<Long>getColumnOriginalValue("size_"));
		syncDLFileVersionDiffImpl.setExpirationDate(
			this.<Date>getColumnOriginalValue("expirationDate"));

		return syncDLFileVersionDiffImpl;
	}

	@Override
	public int compareTo(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		long primaryKey = syncDLFileVersionDiff.getPrimaryKey();

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

		if (!(object instanceof SyncDLFileVersionDiff)) {
			return false;
		}

		SyncDLFileVersionDiff syncDLFileVersionDiff =
			(SyncDLFileVersionDiff)object;

		long primaryKey = syncDLFileVersionDiff.getPrimaryKey();

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
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SyncDLFileVersionDiff> toCacheModel() {
		SyncDLFileVersionDiffCacheModel syncDLFileVersionDiffCacheModel =
			new SyncDLFileVersionDiffCacheModel();

		syncDLFileVersionDiffCacheModel.syncDLFileVersionDiffId =
			getSyncDLFileVersionDiffId();

		syncDLFileVersionDiffCacheModel.companyId = getCompanyId();

		syncDLFileVersionDiffCacheModel.fileEntryId = getFileEntryId();

		syncDLFileVersionDiffCacheModel.sourceFileVersionId =
			getSourceFileVersionId();

		syncDLFileVersionDiffCacheModel.targetFileVersionId =
			getTargetFileVersionId();

		syncDLFileVersionDiffCacheModel.dataFileEntryId = getDataFileEntryId();

		syncDLFileVersionDiffCacheModel.size = getSize();

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			syncDLFileVersionDiffCacheModel.expirationDate =
				expirationDate.getTime();
		}
		else {
			syncDLFileVersionDiffCacheModel.expirationDate = Long.MIN_VALUE;
		}

		return syncDLFileVersionDiffCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SyncDLFileVersionDiff, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDLFileVersionDiff, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(SyncDLFileVersionDiff)this);

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
		Map<String, Function<SyncDLFileVersionDiff, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SyncDLFileVersionDiff, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SyncDLFileVersionDiff, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((SyncDLFileVersionDiff)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SyncDLFileVersionDiff>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _syncDLFileVersionDiffId;
	private long _companyId;
	private long _fileEntryId;
	private long _sourceFileVersionId;
	private long _targetFileVersionId;
	private long _dataFileEntryId;
	private long _size;
	private Date _expirationDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<SyncDLFileVersionDiff, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((SyncDLFileVersionDiff)this);
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

		_columnOriginalValues.put(
			"syncDLFileVersionDiffId", _syncDLFileVersionDiffId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("fileEntryId", _fileEntryId);
		_columnOriginalValues.put("sourceFileVersionId", _sourceFileVersionId);
		_columnOriginalValues.put("targetFileVersionId", _targetFileVersionId);
		_columnOriginalValues.put("dataFileEntryId", _dataFileEntryId);
		_columnOriginalValues.put("size_", _size);
		_columnOriginalValues.put("expirationDate", _expirationDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("size_", "size");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("syncDLFileVersionDiffId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("fileEntryId", 4L);

		columnBitmasks.put("sourceFileVersionId", 8L);

		columnBitmasks.put("targetFileVersionId", 16L);

		columnBitmasks.put("dataFileEntryId", 32L);

		columnBitmasks.put("size_", 64L);

		columnBitmasks.put("expirationDate", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private SyncDLFileVersionDiff _escapedModel;

}