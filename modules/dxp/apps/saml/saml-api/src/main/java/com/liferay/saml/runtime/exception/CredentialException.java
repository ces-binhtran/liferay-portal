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

package com.liferay.saml.runtime.exception;

import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Mika Koivisto
 */
@ProviderType
public class CredentialException extends PortalException {

	public CredentialException() {
	}

	public CredentialException(String msg) {
		super(msg);
	}

	public CredentialException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CredentialException(Throwable cause) {
		super(cause);
	}

}