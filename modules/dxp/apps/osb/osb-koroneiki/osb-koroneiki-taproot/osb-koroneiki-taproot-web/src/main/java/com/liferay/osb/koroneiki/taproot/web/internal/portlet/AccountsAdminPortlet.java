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

package com.liferay.osb.koroneiki.taproot.web.internal.portlet;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=koroneiki-accounts-admin-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.render-weight=0",
		"javax.portlet.display-name=Accounts",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class AccountsAdminPortlet extends BaseAdminPortlet {
}