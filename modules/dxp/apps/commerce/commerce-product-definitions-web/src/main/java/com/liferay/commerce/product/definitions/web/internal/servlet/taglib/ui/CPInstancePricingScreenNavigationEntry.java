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

package com.liferay.commerce.product.definitions.web.internal.servlet.taglib.ui;

import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.product.definitions.web.internal.display.context.CPInstancePricingInfoDisplayContext;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPInstanceScreenNavigationConstants;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {"screen.navigation.entry.order:Integer=20"},
	service = ScreenNavigationEntry.class
)
public class CPInstancePricingScreenNavigationEntry
	implements ScreenNavigationEntry<CPInstance> {

	@Override
	public String getCategoryKey() {
		return CPInstanceScreenNavigationConstants.CATEGORY_KEY_DETAILS;
	}

	@Override
	public String getEntryKey() {
		return CPInstanceScreenNavigationConstants.CATEGORY_KEY_PRICING;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CPInstanceScreenNavigationConstants.CATEGORY_KEY_PRICING);
	}

	@Override
	public String getScreenNavigationKey() {
		return CPInstanceScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_CP_INSTANCE_GENERAL;
	}

	@Override
	public boolean isVisible(User user, CPInstance cpInstance) {
		if (cpInstance == null) {
			return false;
		}

		return true;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			CPInstancePricingInfoDisplayContext
				cpInstancePricingInfoDisplayContext =
					new CPInstancePricingInfoDisplayContext(
						_actionHelper, httpServletRequest,
						_cpDefinitionOptionRelService, _cpInstanceService,
						_cpInstanceHelper, _commerceCurrencyService);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpInstancePricingInfoDisplayContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_jspRenderer.renderJSP(
			_setServletContext, httpServletRequest, httpServletResponse,
			"/edit_instance_pricing_info.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstancePricingScreenNavigationEntry.class);

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CPDefinitionOptionRelService _cpDefinitionOptionRelService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.definitions.web)"
	)
	private ServletContext _setServletContext;

}