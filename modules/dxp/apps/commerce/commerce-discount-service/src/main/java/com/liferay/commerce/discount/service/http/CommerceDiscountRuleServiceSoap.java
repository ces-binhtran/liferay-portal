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

package com.liferay.commerce.discount.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.service.CommerceDiscountRuleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CommerceDiscountRuleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.discount.model.CommerceDiscountRuleSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.discount.model.CommerceDiscountRule}, that is translated to a
 * {@link com.liferay.commerce.discount.model.CommerceDiscountRuleSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceDiscountRuleServiceHttp
 * @see com.liferay.commerce.discount.model.CommerceDiscountRuleSoap
 * @see CommerceDiscountRuleServiceUtil
 * @generated
 */
@ProviderType
public class CommerceDiscountRuleServiceSoap {
	public static com.liferay.commerce.discount.model.CommerceDiscountRuleSoap addCommerceDiscountRule(
		long commerceDiscountId, java.lang.String type,
		java.lang.String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.discount.model.CommerceDiscountRule returnValue =
				CommerceDiscountRuleServiceUtil.addCommerceDiscountRule(commerceDiscountId,
					type, typeSettings, serviceContext);

			return com.liferay.commerce.discount.model.CommerceDiscountRuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCommerceDiscountRule(long commerceDiscountRuleId)
		throws RemoteException {
		try {
			CommerceDiscountRuleServiceUtil.deleteCommerceDiscountRule(commerceDiscountRuleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountRuleSoap updateCommerceDiscountRule(
		long commerceDiscountRuleId, java.lang.String type,
		java.lang.String typeSettings) throws RemoteException {
		try {
			com.liferay.commerce.discount.model.CommerceDiscountRule returnValue =
				CommerceDiscountRuleServiceUtil.updateCommerceDiscountRule(commerceDiscountRuleId,
					type, typeSettings);

			return com.liferay.commerce.discount.model.CommerceDiscountRuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceDiscountRuleServiceSoap.class);
}