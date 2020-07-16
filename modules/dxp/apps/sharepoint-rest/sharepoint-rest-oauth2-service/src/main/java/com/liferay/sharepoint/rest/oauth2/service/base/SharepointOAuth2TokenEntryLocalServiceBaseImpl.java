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

package com.liferay.sharepoint.rest.oauth2.service.base;

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
import com.liferay.sharepoint.rest.oauth2.model.SharepointOAuth2TokenEntry;
import com.liferay.sharepoint.rest.oauth2.service.SharepointOAuth2TokenEntryLocalService;
import com.liferay.sharepoint.rest.oauth2.service.persistence.SharepointOAuth2TokenEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the sharepoint o auth2 token entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.sharepoint.rest.oauth2.service.impl.SharepointOAuth2TokenEntryLocalServiceImpl}.
 * </p>
 *
 * @author Adolfo Pérez
 * @see com.liferay.sharepoint.rest.oauth2.service.impl.SharepointOAuth2TokenEntryLocalServiceImpl
 * @generated
 */
public abstract class SharepointOAuth2TokenEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, SharepointOAuth2TokenEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SharepointOAuth2TokenEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.sharepoint.rest.oauth2.service.SharepointOAuth2TokenEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the sharepoint o auth2 token entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SharepointOAuth2TokenEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sharepointOAuth2TokenEntry the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SharepointOAuth2TokenEntry addSharepointOAuth2TokenEntry(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {

		sharepointOAuth2TokenEntry.setNew(true);

		return sharepointOAuth2TokenEntryPersistence.update(
			sharepointOAuth2TokenEntry);
	}

	/**
	 * Creates a new sharepoint o auth2 token entry with the primary key. Does not add the sharepoint o auth2 token entry to the database.
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key for the new sharepoint o auth2 token entry
	 * @return the new sharepoint o auth2 token entry
	 */
	@Override
	@Transactional(enabled = false)
	public SharepointOAuth2TokenEntry createSharepointOAuth2TokenEntry(
		long sharepointOAuth2TokenEntryId) {

		return sharepointOAuth2TokenEntryPersistence.create(
			sharepointOAuth2TokenEntryId);
	}

	/**
	 * Deletes the sharepoint o auth2 token entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SharepointOAuth2TokenEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry that was removed
	 * @throws PortalException if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SharepointOAuth2TokenEntry deleteSharepointOAuth2TokenEntry(
			long sharepointOAuth2TokenEntryId)
		throws PortalException {

		return sharepointOAuth2TokenEntryPersistence.remove(
			sharepointOAuth2TokenEntryId);
	}

	/**
	 * Deletes the sharepoint o auth2 token entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SharepointOAuth2TokenEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sharepointOAuth2TokenEntry the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SharepointOAuth2TokenEntry deleteSharepointOAuth2TokenEntry(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {

		return sharepointOAuth2TokenEntryPersistence.remove(
			sharepointOAuth2TokenEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			SharepointOAuth2TokenEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return sharepointOAuth2TokenEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sharepoint.rest.oauth2.model.impl.SharepointOAuth2TokenEntryModelImpl</code>.
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

		return sharepointOAuth2TokenEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sharepoint.rest.oauth2.model.impl.SharepointOAuth2TokenEntryModelImpl</code>.
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

		return sharepointOAuth2TokenEntryPersistence.findWithDynamicQuery(
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
		return sharepointOAuth2TokenEntryPersistence.countWithDynamicQuery(
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

		return sharepointOAuth2TokenEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SharepointOAuth2TokenEntry fetchSharepointOAuth2TokenEntry(
		long sharepointOAuth2TokenEntryId) {

		return sharepointOAuth2TokenEntryPersistence.fetchByPrimaryKey(
			sharepointOAuth2TokenEntryId);
	}

	/**
	 * Returns the sharepoint o auth2 token entry with the primary key.
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry
	 * @throws PortalException if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry getSharepointOAuth2TokenEntry(
			long sharepointOAuth2TokenEntryId)
		throws PortalException {

		return sharepointOAuth2TokenEntryPersistence.findByPrimaryKey(
			sharepointOAuth2TokenEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			sharepointOAuth2TokenEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SharepointOAuth2TokenEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"sharepointOAuth2TokenEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			sharepointOAuth2TokenEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			SharepointOAuth2TokenEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"sharepointOAuth2TokenEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			sharepointOAuth2TokenEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SharepointOAuth2TokenEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"sharepointOAuth2TokenEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return sharepointOAuth2TokenEntryLocalService.
			deleteSharepointOAuth2TokenEntry(
				(SharepointOAuth2TokenEntry)persistedModel);
	}

	public BasePersistence<SharepointOAuth2TokenEntry> getBasePersistence() {
		return sharepointOAuth2TokenEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return sharepointOAuth2TokenEntryPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the sharepoint o auth2 token entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.sharepoint.rest.oauth2.model.impl.SharepointOAuth2TokenEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sharepoint o auth2 token entries
	 * @param end the upper bound of the range of sharepoint o auth2 token entries (not inclusive)
	 * @return the range of sharepoint o auth2 token entries
	 */
	@Override
	public List<SharepointOAuth2TokenEntry> getSharepointOAuth2TokenEntries(
		int start, int end) {

		return sharepointOAuth2TokenEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of sharepoint o auth2 token entries.
	 *
	 * @return the number of sharepoint o auth2 token entries
	 */
	@Override
	public int getSharepointOAuth2TokenEntriesCount() {
		return sharepointOAuth2TokenEntryPersistence.countAll();
	}

	/**
	 * Updates the sharepoint o auth2 token entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect SharepointOAuth2TokenEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sharepointOAuth2TokenEntry the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SharepointOAuth2TokenEntry updateSharepointOAuth2TokenEntry(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {

		return sharepointOAuth2TokenEntryPersistence.update(
			sharepointOAuth2TokenEntry);
	}

	/**
	 * Returns the sharepoint o auth2 token entry local service.
	 *
	 * @return the sharepoint o auth2 token entry local service
	 */
	public SharepointOAuth2TokenEntryLocalService
		getSharepointOAuth2TokenEntryLocalService() {

		return sharepointOAuth2TokenEntryLocalService;
	}

	/**
	 * Sets the sharepoint o auth2 token entry local service.
	 *
	 * @param sharepointOAuth2TokenEntryLocalService the sharepoint o auth2 token entry local service
	 */
	public void setSharepointOAuth2TokenEntryLocalService(
		SharepointOAuth2TokenEntryLocalService
			sharepointOAuth2TokenEntryLocalService) {

		this.sharepointOAuth2TokenEntryLocalService =
			sharepointOAuth2TokenEntryLocalService;
	}

	/**
	 * Returns the sharepoint o auth2 token entry persistence.
	 *
	 * @return the sharepoint o auth2 token entry persistence
	 */
	public SharepointOAuth2TokenEntryPersistence
		getSharepointOAuth2TokenEntryPersistence() {

		return sharepointOAuth2TokenEntryPersistence;
	}

	/**
	 * Sets the sharepoint o auth2 token entry persistence.
	 *
	 * @param sharepointOAuth2TokenEntryPersistence the sharepoint o auth2 token entry persistence
	 */
	public void setSharepointOAuth2TokenEntryPersistence(
		SharepointOAuth2TokenEntryPersistence
			sharepointOAuth2TokenEntryPersistence) {

		this.sharepointOAuth2TokenEntryPersistence =
			sharepointOAuth2TokenEntryPersistence;
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
			"com.liferay.sharepoint.rest.oauth2.model.SharepointOAuth2TokenEntry",
			sharepointOAuth2TokenEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.sharepoint.rest.oauth2.model.SharepointOAuth2TokenEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SharepointOAuth2TokenEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SharepointOAuth2TokenEntry.class;
	}

	protected String getModelClassName() {
		return SharepointOAuth2TokenEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				sharepointOAuth2TokenEntryPersistence.getDataSource();

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

	@BeanReference(type = SharepointOAuth2TokenEntryLocalService.class)
	protected SharepointOAuth2TokenEntryLocalService
		sharepointOAuth2TokenEntryLocalService;

	@BeanReference(type = SharepointOAuth2TokenEntryPersistence.class)
	protected SharepointOAuth2TokenEntryPersistence
		sharepointOAuth2TokenEntryPersistence;

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