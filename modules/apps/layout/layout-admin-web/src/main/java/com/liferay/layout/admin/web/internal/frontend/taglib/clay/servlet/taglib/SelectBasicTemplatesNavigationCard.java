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

package com.liferay.layout.admin.web.internal.frontend.taglib.clay.servlet.taglib;

import com.liferay.frontend.taglib.clay.servlet.taglib.NavigationCard;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.LayoutTypeControllerTracker;

import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class SelectBasicTemplatesNavigationCard implements NavigationCard {

	public SelectBasicTemplatesNavigationCard(
		String type, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_type = type;
		_renderResponse = renderResponse;

		_layoutTypeController =
			LayoutTypeControllerTracker.getLayoutTypeController(type);
		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public String getCssClass() {
		return "add-layout-action-option";
	}

	@Override
	public Map<String, String> getDynamicAttributes() {
		return HashMapBuilder.put(
			"data-add-layout-url",
			PortletURLBuilder.createRenderURL(
				_renderResponse
			).setMVCRenderCommandName(
				"/layout_admin/add_layout"
			).setBackURL(
				ParamUtil.getString(_httpServletRequest, "redirect")
			).setParameter(
				"privateLayout",
				ParamUtil.getBoolean(_httpServletRequest, "privateLayout")
			).setParameter(
				"selPlid", ParamUtil.getLong(_httpServletRequest, "selPlid")
			).setParameter(
				"type", _type
			).setWindowState(
				LiferayWindowState.POP_UP
			).buildString()
		).put(
			"role", "button"
		).put(
			"tabIndex", "0"
		).build();
	}

	@Override
	public String getIcon() {
		return "page";
	}

	@Override
	public String getTitle() {
		ResourceBundle layoutTypeResourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", _themeDisplay.getLocale(),
			_layoutTypeController.getClass());

		return LanguageUtil.get(
			_httpServletRequest, layoutTypeResourceBundle,
			"layout.types." + _type);
	}

	@Override
	public Boolean isSmall() {
		return true;
	}

	private final HttpServletRequest _httpServletRequest;
	private final LayoutTypeController _layoutTypeController;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;
	private final String _type;

}