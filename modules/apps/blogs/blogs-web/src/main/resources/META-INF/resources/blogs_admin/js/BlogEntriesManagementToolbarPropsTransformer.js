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

import {getCheckedCheckboxes, postForm} from 'frontend-js-web';

import {openConfirmModal} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {deleteEntriesCmd, deleteEntriesURL, trashEnabled},
	portletNamespace,
	...otherProps
}) {
	return {
		...otherProps,
		onActionButtonClick: (event, {item}) => {
			if (item?.data?.action === 'deleteEntries') {
				openConfirmModal({
					message: Liferay.Language.get(
						'are-you-sure-you-want-to-delete-this'
					),
					onConfirm: (isConfimed) => {
						if (isConfimed || trashEnabled) {
							const form = document.getElementById(
								`${portletNamespace}fm`
							);

							if (!form) {
								return;
							}

							const searchContainer = Liferay.SearchContainer.get(
								`${portletNamespace}blogEntries`
							);

							postForm(form, {
								data: {
									cmd: deleteEntriesCmd,
									deleteEntryIds: getCheckedCheckboxes(
										form,
										`${portletNamespace}allRowIds`
									),
									selectAll: searchContainer.select?.get(
										'bulkSelection'
									),
								},
								url: deleteEntriesURL,
							});
						}
					},
				});
			}
		},
	};
}
