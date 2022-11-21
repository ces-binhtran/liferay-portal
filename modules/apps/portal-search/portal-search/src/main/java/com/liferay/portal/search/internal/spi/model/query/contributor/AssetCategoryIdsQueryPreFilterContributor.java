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

package com.liferay.portal.search.internal.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.spi.model.query.contributor.QueryPreFilterContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(service = QueryPreFilterContributor.class)
public class AssetCategoryIdsQueryPreFilterContributor
	implements QueryPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter fullQueryBooleanFilter, SearchContext searchContext) {

		long[] assetCategoryIds = searchContext.getAssetCategoryIds();

		if (ArrayUtil.isEmpty(assetCategoryIds)) {
			return;
		}

		TermsFilter categoryIdsTermsFilter = new TermsFilter(
			Field.ASSET_CATEGORY_IDS);

		categoryIdsTermsFilter.addValues(
			ArrayUtil.toStringArray(assetCategoryIds));

		if (!searchContext.isIncludeInternalAssetCategories()) {
			fullQueryBooleanFilter.add(
				categoryIdsTermsFilter, BooleanClauseOccur.MUST);

			return;
		}

		BooleanFilter booleanFilter = new BooleanFilter();

		TermsFilter internalCategoryIdsTermsFilter = new TermsFilter(
			Field.ASSET_INTERNAL_CATEGORY_IDS);

		internalCategoryIdsTermsFilter.addValues(
			ArrayUtil.toStringArray(assetCategoryIds));

		booleanFilter.add(categoryIdsTermsFilter);
		booleanFilter.add(internalCategoryIdsTermsFilter);

		fullQueryBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
	}

}