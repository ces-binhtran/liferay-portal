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

package com.liferay.layout.page.template.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateEntryPersistence;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Conjunction;
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
import com.liferay.portal.kernel.service.change.tracking.CTService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the layout page template entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.layout.page.template.service.impl.LayoutPageTemplateEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.layout.page.template.service.impl.LayoutPageTemplateEntryLocalServiceImpl
 * @generated
 */
public abstract class LayoutPageTemplateEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   LayoutPageTemplateEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LayoutPageTemplateEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>LayoutPageTemplateEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the layout page template entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateEntry the layout page template entry
	 * @return the layout page template entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutPageTemplateEntry addLayoutPageTemplateEntry(
		LayoutPageTemplateEntry layoutPageTemplateEntry) {

		layoutPageTemplateEntry.setNew(true);

		return layoutPageTemplateEntryPersistence.update(
			layoutPageTemplateEntry);
	}

	/**
	 * Creates a new layout page template entry with the primary key. Does not add the layout page template entry to the database.
	 *
	 * @param layoutPageTemplateEntryId the primary key for the new layout page template entry
	 * @return the new layout page template entry
	 */
	@Override
	@Transactional(enabled = false)
	public LayoutPageTemplateEntry createLayoutPageTemplateEntry(
		long layoutPageTemplateEntryId) {

		return layoutPageTemplateEntryPersistence.create(
			layoutPageTemplateEntryId);
	}

	/**
	 * Deletes the layout page template entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateEntryId the primary key of the layout page template entry
	 * @return the layout page template entry that was removed
	 * @throws PortalException if a layout page template entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutPageTemplateEntry deleteLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId)
		throws PortalException {

		return layoutPageTemplateEntryPersistence.remove(
			layoutPageTemplateEntryId);
	}

	/**
	 * Deletes the layout page template entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateEntry the layout page template entry
	 * @return the layout page template entry that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutPageTemplateEntry deleteLayoutPageTemplateEntry(
			LayoutPageTemplateEntry layoutPageTemplateEntry)
		throws PortalException {

		return layoutPageTemplateEntryPersistence.remove(
			layoutPageTemplateEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return layoutPageTemplateEntryPersistence.dslQuery(dslQuery);
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
			LayoutPageTemplateEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return layoutPageTemplateEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateEntryModelImpl</code>.
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

		return layoutPageTemplateEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateEntryModelImpl</code>.
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

		return layoutPageTemplateEntryPersistence.findWithDynamicQuery(
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
		return layoutPageTemplateEntryPersistence.countWithDynamicQuery(
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

		return layoutPageTemplateEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LayoutPageTemplateEntry fetchLayoutPageTemplateEntry(
		long layoutPageTemplateEntryId) {

		return layoutPageTemplateEntryPersistence.fetchByPrimaryKey(
			layoutPageTemplateEntryId);
	}

	/**
	 * Returns the layout page template entry matching the UUID and group.
	 *
	 * @param uuid the layout page template entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching layout page template entry, or <code>null</code> if a matching layout page template entry could not be found
	 */
	@Override
	public LayoutPageTemplateEntry fetchLayoutPageTemplateEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return layoutPageTemplateEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the layout page template entry with the primary key.
	 *
	 * @param layoutPageTemplateEntryId the primary key of the layout page template entry
	 * @return the layout page template entry
	 * @throws PortalException if a layout page template entry with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplateEntry getLayoutPageTemplateEntry(
			long layoutPageTemplateEntryId)
		throws PortalException {

		return layoutPageTemplateEntryPersistence.findByPrimaryKey(
			layoutPageTemplateEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			layoutPageTemplateEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutPageTemplateEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"layoutPageTemplateEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			layoutPageTemplateEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			LayoutPageTemplateEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"layoutPageTemplateEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			layoutPageTemplateEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutPageTemplateEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"layoutPageTemplateEntryId");
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

					if (modifiedDateCriterion != null) {
						Conjunction conjunction =
							RestrictionsFactoryUtil.conjunction();

						conjunction.add(modifiedDateCriterion);

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(
							RestrictionsFactoryUtil.gtProperty(
								"modifiedDate", "lastPublishDate"));

						Property lastPublishDateProperty =
							PropertyFactoryUtil.forName("lastPublishDate");

						disjunction.add(lastPublishDateProperty.isNull());

						conjunction.add(disjunction);

						modifiedDateCriterion = conjunction;
					}

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

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
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
									LayoutPageTemplateEntry.class.getName());

						dynamicQuery.add(
							workflowStatusProperty.in(
								stagedModelDataHandler.
									getExportableStatuses()));
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<LayoutPageTemplateEntry>() {

				@Override
				public void performAction(
						LayoutPageTemplateEntry layoutPageTemplateEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, layoutPageTemplateEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					LayoutPageTemplateEntry.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutPageTemplateEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return layoutPageTemplateEntryLocalService.
			deleteLayoutPageTemplateEntry(
				(LayoutPageTemplateEntry)persistedModel);
	}

	public BasePersistence<LayoutPageTemplateEntry> getBasePersistence() {
		return layoutPageTemplateEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutPageTemplateEntryPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the layout page template entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the layout page template entries
	 * @param companyId the primary key of the company
	 * @return the matching layout page template entries, or an empty list if no matches were found
	 */
	@Override
	public List<LayoutPageTemplateEntry>
		getLayoutPageTemplateEntriesByUuidAndCompanyId(
			String uuid, long companyId) {

		return layoutPageTemplateEntryPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of layout page template entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the layout page template entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of layout page template entries
	 * @param end the upper bound of the range of layout page template entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching layout page template entries, or an empty list if no matches were found
	 */
	@Override
	public List<LayoutPageTemplateEntry>
		getLayoutPageTemplateEntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<LayoutPageTemplateEntry> orderByComparator) {

		return layoutPageTemplateEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the layout page template entry matching the UUID and group.
	 *
	 * @param uuid the layout page template entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching layout page template entry
	 * @throws PortalException if a matching layout page template entry could not be found
	 */
	@Override
	public LayoutPageTemplateEntry getLayoutPageTemplateEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return layoutPageTemplateEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the layout page template entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.layout.page.template.model.impl.LayoutPageTemplateEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page template entries
	 * @param end the upper bound of the range of layout page template entries (not inclusive)
	 * @return the range of layout page template entries
	 */
	@Override
	public List<LayoutPageTemplateEntry> getLayoutPageTemplateEntries(
		int start, int end) {

		return layoutPageTemplateEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of layout page template entries.
	 *
	 * @return the number of layout page template entries
	 */
	@Override
	public int getLayoutPageTemplateEntriesCount() {
		return layoutPageTemplateEntryPersistence.countAll();
	}

	/**
	 * Updates the layout page template entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LayoutPageTemplateEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param layoutPageTemplateEntry the layout page template entry
	 * @return the layout page template entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutPageTemplateEntry updateLayoutPageTemplateEntry(
		LayoutPageTemplateEntry layoutPageTemplateEntry) {

		return layoutPageTemplateEntryPersistence.update(
			layoutPageTemplateEntry);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			LayoutPageTemplateEntryLocalService.class,
			IdentifiableOSGiService.class, CTService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		layoutPageTemplateEntryLocalService =
			(LayoutPageTemplateEntryLocalService)aopProxy;

		_setLocalServiceUtilService(layoutPageTemplateEntryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutPageTemplateEntryLocalService.class.getName();
	}

	@Override
	public CTPersistence<LayoutPageTemplateEntry> getCTPersistence() {
		return layoutPageTemplateEntryPersistence;
	}

	@Override
	public Class<LayoutPageTemplateEntry> getModelClass() {
		return LayoutPageTemplateEntry.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<LayoutPageTemplateEntry>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(layoutPageTemplateEntryPersistence);
	}

	protected String getModelClassName() {
		return LayoutPageTemplateEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				layoutPageTemplateEntryPersistence.getDataSource();

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
		LayoutPageTemplateEntryLocalService
			layoutPageTemplateEntryLocalService) {

		try {
			Field field =
				LayoutPageTemplateEntryLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, layoutPageTemplateEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected LayoutPageTemplateEntryLocalService
		layoutPageTemplateEntryLocalService;

	@Reference
	protected LayoutPageTemplateEntryPersistence
		layoutPageTemplateEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.LayoutLocalService
		layoutLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.LayoutSetLocalService
		layoutSetLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}