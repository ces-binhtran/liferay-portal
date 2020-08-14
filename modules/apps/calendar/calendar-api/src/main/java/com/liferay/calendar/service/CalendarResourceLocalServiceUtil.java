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

package com.liferay.calendar.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CalendarResource. This utility wraps
 * <code>com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceLocalService
 * @generated
 */
public class CalendarResourceLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the calendar resource to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarResourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was added
	 */
	public static com.liferay.calendar.model.CalendarResource
		addCalendarResource(
			com.liferay.calendar.model.CalendarResource calendarResource) {

		return getService().addCalendarResource(calendarResource);
	}

	public static com.liferay.calendar.model.CalendarResource
			addCalendarResource(
				long userId, long groupId, long classNameId, long classPK,
				String classUuid, String code,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCalendarResource(
			userId, groupId, classNameId, classPK, classUuid, code, nameMap,
			descriptionMap, active, serviceContext);
	}

	/**
	 * Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	 *
	 * @param calendarResourceId the primary key for the new calendar resource
	 * @return the new calendar resource
	 */
	public static com.liferay.calendar.model.CalendarResource
		createCalendarResource(long calendarResourceId) {

		return getService().createCalendarResource(calendarResourceId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the calendar resource from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarResourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was removed
	 * @throws PortalException
	 */
	public static com.liferay.calendar.model.CalendarResource
			deleteCalendarResource(
				com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCalendarResource(calendarResource);
	}

	/**
	 * Deletes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarResourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource that was removed
	 * @throws PortalException if a calendar resource with the primary key could not be found
	 */
	public static com.liferay.calendar.model.CalendarResource
			deleteCalendarResource(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCalendarResource(calendarResourceId);
	}

	public static void deleteCalendarResources(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCalendarResources(groupId);
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

	public static <T> T dslQuery(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return getService().dslQuery(dslQuery);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarResourceModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarResourceModelImpl</code>.
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

	public static com.liferay.calendar.model.CalendarResource
		fetchCalendarResource(long calendarResourceId) {

		return getService().fetchCalendarResource(calendarResourceId);
	}

	public static com.liferay.calendar.model.CalendarResource
		fetchCalendarResource(long classNameId, long classPK) {

		return getService().fetchCalendarResource(classNameId, classPK);
	}

	public static com.liferay.calendar.model.CalendarResource
		fetchCalendarResource(long groupId, String code) {

		return getService().fetchCalendarResource(groupId, code);
	}

	/**
	 * Returns the calendar resource matching the UUID and group.
	 *
	 * @param uuid the calendar resource's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	 */
	public static com.liferay.calendar.model.CalendarResource
		fetchCalendarResourceByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchCalendarResourceByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the calendar resource with the primary key.
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource
	 * @throws PortalException if a calendar resource with the primary key could not be found
	 */
	public static com.liferay.calendar.model.CalendarResource
			getCalendarResource(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendarResource(calendarResourceId);
	}

	/**
	 * Returns the calendar resource matching the UUID and group.
	 *
	 * @param uuid the calendar resource's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar resource
	 * @throws PortalException if a matching calendar resource could not be found
	 */
	public static com.liferay.calendar.model.CalendarResource
			getCalendarResourceByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendarResourceByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the calendar resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarResourceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of calendar resources
	 */
	public static java.util.List<com.liferay.calendar.model.CalendarResource>
		getCalendarResources(int start, int end) {

		return getService().getCalendarResources(start, end);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource>
		getCalendarResources(long groupId) {

		return getService().getCalendarResources(groupId);
	}

	/**
	 * Returns all the calendar resources matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar resources
	 * @param companyId the primary key of the company
	 * @return the matching calendar resources, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.calendar.model.CalendarResource>
		getCalendarResourcesByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getCalendarResourcesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of calendar resources matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendar resources
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching calendar resources, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.calendar.model.CalendarResource>
		getCalendarResourcesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.calendar.model.CalendarResource>
					orderByComparator) {

		return getService().getCalendarResourcesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of calendar resources.
	 *
	 * @return the number of calendar resources
	 */
	public static int getCalendarResourcesCount() {
		return getService().getCalendarResourcesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource>
		search(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, boolean active,
			boolean andOperator, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.calendar.model.CalendarResource>
					orderByComparator) {

		return getService().search(
			companyId, groupIds, classNameIds, code, name, description, active,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource>
		searchByKeywords(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active, boolean andOperator, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.calendar.model.CalendarResource>
					orderByComparator) {

		return getService().searchByKeywords(
			companyId, groupIds, classNameIds, keywords, active, andOperator,
			start, end, orderByComparator);
	}

	public static int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active) {

		return getService().searchCount(
			companyId, groupIds, classNameIds, keywords, active);
	}

	public static int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator) {

		return getService().searchCount(
			companyId, groupIds, classNameIds, code, name, description, active,
			andOperator);
	}

	public static void updateAsset(
			long userId,
			com.liferay.calendar.model.CalendarResource calendarResource,
			long[] assetCategoryIds, String[] assetTagNames, Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateAsset(
			userId, calendarResource, assetCategoryIds, assetTagNames,
			priority);
	}

	/**
	 * Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarResourceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was updated
	 */
	public static com.liferay.calendar.model.CalendarResource
		updateCalendarResource(
			com.liferay.calendar.model.CalendarResource calendarResource) {

		return getService().updateCalendarResource(calendarResource);
	}

	public static com.liferay.calendar.model.CalendarResource
			updateCalendarResource(
				long calendarResourceId,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCalendarResource(
			calendarResourceId, nameMap, descriptionMap, active,
			serviceContext);
	}

	public static CalendarResourceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CalendarResourceLocalService, CalendarResourceLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CalendarResourceLocalService.class);

		ServiceTracker
			<CalendarResourceLocalService, CalendarResourceLocalService>
				serviceTracker =
					new ServiceTracker
						<CalendarResourceLocalService,
						 CalendarResourceLocalService>(
							 bundle.getBundleContext(),
							 CalendarResourceLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}