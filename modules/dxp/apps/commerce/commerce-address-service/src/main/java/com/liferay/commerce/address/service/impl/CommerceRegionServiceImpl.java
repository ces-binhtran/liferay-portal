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

package com.liferay.commerce.address.service.impl;

import com.liferay.commerce.address.constants.CommerceAddressActionKeys;
import com.liferay.commerce.address.model.CommerceCountry;
import com.liferay.commerce.address.model.CommerceRegion;
import com.liferay.commerce.address.service.base.CommerceRegionServiceBaseImpl;
import com.liferay.commerce.address.service.permission.CommerceAddressPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 */
public class CommerceRegionServiceImpl extends CommerceRegionServiceBaseImpl {

	@Override
	public CommerceRegion addCommerceRegion(
			long commerceCountryId, String name, String abbreviation,
			double priority, boolean active, ServiceContext serviceContext)
		throws PortalException {

		CommerceCountry commerceCountry =
			commerceCountryPersistence.findByPrimaryKey(commerceCountryId);

		CommerceAddressPermission.check(
			getPermissionChecker(), commerceCountry.getGroupId(),
			CommerceAddressActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.addCommerceRegion(
			commerceCountry.getCommerceCountryId(), name, abbreviation,
			priority, active, serviceContext);
	}

	@Override
	public void deleteCommerceRegion(long commerceRegionId)
		throws PortalException {

		CommerceRegion commerceRegion =
			commerceRegionPersistence.findByPrimaryKey(commerceRegionId);

		CommerceAddressPermission.check(
			getPermissionChecker(), commerceRegion.getGroupId(),
			CommerceAddressActionKeys.MANAGE_COMMERCE_COUNTRIES);

		commerceRegionLocalService.deleteCommerceRegion(commerceRegion);
	}

	@Override
	public CommerceRegion getCommerceRegion(long commerceRegionId)
		throws PortalException {

		return commerceRegionLocalService.getCommerceRegion(commerceRegionId);
	}

	@Override
	public List<CommerceRegion> getCommerceRegions(
		long commerceCountryId, int start, int end,
		OrderByComparator<CommerceRegion> orderByComparator) {

		return commerceRegionLocalService.getCommerceRegions(
			commerceCountryId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceRegionsCount(long commerceCountryId) {
		return commerceRegionLocalService.getCommerceRegionsCount(
			commerceCountryId);
	}

	@Override
	public CommerceRegion updateCommerceRegion(
			long commerceRegionId, String name, String abbreviation,
			double priority, boolean active)
		throws PortalException {

		CommerceRegion commerceRegion =
			commerceRegionPersistence.findByPrimaryKey(commerceRegionId);

		CommerceAddressPermission.check(
			getPermissionChecker(), commerceRegion.getGroupId(),
			CommerceAddressActionKeys.MANAGE_COMMERCE_COUNTRIES);

		return commerceRegionLocalService.updateCommerceRegion(
			commerceRegion.getCommerceRegionId(), name, abbreviation, priority,
			active);
	}

}