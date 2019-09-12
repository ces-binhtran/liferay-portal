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

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ProductEntryNameException extends PortalException {

	public ProductEntryNameException() {
	}

	public ProductEntryNameException(String msg) {
		super(msg);
	}

	public ProductEntryNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ProductEntryNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends ProductEntryNameException {

		public MustNotBeDuplicate(String name) {
			super(
				String.format(
					"A product with name %s is already in use", name));
		}

	}

}