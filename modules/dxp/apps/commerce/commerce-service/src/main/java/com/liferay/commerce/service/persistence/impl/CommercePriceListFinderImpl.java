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

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommercePriceList;
import com.liferay.commerce.model.impl.CommercePriceListImpl;
import com.liferay.commerce.service.persistence.CommercePriceListFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListFinderImpl
	extends CommercePriceListFinderBaseImpl implements CommercePriceListFinder {

	public static final String FIND_BY_EXPIRATION_DATE =
		CommercePriceListFinder.class.getName() + ".findByExpirationDate";

	@Override
	public List<CommercePriceList> findByExpirationDate(
		Date expirationDate,
		QueryDefinition<CommercePriceList> queryDefinition) {

		return doFindByExpirationDate(expirationDate, queryDefinition, false);
	}

	protected List<CommercePriceList> doFindByExpirationDate(
		Date expirationDate, QueryDefinition<CommercePriceList> queryDefinition,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_BY_EXPIRATION_DATE, queryDefinition,
				CommercePriceListImpl.TABLE_NAME);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				CommercePriceListImpl.TABLE_NAME, CommercePriceListImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (expirationDate != null) {
				qPos.add(expirationDate);
			}

			qPos.add(queryDefinition.getStatus());

			return (List<CommercePriceList>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}