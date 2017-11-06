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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCartItem;
import com.liferay.commerce.service.CommerceAddressLocalServiceUtil;
import com.liferay.commerce.service.CommerceCartItemLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@ProviderType
public class CommerceCartImpl extends CommerceCartBaseImpl {

	public CommerceCartImpl() {
	}

	@Override
	public CommerceAddress getBillingAddress() throws PortalException {
		long billingAddressId = getBillingAddressId();

		if (billingAddressId > 0) {
			return CommerceAddressLocalServiceUtil.getCommerceAddress(
				billingAddressId);
		}

		return null;
	}

	@Override
	public List<CommerceCartItem> getCommerceCartItems() {
		return CommerceCartItemLocalServiceUtil.getCommerceCartItems(
			getCommerceCartId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public CommerceAddress getShippingAddress() throws PortalException {
		long shippingAddressId = getShippingAddressId();

		if (shippingAddressId > 0) {
			return CommerceAddressLocalServiceUtil.getCommerceAddress(
				shippingAddressId);
		}

		return null;
	}

	@Override
	public boolean isGuestCart() throws PortalException {
		User user = UserLocalServiceUtil.getUser(getUserId());

		return user.isDefaultUser();
	}

}