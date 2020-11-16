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

package com.liferay.journal.uad.anonymizer;

import com.liferay.journal.model.JournalFeed;
import com.liferay.journal.service.JournalFeedLocalService;
import com.liferay.journal.uad.constants.JournalUADConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the journal feed UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link JournalFeedUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseJournalFeedUADAnonymizer
	extends DynamicQueryUADAnonymizer<JournalFeed> {

	@Override
	public void autoAnonymize(
			JournalFeed journalFeed, long userId, User anonymousUser)
		throws PortalException {

		if (journalFeed.getUserId() == userId) {
			journalFeed.setUserId(anonymousUser.getUserId());
			journalFeed.setUserName(anonymousUser.getFullName());
		}

		journalFeedLocalService.updateJournalFeed(journalFeed);
	}

	@Override
	public void delete(JournalFeed journalFeed) throws PortalException {
		journalFeedLocalService.deleteFeed(journalFeed);
	}

	@Override
	public Class<JournalFeed> getTypeClass() {
		return JournalFeed.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return journalFeedLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return JournalUADConstants.USER_ID_FIELD_NAMES_JOURNAL_FEED;
	}

	@Reference
	protected JournalFeedLocalService journalFeedLocalService;

}