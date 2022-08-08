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

package com.liferay.commerce.service.base;

import com.liferay.commerce.model.CommerceAddressRestriction;
import com.liferay.commerce.service.CommerceAddressRestrictionService;
import com.liferay.commerce.service.CommerceAddressRestrictionServiceUtil;
import com.liferay.commerce.service.persistence.CommerceAddressRestrictionPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce address restriction remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CommerceAddressRestrictionServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CommerceAddressRestrictionServiceImpl
 * @generated
 */
public abstract class CommerceAddressRestrictionServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceAddressRestrictionService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceAddressRestrictionService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceAddressRestrictionServiceUtil</code>.
	 */

	/**
	 * Returns the commerce address restriction local service.
	 *
	 * @return the commerce address restriction local service
	 */
	public com.liferay.commerce.service.CommerceAddressRestrictionLocalService
		getCommerceAddressRestrictionLocalService() {

		return commerceAddressRestrictionLocalService;
	}

	/**
	 * Sets the commerce address restriction local service.
	 *
	 * @param commerceAddressRestrictionLocalService the commerce address restriction local service
	 */
	public void setCommerceAddressRestrictionLocalService(
		com.liferay.commerce.service.CommerceAddressRestrictionLocalService
			commerceAddressRestrictionLocalService) {

		this.commerceAddressRestrictionLocalService =
			commerceAddressRestrictionLocalService;
	}

	/**
	 * Returns the commerce address restriction remote service.
	 *
	 * @return the commerce address restriction remote service
	 */
	public CommerceAddressRestrictionService
		getCommerceAddressRestrictionService() {

		return commerceAddressRestrictionService;
	}

	/**
	 * Sets the commerce address restriction remote service.
	 *
	 * @param commerceAddressRestrictionService the commerce address restriction remote service
	 */
	public void setCommerceAddressRestrictionService(
		CommerceAddressRestrictionService commerceAddressRestrictionService) {

		this.commerceAddressRestrictionService =
			commerceAddressRestrictionService;
	}

	/**
	 * Returns the commerce address restriction persistence.
	 *
	 * @return the commerce address restriction persistence
	 */
	public CommerceAddressRestrictionPersistence
		getCommerceAddressRestrictionPersistence() {

		return commerceAddressRestrictionPersistence;
	}

	/**
	 * Sets the commerce address restriction persistence.
	 *
	 * @param commerceAddressRestrictionPersistence the commerce address restriction persistence
	 */
	public void setCommerceAddressRestrictionPersistence(
		CommerceAddressRestrictionPersistence
			commerceAddressRestrictionPersistence) {

		this.commerceAddressRestrictionPersistence =
			commerceAddressRestrictionPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(commerceAddressRestrictionService);
	}

	public void destroy() {
		_setServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceAddressRestrictionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceAddressRestriction.class;
	}

	protected String getModelClassName() {
		return CommerceAddressRestriction.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceAddressRestrictionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(
		CommerceAddressRestrictionService commerceAddressRestrictionService) {

		try {
			Field field =
				CommerceAddressRestrictionServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceAddressRestrictionService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.service.CommerceAddressRestrictionLocalService.class
	)
	protected
		com.liferay.commerce.service.CommerceAddressRestrictionLocalService
			commerceAddressRestrictionLocalService;

	@BeanReference(type = CommerceAddressRestrictionService.class)
	protected CommerceAddressRestrictionService
		commerceAddressRestrictionService;

	@BeanReference(type = CommerceAddressRestrictionPersistence.class)
	protected CommerceAddressRestrictionPersistence
		commerceAddressRestrictionPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}