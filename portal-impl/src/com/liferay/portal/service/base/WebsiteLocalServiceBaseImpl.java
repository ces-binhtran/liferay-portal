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

package com.liferay.portal.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.WebsiteLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.ListTypePersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.WebsitePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the website local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.WebsiteLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.WebsiteLocalServiceImpl
 * @generated
 */
public abstract class WebsiteLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, WebsiteLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WebsiteLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.kernel.service.WebsiteLocalServiceUtil</code>.
	 */

	/**
	 * Adds the website to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebsiteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param website the website
	 * @return the website that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Website addWebsite(Website website) {
		website.setNew(true);

		return websitePersistence.update(website);
	}

	/**
	 * Creates a new website with the primary key. Does not add the website to the database.
	 *
	 * @param websiteId the primary key for the new website
	 * @return the new website
	 */
	@Override
	@Transactional(enabled = false)
	public Website createWebsite(long websiteId) {
		return websitePersistence.create(websiteId);
	}

	/**
	 * Deletes the website with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebsiteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param websiteId the primary key of the website
	 * @return the website that was removed
	 * @throws PortalException if a website with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Website deleteWebsite(long websiteId) throws PortalException {
		return websitePersistence.remove(websiteId);
	}

	/**
	 * Deletes the website from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebsiteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param website the website
	 * @return the website that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Website deleteWebsite(Website website) {
		return websitePersistence.remove(website);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Website.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return websitePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WebsiteModelImpl</code>.
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

		return websitePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WebsiteModelImpl</code>.
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

		return websitePersistence.findWithDynamicQuery(
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
		return websitePersistence.countWithDynamicQuery(dynamicQuery);
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

		return websitePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Website fetchWebsite(long websiteId) {
		return websitePersistence.fetchByPrimaryKey(websiteId);
	}

	/**
	 * Returns the website with the matching UUID and company.
	 *
	 * @param uuid the website's UUID
	 * @param companyId the primary key of the company
	 * @return the matching website, or <code>null</code> if a matching website could not be found
	 */
	@Override
	public Website fetchWebsiteByUuidAndCompanyId(String uuid, long companyId) {
		return websitePersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the website with the primary key.
	 *
	 * @param websiteId the primary key of the website
	 * @return the website
	 * @throws PortalException if a website with the primary key could not be found
	 */
	@Override
	public Website getWebsite(long websiteId) throws PortalException {
		return websitePersistence.findByPrimaryKey(websiteId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(websiteLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Website.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("websiteId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			websiteLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Website.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("websiteId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(websiteLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Website.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("websiteId");
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

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Website>() {

				@Override
				public void performAction(Website website)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, website);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Website.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return websiteLocalService.deleteWebsite((Website)persistedModel);
	}

	public BasePersistence<Website> getBasePersistence() {
		return websitePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return websitePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the website with the matching UUID and company.
	 *
	 * @param uuid the website's UUID
	 * @param companyId the primary key of the company
	 * @return the matching website
	 * @throws PortalException if a matching website could not be found
	 */
	@Override
	public Website getWebsiteByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException {

		return websitePersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns a range of all the websites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.WebsiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of websites
	 * @param end the upper bound of the range of websites (not inclusive)
	 * @return the range of websites
	 */
	@Override
	public List<Website> getWebsites(int start, int end) {
		return websitePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of websites.
	 *
	 * @return the number of websites
	 */
	@Override
	public int getWebsitesCount() {
		return websitePersistence.countAll();
	}

	/**
	 * Updates the website in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WebsiteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param website the website
	 * @return the website that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Website updateWebsite(Website website) {
		return websitePersistence.update(website);
	}

	/**
	 * Returns the website local service.
	 *
	 * @return the website local service
	 */
	public WebsiteLocalService getWebsiteLocalService() {
		return websiteLocalService;
	}

	/**
	 * Sets the website local service.
	 *
	 * @param websiteLocalService the website local service
	 */
	public void setWebsiteLocalService(
		WebsiteLocalService websiteLocalService) {

		this.websiteLocalService = websiteLocalService;
	}

	/**
	 * Returns the website persistence.
	 *
	 * @return the website persistence
	 */
	public WebsitePersistence getWebsitePersistence() {
		return websitePersistence;
	}

	/**
	 * Sets the website persistence.
	 *
	 * @param websitePersistence the website persistence
	 */
	public void setWebsitePersistence(WebsitePersistence websitePersistence) {
		this.websitePersistence = websitePersistence;
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
	 * Returns the list type local service.
	 *
	 * @return the list type local service
	 */
	public com.liferay.portal.kernel.service.ListTypeLocalService
		getListTypeLocalService() {

		return listTypeLocalService;
	}

	/**
	 * Sets the list type local service.
	 *
	 * @param listTypeLocalService the list type local service
	 */
	public void setListTypeLocalService(
		com.liferay.portal.kernel.service.ListTypeLocalService
			listTypeLocalService) {

		this.listTypeLocalService = listTypeLocalService;
	}

	/**
	 * Returns the list type persistence.
	 *
	 * @return the list type persistence
	 */
	public ListTypePersistence getListTypePersistence() {
		return listTypePersistence;
	}

	/**
	 * Sets the list type persistence.
	 *
	 * @param listTypePersistence the list type persistence
	 */
	public void setListTypePersistence(
		ListTypePersistence listTypePersistence) {

		this.listTypePersistence = listTypePersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.Website", websiteLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.Website");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WebsiteLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Website.class;
	}

	protected String getModelClassName() {
		return Website.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = websitePersistence.getDataSource();

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

	@BeanReference(type = WebsiteLocalService.class)
	protected WebsiteLocalService websiteLocalService;

	@BeanReference(type = WebsitePersistence.class)
	protected WebsitePersistence websitePersistence;

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
		type = com.liferay.portal.kernel.service.ListTypeLocalService.class
	)
	protected com.liferay.portal.kernel.service.ListTypeLocalService
		listTypeLocalService;

	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}