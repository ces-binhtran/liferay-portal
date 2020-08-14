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

package com.liferay.headless.admin.taxonomy.resource.v1_0;

import com.liferay.headless.admin.taxonomy.dto.v1_0.Keyword;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Locale;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-admin-taxonomy/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@ProviderType
public interface KeywordResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Page<Keyword> getAssetLibraryKeywordsPage(
			Long assetLibraryId, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception;

	public Keyword postAssetLibraryKeyword(Long assetLibraryId, Keyword keyword)
		throws Exception;

	public Response postAssetLibraryKeywordBatch(
			Long assetLibraryId, String callbackURL, Object object)
		throws Exception;

	public Page<Keyword> getKeywordsRankedPage(
			Long siteId, String search, Pagination pagination)
		throws Exception;

	public void deleteKeyword(Long keywordId) throws Exception;

	public Response deleteKeywordBatch(String callbackURL, Object object)
		throws Exception;

	public Keyword getKeyword(Long keywordId) throws Exception;

	public Keyword putKeyword(Long keywordId, Keyword keyword) throws Exception;

	public Response putKeywordBatch(String callbackURL, Object object)
		throws Exception;

	public Page<Keyword> getSiteKeywordsPage(
			Long siteId, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception;

	public Keyword postSiteKeyword(Long siteId, Keyword keyword)
		throws Exception;

	public Response postSiteKeywordBatch(
			Long siteId, String callbackURL, Object object)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser);

	public static class FactoryHolder {

		public static volatile Factory factory;

	}

	@ProviderType
	public interface Builder {

		public KeywordResource build();

		public Builder checkPermissions(boolean checkPermissions);

		public Builder httpServletRequest(
			HttpServletRequest httpServletRequest);

		public Builder preferredLocale(Locale preferredLocale);

		public Builder user(com.liferay.portal.kernel.model.User user);

	}

	@ProviderType
	public interface Factory {

		public Builder create();

	}

}