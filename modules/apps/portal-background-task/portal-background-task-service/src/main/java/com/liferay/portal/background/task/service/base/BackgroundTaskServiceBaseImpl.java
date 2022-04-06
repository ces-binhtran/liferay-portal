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

package com.liferay.portal.background.task.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskService;
import com.liferay.portal.background.task.service.BackgroundTaskServiceUtil;
import com.liferay.portal.background.task.service.persistence.BackgroundTaskFinder;
import com.liferay.portal.background.task.service.persistence.BackgroundTaskPersistence;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the background task remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.background.task.service.impl.BackgroundTaskServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.background.task.service.impl.BackgroundTaskServiceImpl
 * @generated
 */
public abstract class BackgroundTaskServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, BackgroundTaskService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>BackgroundTaskService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>BackgroundTaskServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			BackgroundTaskService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		backgroundTaskService = (BackgroundTaskService)aopProxy;

		_setServiceUtilService(backgroundTaskService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BackgroundTaskService.class.getName();
	}

	protected Class<?> getModelClass() {
		return BackgroundTask.class;
	}

	protected String getModelClassName() {
		return BackgroundTask.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = backgroundTaskPersistence.getDataSource();

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
		BackgroundTaskService backgroundTaskService) {

		try {
			Field field = BackgroundTaskServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, backgroundTaskService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected
		com.liferay.portal.background.task.service.BackgroundTaskLocalService
			backgroundTaskLocalService;

	protected BackgroundTaskService backgroundTaskService;

	@Reference
	protected BackgroundTaskPersistence backgroundTaskPersistence;

	@Reference
	protected BackgroundTaskFinder backgroundTaskFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}