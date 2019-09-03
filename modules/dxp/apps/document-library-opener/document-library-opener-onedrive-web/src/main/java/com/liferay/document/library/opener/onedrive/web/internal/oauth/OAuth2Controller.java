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

package com.liferay.document.library.opener.onedrive.web.internal.oauth;

import com.liferay.document.library.opener.oauth.OAuth2State;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PwdGenerator;

import java.io.IOException;

import java.util.Objects;

import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Cristina González
 */
public class OAuth2Controller {

	public OAuth2Controller(
		OAuth2Manager oAuth2Manager, Portal portal,
		PortletURLFactory portletURLFactory) {

		_oAuth2Manager = oAuth2Manager;
		_portal = portal;
		_portletURLFactory = portletURLFactory;
	}

	public <T extends PortletRequest, R extends ActionResponse> void execute(
			T t, R r,
			UnsafeFunction<T, JSONObject, PortalException> unsafeFunction)
		throws PortalException {

		OAuth2Result oAuth2Result = _executeWithOAuth2(t, unsafeFunction);

		if (!Objects.isNull(oAuth2Result.getRedirectURL())) {
			try {
				r.sendRedirect(oAuth2Result.getRedirectURL());
			}
			catch (IOException ioe) {
				throw new PortalException(ioe);
			}
		}
	}

	protected static class OAuth2Result {

		public OAuth2Result(JSONObject response) {
			_response = response;
		}

		public OAuth2Result(String redirectURL) {
			_redirectURL = redirectURL;
		}

		public String getRedirectURL() {
			return _redirectURL;
		}

		public JSONObject getResponse() {
			return _response;
		}

		private String _redirectURL;
		private JSONObject _response;

	}

	private <T extends PortletRequest> OAuth2Result _executeWithOAuth2(
			T t, UnsafeFunction<T, JSONObject, PortalException> unsafeFunction)
		throws PortalException {

		long companyId = _portal.getCompanyId(t);
		long userId = _portal.getUserId(t);

		if (_oAuth2Manager.hasAccessToken(companyId, userId)) {
			return new OAuth2Result(unsafeFunction.apply(t));
		}

		String state = PwdGenerator.getPassword(5);

		OAuth2StateUtil.save(
			_portal.getOriginalServletRequest(_portal.getHttpServletRequest(t)),
			new OAuth2State(
				userId, _getSuccessURL(t), _getFailureURL(t), state));

		return new OAuth2Result(
			_oAuth2Manager.getAuthorizationURL(
				companyId, _portal.getPortalURL(t), state));
	}

	private String _getFailureURL(PortletRequest portletRequest)
		throws PortalException {

		LiferayPortletURL liferayPortletURL = _portletURLFactory.create(
			portletRequest, _portal.getPortletId(portletRequest),
			_portal.getControlPanelPlid(portletRequest),
			PortletRequest.RENDER_PHASE);

		return liferayPortletURL.toString();
	}

	private String _getSuccessURL(PortletRequest portletRequest) {
		return _portal.getCurrentURL(
			_portal.getHttpServletRequest(portletRequest));
	}

	private final OAuth2Manager _oAuth2Manager;
	private final Portal _portal;
	private final PortletURLFactory _portletURLFactory;

}