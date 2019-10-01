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

package com.liferay.portal.store.file.system;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.convert.documentlibrary.FileSystemStoreRootDirException;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Shuyang Zhou
 */
@Component(
	configurationPid = "com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration",
	service = {}
)
public class FileSystemStoreRegister {

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		FileSystemStoreConfiguration fileSystemStoreConfiguration =
			ConfigurableUtil.createConfigurable(
				FileSystemStoreConfiguration.class, properties);

		if (Validator.isBlank(fileSystemStoreConfiguration.rootDir())) {
			throw new IllegalArgumentException(
				"File system root directory is not set",
				new FileSystemStoreRootDirException());
		}

		_serviceRegistration = bundleContext.registerService(
			Store.class, new FileSystemStore(fileSystemStoreConfiguration),
			MapUtil.singletonDictionary(
				"store.type", FileSystemStore.class.getName()));
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}

	private ServiceRegistration<Store> _serviceRegistration;

}