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

package com.liferay.oauth.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.model.OAuthUserModel;
import com.liferay.oauth.model.OAuthUserSoap;
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
import com.liferay.portal.kernel.util.GetterUtil;
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
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the OAuthUser service. Represents a row in the &quot;OAuth_OAuthUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OAuthUserModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuthUserImpl}.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUserImpl
 * @generated
 */
@JSON(strict = true)
public class OAuthUserModelImpl
	extends BaseModelImpl<OAuthUser> implements OAuthUserModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth user model instance should use the <code>OAuthUser</code> interface instead.
	 */
	public static final String TABLE_NAME = "OAuth_OAuthUser";

	public static final Object[][] TABLE_COLUMNS = {
		{"oAuthUserId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"oAuthApplicationId", Types.BIGINT}, {"accessToken", Types.VARCHAR},
		{"accessSecret", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("oAuthUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("oAuthApplicationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accessToken", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accessSecret", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OAuth_OAuthUser (oAuthUserId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,oAuthApplicationId LONG,accessToken VARCHAR(75) null,accessSecret VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table OAuth_OAuthUser";

	public static final String ORDER_BY_JPQL =
		" ORDER BY oAuthUser.oAuthUserId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OAuth_OAuthUser.oAuthUserId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long ACCESSTOKEN_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OAUTHAPPLICATIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OAUTHUSERID_COLUMN_BITMASK = 8L;

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

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static OAuthUser toModel(OAuthUserSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		OAuthUser model = new OAuthUserImpl();

		model.setOAuthUserId(soapModel.getOAuthUserId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setOAuthApplicationId(soapModel.getOAuthApplicationId());
		model.setAccessToken(soapModel.getAccessToken());
		model.setAccessSecret(soapModel.getAccessSecret());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<OAuthUser> toModels(OAuthUserSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<OAuthUser> models = new ArrayList<OAuthUser>(soapModels.length);

		for (OAuthUserSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public OAuthUserModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthUserId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthUserId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthUser.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OAuthUser, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthUser, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((OAuthUser)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OAuthUser, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OAuthUser, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OAuthUser)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OAuthUser, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OAuthUser, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OAuthUser>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OAuthUser.class.getClassLoader(), OAuthUser.class,
			ModelWrapper.class);

		try {
			Constructor<OAuthUser> constructor =
				(Constructor<OAuthUser>)proxyClass.getConstructor(
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

	private static final Map<String, Function<OAuthUser, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OAuthUser, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<OAuthUser, Object>>();
		Map<String, BiConsumer<OAuthUser, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<OAuthUser, ?>>();

		attributeGetterFunctions.put("oAuthUserId", OAuthUser::getOAuthUserId);
		attributeSetterBiConsumers.put(
			"oAuthUserId",
			(BiConsumer<OAuthUser, Long>)OAuthUser::setOAuthUserId);
		attributeGetterFunctions.put("companyId", OAuthUser::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<OAuthUser, Long>)OAuthUser::setCompanyId);
		attributeGetterFunctions.put("userId", OAuthUser::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<OAuthUser, Long>)OAuthUser::setUserId);
		attributeGetterFunctions.put("userName", OAuthUser::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<OAuthUser, String>)OAuthUser::setUserName);
		attributeGetterFunctions.put("createDate", OAuthUser::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<OAuthUser, Date>)OAuthUser::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", OAuthUser::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<OAuthUser, Date>)OAuthUser::setModifiedDate);
		attributeGetterFunctions.put(
			"oAuthApplicationId", OAuthUser::getOAuthApplicationId);
		attributeSetterBiConsumers.put(
			"oAuthApplicationId",
			(BiConsumer<OAuthUser, Long>)OAuthUser::setOAuthApplicationId);
		attributeGetterFunctions.put("accessToken", OAuthUser::getAccessToken);
		attributeSetterBiConsumers.put(
			"accessToken",
			(BiConsumer<OAuthUser, String>)OAuthUser::setAccessToken);
		attributeGetterFunctions.put(
			"accessSecret", OAuthUser::getAccessSecret);
		attributeSetterBiConsumers.put(
			"accessSecret",
			(BiConsumer<OAuthUser, String>)OAuthUser::setAccessSecret);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getOAuthUserId() {
		return _oAuthUserId;
	}

	@Override
	public void setOAuthUserId(long oAuthUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_oAuthUserId = oAuthUserId;
	}

	@Override
	public String getOAuthUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getOAuthUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setOAuthUserUuid(String oAuthUserUuid) {
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

	@JSON
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

	@JSON
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

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getOAuthApplicationId() {
		return _oAuthApplicationId;
	}

	@Override
	public void setOAuthApplicationId(long oAuthApplicationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_oAuthApplicationId = oAuthApplicationId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalOAuthApplicationId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("oAuthApplicationId"));
	}

	@JSON
	@Override
	public String getAccessToken() {
		if (_accessToken == null) {
			return "";
		}
		else {
			return _accessToken;
		}
	}

	@Override
	public void setAccessToken(String accessToken) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessToken = accessToken;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalAccessToken() {
		return getColumnOriginalValue("accessToken");
	}

	@JSON
	@Override
	public String getAccessSecret() {
		if (_accessSecret == null) {
			return "";
		}
		else {
			return _accessSecret;
		}
	}

	@Override
	public void setAccessSecret(String accessSecret) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessSecret = accessSecret;
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
			getCompanyId(), OAuthUser.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuthUser toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OAuthUser>
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
		OAuthUserImpl oAuthUserImpl = new OAuthUserImpl();

		oAuthUserImpl.setOAuthUserId(getOAuthUserId());
		oAuthUserImpl.setCompanyId(getCompanyId());
		oAuthUserImpl.setUserId(getUserId());
		oAuthUserImpl.setUserName(getUserName());
		oAuthUserImpl.setCreateDate(getCreateDate());
		oAuthUserImpl.setModifiedDate(getModifiedDate());
		oAuthUserImpl.setOAuthApplicationId(getOAuthApplicationId());
		oAuthUserImpl.setAccessToken(getAccessToken());
		oAuthUserImpl.setAccessSecret(getAccessSecret());

		oAuthUserImpl.resetOriginalValues();

		return oAuthUserImpl;
	}

	@Override
	public int compareTo(OAuthUser oAuthUser) {
		long primaryKey = oAuthUser.getPrimaryKey();

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

		if (!(object instanceof OAuthUser)) {
			return false;
		}

		OAuthUser oAuthUser = (OAuthUser)object;

		long primaryKey = oAuthUser.getPrimaryKey();

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
	public CacheModel<OAuthUser> toCacheModel() {
		OAuthUserCacheModel oAuthUserCacheModel = new OAuthUserCacheModel();

		oAuthUserCacheModel.oAuthUserId = getOAuthUserId();

		oAuthUserCacheModel.companyId = getCompanyId();

		oAuthUserCacheModel.userId = getUserId();

		oAuthUserCacheModel.userName = getUserName();

		String userName = oAuthUserCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			oAuthUserCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuthUserCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuthUserCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			oAuthUserCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			oAuthUserCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		oAuthUserCacheModel.oAuthApplicationId = getOAuthApplicationId();

		oAuthUserCacheModel.accessToken = getAccessToken();

		String accessToken = oAuthUserCacheModel.accessToken;

		if ((accessToken != null) && (accessToken.length() == 0)) {
			oAuthUserCacheModel.accessToken = null;
		}

		oAuthUserCacheModel.accessSecret = getAccessSecret();

		String accessSecret = oAuthUserCacheModel.accessSecret;

		if ((accessSecret != null) && (accessSecret.length() == 0)) {
			oAuthUserCacheModel.accessSecret = null;
		}

		return oAuthUserCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OAuthUser, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthUser, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((OAuthUser)this));
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
		Map<String, Function<OAuthUser, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OAuthUser, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthUser, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OAuthUser)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OAuthUser>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _oAuthUserId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _oAuthApplicationId;
	private String _accessToken;
	private String _accessSecret;

	public <T> T getColumnValue(String columnName) {
		Function<OAuthUser, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OAuthUser)this);
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

		_columnOriginalValues.put("oAuthUserId", _oAuthUserId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("oAuthApplicationId", _oAuthApplicationId);
		_columnOriginalValues.put("accessToken", _accessToken);
		_columnOriginalValues.put("accessSecret", _accessSecret);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("oAuthUserId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("oAuthApplicationId", 64L);

		columnBitmasks.put("accessToken", 128L);

		columnBitmasks.put("accessSecret", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OAuthUser _escapedModel;

}