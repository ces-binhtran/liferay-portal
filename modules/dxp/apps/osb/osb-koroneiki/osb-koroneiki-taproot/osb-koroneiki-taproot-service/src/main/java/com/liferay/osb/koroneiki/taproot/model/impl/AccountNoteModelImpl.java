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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.model.AccountNoteModel;
import com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the AccountNote service. Represents a row in the &quot;Koroneiki_AccountNote&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AccountNoteModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountNoteImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteImpl
 * @generated
 */
@JSON(strict = true)
public class AccountNoteModelImpl
	extends BaseModelImpl<AccountNote> implements AccountNoteModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account note model instance should use the <code>AccountNote</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_AccountNote";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"accountNoteId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"creatorOktaId", Types.VARCHAR}, {"creatorName", Types.VARCHAR},
		{"modifiedDate", Types.TIMESTAMP}, {"modifierOktaId", Types.VARCHAR},
		{"modifierName", Types.VARCHAR}, {"accountNoteKey", Types.VARCHAR},
		{"accountId", Types.BIGINT}, {"type_", Types.VARCHAR},
		{"priority", Types.INTEGER}, {"content", Types.VARCHAR},
		{"format", Types.VARCHAR}, {"status", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountNoteId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("creatorOktaId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("creatorName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifierOktaId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modifierName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountNoteKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("format", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_AccountNote (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,accountNoteId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,creatorOktaId VARCHAR(75) null,creatorName VARCHAR(75) null,modifiedDate DATE null,modifierOktaId VARCHAR(75) null,modifierName VARCHAR(75) null,accountNoteKey VARCHAR(75) null,accountId LONG,type_ VARCHAR(75) null,priority INTEGER,content STRING null,format VARCHAR(75) null,status VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_AccountNote";

	public static final String ORDER_BY_JPQL =
		" ORDER BY accountNote.priority ASC, accountNote.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_AccountNote.priority ASC, Koroneiki_AccountNote.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACCOUNTID_COLUMN_BITMASK = 1L;

	public static final long ACCOUNTNOTEKEY_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long STATUS_COLUMN_BITMASK = 8L;

	public static final long TYPE_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long PRIORITY_COLUMN_BITMASK = 64L;

	public static final long CREATEDATE_COLUMN_BITMASK = 128L;

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
	public static AccountNote toModel(AccountNoteSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AccountNote model = new AccountNoteImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setAccountNoteId(soapModel.getAccountNoteId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setCreatorOktaId(soapModel.getCreatorOktaId());
		model.setCreatorName(soapModel.getCreatorName());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setModifierOktaId(soapModel.getModifierOktaId());
		model.setModifierName(soapModel.getModifierName());
		model.setAccountNoteKey(soapModel.getAccountNoteKey());
		model.setAccountId(soapModel.getAccountId());
		model.setType(soapModel.getType());
		model.setPriority(soapModel.getPriority());
		model.setContent(soapModel.getContent());
		model.setFormat(soapModel.getFormat());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AccountNote> toModels(AccountNoteSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AccountNote> models = new ArrayList<AccountNote>(
			soapModels.length);

		for (AccountNoteSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public AccountNoteModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _accountNoteId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountNoteId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountNoteId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AccountNote.class;
	}

	@Override
	public String getModelClassName() {
		return AccountNote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AccountNote, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AccountNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountNote, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AccountNote)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AccountNote, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AccountNote, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AccountNote)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AccountNote, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AccountNote, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AccountNote>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AccountNote.class.getClassLoader(), AccountNote.class,
			ModelWrapper.class);

		try {
			Constructor<AccountNote> constructor =
				(Constructor<AccountNote>)proxyClass.getConstructor(
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

	private static final Map<String, Function<AccountNote, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AccountNote, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AccountNote, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<AccountNote, Object>>();
		Map<String, BiConsumer<AccountNote, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<AccountNote, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AccountNote::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AccountNote, Long>)AccountNote::setMvccVersion);
		attributeGetterFunctions.put("uuid", AccountNote::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<AccountNote, String>)AccountNote::setUuid);
		attributeGetterFunctions.put(
			"accountNoteId", AccountNote::getAccountNoteId);
		attributeSetterBiConsumers.put(
			"accountNoteId",
			(BiConsumer<AccountNote, Long>)AccountNote::setAccountNoteId);
		attributeGetterFunctions.put("companyId", AccountNote::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AccountNote, Long>)AccountNote::setCompanyId);
		attributeGetterFunctions.put("userId", AccountNote::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<AccountNote, Long>)AccountNote::setUserId);
		attributeGetterFunctions.put("createDate", AccountNote::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AccountNote, Date>)AccountNote::setCreateDate);
		attributeGetterFunctions.put(
			"creatorOktaId", AccountNote::getCreatorOktaId);
		attributeSetterBiConsumers.put(
			"creatorOktaId",
			(BiConsumer<AccountNote, String>)AccountNote::setCreatorOktaId);
		attributeGetterFunctions.put(
			"creatorName", AccountNote::getCreatorName);
		attributeSetterBiConsumers.put(
			"creatorName",
			(BiConsumer<AccountNote, String>)AccountNote::setCreatorName);
		attributeGetterFunctions.put(
			"modifiedDate", AccountNote::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AccountNote, Date>)AccountNote::setModifiedDate);
		attributeGetterFunctions.put(
			"modifierOktaId", AccountNote::getModifierOktaId);
		attributeSetterBiConsumers.put(
			"modifierOktaId",
			(BiConsumer<AccountNote, String>)AccountNote::setModifierOktaId);
		attributeGetterFunctions.put(
			"modifierName", AccountNote::getModifierName);
		attributeSetterBiConsumers.put(
			"modifierName",
			(BiConsumer<AccountNote, String>)AccountNote::setModifierName);
		attributeGetterFunctions.put(
			"accountNoteKey", AccountNote::getAccountNoteKey);
		attributeSetterBiConsumers.put(
			"accountNoteKey",
			(BiConsumer<AccountNote, String>)AccountNote::setAccountNoteKey);
		attributeGetterFunctions.put("accountId", AccountNote::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId",
			(BiConsumer<AccountNote, Long>)AccountNote::setAccountId);
		attributeGetterFunctions.put("type", AccountNote::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<AccountNote, String>)AccountNote::setType);
		attributeGetterFunctions.put("priority", AccountNote::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<AccountNote, Integer>)AccountNote::setPriority);
		attributeGetterFunctions.put("content", AccountNote::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<AccountNote, String>)AccountNote::setContent);
		attributeGetterFunctions.put("format", AccountNote::getFormat);
		attributeSetterBiConsumers.put(
			"format", (BiConsumer<AccountNote, String>)AccountNote::setFormat);
		attributeGetterFunctions.put("status", AccountNote::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<AccountNote, String>)AccountNote::setStatus);

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
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getAccountNoteId() {
		return _accountNoteId;
	}

	@Override
	public void setAccountNoteId(long accountNoteId) {
		_accountNoteId = accountNoteId;
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public String getCreatorOktaId() {
		if (_creatorOktaId == null) {
			return "";
		}
		else {
			return _creatorOktaId;
		}
	}

	@Override
	public void setCreatorOktaId(String creatorOktaId) {
		_creatorOktaId = creatorOktaId;
	}

	@JSON
	@Override
	public String getCreatorName() {
		if (_creatorName == null) {
			return "";
		}
		else {
			return _creatorName;
		}
	}

	@Override
	public void setCreatorName(String creatorName) {
		_creatorName = creatorName;
	}

	@JSON
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

	@JSON
	@Override
	public String getModifierOktaId() {
		if (_modifierOktaId == null) {
			return "";
		}
		else {
			return _modifierOktaId;
		}
	}

	@Override
	public void setModifierOktaId(String modifierOktaId) {
		_modifierOktaId = modifierOktaId;
	}

	@JSON
	@Override
	public String getModifierName() {
		if (_modifierName == null) {
			return "";
		}
		else {
			return _modifierName;
		}
	}

	@Override
	public void setModifierName(String modifierName) {
		_modifierName = modifierName;
	}

	@JSON
	@Override
	public String getAccountNoteKey() {
		if (_accountNoteKey == null) {
			return "";
		}
		else {
			return _accountNoteKey;
		}
	}

	@Override
	public void setAccountNoteKey(String accountNoteKey) {
		_columnBitmask |= ACCOUNTNOTEKEY_COLUMN_BITMASK;

		if (_originalAccountNoteKey == null) {
			_originalAccountNoteKey = _accountNoteKey;
		}

		_accountNoteKey = accountNoteKey;
	}

	public String getOriginalAccountNoteKey() {
		return GetterUtil.getString(_originalAccountNoteKey);
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
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		_priority = priority;
	}

	@JSON
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
		_content = content;
	}

	@JSON
	@Override
	public String getFormat() {
		if (_format == null) {
			return "";
		}
		else {
			return _format;
		}
	}

	@Override
	public void setFormat(String format) {
		_format = format;
	}

	@JSON
	@Override
	public String getStatus() {
		if (_status == null) {
			return "";
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (_originalStatus == null) {
			_originalStatus = _status;
		}

		_status = status;
	}

	public String getOriginalStatus() {
		return GetterUtil.getString(_originalStatus);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AccountNote.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AccountNote.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AccountNote toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AccountNote>
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
		AccountNoteImpl accountNoteImpl = new AccountNoteImpl();

		accountNoteImpl.setMvccVersion(getMvccVersion());
		accountNoteImpl.setUuid(getUuid());
		accountNoteImpl.setAccountNoteId(getAccountNoteId());
		accountNoteImpl.setCompanyId(getCompanyId());
		accountNoteImpl.setUserId(getUserId());
		accountNoteImpl.setCreateDate(getCreateDate());
		accountNoteImpl.setCreatorOktaId(getCreatorOktaId());
		accountNoteImpl.setCreatorName(getCreatorName());
		accountNoteImpl.setModifiedDate(getModifiedDate());
		accountNoteImpl.setModifierOktaId(getModifierOktaId());
		accountNoteImpl.setModifierName(getModifierName());
		accountNoteImpl.setAccountNoteKey(getAccountNoteKey());
		accountNoteImpl.setAccountId(getAccountId());
		accountNoteImpl.setType(getType());
		accountNoteImpl.setPriority(getPriority());
		accountNoteImpl.setContent(getContent());
		accountNoteImpl.setFormat(getFormat());
		accountNoteImpl.setStatus(getStatus());

		accountNoteImpl.resetOriginalValues();

		return accountNoteImpl;
	}

	@Override
	public int compareTo(AccountNote accountNote) {
		int value = 0;

		if (getPriority() < accountNote.getPriority()) {
			value = -1;
		}
		else if (getPriority() > accountNote.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(
			getCreateDate(), accountNote.getCreateDate());

		value = value * -1;

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

		if (!(object instanceof AccountNote)) {
			return false;
		}

		AccountNote accountNote = (AccountNote)object;

		long primaryKey = accountNote.getPrimaryKey();

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
		_originalUuid = _uuid;

		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;

		_originalAccountNoteKey = _accountNoteKey;

		_originalAccountId = _accountId;

		_setOriginalAccountId = false;

		_originalType = _type;

		_originalStatus = _status;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AccountNote> toCacheModel() {
		AccountNoteCacheModel accountNoteCacheModel =
			new AccountNoteCacheModel();

		accountNoteCacheModel.mvccVersion = getMvccVersion();

		accountNoteCacheModel.uuid = getUuid();

		String uuid = accountNoteCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			accountNoteCacheModel.uuid = null;
		}

		accountNoteCacheModel.accountNoteId = getAccountNoteId();

		accountNoteCacheModel.companyId = getCompanyId();

		accountNoteCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			accountNoteCacheModel.createDate = createDate.getTime();
		}
		else {
			accountNoteCacheModel.createDate = Long.MIN_VALUE;
		}

		accountNoteCacheModel.creatorOktaId = getCreatorOktaId();

		String creatorOktaId = accountNoteCacheModel.creatorOktaId;

		if ((creatorOktaId != null) && (creatorOktaId.length() == 0)) {
			accountNoteCacheModel.creatorOktaId = null;
		}

		accountNoteCacheModel.creatorName = getCreatorName();

		String creatorName = accountNoteCacheModel.creatorName;

		if ((creatorName != null) && (creatorName.length() == 0)) {
			accountNoteCacheModel.creatorName = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			accountNoteCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			accountNoteCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		accountNoteCacheModel.modifierOktaId = getModifierOktaId();

		String modifierOktaId = accountNoteCacheModel.modifierOktaId;

		if ((modifierOktaId != null) && (modifierOktaId.length() == 0)) {
			accountNoteCacheModel.modifierOktaId = null;
		}

		accountNoteCacheModel.modifierName = getModifierName();

		String modifierName = accountNoteCacheModel.modifierName;

		if ((modifierName != null) && (modifierName.length() == 0)) {
			accountNoteCacheModel.modifierName = null;
		}

		accountNoteCacheModel.accountNoteKey = getAccountNoteKey();

		String accountNoteKey = accountNoteCacheModel.accountNoteKey;

		if ((accountNoteKey != null) && (accountNoteKey.length() == 0)) {
			accountNoteCacheModel.accountNoteKey = null;
		}

		accountNoteCacheModel.accountId = getAccountId();

		accountNoteCacheModel.type = getType();

		String type = accountNoteCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			accountNoteCacheModel.type = null;
		}

		accountNoteCacheModel.priority = getPriority();

		accountNoteCacheModel.content = getContent();

		String content = accountNoteCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			accountNoteCacheModel.content = null;
		}

		accountNoteCacheModel.format = getFormat();

		String format = accountNoteCacheModel.format;

		if ((format != null) && (format.length() == 0)) {
			accountNoteCacheModel.format = null;
		}

		accountNoteCacheModel.status = getStatus();

		String status = accountNoteCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			accountNoteCacheModel.status = null;
		}

		return accountNoteCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AccountNote, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AccountNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((AccountNote)this));
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
		Map<String, Function<AccountNote, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AccountNote, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountNote, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AccountNote)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AccountNote>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _accountNoteId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private String _creatorOktaId;
	private String _creatorName;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _modifierOktaId;
	private String _modifierName;
	private String _accountNoteKey;
	private String _originalAccountNoteKey;
	private long _accountId;
	private long _originalAccountId;
	private boolean _setOriginalAccountId;
	private String _type;
	private String _originalType;
	private int _priority;
	private String _content;
	private String _format;
	private String _status;
	private String _originalStatus;
	private long _columnBitmask;
	private AccountNote _escapedModel;

}