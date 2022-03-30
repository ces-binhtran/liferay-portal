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

package com.liferay.multi.factor.authentication.email.otp.web.internal.configuration.admin.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(service = ConfigurationCategory.class)
public class MFAEmailOTPConfigurationCategory implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return "lock";
	}

	@Override
	public String getCategoryKey() {
		return "multi-factor-authentication";
	}

	@Override
	public String getCategorySection() {
		return "security";
	}

}