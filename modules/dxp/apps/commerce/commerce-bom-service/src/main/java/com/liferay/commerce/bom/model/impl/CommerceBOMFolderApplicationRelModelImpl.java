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

package com.liferay.commerce.bom.model.impl;

import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;
import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRelModel;
import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRelSoap;
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
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceBOMFolderApplicationRel service. Represents a row in the &quot;CBOMFolderApplicationRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceBOMFolderApplicationRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceBOMFolderApplicationRelImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceBOMFolderApplicationRelModelImpl
	extends BaseModelImpl<CommerceBOMFolderApplicationRel>
	implements CommerceBOMFolderApplicationRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce bom folder application rel model instance should use the <code>CommerceBOMFolderApplicationRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CBOMFolderApplicationRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"CBOMFolderApplicationRelId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"commerceBOMFolderId", Types.BIGINT},
		{"commerceApplicationModelId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CBOMFolderApplicationRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceBOMFolderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceApplicationModelId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CBOMFolderApplicationRel (CBOMFolderApplicationRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceBOMFolderId LONG,commerceApplicationModelId LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CBOMFolderApplicationRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceBOMFolderApplicationRel.commerceBOMFolderApplicationRelId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CBOMFolderApplicationRel.CBOMFolderApplicationRelId ASC";

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
	public static final long COMMERCEAPPLICATIONMODELID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEBOMFOLDERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMMERCEBOMFOLDERAPPLICATIONRELID_COLUMN_BITMASK =
		4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static CommerceBOMFolderApplicationRel toModel(
		CommerceBOMFolderApplicationRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceBOMFolderApplicationRel model =
			new CommerceBOMFolderApplicationRelImpl();

		model.setCommerceBOMFolderApplicationRelId(
			soapModel.getCommerceBOMFolderApplicationRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceBOMFolderId(soapModel.getCommerceBOMFolderId());
		model.setCommerceApplicationModelId(
			soapModel.getCommerceApplicationModelId());

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
	public static List<CommerceBOMFolderApplicationRel> toModels(
		CommerceBOMFolderApplicationRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceBOMFolderApplicationRel> models =
			new ArrayList<CommerceBOMFolderApplicationRel>(soapModels.length);

		for (CommerceBOMFolderApplicationRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.bom.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel"));

	public CommerceBOMFolderApplicationRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceBOMFolderApplicationRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceBOMFolderApplicationRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBOMFolderApplicationRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBOMFolderApplicationRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBOMFolderApplicationRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceBOMFolderApplicationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommerceBOMFolderApplicationRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceBOMFolderApplicationRel, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceBOMFolderApplicationRel)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceBOMFolderApplicationRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceBOMFolderApplicationRel, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceBOMFolderApplicationRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceBOMFolderApplicationRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceBOMFolderApplicationRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceBOMFolderApplicationRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceBOMFolderApplicationRel.class.getClassLoader(),
			CommerceBOMFolderApplicationRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceBOMFolderApplicationRel> constructor =
				(Constructor<CommerceBOMFolderApplicationRel>)
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
		<String, Function<CommerceBOMFolderApplicationRel, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceBOMFolderApplicationRel, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceBOMFolderApplicationRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceBOMFolderApplicationRel, Object>>();
		Map<String, BiConsumer<CommerceBOMFolderApplicationRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceBOMFolderApplicationRel, ?>>();

		attributeGetterFunctions.put(
			"commerceBOMFolderApplicationRelId",
			CommerceBOMFolderApplicationRel::
				getCommerceBOMFolderApplicationRelId);
		attributeSetterBiConsumers.put(
			"commerceBOMFolderApplicationRelId",
			(BiConsumer<CommerceBOMFolderApplicationRel, Long>)
				CommerceBOMFolderApplicationRel::
					setCommerceBOMFolderApplicationRelId);
		attributeGetterFunctions.put(
			"companyId", CommerceBOMFolderApplicationRel::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceBOMFolderApplicationRel, Long>)
				CommerceBOMFolderApplicationRel::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceBOMFolderApplicationRel::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceBOMFolderApplicationRel, Long>)
				CommerceBOMFolderApplicationRel::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceBOMFolderApplicationRel::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceBOMFolderApplicationRel, String>)
				CommerceBOMFolderApplicationRel::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceBOMFolderApplicationRel::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceBOMFolderApplicationRel, Date>)
				CommerceBOMFolderApplicationRel::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceBOMFolderApplicationRel::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceBOMFolderApplicationRel, Date>)
				CommerceBOMFolderApplicationRel::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceBOMFolderId",
			CommerceBOMFolderApplicationRel::getCommerceBOMFolderId);
		attributeSetterBiConsumers.put(
			"commerceBOMFolderId",
			(BiConsumer<CommerceBOMFolderApplicationRel, Long>)
				CommerceBOMFolderApplicationRel::setCommerceBOMFolderId);
		attributeGetterFunctions.put(
			"commerceApplicationModelId",
			CommerceBOMFolderApplicationRel::getCommerceApplicationModelId);
		attributeSetterBiConsumers.put(
			"commerceApplicationModelId",
			(BiConsumer<CommerceBOMFolderApplicationRel, Long>)
				CommerceBOMFolderApplicationRel::setCommerceApplicationModelId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceBOMFolderApplicationRelId() {
		return _commerceBOMFolderApplicationRelId;
	}

	@Override
	public void setCommerceBOMFolderApplicationRelId(
		long commerceBOMFolderApplicationRelId) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceBOMFolderApplicationRelId = commerceBOMFolderApplicationRelId;
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
	public long getCommerceBOMFolderId() {
		return _commerceBOMFolderId;
	}

	@Override
	public void setCommerceBOMFolderId(long commerceBOMFolderId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceBOMFolderId = commerceBOMFolderId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceBOMFolderId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceBOMFolderId"));
	}

	@JSON
	@Override
	public long getCommerceApplicationModelId() {
		return _commerceApplicationModelId;
	}

	@Override
	public void setCommerceApplicationModelId(long commerceApplicationModelId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceApplicationModelId = commerceApplicationModelId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCommerceApplicationModelId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("commerceApplicationModelId"));
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

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceBOMFolderApplicationRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceBOMFolderApplicationRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceBOMFolderApplicationRel>
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
		CommerceBOMFolderApplicationRelImpl
			commerceBOMFolderApplicationRelImpl =
				new CommerceBOMFolderApplicationRelImpl();

		commerceBOMFolderApplicationRelImpl.
			setCommerceBOMFolderApplicationRelId(
				getCommerceBOMFolderApplicationRelId());
		commerceBOMFolderApplicationRelImpl.setCompanyId(getCompanyId());
		commerceBOMFolderApplicationRelImpl.setUserId(getUserId());
		commerceBOMFolderApplicationRelImpl.setUserName(getUserName());
		commerceBOMFolderApplicationRelImpl.setCreateDate(getCreateDate());
		commerceBOMFolderApplicationRelImpl.setModifiedDate(getModifiedDate());
		commerceBOMFolderApplicationRelImpl.setCommerceBOMFolderId(
			getCommerceBOMFolderId());
		commerceBOMFolderApplicationRelImpl.setCommerceApplicationModelId(
			getCommerceApplicationModelId());

		commerceBOMFolderApplicationRelImpl.resetOriginalValues();

		return commerceBOMFolderApplicationRelImpl;
	}

	@Override
	public int compareTo(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {

		long primaryKey = commerceBOMFolderApplicationRel.getPrimaryKey();

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

		if (!(object instanceof CommerceBOMFolderApplicationRel)) {
			return false;
		}

		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel =
			(CommerceBOMFolderApplicationRel)object;

		long primaryKey = commerceBOMFolderApplicationRel.getPrimaryKey();

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
	public CacheModel<CommerceBOMFolderApplicationRel> toCacheModel() {
		CommerceBOMFolderApplicationRelCacheModel
			commerceBOMFolderApplicationRelCacheModel =
				new CommerceBOMFolderApplicationRelCacheModel();

		commerceBOMFolderApplicationRelCacheModel.
			commerceBOMFolderApplicationRelId =
				getCommerceBOMFolderApplicationRelId();

		commerceBOMFolderApplicationRelCacheModel.companyId = getCompanyId();

		commerceBOMFolderApplicationRelCacheModel.userId = getUserId();

		commerceBOMFolderApplicationRelCacheModel.userName = getUserName();

		String userName = commerceBOMFolderApplicationRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceBOMFolderApplicationRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceBOMFolderApplicationRelCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceBOMFolderApplicationRelCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceBOMFolderApplicationRelCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceBOMFolderApplicationRelCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceBOMFolderApplicationRelCacheModel.commerceBOMFolderId =
			getCommerceBOMFolderId();

		commerceBOMFolderApplicationRelCacheModel.commerceApplicationModelId =
			getCommerceApplicationModelId();

		return commerceBOMFolderApplicationRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceBOMFolderApplicationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommerceBOMFolderApplicationRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceBOMFolderApplicationRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceBOMFolderApplicationRel)this));
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
		Map<String, Function<CommerceBOMFolderApplicationRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String, Function<CommerceBOMFolderApplicationRel, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceBOMFolderApplicationRel, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceBOMFolderApplicationRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceBOMFolderApplicationRel>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceBOMFolderApplicationRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceBOMFolderId;
	private long _commerceApplicationModelId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceBOMFolderApplicationRel, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceBOMFolderApplicationRel)this);
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
			"CBOMFolderApplicationRelId", _commerceBOMFolderApplicationRelId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceBOMFolderId", _commerceBOMFolderId);
		_columnOriginalValues.put(
			"commerceApplicationModelId", _commerceApplicationModelId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put(
			"CBOMFolderApplicationRelId", "commerceBOMFolderApplicationRelId");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("CBOMFolderApplicationRelId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("commerceBOMFolderId", 64L);

		columnBitmasks.put("commerceApplicationModelId", 128L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceBOMFolderApplicationRel _escapedModel;

}