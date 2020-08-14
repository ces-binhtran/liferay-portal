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

package com.liferay.dynamic.data.mapping.form.field.type.internal.radio;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueAccessor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.Locale;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=radio",
	service = {
		DDMFormFieldValueAccessor.class, RadioDDMFormFieldValueAccessor.class
	}
)
public class RadioDDMFormFieldValueAccessor
	implements DDMFormFieldValueAccessor<String> {

	@Override
	public IntFunction<String[]> getArrayGeneratorIntFunction() {
		return String[]::new;
	}

	@Override
	public String getValue(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		String optionValue = getOptionValue(ddmFormFieldValue, locale);

		Matcher matcher = _autoGeneratedIDPattern.matcher(optionValue);

		if (!matcher.matches() ||
			(ddmFormFieldValue.getDDMFormValues() == null)) {

			return optionValue;
		}

		return getOptionLabel(ddmFormFieldValue, locale);
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		DDMFormFieldValue ddmFormFieldValue) {

		DDMFormField ddmFormField = ddmFormFieldValue.getDDMFormField();

		return ddmFormField.getDDMFormFieldOptions();
	}

	protected String getOptionLabel(
		DDMFormFieldValue ddmFormFieldValue, Locale locale) {

		DDMFormFieldOptions ddmFormFieldOptions = getDDMFormFieldOptions(
			ddmFormFieldValue);

		String optionValue = getOptionValue(ddmFormFieldValue, locale);

		LocalizedValue optionLabel = ddmFormFieldOptions.getOptionLabels(
			optionValue);

		if (optionLabel == null) {
			return optionValue;
		}

		return HtmlUtil.escape(optionLabel.getString(locale));
	}

	protected String getOptionValue(
		DDMFormFieldValue ddmFormFieldValue, Locale locale) {

		Value value = ddmFormFieldValue.getValue();

		return value.getString(locale);
	}

	private static final Pattern _autoGeneratedIDPattern = Pattern.compile(
		"^[\\D]*[\\d]{8}$");

}