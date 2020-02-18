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

package com.liferay.osb.koroneiki.trunk.service.base;

import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldService;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductConsumptionFinder;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductConsumptionPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductEntryPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductFieldFinder;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductFieldPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductPurchaseFinder;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductPurchasePersistence;
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
 * Provides the base implementation for the product field remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.koroneiki.trunk.service.impl.ProductFieldServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.koroneiki.trunk.service.impl.ProductFieldServiceImpl
 * @generated
 */
public abstract class ProductFieldServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, ProductFieldService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ProductFieldService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.osb.koroneiki.trunk.service.ProductFieldServiceUtil</code>.
	 */
	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ProductFieldService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		productFieldService = (ProductFieldService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ProductFieldService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ProductField.class;
	}

	protected String getModelClassName() {
		return ProductField.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = productFieldPersistence.getDataSource();

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

	@Reference
	protected ProductConsumptionPersistence productConsumptionPersistence;

	@Reference
	protected ProductConsumptionFinder productConsumptionFinder;

	@Reference
	protected ProductEntryPersistence productEntryPersistence;

	@Reference
	protected com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService
		productFieldLocalService;

	protected ProductFieldService productFieldService;

	@Reference
	protected ProductFieldPersistence productFieldPersistence;

	@Reference
	protected ProductFieldFinder productFieldFinder;

	@Reference
	protected ProductPurchasePersistence productPurchasePersistence;

	@Reference
	protected ProductPurchaseFinder productPurchaseFinder;

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