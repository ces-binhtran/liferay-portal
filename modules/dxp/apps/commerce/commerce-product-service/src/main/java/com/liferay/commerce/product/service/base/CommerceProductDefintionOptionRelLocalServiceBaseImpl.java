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

package com.liferay.commerce.product.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CommerceProductDefintionOptionRel;
import com.liferay.commerce.product.service.CommerceProductDefintionOptionRelLocalService;
import com.liferay.commerce.product.service.persistence.CommerceProductDefinitionPersistence;
import com.liferay.commerce.product.service.persistence.CommerceProductDefintionOptionRelPersistence;
import com.liferay.commerce.product.service.persistence.CommerceProductDefintionOptionValueRelPersistence;
import com.liferay.commerce.product.service.persistence.CommerceProductInstancePersistence;
import com.liferay.commerce.product.service.persistence.CommerceProductOptionPersistence;
import com.liferay.commerce.product.service.persistence.CommerceProductOptionValuePersistence;

import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce product defintion option rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.service.impl.CommerceProductDefintionOptionRelLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.service.impl.CommerceProductDefintionOptionRelLocalServiceImpl
 * @see com.liferay.commerce.product.service.CommerceProductDefintionOptionRelLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CommerceProductDefintionOptionRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceProductDefintionOptionRelLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.commerce.product.service.CommerceProductDefintionOptionRelLocalServiceUtil} to access the commerce product defintion option rel local service.
	 */

	/**
	 * Adds the commerce product defintion option rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceProductDefintionOptionRel the commerce product defintion option rel
	 * @return the commerce product defintion option rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceProductDefintionOptionRel addCommerceProductDefintionOptionRel(
		CommerceProductDefintionOptionRel commerceProductDefintionOptionRel) {
		commerceProductDefintionOptionRel.setNew(true);

		return commerceProductDefintionOptionRelPersistence.update(commerceProductDefintionOptionRel);
	}

	/**
	 * Creates a new commerce product defintion option rel with the primary key. Does not add the commerce product defintion option rel to the database.
	 *
	 * @param commerceProductDefintionOptionRelId the primary key for the new commerce product defintion option rel
	 * @return the new commerce product defintion option rel
	 */
	@Override
	public CommerceProductDefintionOptionRel createCommerceProductDefintionOptionRel(
		long commerceProductDefintionOptionRelId) {
		return commerceProductDefintionOptionRelPersistence.create(commerceProductDefintionOptionRelId);
	}

	/**
	 * Deletes the commerce product defintion option rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceProductDefintionOptionRelId the primary key of the commerce product defintion option rel
	 * @return the commerce product defintion option rel that was removed
	 * @throws PortalException if a commerce product defintion option rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceProductDefintionOptionRel deleteCommerceProductDefintionOptionRel(
		long commerceProductDefintionOptionRelId) throws PortalException {
		return commerceProductDefintionOptionRelPersistence.remove(commerceProductDefintionOptionRelId);
	}

	/**
	 * Deletes the commerce product defintion option rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceProductDefintionOptionRel the commerce product defintion option rel
	 * @return the commerce product defintion option rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceProductDefintionOptionRel deleteCommerceProductDefintionOptionRel(
		CommerceProductDefintionOptionRel commerceProductDefintionOptionRel) {
		return commerceProductDefintionOptionRelPersistence.remove(commerceProductDefintionOptionRel);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CommerceProductDefintionOptionRel.class,
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
		return commerceProductDefintionOptionRelPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CommerceProductDefintionOptionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return commerceProductDefintionOptionRelPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CommerceProductDefintionOptionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return commerceProductDefintionOptionRelPersistence.findWithDynamicQuery(dynamicQuery,
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
		return commerceProductDefintionOptionRelPersistence.countWithDynamicQuery(dynamicQuery);
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
		return commerceProductDefintionOptionRelPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CommerceProductDefintionOptionRel fetchCommerceProductDefintionOptionRel(
		long commerceProductDefintionOptionRelId) {
		return commerceProductDefintionOptionRelPersistence.fetchByPrimaryKey(commerceProductDefintionOptionRelId);
	}

	/**
	 * Returns the commerce product defintion option rel with the primary key.
	 *
	 * @param commerceProductDefintionOptionRelId the primary key of the commerce product defintion option rel
	 * @return the commerce product defintion option rel
	 * @throws PortalException if a commerce product defintion option rel with the primary key could not be found
	 */
	@Override
	public CommerceProductDefintionOptionRel getCommerceProductDefintionOptionRel(
		long commerceProductDefintionOptionRelId) throws PortalException {
		return commerceProductDefintionOptionRelPersistence.findByPrimaryKey(commerceProductDefintionOptionRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(commerceProductDefintionOptionRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceProductDefintionOptionRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceProductDefintionOptionRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(commerceProductDefintionOptionRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CommerceProductDefintionOptionRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceProductDefintionOptionRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(commerceProductDefintionOptionRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceProductDefintionOptionRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceProductDefintionOptionRelId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return commerceProductDefintionOptionRelLocalService.deleteCommerceProductDefintionOptionRel((CommerceProductDefintionOptionRel)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return commerceProductDefintionOptionRelPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce product defintion option rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CommerceProductDefintionOptionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce product defintion option rels
	 * @param end the upper bound of the range of commerce product defintion option rels (not inclusive)
	 * @return the range of commerce product defintion option rels
	 */
	@Override
	public List<CommerceProductDefintionOptionRel> getCommerceProductDefintionOptionRels(
		int start, int end) {
		return commerceProductDefintionOptionRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce product defintion option rels.
	 *
	 * @return the number of commerce product defintion option rels
	 */
	@Override
	public int getCommerceProductDefintionOptionRelsCount() {
		return commerceProductDefintionOptionRelPersistence.countAll();
	}

	/**
	 * Updates the commerce product defintion option rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceProductDefintionOptionRel the commerce product defintion option rel
	 * @return the commerce product defintion option rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceProductDefintionOptionRel updateCommerceProductDefintionOptionRel(
		CommerceProductDefintionOptionRel commerceProductDefintionOptionRel) {
		return commerceProductDefintionOptionRelPersistence.update(commerceProductDefintionOptionRel);
	}

	/**
	 * Returns the commerce product definition local service.
	 *
	 * @return the commerce product definition local service
	 */
	public com.liferay.commerce.product.service.CommerceProductDefinitionLocalService getCommerceProductDefinitionLocalService() {
		return commerceProductDefinitionLocalService;
	}

	/**
	 * Sets the commerce product definition local service.
	 *
	 * @param commerceProductDefinitionLocalService the commerce product definition local service
	 */
	public void setCommerceProductDefinitionLocalService(
		com.liferay.commerce.product.service.CommerceProductDefinitionLocalService commerceProductDefinitionLocalService) {
		this.commerceProductDefinitionLocalService = commerceProductDefinitionLocalService;
	}

	/**
	 * Returns the commerce product definition persistence.
	 *
	 * @return the commerce product definition persistence
	 */
	public CommerceProductDefinitionPersistence getCommerceProductDefinitionPersistence() {
		return commerceProductDefinitionPersistence;
	}

	/**
	 * Sets the commerce product definition persistence.
	 *
	 * @param commerceProductDefinitionPersistence the commerce product definition persistence
	 */
	public void setCommerceProductDefinitionPersistence(
		CommerceProductDefinitionPersistence commerceProductDefinitionPersistence) {
		this.commerceProductDefinitionPersistence = commerceProductDefinitionPersistence;
	}

	/**
	 * Returns the commerce product defintion option rel local service.
	 *
	 * @return the commerce product defintion option rel local service
	 */
	public CommerceProductDefintionOptionRelLocalService getCommerceProductDefintionOptionRelLocalService() {
		return commerceProductDefintionOptionRelLocalService;
	}

	/**
	 * Sets the commerce product defintion option rel local service.
	 *
	 * @param commerceProductDefintionOptionRelLocalService the commerce product defintion option rel local service
	 */
	public void setCommerceProductDefintionOptionRelLocalService(
		CommerceProductDefintionOptionRelLocalService commerceProductDefintionOptionRelLocalService) {
		this.commerceProductDefintionOptionRelLocalService = commerceProductDefintionOptionRelLocalService;
	}

	/**
	 * Returns the commerce product defintion option rel persistence.
	 *
	 * @return the commerce product defintion option rel persistence
	 */
	public CommerceProductDefintionOptionRelPersistence getCommerceProductDefintionOptionRelPersistence() {
		return commerceProductDefintionOptionRelPersistence;
	}

	/**
	 * Sets the commerce product defintion option rel persistence.
	 *
	 * @param commerceProductDefintionOptionRelPersistence the commerce product defintion option rel persistence
	 */
	public void setCommerceProductDefintionOptionRelPersistence(
		CommerceProductDefintionOptionRelPersistence commerceProductDefintionOptionRelPersistence) {
		this.commerceProductDefintionOptionRelPersistence = commerceProductDefintionOptionRelPersistence;
	}

	/**
	 * Returns the commerce product defintion option value rel local service.
	 *
	 * @return the commerce product defintion option value rel local service
	 */
	public com.liferay.commerce.product.service.CommerceProductDefintionOptionValueRelLocalService getCommerceProductDefintionOptionValueRelLocalService() {
		return commerceProductDefintionOptionValueRelLocalService;
	}

	/**
	 * Sets the commerce product defintion option value rel local service.
	 *
	 * @param commerceProductDefintionOptionValueRelLocalService the commerce product defintion option value rel local service
	 */
	public void setCommerceProductDefintionOptionValueRelLocalService(
		com.liferay.commerce.product.service.CommerceProductDefintionOptionValueRelLocalService commerceProductDefintionOptionValueRelLocalService) {
		this.commerceProductDefintionOptionValueRelLocalService = commerceProductDefintionOptionValueRelLocalService;
	}

	/**
	 * Returns the commerce product defintion option value rel persistence.
	 *
	 * @return the commerce product defintion option value rel persistence
	 */
	public CommerceProductDefintionOptionValueRelPersistence getCommerceProductDefintionOptionValueRelPersistence() {
		return commerceProductDefintionOptionValueRelPersistence;
	}

	/**
	 * Sets the commerce product defintion option value rel persistence.
	 *
	 * @param commerceProductDefintionOptionValueRelPersistence the commerce product defintion option value rel persistence
	 */
	public void setCommerceProductDefintionOptionValueRelPersistence(
		CommerceProductDefintionOptionValueRelPersistence commerceProductDefintionOptionValueRelPersistence) {
		this.commerceProductDefintionOptionValueRelPersistence = commerceProductDefintionOptionValueRelPersistence;
	}

	/**
	 * Returns the commerce product instance local service.
	 *
	 * @return the commerce product instance local service
	 */
	public com.liferay.commerce.product.service.CommerceProductInstanceLocalService getCommerceProductInstanceLocalService() {
		return commerceProductInstanceLocalService;
	}

	/**
	 * Sets the commerce product instance local service.
	 *
	 * @param commerceProductInstanceLocalService the commerce product instance local service
	 */
	public void setCommerceProductInstanceLocalService(
		com.liferay.commerce.product.service.CommerceProductInstanceLocalService commerceProductInstanceLocalService) {
		this.commerceProductInstanceLocalService = commerceProductInstanceLocalService;
	}

	/**
	 * Returns the commerce product instance persistence.
	 *
	 * @return the commerce product instance persistence
	 */
	public CommerceProductInstancePersistence getCommerceProductInstancePersistence() {
		return commerceProductInstancePersistence;
	}

	/**
	 * Sets the commerce product instance persistence.
	 *
	 * @param commerceProductInstancePersistence the commerce product instance persistence
	 */
	public void setCommerceProductInstancePersistence(
		CommerceProductInstancePersistence commerceProductInstancePersistence) {
		this.commerceProductInstancePersistence = commerceProductInstancePersistence;
	}

	/**
	 * Returns the commerce product option local service.
	 *
	 * @return the commerce product option local service
	 */
	public com.liferay.commerce.product.service.CommerceProductOptionLocalService getCommerceProductOptionLocalService() {
		return commerceProductOptionLocalService;
	}

	/**
	 * Sets the commerce product option local service.
	 *
	 * @param commerceProductOptionLocalService the commerce product option local service
	 */
	public void setCommerceProductOptionLocalService(
		com.liferay.commerce.product.service.CommerceProductOptionLocalService commerceProductOptionLocalService) {
		this.commerceProductOptionLocalService = commerceProductOptionLocalService;
	}

	/**
	 * Returns the commerce product option persistence.
	 *
	 * @return the commerce product option persistence
	 */
	public CommerceProductOptionPersistence getCommerceProductOptionPersistence() {
		return commerceProductOptionPersistence;
	}

	/**
	 * Sets the commerce product option persistence.
	 *
	 * @param commerceProductOptionPersistence the commerce product option persistence
	 */
	public void setCommerceProductOptionPersistence(
		CommerceProductOptionPersistence commerceProductOptionPersistence) {
		this.commerceProductOptionPersistence = commerceProductOptionPersistence;
	}

	/**
	 * Returns the commerce product option value local service.
	 *
	 * @return the commerce product option value local service
	 */
	public com.liferay.commerce.product.service.CommerceProductOptionValueLocalService getCommerceProductOptionValueLocalService() {
		return commerceProductOptionValueLocalService;
	}

	/**
	 * Sets the commerce product option value local service.
	 *
	 * @param commerceProductOptionValueLocalService the commerce product option value local service
	 */
	public void setCommerceProductOptionValueLocalService(
		com.liferay.commerce.product.service.CommerceProductOptionValueLocalService commerceProductOptionValueLocalService) {
		this.commerceProductOptionValueLocalService = commerceProductOptionValueLocalService;
	}

	/**
	 * Returns the commerce product option value persistence.
	 *
	 * @return the commerce product option value persistence
	 */
	public CommerceProductOptionValuePersistence getCommerceProductOptionValuePersistence() {
		return commerceProductOptionValuePersistence;
	}

	/**
	 * Sets the commerce product option value persistence.
	 *
	 * @param commerceProductOptionValuePersistence the commerce product option value persistence
	 */
	public void setCommerceProductOptionValuePersistence(
		CommerceProductOptionValuePersistence commerceProductOptionValuePersistence) {
		this.commerceProductOptionValuePersistence = commerceProductOptionValuePersistence;
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

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService getExpandoRowLocalService() {
		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService expandoRowLocalService) {
		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {
		this.expandoRowPersistence = expandoRowPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.commerce.product.model.CommerceProductDefintionOptionRel",
			commerceProductDefintionOptionRelLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.product.model.CommerceProductDefintionOptionRel");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceProductDefintionOptionRelLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceProductDefintionOptionRel.class;
	}

	protected String getModelClassName() {
		return CommerceProductDefintionOptionRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = commerceProductDefintionOptionRelPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.commerce.product.service.CommerceProductDefinitionLocalService.class)
	protected com.liferay.commerce.product.service.CommerceProductDefinitionLocalService commerceProductDefinitionLocalService;
	@BeanReference(type = CommerceProductDefinitionPersistence.class)
	protected CommerceProductDefinitionPersistence commerceProductDefinitionPersistence;
	@BeanReference(type = CommerceProductDefintionOptionRelLocalService.class)
	protected CommerceProductDefintionOptionRelLocalService commerceProductDefintionOptionRelLocalService;
	@BeanReference(type = CommerceProductDefintionOptionRelPersistence.class)
	protected CommerceProductDefintionOptionRelPersistence commerceProductDefintionOptionRelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CommerceProductDefintionOptionValueRelLocalService.class)
	protected com.liferay.commerce.product.service.CommerceProductDefintionOptionValueRelLocalService commerceProductDefintionOptionValueRelLocalService;
	@BeanReference(type = CommerceProductDefintionOptionValueRelPersistence.class)
	protected CommerceProductDefintionOptionValueRelPersistence commerceProductDefintionOptionValueRelPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CommerceProductInstanceLocalService.class)
	protected com.liferay.commerce.product.service.CommerceProductInstanceLocalService commerceProductInstanceLocalService;
	@BeanReference(type = CommerceProductInstancePersistence.class)
	protected CommerceProductInstancePersistence commerceProductInstancePersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CommerceProductOptionLocalService.class)
	protected com.liferay.commerce.product.service.CommerceProductOptionLocalService commerceProductOptionLocalService;
	@BeanReference(type = CommerceProductOptionPersistence.class)
	protected CommerceProductOptionPersistence commerceProductOptionPersistence;
	@BeanReference(type = com.liferay.commerce.product.service.CommerceProductOptionValueLocalService.class)
	protected com.liferay.commerce.product.service.CommerceProductOptionValueLocalService commerceProductOptionValueLocalService;
	@BeanReference(type = CommerceProductOptionValuePersistence.class)
	protected CommerceProductOptionValuePersistence commerceProductOptionValuePersistence;
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
	@ServiceReference(type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService expandoRowLocalService;
	@ServiceReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}