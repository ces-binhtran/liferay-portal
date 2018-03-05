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

package com.liferay.commerce.price.list.qualification.type.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommercePriceListUserRel. This utility wraps
 * {@link com.liferay.commerce.price.list.qualification.type.service.impl.CommercePriceListUserRelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListUserRelService
 * @see com.liferay.commerce.price.list.qualification.type.service.base.CommercePriceListUserRelServiceBaseImpl
 * @see com.liferay.commerce.price.list.qualification.type.service.impl.CommercePriceListUserRelServiceImpl
 * @generated
 */
@ProviderType
public class CommercePriceListUserRelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.price.list.qualification.type.service.impl.CommercePriceListUserRelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel addCommercePriceListUserRel(
		long commercePriceListQualificationTypeRelId,
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommercePriceListUserRel(commercePriceListQualificationTypeRelId,
			className, classPK, serviceContext);
	}

	public static void deleteCommercePriceListUserRel(
		long commercePriceListUserRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommercePriceListUserRel(commercePriceListUserRelId);
	}

	public static void deleteCommercePriceListUserRels(
		long commercePriceListQualificationTypeRelId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCommercePriceListUserRels(commercePriceListQualificationTypeRelId,
			className, classPK);
	}

	public static com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel getCommercePriceListUserRel(
		long commercePriceListUserRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommercePriceListUserRel(commercePriceListUserRelId);
	}

	public static java.util.List<com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel> getCommercePriceListUserRels(
		long commercePriceListQualificationTypeRelId, java.lang.String className) {
		return getService()
				   .getCommercePriceListUserRels(commercePriceListQualificationTypeRelId,
			className);
	}

	public static java.util.List<com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel> getCommercePriceListUserRels(
		long commercePriceListQualificationTypeRelId,
		java.lang.String className, int start, int end) {
		return getService()
				   .getCommercePriceListUserRels(commercePriceListQualificationTypeRelId,
			className, start, end);
	}

	public static java.util.List<com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel> getCommercePriceListUserRels(
		long commercePriceListQualificationTypeRelId,
		java.lang.String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel> orderByComparator) {
		return getService()
				   .getCommercePriceListUserRels(commercePriceListQualificationTypeRelId,
			className, start, end, orderByComparator);
	}

	public static int getCommercePriceListUserRelsCount(
		long commercePriceListQualificationTypeRelId, java.lang.String className) {
		return getService()
				   .getCommercePriceListUserRelsCount(commercePriceListQualificationTypeRelId,
			className);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.price.list.qualification.type.model.CommercePriceListUserRel updateCommercePriceListUserRel(
		long commercePriceListUserRelId,
		long commercePriceListQualificationTypeRelId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommercePriceListUserRel(commercePriceListUserRelId,
			commercePriceListQualificationTypeRelId, serviceContext);
	}

	public static CommercePriceListUserRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommercePriceListUserRelService, CommercePriceListUserRelService> _serviceTracker =
		ServiceTrackerFactory.open(CommercePriceListUserRelService.class);
}