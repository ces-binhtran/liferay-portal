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

package com.liferay.commerce.tax.engine.fixed.service.base;

import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateAddressRel;
import com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateAddressRelService;
import com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateAddressRelServiceUtil;
import com.liferay.commerce.tax.engine.fixed.service.persistence.CommerceTaxFixedRateAddressRelFinder;
import com.liferay.commerce.tax.engine.fixed.service.persistence.CommerceTaxFixedRateAddressRelPersistence;
import com.liferay.commerce.tax.engine.fixed.service.persistence.CommerceTaxFixedRatePersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce tax fixed rate address rel remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.tax.engine.fixed.service.impl.CommerceTaxFixedRateAddressRelServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.tax.engine.fixed.service.impl.CommerceTaxFixedRateAddressRelServiceImpl
 * @generated
 */
public abstract class CommerceTaxFixedRateAddressRelServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceTaxFixedRateAddressRelService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceTaxFixedRateAddressRelService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceTaxFixedRateAddressRelServiceUtil</code>.
	 */

	/**
	 * Returns the commerce tax fixed rate local service.
	 *
	 * @return the commerce tax fixed rate local service
	 */
	public com.liferay.commerce.tax.engine.fixed.service.
		CommerceTaxFixedRateLocalService getCommerceTaxFixedRateLocalService() {

		return commerceTaxFixedRateLocalService;
	}

	/**
	 * Sets the commerce tax fixed rate local service.
	 *
	 * @param commerceTaxFixedRateLocalService the commerce tax fixed rate local service
	 */
	public void setCommerceTaxFixedRateLocalService(
		com.liferay.commerce.tax.engine.fixed.service.
			CommerceTaxFixedRateLocalService commerceTaxFixedRateLocalService) {

		this.commerceTaxFixedRateLocalService =
			commerceTaxFixedRateLocalService;
	}

	/**
	 * Returns the commerce tax fixed rate remote service.
	 *
	 * @return the commerce tax fixed rate remote service
	 */
	public
		com.liferay.commerce.tax.engine.fixed.service.
			CommerceTaxFixedRateService getCommerceTaxFixedRateService() {

		return commerceTaxFixedRateService;
	}

	/**
	 * Sets the commerce tax fixed rate remote service.
	 *
	 * @param commerceTaxFixedRateService the commerce tax fixed rate remote service
	 */
	public void setCommerceTaxFixedRateService(
		com.liferay.commerce.tax.engine.fixed.service.
			CommerceTaxFixedRateService commerceTaxFixedRateService) {

		this.commerceTaxFixedRateService = commerceTaxFixedRateService;
	}

	/**
	 * Returns the commerce tax fixed rate persistence.
	 *
	 * @return the commerce tax fixed rate persistence
	 */
	public CommerceTaxFixedRatePersistence
		getCommerceTaxFixedRatePersistence() {

		return commerceTaxFixedRatePersistence;
	}

	/**
	 * Sets the commerce tax fixed rate persistence.
	 *
	 * @param commerceTaxFixedRatePersistence the commerce tax fixed rate persistence
	 */
	public void setCommerceTaxFixedRatePersistence(
		CommerceTaxFixedRatePersistence commerceTaxFixedRatePersistence) {

		this.commerceTaxFixedRatePersistence = commerceTaxFixedRatePersistence;
	}

	/**
	 * Returns the commerce tax fixed rate address rel local service.
	 *
	 * @return the commerce tax fixed rate address rel local service
	 */
	public com.liferay.commerce.tax.engine.fixed.service.
		CommerceTaxFixedRateAddressRelLocalService
			getCommerceTaxFixedRateAddressRelLocalService() {

		return commerceTaxFixedRateAddressRelLocalService;
	}

	/**
	 * Sets the commerce tax fixed rate address rel local service.
	 *
	 * @param commerceTaxFixedRateAddressRelLocalService the commerce tax fixed rate address rel local service
	 */
	public void setCommerceTaxFixedRateAddressRelLocalService(
		com.liferay.commerce.tax.engine.fixed.service.
			CommerceTaxFixedRateAddressRelLocalService
				commerceTaxFixedRateAddressRelLocalService) {

		this.commerceTaxFixedRateAddressRelLocalService =
			commerceTaxFixedRateAddressRelLocalService;
	}

	/**
	 * Returns the commerce tax fixed rate address rel remote service.
	 *
	 * @return the commerce tax fixed rate address rel remote service
	 */
	public CommerceTaxFixedRateAddressRelService
		getCommerceTaxFixedRateAddressRelService() {

		return commerceTaxFixedRateAddressRelService;
	}

	/**
	 * Sets the commerce tax fixed rate address rel remote service.
	 *
	 * @param commerceTaxFixedRateAddressRelService the commerce tax fixed rate address rel remote service
	 */
	public void setCommerceTaxFixedRateAddressRelService(
		CommerceTaxFixedRateAddressRelService
			commerceTaxFixedRateAddressRelService) {

		this.commerceTaxFixedRateAddressRelService =
			commerceTaxFixedRateAddressRelService;
	}

	/**
	 * Returns the commerce tax fixed rate address rel persistence.
	 *
	 * @return the commerce tax fixed rate address rel persistence
	 */
	public CommerceTaxFixedRateAddressRelPersistence
		getCommerceTaxFixedRateAddressRelPersistence() {

		return commerceTaxFixedRateAddressRelPersistence;
	}

	/**
	 * Sets the commerce tax fixed rate address rel persistence.
	 *
	 * @param commerceTaxFixedRateAddressRelPersistence the commerce tax fixed rate address rel persistence
	 */
	public void setCommerceTaxFixedRateAddressRelPersistence(
		CommerceTaxFixedRateAddressRelPersistence
			commerceTaxFixedRateAddressRelPersistence) {

		this.commerceTaxFixedRateAddressRelPersistence =
			commerceTaxFixedRateAddressRelPersistence;
	}

	/**
	 * Returns the commerce tax fixed rate address rel finder.
	 *
	 * @return the commerce tax fixed rate address rel finder
	 */
	public CommerceTaxFixedRateAddressRelFinder
		getCommerceTaxFixedRateAddressRelFinder() {

		return commerceTaxFixedRateAddressRelFinder;
	}

	/**
	 * Sets the commerce tax fixed rate address rel finder.
	 *
	 * @param commerceTaxFixedRateAddressRelFinder the commerce tax fixed rate address rel finder
	 */
	public void setCommerceTaxFixedRateAddressRelFinder(
		CommerceTaxFixedRateAddressRelFinder
			commerceTaxFixedRateAddressRelFinder) {

		this.commerceTaxFixedRateAddressRelFinder =
			commerceTaxFixedRateAddressRelFinder;
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

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(commerceTaxFixedRateAddressRelService);
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
		return CommerceTaxFixedRateAddressRelService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceTaxFixedRateAddressRel.class;
	}

	protected String getModelClassName() {
		return CommerceTaxFixedRateAddressRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceTaxFixedRateAddressRelPersistence.getDataSource();

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
		CommerceTaxFixedRateAddressRelService
			commerceTaxFixedRateAddressRelService) {

		try {
			Field field =
				CommerceTaxFixedRateAddressRelServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, commerceTaxFixedRateAddressRelService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateLocalService.class
	)
	protected com.liferay.commerce.tax.engine.fixed.service.
		CommerceTaxFixedRateLocalService commerceTaxFixedRateLocalService;

	@BeanReference(
		type = com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateService.class
	)
	protected
		com.liferay.commerce.tax.engine.fixed.service.
			CommerceTaxFixedRateService commerceTaxFixedRateService;

	@BeanReference(type = CommerceTaxFixedRatePersistence.class)
	protected CommerceTaxFixedRatePersistence commerceTaxFixedRatePersistence;

	@BeanReference(
		type = com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateAddressRelLocalService.class
	)
	protected com.liferay.commerce.tax.engine.fixed.service.
		CommerceTaxFixedRateAddressRelLocalService
			commerceTaxFixedRateAddressRelLocalService;

	@BeanReference(type = CommerceTaxFixedRateAddressRelService.class)
	protected CommerceTaxFixedRateAddressRelService
		commerceTaxFixedRateAddressRelService;

	@BeanReference(type = CommerceTaxFixedRateAddressRelPersistence.class)
	protected CommerceTaxFixedRateAddressRelPersistence
		commerceTaxFixedRateAddressRelPersistence;

	@BeanReference(type = CommerceTaxFixedRateAddressRelFinder.class)
	protected CommerceTaxFixedRateAddressRelFinder
		commerceTaxFixedRateAddressRelFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

}