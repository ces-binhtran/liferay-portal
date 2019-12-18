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

package com.liferay.layout.seo.web.internal.display.context;

import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.layout.seo.model.LayoutSEOSite;
import com.liferay.layout.seo.open.graph.OpenGraphConfiguration;
import com.liferay.layout.seo.service.LayoutSEOSiteLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public class OpenGraphSettingsDisplayContext {

	public OpenGraphSettingsDisplayContext(
		DLAppService dlAppService, DLURLHelper dlurlHelper,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		LayoutSEOSiteLocalService layoutSEOSiteLocalService,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		OpenGraphConfiguration openGraphConfiguration) {

		_dlAppService = dlAppService;
		_dlurlHelper = dlurlHelper;
		_httpServletRequest = httpServletRequest;
		_itemSelector = itemSelector;
		_layoutSEOSiteLocalService = layoutSEOSiteLocalService;
		_liferayPortletResponse = liferayPortletResponse;
		_openGraphConfiguration = openGraphConfiguration;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getItemSelectorURL() {
		ItemSelectorCriterion imageItemSelectorCriterion =
			new ImageItemSelectorCriterion();

		imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new FileEntryItemSelectorReturnType(),
			new URLItemSelectorReturnType());

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(_httpServletRequest),
			_liferayPortletResponse.getNamespace() +
				"openGraphImageSelectedItem",
			imageItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public long getOpenGraphImageFileEntryId() {
		LayoutSEOSite selLayoutSEOSite = getSelLayoutSEOSite();

		if (selLayoutSEOSite == null) {
			return 0;
		}

		return selLayoutSEOSite.getOpenGraphImageFileEntryId();
	}

	public String getOpenGraphImageTitle() throws Exception {
		LayoutSEOSite selLayoutSEOSite = getSelLayoutSEOSite();

		if ((selLayoutSEOSite == null) ||
			(selLayoutSEOSite.getOpenGraphImageFileEntryId() == 0)) {

			return null;
		}

		FileEntry fileEntry = _dlAppService.getFileEntry(
			selLayoutSEOSite.getOpenGraphImageFileEntryId());

		return fileEntry.getTitle();
	}

	public String getOpenGraphImageURL() throws Exception {
		LayoutSEOSite selLayoutSEOSite = getSelLayoutSEOSite();

		if ((selLayoutSEOSite == null) ||
			(selLayoutSEOSite.getOpenGraphImageFileEntryId() == 0)) {

			return null;
		}

		return _dlurlHelper.getImagePreviewURL(
			_dlAppService.getFileEntry(
				selLayoutSEOSite.getOpenGraphImageFileEntryId()),
			_themeDisplay);
	}

	public LayoutSEOSite getSelLayoutSEOSite() {
		Group group = _getGroup();

		return _layoutSEOSiteLocalService.fetchLayoutSEOSiteByGroupId(
			group.getGroupId());
	}

	public boolean isOpenGraphEnabled() throws PortalException {
		return _openGraphConfiguration.isOpenGraphEnabled(_getGroup());
	}

	private Group _getGroup() {
		Group liveGroup = (Group)_httpServletRequest.getAttribute(
			"site.liveGroup");

		if (liveGroup != null) {
			return liveGroup;
		}

		return (Group)_httpServletRequest.getAttribute("site.group");
	}

	private final DLAppService _dlAppService;
	private final DLURLHelper _dlurlHelper;
	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;
	private final LayoutSEOSiteLocalService _layoutSEOSiteLocalService;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final OpenGraphConfiguration _openGraphConfiguration;
	private final ThemeDisplay _themeDisplay;

}