/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.application.service.base;

import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.commerce.application.service.CommerceApplicationBrandLocalService;
import com.liferay.commerce.application.service.CommerceApplicationBrandLocalServiceUtil;
import com.liferay.commerce.application.service.persistence.CommerceApplicationBrandPersistence;
import com.liferay.commerce.application.service.persistence.CommerceApplicationModelCProductRelPersistence;
import com.liferay.commerce.application.service.persistence.CommerceApplicationModelPersistence;
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
 * Provides the base implementation for the commerce application brand local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.application.service.impl.CommerceApplicationBrandLocalServiceImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see com.liferay.commerce.application.service.impl.CommerceApplicationBrandLocalServiceImpl
 * @generated
 */
public abstract class CommerceApplicationBrandLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceApplicationBrandLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceApplicationBrandLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceApplicationBrandLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce application brand to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceApplicationBrandLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceApplicationBrand the commerce application brand
	 * @return the commerce application brand that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceApplicationBrand addCommerceApplicationBrand(
		CommerceApplicationBrand commerceApplicationBrand) {

		commerceApplicationBrand.setNew(true);

		return commerceApplicationBrandPersistence.update(
			commerceApplicationBrand);
	}

	/**
	 * Creates a new commerce application brand with the primary key. Does not add the commerce application brand to the database.
	 *
	 * @param commerceApplicationBrandId the primary key for the new commerce application brand
	 * @return the new commerce application brand
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceApplicationBrand createCommerceApplicationBrand(
		long commerceApplicationBrandId) {

		return commerceApplicationBrandPersistence.create(
			commerceApplicationBrandId);
	}

	/**
	 * Deletes the commerce application brand with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceApplicationBrandLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceApplicationBrandId the primary key of the commerce application brand
	 * @return the commerce application brand that was removed
	 * @throws PortalException if a commerce application brand with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceApplicationBrand deleteCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws PortalException {

		return commerceApplicationBrandPersistence.remove(
			commerceApplicationBrandId);
	}

	/**
	 * Deletes the commerce application brand from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceApplicationBrandLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceApplicationBrand the commerce application brand
	 * @return the commerce application brand that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceApplicationBrand deleteCommerceApplicationBrand(
			CommerceApplicationBrand commerceApplicationBrand)
		throws PortalException {

		return commerceApplicationBrandPersistence.remove(
			commerceApplicationBrand);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return commerceApplicationBrandPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CommerceApplicationBrand.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceApplicationBrandPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.application.model.impl.CommerceApplicationBrandModelImpl</code>.
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

		return commerceApplicationBrandPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.application.model.impl.CommerceApplicationBrandModelImpl</code>.
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

		return commerceApplicationBrandPersistence.findWithDynamicQuery(
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
		return commerceApplicationBrandPersistence.countWithDynamicQuery(
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

		return commerceApplicationBrandPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceApplicationBrand fetchCommerceApplicationBrand(
		long commerceApplicationBrandId) {

		return commerceApplicationBrandPersistence.fetchByPrimaryKey(
			commerceApplicationBrandId);
	}

	/**
	 * Returns the commerce application brand with the primary key.
	 *
	 * @param commerceApplicationBrandId the primary key of the commerce application brand
	 * @return the commerce application brand
	 * @throws PortalException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand getCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws PortalException {

		return commerceApplicationBrandPersistence.findByPrimaryKey(
			commerceApplicationBrandId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceApplicationBrandLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceApplicationBrand.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceApplicationBrandId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceApplicationBrandLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommerceApplicationBrand.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceApplicationBrandId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceApplicationBrandLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceApplicationBrand.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceApplicationBrandId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceApplicationBrandPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return commerceApplicationBrandLocalService.
			deleteCommerceApplicationBrand(
				(CommerceApplicationBrand)persistedModel);
	}

	public BasePersistence<CommerceApplicationBrand> getBasePersistence() {
		return commerceApplicationBrandPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceApplicationBrandPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce application brands.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.application.model.impl.CommerceApplicationBrandModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @return the range of commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> getCommerceApplicationBrands(
		int start, int end) {

		return commerceApplicationBrandPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce application brands.
	 *
	 * @return the number of commerce application brands
	 */
	@Override
	public int getCommerceApplicationBrandsCount() {
		return commerceApplicationBrandPersistence.countAll();
	}

	/**
	 * Updates the commerce application brand in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceApplicationBrandLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceApplicationBrand the commerce application brand
	 * @return the commerce application brand that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceApplicationBrand updateCommerceApplicationBrand(
		CommerceApplicationBrand commerceApplicationBrand) {

		return commerceApplicationBrandPersistence.update(
			commerceApplicationBrand);
	}

	/**
	 * Returns the commerce application brand local service.
	 *
	 * @return the commerce application brand local service
	 */
	public CommerceApplicationBrandLocalService
		getCommerceApplicationBrandLocalService() {

		return commerceApplicationBrandLocalService;
	}

	/**
	 * Sets the commerce application brand local service.
	 *
	 * @param commerceApplicationBrandLocalService the commerce application brand local service
	 */
	public void setCommerceApplicationBrandLocalService(
		CommerceApplicationBrandLocalService
			commerceApplicationBrandLocalService) {

		this.commerceApplicationBrandLocalService =
			commerceApplicationBrandLocalService;
	}

	/**
	 * Returns the commerce application brand persistence.
	 *
	 * @return the commerce application brand persistence
	 */
	public CommerceApplicationBrandPersistence
		getCommerceApplicationBrandPersistence() {

		return commerceApplicationBrandPersistence;
	}

	/**
	 * Sets the commerce application brand persistence.
	 *
	 * @param commerceApplicationBrandPersistence the commerce application brand persistence
	 */
	public void setCommerceApplicationBrandPersistence(
		CommerceApplicationBrandPersistence
			commerceApplicationBrandPersistence) {

		this.commerceApplicationBrandPersistence =
			commerceApplicationBrandPersistence;
	}

	/**
	 * Returns the commerce application model local service.
	 *
	 * @return the commerce application model local service
	 */
	public com.liferay.commerce.application.service.
		CommerceApplicationModelLocalService
			getCommerceApplicationModelLocalService() {

		return commerceApplicationModelLocalService;
	}

	/**
	 * Sets the commerce application model local service.
	 *
	 * @param commerceApplicationModelLocalService the commerce application model local service
	 */
	public void setCommerceApplicationModelLocalService(
		com.liferay.commerce.application.service.
			CommerceApplicationModelLocalService
				commerceApplicationModelLocalService) {

		this.commerceApplicationModelLocalService =
			commerceApplicationModelLocalService;
	}

	/**
	 * Returns the commerce application model persistence.
	 *
	 * @return the commerce application model persistence
	 */
	public CommerceApplicationModelPersistence
		getCommerceApplicationModelPersistence() {

		return commerceApplicationModelPersistence;
	}

	/**
	 * Sets the commerce application model persistence.
	 *
	 * @param commerceApplicationModelPersistence the commerce application model persistence
	 */
	public void setCommerceApplicationModelPersistence(
		CommerceApplicationModelPersistence
			commerceApplicationModelPersistence) {

		this.commerceApplicationModelPersistence =
			commerceApplicationModelPersistence;
	}

	/**
	 * Returns the commerce application model c product rel local service.
	 *
	 * @return the commerce application model c product rel local service
	 */
	public com.liferay.commerce.application.service.
		CommerceApplicationModelCProductRelLocalService
			getCommerceApplicationModelCProductRelLocalService() {

		return commerceApplicationModelCProductRelLocalService;
	}

	/**
	 * Sets the commerce application model c product rel local service.
	 *
	 * @param commerceApplicationModelCProductRelLocalService the commerce application model c product rel local service
	 */
	public void setCommerceApplicationModelCProductRelLocalService(
		com.liferay.commerce.application.service.
			CommerceApplicationModelCProductRelLocalService
				commerceApplicationModelCProductRelLocalService) {

		this.commerceApplicationModelCProductRelLocalService =
			commerceApplicationModelCProductRelLocalService;
	}

	/**
	 * Returns the commerce application model c product rel persistence.
	 *
	 * @return the commerce application model c product rel persistence
	 */
	public CommerceApplicationModelCProductRelPersistence
		getCommerceApplicationModelCProductRelPersistence() {

		return commerceApplicationModelCProductRelPersistence;
	}

	/**
	 * Sets the commerce application model c product rel persistence.
	 *
	 * @param commerceApplicationModelCProductRelPersistence the commerce application model c product rel persistence
	 */
	public void setCommerceApplicationModelCProductRelPersistence(
		CommerceApplicationModelCProductRelPersistence
			commerceApplicationModelCProductRelPersistence) {

		this.commerceApplicationModelCProductRelPersistence =
			commerceApplicationModelCProductRelPersistence;
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
			"com.liferay.commerce.application.model.CommerceApplicationBrand",
			commerceApplicationBrandLocalService);

		_setLocalServiceUtilService(commerceApplicationBrandLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.application.model.CommerceApplicationBrand");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceApplicationBrandLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceApplicationBrand.class;
	}

	protected String getModelClassName() {
		return CommerceApplicationBrand.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceApplicationBrandPersistence.getDataSource();

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
		CommerceApplicationBrandLocalService
			commerceApplicationBrandLocalService) {

		try {
			Field field =
				CommerceApplicationBrandLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceApplicationBrandLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = CommerceApplicationBrandLocalService.class)
	protected CommerceApplicationBrandLocalService
		commerceApplicationBrandLocalService;

	@BeanReference(type = CommerceApplicationBrandPersistence.class)
	protected CommerceApplicationBrandPersistence
		commerceApplicationBrandPersistence;

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationModelLocalService.class
	)
	protected com.liferay.commerce.application.service.
		CommerceApplicationModelLocalService
			commerceApplicationModelLocalService;

	@BeanReference(type = CommerceApplicationModelPersistence.class)
	protected CommerceApplicationModelPersistence
		commerceApplicationModelPersistence;

	@BeanReference(
		type = com.liferay.commerce.application.service.CommerceApplicationModelCProductRelLocalService.class
	)
	protected com.liferay.commerce.application.service.
		CommerceApplicationModelCProductRelLocalService
			commerceApplicationModelCProductRelLocalService;

	@BeanReference(type = CommerceApplicationModelCProductRelPersistence.class)
	protected CommerceApplicationModelCProductRelPersistence
		commerceApplicationModelCProductRelPersistence;

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