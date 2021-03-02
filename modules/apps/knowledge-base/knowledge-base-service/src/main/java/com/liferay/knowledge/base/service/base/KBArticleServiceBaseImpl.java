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

package com.liferay.knowledge.base.service.base;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.service.KBArticleService;
import com.liferay.knowledge.base.service.KBArticleServiceUtil;
import com.liferay.knowledge.base.service.persistence.KBArticleFinder;
import com.liferay.knowledge.base.service.persistence.KBArticlePersistence;
import com.liferay.knowledge.base.service.persistence.KBCommentPersistence;
import com.liferay.knowledge.base.service.persistence.KBFolderFinder;
import com.liferay.knowledge.base.service.persistence.KBFolderPersistence;
import com.liferay.knowledge.base.service.persistence.KBTemplatePersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the kb article remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.knowledge.base.service.impl.KBArticleServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledge.base.service.impl.KBArticleServiceImpl
 * @generated
 */
public abstract class KBArticleServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, KBArticleService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>KBArticleService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>KBArticleServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			KBArticleService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		kbArticleService = (KBArticleService)aopProxy;

		_setServiceUtilService(kbArticleService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return KBArticleService.class.getName();
	}

	protected Class<?> getModelClass() {
		return KBArticle.class;
	}

	protected String getModelClassName() {
		return KBArticle.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = kbArticlePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(KBArticleService kbArticleService) {
		try {
			Field field = KBArticleServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, kbArticleService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected com.liferay.knowledge.base.service.KBArticleLocalService
		kbArticleLocalService;

	protected KBArticleService kbArticleService;

	@Reference
	protected KBArticlePersistence kbArticlePersistence;

	@Reference
	protected KBArticleFinder kbArticleFinder;

	@Reference
	protected KBCommentPersistence kbCommentPersistence;

	@Reference
	protected KBFolderPersistence kbFolderPersistence;

	@Reference
	protected KBFolderFinder kbFolderFinder;

	@Reference
	protected KBTemplatePersistence kbTemplatePersistence;

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
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.GroupService groupService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

	@Reference
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryService
		assetEntryService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetLinkLocalService
		assetLinkLocalService;

	@Reference
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService
		expandoRowLocalService;

	@Reference
	protected com.liferay.ratings.kernel.service.RatingsStatsLocalService
		ratingsStatsLocalService;

	@Reference
	protected com.liferay.social.kernel.service.SocialActivityLocalService
		socialActivityLocalService;

	@Reference
	protected com.liferay.social.kernel.service.SocialActivityService
		socialActivityService;

}