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

package com.liferay.commerce.cart.content.web.internal.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.BaseJSPDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = DynamicInclude.class)
public class AddToCartButtonDynamicInclude extends BaseJSPDynamicInclude{

	@Override
	protected String getJspPath() {
		return "/add_to_cart_button.jsp";
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
				"com.liferay.commerce.product.content.web#/add_to_cart#");
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.cart.content.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddToCartButtonDynamicInclude.class);

}
