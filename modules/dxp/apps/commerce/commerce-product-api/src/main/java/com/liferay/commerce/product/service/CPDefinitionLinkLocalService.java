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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPDefinitionLink;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CPDefinitionLink. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Marco Leo
 * @see CPDefinitionLinkLocalServiceUtil
 * @see com.liferay.commerce.product.service.base.CPDefinitionLinkLocalServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CPDefinitionLinkLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CPDefinitionLinkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionLinkLocalServiceUtil} to access the cp definition link local service. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CPDefinitionLinkLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the cp definition link to the database. Also notifies the appropriate model listeners.
	*
	* @param cpDefinitionLink the cp definition link
	* @return the cp definition link that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CPDefinitionLink addCPDefinitionLink(
		CPDefinitionLink cpDefinitionLink);

	public CPDefinitionLink addCPDefinitionLink(long cpDefinitionId1,
		long cpDefinitionId2, double priority, int type,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new cp definition link with the primary key. Does not add the cp definition link to the database.
	*
	* @param CPDefinitionLinkId the primary key for the new cp definition link
	* @return the new cp definition link
	*/
	public CPDefinitionLink createCPDefinitionLink(long CPDefinitionLinkId);

	/**
	* Deletes the cp definition link from the database. Also notifies the appropriate model listeners.
	*
	* @param cpDefinitionLink the cp definition link
	* @return the cp definition link that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public CPDefinitionLink deleteCPDefinitionLink(
		CPDefinitionLink cpDefinitionLink) throws PortalException;

	/**
	* Deletes the cp definition link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param CPDefinitionLinkId the primary key of the cp definition link
	* @return the cp definition link that was removed
	* @throws PortalException if a cp definition link with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CPDefinitionLink deleteCPDefinitionLink(long CPDefinitionLinkId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPDefinitionLink fetchCPDefinitionLink(long CPDefinitionLinkId);

	/**
	* Returns the cp definition link with the primary key.
	*
	* @param CPDefinitionLinkId the primary key of the cp definition link
	* @return the cp definition link
	* @throws PortalException if a cp definition link with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CPDefinitionLink getCPDefinitionLink(long CPDefinitionLinkId)
		throws PortalException;

	/**
	* Updates the cp definition link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cpDefinitionLink the cp definition link
	* @return the cp definition link that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CPDefinitionLink updateCPDefinitionLink(
		CPDefinitionLink cpDefinitionLink);

	public CPDefinitionLink updateCPDefinitionLink(long cpDefinitionLinkId,
		double priority) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of cp definition links.
	*
	* @return the number of cp definition links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCPDefinitionLinksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCPDefinitionLinksCount(long cpDefinitionId)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the cp definition links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cp definition links
	* @param end the upper bound of the range of cp definition links (not inclusive)
	* @return the range of cp definition links
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPDefinitionLink> getCPDefinitionLinks(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPDefinitionLink> getCPDefinitionLinks(long cpDefinitionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CPDefinitionLink> getCPDefinitionLinks(long cpDefinitionId,
		int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator)
		throws PortalException;

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void updateCPDefinitionLinks(long cpDefinitionId,
		long[] cpDefinitionLinkEntryIds, int type, ServiceContext serviceContext)
		throws PortalException;
}