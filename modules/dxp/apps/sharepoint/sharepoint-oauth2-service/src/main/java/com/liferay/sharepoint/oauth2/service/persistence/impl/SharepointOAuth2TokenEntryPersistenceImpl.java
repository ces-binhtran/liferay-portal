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

package com.liferay.sharepoint.oauth2.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.sharepoint.oauth2.exception.NoSuch2TokenEntryException;
import com.liferay.sharepoint.oauth2.model.SharepointOAuth2TokenEntry;
import com.liferay.sharepoint.oauth2.model.impl.SharepointOAuth2TokenEntryImpl;
import com.liferay.sharepoint.oauth2.model.impl.SharepointOAuth2TokenEntryModelImpl;
import com.liferay.sharepoint.oauth2.service.persistence.SharepointOAuth2TokenEntryPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the sharepoint o auth2 token entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Adolfo Pérez
 * @see SharepointOAuth2TokenEntryPersistence
 * @see com.liferay.sharepoint.oauth2.service.persistence.SharepointOAuth2TokenEntryUtil
 * @generated
 */
@ProviderType
public class SharepointOAuth2TokenEntryPersistenceImpl
	extends BasePersistenceImpl<SharepointOAuth2TokenEntry>
	implements SharepointOAuth2TokenEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SharepointOAuth2TokenEntryUtil} to access the sharepoint o auth2 token entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SharepointOAuth2TokenEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryModelImpl.FINDER_CACHE_ENABLED,
			SharepointOAuth2TokenEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryModelImpl.FINDER_CACHE_ENABLED,
			SharepointOAuth2TokenEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_U_C = new FinderPath(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryModelImpl.FINDER_CACHE_ENABLED,
			SharepointOAuth2TokenEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_C",
			new String[] { Long.class.getName(), String.class.getName() },
			SharepointOAuth2TokenEntryModelImpl.USERID_COLUMN_BITMASK |
			SharepointOAuth2TokenEntryModelImpl.CONFIGURATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C = new FinderPath(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByU_C",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the sharepoint o auth2 token entry where userId = &#63; and configurationId = &#63; or throws a {@link NoSuch2TokenEntryException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param configurationId the configuration ID
	 * @return the matching sharepoint o auth2 token entry
	 * @throws NoSuch2TokenEntryException if a matching sharepoint o auth2 token entry could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry findByU_C(long userId,
		String configurationId) throws NoSuch2TokenEntryException {
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = fetchByU_C(userId,
				configurationId);

		if (sharepointOAuth2TokenEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", configurationId=");
			msg.append(configurationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuch2TokenEntryException(msg.toString());
		}

		return sharepointOAuth2TokenEntry;
	}

	/**
	 * Returns the sharepoint o auth2 token entry where userId = &#63; and configurationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param configurationId the configuration ID
	 * @return the matching sharepoint o auth2 token entry, or <code>null</code> if a matching sharepoint o auth2 token entry could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry fetchByU_C(long userId,
		String configurationId) {
		return fetchByU_C(userId, configurationId, true);
	}

	/**
	 * Returns the sharepoint o auth2 token entry where userId = &#63; and configurationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param configurationId the configuration ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sharepoint o auth2 token entry, or <code>null</code> if a matching sharepoint o auth2 token entry could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry fetchByU_C(long userId,
		String configurationId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, configurationId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_C,
					finderArgs, this);
		}

		if (result instanceof SharepointOAuth2TokenEntry) {
			SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = (SharepointOAuth2TokenEntry)result;

			if ((userId != sharepointOAuth2TokenEntry.getUserId()) ||
					!Objects.equals(configurationId,
						sharepointOAuth2TokenEntry.getConfigurationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			boolean bindConfigurationId = false;

			if (configurationId == null) {
				query.append(_FINDER_COLUMN_U_C_CONFIGURATIONID_1);
			}
			else if (configurationId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_C_CONFIGURATIONID_3);
			}
			else {
				bindConfigurationId = true;

				query.append(_FINDER_COLUMN_U_C_CONFIGURATIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindConfigurationId) {
					qPos.add(configurationId);
				}

				List<SharepointOAuth2TokenEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_C, finderArgs,
						list);
				}
				else {
					SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = list.get(0);

					result = sharepointOAuth2TokenEntry;

					cacheResult(sharepointOAuth2TokenEntry);

					if ((sharepointOAuth2TokenEntry.getUserId() != userId) ||
							(sharepointOAuth2TokenEntry.getConfigurationId() == null) ||
							!sharepointOAuth2TokenEntry.getConfigurationId()
														   .equals(configurationId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_C,
							finderArgs, sharepointOAuth2TokenEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C, finderArgs);

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
			return (SharepointOAuth2TokenEntry)result;
		}
	}

	/**
	 * Removes the sharepoint o auth2 token entry where userId = &#63; and configurationId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param configurationId the configuration ID
	 * @return the sharepoint o auth2 token entry that was removed
	 */
	@Override
	public SharepointOAuth2TokenEntry removeByU_C(long userId,
		String configurationId) throws NoSuch2TokenEntryException {
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = findByU_C(userId,
				configurationId);

		return remove(sharepointOAuth2TokenEntry);
	}

	/**
	 * Returns the number of sharepoint o auth2 token entries where userId = &#63; and configurationId = &#63;.
	 *
	 * @param userId the user ID
	 * @param configurationId the configuration ID
	 * @return the number of matching sharepoint o auth2 token entries
	 */
	@Override
	public int countByU_C(long userId, String configurationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_C;

		Object[] finderArgs = new Object[] { userId, configurationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHAREPOINTOAUTH2TOKENENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_C_USERID_2);

			boolean bindConfigurationId = false;

			if (configurationId == null) {
				query.append(_FINDER_COLUMN_U_C_CONFIGURATIONID_1);
			}
			else if (configurationId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_C_CONFIGURATIONID_3);
			}
			else {
				bindConfigurationId = true;

				query.append(_FINDER_COLUMN_U_C_CONFIGURATIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindConfigurationId) {
					qPos.add(configurationId);
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

	private static final String _FINDER_COLUMN_U_C_USERID_2 = "sharepointOAuth2TokenEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_CONFIGURATIONID_1 = "sharepointOAuth2TokenEntry.configurationId IS NULL";
	private static final String _FINDER_COLUMN_U_C_CONFIGURATIONID_2 = "sharepointOAuth2TokenEntry.configurationId = ?";
	private static final String _FINDER_COLUMN_U_C_CONFIGURATIONID_3 = "(sharepointOAuth2TokenEntry.configurationId IS NULL OR sharepointOAuth2TokenEntry.configurationId = '')";

	public SharepointOAuth2TokenEntryPersistenceImpl() {
		setModelClass(SharepointOAuth2TokenEntry.class);
	}

	/**
	 * Caches the sharepoint o auth2 token entry in the entity cache if it is enabled.
	 *
	 * @param sharepointOAuth2TokenEntry the sharepoint o auth2 token entry
	 */
	@Override
	public void cacheResult(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {
		entityCache.putResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryImpl.class,
			sharepointOAuth2TokenEntry.getPrimaryKey(),
			sharepointOAuth2TokenEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_C,
			new Object[] {
				sharepointOAuth2TokenEntry.getUserId(),
				sharepointOAuth2TokenEntry.getConfigurationId()
			}, sharepointOAuth2TokenEntry);

		sharepointOAuth2TokenEntry.resetOriginalValues();
	}

	/**
	 * Caches the sharepoint o auth2 token entries in the entity cache if it is enabled.
	 *
	 * @param sharepointOAuth2TokenEntries the sharepoint o auth2 token entries
	 */
	@Override
	public void cacheResult(
		List<SharepointOAuth2TokenEntry> sharepointOAuth2TokenEntries) {
		for (SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry : sharepointOAuth2TokenEntries) {
			if (entityCache.getResult(
						SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
						SharepointOAuth2TokenEntryImpl.class,
						sharepointOAuth2TokenEntry.getPrimaryKey()) == null) {
				cacheResult(sharepointOAuth2TokenEntry);
			}
			else {
				sharepointOAuth2TokenEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sharepoint o auth2 token entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SharepointOAuth2TokenEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sharepoint o auth2 token entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {
		entityCache.removeResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryImpl.class,
			sharepointOAuth2TokenEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SharepointOAuth2TokenEntryModelImpl)sharepointOAuth2TokenEntry,
			true);
	}

	@Override
	public void clearCache(
		List<SharepointOAuth2TokenEntry> sharepointOAuth2TokenEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry : sharepointOAuth2TokenEntries) {
			entityCache.removeResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
				SharepointOAuth2TokenEntryImpl.class,
				sharepointOAuth2TokenEntry.getPrimaryKey());

			clearUniqueFindersCache((SharepointOAuth2TokenEntryModelImpl)sharepointOAuth2TokenEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		SharepointOAuth2TokenEntryModelImpl sharepointOAuth2TokenEntryModelImpl) {
		Object[] args = new Object[] {
				sharepointOAuth2TokenEntryModelImpl.getUserId(),
				sharepointOAuth2TokenEntryModelImpl.getConfigurationId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_U_C, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_U_C, args,
			sharepointOAuth2TokenEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SharepointOAuth2TokenEntryModelImpl sharepointOAuth2TokenEntryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					sharepointOAuth2TokenEntryModelImpl.getUserId(),
					sharepointOAuth2TokenEntryModelImpl.getConfigurationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C, args);
		}

		if ((sharepointOAuth2TokenEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_C.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					sharepointOAuth2TokenEntryModelImpl.getOriginalUserId(),
					sharepointOAuth2TokenEntryModelImpl.getOriginalConfigurationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C, args);
		}
	}

	/**
	 * Creates a new sharepoint o auth2 token entry with the primary key. Does not add the sharepoint o auth2 token entry to the database.
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key for the new sharepoint o auth2 token entry
	 * @return the new sharepoint o auth2 token entry
	 */
	@Override
	public SharepointOAuth2TokenEntry create(long sharepointOAuth2TokenEntryId) {
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = new SharepointOAuth2TokenEntryImpl();

		sharepointOAuth2TokenEntry.setNew(true);
		sharepointOAuth2TokenEntry.setPrimaryKey(sharepointOAuth2TokenEntryId);

		return sharepointOAuth2TokenEntry;
	}

	/**
	 * Removes the sharepoint o auth2 token entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry that was removed
	 * @throws NoSuch2TokenEntryException if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry remove(long sharepointOAuth2TokenEntryId)
		throws NoSuch2TokenEntryException {
		return remove((Serializable)sharepointOAuth2TokenEntryId);
	}

	/**
	 * Removes the sharepoint o auth2 token entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry that was removed
	 * @throws NoSuch2TokenEntryException if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry remove(Serializable primaryKey)
		throws NoSuch2TokenEntryException {
		Session session = null;

		try {
			session = openSession();

			SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = (SharepointOAuth2TokenEntry)session.get(SharepointOAuth2TokenEntryImpl.class,
					primaryKey);

			if (sharepointOAuth2TokenEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuch2TokenEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(sharepointOAuth2TokenEntry);
		}
		catch (NoSuch2TokenEntryException nsee) {
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
	protected SharepointOAuth2TokenEntry removeImpl(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {
		sharepointOAuth2TokenEntry = toUnwrappedModel(sharepointOAuth2TokenEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sharepointOAuth2TokenEntry)) {
				sharepointOAuth2TokenEntry = (SharepointOAuth2TokenEntry)session.get(SharepointOAuth2TokenEntryImpl.class,
						sharepointOAuth2TokenEntry.getPrimaryKeyObj());
			}

			if (sharepointOAuth2TokenEntry != null) {
				session.delete(sharepointOAuth2TokenEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (sharepointOAuth2TokenEntry != null) {
			clearCache(sharepointOAuth2TokenEntry);
		}

		return sharepointOAuth2TokenEntry;
	}

	@Override
	public SharepointOAuth2TokenEntry updateImpl(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {
		sharepointOAuth2TokenEntry = toUnwrappedModel(sharepointOAuth2TokenEntry);

		boolean isNew = sharepointOAuth2TokenEntry.isNew();

		SharepointOAuth2TokenEntryModelImpl sharepointOAuth2TokenEntryModelImpl = (SharepointOAuth2TokenEntryModelImpl)sharepointOAuth2TokenEntry;

		Session session = null;

		try {
			session = openSession();

			if (sharepointOAuth2TokenEntry.isNew()) {
				session.save(sharepointOAuth2TokenEntry);

				sharepointOAuth2TokenEntry.setNew(false);
			}
			else {
				sharepointOAuth2TokenEntry = (SharepointOAuth2TokenEntry)session.merge(sharepointOAuth2TokenEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SharepointOAuth2TokenEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
			SharepointOAuth2TokenEntryImpl.class,
			sharepointOAuth2TokenEntry.getPrimaryKey(),
			sharepointOAuth2TokenEntry, false);

		clearUniqueFindersCache(sharepointOAuth2TokenEntryModelImpl, false);
		cacheUniqueFindersCache(sharepointOAuth2TokenEntryModelImpl);

		sharepointOAuth2TokenEntry.resetOriginalValues();

		return sharepointOAuth2TokenEntry;
	}

	protected SharepointOAuth2TokenEntry toUnwrappedModel(
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry) {
		if (sharepointOAuth2TokenEntry instanceof SharepointOAuth2TokenEntryImpl) {
			return sharepointOAuth2TokenEntry;
		}

		SharepointOAuth2TokenEntryImpl sharepointOAuth2TokenEntryImpl = new SharepointOAuth2TokenEntryImpl();

		sharepointOAuth2TokenEntryImpl.setNew(sharepointOAuth2TokenEntry.isNew());
		sharepointOAuth2TokenEntryImpl.setPrimaryKey(sharepointOAuth2TokenEntry.getPrimaryKey());

		sharepointOAuth2TokenEntryImpl.setSharepointOAuth2TokenEntryId(sharepointOAuth2TokenEntry.getSharepointOAuth2TokenEntryId());
		sharepointOAuth2TokenEntryImpl.setUserId(sharepointOAuth2TokenEntry.getUserId());
		sharepointOAuth2TokenEntryImpl.setUserName(sharepointOAuth2TokenEntry.getUserName());
		sharepointOAuth2TokenEntryImpl.setCreateDate(sharepointOAuth2TokenEntry.getCreateDate());
		sharepointOAuth2TokenEntryImpl.setAccessToken(sharepointOAuth2TokenEntry.getAccessToken());
		sharepointOAuth2TokenEntryImpl.setConfigurationId(sharepointOAuth2TokenEntry.getConfigurationId());
		sharepointOAuth2TokenEntryImpl.setExpirationDate(sharepointOAuth2TokenEntry.getExpirationDate());
		sharepointOAuth2TokenEntryImpl.setRefreshToken(sharepointOAuth2TokenEntry.getRefreshToken());

		return sharepointOAuth2TokenEntryImpl;
	}

	/**
	 * Returns the sharepoint o auth2 token entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry
	 * @throws NoSuch2TokenEntryException if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuch2TokenEntryException {
		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = fetchByPrimaryKey(primaryKey);

		if (sharepointOAuth2TokenEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuch2TokenEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return sharepointOAuth2TokenEntry;
	}

	/**
	 * Returns the sharepoint o auth2 token entry with the primary key or throws a {@link NoSuch2TokenEntryException} if it could not be found.
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry
	 * @throws NoSuch2TokenEntryException if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry findByPrimaryKey(
		long sharepointOAuth2TokenEntryId) throws NoSuch2TokenEntryException {
		return findByPrimaryKey((Serializable)sharepointOAuth2TokenEntryId);
	}

	/**
	 * Returns the sharepoint o auth2 token entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry, or <code>null</code> if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
				SharepointOAuth2TokenEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = (SharepointOAuth2TokenEntry)serializable;

		if (sharepointOAuth2TokenEntry == null) {
			Session session = null;

			try {
				session = openSession();

				sharepointOAuth2TokenEntry = (SharepointOAuth2TokenEntry)session.get(SharepointOAuth2TokenEntryImpl.class,
						primaryKey);

				if (sharepointOAuth2TokenEntry != null) {
					cacheResult(sharepointOAuth2TokenEntry);
				}
				else {
					entityCache.putResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
						SharepointOAuth2TokenEntryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
					SharepointOAuth2TokenEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return sharepointOAuth2TokenEntry;
	}

	/**
	 * Returns the sharepoint o auth2 token entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sharepointOAuth2TokenEntryId the primary key of the sharepoint o auth2 token entry
	 * @return the sharepoint o auth2 token entry, or <code>null</code> if a sharepoint o auth2 token entry with the primary key could not be found
	 */
	@Override
	public SharepointOAuth2TokenEntry fetchByPrimaryKey(
		long sharepointOAuth2TokenEntryId) {
		return fetchByPrimaryKey((Serializable)sharepointOAuth2TokenEntryId);
	}

	@Override
	public Map<Serializable, SharepointOAuth2TokenEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SharepointOAuth2TokenEntry> map = new HashMap<Serializable, SharepointOAuth2TokenEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry = fetchByPrimaryKey(primaryKey);

			if (sharepointOAuth2TokenEntry != null) {
				map.put(primaryKey, sharepointOAuth2TokenEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
					SharepointOAuth2TokenEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SharepointOAuth2TokenEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry : (List<SharepointOAuth2TokenEntry>)q.list()) {
				map.put(sharepointOAuth2TokenEntry.getPrimaryKeyObj(),
					sharepointOAuth2TokenEntry);

				cacheResult(sharepointOAuth2TokenEntry);

				uncachedPrimaryKeys.remove(sharepointOAuth2TokenEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SharepointOAuth2TokenEntryModelImpl.ENTITY_CACHE_ENABLED,
					SharepointOAuth2TokenEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the sharepoint o auth2 token entries.
	 *
	 * @return the sharepoint o auth2 token entries
	 */
	@Override
	public List<SharepointOAuth2TokenEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sharepoint o auth2 token entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SharepointOAuth2TokenEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sharepoint o auth2 token entries
	 * @param end the upper bound of the range of sharepoint o auth2 token entries (not inclusive)
	 * @return the range of sharepoint o auth2 token entries
	 */
	@Override
	public List<SharepointOAuth2TokenEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sharepoint o auth2 token entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SharepointOAuth2TokenEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sharepoint o auth2 token entries
	 * @param end the upper bound of the range of sharepoint o auth2 token entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sharepoint o auth2 token entries
	 */
	@Override
	public List<SharepointOAuth2TokenEntry> findAll(int start, int end,
		OrderByComparator<SharepointOAuth2TokenEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sharepoint o auth2 token entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SharepointOAuth2TokenEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sharepoint o auth2 token entries
	 * @param end the upper bound of the range of sharepoint o auth2 token entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sharepoint o auth2 token entries
	 */
	@Override
	public List<SharepointOAuth2TokenEntry> findAll(int start, int end,
		OrderByComparator<SharepointOAuth2TokenEntry> orderByComparator,
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

		List<SharepointOAuth2TokenEntry> list = null;

		if (retrieveFromCache) {
			list = (List<SharepointOAuth2TokenEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY;

				if (pagination) {
					sql = sql.concat(SharepointOAuth2TokenEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SharepointOAuth2TokenEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SharepointOAuth2TokenEntry>)QueryUtil.list(q,
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
	 * Removes all the sharepoint o auth2 token entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry : findAll()) {
			remove(sharepointOAuth2TokenEntry);
		}
	}

	/**
	 * Returns the number of sharepoint o auth2 token entries.
	 *
	 * @return the number of sharepoint o auth2 token entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SHAREPOINTOAUTH2TOKENENTRY);

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
		return SharepointOAuth2TokenEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sharepoint o auth2 token entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SharepointOAuth2TokenEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY = "SELECT sharepointOAuth2TokenEntry FROM SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry";
	private static final String _SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY_WHERE_PKS_IN =
		"SELECT sharepointOAuth2TokenEntry FROM SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry WHERE sharepointOAuth2TokenEntryId IN (";
	private static final String _SQL_SELECT_SHAREPOINTOAUTH2TOKENENTRY_WHERE = "SELECT sharepointOAuth2TokenEntry FROM SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry WHERE ";
	private static final String _SQL_COUNT_SHAREPOINTOAUTH2TOKENENTRY = "SELECT COUNT(sharepointOAuth2TokenEntry) FROM SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry";
	private static final String _SQL_COUNT_SHAREPOINTOAUTH2TOKENENTRY_WHERE = "SELECT COUNT(sharepointOAuth2TokenEntry) FROM SharepointOAuth2TokenEntry sharepointOAuth2TokenEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "sharepointOAuth2TokenEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SharepointOAuth2TokenEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SharepointOAuth2TokenEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SharepointOAuth2TokenEntryPersistenceImpl.class);
}