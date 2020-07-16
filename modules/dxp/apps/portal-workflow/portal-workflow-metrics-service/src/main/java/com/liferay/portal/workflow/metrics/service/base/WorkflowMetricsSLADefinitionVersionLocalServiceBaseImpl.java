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
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionVersionLocalService;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionPersistence;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionVersionFinder;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionVersionPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the workflow metrics sla definition version local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.metrics.service.impl.WorkflowMetricsSLADefinitionVersionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.metrics.service.impl.WorkflowMetricsSLADefinitionVersionLocalServiceImpl
 * @generated
 */
public abstract class WorkflowMetricsSLADefinitionVersionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService,
			   WorkflowMetricsSLADefinitionVersionLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WorkflowMetricsSLADefinitionVersionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionVersionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the workflow metrics sla definition version to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinitionVersion the workflow metrics sla definition version
	 * @return the workflow metrics sla definition version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkflowMetricsSLADefinitionVersion
		addWorkflowMetricsSLADefinitionVersion(
			WorkflowMetricsSLADefinitionVersion
				workflowMetricsSLADefinitionVersion) {

		workflowMetricsSLADefinitionVersion.setNew(true);

		return workflowMetricsSLADefinitionVersionPersistence.update(
			workflowMetricsSLADefinitionVersion);
	}

	/**
	 * Creates a new workflow metrics sla definition version with the primary key. Does not add the workflow metrics sla definition version to the database.
	 *
	 * @param workflowMetricsSLADefinitionVersionId the primary key for the new workflow metrics sla definition version
	 * @return the new workflow metrics sla definition version
	 */
	@Override
	@Transactional(enabled = false)
	public WorkflowMetricsSLADefinitionVersion
		createWorkflowMetricsSLADefinitionVersion(
			long workflowMetricsSLADefinitionVersionId) {

		return workflowMetricsSLADefinitionVersionPersistence.create(
			workflowMetricsSLADefinitionVersionId);
	}

	/**
	 * Deletes the workflow metrics sla definition version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinitionVersionId the primary key of the workflow metrics sla definition version
	 * @return the workflow metrics sla definition version that was removed
	 * @throws PortalException if a workflow metrics sla definition version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkflowMetricsSLADefinitionVersion
			deleteWorkflowMetricsSLADefinitionVersion(
				long workflowMetricsSLADefinitionVersionId)
		throws PortalException {

		return workflowMetricsSLADefinitionVersionPersistence.remove(
			workflowMetricsSLADefinitionVersionId);
	}

	/**
	 * Deletes the workflow metrics sla definition version from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinitionVersion the workflow metrics sla definition version
	 * @return the workflow metrics sla definition version that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkflowMetricsSLADefinitionVersion
		deleteWorkflowMetricsSLADefinitionVersion(
			WorkflowMetricsSLADefinitionVersion
				workflowMetricsSLADefinitionVersion) {

		return workflowMetricsSLADefinitionVersionPersistence.remove(
			workflowMetricsSLADefinitionVersion);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			WorkflowMetricsSLADefinitionVersion.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return workflowMetricsSLADefinitionVersionPersistence.
			findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionVersionModelImpl</code>.
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

		return workflowMetricsSLADefinitionVersionPersistence.
			findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionVersionModelImpl</code>.
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

		return workflowMetricsSLADefinitionVersionPersistence.
			findWithDynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return workflowMetricsSLADefinitionVersionPersistence.
			countWithDynamicQuery(dynamicQuery);
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

		return workflowMetricsSLADefinitionVersionPersistence.
			countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public WorkflowMetricsSLADefinitionVersion
		fetchWorkflowMetricsSLADefinitionVersion(
			long workflowMetricsSLADefinitionVersionId) {

		return workflowMetricsSLADefinitionVersionPersistence.fetchByPrimaryKey(
			workflowMetricsSLADefinitionVersionId);
	}

	/**
	 * Returns the workflow metrics sla definition version matching the UUID and group.
	 *
	 * @param uuid the workflow metrics sla definition version's UUID
	 * @param groupId the primary key of the group
	 * @return the matching workflow metrics sla definition version, or <code>null</code> if a matching workflow metrics sla definition version could not be found
	 */
	@Override
	public WorkflowMetricsSLADefinitionVersion
		fetchWorkflowMetricsSLADefinitionVersionByUuidAndGroupId(
			String uuid, long groupId) {

		return workflowMetricsSLADefinitionVersionPersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the workflow metrics sla definition version with the primary key.
	 *
	 * @param workflowMetricsSLADefinitionVersionId the primary key of the workflow metrics sla definition version
	 * @return the workflow metrics sla definition version
	 * @throws PortalException if a workflow metrics sla definition version with the primary key could not be found
	 */
	@Override
	public WorkflowMetricsSLADefinitionVersion
			getWorkflowMetricsSLADefinitionVersion(
				long workflowMetricsSLADefinitionVersionId)
		throws PortalException {

		return workflowMetricsSLADefinitionVersionPersistence.findByPrimaryKey(
			workflowMetricsSLADefinitionVersionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			workflowMetricsSLADefinitionVersionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			WorkflowMetricsSLADefinitionVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"workflowMetricsSLADefinitionVersionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			workflowMetricsSLADefinitionVersionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			WorkflowMetricsSLADefinitionVersion.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"workflowMetricsSLADefinitionVersionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			workflowMetricsSLADefinitionVersionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(
			WorkflowMetricsSLADefinitionVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"workflowMetricsSLADefinitionVersionId");
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
									WorkflowMetricsSLADefinitionVersion.class.
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
				<WorkflowMetricsSLADefinitionVersion>() {

				@Override
				public void performAction(
						WorkflowMetricsSLADefinitionVersion
							workflowMetricsSLADefinitionVersion)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext,
						workflowMetricsSLADefinitionVersion);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					WorkflowMetricsSLADefinitionVersion.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return workflowMetricsSLADefinitionVersionLocalService.
			deleteWorkflowMetricsSLADefinitionVersion(
				(WorkflowMetricsSLADefinitionVersion)persistedModel);
	}

	public BasePersistence<WorkflowMetricsSLADefinitionVersion>
		getBasePersistence() {

		return workflowMetricsSLADefinitionVersionPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return workflowMetricsSLADefinitionVersionPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the workflow metrics sla definition versions matching the UUID and company.
	 *
	 * @param uuid the UUID of the workflow metrics sla definition versions
	 * @param companyId the primary key of the company
	 * @return the matching workflow metrics sla definition versions, or an empty list if no matches were found
	 */
	@Override
	public List<WorkflowMetricsSLADefinitionVersion>
		getWorkflowMetricsSLADefinitionVersionsByUuidAndCompanyId(
			String uuid, long companyId) {

		return workflowMetricsSLADefinitionVersionPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of workflow metrics sla definition versions matching the UUID and company.
	 *
	 * @param uuid the UUID of the workflow metrics sla definition versions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of workflow metrics sla definition versions
	 * @param end the upper bound of the range of workflow metrics sla definition versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching workflow metrics sla definition versions, or an empty list if no matches were found
	 */
	@Override
	public List<WorkflowMetricsSLADefinitionVersion>
		getWorkflowMetricsSLADefinitionVersionsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<WorkflowMetricsSLADefinitionVersion>
				orderByComparator) {

		return workflowMetricsSLADefinitionVersionPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the workflow metrics sla definition version matching the UUID and group.
	 *
	 * @param uuid the workflow metrics sla definition version's UUID
	 * @param groupId the primary key of the group
	 * @return the matching workflow metrics sla definition version
	 * @throws PortalException if a matching workflow metrics sla definition version could not be found
	 */
	@Override
	public WorkflowMetricsSLADefinitionVersion
			getWorkflowMetricsSLADefinitionVersionByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return workflowMetricsSLADefinitionVersionPersistence.findByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the workflow metrics sla definition versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.metrics.model.impl.WorkflowMetricsSLADefinitionVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow metrics sla definition versions
	 * @param end the upper bound of the range of workflow metrics sla definition versions (not inclusive)
	 * @return the range of workflow metrics sla definition versions
	 */
	@Override
	public List<WorkflowMetricsSLADefinitionVersion>
		getWorkflowMetricsSLADefinitionVersions(int start, int end) {

		return workflowMetricsSLADefinitionVersionPersistence.findAll(
			start, end);
	}

	/**
	 * Returns the number of workflow metrics sla definition versions.
	 *
	 * @return the number of workflow metrics sla definition versions
	 */
	@Override
	public int getWorkflowMetricsSLADefinitionVersionsCount() {
		return workflowMetricsSLADefinitionVersionPersistence.countAll();
	}

	/**
	 * Updates the workflow metrics sla definition version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WorkflowMetricsSLADefinitionVersionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param workflowMetricsSLADefinitionVersion the workflow metrics sla definition version
	 * @return the workflow metrics sla definition version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkflowMetricsSLADefinitionVersion
		updateWorkflowMetricsSLADefinitionVersion(
			WorkflowMetricsSLADefinitionVersion
				workflowMetricsSLADefinitionVersion) {

		return workflowMetricsSLADefinitionVersionPersistence.update(
			workflowMetricsSLADefinitionVersion);
	}

	/**
	 * Returns the workflow metrics sla definition local service.
	 *
	 * @return the workflow metrics sla definition local service
	 */
	public com.liferay.portal.workflow.metrics.service.
		WorkflowMetricsSLADefinitionLocalService
			getWorkflowMetricsSLADefinitionLocalService() {

		return workflowMetricsSLADefinitionLocalService;
	}

	/**
	 * Sets the workflow metrics sla definition local service.
	 *
	 * @param workflowMetricsSLADefinitionLocalService the workflow metrics sla definition local service
	 */
	public void setWorkflowMetricsSLADefinitionLocalService(
		com.liferay.portal.workflow.metrics.service.
			WorkflowMetricsSLADefinitionLocalService
				workflowMetricsSLADefinitionLocalService) {

		this.workflowMetricsSLADefinitionLocalService =
			workflowMetricsSLADefinitionLocalService;
	}

	/**
	 * Returns the workflow metrics sla definition persistence.
	 *
	 * @return the workflow metrics sla definition persistence
	 */
	public WorkflowMetricsSLADefinitionPersistence
		getWorkflowMetricsSLADefinitionPersistence() {

		return workflowMetricsSLADefinitionPersistence;
	}

	/**
	 * Sets the workflow metrics sla definition persistence.
	 *
	 * @param workflowMetricsSLADefinitionPersistence the workflow metrics sla definition persistence
	 */
	public void setWorkflowMetricsSLADefinitionPersistence(
		WorkflowMetricsSLADefinitionPersistence
			workflowMetricsSLADefinitionPersistence) {

		this.workflowMetricsSLADefinitionPersistence =
			workflowMetricsSLADefinitionPersistence;
	}

	/**
	 * Returns the workflow metrics sla definition version local service.
	 *
	 * @return the workflow metrics sla definition version local service
	 */
	public WorkflowMetricsSLADefinitionVersionLocalService
		getWorkflowMetricsSLADefinitionVersionLocalService() {

		return workflowMetricsSLADefinitionVersionLocalService;
	}

	/**
	 * Sets the workflow metrics sla definition version local service.
	 *
	 * @param workflowMetricsSLADefinitionVersionLocalService the workflow metrics sla definition version local service
	 */
	public void setWorkflowMetricsSLADefinitionVersionLocalService(
		WorkflowMetricsSLADefinitionVersionLocalService
			workflowMetricsSLADefinitionVersionLocalService) {

		this.workflowMetricsSLADefinitionVersionLocalService =
			workflowMetricsSLADefinitionVersionLocalService;
	}

	/**
	 * Returns the workflow metrics sla definition version persistence.
	 *
	 * @return the workflow metrics sla definition version persistence
	 */
	public WorkflowMetricsSLADefinitionVersionPersistence
		getWorkflowMetricsSLADefinitionVersionPersistence() {

		return workflowMetricsSLADefinitionVersionPersistence;
	}

	/**
	 * Sets the workflow metrics sla definition version persistence.
	 *
	 * @param workflowMetricsSLADefinitionVersionPersistence the workflow metrics sla definition version persistence
	 */
	public void setWorkflowMetricsSLADefinitionVersionPersistence(
		WorkflowMetricsSLADefinitionVersionPersistence
			workflowMetricsSLADefinitionVersionPersistence) {

		this.workflowMetricsSLADefinitionVersionPersistence =
			workflowMetricsSLADefinitionVersionPersistence;
	}

	/**
	 * Returns the workflow metrics sla definition version finder.
	 *
	 * @return the workflow metrics sla definition version finder
	 */
	public WorkflowMetricsSLADefinitionVersionFinder
		getWorkflowMetricsSLADefinitionVersionFinder() {

		return workflowMetricsSLADefinitionVersionFinder;
	}

	/**
	 * Sets the workflow metrics sla definition version finder.
	 *
	 * @param workflowMetricsSLADefinitionVersionFinder the workflow metrics sla definition version finder
	 */
	public void setWorkflowMetricsSLADefinitionVersionFinder(
		WorkflowMetricsSLADefinitionVersionFinder
			workflowMetricsSLADefinitionVersionFinder) {

		this.workflowMetricsSLADefinitionVersionFinder =
			workflowMetricsSLADefinitionVersionFinder;
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
			"com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion",
			workflowMetricsSLADefinitionVersionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WorkflowMetricsSLADefinitionVersionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return WorkflowMetricsSLADefinitionVersion.class;
	}

	protected String getModelClassName() {
		return WorkflowMetricsSLADefinitionVersion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				workflowMetricsSLADefinitionVersionPersistence.getDataSource();

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
		type = com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionLocalService.class
	)
	protected com.liferay.portal.workflow.metrics.service.
		WorkflowMetricsSLADefinitionLocalService
			workflowMetricsSLADefinitionLocalService;

	@BeanReference(type = WorkflowMetricsSLADefinitionPersistence.class)
	protected WorkflowMetricsSLADefinitionPersistence
		workflowMetricsSLADefinitionPersistence;

	@BeanReference(type = WorkflowMetricsSLADefinitionVersionLocalService.class)
	protected WorkflowMetricsSLADefinitionVersionLocalService
		workflowMetricsSLADefinitionVersionLocalService;

	@BeanReference(type = WorkflowMetricsSLADefinitionVersionPersistence.class)
	protected WorkflowMetricsSLADefinitionVersionPersistence
		workflowMetricsSLADefinitionVersionPersistence;

	@BeanReference(type = WorkflowMetricsSLADefinitionVersionFinder.class)
	protected WorkflowMetricsSLADefinitionVersionFinder
		workflowMetricsSLADefinitionVersionFinder;

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