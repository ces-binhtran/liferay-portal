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

package com.liferay.commerce.shipping.engine.fixed.web.internal.display.context;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceShippingMethodService;
import com.liferay.commerce.shipping.web.admin.ShippingMethodsCommerceAdminModule;
import com.liferay.commerce.shipping.web.servlet.taglib.ui.CommerceShippingScreenNavigationConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public abstract class BaseCommerceShippingFixedOptionDisplayContext<T> {

	public BaseCommerceShippingFixedOptionDisplayContext(
		CommerceCurrencyService commerceCurrencyService,
		CommerceShippingMethodService commerceShippingMethodService,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		this.commerceCurrencyService = commerceCurrencyService;
		this.commerceShippingMethodService = commerceShippingMethodService;
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;

		_defaultOrderByCol = "priority";
		_defaultOrderByType = "asc";
	}

	public String getCommerceCurrencyCode() {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CommerceCurrency commerceCurrency =
			commerceCurrencyService.fetchPrimaryCommerceCurrency(
				themeDisplay.getScopeGroupId());

		if (commerceCurrency != null) {
			return commerceCurrency.getCode();
		}

		return StringPool.BLANK;
	}

	public CommerceShippingMethod getCommerceShippingMethod()
		throws PortalException {

		if (_commerceShippingMethod != null) {
			return _commerceShippingMethod;
		}

		long commerceShippingMethodId = ParamUtil.getLong(
			renderRequest, "commerceShippingMethodId");

		if (commerceShippingMethodId > 0) {
			_commerceShippingMethod =
				commerceShippingMethodService.getCommerceShippingMethod(
					commerceShippingMethodId);
		}

		return _commerceShippingMethod;
	}

	public long getCommerceShippingMethodId() throws PortalException {
		CommerceShippingMethod commerceShippingMethod =
			getCommerceShippingMethod();

		if (commerceShippingMethod == null) {
			return 0;
		}

		return commerceShippingMethod.getCommerceShippingMethodId();
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			renderRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			_defaultOrderByCol);
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			renderRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			_defaultOrderByType);
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey", ShippingMethodsCommerceAdminModule.KEY);
		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceShippingMethod");
		portletURL.setParameter(
			"screenNavigationEntryKey", getSelectedScreenNavigationEntryKey());

		CommerceShippingMethod commerceShippingMethod =
			getCommerceShippingMethod();

		if (commerceShippingMethod != null) {
			portletURL.setParameter(
				"commerceShippingMethodId",
				String.valueOf(
					commerceShippingMethod.getCommerceShippingMethodId()));
		}

		String engineKey = ParamUtil.getString(renderRequest, "engineKey");

		if (Validator.isNotNull(engineKey)) {
			portletURL.setParameter("engineKey", engineKey);
		}

		String delta = ParamUtil.getString(renderRequest, "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(renderResponse);
		}

		return _rowChecker;
	}

	public String getScreenNavigationEntryKey() {
		return CommerceShippingScreenNavigationConstants.
			ENTRY_KEY_COMMERCE_SHIPPING_METHOD_DETAILS;
	}

	public abstract SearchContainer<T> getSearchContainer()
		throws PortalException;

	public void setDefaultOrderByCol(String defaultOrderByCol) {
		_defaultOrderByCol = defaultOrderByCol;
	}

	public void setDefaultOrderByType(String defaultOrderByType) {
		_defaultOrderByType = defaultOrderByType;
	}

	protected String getSelectedScreenNavigationEntryKey() {
		return ParamUtil.getString(
			renderRequest, "screenNavigationEntryKey",
			getScreenNavigationEntryKey());
	}

	protected final CommerceCurrencyService commerceCurrencyService;
	protected final CommerceShippingMethodService commerceShippingMethodService;
	protected final RenderRequest renderRequest;
	protected final RenderResponse renderResponse;
	protected SearchContainer<T> searchContainer;

	private CommerceShippingMethod _commerceShippingMethod;
	private String _defaultOrderByCol;
	private String _defaultOrderByType;
	private RowChecker _rowChecker;

}