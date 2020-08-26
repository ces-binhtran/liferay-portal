/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.type.virtual.internal.upgrade;

import com.liferay.commerce.product.type.virtual.internal.upgrade.v1_1_0.CPDefinitionVirtualSettingUpgradeProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class CommerceProductTypeVirtualUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"COMMERCE PRODUCT TYPE VIRTUAL UPGRADE STEP REGISTRATOR " +
					"STARTED");
		}

		registry.register(
			_SCHEMA_VERSION_1_0_0, _SCHEMA_VERSION_1_1_0,
			new CPDefinitionVirtualSettingUpgradeProcess());

		if (_log.isInfoEnabled()) {
			_log.info(
				"COMMERCE PRODUCT TYPE VIRTUAL UPGRADE STEP REGISTRATOR " +
					"FINISHED");
		}
	}

	private static final String _SCHEMA_VERSION_1_0_0 = "1.0.0";

	private static final String _SCHEMA_VERSION_1_1_0 = "1.1.0";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductTypeVirtualUpgradeStepRegistrator.class);

}