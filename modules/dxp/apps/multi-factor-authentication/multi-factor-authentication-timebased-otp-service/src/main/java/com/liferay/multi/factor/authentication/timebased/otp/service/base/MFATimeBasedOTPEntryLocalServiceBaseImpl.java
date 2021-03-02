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

package com.liferay.multi.factor.authentication.timebased.otp.service.base;

import com.liferay.multi.factor.authentication.timebased.otp.model.MFATimeBasedOTPEntry;
import com.liferay.multi.factor.authentication.timebased.otp.service.MFATimeBasedOTPEntryLocalService;
import com.liferay.multi.factor.authentication.timebased.otp.service.MFATimeBasedOTPEntryLocalServiceUtil;
import com.liferay.multi.factor.authentication.timebased.otp.service.persistence.MFATimeBasedOTPEntryPersistence;
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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
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
 * Provides the base implementation for the mfa time based otp entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.multi.factor.authentication.timebased.otp.service.impl.MFATimeBasedOTPEntryLocalServiceImpl}.
 * </p>
 *
 * @author Arthur Chan
 * @see com.liferay.multi.factor.authentication.timebased.otp.service.impl.MFATimeBasedOTPEntryLocalServiceImpl
 * @generated
 */
public abstract class MFATimeBasedOTPEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   MFATimeBasedOTPEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MFATimeBasedOTPEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MFATimeBasedOTPEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the mfa time based otp entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFATimeBasedOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaTimeBasedOTPEntry the mfa time based otp entry
	 * @return the mfa time based otp entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MFATimeBasedOTPEntry addMFATimeBasedOTPEntry(
		MFATimeBasedOTPEntry mfaTimeBasedOTPEntry) {

		mfaTimeBasedOTPEntry.setNew(true);

		return mfaTimeBasedOTPEntryPersistence.update(mfaTimeBasedOTPEntry);
	}

	/**
	 * Creates a new mfa time based otp entry with the primary key. Does not add the mfa time based otp entry to the database.
	 *
	 * @param mfaTimeBasedOTPEntryId the primary key for the new mfa time based otp entry
	 * @return the new mfa time based otp entry
	 */
	@Override
	@Transactional(enabled = false)
	public MFATimeBasedOTPEntry createMFATimeBasedOTPEntry(
		long mfaTimeBasedOTPEntryId) {

		return mfaTimeBasedOTPEntryPersistence.create(mfaTimeBasedOTPEntryId);
	}

	/**
	 * Deletes the mfa time based otp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFATimeBasedOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaTimeBasedOTPEntryId the primary key of the mfa time based otp entry
	 * @return the mfa time based otp entry that was removed
	 * @throws PortalException if a mfa time based otp entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MFATimeBasedOTPEntry deleteMFATimeBasedOTPEntry(
			long mfaTimeBasedOTPEntryId)
		throws PortalException {

		return mfaTimeBasedOTPEntryPersistence.remove(mfaTimeBasedOTPEntryId);
	}

	/**
	 * Deletes the mfa time based otp entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFATimeBasedOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaTimeBasedOTPEntry the mfa time based otp entry
	 * @return the mfa time based otp entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MFATimeBasedOTPEntry deleteMFATimeBasedOTPEntry(
		MFATimeBasedOTPEntry mfaTimeBasedOTPEntry) {

		return mfaTimeBasedOTPEntryPersistence.remove(mfaTimeBasedOTPEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return mfaTimeBasedOTPEntryPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			MFATimeBasedOTPEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return mfaTimeBasedOTPEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.timebased.otp.model.impl.MFATimeBasedOTPEntryModelImpl</code>.
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

		return mfaTimeBasedOTPEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.timebased.otp.model.impl.MFATimeBasedOTPEntryModelImpl</code>.
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

		return mfaTimeBasedOTPEntryPersistence.findWithDynamicQuery(
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
		return mfaTimeBasedOTPEntryPersistence.countWithDynamicQuery(
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

		return mfaTimeBasedOTPEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MFATimeBasedOTPEntry fetchMFATimeBasedOTPEntry(
		long mfaTimeBasedOTPEntryId) {

		return mfaTimeBasedOTPEntryPersistence.fetchByPrimaryKey(
			mfaTimeBasedOTPEntryId);
	}

	/**
	 * Returns the mfa time based otp entry with the primary key.
	 *
	 * @param mfaTimeBasedOTPEntryId the primary key of the mfa time based otp entry
	 * @return the mfa time based otp entry
	 * @throws PortalException if a mfa time based otp entry with the primary key could not be found
	 */
	@Override
	public MFATimeBasedOTPEntry getMFATimeBasedOTPEntry(
			long mfaTimeBasedOTPEntryId)
		throws PortalException {

		return mfaTimeBasedOTPEntryPersistence.findByPrimaryKey(
			mfaTimeBasedOTPEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			mfaTimeBasedOTPEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MFATimeBasedOTPEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaTimeBasedOTPEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			mfaTimeBasedOTPEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			MFATimeBasedOTPEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaTimeBasedOTPEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			mfaTimeBasedOTPEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MFATimeBasedOTPEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaTimeBasedOTPEntryId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mfaTimeBasedOTPEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return mfaTimeBasedOTPEntryLocalService.deleteMFATimeBasedOTPEntry(
			(MFATimeBasedOTPEntry)persistedModel);
	}

	public BasePersistence<MFATimeBasedOTPEntry> getBasePersistence() {
		return mfaTimeBasedOTPEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mfaTimeBasedOTPEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the mfa time based otp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.timebased.otp.model.impl.MFATimeBasedOTPEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfa time based otp entries
	 * @param end the upper bound of the range of mfa time based otp entries (not inclusive)
	 * @return the range of mfa time based otp entries
	 */
	@Override
	public List<MFATimeBasedOTPEntry> getMFATimeBasedOTPEntries(
		int start, int end) {

		return mfaTimeBasedOTPEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of mfa time based otp entries.
	 *
	 * @return the number of mfa time based otp entries
	 */
	@Override
	public int getMFATimeBasedOTPEntriesCount() {
		return mfaTimeBasedOTPEntryPersistence.countAll();
	}

	/**
	 * Updates the mfa time based otp entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFATimeBasedOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaTimeBasedOTPEntry the mfa time based otp entry
	 * @return the mfa time based otp entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MFATimeBasedOTPEntry updateMFATimeBasedOTPEntry(
		MFATimeBasedOTPEntry mfaTimeBasedOTPEntry) {

		return mfaTimeBasedOTPEntryPersistence.update(mfaTimeBasedOTPEntry);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MFATimeBasedOTPEntryLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		mfaTimeBasedOTPEntryLocalService =
			(MFATimeBasedOTPEntryLocalService)aopProxy;

		_setLocalServiceUtilService(mfaTimeBasedOTPEntryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MFATimeBasedOTPEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MFATimeBasedOTPEntry.class;
	}

	protected String getModelClassName() {
		return MFATimeBasedOTPEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				mfaTimeBasedOTPEntryPersistence.getDataSource();

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
		MFATimeBasedOTPEntryLocalService mfaTimeBasedOTPEntryLocalService) {

		try {
			Field field =
				MFATimeBasedOTPEntryLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, mfaTimeBasedOTPEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected MFATimeBasedOTPEntryLocalService mfaTimeBasedOTPEntryLocalService;

	@Reference
	protected MFATimeBasedOTPEntryPersistence mfaTimeBasedOTPEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}