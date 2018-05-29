/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.saml.persistence.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.saml.persistence.exception.NoSuchIdpSpSessionException;
import com.liferay.saml.persistence.model.SamlIdpSpSession;
import com.liferay.saml.persistence.model.impl.SamlIdpSpSessionImpl;
import com.liferay.saml.persistence.model.impl.SamlIdpSpSessionModelImpl;
import com.liferay.saml.persistence.service.persistence.SamlIdpSpSessionPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the saml idp sp session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpSessionPersistence
 * @see com.liferay.saml.persistence.service.persistence.SamlIdpSpSessionUtil
 * @generated
 */
@ProviderType
public class SamlIdpSpSessionPersistenceImpl extends BasePersistenceImpl<SamlIdpSpSession>
	implements SamlIdpSpSessionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SamlIdpSpSessionUtil} to access the saml idp sp session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SamlIdpSpSessionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATE =
		new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCreateDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CREATEDATE =
		new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCreateDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the saml idp sp sessions where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findByCreateDate(Date createDate) {
		return findByCreateDate(createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml idp sp sessions where createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @return the range of matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findByCreateDate(Date createDate, int start,
		int end) {
		return findByCreateDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml idp sp sessions where createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findByCreateDate(Date createDate, int start,
		int end, OrderByComparator<SamlIdpSpSession> orderByComparator) {
		return findByCreateDate(createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the saml idp sp sessions where createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findByCreateDate(Date createDate, int start,
		int end, OrderByComparator<SamlIdpSpSession> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATE;
		finderArgs = new Object[] {
				_getTime(createDate),
				
				start, end, orderByComparator
			};

		List<SamlIdpSpSession> list = null;

		if (retrieveFromCache) {
			list = (List<SamlIdpSpSession>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SamlIdpSpSession samlIdpSpSession : list) {
					if ((createDate.getTime() <= samlIdpSpSession.getCreateDate()
																	 .getTime())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SAMLIDPSPSESSION_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SamlIdpSpSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				if (!pagination) {
					list = (List<SamlIdpSpSession>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SamlIdpSpSession>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first saml idp sp session in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession findByCreateDate_First(Date createDate,
		OrderByComparator<SamlIdpSpSession> orderByComparator)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = fetchByCreateDate_First(createDate,
				orderByComparator);

		if (samlIdpSpSession != null) {
			return samlIdpSpSession;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchIdpSpSessionException(msg.toString());
	}

	/**
	 * Returns the first saml idp sp session in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession fetchByCreateDate_First(Date createDate,
		OrderByComparator<SamlIdpSpSession> orderByComparator) {
		List<SamlIdpSpSession> list = findByCreateDate(createDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last saml idp sp session in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession findByCreateDate_Last(Date createDate,
		OrderByComparator<SamlIdpSpSession> orderByComparator)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = fetchByCreateDate_Last(createDate,
				orderByComparator);

		if (samlIdpSpSession != null) {
			return samlIdpSpSession;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchIdpSpSessionException(msg.toString());
	}

	/**
	 * Returns the last saml idp sp session in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession fetchByCreateDate_Last(Date createDate,
		OrderByComparator<SamlIdpSpSession> orderByComparator) {
		int count = countByCreateDate(createDate);

		if (count == 0) {
			return null;
		}

		List<SamlIdpSpSession> list = findByCreateDate(createDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the saml idp sp sessions before and after the current saml idp sp session in the ordered set where createDate &lt; &#63;.
	 *
	 * @param samlIdpSpSessionId the primary key of the current saml idp sp session
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession[] findByCreateDate_PrevAndNext(
		long samlIdpSpSessionId, Date createDate,
		OrderByComparator<SamlIdpSpSession> orderByComparator)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = findByPrimaryKey(samlIdpSpSessionId);

		Session session = null;

		try {
			session = openSession();

			SamlIdpSpSession[] array = new SamlIdpSpSessionImpl[3];

			array[0] = getByCreateDate_PrevAndNext(session, samlIdpSpSession,
					createDate, orderByComparator, true);

			array[1] = samlIdpSpSession;

			array[2] = getByCreateDate_PrevAndNext(session, samlIdpSpSession,
					createDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SamlIdpSpSession getByCreateDate_PrevAndNext(Session session,
		SamlIdpSpSession samlIdpSpSession, Date createDate,
		OrderByComparator<SamlIdpSpSession> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAMLIDPSPSESSION_WHERE);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SamlIdpSpSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(samlIdpSpSession);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SamlIdpSpSession> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the saml idp sp sessions where createDate &lt; &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDate(Date createDate) {
		for (SamlIdpSpSession samlIdpSpSession : findByCreateDate(createDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(samlIdpSpSession);
		}
	}

	/**
	 * Returns the number of saml idp sp sessions where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching saml idp sp sessions
	 */
	@Override
	public int countByCreateDate(Date createDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_CREATEDATE;

		Object[] finderArgs = new Object[] { _getTime(createDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLIDPSPSESSION_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_1 = "samlIdpSpSession.createDate IS NULL";
	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_2 = "samlIdpSpSession.createDate < ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID =
		new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySamlIdpSsoSessionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID =
		new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySamlIdpSsoSessionId", new String[] { Long.class.getName() },
			SamlIdpSpSessionModelImpl.SAMLIDPSSOSESSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONID = new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySamlIdpSsoSessionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @return the matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId) {
		return findBySamlIdpSsoSessionId(samlIdpSsoSessionId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @return the range of matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId, int start, int end) {
		return findBySamlIdpSsoSessionId(samlIdpSsoSessionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId, int start, int end,
		OrderByComparator<SamlIdpSpSession> orderByComparator) {
		return findBySamlIdpSsoSessionId(samlIdpSsoSessionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId, int start, int end,
		OrderByComparator<SamlIdpSpSession> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID;
			finderArgs = new Object[] { samlIdpSsoSessionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID;
			finderArgs = new Object[] {
					samlIdpSsoSessionId,
					
					start, end, orderByComparator
				};
		}

		List<SamlIdpSpSession> list = null;

		if (retrieveFromCache) {
			list = (List<SamlIdpSpSession>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SamlIdpSpSession samlIdpSpSession : list) {
					if ((samlIdpSsoSessionId != samlIdpSpSession.getSamlIdpSsoSessionId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SAMLIDPSPSESSION_WHERE);

			query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONID_SAMLIDPSSOSESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SamlIdpSpSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(samlIdpSsoSessionId);

				if (!pagination) {
					list = (List<SamlIdpSpSession>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SamlIdpSpSession>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession findBySamlIdpSsoSessionId_First(
		long samlIdpSsoSessionId,
		OrderByComparator<SamlIdpSpSession> orderByComparator)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = fetchBySamlIdpSsoSessionId_First(samlIdpSsoSessionId,
				orderByComparator);

		if (samlIdpSpSession != null) {
			return samlIdpSpSession;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("samlIdpSsoSessionId=");
		msg.append(samlIdpSsoSessionId);

		msg.append("}");

		throw new NoSuchIdpSpSessionException(msg.toString());
	}

	/**
	 * Returns the first saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession fetchBySamlIdpSsoSessionId_First(
		long samlIdpSsoSessionId,
		OrderByComparator<SamlIdpSpSession> orderByComparator) {
		List<SamlIdpSpSession> list = findBySamlIdpSsoSessionId(samlIdpSsoSessionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession findBySamlIdpSsoSessionId_Last(
		long samlIdpSsoSessionId,
		OrderByComparator<SamlIdpSpSession> orderByComparator)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = fetchBySamlIdpSsoSessionId_Last(samlIdpSsoSessionId,
				orderByComparator);

		if (samlIdpSpSession != null) {
			return samlIdpSpSession;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("samlIdpSsoSessionId=");
		msg.append(samlIdpSsoSessionId);

		msg.append("}");

		throw new NoSuchIdpSpSessionException(msg.toString());
	}

	/**
	 * Returns the last saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession fetchBySamlIdpSsoSessionId_Last(
		long samlIdpSsoSessionId,
		OrderByComparator<SamlIdpSpSession> orderByComparator) {
		int count = countBySamlIdpSsoSessionId(samlIdpSsoSessionId);

		if (count == 0) {
			return null;
		}

		List<SamlIdpSpSession> list = findBySamlIdpSsoSessionId(samlIdpSsoSessionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the saml idp sp sessions before and after the current saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSpSessionId the primary key of the current saml idp sp session
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession[] findBySamlIdpSsoSessionId_PrevAndNext(
		long samlIdpSpSessionId, long samlIdpSsoSessionId,
		OrderByComparator<SamlIdpSpSession> orderByComparator)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = findByPrimaryKey(samlIdpSpSessionId);

		Session session = null;

		try {
			session = openSession();

			SamlIdpSpSession[] array = new SamlIdpSpSessionImpl[3];

			array[0] = getBySamlIdpSsoSessionId_PrevAndNext(session,
					samlIdpSpSession, samlIdpSsoSessionId, orderByComparator,
					true);

			array[1] = samlIdpSpSession;

			array[2] = getBySamlIdpSsoSessionId_PrevAndNext(session,
					samlIdpSpSession, samlIdpSsoSessionId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SamlIdpSpSession getBySamlIdpSsoSessionId_PrevAndNext(
		Session session, SamlIdpSpSession samlIdpSpSession,
		long samlIdpSsoSessionId,
		OrderByComparator<SamlIdpSpSession> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAMLIDPSPSESSION_WHERE);

		query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONID_SAMLIDPSSOSESSIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SamlIdpSpSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(samlIdpSsoSessionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(samlIdpSpSession);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SamlIdpSpSession> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the saml idp sp sessions where samlIdpSsoSessionId = &#63; from the database.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 */
	@Override
	public void removeBySamlIdpSsoSessionId(long samlIdpSsoSessionId) {
		for (SamlIdpSpSession samlIdpSpSession : findBySamlIdpSsoSessionId(
				samlIdpSsoSessionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(samlIdpSpSession);
		}
	}

	/**
	 * Returns the number of saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @return the number of matching saml idp sp sessions
	 */
	@Override
	public int countBySamlIdpSsoSessionId(long samlIdpSsoSessionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONID;

		Object[] finderArgs = new Object[] { samlIdpSsoSessionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLIDPSPSESSION_WHERE);

			query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONID_SAMLIDPSSOSESSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(samlIdpSsoSessionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SAMLIDPSSOSESSIONID_SAMLIDPSSOSESSIONID_2 =
		"samlIdpSpSession.samlIdpSsoSessionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SISSI_SSEI = new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySISSI_SSEI",
			new String[] { Long.class.getName(), String.class.getName() },
			SamlIdpSpSessionModelImpl.SAMLIDPSSOSESSIONID_COLUMN_BITMASK |
			SamlIdpSpSessionModelImpl.SAMLSPENTITYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SISSI_SSEI = new FinderPath(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySISSI_SSEI",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; or throws a {@link NoSuchIdpSpSessionException} if it could not be found.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param samlSpEntityId the saml sp entity ID
	 * @return the matching saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession findBySISSI_SSEI(long samlIdpSsoSessionId,
		String samlSpEntityId) throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = fetchBySISSI_SSEI(samlIdpSsoSessionId,
				samlSpEntityId);

		if (samlIdpSpSession == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("samlIdpSsoSessionId=");
			msg.append(samlIdpSsoSessionId);

			msg.append(", samlSpEntityId=");
			msg.append(samlSpEntityId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchIdpSpSessionException(msg.toString());
		}

		return samlIdpSpSession;
	}

	/**
	 * Returns the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param samlSpEntityId the saml sp entity ID
	 * @return the matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession fetchBySISSI_SSEI(long samlIdpSsoSessionId,
		String samlSpEntityId) {
		return fetchBySISSI_SSEI(samlIdpSsoSessionId, samlSpEntityId, true);
	}

	/**
	 * Returns the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param samlSpEntityId the saml sp entity ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	 */
	@Override
	public SamlIdpSpSession fetchBySISSI_SSEI(long samlIdpSsoSessionId,
		String samlSpEntityId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { samlIdpSsoSessionId, samlSpEntityId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SISSI_SSEI,
					finderArgs, this);
		}

		if (result instanceof SamlIdpSpSession) {
			SamlIdpSpSession samlIdpSpSession = (SamlIdpSpSession)result;

			if ((samlIdpSsoSessionId != samlIdpSpSession.getSamlIdpSsoSessionId()) ||
					!Objects.equals(samlSpEntityId,
						samlIdpSpSession.getSamlSpEntityId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SAMLIDPSPSESSION_WHERE);

			query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLIDPSSOSESSIONID_2);

			boolean bindSamlSpEntityId = false;

			if (samlSpEntityId == null) {
				query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_1);
			}
			else if (samlSpEntityId.equals("")) {
				query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_3);
			}
			else {
				bindSamlSpEntityId = true;

				query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(samlIdpSsoSessionId);

				if (bindSamlSpEntityId) {
					qPos.add(samlSpEntityId);
				}

				List<SamlIdpSpSession> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SISSI_SSEI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SamlIdpSpSessionPersistenceImpl.fetchBySISSI_SSEI(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SamlIdpSpSession samlIdpSpSession = list.get(0);

					result = samlIdpSpSession;

					cacheResult(samlIdpSpSession);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SISSI_SSEI,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SamlIdpSpSession)result;
		}
	}

	/**
	 * Removes the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; from the database.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param samlSpEntityId the saml sp entity ID
	 * @return the saml idp sp session that was removed
	 */
	@Override
	public SamlIdpSpSession removeBySISSI_SSEI(long samlIdpSsoSessionId,
		String samlSpEntityId) throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = findBySISSI_SSEI(samlIdpSsoSessionId,
				samlSpEntityId);

		return remove(samlIdpSpSession);
	}

	/**
	 * Returns the number of saml idp sp sessions where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63;.
	 *
	 * @param samlIdpSsoSessionId the saml idp sso session ID
	 * @param samlSpEntityId the saml sp entity ID
	 * @return the number of matching saml idp sp sessions
	 */
	@Override
	public int countBySISSI_SSEI(long samlIdpSsoSessionId, String samlSpEntityId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SISSI_SSEI;

		Object[] finderArgs = new Object[] { samlIdpSsoSessionId, samlSpEntityId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAMLIDPSPSESSION_WHERE);

			query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLIDPSSOSESSIONID_2);

			boolean bindSamlSpEntityId = false;

			if (samlSpEntityId == null) {
				query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_1);
			}
			else if (samlSpEntityId.equals("")) {
				query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_3);
			}
			else {
				bindSamlSpEntityId = true;

				query.append(_FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(samlIdpSsoSessionId);

				if (bindSamlSpEntityId) {
					qPos.add(samlSpEntityId);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SISSI_SSEI_SAMLIDPSSOSESSIONID_2 = "samlIdpSpSession.samlIdpSsoSessionId = ? AND ";
	private static final String _FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_1 = "samlIdpSpSession.samlSpEntityId IS NULL";
	private static final String _FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_2 = "samlIdpSpSession.samlSpEntityId = ?";
	private static final String _FINDER_COLUMN_SISSI_SSEI_SAMLSPENTITYID_3 = "(samlIdpSpSession.samlSpEntityId IS NULL OR samlIdpSpSession.samlSpEntityId = '')";

	public SamlIdpSpSessionPersistenceImpl() {
		setModelClass(SamlIdpSpSession.class);
	}

	/**
	 * Caches the saml idp sp session in the entity cache if it is enabled.
	 *
	 * @param samlIdpSpSession the saml idp sp session
	 */
	@Override
	public void cacheResult(SamlIdpSpSession samlIdpSpSession) {
		entityCache.putResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, samlIdpSpSession.getPrimaryKey(),
			samlIdpSpSession);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SISSI_SSEI,
			new Object[] {
				samlIdpSpSession.getSamlIdpSsoSessionId(),
				samlIdpSpSession.getSamlSpEntityId()
			}, samlIdpSpSession);

		samlIdpSpSession.resetOriginalValues();
	}

	/**
	 * Caches the saml idp sp sessions in the entity cache if it is enabled.
	 *
	 * @param samlIdpSpSessions the saml idp sp sessions
	 */
	@Override
	public void cacheResult(List<SamlIdpSpSession> samlIdpSpSessions) {
		for (SamlIdpSpSession samlIdpSpSession : samlIdpSpSessions) {
			if (entityCache.getResult(
						SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlIdpSpSessionImpl.class,
						samlIdpSpSession.getPrimaryKey()) == null) {
				cacheResult(samlIdpSpSession);
			}
			else {
				samlIdpSpSession.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all saml idp sp sessions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SamlIdpSpSessionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the saml idp sp session.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SamlIdpSpSession samlIdpSpSession) {
		entityCache.removeResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, samlIdpSpSession.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SamlIdpSpSessionModelImpl)samlIdpSpSession,
			true);
	}

	@Override
	public void clearCache(List<SamlIdpSpSession> samlIdpSpSessions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SamlIdpSpSession samlIdpSpSession : samlIdpSpSessions) {
			entityCache.removeResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlIdpSpSessionImpl.class, samlIdpSpSession.getPrimaryKey());

			clearUniqueFindersCache((SamlIdpSpSessionModelImpl)samlIdpSpSession,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		SamlIdpSpSessionModelImpl samlIdpSpSessionModelImpl) {
		Object[] args = new Object[] {
				samlIdpSpSessionModelImpl.getSamlIdpSsoSessionId(),
				samlIdpSpSessionModelImpl.getSamlSpEntityId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_SISSI_SSEI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SISSI_SSEI, args,
			samlIdpSpSessionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SamlIdpSpSessionModelImpl samlIdpSpSessionModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					samlIdpSpSessionModelImpl.getSamlIdpSsoSessionId(),
					samlIdpSpSessionModelImpl.getSamlSpEntityId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SISSI_SSEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SISSI_SSEI, args);
		}

		if ((samlIdpSpSessionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SISSI_SSEI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					samlIdpSpSessionModelImpl.getOriginalSamlIdpSsoSessionId(),
					samlIdpSpSessionModelImpl.getOriginalSamlSpEntityId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SISSI_SSEI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SISSI_SSEI, args);
		}
	}

	/**
	 * Creates a new saml idp sp session with the primary key. Does not add the saml idp sp session to the database.
	 *
	 * @param samlIdpSpSessionId the primary key for the new saml idp sp session
	 * @return the new saml idp sp session
	 */
	@Override
	public SamlIdpSpSession create(long samlIdpSpSessionId) {
		SamlIdpSpSession samlIdpSpSession = new SamlIdpSpSessionImpl();

		samlIdpSpSession.setNew(true);
		samlIdpSpSession.setPrimaryKey(samlIdpSpSessionId);

		samlIdpSpSession.setCompanyId(companyProvider.getCompanyId());

		return samlIdpSpSession;
	}

	/**
	 * Removes the saml idp sp session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samlIdpSpSessionId the primary key of the saml idp sp session
	 * @return the saml idp sp session that was removed
	 * @throws NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession remove(long samlIdpSpSessionId)
		throws NoSuchIdpSpSessionException {
		return remove((Serializable)samlIdpSpSessionId);
	}

	/**
	 * Removes the saml idp sp session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the saml idp sp session
	 * @return the saml idp sp session that was removed
	 * @throws NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession remove(Serializable primaryKey)
		throws NoSuchIdpSpSessionException {
		Session session = null;

		try {
			session = openSession();

			SamlIdpSpSession samlIdpSpSession = (SamlIdpSpSession)session.get(SamlIdpSpSessionImpl.class,
					primaryKey);

			if (samlIdpSpSession == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIdpSpSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samlIdpSpSession);
		}
		catch (NoSuchIdpSpSessionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SamlIdpSpSession removeImpl(SamlIdpSpSession samlIdpSpSession) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samlIdpSpSession)) {
				samlIdpSpSession = (SamlIdpSpSession)session.get(SamlIdpSpSessionImpl.class,
						samlIdpSpSession.getPrimaryKeyObj());
			}

			if (samlIdpSpSession != null) {
				session.delete(samlIdpSpSession);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samlIdpSpSession != null) {
			clearCache(samlIdpSpSession);
		}

		return samlIdpSpSession;
	}

	@Override
	public SamlIdpSpSession updateImpl(SamlIdpSpSession samlIdpSpSession) {
		boolean isNew = samlIdpSpSession.isNew();

		if (!(samlIdpSpSession instanceof SamlIdpSpSessionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(samlIdpSpSession.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(samlIdpSpSession);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in samlIdpSpSession proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SamlIdpSpSession implementation " +
				samlIdpSpSession.getClass());
		}

		SamlIdpSpSessionModelImpl samlIdpSpSessionModelImpl = (SamlIdpSpSessionModelImpl)samlIdpSpSession;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (samlIdpSpSession.getCreateDate() == null)) {
			if (serviceContext == null) {
				samlIdpSpSession.setCreateDate(now);
			}
			else {
				samlIdpSpSession.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!samlIdpSpSessionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				samlIdpSpSession.setModifiedDate(now);
			}
			else {
				samlIdpSpSession.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (samlIdpSpSession.isNew()) {
				session.save(samlIdpSpSession);

				samlIdpSpSession.setNew(false);
			}
			else {
				samlIdpSpSession = (SamlIdpSpSession)session.merge(samlIdpSpSession);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SamlIdpSpSessionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					samlIdpSpSessionModelImpl.getSamlIdpSsoSessionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((samlIdpSpSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlIdpSpSessionModelImpl.getOriginalSamlIdpSsoSessionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID,
					args);

				args = new Object[] {
						samlIdpSpSessionModelImpl.getSamlIdpSsoSessionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SAMLIDPSSOSESSIONID,
					args);
			}
		}

		entityCache.putResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSpSessionImpl.class, samlIdpSpSession.getPrimaryKey(),
			samlIdpSpSession, false);

		clearUniqueFindersCache(samlIdpSpSessionModelImpl, false);
		cacheUniqueFindersCache(samlIdpSpSessionModelImpl);

		samlIdpSpSession.resetOriginalValues();

		return samlIdpSpSession;
	}

	/**
	 * Returns the saml idp sp session with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml idp sp session
	 * @return the saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIdpSpSessionException {
		SamlIdpSpSession samlIdpSpSession = fetchByPrimaryKey(primaryKey);

		if (samlIdpSpSession == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIdpSpSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samlIdpSpSession;
	}

	/**
	 * Returns the saml idp sp session with the primary key or throws a {@link NoSuchIdpSpSessionException} if it could not be found.
	 *
	 * @param samlIdpSpSessionId the primary key of the saml idp sp session
	 * @return the saml idp sp session
	 * @throws NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession findByPrimaryKey(long samlIdpSpSessionId)
		throws NoSuchIdpSpSessionException {
		return findByPrimaryKey((Serializable)samlIdpSpSessionId);
	}

	/**
	 * Returns the saml idp sp session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml idp sp session
	 * @return the saml idp sp session, or <code>null</code> if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlIdpSpSessionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SamlIdpSpSession samlIdpSpSession = (SamlIdpSpSession)serializable;

		if (samlIdpSpSession == null) {
			Session session = null;

			try {
				session = openSession();

				samlIdpSpSession = (SamlIdpSpSession)session.get(SamlIdpSpSessionImpl.class,
						primaryKey);

				if (samlIdpSpSession != null) {
					cacheResult(samlIdpSpSession);
				}
				else {
					entityCache.putResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlIdpSpSessionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
					SamlIdpSpSessionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samlIdpSpSession;
	}

	/**
	 * Returns the saml idp sp session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlIdpSpSessionId the primary key of the saml idp sp session
	 * @return the saml idp sp session, or <code>null</code> if a saml idp sp session with the primary key could not be found
	 */
	@Override
	public SamlIdpSpSession fetchByPrimaryKey(long samlIdpSpSessionId) {
		return fetchByPrimaryKey((Serializable)samlIdpSpSessionId);
	}

	@Override
	public Map<Serializable, SamlIdpSpSession> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SamlIdpSpSession> map = new HashMap<Serializable, SamlIdpSpSession>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SamlIdpSpSession samlIdpSpSession = fetchByPrimaryKey(primaryKey);

			if (samlIdpSpSession != null) {
				map.put(primaryKey, samlIdpSpSession);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
					SamlIdpSpSessionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SamlIdpSpSession)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SAMLIDPSPSESSION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SamlIdpSpSession samlIdpSpSession : (List<SamlIdpSpSession>)q.list()) {
				map.put(samlIdpSpSession.getPrimaryKeyObj(), samlIdpSpSession);

				cacheResult(samlIdpSpSession);

				uncachedPrimaryKeys.remove(samlIdpSpSession.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SamlIdpSpSessionModelImpl.ENTITY_CACHE_ENABLED,
					SamlIdpSpSessionImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the saml idp sp sessions.
	 *
	 * @return the saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml idp sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @return the range of saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml idp sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findAll(int start, int end,
		OrderByComparator<SamlIdpSpSession> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the saml idp sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml idp sp sessions
	 * @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of saml idp sp sessions
	 */
	@Override
	public List<SamlIdpSpSession> findAll(int start, int end,
		OrderByComparator<SamlIdpSpSession> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SamlIdpSpSession> list = null;

		if (retrieveFromCache) {
			list = (List<SamlIdpSpSession>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SAMLIDPSPSESSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMLIDPSPSESSION;

				if (pagination) {
					sql = sql.concat(SamlIdpSpSessionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SamlIdpSpSession>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SamlIdpSpSession>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the saml idp sp sessions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SamlIdpSpSession samlIdpSpSession : findAll()) {
			remove(samlIdpSpSession);
		}
	}

	/**
	 * Returns the number of saml idp sp sessions.
	 *
	 * @return the number of saml idp sp sessions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAMLIDPSPSESSION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SamlIdpSpSessionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the saml idp sp session persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SamlIdpSpSessionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_SAMLIDPSPSESSION = "SELECT samlIdpSpSession FROM SamlIdpSpSession samlIdpSpSession";
	private static final String _SQL_SELECT_SAMLIDPSPSESSION_WHERE_PKS_IN = "SELECT samlIdpSpSession FROM SamlIdpSpSession samlIdpSpSession WHERE samlIdpSpSessionId IN (";
	private static final String _SQL_SELECT_SAMLIDPSPSESSION_WHERE = "SELECT samlIdpSpSession FROM SamlIdpSpSession samlIdpSpSession WHERE ";
	private static final String _SQL_COUNT_SAMLIDPSPSESSION = "SELECT COUNT(samlIdpSpSession) FROM SamlIdpSpSession samlIdpSpSession";
	private static final String _SQL_COUNT_SAMLIDPSPSESSION_WHERE = "SELECT COUNT(samlIdpSpSession) FROM SamlIdpSpSession samlIdpSpSession WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samlIdpSpSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SamlIdpSpSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SamlIdpSpSession exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SamlIdpSpSessionPersistenceImpl.class);
}