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

package com.liferay.saml.persistence.service;

import aQute.bnd.annotation.ProviderType;

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

import com.liferay.saml.persistence.model.SamlIdpSpConnection;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SamlIdpSpConnection. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Mika Koivisto
 * @see SamlIdpSpConnectionLocalServiceUtil
 * @see com.liferay.saml.persistence.service.base.SamlIdpSpConnectionLocalServiceBaseImpl
 * @see com.liferay.saml.persistence.service.impl.SamlIdpSpConnectionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SamlIdpSpConnectionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SamlIdpSpConnectionLocalServiceUtil} to access the saml idp sp connection local service. Add custom service methods to {@link com.liferay.saml.persistence.service.impl.SamlIdpSpConnectionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
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
	* Adds the saml idp sp connection to the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpConnection the saml idp sp connection
	* @return the saml idp sp connection that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SamlIdpSpConnection addSamlIdpSpConnection(
		SamlIdpSpConnection samlIdpSpConnection);

	public SamlIdpSpConnection addSamlIdpSpConnection(
		java.lang.String samlSpEntityId, int assertionLifetime,
		java.lang.String attributeNames, boolean attributesEnabled,
		boolean attributesNamespaceEnabled, boolean enabled,
		java.lang.String metadataUrl, InputStream metadataXmlInputStream,
		java.lang.String name, java.lang.String nameIdAttribute,
		java.lang.String nameIdFormat, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new saml idp sp connection with the primary key. Does not add the saml idp sp connection to the database.
	*
	* @param samlIdpSpConnectionId the primary key for the new saml idp sp connection
	* @return the new saml idp sp connection
	*/
	public SamlIdpSpConnection createSamlIdpSpConnection(
		long samlIdpSpConnectionId);

	/**
	* Deletes the saml idp sp connection from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpConnection the saml idp sp connection
	* @return the saml idp sp connection that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SamlIdpSpConnection deleteSamlIdpSpConnection(
		SamlIdpSpConnection samlIdpSpConnection);

	/**
	* Deletes the saml idp sp connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection that was removed
	* @throws PortalException if a saml idp sp connection with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SamlIdpSpConnection deleteSamlIdpSpConnection(
		long samlIdpSpConnectionId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SamlIdpSpConnection fetchSamlIdpSpConnection(
		long samlIdpSpConnectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SamlIdpSpConnection getSamlIdpSpConnection(long companyId,
		java.lang.String samlSpEntityId) throws PortalException;

	/**
	* Returns the saml idp sp connection with the primary key.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection
	* @throws PortalException if a saml idp sp connection with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SamlIdpSpConnection getSamlIdpSpConnection(
		long samlIdpSpConnectionId) throws PortalException;

	/**
	* Updates the saml idp sp connection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpConnection the saml idp sp connection
	* @return the saml idp sp connection that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SamlIdpSpConnection updateSamlIdpSpConnection(
		SamlIdpSpConnection samlIdpSpConnection);

	public SamlIdpSpConnection updateSamlIdpSpConnection(
		long samlIdpSpConnectionId, java.lang.String samlSpEntityId,
		int assertionLifetime, java.lang.String attributeNames,
		boolean attributesEnabled, boolean attributesNamespaceEnabled,
		boolean enabled, java.lang.String metadataUrl,
		InputStream metadataXmlInputStream, java.lang.String name,
		java.lang.String nameIdAttribute, java.lang.String nameIdFormat,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Returns the number of saml idp sp connections.
	*
	* @return the number of saml idp sp connections
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSamlIdpSpConnectionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSamlIdpSpConnectionsCount(long companyId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.persistence.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.persistence.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the saml idp sp connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.persistence.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sp connections
	* @param end the upper bound of the range of saml idp sp connections (not inclusive)
	* @return the range of saml idp sp connections
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SamlIdpSpConnection> getSamlIdpSpConnections(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SamlIdpSpConnection> getSamlIdpSpConnections(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SamlIdpSpConnection> getSamlIdpSpConnections(long companyId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SamlIdpSpConnection> getSamlIdpSpConnections(long companyId,
		int start, int end, OrderByComparator orderByComparator);

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

	public void updateMetadata(long samlIdpSpConnectionId)
		throws PortalException;
}