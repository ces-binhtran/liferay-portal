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

package com.liferay.commerce.service.http;

import com.liferay.commerce.service.CommerceSubscriptionEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceSubscriptionEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.model.CommerceSubscriptionEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.model.CommerceSubscriptionEntry</code>, that is translated to a
 * <code>com.liferay.commerce.model.CommerceSubscriptionEntrySoap</code>. Methods that SOAP
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
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryServiceHttp
 * @generated
 */
public class CommerceSubscriptionEntryServiceSoap {

	public static void deleteCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId)
		throws RemoteException {

		try {
			CommerceSubscriptionEntryServiceUtil.
				deleteCommerceSubscriptionEntry(commerceSubscriptionEntryId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceSubscriptionEntrySoap
			fetchCommerceSubscriptionEntry(long commerceSubscriptionEntryId)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceSubscriptionEntry returnValue =
				CommerceSubscriptionEntryServiceUtil.
					fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);

			return com.liferay.commerce.model.CommerceSubscriptionEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static com.liferay.commerce.model.CommerceSubscriptionEntrySoap[]
			getCommerceSubscriptionEntries(
				long companyId, long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceSubscriptionEntry>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
				returnValue =
					CommerceSubscriptionEntryServiceUtil.
						getCommerceSubscriptionEntries(
							companyId, userId, start, end, orderByComparator);

			return com.liferay.commerce.model.CommerceSubscriptionEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.commerce.model.CommerceSubscriptionEntrySoap[]
			getCommerceSubscriptionEntries(
				long companyId, long groupId, long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceSubscriptionEntry>
						orderByComparator)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
				returnValue =
					CommerceSubscriptionEntryServiceUtil.
						getCommerceSubscriptionEntries(
							companyId, groupId, userId, start, end,
							orderByComparator);

			return com.liferay.commerce.model.CommerceSubscriptionEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static int getCommerceSubscriptionEntriesCount(
			long companyId, long userId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceSubscriptionEntryServiceUtil.
					getCommerceSubscriptionEntriesCount(companyId, userId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCommerceSubscriptionEntriesCount(
			long companyId, long groupId, long userId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceSubscriptionEntryServiceUtil.
					getCommerceSubscriptionEntriesCount(
						companyId, groupId, userId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static com.liferay.commerce.model.CommerceSubscriptionEntrySoap
			updateSubscriptionStatus(
				long commerceSubscriptionEntryId, int subscriptionStatus)
		throws RemoteException {

		try {
			com.liferay.commerce.model.CommerceSubscriptionEntry returnValue =
				CommerceSubscriptionEntryServiceUtil.updateSubscriptionStatus(
					commerceSubscriptionEntryId, subscriptionStatus);

			return com.liferay.commerce.model.CommerceSubscriptionEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceSubscriptionEntryServiceSoap.class);

}