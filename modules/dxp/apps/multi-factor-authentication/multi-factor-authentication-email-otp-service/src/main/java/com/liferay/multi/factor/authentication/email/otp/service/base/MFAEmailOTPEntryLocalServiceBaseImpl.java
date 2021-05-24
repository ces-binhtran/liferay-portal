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

package com.liferay.multi.factor.authentication.email.otp.service.base;

import com.liferay.multi.factor.authentication.email.otp.model.MFAEmailOTPEntry;
import com.liferay.multi.factor.authentication.email.otp.service.MFAEmailOTPEntryLocalService;
import com.liferay.multi.factor.authentication.email.otp.service.MFAEmailOTPEntryLocalServiceUtil;
import com.liferay.multi.factor.authentication.email.otp.service.persistence.MFAEmailOTPEntryPersistence;
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
 * Provides the base implementation for the mfa email otp entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.multi.factor.authentication.email.otp.service.impl.MFAEmailOTPEntryLocalServiceImpl}.
 * </p>
 *
 * @author Arthur Chan
 * @see com.liferay.multi.factor.authentication.email.otp.service.impl.MFAEmailOTPEntryLocalServiceImpl
 * @generated
 */
public abstract class MFAEmailOTPEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   MFAEmailOTPEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MFAEmailOTPEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>MFAEmailOTPEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the mfa email otp entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAEmailOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaEmailOTPEntry the mfa email otp entry
	 * @return the mfa email otp entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MFAEmailOTPEntry addMFAEmailOTPEntry(
		MFAEmailOTPEntry mfaEmailOTPEntry) {

		mfaEmailOTPEntry.setNew(true);

		return mfaEmailOTPEntryPersistence.update(mfaEmailOTPEntry);
	}

	/**
	 * Creates a new mfa email otp entry with the primary key. Does not add the mfa email otp entry to the database.
	 *
	 * @param mfaEmailOTPEntryId the primary key for the new mfa email otp entry
	 * @return the new mfa email otp entry
	 */
	@Override
	@Transactional(enabled = false)
	public MFAEmailOTPEntry createMFAEmailOTPEntry(long mfaEmailOTPEntryId) {
		return mfaEmailOTPEntryPersistence.create(mfaEmailOTPEntryId);
	}

	/**
	 * Deletes the mfa email otp entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAEmailOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaEmailOTPEntryId the primary key of the mfa email otp entry
	 * @return the mfa email otp entry that was removed
	 * @throws PortalException if a mfa email otp entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MFAEmailOTPEntry deleteMFAEmailOTPEntry(long mfaEmailOTPEntryId)
		throws PortalException {

		return mfaEmailOTPEntryPersistence.remove(mfaEmailOTPEntryId);
	}

	/**
	 * Deletes the mfa email otp entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAEmailOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaEmailOTPEntry the mfa email otp entry
	 * @return the mfa email otp entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MFAEmailOTPEntry deleteMFAEmailOTPEntry(
		MFAEmailOTPEntry mfaEmailOTPEntry) {

		return mfaEmailOTPEntryPersistence.remove(mfaEmailOTPEntry);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return mfaEmailOTPEntryPersistence.dslQuery(dslQuery);
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
			MFAEmailOTPEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return mfaEmailOTPEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.email.otp.model.impl.MFAEmailOTPEntryModelImpl</code>.
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

		return mfaEmailOTPEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.email.otp.model.impl.MFAEmailOTPEntryModelImpl</code>.
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

		return mfaEmailOTPEntryPersistence.findWithDynamicQuery(
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
		return mfaEmailOTPEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return mfaEmailOTPEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MFAEmailOTPEntry fetchMFAEmailOTPEntry(long mfaEmailOTPEntryId) {
		return mfaEmailOTPEntryPersistence.fetchByPrimaryKey(
			mfaEmailOTPEntryId);
	}

	/**
	 * Returns the mfa email otp entry with the primary key.
	 *
	 * @param mfaEmailOTPEntryId the primary key of the mfa email otp entry
	 * @return the mfa email otp entry
	 * @throws PortalException if a mfa email otp entry with the primary key could not be found
	 */
	@Override
	public MFAEmailOTPEntry getMFAEmailOTPEntry(long mfaEmailOTPEntryId)
		throws PortalException {

		return mfaEmailOTPEntryPersistence.findByPrimaryKey(mfaEmailOTPEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			mfaEmailOTPEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MFAEmailOTPEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("mfaEmailOTPEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			mfaEmailOTPEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MFAEmailOTPEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"mfaEmailOTPEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			mfaEmailOTPEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MFAEmailOTPEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("mfaEmailOTPEntryId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mfaEmailOTPEntryPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return mfaEmailOTPEntryLocalService.deleteMFAEmailOTPEntry(
			(MFAEmailOTPEntry)persistedModel);
	}

	public BasePersistence<MFAEmailOTPEntry> getBasePersistence() {
		return mfaEmailOTPEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mfaEmailOTPEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the mfa email otp entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.multi.factor.authentication.email.otp.model.impl.MFAEmailOTPEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mfa email otp entries
	 * @param end the upper bound of the range of mfa email otp entries (not inclusive)
	 * @return the range of mfa email otp entries
	 */
	@Override
	public List<MFAEmailOTPEntry> getMFAEmailOTPEntries(int start, int end) {
		return mfaEmailOTPEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of mfa email otp entries.
	 *
	 * @return the number of mfa email otp entries
	 */
	@Override
	public int getMFAEmailOTPEntriesCount() {
		return mfaEmailOTPEntryPersistence.countAll();
	}

	/**
	 * Updates the mfa email otp entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MFAEmailOTPEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param mfaEmailOTPEntry the mfa email otp entry
	 * @return the mfa email otp entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MFAEmailOTPEntry updateMFAEmailOTPEntry(
		MFAEmailOTPEntry mfaEmailOTPEntry) {

		return mfaEmailOTPEntryPersistence.update(mfaEmailOTPEntry);
	}

	@Deactivate
	protected void deactivate() {
		_setLocalServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			MFAEmailOTPEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		mfaEmailOTPEntryLocalService = (MFAEmailOTPEntryLocalService)aopProxy;

		_setLocalServiceUtilService(mfaEmailOTPEntryLocalService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MFAEmailOTPEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MFAEmailOTPEntry.class;
	}

	protected String getModelClassName() {
		return MFAEmailOTPEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mfaEmailOTPEntryPersistence.getDataSource();

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
		MFAEmailOTPEntryLocalService mfaEmailOTPEntryLocalService) {

		try {
			Field field =
				MFAEmailOTPEntryLocalServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, mfaEmailOTPEntryLocalService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	protected MFAEmailOTPEntryLocalService mfaEmailOTPEntryLocalService;

	@Reference
	protected MFAEmailOTPEntryPersistence mfaEmailOTPEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}