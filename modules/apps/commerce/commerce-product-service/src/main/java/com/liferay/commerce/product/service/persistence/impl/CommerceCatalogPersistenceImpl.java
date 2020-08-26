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

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.impl.CommerceCatalogImpl;
import com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceCatalogPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the commerce catalog service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceCatalogPersistenceImpl
	extends BasePersistenceImpl<CommerceCatalog>
	implements CommerceCatalogPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceCatalogUtil</code> to access the commerce catalog persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceCatalogImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce catalogs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce catalogs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceCatalog> list = null;

		if (useFinderCache) {
			list = (List<CommerceCatalog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCatalog commerceCatalog : list) {
					if (companyId != commerceCatalog.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<CommerceCatalog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (commerceCatalog != null) {
			return commerceCatalog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCatalogException(sb.toString());
	}

	/**
	 * Returns the first commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByCompanyId_First(
		long companyId, OrderByComparator<CommerceCatalog> orderByComparator) {

		List<CommerceCatalog> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (commerceCatalog != null) {
			return commerceCatalog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCatalogException(sb.toString());
	}

	/**
	 * Returns the last commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByCompanyId_Last(
		long companyId, OrderByComparator<CommerceCatalog> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceCatalog> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce catalogs before and after the current commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param commerceCatalogId the primary key of the current commerce catalog
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog[] findByCompanyId_PrevAndNext(
			long commerceCatalogId, long companyId,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = findByPrimaryKey(commerceCatalogId);

		Session session = null;

		try {
			session = openSession();

			CommerceCatalog[] array = new CommerceCatalogImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceCatalog, companyId, orderByComparator, true);

			array[1] = commerceCatalog;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceCatalog, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceCatalog getByCompanyId_PrevAndNext(
		Session session, CommerceCatalog commerceCatalog, long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCatalog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceCatalog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce catalogs that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce catalogs that the user has permission to view
	 */
	@Override
	public List<CommerceCatalog> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce catalogs that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of matching commerce catalogs that the user has permission to view
	 */
	@Override
	public List<CommerceCatalog> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce catalogs that the user has permission to view
	 */
	@Override
	public List<CommerceCatalog> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_COMMERCECATALOG_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceCatalog.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceCatalogImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, CommerceCatalogImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(companyId);

			return (List<CommerceCatalog>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce catalogs before and after the current commerce catalog in the ordered set of commerce catalogs that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceCatalogId the primary key of the current commerce catalog
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog[] filterFindByCompanyId_PrevAndNext(
			long commerceCatalogId, long companyId,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				commerceCatalogId, companyId, orderByComparator);
		}

		CommerceCatalog commerceCatalog = findByPrimaryKey(commerceCatalogId);

		Session session = null;

		try {
			session = openSession();

			CommerceCatalog[] array = new CommerceCatalogImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, commerceCatalog, companyId, orderByComparator, true);

			array[1] = commerceCatalog;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, commerceCatalog, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceCatalog filterGetByCompanyId_PrevAndNext(
		Session session, CommerceCatalog commerceCatalog, long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_COMMERCECATALOG_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceCatalog.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, CommerceCatalogImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, CommerceCatalogImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCatalog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceCatalog> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce catalogs where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceCatalog commerceCatalog :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceCatalog);
		}
	}

	/**
	 * Returns the number of commerce catalogs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce catalogs
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCECATALOG_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of commerce catalogs that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce catalogs that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_COMMERCECATALOG_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceCatalog.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(companyId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceCatalog.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByC_S;
	private FinderPath _finderPathWithoutPaginationFindByC_S;
	private FinderPath _finderPathCountByC_S;

	/**
	 * Returns all the commerce catalogs where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @return the matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByC_S(long companyId, boolean system) {
		return findByC_S(
			companyId, system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce catalogs where companyId = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByC_S(
		long companyId, boolean system, int start, int end) {

		return findByC_S(companyId, system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs where companyId = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByC_S(
		long companyId, boolean system, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		return findByC_S(
			companyId, system, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs where companyId = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByC_S(
		long companyId, boolean system, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_S;
				finderArgs = new Object[] {companyId, system};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_S;
			finderArgs = new Object[] {
				companyId, system, start, end, orderByComparator
			};
		}

		List<CommerceCatalog> list = null;

		if (useFinderCache) {
			list = (List<CommerceCatalog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCatalog commerceCatalog : list) {
					if ((companyId != commerceCatalog.getCompanyId()) ||
						(system != commerceCatalog.isSystem())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

			sb.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_S_SYSTEM_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(system);

				list = (List<CommerceCatalog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce catalog in the ordered set where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByC_S_First(
			long companyId, boolean system,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = fetchByC_S_First(
			companyId, system, orderByComparator);

		if (commerceCatalog != null) {
			return commerceCatalog;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", system=");
		sb.append(system);

		sb.append("}");

		throw new NoSuchCatalogException(sb.toString());
	}

	/**
	 * Returns the first commerce catalog in the ordered set where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByC_S_First(
		long companyId, boolean system,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		List<CommerceCatalog> list = findByC_S(
			companyId, system, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce catalog in the ordered set where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByC_S_Last(
			long companyId, boolean system,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = fetchByC_S_Last(
			companyId, system, orderByComparator);

		if (commerceCatalog != null) {
			return commerceCatalog;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", system=");
		sb.append(system);

		sb.append("}");

		throw new NoSuchCatalogException(sb.toString());
	}

	/**
	 * Returns the last commerce catalog in the ordered set where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByC_S_Last(
		long companyId, boolean system,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		int count = countByC_S(companyId, system);

		if (count == 0) {
			return null;
		}

		List<CommerceCatalog> list = findByC_S(
			companyId, system, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce catalogs before and after the current commerce catalog in the ordered set where companyId = &#63; and system = &#63;.
	 *
	 * @param commerceCatalogId the primary key of the current commerce catalog
	 * @param companyId the company ID
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog[] findByC_S_PrevAndNext(
			long commerceCatalogId, long companyId, boolean system,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = findByPrimaryKey(commerceCatalogId);

		Session session = null;

		try {
			session = openSession();

			CommerceCatalog[] array = new CommerceCatalogImpl[3];

			array[0] = getByC_S_PrevAndNext(
				session, commerceCatalog, companyId, system, orderByComparator,
				true);

			array[1] = commerceCatalog;

			array[2] = getByC_S_PrevAndNext(
				session, commerceCatalog, companyId, system, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceCatalog getByC_S_PrevAndNext(
		Session session, CommerceCatalog commerceCatalog, long companyId,
		boolean system, OrderByComparator<CommerceCatalog> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

		sb.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_S_SYSTEM_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		queryPos.add(system);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCatalog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceCatalog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce catalogs that the user has permission to view where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @return the matching commerce catalogs that the user has permission to view
	 */
	@Override
	public List<CommerceCatalog> filterFindByC_S(
		long companyId, boolean system) {

		return filterFindByC_S(
			companyId, system, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce catalogs that the user has permission to view where companyId = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of matching commerce catalogs that the user has permission to view
	 */
	@Override
	public List<CommerceCatalog> filterFindByC_S(
		long companyId, boolean system, int start, int end) {

		return filterFindByC_S(companyId, system, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs that the user has permissions to view where companyId = &#63; and system = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce catalogs that the user has permission to view
	 */
	@Override
	public List<CommerceCatalog> filterFindByC_S(
		long companyId, boolean system, int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_S(companyId, system, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_COMMERCECATALOG_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_S_SYSTEM_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceCatalog.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceCatalogImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, CommerceCatalogImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(companyId);

			queryPos.add(system);

			return (List<CommerceCatalog>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce catalogs before and after the current commerce catalog in the ordered set of commerce catalogs that the user has permission to view where companyId = &#63; and system = &#63;.
	 *
	 * @param commerceCatalogId the primary key of the current commerce catalog
	 * @param companyId the company ID
	 * @param system the system
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog[] filterFindByC_S_PrevAndNext(
			long commerceCatalogId, long companyId, boolean system,
			OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_S_PrevAndNext(
				commerceCatalogId, companyId, system, orderByComparator);
		}

		CommerceCatalog commerceCatalog = findByPrimaryKey(commerceCatalogId);

		Session session = null;

		try {
			session = openSession();

			CommerceCatalog[] array = new CommerceCatalogImpl[3];

			array[0] = filterGetByC_S_PrevAndNext(
				session, commerceCatalog, companyId, system, orderByComparator,
				true);

			array[1] = commerceCatalog;

			array[2] = filterGetByC_S_PrevAndNext(
				session, commerceCatalog, companyId, system, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceCatalog filterGetByC_S_PrevAndNext(
		Session session, CommerceCatalog commerceCatalog, long companyId,
		boolean system, OrderByComparator<CommerceCatalog> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_COMMERCECATALOG_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_S_SYSTEM_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(CommerceCatalogModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceCatalog.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, CommerceCatalogImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, CommerceCatalogImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(companyId);

		queryPos.add(system);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCatalog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceCatalog> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce catalogs where companyId = &#63; and system = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 */
	@Override
	public void removeByC_S(long companyId, boolean system) {
		for (CommerceCatalog commerceCatalog :
				findByC_S(
					companyId, system, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceCatalog);
		}
	}

	/**
	 * Returns the number of commerce catalogs where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @return the number of matching commerce catalogs
	 */
	@Override
	public int countByC_S(long companyId, boolean system) {
		FinderPath finderPath = _finderPathCountByC_S;

		Object[] finderArgs = new Object[] {companyId, system};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCECATALOG_WHERE);

			sb.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_S_SYSTEM_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(system);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of commerce catalogs that the user has permission to view where companyId = &#63; and system = &#63;.
	 *
	 * @param companyId the company ID
	 * @param system the system
	 * @return the number of matching commerce catalogs that the user has permission to view
	 */
	@Override
	public int filterCountByC_S(long companyId, boolean system) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByC_S(companyId, system);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_COMMERCECATALOG_WHERE);

		sb.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_S_SYSTEM_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceCatalog.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(companyId);

			queryPos.add(system);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_C_S_COMPANYID_2 =
		"commerceCatalog.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_S_SYSTEM_2 =
		"commerceCatalog.system = ?";

	private static final String _FINDER_COLUMN_C_S_SYSTEM_2_SQL =
		"commerceCatalog.system_ = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce catalog where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchCatalogException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceCatalog == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", externalReferenceCode=");
			sb.append(externalReferenceCode);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCatalogException(sb.toString());
		}

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce catalog where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, externalReferenceCode};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceCatalog) {
			CommerceCatalog commerceCatalog = (CommerceCatalog)result;

			if ((companyId != commerceCatalog.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceCatalog.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				List<CommerceCatalog> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_ERC, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, externalReferenceCode
								};
							}

							_log.warn(
								"CommerceCatalogPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceCatalog commerceCatalog = list.get(0);

					result = commerceCatalog;

					cacheResult(commerceCatalog);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByC_ERC, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CommerceCatalog)result;
		}
	}

	/**
	 * Removes the commerce catalog where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce catalog that was removed
	 */
	@Override
	public CommerceCatalog removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceCatalog);
	}

	/**
	 * Returns the number of commerce catalogs where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce catalogs
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCECATALOG_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"commerceCatalog.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceCatalog.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceCatalog.externalReferenceCode IS NULL OR commerceCatalog.externalReferenceCode = '')";

	public CommerceCatalogPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("system", "system_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(CommerceCatalog.class);
	}

	/**
	 * Caches the commerce catalog in the entity cache if it is enabled.
	 *
	 * @param commerceCatalog the commerce catalog
	 */
	@Override
	public void cacheResult(CommerceCatalog commerceCatalog) {
		entityCache.putResult(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey(),
			commerceCatalog);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceCatalog.getCompanyId(),
				commerceCatalog.getExternalReferenceCode()
			},
			commerceCatalog);

		commerceCatalog.resetOriginalValues();
	}

	/**
	 * Caches the commerce catalogs in the entity cache if it is enabled.
	 *
	 * @param commerceCatalogs the commerce catalogs
	 */
	@Override
	public void cacheResult(List<CommerceCatalog> commerceCatalogs) {
		for (CommerceCatalog commerceCatalog : commerceCatalogs) {
			if (entityCache.getResult(
					CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCatalogImpl.class,
					commerceCatalog.getPrimaryKey()) == null) {

				cacheResult(commerceCatalog);
			}
			else {
				commerceCatalog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce catalogs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceCatalogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce catalog.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceCatalog commerceCatalog) {
		entityCache.removeResult(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceCatalogModelImpl)commerceCatalog, true);
	}

	@Override
	public void clearCache(List<CommerceCatalog> commerceCatalogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceCatalog commerceCatalog : commerceCatalogs) {
			entityCache.removeResult(
				CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceCatalogModelImpl)commerceCatalog, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCatalogImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceCatalogModelImpl commerceCatalogModelImpl) {

		Object[] args = new Object[] {
			commerceCatalogModelImpl.getCompanyId(),
			commerceCatalogModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceCatalogModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceCatalogModelImpl commerceCatalogModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceCatalogModelImpl.getCompanyId(),
				commerceCatalogModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceCatalogModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceCatalogModelImpl.getOriginalCompanyId(),
				commerceCatalogModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce catalog with the primary key. Does not add the commerce catalog to the database.
	 *
	 * @param commerceCatalogId the primary key for the new commerce catalog
	 * @return the new commerce catalog
	 */
	@Override
	public CommerceCatalog create(long commerceCatalogId) {
		CommerceCatalog commerceCatalog = new CommerceCatalogImpl();

		commerceCatalog.setNew(true);
		commerceCatalog.setPrimaryKey(commerceCatalogId);

		commerceCatalog.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceCatalog;
	}

	/**
	 * Removes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog remove(long commerceCatalogId)
		throws NoSuchCatalogException {

		return remove((Serializable)commerceCatalogId);
	}

	/**
	 * Removes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog remove(Serializable primaryKey)
		throws NoSuchCatalogException {

		Session session = null;

		try {
			session = openSession();

			CommerceCatalog commerceCatalog = (CommerceCatalog)session.get(
				CommerceCatalogImpl.class, primaryKey);

			if (commerceCatalog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCatalogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceCatalog);
		}
		catch (NoSuchCatalogException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CommerceCatalog removeImpl(CommerceCatalog commerceCatalog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceCatalog)) {
				commerceCatalog = (CommerceCatalog)session.get(
					CommerceCatalogImpl.class,
					commerceCatalog.getPrimaryKeyObj());
			}

			if (commerceCatalog != null) {
				session.delete(commerceCatalog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceCatalog != null) {
			clearCache(commerceCatalog);
		}

		return commerceCatalog;
	}

	@Override
	public CommerceCatalog updateImpl(CommerceCatalog commerceCatalog) {
		boolean isNew = commerceCatalog.isNew();

		if (!(commerceCatalog instanceof CommerceCatalogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceCatalog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceCatalog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceCatalog proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceCatalog implementation " +
					commerceCatalog.getClass());
		}

		CommerceCatalogModelImpl commerceCatalogModelImpl =
			(CommerceCatalogModelImpl)commerceCatalog;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceCatalog.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceCatalog.setCreateDate(now);
			}
			else {
				commerceCatalog.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceCatalogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceCatalog.setModifiedDate(now);
			}
			else {
				commerceCatalog.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceCatalog);

				commerceCatalog.setNew(false);
			}
			else {
				commerceCatalog = (CommerceCatalog)session.merge(
					commerceCatalog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceCatalogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceCatalogModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceCatalogModelImpl.getCompanyId(),
				commerceCatalogModelImpl.isSystem()
			};

			finderCache.removeResult(_finderPathCountByC_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceCatalogModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceCatalogModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {commerceCatalogModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceCatalogModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceCatalogModelImpl.getOriginalCompanyId(),
					commerceCatalogModelImpl.getOriginalSystem()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);

				args = new Object[] {
					commerceCatalogModelImpl.getCompanyId(),
					commerceCatalogModelImpl.isSystem()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);
			}
		}

		entityCache.putResult(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey(),
			commerceCatalog, false);

		clearUniqueFindersCache(commerceCatalogModelImpl, false);
		cacheUniqueFindersCache(commerceCatalogModelImpl);

		commerceCatalog.resetOriginalValues();

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce catalog
	 * @return the commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCatalogException {

		CommerceCatalog commerceCatalog = fetchByPrimaryKey(primaryKey);

		if (commerceCatalog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCatalogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog with the primary key or throws a <code>NoSuchCatalogException</code> if it could not be found.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog findByPrimaryKey(long commerceCatalogId)
		throws NoSuchCatalogException {

		return findByPrimaryKey((Serializable)commerceCatalogId);
	}

	/**
	 * Returns the commerce catalog with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce catalog
	 * @return the commerce catalog, or <code>null</code> if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceCatalog commerceCatalog = (CommerceCatalog)serializable;

		if (commerceCatalog == null) {
			Session session = null;

			try {
				session = openSession();

				commerceCatalog = (CommerceCatalog)session.get(
					CommerceCatalogImpl.class, primaryKey);

				if (commerceCatalog != null) {
					cacheResult(commerceCatalog);
				}
				else {
					entityCache.putResult(
						CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
						CommerceCatalogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCatalogImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog, or <code>null</code> if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog fetchByPrimaryKey(long commerceCatalogId) {
		return fetchByPrimaryKey((Serializable)commerceCatalogId);
	}

	@Override
	public Map<Serializable, CommerceCatalog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceCatalog> map =
			new HashMap<Serializable, CommerceCatalog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceCatalog commerceCatalog = fetchByPrimaryKey(primaryKey);

			if (commerceCatalog != null) {
				map.put(primaryKey, commerceCatalog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCatalogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceCatalog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_COMMERCECATALOG_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (CommerceCatalog commerceCatalog :
					(List<CommerceCatalog>)query.list()) {

				map.put(commerceCatalog.getPrimaryKeyObj(), commerceCatalog);

				cacheResult(commerceCatalog);

				uncachedPrimaryKeys.remove(commerceCatalog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCatalogImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the commerce catalogs.
	 *
	 * @return the commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll(
		int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll(
		int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceCatalog> list = null;

		if (useFinderCache) {
			list = (List<CommerceCatalog>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCECATALOG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECATALOG;

				sql = sql.concat(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommerceCatalog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce catalogs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceCatalog commerceCatalog : findAll()) {
			remove(commerceCatalog);
		}
	}

	/**
	 * Returns the number of commerce catalogs.
	 *
	 * @return the number of commerce catalogs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMMERCECATALOG);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceCatalogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce catalog persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceCatalogModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCatalogModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_S = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_S",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_S = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CommerceCatalogModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCatalogModelImpl.SYSTEM_COLUMN_BITMASK |
			CommerceCatalogModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_S = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceCatalogModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCatalogModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceCatalogImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCECATALOG =
		"SELECT commerceCatalog FROM CommerceCatalog commerceCatalog";

	private static final String _SQL_SELECT_COMMERCECATALOG_WHERE_PKS_IN =
		"SELECT commerceCatalog FROM CommerceCatalog commerceCatalog WHERE commerceCatalogId IN (";

	private static final String _SQL_SELECT_COMMERCECATALOG_WHERE =
		"SELECT commerceCatalog FROM CommerceCatalog commerceCatalog WHERE ";

	private static final String _SQL_COUNT_COMMERCECATALOG =
		"SELECT COUNT(commerceCatalog) FROM CommerceCatalog commerceCatalog";

	private static final String _SQL_COUNT_COMMERCECATALOG_WHERE =
		"SELECT COUNT(commerceCatalog) FROM CommerceCatalog commerceCatalog WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"commerceCatalog.commerceCatalogId";

	private static final String _FILTER_SQL_SELECT_COMMERCECATALOG_WHERE =
		"SELECT DISTINCT {commerceCatalog.*} FROM CommerceCatalog commerceCatalog WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CommerceCatalog.*} FROM (SELECT DISTINCT commerceCatalog.commerceCatalogId FROM CommerceCatalog commerceCatalog WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCECATALOG_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CommerceCatalog ON TEMP_TABLE.commerceCatalogId = CommerceCatalog.commerceCatalogId";

	private static final String _FILTER_SQL_COUNT_COMMERCECATALOG_WHERE =
		"SELECT COUNT(DISTINCT commerceCatalog.commerceCatalogId) AS COUNT_VALUE FROM CommerceCatalog commerceCatalog WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "commerceCatalog";

	private static final String _FILTER_ENTITY_TABLE = "CommerceCatalog";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceCatalog.";

	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceCatalog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceCatalog exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceCatalog exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCatalogPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"system"});

}