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

package com.liferay.commerce.machine.learning.forecast.alert.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Riccardo Ferrari
 */
public class NoSuchMLForecastAlertEntryException extends NoSuchModelException {

	public NoSuchMLForecastAlertEntryException() {
	}

	public NoSuchMLForecastAlertEntryException(String msg) {
		super(msg);
	}

	public NoSuchMLForecastAlertEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMLForecastAlertEntryException(Throwable cause) {
		super(cause);
	}

}