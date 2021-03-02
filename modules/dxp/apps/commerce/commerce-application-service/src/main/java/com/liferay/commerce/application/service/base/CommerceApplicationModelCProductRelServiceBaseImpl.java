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

package com.liferay.commerce.application.service.base;

import com.liferay.commerce.application.model.CommerceApplicationModelCProductRel;
import com.liferay.commerce.application.service.CommerceApplicationModelCProductRelService;
import com.liferay.commerce.application.service.CommerceApplicationModelCProductRelServiceUtil;
import com.liferay.commerce.application.service.persistence.CommerceApplicationBrandPersistence;
import com.liferay.commerce.application.service.persistence.CommerceApplicationModelCProductRelPersistence;
import com.liferay.commerce.application.service.persistence.CommerceApplicationModelPersistence;
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
 * Provides the base implementation for the commerce application model c product rel remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.application.service.impl.CommerceApplicationModelCProductRelServiceImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.application.service.impl.CommerceApplicationModelCProductRelServiceImpl
 * @generated
 */
public abstract class CommerceApplicationModelCProductRelServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceApplicationModelCProductRelService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceApplicationModelCProductRelService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceApplicationModelCProductRelServiceUtil</code>.
	 */

	/**
	 * Returns the commerce application brand local service.
	 *
	 * @return the commerce application brand local service
	 */
	public com.liferay.commerce.application.service.
		CommerceApplicationBrandLocalService
			getCommerceApplicationBrandLocalService() {

		return commerceApplicationBrandLocalService;
	}

	/**
	 * Sets the commerce application brand local service.
	 *
	 * @param commerceApplicationBrandLocalService the commerce application brand local service
	 */
	public void setCommerceApplicationBrandLocalService(
		com.liferay.commerce.application.service.
			CommerceApplicationBrandLocalService
				commerceApplicationBrandLocalService) {

		this.commerceApplicationBrandLocalService =
			commerceApplicationBrandLocalService;
	}

	/**
	 * Returns the commerce application brand remote service.
	 *
	 * @return the commerce application brand remote service
	 */
	public
		com.liferay.commerce.application.service.CommerceApplicationBrandService
			getCommerceApplicationBrandService() {

		return commerceApplicationBrandService;
	}

	/**
	 * Sets the commerce application brand remote service.
	 *
	 * @param commerceApplicationBrandService the commerce application brand remote service
	 */
	public void setCommerceApplicationBrandService(
		com.liferay.commerce.application.service.CommerceApplicationBrandService
			commerceApplicationBrandService) {

		this.commerceApplicationBrandService = commerceApplicationBrandService;
	}

	/**
	 * Returns the commerce application brand persistence.
	 *
	 * @return the commerce application brand persistence
	 */
	public CommerceApplicationBrandPersistence
		getCommerceApplicationBrandPersistence() {

		return commerceApplicationBrandPersistence;
	}

	/**
	 * Sets the commerce application brand persistence.
	 *
	 * @param commerceApplicationBrandPersistence the commerce application brand persistence
	 */
	public void setCommerceApplicationBrandPersistence(
		CommerceApplicationBrandPersistence
			commerceApplicationBrandPersistence) {

		this.commerceApplicationBrandPersistence =
			commerceApplicationBrandPersistence;
	}

	/**
	 * Returns the commerce application model local service.
	 *
	 * @return the commerce application model local service
	 */
	public com.liferay.commerce.application.service.
		CommerceApplicationModelLocalService
			getCommerceApplicationModelLocalService() {

		return commerceApplicationModelLocalService;
	}

	/**
	 * Sets the commerce application model local service.
	 *
	 * @param commerceApplicationModelLocalService the commerce application model local service
	 */
	public void setCommerceApplicationModelLocalService(
		com.liferay.commerce.application.service.
			CommerceApplicationModelLocalService
				commerceApplicationModelLocalService) {

		this.commerceApplicationModelLocalService =
			commerceApplicationModelLocalService;
	}

	/**
	 * Returns the commerce application model remote service.
	 *
	 * @return the commerce application model remote service
	 */
	public
		com.liferay.commerce.application.service.CommerceApplicationModelService
			getCommerceApplicationModelService() {

		return commerceApplicationModelService;
	}

	/**
	 * Sets the commerce application model remote service.
	 *
	 * @param commerceApplicationModelService the commerce application model remote service
	 */
	public void setCommerceApplicationModelService(
		com.liferay.commerce.application.service.CommerceApplicationModelService
			commerceApplicationModelService) {

		this.commerceApplicationModelService = commerceApplicationModelService;
	}

	/**
	 * Returns the commerce application model persistence.
	 *
	 * @return the commerce application model persistence
	 */
	public CommerceApplicationModelPersistence
		getCommerceApplicationModelPersistence() {

		return commerceApplicationModelPersistence;
	}

	/**
	 * Sets the commerce application model persistence.
	 *
	 * @param commerceApplicationModelPersistence the commerce application model persistence
	 */
	public void setCommerceApplicationModelPersistence(
		CommerceApplicationModelPersistence
			commerceApplicationModelPersistence) {

		this.commerceApplicationModelPersistence =
			commerceApplicationModelPersistence;
	}

	/**
	 * Returns the commerce application model c product rel local service.
	 *
	 * @return the commerce application model c product rel local service
	 */
	public com.liferay.commerce.application.service.
		CommerceApplicationModelCProductRelLocalService
			getCommerceApplicationModelCProductRelLocalService() {

		return commerceApplicationModelCProductRelLocalService;
	}

	/**
	 * Sets the commerce application model c product rel local service.
	 *
	 * @param commerceApplicationModelCProductRelLocalService the commerce application model c product rel local service
	 */
	public void setCommerceApplicationModelCProductRelLocalService(
		com.liferay.commerce.application.service.
			CommerceApplicationModelCProductRelLocalService
				commerceApplicationModelCProductRelLocalService) {

		this.commerceApplicationModelCProductRelLocalService =
			commerceApplicationModelCProductRelLocalService;
	}

	/**
	 * Returns the commerce application model c product rel remote service.
	 *
	 * @return the commerce application model c product rel remote service
	 */
	public CommerceApplicationModelCProductRelService
		getCommerceApplicationModelCProductRelService() {

		return commerceApplicationModelCProductRelService;
	}

	/**
	 * Sets the commerce application model c product rel remote service.
	 *
	 * @param commerceApplicationModelCProductRelService the commerce application model c product rel remote service
	 */
	public void setCommerceApplicationModelCProductRelService(
		CommerceApplicationModelCProductRelService
			commerceApplicationModelCProductRelService) {

		this.commerceApplicationModelCProductRelService =
			commerceApplicationModelCProductRelService;
	}

	/**
	 * Returns the commerce application model c product rel persistence.
	 *
	 * @return the commerce application model c product rel persistence
	 */
	public CommerceApplicationModelCProductRelPersistence
		getCommerceApplicationModelCProductRelPersistence() {

		return commerceApplicationModelCProductRelPersistence;
	}

	/**
	 * Sets the commerce application model c product rel persistence.
	 *
	 * @param commerceApplicationModelCProductRelPersistence the commerce application model c product rel persistence
	 */
	public void setCommerceApplicationModelCProductRelPersistence(
		CommerceApplicationModelCProductRelPersistence
			commerceApplicationModelCProductRelPersistence) {

		this.commerceApplicationModelCProductRelPersistence =
			commerceApplicationModelCProductRelPersistence;
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
		_setServiceUtilService(commerceApplicationModelCProductRelService);
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
		return CommerceApplicationModelCProductRelService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceApplicationModelCProductRel.class;
	}

	protected String getModelClassName() {
		return CommerceApplicationModelCProductRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceApplicationModelCProductRelPersistence.getDataSource();

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
		CommerceApplicationModelCProductRelService
			commerceApplicationModelCProductRelService) {

		try {
			Field field =
				CommerceApplicationModelCProductRelServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, commerceApplicationModelCProductRelService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationBrandLocalService.class
	)
	protected com.liferay.commerce.application.service.
		CommerceApplicationBrandLocalService
			commerceApplicationBrandLocalService;

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationBrandService.class
	)
	protected
		com.liferay.commerce.application.service.CommerceApplicationBrandService
			commerceApplicationBrandService;

	@BeanReference(type = CommerceApplicationBrandPersistence.class)
	protected CommerceApplicationBrandPersistence
		commerceApplicationBrandPersistence;

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationModelLocalService.class
	)
	protected com.liferay.commerce.application.service.
		CommerceApplicationModelLocalService
			commerceApplicationModelLocalService;

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationModelService.class
	)
	protected
		com.liferay.commerce.application.service.CommerceApplicationModelService
			commerceApplicationModelService;

	@BeanReference(type = CommerceApplicationModelPersistence.class)
	protected CommerceApplicationModelPersistence
		commerceApplicationModelPersistence;

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationModelCProductRelLocalService.class
	)
	protected com.liferay.commerce.application.service.
		CommerceApplicationModelCProductRelLocalService
			commerceApplicationModelCProductRelLocalService;

	@BeanReference(type = CommerceApplicationModelCProductRelService.class)
	protected CommerceApplicationModelCProductRelService
		commerceApplicationModelCProductRelService;

	@BeanReference(type = CommerceApplicationModelCProductRelPersistence.class)
	protected CommerceApplicationModelCProductRelPersistence
		commerceApplicationModelCProductRelPersistence;

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