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

package com.liferay.osb.koroneiki.phytohormone.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementModel;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementSoap;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the Entitlement service. Represents a row in the &quot;Koroneiki_Entitlement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>EntitlementModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EntitlementImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementImpl
 * @generated
 */
@JSON(strict = true)
public class EntitlementModelImpl
	extends BaseModelImpl<Entitlement> implements EntitlementModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a entitlement model instance should use the <code>Entitlement</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_Entitlement";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"entitlementId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"entitlementDefinitionId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("entitlementId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("entitlementDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_Entitlement (mvccVersion LONG default 0 not null,entitlementId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,entitlementDefinitionId LONG,classNameId LONG,classPK LONG,name VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_Entitlement";

	public static final String ORDER_BY_JPQL = " ORDER BY entitlement.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_Entitlement.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long ENTITLEMENTDEFINITIONID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

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
	public static Entitlement toModel(EntitlementSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Entitlement model = new EntitlementImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setEntitlementId(soapModel.getEntitlementId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setEntitlementDefinitionId(
			soapModel.getEntitlementDefinitionId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setName(soapModel.getName());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Entitlement> toModels(EntitlementSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Entitlement> models = new ArrayList<Entitlement>(
			soapModels.length);

		for (EntitlementSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public EntitlementModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _entitlementId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntitlementId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entitlementId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Entitlement.class;
	}

	@Override
	public String getModelClassName() {
		return Entitlement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Entitlement, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Entitlement, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Entitlement, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Entitlement)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Entitlement, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Entitlement, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Entitlement)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Entitlement, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Entitlement, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Entitlement>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Entitlement.class.getClassLoader(), Entitlement.class,
			ModelWrapper.class);

		try {
			Constructor<Entitlement> constructor =
				(Constructor<Entitlement>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Entitlement, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Entitlement, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Entitlement, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Entitlement, Object>>();
		Map<String, BiConsumer<Entitlement, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Entitlement, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", Entitlement::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<Entitlement, Long>)Entitlement::setMvccVersion);
		attributeGetterFunctions.put(
			"entitlementId", Entitlement::getEntitlementId);
		attributeSetterBiConsumers.put(
			"entitlementId",
			(BiConsumer<Entitlement, Long>)Entitlement::setEntitlementId);
		attributeGetterFunctions.put("companyId", Entitlement::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<Entitlement, Long>)Entitlement::setCompanyId);
		attributeGetterFunctions.put("userId", Entitlement::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Entitlement, Long>)Entitlement::setUserId);
		attributeGetterFunctions.put("createDate", Entitlement::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<Entitlement, Date>)Entitlement::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", Entitlement::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Entitlement, Date>)Entitlement::setModifiedDate);
		attributeGetterFunctions.put(
			"entitlementDefinitionId", Entitlement::getEntitlementDefinitionId);
		attributeSetterBiConsumers.put(
			"entitlementDefinitionId",
			(BiConsumer<Entitlement, Long>)
				Entitlement::setEntitlementDefinitionId);
		attributeGetterFunctions.put(
			"classNameId", Entitlement::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<Entitlement, Long>)Entitlement::setClassNameId);
		attributeGetterFunctions.put("classPK", Entitlement::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK", (BiConsumer<Entitlement, Long>)Entitlement::setClassPK);
		attributeGetterFunctions.put("name", Entitlement::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Entitlement, String>)Entitlement::setName);

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
	public long getEntitlementId() {
		return _entitlementId;
	}

	@Override
	public void setEntitlementId(long entitlementId) {
		_entitlementId = entitlementId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public long getEntitlementDefinitionId() {
		return _entitlementDefinitionId;
	}

	@Override
	public void setEntitlementDefinitionId(long entitlementDefinitionId) {
		_columnBitmask |= ENTITLEMENTDEFINITIONID_COLUMN_BITMASK;

		if (!_setOriginalEntitlementDefinitionId) {
			_setOriginalEntitlementDefinitionId = true;

			_originalEntitlementDefinitionId = _entitlementDefinitionId;
		}

		_entitlementDefinitionId = entitlementDefinitionId;
	}

	public long getOriginalEntitlementDefinitionId() {
		return _originalEntitlementDefinitionId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
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
		_name = name;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Entitlement.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Entitlement toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Entitlement>
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
		EntitlementImpl entitlementImpl = new EntitlementImpl();

		entitlementImpl.setMvccVersion(getMvccVersion());
		entitlementImpl.setEntitlementId(getEntitlementId());
		entitlementImpl.setCompanyId(getCompanyId());
		entitlementImpl.setUserId(getUserId());
		entitlementImpl.setCreateDate(getCreateDate());
		entitlementImpl.setModifiedDate(getModifiedDate());
		entitlementImpl.setEntitlementDefinitionId(
			getEntitlementDefinitionId());
		entitlementImpl.setClassNameId(getClassNameId());
		entitlementImpl.setClassPK(getClassPK());
		entitlementImpl.setName(getName());

		entitlementImpl.resetOriginalValues();

		return entitlementImpl;
	}

	@Override
	public int compareTo(Entitlement entitlement) {
		int value = 0;

		value = getName().compareTo(entitlement.getName());

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

		if (!(object instanceof Entitlement)) {
			return false;
		}

		Entitlement entitlement = (Entitlement)object;

		long primaryKey = entitlement.getPrimaryKey();

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
		_setModifiedDate = false;
		_originalEntitlementDefinitionId = _entitlementDefinitionId;

		_setOriginalEntitlementDefinitionId = false;

		_originalClassNameId = _classNameId;

		_setOriginalClassNameId = false;

		_originalClassPK = _classPK;

		_setOriginalClassPK = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Entitlement> toCacheModel() {
		EntitlementCacheModel entitlementCacheModel =
			new EntitlementCacheModel();

		entitlementCacheModel.mvccVersion = getMvccVersion();

		entitlementCacheModel.entitlementId = getEntitlementId();

		entitlementCacheModel.companyId = getCompanyId();

		entitlementCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			entitlementCacheModel.createDate = createDate.getTime();
		}
		else {
			entitlementCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			entitlementCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			entitlementCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		entitlementCacheModel.entitlementDefinitionId =
			getEntitlementDefinitionId();

		entitlementCacheModel.classNameId = getClassNameId();

		entitlementCacheModel.classPK = getClassPK();

		entitlementCacheModel.name = getName();

		String name = entitlementCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			entitlementCacheModel.name = null;
		}

		return entitlementCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Entitlement, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Entitlement, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Entitlement, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Entitlement)this));
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
		Map<String, Function<Entitlement, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Entitlement, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Entitlement, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Entitlement)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Entitlement>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _entitlementId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _entitlementDefinitionId;
	private long _originalEntitlementDefinitionId;
	private boolean _setOriginalEntitlementDefinitionId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _name;
	private long _columnBitmask;
	private Entitlement _escapedModel;

}