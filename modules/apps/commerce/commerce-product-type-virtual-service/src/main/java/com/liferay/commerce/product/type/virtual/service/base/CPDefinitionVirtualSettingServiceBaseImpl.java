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

package com.liferay.commerce.product.type.virtual.service.base;

import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingService;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingServiceUtil;
import com.liferay.commerce.product.type.virtual.service.persistence.CPDefinitionVirtualSettingPersistence;
import com.liferay.journal.service.persistence.JournalArticlePersistence;
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
 * Provides the base implementation for the cp definition virtual setting remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.virtual.service.impl.CPDefinitionVirtualSettingServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.type.virtual.service.impl.CPDefinitionVirtualSettingServiceImpl
 * @generated
 */
public abstract class CPDefinitionVirtualSettingServiceBaseImpl
	extends BaseServiceImpl
	implements CPDefinitionVirtualSettingService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDefinitionVirtualSettingService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CPDefinitionVirtualSettingServiceUtil</code>.
	 */

	/**
	 * Returns the cp definition virtual setting local service.
	 *
	 * @return the cp definition virtual setting local service
	 */
	public com.liferay.commerce.product.type.virtual.service.
		CPDefinitionVirtualSettingLocalService
			getCPDefinitionVirtualSettingLocalService() {

		return cpDefinitionVirtualSettingLocalService;
	}

	/**
	 * Sets the cp definition virtual setting local service.
	 *
	 * @param cpDefinitionVirtualSettingLocalService the cp definition virtual setting local service
	 */
	public void setCPDefinitionVirtualSettingLocalService(
		com.liferay.commerce.product.type.virtual.service.
			CPDefinitionVirtualSettingLocalService
				cpDefinitionVirtualSettingLocalService) {

		this.cpDefinitionVirtualSettingLocalService =
			cpDefinitionVirtualSettingLocalService;
	}

	/**
	 * Returns the cp definition virtual setting remote service.
	 *
	 * @return the cp definition virtual setting remote service
	 */
	public CPDefinitionVirtualSettingService
		getCPDefinitionVirtualSettingService() {

		return cpDefinitionVirtualSettingService;
	}

	/**
	 * Sets the cp definition virtual setting remote service.
	 *
	 * @param cpDefinitionVirtualSettingService the cp definition virtual setting remote service
	 */
	public void setCPDefinitionVirtualSettingService(
		CPDefinitionVirtualSettingService cpDefinitionVirtualSettingService) {

		this.cpDefinitionVirtualSettingService =
			cpDefinitionVirtualSettingService;
	}

	/**
	 * Returns the cp definition virtual setting persistence.
	 *
	 * @return the cp definition virtual setting persistence
	 */
	public CPDefinitionVirtualSettingPersistence
		getCPDefinitionVirtualSettingPersistence() {

		return cpDefinitionVirtualSettingPersistence;
	}

	/**
	 * Sets the cp definition virtual setting persistence.
	 *
	 * @param cpDefinitionVirtualSettingPersistence the cp definition virtual setting persistence
	 */
	public void setCPDefinitionVirtualSettingPersistence(
		CPDefinitionVirtualSettingPersistence
			cpDefinitionVirtualSettingPersistence) {

		this.cpDefinitionVirtualSettingPersistence =
			cpDefinitionVirtualSettingPersistence;
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
	 * Returns the journal article local service.
	 *
	 * @return the journal article local service
	 */
	public com.liferay.journal.service.JournalArticleLocalService
		getJournalArticleLocalService() {

		return journalArticleLocalService;
	}

	/**
	 * Sets the journal article local service.
	 *
	 * @param journalArticleLocalService the journal article local service
	 */
	public void setJournalArticleLocalService(
		com.liferay.journal.service.JournalArticleLocalService
			journalArticleLocalService) {

		this.journalArticleLocalService = journalArticleLocalService;
	}

	/**
	 * Returns the journal article remote service.
	 *
	 * @return the journal article remote service
	 */
	public com.liferay.journal.service.JournalArticleService
		getJournalArticleService() {

		return journalArticleService;
	}

	/**
	 * Sets the journal article remote service.
	 *
	 * @param journalArticleService the journal article remote service
	 */
	public void setJournalArticleService(
		com.liferay.journal.service.JournalArticleService
			journalArticleService) {

		this.journalArticleService = journalArticleService;
	}

	/**
	 * Returns the journal article persistence.
	 *
	 * @return the journal article persistence
	 */
	public JournalArticlePersistence getJournalArticlePersistence() {
		return journalArticlePersistence;
	}

	/**
	 * Sets the journal article persistence.
	 *
	 * @param journalArticlePersistence the journal article persistence
	 */
	public void setJournalArticlePersistence(
		JournalArticlePersistence journalArticlePersistence) {

		this.journalArticlePersistence = journalArticlePersistence;
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

	/**
	 * Returns the dl app local service.
	 *
	 * @return the dl app local service
	 */
	public com.liferay.document.library.kernel.service.DLAppLocalService
		getDLAppLocalService() {

		return dlAppLocalService;
	}

	/**
	 * Sets the dl app local service.
	 *
	 * @param dlAppLocalService the dl app local service
	 */
	public void setDLAppLocalService(
		com.liferay.document.library.kernel.service.DLAppLocalService
			dlAppLocalService) {

		this.dlAppLocalService = dlAppLocalService;
	}

	/**
	 * Returns the dl app remote service.
	 *
	 * @return the dl app remote service
	 */
	public com.liferay.document.library.kernel.service.DLAppService
		getDLAppService() {

		return dlAppService;
	}

	/**
	 * Sets the dl app remote service.
	 *
	 * @param dlAppService the dl app remote service
	 */
	public void setDLAppService(
		com.liferay.document.library.kernel.service.DLAppService dlAppService) {

		this.dlAppService = dlAppService;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(cpDefinitionVirtualSettingService);
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
		return CPDefinitionVirtualSettingService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDefinitionVirtualSetting.class;
	}

	protected String getModelClassName() {
		return CPDefinitionVirtualSetting.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpDefinitionVirtualSettingPersistence.getDataSource();

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
		CPDefinitionVirtualSettingService cpDefinitionVirtualSettingService) {

		try {
			Field field =
				CPDefinitionVirtualSettingServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, cpDefinitionVirtualSettingService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalService.class
	)
	protected com.liferay.commerce.product.type.virtual.service.
		CPDefinitionVirtualSettingLocalService
			cpDefinitionVirtualSettingLocalService;

	@BeanReference(type = CPDefinitionVirtualSettingService.class)
	protected CPDefinitionVirtualSettingService
		cpDefinitionVirtualSettingService;

	@BeanReference(type = CPDefinitionVirtualSettingPersistence.class)
	protected CPDefinitionVirtualSettingPersistence
		cpDefinitionVirtualSettingPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.journal.service.JournalArticleLocalService.class
	)
	protected com.liferay.journal.service.JournalArticleLocalService
		journalArticleLocalService;

	@ServiceReference(
		type = com.liferay.journal.service.JournalArticleService.class
	)
	protected com.liferay.journal.service.JournalArticleService
		journalArticleService;

	@ServiceReference(type = JournalArticlePersistence.class)
	protected JournalArticlePersistence journalArticlePersistence;

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

	@ServiceReference(
		type = com.liferay.document.library.kernel.service.DLAppLocalService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@ServiceReference(
		type = com.liferay.document.library.kernel.service.DLAppService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppService
		dlAppService;

}