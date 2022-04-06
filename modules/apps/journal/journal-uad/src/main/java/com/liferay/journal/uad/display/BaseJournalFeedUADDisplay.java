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

package com.liferay.journal.uad.display;

import com.liferay.journal.model.JournalFeed;
import com.liferay.journal.service.JournalFeedLocalService;
import com.liferay.journal.uad.constants.JournalUADConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.user.associated.data.display.BaseModelUADDisplay;

import java.io.Serializable;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the JournalFeed UAD display.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom methods should be put in
 * {@link JournalFeedUADDisplay}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseJournalFeedUADDisplay
	extends BaseModelUADDisplay<JournalFeed> {

	@Override
	public JournalFeed get(Serializable primaryKey) throws PortalException {
		return journalFeedLocalService.getJournalFeed(
			Long.valueOf(primaryKey.toString()));
	}

	@Override
	public String[] getDisplayFieldNames() {
		return new String[] {"name", "description"};
	}

	@Override
	public Class<JournalFeed> getTypeClass() {
		return JournalFeed.class;
	}

	@Override
	protected long doCount(DynamicQuery dynamicQuery) {
		return journalFeedLocalService.dynamicQueryCount(dynamicQuery);
	}

	@Override
	protected DynamicQuery doGetDynamicQuery() {
		return journalFeedLocalService.dynamicQuery();
	}

	@Override
	protected List<JournalFeed> doGetRange(
		DynamicQuery dynamicQuery, int start, int end) {

		return journalFeedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return JournalUADConstants.USER_ID_FIELD_NAMES_JOURNAL_FEED;
	}

	@Reference
	protected JournalFeedLocalService journalFeedLocalService;

}