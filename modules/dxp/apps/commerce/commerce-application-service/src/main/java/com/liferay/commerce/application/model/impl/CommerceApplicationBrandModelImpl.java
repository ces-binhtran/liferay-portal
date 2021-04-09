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

package com.liferay.commerce.application.model.impl;

import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.commerce.application.model.CommerceApplicationBrandModel;
import com.liferay.commerce.application.model.CommerceApplicationBrandSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
 * The base model implementation for the CommerceApplicationBrand service. Represents a row in the &quot;CommerceApplicationBrand&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceApplicationBrandModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceApplicationBrandImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceApplicationBrandModelImpl
	extends BaseModelImpl<CommerceApplicationBrand>
	implements CommerceApplicationBrandModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce application brand model instance should use the <code>CommerceApplicationBrand</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceApplicationBrand";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceApplicationBrandId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"name", Types.VARCHAR},
		{"logoId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceApplicationBrandId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceApplicationBrand (commerceApplicationBrandId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,logoId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceApplicationBrand";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceApplicationBrand.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceApplicationBrand.name ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceApplicationBrand toModel(
		CommerceApplicationBrandSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceApplicationBrand model = new CommerceApplicationBrandImpl();

		model.setCommerceApplicationBrandId(
			soapModel.getCommerceApplicationBrandId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setLogoId(soapModel.getLogoId());

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
	public static List<CommerceApplicationBrand> toModels(
		CommerceApplicationBrandSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceApplicationBrand> models =
			new ArrayList<CommerceApplicationBrand>(soapModels.length);

		for (CommerceApplicationBrandSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.application.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.application.model.CommerceApplicationBrand"));

	public CommerceApplicationBrandModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceApplicationBrandId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceApplicationBrandId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceApplicationBrandId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceApplicationBrand.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceApplicationBrand.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceApplicationBrand, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceApplicationBrand, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceApplicationBrand, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceApplicationBrand)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceApplicationBrand, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceApplicationBrand, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceApplicationBrand)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceApplicationBrand, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceApplicationBrand, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceApplicationBrand>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceApplicationBrand.class.getClassLoader(),
			CommerceApplicationBrand.class, ModelWrapper.class);

		try {
			Constructor<CommerceApplicationBrand> constructor =
				(Constructor<CommerceApplicationBrand>)
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

	private static final Map<String, Function<CommerceApplicationBrand, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceApplicationBrand, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceApplicationBrand, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceApplicationBrand, Object>>();
		Map<String, BiConsumer<CommerceApplicationBrand, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceApplicationBrand, ?>>();

		attributeGetterFunctions.put(
			"commerceApplicationBrandId",
			CommerceApplicationBrand::getCommerceApplicationBrandId);
		attributeSetterBiConsumers.put(
			"commerceApplicationBrandId",
			(BiConsumer<CommerceApplicationBrand, Long>)
				CommerceApplicationBrand::setCommerceApplicationBrandId);
		attributeGetterFunctions.put(
			"companyId", CommerceApplicationBrand::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceApplicationBrand, Long>)
				CommerceApplicationBrand::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceApplicationBrand::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceApplicationBrand, Long>)
				CommerceApplicationBrand::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceApplicationBrand::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceApplicationBrand, String>)
				CommerceApplicationBrand::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceApplicationBrand::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceApplicationBrand, Date>)
				CommerceApplicationBrand::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceApplicationBrand::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceApplicationBrand, Date>)
				CommerceApplicationBrand::setModifiedDate);
		attributeGetterFunctions.put("name", CommerceApplicationBrand::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<CommerceApplicationBrand, String>)
				CommerceApplicationBrand::setName);
		attributeGetterFunctions.put(
			"logoId", CommerceApplicationBrand::getLogoId);
		attributeSetterBiConsumers.put(
			"logoId",
			(BiConsumer<CommerceApplicationBrand, Long>)
				CommerceApplicationBrand::setLogoId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceApplicationBrandId() {
		return _commerceApplicationBrandId;
	}

	@Override
	public void setCommerceApplicationBrandId(long commerceApplicationBrandId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceApplicationBrandId = commerceApplicationBrandId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logoId = logoId;
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
			getCompanyId(), CommerceApplicationBrand.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceApplicationBrand toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceApplicationBrand>
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
		CommerceApplicationBrandImpl commerceApplicationBrandImpl =
			new CommerceApplicationBrandImpl();

		commerceApplicationBrandImpl.setCommerceApplicationBrandId(
			getCommerceApplicationBrandId());
		commerceApplicationBrandImpl.setCompanyId(getCompanyId());
		commerceApplicationBrandImpl.setUserId(getUserId());
		commerceApplicationBrandImpl.setUserName(getUserName());
		commerceApplicationBrandImpl.setCreateDate(getCreateDate());
		commerceApplicationBrandImpl.setModifiedDate(getModifiedDate());
		commerceApplicationBrandImpl.setName(getName());
		commerceApplicationBrandImpl.setLogoId(getLogoId());

		commerceApplicationBrandImpl.resetOriginalValues();

		return commerceApplicationBrandImpl;
	}

	@Override
	public int compareTo(CommerceApplicationBrand commerceApplicationBrand) {
		int value = 0;

		value = getName().compareTo(commerceApplicationBrand.getName());

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

		if (!(object instanceof CommerceApplicationBrand)) {
			return false;
		}

		CommerceApplicationBrand commerceApplicationBrand =
			(CommerceApplicationBrand)object;

		long primaryKey = commerceApplicationBrand.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceApplicationBrand> toCacheModel() {
		CommerceApplicationBrandCacheModel commerceApplicationBrandCacheModel =
			new CommerceApplicationBrandCacheModel();

		commerceApplicationBrandCacheModel.commerceApplicationBrandId =
			getCommerceApplicationBrandId();

		commerceApplicationBrandCacheModel.companyId = getCompanyId();

		commerceApplicationBrandCacheModel.userId = getUserId();

		commerceApplicationBrandCacheModel.userName = getUserName();

		String userName = commerceApplicationBrandCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceApplicationBrandCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceApplicationBrandCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceApplicationBrandCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceApplicationBrandCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceApplicationBrandCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceApplicationBrandCacheModel.name = getName();

		String name = commerceApplicationBrandCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceApplicationBrandCacheModel.name = null;
		}

		commerceApplicationBrandCacheModel.logoId = getLogoId();

		return commerceApplicationBrandCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceApplicationBrand, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceApplicationBrand, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceApplicationBrand, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CommerceApplicationBrand)this));
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
		Map<String, Function<CommerceApplicationBrand, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceApplicationBrand, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceApplicationBrand, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommerceApplicationBrand)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceApplicationBrand>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceApplicationBrandId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private long _logoId;

	public <T> T getColumnValue(String columnName) {
		Function<CommerceApplicationBrand, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceApplicationBrand)this);
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
			"commerceApplicationBrandId", _commerceApplicationBrandId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("logoId", _logoId);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("commerceApplicationBrandId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("name", 64L);

		columnBitmasks.put("logoId", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceApplicationBrand _escapedModel;

}