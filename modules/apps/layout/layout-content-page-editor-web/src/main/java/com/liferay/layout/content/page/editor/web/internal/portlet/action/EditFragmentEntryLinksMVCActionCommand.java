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

import com.liferay.fragment.service.FragmentEntryLinkService;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/content_layout/edit_fragment_entry_links"
	},
	service = MVCActionCommand.class
)
public class EditFragmentEntryLinksMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String fragmentEntryLinks = ParamUtil.getString(
			actionRequest, "fragmentEntryLinks");

		_fragmentEntryLinkService.updateFragmentEntryLinks(
			_getFragmentEntryLinksEditableValuesMap(fragmentEntryLinks));

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private Map<Long, String> _getFragmentEntryLinksEditableValuesMap(
			String fragmentEntryLinks)
		throws Exception {

		Map<Long, String> fragmentEntryLinksEditableValuesMap = new HashMap<>();

		JSONObject fragmentEntryLinksJSONObject =
			JSONFactoryUtil.createJSONObject(fragmentEntryLinks);

		Iterator<String> iterator = fragmentEntryLinksJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			fragmentEntryLinksEditableValuesMap.put(
				GetterUtil.getLong(key),
				fragmentEntryLinksJSONObject.getString(key));
		}

		return fragmentEntryLinksEditableValuesMap;
	}

	@Reference
	private FragmentEntryLinkService _fragmentEntryLinkService;

}