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

package com.liferay.portal.workflow.metrics.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinition;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionLocalService;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionPersistence;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionVersionFinder;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionVersionPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the workflow metrics sla definition local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.metrics.service.impl.WorkflowMetricsSLADefinitionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.metrics.service.impl.WorkflowMetricsSLADefinitionLocalServiceImpl
 * @generated
 */
public abstract class WorkflowMetricsSLADefinitionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   WorkflowMetricsSLADefinitionLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WorkflowMetricsSLADefinitionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the workflow metrics sla definition to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinition the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkflowMetricsSLADefinition addWorkflowMetricsSLADefinition(
		WorkflowMetricsSLADefinition workflowMetricsSLADefinition) {

		workflowMetricsSLADefinition.setNew(true);

		return workflowMetricsSLADefinitionPersistence.update(
			workflowMetricsSLADefinition);
	}

	/**
	 * Creates a new workflow metrics sla definition with the primary key. Does not add the workflow metrics sla definition to the database.
	 *
	 * @param workflowMetricsSLADefinitionId the primary key for the new workflow metrics sla definition
	 * @return the new workflow metrics sla definition
	 */
	@Override
	@Transactional(enabled = false)
	public WorkflowMetricsSLADefinition createWorkflowMetricsSLADefinition(
		long workflowMetricsSLADefinitionId) {

		return workflowMetricsSLADefinitionPersistence.create(
			workflowMetricsSLADefinitionId);
	}

	/**
	 * Deletes the workflow metrics sla definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinitionId the primary key of the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was removed
	 * @throws PortalException if a workflow metrics sla definition with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkflowMetricsSLADefinition deleteWorkflowMetricsSLADefinition(
			long workflowMetricsSLADefinitionId)
		throws PortalException {

		return workflowMetricsSLADefinitionPersistence.remove(
			workflowMetricsSLADefinitionId);
	}

	/**
	 * Deletes the workflow metrics sla definition from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinition the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkflowMetricsSLADefinition deleteWorkflowMetricsSLADefinition(
		WorkflowMetricsSLADefinition workflowMetricsSLADefinition) {

		return workflowMetricsSLADefinitionPersistence.remove(
			workflowMetricsSLADefinition);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return workflowMetricsSLADefinitionPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			WorkflowMetricsSLADefinition.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return workflowMetricsSLADefinitionPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionModelImpl</code>.
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

		return workflowMetricsSLADefinitionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionModelImpl</code>.
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

		return workflowMetricsSLADefinitionPersistence.findWithDynamicQuery(
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
		return workflowMetricsSLADefinitionPersistence.countWithDynamicQuery(
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

		return workflowMetricsSLADefinitionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public WorkflowMetricsSLADefinition fetchWorkflowMetricsSLADefinition(
		long workflowMetricsSLADefinitionId) {

		return workflowMetricsSLADefinitionPersistence.fetchByPrimaryKey(
			workflowMetricsSLADefinitionId);
	}

	/**
	 * Returns the workflow metrics sla definition matching the UUID and group.
	 *
	 * @param uuid the workflow metrics sla definition's UUID
	 * @param groupId the primary key of the group
	 * @return the matching workflow metrics sla definition, or <code>null</code> if a matching workflow metrics sla definition could not be found
	 */
	@Override
	public WorkflowMetricsSLADefinition
		fetchWorkflowMetricsSLADefinitionByUuidAndGroupId(
			String uuid, long groupId) {

		return workflowMetricsSLADefinitionPersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the workflow metrics sla definition with the primary key.
	 *
	 * @param workflowMetricsSLADefinitionId the primary key of the workflow metrics sla definition
	 * @return the workflow metrics sla definition
	 * @throws PortalException if a workflow metrics sla definition with the primary key could not be found
	 */
	@Override
	public WorkflowMetricsSLADefinition getWorkflowMetricsSLADefinition(
			long workflowMetricsSLADefinitionId)
		throws PortalException {

		return workflowMetricsSLADefinitionPersistence.findByPrimaryKey(
			workflowMetricsSLADefinitionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			workflowMetricsSLADefinitionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			WorkflowMetricsSLADefinition.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"workflowMetricsSLADefinitionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			workflowMetricsSLADefinitionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			WorkflowMetricsSLADefinition.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"workflowMetricsSLADefinitionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			workflowMetricsSLADefinitionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			WorkflowMetricsSLADefinition.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"workflowMetricsSLADefinitionId");
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
					Criterion modifiedDateCriterion =
						portletDataContext.getDateRangeCriteria("modifiedDate");

					Criterion statusDateCriterion =
						portletDataContext.getDateRangeCriteria("statusDate");

					if ((modifiedDateCriterion != null) &&
						(statusDateCriterion != null)) {

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(modifiedDateCriterion);
						disjunction.add(statusDateCriterion);

						dynamicQuery.add(disjunction);
					}

					Property workflowStatusProperty =
						PropertyFactoryUtil.forName("status");

					if (portletDataContext.isInitialPublication()) {
						dynamicQuery.add(
							workflowStatusProperty.ne(
								WorkflowConstants.STATUS_IN_TRASH));
					}
					else {
						StagedModelDataHandler<?> stagedModelDataHandler =
							StagedModelDataHandlerRegistryUtil.
								getStagedModelDataHandler(
									WorkflowMetricsSLADefinition.class.
										getName());

						dynamicQuery.add(
							workflowStatusProperty.in(
								stagedModelDataHandler.
									getExportableStatuses()));
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<WorkflowMetricsSLADefinition>() {

				@Override
				public void performAction(
						WorkflowMetricsSLADefinition
							workflowMetricsSLADefinition)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, workflowMetricsSLADefinition);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					WorkflowMetricsSLADefinition.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return workflowMetricsSLADefinitionPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return workflowMetricsSLADefinitionLocalService.
			deleteWorkflowMetricsSLADefinition(
				(WorkflowMetricsSLADefinition)persistedModel);
	}

	public BasePersistence<WorkflowMetricsSLADefinition> getBasePersistence() {
		return workflowMetricsSLADefinitionPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return workflowMetricsSLADefinitionPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the workflow metrics sla definitions matching the UUID and company.
	 *
	 * @param uuid the UUID of the workflow metrics sla definitions
	 * @param companyId the primary key of the company
	 * @return the matching workflow metrics sla definitions, or an empty list if no matches were found
	 */
	@Override
	public List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitionsByUuidAndCompanyId(
			String uuid, long companyId) {

		return workflowMetricsSLADefinitionPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of workflow metrics sla definitions matching the UUID and company.
	 *
	 * @param uuid the UUID of the workflow metrics sla definitions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of workflow metrics sla definitions
	 * @param end the upper bound of the range of workflow metrics sla definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching workflow metrics sla definitions, or an empty list if no matches were found
	 */
	@Override
	public List<WorkflowMetricsSLADefinition>
		getWorkflowMetricsSLADefinitionsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<WorkflowMetricsSLADefinition> orderByComparator) {

		return workflowMetricsSLADefinitionPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the workflow metrics sla definition matching the UUID and group.
	 *
	 * @param uuid the workflow metrics sla definition's UUID
	 * @param groupId the primary key of the group
	 * @return the matching workflow metrics sla definition
	 * @throws PortalException if a matching workflow metrics sla definition could not be found
	 */
	@Override
	public WorkflowMetricsSLADefinition
			getWorkflowMetricsSLADefinitionByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return workflowMetricsSLADefinitionPersistence.findByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the workflow metrics sla definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow metrics sla definitions
	 * @param end the upper bound of the range of workflow metrics sla definitions (not inclusive)
	 * @return the range of workflow metrics sla definitions
	 */
	@Override
	public List<WorkflowMetricsSLADefinition> getWorkflowMetricsSLADefinitions(
		int start, int end) {

		return workflowMetricsSLADefinitionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of workflow metrics sla definitions.
	 *
	 * @return the number of workflow metrics sla definitions
	 */
	@Override
	public int getWorkflowMetricsSLADefinitionsCount() {
		return workflowMetricsSLADefinitionPersistence.countAll();
	}

	/**
	 * Updates the workflow metrics sla definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinition the workflow metrics sla definition
	 * @return the workflow metrics sla definition that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkflowMetricsSLADefinition updateWorkflowMetricsSLADefinition(
		WorkflowMetricsSLADefinition workflowMetricsSLADefinition) {

		return workflowMetricsSLADefinitionPersistence.update(
			workflowMetricsSLADefinition);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			WorkflowMetricsSLADefinitionLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		workflowMetricsSLADefinitionLocalService =
			(WorkflowMetricsSLADefinitionLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WorkflowMetricsSLADefinitionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return WorkflowMetricsSLADefinition.class;
	}

	protected String getModelClassName() {
		return WorkflowMetricsSLADefinition.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				workflowMetricsSLADefinitionPersistence.getDataSource();

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

	protected WorkflowMetricsSLADefinitionLocalService
		workflowMetricsSLADefinitionLocalService;

	@Reference
	protected WorkflowMetricsSLADefinitionPersistence
		workflowMetricsSLADefinitionPersistence;

	@Reference
	protected WorkflowMetricsSLADefinitionVersionPersistence
		workflowMetricsSLADefinitionVersionPersistence;

	@Reference
	protected WorkflowMetricsSLADefinitionVersionFinder
		workflowMetricsSLADefinitionVersionFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}