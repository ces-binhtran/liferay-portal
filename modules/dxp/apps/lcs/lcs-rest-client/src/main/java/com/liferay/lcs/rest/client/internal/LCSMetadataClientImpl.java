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

package com.liferay.lcs.rest.client.internal;

import com.liferay.lcs.rest.client.LCSMetadata;
import com.liferay.lcs.rest.client.LCSMetadataClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = LCSMetadataClient.class)
public class LCSMetadataClientImpl implements LCSMetadataClient {

	@Override
	public int getSupportedLCSPortlet(String buildNumber, String portalEdition)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException {

		StringBuilder sb = new StringBuilder(5);

		sb.append(_URL_LCS_METADATA);
		sb.append("/find/");
		sb.append(buildNumber);
		sb.append("/");
		sb.append(portalEdition);

		Integer supportedLCSPortlet = _supportedLCSPortletMap.get(
			sb.toString());

		if (supportedLCSPortlet != null) {
			return supportedLCSPortlet;
		}

		LCSMetadata lcsMetadata = _jsonWebServiceClient.doGetToObject(
			LCSMetadata.class, sb.toString());

		_supportedLCSPortletMap.put(
			sb.toString(), lcsMetadata.getSupportedLCSPortlet());

		return lcsMetadata.getSupportedLCSPortlet();
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	private static final String _URL_LCS_METADATA =
		"/o/osb-lcs-rest/LCSMetadata";

	private static final Map<String, Integer> _supportedLCSPortletMap =
		new HashMap<String, Integer>();

	@Reference(target = "(component.name=OSBLCSJSONWebServiceClient)")
	private JSONWebServiceClient _jsonWebServiceClient;

}