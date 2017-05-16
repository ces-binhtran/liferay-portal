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

package com.liferay.commerce.product.type.virtual.internal;

import com.liferay.commerce.product.type.CPType;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.type.display.order:Integer=15",
		"commerce.product.type.name=virtual"
	},
	service = CPType.class
)
public class VirtualCPType implements CPType {

	@Override
	public String getCPDefinitionEditUrl() {
		return null;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "virtual");
	}

	@Override
	public String getName() {
		return "virtual";
	}

}