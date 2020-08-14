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

package com.liferay.account.internal.upgrade;

import com.liferay.account.internal.upgrade.v1_1_0.UpgradeSchema;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class AccountServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.0.1",
			new com.liferay.account.internal.upgrade.v1_0_1.UpgradeRole());

		registry.register(
			"1.0.1", "1.0.2",
			new com.liferay.account.internal.upgrade.v1_0_2.UpgradeRole());

		registry.register(
			"1.0.2", "1.0.3",
			new com.liferay.account.internal.upgrade.v1_0_3.UpgradeRole());

		registry.register(
			"1.0.3", "1.1.0",
			new com.liferay.account.internal.upgrade.v1_1_0.
				UpgradeAccountEntry(),
			new UpgradeSchema());

		registry.register(
			"1.1.0", "1.1.1",
			new com.liferay.account.internal.upgrade.v1_1_1.
				UpgradeAccountEntry());

		registry.register(
			"1.1.1", "1.2.0",
			new com.liferay.account.internal.upgrade.v1_2_0.UpgradeSchema());
	}

}