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

package com.liferay.portal.security.audit.storage.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.audit.AuditMessage;
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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.audit.storage.model.AuditEvent;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for AuditEvent. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEventLocalServiceUtil
 * @see com.liferay.portal.security.audit.storage.service.base.AuditEventLocalServiceBaseImpl
 * @see com.liferay.portal.security.audit.storage.service.impl.AuditEventLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AuditEventLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditEventLocalServiceUtil} to access the audit event local service. Add custom service methods to {@link com.liferay.portal.security.audit.storage.service.impl.AuditEventLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the audit event to the database. Also notifies the appropriate model listeners.
	*
	* @param auditEvent the audit event
	* @return the audit event that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AuditEvent addAuditEvent(AuditEvent auditEvent);

	public AuditEvent addAuditEvent(AuditMessage auditMessage);

	/**
	* Creates a new audit event with the primary key. Does not add the audit event to the database.
	*
	* @param auditEventId the primary key for the new audit event
	* @return the new audit event
	*/
	@Transactional(enabled = false)
	public AuditEvent createAuditEvent(long auditEventId);

	/**
	* Deletes the audit event from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEvent the audit event
	* @return the audit event that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AuditEvent deleteAuditEvent(AuditEvent auditEvent);

	/**
	* Deletes the audit event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event that was removed
	* @throws PortalException if a audit event with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AuditEvent deleteAuditEvent(long auditEventId)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.security.audit.storage.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.security.audit.storage.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AuditEvent fetchAuditEvent(long auditEventId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the audit event with the primary key.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event
	* @throws PortalException if a audit event with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AuditEvent getAuditEvent(long auditEventId)
		throws PortalException;

	/**
	* Returns a range of all the audit events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.security.audit.storage.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit events
	* @param end the upper bound of the range of audit events (not inclusive)
	* @return the range of audit events
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, int start, int end,
		OrderByComparator orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, long userId,
		String userName, Date createDateGT, Date createDateLT,
		String eventType, String className, String classPK, String clientHost,
		String clientIP, String serverName, int serverPort, String sessionID,
		boolean andSearch, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, long userId,
		String userName, Date createDateGT, Date createDateLT,
		String eventType, String className, String classPK, String clientHost,
		String clientIP, String serverName, int serverPort, String sessionID,
		boolean andSearch, int start, int end,
		OrderByComparator orderByComparator);

	/**
	* Returns the number of audit events.
	*
	* @return the number of audit events
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAuditEventsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAuditEventsCount(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAuditEventsCount(long companyId, long userId,
		String userName, Date createDateGT, Date createDateLT,
		String eventType, String className, String classPK, String clientHost,
		String clientIP, String serverName, int serverPort, String sessionID,
		boolean andSearch);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the audit event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEvent the audit event
	* @return the audit event that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AuditEvent updateAuditEvent(AuditEvent auditEvent);
}