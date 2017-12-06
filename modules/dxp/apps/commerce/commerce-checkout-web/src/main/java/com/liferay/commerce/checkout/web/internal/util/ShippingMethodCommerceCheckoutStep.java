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

package com.liferay.commerce.checkout.web.internal.util;

import com.liferay.commerce.checkout.web.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.checkout.web.internal.display.context.ShippingMethodCheckoutStepDisplayContext;
import com.liferay.commerce.checkout.web.util.CommerceCheckoutStep;
import com.liferay.commerce.exception.CommerceCartShippingMethodException;
import com.liferay.commerce.model.CommerceCart;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceShippingOption;
import com.liferay.commerce.service.CommerceCartService;
import com.liferay.commerce.service.CommerceShippingMethodService;
import com.liferay.commerce.util.CommerceCartHelper;
import com.liferay.commerce.util.CommercePriceFormatter;
import com.liferay.commerce.util.CommerceShippingEngineRegistry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	immediate = true,
	property = {
		"commerce.checkout.step.name=" + ShippingMethodCommerceCheckoutStep.NAME,
		"commerce.checkout.step.order:Integer=30"
	},
	service = CommerceCheckoutStep.class
)
public class ShippingMethodCommerceCheckoutStep
	implements CommerceCheckoutStep {

	public static final char COMMERCE_SHIPPING_OPTION_KEY_SEPARATOR =
		CharPool.POUND;

	public static final String NAME = "shipping-method";

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, NAME);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isActive(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<CommerceShippingMethod> commerceShippingMethods =
			_commerceShippingMethodService.getCommerceShippingMethods(
				themeDisplay.getScopeGroupId(), true);

		return !commerceShippingMethods.isEmpty();
	}

	@Override
	public boolean isVisible(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		return isActive(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateCommerceCartShippingMethod(actionRequest);
		}
		catch (Exception e) {
			if (e instanceof CommerceCartShippingMethodException) {
				SessionErrors.add(actionRequest, e.getClass());

				return false;
			}

			throw e;
		}

		return true;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		ShippingMethodCheckoutStepDisplayContext
			shippingMethodCheckoutStepDisplayContext =
				new ShippingMethodCheckoutStepDisplayContext(
					_commerceCartHelper, _commercePriceFormatter,
					_commerceShippingEngineRegistry,
					_commerceShippingMethodService, httpServletRequest,
					httpServletResponse);

		httpServletRequest.setAttribute(
			CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT,
			shippingMethodCheckoutStepDisplayContext);

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/checkout_step/shipping_method.jsp");
	}

	@Override
	public boolean showControls(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		return true;
	}

	protected double getShippingPrice(
			CommerceCart commerceCart, long commerceShippingMethodId,
			String shippingOptionName, Locale locale)
		throws PortalException {

		CommerceShippingMethod commerceShippingMethod =
			_commerceShippingMethodService.getCommerceShippingMethod(
				commerceShippingMethodId);

		if (!commerceShippingMethod.isActive()) {
			throw new CommerceCartShippingMethodException(
				"Shipping method " +
					commerceShippingMethod.getCommerceShippingMethodId() +
						" is not active");
		}

		CommerceShippingEngine commerceShippingEngine =
			_commerceShippingEngineRegistry.getCommerceShippingEngine(
				commerceShippingMethod.getEngineKey());

		List<CommerceShippingOption> commerceShippingOptions =
			commerceShippingEngine.getCommerceShippingOptions(
				commerceCart, locale);

		for (CommerceShippingOption commerceShippingOption :
				commerceShippingOptions) {

			if (shippingOptionName.equals(commerceShippingOption.getName())) {
				return commerceShippingOption.getAmount();
			}
		}

		throw new CommerceCartShippingMethodException(
			"Unable to get amount of option \"" + shippingOptionName +
				"\" for shipping method " +
					commerceShippingMethod.getCommerceShippingMethodId());
	}

	protected void updateCommerceCartShippingMethod(ActionRequest actionRequest)
		throws Exception {

		String commerceShippingOptionKey = ParamUtil.getString(
			actionRequest, "commerceShippingOptionKey");

		if (Validator.isNull(commerceShippingOptionKey)) {
			throw new CommerceCartShippingMethodException();
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long commerceCartId = ParamUtil.getLong(
			actionRequest, "commerceCartId");

		CommerceCart commerceCart = _commerceCartService.getCommerceCart(
			commerceCartId);

		int pos = commerceShippingOptionKey.indexOf(
			COMMERCE_SHIPPING_OPTION_KEY_SEPARATOR);

		long commerceShippingMethodId = Long.valueOf(
			commerceShippingOptionKey.substring(0, pos));
		String shippingOptionName = commerceShippingOptionKey.substring(
			pos + 1);

		double shippingPrice = getShippingPrice(
			commerceCart, commerceShippingMethodId, shippingOptionName,
			themeDisplay.getLocale());

		_commerceCartService.updateCommerceCart(
			commerceCart.getCommerceCartId(),
			commerceCart.getBillingAddressId(),
			commerceCart.getShippingAddressId(),
			commerceCart.getCommercePaymentMethodId(), commerceShippingMethodId,
			shippingOptionName, shippingPrice);
	}

	@Reference
	private CommerceCartHelper _commerceCartHelper;

	@Reference
	private CommerceCartService _commerceCartService;

	@Reference
	private CommercePriceFormatter _commercePriceFormatter;

	@Reference
	private CommerceShippingEngineRegistry _commerceShippingEngineRegistry;

	@Reference
	private CommerceShippingMethodService _commerceShippingMethodService;

	@Reference
	private JSPRenderer _jspRenderer;

}