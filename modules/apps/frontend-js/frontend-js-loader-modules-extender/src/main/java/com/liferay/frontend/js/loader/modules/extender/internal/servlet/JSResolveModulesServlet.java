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

package com.liferay.frontend.js.loader.modules.extender.internal.servlet;

import com.liferay.frontend.js.loader.modules.extender.internal.configuration.Details;
import com.liferay.frontend.js.loader.modules.extender.internal.npm.NPMRegistryResolutionStateDigestUtil;
import com.liferay.frontend.js.loader.modules.extender.internal.resolution.BrowserModulesResolution;
import com.liferay.frontend.js.loader.modules.extender.internal.resolution.BrowserModulesResolver;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMRegistry;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMRegistryUpdatesListener;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilder;
import com.liferay.portal.url.builder.AbsolutePortalURLBuilderFactory;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URLDecoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodolfo Roza Miranda
 */
@Component(
	configurationPid = "com.liferay.frontend.js.loader.modules.extender.internal.configuration.Details",
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.frontend.js.loader.modules.extender.internal.servlet.JSResolveModulesServlet",
		"osgi.http.whiteboard.servlet.pattern=/js_resolve_modules/*",
		"service.ranking:Integer=" + Details.MAX_VALUE_LESS_1K
	},
	service = {
		JSResolveModulesServlet.class, NPMRegistryUpdatesListener.class,
		Servlet.class
	}
)
public class JSResolveModulesServlet
	extends HttpServlet implements NPMRegistryUpdatesListener {

	public String getURL() {
		State state = _getState();

		return state._url;
	}

	@Override
	public void onAfterUpdate() {
		_state = null;
	}

	@Override
	protected void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		State state = _getState();

		if (state == null) {
			httpServletResponse.sendError(
				HttpServletResponse.SC_SERVICE_UNAVAILABLE);

			return;
		}

		if (!state._expectedPathInfo.equals(httpServletRequest.getPathInfo())) {
			AbsolutePortalURLBuilder absolutePortalURLBuilder =
				_absolutePortalURLBuilderFactory.getAbsolutePortalURLBuilder(
					httpServletRequest);

			String url = absolutePortalURLBuilder.forServlet(
				state._url
			).build();

			// Send a redirect so that the AMD loader knows that it must update
			// its resolve path to the new URL

			httpServletResponse.sendRedirect(url);

			return;
		}

		// See https://ashton.codes/set-cache-control-max-age-1-year

		httpServletResponse.addHeader(
			HttpHeaders.CACHE_CONTROL, "immutable, max-age=31536000, public");
		httpServletResponse.setCharacterEncoding(StringPool.UTF8);
		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		PrintWriter printWriter = new PrintWriter(
			httpServletResponse.getOutputStream(), true);

		BrowserModulesResolution browserModulesResolution =
			_browserModulesResolver.resolve(
				_getModuleNames(httpServletRequest), httpServletRequest);

		printWriter.write(browserModulesResolution.toJSON());

		printWriter.close();
	}

	private List<String> _getModuleNames(HttpServletRequest httpServletRequest)
		throws IOException {

		String[] moduleNames = null;

		String method = httpServletRequest.getMethod();

		if (method.equals("GET")) {
			moduleNames = ParamUtil.getStringValues(
				httpServletRequest, "modules");
		}
		else {
			String body = StringUtil.read(httpServletRequest.getInputStream());

			body = URLDecoder.decode(
				body, httpServletRequest.getCharacterEncoding());

			if (!body.isEmpty()) {
				body = body.substring(8);

				moduleNames = body.split(StringPool.COMMA);
			}
		}

		if (moduleNames != null) {
			return Arrays.asList(moduleNames);
		}

		return Collections.emptyList();
	}

	private State _getState() {
		State state = _state;

		if (state != null) {
			return state;
		}

		synchronized (this) {
			if (_state != null) {
				return _state;
			}

			state = new State(
				NPMRegistryResolutionStateDigestUtil.digest(_npmRegistry));

			_state = state;
		}

		return state;
	}

	@Reference
	private AbsolutePortalURLBuilderFactory _absolutePortalURLBuilderFactory;

	@Reference
	private BrowserModulesResolver _browserModulesResolver;

	@Reference
	private NPMRegistry _npmRegistry;

	private volatile State _state;

	private static class State {

		private State(String digest) {
			_expectedPathInfo = StringPool.SLASH + digest;
			_url = "/js_resolve_modules/" + digest;
		}

		private final String _expectedPathInfo;
		private final String _url;

	}

}