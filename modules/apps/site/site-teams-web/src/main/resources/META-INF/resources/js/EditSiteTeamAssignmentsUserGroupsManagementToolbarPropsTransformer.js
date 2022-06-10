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

import {openSelectionModal} from 'frontend-js-web';

import {openConfirmModal} from 'frontend-js-web';

export default function propsTransformer({portletNamespace, ...otherProps}) {
	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteUserGroups') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-delete-this'
					),
					onConfirm: (isSelected) => {
						if (isSelected) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (form) {
								submitForm(form);
							}
						}
					},
				});
			}
		},
		onCreateButtonClick(event, {item}) {
			const data = item?.data;

			const action = data?.action;

			if (action === 'selectUserGroup') {
				openSelectionModal({
					multiple: true,
					onSelect: (selectedItems) => {
						if (selectedItems.length) {
							const addTeamUserGroupsFm = document.getElementById(
								`${portletNamespace}addTeamUserGroupsFm`
							);

							if (!addTeamUserGroupsFm) {
								return;
							}

							const input = document.createElement('input');

							input.name = `${portletNamespace}rowIds`;
							input.value = selectedItems.map(
								(item) => item.value
							);

							addTeamUserGroupsFm.appendChild(input);

							submitForm(addTeamUserGroupsFm);
						}
					},
					title: data?.title,
					url: data?.selectUserGroupURL,
				});
			}
		},
	};
}
