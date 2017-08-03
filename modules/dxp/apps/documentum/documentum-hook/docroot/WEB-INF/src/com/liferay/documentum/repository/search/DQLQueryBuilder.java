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

package com.liferay.documentum.repository.search;

import com.liferay.document.library.repository.external.search.ExtRepositoryQueryMapper;
import com.liferay.documentum.repository.model.Constants;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mika Koivisto
 * @author Iván Zaera Avellón
 */
public class DQLQueryBuilder {

	public DQLQueryBuilder(ExtRepositoryQueryMapper extRepositoryQueryMapper) {
		_extRepositoryQueryMapper = extRepositoryQueryMapper;
	}

	public String buildSearchCountQueryString(
			SearchContext searchContext, Query query)
		throws SearchException {

		StringBundler sb = new StringBundler();

		sb.append("SELECT COUNT(r_object_id) AS num_hits FROM ");
		sb.append(Constants.DM_DOCUMENT);

		DQLConjunction dqlConjunction = new DQLConjunction();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		_traverseQuery(dqlConjunction, query, queryConfig);

		if (!dqlConjunction.isEmpty()) {
			sb.append(" WHERE ");
			sb.append(dqlConjunction.toQueryFragment());
		}

		return sb.toString();
	}

	public String buildSearchSelectQueryString(
			SearchContext searchContext, Query query)
		throws SearchException {

		StringBundler sb = new StringBundler();

		sb.append("SELECT ");
		sb.append(Constants.R_OBJECT_ID);
		sb.append(" FROM ");
		sb.append(Constants.DM_DOCUMENT);

		DQLConjunction dqlConjunction = new DQLConjunction();

		QueryConfig queryConfig = searchContext.getQueryConfig();

		_traverseQuery(dqlConjunction, query, queryConfig);

		if (!dqlConjunction.isEmpty()) {
			sb.append(" WHERE ");
			sb.append(dqlConjunction.toQueryFragment());
		}

		Sort[] sorts = searchContext.getSorts();

		if ((sorts != null) && (sorts.length > 0)) {
			sb.append(" ORDER BY ");

			for (int i = 0; i < sorts.length; i++) {
				Sort sort = sorts[i];

				if (i > 0) {
					sb.append(", ");
				}

				String fieldName = sort.getFieldName();

				sb.append(_dqlFields.get(fieldName));

				if (sort.isReverse()) {
					sb.append(" DESC");
				}
				else {
					sb.append(" ASC");
				}
			}
		}

		return sb.toString();
	}

	private DQLCriterion _buildFieldExpression(
			String field, String value,
			DQLSimpleExpressionOperator dqlSimpleExpressionOperator,
			QueryConfig queryConfig)
		throws SearchException {

		DQLCriterion dqlCriterion = null;

		if (DQLSimpleExpressionOperator.LIKE == dqlSimpleExpressionOperator) {
			value = value.replaceAll("\\*", StringPool.PERCENT);
		}

		if (field.equals(Field.CREATE_DATE) ||
			field.equals(Field.MODIFIED_DATE)) {

			Date date = _extRepositoryQueryMapper.formatDateParameterValue(
				field, value);

			dqlCriterion = new DQLDateExpression(
				_dqlFields.get(field), date, dqlSimpleExpressionOperator);
		}
		else if (field.equals(Field.FOLDER_ID)) {
			String extRepositoryFolderKey =
				_extRepositoryQueryMapper.formatParameterValue(field, value);

			boolean descend = false;

			if (queryConfig != null) {
				descend = queryConfig.isSearchSubfolders();
			}

			dqlCriterion = new DQLInFolderExpression(
				extRepositoryFolderKey, descend);
		}
		else if (field.equals(Field.USER_ID) || field.equals(Field.USER_NAME)) {
			String screenName = _extRepositoryQueryMapper.formatParameterValue(
				field, value);

			dqlCriterion = new DQLSimpleExpression(
				Constants.R_CREATOR_NAME, screenName,
				dqlSimpleExpressionOperator);
		}
		else {
			value = _extRepositoryQueryMapper.formatParameterValue(
				field, value);

			String dqlField = _dqlFields.get(field);

			if (Validator.isNull(dqlField)) {
				dqlField = field;
			}

			dqlCriterion = new DQLSimpleExpression(
				dqlField, value, dqlSimpleExpressionOperator);
		}

		return dqlCriterion;
	}

	private void _traverseQuery(
			DQLJunction criterionDQLJunction, Query query,
			QueryConfig queryConfig)
		throws SearchException {

		if (query instanceof BooleanQuery) {
			BooleanQuery booleanQuery = (BooleanQuery)query;

			List<BooleanClause<Query>> booleanClauses = booleanQuery.clauses();

			DQLConjunction anyDQLConjunction = new DQLConjunction();
			DQLConjunction notDQLConjunction = new DQLConjunction();
			DQLDisjunction dqlDisjunction = new DQLDisjunction();

			for (BooleanClause<Query> booleanClause : booleanClauses) {
				DQLJunction dqlJunction = dqlDisjunction;

				BooleanClauseOccur booleanClauseOccur =
					booleanClause.getBooleanClauseOccur();

				if (booleanClauseOccur.equals(BooleanClauseOccur.MUST)) {
					dqlJunction = anyDQLConjunction;
				}
				else if (booleanClauseOccur.equals(
							BooleanClauseOccur.MUST_NOT)) {

					dqlJunction = notDQLConjunction;
				}

				Query booleanClauseQuery = booleanClause.getClause();

				_traverseQuery(dqlJunction, booleanClauseQuery, queryConfig);
			}

			if (!anyDQLConjunction.isEmpty()) {
				criterionDQLJunction.add(anyDQLConjunction);
			}

			if (!dqlDisjunction.isEmpty()) {
				criterionDQLJunction.add(dqlDisjunction);
			}

			if (!notDQLConjunction.isEmpty()) {
				criterionDQLJunction.add(
					new DQLNotExpression(notDQLConjunction));
			}
		}
		else if (query instanceof TermQuery) {
			TermQuery termQuery = (TermQuery)query;

			QueryTerm queryTerm = termQuery.getQueryTerm();

			if (!_supportedFields.contains(queryTerm.getField())) {
				return;
			}

			DQLCriterion dqlExpression = _buildFieldExpression(
				queryTerm.getField(), queryTerm.getValue(),
				DQLSimpleExpressionOperator.EQ, queryConfig);

			if (dqlExpression != null) {
				criterionDQLJunction.add(dqlExpression);
			}
		}
		else if (query instanceof TermRangeQuery) {
			TermRangeQuery termRangeQuery = (TermRangeQuery)query;

			if (!_supportedFields.contains(termRangeQuery.getField())) {
				return;
			}

			String fieldName = termRangeQuery.getField();

			String dqlField = _dqlFields.get(fieldName);
			String dqlLowerTerm = DQLParameterValueUtil.formatParameterValue(
				fieldName, termRangeQuery.getLowerTerm());
			String dqlUpperTerm = DQLParameterValueUtil.formatParameterValue(
				fieldName, termRangeQuery.getUpperTerm());

			DQLCriterion dqlCriterion = new DQLBetweenExpression(
				dqlField, dqlLowerTerm, dqlUpperTerm,
				termRangeQuery.includesLower(), termRangeQuery.includesUpper());

			criterionDQLJunction.add(dqlCriterion);
		}
		else if (query instanceof WildcardQuery) {
			WildcardQuery wildcardQuery = (WildcardQuery)query;

			QueryTerm queryTerm = wildcardQuery.getQueryTerm();

			if (!_supportedFields.contains(queryTerm.getField())) {
				return;
			}

			DQLCriterion dqlCriterion = _buildFieldExpression(
				queryTerm.getField(), queryTerm.getValue(),
				DQLSimpleExpressionOperator.LIKE, queryConfig);

			if (dqlCriterion != null) {
				criterionDQLJunction.add(dqlCriterion);
			}
		}
	}

	private static final Map<String, String> _dqlFields;
	private static final Set<String> _supportedFields;

	static {
		_dqlFields = new HashMap<>();

		_dqlFields.put(Field.CREATE_DATE, Constants.R_CREATION_DATE);
		_dqlFields.put(Field.MODIFIED_DATE, Constants.R_MODIFY_DATE);
		_dqlFields.put(Field.NAME, Constants.OBJECT_NAME);
		_dqlFields.put(Field.TITLE, Constants.OBJECT_NAME);
		_dqlFields.put(Field.USER_NAME, Constants.R_CREATOR_NAME);
		_dqlFields.put("modifiedDate", Constants.R_MODIFY_DATE);
		_dqlFields.put("size_", Constants.R_CONTENT_SIZE);

		_supportedFields = new HashSet<>();

		_supportedFields.add(Field.CREATE_DATE);
		_supportedFields.add(Field.FOLDER_ID);
		_supportedFields.add(Field.MODIFIED_DATE);
		_supportedFields.add(Field.NAME);
		_supportedFields.add(Field.TITLE);
		_supportedFields.add(Field.USER_ID);
		_supportedFields.add(Field.USER_NAME);
	}

	private final ExtRepositoryQueryMapper _extRepositoryQueryMapper;

}