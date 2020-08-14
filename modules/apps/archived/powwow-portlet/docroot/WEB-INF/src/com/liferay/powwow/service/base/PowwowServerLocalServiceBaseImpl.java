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

package com.liferay.powwow.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.service.PowwowServerLocalService;
import com.liferay.powwow.service.persistence.PowwowMeetingFinder;
import com.liferay.powwow.service.persistence.PowwowMeetingPersistence;
import com.liferay.powwow.service.persistence.PowwowParticipantPersistence;
import com.liferay.powwow.service.persistence.PowwowServerPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the powwow server local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.powwow.service.impl.PowwowServerLocalServiceImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see com.liferay.powwow.service.impl.PowwowServerLocalServiceImpl
 * @generated
 */
public abstract class PowwowServerLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, PowwowServerLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PowwowServerLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.powwow.service.PowwowServerLocalServiceUtil</code>.
	 */

	/**
	 * Adds the powwow server to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowServerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowServer the powwow server
	 * @return the powwow server that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowServer addPowwowServer(PowwowServer powwowServer) {
		powwowServer.setNew(true);

		return powwowServerPersistence.update(powwowServer);
	}

	/**
	 * Creates a new powwow server with the primary key. Does not add the powwow server to the database.
	 *
	 * @param powwowServerId the primary key for the new powwow server
	 * @return the new powwow server
	 */
	@Override
	@Transactional(enabled = false)
	public PowwowServer createPowwowServer(long powwowServerId) {
		return powwowServerPersistence.create(powwowServerId);
	}

	/**
	 * Deletes the powwow server with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowServerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowServerId the primary key of the powwow server
	 * @return the powwow server that was removed
	 * @throws PortalException if a powwow server with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PowwowServer deletePowwowServer(long powwowServerId)
		throws PortalException {

		return powwowServerPersistence.remove(powwowServerId);
	}

	/**
	 * Deletes the powwow server from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowServerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowServer the powwow server
	 * @return the powwow server that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PowwowServer deletePowwowServer(PowwowServer powwowServer) {
		return powwowServerPersistence.remove(powwowServer);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PowwowServer.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return powwowServerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowServerModelImpl</code>.
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

		return powwowServerPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowServerModelImpl</code>.
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

		return powwowServerPersistence.findWithDynamicQuery(
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
		return powwowServerPersistence.countWithDynamicQuery(dynamicQuery);
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

		return powwowServerPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PowwowServer fetchPowwowServer(long powwowServerId) {
		return powwowServerPersistence.fetchByPrimaryKey(powwowServerId);
	}

	/**
	 * Returns the powwow server with the primary key.
	 *
	 * @param powwowServerId the primary key of the powwow server
	 * @return the powwow server
	 * @throws PortalException if a powwow server with the primary key could not be found
	 */
	@Override
	public PowwowServer getPowwowServer(long powwowServerId)
		throws PortalException {

		return powwowServerPersistence.findByPrimaryKey(powwowServerId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(powwowServerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PowwowServer.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("powwowServerId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			powwowServerLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PowwowServer.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"powwowServerId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(powwowServerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PowwowServer.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("powwowServerId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return powwowServerPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return powwowServerLocalService.deletePowwowServer(
			(PowwowServer)persistedModel);
	}

	public BasePersistence<PowwowServer> getBasePersistence() {
		return powwowServerPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return powwowServerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the powwow servers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowServerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow servers
	 * @param end the upper bound of the range of powwow servers (not inclusive)
	 * @return the range of powwow servers
	 */
	@Override
	public List<PowwowServer> getPowwowServers(int start, int end) {
		return powwowServerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of powwow servers.
	 *
	 * @return the number of powwow servers
	 */
	@Override
	public int getPowwowServersCount() {
		return powwowServerPersistence.countAll();
	}

	/**
	 * Updates the powwow server in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowServerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowServer the powwow server
	 * @return the powwow server that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowServer updatePowwowServer(PowwowServer powwowServer) {
		return powwowServerPersistence.update(powwowServer);
	}

	/**
	 * Returns the powwow meeting local service.
	 *
	 * @return the powwow meeting local service
	 */
	public com.liferay.powwow.service.PowwowMeetingLocalService
		getPowwowMeetingLocalService() {

		return powwowMeetingLocalService;
	}

	/**
	 * Sets the powwow meeting local service.
	 *
	 * @param powwowMeetingLocalService the powwow meeting local service
	 */
	public void setPowwowMeetingLocalService(
		com.liferay.powwow.service.PowwowMeetingLocalService
			powwowMeetingLocalService) {

		this.powwowMeetingLocalService = powwowMeetingLocalService;
	}

	/**
	 * Returns the powwow meeting persistence.
	 *
	 * @return the powwow meeting persistence
	 */
	public PowwowMeetingPersistence getPowwowMeetingPersistence() {
		return powwowMeetingPersistence;
	}

	/**
	 * Sets the powwow meeting persistence.
	 *
	 * @param powwowMeetingPersistence the powwow meeting persistence
	 */
	public void setPowwowMeetingPersistence(
		PowwowMeetingPersistence powwowMeetingPersistence) {

		this.powwowMeetingPersistence = powwowMeetingPersistence;
	}

	/**
	 * Returns the powwow meeting finder.
	 *
	 * @return the powwow meeting finder
	 */
	public PowwowMeetingFinder getPowwowMeetingFinder() {
		return powwowMeetingFinder;
	}

	/**
	 * Sets the powwow meeting finder.
	 *
	 * @param powwowMeetingFinder the powwow meeting finder
	 */
	public void setPowwowMeetingFinder(
		PowwowMeetingFinder powwowMeetingFinder) {

		this.powwowMeetingFinder = powwowMeetingFinder;
	}

	/**
	 * Returns the powwow participant local service.
	 *
	 * @return the powwow participant local service
	 */
	public com.liferay.powwow.service.PowwowParticipantLocalService
		getPowwowParticipantLocalService() {

		return powwowParticipantLocalService;
	}

	/**
	 * Sets the powwow participant local service.
	 *
	 * @param powwowParticipantLocalService the powwow participant local service
	 */
	public void setPowwowParticipantLocalService(
		com.liferay.powwow.service.PowwowParticipantLocalService
			powwowParticipantLocalService) {

		this.powwowParticipantLocalService = powwowParticipantLocalService;
	}

	/**
	 * Returns the powwow participant persistence.
	 *
	 * @return the powwow participant persistence
	 */
	public PowwowParticipantPersistence getPowwowParticipantPersistence() {
		return powwowParticipantPersistence;
	}

	/**
	 * Sets the powwow participant persistence.
	 *
	 * @param powwowParticipantPersistence the powwow participant persistence
	 */
	public void setPowwowParticipantPersistence(
		PowwowParticipantPersistence powwowParticipantPersistence) {

		this.powwowParticipantPersistence = powwowParticipantPersistence;
	}

	/**
	 * Returns the powwow server local service.
	 *
	 * @return the powwow server local service
	 */
	public PowwowServerLocalService getPowwowServerLocalService() {
		return powwowServerLocalService;
	}

	/**
	 * Sets the powwow server local service.
	 *
	 * @param powwowServerLocalService the powwow server local service
	 */
	public void setPowwowServerLocalService(
		PowwowServerLocalService powwowServerLocalService) {

		this.powwowServerLocalService = powwowServerLocalService;
	}

	/**
	 * Returns the powwow server persistence.
	 *
	 * @return the powwow server persistence
	 */
	public PowwowServerPersistence getPowwowServerPersistence() {
		return powwowServerPersistence;
	}

	/**
	 * Sets the powwow server persistence.
	 *
	 * @param powwowServerPersistence the powwow server persistence
	 */
	public void setPowwowServerPersistence(
		PowwowServerPersistence powwowServerPersistence) {

		this.powwowServerPersistence = powwowServerPersistence;
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
		PersistedModelLocalServiceRegistryUtil.register(
			"com.liferay.powwow.model.PowwowServer", powwowServerLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.powwow.model.PowwowServer");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PowwowServerLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PowwowServer.class;
	}

	protected String getModelClassName() {
		return PowwowServer.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = powwowServerPersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.powwow.service.PowwowMeetingLocalService.class
	)
	protected com.liferay.powwow.service.PowwowMeetingLocalService
		powwowMeetingLocalService;

	@BeanReference(type = PowwowMeetingPersistence.class)
	protected PowwowMeetingPersistence powwowMeetingPersistence;

	@BeanReference(type = PowwowMeetingFinder.class)
	protected PowwowMeetingFinder powwowMeetingFinder;

	@BeanReference(
		type = com.liferay.powwow.service.PowwowParticipantLocalService.class
	)
	protected com.liferay.powwow.service.PowwowParticipantLocalService
		powwowParticipantLocalService;

	@BeanReference(type = PowwowParticipantPersistence.class)
	protected PowwowParticipantPersistence powwowParticipantPersistence;

	@BeanReference(type = PowwowServerLocalService.class)
	protected PowwowServerLocalService powwowServerLocalService;

	@BeanReference(type = PowwowServerPersistence.class)
	protected PowwowServerPersistence powwowServerPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

}