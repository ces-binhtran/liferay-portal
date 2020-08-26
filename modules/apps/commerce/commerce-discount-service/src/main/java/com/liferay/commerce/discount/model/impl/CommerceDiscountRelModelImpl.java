/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.discount.model.impl;

import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.model.CommerceDiscountRelModel;
import com.liferay.commerce.discount.model.CommerceDiscountRelSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.StringBundler;
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
 * The base model implementation for the CommerceDiscountRel service. Represents a row in the &quot;CommerceDiscountRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceDiscountRelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceDiscountRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountRelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceDiscountRelModelImpl
	extends BaseModelImpl<CommerceDiscountRel>
	implements CommerceDiscountRelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce discount rel model instance should use the <code>CommerceDiscountRel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceDiscountRel";

	public static final Object[][] TABLE_COLUMNS = {
		{"commerceDiscountRelId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"commerceDiscountId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceDiscountRelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceDiscountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceDiscountRel (commerceDiscountRelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceDiscountId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table CommerceDiscountRel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceDiscountRel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceDiscountRel.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.discount.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.discount.model.CommerceDiscountRel"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.discount.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.discount.model.CommerceDiscountRel"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.discount.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.discount.model.CommerceDiscountRel"),
		true);

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMMERCEDISCOUNTID_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceDiscountRel toModel(
		CommerceDiscountRelSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceDiscountRel model = new CommerceDiscountRelImpl();

		model.setCommerceDiscountRelId(soapModel.getCommerceDiscountRelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceDiscountId(soapModel.getCommerceDiscountId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceDiscountRel> toModels(
		CommerceDiscountRelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceDiscountRel> models = new ArrayList<CommerceDiscountRel>(
			soapModels.length);

		for (CommerceDiscountRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.discount.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.discount.model.CommerceDiscountRel"));

	public CommerceDiscountRelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceDiscountRelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceDiscountRelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceDiscountRelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceDiscountRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceDiscountRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceDiscountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceDiscountRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountRel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceDiscountRel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceDiscountRel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceDiscountRel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceDiscountRel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceDiscountRel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceDiscountRel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceDiscountRel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceDiscountRel.class.getClassLoader(),
			CommerceDiscountRel.class, ModelWrapper.class);

		try {
			Constructor<CommerceDiscountRel> constructor =
				(Constructor<CommerceDiscountRel>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceDiscountRel, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceDiscountRel, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceDiscountRel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<CommerceDiscountRel, Object>>();
		Map<String, BiConsumer<CommerceDiscountRel, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<CommerceDiscountRel, ?>>();

		attributeGetterFunctions.put(
			"commerceDiscountRelId",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getCommerceDiscountRelId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceDiscountRelId",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object commerceDiscountRelIdObject) {

					commerceDiscountRel.setCommerceDiscountRelId(
						(Long)commerceDiscountRelIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object companyIdObject) {

					commerceDiscountRel.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object userIdObject) {

					commerceDiscountRel.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object userNameObject) {

					commerceDiscountRel.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object createDateObject) {

					commerceDiscountRel.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object modifiedDateObject) {

					commerceDiscountRel.setModifiedDate(
						(Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceDiscountId",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getCommerceDiscountId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceDiscountId",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object commerceDiscountIdObject) {

					commerceDiscountRel.setCommerceDiscountId(
						(Long)commerceDiscountIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classNameId",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getClassNameId();
				}

			});
		attributeSetterBiConsumers.put(
			"classNameId",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object classNameIdObject) {

					commerceDiscountRel.setClassNameId((Long)classNameIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classPK",
			new Function<CommerceDiscountRel, Object>() {

				@Override
				public Object apply(CommerceDiscountRel commerceDiscountRel) {
					return commerceDiscountRel.getClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"classPK",
			new BiConsumer<CommerceDiscountRel, Object>() {

				@Override
				public void accept(
					CommerceDiscountRel commerceDiscountRel,
					Object classPKObject) {

					commerceDiscountRel.setClassPK((Long)classPKObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceDiscountRelId() {
		return _commerceDiscountRelId;
	}

	@Override
	public void setCommerceDiscountRelId(long commerceDiscountRelId) {
		_commerceDiscountRelId = commerceDiscountRelId;
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
	public long getCommerceDiscountId() {
		return _commerceDiscountId;
	}

	@Override
	public void setCommerceDiscountId(long commerceDiscountId) {
		_columnBitmask |= COMMERCEDISCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalCommerceDiscountId) {
			_setOriginalCommerceDiscountId = true;

			_originalCommerceDiscountId = _commerceDiscountId;
		}

		_commerceDiscountId = commerceDiscountId;
	}

	public long getOriginalCommerceDiscountId() {
		return _originalCommerceDiscountId;
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

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceDiscountRel.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceDiscountRel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceDiscountRel>
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
		CommerceDiscountRelImpl commerceDiscountRelImpl =
			new CommerceDiscountRelImpl();

		commerceDiscountRelImpl.setCommerceDiscountRelId(
			getCommerceDiscountRelId());
		commerceDiscountRelImpl.setCompanyId(getCompanyId());
		commerceDiscountRelImpl.setUserId(getUserId());
		commerceDiscountRelImpl.setUserName(getUserName());
		commerceDiscountRelImpl.setCreateDate(getCreateDate());
		commerceDiscountRelImpl.setModifiedDate(getModifiedDate());
		commerceDiscountRelImpl.setCommerceDiscountId(getCommerceDiscountId());
		commerceDiscountRelImpl.setClassNameId(getClassNameId());
		commerceDiscountRelImpl.setClassPK(getClassPK());

		commerceDiscountRelImpl.resetOriginalValues();

		return commerceDiscountRelImpl;
	}

	@Override
	public int compareTo(CommerceDiscountRel commerceDiscountRel) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceDiscountRel.getCreateDate());

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

		if (!(object instanceof CommerceDiscountRel)) {
			return false;
		}

		CommerceDiscountRel commerceDiscountRel = (CommerceDiscountRel)object;

		long primaryKey = commerceDiscountRel.getPrimaryKey();

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
		_setModifiedDate = false;
		_originalCommerceDiscountId = _commerceDiscountId;

		_setOriginalCommerceDiscountId = false;

		_originalClassNameId = _classNameId;

		_setOriginalClassNameId = false;

		_originalClassPK = _classPK;

		_setOriginalClassPK = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceDiscountRel> toCacheModel() {
		CommerceDiscountRelCacheModel commerceDiscountRelCacheModel =
			new CommerceDiscountRelCacheModel();

		commerceDiscountRelCacheModel.commerceDiscountRelId =
			getCommerceDiscountRelId();

		commerceDiscountRelCacheModel.companyId = getCompanyId();

		commerceDiscountRelCacheModel.userId = getUserId();

		commerceDiscountRelCacheModel.userName = getUserName();

		String userName = commerceDiscountRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceDiscountRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceDiscountRelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceDiscountRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceDiscountRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceDiscountRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceDiscountRelCacheModel.commerceDiscountId =
			getCommerceDiscountId();

		commerceDiscountRelCacheModel.classNameId = getClassNameId();

		commerceDiscountRelCacheModel.classPK = getClassPK();

		return commerceDiscountRelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceDiscountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceDiscountRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CommerceDiscountRel)this));
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
		Map<String, Function<CommerceDiscountRel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceDiscountRel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceDiscountRel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceDiscountRel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceDiscountRel>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _commerceDiscountRelId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceDiscountId;
	private long _originalCommerceDiscountId;
	private boolean _setOriginalCommerceDiscountId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _columnBitmask;
	private CommerceDiscountRel _escapedModel;

}