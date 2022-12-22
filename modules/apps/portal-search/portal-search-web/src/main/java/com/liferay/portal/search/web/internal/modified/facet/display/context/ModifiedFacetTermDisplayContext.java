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

package com.liferay.portal.search.web.internal.modified.facet.display.context;

import com.liferay.portal.search.web.internal.facet.display.context.BucketDisplayContext;

/**
 * @author Lino Alves
 */
public class ModifiedFacetTermDisplayContext extends BucketDisplayContext {

	public String getRange() {
		return _range;
	}

	public String getRangeURL() {
		return _rangeURL;
	}

	public void setRange(String range) {
		_range = range;
	}

	public void setRangeURL(String rangeURL) {
		_rangeURL = rangeURL;
	}

	private String _range;
	private String _rangeURL;

}