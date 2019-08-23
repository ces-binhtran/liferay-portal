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

package com.liferay.info.provider;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Eudaldo Alonso
 * @deprecated As of Mueller (7.2.x), moved to {@link
 *             com.liferay.info.list.provider.InfoListProviderTracker}
 */
@Deprecated
@ProviderType
public interface InfoListProviderTracker {

	public InfoListProvider getInfoListProvider(String className);

	public List<InfoListProvider> getInfoListProviders();

	public List<InfoListProvider> getInfoListProviders(Class<?> itemClass);

}