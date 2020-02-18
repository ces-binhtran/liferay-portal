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

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>ProductConsumptionServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionServiceSoap
 * @generated
 */
public class ProductConsumptionServiceHttp {

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			addProductConsumption(
				HttpPrincipal httpPrincipal, long accountId,
				long productEntryId,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "addProductConsumption",
				_addProductConsumptionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, productEntryId, productFields);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			addProductConsumption(
				HttpPrincipal httpPrincipal, String accountKey,
				String productEntryKey,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "addProductConsumption",
				_addProductConsumptionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey, productEntryKey, productFields);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(
				HttpPrincipal httpPrincipal, long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "deleteProductConsumption",
				_deleteProductConsumptionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productConsumptionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(
				HttpPrincipal httpPrincipal, long accountId,
				long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "deleteProductConsumption",
				_deleteProductConsumptionParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, productEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(
				HttpPrincipal httpPrincipal, String productConsumptionKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "deleteProductConsumption",
				_deleteProductConsumptionParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productConsumptionKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getAccountProductConsumptions(
					HttpPrincipal httpPrincipal, long accountId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getAccountProductConsumptions",
				_getAccountProductConsumptionsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getAccountProductConsumptions(
					HttpPrincipal httpPrincipal, String accountKey, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getAccountProductConsumptions",
				_getAccountProductConsumptionsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAccountProductConsumptionsCount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getAccountProductConsumptionsCount",
				_getAccountProductConsumptionsCountParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAccountProductConsumptionsCount(
			HttpPrincipal httpPrincipal, String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getAccountProductConsumptionsCount",
				_getAccountProductConsumptionsCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getContactProductConsumptions(
					HttpPrincipal httpPrincipal, long contactId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getContactProductConsumptions",
				_getContactProductConsumptionsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getContactProductConsumptionsCount(
			HttpPrincipal httpPrincipal, long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getContactProductConsumptionsCount",
				_getContactProductConsumptionsCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumption(
				HttpPrincipal httpPrincipal, long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "getProductConsumption",
				_getProductConsumptionParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productConsumptionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumption(
				HttpPrincipal httpPrincipal, String productConsumptionKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "getProductConsumption",
				_getProductConsumptionParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productConsumptionKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.trunk.model.ProductConsumption)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getProductConsumptions(
					HttpPrincipal httpPrincipal, String domain,
					String entityName, String entityId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class, "getProductConsumptions",
				_getProductConsumptionsParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, domain, entityName, entityId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getProductConsumptionsCount(
			HttpPrincipal httpPrincipal, String domain, String entityName,
			String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductConsumptionServiceUtil.class,
				"getProductConsumptionsCount",
				_getProductConsumptionsCountParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, domain, entityName, entityId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductConsumptionServiceHttp.class);

	private static final Class<?>[] _addProductConsumptionParameterTypes0 =
		new Class[] {long.class, long.class, java.util.List.class};
	private static final Class<?>[] _addProductConsumptionParameterTypes1 =
		new Class[] {String.class, String.class, java.util.List.class};
	private static final Class<?>[] _deleteProductConsumptionParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteProductConsumptionParameterTypes3 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _deleteProductConsumptionParameterTypes4 =
		new Class[] {String.class};
	private static final Class<?>[]
		_getAccountProductConsumptionsParameterTypes5 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[]
		_getAccountProductConsumptionsParameterTypes6 = new Class[] {
			String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getAccountProductConsumptionsCountParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_getAccountProductConsumptionsCountParameterTypes8 = new Class[] {
			String.class
		};
	private static final Class<?>[]
		_getContactProductConsumptionsParameterTypes9 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[]
		_getContactProductConsumptionsCountParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getProductConsumptionParameterTypes11 =
		new Class[] {long.class};
	private static final Class<?>[] _getProductConsumptionParameterTypes12 =
		new Class[] {String.class};
	private static final Class<?>[] _getProductConsumptionsParameterTypes13 =
		new Class[] {
			String.class, String.class, String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getProductConsumptionsCountParameterTypes14 = new Class[] {
			String.class, String.class, String.class
		};

}