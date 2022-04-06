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

package com.liferay.microblogs.internal.upgrade.v1_0_2;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class SocialUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeMicroblogActivities();
	}

	private void _updateSocialActivity(long activityId, JSONObject jsonObject)
		throws Exception {

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update SocialActivity set extraData = ? where activityId = " +
					"?")) {

			preparedStatement.setString(1, jsonObject.toString());
			preparedStatement.setLong(2, activityId);

			preparedStatement.executeUpdate();
		}
	}

	private void _upgradeMicroblogActivities() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"select activityId, extraData from SocialActivity where " +
					"classNameId = ?")) {

			preparedStatement.setLong(
				1, PortalUtil.getClassNameId(MicroblogsEntry.class));

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					long activityId = resultSet.getLong("activityId");

					String extraData = resultSet.getString("extraData");

					JSONObject extraDataJSONObject =
						JSONFactoryUtil.createJSONObject(extraData);

					long parentMicroblogsEntryId = extraDataJSONObject.getLong(
						"receiverMicroblogsEntryId");

					extraDataJSONObject.put(
						"parentMicroblogsEntryId", parentMicroblogsEntryId);

					extraDataJSONObject.remove("receiverMicroblogsEntryId");

					_updateSocialActivity(activityId, extraDataJSONObject);
				}
			}
		}
	}

}