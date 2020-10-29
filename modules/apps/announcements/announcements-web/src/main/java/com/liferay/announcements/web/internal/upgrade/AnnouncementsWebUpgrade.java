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

package com.liferay.announcements.web.internal.upgrade;

import com.liferay.announcements.web.internal.upgrade.v1_0_2.UpgradePermission;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeWebModuleRelease;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 * @author Roberto Díaz
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class AnnouncementsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		BaseUpgradeWebModuleRelease upgradeWebModuleRelease =
			new BaseUpgradeWebModuleRelease() {

				@Override
				protected String getBundleSymbolicName() {
					return "com.liferay.announcements.web";
				}

				@Override
				protected String[] getPortletIds() {
					return new String[] {
						"1_WAR_soannouncementsportlet", "83", "84"
					};
				}

			};

		try {
			upgradeWebModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register("0.0.0", "2.0.0", new DummyUpgradeStep());

		registry.register("0.0.1", "1.0.1", new DummyUpgradeStep());

		// See LPS-65946

		registry.register("1.0.0", "1.0.1", new DummyUpgradeStep());

		registry.register("1.0.1", "1.0.2", new UpgradePermission(true));

		// See LPS-69656

		registry.register("1.0.2", "1.0.3", new UpgradePermission(true));

		registry.register("1.0.3", "1.0.4", new DummyUpgradeStep());

		registry.register(
			"1.0.4", "2.0.0",
			new com.liferay.announcements.web.internal.upgrade.v2_0_0.
				UpgradePortletPreferences());
	}

}