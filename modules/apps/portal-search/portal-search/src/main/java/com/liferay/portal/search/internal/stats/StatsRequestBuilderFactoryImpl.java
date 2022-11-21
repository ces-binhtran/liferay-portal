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

package com.liferay.portal.search.internal.stats;

import com.liferay.portal.search.stats.StatsRequestBuilder;
import com.liferay.portal.search.stats.StatsRequestBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 */
@Component(service = StatsRequestBuilderFactory.class)
public class StatsRequestBuilderFactoryImpl
	implements StatsRequestBuilderFactory {

	@Override
	public StatsRequestBuilder getStatsRequestBuilder() {
		return new StatsRequestBuilderImpl();
	}

}