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

package com.liferay.commerce.bom.service.base;

import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;
import com.liferay.commerce.bom.service.CommerceBOMFolderApplicationRelService;
import com.liferay.commerce.bom.service.CommerceBOMFolderApplicationRelServiceUtil;
import com.liferay.commerce.bom.service.persistence.CommerceBOMDefinitionPersistence;
import com.liferay.commerce.bom.service.persistence.CommerceBOMEntryPersistence;
import com.liferay.commerce.bom.service.persistence.CommerceBOMFolderApplicationRelPersistence;
import com.liferay.commerce.bom.service.persistence.CommerceBOMFolderPersistence;
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
 * Provides the base implementation for the commerce bom folder application rel remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMFolderApplicationRelServiceImpl
 * @generated
 */
public abstract class CommerceBOMFolderApplicationRelServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceBOMFolderApplicationRelService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceBOMFolderApplicationRelService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceBOMFolderApplicationRelServiceUtil</code>.
	 */

	/**
	 * Returns the commerce bom definition local service.
	 *
	 * @return the commerce bom definition local service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService
		getCommerceBOMDefinitionLocalService() {

		return commerceBOMDefinitionLocalService;
	}

	/**
	 * Sets the commerce bom definition local service.
	 *
	 * @param commerceBOMDefinitionLocalService the commerce bom definition local service
	 */
	public void setCommerceBOMDefinitionLocalService(
		com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService
			commerceBOMDefinitionLocalService) {

		this.commerceBOMDefinitionLocalService =
			commerceBOMDefinitionLocalService;
	}

	/**
	 * Returns the commerce bom definition remote service.
	 *
	 * @return the commerce bom definition remote service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMDefinitionService
		getCommerceBOMDefinitionService() {

		return commerceBOMDefinitionService;
	}

	/**
	 * Sets the commerce bom definition remote service.
	 *
	 * @param commerceBOMDefinitionService the commerce bom definition remote service
	 */
	public void setCommerceBOMDefinitionService(
		com.liferay.commerce.bom.service.CommerceBOMDefinitionService
			commerceBOMDefinitionService) {

		this.commerceBOMDefinitionService = commerceBOMDefinitionService;
	}

	/**
	 * Returns the commerce bom definition persistence.
	 *
	 * @return the commerce bom definition persistence
	 */
	public CommerceBOMDefinitionPersistence
		getCommerceBOMDefinitionPersistence() {

		return commerceBOMDefinitionPersistence;
	}

	/**
	 * Sets the commerce bom definition persistence.
	 *
	 * @param commerceBOMDefinitionPersistence the commerce bom definition persistence
	 */
	public void setCommerceBOMDefinitionPersistence(
		CommerceBOMDefinitionPersistence commerceBOMDefinitionPersistence) {

		this.commerceBOMDefinitionPersistence =
			commerceBOMDefinitionPersistence;
	}

	/**
	 * Returns the commerce bom entry local service.
	 *
	 * @return the commerce bom entry local service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMEntryLocalService
		getCommerceBOMEntryLocalService() {

		return commerceBOMEntryLocalService;
	}

	/**
	 * Sets the commerce bom entry local service.
	 *
	 * @param commerceBOMEntryLocalService the commerce bom entry local service
	 */
	public void setCommerceBOMEntryLocalService(
		com.liferay.commerce.bom.service.CommerceBOMEntryLocalService
			commerceBOMEntryLocalService) {

		this.commerceBOMEntryLocalService = commerceBOMEntryLocalService;
	}

	/**
	 * Returns the commerce bom entry remote service.
	 *
	 * @return the commerce bom entry remote service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMEntryService
		getCommerceBOMEntryService() {

		return commerceBOMEntryService;
	}

	/**
	 * Sets the commerce bom entry remote service.
	 *
	 * @param commerceBOMEntryService the commerce bom entry remote service
	 */
	public void setCommerceBOMEntryService(
		com.liferay.commerce.bom.service.CommerceBOMEntryService
			commerceBOMEntryService) {

		this.commerceBOMEntryService = commerceBOMEntryService;
	}

	/**
	 * Returns the commerce bom entry persistence.
	 *
	 * @return the commerce bom entry persistence
	 */
	public CommerceBOMEntryPersistence getCommerceBOMEntryPersistence() {
		return commerceBOMEntryPersistence;
	}

	/**
	 * Sets the commerce bom entry persistence.
	 *
	 * @param commerceBOMEntryPersistence the commerce bom entry persistence
	 */
	public void setCommerceBOMEntryPersistence(
		CommerceBOMEntryPersistence commerceBOMEntryPersistence) {

		this.commerceBOMEntryPersistence = commerceBOMEntryPersistence;
	}

	/**
	 * Returns the commerce bom folder local service.
	 *
	 * @return the commerce bom folder local service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMFolderLocalService
		getCommerceBOMFolderLocalService() {

		return commerceBOMFolderLocalService;
	}

	/**
	 * Sets the commerce bom folder local service.
	 *
	 * @param commerceBOMFolderLocalService the commerce bom folder local service
	 */
	public void setCommerceBOMFolderLocalService(
		com.liferay.commerce.bom.service.CommerceBOMFolderLocalService
			commerceBOMFolderLocalService) {

		this.commerceBOMFolderLocalService = commerceBOMFolderLocalService;
	}

	/**
	 * Returns the commerce bom folder remote service.
	 *
	 * @return the commerce bom folder remote service
	 */
	public com.liferay.commerce.bom.service.CommerceBOMFolderService
		getCommerceBOMFolderService() {

		return commerceBOMFolderService;
	}

	/**
	 * Sets the commerce bom folder remote service.
	 *
	 * @param commerceBOMFolderService the commerce bom folder remote service
	 */
	public void setCommerceBOMFolderService(
		com.liferay.commerce.bom.service.CommerceBOMFolderService
			commerceBOMFolderService) {

		this.commerceBOMFolderService = commerceBOMFolderService;
	}

	/**
	 * Returns the commerce bom folder persistence.
	 *
	 * @return the commerce bom folder persistence
	 */
	public CommerceBOMFolderPersistence getCommerceBOMFolderPersistence() {
		return commerceBOMFolderPersistence;
	}

	/**
	 * Sets the commerce bom folder persistence.
	 *
	 * @param commerceBOMFolderPersistence the commerce bom folder persistence
	 */
	public void setCommerceBOMFolderPersistence(
		CommerceBOMFolderPersistence commerceBOMFolderPersistence) {

		this.commerceBOMFolderPersistence = commerceBOMFolderPersistence;
	}

	/**
	 * Returns the commerce bom folder application rel local service.
	 *
	 * @return the commerce bom folder application rel local service
	 */
	public
		com.liferay.commerce.bom.service.
			CommerceBOMFolderApplicationRelLocalService
				getCommerceBOMFolderApplicationRelLocalService() {

		return commerceBOMFolderApplicationRelLocalService;
	}

	/**
	 * Sets the commerce bom folder application rel local service.
	 *
	 * @param commerceBOMFolderApplicationRelLocalService the commerce bom folder application rel local service
	 */
	public void setCommerceBOMFolderApplicationRelLocalService(
		com.liferay.commerce.bom.service.
			CommerceBOMFolderApplicationRelLocalService
				commerceBOMFolderApplicationRelLocalService) {

		this.commerceBOMFolderApplicationRelLocalService =
			commerceBOMFolderApplicationRelLocalService;
	}

	/**
	 * Returns the commerce bom folder application rel remote service.
	 *
	 * @return the commerce bom folder application rel remote service
	 */
	public CommerceBOMFolderApplicationRelService
		getCommerceBOMFolderApplicationRelService() {

		return commerceBOMFolderApplicationRelService;
	}

	/**
	 * Sets the commerce bom folder application rel remote service.
	 *
	 * @param commerceBOMFolderApplicationRelService the commerce bom folder application rel remote service
	 */
	public void setCommerceBOMFolderApplicationRelService(
		CommerceBOMFolderApplicationRelService
			commerceBOMFolderApplicationRelService) {

		this.commerceBOMFolderApplicationRelService =
			commerceBOMFolderApplicationRelService;
	}

	/**
	 * Returns the commerce bom folder application rel persistence.
	 *
	 * @return the commerce bom folder application rel persistence
	 */
	public CommerceBOMFolderApplicationRelPersistence
		getCommerceBOMFolderApplicationRelPersistence() {

		return commerceBOMFolderApplicationRelPersistence;
	}

	/**
	 * Sets the commerce bom folder application rel persistence.
	 *
	 * @param commerceBOMFolderApplicationRelPersistence the commerce bom folder application rel persistence
	 */
	public void setCommerceBOMFolderApplicationRelPersistence(
		CommerceBOMFolderApplicationRelPersistence
			commerceBOMFolderApplicationRelPersistence) {

		this.commerceBOMFolderApplicationRelPersistence =
			commerceBOMFolderApplicationRelPersistence;
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
		_setServiceUtilService(commerceBOMFolderApplicationRelService);
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
		return CommerceBOMFolderApplicationRelService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceBOMFolderApplicationRel.class;
	}

	protected String getModelClassName() {
		return CommerceBOMFolderApplicationRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceBOMFolderApplicationRelPersistence.getDataSource();

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
		CommerceBOMFolderApplicationRelService
			commerceBOMFolderApplicationRelService) {

		try {
			Field field =
				CommerceBOMFolderApplicationRelServiceUtil.class.
					getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, commerceBOMFolderApplicationRelService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMDefinitionLocalService
		commerceBOMDefinitionLocalService;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMDefinitionService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMDefinitionService
		commerceBOMDefinitionService;

	@BeanReference(type = CommerceBOMDefinitionPersistence.class)
	protected CommerceBOMDefinitionPersistence commerceBOMDefinitionPersistence;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMEntryLocalService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMEntryLocalService
		commerceBOMEntryLocalService;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMEntryService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMEntryService
		commerceBOMEntryService;

	@BeanReference(type = CommerceBOMEntryPersistence.class)
	protected CommerceBOMEntryPersistence commerceBOMEntryPersistence;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMFolderLocalService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMFolderLocalService
		commerceBOMFolderLocalService;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMFolderService.class
	)
	protected com.liferay.commerce.bom.service.CommerceBOMFolderService
		commerceBOMFolderService;

	@BeanReference(type = CommerceBOMFolderPersistence.class)
	protected CommerceBOMFolderPersistence commerceBOMFolderPersistence;

	@BeanReference(
		type = com.liferay.commerce.bom.service.CommerceBOMFolderApplicationRelLocalService.class
	)
	protected
		com.liferay.commerce.bom.service.
			CommerceBOMFolderApplicationRelLocalService
				commerceBOMFolderApplicationRelLocalService;

	@BeanReference(type = CommerceBOMFolderApplicationRelService.class)
	protected CommerceBOMFolderApplicationRelService
		commerceBOMFolderApplicationRelService;

	@BeanReference(type = CommerceBOMFolderApplicationRelPersistence.class)
	protected CommerceBOMFolderApplicationRelPersistence
		commerceBOMFolderApplicationRelPersistence;

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