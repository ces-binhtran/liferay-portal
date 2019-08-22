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

import com.liferay.osb.koroneiki.taproot.constants.TeamRoleType;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Amos Fong
 */
@ProviderType
public class TeamRoleNameException extends PortalException {

	public TeamRoleNameException() {
	}

	public TeamRoleNameException(String msg) {
		super(msg);
	}

	public TeamRoleNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TeamRoleNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends TeamRoleNameException {

		public MustNotBeDuplicate(String name, int type) {
			super(
				String.format(
					"A team role with name %s and type %s is already in use",
					name, TeamRoleType.getLabel(type)));
		}

	}

}