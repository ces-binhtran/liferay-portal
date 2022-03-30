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

package com.liferay.dispatch.service.base;

import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.dispatch.service.DispatchTriggerLocalServiceUtil;
import com.liferay.dispatch.service.persistence.DispatchLogPersistence;
import com.liferay.dispatch.service.persistence.DispatchTriggerPersistence;
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

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the dispatch trigger local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dispatch.service.impl.DispatchTriggerLocalServiceImpl}.
 * </p>
 *
 * @author Matija Petanjek
 * @see com.liferay.dispatch.service.impl.DispatchTriggerLocalServiceImpl
 * @generated
 */
public abstract class DispatchTriggerLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, DispatchTriggerLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DispatchTriggerLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>DispatchTriggerLocalServiceUtil</code>.
	 */

	/**
	 * Adds the dispatch trigger to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DispatchTriggerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dispatchTrigger the dispatch trigger
	 * @return the dispatch trigger that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DispatchTrigger addDispatchTrigger(DispatchTrigger dispatchTrigger) {
		dispatchTrigger.setNew(true);

		return dispatchTriggerPersistence.update(dispatchTrigger);
	}

	/**
	 * Creates a new dispatch trigger with the primary key. Does not add the dispatch trigger to the database.
	 *
	 * @param dispatchTriggerId the primary key for the new dispatch trigger
	 * @return the new dispatch trigger
	 */
	@Override
	@Transactional(enabled = false)
	public DispatchTrigger createDispatchTrigger(long dispatchTriggerId) {
		return dispatchTriggerPersistence.create(dispatchTriggerId);
	}

	/**
	 * Deletes the dispatch trigger with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DispatchTriggerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dispatchTriggerId the primary key of the dispatch trigger
	 * @return the dispatch trigger that was removed
	 * @throws PortalException if a dispatch trigger with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DispatchTrigger deleteDispatchTrigger(long dispatchTriggerId)
		throws PortalException {

		return dispatchTriggerPersistence.remove(dispatchTriggerId);
	}

	/**
	 * Deletes the dispatch trigger from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DispatchTriggerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dispatchTrigger the dispatch trigger
	 * @return the dispatch trigger that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DispatchTrigger deleteDispatchTrigger(
			DispatchTrigger dispatchTrigger)
		throws PortalException {

		return dispatchTriggerPersistence.remove(dispatchTrigger);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return dispatchTriggerPersistence.dslQuery(dslQuery);
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
			DispatchTrigger.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return dispatchTriggerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dispatch.model.impl.DispatchTriggerModelImpl</code>.
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

		return dispatchTriggerPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dispatch.model.impl.DispatchTriggerModelImpl</code>.
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

		return dispatchTriggerPersistence.findWithDynamicQuery(
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
		return dispatchTriggerPersistence.countWithDynamicQuery(dynamicQuery);
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

		return dispatchTriggerPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DispatchTrigger fetchDispatchTrigger(long dispatchTriggerId) {
		return dispatchTriggerPersistence.fetchByPrimaryKey(dispatchTriggerId);
	}

	/**
	 * Returns the dispatch trigger with the primary key.
	 *
	 * @param dispatchTriggerId the primary key of the dispatch trigger
	 * @return the dispatch trigger
	 * @throws PortalException if a dispatch trigger with the primary key could not be found
	 */
	@Override
	public DispatchTrigger getDispatchTrigger(long dispatchTriggerId)
		throws PortalException {

		return dispatchTriggerPersistence.findByPrimaryKey(dispatchTriggerId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(dispatchTriggerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DispatchTrigger.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("dispatchTriggerId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			dispatchTriggerLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DispatchTrigger.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"dispatchTriggerId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(dispatchTriggerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DispatchTrigger.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("dispatchTriggerId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return dispatchTriggerPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return dispatchTriggerLocalService.deleteDispatchTrigger(
			(DispatchTrigger)persistedModel);
	}

	@Override
	public BasePersistence<DispatchTrigger> getBasePersistence() {
		return dispatchTriggerPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return dispatchTriggerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the dispatch triggers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dispatch.model.impl.DispatchTriggerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dispatch triggers
	 * @param end the upper bound of the range of dispatch triggers (not inclusive)
	 * @return the range of dispatch triggers
	 */
	@Override
	public List<DispatchTrigger> getDispatchTriggers(int start, int end) {
		return dispatchTriggerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of dispatch triggers.
	 *
	 * @return the number of dispatch triggers
	 */
	@Override
	public int getDispatchTriggersCount() {
		return dispatchTriggerPersistence.countAll();
	}

	/**
	 * Updates the dispatch trigger in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DispatchTriggerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dispatchTrigger the dispatch trigger
	 * @return the dispatch trigger that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DispatchTrigger updateDispatchTrigger(
		DispatchTrigger dispatchTrigger) {

		return dispatchTriggerPersistence.update(dispatchTrigger);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DispatchTriggerLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		dispatchTriggerLocalService = (DispatchTriggerLocalService)aopProxy;

		_setLocalServiceUtilService(dispatchTriggerLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DispatchTriggerLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DispatchTrigger.class;
	}

	protected String getModelClassName() {
		return DispatchTrigger.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = dispatchTriggerPersistence.getDataSource();

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
		DispatchTriggerLocalService dispatchTriggerLocalService) {

		try {
			Field field =
				DispatchTriggerLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, dispatchTriggerLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected DispatchLogPersistence dispatchLogPersistence;

	protected DispatchTriggerLocalService dispatchTriggerLocalService;

	@Reference
	protected DispatchTriggerPersistence dispatchTriggerPersistence;

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