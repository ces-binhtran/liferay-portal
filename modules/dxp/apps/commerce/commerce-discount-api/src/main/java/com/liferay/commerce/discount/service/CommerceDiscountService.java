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

package com.liferay.commerce.discount.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.model.CommerceDiscount;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.math.BigDecimal;

/**
 * Provides the remote service interface for CommerceDiscount. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceDiscountServiceUtil
 * @see com.liferay.commerce.discount.service.base.CommerceDiscountServiceBaseImpl
 * @see com.liferay.commerce.discount.service.impl.CommerceDiscountServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceDiscount"}, service = CommerceDiscountService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceDiscountService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceDiscountServiceUtil} to access the commerce discount remote service. Add custom service methods to {@link com.liferay.commerce.discount.service.impl.CommerceDiscountServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceDiscount addCommerceDiscount(java.lang.String title,
		java.lang.String target, java.lang.String type,
		java.lang.String typeSettings, boolean useCouponCode,
		java.lang.String couponCode, java.lang.String limitationType,
		int limitationTimes, int numberOfUse, boolean cumulative,
		boolean usePercentage, BigDecimal level1, BigDecimal level2,
		BigDecimal level3, BigDecimal maximumDiscountAmount, boolean active,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		ServiceContext serviceContext) throws PortalException;

	public void deleteCommerceDiscount(long commerceDiscountId)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public CommerceDiscount updateCommerceDiscount(long commerceDiscountId,
		java.lang.String title, java.lang.String target, java.lang.String type,
		java.lang.String typeSettings, boolean useCouponCode,
		java.lang.String couponCode, java.lang.String limitationType,
		int limitationTimes, int numberOfUse, boolean cumulative,
		boolean usePercentage, BigDecimal level1, BigDecimal level2,
		BigDecimal level3, BigDecimal maximumDiscountAmount, boolean active,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		ServiceContext serviceContext) throws PortalException;
}