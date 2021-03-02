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

package com.liferay.message.boards.service.base;

import com.liferay.message.boards.model.MBStatsUser;
import com.liferay.message.boards.service.MBStatsUserLocalService;
import com.liferay.message.boards.service.MBStatsUserLocalServiceUtil;
import com.liferay.message.boards.service.persistence.MBStatsUserPersistence;
import com.liferay.petra.function.UnsafeFunction;
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
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
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
 * Provides the base implementation for the message boards stats user local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.message.boards.service.impl.MBStatsUserLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.message.boards.service.impl.MBStatsUserLocalServiceImpl
 * @generated
 */
public abstract class MBStatsUserLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, MBStatsUserLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MBStatsUserLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MBStatsUserLocalServiceUtil</code>.
	 */

	/**
	 * Adds the message boards stats user to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MBStatsUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mbStatsUser the message boards stats user
	 * @return the message boards stats user that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MBStatsUser addMBStatsUser(MBStatsUser mbStatsUser) {
		mbStatsUser.setNew(true);

		return mbStatsUserPersistence.update(mbStatsUser);
	}

	/**
	 * Creates a new message boards stats user with the primary key. Does not add the message boards stats user to the database.
	 *
	 * @param statsUserId the primary key for the new message boards stats user
	 * @return the new message boards stats user
	 */
	@Override
	@Transactional(enabled = false)
	public MBStatsUser createMBStatsUser(long statsUserId) {
		return mbStatsUserPersistence.create(statsUserId);
	}

	/**
	 * Deletes the message boards stats user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MBStatsUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param statsUserId the primary key of the message boards stats user
	 * @return the message boards stats user that was removed
	 * @throws PortalException if a message boards stats user with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MBStatsUser deleteMBStatsUser(long statsUserId)
		throws PortalException {

		return mbStatsUserPersistence.remove(statsUserId);
	}

	/**
	 * Deletes the message boards stats user from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MBStatsUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mbStatsUser the message boards stats user
	 * @return the message boards stats user that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MBStatsUser deleteMBStatsUser(MBStatsUser mbStatsUser) {
		return mbStatsUserPersistence.remove(mbStatsUser);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return mbStatsUserPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			MBStatsUser.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return mbStatsUserPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.message.boards.model.impl.MBStatsUserModelImpl</code>.
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

		return mbStatsUserPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.message.boards.model.impl.MBStatsUserModelImpl</code>.
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

		return mbStatsUserPersistence.findWithDynamicQuery(
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
		return mbStatsUserPersistence.countWithDynamicQuery(dynamicQuery);
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

		return mbStatsUserPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MBStatsUser fetchMBStatsUser(long statsUserId) {
		return mbStatsUserPersistence.fetchByPrimaryKey(statsUserId);
	}

	/**
	 * Returns the message boards stats user with the primary key.
	 *
	 * @param statsUserId the primary key of the message boards stats user
	 * @return the message boards stats user
	 * @throws PortalException if a message boards stats user with the primary key could not be found
	 */
	@Override
	public MBStatsUser getMBStatsUser(long statsUserId) throws PortalException {
		return mbStatsUserPersistence.findByPrimaryKey(statsUserId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(mbStatsUserLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MBStatsUser.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("statsUserId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			mbStatsUserLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MBStatsUser.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"statsUserId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(mbStatsUserLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MBStatsUser.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("statsUserId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mbStatsUserPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return mbStatsUserLocalService.deleteMBStatsUser(
			(MBStatsUser)persistedModel);
	}

	public BasePersistence<MBStatsUser> getBasePersistence() {
		return mbStatsUserPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mbStatsUserPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the message boards stats users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.message.boards.model.impl.MBStatsUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of message boards stats users
	 * @param end the upper bound of the range of message boards stats users (not inclusive)
	 * @return the range of message boards stats users
	 */
	@Override
	public List<MBStatsUser> getMBStatsUsers(int start, int end) {
		return mbStatsUserPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of message boards stats users.
	 *
	 * @return the number of message boards stats users
	 */
	@Override
	public int getMBStatsUsersCount() {
		return mbStatsUserPersistence.countAll();
	}

	/**
	 * Updates the message boards stats user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MBStatsUserLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mbStatsUser the message boards stats user
	 * @return the message boards stats user that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MBStatsUser updateMBStatsUser(MBStatsUser mbStatsUser) {
		return mbStatsUserPersistence.update(mbStatsUser);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MBStatsUserLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		mbStatsUserLocalService = (MBStatsUserLocalService)aopProxy;

		_setLocalServiceUtilService(mbStatsUserLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MBStatsUserLocalService.class.getName();
	}

	@Override
	public CTPersistence<MBStatsUser> getCTPersistence() {
		return mbStatsUserPersistence;
	}

	@Override
	public Class<MBStatsUser> getModelClass() {
		return MBStatsUser.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<MBStatsUser>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(mbStatsUserPersistence);
	}

	protected String getModelClassName() {
		return MBStatsUser.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mbStatsUserPersistence.getDataSource();

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
		MBStatsUserLocalService mbStatsUserLocalService) {

		try {
			Field field = MBStatsUserLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, mbStatsUserLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected MBStatsUserLocalService mbStatsUserLocalService;

	@Reference
	protected MBStatsUserPersistence mbStatsUserPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}