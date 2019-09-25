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

package com.liferay.osb.koroneiki.taproot.service.base;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleService;
import com.liferay.osb.koroneiki.taproot.service.persistence.AccountFinder;
import com.liferay.osb.koroneiki.taproot.service.persistence.AccountPersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactFinder;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactPersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactRoleFinder;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamFinder;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamPersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamRoleFinder;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamRolePersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the team role remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.koroneiki.taproot.service.impl.TeamRoleServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.koroneiki.taproot.service.impl.TeamRoleServiceImpl
 * @generated
 */
public abstract class TeamRoleServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, TeamRoleService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>TeamRoleService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.osb.koroneiki.taproot.service.TeamRoleServiceUtil</code>.
	 */
	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			TeamRoleService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		teamRoleService = (TeamRoleService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TeamRoleService.class.getName();
	}

	protected Class<?> getModelClass() {
		return TeamRole.class;
	}

	protected String getModelClassName() {
		return TeamRole.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = teamRolePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Reference
	protected AccountPersistence accountPersistence;

	@Reference
	protected AccountFinder accountFinder;

	@Reference
	protected ContactPersistence contactPersistence;

	@Reference
	protected ContactFinder contactFinder;

	@Reference
	protected ContactAccountRolePersistence contactAccountRolePersistence;

	@Reference
	protected ContactRolePersistence contactRolePersistence;

	@Reference
	protected ContactRoleFinder contactRoleFinder;

	@Reference
	protected ContactTeamRolePersistence contactTeamRolePersistence;

	@Reference
	protected TeamPersistence teamPersistence;

	@Reference
	protected TeamFinder teamFinder;

	@Reference
	protected TeamAccountRolePersistence teamAccountRolePersistence;

	@Reference
	protected com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService
		teamRoleLocalService;

	protected TeamRoleService teamRoleService;

	@Reference
	protected TeamRolePersistence teamRolePersistence;

	@Reference
	protected TeamRoleFinder teamRoleFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

}