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

package com.liferay.content.dashboard.journal.internal.item.action.provider;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.content.dashboard.item.action.ContentDashboardItemAction;
import com.liferay.content.dashboard.item.action.provider.ContentDashboardItemActionProvider;
import com.liferay.content.dashboard.journal.internal.item.action.ViewJournalArticleContentDashboardItemAction;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = ContentDashboardItemActionProvider.class)
public class ViewJournalArticleContentDashboardItemActionProvider
	implements ContentDashboardItemActionProvider<JournalArticle> {

	@Override
	public ContentDashboardItemAction getContentDashboardItemAction(
		JournalArticle journalArticle, HttpServletRequest httpServletRequest) {

		return new ViewJournalArticleContentDashboardItemAction(
			_assetDisplayPageFriendlyURLProvider, httpServletRequest,
			journalArticle, _language);
	}

	@Override
	public String getKey() {
		return "view";
	}

	@Override
	public ContentDashboardItemAction.Type getType() {
		return ContentDashboardItemAction.Type.VIEW;
	}

	@Override
	public boolean isShow(
		JournalArticle journalArticle, HttpServletRequest httpServletRequest) {

		if (!journalArticle.hasApprovedVersion()) {
			return false;
		}

		ContentDashboardItemAction contentDashboardItemAction =
			getContentDashboardItemAction(journalArticle, httpServletRequest);

		if (Validator.isNull(contentDashboardItemAction.getURL())) {
			return false;
		}

		return true;
	}

	@Reference
	private AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;

	@Reference
	private Language _language;

}