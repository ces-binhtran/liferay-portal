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

import DetailField from './DetailField';

function ExternalAccountKeys({details}) {
	const formData = {
		// TODO: LHC-2472
	};

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('external-account-keys')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('dossiera-account')}
				fieldName="dossieraAccountKey"
				formAction={''}
				formData={formData}
			>
				{details.dossieraAccountKey}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('dossiera-project')}
				fieldName="dossieraProjectKey"
				formAction={''}
				formData={formData}
			>
				{details.dossieraProjectKey}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('salesforce-project')}
				fieldName="salesforceProjectKey"
				formAction={''}
				formData={formData}
			>
				{details.salesforceProjectKey}
			</DetailField>
		</ClayList>
	);
}

ExternalAccountKeys.propTypes = {
	details: PropTypes.shape({
		dossieraAccountKey: PropTypes.string,
		dossieraProjectKey: PropTypes.string,
		key: PropTypes.string,
		salesforceProjectKey: PropTypes.string
	})
};

export default ExternalAccountKeys;
