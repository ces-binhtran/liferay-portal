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

package com.liferay.commerce.wish.list.service.base;

import com.liferay.commerce.wish.list.model.CommerceWishListItem;
import com.liferay.commerce.wish.list.service.CommerceWishListItemLocalService;
import com.liferay.commerce.wish.list.service.CommerceWishListItemLocalServiceUtil;
import com.liferay.commerce.wish.list.service.persistence.CommerceWishListItemPersistence;
import com.liferay.commerce.wish.list.service.persistence.CommerceWishListPersistence;
import com.liferay.petra.sql.dsl.query.DSLQuery;
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
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce wish list item local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.wish.list.service.impl.CommerceWishListItemLocalServiceImpl}.
 * </p>
 *
 * @author Andrea Di Giorgi
 * @see com.liferay.commerce.wish.list.service.impl.CommerceWishListItemLocalServiceImpl
 * @generated
 */
public abstract class CommerceWishListItemLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceWishListItemLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceWishListItemLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceWishListItemLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce wish list item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceWishListItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceWishListItem the commerce wish list item
	 * @return the commerce wish list item that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceWishListItem addCommerceWishListItem(
		CommerceWishListItem commerceWishListItem) {

		commerceWishListItem.setNew(true);

		return commerceWishListItemPersistence.update(commerceWishListItem);
	}

	/**
	 * Creates a new commerce wish list item with the primary key. Does not add the commerce wish list item to the database.
	 *
	 * @param commerceWishListItemId the primary key for the new commerce wish list item
	 * @return the new commerce wish list item
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceWishListItem createCommerceWishListItem(
		long commerceWishListItemId) {

		return commerceWishListItemPersistence.create(commerceWishListItemId);
	}

	/**
	 * Deletes the commerce wish list item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceWishListItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceWishListItemId the primary key of the commerce wish list item
	 * @return the commerce wish list item that was removed
	 * @throws PortalException if a commerce wish list item with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceWishListItem deleteCommerceWishListItem(
			long commerceWishListItemId)
		throws PortalException {

		return commerceWishListItemPersistence.remove(commerceWishListItemId);
	}

	/**
	 * Deletes the commerce wish list item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceWishListItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceWishListItem the commerce wish list item
	 * @return the commerce wish list item that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceWishListItem deleteCommerceWishListItem(
		CommerceWishListItem commerceWishListItem) {

		return commerceWishListItemPersistence.remove(commerceWishListItem);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceWishListItemPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CommerceWishListItem.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceWishListItemPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl</code>.
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

		return commerceWishListItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl</code>.
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

		return commerceWishListItemPersistence.findWithDynamicQuery(
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
		return commerceWishListItemPersistence.countWithDynamicQuery(
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

		return commerceWishListItemPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceWishListItem fetchCommerceWishListItem(
		long commerceWishListItemId) {

		return commerceWishListItemPersistence.fetchByPrimaryKey(
			commerceWishListItemId);
	}

	/**
	 * Returns the commerce wish list item with the primary key.
	 *
	 * @param commerceWishListItemId the primary key of the commerce wish list item
	 * @return the commerce wish list item
	 * @throws PortalException if a commerce wish list item with the primary key could not be found
	 */
	@Override
	public CommerceWishListItem getCommerceWishListItem(
			long commerceWishListItemId)
		throws PortalException {

		return commerceWishListItemPersistence.findByPrimaryKey(
			commerceWishListItemId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceWishListItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceWishListItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceWishListItemId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceWishListItemLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommerceWishListItem.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceWishListItemId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceWishListItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceWishListItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceWishListItemId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceWishListItemPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return commerceWishListItemLocalService.deleteCommerceWishListItem(
			(CommerceWishListItem)persistedModel);
	}

	public BasePersistence<CommerceWishListItem> getBasePersistence() {
		return commerceWishListItemPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceWishListItemPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce wish list items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.wish.list.model.impl.CommerceWishListItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce wish list items
	 * @param end the upper bound of the range of commerce wish list items (not inclusive)
	 * @return the range of commerce wish list items
	 */
	@Override
	public List<CommerceWishListItem> getCommerceWishListItems(
		int start, int end) {

		return commerceWishListItemPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce wish list items.
	 *
	 * @return the number of commerce wish list items
	 */
	@Override
	public int getCommerceWishListItemsCount() {
		return commerceWishListItemPersistence.countAll();
	}

	/**
	 * Updates the commerce wish list item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceWishListItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceWishListItem the commerce wish list item
	 * @return the commerce wish list item that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceWishListItem updateCommerceWishListItem(
		CommerceWishListItem commerceWishListItem) {

		return commerceWishListItemPersistence.update(commerceWishListItem);
	}

	/**
	 * Returns the commerce wish list local service.
	 *
	 * @return the commerce wish list local service
	 */
	public com.liferay.commerce.wish.list.service.CommerceWishListLocalService
		getCommerceWishListLocalService() {

		return commerceWishListLocalService;
	}

	/**
	 * Sets the commerce wish list local service.
	 *
	 * @param commerceWishListLocalService the commerce wish list local service
	 */
	public void setCommerceWishListLocalService(
		com.liferay.commerce.wish.list.service.CommerceWishListLocalService
			commerceWishListLocalService) {

		this.commerceWishListLocalService = commerceWishListLocalService;
	}

	/**
	 * Returns the commerce wish list persistence.
	 *
	 * @return the commerce wish list persistence
	 */
	public CommerceWishListPersistence getCommerceWishListPersistence() {
		return commerceWishListPersistence;
	}

	/**
	 * Sets the commerce wish list persistence.
	 *
	 * @param commerceWishListPersistence the commerce wish list persistence
	 */
	public void setCommerceWishListPersistence(
		CommerceWishListPersistence commerceWishListPersistence) {

		this.commerceWishListPersistence = commerceWishListPersistence;
	}

	/**
	 * Returns the commerce wish list item local service.
	 *
	 * @return the commerce wish list item local service
	 */
	public CommerceWishListItemLocalService
		getCommerceWishListItemLocalService() {

		return commerceWishListItemLocalService;
	}

	/**
	 * Sets the commerce wish list item local service.
	 *
	 * @param commerceWishListItemLocalService the commerce wish list item local service
	 */
	public void setCommerceWishListItemLocalService(
		CommerceWishListItemLocalService commerceWishListItemLocalService) {

		this.commerceWishListItemLocalService =
			commerceWishListItemLocalService;
	}

	/**
	 * Returns the commerce wish list item persistence.
	 *
	 * @return the commerce wish list item persistence
	 */
	public CommerceWishListItemPersistence
		getCommerceWishListItemPersistence() {

		return commerceWishListItemPersistence;
	}

	/**
	 * Sets the commerce wish list item persistence.
	 *
	 * @param commerceWishListItemPersistence the commerce wish list item persistence
	 */
	public void setCommerceWishListItemPersistence(
		CommerceWishListItemPersistence commerceWishListItemPersistence) {

		this.commerceWishListItemPersistence = commerceWishListItemPersistence;
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
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.wish.list.model.CommerceWishListItem",
			commerceWishListItemLocalService);

		_setLocalServiceUtilService(commerceWishListItemLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.wish.list.model.CommerceWishListItem");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceWishListItemLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceWishListItem.class;
	}

	protected String getModelClassName() {
		return CommerceWishListItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceWishListItemPersistence.getDataSource();

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
		CommerceWishListItemLocalService commerceWishListItemLocalService) {

		try {
			Field field =
				CommerceWishListItemLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceWishListItemLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.commerce.wish.list.service.CommerceWishListLocalService.class
	)
	protected
		com.liferay.commerce.wish.list.service.CommerceWishListLocalService
			commerceWishListLocalService;

	@BeanReference(type = CommerceWishListPersistence.class)
	protected CommerceWishListPersistence commerceWishListPersistence;

	@BeanReference(type = CommerceWishListItemLocalService.class)
	protected CommerceWishListItemLocalService commerceWishListItemLocalService;

	@BeanReference(type = CommerceWishListItemPersistence.class)
	protected CommerceWishListItemPersistence commerceWishListItemPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}