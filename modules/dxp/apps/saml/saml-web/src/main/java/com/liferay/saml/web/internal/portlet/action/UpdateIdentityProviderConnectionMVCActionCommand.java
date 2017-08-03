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

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;
import com.liferay.saml.persistence.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.web.internal.constants.SamlAdminPortletKeys;

import java.io.InputStream;

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
		"javax.portlet.name=" + SamlAdminPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/updateIdentityProviderConnection"
	},
	service = MVCActionCommand.class
)
public class UpdateIdentityProviderConnectionMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long samlSpIdpConnectionId = ParamUtil.getLong(
			uploadPortletRequest, "samlSpIdpConnectionId");

		String samlIdpEntityId = ParamUtil.getString(
			uploadPortletRequest, "samlIdpEntityId");
		boolean assertionSignatureRequired = ParamUtil.getBoolean(
			uploadPortletRequest, "assertionSignatureRequired");
		long clockSkew = ParamUtil.getLong(uploadPortletRequest, "clockSkew");

		boolean enabled = true;
		boolean forceAuthn = ParamUtil.getBoolean(
			uploadPortletRequest, "forceAuthn");
		boolean ldapImportEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "ldapImportEnabled");
		String metadataUrl = ParamUtil.getString(
			uploadPortletRequest, "metadataUrl");
		InputStream metadataXmlInputStream =
			uploadPortletRequest.getFileAsStream("metadataXml");
		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String nameIdFormat = ParamUtil.getString(
			uploadPortletRequest, "nameIdFormat");
		boolean signAuthnRequest = ParamUtil.getBoolean(
			uploadPortletRequest, "signAuthnRequest");
		String userAttributeMappings = ParamUtil.getString(
			uploadPortletRequest, "userAttributeMappings");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			SamlSpIdpConnection.class.getName(), actionRequest);

		if (samlSpIdpConnectionId <= 0) {
			_samlSpIdpConnectionLocalService.addSamlSpIdpConnection(
				samlIdpEntityId, assertionSignatureRequired, clockSkew, enabled,
				forceAuthn, ldapImportEnabled, metadataUrl,
				metadataXmlInputStream, name, nameIdFormat, signAuthnRequest,
				userAttributeMappings, serviceContext);
		}
		else {
			_samlSpIdpConnectionLocalService.updateSamlSpIdpConnection(
				samlSpIdpConnectionId, samlIdpEntityId,
				assertionSignatureRequired, clockSkew, enabled, forceAuthn,
				ldapImportEnabled, metadataUrl, metadataXmlInputStream, name,
				nameIdFormat, signAuthnRequest, userAttributeMappings,
				serviceContext);
		}

		UnicodeProperties properties = new UnicodeProperties();

		properties.setProperty(
			PortletPropsKeys.SAML_SP_DEFAULT_IDP_ENTITY_ID, samlIdpEntityId);

		_samlProviderConfigurationHelper.updateProperties(properties);

		actionResponse.setRenderParameter("mvcRenderCommandName", "/admin");
		actionResponse.setRenderParameter(
			"tabs1", "identity-provider-connection");
	}

	@Reference
	private Portal _portal;

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

}