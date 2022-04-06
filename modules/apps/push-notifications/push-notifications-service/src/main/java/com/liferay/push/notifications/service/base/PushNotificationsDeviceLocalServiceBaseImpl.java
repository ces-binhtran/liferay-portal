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

package com.liferay.push.notifications.service.base;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.push.notifications.model.PushNotificationsDevice;
import com.liferay.push.notifications.service.PushNotificationsDeviceLocalService;
import com.liferay.push.notifications.service.PushNotificationsDeviceLocalServiceUtil;
import com.liferay.push.notifications.service.persistence.PushNotificationsDevicePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the push notifications device local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.push.notifications.service.impl.PushNotificationsDeviceLocalServiceImpl}.
 * </p>
 *
 * @author Bruno Farache
 * @see com.liferay.push.notifications.service.impl.PushNotificationsDeviceLocalServiceImpl
 * @generated
 */
public abstract class PushNotificationsDeviceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   PushNotificationsDeviceLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PushNotificationsDeviceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>PushNotificationsDeviceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the push notifications device to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PushNotificationsDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pushNotificationsDevice the push notifications device
	 * @return the push notifications device that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PushNotificationsDevice addPushNotificationsDevice(
		PushNotificationsDevice pushNotificationsDevice) {

		pushNotificationsDevice.setNew(true);

		return pushNotificationsDevicePersistence.update(
			pushNotificationsDevice);
	}

	/**
	 * Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	 *
	 * @param pushNotificationsDeviceId the primary key for the new push notifications device
	 * @return the new push notifications device
	 */
	@Override
	@Transactional(enabled = false)
	public PushNotificationsDevice createPushNotificationsDevice(
		long pushNotificationsDeviceId) {

		return pushNotificationsDevicePersistence.create(
			pushNotificationsDeviceId);
	}

	/**
	 * Deletes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PushNotificationsDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device that was removed
	 * @throws PortalException if a push notifications device with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PushNotificationsDevice deletePushNotificationsDevice(
			long pushNotificationsDeviceId)
		throws PortalException {

		return pushNotificationsDevicePersistence.remove(
			pushNotificationsDeviceId);
	}

	/**
	 * Deletes the push notifications device from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PushNotificationsDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pushNotificationsDevice the push notifications device
	 * @return the push notifications device that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PushNotificationsDevice deletePushNotificationsDevice(
		PushNotificationsDevice pushNotificationsDevice) {

		return pushNotificationsDevicePersistence.remove(
			pushNotificationsDevice);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return pushNotificationsDevicePersistence.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(DSLQuery dslQuery) {
		Long count = dslQuery(dslQuery);

		return count.intValue();
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PushNotificationsDevice.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return pushNotificationsDevicePersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.push.notifications.model.impl.PushNotificationsDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return pushNotificationsDevicePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.push.notifications.model.impl.PushNotificationsDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return pushNotificationsDevicePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return pushNotificationsDevicePersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return pushNotificationsDevicePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PushNotificationsDevice fetchPushNotificationsDevice(
		long pushNotificationsDeviceId) {

		return pushNotificationsDevicePersistence.fetchByPrimaryKey(
			pushNotificationsDeviceId);
	}

	/**
	 * Returns the push notifications device with the primary key.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device
	 * @throws PortalException if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice getPushNotificationsDevice(
			long pushNotificationsDeviceId)
		throws PortalException {

		return pushNotificationsDevicePersistence.findByPrimaryKey(
			pushNotificationsDeviceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			pushNotificationsDeviceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PushNotificationsDevice.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"pushNotificationsDeviceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			pushNotificationsDeviceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			PushNotificationsDevice.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"pushNotificationsDeviceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			pushNotificationsDeviceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PushNotificationsDevice.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"pushNotificationsDeviceId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return pushNotificationsDevicePersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return pushNotificationsDeviceLocalService.
			deletePushNotificationsDevice(
				(PushNotificationsDevice)persistedModel);
	}

	@Override
	public BasePersistence<PushNotificationsDevice> getBasePersistence() {
		return pushNotificationsDevicePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return pushNotificationsDevicePersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the push notifications devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.push.notifications.model.impl.PushNotificationsDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @return the range of push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end) {

		return pushNotificationsDevicePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of push notifications devices.
	 *
	 * @return the number of push notifications devices
	 */
	@Override
	public int getPushNotificationsDevicesCount() {
		return pushNotificationsDevicePersistence.countAll();
	}

	/**
	 * Updates the push notifications device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PushNotificationsDeviceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param pushNotificationsDevice the push notifications device
	 * @return the push notifications device that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PushNotificationsDevice updatePushNotificationsDevice(
		PushNotificationsDevice pushNotificationsDevice) {

		return pushNotificationsDevicePersistence.update(
			pushNotificationsDevice);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			PushNotificationsDeviceLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		pushNotificationsDeviceLocalService =
			(PushNotificationsDeviceLocalService)aopProxy;

		_setLocalServiceUtilService(pushNotificationsDeviceLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PushNotificationsDeviceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PushNotificationsDevice.class;
	}

	protected String getModelClassName() {
		return PushNotificationsDevice.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				pushNotificationsDevicePersistence.getDataSource();

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

	private void _setLocalServiceUtilService(
		PushNotificationsDeviceLocalService
			pushNotificationsDeviceLocalService) {

		try {
			Field field =
				PushNotificationsDeviceLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, pushNotificationsDeviceLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected PushNotificationsDeviceLocalService
		pushNotificationsDeviceLocalService;

	@Reference
	protected PushNotificationsDevicePersistence
		pushNotificationsDevicePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}