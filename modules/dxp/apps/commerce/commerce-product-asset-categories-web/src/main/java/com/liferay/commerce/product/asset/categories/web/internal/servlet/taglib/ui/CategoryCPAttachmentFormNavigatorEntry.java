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

package com.liferay.commerce.product.asset.categories.web.internal.servlet.taglib.ui;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {"form.navigator.entry.order:Integer=300"},
	service = FormNavigatorEntry.class
)
public class CategoryCPAttachmentFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<AssetCategory> {

	@Override
	public String getCategoryKey() {
		return "images";
	}

	@Override
	public String getFormNavigatorId() {
		return "edit.category.form";
	}

	@Override
	public String getKey() {
		return "images";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		AssetCategory assetCategory = null;

		long categoryId = ParamUtil.getLong(request, "categoryId");

		try {
			assetCategory = _assetCategoryService.fetchCategory(categoryId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		request.setAttribute(WebKeys.ASSET_CATEGORY, assetCategory);
		request.setAttribute(
			"cpAttachmentFileEntryService", cpAttachmentFileEntryService);

		super.include(request, response);
	}

	@Override
	public boolean isVisible(User user, AssetCategory formModelBean) {
		if (formModelBean != null) {
			return true;
		}

		return false;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.asset.categories.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/images.jsp";
	}

	@Reference
	protected CPAttachmentFileEntryService cpAttachmentFileEntryService;

	private static final Log _log = LogFactoryUtil.getLog(
		CategoryCPAttachmentFormNavigatorEntry.class);

	@Reference
	private AssetCategoryService _assetCategoryService;

}