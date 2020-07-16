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
 * Provides the local service utility for Calendar. This utility wraps
 * <code>com.liferay.calendar.service.impl.CalendarLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarLocalService
 * @generated
 */
public class CalendarLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.calendar.service.impl.CalendarLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the calendar to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendar the calendar
	 * @return the calendar that was added
	 */
	public static com.liferay.calendar.model.Calendar addCalendar(
		com.liferay.calendar.model.Calendar calendar) {

		return getService().addCalendar(calendar);
	}

	public static com.liferay.calendar.model.Calendar addCalendar(
			long userId, long groupId, long calendarResourceId,
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			String timeZoneId, int color, boolean defaultCalendar,
			boolean enableComments, boolean enableRatings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCalendar(
			userId, groupId, calendarResourceId, nameMap, descriptionMap,
			timeZoneId, color, defaultCalendar, enableComments, enableRatings,
			serviceContext);
	}

	/**
	 * Creates a new calendar with the primary key. Does not add the calendar to the database.
	 *
	 * @param calendarId the primary key for the new calendar
	 * @return the new calendar
	 */
	public static com.liferay.calendar.model.Calendar createCalendar(
		long calendarId) {

		return getService().createCalendar(calendarId);
	}

	/**
	 * Deletes the calendar from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendar the calendar
	 * @return the calendar that was removed
	 * @throws PortalException
	 */
	public static com.liferay.calendar.model.Calendar deleteCalendar(
			com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCalendar(calendar);
	}

	/**
	 * Deletes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws PortalException if a calendar with the primary key could not be found
	 */
	public static com.liferay.calendar.model.Calendar deleteCalendar(
			long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCalendar(calendarId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarModelImpl</code>.
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

	public static String exportCalendar(long calendarId, String type)
		throws Exception {

		return getService().exportCalendar(calendarId, type);
	}

	public static com.liferay.calendar.model.Calendar fetchCalendar(
		long calendarId) {

		return getService().fetchCalendar(calendarId);
	}

	/**
	 * Returns the calendar matching the UUID and group.
	 *
	 * @param uuid the calendar's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	public static com.liferay.calendar.model.Calendar
		fetchCalendarByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchCalendarByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.calendar.model.Calendar fetchGroupCalendar(
		long companyId, long groupId, String name) {

		return getService().fetchGroupCalendar(companyId, groupId, name);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the calendar with the primary key.
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar
	 * @throws PortalException if a calendar with the primary key could not be found
	 */
	public static com.liferay.calendar.model.Calendar getCalendar(
			long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendar(calendarId);
	}

	/**
	 * Returns the calendar matching the UUID and group.
	 *
	 * @param uuid the calendar's UUID
	 * @param groupId the primary key of the group
	 * @return the matching calendar
	 * @throws PortalException if a matching calendar could not be found
	 */
	public static com.liferay.calendar.model.Calendar
			getCalendarByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendarByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar>
		getCalendarResourceCalendars(long groupId, long calendarResourceId) {

		return getService().getCalendarResourceCalendars(
			groupId, calendarResourceId);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar>
		getCalendarResourceCalendars(
			long groupId, long calendarResourceId, boolean defaultCalendar) {

		return getService().getCalendarResourceCalendars(
			groupId, calendarResourceId, defaultCalendar);
	}

	/**
	 * Returns a range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.calendar.model.impl.CalendarModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of calendars
	 */
	public static java.util.List<com.liferay.calendar.model.Calendar>
		getCalendars(int start, int end) {

		return getService().getCalendars(start, end);
	}

	/**
	 * Returns all the calendars matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendars
	 * @param companyId the primary key of the company
	 * @return the matching calendars, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.calendar.model.Calendar>
		getCalendarsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getCalendarsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of calendars matching the UUID and company.
	 *
	 * @param uuid the UUID of the calendars
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching calendars, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.calendar.model.Calendar>
		getCalendarsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.calendar.model.Calendar> orderByComparator) {

		return getService().getCalendarsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of calendars.
	 *
	 * @return the number of calendars
	 */
	public static int getCalendarsCount() {
		return getService().getCalendarsCount();
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

	public static boolean hasStagingCalendar(
			com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().hasStagingCalendar(calendar);
	}

	public static void importCalendar(long calendarId, String data, String type)
		throws Exception {

		getService().importCalendar(calendarId, data, type);
	}

	public static boolean isStagingCalendar(
		com.liferay.calendar.model.Calendar calendar) {

		return getService().isStagingCalendar(calendar);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.calendar.model.Calendar> orderByComparator) {

		return getService().search(
			companyId, groupIds, calendarResourceIds, keywords, andOperator,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String name, String description, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.calendar.model.Calendar> orderByComparator) {

		return getService().search(
			companyId, groupIds, calendarResourceIds, name, description,
			andOperator, start, end, orderByComparator);
	}

	public static int searchCount(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String keywords, boolean andOperator) {

		return getService().searchCount(
			companyId, groupIds, calendarResourceIds, keywords, andOperator);
	}

	public static int searchCount(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String name, String description, boolean andOperator) {

		return getService().searchCount(
			companyId, groupIds, calendarResourceIds, name, description,
			andOperator);
	}

	/**
	 * Updates the calendar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CalendarLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param calendar the calendar
	 * @return the calendar that was updated
	 */
	public static com.liferay.calendar.model.Calendar updateCalendar(
		com.liferay.calendar.model.Calendar calendar) {

		return getService().updateCalendar(calendar);
	}

	public static void updateCalendar(long calendarId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateCalendar(calendarId, defaultCalendar);
	}

	public static com.liferay.calendar.model.Calendar updateCalendar(
			long calendarId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap, int color,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCalendar(
			calendarId, nameMap, descriptionMap, color, serviceContext);
	}

	public static com.liferay.calendar.model.Calendar updateCalendar(
			long calendarId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			String timeZoneId, int color, boolean defaultCalendar,
			boolean enableComments, boolean enableRatings,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCalendar(
			calendarId, nameMap, descriptionMap, timeZoneId, color,
			defaultCalendar, enableComments, enableRatings, serviceContext);
	}

	public static com.liferay.calendar.model.Calendar updateColor(
			long calendarId, int color,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateColor(calendarId, color, serviceContext);
	}

	public static CalendarLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CalendarLocalService, CalendarLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CalendarLocalService.class);

		ServiceTracker<CalendarLocalService, CalendarLocalService>
			serviceTracker =
				new ServiceTracker<CalendarLocalService, CalendarLocalService>(
					bundle.getBundleContext(), CalendarLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}