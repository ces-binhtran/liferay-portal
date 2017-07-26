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

package com.liferay.commerce.currency.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceCurrencyLocalService}.
 *
 * @author Andrea Di Giorgi
 * @see CommerceCurrencyLocalService
 * @generated
 */
@ProviderType
public class CommerceCurrencyLocalServiceWrapper
	implements CommerceCurrencyLocalService,
		ServiceWrapper<CommerceCurrencyLocalService> {
	public CommerceCurrencyLocalServiceWrapper(
		CommerceCurrencyLocalService commerceCurrencyLocalService) {
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
	}

	/**
	* Adds the commerce currency to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceCurrency the commerce currency
	* @return the commerce currency that was added
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency addCommerceCurrency(
		com.liferay.commerce.currency.model.CommerceCurrency commerceCurrency) {
		return _commerceCurrencyLocalService.addCommerceCurrency(commerceCurrency);
	}

	/**
	* Creates a new commerce currency with the primary key. Does not add the commerce currency to the database.
	*
	* @param commerceCurrencyId the primary key for the new commerce currency
	* @return the new commerce currency
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency createCommerceCurrency(
		long commerceCurrencyId) {
		return _commerceCurrencyLocalService.createCommerceCurrency(commerceCurrencyId);
	}

	/**
	* Deletes the commerce currency from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceCurrency the commerce currency
	* @return the commerce currency that was removed
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency deleteCommerceCurrency(
		com.liferay.commerce.currency.model.CommerceCurrency commerceCurrency) {
		return _commerceCurrencyLocalService.deleteCommerceCurrency(commerceCurrency);
	}

	/**
	* Deletes the commerce currency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceCurrencyId the primary key of the commerce currency
	* @return the commerce currency that was removed
	* @throws PortalException if a commerce currency with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency deleteCommerceCurrency(
		long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCurrencyLocalService.deleteCommerceCurrency(commerceCurrencyId);
	}

	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency fetchCommerceCurrency(
		long commerceCurrencyId) {
		return _commerceCurrencyLocalService.fetchCommerceCurrency(commerceCurrencyId);
	}

	/**
	* Returns the commerce currency matching the UUID and group.
	*
	* @param uuid the commerce currency's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency fetchCommerceCurrencyByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _commerceCurrencyLocalService.fetchCommerceCurrencyByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the commerce currency with the primary key.
	*
	* @param commerceCurrencyId the primary key of the commerce currency
	* @return the commerce currency
	* @throws PortalException if a commerce currency with the primary key could not be found
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency getCommerceCurrency(
		long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCurrencyLocalService.getCommerceCurrency(commerceCurrencyId);
	}

	/**
	* Returns the commerce currency matching the UUID and group.
	*
	* @param uuid the commerce currency's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce currency
	* @throws PortalException if a matching commerce currency could not be found
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency getCommerceCurrencyByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCurrencyLocalService.getCommerceCurrencyByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the commerce currency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceCurrency the commerce currency
	* @return the commerce currency that was updated
	*/
	@Override
	public com.liferay.commerce.currency.model.CommerceCurrency updateCommerceCurrency(
		com.liferay.commerce.currency.model.CommerceCurrency commerceCurrency) {
		return _commerceCurrencyLocalService.updateCommerceCurrency(commerceCurrency);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commerceCurrencyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceCurrencyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _commerceCurrencyLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commerceCurrencyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCurrencyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCurrencyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of commerce currencies.
	*
	* @return the number of commerce currencies
	*/
	@Override
	public int getCommerceCurrenciesCount() {
		return _commerceCurrencyLocalService.getCommerceCurrenciesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _commerceCurrencyLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceCurrencyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _commerceCurrencyLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _commerceCurrencyLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the commerce currencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce currencies
	* @param end the upper bound of the range of commerce currencies (not inclusive)
	* @return the range of commerce currencies
	*/
	@Override
	public java.util.List<com.liferay.commerce.currency.model.CommerceCurrency> getCommerceCurrencies(
		int start, int end) {
		return _commerceCurrencyLocalService.getCommerceCurrencies(start, end);
	}

	/**
	* Returns all the commerce currencies matching the UUID and company.
	*
	* @param uuid the UUID of the commerce currencies
	* @param companyId the primary key of the company
	* @return the matching commerce currencies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.currency.model.CommerceCurrency> getCommerceCurrenciesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _commerceCurrencyLocalService.getCommerceCurrenciesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of commerce currencies matching the UUID and company.
	*
	* @param uuid the UUID of the commerce currencies
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of commerce currencies
	* @param end the upper bound of the range of commerce currencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching commerce currencies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.commerce.currency.model.CommerceCurrency> getCommerceCurrenciesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.currency.model.CommerceCurrency> orderByComparator) {
		return _commerceCurrencyLocalService.getCommerceCurrenciesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _commerceCurrencyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _commerceCurrencyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public CommerceCurrencyLocalService getWrappedService() {
		return _commerceCurrencyLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceCurrencyLocalService commerceCurrencyLocalService) {
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
	}

	private CommerceCurrencyLocalService _commerceCurrencyLocalService;
}