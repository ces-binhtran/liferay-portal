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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.OrgLabor;
import com.liferay.portal.kernel.model.OrgLaborModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the OrgLabor service. Represents a row in the &quot;OrgLabor&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OrgLaborModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrgLaborImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgLaborImpl
 * @generated
 */
@JSON(strict = true)
public class OrgLaborModelImpl
	extends BaseModelImpl<OrgLabor> implements OrgLaborModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a org labor model instance should use the <code>OrgLabor</code> interface instead.
	 */
	public static final String TABLE_NAME = "OrgLabor";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"orgLaborId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"organizationId", Types.BIGINT},
		{"typeId", Types.BIGINT}, {"sunOpen", Types.INTEGER},
		{"sunClose", Types.INTEGER}, {"monOpen", Types.INTEGER},
		{"monClose", Types.INTEGER}, {"tueOpen", Types.INTEGER},
		{"tueClose", Types.INTEGER}, {"wedOpen", Types.INTEGER},
		{"wedClose", Types.INTEGER}, {"thuOpen", Types.INTEGER},
		{"thuClose", Types.INTEGER}, {"friOpen", Types.INTEGER},
		{"friClose", Types.INTEGER}, {"satOpen", Types.INTEGER},
		{"satClose", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("orgLaborId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("organizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("typeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sunOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("sunClose", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("monOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("monClose", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("tueOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("tueClose", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("wedOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("wedClose", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("thuOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("thuClose", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("friOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("friClose", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("satOpen", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("satClose", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OrgLabor (mvccVersion LONG default 0 not null,orgLaborId LONG not null primary key,companyId LONG,organizationId LONG,typeId LONG,sunOpen INTEGER,sunClose INTEGER,monOpen INTEGER,monClose INTEGER,tueOpen INTEGER,tueClose INTEGER,wedOpen INTEGER,wedClose INTEGER,thuOpen INTEGER,thuClose INTEGER,friOpen INTEGER,friClose INTEGER,satOpen INTEGER,satClose INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table OrgLabor";

	public static final String ORDER_BY_JPQL =
		" ORDER BY orgLabor.organizationId ASC, orgLabor.typeId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OrgLabor.organizationId ASC, OrgLabor.typeId ASC";

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
	public static final long ORGANIZATIONID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long TYPEID_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.OrgLabor"));

	public OrgLaborModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _orgLaborId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrgLaborId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _orgLaborId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OrgLabor.class;
	}

	@Override
	public String getModelClassName() {
		return OrgLabor.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OrgLabor, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OrgLabor, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgLabor, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((OrgLabor)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OrgLabor, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OrgLabor, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OrgLabor)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OrgLabor, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OrgLabor, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OrgLabor>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OrgLabor.class.getClassLoader(), OrgLabor.class,
			ModelWrapper.class);

		try {
			Constructor<OrgLabor> constructor =
				(Constructor<OrgLabor>)proxyClass.getConstructor(
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

	private static final Map<String, Function<OrgLabor, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OrgLabor, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OrgLabor, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<OrgLabor, Object>>();
		Map<String, BiConsumer<OrgLabor, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OrgLabor, ?>>();

		attributeGetterFunctions.put("mvccVersion", OrgLabor::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<OrgLabor, Long>)OrgLabor::setMvccVersion);
		attributeGetterFunctions.put("orgLaborId", OrgLabor::getOrgLaborId);
		attributeSetterBiConsumers.put(
			"orgLaborId", (BiConsumer<OrgLabor, Long>)OrgLabor::setOrgLaborId);
		attributeGetterFunctions.put("companyId", OrgLabor::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<OrgLabor, Long>)OrgLabor::setCompanyId);
		attributeGetterFunctions.put(
			"organizationId", OrgLabor::getOrganizationId);
		attributeSetterBiConsumers.put(
			"organizationId",
			(BiConsumer<OrgLabor, Long>)OrgLabor::setOrganizationId);
		attributeGetterFunctions.put("typeId", OrgLabor::getTypeId);
		attributeSetterBiConsumers.put(
			"typeId", (BiConsumer<OrgLabor, Long>)OrgLabor::setTypeId);
		attributeGetterFunctions.put("sunOpen", OrgLabor::getSunOpen);
		attributeSetterBiConsumers.put(
			"sunOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setSunOpen);
		attributeGetterFunctions.put("sunClose", OrgLabor::getSunClose);
		attributeSetterBiConsumers.put(
			"sunClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setSunClose);
		attributeGetterFunctions.put("monOpen", OrgLabor::getMonOpen);
		attributeSetterBiConsumers.put(
			"monOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setMonOpen);
		attributeGetterFunctions.put("monClose", OrgLabor::getMonClose);
		attributeSetterBiConsumers.put(
			"monClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setMonClose);
		attributeGetterFunctions.put("tueOpen", OrgLabor::getTueOpen);
		attributeSetterBiConsumers.put(
			"tueOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setTueOpen);
		attributeGetterFunctions.put("tueClose", OrgLabor::getTueClose);
		attributeSetterBiConsumers.put(
			"tueClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setTueClose);
		attributeGetterFunctions.put("wedOpen", OrgLabor::getWedOpen);
		attributeSetterBiConsumers.put(
			"wedOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setWedOpen);
		attributeGetterFunctions.put("wedClose", OrgLabor::getWedClose);
		attributeSetterBiConsumers.put(
			"wedClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setWedClose);
		attributeGetterFunctions.put("thuOpen", OrgLabor::getThuOpen);
		attributeSetterBiConsumers.put(
			"thuOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setThuOpen);
		attributeGetterFunctions.put("thuClose", OrgLabor::getThuClose);
		attributeSetterBiConsumers.put(
			"thuClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setThuClose);
		attributeGetterFunctions.put("friOpen", OrgLabor::getFriOpen);
		attributeSetterBiConsumers.put(
			"friOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setFriOpen);
		attributeGetterFunctions.put("friClose", OrgLabor::getFriClose);
		attributeSetterBiConsumers.put(
			"friClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setFriClose);
		attributeGetterFunctions.put("satOpen", OrgLabor::getSatOpen);
		attributeSetterBiConsumers.put(
			"satOpen", (BiConsumer<OrgLabor, Integer>)OrgLabor::setSatOpen);
		attributeGetterFunctions.put("satClose", OrgLabor::getSatClose);
		attributeSetterBiConsumers.put(
			"satClose", (BiConsumer<OrgLabor, Integer>)OrgLabor::setSatClose);

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
	public long getOrgLaborId() {
		return _orgLaborId;
	}

	@Override
	public void setOrgLaborId(long orgLaborId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_orgLaborId = orgLaborId;
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
	public long getOrganizationId() {
		return _organizationId;
	}

	@Override
	public void setOrganizationId(long organizationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_organizationId = organizationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOrganizationId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("organizationId"));
	}

	@JSON
	@Override
	public long getTypeId() {
		return _typeId;
	}

	@Override
	public void setTypeId(long typeId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_typeId = typeId;
	}

	@JSON
	@Override
	public int getSunOpen() {
		return _sunOpen;
	}

	@Override
	public void setSunOpen(int sunOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sunOpen = sunOpen;
	}

	@JSON
	@Override
	public int getSunClose() {
		return _sunClose;
	}

	@Override
	public void setSunClose(int sunClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sunClose = sunClose;
	}

	@JSON
	@Override
	public int getMonOpen() {
		return _monOpen;
	}

	@Override
	public void setMonOpen(int monOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_monOpen = monOpen;
	}

	@JSON
	@Override
	public int getMonClose() {
		return _monClose;
	}

	@Override
	public void setMonClose(int monClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_monClose = monClose;
	}

	@JSON
	@Override
	public int getTueOpen() {
		return _tueOpen;
	}

	@Override
	public void setTueOpen(int tueOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tueOpen = tueOpen;
	}

	@JSON
	@Override
	public int getTueClose() {
		return _tueClose;
	}

	@Override
	public void setTueClose(int tueClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tueClose = tueClose;
	}

	@JSON
	@Override
	public int getWedOpen() {
		return _wedOpen;
	}

	@Override
	public void setWedOpen(int wedOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_wedOpen = wedOpen;
	}

	@JSON
	@Override
	public int getWedClose() {
		return _wedClose;
	}

	@Override
	public void setWedClose(int wedClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_wedClose = wedClose;
	}

	@JSON
	@Override
	public int getThuOpen() {
		return _thuOpen;
	}

	@Override
	public void setThuOpen(int thuOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_thuOpen = thuOpen;
	}

	@JSON
	@Override
	public int getThuClose() {
		return _thuClose;
	}

	@Override
	public void setThuClose(int thuClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_thuClose = thuClose;
	}

	@JSON
	@Override
	public int getFriOpen() {
		return _friOpen;
	}

	@Override
	public void setFriOpen(int friOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friOpen = friOpen;
	}

	@JSON
	@Override
	public int getFriClose() {
		return _friClose;
	}

	@Override
	public void setFriClose(int friClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friClose = friClose;
	}

	@JSON
	@Override
	public int getSatOpen() {
		return _satOpen;
	}

	@Override
	public void setSatOpen(int satOpen) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_satOpen = satOpen;
	}

	@JSON
	@Override
	public int getSatClose() {
		return _satClose;
	}

	@Override
	public void setSatClose(int satClose) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_satClose = satClose;
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
			getCompanyId(), OrgLabor.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OrgLabor toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OrgLabor>
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
		OrgLaborImpl orgLaborImpl = new OrgLaborImpl();

		orgLaborImpl.setMvccVersion(getMvccVersion());
		orgLaborImpl.setOrgLaborId(getOrgLaborId());
		orgLaborImpl.setCompanyId(getCompanyId());
		orgLaborImpl.setOrganizationId(getOrganizationId());
		orgLaborImpl.setTypeId(getTypeId());
		orgLaborImpl.setSunOpen(getSunOpen());
		orgLaborImpl.setSunClose(getSunClose());
		orgLaborImpl.setMonOpen(getMonOpen());
		orgLaborImpl.setMonClose(getMonClose());
		orgLaborImpl.setTueOpen(getTueOpen());
		orgLaborImpl.setTueClose(getTueClose());
		orgLaborImpl.setWedOpen(getWedOpen());
		orgLaborImpl.setWedClose(getWedClose());
		orgLaborImpl.setThuOpen(getThuOpen());
		orgLaborImpl.setThuClose(getThuClose());
		orgLaborImpl.setFriOpen(getFriOpen());
		orgLaborImpl.setFriClose(getFriClose());
		orgLaborImpl.setSatOpen(getSatOpen());
		orgLaborImpl.setSatClose(getSatClose());

		orgLaborImpl.resetOriginalValues();

		return orgLaborImpl;
	}

	@Override
	public OrgLabor cloneWithOriginalValues() {
		OrgLaborImpl orgLaborImpl = new OrgLaborImpl();

		orgLaborImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		orgLaborImpl.setOrgLaborId(
			this.<Long>getColumnOriginalValue("orgLaborId"));
		orgLaborImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		orgLaborImpl.setOrganizationId(
			this.<Long>getColumnOriginalValue("organizationId"));
		orgLaborImpl.setTypeId(this.<Long>getColumnOriginalValue("typeId"));
		orgLaborImpl.setSunOpen(
			this.<Integer>getColumnOriginalValue("sunOpen"));
		orgLaborImpl.setSunClose(
			this.<Integer>getColumnOriginalValue("sunClose"));
		orgLaborImpl.setMonOpen(
			this.<Integer>getColumnOriginalValue("monOpen"));
		orgLaborImpl.setMonClose(
			this.<Integer>getColumnOriginalValue("monClose"));
		orgLaborImpl.setTueOpen(
			this.<Integer>getColumnOriginalValue("tueOpen"));
		orgLaborImpl.setTueClose(
			this.<Integer>getColumnOriginalValue("tueClose"));
		orgLaborImpl.setWedOpen(
			this.<Integer>getColumnOriginalValue("wedOpen"));
		orgLaborImpl.setWedClose(
			this.<Integer>getColumnOriginalValue("wedClose"));
		orgLaborImpl.setThuOpen(
			this.<Integer>getColumnOriginalValue("thuOpen"));
		orgLaborImpl.setThuClose(
			this.<Integer>getColumnOriginalValue("thuClose"));
		orgLaborImpl.setFriOpen(
			this.<Integer>getColumnOriginalValue("friOpen"));
		orgLaborImpl.setFriClose(
			this.<Integer>getColumnOriginalValue("friClose"));
		orgLaborImpl.setSatOpen(
			this.<Integer>getColumnOriginalValue("satOpen"));
		orgLaborImpl.setSatClose(
			this.<Integer>getColumnOriginalValue("satClose"));

		return orgLaborImpl;
	}

	@Override
	public int compareTo(OrgLabor orgLabor) {
		int value = 0;

		if (getOrganizationId() < orgLabor.getOrganizationId()) {
			value = -1;
		}
		else if (getOrganizationId() > orgLabor.getOrganizationId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getTypeId() < orgLabor.getTypeId()) {
			value = -1;
		}
		else if (getTypeId() > orgLabor.getTypeId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof OrgLabor)) {
			return false;
		}

		OrgLabor orgLabor = (OrgLabor)object;

		long primaryKey = orgLabor.getPrimaryKey();

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
	public CacheModel<OrgLabor> toCacheModel() {
		OrgLaborCacheModel orgLaborCacheModel = new OrgLaborCacheModel();

		orgLaborCacheModel.mvccVersion = getMvccVersion();

		orgLaborCacheModel.orgLaborId = getOrgLaborId();

		orgLaborCacheModel.companyId = getCompanyId();

		orgLaborCacheModel.organizationId = getOrganizationId();

		orgLaborCacheModel.typeId = getTypeId();

		orgLaborCacheModel.sunOpen = getSunOpen();

		orgLaborCacheModel.sunClose = getSunClose();

		orgLaborCacheModel.monOpen = getMonOpen();

		orgLaborCacheModel.monClose = getMonClose();

		orgLaborCacheModel.tueOpen = getTueOpen();

		orgLaborCacheModel.tueClose = getTueClose();

		orgLaborCacheModel.wedOpen = getWedOpen();

		orgLaborCacheModel.wedClose = getWedClose();

		orgLaborCacheModel.thuOpen = getThuOpen();

		orgLaborCacheModel.thuClose = getThuClose();

		orgLaborCacheModel.friOpen = getFriOpen();

		orgLaborCacheModel.friClose = getFriClose();

		orgLaborCacheModel.satOpen = getSatOpen();

		orgLaborCacheModel.satClose = getSatClose();

		return orgLaborCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OrgLabor, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OrgLabor, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgLabor, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((OrgLabor)this);

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
		Map<String, Function<OrgLabor, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OrgLabor, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OrgLabor, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OrgLabor)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OrgLabor>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _orgLaborId;
	private long _companyId;
	private long _organizationId;
	private long _typeId;
	private int _sunOpen;
	private int _sunClose;
	private int _monOpen;
	private int _monClose;
	private int _tueOpen;
	private int _tueClose;
	private int _wedOpen;
	private int _wedClose;
	private int _thuOpen;
	private int _thuClose;
	private int _friOpen;
	private int _friClose;
	private int _satOpen;
	private int _satClose;

	public <T> T getColumnValue(String columnName) {
		Function<OrgLabor, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OrgLabor)this);
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
		_columnOriginalValues.put("orgLaborId", _orgLaborId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("organizationId", _organizationId);
		_columnOriginalValues.put("typeId", _typeId);
		_columnOriginalValues.put("sunOpen", _sunOpen);
		_columnOriginalValues.put("sunClose", _sunClose);
		_columnOriginalValues.put("monOpen", _monOpen);
		_columnOriginalValues.put("monClose", _monClose);
		_columnOriginalValues.put("tueOpen", _tueOpen);
		_columnOriginalValues.put("tueClose", _tueClose);
		_columnOriginalValues.put("wedOpen", _wedOpen);
		_columnOriginalValues.put("wedClose", _wedClose);
		_columnOriginalValues.put("thuOpen", _thuOpen);
		_columnOriginalValues.put("thuClose", _thuClose);
		_columnOriginalValues.put("friOpen", _friOpen);
		_columnOriginalValues.put("friClose", _friClose);
		_columnOriginalValues.put("satOpen", _satOpen);
		_columnOriginalValues.put("satClose", _satClose);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("orgLaborId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("organizationId", 8L);

		columnBitmasks.put("typeId", 16L);

		columnBitmasks.put("sunOpen", 32L);

		columnBitmasks.put("sunClose", 64L);

		columnBitmasks.put("monOpen", 128L);

		columnBitmasks.put("monClose", 256L);

		columnBitmasks.put("tueOpen", 512L);

		columnBitmasks.put("tueClose", 1024L);

		columnBitmasks.put("wedOpen", 2048L);

		columnBitmasks.put("wedClose", 4096L);

		columnBitmasks.put("thuOpen", 8192L);

		columnBitmasks.put("thuClose", 16384L);

		columnBitmasks.put("friOpen", 32768L);

		columnBitmasks.put("friClose", 65536L);

		columnBitmasks.put("satOpen", 131072L);

		columnBitmasks.put("satClose", 262144L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OrgLabor _escapedModel;

}