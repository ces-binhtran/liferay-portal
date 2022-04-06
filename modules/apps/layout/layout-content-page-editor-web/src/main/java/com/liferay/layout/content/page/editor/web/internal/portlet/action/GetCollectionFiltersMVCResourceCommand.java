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

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.collection.filter.FragmentCollectionFilter;
import com.liferay.fragment.collection.filter.FragmentCollectionFilterTracker;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pablo Molina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/layout_content_page_editor/get_collection_filters"
	},
	service = MVCResourceCommand.class
)
public class GetCollectionFiltersMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject fragmentCollectionFiltersJSONObject =
			JSONFactoryUtil.createJSONObject();

		for (FragmentCollectionFilter fragmentCollectionFilter :
				_fragmentCollectionFilterTracker.
					getFragmentCollectionFilters()) {

			fragmentCollectionFiltersJSONObject.put(
				fragmentCollectionFilter.getFilterKey(),
				JSONUtil.put(
					"configuration",
					_getConfigurationJSONObject(
						fragmentCollectionFilter.getConfiguration())
				).put(
					"key", fragmentCollectionFilter.getFilterKey()
				).put(
					"label",
					fragmentCollectionFilter.getLabel(themeDisplay.getLocale())
				));
		}

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse,
			fragmentCollectionFiltersJSONObject);
	}

	private JSONObject _getConfigurationJSONObject(String configuration) {
		try {
			return JSONFactoryUtil.createJSONObject(configuration);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}

			return JSONFactoryUtil.createJSONObject();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GetCollectionFiltersMVCResourceCommand.class);

	@Reference
	private FragmentCollectionFilterTracker _fragmentCollectionFilterTracker;

}