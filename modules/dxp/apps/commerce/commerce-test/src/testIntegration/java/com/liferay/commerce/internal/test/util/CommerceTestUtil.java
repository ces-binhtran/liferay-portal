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

package com.liferay.commerce.internal.test.util;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.commerce.service.CommerceWarehouseItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceWarehouseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

import java.math.BigDecimal;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceTestUtil {

	public static CommerceOrder addCommerceOrder(long groupId)
		throws Exception {

		return addCommerceOrder(
			groupId, CommerceOrderConstants.ORDER_STATUS_COMPLETED);
	}

	public static CommerceOrder addCommerceOrder(long groupId, int orderStatus)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommerceOrderLocalServiceUtil.addUserCommerceOrder(
			groupId, serviceContext.getUserId());
	}

	public static CommerceOrderItem addCommerceOrderItem(
			long commerceOrderId, long cpInstanceId)
		throws Exception {

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.getCommerceOrder(commerceOrderId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId());

		return CommerceOrderItemLocalServiceUtil.addCommerceOrderItem(
			commerceOrderId, cpInstanceId, RandomTestUtil.randomInt(),
			RandomTestUtil.randomInt(), null,
			new BigDecimal(RandomTestUtil.nextDouble()), null, serviceContext);
	}

	public static CommerceWarehouse addCommerceWarehouse(long groupId)
		throws PortalException {

		CommerceCountry commerceCountry =
			CommerceCountryLocalServiceUtil.fetchCommerceCountry(groupId, 380);

		CommerceRegion commerceRegion =
			CommerceRegionLocalServiceUtil.getCommerceRegion(
				commerceCountry.getCommerceCountryId(), "VE");

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommerceWarehouseLocalServiceUtil.addCommerceWarehouse(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), true,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), commerceRegion.getCommerceRegionId(),
			commerceCountry.getCommerceCountryId(), 45.4386111, 12.3266667,
			serviceContext);
	}

	public static CommerceWarehouse addCommerceWarehouse(
			long groupId, String name)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommerceWarehouseLocalServiceUtil.addCommerceWarehouse(
			name, RandomTestUtil.randomString(), true,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), 0, 0, RandomTestUtil.randomDouble(),
			RandomTestUtil.randomDouble(), serviceContext);
	}

	public static CommerceWarehouseItem addCommerceWarehouseItem(
			CommerceWarehouse commerceWarehouse, long cpInstanceId,
			int quantity)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceWarehouse.getGroupId());

		return CommerceWarehouseItemLocalServiceUtil.addCommerceWarehouseItem(
			commerceWarehouse.getCommerceWarehouseId(), cpInstanceId, quantity,
			serviceContext);
	}

}