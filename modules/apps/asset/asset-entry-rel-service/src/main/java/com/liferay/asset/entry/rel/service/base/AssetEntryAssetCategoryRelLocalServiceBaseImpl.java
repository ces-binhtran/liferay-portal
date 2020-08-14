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

package com.liferay.asset.entry.rel.service.base;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryAssetCategoryRelPersistence;
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

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the asset entry asset category rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.asset.entry.rel.service.impl.AssetEntryAssetCategoryRelLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.entry.rel.service.impl.AssetEntryAssetCategoryRelLocalServiceImpl
 * @generated
 */
public abstract class AssetEntryAssetCategoryRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, AssetEntryAssetCategoryRelLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetEntryAssetCategoryRelLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil</code>.
	 */

	/**
	 * Adds the asset entry asset category rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryAssetCategoryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryAssetCategoryRel the asset entry asset category rel
	 * @return the asset entry asset category rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetEntryAssetCategoryRel addAssetEntryAssetCategoryRel(
		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel) {

		assetEntryAssetCategoryRel.setNew(true);

		return assetEntryAssetCategoryRelPersistence.update(
			assetEntryAssetCategoryRel);
	}

	/**
	 * Creates a new asset entry asset category rel with the primary key. Does not add the asset entry asset category rel to the database.
	 *
	 * @param assetEntryAssetCategoryRelId the primary key for the new asset entry asset category rel
	 * @return the new asset entry asset category rel
	 */
	@Override
	@Transactional(enabled = false)
	public AssetEntryAssetCategoryRel createAssetEntryAssetCategoryRel(
		long assetEntryAssetCategoryRelId) {

		return assetEntryAssetCategoryRelPersistence.create(
			assetEntryAssetCategoryRelId);
	}

	/**
	 * Deletes the asset entry asset category rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryAssetCategoryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryAssetCategoryRelId the primary key of the asset entry asset category rel
	 * @return the asset entry asset category rel that was removed
	 * @throws PortalException if a asset entry asset category rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetEntryAssetCategoryRel deleteAssetEntryAssetCategoryRel(
			long assetEntryAssetCategoryRelId)
		throws PortalException {

		return assetEntryAssetCategoryRelPersistence.remove(
			assetEntryAssetCategoryRelId);
	}

	/**
	 * Deletes the asset entry asset category rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryAssetCategoryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryAssetCategoryRel the asset entry asset category rel
	 * @return the asset entry asset category rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetEntryAssetCategoryRel deleteAssetEntryAssetCategoryRel(
		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel) {

		return assetEntryAssetCategoryRelPersistence.remove(
			assetEntryAssetCategoryRel);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return assetEntryAssetCategoryRelPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AssetEntryAssetCategoryRel.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return assetEntryAssetCategoryRelPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelModelImpl</code>.
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

		return assetEntryAssetCategoryRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelModelImpl</code>.
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

		return assetEntryAssetCategoryRelPersistence.findWithDynamicQuery(
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
		return assetEntryAssetCategoryRelPersistence.countWithDynamicQuery(
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

		return assetEntryAssetCategoryRelPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AssetEntryAssetCategoryRel fetchAssetEntryAssetCategoryRel(
		long assetEntryAssetCategoryRelId) {

		return assetEntryAssetCategoryRelPersistence.fetchByPrimaryKey(
			assetEntryAssetCategoryRelId);
	}

	/**
	 * Returns the asset entry asset category rel with the primary key.
	 *
	 * @param assetEntryAssetCategoryRelId the primary key of the asset entry asset category rel
	 * @return the asset entry asset category rel
	 * @throws PortalException if a asset entry asset category rel with the primary key could not be found
	 */
	@Override
	public AssetEntryAssetCategoryRel getAssetEntryAssetCategoryRel(
			long assetEntryAssetCategoryRelId)
		throws PortalException {

		return assetEntryAssetCategoryRelPersistence.findByPrimaryKey(
			assetEntryAssetCategoryRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			assetEntryAssetCategoryRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetEntryAssetCategoryRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetEntryAssetCategoryRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			assetEntryAssetCategoryRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			AssetEntryAssetCategoryRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetEntryAssetCategoryRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			assetEntryAssetCategoryRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetEntryAssetCategoryRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetEntryAssetCategoryRelId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return assetEntryAssetCategoryRelPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return assetEntryAssetCategoryRelLocalService.
			deleteAssetEntryAssetCategoryRel(
				(AssetEntryAssetCategoryRel)persistedModel);
	}

	public BasePersistence<AssetEntryAssetCategoryRel> getBasePersistence() {
		return assetEntryAssetCategoryRelPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return assetEntryAssetCategoryRelPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the asset entry asset category rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry asset category rels
	 * @param end the upper bound of the range of asset entry asset category rels (not inclusive)
	 * @return the range of asset entry asset category rels
	 */
	@Override
	public List<AssetEntryAssetCategoryRel> getAssetEntryAssetCategoryRels(
		int start, int end) {

		return assetEntryAssetCategoryRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of asset entry asset category rels.
	 *
	 * @return the number of asset entry asset category rels
	 */
	@Override
	public int getAssetEntryAssetCategoryRelsCount() {
		return assetEntryAssetCategoryRelPersistence.countAll();
	}

	/**
	 * Updates the asset entry asset category rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetEntryAssetCategoryRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetEntryAssetCategoryRel the asset entry asset category rel
	 * @return the asset entry asset category rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetEntryAssetCategoryRel updateAssetEntryAssetCategoryRel(
		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel) {

		return assetEntryAssetCategoryRelPersistence.update(
			assetEntryAssetCategoryRel);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AssetEntryAssetCategoryRelLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		assetEntryAssetCategoryRelLocalService =
			(AssetEntryAssetCategoryRelLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetEntryAssetCategoryRelLocalService.class.getName();
	}

	@Override
	public CTPersistence<AssetEntryAssetCategoryRel> getCTPersistence() {
		return assetEntryAssetCategoryRelPersistence;
	}

	@Override
	public Class<AssetEntryAssetCategoryRel> getModelClass() {
		return AssetEntryAssetCategoryRel.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<AssetEntryAssetCategoryRel>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(
			assetEntryAssetCategoryRelPersistence);
	}

	protected String getModelClassName() {
		return AssetEntryAssetCategoryRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				assetEntryAssetCategoryRelPersistence.getDataSource();

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

	protected AssetEntryAssetCategoryRelLocalService
		assetEntryAssetCategoryRelLocalService;

	@Reference
	protected AssetEntryAssetCategoryRelPersistence
		assetEntryAssetCategoryRelPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

}