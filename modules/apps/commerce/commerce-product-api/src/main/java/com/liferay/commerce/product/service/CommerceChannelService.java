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

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

/**
 * Provides the remote service interface for CommerceChannel. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceChannelServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=commerce",
		"json.web.service.context.path=CommerceChannel"
	},
	service = CommerceChannelService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceChannelService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CommerceChannelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the commerce channel remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CommerceChannelServiceUtil} if injection and service tracking are not available.
	 */
	public CommerceChannel addCommerceChannel(
			long siteGroupId, String name, String type,
			UnicodeProperties typeSettingsProperties,
			String commerceCurrencyCode, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceChannel deleteCommerceChannel(long commerceChannelId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceChannel fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceChannel fetchCommerceChannel(long commerceChannelId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceChannel getCommerceChannel(long commerceChannelId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceChannel getCommerceChannelByOrderGroupId(long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceChannel> getCommerceChannels(int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceChannel> getCommerceChannels(long companyId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceChannel> searchCommerceChannels(long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceChannel> searchCommerceChannels(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCommerceChannelsCount(long companyId, String keywords)
		throws PortalException;

	public CommerceChannel updateCommerceChannel(
			long commerceChannelId, long siteGroupId, String name, String type,
			UnicodeProperties typeSettingsProperties,
			String commerceCurrencyCode)
		throws PortalException;

	public CommerceChannel updateCommerceChannel(
			long commerceChannelId, long siteGroupId, String name, String type,
			UnicodeProperties typeSettingsProperties,
			String commerceCurrencyCode, String priceDisplayType,
			boolean discountsTargetNetPrice)
		throws PortalException;

	public CommerceChannel updateCommerceChannelExternalReferenceCode(
			long commerceChannelId, String externalReferenceCode)
		throws PortalException;

}