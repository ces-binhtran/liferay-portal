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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLayoutPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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

import java.lang.reflect.Field;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the ddm structure local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMStructureLocalServiceImpl
 * @generated
 */
public abstract class DDMStructureLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, DDMStructureLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDMStructureLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>DDMStructureLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ddm structure to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMStructureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmStructure the ddm structure
	 * @return the ddm structure that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMStructure addDDMStructure(DDMStructure ddmStructure) {
		ddmStructure.setNew(true);

		return ddmStructurePersistence.update(ddmStructure);
	}

	/**
	 * Creates a new ddm structure with the primary key. Does not add the ddm structure to the database.
	 *
	 * @param structureId the primary key for the new ddm structure
	 * @return the new ddm structure
	 */
	@Override
	@Transactional(enabled = false)
	public DDMStructure createDDMStructure(long structureId) {
		return ddmStructurePersistence.create(structureId);
	}

	/**
	 * Deletes the ddm structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMStructureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param structureId the primary key of the ddm structure
	 * @return the ddm structure that was removed
	 * @throws PortalException if a ddm structure with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMStructure deleteDDMStructure(long structureId)
		throws PortalException {

		return ddmStructurePersistence.remove(structureId);
	}

	/**
	 * Deletes the ddm structure from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMStructureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmStructure the ddm structure
	 * @return the ddm structure that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMStructure deleteDDMStructure(DDMStructure ddmStructure) {
		return ddmStructurePersistence.remove(ddmStructure);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return ddmStructurePersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			DDMStructure.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddmStructurePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureModelImpl</code>.
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

		return ddmStructurePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureModelImpl</code>.
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

		return ddmStructurePersistence.findWithDynamicQuery(
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
		return ddmStructurePersistence.countWithDynamicQuery(dynamicQuery);
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

		return ddmStructurePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DDMStructure fetchDDMStructure(long structureId) {
		return ddmStructurePersistence.fetchByPrimaryKey(structureId);
	}

	/**
	 * Returns the ddm structure matching the UUID and group.
	 *
	 * @param uuid the ddm structure's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddm structure, or <code>null</code> if a matching ddm structure could not be found
	 */
	@Override
	public DDMStructure fetchDDMStructureByUuidAndGroupId(
		String uuid, long groupId) {

		return ddmStructurePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the ddm structure with the primary key.
	 *
	 * @param structureId the primary key of the ddm structure
	 * @return the ddm structure
	 * @throws PortalException if a ddm structure with the primary key could not be found
	 */
	@Override
	public DDMStructure getDDMStructure(long structureId)
		throws PortalException {

		return ddmStructurePersistence.findByPrimaryKey(structureId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ddmStructureLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMStructure.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("structureId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ddmStructureLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDMStructure.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"structureId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ddmStructureLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMStructure.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("structureId");
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
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DDMStructure>() {

				@Override
				public void performAction(DDMStructure ddmStructure)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, ddmStructure);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(DDMStructure.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddmStructurePersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return ddmStructureLocalService.deleteDDMStructure(
			(DDMStructure)persistedModel);
	}

	public BasePersistence<DDMStructure> getBasePersistence() {
		return ddmStructurePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddmStructurePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the ddm structures matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddm structures
	 * @param companyId the primary key of the company
	 * @return the matching ddm structures, or an empty list if no matches were found
	 */
	@Override
	public List<DDMStructure> getDDMStructuresByUuidAndCompanyId(
		String uuid, long companyId) {

		return ddmStructurePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of ddm structures matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddm structures
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of ddm structures
	 * @param end the upper bound of the range of ddm structures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching ddm structures, or an empty list if no matches were found
	 */
	@Override
	public List<DDMStructure> getDDMStructuresByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDMStructure> orderByComparator) {

		return ddmStructurePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the ddm structure matching the UUID and group.
	 *
	 * @param uuid the ddm structure's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddm structure
	 * @throws PortalException if a matching ddm structure could not be found
	 */
	@Override
	public DDMStructure getDDMStructureByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return ddmStructurePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the ddm structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm structures
	 * @param end the upper bound of the range of ddm structures (not inclusive)
	 * @return the range of ddm structures
	 */
	@Override
	public List<DDMStructure> getDDMStructures(int start, int end) {
		return ddmStructurePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ddm structures.
	 *
	 * @return the number of ddm structures
	 */
	@Override
	public int getDDMStructuresCount() {
		return ddmStructurePersistence.countAll();
	}

	/**
	 * Updates the ddm structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDMStructureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddmStructure the ddm structure
	 * @return the ddm structure that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMStructure updateDDMStructure(DDMStructure ddmStructure) {
		return ddmStructurePersistence.update(ddmStructure);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DDMStructureLocalService.class, IdentifiableOSGiService.class,
			CTService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		ddmStructureLocalService = (DDMStructureLocalService)aopProxy;

		_setLocalServiceUtilService(ddmStructureLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMStructureLocalService.class.getName();
	}

	@Override
	public CTPersistence<DDMStructure> getCTPersistence() {
		return ddmStructurePersistence;
	}

	@Override
	public Class<DDMStructure> getModelClass() {
		return DDMStructure.class;
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DDMStructure>, R, E>
				updateUnsafeFunction)
		throws E {

		return updateUnsafeFunction.apply(ddmStructurePersistence);
	}

	protected String getModelClassName() {
		return DDMStructure.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmStructurePersistence.getDataSource();

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
		DDMStructureLocalService ddmStructureLocalService) {

		try {
			Field field = DDMStructureLocalServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, ddmStructureLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected DDMStructureLocalService ddmStructureLocalService;

	@Reference
	protected DDMStructurePersistence ddmStructurePersistence;

	@Reference
	protected DDMStructureFinder ddmStructureFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected DDMDataProviderInstanceLinkPersistence
		ddmDataProviderInstanceLinkPersistence;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected DDMStructureLayoutPersistence ddmStructureLayoutPersistence;

	@Reference
	protected DDMStructureLinkPersistence ddmStructureLinkPersistence;

	@Reference
	protected DDMStructureLinkFinder ddmStructureLinkFinder;

	@Reference
	protected DDMStructureVersionPersistence ddmStructureVersionPersistence;

	@Reference
	protected DDMTemplatePersistence ddmTemplatePersistence;

	@Reference
	protected DDMTemplateFinder ddmTemplateFinder;

}