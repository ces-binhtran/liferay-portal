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

import {gql, useMutation} from '@apollo/client';

export const CREATE_ADMIN_LIFERAY_EXPERIENCE_CLOUD = gql`
	mutation createAdminLiferayExperienceCloud(
		$AdminLiferayExperienceCloud: InputC_AdminLiferayExperienceCloud!
	) {
		c {
			createAdminLiferayExperienceCloud(
				AdminLiferayExperienceCloud: $AdminLiferayExperienceCloud
			) {
				emailAddress
				fullName
				githubUsername
				liferayExperienceCloudEnvironmentId
			}
		}
	}
`;

export function useCreateAdminLiferayExperienceCloud(variables) {
	return useMutation(CREATE_ADMIN_LIFERAY_EXPERIENCE_CLOUD, {
		variables,
	});
}
