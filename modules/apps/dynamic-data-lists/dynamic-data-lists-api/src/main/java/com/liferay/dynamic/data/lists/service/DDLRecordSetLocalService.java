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
import com.liferay.dynamic.data.lists.model.DDLRecordSetSettings;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for DDLRecordSet. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSetLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DDLRecordSetLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.dynamic.data.lists.service.impl.DDLRecordSetLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the ddl record set local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link DDLRecordSetLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the ddl record set to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDLRecordSetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddlRecordSet the ddl record set
	 * @return the ddl record set that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDLRecordSet addDDLRecordSet(DDLRecordSet ddlRecordSet);

	/**
	 * Adds a record set referencing the DDM structure.
	 *
	 * @param userId the primary key of the record set's creator/owner
	 * @param groupId the primary key of the record set's group
	 * @param ddmStructureId the primary key of the record set's DDM structure
	 * @param recordSetKey the record set's mnemonic primary key. If
	 <code>null</code>, the record set key will be autogenerated.
	 * @param nameMap the record set's locales and localized names
	 * @param descriptionMap the record set's locales and localized
	 descriptions
	 * @param minDisplayRows the record set's minimum number of rows to be
	 displayed in spreadsheet view.
	 * @param scope the record set's scope, used to scope the record set's
	 data. For more information search
	 <code>DDLRecordSetConstants</code> in the
	 <code>dynamic.data.lists.api</code> module for constants starting
	 with the "SCOPE_" prefix.
	 * @param serviceContext the service context to be applied. Can set the
	 UUID, guest permissions, and group permissions for the record
	 set.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDLRecordSet addRecordSet(
			long userId, long groupId, long ddmStructureId, String recordSetKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int minDisplayRows, int scope, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Adds the resources to the record set.
	 *
	 * @param recordSet the record set
	 * @param addGroupPermissions whether to add group permissions
	 * @param addGuestPermissions whether to add guest permissions
	 * @throws PortalException if a portal exception occurred
	 */
	public void addRecordSetResources(
			DDLRecordSet recordSet, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException;

	/**
	 * Adds the model resources with the permissions to the record set.
	 *
	 * @param recordSet the record set
	 * @param modelPermissions the model permissions
	 * @throws PortalException if a portal exception occurred
	 */
	public void addRecordSetResources(
			DDLRecordSet recordSet, ModelPermissions modelPermissions)
		throws PortalException;

	/**
	 * Creates a new ddl record set with the primary key. Does not add the ddl record set to the database.
	 *
	 * @param recordSetId the primary key for the new ddl record set
	 * @return the new ddl record set
	 */
	@Transactional(enabled = false)
	public DDLRecordSet createDDLRecordSet(long recordSetId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the ddl record set from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDLRecordSetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddlRecordSet the ddl record set
	 * @return the ddl record set that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public DDLRecordSet deleteDDLRecordSet(DDLRecordSet ddlRecordSet);

	/**
	 * Deletes the ddl record set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDLRecordSetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recordSetId the primary key of the ddl record set
	 * @return the ddl record set that was removed
	 * @throws PortalException if a ddl record set with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public DDLRecordSet deleteDDLRecordSet(long recordSetId)
		throws PortalException;

	public void deleteDDMStructureRecordSets(long ddmStructureId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the record set and its resources.
	 *
	 * @param recordSet the record set to be deleted
	 * @throws PortalException if a portal exception occurred
	 */
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE
	)
	public void deleteRecordSet(DDLRecordSet recordSet) throws PortalException;

	/**
	 * Deletes the record set and its resources.
	 *
	 * @param recordSetId the primary key of the record set to be deleted
	 * @throws PortalException if a portal exception occurred
	 */
	public void deleteRecordSet(long recordSetId) throws PortalException;

	/**
	 * Deletes the record set and its resources.
	 *
	 * <p>
	 * This operation updates the record set matching the group and
	 * recordSetKey.
	 * </p>
	 *
	 * @param groupId the primary key of the record set's group
	 * @param recordSetKey the record set's mnemonic primary key
	 * @throws PortalException if a portal exception occurred
	 */
	public void deleteRecordSet(long groupId, String recordSetKey)
		throws PortalException;

	/**
	 * Deletes all the record sets matching the group.
	 *
	 * @param groupId the primary key of the record set's group
	 * @throws PortalException if a portal exception occurred
	 */
	public void deleteRecordSets(long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordSetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordSetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet fetchDDLRecordSet(long recordSetId);

	/**
	 * Returns the ddl record set matching the UUID and group.
	 *
	 * @param uuid the ddl record set's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddl record set, or <code>null</code> if a matching ddl record set could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet fetchDDLRecordSetByUuidAndGroupId(
		String uuid, long groupId);

	/**
	 * Returns the record set with the ID.
	 *
	 * @param recordSetId the primary key of the record set
	 * @return the record set with the ID, or <code>null</code> if a matching
	 record set could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet fetchRecordSet(long recordSetId);

	/**
	 * Returns the record set matching the group and record set key.
	 *
	 * @param groupId the primary key of the record set's group
	 * @param recordSetKey the record set's mnemonic primary key
	 * @return the record set matching the group and record set key, or
	 <code>null</code> if a matching record set could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet fetchRecordSet(long groupId, String recordSetKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the ddl record set with the primary key.
	 *
	 * @param recordSetId the primary key of the ddl record set
	 * @return the ddl record set
	 * @throws PortalException if a ddl record set with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet getDDLRecordSet(long recordSetId)
		throws PortalException;

	/**
	 * Returns the ddl record set matching the UUID and group.
	 *
	 * @param uuid the ddl record set's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddl record set
	 * @throws PortalException if a matching ddl record set could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet getDDLRecordSetByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the ddl record sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.lists.model.impl.DDLRecordSetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl record sets
	 * @param end the upper bound of the range of ddl record sets (not inclusive)
	 * @return the range of ddl record sets
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getDDLRecordSets(int start, int end);

	/**
	 * Returns all the ddl record sets matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddl record sets
	 * @param companyId the primary key of the company
	 * @return the matching ddl record sets, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getDDLRecordSetsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of ddl record sets matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddl record sets
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of ddl record sets
	 * @param end the upper bound of the range of ddl record sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching ddl record sets, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getDDLRecordSetsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	 * Returns the number of ddl record sets.
	 *
	 * @return the number of ddl record sets
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDDLRecordSetsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getDDMStructureRecordSets(long ddmStructureId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the record set with the ID.
	 *
	 * @param recordSetId the primary key of the record set
	 * @return the record set with the ID
	 * @throws PortalException if the the matching record set could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet getRecordSet(long recordSetId) throws PortalException;

	/**
	 * Returns the record set matching the group and record set key.
	 *
	 * @param groupId the primary key of the record set's group
	 * @param recordSetKey the record set's mnemonic primary key
	 * @return the record set matching the group and record set key
	 * @throws PortalException if the the matching record set could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet getRecordSet(long groupId, String recordSetKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet getRecordSet(String uuid, long recordSetId)
		throws PortalException;

	/**
	 * Returns all the record sets belonging the group.
	 *
	 * @return the record sets belonging to the group
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getRecordSets(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getRecordSets(long groupId, int start, int end);

	/**
	 * Returns the number of all the record sets belonging the group.
	 *
	 * @param groupId the primary key of the record set's group
	 * @return the number of record sets belonging to the group
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecordSetsCount(long groupId);

	/**
	 * Returns the number of all the record sets belonging the group and
	 * associated with the DDMStructure.
	 *
	 * @param groupId the primary key of the record set's group
	 * @return the number of record sets belonging to the group
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRecordSetsCount(
		long groupId, long ddmStructureId, boolean andOperator);

	/**
	 * Returns the record set's settings as a DDMFormValues object. For more
	 * information see <code>DDMFormValues</code> in the
	 * <code>dynamic.data.mapping.api</code> module.
	 *
	 * @param recordSet the record set
	 * @return the record set settings as a DDMFormValues object
	 * @throws PortalException if a portal exception occurred
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormValues getRecordSetSettingsDDMFormValues(
			DDLRecordSet recordSet)
		throws PortalException;

	/**
	 * Returns the record set's settings.
	 *
	 * @param recordSet the record set
	 * @return the record set settings
	 * @throws PortalException if a portal exception occurred
	 * @see #getRecordSetSettingsDDMFormValues(DDLRecordSet)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSetSettings getRecordSetSettingsModel(
			DDLRecordSet recordSet)
		throws PortalException;

	/**
	 * Returns a range of all record sets matching the parameters, including a
	 * keywords parameter for matching string values to the record set's name or
	 * description.
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
	 * @param keywords the keywords (space separated) to look for and match in
	 the record set name or description (optionally
	 <code>null</code>). If the keywords value is not
	 <code>null</code>, the search uses the OR operator in connecting
	 query criteria; otherwise it uses the AND operator.
	 * @param scope the record set's scope. A constant used to scope the record
	 set's data. For more information search the
	 <code>dynamic.data.lists.api</code> module's
	 <code>DDLRecordSetConstants</code> class for constants prefixed
	 with "SCOPE_".
	 * @param start the lower bound of the range of record sets to return
	 * @param end the upper bound of the range of recor sets to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the record sets
	 * @return the range of matching record sets ordered by the comparator
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> search(
		long companyId, long groupId, String keywords, int scope, int start,
		int end, OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	 * Returns an ordered range of record sets. Company ID and group ID must be
	 * matched. If the and operator is set to <code>true</code>, only record
	 * sets with a matching name, description, and scope are returned. If the
	 * and operator is set to <code>false</code>, only one parameter of name,
	 * description, and scope is needed to return matching record sets.
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
	 * @param scope the record set's scope. A constant used to scope the record
	 set's data. For more information search the
	 <code>dynamic.data.lists.api</code> module's
	 <code>DDLRecordSetConstants</code> class for constants prefixed
	 with "SCOPE_".
	 * @param andOperator whether every field must match its value or keywords,
	 or just one field must match. Company and group must match their
	 values.
	 * @param start the lower bound of the range of record sets to return
	 * @param end the upper bound of the range of recor sets to return (not
	 inclusive)
	 * @param orderByComparator the comparator to order the record sets
	 * @return the range of matching record sets ordered by the comparator
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> search(
		long companyId, long groupId, String name, String description,
		int scope, boolean andOperator, int start, int end,
		OrderByComparator<DDLRecordSet> orderByComparator);

	/**
	 * Returns the number of record sets matching the parameters. The keywords
	 * parameter is used for matching the record set's name or description
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(
		long companyId, long groupId, String keywords, int scope);

	/**
	 * Returns the number of all record sets matching the parameters. name and
	 * description keywords. Company ID and group ID must be matched. If the and
	 * operator is set to <code>true</code>, only record sets with a matching
	 * name, description, and scope are counted. If the and operator is set to
	 * <code>false</code>, only one parameter of name, description, and scope is
	 * needed to count matching record sets.
	 *
	 * @param companyId the primary key of the record set's company
	 * @param groupId the primary key of the record set's group
	 * @param name the name keywords (space separated). This can be
	 <code>null</code>.
	 * @param description the description keywords (space separated). This can
	 be <code>null</code>.
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(
		long companyId, long groupId, String name, String description,
		int scope, boolean andOperator);

	/**
	 * Updates the ddl record set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDLRecordSetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddlRecordSet the ddl record set
	 * @return the ddl record set that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDLRecordSet updateDDLRecordSet(DDLRecordSet ddlRecordSet);

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
	public DDLRecordSet updateMinDisplayRows(
			long recordSetId, int minDisplayRows, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Updates the the record set's settings.
	 *
	 * @param recordSetId the primary key of the record set
	 * @param settingsDDMFormValues the record set's settings. For more
	 information see <code>DDMFormValues</code> in the
	 <code>dynamic.data.mapping.api</code> the module.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	public DDLRecordSet updateRecordSet(
			long recordSetId, DDMFormValues settingsDDMFormValues)
		throws PortalException;

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
	 displayed in spreadsheet view
	 * @param serviceContext the service context to be applied. This can set
	 the record set modified date.
	 * @return the record set
	 * @throws PortalException if a portal exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDLRecordSet updateRecordSet(
			long recordSetId, long ddmStructureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int minDisplayRows,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Updates the DDM strucutre, name, description, and minimum number of
	 * display rows for the record set matching the record set key and group ID.
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
	public DDLRecordSet updateRecordSet(
			long groupId, long ddmStructureId, String recordSetKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int minDisplayRows, ServiceContext serviceContext)
		throws PortalException;

}