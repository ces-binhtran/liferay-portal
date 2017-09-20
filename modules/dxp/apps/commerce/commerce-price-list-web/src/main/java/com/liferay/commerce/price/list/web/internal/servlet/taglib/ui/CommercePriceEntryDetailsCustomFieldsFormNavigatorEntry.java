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

package com.liferay.commerce.price.list.web.internal.servlet.taglib.ui;

import com.liferay.commerce.model.CommercePriceEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "form.navigator.entry.order:Integer=10",
	service = FormNavigatorEntry.class
)
public class CommercePriceEntryDetailsCustomFieldsFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CommercePriceEntry> {

	@Override
	public String getCategoryKey() {
		return CommercePriceEntryFormNavigatorConstants.
			CATEGORY_KEY_COMMERCE_PRICE_ENTRY_DETAILS;
	}

	@Override
	public String getFormNavigatorId() {
		return CommercePriceEntryFormNavigatorConstants.
			FORM_NAVIGATOR_ID_COMMERCE_PRICE_ENTRY;
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
	public boolean isVisible(User user, CommercePriceEntry commercePriceEntry) {
		boolean hasCustomAttributesAvailable = false;

		try {
			long classPK = 0;

			if (commercePriceEntry != null) {
				classPK = commercePriceEntry.getCommercePriceEntryId();
			}

			hasCustomAttributesAvailable =
				CustomAttributesUtil.hasCustomAttributes(
					user.getCompanyId(), CommercePriceEntry.class.getName(),
					classPK, null);
		}
		catch (Exception e) {
		}

		return hasCustomAttributesAvailable;
	}

	@Override
	protected String getJspPath() {
		return "/price_entry/custom_fields.jsp";
	}

}