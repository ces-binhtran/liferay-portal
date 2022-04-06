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

package com.liferay.dynamic.data.lists.service;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for DDLRecordSet. This utility wraps
 * <code>com.liferay.dynamic.data.lists.service.impl.DDLRecordSetServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSetService
 * @generated
 */
public class DDLRecordSetServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.dynamic.data.lists.service.impl.DDLRecordSetServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds a record set referencing the DDM structure.
	 *
	 * @param groupId the primary key of the record set's group
	 * @param ddmStructureId the primary key of the record set's DDM structure
	 * @param recordSetKey the mnemonic primary key of the record set. If
	 <code>null</code>, the record set key will be autogenerated.
	 * @param nameMap the record set's locales and localized names
	 * @param descriptionMap the record set's locales and localized
	 descriptions
	 * @param minDisplayRows the record set's minimum number of rows to be
	 displayed in spreadsheet view
	 * @param scope the record set's scope, used to scope the record set's
	 data. For more information search
	 <code>DDLRecordSetConstants</code> in the
	 <code>dynamic.data.lists.api</code> module for constants starting
	 with the "SCOPE_" prefix.
	 * @param serviceContext serviceContext the service context to be applied.
	 This can set the UUID, guest permissions, and group permissions
	 for the record set.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDLRecordSet addRecordSet(
			long groupId, long ddmStructureId, String recordSetKey,
			Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap, int minDisplayRows,
			int scope,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addRecordSet(
			groupId, ddmStructureId, recordSetKey, nameMap, descriptionMap,
			minDisplayRows, scope, serviceContext);
	}

	/**
	 * Deletes a record set and its resources.
	 *
	 * @param recordSetId the primary key of the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static void deleteRecordSet(long recordSetId)
		throws PortalException {

		getService().deleteRecordSet(recordSetId);
	}

	/**
	 * Returns a record set with the ID.
	 *
	 * @param recordSetId the primary key of the record set
	 * @return the record set with the ID or <code>null</code> if the matching
	 record set is not found
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDLRecordSet fetchRecordSet(long recordSetId)
		throws PortalException {

		return getService().fetchRecordSet(recordSetId);
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
	 * Returns a record set with the ID.
	 *
	 * @param recordSetId the primary key of the record set
	 * @return the record set with the ID
	 * @throws PortalException if a matching record set could not be found or if
	 the user did not have the required permission to access the
	 record set
	 */
	public static DDLRecordSet getRecordSet(long recordSetId)
		throws PortalException {

		return getService().getRecordSet(recordSetId);
	}

	/**
	 * Returns all the record sets matching the groups, filtered by the user's
	 * <code>VIEW</code> permission.
	 *
	 * @param groupIds the primary keys of the record set's groups
	 * @return the matching record sets
	 */
	public static List<DDLRecordSet> getRecordSets(long[] groupIds) {
		return getService().getRecordSets(groupIds);
	}

	/**
	 * Returns a range of all record sets matching the parameters, filtered by
	 * the user's <code>VIEW</code> permission. The keywords parameter is used
	 * for matching String values to the record set's name or description.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to <code>QueryUtil.ALL_POS</code> will return the
	 * full result set.
	 * </p>
	 *
	 * @param companyId the primary key of the record set's company
	 * @param groupId the primary key of the record set's group
	 * @param keywords the keywords (space separated), which my occur in the
	 record set name or description (optionally <code>null</code>). If
	 the keywords value is not <code>null</code>, the search uses the
	 OR operator in connecting query criteria; otherwise it uses the
	 AND operator.
	 * @param scope the record set's scope. Constant used to scope the record
	 set's data. For more information search the
	 <code>dynamic.data.lists.api</code> module's
	 <code>DDLRecordSetConstants</code> class for constants prefixed
	 with "SCOPE_".
	 * @param start the lower bound of the range of record sets to return
	 * @param end the upper bound of the range of record sets to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the record sets
	 * @return the range of matching record sets ordered by the comparator
	 */
	public static List<DDLRecordSet> search(
		long companyId, long groupId, String keywords, int scope, int start,
		int end, OrderByComparator<DDLRecordSet> orderByComparator) {

		return getService().search(
			companyId, groupId, keywords, scope, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of record sets, filtered by the user's
	 * <code>VIEW</code> permission. Company ID and group ID must be matched. If
	 * the and operator is set to <code>true</code>, only record sets with a
	 * matching name, description, and scope are returned. If the and operator
	 * is set to <code>false</code>, only one parameter of name, description,
	 * and scope is needed to return matching record sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to <code>QueryUtil.ALL_POS</code> will return the
	 * full result set.
	 * </p>
	 *
	 * @param companyId the primary key of the record set's company
	 * @param groupId the primary key of the record set's group
	 * @param name the name keywords (space separated, optionally
	 <code>null</code>)
	 * @param description the description keywords (space separated, optionally
	 <code>null</code>)
	 * @param scope the record set's scope. Constant used to scope the record
	 set's data. For more information search the
	 <code>dynamic.data.lists.api</code> module's
	 <code>DDLRecordSetConstants</code> class for constants prefixed
	 with "SCOPE_".
	 * @param andOperator whether every field must match its value or keywords,
	 or just one field must match. Company and group must match their
	 values.
	 * @param start the lower bound of the range of record sets to return
	 * @param end the upper bound of the range of record sets to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the record sets
	 * @return the range of matching record sets ordered by the comparator
	 */
	public static List<DDLRecordSet> search(
		long companyId, long groupId, String name, String description,
		int scope, boolean andOperator, int start, int end,
		OrderByComparator<DDLRecordSet> orderByComparator) {

		return getService().search(
			companyId, groupId, name, description, scope, andOperator, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of record sets matching the parameters, filtered by
	 * the user's <code>VIEW</code> permission. The keywords parameter is used
	 * for matching record set names or descriptions.
	 *
	 * @param companyId the primary key of the record set's company
	 * @param groupId the primary key of the record set's group.
	 * @param keywords the keywords (space separated) to look for and match in
	 the record set name or description (optionally
	 <code>null</code>). If the keywords value is not
	 <code>null</code>, the OR operator is used in connecting query
	 criteria; otherwise it uses the AND operator.
	 * @param scope the record set's scope. A constant used to scope the record
	 set's data. For more information search the
	 <code>dynamic.data.lists.api</code> module's
	 <code>DDLRecordSetConstants</code> class for constants prefixed
	 with "SCOPE_".
	 * @return the number of matching record sets
	 */
	public static int searchCount(
		long companyId, long groupId, String keywords, int scope) {

		return getService().searchCount(companyId, groupId, keywords, scope);
	}

	/**
	 * Returns the number of all record sets matching the parameters, filtered
	 * by the user's <code>VIEW</code> permission. If the and operator is set to
	 * <code>true</code>, only record sets with a matching name, description,
	 * and scope are counted. If the and operator is set to <code>false</code>,
	 * only one parameter of name, description, and scope is needed to count
	 * matching record sets.
	 *
	 * @param companyId the primary key of the record set's company
	 * @param groupId the primary key of the record set's group
	 * @param name the name keywords (space separated). This can be
	 <code>null</code>.
	 * @param description the description keywords (space separated). Can be
	 <code>null</code>.
	 * @param scope the record set's scope. A constant used to scope the record
	 set's data. For more information search the
	 <code>dynamic.data.lists.api</code> module's
	 <code>DDLRecordSetConstants</code> class for constants prefixed
	 with "SCOPE_".
	 * @param andOperator whether every field must match its value or keywords,
	 or just one field must match. Company and group must match their
	 values.
	 * @return the number of matching record sets
	 */
	public static int searchCount(
		long companyId, long groupId, String name, String description,
		int scope, boolean andOperator) {

		return getService().searchCount(
			companyId, groupId, name, description, scope, andOperator);
	}

	/**
	 * Updates the number of minimum rows to display for the record set. Useful
	 * when the record set is being displayed in spreadsheet.
	 *
	 * @param recordSetId the primary key of the record set
	 * @param minDisplayRows the record set's minimum number of rows to be
	 displayed in spreadsheet view
	 * @param serviceContext the service context to be applied. This can set
	 the record set modified date.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDLRecordSet updateMinDisplayRows(
			long recordSetId, int minDisplayRows,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateMinDisplayRows(
			recordSetId, minDisplayRows, serviceContext);
	}

	/**
	 * Updates the the record set's settings.
	 *
	 * @param recordSetId the primary key of the record set
	 * @param settingsDDMFormValues the record set's settings. For more
	 information see <code>DDMFormValues</code> in the
	 <code>dynamic.data.mapping.api</code> module.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDLRecordSet updateRecordSet(
			long recordSetId,
			com.liferay.dynamic.data.mapping.storage.DDMFormValues
				settingsDDMFormValues)
		throws PortalException {

		return getService().updateRecordSet(recordSetId, settingsDDMFormValues);
	}

	/**
	 * Updates the DDM structure, name, description, and minimum number of
	 * display rows for the record set matching the record set ID.
	 *
	 * @param recordSetId the primary key of the record set
	 * @param ddmStructureId the primary key of the record set's DDM structure
	 * @param nameMap the record set's locales and localized names
	 * @param descriptionMap the record set's locales and localized
	 descriptions
	 * @param minDisplayRows the record set's minimum number of rows to be
	 displayed in spreadsheet view.
	 * @param serviceContext the service context to be applied. Can set the
	 record set modified date.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDLRecordSet updateRecordSet(
			long recordSetId, long ddmStructureId,
			Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap, int minDisplayRows,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateRecordSet(
			recordSetId, ddmStructureId, nameMap, descriptionMap,
			minDisplayRows, serviceContext);
	}

	/**
	 * Updates the DDM structure, name, description, and minimum number of
	 * display rows for the record set matching the group ID and record set key.
	 *
	 * @param groupId the primary key of the record set's group
	 * @param ddmStructureId the primary key of the record set's DDM structure
	 * @param recordSetKey the record set's mnemonic primary key
	 * @param nameMap the record set's locales and localized names
	 * @param descriptionMap the record set's locales and localized
	 descriptions
	 * @param minDisplayRows the record set's minimum number of rows to be
	 displayed in spreadsheet view
	 * @param serviceContext the service context to be applied. This can set
	 the record set modified date.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public static DDLRecordSet updateRecordSet(
			long groupId, long ddmStructureId, String recordSetKey,
			Map<java.util.Locale, String> nameMap,
			Map<java.util.Locale, String> descriptionMap, int minDisplayRows,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateRecordSet(
			groupId, ddmStructureId, recordSetKey, nameMap, descriptionMap,
			minDisplayRows, serviceContext);
	}

	public static DDLRecordSetService getService() {
		return _service;
	}

	private static volatile DDLRecordSetService _service;

}