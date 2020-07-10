/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE
} from '../../utilities/constants';
import {postData} from '../../utilities/helpers';
import InlineEdit from '../InlineEdit';

function LiveUpdateableField({
	displayValue,
	fieldLabel,
	fieldName = fieldLabel,
	formAction,
	inputStyle,
	options = [],
	type = FIELD_TYPE_TEXT,
	value,
	updateFormData
}) {
	const [currentDisplayValue, setCurrentDisplayValue] = useState(
		displayValue
	);
	const [fieldValue, setFieldValue] = useState(value);

	useEffect(() => {
		if (displayValue && fieldValue !== value) {
			const currentOption = options.find(
				item => item.value === fieldValue
			);

			setCurrentDisplayValue(currentOption.label);
		}
	}, [displayValue, fieldValue, options, value]);

	function handleSave(newValue) {
		const formData = updateFormData(newValue);

		postData(formAction, formData, 'formData')
			.then(({data}) => {
				if (data.successMessage) {
					setFieldValue(newValue);
				}
			})
			.catch(err => console.error(err));
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				{fieldLabel && (
					<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>
				)}

				<div className="list-group-text">
					<InlineEdit
						displayValue={currentDisplayValue}
						fieldName={fieldName}
						fieldValue={fieldValue}
						inputStyle={inputStyle}
						options={options}
						save={handleSave}
						type={type}
					/>
				</div>
			</div>
		</ClayList.Item>
	);
}

LiveUpdateableField.propTypes = {
	displayValue: PropTypes.string,
	fieldLabel: PropTypes.string,
	fieldName: PropTypes.string,
	formAction: PropTypes.string,
	inputStyle: PropTypes.string,
	options: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string
		})
	),
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TEXTAREA,
		FIELD_TYPE_TOGGLE
	]),
	updateFormData: PropTypes.func,
	value: PropTypes.string.isRequired
};

export default LiveUpdateableField;
