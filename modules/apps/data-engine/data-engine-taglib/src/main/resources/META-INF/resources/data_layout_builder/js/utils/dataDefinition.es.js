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

export const containsFieldSet = (dataDefinition, dataDefinitionId) => {
	let hasFieldSet = false;

	forEachDataDefinitionField(dataDefinition, (dataDefinitionField) => {
		const {customProperties, fieldType} = dataDefinitionField;

		if (
			fieldType === 'fieldset' &&
			customProperties &&
			customProperties.ddmStructureId == dataDefinitionId
		) {
			hasFieldSet = true;
		}

		return hasFieldSet;
	});

	return hasFieldSet;
};

export const forEachDataDefinitionField = (
	dataDefinition = {dataDefinitionFields: []},
	fn
) => {
	const {dataDefinitionFields = []} = dataDefinition;

	for (let i = 0; i < dataDefinitionFields.length; i++) {
		const field = dataDefinitionFields[i];

		if (fn(field)) {
			return true;
		}

		if (
			forEachDataDefinitionField(
				{
					dataDefinitionFields:
						field.nestedDataDefinitionFields || [],
				},
				fn
			)
		) {
			return true;
		}
	}

	return false;
};

/**
 * @param {Array} fieldSets
 * @returns {Array} fields
 */
export const getAllDataDefinitionFieldsFromAllFieldSets = (fieldSets = []) => {
	const fields = [];

	fieldSets.forEach(({dataDefinitionFields = []}) => {
		dataDefinitionFields.forEach((field) => {
			fields.push(field);
		});
	});

	return fields;
};

export const getDataDefinitionField = (
	dataDefinition = {dataDefinitionFields: []},
	fieldName
) => {
	let field = null;

	forEachDataDefinitionField(dataDefinition, (currentField) => {
		if (currentField.name === fieldName) {
			field = currentField;

			return true;
		}

		return false;
	});

	return field;
};

export const getDataDefinitionFieldSet = (dataDefinitionFields, fieldSetId) =>
	dataDefinitionFields.find(
		({customProperties: {ddmStructureId}}) => ddmStructureId == fieldSetId
	);

export const getFieldLabel = (dataDefinition, fieldName) => {
	const field = getDataDefinitionField(dataDefinition, fieldName);

	if (field) {
		return (
			field.label[Liferay.ThemeDisplay.getLanguageId()] ||
			field.label[dataDefinition.defaultLanguageId]
		);
	}

	return fieldName;
};

export const getOptionLabel = (options = {}, value) => {
	return (options[themeDisplay.getLanguageId()] || []).reduce(
		(result, option) => {
			if (option.value === value) {
				return option.label;
			}

			return result;
		},
		value
	);
};
