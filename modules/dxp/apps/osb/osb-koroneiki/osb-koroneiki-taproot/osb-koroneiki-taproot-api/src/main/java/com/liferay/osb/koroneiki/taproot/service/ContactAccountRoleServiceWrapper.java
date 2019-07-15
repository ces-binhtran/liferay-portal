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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link ContactAccountRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRoleService
 * @generated
 */
@ProviderType
public class ContactAccountRoleServiceWrapper
	implements ContactAccountRoleService,
			   ServiceWrapper<ContactAccountRoleService> {

	public ContactAccountRoleServiceWrapper(
		ContactAccountRoleService contactAccountRoleService) {

		_contactAccountRoleService = contactAccountRoleService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			addContactAccountRole(
				long contactId, long accountId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleService.addContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			addContactAccountRole(
				String contactKey, String accountKey, String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleService.addContactAccountRole(
			contactKey, accountKey, contactRoleKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			deleteContactAccountRole(
				long contactId, long accountId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleService.deleteContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			deleteContactAccountRole(
				String contactKey, String accountKey, String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleService.deleteContactAccountRole(
			contactKey, accountKey, contactRoleKey);
	}

	@Override
	public void deleteContactAccountRoles(long contactId, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_contactAccountRoleService.deleteContactAccountRoles(
			contactId, accountId);
	}

	@Override
	public void deleteContactAccountRoles(String contactKey, String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		_contactAccountRoleService.deleteContactAccountRoles(
			contactKey, accountKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactAccountRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public ContactAccountRoleService getWrappedService() {
		return _contactAccountRoleService;
	}

	@Override
	public void setWrappedService(
		ContactAccountRoleService contactAccountRoleService) {

		_contactAccountRoleService = contactAccountRoleService;
	}

	private ContactAccountRoleService _contactAccountRoleService;

}