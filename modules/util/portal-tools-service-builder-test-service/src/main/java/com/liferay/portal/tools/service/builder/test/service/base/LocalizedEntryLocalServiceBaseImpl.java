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

package com.liferay.portal.tools.service.builder.test.service.base;

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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.LocalizedEntry;
import com.liferay.portal.tools.service.builder.test.model.LocalizedEntryLocalization;
import com.liferay.portal.tools.service.builder.test.service.LocalizedEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.persistence.LocalizedEntryLocalizationPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.LocalizedEntryPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the localized entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.LocalizedEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.LocalizedEntryLocalServiceImpl
 * @generated
 */
public abstract class LocalizedEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, LocalizedEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LocalizedEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.tools.service.builder.test.service.LocalizedEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the localized entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LocalizedEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param localizedEntry the localized entry
	 * @return the localized entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LocalizedEntry addLocalizedEntry(LocalizedEntry localizedEntry) {
		localizedEntry.setNew(true);

		return localizedEntryPersistence.update(localizedEntry);
	}

	/**
	 * Creates a new localized entry with the primary key. Does not add the localized entry to the database.
	 *
	 * @param localizedEntryId the primary key for the new localized entry
	 * @return the new localized entry
	 */
	@Override
	@Transactional(enabled = false)
	public LocalizedEntry createLocalizedEntry(long localizedEntryId) {
		return localizedEntryPersistence.create(localizedEntryId);
	}

	/**
	 * Deletes the localized entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LocalizedEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param localizedEntryId the primary key of the localized entry
	 * @return the localized entry that was removed
	 * @throws PortalException if a localized entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LocalizedEntry deleteLocalizedEntry(long localizedEntryId)
		throws PortalException {

		return localizedEntryPersistence.remove(localizedEntryId);
	}

	/**
	 * Deletes the localized entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LocalizedEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param localizedEntry the localized entry
	 * @return the localized entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LocalizedEntry deleteLocalizedEntry(LocalizedEntry localizedEntry) {
		return localizedEntryPersistence.remove(localizedEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return localizedEntryPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			LocalizedEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return localizedEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LocalizedEntryModelImpl</code>.
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

		return localizedEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LocalizedEntryModelImpl</code>.
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

		return localizedEntryPersistence.findWithDynamicQuery(
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
		return localizedEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return localizedEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LocalizedEntry fetchLocalizedEntry(long localizedEntryId) {
		return localizedEntryPersistence.fetchByPrimaryKey(localizedEntryId);
	}

	/**
	 * Returns the localized entry with the primary key.
	 *
	 * @param localizedEntryId the primary key of the localized entry
	 * @return the localized entry
	 * @throws PortalException if a localized entry with the primary key could not be found
	 */
	@Override
	public LocalizedEntry getLocalizedEntry(long localizedEntryId)
		throws PortalException {

		return localizedEntryPersistence.findByPrimaryKey(localizedEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(localizedEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LocalizedEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("localizedEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			localizedEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LocalizedEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"localizedEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(localizedEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LocalizedEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("localizedEntryId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return localizedEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return localizedEntryLocalService.deleteLocalizedEntry(
			(LocalizedEntry)persistedModel);
	}

	public BasePersistence<LocalizedEntry> getBasePersistence() {
		return localizedEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return localizedEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the localized entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.LocalizedEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of localized entries
	 * @param end the upper bound of the range of localized entries (not inclusive)
	 * @return the range of localized entries
	 */
	@Override
	public List<LocalizedEntry> getLocalizedEntries(int start, int end) {
		return localizedEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of localized entries.
	 *
	 * @return the number of localized entries
	 */
	@Override
	public int getLocalizedEntriesCount() {
		return localizedEntryPersistence.countAll();
	}

	/**
	 * Updates the localized entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LocalizedEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param localizedEntry the localized entry
	 * @return the localized entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LocalizedEntry updateLocalizedEntry(LocalizedEntry localizedEntry) {
		return localizedEntryPersistence.update(localizedEntry);
	}

	@Override
	public LocalizedEntryLocalization fetchLocalizedEntryLocalization(
		long localizedEntryId, String languageId) {

		return localizedEntryLocalizationPersistence.
			fetchByLocalizedEntryId_LanguageId(localizedEntryId, languageId);
	}

	@Override
	public LocalizedEntryLocalization getLocalizedEntryLocalization(
			long localizedEntryId, String languageId)
		throws PortalException {

		return localizedEntryLocalizationPersistence.
			findByLocalizedEntryId_LanguageId(localizedEntryId, languageId);
	}

	@Override
	public List<LocalizedEntryLocalization> getLocalizedEntryLocalizations(
		long localizedEntryId) {

		return localizedEntryLocalizationPersistence.findByLocalizedEntryId(
			localizedEntryId);
	}

	@Override
	public LocalizedEntryLocalization updateLocalizedEntryLocalization(
			LocalizedEntry localizedEntry, String languageId, String title,
			String content)
		throws PortalException {

		localizedEntry = localizedEntryPersistence.findByPrimaryKey(
			localizedEntry.getPrimaryKey());

		LocalizedEntryLocalization localizedEntryLocalization =
			localizedEntryLocalizationPersistence.
				fetchByLocalizedEntryId_LanguageId(
					localizedEntry.getLocalizedEntryId(), languageId);

		return _updateLocalizedEntryLocalization(
			localizedEntry, localizedEntryLocalization, languageId, title,
			content);
	}

	@Override
	public List<LocalizedEntryLocalization> updateLocalizedEntryLocalizations(
			LocalizedEntry localizedEntry, Map<String, String> titleMap,
			Map<String, String> contentMap)
		throws PortalException {

		localizedEntry = localizedEntryPersistence.findByPrimaryKey(
			localizedEntry.getPrimaryKey());

		Map<String, String[]> localizedValuesMap =
			new HashMap<String, String[]>();

		for (Map.Entry<String, String> entry : titleMap.entrySet()) {
			String languageId = entry.getKey();

			String[] localizedValues = localizedValuesMap.get(languageId);

			if (localizedValues == null) {
				localizedValues = new String[2];

				localizedValuesMap.put(languageId, localizedValues);
			}

			localizedValues[0] = entry.getValue();
		}

		for (Map.Entry<String, String> entry : contentMap.entrySet()) {
			String languageId = entry.getKey();

			String[] localizedValues = localizedValuesMap.get(languageId);

			if (localizedValues == null) {
				localizedValues = new String[2];

				localizedValuesMap.put(languageId, localizedValues);
			}

			localizedValues[1] = entry.getValue();
		}

		List<LocalizedEntryLocalization> localizedEntryLocalizations =
			new ArrayList<LocalizedEntryLocalization>(
				localizedValuesMap.size());

		for (LocalizedEntryLocalization localizedEntryLocalization :
				localizedEntryLocalizationPersistence.findByLocalizedEntryId(
					localizedEntry.getLocalizedEntryId())) {

			String[] localizedValues = localizedValuesMap.remove(
				localizedEntryLocalization.getLanguageId());

			if (localizedValues == null) {
				localizedEntryLocalizationPersistence.remove(
					localizedEntryLocalization);
			}
			else {
				localizedEntryLocalization.setTitle(localizedValues[0]);
				localizedEntryLocalization.setContent(localizedValues[1]);

				localizedEntryLocalizations.add(
					localizedEntryLocalizationPersistence.update(
						localizedEntryLocalization));
			}
		}

		long batchCounter =
			counterLocalService.increment(
				LocalizedEntryLocalization.class.getName(),
				localizedValuesMap.size()) - localizedValuesMap.size();

		for (Map.Entry<String, String[]> entry :
				localizedValuesMap.entrySet()) {

			String languageId = entry.getKey();
			String[] localizedValues = entry.getValue();

			LocalizedEntryLocalization localizedEntryLocalization =
				localizedEntryLocalizationPersistence.create(++batchCounter);

			localizedEntryLocalization.setLocalizedEntryId(
				localizedEntry.getLocalizedEntryId());

			localizedEntryLocalization.setLanguageId(languageId);

			localizedEntryLocalization.setTitle(localizedValues[0]);
			localizedEntryLocalization.setContent(localizedValues[1]);

			localizedEntryLocalizations.add(
				localizedEntryLocalizationPersistence.update(
					localizedEntryLocalization));
		}

		return localizedEntryLocalizations;
	}

	private LocalizedEntryLocalization _updateLocalizedEntryLocalization(
			LocalizedEntry localizedEntry,
			LocalizedEntryLocalization localizedEntryLocalization,
			String languageId, String title, String content)
		throws PortalException {

		if (localizedEntryLocalization == null) {
			long localizedEntryLocalizationId = counterLocalService.increment(
				LocalizedEntryLocalization.class.getName());

			localizedEntryLocalization =
				localizedEntryLocalizationPersistence.create(
					localizedEntryLocalizationId);

			localizedEntryLocalization.setLocalizedEntryId(
				localizedEntry.getLocalizedEntryId());
			localizedEntryLocalization.setLanguageId(languageId);
		}

		localizedEntryLocalization.setTitle(title);
		localizedEntryLocalization.setContent(content);

		return localizedEntryLocalizationPersistence.update(
			localizedEntryLocalization);
	}

	/**
	 * Returns the localized entry local service.
	 *
	 * @return the localized entry local service
	 */
	public LocalizedEntryLocalService getLocalizedEntryLocalService() {
		return localizedEntryLocalService;
	}

	/**
	 * Sets the localized entry local service.
	 *
	 * @param localizedEntryLocalService the localized entry local service
	 */
	public void setLocalizedEntryLocalService(
		LocalizedEntryLocalService localizedEntryLocalService) {

		this.localizedEntryLocalService = localizedEntryLocalService;
	}

	/**
	 * Returns the localized entry persistence.
	 *
	 * @return the localized entry persistence
	 */
	public LocalizedEntryPersistence getLocalizedEntryPersistence() {
		return localizedEntryPersistence;
	}

	/**
	 * Sets the localized entry persistence.
	 *
	 * @param localizedEntryPersistence the localized entry persistence
	 */
	public void setLocalizedEntryPersistence(
		LocalizedEntryPersistence localizedEntryPersistence) {

		this.localizedEntryPersistence = localizedEntryPersistence;
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
	 * Returns the localized entry localization persistence.
	 *
	 * @return the localized entry localization persistence
	 */
	public LocalizedEntryLocalizationPersistence
		getLocalizedEntryLocalizationPersistence() {

		return localizedEntryLocalizationPersistence;
	}

	/**
	 * Sets the localized entry localization persistence.
	 *
	 * @param localizedEntryLocalizationPersistence the localized entry localization persistence
	 */
	public void setLocalizedEntryLocalizationPersistence(
		LocalizedEntryLocalizationPersistence
			localizedEntryLocalizationPersistence) {

		this.localizedEntryLocalizationPersistence =
			localizedEntryLocalizationPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.tools.service.builder.test.model.LocalizedEntry",
			localizedEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.tools.service.builder.test.model.LocalizedEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LocalizedEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LocalizedEntry.class;
	}

	protected String getModelClassName() {
		return LocalizedEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = localizedEntryPersistence.getDataSource();

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

	@BeanReference(type = LocalizedEntryLocalService.class)
	protected LocalizedEntryLocalService localizedEntryLocalService;

	@BeanReference(type = LocalizedEntryPersistence.class)
	protected LocalizedEntryPersistence localizedEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = LocalizedEntryLocalizationPersistence.class)
	protected LocalizedEntryLocalizationPersistence
		localizedEntryLocalizationPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}