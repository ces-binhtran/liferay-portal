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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for KaleoTaskAssignmentInstance. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstanceLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface KaleoTaskAssignmentInstanceLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTaskAssignmentInstanceLocalServiceUtil} to access the kaleo task assignment instance local service. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentInstanceLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the kaleo task assignment instance to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	 * @return the kaleo task assignment instance that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance);

	public KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
			long groupId, KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String assigneeClassName, long assigneeClassPK,
			ServiceContext serviceContext)
		throws PortalException;

	public List<KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTaskAssignment> kaleoTaskAssignments,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException;

	public KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String assigneeClassName, long assigneeClassPK,
			ServiceContext serviceContext)
		throws PortalException;

	public List<KaleoTaskAssignmentInstance> assignKaleoTaskAssignmentInstances(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTaskAssignment> kaleoTaskAssignments,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException;

	public KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	 * @return the new kaleo task assignment instance
	 */
	@Transactional(enabled = false)
	public KaleoTaskAssignmentInstance createKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId);

	public void deleteCompanyKaleoTaskAssignmentInstances(long companyId);

	public void deleteKaleoDefinitionVersionKaleoTaskAssignmentInstances(
		long kaleoDefinitionId);

	public void deleteKaleoInstanceKaleoTaskAssignmentInstances(
		long kaleoInstanceId);

	/**
	 * Deletes the kaleo task assignment instance from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	 * @return the kaleo task assignment instance that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public KaleoTaskAssignmentInstance deleteKaleoTaskAssignmentInstance(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance);

	/**
	 * Deletes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance that was removed
	 * @throws PortalException if a kaleo task assignment instance with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public KaleoTaskAssignmentInstance deleteKaleoTaskAssignmentInstance(
			long kaleoTaskAssignmentInstanceId)
		throws PortalException;

	public void deleteKaleoTaskAssignmentInstances(
		KaleoTaskInstanceToken kaleoTaskInstanceToken);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl</code>.
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
	public KaleoTaskAssignmentInstance fetchFirstKaleoTaskAssignmentInstance(
		long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoTaskAssignmentInstance> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoTaskAssignmentInstance fetchFirstKaleoTaskAssignmentInstance(
		long kaleoTaskInstanceTokenId, String assigneeClassName,
		OrderByComparator<KaleoTaskAssignmentInstance> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoTaskAssignmentInstance fetchKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the kaleo task assignment instance with the primary key.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance
	 * @throws PortalException if a kaleo task assignment instance with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KaleoTaskAssignmentInstance getKaleoTaskAssignmentInstance(
			long kaleoTaskAssignmentInstanceId)
		throws PortalException;

	/**
	 * Returns a range of all the kaleo task assignment instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @return the range of kaleo task assignment instances
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long kaleoTaskInstanceTokenId);

	/**
	 * Returns the number of kaleo task assignment instances.
	 *
	 * @return the number of kaleo task assignment instances
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoTaskAssignmentInstancesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKaleoTaskAssignmentInstancesCount(
		long kaleoTaskInstanceTokenId);

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
	 * Updates the kaleo task assignment instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoTaskAssignmentInstanceLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	 * @return the kaleo task assignment instance that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance);

}