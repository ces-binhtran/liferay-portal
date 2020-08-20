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

package com.liferay.saml.persistence.model.impl;

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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;
import com.liferay.saml.persistence.model.SamlSpIdpConnectionModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SamlSpIdpConnection service. Represents a row in the &quot;SamlSpIdpConnection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SamlSpIdpConnectionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SamlSpIdpConnectionImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpIdpConnectionImpl
 * @generated
 */
public class SamlSpIdpConnectionModelImpl
	extends BaseModelImpl<SamlSpIdpConnection>
	implements SamlSpIdpConnectionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a saml sp idp connection model instance should use the <code>SamlSpIdpConnection</code> interface instead.
	 */
	public static final String TABLE_NAME = "SamlSpIdpConnection";

	public static final Object[][] TABLE_COLUMNS = {
		{"samlSpIdpConnectionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"samlIdpEntityId", Types.VARCHAR},
		{"assertionSignatureRequired", Types.BOOLEAN},
		{"clockSkew", Types.BIGINT}, {"enabled", Types.BOOLEAN},
		{"forceAuthn", Types.BOOLEAN}, {"ldapImportEnabled", Types.BOOLEAN},
		{"metadataUrl", Types.VARCHAR}, {"metadataXml", Types.CLOB},
		{"metadataUpdatedDate", Types.TIMESTAMP}, {"name", Types.VARCHAR},
		{"nameIdFormat", Types.VARCHAR}, {"signAuthnRequest", Types.BOOLEAN},
		{"userAttributeMappings", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("samlSpIdpConnectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("samlIdpEntityId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assertionSignatureRequired", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("clockSkew", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("enabled", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("forceAuthn", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("ldapImportEnabled", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("metadataUrl", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("metadataXml", Types.CLOB);
		TABLE_COLUMNS_MAP.put("metadataUpdatedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("nameIdFormat", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signAuthnRequest", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("userAttributeMappings", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SamlSpIdpConnection (samlSpIdpConnectionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,samlIdpEntityId VARCHAR(1024) null,assertionSignatureRequired BOOLEAN,clockSkew LONG,enabled BOOLEAN,forceAuthn BOOLEAN,ldapImportEnabled BOOLEAN,metadataUrl VARCHAR(1024) null,metadataXml TEXT null,metadataUpdatedDate DATE null,name VARCHAR(75) null,nameIdFormat VARCHAR(1024) null,signAuthnRequest BOOLEAN,userAttributeMappings STRING null)";

	public static final String TABLE_SQL_DROP =
		"drop table SamlSpIdpConnection";

	public static final String ORDER_BY_JPQL =
		" ORDER BY samlSpIdpConnection.samlSpIdpConnectionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SamlSpIdpConnection.samlSpIdpConnectionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.saml.persistence.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.saml.persistence.model.SamlSpIdpConnection"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.saml.persistence.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.saml.persistence.model.SamlSpIdpConnection"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.saml.persistence.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.saml.persistence.model.SamlSpIdpConnection"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long SAMLIDPENTITYID_COLUMN_BITMASK = 2L;

	public static final long SAMLSPIDPCONNECTIONID_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.saml.persistence.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.saml.persistence.model.SamlSpIdpConnection"));

	public SamlSpIdpConnectionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _samlSpIdpConnectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSamlSpIdpConnectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samlSpIdpConnectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpIdpConnection.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpIdpConnection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SamlSpIdpConnection, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SamlSpIdpConnection, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SamlSpIdpConnection, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SamlSpIdpConnection)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SamlSpIdpConnection, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SamlSpIdpConnection, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SamlSpIdpConnection)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SamlSpIdpConnection, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SamlSpIdpConnection, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SamlSpIdpConnection>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SamlSpIdpConnection.class.getClassLoader(),
			SamlSpIdpConnection.class, ModelWrapper.class);

		try {
			Constructor<SamlSpIdpConnection> constructor =
				(Constructor<SamlSpIdpConnection>)proxyClass.getConstructor(
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

	private static final Map<String, Function<SamlSpIdpConnection, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SamlSpIdpConnection, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SamlSpIdpConnection, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SamlSpIdpConnection, Object>>();
		Map<String, BiConsumer<SamlSpIdpConnection, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SamlSpIdpConnection, ?>>();

		attributeGetterFunctions.put(
			"samlSpIdpConnectionId",
			SamlSpIdpConnection::getSamlSpIdpConnectionId);
		attributeSetterBiConsumers.put(
			"samlSpIdpConnectionId",
			(BiConsumer<SamlSpIdpConnection, Long>)
				SamlSpIdpConnection::setSamlSpIdpConnectionId);
		attributeGetterFunctions.put(
			"companyId", SamlSpIdpConnection::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SamlSpIdpConnection, Long>)
				SamlSpIdpConnection::setCompanyId);
		attributeGetterFunctions.put("userId", SamlSpIdpConnection::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<SamlSpIdpConnection, Long>)
				SamlSpIdpConnection::setUserId);
		attributeGetterFunctions.put(
			"userName", SamlSpIdpConnection::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setUserName);
		attributeGetterFunctions.put(
			"createDate", SamlSpIdpConnection::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SamlSpIdpConnection, Date>)
				SamlSpIdpConnection::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", SamlSpIdpConnection::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<SamlSpIdpConnection, Date>)
				SamlSpIdpConnection::setModifiedDate);
		attributeGetterFunctions.put(
			"samlIdpEntityId", SamlSpIdpConnection::getSamlIdpEntityId);
		attributeSetterBiConsumers.put(
			"samlIdpEntityId",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setSamlIdpEntityId);
		attributeGetterFunctions.put(
			"assertionSignatureRequired",
			SamlSpIdpConnection::getAssertionSignatureRequired);
		attributeSetterBiConsumers.put(
			"assertionSignatureRequired",
			(BiConsumer<SamlSpIdpConnection, Boolean>)
				SamlSpIdpConnection::setAssertionSignatureRequired);
		attributeGetterFunctions.put(
			"clockSkew", SamlSpIdpConnection::getClockSkew);
		attributeSetterBiConsumers.put(
			"clockSkew",
			(BiConsumer<SamlSpIdpConnection, Long>)
				SamlSpIdpConnection::setClockSkew);
		attributeGetterFunctions.put(
			"enabled", SamlSpIdpConnection::getEnabled);
		attributeSetterBiConsumers.put(
			"enabled",
			(BiConsumer<SamlSpIdpConnection, Boolean>)
				SamlSpIdpConnection::setEnabled);
		attributeGetterFunctions.put(
			"forceAuthn", SamlSpIdpConnection::getForceAuthn);
		attributeSetterBiConsumers.put(
			"forceAuthn",
			(BiConsumer<SamlSpIdpConnection, Boolean>)
				SamlSpIdpConnection::setForceAuthn);
		attributeGetterFunctions.put(
			"ldapImportEnabled", SamlSpIdpConnection::getLdapImportEnabled);
		attributeSetterBiConsumers.put(
			"ldapImportEnabled",
			(BiConsumer<SamlSpIdpConnection, Boolean>)
				SamlSpIdpConnection::setLdapImportEnabled);
		attributeGetterFunctions.put(
			"metadataUrl", SamlSpIdpConnection::getMetadataUrl);
		attributeSetterBiConsumers.put(
			"metadataUrl",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setMetadataUrl);
		attributeGetterFunctions.put(
			"metadataXml", SamlSpIdpConnection::getMetadataXml);
		attributeSetterBiConsumers.put(
			"metadataXml",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setMetadataXml);
		attributeGetterFunctions.put(
			"metadataUpdatedDate", SamlSpIdpConnection::getMetadataUpdatedDate);
		attributeSetterBiConsumers.put(
			"metadataUpdatedDate",
			(BiConsumer<SamlSpIdpConnection, Date>)
				SamlSpIdpConnection::setMetadataUpdatedDate);
		attributeGetterFunctions.put("name", SamlSpIdpConnection::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setName);
		attributeGetterFunctions.put(
			"nameIdFormat", SamlSpIdpConnection::getNameIdFormat);
		attributeSetterBiConsumers.put(
			"nameIdFormat",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setNameIdFormat);
		attributeGetterFunctions.put(
			"signAuthnRequest", SamlSpIdpConnection::getSignAuthnRequest);
		attributeSetterBiConsumers.put(
			"signAuthnRequest",
			(BiConsumer<SamlSpIdpConnection, Boolean>)
				SamlSpIdpConnection::setSignAuthnRequest);
		attributeGetterFunctions.put(
			"userAttributeMappings",
			SamlSpIdpConnection::getUserAttributeMappings);
		attributeSetterBiConsumers.put(
			"userAttributeMappings",
			(BiConsumer<SamlSpIdpConnection, String>)
				SamlSpIdpConnection::setUserAttributeMappings);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getSamlSpIdpConnectionId() {
		return _samlSpIdpConnectionId;
	}

	@Override
	public void setSamlSpIdpConnectionId(long samlSpIdpConnectionId) {
		_samlSpIdpConnectionId = samlSpIdpConnectionId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getSamlIdpEntityId() {
		if (_samlIdpEntityId == null) {
			return "";
		}
		else {
			return _samlIdpEntityId;
		}
	}

	@Override
	public void setSamlIdpEntityId(String samlIdpEntityId) {
		_columnBitmask |= SAMLIDPENTITYID_COLUMN_BITMASK;

		if (_originalSamlIdpEntityId == null) {
			_originalSamlIdpEntityId = _samlIdpEntityId;
		}

		_samlIdpEntityId = samlIdpEntityId;
	}

	public String getOriginalSamlIdpEntityId() {
		return GetterUtil.getString(_originalSamlIdpEntityId);
	}

	@Override
	public boolean getAssertionSignatureRequired() {
		return _assertionSignatureRequired;
	}

	@Override
	public boolean isAssertionSignatureRequired() {
		return _assertionSignatureRequired;
	}

	@Override
	public void setAssertionSignatureRequired(
		boolean assertionSignatureRequired) {

		_assertionSignatureRequired = assertionSignatureRequired;
	}

	@Override
	public long getClockSkew() {
		return _clockSkew;
	}

	@Override
	public void setClockSkew(long clockSkew) {
		_clockSkew = clockSkew;
	}

	@Override
	public boolean getEnabled() {
		return _enabled;
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	@Override
	public boolean getForceAuthn() {
		return _forceAuthn;
	}

	@Override
	public boolean isForceAuthn() {
		return _forceAuthn;
	}

	@Override
	public void setForceAuthn(boolean forceAuthn) {
		_forceAuthn = forceAuthn;
	}

	@Override
	public boolean getLdapImportEnabled() {
		return _ldapImportEnabled;
	}

	@Override
	public boolean isLdapImportEnabled() {
		return _ldapImportEnabled;
	}

	@Override
	public void setLdapImportEnabled(boolean ldapImportEnabled) {
		_ldapImportEnabled = ldapImportEnabled;
	}

	@Override
	public String getMetadataUrl() {
		if (_metadataUrl == null) {
			return "";
		}
		else {
			return _metadataUrl;
		}
	}

	@Override
	public void setMetadataUrl(String metadataUrl) {
		_metadataUrl = metadataUrl;
	}

	@Override
	public String getMetadataXml() {
		if (_metadataXml == null) {
			return "";
		}
		else {
			return _metadataXml;
		}
	}

	@Override
	public void setMetadataXml(String metadataXml) {
		_metadataXml = metadataXml;
	}

	@Override
	public Date getMetadataUpdatedDate() {
		return _metadataUpdatedDate;
	}

	@Override
	public void setMetadataUpdatedDate(Date metadataUpdatedDate) {
		_metadataUpdatedDate = metadataUpdatedDate;
	}

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
		_name = name;
	}

	@Override
	public String getNameIdFormat() {
		if (_nameIdFormat == null) {
			return "";
		}
		else {
			return _nameIdFormat;
		}
	}

	@Override
	public void setNameIdFormat(String nameIdFormat) {
		_nameIdFormat = nameIdFormat;
	}

	@Override
	public boolean getSignAuthnRequest() {
		return _signAuthnRequest;
	}

	@Override
	public boolean isSignAuthnRequest() {
		return _signAuthnRequest;
	}

	@Override
	public void setSignAuthnRequest(boolean signAuthnRequest) {
		_signAuthnRequest = signAuthnRequest;
	}

	@Override
	public String getUserAttributeMappings() {
		if (_userAttributeMappings == null) {
			return "";
		}
		else {
			return _userAttributeMappings;
		}
	}

	@Override
	public void setUserAttributeMappings(String userAttributeMappings) {
		_userAttributeMappings = userAttributeMappings;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SamlSpIdpConnection.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SamlSpIdpConnection toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SamlSpIdpConnection>
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
		SamlSpIdpConnectionImpl samlSpIdpConnectionImpl =
			new SamlSpIdpConnectionImpl();

		samlSpIdpConnectionImpl.setSamlSpIdpConnectionId(
			getSamlSpIdpConnectionId());
		samlSpIdpConnectionImpl.setCompanyId(getCompanyId());
		samlSpIdpConnectionImpl.setUserId(getUserId());
		samlSpIdpConnectionImpl.setUserName(getUserName());
		samlSpIdpConnectionImpl.setCreateDate(getCreateDate());
		samlSpIdpConnectionImpl.setModifiedDate(getModifiedDate());
		samlSpIdpConnectionImpl.setSamlIdpEntityId(getSamlIdpEntityId());
		samlSpIdpConnectionImpl.setAssertionSignatureRequired(
			isAssertionSignatureRequired());
		samlSpIdpConnectionImpl.setClockSkew(getClockSkew());
		samlSpIdpConnectionImpl.setEnabled(isEnabled());
		samlSpIdpConnectionImpl.setForceAuthn(isForceAuthn());
		samlSpIdpConnectionImpl.setLdapImportEnabled(isLdapImportEnabled());
		samlSpIdpConnectionImpl.setMetadataUrl(getMetadataUrl());
		samlSpIdpConnectionImpl.setMetadataXml(getMetadataXml());
		samlSpIdpConnectionImpl.setMetadataUpdatedDate(
			getMetadataUpdatedDate());
		samlSpIdpConnectionImpl.setName(getName());
		samlSpIdpConnectionImpl.setNameIdFormat(getNameIdFormat());
		samlSpIdpConnectionImpl.setSignAuthnRequest(isSignAuthnRequest());
		samlSpIdpConnectionImpl.setUserAttributeMappings(
			getUserAttributeMappings());

		samlSpIdpConnectionImpl.resetOriginalValues();

		return samlSpIdpConnectionImpl;
	}

	@Override
	public int compareTo(SamlSpIdpConnection samlSpIdpConnection) {
		long primaryKey = samlSpIdpConnection.getPrimaryKey();

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

		if (!(object instanceof SamlSpIdpConnection)) {
			return false;
		}

		SamlSpIdpConnection samlSpIdpConnection = (SamlSpIdpConnection)object;

		long primaryKey = samlSpIdpConnection.getPrimaryKey();

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
		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;
		_originalSamlIdpEntityId = _samlIdpEntityId;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<SamlSpIdpConnection> toCacheModel() {
		SamlSpIdpConnectionCacheModel samlSpIdpConnectionCacheModel =
			new SamlSpIdpConnectionCacheModel();

		samlSpIdpConnectionCacheModel.samlSpIdpConnectionId =
			getSamlSpIdpConnectionId();

		samlSpIdpConnectionCacheModel.companyId = getCompanyId();

		samlSpIdpConnectionCacheModel.userId = getUserId();

		samlSpIdpConnectionCacheModel.userName = getUserName();

		String userName = samlSpIdpConnectionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			samlSpIdpConnectionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			samlSpIdpConnectionCacheModel.createDate = createDate.getTime();
		}
		else {
			samlSpIdpConnectionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			samlSpIdpConnectionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			samlSpIdpConnectionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		samlSpIdpConnectionCacheModel.samlIdpEntityId = getSamlIdpEntityId();

		String samlIdpEntityId = samlSpIdpConnectionCacheModel.samlIdpEntityId;

		if ((samlIdpEntityId != null) && (samlIdpEntityId.length() == 0)) {
			samlSpIdpConnectionCacheModel.samlIdpEntityId = null;
		}

		samlSpIdpConnectionCacheModel.assertionSignatureRequired =
			isAssertionSignatureRequired();

		samlSpIdpConnectionCacheModel.clockSkew = getClockSkew();

		samlSpIdpConnectionCacheModel.enabled = isEnabled();

		samlSpIdpConnectionCacheModel.forceAuthn = isForceAuthn();

		samlSpIdpConnectionCacheModel.ldapImportEnabled = isLdapImportEnabled();

		samlSpIdpConnectionCacheModel.metadataUrl = getMetadataUrl();

		String metadataUrl = samlSpIdpConnectionCacheModel.metadataUrl;

		if ((metadataUrl != null) && (metadataUrl.length() == 0)) {
			samlSpIdpConnectionCacheModel.metadataUrl = null;
		}

		samlSpIdpConnectionCacheModel.metadataXml = getMetadataXml();

		String metadataXml = samlSpIdpConnectionCacheModel.metadataXml;

		if ((metadataXml != null) && (metadataXml.length() == 0)) {
			samlSpIdpConnectionCacheModel.metadataXml = null;
		}

		Date metadataUpdatedDate = getMetadataUpdatedDate();

		if (metadataUpdatedDate != null) {
			samlSpIdpConnectionCacheModel.metadataUpdatedDate =
				metadataUpdatedDate.getTime();
		}
		else {
			samlSpIdpConnectionCacheModel.metadataUpdatedDate = Long.MIN_VALUE;
		}

		samlSpIdpConnectionCacheModel.name = getName();

		String name = samlSpIdpConnectionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			samlSpIdpConnectionCacheModel.name = null;
		}

		samlSpIdpConnectionCacheModel.nameIdFormat = getNameIdFormat();

		String nameIdFormat = samlSpIdpConnectionCacheModel.nameIdFormat;

		if ((nameIdFormat != null) && (nameIdFormat.length() == 0)) {
			samlSpIdpConnectionCacheModel.nameIdFormat = null;
		}

		samlSpIdpConnectionCacheModel.signAuthnRequest = isSignAuthnRequest();

		samlSpIdpConnectionCacheModel.userAttributeMappings =
			getUserAttributeMappings();

		String userAttributeMappings =
			samlSpIdpConnectionCacheModel.userAttributeMappings;

		if ((userAttributeMappings != null) &&
			(userAttributeMappings.length() == 0)) {

			samlSpIdpConnectionCacheModel.userAttributeMappings = null;
		}

		return samlSpIdpConnectionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SamlSpIdpConnection, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SamlSpIdpConnection, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SamlSpIdpConnection, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SamlSpIdpConnection)this));
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
		Map<String, Function<SamlSpIdpConnection, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SamlSpIdpConnection, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SamlSpIdpConnection, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SamlSpIdpConnection)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SamlSpIdpConnection>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _samlSpIdpConnectionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _samlIdpEntityId;
	private String _originalSamlIdpEntityId;
	private boolean _assertionSignatureRequired;
	private long _clockSkew;
	private boolean _enabled;
	private boolean _forceAuthn;
	private boolean _ldapImportEnabled;
	private String _metadataUrl;
	private String _metadataXml;
	private Date _metadataUpdatedDate;
	private String _name;
	private String _nameIdFormat;
	private boolean _signAuthnRequest;
	private String _userAttributeMappings;
	private long _columnBitmask;
	private SamlSpIdpConnection _escapedModel;

}