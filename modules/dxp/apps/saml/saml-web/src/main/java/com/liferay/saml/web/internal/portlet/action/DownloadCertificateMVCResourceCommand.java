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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.constants.SamlPortletKeys;
import com.liferay.saml.runtime.metadata.LocalEntityManager;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = {
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/download_certificate"
	},
	service = MVCResourceCommand.class
)
public class DownloadCertificateMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		LocalEntityManager.CertificateUsage certificateUsage =
			LocalEntityManager.CertificateUsage.valueOf(
				ParamUtil.getString(resourceRequest, "certificateUsage"));

		String encodedCertificate =
			_localEntityManager.getEncodedLocalEntityCertificate(
				certificateUsage);

		if (Validator.isNull(encodedCertificate)) {
			return;
		}

		String content = StringBundler.concat(
			"-----BEGIN CERTIFICATE-----\r\n", encodedCertificate,
			"\r\n-----END CERTIFICATE-----");

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse,
			StringBundler.concat(
				_localEntityManager.getLocalEntityId(), StringPool.DASH,
				certificateUsage.name(), ".pem"),
			content.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Reference
	private LocalEntityManager _localEntityManager;

}