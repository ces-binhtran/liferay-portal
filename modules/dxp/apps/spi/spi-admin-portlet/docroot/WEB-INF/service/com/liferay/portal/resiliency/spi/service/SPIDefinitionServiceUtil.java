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

package com.liferay.portal.resiliency.spi.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for SPIDefinition. This utility wraps
 * {@link com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Michael C. Han
 * @see SPIDefinitionService
 * @see com.liferay.portal.resiliency.spi.service.base.SPIDefinitionServiceBaseImpl
 * @see com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionServiceImpl
 * @generated
 */
@ProviderType
public class SPIDefinitionServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.util.Tuple getPortletIdsAndServletContextNames()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPortletIdsAndServletContextNames();
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition addSPIDefinition(
		java.lang.String name, java.lang.String connectorAddress,
		int connectorPort, java.lang.String description,
		java.lang.String jvmArguments, java.lang.String portletIds,
		java.lang.String servletContextNames, java.lang.String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSPIDefinition(name, connectorAddress, connectorPort,
			description, jvmArguments, portletIds, servletContextNames,
			typeSettings, serviceContext);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition deleteSPIDefinition(
		long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSPIDefinition(spiDefinitionId);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition getSPIDefinition(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSPIDefinition(companyId, name);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition getSPIDefinition(
		long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSPIDefinition(spiDefinitionId);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition updateSPIDefinition(
		long spiDefinitionId, java.lang.String connectorAddress,
		int connectorPort, java.lang.String description,
		java.lang.String jvmArguments, java.lang.String portletIds,
		java.lang.String servletContextNames, java.lang.String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSPIDefinition(spiDefinitionId, connectorAddress,
			connectorPort, description, jvmArguments, portletIds,
			servletContextNames, typeSettings, serviceContext);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition updateTypeSettings(
		long userId, long spiDefinitionId, java.lang.String recoveryOptions,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTypeSettings(userId, spiDefinitionId,
			recoveryOptions, serviceContext);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.resiliency.spi.model.SPIDefinition> getSPIDefinitions()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSPIDefinitions();
	}

	public static long startSPIinBackground(long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().startSPIinBackground(spiDefinitionId);
	}

	public static long stopSPIinBackground(long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().stopSPIinBackground(spiDefinitionId);
	}

	public static void startSPI(long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().startSPI(spiDefinitionId);
	}

	public static void stopSPI(long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().stopSPI(spiDefinitionId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SPIDefinitionService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SPIDefinitionService.class.getName());

			if (invokableService instanceof SPIDefinitionService) {
				_service = (SPIDefinitionService)invokableService;
			}
			else {
				_service = new SPIDefinitionServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SPIDefinitionServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SPIDefinitionService _service;
}