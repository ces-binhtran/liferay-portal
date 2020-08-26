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

import com.liferay.commerce.discount.service.CommerceDiscountAccountRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceDiscountAccountRelServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.discount.model.CommerceDiscountAccountRel</code>, that is translated to a
 * <code>com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
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
 * @see CommerceDiscountAccountRelServiceHttp
 * @generated
 */
public class CommerceDiscountAccountRelServiceSoap {

	public static
		com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap
				addCommerceDiscountAccountRel(
					long commerceDiscountId, long commerceAccountId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws RemoteException {

		try {
			com.liferay.commerce.discount.model.CommerceDiscountAccountRel
				returnValue =
					CommerceDiscountAccountRelServiceUtil.
						addCommerceDiscountAccountRel(
							commerceDiscountId, commerceAccountId,
							serviceContext);

			return com.liferay.commerce.discount.model.
				CommerceDiscountAccountRelSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteCommerceDiscountAccountRel(
			long commerceDiscountAccountRelId)
		throws RemoteException {

		try {
			CommerceDiscountAccountRelServiceUtil.
				deleteCommerceDiscountAccountRel(commerceDiscountAccountRelId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteCommerceDiscountAccountRelsByCommerceDiscountId(
			long commerceDiscountId)
		throws RemoteException {

		try {
			CommerceDiscountAccountRelServiceUtil.
				deleteCommerceDiscountAccountRelsByCommerceDiscountId(
					commerceDiscountId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap
				fetchCommerceDiscountAccountRel(
					long commerceDiscountId, long commerceAccountId)
			throws RemoteException {

		try {
			com.liferay.commerce.discount.model.CommerceDiscountAccountRel
				returnValue =
					CommerceDiscountAccountRelServiceUtil.
						fetchCommerceDiscountAccountRel(
							commerceDiscountId, commerceAccountId);

			return com.liferay.commerce.discount.model.
				CommerceDiscountAccountRelSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap
				getCommerceDiscountAccountRel(long commerceDiscountAccountRelId)
			throws RemoteException {

		try {
			com.liferay.commerce.discount.model.CommerceDiscountAccountRel
				returnValue =
					CommerceDiscountAccountRelServiceUtil.
						getCommerceDiscountAccountRel(
							commerceDiscountAccountRelId);

			return com.liferay.commerce.discount.model.
				CommerceDiscountAccountRelSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap[]
				getCommerceDiscountAccountRels(
					long commerceDiscountId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.discount.model.
							CommerceDiscountAccountRel> orderByComparator)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.discount.model.CommerceDiscountAccountRel>
					returnValue =
						CommerceDiscountAccountRelServiceUtil.
							getCommerceDiscountAccountRels(
								commerceDiscountId, start, end,
								orderByComparator);

			return com.liferay.commerce.discount.model.
				CommerceDiscountAccountRelSoap.toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.discount.model.CommerceDiscountAccountRelSoap[]
				getCommerceDiscountAccountRels(
					long commerceDiscountId, String name, int start, int end)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.discount.model.CommerceDiscountAccountRel>
					returnValue =
						CommerceDiscountAccountRelServiceUtil.
							getCommerceDiscountAccountRels(
								commerceDiscountId, name, start, end);

			return com.liferay.commerce.discount.model.
				CommerceDiscountAccountRelSoap.toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCommerceDiscountAccountRelsCount(
			long commerceDiscountId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceDiscountAccountRelServiceUtil.
					getCommerceDiscountAccountRelsCount(commerceDiscountId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCommerceDiscountAccountRelsCount(
			long commerceDiscountId, String name)
		throws RemoteException {

		try {
			int returnValue =
				CommerceDiscountAccountRelServiceUtil.
					getCommerceDiscountAccountRelsCount(
						commerceDiscountId, name);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceDiscountAccountRelServiceSoap.class);

}