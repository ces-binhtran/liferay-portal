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

package com.liferay.commerce.order.web.internal.frontend.data.set.view.table;

import com.liferay.commerce.order.web.internal.constants.CommerceOrderFDSNames;
import com.liferay.frontend.data.set.view.FDSView;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "frontend.data.set.name=" + CommerceOrderFDSNames.NOTIFICATIONS,
	service = FDSView.class
)
public class CommerceNotificationFDSView implements FDSView {

	@Override
	public String getContentRenderer() {
		return "emailsList";
	}

	@Override
	public String getLabel() {
		return "emails";
	}

	@Override
	public String getName() {
		return "emailsList";
	}

	@Override
	public String getThumbnail() {
		return "emails";
	}

}