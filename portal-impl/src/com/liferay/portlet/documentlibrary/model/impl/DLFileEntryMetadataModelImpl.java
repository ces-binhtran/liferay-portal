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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryMetadataModel;
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

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DLFileEntryMetadata service. Represents a row in the &quot;DLFileEntryMetadata&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>DLFileEntryMetadataModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileEntryMetadataImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryMetadataImpl
 * @generated
 */
public class DLFileEntryMetadataModelImpl
	extends BaseModelImpl<DLFileEntryMetadata>
	implements DLFileEntryMetadataModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file entry metadata model instance should use the <code>DLFileEntryMetadata</code> interface instead.
	 */
	public static final String TABLE_NAME = "DLFileEntryMetadata";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"uuid_", Types.VARCHAR}, {"fileEntryMetadataId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"DDMStorageId", Types.BIGINT},
		{"DDMStructureId", Types.BIGINT}, {"fileEntryId", Types.BIGINT},
		{"fileVersionId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryMetadataId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("DDMStorageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("DDMStructureId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileVersionId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DLFileEntryMetadata (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,uuid_ VARCHAR(75) null,fileEntryMetadataId LONG not null,companyId LONG,DDMStorageId LONG,DDMStructureId LONG,fileEntryId LONG,fileVersionId LONG,primary key (fileEntryMetadataId, ctCollectionId))";

	public static final String TABLE_SQL_DROP =
		"drop table DLFileEntryMetadata";

	public static final String ORDER_BY_JPQL =
		" ORDER BY dlFileEntryMetadata.fileEntryMetadataId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DLFileEntryMetadata.fileEntryMetadataId ASC";

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
	public static final long DDMSTRUCTUREID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FILEENTRYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long FILEVERSIONID_COLUMN_BITMASK = 8L;

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
	public static final long FILEENTRYMETADATAID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.document.library.kernel.model.DLFileEntryMetadata"));

	public DLFileEntryMetadataModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryMetadataId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFileEntryMetadataId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileEntryMetadataId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileEntryMetadata.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileEntryMetadata.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DLFileEntryMetadata, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DLFileEntryMetadata, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryMetadata, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DLFileEntryMetadata)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DLFileEntryMetadata, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DLFileEntryMetadata, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DLFileEntryMetadata)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DLFileEntryMetadata, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DLFileEntryMetadata, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DLFileEntryMetadata>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DLFileEntryMetadata.class.getClassLoader(),
			DLFileEntryMetadata.class, ModelWrapper.class);

		try {
			Constructor<DLFileEntryMetadata> constructor =
				(Constructor<DLFileEntryMetadata>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DLFileEntryMetadata, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DLFileEntryMetadata, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DLFileEntryMetadata, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DLFileEntryMetadata, Object>>();
		Map<String, BiConsumer<DLFileEntryMetadata, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<DLFileEntryMetadata, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", DLFileEntryMetadata::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", DLFileEntryMetadata::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setCtCollectionId);
		attributeGetterFunctions.put("uuid", DLFileEntryMetadata::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<DLFileEntryMetadata, String>)
				DLFileEntryMetadata::setUuid);
		attributeGetterFunctions.put(
			"fileEntryMetadataId", DLFileEntryMetadata::getFileEntryMetadataId);
		attributeSetterBiConsumers.put(
			"fileEntryMetadataId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setFileEntryMetadataId);
		attributeGetterFunctions.put(
			"companyId", DLFileEntryMetadata::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setCompanyId);
		attributeGetterFunctions.put(
			"DDMStorageId", DLFileEntryMetadata::getDDMStorageId);
		attributeSetterBiConsumers.put(
			"DDMStorageId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setDDMStorageId);
		attributeGetterFunctions.put(
			"DDMStructureId", DLFileEntryMetadata::getDDMStructureId);
		attributeSetterBiConsumers.put(
			"DDMStructureId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setDDMStructureId);
		attributeGetterFunctions.put(
			"fileEntryId", DLFileEntryMetadata::getFileEntryId);
		attributeSetterBiConsumers.put(
			"fileEntryId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setFileEntryId);
		attributeGetterFunctions.put(
			"fileVersionId", DLFileEntryMetadata::getFileVersionId);
		attributeSetterBiConsumers.put(
			"fileVersionId",
			(BiConsumer<DLFileEntryMetadata, Long>)
				DLFileEntryMetadata::setFileVersionId);

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
	public long getFileEntryMetadataId() {
		return _fileEntryMetadataId;
	}

	@Override
	public void setFileEntryMetadataId(long fileEntryMetadataId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileEntryMetadataId = fileEntryMetadataId;
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
	public long getDDMStorageId() {
		return _DDMStorageId;
	}

	@Override
	public void setDDMStorageId(long DDMStorageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_DDMStorageId = DDMStorageId;
	}

	@Override
	public long getDDMStructureId() {
		return _DDMStructureId;
	}

	@Override
	public void setDDMStructureId(long DDMStructureId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_DDMStructureId = DDMStructureId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalDDMStructureId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("DDMStructureId"));
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
	public long getFileVersionId() {
		return _fileVersionId;
	}

	@Override
	public void setFileVersionId(long fileVersionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_fileVersionId = fileVersionId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalFileVersionId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("fileVersionId"));
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
			getCompanyId(), DLFileEntryMetadata.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLFileEntryMetadata toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DLFileEntryMetadata>
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
		DLFileEntryMetadataImpl dlFileEntryMetadataImpl =
			new DLFileEntryMetadataImpl();

		dlFileEntryMetadataImpl.setMvccVersion(getMvccVersion());
		dlFileEntryMetadataImpl.setCtCollectionId(getCtCollectionId());
		dlFileEntryMetadataImpl.setUuid(getUuid());
		dlFileEntryMetadataImpl.setFileEntryMetadataId(
			getFileEntryMetadataId());
		dlFileEntryMetadataImpl.setCompanyId(getCompanyId());
		dlFileEntryMetadataImpl.setDDMStorageId(getDDMStorageId());
		dlFileEntryMetadataImpl.setDDMStructureId(getDDMStructureId());
		dlFileEntryMetadataImpl.setFileEntryId(getFileEntryId());
		dlFileEntryMetadataImpl.setFileVersionId(getFileVersionId());

		dlFileEntryMetadataImpl.resetOriginalValues();

		return dlFileEntryMetadataImpl;
	}

	@Override
	public int compareTo(DLFileEntryMetadata dlFileEntryMetadata) {
		long primaryKey = dlFileEntryMetadata.getPrimaryKey();

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

		if (!(object instanceof DLFileEntryMetadata)) {
			return false;
		}

		DLFileEntryMetadata dlFileEntryMetadata = (DLFileEntryMetadata)object;

		long primaryKey = dlFileEntryMetadata.getPrimaryKey();

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
	public CacheModel<DLFileEntryMetadata> toCacheModel() {
		DLFileEntryMetadataCacheModel dlFileEntryMetadataCacheModel =
			new DLFileEntryMetadataCacheModel();

		dlFileEntryMetadataCacheModel.mvccVersion = getMvccVersion();

		dlFileEntryMetadataCacheModel.ctCollectionId = getCtCollectionId();

		dlFileEntryMetadataCacheModel.uuid = getUuid();

		String uuid = dlFileEntryMetadataCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dlFileEntryMetadataCacheModel.uuid = null;
		}

		dlFileEntryMetadataCacheModel.fileEntryMetadataId =
			getFileEntryMetadataId();

		dlFileEntryMetadataCacheModel.companyId = getCompanyId();

		dlFileEntryMetadataCacheModel.DDMStorageId = getDDMStorageId();

		dlFileEntryMetadataCacheModel.DDMStructureId = getDDMStructureId();

		dlFileEntryMetadataCacheModel.fileEntryId = getFileEntryId();

		dlFileEntryMetadataCacheModel.fileVersionId = getFileVersionId();

		return dlFileEntryMetadataCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DLFileEntryMetadata, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DLFileEntryMetadata, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryMetadata, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DLFileEntryMetadata)this));
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
		Map<String, Function<DLFileEntryMetadata, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DLFileEntryMetadata, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryMetadata, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DLFileEntryMetadata)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DLFileEntryMetadata>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _fileEntryMetadataId;
	private long _companyId;
	private long _DDMStorageId;
	private long _DDMStructureId;
	private long _fileEntryId;
	private long _fileVersionId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<DLFileEntryMetadata, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((DLFileEntryMetadata)this);
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
		_columnOriginalValues.put("fileEntryMetadataId", _fileEntryMetadataId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("DDMStorageId", _DDMStorageId);
		_columnOriginalValues.put("DDMStructureId", _DDMStructureId);
		_columnOriginalValues.put("fileEntryId", _fileEntryId);
		_columnOriginalValues.put("fileVersionId", _fileVersionId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

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

		columnBitmasks.put("fileEntryMetadataId", 8L);

		columnBitmasks.put("companyId", 16L);

		columnBitmasks.put("DDMStorageId", 32L);

		columnBitmasks.put("DDMStructureId", 64L);

		columnBitmasks.put("fileEntryId", 128L);

		columnBitmasks.put("fileVersionId", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private DLFileEntryMetadata _escapedModel;

}