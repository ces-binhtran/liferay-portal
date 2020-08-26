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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelModel;
import com.liferay.commerce.product.model.CommerceChannelSoap;
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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

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
 * The base model implementation for the CommerceChannel service. Represents a row in the &quot;CommerceChannel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceChannelModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceChannelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceChannelModelImpl
	extends BaseModelImpl<CommerceChannel> implements CommerceChannelModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce channel model instance should use the <code>CommerceChannel</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceChannel";

	public static final Object[][] TABLE_COLUMNS = {
		{"externalReferenceCode", Types.VARCHAR},
		{"commerceChannelId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"siteGroupId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"type_", Types.VARCHAR}, {"typeSettings", Types.VARCHAR},
		{"commerceCurrencyCode", Types.VARCHAR},
		{"priceDisplayType", Types.VARCHAR},
		{"discountsTargetNetPrice", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceChannelId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("siteGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceCurrencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priceDisplayType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("discountsTargetNetPrice", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceChannel (externalReferenceCode VARCHAR(75) null,commerceChannelId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,siteGroupId LONG,name VARCHAR(75) null,type_ VARCHAR(75) null,typeSettings VARCHAR(75) null,commerceCurrencyCode VARCHAR(75) null,priceDisplayType VARCHAR(75) null,discountsTargetNetPrice BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CommerceChannel";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceChannel.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceChannel.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.product.model.CommerceChannel"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.product.model.CommerceChannel"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.product.model.CommerceChannel"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	public static final long SITEGROUPID_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceChannel toModel(CommerceChannelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceChannel model = new CommerceChannelImpl();

		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCommerceChannelId(soapModel.getCommerceChannelId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setSiteGroupId(soapModel.getSiteGroupId());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setCommerceCurrencyCode(soapModel.getCommerceCurrencyCode());
		model.setPriceDisplayType(soapModel.getPriceDisplayType());
		model.setDiscountsTargetNetPrice(soapModel.isDiscountsTargetNetPrice());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceChannel> toModels(
		CommerceChannelSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceChannel> models = new ArrayList<CommerceChannel>(
			soapModels.length);

		for (CommerceChannelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.product.model.CommerceChannel"));

	public CommerceChannelModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceChannelId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceChannelId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceChannelId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceChannel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceChannel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceChannel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceChannel, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceChannel)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceChannel, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceChannel, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceChannel)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceChannel, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceChannel, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceChannel>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceChannel.class.getClassLoader(), CommerceChannel.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceChannel> constructor =
				(Constructor<CommerceChannel>)proxyClass.getConstructor(
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

	private static final Map<String, Function<CommerceChannel, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceChannel, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceChannel, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<CommerceChannel, Object>>();
		Map<String, BiConsumer<CommerceChannel, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CommerceChannel, ?>>();

		attributeGetterFunctions.put(
			"externalReferenceCode",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getExternalReferenceCode();
				}

			});
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object externalReferenceCodeObject) {

					commerceChannel.setExternalReferenceCode(
						(String)externalReferenceCodeObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceChannelId",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getCommerceChannelId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceChannelId",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object commerceChannelIdObject) {

					commerceChannel.setCommerceChannelId(
						(Long)commerceChannelIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object companyIdObject) {

					commerceChannel.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object userIdObject) {

					commerceChannel.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object userNameObject) {

					commerceChannel.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object createDateObject) {

					commerceChannel.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object modifiedDateObject) {

					commerceChannel.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"siteGroupId",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getSiteGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"siteGroupId",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object siteGroupIdObject) {

					commerceChannel.setSiteGroupId((Long)siteGroupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object nameObject) {

					commerceChannel.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"type",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getType();
				}

			});
		attributeSetterBiConsumers.put(
			"type",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel, Object typeObject) {

					commerceChannel.setType((String)typeObject);
				}

			});
		attributeGetterFunctions.put(
			"typeSettings",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getTypeSettings();
				}

			});
		attributeSetterBiConsumers.put(
			"typeSettings",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object typeSettingsObject) {

					commerceChannel.setTypeSettings((String)typeSettingsObject);
				}

			});
		attributeGetterFunctions.put(
			"commerceCurrencyCode",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getCommerceCurrencyCode();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceCurrencyCode",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object commerceCurrencyCodeObject) {

					commerceChannel.setCommerceCurrencyCode(
						(String)commerceCurrencyCodeObject);
				}

			});
		attributeGetterFunctions.put(
			"priceDisplayType",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getPriceDisplayType();
				}

			});
		attributeSetterBiConsumers.put(
			"priceDisplayType",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object priceDisplayTypeObject) {

					commerceChannel.setPriceDisplayType(
						(String)priceDisplayTypeObject);
				}

			});
		attributeGetterFunctions.put(
			"discountsTargetNetPrice",
			new Function<CommerceChannel, Object>() {

				@Override
				public Object apply(CommerceChannel commerceChannel) {
					return commerceChannel.getDiscountsTargetNetPrice();
				}

			});
		attributeSetterBiConsumers.put(
			"discountsTargetNetPrice",
			new BiConsumer<CommerceChannel, Object>() {

				@Override
				public void accept(
					CommerceChannel commerceChannel,
					Object discountsTargetNetPriceObject) {

					commerceChannel.setDiscountsTargetNetPrice(
						(Boolean)discountsTargetNetPriceObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getCommerceChannelId() {
		return _commerceChannelId;
	}

	@Override
	public void setCommerceChannelId(long commerceChannelId) {
		_commerceChannelId = commerceChannelId;
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
	public long getSiteGroupId() {
		return _siteGroupId;
	}

	@Override
	public void setSiteGroupId(long siteGroupId) {
		_columnBitmask |= SITEGROUPID_COLUMN_BITMASK;

		if (!_setOriginalSiteGroupId) {
			_setOriginalSiteGroupId = true;

			_originalSiteGroupId = _siteGroupId;
		}

		_siteGroupId = siteGroupId;
	}

	public long getOriginalSiteGroupId() {
		return _originalSiteGroupId;
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
		_type = type;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public String getCommerceCurrencyCode() {
		if (_commerceCurrencyCode == null) {
			return "";
		}
		else {
			return _commerceCurrencyCode;
		}
	}

	@Override
	public void setCommerceCurrencyCode(String commerceCurrencyCode) {
		_commerceCurrencyCode = commerceCurrencyCode;
	}

	@JSON
	@Override
	public String getPriceDisplayType() {
		if (_priceDisplayType == null) {
			return "";
		}
		else {
			return _priceDisplayType;
		}
	}

	@Override
	public void setPriceDisplayType(String priceDisplayType) {
		_priceDisplayType = priceDisplayType;
	}

	@JSON
	@Override
	public boolean getDiscountsTargetNetPrice() {
		return _discountsTargetNetPrice;
	}

	@JSON
	@Override
	public boolean isDiscountsTargetNetPrice() {
		return _discountsTargetNetPrice;
	}

	@Override
	public void setDiscountsTargetNetPrice(boolean discountsTargetNetPrice) {
		_discountsTargetNetPrice = discountsTargetNetPrice;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceChannel.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceChannel toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceChannel>
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
		CommerceChannelImpl commerceChannelImpl = new CommerceChannelImpl();

		commerceChannelImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceChannelImpl.setCommerceChannelId(getCommerceChannelId());
		commerceChannelImpl.setCompanyId(getCompanyId());
		commerceChannelImpl.setUserId(getUserId());
		commerceChannelImpl.setUserName(getUserName());
		commerceChannelImpl.setCreateDate(getCreateDate());
		commerceChannelImpl.setModifiedDate(getModifiedDate());
		commerceChannelImpl.setSiteGroupId(getSiteGroupId());
		commerceChannelImpl.setName(getName());
		commerceChannelImpl.setType(getType());
		commerceChannelImpl.setTypeSettings(getTypeSettings());
		commerceChannelImpl.setCommerceCurrencyCode(getCommerceCurrencyCode());
		commerceChannelImpl.setPriceDisplayType(getPriceDisplayType());
		commerceChannelImpl.setDiscountsTargetNetPrice(
			isDiscountsTargetNetPrice());

		commerceChannelImpl.resetOriginalValues();

		return commerceChannelImpl;
	}

	@Override
	public int compareTo(CommerceChannel commerceChannel) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceChannel.getCreateDate());

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

		if (!(object instanceof CommerceChannel)) {
			return false;
		}

		CommerceChannel commerceChannel = (CommerceChannel)object;

		long primaryKey = commerceChannel.getPrimaryKey();

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
		_originalExternalReferenceCode = _externalReferenceCode;

		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;
		_originalSiteGroupId = _siteGroupId;

		_setOriginalSiteGroupId = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceChannel> toCacheModel() {
		CommerceChannelCacheModel commerceChannelCacheModel =
			new CommerceChannelCacheModel();

		commerceChannelCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceChannelCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceChannelCacheModel.externalReferenceCode = null;
		}

		commerceChannelCacheModel.commerceChannelId = getCommerceChannelId();

		commerceChannelCacheModel.companyId = getCompanyId();

		commerceChannelCacheModel.userId = getUserId();

		commerceChannelCacheModel.userName = getUserName();

		String userName = commerceChannelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceChannelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceChannelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceChannelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceChannelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceChannelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceChannelCacheModel.siteGroupId = getSiteGroupId();

		commerceChannelCacheModel.name = getName();

		String name = commerceChannelCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceChannelCacheModel.name = null;
		}

		commerceChannelCacheModel.type = getType();

		String type = commerceChannelCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			commerceChannelCacheModel.type = null;
		}

		commerceChannelCacheModel.typeSettings = getTypeSettings();

		String typeSettings = commerceChannelCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			commerceChannelCacheModel.typeSettings = null;
		}

		commerceChannelCacheModel.commerceCurrencyCode =
			getCommerceCurrencyCode();

		String commerceCurrencyCode =
			commerceChannelCacheModel.commerceCurrencyCode;

		if ((commerceCurrencyCode != null) &&
			(commerceCurrencyCode.length() == 0)) {

			commerceChannelCacheModel.commerceCurrencyCode = null;
		}

		commerceChannelCacheModel.priceDisplayType = getPriceDisplayType();

		String priceDisplayType = commerceChannelCacheModel.priceDisplayType;

		if ((priceDisplayType != null) && (priceDisplayType.length() == 0)) {
			commerceChannelCacheModel.priceDisplayType = null;
		}

		commerceChannelCacheModel.discountsTargetNetPrice =
			isDiscountsTargetNetPrice();

		return commerceChannelCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceChannel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceChannel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CommerceChannel)this));
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
		Map<String, Function<CommerceChannel, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceChannel, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceChannel, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceChannel)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceChannel>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _commerceChannelId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _siteGroupId;
	private long _originalSiteGroupId;
	private boolean _setOriginalSiteGroupId;
	private String _name;
	private String _type;
	private String _typeSettings;
	private String _commerceCurrencyCode;
	private String _priceDisplayType;
	private boolean _discountsTargetNetPrice;
	private long _columnBitmask;
	private CommerceChannel _escapedModel;

}