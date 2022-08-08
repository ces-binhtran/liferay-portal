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

package com.liferay.commerce.service.base;

import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.service.CPDAvailabilityEstimateLocalService;
import com.liferay.commerce.service.CPDAvailabilityEstimateLocalServiceUtil;
import com.liferay.commerce.service.persistence.CPDAvailabilityEstimatePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the cpd availability estimate local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.service.impl.CPDAvailabilityEstimateLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.service.impl.CPDAvailabilityEstimateLocalServiceImpl
 * @generated
 */
public abstract class CPDAvailabilityEstimateLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CPDAvailabilityEstimateLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDAvailabilityEstimateLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CPDAvailabilityEstimateLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cpd availability estimate to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDAvailabilityEstimateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 * @return the cpd availability estimate that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDAvailabilityEstimate addCPDAvailabilityEstimate(
		CPDAvailabilityEstimate cpdAvailabilityEstimate) {

		cpdAvailabilityEstimate.setNew(true);

		return cpdAvailabilityEstimatePersistence.update(
			cpdAvailabilityEstimate);
	}

	/**
	 * Creates a new cpd availability estimate with the primary key. Does not add the cpd availability estimate to the database.
	 *
	 * @param CPDAvailabilityEstimateId the primary key for the new cpd availability estimate
	 * @return the new cpd availability estimate
	 */
	@Override
	@Transactional(enabled = false)
	public CPDAvailabilityEstimate createCPDAvailabilityEstimate(
		long CPDAvailabilityEstimateId) {

		return cpdAvailabilityEstimatePersistence.create(
			CPDAvailabilityEstimateId);
	}

	/**
	 * Deletes the cpd availability estimate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDAvailabilityEstimateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate that was removed
	 * @throws PortalException if a cpd availability estimate with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDAvailabilityEstimate deleteCPDAvailabilityEstimate(
			long CPDAvailabilityEstimateId)
		throws PortalException {

		return cpdAvailabilityEstimatePersistence.remove(
			CPDAvailabilityEstimateId);
	}

	/**
	 * Deletes the cpd availability estimate from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDAvailabilityEstimateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 * @return the cpd availability estimate that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDAvailabilityEstimate deleteCPDAvailabilityEstimate(
		CPDAvailabilityEstimate cpdAvailabilityEstimate) {

		return cpdAvailabilityEstimatePersistence.remove(
			cpdAvailabilityEstimate);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return cpdAvailabilityEstimatePersistence.dslQuery(dslQuery);
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
			CPDAvailabilityEstimate.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cpdAvailabilityEstimatePersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl</code>.
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

		return cpdAvailabilityEstimatePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl</code>.
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

		return cpdAvailabilityEstimatePersistence.findWithDynamicQuery(
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
		return cpdAvailabilityEstimatePersistence.countWithDynamicQuery(
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

		return cpdAvailabilityEstimatePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CPDAvailabilityEstimate fetchCPDAvailabilityEstimate(
		long CPDAvailabilityEstimateId) {

		return cpdAvailabilityEstimatePersistence.fetchByPrimaryKey(
			CPDAvailabilityEstimateId);
	}

	/**
	 * Returns the cpd availability estimate with the matching UUID and company.
	 *
	 * @param uuid the cpd availability estimate's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate
		fetchCPDAvailabilityEstimateByUuidAndCompanyId(
			String uuid, long companyId) {

		return cpdAvailabilityEstimatePersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the cpd availability estimate with the primary key.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate
	 * @throws PortalException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate getCPDAvailabilityEstimate(
			long CPDAvailabilityEstimateId)
		throws PortalException {

		return cpdAvailabilityEstimatePersistence.findByPrimaryKey(
			CPDAvailabilityEstimateId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			cpdAvailabilityEstimateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDAvailabilityEstimate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDAvailabilityEstimateId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			cpdAvailabilityEstimateLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CPDAvailabilityEstimate.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDAvailabilityEstimateId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			cpdAvailabilityEstimateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDAvailabilityEstimate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDAvailabilityEstimateId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CPDAvailabilityEstimate>() {

				@Override
				public void performAction(
						CPDAvailabilityEstimate cpdAvailabilityEstimate)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, cpdAvailabilityEstimate);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CPDAvailabilityEstimate.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpdAvailabilityEstimatePersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return cpdAvailabilityEstimateLocalService.
			deleteCPDAvailabilityEstimate(
				(CPDAvailabilityEstimate)persistedModel);
	}

	@Override
	public BasePersistence<CPDAvailabilityEstimate> getBasePersistence() {
		return cpdAvailabilityEstimatePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpdAvailabilityEstimatePersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns the cpd availability estimate with the matching UUID and company.
	 *
	 * @param uuid the cpd availability estimate's UUID
	 * @param companyId the primary key of the company
	 * @return the matching cpd availability estimate
	 * @throws PortalException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate getCPDAvailabilityEstimateByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return cpdAvailabilityEstimatePersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the cpd availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @return the range of cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> getCPDAvailabilityEstimates(
		int start, int end) {

		return cpdAvailabilityEstimatePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cpd availability estimates.
	 *
	 * @return the number of cpd availability estimates
	 */
	@Override
	public int getCPDAvailabilityEstimatesCount() {
		return cpdAvailabilityEstimatePersistence.countAll();
	}

	/**
	 * Updates the cpd availability estimate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPDAvailabilityEstimateLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 * @return the cpd availability estimate that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDAvailabilityEstimate updateCPDAvailabilityEstimate(
		CPDAvailabilityEstimate cpdAvailabilityEstimate) {

		return cpdAvailabilityEstimatePersistence.update(
			cpdAvailabilityEstimate);
	}

	/**
	 * Returns the cpd availability estimate local service.
	 *
	 * @return the cpd availability estimate local service
	 */
	public CPDAvailabilityEstimateLocalService
		getCPDAvailabilityEstimateLocalService() {

		return cpdAvailabilityEstimateLocalService;
	}

	/**
	 * Sets the cpd availability estimate local service.
	 *
	 * @param cpdAvailabilityEstimateLocalService the cpd availability estimate local service
	 */
	public void setCPDAvailabilityEstimateLocalService(
		CPDAvailabilityEstimateLocalService
			cpdAvailabilityEstimateLocalService) {

		this.cpdAvailabilityEstimateLocalService =
			cpdAvailabilityEstimateLocalService;
	}

	/**
	 * Returns the cpd availability estimate persistence.
	 *
	 * @return the cpd availability estimate persistence
	 */
	public CPDAvailabilityEstimatePersistence
		getCPDAvailabilityEstimatePersistence() {

		return cpdAvailabilityEstimatePersistence;
	}

	/**
	 * Sets the cpd availability estimate persistence.
	 *
	 * @param cpdAvailabilityEstimatePersistence the cpd availability estimate persistence
	 */
	public void setCPDAvailabilityEstimatePersistence(
		CPDAvailabilityEstimatePersistence cpdAvailabilityEstimatePersistence) {

		this.cpdAvailabilityEstimatePersistence =
			cpdAvailabilityEstimatePersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.model.CPDAvailabilityEstimate",
			cpdAvailabilityEstimateLocalService);

		_setLocalServiceUtilService(cpdAvailabilityEstimateLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.model.CPDAvailabilityEstimate");

		_setLocalServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CPDAvailabilityEstimateLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDAvailabilityEstimate.class;
	}

	protected String getModelClassName() {
		return CPDAvailabilityEstimate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpdAvailabilityEstimatePersistence.getDataSource();

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
		CPDAvailabilityEstimateLocalService
			cpdAvailabilityEstimateLocalService) {

		try {
			Field field =
				CPDAvailabilityEstimateLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, cpdAvailabilityEstimateLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(type = CPDAvailabilityEstimateLocalService.class)
	protected CPDAvailabilityEstimateLocalService
		cpdAvailabilityEstimateLocalService;

	@BeanReference(type = CPDAvailabilityEstimatePersistence.class)
	protected CPDAvailabilityEstimatePersistence
		cpdAvailabilityEstimatePersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}