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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.model.CommerceCart;
import com.liferay.commerce.model.CommerceCartItem;
import com.liferay.commerce.product.util.DDMFormValuesHelper;
import com.liferay.commerce.service.base.CommerceCartLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
public class CommerceCartLocalServiceImpl
	extends CommerceCartLocalServiceBaseImpl {

	@Override
	public CommerceCart addCommerceCart(
			String name, int type, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		long commerceCartId = counterLocalService.increment();

		CommerceCart commerceCart = commerceCartPersistence.create(
			commerceCartId);

		commerceCart.setUuid(serviceContext.getUuid());
		commerceCart.setGroupId(groupId);
		commerceCart.setCompanyId(user.getCompanyId());
		commerceCart.setUserId(user.getUserId());
		commerceCart.setUserName(user.getFullName());
		commerceCart.setName(name);
		commerceCart.setType(type);
		commerceCart.setExpandoBridgeAttributes(serviceContext);

		commerceCartPersistence.update(commerceCart);

		return commerceCart;
	}

	@Override
	public CommerceCart assignGuestCartToUser(long userId, long commerceCartId)
		throws PortalException {

		CommerceCart commerceCart = commerceCartPersistence.findByPrimaryKey(
			commerceCartId);

		User user = userLocalService.getUser(userId);

		commerceCart.setUserId(user.getUserId());
		commerceCart.setUserName(user.getFullName());

		return commerceCartPersistence.update(commerceCart);
	}

	@Override
	public CommerceCart deleteCommerceCart(CommerceCart commerceCart)
		throws PortalException {

		// Commerce cart

		commerceCartPersistence.remove(commerceCart);

		// Commerce cart items

		commerceCartItemLocalService.deleteCommerceCartItems(
			commerceCart.getCommerceCartId());

		// Expando

		expandoRowLocalService.deleteRows(commerceCart.getCommerceCartId());

		return commerceCart;
	}

	@Override
	public CommerceCart deleteCommerceCart(long commerceCartId)
		throws PortalException {

		CommerceCart commerceCart = commerceCartPersistence.findByPrimaryKey(
			commerceCartId);

		return commerceCartLocalService.deleteCommerceCart(commerceCart);
	}

	@Override
	public CommerceCart fetchCommerceCart(long commerceCartId) {
		return commerceCartPersistence.fetchByPrimaryKey(commerceCartId);
	}

	@Override
	public CommerceCart fetchDefaultCommerceCart(
		long groupId, long userId, int type, String name) {

		return commerceCartPersistence.fetchByG_U_N_T_First(
			groupId, userId, name, type, null);
	}

	@Override
	public List<CommerceCart> getCommerceCarts(
		long groupId, int type, int start, int end,
		OrderByComparator<CommerceCart> orderByComparator) {

		return commerceCartPersistence.findByG_T(
			groupId, type, start, end, orderByComparator);
	}

	@Override
	public int getCommerceCartsCount(long groupId, int type) {
		return commerceCartPersistence.countByG_T(groupId, type);
	}

	@Override
	public void mergeGuestCommerceCart(
			long userCommerceCartId, long guestCommerceCartId,
			ServiceContext serviceContext)
		throws PortalException {

		List<CommerceCartItem> guestCommerceCartItems =
			commerceCartItemPersistence.findByCommerceCartId(
				guestCommerceCartId);

		for (CommerceCartItem guestCommerceCartItem : guestCommerceCartItems) {
			List<CommerceCartItem> userCommerceCartItems =
				commerceCartItemPersistence.findByC_D_I(
					userCommerceCartId,
					guestCommerceCartItem.getCPDefinitionId(),
					guestCommerceCartItem.getCPInstanceId());

			if (!userCommerceCartItems.isEmpty()) {
				boolean found = false;

				for (CommerceCartItem userCommerceCartItem :
						userCommerceCartItems) {

					if (_ddmFormValuesHelper.equals(
							guestCommerceCartItem.getJson(),
							userCommerceCartItem.getJson())) {

						found = true;

						break;
					}
				}

				if (found) {
					break;
				}
			}

			commerceCartItemLocalService.addCommerceCartItem(
				userCommerceCartId, guestCommerceCartItem.getCPDefinitionId(),
				guestCommerceCartItem.getCPInstanceId(),
				guestCommerceCartItem.getQuantity(),
				guestCommerceCartItem.getJson(), serviceContext);
		}

		commerceCartLocalService.deleteCommerceCart(guestCommerceCartId);
	}

	@Reference
	private DDMFormValuesHelper _ddmFormValuesHelper;

}