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

package com.liferay.search.experiences.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchSXPBlueprintException extends NoSuchModelException {

	public NoSuchSXPBlueprintException() {
	}

	public NoSuchSXPBlueprintException(String msg) {
		super(msg);
	}

	public NoSuchSXPBlueprintException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchSXPBlueprintException(Throwable throwable) {
		super(throwable);
	}

}