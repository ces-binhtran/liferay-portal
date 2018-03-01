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

package com.liferay.commerce.vat.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.commerce.application.list.constants.CommercePanelCategoryKeys;
import com.liferay.commerce.product.model.CPGroup;
import com.liferay.commerce.product.service.CPGroupService;
import com.liferay.commerce.vat.constants.CommerceVatActionKeys;
import com.liferay.commerce.vat.constants.CommerceVatPortletKeys;
import com.liferay.commerce.vat.service.permission.CommerceVatPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=700",
		"panel.category.key=" + CommercePanelCategoryKeys.SITE_ADMINISTRATION_COMMERCE
	},
	service = PanelApp.class
)
public class CommerceVatNumberPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CommerceVatPortletKeys.COMMERCE_VAT_NUMBER;
	}

	@Override
	public boolean isShow(PermissionChecker permissionChecker, Group group)
		throws PortalException {

		boolean show = super.isShow(permissionChecker, group);

		if (show) {
			CPGroup cpGroup = _cpGroupService.fetchCPGroupByGroupId(
				group.getGroupId());

			if (cpGroup == null) {
				show = false;
			}
			else {
				show = CommerceVatPermission.contains(
					permissionChecker, group.getGroupId(),
					CommerceVatActionKeys.MANAGE_COMMERCE_VAT_NUMBERS);
			}
		}

		return show;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CommerceVatPortletKeys.COMMERCE_VAT_NUMBER + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

	@Reference
	private CPGroupService _cpGroupService;

}