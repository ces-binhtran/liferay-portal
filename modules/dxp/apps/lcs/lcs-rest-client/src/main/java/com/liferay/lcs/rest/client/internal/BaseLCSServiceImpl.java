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

import com.liferay.petra.json.web.service.client.JSONWebServiceClient;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public abstract class BaseLCSServiceImpl {

	public JSONWebServiceClient getJSONWebServiceClient() {
		return jsonWebServiceClient;
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		this.jsonWebServiceClient = jsonWebServiceClient;
	}

	@Reference(target = "(component.name=OSBLCSJSONWebServiceClient)")
	protected JSONWebServiceClient jsonWebServiceClient;

}