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

package com.liferay.commerce.discount.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.service.CommerceDiscountRuleLocalService;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountPersistence;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountRelPersistence;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountRulePersistence;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountUsageEntryPersistence;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountUserSegmentRelPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce discount rule local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.discount.service.impl.CommerceDiscountRuleLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.discount.service.impl.CommerceDiscountRuleLocalServiceImpl
 * @see com.liferay.commerce.discount.service.CommerceDiscountRuleLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CommerceDiscountRuleLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CommerceDiscountRuleLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.commerce.discount.service.CommerceDiscountRuleLocalServiceUtil} to access the commerce discount rule local service.
	 */

	/**
	 * Adds the commerce discount rule to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRule the commerce discount rule
	 * @return the commerce discount rule that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceDiscountRule addCommerceDiscountRule(
		CommerceDiscountRule commerceDiscountRule) {
		commerceDiscountRule.setNew(true);

		return commerceDiscountRulePersistence.update(commerceDiscountRule);
	}

	/**
	 * Creates a new commerce discount rule with the primary key. Does not add the commerce discount rule to the database.
	 *
	 * @param commerceDiscountRuleId the primary key for the new commerce discount rule
	 * @return the new commerce discount rule
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceDiscountRule createCommerceDiscountRule(
		long commerceDiscountRuleId) {
		return commerceDiscountRulePersistence.create(commerceDiscountRuleId);
	}

	/**
	 * Deletes the commerce discount rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRuleId the primary key of the commerce discount rule
	 * @return the commerce discount rule that was removed
	 * @throws PortalException if a commerce discount rule with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceDiscountRule deleteCommerceDiscountRule(
		long commerceDiscountRuleId) throws PortalException {
		return commerceDiscountRulePersistence.remove(commerceDiscountRuleId);
	}

	/**
	 * Deletes the commerce discount rule from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRule the commerce discount rule
	 * @return the commerce discount rule that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceDiscountRule deleteCommerceDiscountRule(
		CommerceDiscountRule commerceDiscountRule) throws PortalException {
		return commerceDiscountRulePersistence.remove(commerceDiscountRule);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CommerceDiscountRule.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceDiscountRulePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.discount.model.impl.CommerceDiscountRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return commerceDiscountRulePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.discount.model.impl.CommerceDiscountRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return commerceDiscountRulePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return commerceDiscountRulePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return commerceDiscountRulePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CommerceDiscountRule fetchCommerceDiscountRule(
		long commerceDiscountRuleId) {
		return commerceDiscountRulePersistence.fetchByPrimaryKey(commerceDiscountRuleId);
	}

	/**
	 * Returns the commerce discount rule with the primary key.
	 *
	 * @param commerceDiscountRuleId the primary key of the commerce discount rule
	 * @return the commerce discount rule
	 * @throws PortalException if a commerce discount rule with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRule getCommerceDiscountRule(
		long commerceDiscountRuleId) throws PortalException {
		return commerceDiscountRulePersistence.findByPrimaryKey(commerceDiscountRuleId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(commerceDiscountRuleLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceDiscountRule.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceDiscountRuleId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(commerceDiscountRuleLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CommerceDiscountRule.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceDiscountRuleId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(commerceDiscountRuleLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceDiscountRule.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceDiscountRuleId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return commerceDiscountRuleLocalService.deleteCommerceDiscountRule((CommerceDiscountRule)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return commerceDiscountRulePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce discount rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.discount.model.impl.CommerceDiscountRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount rules
	 * @param end the upper bound of the range of commerce discount rules (not inclusive)
	 * @return the range of commerce discount rules
	 */
	@Override
	public List<CommerceDiscountRule> getCommerceDiscountRules(int start,
		int end) {
		return commerceDiscountRulePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce discount rules.
	 *
	 * @return the number of commerce discount rules
	 */
	@Override
	public int getCommerceDiscountRulesCount() {
		return commerceDiscountRulePersistence.countAll();
	}

	/**
	 * Updates the commerce discount rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRule the commerce discount rule
	 * @return the commerce discount rule that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceDiscountRule updateCommerceDiscountRule(
		CommerceDiscountRule commerceDiscountRule) {
		return commerceDiscountRulePersistence.update(commerceDiscountRule);
	}

	/**
	 * Returns the commerce discount local service.
	 *
	 * @return the commerce discount local service
	 */
	public com.liferay.commerce.discount.service.CommerceDiscountLocalService getCommerceDiscountLocalService() {
		return commerceDiscountLocalService;
	}

	/**
	 * Sets the commerce discount local service.
	 *
	 * @param commerceDiscountLocalService the commerce discount local service
	 */
	public void setCommerceDiscountLocalService(
		com.liferay.commerce.discount.service.CommerceDiscountLocalService commerceDiscountLocalService) {
		this.commerceDiscountLocalService = commerceDiscountLocalService;
	}

	/**
	 * Returns the commerce discount persistence.
	 *
	 * @return the commerce discount persistence
	 */
	public CommerceDiscountPersistence getCommerceDiscountPersistence() {
		return commerceDiscountPersistence;
	}

	/**
	 * Sets the commerce discount persistence.
	 *
	 * @param commerceDiscountPersistence the commerce discount persistence
	 */
	public void setCommerceDiscountPersistence(
		CommerceDiscountPersistence commerceDiscountPersistence) {
		this.commerceDiscountPersistence = commerceDiscountPersistence;
	}

	/**
	 * Returns the commerce discount rel local service.
	 *
	 * @return the commerce discount rel local service
	 */
	public com.liferay.commerce.discount.service.CommerceDiscountRelLocalService getCommerceDiscountRelLocalService() {
		return commerceDiscountRelLocalService;
	}

	/**
	 * Sets the commerce discount rel local service.
	 *
	 * @param commerceDiscountRelLocalService the commerce discount rel local service
	 */
	public void setCommerceDiscountRelLocalService(
		com.liferay.commerce.discount.service.CommerceDiscountRelLocalService commerceDiscountRelLocalService) {
		this.commerceDiscountRelLocalService = commerceDiscountRelLocalService;
	}

	/**
	 * Returns the commerce discount rel persistence.
	 *
	 * @return the commerce discount rel persistence
	 */
	public CommerceDiscountRelPersistence getCommerceDiscountRelPersistence() {
		return commerceDiscountRelPersistence;
	}

	/**
	 * Sets the commerce discount rel persistence.
	 *
	 * @param commerceDiscountRelPersistence the commerce discount rel persistence
	 */
	public void setCommerceDiscountRelPersistence(
		CommerceDiscountRelPersistence commerceDiscountRelPersistence) {
		this.commerceDiscountRelPersistence = commerceDiscountRelPersistence;
	}

	/**
	 * Returns the commerce discount rule local service.
	 *
	 * @return the commerce discount rule local service
	 */
	public CommerceDiscountRuleLocalService getCommerceDiscountRuleLocalService() {
		return commerceDiscountRuleLocalService;
	}

	/**
	 * Sets the commerce discount rule local service.
	 *
	 * @param commerceDiscountRuleLocalService the commerce discount rule local service
	 */
	public void setCommerceDiscountRuleLocalService(
		CommerceDiscountRuleLocalService commerceDiscountRuleLocalService) {
		this.commerceDiscountRuleLocalService = commerceDiscountRuleLocalService;
	}

	/**
	 * Returns the commerce discount rule persistence.
	 *
	 * @return the commerce discount rule persistence
	 */
	public CommerceDiscountRulePersistence getCommerceDiscountRulePersistence() {
		return commerceDiscountRulePersistence;
	}

	/**
	 * Sets the commerce discount rule persistence.
	 *
	 * @param commerceDiscountRulePersistence the commerce discount rule persistence
	 */
	public void setCommerceDiscountRulePersistence(
		CommerceDiscountRulePersistence commerceDiscountRulePersistence) {
		this.commerceDiscountRulePersistence = commerceDiscountRulePersistence;
	}

	/**
	 * Returns the commerce discount usage entry local service.
	 *
	 * @return the commerce discount usage entry local service
	 */
	public com.liferay.commerce.discount.service.CommerceDiscountUsageEntryLocalService getCommerceDiscountUsageEntryLocalService() {
		return commerceDiscountUsageEntryLocalService;
	}

	/**
	 * Sets the commerce discount usage entry local service.
	 *
	 * @param commerceDiscountUsageEntryLocalService the commerce discount usage entry local service
	 */
	public void setCommerceDiscountUsageEntryLocalService(
		com.liferay.commerce.discount.service.CommerceDiscountUsageEntryLocalService commerceDiscountUsageEntryLocalService) {
		this.commerceDiscountUsageEntryLocalService = commerceDiscountUsageEntryLocalService;
	}

	/**
	 * Returns the commerce discount usage entry persistence.
	 *
	 * @return the commerce discount usage entry persistence
	 */
	public CommerceDiscountUsageEntryPersistence getCommerceDiscountUsageEntryPersistence() {
		return commerceDiscountUsageEntryPersistence;
	}

	/**
	 * Sets the commerce discount usage entry persistence.
	 *
	 * @param commerceDiscountUsageEntryPersistence the commerce discount usage entry persistence
	 */
	public void setCommerceDiscountUsageEntryPersistence(
		CommerceDiscountUsageEntryPersistence commerceDiscountUsageEntryPersistence) {
		this.commerceDiscountUsageEntryPersistence = commerceDiscountUsageEntryPersistence;
	}

	/**
	 * Returns the commerce discount user segment rel local service.
	 *
	 * @return the commerce discount user segment rel local service
	 */
	public com.liferay.commerce.discount.service.CommerceDiscountUserSegmentRelLocalService getCommerceDiscountUserSegmentRelLocalService() {
		return commerceDiscountUserSegmentRelLocalService;
	}

	/**
	 * Sets the commerce discount user segment rel local service.
	 *
	 * @param commerceDiscountUserSegmentRelLocalService the commerce discount user segment rel local service
	 */
	public void setCommerceDiscountUserSegmentRelLocalService(
		com.liferay.commerce.discount.service.CommerceDiscountUserSegmentRelLocalService commerceDiscountUserSegmentRelLocalService) {
		this.commerceDiscountUserSegmentRelLocalService = commerceDiscountUserSegmentRelLocalService;
	}

	/**
	 * Returns the commerce discount user segment rel persistence.
	 *
	 * @return the commerce discount user segment rel persistence
	 */
	public CommerceDiscountUserSegmentRelPersistence getCommerceDiscountUserSegmentRelPersistence() {
		return commerceDiscountUserSegmentRelPersistence;
	}

	/**
	 * Sets the commerce discount user segment rel persistence.
	 *
	 * @param commerceDiscountUserSegmentRelPersistence the commerce discount user segment rel persistence
	 */
	public void setCommerceDiscountUserSegmentRelPersistence(
		CommerceDiscountUserSegmentRelPersistence commerceDiscountUserSegmentRelPersistence) {
		this.commerceDiscountUserSegmentRelPersistence = commerceDiscountUserSegmentRelPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.commerce.discount.model.CommerceDiscountRule",
			commerceDiscountRuleLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.discount.model.CommerceDiscountRule");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceDiscountRuleLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceDiscountRule.class;
	}

	protected String getModelClassName() {
		return CommerceDiscountRule.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = commerceDiscountRulePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.commerce.discount.service.CommerceDiscountLocalService.class)
	protected com.liferay.commerce.discount.service.CommerceDiscountLocalService commerceDiscountLocalService;
	@BeanReference(type = CommerceDiscountPersistence.class)
	protected CommerceDiscountPersistence commerceDiscountPersistence;
	@BeanReference(type = com.liferay.commerce.discount.service.CommerceDiscountRelLocalService.class)
	protected com.liferay.commerce.discount.service.CommerceDiscountRelLocalService commerceDiscountRelLocalService;
	@BeanReference(type = CommerceDiscountRelPersistence.class)
	protected CommerceDiscountRelPersistence commerceDiscountRelPersistence;
	@BeanReference(type = CommerceDiscountRuleLocalService.class)
	protected CommerceDiscountRuleLocalService commerceDiscountRuleLocalService;
	@BeanReference(type = CommerceDiscountRulePersistence.class)
	protected CommerceDiscountRulePersistence commerceDiscountRulePersistence;
	@BeanReference(type = com.liferay.commerce.discount.service.CommerceDiscountUsageEntryLocalService.class)
	protected com.liferay.commerce.discount.service.CommerceDiscountUsageEntryLocalService commerceDiscountUsageEntryLocalService;
	@BeanReference(type = CommerceDiscountUsageEntryPersistence.class)
	protected CommerceDiscountUsageEntryPersistence commerceDiscountUsageEntryPersistence;
	@BeanReference(type = com.liferay.commerce.discount.service.CommerceDiscountUserSegmentRelLocalService.class)
	protected com.liferay.commerce.discount.service.CommerceDiscountUserSegmentRelLocalService commerceDiscountUserSegmentRelLocalService;
	@BeanReference(type = CommerceDiscountUserSegmentRelPersistence.class)
	protected CommerceDiscountUserSegmentRelPersistence commerceDiscountUserSegmentRelPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}