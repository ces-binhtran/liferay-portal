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

package com.liferay.sync.service.base;

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
import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.service.SyncDLFileVersionDiffLocalService;
import com.liferay.sync.service.persistence.SyncDLFileVersionDiffPersistence;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;
import com.liferay.sync.service.persistence.SyncDLObjectPersistence;
import com.liferay.sync.service.persistence.SyncDevicePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the sync dl file version diff local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.sync.service.impl.SyncDLFileVersionDiffLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.impl.SyncDLFileVersionDiffLocalServiceImpl
 * @generated
 */
public abstract class SyncDLFileVersionDiffLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   SyncDLFileVersionDiffLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SyncDLFileVersionDiffLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.sync.service.SyncDLFileVersionDiffLocalServiceUtil</code>.
	 */

	/**
	 * Adds the sync dl file version diff to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLFileVersionDiffLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLFileVersionDiff the sync dl file version diff
	 * @return the sync dl file version diff that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SyncDLFileVersionDiff addSyncDLFileVersionDiff(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {

		syncDLFileVersionDiff.setNew(true);

		return syncDLFileVersionDiffPersistence.update(syncDLFileVersionDiff);
	}

	/**
	 * Creates a new sync dl file version diff with the primary key. Does not add the sync dl file version diff to the database.
	 *
	 * @param syncDLFileVersionDiffId the primary key for the new sync dl file version diff
	 * @return the new sync dl file version diff
	 */
	@Override
	@Transactional(enabled = false)
	public SyncDLFileVersionDiff createSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId) {

		return syncDLFileVersionDiffPersistence.create(syncDLFileVersionDiffId);
	}

	/**
	 * Deletes the sync dl file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLFileVersionDiffLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLFileVersionDiffId the primary key of the sync dl file version diff
	 * @return the sync dl file version diff that was removed
	 * @throws PortalException if a sync dl file version diff with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
			long syncDLFileVersionDiffId)
		throws PortalException {

		return syncDLFileVersionDiffPersistence.remove(syncDLFileVersionDiffId);
	}

	/**
	 * Deletes the sync dl file version diff from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLFileVersionDiffLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLFileVersionDiff the sync dl file version diff
	 * @return the sync dl file version diff that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
			SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws PortalException {

		return syncDLFileVersionDiffPersistence.remove(syncDLFileVersionDiff);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			SyncDLFileVersionDiff.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return syncDLFileVersionDiffPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl</code>.
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

		return syncDLFileVersionDiffPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl</code>.
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

		return syncDLFileVersionDiffPersistence.findWithDynamicQuery(
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
		return syncDLFileVersionDiffPersistence.countWithDynamicQuery(
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

		return syncDLFileVersionDiffPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SyncDLFileVersionDiff fetchSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId) {

		return syncDLFileVersionDiffPersistence.fetchByPrimaryKey(
			syncDLFileVersionDiffId);
	}

	/**
	 * Returns the sync dl file version diff with the primary key.
	 *
	 * @param syncDLFileVersionDiffId the primary key of the sync dl file version diff
	 * @return the sync dl file version diff
	 * @throws PortalException if a sync dl file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff getSyncDLFileVersionDiff(
			long syncDLFileVersionDiffId)
		throws PortalException {

		return syncDLFileVersionDiffPersistence.findByPrimaryKey(
			syncDLFileVersionDiffId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			syncDLFileVersionDiffLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SyncDLFileVersionDiff.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"syncDLFileVersionDiffId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			syncDLFileVersionDiffLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			SyncDLFileVersionDiff.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"syncDLFileVersionDiffId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			syncDLFileVersionDiffLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SyncDLFileVersionDiff.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"syncDLFileVersionDiffId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return syncDLFileVersionDiffLocalService.deleteSyncDLFileVersionDiff(
			(SyncDLFileVersionDiff)persistedModel);
	}

	public BasePersistence<SyncDLFileVersionDiff> getBasePersistence() {
		return syncDLFileVersionDiffPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return syncDLFileVersionDiffPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the sync dl file version diffs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync dl file version diffs
	 * @param end the upper bound of the range of sync dl file version diffs (not inclusive)
	 * @return the range of sync dl file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> getSyncDLFileVersionDiffs(
		int start, int end) {

		return syncDLFileVersionDiffPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of sync dl file version diffs.
	 *
	 * @return the number of sync dl file version diffs
	 */
	@Override
	public int getSyncDLFileVersionDiffsCount() {
		return syncDLFileVersionDiffPersistence.countAll();
	}

	/**
	 * Updates the sync dl file version diff in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SyncDLFileVersionDiffLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param syncDLFileVersionDiff the sync dl file version diff
	 * @return the sync dl file version diff that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SyncDLFileVersionDiff updateSyncDLFileVersionDiff(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {

		return syncDLFileVersionDiffPersistence.update(syncDLFileVersionDiff);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SyncDLFileVersionDiffLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		syncDLFileVersionDiffLocalService =
			(SyncDLFileVersionDiffLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SyncDLFileVersionDiffLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SyncDLFileVersionDiff.class;
	}

	protected String getModelClassName() {
		return SyncDLFileVersionDiff.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				syncDLFileVersionDiffPersistence.getDataSource();

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

	@Reference
	protected SyncDevicePersistence syncDevicePersistence;

	protected SyncDLFileVersionDiffLocalService
		syncDLFileVersionDiffLocalService;

	@Reference
	protected SyncDLFileVersionDiffPersistence syncDLFileVersionDiffPersistence;

	@Reference
	protected SyncDLObjectPersistence syncDLObjectPersistence;

	@Reference
	protected SyncDLObjectFinder syncDLObjectFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.CompanyLocalService
		companyLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

}