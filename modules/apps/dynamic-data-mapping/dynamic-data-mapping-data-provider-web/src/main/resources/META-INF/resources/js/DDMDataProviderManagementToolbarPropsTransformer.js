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

import {postForm} from 'frontend-js-web';

export default function propsTransformer({
	additionalProps: {deleteDataProviderURL},
	portletNamespace,
	...otherProps
}) {
	return {
		...otherProps,
		onActionButtonClick(event, {item}) {
			if (item?.data?.action === 'deleteDataProviderInstances') {
				if (
					confirm(
						Liferay.Language.get(
							'are-you-sure-you-want-to-delete-this'
						)
					)
				) {
					const form = document.getElementById(
						`${portletNamespace}searchContainerForm`
					);

					const searchContainer = document.getElementById(
						`${portletNamespace}dataProviderInstance`
					);

					if (form && searchContainer) {
						postForm(form, {
							data: {
								deleteDataProviderInstanceIds: Liferay.Util.listCheckedExcept(
									searchContainer,
									`${portletNamespace}allRowIds`
								),
							},
							url: deleteDataProviderURL,
						});
					}
				}
			}
		},
	};
}
