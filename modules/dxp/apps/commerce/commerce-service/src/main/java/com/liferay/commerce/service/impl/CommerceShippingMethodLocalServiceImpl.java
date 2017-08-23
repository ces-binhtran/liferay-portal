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

import com.liferay.commerce.exception.CommerceShippingMethodEngineKeyException;
import com.liferay.commerce.exception.CommerceShippingMethodNameException;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.base.CommerceShippingMethodLocalServiceBaseImpl;
import com.liferay.commerce.util.CommerceShippingEngineRegistry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceShippingMethodLocalServiceImpl
	extends CommerceShippingMethodLocalServiceBaseImpl {

	@Override
	public CommerceShippingMethod addCommerceShippingMethod(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String engineKey, Map<String, String> engineParameterMap,
			double priority, boolean active, ServiceContext serviceContext)
		throws PortalException {

		// Commerce shipping method

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		validate(nameMap, engineKey);

		long commerceShippingMethodId = counterLocalService.increment();

		CommerceShippingMethod commerceShippingMethod =
			commerceShippingMethodPersistence.create(commerceShippingMethodId);

		commerceShippingMethod.setGroupId(groupId);
		commerceShippingMethod.setCompanyId(user.getCompanyId());
		commerceShippingMethod.setUserId(user.getUserId());
		commerceShippingMethod.setUserName(user.getFullName());
		commerceShippingMethod.setNameMap(nameMap);
		commerceShippingMethod.setDescriptionMap(descriptionMap);
		commerceShippingMethod.setEngineKey(engineKey);
		commerceShippingMethod.setPriority(priority);
		commerceShippingMethod.setActive(active);

		commerceShippingMethodPersistence.update(commerceShippingMethod);

		// Commerce shipping engine

		updateCommerceShippingEngineConfiguration(
			engineKey, engineParameterMap, serviceContext);

		return commerceShippingMethod;
	}

	@Override
	public void deleteCommerceShippingMethods(long groupId) {
		commerceShippingMethodPersistence.removeByGroupId(groupId);
	}

	@Override
	public List<CommerceShippingMethod> getCommerceShippingMethods(
		long groupId) {

		return commerceShippingMethodPersistence.findByGroupId(groupId);
	}

	@Override
	public List<CommerceShippingMethod> getCommerceShippingMethods(
		long groupId, boolean active) {

		return commerceShippingMethodPersistence.findByG_A(groupId, active);
	}

	@Override
	public CommerceShippingMethod updateCommerceShippingMethod(
			long commerceShippingMethodId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap,
			Map<String, String> engineParameterMap, double priority,
			boolean active, ServiceContext serviceContext)
		throws PortalException {

		// Commerce shipping method

		CommerceShippingMethod commerceShippingMethod =
			commerceShippingMethodPersistence.findByPrimaryKey(
				commerceShippingMethodId);

		commerceShippingMethod.setNameMap(nameMap);
		commerceShippingMethod.setDescriptionMap(descriptionMap);
		commerceShippingMethod.setPriority(priority);
		commerceShippingMethod.setActive(active);

		commerceShippingMethodPersistence.update(commerceShippingMethod);

		// Commerce shipping engine

		updateCommerceShippingEngineConfiguration(
			commerceShippingMethod.getEngineKey(), engineParameterMap,
			serviceContext);

		return commerceShippingMethod;
	}

	protected void updateCommerceShippingEngineConfiguration(
			String key, Map<String, String> parameterMap,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceShippingEngine commerceShippingEngine =
			_commerceShippingEngineRegistry.getCommerceShippingEngine(key);

		try {
			commerceShippingEngine.updateConfiguration(
				parameterMap, serviceContext);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected void validate(Map<Locale, String> nameMap, String engineKey)
		throws PortalException {

		Locale locale = LocaleUtil.getSiteDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new CommerceShippingMethodNameException();
		}

		if (Validator.isNull(engineKey)) {
			throw new CommerceShippingMethodEngineKeyException();
		}
	}

	@ServiceReference(type = CommerceShippingEngineRegistry.class)
	private CommerceShippingEngineRegistry _commerceShippingEngineRegistry;

}