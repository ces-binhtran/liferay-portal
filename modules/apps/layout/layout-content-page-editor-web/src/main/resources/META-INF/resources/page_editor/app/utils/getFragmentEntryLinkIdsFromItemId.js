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

import {LAYOUT_DATA_ITEM_TYPES} from '../config/constants/layoutDataItemTypes';

export default function getFragmentEntryLinkIdsFromItemId({
	itemId,
	layoutData,
}) {
	const item = layoutData?.items[itemId];

	return item?.type === LAYOUT_DATA_ITEM_TYPES.fragment
		? [item?.config?.fragmentEntryLinkId]
		: item?.children.reduce(
				(acc, childId) => [
					...acc,
					...getFragmentEntryLinkIdsFromItemId({
						itemId: childId,
						layoutData,
					}),
				],
				[]
		  );
}
