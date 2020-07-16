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

package com.liferay.portal.workflow.kaleo.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for KaleoTimerInstanceToken. This utility wraps
 * <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerInstanceTokenLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenLocalService
 * @generated
 */
public class KaleoTimerInstanceTokenLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoTimerInstanceTokenLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the kaleo timer instance token to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTimerInstanceTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was added
	 */
	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
			addKaleoTimerInstanceToken(
				com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
					kaleoTimerInstanceToken) {

		return getService().addKaleoTimerInstanceToken(kaleoTimerInstanceToken);
	}

	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
				addKaleoTimerInstanceToken(
					long kaleoInstanceTokenId, long kaleoTaskInstanceTokenId,
					long kaleoTimerId, String kaleoTimerName,
					java.util.Map<String, java.io.Serializable> workflowContext,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addKaleoTimerInstanceToken(
			kaleoInstanceTokenId, kaleoTaskInstanceTokenId, kaleoTimerId,
			kaleoTimerName, workflowContext, serviceContext);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken>
				addKaleoTimerInstanceTokens(
					com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken
						kaleoInstanceToken,
					com.liferay.portal.workflow.kaleo.model.
						KaleoTaskInstanceToken kaleoTaskInstanceToken,
					java.util.Collection
						<com.liferay.portal.workflow.kaleo.model.KaleoTimer>
							kaleoTimers,
					java.util.Map<String, java.io.Serializable> workflowContext,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addKaleoTimerInstanceTokens(
			kaleoInstanceToken, kaleoTaskInstanceToken, kaleoTimers,
			workflowContext, serviceContext);
	}

	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
				completeKaleoTimerInstanceToken(
					long kaleoTimerInstanceTokenId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().completeKaleoTimerInstanceToken(
			kaleoTimerInstanceTokenId, serviceContext);
	}

	public static void completeKaleoTimerInstanceTokens(
			java.util.List
				<com.liferay.portal.workflow.kaleo.model.
					KaleoTimerInstanceToken> kaleoTimerInstanceTokens,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().completeKaleoTimerInstanceTokens(
			kaleoTimerInstanceTokens, serviceContext);
	}

	public static void completeKaleoTimerInstanceTokens(
			long kaleoInstanceTokenId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().completeKaleoTimerInstanceTokens(
			kaleoInstanceTokenId, serviceContext);
	}

	/**
	 * Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	 * @return the new kaleo timer instance token
	 */
	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
			createKaleoTimerInstanceToken(long kaleoTimerInstanceTokenId) {

		return getService().createKaleoTimerInstanceToken(
			kaleoTimerInstanceTokenId);
	}

	/**
	 * Deletes the kaleo timer instance token from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTimerInstanceTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 */
	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
			deleteKaleoTimerInstanceToken(
				com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
					kaleoTimerInstanceToken) {

		return getService().deleteKaleoTimerInstanceToken(
			kaleoTimerInstanceToken);
	}

	/**
	 * Deletes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTimerInstanceTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws PortalException if a kaleo timer instance token with the primary key could not be found
	 */
	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
				deleteKaleoTimerInstanceToken(long kaleoTimerInstanceTokenId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteKaleoTimerInstanceToken(
			kaleoTimerInstanceTokenId);
	}

	public static void deleteKaleoTimerInstanceToken(
			long kaleoInstanceTokenId, long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteKaleoTimerInstanceToken(
			kaleoInstanceTokenId, kaleoTimerId);
	}

	public static void deleteKaleoTimerInstanceTokens(long kaleoInstanceId) {
		getService().deleteKaleoTimerInstanceTokens(kaleoInstanceId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
			fetchKaleoTimerInstanceToken(long kaleoTimerInstanceTokenId) {

		return getService().fetchKaleoTimerInstanceToken(
			kaleoTimerInstanceTokenId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kaleo timer instance token with the primary key.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token
	 * @throws PortalException if a kaleo timer instance token with the primary key could not be found
	 */
	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
				getKaleoTimerInstanceToken(long kaleoTimerInstanceTokenId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getKaleoTimerInstanceToken(
			kaleoTimerInstanceTokenId);
	}

	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
				getKaleoTimerInstanceToken(
					long kaleoInstanceTokenId, long kaleoTimerId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getKaleoTimerInstanceToken(
			kaleoInstanceTokenId, kaleoTimerId);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of kaleo timer instance tokens
	 */
	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken>
			getKaleoTimerInstanceTokens(int start, int end) {

		return getService().getKaleoTimerInstanceTokens(start, end);
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken>
			getKaleoTimerInstanceTokens(
				long kaleoInstanceTokenId, boolean blocking, boolean completed,
				com.liferay.portal.kernel.service.ServiceContext
					serviceContext) {

		return getService().getKaleoTimerInstanceTokens(
			kaleoInstanceTokenId, blocking, completed, serviceContext);
	}

	/**
	 * Returns the number of kaleo timer instance tokens.
	 *
	 * @return the number of kaleo timer instance tokens
	 */
	public static int getKaleoTimerInstanceTokensCount() {
		return getService().getKaleoTimerInstanceTokensCount();
	}

	public static int getKaleoTimerInstanceTokensCount(
		long kaleoInstanceTokenId, boolean blocking, boolean completed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoTimerInstanceTokensCount(
			kaleoInstanceTokenId, blocking, completed, serviceContext);
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
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the kaleo timer instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTimerInstanceTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was updated
	 */
	public static
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
			updateKaleoTimerInstanceToken(
				com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken
					kaleoTimerInstanceToken) {

		return getService().updateKaleoTimerInstanceToken(
			kaleoTimerInstanceToken);
	}

	public static KaleoTimerInstanceTokenLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<KaleoTimerInstanceTokenLocalService,
		 KaleoTimerInstanceTokenLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			KaleoTimerInstanceTokenLocalService.class);

		ServiceTracker
			<KaleoTimerInstanceTokenLocalService,
			 KaleoTimerInstanceTokenLocalService> serviceTracker =
				new ServiceTracker
					<KaleoTimerInstanceTokenLocalService,
					 KaleoTimerInstanceTokenLocalService>(
						 bundle.getBundleContext(),
						 KaleoTimerInstanceTokenLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}