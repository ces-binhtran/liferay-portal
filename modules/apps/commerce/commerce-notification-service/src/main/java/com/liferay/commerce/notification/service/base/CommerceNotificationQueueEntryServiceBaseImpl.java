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

package com.liferay.commerce.notification.service.base;

import com.liferay.commerce.notification.model.CommerceNotificationQueueEntry;
import com.liferay.commerce.notification.service.CommerceNotificationQueueEntryService;
import com.liferay.commerce.notification.service.CommerceNotificationQueueEntryServiceUtil;
import com.liferay.commerce.notification.service.persistence.CommerceNotificationAttachmentPersistence;
import com.liferay.commerce.notification.service.persistence.CommerceNotificationQueueEntryPersistence;
import com.liferay.commerce.notification.service.persistence.CommerceNotificationTemplateCommerceAccountGroupRelPersistence;
import com.liferay.commerce.notification.service.persistence.CommerceNotificationTemplatePersistence;
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
 * Provides the base implementation for the commerce notification queue entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.notification.service.impl.CommerceNotificationQueueEntryServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.notification.service.impl.CommerceNotificationQueueEntryServiceImpl
 * @generated
 */
public abstract class CommerceNotificationQueueEntryServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceNotificationQueueEntryService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceNotificationQueueEntryService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceNotificationQueueEntryServiceUtil</code>.
	 */

	/**
	 * Returns the commerce notification attachment local service.
	 *
	 * @return the commerce notification attachment local service
	 */
	public com.liferay.commerce.notification.service.
		CommerceNotificationAttachmentLocalService
			getCommerceNotificationAttachmentLocalService() {

		return commerceNotificationAttachmentLocalService;
	}

	/**
	 * Sets the commerce notification attachment local service.
	 *
	 * @param commerceNotificationAttachmentLocalService the commerce notification attachment local service
	 */
	public void setCommerceNotificationAttachmentLocalService(
		com.liferay.commerce.notification.service.
			CommerceNotificationAttachmentLocalService
				commerceNotificationAttachmentLocalService) {

		this.commerceNotificationAttachmentLocalService =
			commerceNotificationAttachmentLocalService;
	}

	/**
	 * Returns the commerce notification attachment persistence.
	 *
	 * @return the commerce notification attachment persistence
	 */
	public CommerceNotificationAttachmentPersistence
		getCommerceNotificationAttachmentPersistence() {

		return commerceNotificationAttachmentPersistence;
	}

	/**
	 * Sets the commerce notification attachment persistence.
	 *
	 * @param commerceNotificationAttachmentPersistence the commerce notification attachment persistence
	 */
	public void setCommerceNotificationAttachmentPersistence(
		CommerceNotificationAttachmentPersistence
			commerceNotificationAttachmentPersistence) {

		this.commerceNotificationAttachmentPersistence =
			commerceNotificationAttachmentPersistence;
	}

	/**
	 * Returns the commerce notification queue entry local service.
	 *
	 * @return the commerce notification queue entry local service
	 */
	public com.liferay.commerce.notification.service.
		CommerceNotificationQueueEntryLocalService
			getCommerceNotificationQueueEntryLocalService() {

		return commerceNotificationQueueEntryLocalService;
	}

	/**
	 * Sets the commerce notification queue entry local service.
	 *
	 * @param commerceNotificationQueueEntryLocalService the commerce notification queue entry local service
	 */
	public void setCommerceNotificationQueueEntryLocalService(
		com.liferay.commerce.notification.service.
			CommerceNotificationQueueEntryLocalService
				commerceNotificationQueueEntryLocalService) {

		this.commerceNotificationQueueEntryLocalService =
			commerceNotificationQueueEntryLocalService;
	}

	/**
	 * Returns the commerce notification queue entry remote service.
	 *
	 * @return the commerce notification queue entry remote service
	 */
	public CommerceNotificationQueueEntryService
		getCommerceNotificationQueueEntryService() {

		return commerceNotificationQueueEntryService;
	}

	/**
	 * Sets the commerce notification queue entry remote service.
	 *
	 * @param commerceNotificationQueueEntryService the commerce notification queue entry remote service
	 */
	public void setCommerceNotificationQueueEntryService(
		CommerceNotificationQueueEntryService
			commerceNotificationQueueEntryService) {

		this.commerceNotificationQueueEntryService =
			commerceNotificationQueueEntryService;
	}

	/**
	 * Returns the commerce notification queue entry persistence.
	 *
	 * @return the commerce notification queue entry persistence
	 */
	public CommerceNotificationQueueEntryPersistence
		getCommerceNotificationQueueEntryPersistence() {

		return commerceNotificationQueueEntryPersistence;
	}

	/**
	 * Sets the commerce notification queue entry persistence.
	 *
	 * @param commerceNotificationQueueEntryPersistence the commerce notification queue entry persistence
	 */
	public void setCommerceNotificationQueueEntryPersistence(
		CommerceNotificationQueueEntryPersistence
			commerceNotificationQueueEntryPersistence) {

		this.commerceNotificationQueueEntryPersistence =
			commerceNotificationQueueEntryPersistence;
	}

	/**
	 * Returns the commerce notification template local service.
	 *
	 * @return the commerce notification template local service
	 */
	public com.liferay.commerce.notification.service.
		CommerceNotificationTemplateLocalService
			getCommerceNotificationTemplateLocalService() {

		return commerceNotificationTemplateLocalService;
	}

	/**
	 * Sets the commerce notification template local service.
	 *
	 * @param commerceNotificationTemplateLocalService the commerce notification template local service
	 */
	public void setCommerceNotificationTemplateLocalService(
		com.liferay.commerce.notification.service.
			CommerceNotificationTemplateLocalService
				commerceNotificationTemplateLocalService) {

		this.commerceNotificationTemplateLocalService =
			commerceNotificationTemplateLocalService;
	}

	/**
	 * Returns the commerce notification template remote service.
	 *
	 * @return the commerce notification template remote service
	 */
	public com.liferay.commerce.notification.service.
		CommerceNotificationTemplateService
			getCommerceNotificationTemplateService() {

		return commerceNotificationTemplateService;
	}

	/**
	 * Sets the commerce notification template remote service.
	 *
	 * @param commerceNotificationTemplateService the commerce notification template remote service
	 */
	public void setCommerceNotificationTemplateService(
		com.liferay.commerce.notification.service.
			CommerceNotificationTemplateService
				commerceNotificationTemplateService) {

		this.commerceNotificationTemplateService =
			commerceNotificationTemplateService;
	}

	/**
	 * Returns the commerce notification template persistence.
	 *
	 * @return the commerce notification template persistence
	 */
	public CommerceNotificationTemplatePersistence
		getCommerceNotificationTemplatePersistence() {

		return commerceNotificationTemplatePersistence;
	}

	/**
	 * Sets the commerce notification template persistence.
	 *
	 * @param commerceNotificationTemplatePersistence the commerce notification template persistence
	 */
	public void setCommerceNotificationTemplatePersistence(
		CommerceNotificationTemplatePersistence
			commerceNotificationTemplatePersistence) {

		this.commerceNotificationTemplatePersistence =
			commerceNotificationTemplatePersistence;
	}

	/**
	 * Returns the commerce notification template commerce account group rel local service.
	 *
	 * @return the commerce notification template commerce account group rel local service
	 */
	public com.liferay.commerce.notification.service.
		CommerceNotificationTemplateCommerceAccountGroupRelLocalService
			getCommerceNotificationTemplateCommerceAccountGroupRelLocalService() {

		return commerceNotificationTemplateCommerceAccountGroupRelLocalService;
	}

	/**
	 * Sets the commerce notification template commerce account group rel local service.
	 *
	 * @param commerceNotificationTemplateCommerceAccountGroupRelLocalService the commerce notification template commerce account group rel local service
	 */
	public void
		setCommerceNotificationTemplateCommerceAccountGroupRelLocalService(
			com.liferay.commerce.notification.service.
				CommerceNotificationTemplateCommerceAccountGroupRelLocalService
					commerceNotificationTemplateCommerceAccountGroupRelLocalService) {

		this.commerceNotificationTemplateCommerceAccountGroupRelLocalService =
			commerceNotificationTemplateCommerceAccountGroupRelLocalService;
	}

	/**
	 * Returns the commerce notification template commerce account group rel remote service.
	 *
	 * @return the commerce notification template commerce account group rel remote service
	 */
	public com.liferay.commerce.notification.service.
		CommerceNotificationTemplateCommerceAccountGroupRelService
			getCommerceNotificationTemplateCommerceAccountGroupRelService() {

		return commerceNotificationTemplateCommerceAccountGroupRelService;
	}

	/**
	 * Sets the commerce notification template commerce account group rel remote service.
	 *
	 * @param commerceNotificationTemplateCommerceAccountGroupRelService the commerce notification template commerce account group rel remote service
	 */
	public void setCommerceNotificationTemplateCommerceAccountGroupRelService(
		com.liferay.commerce.notification.service.
			CommerceNotificationTemplateCommerceAccountGroupRelService
				commerceNotificationTemplateCommerceAccountGroupRelService) {

		this.commerceNotificationTemplateCommerceAccountGroupRelService =
			commerceNotificationTemplateCommerceAccountGroupRelService;
	}

	/**
	 * Returns the commerce notification template commerce account group rel persistence.
	 *
	 * @return the commerce notification template commerce account group rel persistence
	 */
	public CommerceNotificationTemplateCommerceAccountGroupRelPersistence
		getCommerceNotificationTemplateCommerceAccountGroupRelPersistence() {

		return commerceNotificationTemplateCommerceAccountGroupRelPersistence;
	}

	/**
	 * Sets the commerce notification template commerce account group rel persistence.
	 *
	 * @param commerceNotificationTemplateCommerceAccountGroupRelPersistence the commerce notification template commerce account group rel persistence
	 */
	public void
		setCommerceNotificationTemplateCommerceAccountGroupRelPersistence(
			CommerceNotificationTemplateCommerceAccountGroupRelPersistence
				commerceNotificationTemplateCommerceAccountGroupRelPersistence) {

		this.commerceNotificationTemplateCommerceAccountGroupRelPersistence =
			commerceNotificationTemplateCommerceAccountGroupRelPersistence;
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
		_setServiceUtilService(commerceNotificationQueueEntryService);
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
		return CommerceNotificationQueueEntryService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceNotificationQueueEntry.class;
	}

	protected String getModelClassName() {
		return CommerceNotificationQueueEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceNotificationQueueEntryPersistence.getDataSource();

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
		CommerceNotificationQueueEntryService
			commerceNotificationQueueEntryService) {

		try {
			Field field =
				CommerceNotificationQueueEntryServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, commerceNotificationQueueEntryService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.notification.service.CommerceNotificationAttachmentLocalService.class
	)
	protected com.liferay.commerce.notification.service.
		CommerceNotificationAttachmentLocalService
			commerceNotificationAttachmentLocalService;

	@BeanReference(type = CommerceNotificationAttachmentPersistence.class)
	protected CommerceNotificationAttachmentPersistence
		commerceNotificationAttachmentPersistence;

	@BeanReference(
		type = com.liferay.commerce.notification.service.CommerceNotificationQueueEntryLocalService.class
	)
	protected com.liferay.commerce.notification.service.
		CommerceNotificationQueueEntryLocalService
			commerceNotificationQueueEntryLocalService;

	@BeanReference(type = CommerceNotificationQueueEntryService.class)
	protected CommerceNotificationQueueEntryService
		commerceNotificationQueueEntryService;

	@BeanReference(type = CommerceNotificationQueueEntryPersistence.class)
	protected CommerceNotificationQueueEntryPersistence
		commerceNotificationQueueEntryPersistence;

	@BeanReference(
		type = com.liferay.commerce.notification.service.CommerceNotificationTemplateLocalService.class
	)
	protected com.liferay.commerce.notification.service.
		CommerceNotificationTemplateLocalService
			commerceNotificationTemplateLocalService;

	@BeanReference(
		type = com.liferay.commerce.notification.service.CommerceNotificationTemplateService.class
	)
	protected com.liferay.commerce.notification.service.
		CommerceNotificationTemplateService commerceNotificationTemplateService;

	@BeanReference(type = CommerceNotificationTemplatePersistence.class)
	protected CommerceNotificationTemplatePersistence
		commerceNotificationTemplatePersistence;

	@BeanReference(
		type = com.liferay.commerce.notification.service.CommerceNotificationTemplateCommerceAccountGroupRelLocalService.class
	)
	protected com.liferay.commerce.notification.service.
		CommerceNotificationTemplateCommerceAccountGroupRelLocalService
			commerceNotificationTemplateCommerceAccountGroupRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.notification.service.CommerceNotificationTemplateCommerceAccountGroupRelService.class
	)
	protected com.liferay.commerce.notification.service.
		CommerceNotificationTemplateCommerceAccountGroupRelService
			commerceNotificationTemplateCommerceAccountGroupRelService;

	@BeanReference(
		type = CommerceNotificationTemplateCommerceAccountGroupRelPersistence.class
	)
	protected CommerceNotificationTemplateCommerceAccountGroupRelPersistence
		commerceNotificationTemplateCommerceAccountGroupRelPersistence;

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