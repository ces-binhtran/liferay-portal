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

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductEntryModel;
import com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap;
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
 * The base model implementation for the ProductEntry service. Represents a row in the &quot;Koroneiki_ProductEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ProductEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryImpl
 * @generated
 */
@JSON(strict = true)
public class ProductEntryModelImpl
	extends BaseModelImpl<ProductEntry> implements ProductEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a product entry model instance should use the <code>ProductEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ProductEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"productEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"productEntryKey", Types.VARCHAR},
		{"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("productEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("productEntryKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ProductEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,productEntryId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,productEntryKey VARCHAR(75) null,name VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ProductEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY productEntry.productEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ProductEntry.productEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long NAME_COLUMN_BITMASK = 2L;

	public static final long PRODUCTENTRYKEY_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long PRODUCTENTRYID_COLUMN_BITMASK = 16L;

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
	public static ProductEntry toModel(ProductEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ProductEntry model = new ProductEntryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setProductEntryId(soapModel.getProductEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setProductEntryKey(soapModel.getProductEntryKey());
		model.setName(soapModel.getName());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ProductEntry> toModels(ProductEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ProductEntry> models = new ArrayList<ProductEntry>(
			soapModels.length);

		for (ProductEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ProductEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _productEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProductEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _productEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ProductEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ProductEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ProductEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ProductEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ProductEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ProductEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ProductEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ProductEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ProductEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ProductEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ProductEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ProductEntry.class.getClassLoader(), ProductEntry.class,
			ModelWrapper.class);

		try {
			Constructor<ProductEntry> constructor =
				(Constructor<ProductEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ProductEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ProductEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ProductEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ProductEntry, Object>>();
		Map<String, BiConsumer<ProductEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ProductEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ProductEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ProductEntry, Long>)ProductEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", ProductEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<ProductEntry, String>)ProductEntry::setUuid);
		attributeGetterFunctions.put(
			"productEntryId", ProductEntry::getProductEntryId);
		attributeSetterBiConsumers.put(
			"productEntryId",
			(BiConsumer<ProductEntry, Long>)ProductEntry::setProductEntryId);
		attributeGetterFunctions.put("companyId", ProductEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ProductEntry, Long>)ProductEntry::setCompanyId);
		attributeGetterFunctions.put("userId", ProductEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<ProductEntry, Long>)ProductEntry::setUserId);
		attributeGetterFunctions.put("createDate", ProductEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<ProductEntry, Date>)ProductEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", ProductEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<ProductEntry, Date>)ProductEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"productEntryKey", ProductEntry::getProductEntryKey);
		attributeSetterBiConsumers.put(
			"productEntryKey",
			(BiConsumer<ProductEntry, String>)ProductEntry::setProductEntryKey);
		attributeGetterFunctions.put("name", ProductEntry::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<ProductEntry, String>)ProductEntry::setName);

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
	public long getProductEntryId() {
		return _productEntryId;
	}

	@Override
	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
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
	public String getProductEntryKey() {
		if (_productEntryKey == null) {
			return "";
		}
		else {
			return _productEntryKey;
		}
	}

	@Override
	public void setProductEntryKey(String productEntryKey) {
		_columnBitmask |= PRODUCTENTRYKEY_COLUMN_BITMASK;

		if (_originalProductEntryKey == null) {
			_originalProductEntryKey = _productEntryKey;
		}

		_productEntryKey = productEntryKey;
	}

	public String getOriginalProductEntryKey() {
		return GetterUtil.getString(_originalProductEntryKey);
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
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(ProductEntry.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ProductEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ProductEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ProductEntry>
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
		ProductEntryImpl productEntryImpl = new ProductEntryImpl();

		productEntryImpl.setMvccVersion(getMvccVersion());
		productEntryImpl.setUuid(getUuid());
		productEntryImpl.setProductEntryId(getProductEntryId());
		productEntryImpl.setCompanyId(getCompanyId());
		productEntryImpl.setUserId(getUserId());
		productEntryImpl.setCreateDate(getCreateDate());
		productEntryImpl.setModifiedDate(getModifiedDate());
		productEntryImpl.setProductEntryKey(getProductEntryKey());
		productEntryImpl.setName(getName());

		productEntryImpl.resetOriginalValues();

		return productEntryImpl;
	}

	@Override
	public int compareTo(ProductEntry productEntry) {
		long primaryKey = productEntry.getPrimaryKey();

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

		if (!(object instanceof ProductEntry)) {
			return false;
		}

		ProductEntry productEntry = (ProductEntry)object;

		long primaryKey = productEntry.getPrimaryKey();

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
		_originalProductEntryKey = _productEntryKey;

		_originalName = _name;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<ProductEntry> toCacheModel() {
		ProductEntryCacheModel productEntryCacheModel =
			new ProductEntryCacheModel();

		productEntryCacheModel.mvccVersion = getMvccVersion();

		productEntryCacheModel.uuid = getUuid();

		String uuid = productEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			productEntryCacheModel.uuid = null;
		}

		productEntryCacheModel.productEntryId = getProductEntryId();

		productEntryCacheModel.companyId = getCompanyId();

		productEntryCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			productEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			productEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			productEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			productEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		productEntryCacheModel.productEntryKey = getProductEntryKey();

		String productEntryKey = productEntryCacheModel.productEntryKey;

		if ((productEntryKey != null) && (productEntryKey.length() == 0)) {
			productEntryCacheModel.productEntryKey = null;
		}

		productEntryCacheModel.name = getName();

		String name = productEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			productEntryCacheModel.name = null;
		}

		return productEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ProductEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ProductEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ProductEntry)this));
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
		Map<String, Function<ProductEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ProductEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ProductEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ProductEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ProductEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _productEntryId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _productEntryKey;
	private String _originalProductEntryKey;
	private String _name;
	private String _originalName;
	private long _columnBitmask;
	private ProductEntry _escapedModel;

}