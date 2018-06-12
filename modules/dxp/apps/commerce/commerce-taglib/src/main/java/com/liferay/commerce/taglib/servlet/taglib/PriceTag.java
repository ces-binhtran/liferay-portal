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

package com.liferay.commerce.taglib.servlet.taglib;

import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.price.CommerceProductPriceHelper;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionServiceUtil;
import com.liferay.commerce.product.service.CPInstanceServiceUtil;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CPDefinitionInventoryServiceUtil;
import com.liferay.commerce.taglib.servlet.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class PriceTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			CommerceContext commerceContext =
				(CommerceContext)request.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Organization organization = commerceContext.getOrganization();

			long groupId;

			if (organization == null) {
				groupId = themeDisplay.getScopeGroupId();
			}
			else {
				groupId = organization.getGroupId();
			}

			PortletResourcePermission cpPortletResourcePermission =
				ServletContextUtil.getCPPortletResourcePermission();

			if (!cpPortletResourcePermission.contains(
					themeDisplay.getPermissionChecker(), groupId,
					CPActionKeys.VIEW_PRICE)) {

				return super.doStartTag();
			}

			CPDefinition cpDefinition = CPDefinitionServiceUtil.getCPDefinition(
				_cpDefinitionId);

			if (_quantity == 0) {
				CPDefinitionInventory cpDefinitionInventory =
					CPDefinitionInventoryServiceUtil.
						fetchCPDefinitionInventoryByCPDefinitionId(
							_cpDefinitionId);

				if (cpDefinitionInventory != null) {
					_quantity = cpDefinitionInventory.getMinOrderQuantity();
				}
				else {
					_quantity =
						CPDefinitionInventoryConstants.
							DEFAULT_MULTIPLE_ORDER_QUANTITY;
				}
			}

			if (_cpInstanceId > 0) {
				_cpInstance = CPInstanceServiceUtil.getCPInstance(
					_cpInstanceId);
			}
			else {
				if (cpDefinition.isIgnoreSKUCombinations()) {
					CPInstanceHelper cpInstanceHelper =
						ServletContextUtil.getCPInstanceHelper();

					_cpInstance = cpInstanceHelper.getCPInstance(
						_cpDefinitionId, null);
				}
			}

			if (_cpInstance == null) {
				_formattedPrice = getFormattedPrice(
					_quantity, commerceContext, themeDisplay.getLocale());
			}
			else {
				CommerceMoney commerceMoney =
					commerceProductPriceHelper.getFinalPrice(
						_cpInstance.getCPInstanceId(), _quantity, true, true,
						commerceContext);

				_formattedPrice = commerceMoney.format(
					themeDisplay.getLocale());
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public void setCPDefinitionId(long cpDefinitionId) {
		_cpDefinitionId = cpDefinitionId;
	}

	public void setCPInstanceId(long cpInstanceId) {
		_cpInstanceId = cpInstanceId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		commerceProductPriceHelper =
			ServletContextUtil.getCommercePriceCalculation();

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public void setShowPriceRange(boolean showPriceRange) {
		_showPriceRange = showPriceRange;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cpDefinitionId = 0;
		_cpInstanceId = 0;
		_quantity = 0;
		_showPriceRange = false;
	}

	protected String getFormattedPrice(
			int quantity, CommerceContext commerceContext, Locale locale)
		throws PortalException {

		CommerceMoney minPriceCommerceMoney =
			commerceProductPriceHelper.getUnitMinPrice(
				_cpDefinitionId, quantity, commerceContext);

		if (!_showPriceRange) {
			return minPriceCommerceMoney.format(locale);
		}

		CommerceMoney maxPriceCommerceMoney =
			commerceProductPriceHelper.getUnitMaxPrice(
				_cpDefinitionId, quantity, commerceContext);

		return minPriceCommerceMoney.format(locale) + " - " +
			maxPriceCommerceMoney.format(locale);
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute("liferay-commerce:price:cpInstance", _cpInstance);
		request.setAttribute(
			"liferay-commerce:price:formattedPrice", _formattedPrice);
		request.setAttribute(
			"liferay-commerce:price:showPriceRange", _showPriceRange);
	}

	protected CommerceProductPriceHelper commerceProductPriceHelper;

	private static final String _PAGE = "/price/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(PriceTag.class);

	private long _cpDefinitionId;
	private CPInstance _cpInstance;
	private long _cpInstanceId;
	private String _formattedPrice;
	private int _quantity;
	private boolean _showPriceRange;

}