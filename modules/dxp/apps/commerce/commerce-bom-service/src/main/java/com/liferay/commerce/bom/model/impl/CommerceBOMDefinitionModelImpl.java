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

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.model.CommerceBOMDefinitionModel;
import com.liferay.commerce.bom.model.CommerceBOMDefinitionSoap;
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
 * The base model implementation for the CommerceBOMDefinition service. Represents a row in the &quot;CommerceBOMDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceBOMDefinitionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceBOMDefinitionImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceBOMDefinitionModelImpl
	extends BaseModelImpl<CommerceBOMDefinition>
	implements CommerceBOMDefinitionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce bom definition model instance should use the <code>CommerceBOMDefinition</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceBOMDefinition";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceBOMDefinitionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"commerceBOMFolderId", Types.BIGINT},
		{"CPAttachmentFileEntryId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"friendlyUrl", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceBOMDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceBOMFolderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("CPAttachmentFileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("friendlyUrl", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceBOMDefinition (commerceBOMDefinitionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceBOMFolderId LONG,CPAttachmentFileEntryId LONG,name VARCHAR(75) null,friendlyUrl VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceBOMDefinition";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceBOMDefinition.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceBOMDefinition.name ASC";

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
	public static final long COMMERCEBOMFOLDERID_COLUMN_BITMASK = 1L;

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
	public static CommerceBOMDefinition toModel(
		CommerceBOMDefinitionSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceBOMDefinition model = new CommerceBOMDefinitionImpl();

		model.setCommerceBOMDefinitionId(
			soapModel.getCommerceBOMDefinitionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceBOMFolderId(soapModel.getCommerceBOMFolderId());
		model.setCPAttachmentFileEntryId(
			soapModel.getCPAttachmentFileEntryId());
		model.setName(soapModel.getName());
		model.setFriendlyUrl(soapModel.getFriendlyUrl());

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
	public static List<CommerceBOMDefinition> toModels(
		CommerceBOMDefinitionSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceBOMDefinition> models =
			new ArrayList<CommerceBOMDefinition>(soapModels.length);

		for (CommerceBOMDefinitionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.bom.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.bom.model.CommerceBOMDefinition"));

	public CommerceBOMDefinitionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceBOMDefinitionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceBOMDefinitionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceBOMDefinitionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceBOMDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceBOMDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceBOMDefinition, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceBOMDefinition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceBOMDefinition, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceBOMDefinition)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceBOMDefinition, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceBOMDefinition, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceBOMDefinition)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceBOMDefinition, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceBOMDefinition, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceBOMDefinition>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceBOMDefinition.class.getClassLoader(),
			CommerceBOMDefinition.class, ModelWrapper.class);

		try {
			Constructor<CommerceBOMDefinition> constructor =
				(Constructor<CommerceBOMDefinition>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceBOMDefinition, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceBOMDefinition, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceBOMDefinition, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceBOMDefinition, Object>>();
		Map<String, BiConsumer<CommerceBOMDefinition, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<CommerceBOMDefinition, ?>>();

		attributeGetterFunctions.put(
			"commerceBOMDefinitionId",
			CommerceBOMDefinition::getCommerceBOMDefinitionId);
		attributeSetterBiConsumers.put(
			"commerceBOMDefinitionId",
			(BiConsumer<CommerceBOMDefinition, Long>)
				CommerceBOMDefinition::setCommerceBOMDefinitionId);
		attributeGetterFunctions.put(
			"companyId", CommerceBOMDefinition::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceBOMDefinition, Long>)
				CommerceBOMDefinition::setCompanyId);
		attributeGetterFunctions.put(
			"userId", CommerceBOMDefinition::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceBOMDefinition, Long>)
				CommerceBOMDefinition::setUserId);
		attributeGetterFunctions.put(
			"userName", CommerceBOMDefinition::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceBOMDefinition, String>)
				CommerceBOMDefinition::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceBOMDefinition::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceBOMDefinition, Date>)
				CommerceBOMDefinition::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceBOMDefinition::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceBOMDefinition, Date>)
				CommerceBOMDefinition::setModifiedDate);
		attributeGetterFunctions.put(
			"commerceBOMFolderId",
			CommerceBOMDefinition::getCommerceBOMFolderId);
		attributeSetterBiConsumers.put(
			"commerceBOMFolderId",
			(BiConsumer<CommerceBOMDefinition, Long>)
				CommerceBOMDefinition::setCommerceBOMFolderId);
		attributeGetterFunctions.put(
			"CPAttachmentFileEntryId",
			CommerceBOMDefinition::getCPAttachmentFileEntryId);
		attributeSetterBiConsumers.put(
			"CPAttachmentFileEntryId",
			(BiConsumer<CommerceBOMDefinition, Long>)
				CommerceBOMDefinition::setCPAttachmentFileEntryId);
		attributeGetterFunctions.put("name", CommerceBOMDefinition::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<CommerceBOMDefinition, String>)
				CommerceBOMDefinition::setName);
		attributeGetterFunctions.put(
			"friendlyUrl", CommerceBOMDefinition::getFriendlyUrl);
		attributeSetterBiConsumers.put(
			"friendlyUrl",
			(BiConsumer<CommerceBOMDefinition, String>)
				CommerceBOMDefinition::setFriendlyUrl);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceBOMDefinitionId() {
		return _commerceBOMDefinitionId;
	}

	@Override
	public void setCommerceBOMDefinitionId(long commerceBOMDefinitionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceBOMDefinitionId = commerceBOMDefinitionId;
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
	public long getCPAttachmentFileEntryId() {
		return _CPAttachmentFileEntryId;
	}

	@Override
	public void setCPAttachmentFileEntryId(long CPAttachmentFileEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CPAttachmentFileEntryId = CPAttachmentFileEntryId;
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
	public String getFriendlyUrl() {
		if (_friendlyUrl == null) {
			return "";
		}
		else {
			return _friendlyUrl;
		}
	}

	@Override
	public void setFriendlyUrl(String friendlyUrl) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_friendlyUrl = friendlyUrl;
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
			getCompanyId(), CommerceBOMDefinition.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceBOMDefinition toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceBOMDefinition>
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
		CommerceBOMDefinitionImpl commerceBOMDefinitionImpl =
			new CommerceBOMDefinitionImpl();

		commerceBOMDefinitionImpl.setCommerceBOMDefinitionId(
			getCommerceBOMDefinitionId());
		commerceBOMDefinitionImpl.setCompanyId(getCompanyId());
		commerceBOMDefinitionImpl.setUserId(getUserId());
		commerceBOMDefinitionImpl.setUserName(getUserName());
		commerceBOMDefinitionImpl.setCreateDate(getCreateDate());
		commerceBOMDefinitionImpl.setModifiedDate(getModifiedDate());
		commerceBOMDefinitionImpl.setCommerceBOMFolderId(
			getCommerceBOMFolderId());
		commerceBOMDefinitionImpl.setCPAttachmentFileEntryId(
			getCPAttachmentFileEntryId());
		commerceBOMDefinitionImpl.setName(getName());
		commerceBOMDefinitionImpl.setFriendlyUrl(getFriendlyUrl());

		commerceBOMDefinitionImpl.resetOriginalValues();

		return commerceBOMDefinitionImpl;
	}

	@Override
	public int compareTo(CommerceBOMDefinition commerceBOMDefinition) {
		int value = 0;

		value = getName().compareTo(commerceBOMDefinition.getName());

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

		if (!(object instanceof CommerceBOMDefinition)) {
			return false;
		}

		CommerceBOMDefinition commerceBOMDefinition =
			(CommerceBOMDefinition)object;

		long primaryKey = commerceBOMDefinition.getPrimaryKey();

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
	public CacheModel<CommerceBOMDefinition> toCacheModel() {
		CommerceBOMDefinitionCacheModel commerceBOMDefinitionCacheModel =
			new CommerceBOMDefinitionCacheModel();

		commerceBOMDefinitionCacheModel.commerceBOMDefinitionId =
			getCommerceBOMDefinitionId();

		commerceBOMDefinitionCacheModel.companyId = getCompanyId();

		commerceBOMDefinitionCacheModel.userId = getUserId();

		commerceBOMDefinitionCacheModel.userName = getUserName();

		String userName = commerceBOMDefinitionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceBOMDefinitionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceBOMDefinitionCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceBOMDefinitionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceBOMDefinitionCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceBOMDefinitionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceBOMDefinitionCacheModel.commerceBOMFolderId =
			getCommerceBOMFolderId();

		commerceBOMDefinitionCacheModel.CPAttachmentFileEntryId =
			getCPAttachmentFileEntryId();

		commerceBOMDefinitionCacheModel.name = getName();

		String name = commerceBOMDefinitionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceBOMDefinitionCacheModel.name = null;
		}

		commerceBOMDefinitionCacheModel.friendlyUrl = getFriendlyUrl();

		String friendlyUrl = commerceBOMDefinitionCacheModel.friendlyUrl;

		if ((friendlyUrl != null) && (friendlyUrl.length() == 0)) {
			commerceBOMDefinitionCacheModel.friendlyUrl = null;
		}

		return commerceBOMDefinitionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceBOMDefinition, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceBOMDefinition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceBOMDefinition, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((CommerceBOMDefinition)this));
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
		Map<String, Function<CommerceBOMDefinition, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceBOMDefinition, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceBOMDefinition, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((CommerceBOMDefinition)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceBOMDefinition>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _commerceBOMDefinitionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceBOMFolderId;
	private long _CPAttachmentFileEntryId;
	private String _name;
	private String _friendlyUrl;

	public <T> T getColumnValue(String columnName) {
		Function<CommerceBOMDefinition, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceBOMDefinition)this);
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
			"commerceBOMDefinitionId", _commerceBOMDefinitionId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("commerceBOMFolderId", _commerceBOMFolderId);
		_columnOriginalValues.put(
			"CPAttachmentFileEntryId", _CPAttachmentFileEntryId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("friendlyUrl", _friendlyUrl);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("commerceBOMDefinitionId", 1L);

		columnBitmasks.put("companyId", 2L);

		columnBitmasks.put("userId", 4L);

		columnBitmasks.put("userName", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("commerceBOMFolderId", 64L);

		columnBitmasks.put("CPAttachmentFileEntryId", 128L);

		columnBitmasks.put("name", 256L);

		columnBitmasks.put("friendlyUrl", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceBOMDefinition _escapedModel;

}