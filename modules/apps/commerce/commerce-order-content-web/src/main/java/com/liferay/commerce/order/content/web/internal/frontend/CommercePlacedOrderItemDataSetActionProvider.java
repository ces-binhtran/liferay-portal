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

package com.liferay.commerce.order.content.web.internal.frontend;

import com.liferay.commerce.frontend.ClayMenuActionItem;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetAction;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetActionProvider;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.content.web.internal.frontend.util.CommerceOrderClayTableUtil;
import com.liferay.commerce.order.content.web.internal.model.OrderItem;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PLACED_ORDER_ITEMS,
	service = ClayDataSetActionProvider.class
)
public class CommercePlacedOrderItemDataSetActionProvider
	implements ClayDataSetActionProvider {

	@Override
	public List<ClayDataSetAction> clayDataSetActions(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		List<ClayDataSetAction> clayDataSetActions = new ArrayList<>();

		OrderItem orderItem = (OrderItem)model;

		if (orderItem.getParentOrderItemId() > 0) {
			return clayDataSetActions;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			orderItem.getOrderId());

		if (_modelResourcePermission.contains(
				themeDisplay.getPermissionChecker(), commerceOrder,
				ActionKeys.VIEW)) {

			String viewShipmentURL =
				CommerceOrderClayTableUtil.getViewShipmentURL(
					orderItem.getOrderItemId(), themeDisplay);

			ClayDataSetAction viewShipmentsClayDataSetAction =
				new ClayDataSetAction(
					StringPool.BLANK, viewShipmentURL, StringPool.BLANK,
					LanguageUtil.get(httpServletRequest, "shipments"),
					StringPool.BLANK, false, false);

			viewShipmentsClayDataSetAction.setTarget(
				ClayMenuActionItem.CLAY_MENU_ACTION_ITEM_TARGET_MODAL);

			clayDataSetActions.add(viewShipmentsClayDataSetAction);
		}

		return clayDataSetActions;
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	private ModelResourcePermission<CommerceOrder> _modelResourcePermission;

}