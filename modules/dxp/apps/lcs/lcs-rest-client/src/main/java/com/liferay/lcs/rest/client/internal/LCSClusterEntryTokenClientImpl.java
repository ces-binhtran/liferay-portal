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

import com.liferay.lcs.rest.client.LCSClusterEntryToken;
import com.liferay.lcs.rest.client.LCSClusterEntryTokenClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterEntryTokenClient.class)
public class LCSClusterEntryTokenClientImpl
	extends BaseLCSServiceImpl implements LCSClusterEntryTokenClient {

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {

		try {
			LCSClusterEntryToken lcsClusterEntryToken =
				jsonWebServiceClient.doGetToObject(
					LCSClusterEntryToken.class,
					_URL_LCS_CLUSTER_ENTRY_TOKEN + StringPool.SLASH +
						lcsClusterEntryTokenId);

			return lcsClusterEntryToken;
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw new RuntimeException(jsonwsie);
		}
		catch (JSONWebServiceSerializeException jsonwsse) {
			throw new RuntimeException(jsonwsse);
		}
	}

	private static final String _URL_LCS_CLUSTER_ENTRY_TOKEN =
		"/o/osb-lcs-rest/LCSClusterEntryToken";

}