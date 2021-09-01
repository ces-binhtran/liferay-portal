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

package com.liferay.multi.factor.authentication.timebased.otp.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.multi.factor.authentication.timebased.otp.model.MFATimeBasedOTPEntry;
import com.liferay.multi.factor.authentication.timebased.otp.model.MFATimeBasedOTPEntryModel;
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
 * The base model implementation for the MFATimeBasedOTPEntry service. Represents a row in the &quot;MFATimeBasedOTPEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MFATimeBasedOTPEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MFATimeBasedOTPEntryImpl}.
 * </p>
 *
 * @author Arthur Chan
 * @see MFATimeBasedOTPEntryImpl
 * @generated
 */
public class MFATimeBasedOTPEntryModelImpl
	extends BaseModelImpl<MFATimeBasedOTPEntry>
	implements MFATimeBasedOTPEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a mfa time based otp entry model instance should use the <code>MFATimeBasedOTPEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "MFATimeBasedOTPEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"mfaTimeBasedOTPEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"failedAttempts", Types.INTEGER},
		{"lastFailDate", Types.TIMESTAMP}, {"lastFailIP", Types.VARCHAR},
		{"lastSuccessDate", Types.TIMESTAMP}, {"lastSuccessIP", Types.VARCHAR},
		{"sharedSecret", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("mfaTimeBasedOTPEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("failedAttempts", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastFailDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastFailIP", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastSuccessDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastSuccessIP", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sharedSecret", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MFATimeBasedOTPEntry (mvccVersion LONG default 0 not null,mfaTimeBasedOTPEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,failedAttempts INTEGER,lastFailDate DATE null,lastFailIP VARCHAR(75) null,lastSuccessDate DATE null,lastSuccessIP VARCHAR(75) null,sharedSecret VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table MFATimeBasedOTPEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY mfaTimeBasedOTPEntry.mfaTimeBasedOTPEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MFATimeBasedOTPEntry.mfaTimeBasedOTPEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long MFATIMEBASEDOTPENTRYID_COLUMN_BITMASK = 2L;

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

	public MFATimeBasedOTPEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _mfaTimeBasedOTPEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMfaTimeBasedOTPEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mfaTimeBasedOTPEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MFATimeBasedOTPEntry.class;
	}

	@Override
	public String getModelClassName() {
		return MFATimeBasedOTPEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MFATimeBasedOTPEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MFATimeBasedOTPEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MFATimeBasedOTPEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((MFATimeBasedOTPEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MFATimeBasedOTPEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MFATimeBasedOTPEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MFATimeBasedOTPEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MFATimeBasedOTPEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MFATimeBasedOTPEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, MFATimeBasedOTPEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			MFATimeBasedOTPEntry.class.getClassLoader(),
			MFATimeBasedOTPEntry.class, ModelWrapper.class);

		try {
			Constructor<MFATimeBasedOTPEntry> constructor =
				(Constructor<MFATimeBasedOTPEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<MFATimeBasedOTPEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MFATimeBasedOTPEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MFATimeBasedOTPEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<MFATimeBasedOTPEntry, Object>>();
		Map<String, BiConsumer<MFATimeBasedOTPEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<MFATimeBasedOTPEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", MFATimeBasedOTPEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<MFATimeBasedOTPEntry, Long>)
				MFATimeBasedOTPEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"mfaTimeBasedOTPEntryId",
			MFATimeBasedOTPEntry::getMfaTimeBasedOTPEntryId);
		attributeSetterBiConsumers.put(
			"mfaTimeBasedOTPEntryId",
			(BiConsumer<MFATimeBasedOTPEntry, Long>)
				MFATimeBasedOTPEntry::setMfaTimeBasedOTPEntryId);
		attributeGetterFunctions.put(
			"companyId", MFATimeBasedOTPEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<MFATimeBasedOTPEntry, Long>)
				MFATimeBasedOTPEntry::setCompanyId);
		attributeGetterFunctions.put("userId", MFATimeBasedOTPEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<MFATimeBasedOTPEntry, Long>)
				MFATimeBasedOTPEntry::setUserId);
		attributeGetterFunctions.put(
			"userName", MFATimeBasedOTPEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<MFATimeBasedOTPEntry, String>)
				MFATimeBasedOTPEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", MFATimeBasedOTPEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<MFATimeBasedOTPEntry, Date>)
				MFATimeBasedOTPEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", MFATimeBasedOTPEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<MFATimeBasedOTPEntry, Date>)
				MFATimeBasedOTPEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"failedAttempts", MFATimeBasedOTPEntry::getFailedAttempts);
		attributeSetterBiConsumers.put(
			"failedAttempts",
			(BiConsumer<MFATimeBasedOTPEntry, Integer>)
				MFATimeBasedOTPEntry::setFailedAttempts);
		attributeGetterFunctions.put(
			"lastFailDate", MFATimeBasedOTPEntry::getLastFailDate);
		attributeSetterBiConsumers.put(
			"lastFailDate",
			(BiConsumer<MFATimeBasedOTPEntry, Date>)
				MFATimeBasedOTPEntry::setLastFailDate);
		attributeGetterFunctions.put(
			"lastFailIP", MFATimeBasedOTPEntry::getLastFailIP);
		attributeSetterBiConsumers.put(
			"lastFailIP",
			(BiConsumer<MFATimeBasedOTPEntry, String>)
				MFATimeBasedOTPEntry::setLastFailIP);
		attributeGetterFunctions.put(
			"lastSuccessDate", MFATimeBasedOTPEntry::getLastSuccessDate);
		attributeSetterBiConsumers.put(
			"lastSuccessDate",
			(BiConsumer<MFATimeBasedOTPEntry, Date>)
				MFATimeBasedOTPEntry::setLastSuccessDate);
		attributeGetterFunctions.put(
			"lastSuccessIP", MFATimeBasedOTPEntry::getLastSuccessIP);
		attributeSetterBiConsumers.put(
			"lastSuccessIP",
			(BiConsumer<MFATimeBasedOTPEntry, String>)
				MFATimeBasedOTPEntry::setLastSuccessIP);
		attributeGetterFunctions.put(
			"sharedSecret", MFATimeBasedOTPEntry::getSharedSecret);
		attributeSetterBiConsumers.put(
			"sharedSecret",
			(BiConsumer<MFATimeBasedOTPEntry, String>)
				MFATimeBasedOTPEntry::setSharedSecret);

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
	public long getMfaTimeBasedOTPEntryId() {
		return _mfaTimeBasedOTPEntryId;
	}

	@Override
	public void setMfaTimeBasedOTPEntryId(long mfaTimeBasedOTPEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mfaTimeBasedOTPEntryId = mfaTimeBasedOTPEntryId;
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
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

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

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public int getFailedAttempts() {
		return _failedAttempts;
	}

	@Override
	public void setFailedAttempts(int failedAttempts) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_failedAttempts = failedAttempts;
	}

	@Override
	public Date getLastFailDate() {
		return _lastFailDate;
	}

	@Override
	public void setLastFailDate(Date lastFailDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastFailDate = lastFailDate;
	}

	@Override
	public String getLastFailIP() {
		if (_lastFailIP == null) {
			return "";
		}
		else {
			return _lastFailIP;
		}
	}

	@Override
	public void setLastFailIP(String lastFailIP) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastFailIP = lastFailIP;
	}

	@Override
	public Date getLastSuccessDate() {
		return _lastSuccessDate;
	}

	@Override
	public void setLastSuccessDate(Date lastSuccessDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastSuccessDate = lastSuccessDate;
	}

	@Override
	public String getLastSuccessIP() {
		if (_lastSuccessIP == null) {
			return "";
		}
		else {
			return _lastSuccessIP;
		}
	}

	@Override
	public void setLastSuccessIP(String lastSuccessIP) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastSuccessIP = lastSuccessIP;
	}

	@Override
	public String getSharedSecret() {
		if (_sharedSecret == null) {
			return "";
		}
		else {
			return _sharedSecret;
		}
	}

	@Override
	public void setSharedSecret(String sharedSecret) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_sharedSecret = sharedSecret;
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
			getCompanyId(), MFATimeBasedOTPEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MFATimeBasedOTPEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, MFATimeBasedOTPEntry>
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
		MFATimeBasedOTPEntryImpl mfaTimeBasedOTPEntryImpl =
			new MFATimeBasedOTPEntryImpl();

		mfaTimeBasedOTPEntryImpl.setMvccVersion(getMvccVersion());
		mfaTimeBasedOTPEntryImpl.setMfaTimeBasedOTPEntryId(
			getMfaTimeBasedOTPEntryId());
		mfaTimeBasedOTPEntryImpl.setCompanyId(getCompanyId());
		mfaTimeBasedOTPEntryImpl.setUserId(getUserId());
		mfaTimeBasedOTPEntryImpl.setUserName(getUserName());
		mfaTimeBasedOTPEntryImpl.setCreateDate(getCreateDate());
		mfaTimeBasedOTPEntryImpl.setModifiedDate(getModifiedDate());
		mfaTimeBasedOTPEntryImpl.setFailedAttempts(getFailedAttempts());
		mfaTimeBasedOTPEntryImpl.setLastFailDate(getLastFailDate());
		mfaTimeBasedOTPEntryImpl.setLastFailIP(getLastFailIP());
		mfaTimeBasedOTPEntryImpl.setLastSuccessDate(getLastSuccessDate());
		mfaTimeBasedOTPEntryImpl.setLastSuccessIP(getLastSuccessIP());
		mfaTimeBasedOTPEntryImpl.setSharedSecret(getSharedSecret());

		mfaTimeBasedOTPEntryImpl.resetOriginalValues();

		return mfaTimeBasedOTPEntryImpl;
	}

	@Override
	public int compareTo(MFATimeBasedOTPEntry mfaTimeBasedOTPEntry) {
		long primaryKey = mfaTimeBasedOTPEntry.getPrimaryKey();

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

		if (!(object instanceof MFATimeBasedOTPEntry)) {
			return false;
		}

		MFATimeBasedOTPEntry mfaTimeBasedOTPEntry =
			(MFATimeBasedOTPEntry)object;

		long primaryKey = mfaTimeBasedOTPEntry.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<MFATimeBasedOTPEntry> toCacheModel() {
		MFATimeBasedOTPEntryCacheModel mfaTimeBasedOTPEntryCacheModel =
			new MFATimeBasedOTPEntryCacheModel();

		mfaTimeBasedOTPEntryCacheModel.mvccVersion = getMvccVersion();

		mfaTimeBasedOTPEntryCacheModel.mfaTimeBasedOTPEntryId =
			getMfaTimeBasedOTPEntryId();

		mfaTimeBasedOTPEntryCacheModel.companyId = getCompanyId();

		mfaTimeBasedOTPEntryCacheModel.userId = getUserId();

		mfaTimeBasedOTPEntryCacheModel.userName = getUserName();

		String userName = mfaTimeBasedOTPEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mfaTimeBasedOTPEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mfaTimeBasedOTPEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			mfaTimeBasedOTPEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mfaTimeBasedOTPEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			mfaTimeBasedOTPEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mfaTimeBasedOTPEntryCacheModel.failedAttempts = getFailedAttempts();

		Date lastFailDate = getLastFailDate();

		if (lastFailDate != null) {
			mfaTimeBasedOTPEntryCacheModel.lastFailDate =
				lastFailDate.getTime();
		}
		else {
			mfaTimeBasedOTPEntryCacheModel.lastFailDate = Long.MIN_VALUE;
		}

		mfaTimeBasedOTPEntryCacheModel.lastFailIP = getLastFailIP();

		String lastFailIP = mfaTimeBasedOTPEntryCacheModel.lastFailIP;

		if ((lastFailIP != null) && (lastFailIP.length() == 0)) {
			mfaTimeBasedOTPEntryCacheModel.lastFailIP = null;
		}

		Date lastSuccessDate = getLastSuccessDate();

		if (lastSuccessDate != null) {
			mfaTimeBasedOTPEntryCacheModel.lastSuccessDate =
				lastSuccessDate.getTime();
		}
		else {
			mfaTimeBasedOTPEntryCacheModel.lastSuccessDate = Long.MIN_VALUE;
		}

		mfaTimeBasedOTPEntryCacheModel.lastSuccessIP = getLastSuccessIP();

		String lastSuccessIP = mfaTimeBasedOTPEntryCacheModel.lastSuccessIP;

		if ((lastSuccessIP != null) && (lastSuccessIP.length() == 0)) {
			mfaTimeBasedOTPEntryCacheModel.lastSuccessIP = null;
		}

		mfaTimeBasedOTPEntryCacheModel.sharedSecret = getSharedSecret();

		String sharedSecret = mfaTimeBasedOTPEntryCacheModel.sharedSecret;

		if ((sharedSecret != null) && (sharedSecret.length() == 0)) {
			mfaTimeBasedOTPEntryCacheModel.sharedSecret = null;
		}

		return mfaTimeBasedOTPEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MFATimeBasedOTPEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MFATimeBasedOTPEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MFATimeBasedOTPEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(MFATimeBasedOTPEntry)this);

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
		Map<String, Function<MFATimeBasedOTPEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MFATimeBasedOTPEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MFATimeBasedOTPEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((MFATimeBasedOTPEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, MFATimeBasedOTPEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _mfaTimeBasedOTPEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private int _failedAttempts;
	private Date _lastFailDate;
	private String _lastFailIP;
	private Date _lastSuccessDate;
	private String _lastSuccessIP;
	private String _sharedSecret;

	public <T> T getColumnValue(String columnName) {
		Function<MFATimeBasedOTPEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((MFATimeBasedOTPEntry)this);
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
			"mfaTimeBasedOTPEntryId", _mfaTimeBasedOTPEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("failedAttempts", _failedAttempts);
		_columnOriginalValues.put("lastFailDate", _lastFailDate);
		_columnOriginalValues.put("lastFailIP", _lastFailIP);
		_columnOriginalValues.put("lastSuccessDate", _lastSuccessDate);
		_columnOriginalValues.put("lastSuccessIP", _lastSuccessIP);
		_columnOriginalValues.put("sharedSecret", _sharedSecret);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("mfaTimeBasedOTPEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("failedAttempts", 128L);

		columnBitmasks.put("lastFailDate", 256L);

		columnBitmasks.put("lastFailIP", 512L);

		columnBitmasks.put("lastSuccessDate", 1024L);

		columnBitmasks.put("lastSuccessIP", 2048L);

		columnBitmasks.put("sharedSecret", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private MFATimeBasedOTPEntry _escapedModel;

}