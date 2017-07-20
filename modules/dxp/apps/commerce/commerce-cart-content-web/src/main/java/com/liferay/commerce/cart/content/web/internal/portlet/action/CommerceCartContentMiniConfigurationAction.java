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

package com.liferay.commerce.cart.content.web.internal.portlet.action;

import com.liferay.commerce.cart.constants.CommerceCartPortletKeys;
import com.liferay.commerce.cart.content.web.internal.display.context.CommerceCartContentDisplayContext;
import com.liferay.commerce.cart.content.web.internal.portlet.CommerceCartContentMiniPortlet;
import com.liferay.commerce.cart.service.CommerceCartItemLocalService;
import com.liferay.commerce.cart.service.CommerceCartLocalService;
import com.liferay.commerce.product.service.CPFriendlyURLEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CommerceCartPortletKeys.COMMERCE_CART_CONTENT_MINI,
	service = ConfigurationAction.class
)
public class CommerceCartContentMiniConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		try {
			CommerceCartContentDisplayContext
				commerceCartContentDisplayContext =
					new CommerceCartContentDisplayContext(
						request, _commerceCartItemLocalService,
						_commerceCartLocalService,
						_cpFriendlyURLEntryLocalService, _portal,
						CommerceCartContentMiniPortlet.class.getSimpleName());

			request.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceCartContentDisplayContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return "/cart_mini/configuration.jsp";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.cart.content.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCartContentMiniConfigurationAction.class);

	@Reference
	private CommerceCartItemLocalService _commerceCartItemLocalService;

	@Reference
	private CommerceCartLocalService _commerceCartLocalService;

	@Reference
	private CPFriendlyURLEntryLocalService _cpFriendlyURLEntryLocalService;

	@Reference
	private Portal _portal;

}