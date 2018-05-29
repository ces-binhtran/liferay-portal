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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.audit.storage.model.AuditEvent;

import java.util.Date;
import java.util.List;

/**
 * Provides the remote service interface for AuditEvent. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEventServiceUtil
 * @see com.liferay.portal.security.audit.storage.service.base.AuditEventServiceBaseImpl
 * @see com.liferay.portal.security.audit.storage.service.impl.AuditEventServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=audit", "json.web.service.context.path=AuditEvent"}, service = AuditEventService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AuditEventService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditEventServiceUtil} to access the audit event remote service. Add custom service methods to {@link com.liferay.portal.security.audit.storage.service.impl.AuditEventServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, long userId,
		String userName, Date createDateGT, Date createDateLT,
		String eventType, String className, String classPK, String clientHost,
		String clientIP, String serverName, int serverPort, String sessionID,
		boolean andSearch, int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AuditEvent> getAuditEvents(long companyId, long userId,
		String userName, Date createDateGT, Date createDateLT,
		String eventType, String className, String classPK, String clientHost,
		String clientIP, String serverName, int serverPort, String sessionID,
		boolean andSearch, int start, int end,
		OrderByComparator orderByComparator) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAuditEventsCount(long companyId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAuditEventsCount(long companyId, long userId,
		String userName, Date createDateGT, Date createDateLT,
		String eventType, String className, String classPK, String clientHost,
		String clientIP, String serverName, int serverPort, String sessionID,
		boolean andSearch) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();
}