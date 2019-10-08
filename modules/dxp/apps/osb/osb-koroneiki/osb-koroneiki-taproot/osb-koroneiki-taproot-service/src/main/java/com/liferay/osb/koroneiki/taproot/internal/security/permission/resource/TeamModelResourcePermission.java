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

package com.liferay.osb.koroneiki.taproot.internal.security.permission.resource;

import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.Team",
	service = ModelResourcePermission.class
)
public class TeamModelResourcePermission
	implements ModelResourcePermission<Team> {

	@Override
	public void check(
			PermissionChecker permissionChecker, long teamId, String actionId)
		throws PortalException {

		_teamPermission.check(permissionChecker, teamId, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, Team team, String actionId)
		throws PortalException {

		_teamPermission.check(permissionChecker, team, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long teamId, String actionId)
		throws PortalException {

		return _teamPermission.contains(permissionChecker, teamId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Team team, String actionId)
		throws PortalException {

		return _teamPermission.contains(permissionChecker, team, actionId);
	}

	@Override
	public String getModelName() {
		return Team.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private TeamPermission _teamPermission;

}