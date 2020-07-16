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

package com.liferay.portlet.social.service.base;

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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.service.SocialRequestLocalService;
import com.liferay.social.kernel.service.persistence.SocialRequestPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the social request local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.social.service.impl.SocialRequestLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.social.service.impl.SocialRequestLocalServiceImpl
 * @generated
 */
public abstract class SocialRequestLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, SocialRequestLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SocialRequestLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.social.kernel.service.SocialRequestLocalServiceUtil</code>.
	 */

	/**
	 * Adds the social request to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param socialRequest the social request
	 * @return the social request that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SocialRequest addSocialRequest(SocialRequest socialRequest) {
		socialRequest.setNew(true);

		return socialRequestPersistence.update(socialRequest);
	}

	/**
	 * Creates a new social request with the primary key. Does not add the social request to the database.
	 *
	 * @param requestId the primary key for the new social request
	 * @return the new social request
	 */
	@Override
	@Transactional(enabled = false)
	public SocialRequest createSocialRequest(long requestId) {
		return socialRequestPersistence.create(requestId);
	}

	/**
	 * Deletes the social request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param requestId the primary key of the social request
	 * @return the social request that was removed
	 * @throws PortalException if a social request with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SocialRequest deleteSocialRequest(long requestId)
		throws PortalException {

		return socialRequestPersistence.remove(requestId);
	}

	/**
	 * Deletes the social request from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param socialRequest the social request
	 * @return the social request that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SocialRequest deleteSocialRequest(SocialRequest socialRequest) {
		return socialRequestPersistence.remove(socialRequest);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			SocialRequest.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return socialRequestPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.social.model.impl.SocialRequestModelImpl</code>.
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

		return socialRequestPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.social.model.impl.SocialRequestModelImpl</code>.
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

		return socialRequestPersistence.findWithDynamicQuery(
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
		return socialRequestPersistence.countWithDynamicQuery(dynamicQuery);
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

		return socialRequestPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SocialRequest fetchSocialRequest(long requestId) {
		return socialRequestPersistence.fetchByPrimaryKey(requestId);
	}

	/**
	 * Returns the social request matching the UUID and group.
	 *
	 * @param uuid the social request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching social request, or <code>null</code> if a matching social request could not be found
	 */
	@Override
	public SocialRequest fetchSocialRequestByUuidAndGroupId(
		String uuid, long groupId) {

		return socialRequestPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the social request with the primary key.
	 *
	 * @param requestId the primary key of the social request
	 * @return the social request
	 * @throws PortalException if a social request with the primary key could not be found
	 */
	@Override
	public SocialRequest getSocialRequest(long requestId)
		throws PortalException {

		return socialRequestPersistence.findByPrimaryKey(requestId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(socialRequestLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SocialRequest.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("requestId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			socialRequestLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SocialRequest.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("requestId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(socialRequestLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SocialRequest.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("requestId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return socialRequestLocalService.deleteSocialRequest(
			(SocialRequest)persistedModel);
	}

	public BasePersistence<SocialRequest> getBasePersistence() {
		return socialRequestPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return socialRequestPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the social requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the social requests
	 * @param companyId the primary key of the company
	 * @return the matching social requests, or an empty list if no matches were found
	 */
	@Override
	public List<SocialRequest> getSocialRequestsByUuidAndCompanyId(
		String uuid, long companyId) {

		return socialRequestPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of social requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the social requests
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching social requests, or an empty list if no matches were found
	 */
	@Override
	public List<SocialRequest> getSocialRequestsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SocialRequest> orderByComparator) {

		return socialRequestPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the social request matching the UUID and group.
	 *
	 * @param uuid the social request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching social request
	 * @throws PortalException if a matching social request could not be found
	 */
	@Override
	public SocialRequest getSocialRequestByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return socialRequestPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the social requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.social.model.impl.SocialRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of social requests
	 * @param end the upper bound of the range of social requests (not inclusive)
	 * @return the range of social requests
	 */
	@Override
	public List<SocialRequest> getSocialRequests(int start, int end) {
		return socialRequestPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of social requests.
	 *
	 * @return the number of social requests
	 */
	@Override
	public int getSocialRequestsCount() {
		return socialRequestPersistence.countAll();
	}

	/**
	 * Updates the social request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SocialRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param socialRequest the social request
	 * @return the social request that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SocialRequest updateSocialRequest(SocialRequest socialRequest) {
		return socialRequestPersistence.update(socialRequest);
	}

	/**
	 * Returns the social request local service.
	 *
	 * @return the social request local service
	 */
	public SocialRequestLocalService getSocialRequestLocalService() {
		return socialRequestLocalService;
	}

	/**
	 * Sets the social request local service.
	 *
	 * @param socialRequestLocalService the social request local service
	 */
	public void setSocialRequestLocalService(
		SocialRequestLocalService socialRequestLocalService) {

		this.socialRequestLocalService = socialRequestLocalService;
	}

	/**
	 * Returns the social request persistence.
	 *
	 * @return the social request persistence
	 */
	public SocialRequestPersistence getSocialRequestPersistence() {
		return socialRequestPersistence;
	}

	/**
	 * Sets the social request persistence.
	 *
	 * @param socialRequestPersistence the social request persistence
	 */
	public void setSocialRequestPersistence(
		SocialRequestPersistence socialRequestPersistence) {

		this.socialRequestPersistence = socialRequestPersistence;
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

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the social request interpreter local service.
	 *
	 * @return the social request interpreter local service
	 */
	public
		com.liferay.social.kernel.service.SocialRequestInterpreterLocalService
			getSocialRequestInterpreterLocalService() {

		return socialRequestInterpreterLocalService;
	}

	/**
	 * Sets the social request interpreter local service.
	 *
	 * @param socialRequestInterpreterLocalService the social request interpreter local service
	 */
	public void setSocialRequestInterpreterLocalService(
		com.liferay.social.kernel.service.SocialRequestInterpreterLocalService
			socialRequestInterpreterLocalService) {

		this.socialRequestInterpreterLocalService =
			socialRequestInterpreterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.social.kernel.model.SocialRequest",
			socialRequestLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.social.kernel.model.SocialRequest");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SocialRequestLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SocialRequest.class;
	}

	protected String getModelClassName() {
		return SocialRequest.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = socialRequestPersistence.getDataSource();

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

	@BeanReference(type = SocialRequestLocalService.class)
	protected SocialRequestLocalService socialRequestLocalService;

	@BeanReference(type = SocialRequestPersistence.class)
	protected SocialRequestPersistence socialRequestPersistence;

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
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.social.kernel.service.SocialRequestInterpreterLocalService.class
	)
	protected
		com.liferay.social.kernel.service.SocialRequestInterpreterLocalService
			socialRequestInterpreterLocalService;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}