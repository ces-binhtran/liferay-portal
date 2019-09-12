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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalServiceUtil;

/**
 * The extended model base implementation for the TeamRole service. Represents a row in the &quot;Koroneiki_TeamRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TeamRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleImpl
 * @see TeamRole
 * @generated
 */
public abstract class TeamRoleBaseImpl
	extends TeamRoleModelImpl implements TeamRole {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a team role model instance should use the <code>TeamRole</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			TeamRoleLocalServiceUtil.addTeamRole(this);
		}
		else {
			TeamRoleLocalServiceUtil.updateTeamRole(this);
		}
	}

}