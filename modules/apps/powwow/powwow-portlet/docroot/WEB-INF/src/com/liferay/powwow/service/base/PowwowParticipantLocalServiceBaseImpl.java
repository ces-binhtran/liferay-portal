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
import com.liferay.powwow.model.PowwowParticipant;
import com.liferay.powwow.service.PowwowParticipantLocalService;
import com.liferay.powwow.service.persistence.PowwowMeetingFinder;
import com.liferay.powwow.service.persistence.PowwowMeetingPersistence;
import com.liferay.powwow.service.persistence.PowwowParticipantPersistence;
import com.liferay.powwow.service.persistence.PowwowServerPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the powwow participant local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.powwow.service.impl.PowwowParticipantLocalServiceImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see com.liferay.powwow.service.impl.PowwowParticipantLocalServiceImpl
 * @generated
 */
public abstract class PowwowParticipantLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, PowwowParticipantLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PowwowParticipantLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.powwow.service.PowwowParticipantLocalServiceUtil</code>.
	 */

	/**
	 * Adds the powwow participant to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowParticipant the powwow participant
	 * @return the powwow participant that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowParticipant addPowwowParticipant(
		PowwowParticipant powwowParticipant) {

		powwowParticipant.setNew(true);

		return powwowParticipantPersistence.update(powwowParticipant);
	}

	/**
	 * Creates a new powwow participant with the primary key. Does not add the powwow participant to the database.
	 *
	 * @param powwowParticipantId the primary key for the new powwow participant
	 * @return the new powwow participant
	 */
	@Override
	@Transactional(enabled = false)
	public PowwowParticipant createPowwowParticipant(long powwowParticipantId) {
		return powwowParticipantPersistence.create(powwowParticipantId);
	}

	/**
	 * Deletes the powwow participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowParticipantId the primary key of the powwow participant
	 * @return the powwow participant that was removed
	 * @throws PortalException if a powwow participant with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PowwowParticipant deletePowwowParticipant(long powwowParticipantId)
		throws PortalException {

		return powwowParticipantPersistence.remove(powwowParticipantId);
	}

	/**
	 * Deletes the powwow participant from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowParticipant the powwow participant
	 * @return the powwow participant that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PowwowParticipant deletePowwowParticipant(
		PowwowParticipant powwowParticipant) {

		return powwowParticipantPersistence.remove(powwowParticipant);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PowwowParticipant.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return powwowParticipantPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code>.
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

		return powwowParticipantPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code>.
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

		return powwowParticipantPersistence.findWithDynamicQuery(
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
		return powwowParticipantPersistence.countWithDynamicQuery(dynamicQuery);
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

		return powwowParticipantPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PowwowParticipant fetchPowwowParticipant(long powwowParticipantId) {
		return powwowParticipantPersistence.fetchByPrimaryKey(
			powwowParticipantId);
	}

	/**
	 * Returns the powwow participant with the primary key.
	 *
	 * @param powwowParticipantId the primary key of the powwow participant
	 * @return the powwow participant
	 * @throws PortalException if a powwow participant with the primary key could not be found
	 */
	@Override
	public PowwowParticipant getPowwowParticipant(long powwowParticipantId)
		throws PortalException {

		return powwowParticipantPersistence.findByPrimaryKey(
			powwowParticipantId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			powwowParticipantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PowwowParticipant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("powwowParticipantId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			powwowParticipantLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PowwowParticipant.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"powwowParticipantId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			powwowParticipantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PowwowParticipant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("powwowParticipantId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return powwowParticipantLocalService.deletePowwowParticipant(
			(PowwowParticipant)persistedModel);
	}

	public BasePersistence<PowwowParticipant> getBasePersistence() {
		return powwowParticipantPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return powwowParticipantPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the powwow participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.powwow.model.impl.PowwowParticipantModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow participants
	 * @param end the upper bound of the range of powwow participants (not inclusive)
	 * @return the range of powwow participants
	 */
	@Override
	public List<PowwowParticipant> getPowwowParticipants(int start, int end) {
		return powwowParticipantPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of powwow participants.
	 *
	 * @return the number of powwow participants
	 */
	@Override
	public int getPowwowParticipantsCount() {
		return powwowParticipantPersistence.countAll();
	}

	/**
	 * Updates the powwow participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PowwowParticipantLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param powwowParticipant the powwow participant
	 * @return the powwow participant that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PowwowParticipant updatePowwowParticipant(
		PowwowParticipant powwowParticipant) {

		return powwowParticipantPersistence.update(powwowParticipant);
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
	public PowwowParticipantLocalService getPowwowParticipantLocalService() {
		return powwowParticipantLocalService;
	}

	/**
	 * Sets the powwow participant local service.
	 *
	 * @param powwowParticipantLocalService the powwow participant local service
	 */
	public void setPowwowParticipantLocalService(
		PowwowParticipantLocalService powwowParticipantLocalService) {

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
	public com.liferay.powwow.service.PowwowServerLocalService
		getPowwowServerLocalService() {

		return powwowServerLocalService;
	}

	/**
	 * Sets the powwow server local service.
	 *
	 * @param powwowServerLocalService the powwow server local service
	 */
	public void setPowwowServerLocalService(
		com.liferay.powwow.service.PowwowServerLocalService
			powwowServerLocalService) {

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
			"com.liferay.powwow.model.PowwowParticipant",
			powwowParticipantLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.powwow.model.PowwowParticipant");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PowwowParticipantLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PowwowParticipant.class;
	}

	protected String getModelClassName() {
		return PowwowParticipant.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				powwowParticipantPersistence.getDataSource();

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

	@BeanReference(type = PowwowParticipantLocalService.class)
	protected PowwowParticipantLocalService powwowParticipantLocalService;

	@BeanReference(type = PowwowParticipantPersistence.class)
	protected PowwowParticipantPersistence powwowParticipantPersistence;

	@BeanReference(
		type = com.liferay.powwow.service.PowwowServerLocalService.class
	)
	protected com.liferay.powwow.service.PowwowServerLocalService
		powwowServerLocalService;

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