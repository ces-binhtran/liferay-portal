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

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.osb.koroneiki.taproot.constants.ContactRoleType;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ContactRoleNameException extends PortalException {

	public ContactRoleNameException() {
	}

	public ContactRoleNameException(String msg) {
		super(msg);
	}

	public ContactRoleNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ContactRoleNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends ContactRoleNameException {

		public MustNotBeDuplicate(String name, int type) {
			super(
				String.format(
					"A contact role with name %s and type %s is already in use",
					name, ContactRoleType.getLabel(type)));
		}

	}

}