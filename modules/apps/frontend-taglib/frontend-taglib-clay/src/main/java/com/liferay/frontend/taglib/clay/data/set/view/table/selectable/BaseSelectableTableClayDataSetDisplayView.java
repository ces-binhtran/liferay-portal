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

package com.liferay.frontend.taglib.clay.data.set.view.table.selectable;

import com.liferay.frontend.taglib.clay.data.set.ClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.constants.ClayDataSetConstants;
import com.liferay.petra.string.StringPool;

import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 */
public abstract class BaseSelectableTableClayDataSetDisplayView
	implements ClayDataSetDisplayView {

	@Override
	public String getContentRenderer() {
		return ClayDataSetConstants.SELECTABLE_TABLE;
	}

	public abstract String getFirstColumnLabel(Locale locale);

	public abstract String getFirstColumnName();

	@Override
	public String getLabel() {
		return ClayDataSetConstants.SELECTABLE_TABLE;
	}

	public String getName() {
		return ClayDataSetConstants.SELECTABLE_TABLE;
	}

	@Override
	public String getThumbnail() {
		return StringPool.BLANK;
	}

}