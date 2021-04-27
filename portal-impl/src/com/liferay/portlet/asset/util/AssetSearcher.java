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

package com.liferay.portlet.asset.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class AssetSearcher extends BaseSearcher {

	public static Indexer<?> getInstance() {
		return new AssetSearcher();
	}

	public AssetSearcher() {
		setDefaultSelectedFieldNames(
			Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.UID);
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String[] getSearchClassNames() {
		long[] classNameIds = _assetEntryQuery.getClassNameIds();

		String[] classNames = new String[classNameIds.length];

		for (int i = 0; i < classNames.length; i++) {
			long classNameId = classNameIds[i];

			classNames[i] = PortalUtil.getClassName(classNameId);
		}

		return classNames;
	}

	public void setAssetEntryQuery(AssetEntryQuery assetEntryQuery) {
		_assetEntryQuery = assetEntryQuery;
	}

	protected void addImpossibleTerm(
			BooleanFilter queryBooleanFilter, String field)
		throws Exception {

		queryBooleanFilter.addTerm(field, "-1", BooleanClauseOccur.MUST);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #addSearchAllCategories(String, BooleanFilter)}
	 */
	@Deprecated
	protected void addSearchAllCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		addSearchAllCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
	}

	protected void addSearchAllCategories(
			String fieldName, BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] allCategoryIds = _assetEntryQuery.getAllCategoryIds();

		if (allCategoryIds.length == 0) {
			return;
		}

		long[] filteredAllCategoryIds = AssetUtil.filterCategoryIds(
			PermissionThreadLocal.getPermissionChecker(), allCategoryIds);

		if (allCategoryIds.length != filteredAllCategoryIds.length) {
			addImpossibleTerm(queryBooleanFilter, fieldName);

			return;
		}

		BooleanFilter categoryIdsBooleanFilter = new BooleanFilter();

		for (long allCategoryId : filteredAllCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(allCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						allCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(allCategoryId);
			}

			TermsFilter categoryIdTermsFilter = new TermsFilter(fieldName);

			categoryIdTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));

			categoryIdsBooleanFilter.add(
				categoryIdTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			categoryIdsBooleanFilter, BooleanClauseOccur.MUST);
	}

	protected void addSearchAllKeywords(BooleanFilter queryBooleanFilter)
		throws Exception {

		String[] allKeywords = _assetEntryQuery.getAllKeywords();

		if (allKeywords.length == 0) {
			return;
		}

		BooleanQuery keywordsQuery = new BooleanQueryImpl();

		for (String keyword : allKeywords) {
			if (keyword.contains(" ")) {
				keyword = StringUtil.quote(keyword, CharPool.QUOTE);
			}

			StringQuery stringQuery = new StringQuery(keyword);

			keywordsQuery.add(stringQuery, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			new QueryFilter(keywordsQuery), BooleanClauseOccur.MUST);
	}

	protected void addSearchAllTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[][] allTagIdsArray = _assetEntryQuery.getAllTagIdsArray();

		if (allTagIdsArray.length == 0) {
			return;
		}

		BooleanFilter tagIdsArrayBooleanFilter = new BooleanFilter();

		for (long[] allTagIds : allTagIdsArray) {
			if (allTagIds.length == 0) {
				continue;
			}

			TermsFilter tagIdsTermsFilter = new TermsFilter(
				Field.ASSET_TAG_IDS);

			tagIdsTermsFilter.addValues(ArrayUtil.toStringArray(allTagIds));

			tagIdsArrayBooleanFilter.add(
				tagIdsTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			tagIdsArrayBooleanFilter, BooleanClauseOccur.MUST);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #addSearchAnyCategories(String, BooleanFilter)}
	 */
	@Deprecated
	protected void addSearchAnyCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		addSearchAllCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
	}

	protected void addSearchAnyCategories(
			String fieldName, BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] anyCategoryIds = _assetEntryQuery.getAnyCategoryIds();

		if (anyCategoryIds.length == 0) {
			return;
		}

		long[] filteredAnyCategoryIds = AssetUtil.filterCategoryIds(
			PermissionThreadLocal.getPermissionChecker(), anyCategoryIds);

		if (filteredAnyCategoryIds.length == 0) {
			addImpossibleTerm(queryBooleanFilter, fieldName);

			return;
		}

		TermsFilter categoryIdsTermsFilter = new TermsFilter(fieldName);

		for (long anyCategoryId : filteredAnyCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(anyCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						anyCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(anyCategoryId);
			}

			categoryIdsTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));
		}

		queryBooleanFilter.add(categoryIdsTermsFilter, BooleanClauseOccur.MUST);
	}

	protected void addSearchAnyKeywords(BooleanFilter queryBooleanFilter)
		throws Exception {

		String[] anyKeywords = _assetEntryQuery.getAnyKeywords();

		if (anyKeywords.length == 0) {
			return;
		}

		BooleanQuery keywordsQuery = new BooleanQueryImpl();

		for (String keyword : anyKeywords) {
			if (keyword.contains(" ")) {
				keyword = StringUtil.quote(keyword, CharPool.QUOTE);
			}

			StringQuery stringQuery = new StringQuery(keyword);

			keywordsQuery.add(stringQuery, BooleanClauseOccur.SHOULD);
		}

		queryBooleanFilter.add(
			new QueryFilter(keywordsQuery), BooleanClauseOccur.MUST);
	}

	protected void addSearchAnyTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] anyTagIds = _assetEntryQuery.getAnyTagIds();

		if (anyTagIds.length == 0) {
			return;
		}

		TermsFilter tagIdsTermsFilter = new TermsFilter(Field.ASSET_TAG_IDS);

		tagIdsTermsFilter.addValues(ArrayUtil.toStringArray(anyTagIds));

		queryBooleanFilter.add(tagIdsTermsFilter, BooleanClauseOccur.MUST);
	}

	@Override
	protected void addSearchAssetCategoryIds(
			BooleanFilter queryBooleanFilter, SearchContext searchContext)
		throws Exception {

		addSearchAllCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
		addSearchAnyCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
		addSearchNotAnyCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
		addSearchNotAllCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);

		if (searchContext.isIncludeInternalAssetCategories()) {
			addSearchAllCategories(
				Field.ASSET_INTERNAL_CATEGORY_IDS, queryBooleanFilter);
			addSearchAnyCategories(
				Field.ASSET_INTERNAL_CATEGORY_IDS, queryBooleanFilter);
			addSearchNotAnyCategories(
				Field.ASSET_INTERNAL_CATEGORY_IDS, queryBooleanFilter);
			addSearchNotAllCategories(
				Field.ASSET_INTERNAL_CATEGORY_IDS, queryBooleanFilter);
		}
	}

	@Override
	protected void addSearchAssetTagNames(
			BooleanFilter queryBooleanFilter, SearchContext searchContext)
		throws Exception {

		addSearchAllTags(queryBooleanFilter);
		addSearchAnyTags(queryBooleanFilter);
		addSearchNotAllTags(queryBooleanFilter);
		addSearchNotAnyTags(queryBooleanFilter);
	}

	@Override
	protected Map<String, Query> addSearchKeywords(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		if (Validator.isNull(keywords)) {
			return Collections.emptyMap();
		}

		Map<String, Query> queries = super.addSearchKeywords(
			searchQuery, searchContext);

		String field = Field.getLocalizedName(
			searchContext.getLocale(), "localized_title");

		Query query = searchQuery.addTerm(field, keywords, true);

		queries.put(field, query);

		return queries;
	}

	@Override
	protected void addSearchLayout(
			BooleanFilter queryBooleanFilter, SearchContext searchContext)
		throws Exception {

		String layoutUuid = (String)searchContext.getAttribute(
			Field.LAYOUT_UUID);

		if (Validator.isNotNull(layoutUuid)) {
			queryBooleanFilter.addRequiredTerm(Field.LAYOUT_UUID, layoutUuid);
		}
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #addSearchNotAllCategories(String, BooleanFilter)}
	 */
	@Deprecated
	protected void addSearchNotAllCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		addSearchNotAllCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
	}

	protected void addSearchNotAllCategories(
			String fieldName, BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] notAllCategoryIds = _assetEntryQuery.getNotAllCategoryIds();

		if (notAllCategoryIds.length == 0) {
			return;
		}

		BooleanFilter categoryIdsBooleanFilter = new BooleanFilter();

		for (long notAllCategoryId : notAllCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(
					notAllCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						notAllCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(notAllCategoryId);
			}

			TermsFilter categoryIdTermsFilter = new TermsFilter(fieldName);

			categoryIdTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));

			categoryIdsBooleanFilter.add(
				categoryIdTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			categoryIdsBooleanFilter, BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAllKeywords(BooleanFilter queryBooleanFilter)
		throws Exception {

		String[] notAllKeywords = _assetEntryQuery.getNotAllKeywords();

		if (notAllKeywords.length == 0) {
			return;
		}

		BooleanQuery keywordsQuery = new BooleanQueryImpl();

		for (String keyword : notAllKeywords) {
			if (keyword.contains(" ")) {
				keyword = StringUtil.quote(keyword, CharPool.QUOTE);
			}

			StringQuery stringQuery = new StringQuery(keyword);

			keywordsQuery.add(stringQuery, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			new QueryFilter(keywordsQuery), BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAllTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[][] notAllTagIdsArray = _assetEntryQuery.getNotAllTagIdsArray();

		if (notAllTagIdsArray.length == 0) {
			return;
		}

		BooleanFilter tagIdsArrayBooleanFilter = new BooleanFilter();

		for (long[] notAllTagIds : notAllTagIdsArray) {
			if (notAllTagIds.length == 0) {
				continue;
			}

			TermsFilter tagIdsTermsFilter = new TermsFilter(
				Field.ASSET_TAG_IDS);

			tagIdsTermsFilter.addValues(ArrayUtil.toStringArray(notAllTagIds));

			tagIdsArrayBooleanFilter.add(
				tagIdsTermsFilter, BooleanClauseOccur.MUST);
		}

		queryBooleanFilter.add(
			tagIdsArrayBooleanFilter, BooleanClauseOccur.MUST_NOT);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #addSearchNotAnyCategories(String, BooleanFilter)}
	 */
	@Deprecated
	protected void addSearchNotAnyCategories(BooleanFilter queryBooleanFilter)
		throws Exception {

		addSearchNotAnyCategories(Field.ASSET_CATEGORY_IDS, queryBooleanFilter);
	}

	protected void addSearchNotAnyCategories(
			String fieldName, BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] notAnyCategoryIds = _assetEntryQuery.getNotAnyCategoryIds();

		if (notAnyCategoryIds.length == 0) {
			return;
		}

		TermsFilter categoryIdsTermsFilter = new TermsFilter(fieldName);

		for (long notAnyCategoryId : notAnyCategoryIds) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.fetchAssetCategory(
					notAnyCategoryId);

			if (assetCategory == null) {
				continue;
			}

			List<Long> categoryIds = new ArrayList<>();

			if (PropsValues.ASSET_CATEGORIES_SEARCH_HIERARCHICAL) {
				categoryIds.addAll(
					AssetCategoryLocalServiceUtil.getSubcategoryIds(
						notAnyCategoryId));
			}

			if (categoryIds.isEmpty()) {
				categoryIds.add(notAnyCategoryId);
			}

			categoryIdsTermsFilter.addValues(
				ArrayUtil.toStringArray(categoryIds.toArray(new Long[0])));
		}

		queryBooleanFilter.add(
			categoryIdsTermsFilter, BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAnyKeywords(BooleanFilter queryBooleanFilter)
		throws Exception {

		String[] notAnyKeywords = _assetEntryQuery.getNotAnyKeywords();

		if (notAnyKeywords.length == 0) {
			return;
		}

		BooleanQuery keywordsQuery = new BooleanQueryImpl();

		for (String keyword : notAnyKeywords) {
			if (keyword.contains(" ")) {
				keyword = StringUtil.quote(keyword, CharPool.QUOTE);
			}

			StringQuery stringQuery = new StringQuery(keyword);

			keywordsQuery.add(stringQuery, BooleanClauseOccur.SHOULD);
		}

		queryBooleanFilter.add(
			new QueryFilter(keywordsQuery), BooleanClauseOccur.MUST_NOT);
	}

	protected void addSearchNotAnyTags(BooleanFilter queryBooleanFilter)
		throws Exception {

		long[] notAnyTagIds = _assetEntryQuery.getNotAnyTagIds();

		if (notAnyTagIds.length == 0) {
			return;
		}

		TermsFilter tagIgsTermsFilter = new TermsFilter(Field.ASSET_TAG_IDS);

		tagIgsTermsFilter.addValues(ArrayUtil.toStringArray(notAnyTagIds));

		queryBooleanFilter.add(tagIgsTermsFilter, BooleanClauseOccur.MUST_NOT);
	}

	@Override
	protected BooleanQuery createFullQuery(
			BooleanFilter fullQueryBooleanFilter, SearchContext searchContext)
		throws Exception {

		addSearchAllKeywords(fullQueryBooleanFilter);
		addSearchAnyKeywords(fullQueryBooleanFilter);
		addSearchNotAnyKeywords(fullQueryBooleanFilter);
		addSearchNotAllKeywords(fullQueryBooleanFilter);

		return super.createFullQuery(fullQueryBooleanFilter, searchContext);
	}

	@Override
	protected void postProcessFullQuery(
			BooleanQuery fullQuery, SearchContext searchContext)
		throws Exception {

		boolean showInvisible = GetterUtil.getBoolean(
			_assetEntryQuery.getAttribute("showInvisible"));

		if (showInvisible) {
			return;
		}

		BooleanFilter booleanFilter = fullQuery.getPreBooleanFilter();

		if (booleanFilter == null) {
			booleanFilter = new BooleanFilter();
		}

		booleanFilter.addRequiredTerm("visible", true);

		if (booleanFilter.hasClauses() && !showInvisible) {
			fullQuery.setPreBooleanFilter(booleanFilter);
		}
	}

	private AssetEntryQuery _assetEntryQuery;

}