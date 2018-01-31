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

package com.liferay.commerce.internal.util;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.model.CommerceCart;
import com.liferay.commerce.model.CommerceCartConstants;
import com.liferay.commerce.service.CommerceCartItemService;
import com.liferay.commerce.service.CommerceCartService;
import com.liferay.commerce.util.CommerceCartHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true)
public class CommerceCartHelperImpl implements CommerceCartHelper {

	@Override
	public PortletURL getCommerceCartPortletURL(
			HttpServletRequest httpServletRequest, int type)
		throws PortalException {

		PortletURL portletURL = null;

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		String portletId = "";

		if (type == CommerceCartConstants.TYPE_CART) {
			portletId = CommercePortletKeys.COMMERCE_CART_CONTENT;
		}
		else if (type == CommerceCartConstants.TYPE_WISH_LIST) {
			portletId = CommercePortletKeys.COMMERCE_WISH_LIST_CONTENT;
		}

		long plid = _portal.getPlidFromPortletId(groupId, portletId);

		if (plid > 0) {
			portletURL = _portletURLFactory.create(
				httpServletRequest, portletId, plid,
				PortletRequest.RENDER_PHASE);
		}
		else {
			portletURL = _portletURLFactory.create(
				httpServletRequest, portletId, PortletRequest.RENDER_PHASE);
		}

		return portletURL;
	}

	@Override
	public PortletURL getCommerceCheckoutPortletURL(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = null;

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		long plid = _portal.getPlidFromPortletId(
			groupId, CommercePortletKeys.COMMERCE_CHECKOUT);

		if (plid > 0) {
			portletURL = _portletURLFactory.create(
				httpServletRequest, CommercePortletKeys.COMMERCE_CHECKOUT, plid,
				PortletRequest.RENDER_PHASE);
		}
		else {
			portletURL = _portletURLFactory.create(
				httpServletRequest, CommercePortletKeys.COMMERCE_CHECKOUT,
				PortletRequest.RENDER_PHASE);
		}

		return portletURL;
	}

	@Override
	public CommerceCart getCurrentCommerceCart(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		int type = ParamUtil.getInteger(
			httpServletRequest, "type", CommerceCartConstants.TYPE_CART);

		return getCurrentCommerceCart(
			httpServletRequest, httpServletResponse, type);
	}

	@Override
	public CommerceCart getCurrentCommerceCart(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, int type)
		throws PortalException {

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		String uuid = _getCurrentCommerceCartUuid(
			httpServletRequest, httpServletResponse, type, groupId);

		if (Validator.isNull(uuid)) {
			return null;
		}

		CommerceCart commerceCart = _commerceCartService.fetchCommerceCart(
			uuid, groupId);

		commerceCart = _checkGuestCart(
			httpServletRequest, httpServletResponse, commerceCart);

		return commerceCart;
	}

	@Override
	public int getCurrentCommerceCartItemsCount(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, int type)
		throws PortalException {

		CommerceCart commerceCart = getCurrentCommerceCart(
			httpServletRequest, httpServletResponse, type);

		if (commerceCart == null) {
			return 0;
		}

		return _commerceCartItemService.getCommerceCartItemsCount(
			commerceCart.getCommerceCartId());
	}

	private CommerceCart _checkGuestCart(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, CommerceCart commerceCart)
		throws PortalException {

		if (commerceCart == null) {
			return null;
		}

		User user = _portal.getUser(httpServletRequest);

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		if ((user == null) || user.isDefaultUser()) {
			return commerceCart;
		}

		String domain = CookieKeys.getDomain(httpServletRequest);

		String commerceCartUuidWebKey = _getCommerceCartUuidWebKey(
			commerceCart.getType(), groupId);

		if (commerceCart.isGuestCart()) {
			CookieKeys.deleteCookies(
				httpServletRequest, httpServletResponse, domain,
				commerceCartUuidWebKey);

			return _commerceCartService.updateUser(
				commerceCart.getCommerceCartId(), user.getUserId());
		}

		String commerceCartUuid = CookieKeys.getCookie(
			httpServletRequest, commerceCartUuidWebKey, false);

		if (Validator.isNull(commerceCartUuid)) {
			return commerceCart;
		}

		CommerceCart cookieCommerceCart =
			_commerceCartService.fetchCommerceCart(commerceCartUuid, groupId);

		if ((cookieCommerceCart == null) || !cookieCommerceCart.isGuestCart()) {
			return commerceCart;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			httpServletRequest);

		_commerceCartService.mergeGuestCommerceCart(
			cookieCommerceCart.getCommerceCartId(),
			commerceCart.getCommerceCartId(), serviceContext);

		CookieKeys.deleteCookies(
			httpServletRequest, httpServletResponse, domain,
			commerceCartUuidWebKey);

		return commerceCart;
	}

	private String _getCommerceCartUuidWebKey(int type, long groupId) {
		StringBundler sb = new StringBundler(5);

		sb.append(CommerceWebKeys.COMMERCE_CART_UUID);
		sb.append(CharPool.UNDERLINE);
		sb.append(type);
		sb.append(CharPool.UNDERLINE);
		sb.append(groupId);

		return sb.toString();
	}

	private String _getCurrentCommerceCartUuid(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, int type, long groupId)
		throws PortalException {

		String commerceCartUuid = CommerceCartThreadLocal.getCommerceCartUuid(
			type);

		if (Validator.isNotNull(commerceCartUuid)) {
			return commerceCartUuid;
		}

		User user = _portal.getUser(httpServletRequest);

		if ((user != null) && !user.isDefaultUser()) {
			CommerceCart commerceCart =
				_commerceCartService.fetchDefaultCommerceCart(
					groupId, user.getUserId(), true, type);

			if (type == CommerceCartConstants.TYPE_WISH_LIST) {
				RenderRequest renderRequest =
					(RenderRequest)httpServletRequest.getAttribute(
						JavaConstants.JAVAX_PORTLET_REQUEST);

				if (renderRequest != null) {
					CommerceCart selectedCommerceCart = null;

					PortletSession portletSession =
						renderRequest.getPortletSession();

					long commerceCartId = (Long)portletSession.getAttribute(
						CommerceWebKeys.WISH_LIST_COMMERCE_CART_ID);

					if (commerceCartId > 0) {
						selectedCommerceCart =
							_commerceCartService.fetchCommerceCart(
								commerceCartId);
					}

					if (selectedCommerceCart != null) {
						commerceCart = selectedCommerceCart;
					}
				}
			}

			if (commerceCart != null) {
				CommerceCartThreadLocal.setCommerceCartUuid(
					type, commerceCart.getUuid());

				return commerceCart.getUuid();
			}
		}

		String commerceCartUuidWebKey = _getCommerceCartUuidWebKey(
			type, groupId);

		commerceCartUuid = CookieKeys.getCookie(
			httpServletRequest, commerceCartUuidWebKey, false);

		if (Validator.isNotNull(commerceCartUuid)) {
			CommerceCartThreadLocal.setCommerceCartUuid(type, commerceCartUuid);

			return commerceCartUuid;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceCart.class.getName(), httpServletRequest);

		serviceContext.setScopeGroupId(groupId);

		CommerceCart commerceCart = _commerceCartService.addCommerceCart(
			CommerceCartConstants.DEFAULT_TITLE, true, type, serviceContext);

		if (!serviceContext.isSignedIn()) {
			_setGuestCommerceCart(
				httpServletRequest, httpServletResponse, commerceCart);
		}

		return commerceCart.getUuid();
	}

	private void _setGuestCommerceCart(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, CommerceCart commerceCart)
		throws PortalException {

		User user = _portal.getUser(httpServletRequest);

		if ((user != null) && !user.isDefaultUser()) {
			return;
		}

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		String commerceCartUuidWebKey = _getCommerceCartUuidWebKey(
			commerceCart.getType(), groupId);

		Cookie cookie = new Cookie(
			commerceCartUuidWebKey, commerceCart.getUuid());

		String domain = CookieKeys.getDomain(httpServletRequest);

		if (Validator.isNotNull(domain)) {
			cookie.setDomain(domain);
		}

		cookie.setMaxAge(CookieKeys.MAX_AGE);
		cookie.setPath(StringPool.SLASH);

		CookieKeys.addCookie(httpServletRequest, httpServletResponse, cookie);
	}

	@Reference
	private CommerceCartItemService _commerceCartItemService;

	@Reference
	private CommerceCartService _commerceCartService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletURLFactory _portletURLFactory;

}