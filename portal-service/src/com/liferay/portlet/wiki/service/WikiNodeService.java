/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.wiki.service;


/**
 * <a href="WikiNodeService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.liferay.portlet.wiki.service.impl.WikiNodeServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.wiki.service.WikiNodeServiceFactory
 * @see com.liferay.portlet.wiki.service.WikiNodeServiceUtil
 *
 */
public interface WikiNodeService {
	public com.liferay.portlet.wiki.model.WikiNode addNode(long plid,
		java.lang.String name, java.lang.String description,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.wiki.model.WikiNode addNode(long plid,
		java.lang.String name, java.lang.String description,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteNode(long nodeId)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.wiki.model.WikiNode getNode(long nodeId)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void subscribeNode(long nodeId)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void unsubscribeNode(long nodeId)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.wiki.model.WikiNode updateNode(long nodeId,
		java.lang.String name, java.lang.String description)
		throws java.rmi.RemoteException, com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;
}