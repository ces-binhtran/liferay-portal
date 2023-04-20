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

package com.liferay.frontend.taglib.clay.internal;

import com.liferay.frontend.taglib.clay.servlet.taglib.contributor.ClayTagContextContributor;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Rodolfo Roza Miranda
 */
public class ClayTagContextContributorsProvider {

	public static List<ClayTagContextContributor> getClayTagContextContributors(
		String clayTagContextContributorKey) {

		return _serviceTrackerMap.getService(clayTagContextContributorKey);
	}

	private static final ServiceTrackerMap
		<String, List<ClayTagContextContributor>> _serviceTrackerMap;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ClayTagContextContributorsProvider.class);

		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundle.getBundleContext(), ClayTagContextContributor.class,
			"(clay.tag.context.contributor.key=*)",
			new PropertyServiceReferenceMapper<>(
				"clay.tag.context.contributor.key"),
			new PropertyServiceReferenceComparator<>("service.ranking"));
	}

}