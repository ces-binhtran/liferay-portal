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

import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.commerce.service.persistence.CommerceCartItemPersistence;
import com.liferay.commerce.service.persistence.CommerceCartPersistence;
import com.liferay.commerce.service.persistence.CommerceCountryFinder;
import com.liferay.commerce.service.persistence.CommerceCountryPersistence;
import com.liferay.commerce.service.persistence.CommerceOrderItemPersistence;
import com.liferay.commerce.service.persistence.CommerceOrderPersistence;
import com.liferay.commerce.service.persistence.CommercePaymentMethodPersistence;
import com.liferay.commerce.service.persistence.CommerceRegionPersistence;
import com.liferay.commerce.service.persistence.CommerceShippingMethodPersistence;
import com.liferay.commerce.service.persistence.CommerceWarehouseFinder;
import com.liferay.commerce.service.persistence.CommerceWarehouseItemFinder;
import com.liferay.commerce.service.persistence.CommerceWarehouseItemPersistence;
import com.liferay.commerce.service.persistence.CommerceWarehousePersistence;

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

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce region remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CommerceRegionServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CommerceRegionServiceImpl
 * @see com.liferay.commerce.service.CommerceRegionServiceUtil
 * @generated
 */
public abstract class CommerceRegionServiceBaseImpl extends BaseServiceImpl
	implements CommerceRegionService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.commerce.service.CommerceRegionServiceUtil} to access the commerce region remote service.
	 */

	/**
	 * Returns the commerce cart local service.
	 *
	 * @return the commerce cart local service
	 */
	public com.liferay.commerce.service.CommerceCartLocalService getCommerceCartLocalService() {
		return commerceCartLocalService;
	}

	/**
	 * Sets the commerce cart local service.
	 *
	 * @param commerceCartLocalService the commerce cart local service
	 */
	public void setCommerceCartLocalService(
		com.liferay.commerce.service.CommerceCartLocalService commerceCartLocalService) {
		this.commerceCartLocalService = commerceCartLocalService;
	}

	/**
	 * Returns the commerce cart remote service.
	 *
	 * @return the commerce cart remote service
	 */
	public com.liferay.commerce.service.CommerceCartService getCommerceCartService() {
		return commerceCartService;
	}

	/**
	 * Sets the commerce cart remote service.
	 *
	 * @param commerceCartService the commerce cart remote service
	 */
	public void setCommerceCartService(
		com.liferay.commerce.service.CommerceCartService commerceCartService) {
		this.commerceCartService = commerceCartService;
	}

	/**
	 * Returns the commerce cart persistence.
	 *
	 * @return the commerce cart persistence
	 */
	public CommerceCartPersistence getCommerceCartPersistence() {
		return commerceCartPersistence;
	}

	/**
	 * Sets the commerce cart persistence.
	 *
	 * @param commerceCartPersistence the commerce cart persistence
	 */
	public void setCommerceCartPersistence(
		CommerceCartPersistence commerceCartPersistence) {
		this.commerceCartPersistence = commerceCartPersistence;
	}

	/**
	 * Returns the commerce cart item local service.
	 *
	 * @return the commerce cart item local service
	 */
	public com.liferay.commerce.service.CommerceCartItemLocalService getCommerceCartItemLocalService() {
		return commerceCartItemLocalService;
	}

	/**
	 * Sets the commerce cart item local service.
	 *
	 * @param commerceCartItemLocalService the commerce cart item local service
	 */
	public void setCommerceCartItemLocalService(
		com.liferay.commerce.service.CommerceCartItemLocalService commerceCartItemLocalService) {
		this.commerceCartItemLocalService = commerceCartItemLocalService;
	}

	/**
	 * Returns the commerce cart item remote service.
	 *
	 * @return the commerce cart item remote service
	 */
	public com.liferay.commerce.service.CommerceCartItemService getCommerceCartItemService() {
		return commerceCartItemService;
	}

	/**
	 * Sets the commerce cart item remote service.
	 *
	 * @param commerceCartItemService the commerce cart item remote service
	 */
	public void setCommerceCartItemService(
		com.liferay.commerce.service.CommerceCartItemService commerceCartItemService) {
		this.commerceCartItemService = commerceCartItemService;
	}

	/**
	 * Returns the commerce cart item persistence.
	 *
	 * @return the commerce cart item persistence
	 */
	public CommerceCartItemPersistence getCommerceCartItemPersistence() {
		return commerceCartItemPersistence;
	}

	/**
	 * Sets the commerce cart item persistence.
	 *
	 * @param commerceCartItemPersistence the commerce cart item persistence
	 */
	public void setCommerceCartItemPersistence(
		CommerceCartItemPersistence commerceCartItemPersistence) {
		this.commerceCartItemPersistence = commerceCartItemPersistence;
	}

	/**
	 * Returns the commerce country local service.
	 *
	 * @return the commerce country local service
	 */
	public com.liferay.commerce.service.CommerceCountryLocalService getCommerceCountryLocalService() {
		return commerceCountryLocalService;
	}

	/**
	 * Sets the commerce country local service.
	 *
	 * @param commerceCountryLocalService the commerce country local service
	 */
	public void setCommerceCountryLocalService(
		com.liferay.commerce.service.CommerceCountryLocalService commerceCountryLocalService) {
		this.commerceCountryLocalService = commerceCountryLocalService;
	}

	/**
	 * Returns the commerce country remote service.
	 *
	 * @return the commerce country remote service
	 */
	public com.liferay.commerce.service.CommerceCountryService getCommerceCountryService() {
		return commerceCountryService;
	}

	/**
	 * Sets the commerce country remote service.
	 *
	 * @param commerceCountryService the commerce country remote service
	 */
	public void setCommerceCountryService(
		com.liferay.commerce.service.CommerceCountryService commerceCountryService) {
		this.commerceCountryService = commerceCountryService;
	}

	/**
	 * Returns the commerce country persistence.
	 *
	 * @return the commerce country persistence
	 */
	public CommerceCountryPersistence getCommerceCountryPersistence() {
		return commerceCountryPersistence;
	}

	/**
	 * Sets the commerce country persistence.
	 *
	 * @param commerceCountryPersistence the commerce country persistence
	 */
	public void setCommerceCountryPersistence(
		CommerceCountryPersistence commerceCountryPersistence) {
		this.commerceCountryPersistence = commerceCountryPersistence;
	}

	/**
	 * Returns the commerce country finder.
	 *
	 * @return the commerce country finder
	 */
	public CommerceCountryFinder getCommerceCountryFinder() {
		return commerceCountryFinder;
	}

	/**
	 * Sets the commerce country finder.
	 *
	 * @param commerceCountryFinder the commerce country finder
	 */
	public void setCommerceCountryFinder(
		CommerceCountryFinder commerceCountryFinder) {
		this.commerceCountryFinder = commerceCountryFinder;
	}

	/**
	 * Returns the commerce order local service.
	 *
	 * @return the commerce order local service
	 */
	public com.liferay.commerce.service.CommerceOrderLocalService getCommerceOrderLocalService() {
		return commerceOrderLocalService;
	}

	/**
	 * Sets the commerce order local service.
	 *
	 * @param commerceOrderLocalService the commerce order local service
	 */
	public void setCommerceOrderLocalService(
		com.liferay.commerce.service.CommerceOrderLocalService commerceOrderLocalService) {
		this.commerceOrderLocalService = commerceOrderLocalService;
	}

	/**
	 * Returns the commerce order remote service.
	 *
	 * @return the commerce order remote service
	 */
	public com.liferay.commerce.service.CommerceOrderService getCommerceOrderService() {
		return commerceOrderService;
	}

	/**
	 * Sets the commerce order remote service.
	 *
	 * @param commerceOrderService the commerce order remote service
	 */
	public void setCommerceOrderService(
		com.liferay.commerce.service.CommerceOrderService commerceOrderService) {
		this.commerceOrderService = commerceOrderService;
	}

	/**
	 * Returns the commerce order persistence.
	 *
	 * @return the commerce order persistence
	 */
	public CommerceOrderPersistence getCommerceOrderPersistence() {
		return commerceOrderPersistence;
	}

	/**
	 * Sets the commerce order persistence.
	 *
	 * @param commerceOrderPersistence the commerce order persistence
	 */
	public void setCommerceOrderPersistence(
		CommerceOrderPersistence commerceOrderPersistence) {
		this.commerceOrderPersistence = commerceOrderPersistence;
	}

	/**
	 * Returns the commerce order item local service.
	 *
	 * @return the commerce order item local service
	 */
	public com.liferay.commerce.service.CommerceOrderItemLocalService getCommerceOrderItemLocalService() {
		return commerceOrderItemLocalService;
	}

	/**
	 * Sets the commerce order item local service.
	 *
	 * @param commerceOrderItemLocalService the commerce order item local service
	 */
	public void setCommerceOrderItemLocalService(
		com.liferay.commerce.service.CommerceOrderItemLocalService commerceOrderItemLocalService) {
		this.commerceOrderItemLocalService = commerceOrderItemLocalService;
	}

	/**
	 * Returns the commerce order item remote service.
	 *
	 * @return the commerce order item remote service
	 */
	public com.liferay.commerce.service.CommerceOrderItemService getCommerceOrderItemService() {
		return commerceOrderItemService;
	}

	/**
	 * Sets the commerce order item remote service.
	 *
	 * @param commerceOrderItemService the commerce order item remote service
	 */
	public void setCommerceOrderItemService(
		com.liferay.commerce.service.CommerceOrderItemService commerceOrderItemService) {
		this.commerceOrderItemService = commerceOrderItemService;
	}

	/**
	 * Returns the commerce order item persistence.
	 *
	 * @return the commerce order item persistence
	 */
	public CommerceOrderItemPersistence getCommerceOrderItemPersistence() {
		return commerceOrderItemPersistence;
	}

	/**
	 * Sets the commerce order item persistence.
	 *
	 * @param commerceOrderItemPersistence the commerce order item persistence
	 */
	public void setCommerceOrderItemPersistence(
		CommerceOrderItemPersistence commerceOrderItemPersistence) {
		this.commerceOrderItemPersistence = commerceOrderItemPersistence;
	}

	/**
	 * Returns the commerce payment method local service.
	 *
	 * @return the commerce payment method local service
	 */
	public com.liferay.commerce.service.CommercePaymentMethodLocalService getCommercePaymentMethodLocalService() {
		return commercePaymentMethodLocalService;
	}

	/**
	 * Sets the commerce payment method local service.
	 *
	 * @param commercePaymentMethodLocalService the commerce payment method local service
	 */
	public void setCommercePaymentMethodLocalService(
		com.liferay.commerce.service.CommercePaymentMethodLocalService commercePaymentMethodLocalService) {
		this.commercePaymentMethodLocalService = commercePaymentMethodLocalService;
	}

	/**
	 * Returns the commerce payment method remote service.
	 *
	 * @return the commerce payment method remote service
	 */
	public com.liferay.commerce.service.CommercePaymentMethodService getCommercePaymentMethodService() {
		return commercePaymentMethodService;
	}

	/**
	 * Sets the commerce payment method remote service.
	 *
	 * @param commercePaymentMethodService the commerce payment method remote service
	 */
	public void setCommercePaymentMethodService(
		com.liferay.commerce.service.CommercePaymentMethodService commercePaymentMethodService) {
		this.commercePaymentMethodService = commercePaymentMethodService;
	}

	/**
	 * Returns the commerce payment method persistence.
	 *
	 * @return the commerce payment method persistence
	 */
	public CommercePaymentMethodPersistence getCommercePaymentMethodPersistence() {
		return commercePaymentMethodPersistence;
	}

	/**
	 * Sets the commerce payment method persistence.
	 *
	 * @param commercePaymentMethodPersistence the commerce payment method persistence
	 */
	public void setCommercePaymentMethodPersistence(
		CommercePaymentMethodPersistence commercePaymentMethodPersistence) {
		this.commercePaymentMethodPersistence = commercePaymentMethodPersistence;
	}

	/**
	 * Returns the commerce region local service.
	 *
	 * @return the commerce region local service
	 */
	public com.liferay.commerce.service.CommerceRegionLocalService getCommerceRegionLocalService() {
		return commerceRegionLocalService;
	}

	/**
	 * Sets the commerce region local service.
	 *
	 * @param commerceRegionLocalService the commerce region local service
	 */
	public void setCommerceRegionLocalService(
		com.liferay.commerce.service.CommerceRegionLocalService commerceRegionLocalService) {
		this.commerceRegionLocalService = commerceRegionLocalService;
	}

	/**
	 * Returns the commerce region remote service.
	 *
	 * @return the commerce region remote service
	 */
	public CommerceRegionService getCommerceRegionService() {
		return commerceRegionService;
	}

	/**
	 * Sets the commerce region remote service.
	 *
	 * @param commerceRegionService the commerce region remote service
	 */
	public void setCommerceRegionService(
		CommerceRegionService commerceRegionService) {
		this.commerceRegionService = commerceRegionService;
	}

	/**
	 * Returns the commerce region persistence.
	 *
	 * @return the commerce region persistence
	 */
	public CommerceRegionPersistence getCommerceRegionPersistence() {
		return commerceRegionPersistence;
	}

	/**
	 * Sets the commerce region persistence.
	 *
	 * @param commerceRegionPersistence the commerce region persistence
	 */
	public void setCommerceRegionPersistence(
		CommerceRegionPersistence commerceRegionPersistence) {
		this.commerceRegionPersistence = commerceRegionPersistence;
	}

	/**
	 * Returns the commerce shipping method local service.
	 *
	 * @return the commerce shipping method local service
	 */
	public com.liferay.commerce.service.CommerceShippingMethodLocalService getCommerceShippingMethodLocalService() {
		return commerceShippingMethodLocalService;
	}

	/**
	 * Sets the commerce shipping method local service.
	 *
	 * @param commerceShippingMethodLocalService the commerce shipping method local service
	 */
	public void setCommerceShippingMethodLocalService(
		com.liferay.commerce.service.CommerceShippingMethodLocalService commerceShippingMethodLocalService) {
		this.commerceShippingMethodLocalService = commerceShippingMethodLocalService;
	}

	/**
	 * Returns the commerce shipping method remote service.
	 *
	 * @return the commerce shipping method remote service
	 */
	public com.liferay.commerce.service.CommerceShippingMethodService getCommerceShippingMethodService() {
		return commerceShippingMethodService;
	}

	/**
	 * Sets the commerce shipping method remote service.
	 *
	 * @param commerceShippingMethodService the commerce shipping method remote service
	 */
	public void setCommerceShippingMethodService(
		com.liferay.commerce.service.CommerceShippingMethodService commerceShippingMethodService) {
		this.commerceShippingMethodService = commerceShippingMethodService;
	}

	/**
	 * Returns the commerce shipping method persistence.
	 *
	 * @return the commerce shipping method persistence
	 */
	public CommerceShippingMethodPersistence getCommerceShippingMethodPersistence() {
		return commerceShippingMethodPersistence;
	}

	/**
	 * Sets the commerce shipping method persistence.
	 *
	 * @param commerceShippingMethodPersistence the commerce shipping method persistence
	 */
	public void setCommerceShippingMethodPersistence(
		CommerceShippingMethodPersistence commerceShippingMethodPersistence) {
		this.commerceShippingMethodPersistence = commerceShippingMethodPersistence;
	}

	/**
	 * Returns the commerce warehouse local service.
	 *
	 * @return the commerce warehouse local service
	 */
	public com.liferay.commerce.service.CommerceWarehouseLocalService getCommerceWarehouseLocalService() {
		return commerceWarehouseLocalService;
	}

	/**
	 * Sets the commerce warehouse local service.
	 *
	 * @param commerceWarehouseLocalService the commerce warehouse local service
	 */
	public void setCommerceWarehouseLocalService(
		com.liferay.commerce.service.CommerceWarehouseLocalService commerceWarehouseLocalService) {
		this.commerceWarehouseLocalService = commerceWarehouseLocalService;
	}

	/**
	 * Returns the commerce warehouse remote service.
	 *
	 * @return the commerce warehouse remote service
	 */
	public com.liferay.commerce.service.CommerceWarehouseService getCommerceWarehouseService() {
		return commerceWarehouseService;
	}

	/**
	 * Sets the commerce warehouse remote service.
	 *
	 * @param commerceWarehouseService the commerce warehouse remote service
	 */
	public void setCommerceWarehouseService(
		com.liferay.commerce.service.CommerceWarehouseService commerceWarehouseService) {
		this.commerceWarehouseService = commerceWarehouseService;
	}

	/**
	 * Returns the commerce warehouse persistence.
	 *
	 * @return the commerce warehouse persistence
	 */
	public CommerceWarehousePersistence getCommerceWarehousePersistence() {
		return commerceWarehousePersistence;
	}

	/**
	 * Sets the commerce warehouse persistence.
	 *
	 * @param commerceWarehousePersistence the commerce warehouse persistence
	 */
	public void setCommerceWarehousePersistence(
		CommerceWarehousePersistence commerceWarehousePersistence) {
		this.commerceWarehousePersistence = commerceWarehousePersistence;
	}

	/**
	 * Returns the commerce warehouse finder.
	 *
	 * @return the commerce warehouse finder
	 */
	public CommerceWarehouseFinder getCommerceWarehouseFinder() {
		return commerceWarehouseFinder;
	}

	/**
	 * Sets the commerce warehouse finder.
	 *
	 * @param commerceWarehouseFinder the commerce warehouse finder
	 */
	public void setCommerceWarehouseFinder(
		CommerceWarehouseFinder commerceWarehouseFinder) {
		this.commerceWarehouseFinder = commerceWarehouseFinder;
	}

	/**
	 * Returns the commerce warehouse item local service.
	 *
	 * @return the commerce warehouse item local service
	 */
	public com.liferay.commerce.service.CommerceWarehouseItemLocalService getCommerceWarehouseItemLocalService() {
		return commerceWarehouseItemLocalService;
	}

	/**
	 * Sets the commerce warehouse item local service.
	 *
	 * @param commerceWarehouseItemLocalService the commerce warehouse item local service
	 */
	public void setCommerceWarehouseItemLocalService(
		com.liferay.commerce.service.CommerceWarehouseItemLocalService commerceWarehouseItemLocalService) {
		this.commerceWarehouseItemLocalService = commerceWarehouseItemLocalService;
	}

	/**
	 * Returns the commerce warehouse item remote service.
	 *
	 * @return the commerce warehouse item remote service
	 */
	public com.liferay.commerce.service.CommerceWarehouseItemService getCommerceWarehouseItemService() {
		return commerceWarehouseItemService;
	}

	/**
	 * Sets the commerce warehouse item remote service.
	 *
	 * @param commerceWarehouseItemService the commerce warehouse item remote service
	 */
	public void setCommerceWarehouseItemService(
		com.liferay.commerce.service.CommerceWarehouseItemService commerceWarehouseItemService) {
		this.commerceWarehouseItemService = commerceWarehouseItemService;
	}

	/**
	 * Returns the commerce warehouse item persistence.
	 *
	 * @return the commerce warehouse item persistence
	 */
	public CommerceWarehouseItemPersistence getCommerceWarehouseItemPersistence() {
		return commerceWarehouseItemPersistence;
	}

	/**
	 * Sets the commerce warehouse item persistence.
	 *
	 * @param commerceWarehouseItemPersistence the commerce warehouse item persistence
	 */
	public void setCommerceWarehouseItemPersistence(
		CommerceWarehouseItemPersistence commerceWarehouseItemPersistence) {
		this.commerceWarehouseItemPersistence = commerceWarehouseItemPersistence;
	}

	/**
	 * Returns the commerce warehouse item finder.
	 *
	 * @return the commerce warehouse item finder
	 */
	public CommerceWarehouseItemFinder getCommerceWarehouseItemFinder() {
		return commerceWarehouseItemFinder;
	}

	/**
	 * Sets the commerce warehouse item finder.
	 *
	 * @param commerceWarehouseItemFinder the commerce warehouse item finder
	 */
	public void setCommerceWarehouseItemFinder(
		CommerceWarehouseItemFinder commerceWarehouseItemFinder) {
		this.commerceWarehouseItemFinder = commerceWarehouseItemFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
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
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
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
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceRegionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceRegion.class;
	}

	protected String getModelClassName() {
		return CommerceRegion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = commerceRegionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.commerce.service.CommerceCartLocalService.class)
	protected com.liferay.commerce.service.CommerceCartLocalService commerceCartLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceCartService.class)
	protected com.liferay.commerce.service.CommerceCartService commerceCartService;
	@BeanReference(type = CommerceCartPersistence.class)
	protected CommerceCartPersistence commerceCartPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommerceCartItemLocalService.class)
	protected com.liferay.commerce.service.CommerceCartItemLocalService commerceCartItemLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceCartItemService.class)
	protected com.liferay.commerce.service.CommerceCartItemService commerceCartItemService;
	@BeanReference(type = CommerceCartItemPersistence.class)
	protected CommerceCartItemPersistence commerceCartItemPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommerceCountryLocalService.class)
	protected com.liferay.commerce.service.CommerceCountryLocalService commerceCountryLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceCountryService.class)
	protected com.liferay.commerce.service.CommerceCountryService commerceCountryService;
	@BeanReference(type = CommerceCountryPersistence.class)
	protected CommerceCountryPersistence commerceCountryPersistence;
	@BeanReference(type = CommerceCountryFinder.class)
	protected CommerceCountryFinder commerceCountryFinder;
	@BeanReference(type = com.liferay.commerce.service.CommerceOrderLocalService.class)
	protected com.liferay.commerce.service.CommerceOrderLocalService commerceOrderLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceOrderService.class)
	protected com.liferay.commerce.service.CommerceOrderService commerceOrderService;
	@BeanReference(type = CommerceOrderPersistence.class)
	protected CommerceOrderPersistence commerceOrderPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommerceOrderItemLocalService.class)
	protected com.liferay.commerce.service.CommerceOrderItemLocalService commerceOrderItemLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceOrderItemService.class)
	protected com.liferay.commerce.service.CommerceOrderItemService commerceOrderItemService;
	@BeanReference(type = CommerceOrderItemPersistence.class)
	protected CommerceOrderItemPersistence commerceOrderItemPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommercePaymentMethodLocalService.class)
	protected com.liferay.commerce.service.CommercePaymentMethodLocalService commercePaymentMethodLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommercePaymentMethodService.class)
	protected com.liferay.commerce.service.CommercePaymentMethodService commercePaymentMethodService;
	@BeanReference(type = CommercePaymentMethodPersistence.class)
	protected CommercePaymentMethodPersistence commercePaymentMethodPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommerceRegionLocalService.class)
	protected com.liferay.commerce.service.CommerceRegionLocalService commerceRegionLocalService;
	@BeanReference(type = CommerceRegionService.class)
	protected CommerceRegionService commerceRegionService;
	@BeanReference(type = CommerceRegionPersistence.class)
	protected CommerceRegionPersistence commerceRegionPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommerceShippingMethodLocalService.class)
	protected com.liferay.commerce.service.CommerceShippingMethodLocalService commerceShippingMethodLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceShippingMethodService.class)
	protected com.liferay.commerce.service.CommerceShippingMethodService commerceShippingMethodService;
	@BeanReference(type = CommerceShippingMethodPersistence.class)
	protected CommerceShippingMethodPersistence commerceShippingMethodPersistence;
	@BeanReference(type = com.liferay.commerce.service.CommerceWarehouseLocalService.class)
	protected com.liferay.commerce.service.CommerceWarehouseLocalService commerceWarehouseLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceWarehouseService.class)
	protected com.liferay.commerce.service.CommerceWarehouseService commerceWarehouseService;
	@BeanReference(type = CommerceWarehousePersistence.class)
	protected CommerceWarehousePersistence commerceWarehousePersistence;
	@BeanReference(type = CommerceWarehouseFinder.class)
	protected CommerceWarehouseFinder commerceWarehouseFinder;
	@BeanReference(type = com.liferay.commerce.service.CommerceWarehouseItemLocalService.class)
	protected com.liferay.commerce.service.CommerceWarehouseItemLocalService commerceWarehouseItemLocalService;
	@BeanReference(type = com.liferay.commerce.service.CommerceWarehouseItemService.class)
	protected com.liferay.commerce.service.CommerceWarehouseItemService commerceWarehouseItemService;
	@BeanReference(type = CommerceWarehouseItemPersistence.class)
	protected CommerceWarehouseItemPersistence commerceWarehouseItemPersistence;
	@BeanReference(type = CommerceWarehouseItemFinder.class)
	protected CommerceWarehouseItemFinder commerceWarehouseItemFinder;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}