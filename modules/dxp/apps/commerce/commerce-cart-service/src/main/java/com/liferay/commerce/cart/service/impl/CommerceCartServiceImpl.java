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

package com.liferay.commerce.cart.service.impl;

import com.liferay.commerce.cart.model.CommerceCart;
import com.liferay.commerce.cart.service.base.CommerceCartServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 */
public class CommerceCartServiceImpl extends CommerceCartServiceBaseImpl {

	@Override
	public CommerceCart addCommerceCart(
			String name, int type, ServiceContext serviceContext)
		throws PortalException {

		return commerceCartLocalService.addCommerceCart(
			name, type, serviceContext);
	}

	@Override
	public void deleteCommerceCart(long commerceCartId) throws PortalException {
		commerceCartLocalService.deleteCommerceCart(commerceCartId);
	}

	@Override
	public CommerceCart fetchCommerceCart(long commerceCartId) {
		return commerceCartLocalService.fetchCommerceCart(commerceCartId);
	}

	@Override
	public CommerceCart getCommerceCart(long commerceCartId)
		throws PortalException {

		return commerceCartLocalService.getCommerceCart(commerceCartId);
	}

	@Override
	public List<CommerceCart> getCommerceCarts(
		long groupId, int type, int start, int end,
		OrderByComparator<CommerceCart> orderByComparator) {

		return commerceCartLocalService.getCommerceCarts(
			groupId, type, start, end, orderByComparator);
	}

	@Override
	public int getCommerceCartsCount(long groupId, int type) {
		return commerceCartLocalService.getCommerceCartsCount(groupId, type);
	}

}