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

package com.liferay.portal.kernel.upgrade;

import com.liferay.exportimport.kernel.staging.StagingConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Gergely Mathe
 * @author Balázs Sáfrány-Kovalik
 */
public class UpgradeStagingGroupTypeSettings extends UpgradeProcess {

	public UpgradeStagingGroupTypeSettings(
		GroupLocalService groupLocalService, String oldPortletId,
		String newPortletId) {

		this.groupLocalService = groupLocalService;
		this.oldPortletId = oldPortletId;
		this.newPortletId = newPortletId;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateStagedPortletNames();
	}

	protected void updateStagedPortletNames() throws PortalException {
		ActionableDynamicQuery groupActionableDynamicQuery =
			groupLocalService.getActionableDynamicQuery();

		groupActionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property siteProperty = PropertyFactoryUtil.forName("site");

				dynamicQuery.add(siteProperty.eq(Boolean.TRUE));
			});

		groupActionableDynamicQuery.setPerformActionMethod(
			(ActionableDynamicQuery.PerformActionMethod<Group>)group -> {
				UnicodeProperties typeSettingsProperties =
					group.getTypeSettingsProperties();

				if (typeSettingsProperties == null) {
					return;
				}

				String propertyKey = _getStagedPortletId(oldPortletId);

				String propertyValue = typeSettingsProperties.getProperty(
					propertyKey);

				if (Validator.isNull(propertyValue)) {
					return;
				}

				typeSettingsProperties.remove(propertyKey);

				propertyKey = _getStagedPortletId(newPortletId);

				typeSettingsProperties.put(propertyKey, propertyValue);

				group.setTypeSettingsProperties(typeSettingsProperties);

				groupLocalService.updateGroup(group);
			});

		groupActionableDynamicQuery.performActions();
	}

	protected GroupLocalService groupLocalService;
	protected String newPortletId;
	protected String oldPortletId;

	private String _getStagedPortletId(String portletId) {
		String key = portletId;

		if (key.startsWith(StagingConstants.STAGED_PORTLET)) {
			return key;
		}

		return StagingConstants.STAGED_PORTLET.concat(portletId);
	}

}