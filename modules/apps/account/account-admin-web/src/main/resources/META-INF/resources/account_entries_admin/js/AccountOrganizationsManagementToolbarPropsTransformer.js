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

import {
	getCheckedCheckboxes,
	openSelectionModal,
	postForm,
	sub,
} from 'frontend-js-web';

import {openConfirmModal} from 'frontend-js-web';

export default function propsTransformer({portletNamespace, ...otherProps}) {
	return {
		...otherProps,
		onActionButtonClick: (event, {item}) => {
			const data = item?.data;

			const action = data?.action;

			if (action === 'removeOrganizations') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-remove-the-selected-organizations'
					),
					onConfirm: (isConfirmed) => {
						if (isConfirmed) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (form) {
								postForm(form, {
									data: {
										accountOrganizationIds: getCheckedCheckboxes(
											form,
											`${portletNamespace}allRowIds`
										),
									},
									url: data?.removeOrganizationsURL,
								});
							}
						}
					},
				});
			}
		},
		onCreateButtonClick: (event, {item}) => {
			const data = item?.data;

			openSelectionModal({
				buttonAddLabel: Liferay.Language.get('assign'),
				containerProps: {
					className: '',
				},
				iframeBodyCssClass: '',
				multiple: true,
				onSelect: (selectedItems) => {
					if (!selectedItems?.length) {
						return;
					}

					const form = document.getElementById(
						`${portletNamespace}fm`
					);

					if (form) {
						const values = selectedItems.map((item) => item.value);

						postForm(form, {
							data: {
								accountOrganizationIds: values.join(','),
							},
							url: data?.assignAccountOrganizationsURL,
						});
					}
				},
				title: sub(
					Liferay.Language.get('assign-organizations-to-x'),
					data?.accountEntryName
				),
				url: data?.selectAccountOrganizationsURL,
			});
		},
	};
}
