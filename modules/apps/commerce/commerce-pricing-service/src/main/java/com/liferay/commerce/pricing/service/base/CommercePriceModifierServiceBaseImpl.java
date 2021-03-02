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

package com.liferay.commerce.pricing.service.base;

import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.service.CommercePriceModifierService;
import com.liferay.commerce.pricing.service.CommercePriceModifierServiceUtil;
import com.liferay.commerce.pricing.service.persistence.CommercePriceModifierFinder;
import com.liferay.commerce.pricing.service.persistence.CommercePriceModifierPersistence;
import com.liferay.commerce.pricing.service.persistence.CommercePriceModifierRelFinder;
import com.liferay.commerce.pricing.service.persistence.CommercePriceModifierRelPersistence;
import com.liferay.commerce.pricing.service.persistence.CommercePricingClassCPDefinitionRelFinder;
import com.liferay.commerce.pricing.service.persistence.CommercePricingClassCPDefinitionRelPersistence;
import com.liferay.commerce.pricing.service.persistence.CommercePricingClassFinder;
import com.liferay.commerce.pricing.service.persistence.CommercePricingClassPersistence;
import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;
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
import com.liferay.portal.kernel.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce price modifier remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.pricing.service.impl.CommercePriceModifierServiceImpl}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see com.liferay.commerce.pricing.service.impl.CommercePriceModifierServiceImpl
 * @generated
 */
public abstract class CommercePriceModifierServiceBaseImpl
	extends BaseServiceImpl
	implements CommercePriceModifierService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommercePriceModifierService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommercePriceModifierServiceUtil</code>.
	 */

	/**
	 * Returns the commerce price modifier local service.
	 *
	 * @return the commerce price modifier local service
	 */
	public
		com.liferay.commerce.pricing.service.CommercePriceModifierLocalService
			getCommercePriceModifierLocalService() {

		return commercePriceModifierLocalService;
	}

	/**
	 * Sets the commerce price modifier local service.
	 *
	 * @param commercePriceModifierLocalService the commerce price modifier local service
	 */
	public void setCommercePriceModifierLocalService(
		com.liferay.commerce.pricing.service.CommercePriceModifierLocalService
			commercePriceModifierLocalService) {

		this.commercePriceModifierLocalService =
			commercePriceModifierLocalService;
	}

	/**
	 * Returns the commerce price modifier remote service.
	 *
	 * @return the commerce price modifier remote service
	 */
	public CommercePriceModifierService getCommercePriceModifierService() {
		return commercePriceModifierService;
	}

	/**
	 * Sets the commerce price modifier remote service.
	 *
	 * @param commercePriceModifierService the commerce price modifier remote service
	 */
	public void setCommercePriceModifierService(
		CommercePriceModifierService commercePriceModifierService) {

		this.commercePriceModifierService = commercePriceModifierService;
	}

	/**
	 * Returns the commerce price modifier persistence.
	 *
	 * @return the commerce price modifier persistence
	 */
	public CommercePriceModifierPersistence
		getCommercePriceModifierPersistence() {

		return commercePriceModifierPersistence;
	}

	/**
	 * Sets the commerce price modifier persistence.
	 *
	 * @param commercePriceModifierPersistence the commerce price modifier persistence
	 */
	public void setCommercePriceModifierPersistence(
		CommercePriceModifierPersistence commercePriceModifierPersistence) {

		this.commercePriceModifierPersistence =
			commercePriceModifierPersistence;
	}

	/**
	 * Returns the commerce price modifier finder.
	 *
	 * @return the commerce price modifier finder
	 */
	public CommercePriceModifierFinder getCommercePriceModifierFinder() {
		return commercePriceModifierFinder;
	}

	/**
	 * Sets the commerce price modifier finder.
	 *
	 * @param commercePriceModifierFinder the commerce price modifier finder
	 */
	public void setCommercePriceModifierFinder(
		CommercePriceModifierFinder commercePriceModifierFinder) {

		this.commercePriceModifierFinder = commercePriceModifierFinder;
	}

	/**
	 * Returns the commerce price modifier rel local service.
	 *
	 * @return the commerce price modifier rel local service
	 */
	public
		com.liferay.commerce.pricing.service.
			CommercePriceModifierRelLocalService
				getCommercePriceModifierRelLocalService() {

		return commercePriceModifierRelLocalService;
	}

	/**
	 * Sets the commerce price modifier rel local service.
	 *
	 * @param commercePriceModifierRelLocalService the commerce price modifier rel local service
	 */
	public void setCommercePriceModifierRelLocalService(
		com.liferay.commerce.pricing.service.
			CommercePriceModifierRelLocalService
				commercePriceModifierRelLocalService) {

		this.commercePriceModifierRelLocalService =
			commercePriceModifierRelLocalService;
	}

	/**
	 * Returns the commerce price modifier rel remote service.
	 *
	 * @return the commerce price modifier rel remote service
	 */
	public com.liferay.commerce.pricing.service.CommercePriceModifierRelService
		getCommercePriceModifierRelService() {

		return commercePriceModifierRelService;
	}

	/**
	 * Sets the commerce price modifier rel remote service.
	 *
	 * @param commercePriceModifierRelService the commerce price modifier rel remote service
	 */
	public void setCommercePriceModifierRelService(
		com.liferay.commerce.pricing.service.CommercePriceModifierRelService
			commercePriceModifierRelService) {

		this.commercePriceModifierRelService = commercePriceModifierRelService;
	}

	/**
	 * Returns the commerce price modifier rel persistence.
	 *
	 * @return the commerce price modifier rel persistence
	 */
	public CommercePriceModifierRelPersistence
		getCommercePriceModifierRelPersistence() {

		return commercePriceModifierRelPersistence;
	}

	/**
	 * Sets the commerce price modifier rel persistence.
	 *
	 * @param commercePriceModifierRelPersistence the commerce price modifier rel persistence
	 */
	public void setCommercePriceModifierRelPersistence(
		CommercePriceModifierRelPersistence
			commercePriceModifierRelPersistence) {

		this.commercePriceModifierRelPersistence =
			commercePriceModifierRelPersistence;
	}

	/**
	 * Returns the commerce price modifier rel finder.
	 *
	 * @return the commerce price modifier rel finder
	 */
	public CommercePriceModifierRelFinder getCommercePriceModifierRelFinder() {
		return commercePriceModifierRelFinder;
	}

	/**
	 * Sets the commerce price modifier rel finder.
	 *
	 * @param commercePriceModifierRelFinder the commerce price modifier rel finder
	 */
	public void setCommercePriceModifierRelFinder(
		CommercePriceModifierRelFinder commercePriceModifierRelFinder) {

		this.commercePriceModifierRelFinder = commercePriceModifierRelFinder;
	}

	/**
	 * Returns the commerce pricing class local service.
	 *
	 * @return the commerce pricing class local service
	 */
	public com.liferay.commerce.pricing.service.CommercePricingClassLocalService
		getCommercePricingClassLocalService() {

		return commercePricingClassLocalService;
	}

	/**
	 * Sets the commerce pricing class local service.
	 *
	 * @param commercePricingClassLocalService the commerce pricing class local service
	 */
	public void setCommercePricingClassLocalService(
		com.liferay.commerce.pricing.service.CommercePricingClassLocalService
			commercePricingClassLocalService) {

		this.commercePricingClassLocalService =
			commercePricingClassLocalService;
	}

	/**
	 * Returns the commerce pricing class remote service.
	 *
	 * @return the commerce pricing class remote service
	 */
	public com.liferay.commerce.pricing.service.CommercePricingClassService
		getCommercePricingClassService() {

		return commercePricingClassService;
	}

	/**
	 * Sets the commerce pricing class remote service.
	 *
	 * @param commercePricingClassService the commerce pricing class remote service
	 */
	public void setCommercePricingClassService(
		com.liferay.commerce.pricing.service.CommercePricingClassService
			commercePricingClassService) {

		this.commercePricingClassService = commercePricingClassService;
	}

	/**
	 * Returns the commerce pricing class persistence.
	 *
	 * @return the commerce pricing class persistence
	 */
	public CommercePricingClassPersistence
		getCommercePricingClassPersistence() {

		return commercePricingClassPersistence;
	}

	/**
	 * Sets the commerce pricing class persistence.
	 *
	 * @param commercePricingClassPersistence the commerce pricing class persistence
	 */
	public void setCommercePricingClassPersistence(
		CommercePricingClassPersistence commercePricingClassPersistence) {

		this.commercePricingClassPersistence = commercePricingClassPersistence;
	}

	/**
	 * Returns the commerce pricing class finder.
	 *
	 * @return the commerce pricing class finder
	 */
	public CommercePricingClassFinder getCommercePricingClassFinder() {
		return commercePricingClassFinder;
	}

	/**
	 * Sets the commerce pricing class finder.
	 *
	 * @param commercePricingClassFinder the commerce pricing class finder
	 */
	public void setCommercePricingClassFinder(
		CommercePricingClassFinder commercePricingClassFinder) {

		this.commercePricingClassFinder = commercePricingClassFinder;
	}

	/**
	 * Returns the commerce pricing class cp definition rel local service.
	 *
	 * @return the commerce pricing class cp definition rel local service
	 */
	public com.liferay.commerce.pricing.service.
		CommercePricingClassCPDefinitionRelLocalService
			getCommercePricingClassCPDefinitionRelLocalService() {

		return commercePricingClassCPDefinitionRelLocalService;
	}

	/**
	 * Sets the commerce pricing class cp definition rel local service.
	 *
	 * @param commercePricingClassCPDefinitionRelLocalService the commerce pricing class cp definition rel local service
	 */
	public void setCommercePricingClassCPDefinitionRelLocalService(
		com.liferay.commerce.pricing.service.
			CommercePricingClassCPDefinitionRelLocalService
				commercePricingClassCPDefinitionRelLocalService) {

		this.commercePricingClassCPDefinitionRelLocalService =
			commercePricingClassCPDefinitionRelLocalService;
	}

	/**
	 * Returns the commerce pricing class cp definition rel remote service.
	 *
	 * @return the commerce pricing class cp definition rel remote service
	 */
	public com.liferay.commerce.pricing.service.
		CommercePricingClassCPDefinitionRelService
			getCommercePricingClassCPDefinitionRelService() {

		return commercePricingClassCPDefinitionRelService;
	}

	/**
	 * Sets the commerce pricing class cp definition rel remote service.
	 *
	 * @param commercePricingClassCPDefinitionRelService the commerce pricing class cp definition rel remote service
	 */
	public void setCommercePricingClassCPDefinitionRelService(
		com.liferay.commerce.pricing.service.
			CommercePricingClassCPDefinitionRelService
				commercePricingClassCPDefinitionRelService) {

		this.commercePricingClassCPDefinitionRelService =
			commercePricingClassCPDefinitionRelService;
	}

	/**
	 * Returns the commerce pricing class cp definition rel persistence.
	 *
	 * @return the commerce pricing class cp definition rel persistence
	 */
	public CommercePricingClassCPDefinitionRelPersistence
		getCommercePricingClassCPDefinitionRelPersistence() {

		return commercePricingClassCPDefinitionRelPersistence;
	}

	/**
	 * Sets the commerce pricing class cp definition rel persistence.
	 *
	 * @param commercePricingClassCPDefinitionRelPersistence the commerce pricing class cp definition rel persistence
	 */
	public void setCommercePricingClassCPDefinitionRelPersistence(
		CommercePricingClassCPDefinitionRelPersistence
			commercePricingClassCPDefinitionRelPersistence) {

		this.commercePricingClassCPDefinitionRelPersistence =
			commercePricingClassCPDefinitionRelPersistence;
	}

	/**
	 * Returns the commerce pricing class cp definition rel finder.
	 *
	 * @return the commerce pricing class cp definition rel finder
	 */
	public CommercePricingClassCPDefinitionRelFinder
		getCommercePricingClassCPDefinitionRelFinder() {

		return commercePricingClassCPDefinitionRelFinder;
	}

	/**
	 * Sets the commerce pricing class cp definition rel finder.
	 *
	 * @param commercePricingClassCPDefinitionRelFinder the commerce pricing class cp definition rel finder
	 */
	public void setCommercePricingClassCPDefinitionRelFinder(
		CommercePricingClassCPDefinitionRelFinder
			commercePricingClassCPDefinitionRelFinder) {

		this.commercePricingClassCPDefinitionRelFinder =
			commercePricingClassCPDefinitionRelFinder;
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

	/**
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		getWorkflowInstanceLinkLocalService() {

		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
			workflowInstanceLinkLocalService) {

		this.workflowInstanceLinkLocalService =
			workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence
		getWorkflowInstanceLinkPersistence() {

		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {

		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService
		getExpandoRowLocalService() {

		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService
			expandoRowLocalService) {

		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {

		this.expandoRowPersistence = expandoRowPersistence;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(commercePriceModifierService);
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
		return CommercePriceModifierService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommercePriceModifier.class;
	}

	protected String getModelClassName() {
		return CommercePriceModifier.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commercePriceModifierPersistence.getDataSource();

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
		CommercePriceModifierService commercePriceModifierService) {

		try {
			Field field =
				CommercePriceModifierServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commercePriceModifierService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePriceModifierLocalService.class
	)
	protected
		com.liferay.commerce.pricing.service.CommercePriceModifierLocalService
			commercePriceModifierLocalService;

	@BeanReference(type = CommercePriceModifierService.class)
	protected CommercePriceModifierService commercePriceModifierService;

	@BeanReference(type = CommercePriceModifierPersistence.class)
	protected CommercePriceModifierPersistence commercePriceModifierPersistence;

	@BeanReference(type = CommercePriceModifierFinder.class)
	protected CommercePriceModifierFinder commercePriceModifierFinder;

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePriceModifierRelLocalService.class
	)
	protected
		com.liferay.commerce.pricing.service.
			CommercePriceModifierRelLocalService
				commercePriceModifierRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePriceModifierRelService.class
	)
	protected
		com.liferay.commerce.pricing.service.CommercePriceModifierRelService
			commercePriceModifierRelService;

	@BeanReference(type = CommercePriceModifierRelPersistence.class)
	protected CommercePriceModifierRelPersistence
		commercePriceModifierRelPersistence;

	@BeanReference(type = CommercePriceModifierRelFinder.class)
	protected CommercePriceModifierRelFinder commercePriceModifierRelFinder;

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePricingClassLocalService.class
	)
	protected
		com.liferay.commerce.pricing.service.CommercePricingClassLocalService
			commercePricingClassLocalService;

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePricingClassService.class
	)
	protected com.liferay.commerce.pricing.service.CommercePricingClassService
		commercePricingClassService;

	@BeanReference(type = CommercePricingClassPersistence.class)
	protected CommercePricingClassPersistence commercePricingClassPersistence;

	@BeanReference(type = CommercePricingClassFinder.class)
	protected CommercePricingClassFinder commercePricingClassFinder;

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelLocalService.class
	)
	protected com.liferay.commerce.pricing.service.
		CommercePricingClassCPDefinitionRelLocalService
			commercePricingClassCPDefinitionRelLocalService;

	@BeanReference(
		type = com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelService.class
	)
	protected com.liferay.commerce.pricing.service.
		CommercePricingClassCPDefinitionRelService
			commercePricingClassCPDefinitionRelService;

	@BeanReference(type = CommercePricingClassCPDefinitionRelPersistence.class)
	protected CommercePricingClassCPDefinitionRelPersistence
		commercePricingClassCPDefinitionRelPersistence;

	@BeanReference(type = CommercePricingClassCPDefinitionRelFinder.class)
	protected CommercePricingClassCPDefinitionRelFinder
		commercePricingClassCPDefinitionRelFinder;

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

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService.class
	)
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@ServiceReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;

	@ServiceReference(
		type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class
	)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService
		expandoRowLocalService;

	@ServiceReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;

}