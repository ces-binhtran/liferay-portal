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

package com.liferay.portal.search.engine.adapter.search;

import com.liferay.portal.search.engine.adapter.ccr.CrossClusterRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dylan Rebelak
 */
public class MultisearchSearchRequest
	extends CrossClusterRequest
	implements SearchRequest<MultisearchSearchResponse> {

	public MultisearchSearchRequest() {
		setPreferLocalCluster(true);
	}

	@Override
	public MultisearchSearchResponse accept(
		SearchRequestExecutor searchRequestExecutor) {

		return searchRequestExecutor.executeSearchRequest(this);
	}

	public void addSearchSearchRequest(
		SearchSearchRequest searchSearchRequest) {

		_searchSearchRequests.add(searchSearchRequest);
	}

	public void addSearchSearchRequests(
		Collection<SearchSearchRequest> searchSearchRequests) {

		_searchSearchRequests.addAll(searchSearchRequests);
	}

	public List<SearchSearchRequest> getSearchSearchRequests() {
		return _searchSearchRequests;
	}

	private final List<SearchSearchRequest> _searchSearchRequests =
		new ArrayList<>();

}