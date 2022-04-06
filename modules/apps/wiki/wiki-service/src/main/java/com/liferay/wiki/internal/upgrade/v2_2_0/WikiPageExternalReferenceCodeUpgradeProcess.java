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

package com.liferay.wiki.internal.upgrade.v2_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Luis Miguel Barcos
 */
public class WikiPageExternalReferenceCodeUpgradeProcess
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("WikiPage", "externalReferenceCode")) {
			alterTableAddColumn(
				"WikiPage", "externalReferenceCode", "VARCHAR(75)");

			runSQL(
				"update WikiPage set externalReferenceCode = " +
					"CAST_TEXT(resourcePrimKey) where externalReferenceCode " +
						"is null or externalReferenceCode = ''");
		}
	}

}