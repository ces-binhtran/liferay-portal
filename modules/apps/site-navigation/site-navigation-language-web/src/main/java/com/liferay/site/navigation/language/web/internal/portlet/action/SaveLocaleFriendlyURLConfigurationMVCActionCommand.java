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

package com.liferay.site.navigation.language.web.internal.portlet.action;

import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.portal.kernel.model.PortalPreferences;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.PortalPreferenceValueLocalService;
import com.liferay.portal.kernel.service.PortalPreferencesLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Albert Gomes Cabral
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/portal_settings/save_locale_friendly_url_configuration"
	},
	service = MVCActionCommand.class
)
public class SaveLocaleFriendlyURLConfigurationMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin(themeDisplay.getCompanyId())) {
			SessionErrors.add(actionRequest, PrincipalException.class);

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");

			return;
		}

		PropsUtil.set(
			PropsKeys.LOCALE_PREPEND_FRIENDLY_URL_STYLE,
			ParamUtil.getString(
				actionRequest, "localePrependFriendlyURLStyle"));

		PropsValues.LOCALE_PREPEND_FRIENDLY_URL_STYLE = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.LOCALE_PREPEND_FRIENDLY_URL_STYLE));

		PortalPreferences portalPreferences =
			_portalPreferencesLocalService.fetchPortalPreferences(
				themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY);

		com.liferay.portal.kernel.portlet.PortalPreferences
			newPortalPreferences =
				_portalPreferenceValueLocalService.getPortalPreferences(
					portalPreferences, false);

		newPortalPreferences.setValue(
			null, "localePrependFriendlyURLStyle",
			ParamUtil.getString(
				actionRequest, "localePrependFriendlyURLStyle"));

		_portalPreferencesLocalService.updatePreferences(
			themeDisplay.getCompanyId(), PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			newPortalPreferences);
	}

	@Reference
	private PortalPreferencesLocalService _portalPreferencesLocalService;

	@Reference
	private PortalPreferenceValueLocalService
		_portalPreferenceValueLocalService;

}