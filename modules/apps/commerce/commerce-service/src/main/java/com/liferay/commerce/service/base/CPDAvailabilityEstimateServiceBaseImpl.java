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

import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.service.CPDAvailabilityEstimateService;
import com.liferay.commerce.service.CPDAvailabilityEstimateServiceUtil;
import com.liferay.commerce.service.persistence.CPDAvailabilityEstimatePersistence;
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
 * Provides the base implementation for the cpd availability estimate remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CPDAvailabilityEstimateServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CPDAvailabilityEstimateServiceImpl
 * @generated
 */
public abstract class CPDAvailabilityEstimateServiceBaseImpl
	extends BaseServiceImpl
	implements CPDAvailabilityEstimateService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDAvailabilityEstimateService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CPDAvailabilityEstimateServiceUtil</code>.
	 */

	/**
	 * Returns the cpd availability estimate local service.
	 *
	 * @return the cpd availability estimate local service
	 */
	public com.liferay.commerce.service.CPDAvailabilityEstimateLocalService
		getCPDAvailabilityEstimateLocalService() {

		return cpdAvailabilityEstimateLocalService;
	}

	/**
	 * Sets the cpd availability estimate local service.
	 *
	 * @param cpdAvailabilityEstimateLocalService the cpd availability estimate local service
	 */
	public void setCPDAvailabilityEstimateLocalService(
		com.liferay.commerce.service.CPDAvailabilityEstimateLocalService
			cpdAvailabilityEstimateLocalService) {

		this.cpdAvailabilityEstimateLocalService =
			cpdAvailabilityEstimateLocalService;
	}

	/**
	 * Returns the cpd availability estimate remote service.
	 *
	 * @return the cpd availability estimate remote service
	 */
	public CPDAvailabilityEstimateService getCPDAvailabilityEstimateService() {
		return cpdAvailabilityEstimateService;
	}

	/**
	 * Sets the cpd availability estimate remote service.
	 *
	 * @param cpdAvailabilityEstimateService the cpd availability estimate remote service
	 */
	public void setCPDAvailabilityEstimateService(
		CPDAvailabilityEstimateService cpdAvailabilityEstimateService) {

		this.cpdAvailabilityEstimateService = cpdAvailabilityEstimateService;
	}

	/**
	 * Returns the cpd availability estimate persistence.
	 *
	 * @return the cpd availability estimate persistence
	 */
	public CPDAvailabilityEstimatePersistence
		getCPDAvailabilityEstimatePersistence() {

		return cpdAvailabilityEstimatePersistence;
	}

	/**
	 * Sets the cpd availability estimate persistence.
	 *
	 * @param cpdAvailabilityEstimatePersistence the cpd availability estimate persistence
	 */
	public void setCPDAvailabilityEstimatePersistence(
		CPDAvailabilityEstimatePersistence cpdAvailabilityEstimatePersistence) {

		this.cpdAvailabilityEstimatePersistence =
			cpdAvailabilityEstimatePersistence;
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
		_setServiceUtilService(cpdAvailabilityEstimateService);
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
		return CPDAvailabilityEstimateService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDAvailabilityEstimate.class;
	}

	protected String getModelClassName() {
		return CPDAvailabilityEstimate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpdAvailabilityEstimatePersistence.getDataSource();

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
		CPDAvailabilityEstimateService cpdAvailabilityEstimateService) {

		try {
			Field field =
				CPDAvailabilityEstimateServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, cpdAvailabilityEstimateService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.service.CPDAvailabilityEstimateLocalService.class
	)
	protected com.liferay.commerce.service.CPDAvailabilityEstimateLocalService
		cpdAvailabilityEstimateLocalService;

	@BeanReference(type = CPDAvailabilityEstimateService.class)
	protected CPDAvailabilityEstimateService cpdAvailabilityEstimateService;

	@BeanReference(type = CPDAvailabilityEstimatePersistence.class)
	protected CPDAvailabilityEstimatePersistence
		cpdAvailabilityEstimatePersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}