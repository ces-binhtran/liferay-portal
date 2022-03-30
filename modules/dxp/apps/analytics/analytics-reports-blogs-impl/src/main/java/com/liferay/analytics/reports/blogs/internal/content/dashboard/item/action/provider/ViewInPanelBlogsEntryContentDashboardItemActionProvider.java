/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.analytics.reports.blogs.internal.content.dashboard.item.action.provider;

import com.liferay.analytics.reports.info.action.provider.AnalyticsReportsContentDashboardItemActionProvider;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.content.dashboard.item.action.ContentDashboardItemAction;
import com.liferay.content.dashboard.item.action.exception.ContentDashboardItemActionException;
import com.liferay.content.dashboard.item.action.provider.ContentDashboardItemActionProvider;
import com.liferay.info.item.InfoItemReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = ContentDashboardItemActionProvider.class)
public class ViewInPanelBlogsEntryContentDashboardItemActionProvider
	implements ContentDashboardItemActionProvider<BlogsEntry> {

	@Override
	public ContentDashboardItemAction getContentDashboardItemAction(
			BlogsEntry blogsEntry, HttpServletRequest httpServletRequest)
		throws ContentDashboardItemActionException {

		if (!isShow(blogsEntry, httpServletRequest)) {
			return null;
		}

		return _analyticsReportsContentDashboardItemActionProvider.
			getContentDashboardItemAction(
				httpServletRequest,
				new InfoItemReference(
					BlogsEntry.class.getName(), blogsEntry.getEntryId()));
	}

	@Override
	public String getKey() {
		return "showMetrics";
	}

	@Override
	public ContentDashboardItemAction.Type getType() {
		return ContentDashboardItemAction.Type.VIEW_IN_PANEL;
	}

	@Override
	public boolean isShow(
		BlogsEntry blogsEntry, HttpServletRequest httpServletRequest) {

		if (blogsEntry.isDraft() || blogsEntry.isInTrash()) {
			return false;
		}

		try {
			return _analyticsReportsContentDashboardItemActionProvider.
				isShowContentDashboardItemAction(
					httpServletRequest,
					new InfoItemReference(
						BlogsEntry.class.getName(), blogsEntry.getEntryId()));
		}
		catch (PortalException portalException) {
			_log.error(portalException);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewInPanelBlogsEntryContentDashboardItemActionProvider.class);

	@Reference
	private AnalyticsReportsContentDashboardItemActionProvider
		_analyticsReportsContentDashboardItemActionProvider;

}