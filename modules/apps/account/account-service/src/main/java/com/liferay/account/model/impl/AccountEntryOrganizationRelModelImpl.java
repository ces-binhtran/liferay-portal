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

package com.liferay.account.model.impl;

import com.liferay.account.model.AccountEntryOrganizationRel;
import com.liferay.account.model.AccountEntryOrganizationRelModel;
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
 * The base model implementation for the AccountEntryOrganizationRel service. Represents a row in the &quot;AccountEntryOrganizationRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AccountEntryOrganizationRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountEntryOrganizationRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryOrganizationRelImpl
 * @generated
 */
@JSON(strict = true)
public class AccountEntryOrganizationRelModelImpl
	extends BaseModelImpl<AccountEntryOrganizationRel>
	implements AccountEntryOrganizationRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account entry organization rel model instance should use the <code>AccountEntryOrganizationRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "AccountEntryOrganizationRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"accountEntryOrganizationRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"accountEntryId", Types.BIGINT},
		{"organizationId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountEntryOrganizationRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("organizationId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AccountEntryOrganizationRel (mvccVersion LONG default 0 not null,accountEntryOrganizationRelId LONG not null primary key,companyId LONG,accountEntryId LONG,organizationId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table AccountEntryOrganizationRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY accountEntryOrganizationRel.accountEntryOrganizationRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AccountEntryOrganizationRel.accountEntryOrganizationRelId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACCOUNTENTRYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ORGANIZATIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACCOUNTENTRYORGANIZATIONRELID_COLUMN_BITMASK = 4L;

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

	public AccountEntryOrganizationRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _accountEntryOrganizationRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountEntryOrganizationRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEntryOrganizationRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEntryOrganizationRel.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntryOrganizationRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AccountEntryOrganizationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AccountEntryOrganizationRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountEntryOrganizationRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(AccountEntryOrganizationRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AccountEntryOrganizationRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AccountEntryOrganizationRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AccountEntryOrganizationRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AccountEntryOrganizationRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AccountEntryOrganizationRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AccountEntryOrganizationRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AccountEntryOrganizationRel.class.getClassLoader(),
			AccountEntryOrganizationRel.class, ModelWrapper.class);

		try {
			Constructor<AccountEntryOrganizationRel> constructor =
				(Constructor<AccountEntryOrganizationRel>)
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
		<String, Function<AccountEntryOrganizationRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<AccountEntryOrganizationRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<AccountEntryOrganizationRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<AccountEntryOrganizationRel, Object>>();
		Map<String, BiConsumer<AccountEntryOrganizationRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<AccountEntryOrganizationRel, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AccountEntryOrganizationRel::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AccountEntryOrganizationRel, Long>)
				AccountEntryOrganizationRel::setMvccVersion);
		attributeGetterFunctions.put(
			"accountEntryOrganizationRelId",
			AccountEntryOrganizationRel::getAccountEntryOrganizationRelId);
		attributeSetterBiConsumers.put(
			"accountEntryOrganizationRelId",
			(BiConsumer<AccountEntryOrganizationRel, Long>)
				AccountEntryOrganizationRel::setAccountEntryOrganizationRelId);
		attributeGetterFunctions.put(
			"companyId", AccountEntryOrganizationRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AccountEntryOrganizationRel, Long>)
				AccountEntryOrganizationRel::setCompanyId);
		attributeGetterFunctions.put(
			"accountEntryId", AccountEntryOrganizationRel::getAccountEntryId);
		attributeSetterBiConsumers.put(
			"accountEntryId",
			(BiConsumer<AccountEntryOrganizationRel, Long>)
				AccountEntryOrganizationRel::setAccountEntryId);
		attributeGetterFunctions.put(
			"organizationId", AccountEntryOrganizationRel::getOrganizationId);
		attributeSetterBiConsumers.put(
			"organizationId",
			(BiConsumer<AccountEntryOrganizationRel, Long>)
				AccountEntryOrganizationRel::setOrganizationId);

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
	public long getAccountEntryOrganizationRelId() {
		return _accountEntryOrganizationRelId;
	}

	@Override
	public void setAccountEntryOrganizationRelId(
		long accountEntryOrganizationRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountEntryOrganizationRelId = accountEntryOrganizationRelId;
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
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountEntryId = accountEntryId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalAccountEntryId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("accountEntryId"));
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
			getCompanyId(), AccountEntryOrganizationRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AccountEntryOrganizationRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AccountEntryOrganizationRel>
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
		AccountEntryOrganizationRelImpl accountEntryOrganizationRelImpl =
			new AccountEntryOrganizationRelImpl();

		accountEntryOrganizationRelImpl.setMvccVersion(getMvccVersion());
		accountEntryOrganizationRelImpl.setAccountEntryOrganizationRelId(
			getAccountEntryOrganizationRelId());
		accountEntryOrganizationRelImpl.setCompanyId(getCompanyId());
		accountEntryOrganizationRelImpl.setAccountEntryId(getAccountEntryId());
		accountEntryOrganizationRelImpl.setOrganizationId(getOrganizationId());

		accountEntryOrganizationRelImpl.resetOriginalValues();

		return accountEntryOrganizationRelImpl;
	}

	@Override
	public AccountEntryOrganizationRel cloneWithOriginalValues() {
		AccountEntryOrganizationRelImpl accountEntryOrganizationRelImpl =
			new AccountEntryOrganizationRelImpl();

		accountEntryOrganizationRelImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		accountEntryOrganizationRelImpl.setAccountEntryOrganizationRelId(
			this.<Long>getColumnOriginalValue("accountEntryOrganizationRelId"));
		accountEntryOrganizationRelImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		accountEntryOrganizationRelImpl.setAccountEntryId(
			this.<Long>getColumnOriginalValue("accountEntryId"));
		accountEntryOrganizationRelImpl.setOrganizationId(
			this.<Long>getColumnOriginalValue("organizationId"));

		return accountEntryOrganizationRelImpl;
	}

	@Override
	public int compareTo(
		AccountEntryOrganizationRel accountEntryOrganizationRel) {

		long primaryKey = accountEntryOrganizationRel.getPrimaryKey();

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

		if (!(object instanceof AccountEntryOrganizationRel)) {
			return false;
		}

		AccountEntryOrganizationRel accountEntryOrganizationRel =
			(AccountEntryOrganizationRel)object;

		long primaryKey = accountEntryOrganizationRel.getPrimaryKey();

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
	public CacheModel<AccountEntryOrganizationRel> toCacheModel() {
		AccountEntryOrganizationRelCacheModel
			accountEntryOrganizationRelCacheModel =
				new AccountEntryOrganizationRelCacheModel();

		accountEntryOrganizationRelCacheModel.mvccVersion = getMvccVersion();

		accountEntryOrganizationRelCacheModel.accountEntryOrganizationRelId =
			getAccountEntryOrganizationRelId();

		accountEntryOrganizationRelCacheModel.companyId = getCompanyId();

		accountEntryOrganizationRelCacheModel.accountEntryId =
			getAccountEntryId();

		accountEntryOrganizationRelCacheModel.organizationId =
			getOrganizationId();

		return accountEntryOrganizationRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AccountEntryOrganizationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AccountEntryOrganizationRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountEntryOrganizationRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(AccountEntryOrganizationRel)this);

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
		Map<String, Function<AccountEntryOrganizationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AccountEntryOrganizationRel, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountEntryOrganizationRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(AccountEntryOrganizationRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, AccountEntryOrganizationRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _accountEntryOrganizationRelId;
	private long _companyId;
	private long _accountEntryId;
	private long _organizationId;

	public <T> T getColumnValue(String columnName) {
		Function<AccountEntryOrganizationRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AccountEntryOrganizationRel)this);
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
			"accountEntryOrganizationRelId", _accountEntryOrganizationRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("accountEntryId", _accountEntryId);
		_columnOriginalValues.put("organizationId", _organizationId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("accountEntryOrganizationRelId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("accountEntryId", 8L);

		columnBitmasks.put("organizationId", 16L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AccountEntryOrganizationRel _escapedModel;

}