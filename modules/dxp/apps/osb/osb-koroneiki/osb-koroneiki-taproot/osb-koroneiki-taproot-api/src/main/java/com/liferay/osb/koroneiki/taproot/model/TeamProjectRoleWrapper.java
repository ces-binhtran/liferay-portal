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

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link TeamProjectRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamProjectRole
 * @generated
 */
@ProviderType
public class TeamProjectRoleWrapper
	extends BaseModelWrapper<TeamProjectRole>
	implements TeamProjectRole, ModelWrapper<TeamProjectRole> {

	public TeamProjectRoleWrapper(TeamProjectRole teamProjectRole) {
		super(teamProjectRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("teamId", getTeamId());
		attributes.put("projectId", getProjectId());
		attributes.put("teamRoleId", getTeamRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long teamId = (Long)attributes.get("teamId");

		if (teamId != null) {
			setTeamId(teamId);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long teamRoleId = (Long)attributes.get("teamRoleId");

		if (teamRoleId != null) {
			setTeamRoleId(teamRoleId);
		}
	}

	/**
	 * Returns the primary key of this team project role.
	 *
	 * @return the primary key of this team project role
	 */
	@Override
	public
		com.liferay.osb.koroneiki.taproot.service.persistence.TeamProjectRolePK
			getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the project ID of this team project role.
	 *
	 * @return the project ID of this team project role
	 */
	@Override
	public long getProjectId() {
		return model.getProjectId();
	}

	/**
	 * Returns the team ID of this team project role.
	 *
	 * @return the team ID of this team project role
	 */
	@Override
	public long getTeamId() {
		return model.getTeamId();
	}

	/**
	 * Returns the team role ID of this team project role.
	 *
	 * @return the team role ID of this team project role
	 */
	@Override
	public long getTeamRoleId() {
		return model.getTeamRoleId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this team project role.
	 *
	 * @param primaryKey the primary key of this team project role
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.osb.koroneiki.taproot.service.persistence.TeamProjectRolePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the project ID of this team project role.
	 *
	 * @param projectId the project ID of this team project role
	 */
	@Override
	public void setProjectId(long projectId) {
		model.setProjectId(projectId);
	}

	/**
	 * Sets the team ID of this team project role.
	 *
	 * @param teamId the team ID of this team project role
	 */
	@Override
	public void setTeamId(long teamId) {
		model.setTeamId(teamId);
	}

	/**
	 * Sets the team role ID of this team project role.
	 *
	 * @param teamRoleId the team role ID of this team project role
	 */
	@Override
	public void setTeamRoleId(long teamRoleId) {
		model.setTeamRoleId(teamRoleId);
	}

	@Override
	protected TeamProjectRoleWrapper wrap(TeamProjectRole teamProjectRole) {
		return new TeamProjectRoleWrapper(teamProjectRole);
	}

}