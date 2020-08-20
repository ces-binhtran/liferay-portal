/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRoleModel;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRoleSoap;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.ProxyUtil;

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
 * The base model implementation for the ContactAccountRole service. Represents a row in the &quot;Koroneiki_ContactAccountRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ContactAccountRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ContactAccountRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRoleImpl
 * @generated
 */
@JSON(strict = true)
public class ContactAccountRoleModelImpl
	extends BaseModelImpl<ContactAccountRole>
	implements ContactAccountRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a contact account role model instance should use the <code>ContactAccountRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ContactAccountRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"contactId", Types.BIGINT},
		{"accountId", Types.BIGINT}, {"contactRoleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contactId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contactRoleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ContactAccountRole (mvccVersion LONG default 0 not null,contactId LONG not null,accountId LONG not null,contactRoleId LONG not null,primary key (contactId, accountId, contactRoleId))";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ContactAccountRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY contactAccountRole.id.contactId ASC, contactAccountRole.id.accountId ASC, contactAccountRole.id.contactRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ContactAccountRole.contactId ASC, Koroneiki_ContactAccountRole.accountId ASC, Koroneiki_ContactAccountRole.contactRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long CONTACTID_COLUMN_BITMASK = 2L;

	public static final long CONTACTROLEID_COLUMN_BITMASK = 4L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ContactAccountRole toModel(ContactAccountRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ContactAccountRole model = new ContactAccountRoleImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setContactId(soapModel.getContactId());
		model.setAccountId(soapModel.getAccountId());
		model.setContactRoleId(soapModel.getContactRoleId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ContactAccountRole> toModels(
		ContactAccountRoleSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ContactAccountRole> models = new ArrayList<ContactAccountRole>(
			soapModels.length);

		for (ContactAccountRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ContactAccountRoleModelImpl() {
	}

	@Override
	public ContactAccountRolePK getPrimaryKey() {
		return new ContactAccountRolePK(_contactId, _accountId, _contactRoleId);
	}

	@Override
	public void setPrimaryKey(ContactAccountRolePK primaryKey) {
		setContactId(primaryKey.contactId);
		setAccountId(primaryKey.accountId);
		setContactRoleId(primaryKey.contactRoleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ContactAccountRolePK(_contactId, _accountId, _contactRoleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ContactAccountRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return ContactAccountRole.class;
	}

	@Override
	public String getModelClassName() {
		return ContactAccountRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ContactAccountRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ContactAccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactAccountRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ContactAccountRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ContactAccountRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ContactAccountRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ContactAccountRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ContactAccountRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ContactAccountRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ContactAccountRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ContactAccountRole.class.getClassLoader(), ContactAccountRole.class,
			ModelWrapper.class);

		try {
			Constructor<ContactAccountRole> constructor =
				(Constructor<ContactAccountRole>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ContactAccountRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ContactAccountRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ContactAccountRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<ContactAccountRole, Object>>();
		Map<String, BiConsumer<ContactAccountRole, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<ContactAccountRole, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ContactAccountRole::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ContactAccountRole, Long>)
				ContactAccountRole::setMvccVersion);
		attributeGetterFunctions.put(
			"contactId", ContactAccountRole::getContactId);
		attributeSetterBiConsumers.put(
			"contactId",
			(BiConsumer<ContactAccountRole, Long>)
				ContactAccountRole::setContactId);
		attributeGetterFunctions.put(
			"accountId", ContactAccountRole::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId",
			(BiConsumer<ContactAccountRole, Long>)
				ContactAccountRole::setAccountId);
		attributeGetterFunctions.put(
			"contactRoleId", ContactAccountRole::getContactRoleId);
		attributeSetterBiConsumers.put(
			"contactRoleId",
			(BiConsumer<ContactAccountRole, Long>)
				ContactAccountRole::setContactRoleId);

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
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getContactId() {
		return _contactId;
	}

	@Override
	public void setContactId(long contactId) {
		_columnBitmask |= CONTACTID_COLUMN_BITMASK;

		if (!_setOriginalContactId) {
			_setOriginalContactId = true;

			_originalContactId = _contactId;
		}

		_contactId = contactId;
	}

	public long getOriginalContactId() {
		return _originalContactId;
	}

	@JSON
	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_columnBitmask |= ACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalAccountId) {
			_setOriginalAccountId = true;

			_originalAccountId = _accountId;
		}

		_accountId = accountId;
	}

	public long getOriginalAccountId() {
		return _originalAccountId;
	}

	@JSON
	@Override
	public long getContactRoleId() {
		return _contactRoleId;
	}

	@Override
	public void setContactRoleId(long contactRoleId) {
		_columnBitmask |= CONTACTROLEID_COLUMN_BITMASK;

		if (!_setOriginalContactRoleId) {
			_setOriginalContactRoleId = true;

			_originalContactRoleId = _contactRoleId;
		}

		_contactRoleId = contactRoleId;
	}

	public long getOriginalContactRoleId() {
		return _originalContactRoleId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ContactAccountRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ContactAccountRole>
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
		ContactAccountRoleImpl contactAccountRoleImpl =
			new ContactAccountRoleImpl();

		contactAccountRoleImpl.setMvccVersion(getMvccVersion());
		contactAccountRoleImpl.setContactId(getContactId());
		contactAccountRoleImpl.setAccountId(getAccountId());
		contactAccountRoleImpl.setContactRoleId(getContactRoleId());

		contactAccountRoleImpl.resetOriginalValues();

		return contactAccountRoleImpl;
	}

	@Override
	public int compareTo(ContactAccountRole contactAccountRole) {
		ContactAccountRolePK primaryKey = contactAccountRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountRole)) {
			return false;
		}

		ContactAccountRole contactAccountRole = (ContactAccountRole)object;

		ContactAccountRolePK primaryKey = contactAccountRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		_originalContactId = _contactId;

		_setOriginalContactId = false;

		_originalAccountId = _accountId;

		_setOriginalAccountId = false;

		_originalContactRoleId = _contactRoleId;

		_setOriginalContactRoleId = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<ContactAccountRole> toCacheModel() {
		ContactAccountRoleCacheModel contactAccountRoleCacheModel =
			new ContactAccountRoleCacheModel();

		contactAccountRoleCacheModel.contactAccountRolePK = getPrimaryKey();

		contactAccountRoleCacheModel.mvccVersion = getMvccVersion();

		contactAccountRoleCacheModel.contactId = getContactId();

		contactAccountRoleCacheModel.accountId = getAccountId();

		contactAccountRoleCacheModel.contactRoleId = getContactRoleId();

		return contactAccountRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ContactAccountRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ContactAccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactAccountRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ContactAccountRole)this));
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
		Map<String, Function<ContactAccountRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ContactAccountRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ContactAccountRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ContactAccountRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ContactAccountRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _contactId;
	private long _originalContactId;
	private boolean _setOriginalContactId;
	private long _accountId;
	private long _originalAccountId;
	private boolean _setOriginalAccountId;
	private long _contactRoleId;
	private long _originalContactRoleId;
	private boolean _setOriginalContactRoleId;
	private long _columnBitmask;
	private ContactAccountRole _escapedModel;

}