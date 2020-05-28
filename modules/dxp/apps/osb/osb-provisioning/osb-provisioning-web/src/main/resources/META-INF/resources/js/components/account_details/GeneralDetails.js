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
import React from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TOGGLE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from './DetailField';

function GeneralDetails({details, statuses, tiers}) {
	const formData = {
		code: convertDashToEmptyString(details.code),
		name: convertDashToEmptyString(details.name),
		region: convertDashToEmptyString(details.region),
		status: convertDashToEmptyString(details.status),
		tier: convertDashToEmptyString(details.tier),
		updateAccount: true
	};

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('general-details')}
			</ClayList.Header>

			<DetailField
				formAction={details.editAccountURL}
				formData={formData}
				name={Liferay.Language.get('account-name')}
			>
				{details.name}
			</DetailField>

			<DetailField
				displayAs="label"
				formAction={details.editAccountURL}
				formData={formData}
				inputStyle={details.statusStyle}
				name={Liferay.Language.get('status')}
				options={statuses}
				type={FIELD_TYPE_SELECT}
			>
				{details.status}
			</DetailField>

			<DetailField
				formAction={details.editAccountURL}
				formData={formData}
				name={Liferay.Language.get('code')}
			>
				{details.code}
			</DetailField>

			<DetailField
				name={Liferay.Language.get('created')}
				type={FIELD_TYPE_NONEDITABLE}
			>
				{details.dateCreated}
			</DetailField>

			<DetailField
				formAction={details.editAccountURL}
				formData={formData}
				name={Liferay.Language.get('tier')}
				options={tiers}
				type={FIELD_TYPE_SELECT}
			>
				{details.tier}
			</DetailField>

			<DetailField
				name={Liferay.Language.get('last-modified')}
				type={FIELD_TYPE_NONEDITABLE}
			>
				{details.dateModified}
			</DetailField>

			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>

			<DetailField
				formAction={details.editAccountURL}
				formData={formData}
				name={Liferay.Language.get('partner-reseller-si')}
				type={FIELD_TYPE_EXTERNAL}
			>
				{details.partnerTeamName}
			</DetailField>

			<DetailField
				formAction={details.editAccountURL}
				formData={formData}
				name={Liferay.Language.get('first-line-support')}
				type={FIELD_TYPE_TOGGLE}
			>
				{details.firstLineSupportTeamName}
			</DetailField>
		</ClayList>
	);
}

GeneralDetails.propTypes = {
	details: PropTypes.shape({
		code: PropTypes.string,
		dateCreated: PropTypes.string,
		dateModified: PropTypes.string,
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		parterTeamName: PropTypes.string,
		status: PropTypes.string,
		statusStyle: PropTypes.string,
		tier: PropTypes.string
	}),
	statuses: PropTypes.arrayOf(PropTypes.string),
	tiers: PropTypes.arrayOf(PropTypes.string)
};

export default GeneralDetails;
