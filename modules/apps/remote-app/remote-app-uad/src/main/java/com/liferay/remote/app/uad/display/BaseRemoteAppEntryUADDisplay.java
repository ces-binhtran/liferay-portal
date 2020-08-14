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

package com.liferay.remote.app.uad.display;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.remote.app.model.RemoteAppEntry;
import com.liferay.remote.app.service.RemoteAppEntryLocalService;
import com.liferay.remote.app.uad.constants.RemoteAppUADConstants;
import com.liferay.user.associated.data.display.BaseModelUADDisplay;

import java.io.Serializable;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the RemoteAppEntry UAD display.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom methods should be put in
 * {@link RemoteAppEntryUADDisplay}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseRemoteAppEntryUADDisplay
	extends BaseModelUADDisplay<RemoteAppEntry> {

	@Override
	public RemoteAppEntry get(Serializable primaryKey) throws PortalException {
		return remoteAppEntryLocalService.getRemoteAppEntry(
			Long.valueOf(primaryKey.toString()));
	}

	@Override
	public String[] getDisplayFieldNames() {
		return new String[] {"name", "url"};
	}

	@Override
	public Class<RemoteAppEntry> getTypeClass() {
		return RemoteAppEntry.class;
	}

	@Override
	protected long doCount(DynamicQuery dynamicQuery) {
		return remoteAppEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	@Override
	protected DynamicQuery doGetDynamicQuery() {
		return remoteAppEntryLocalService.dynamicQuery();
	}

	@Override
	protected List<RemoteAppEntry> doGetRange(
		DynamicQuery dynamicQuery, int start, int end) {

		return remoteAppEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return RemoteAppUADConstants.USER_ID_FIELD_NAMES_REMOTE_APP_ENTRY;
	}

	@Reference
	protected RemoteAppEntryLocalService remoteAppEntryLocalService;

}