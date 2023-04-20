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

import {TreeView as ClayTreeView} from '@clayui/core';
import ClayIcon from '@clayui/icon';
import classnames from 'classnames';
import {getOpener} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useMemo, useState} from 'react';

import getSearchItems from '../utils/getSearchItems';
import normalizeItems from '../utils/normalizeItems';
import SearchField from './SearchField';

const ITEM_TYPES_SYMBOL = {
	article: 'document-text',
	folder: 'folder',
};

export default function MoveModal({items: initialItems, selectedItemId}) {
	const items = useMemo(() => normalizeItems(initialItems), [initialItems]);

	const searchItems = useMemo(() => getSearchItems(initialItems), [
		initialItems,
	]);

	const [searchActive, setSearchActive] = useState(false);

	const handleItemMove = (item, parentItem, index) => {
		getOpener().Liferay.fire('selectKBMoveFolder', {
			index,
			item,
			parentItem,
		});
	};

	const onItemClick = (event, parentItem) => {
		event.stopPropagation();

		const index = {next: parentItem.children.length, previous: 0};
		getOpener().Liferay.fire('selectKBMoveFolder', {index, parentItem});
	};

	const handleSearchChange = ({isSearchActive}) => {
		setSearchActive(isSearchActive);
	};

	return (
		<div className="container-fluid p-3">
			<SearchField
				handleSearchChange={handleSearchChange}
				items={searchItems}
			/>

			{!searchActive && (
				<ClayTreeView
					defaultItems={items}
					defaultSelectedKeys={new Set([selectedItemId])}
					dragAndDrop
					nestedKey="children"
					onItemMove={handleItemMove}
					showExpanderOnHover={false}
				>
					{(item) => {
						return (
							<ClayTreeView.Item
								className={classnames({
									'knowledge-base-navigation-item-active':
										item.id === selectedItemId,
								})}
								onClick={(event) => {
									onItemClick(event, item);
								}}
							>
								<ClayTreeView.ItemStack>
									<ClayIcon
										symbol={ITEM_TYPES_SYMBOL[item.type]}
									/>

									{item.name}
								</ClayTreeView.ItemStack>

								<ClayTreeView.Group items={item.children}>
									{(item) => {
										return (
											<ClayTreeView.Item>
												<ClayIcon
													symbol={
														ITEM_TYPES_SYMBOL[
															item.type
														]
													}
												/>

												{item.name}
											</ClayTreeView.Item>
										);
									}}
								</ClayTreeView.Group>
							</ClayTreeView.Item>
						);
					}}
				</ClayTreeView>
			)}
		</div>
	);
}

const itemShape = {
	classNameId: PropTypes.string.isRequired,
	href: PropTypes.string.isRequired,
	id: PropTypes.string.isRequired,
	name: PropTypes.string.isRequired,
	type: PropTypes.oneOf(Object.keys(ITEM_TYPES_SYMBOL)).isRequired,
};

itemShape.children = PropTypes.arrayOf(PropTypes.shape(itemShape));

MoveModal.propTypes = {
	items: PropTypes.arrayOf(PropTypes.shape(itemShape)),
	selectedItemId: PropTypes.string,
};
