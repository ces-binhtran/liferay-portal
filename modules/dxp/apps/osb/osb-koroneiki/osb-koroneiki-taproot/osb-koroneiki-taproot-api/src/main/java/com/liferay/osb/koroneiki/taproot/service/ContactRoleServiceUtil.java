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

package com.liferay.osb.koroneiki.taproot.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for ContactRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactRoleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleService
 * @generated
 */
@ProviderType
public class ContactRoleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactRoleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			addContactRole(String name, String description, int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addContactRole(name, description, type);
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			deleteContactRole(long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteContactRole(contactRoleId);
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			deleteContactRole(String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteContactRole(contactRoleKey);
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			getContactRole(long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContactRole(contactRoleId);
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			getContactRole(String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContactRole(contactRoleKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			updateContactRole(
				long contactRoleId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateContactRole(contactRoleId, name, description);
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			updateContactRole(
				String contactRoleKey, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateContactRole(
			contactRoleKey, name, description);
	}

	public static ContactRoleService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContactRoleService, ContactRoleService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContactRoleService.class);

		ServiceTracker<ContactRoleService, ContactRoleService> serviceTracker =
			new ServiceTracker<ContactRoleService, ContactRoleService>(
				bundle.getBundleContext(), ContactRoleService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}