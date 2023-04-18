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

package com.liferay.commerce.product.asset.categories.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.commerce.product.url.CPFriendlyURL;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pedro Victor Silvestre
 */
@Component(
	property = "screen.navigation.entry.order:Integer=20",
	service = ScreenNavigationEntry.class
)
public class CategoryCPFriendlyURLScreenNavigationEntry
	extends CategoryCPFriendlyURLScreenNavigationCategory
	implements ScreenNavigationEntry<AssetCategory> {

	@Override
	public String getEntryKey() {
		return getCategoryKey();
	}

	@Override
	public boolean isVisible(User user, AssetCategory assetCategory) {
		if (assetCategory == null) {
			return false;
		}

		return true;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		AssetCategory assetCategory = null;

		long categoryId = ParamUtil.getLong(httpServletRequest, "categoryId");
		long classNameId = _portal.getClassNameId(AssetCategory.class);

		String titleMapAsXML = StringPool.BLANK;

		try {
			assetCategory = _assetCategoryService.fetchCategory(categoryId);

			if (assetCategory != null) {
				FriendlyURLEntry friendlyURLEntry =
					_friendlyURLEntryLocalService.getMainFriendlyURLEntry(
						classNameId, categoryId);

				titleMapAsXML = friendlyURLEntry.getUrlTitleMapAsXML();
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		httpServletRequest.setAttribute("assetCategory", assetCategory);
		httpServletRequest.setAttribute(
			"assetCategoryURLSeparator",
			_cpFriendlyURL.getAssetCategoryURLSeparator(
				_portal.getCompanyId(httpServletRequest)));
		httpServletRequest.setAttribute("titleMapAsXML", titleMapAsXML);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/friendly_url.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CategoryCPFriendlyURLScreenNavigationEntry.class);

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private CPFriendlyURL _cpFriendlyURL;

	@Reference
	private FriendlyURLEntryLocalService _friendlyURLEntryLocalService;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.asset.categories.web)"
	)
	private ServletContext _servletContext;

}