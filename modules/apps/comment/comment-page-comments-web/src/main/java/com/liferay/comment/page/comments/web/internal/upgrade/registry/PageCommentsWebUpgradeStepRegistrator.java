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

package com.liferay.comment.page.comments.web.internal.upgrade.registry;

import com.liferay.comment.page.comments.web.internal.constants.PageCommentsPortletKeys;
import com.liferay.comment.upgrade.DiscussionSubscriptionClassNameUpgradeProcess;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.BasePortletIdUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.subscription.service.SubscriptionLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(service = UpgradeStepRegistrator.class)
public class PageCommentsWebUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.registerInitialization();

		UpgradeStep upgradePortletId = new BasePortletIdUpgradeProcess() {

			@Override
			protected String[][] getRenamePortletIdsArray() {
				return new String[][] {
					{"107", PageCommentsPortletKeys.PAGE_COMMENTS}
				};
			}

		};

		registry.register("0.0.1", "1.0.0", upgradePortletId);

		registry.register(
			"1.0.0", "1.0.1",
			new DiscussionSubscriptionClassNameUpgradeProcess(
				_classNameLocalService, _subscriptionLocalService,
				Layout.class.getName(),
				DiscussionSubscriptionClassNameUpgradeProcess.DeletionMode.
					UPDATE));

		registry.register(
			"1.0.1", "2.0.0",
			new DiscussionSubscriptionClassNameUpgradeProcess(
				_classNameLocalService, _subscriptionLocalService,
				Layout.class.getName(),
				DiscussionSubscriptionClassNameUpgradeProcess.DeletionMode.
					DELETE_OLD));
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

}