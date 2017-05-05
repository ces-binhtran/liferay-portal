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

package com.liferay.commerce.product.options.web.internal.servlet.taglib.ui;

import com.liferay.commerce.product.model.CPOption;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {"form.navigator.entry.order:Integer=10"},
	service = FormNavigatorEntry.class
)
public class CPOptionDetailsCustomFieldsFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CPOption>
	implements FormNavigatorEntry<CPOption> {

	@Override
	public String getCategoryKey() {
		return CPOptionFormNavigatorConstants.
			CATEGORY_KEY_COMMERCE_PRODUCT_OPTION_DETAILS;
	}

	@Override
	public String getFormNavigatorId() {
		return CPOptionFormNavigatorConstants.
			FORM_NAVIGATOR_ID_COMMERCE_PRODUCT_OPTION;
	}

	@Override
	public String getKey() {
		return "custom-fields";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "custom-fields");
	}

	@Override
	public boolean isVisible(User user, CPOption formModelBean) {
		if (formModelBean == null) {
			return false;
		}

		ExpandoBridge expandoBridge = formModelBean.getExpandoBridge();

		if (expandoBridge.getAttributes().isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	protected String getJspPath() {
		return "/option/custom_fields.jsp";
	}

}