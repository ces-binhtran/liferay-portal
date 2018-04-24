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

package com.liferay.oauth.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.service.OAuthApplicationLocalService;
import com.liferay.oauth.service.persistence.OAuthApplicationPersistence;
import com.liferay.oauth.service.persistence.OAuthUserPersistence;

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
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.ImagePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the o auth application local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.oauth.service.impl.OAuthApplicationLocalServiceImpl}.
 * </p>
 *
 * @author Ivica Cardic
 * @see com.liferay.oauth.service.impl.OAuthApplicationLocalServiceImpl
 * @see com.liferay.oauth.service.OAuthApplicationLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class OAuthApplicationLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements OAuthApplicationLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.oauth.service.OAuthApplicationLocalServiceUtil} to access the o auth application local service.
	 */

	/**
	 * Adds the o auth application to the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthApplication the o auth application
	 * @return the o auth application that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OAuthApplication addOAuthApplication(
		OAuthApplication oAuthApplication) {
		oAuthApplication.setNew(true);

		return oAuthApplicationPersistence.update(oAuthApplication);
	}

	/**
	 * Creates a new o auth application with the primary key. Does not add the o auth application to the database.
	 *
	 * @param oAuthApplicationId the primary key for the new o auth application
	 * @return the new o auth application
	 */
	@Override
	@Transactional(enabled = false)
	public OAuthApplication createOAuthApplication(long oAuthApplicationId) {
		return oAuthApplicationPersistence.create(oAuthApplicationId);
	}

	/**
	 * Deletes the o auth application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthApplicationId the primary key of the o auth application
	 * @return the o auth application that was removed
	 * @throws PortalException if a o auth application with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OAuthApplication deleteOAuthApplication(long oAuthApplicationId)
		throws PortalException {
		return oAuthApplicationPersistence.remove(oAuthApplicationId);
	}

	/**
	 * Deletes the o auth application from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthApplication the o auth application
	 * @return the o auth application that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OAuthApplication deleteOAuthApplication(
		OAuthApplication oAuthApplication) throws PortalException {
		return oAuthApplicationPersistence.remove(oAuthApplication);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(OAuthApplication.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return oAuthApplicationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return oAuthApplicationPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return oAuthApplicationPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return oAuthApplicationPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return oAuthApplicationPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public OAuthApplication fetchOAuthApplication(long oAuthApplicationId) {
		return oAuthApplicationPersistence.fetchByPrimaryKey(oAuthApplicationId);
	}

	/**
	 * Returns the o auth application with the primary key.
	 *
	 * @param oAuthApplicationId the primary key of the o auth application
	 * @return the o auth application
	 * @throws PortalException if a o auth application with the primary key could not be found
	 */
	@Override
	public OAuthApplication getOAuthApplication(long oAuthApplicationId)
		throws PortalException {
		return oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(oAuthApplicationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(OAuthApplication.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("oAuthApplicationId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(oAuthApplicationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(OAuthApplication.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"oAuthApplicationId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(oAuthApplicationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(OAuthApplication.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("oAuthApplicationId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return oAuthApplicationLocalService.deleteOAuthApplication((OAuthApplication)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return oAuthApplicationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the o auth applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth applications
	 * @param end the upper bound of the range of o auth applications (not inclusive)
	 * @return the range of o auth applications
	 */
	@Override
	public List<OAuthApplication> getOAuthApplications(int start, int end) {
		return oAuthApplicationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of o auth applications.
	 *
	 * @return the number of o auth applications
	 */
	@Override
	public int getOAuthApplicationsCount() {
		return oAuthApplicationPersistence.countAll();
	}

	/**
	 * Updates the o auth application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthApplication the o auth application
	 * @return the o auth application that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OAuthApplication updateOAuthApplication(
		OAuthApplication oAuthApplication) {
		return oAuthApplicationPersistence.update(oAuthApplication);
	}

	/**
	 * Returns the o auth application local service.
	 *
	 * @return the o auth application local service
	 */
	public OAuthApplicationLocalService getOAuthApplicationLocalService() {
		return oAuthApplicationLocalService;
	}

	/**
	 * Sets the o auth application local service.
	 *
	 * @param oAuthApplicationLocalService the o auth application local service
	 */
	public void setOAuthApplicationLocalService(
		OAuthApplicationLocalService oAuthApplicationLocalService) {
		this.oAuthApplicationLocalService = oAuthApplicationLocalService;
	}

	/**
	 * Returns the o auth application persistence.
	 *
	 * @return the o auth application persistence
	 */
	public OAuthApplicationPersistence getOAuthApplicationPersistence() {
		return oAuthApplicationPersistence;
	}

	/**
	 * Sets the o auth application persistence.
	 *
	 * @param oAuthApplicationPersistence the o auth application persistence
	 */
	public void setOAuthApplicationPersistence(
		OAuthApplicationPersistence oAuthApplicationPersistence) {
		this.oAuthApplicationPersistence = oAuthApplicationPersistence;
	}

	/**
	 * Returns the o auth user local service.
	 *
	 * @return the o auth user local service
	 */
	public com.liferay.oauth.service.OAuthUserLocalService getOAuthUserLocalService() {
		return oAuthUserLocalService;
	}

	/**
	 * Sets the o auth user local service.
	 *
	 * @param oAuthUserLocalService the o auth user local service
	 */
	public void setOAuthUserLocalService(
		com.liferay.oauth.service.OAuthUserLocalService oAuthUserLocalService) {
		this.oAuthUserLocalService = oAuthUserLocalService;
	}

	/**
	 * Returns the o auth user persistence.
	 *
	 * @return the o auth user persistence
	 */
	public OAuthUserPersistence getOAuthUserPersistence() {
		return oAuthUserPersistence;
	}

	/**
	 * Sets the o auth user persistence.
	 *
	 * @param oAuthUserPersistence the o auth user persistence
	 */
	public void setOAuthUserPersistence(
		OAuthUserPersistence oAuthUserPersistence) {
		this.oAuthUserPersistence = oAuthUserPersistence;
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
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.kernel.service.ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.kernel.service.ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
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
		persistedModelLocalServiceRegistry.register("com.liferay.oauth.model.OAuthApplication",
			oAuthApplicationLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.oauth.model.OAuthApplication");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return OAuthApplicationLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return OAuthApplication.class;
	}

	protected String getModelClassName() {
		return OAuthApplication.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = oAuthApplicationPersistence.getDataSource();

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

	@BeanReference(type = OAuthApplicationLocalService.class)
	protected OAuthApplicationLocalService oAuthApplicationLocalService;
	@BeanReference(type = OAuthApplicationPersistence.class)
	protected OAuthApplicationPersistence oAuthApplicationPersistence;
	@BeanReference(type = com.liferay.oauth.service.OAuthUserLocalService.class)
	protected com.liferay.oauth.service.OAuthUserLocalService oAuthUserLocalService;
	@BeanReference(type = OAuthUserPersistence.class)
	protected OAuthUserPersistence oAuthUserPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ImageLocalService.class)
	protected com.liferay.portal.kernel.service.ImageLocalService imageLocalService;
	@ServiceReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}