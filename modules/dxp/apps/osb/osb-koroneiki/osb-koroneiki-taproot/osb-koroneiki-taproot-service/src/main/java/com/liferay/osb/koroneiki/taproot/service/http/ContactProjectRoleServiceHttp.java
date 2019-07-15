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

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.ContactProjectRoleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the HTTP utility for the
 * <code>ContactProjectRoleServiceUtil</code> service
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
 * @see ContactProjectRoleServiceSoap
 * @generated
 */
@ProviderType
public class ContactProjectRoleServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.ContactProjectRole
			addContactProjectRole(
				HttpPrincipal httpPrincipal, long contactId, long projectId,
				long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactProjectRoleServiceUtil.class, "addContactProjectRole",
				_addContactProjectRoleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, projectId, contactRoleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.ContactProjectRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactProjectRole
			addContactProjectRole(
				HttpPrincipal httpPrincipal, String contactKey,
				String projectKey, String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactProjectRoleServiceUtil.class, "addContactProjectRole",
				_addContactProjectRoleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactKey, projectKey, contactRoleKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.ContactProjectRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactProjectRole
			deleteContactProjectRole(
				HttpPrincipal httpPrincipal, long contactId, long projectId,
				long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactProjectRoleServiceUtil.class, "deleteContactProjectRole",
				_deleteContactProjectRoleParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, projectId, contactRoleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.ContactProjectRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactProjectRole
			deleteContactProjectRole(
				HttpPrincipal httpPrincipal, String contactKey,
				String projectKey, String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactProjectRoleServiceUtil.class, "deleteContactProjectRole",
				_deleteContactProjectRoleParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactKey, projectKey, contactRoleKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.osb.koroneiki.taproot.model.ContactProjectRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteContactProjectRoles(
			HttpPrincipal httpPrincipal, long contactId, long projectId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactProjectRoleServiceUtil.class,
				"deleteContactProjectRoles",
				_deleteContactProjectRolesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, projectId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteContactProjectRoles(
			HttpPrincipal httpPrincipal, String contactKey, String projectKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactProjectRoleServiceUtil.class,
				"deleteContactProjectRoles",
				_deleteContactProjectRolesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactKey, projectKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ContactProjectRoleServiceHttp.class);

	private static final Class<?>[] _addContactProjectRoleParameterTypes0 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _addContactProjectRoleParameterTypes1 =
		new Class[] {String.class, String.class, String.class};
	private static final Class<?>[] _deleteContactProjectRoleParameterTypes2 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _deleteContactProjectRoleParameterTypes3 =
		new Class[] {String.class, String.class, String.class};
	private static final Class<?>[] _deleteContactProjectRolesParameterTypes4 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _deleteContactProjectRolesParameterTypes5 =
		new Class[] {String.class, String.class};

}