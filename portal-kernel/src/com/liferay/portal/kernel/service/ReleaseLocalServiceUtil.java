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

package com.liferay.portal.kernel.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Release. This utility wraps
 * <code>com.liferay.portal.service.impl.ReleaseLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseLocalService
 * @generated
 */
public class ReleaseLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.ReleaseLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the release to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param release the release
	 * @return the release that was added
	 */
	public static Release addRelease(Release release) {
		return getService().addRelease(release);
	}

	public static Release addRelease(
		String servletContextName, int buildNumber) {

		return getService().addRelease(servletContextName, buildNumber);
	}

	public static Release addRelease(
		String servletContextName, String schemaVersion) {

		return getService().addRelease(servletContextName, schemaVersion);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new release with the primary key. Does not add the release to the database.
	 *
	 * @param releaseId the primary key for the new release
	 * @return the new release
	 */
	public static Release createRelease(long releaseId) {
		return getService().createRelease(releaseId);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void createTablesAndPopulate() {
		getService().createTablesAndPopulate();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the release with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param releaseId the primary key of the release
	 * @return the release that was removed
	 * @throws PortalException if a release with the primary key could not be found
	 */
	public static Release deleteRelease(long releaseId) throws PortalException {
		return getService().deleteRelease(releaseId);
	}

	/**
	 * Deletes the release from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param release the release
	 * @return the release that was removed
	 */
	public static Release deleteRelease(Release release) {
		return getService().deleteRelease(release);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ReleaseModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ReleaseModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Release fetchRelease(long releaseId) {
		return getService().fetchRelease(releaseId);
	}

	public static Release fetchRelease(String servletContextName) {
		return getService().fetchRelease(servletContextName);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static int getBuildNumberOrCreate() throws PortalException {
		return getService().getBuildNumberOrCreate();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the release with the primary key.
	 *
	 * @param releaseId the primary key of the release
	 * @return the release
	 * @throws PortalException if a release with the primary key could not be found
	 */
	public static Release getRelease(long releaseId) throws PortalException {
		return getService().getRelease(releaseId);
	}

	/**
	 * Returns a range of all the releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ReleaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of releases
	 * @param end the upper bound of the range of releases (not inclusive)
	 * @return the range of releases
	 */
	public static List<Release> getReleases(int start, int end) {
		return getService().getReleases(start, end);
	}

	/**
	 * Returns the number of releases.
	 *
	 * @return the number of releases
	 */
	public static int getReleasesCount() {
		return getService().getReleasesCount();
	}

	public static Release updateRelease(
			long releaseId, String schemaVersion, int buildNumber,
			java.util.Date buildDate, boolean verified)
		throws PortalException {

		return getService().updateRelease(
			releaseId, schemaVersion, buildNumber, buildDate, verified);
	}

	/**
	 * Updates the release in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ReleaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param release the release
	 * @return the release that was updated
	 */
	public static Release updateRelease(Release release) {
		return getService().updateRelease(release);
	}

	public static void updateRelease(
			String servletContextName,
			List<com.liferay.portal.kernel.upgrade.UpgradeProcess>
				upgradeProcesses,
			int buildNumber, int previousBuildNumber)
		throws PortalException {

		getService().updateRelease(
			servletContextName, upgradeProcesses, buildNumber,
			previousBuildNumber);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 #updateRelease(String, List, int, int)}
	 */
	@Deprecated
	public static void updateRelease(
			String servletContextName,
			List<com.liferay.portal.kernel.upgrade.UpgradeProcess>
				upgradeProcesses,
			int buildNumber, int previousBuildNumber, boolean indexOnUpgrade)
		throws PortalException {

		getService().updateRelease(
			servletContextName, upgradeProcesses, buildNumber,
			previousBuildNumber, indexOnUpgrade);
	}

	public static void updateRelease(
			String servletContextName,
			List<com.liferay.portal.kernel.upgrade.UpgradeProcess>
				upgradeProcesses,
			java.util.Properties unfilteredPortalProperties)
		throws Exception {

		getService().updateRelease(
			servletContextName, upgradeProcesses, unfilteredPortalProperties);
	}

	public static void updateRelease(
		String servletContextName, String schemaVersion,
		String previousSchemaVersion) {

		getService().updateRelease(
			servletContextName, schemaVersion, previousSchemaVersion);
	}

	public static ReleaseLocalService getService() {
		return _service;
	}

	private static volatile ReleaseLocalService _service;

}