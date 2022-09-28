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

package com.liferay.commerce.product.type.virtual.order.service.base;

import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemService;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemServiceUtil;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemFinder;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the commerce virtual order item remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.virtual.order.service.impl.CommerceVirtualOrderItemServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.product.type.virtual.order.service.impl.CommerceVirtualOrderItemServiceImpl
 * @generated
 */
public abstract class CommerceVirtualOrderItemServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, CommerceVirtualOrderItemService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceVirtualOrderItemService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceVirtualOrderItemServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CommerceVirtualOrderItemService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		commerceVirtualOrderItemService =
			(CommerceVirtualOrderItemService)aopProxy;

		_setServiceUtilService(commerceVirtualOrderItemService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceVirtualOrderItemService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceVirtualOrderItem.class;
	}

	protected String getModelClassName() {
		return CommerceVirtualOrderItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceVirtualOrderItemPersistence.getDataSource();

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
		CommerceVirtualOrderItemService commerceVirtualOrderItemService) {

		try {
			Field field =
				CommerceVirtualOrderItemServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceVirtualOrderItemService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected com.liferay.commerce.product.type.virtual.order.service.
		CommerceVirtualOrderItemLocalService
			commerceVirtualOrderItemLocalService;

	protected CommerceVirtualOrderItemService commerceVirtualOrderItemService;

	@Reference
	protected CommerceVirtualOrderItemPersistence
		commerceVirtualOrderItemPersistence;

	@Reference
	protected CommerceVirtualOrderItemFinder commerceVirtualOrderItemFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceVirtualOrderItemServiceBaseImpl.class);

}