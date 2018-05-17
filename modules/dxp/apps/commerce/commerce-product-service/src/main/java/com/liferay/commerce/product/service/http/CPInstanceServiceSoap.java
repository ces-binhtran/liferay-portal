/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.product.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.service.CPInstanceServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CPInstanceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.commerce.product.model.CPInstanceSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.commerce.product.model.CPInstance}, that is translated to a
 * {@link com.liferay.commerce.product.model.CPInstanceSoap}. Methods that SOAP cannot
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
 * @see CPInstanceServiceHttp
 * @see com.liferay.commerce.product.model.CPInstanceSoap
 * @see CPInstanceServiceUtil
 * @generated
 */
@ProviderType
public class CPInstanceServiceSoap {
	public static com.liferay.commerce.product.model.CPInstanceSoap addCPInstance(
		long cpDefinitionId, String sku, String gtin,
		String manufacturerPartNumber, boolean purchasable, String ddmContent,
		boolean published, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.addCPInstance(cpDefinitionId,
					sku, gtin, manufacturerPartNumber, purchasable, ddmContent,
					published, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, neverExpire,
					serviceContext);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void buildCPInstances(long cpDefinitionId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			CPInstanceServiceUtil.buildCPInstances(cpDefinitionId,
				serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCPInstance(long cpInstanceId)
		throws RemoteException {
		try {
			CPInstanceServiceUtil.deleteCPInstance(cpInstanceId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap fetchCPInstance(
		long cpInstanceId) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.fetchCPInstance(cpInstanceId);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap[] getCPDefinitionInstances(
		long cpDefinitionId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPInstance> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.product.model.CPInstance> returnValue =
				CPInstanceServiceUtil.getCPDefinitionInstances(cpDefinitionId,
					status, start, end, orderByComparator);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCPDefinitionInstancesCount(long cpDefinitionId,
		int status) throws RemoteException {
		try {
			int returnValue = CPInstanceServiceUtil.getCPDefinitionInstancesCount(cpDefinitionId,
					status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap getCPInstance(
		long cpInstanceId) throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.getCPInstance(cpInstanceId);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap[] getCPInstances(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPInstance> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.commerce.product.model.CPInstance> returnValue =
				CPInstanceServiceUtil.getCPInstances(groupId, status, start,
					end, orderByComparator);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getCPInstancesCount(long groupId, int status)
		throws RemoteException {
		try {
			int returnValue = CPInstanceServiceUtil.getCPInstancesCount(groupId,
					status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap updateCPInstance(
		long cpInstanceId, String sku, String gtin,
		String manufacturerPartNumber, boolean purchasable, boolean published,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.updateCPInstance(cpInstanceId,
					sku, gtin, manufacturerPartNumber, purchasable, published,
					displayDateMonth, displayDateDay, displayDateYear,
					displayDateHour, displayDateMinute, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap updatePricingInfo(
		long cpInstanceId, java.math.BigDecimal price,
		java.math.BigDecimal promoPrice, java.math.BigDecimal cost,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.updatePricingInfo(cpInstanceId,
					price, promoPrice, cost, serviceContext);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap updateShippingInfo(
		long cpInstanceId, double width, double height, double depth,
		double weight,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.updateShippingInfo(cpInstanceId,
					width, height, depth, weight, serviceContext);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.commerce.product.model.CPInstanceSoap upsertCPInstance(
		long cpDefinitionId, String sku, String gtin,
		String manufacturerPartNumber, boolean purchasable, String ddmContent,
		double width, double height, double depth, double weight,
		java.math.BigDecimal price, java.math.BigDecimal promoPrice,
		java.math.BigDecimal cost, boolean published,
		String externalReferenceCode, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.commerce.product.model.CPInstance returnValue = CPInstanceServiceUtil.upsertCPInstance(cpDefinitionId,
					sku, gtin, manufacturerPartNumber, purchasable, ddmContent,
					width, height, depth, weight, price, promoPrice, cost,
					published, externalReferenceCode, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, serviceContext);

			return com.liferay.commerce.product.model.CPInstanceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CPInstanceServiceSoap.class);
}