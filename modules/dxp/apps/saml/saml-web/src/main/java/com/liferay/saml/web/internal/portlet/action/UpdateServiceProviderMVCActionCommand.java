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

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.saml.constants.SamlPortletKeys;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/update_service_provider"
	},
	service = MVCActionCommand.class
)
public class UpdateServiceProviderMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UnicodeProperties unicodeProperties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");

		_samlProviderConfigurationHelper.updateProperties(unicodeProperties);

		actionResponse.setRenderParameter(
			"mvcRenderCommandName", "/admin/view");
		actionResponse.setRenderParameter("tabs1", "service-provider");
	}

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

}