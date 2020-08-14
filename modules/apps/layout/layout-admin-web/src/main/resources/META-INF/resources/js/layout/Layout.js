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

import {fetch, openToast} from 'frontend-js-web';
import React, {useEffect, useRef, useState} from 'react';

import Breadcrumbs from '../breadcrumbs/Breadcrumbs';
import MillerColumns from '../miller_columns/MillerColumns';
import actionHandlers from './actionHandlers';

const Layout = ({
	getItemChildrenURL,
	initialBreadcrumbEntries,
	initialLayoutColumns,
	languageDirection,
	languageId,
	moveItemURL,
	namespace,
	searchContainerId,
}) => {
	const layoutRef = useRef();
	const searchContainer = useRef();

	const [breadcrumbEntries, setBreadcrumbEntries] = useState(
		initialBreadcrumbEntries
	);
	const [layoutColumns, setLayoutColumns] = useState(initialLayoutColumns);
	const [searchContainerElement, setSearchContainerElement] = useState();

	useEffect(() => {
		const A = new AUI();

		A.use(
			'liferay-search-container',
			'liferay-search-container-select',
			(A) => {
				const plugins = [
					{
						cfg: {
							rowSelector: '.miller-columns-item',
						},
						fn: A.Plugin.SearchContainerSelect,
					},
				];

				if (searchContainer.current) {
					searchContainer.current.destroy();
				}

				searchContainer.current = new Liferay.SearchContainer({
					contentBox: layoutRef.current,
					id: `${namespace}${searchContainerId}`,
					plugins,
				});

				setSearchContainerElement(searchContainer.current);
			}
		);
	}, [namespace, searchContainerId]);

	const getItemChildren = (parentId) => {
		const formData = new FormData();

		formData.append(`${namespace}plid`, parentId);

		fetch(getItemChildrenURL, {
			body: formData,
			method: 'POST',
		})
			.then((response) => response.json())
			.then(({children}) => {
				const newLayoutColumns = [];

				for (let i = 0; i < layoutColumns.length; i++) {
					const column = layoutColumns[i];

					if (!column.some((item) => item.id === parentId)) {
						newLayoutColumns.push(column);
					}
					else {
						const newColumn = [];

						column.forEach((item) => {
							if (item.active) {
								newColumn.push({...item, active: false});
							}
							else if (item.id === parentId) {
								newColumn.push({...item, active: true});
							}
							else {
								newColumn.push({...item});
							}
						});

						newLayoutColumns.push(newColumn);

						break;
					}
				}

				newLayoutColumns.push(children);

				setLayoutColumns(newLayoutColumns);
			})
			.catch();
	};

	const saveData = (movedItems, parentItemId) => {
		const formData = new FormData();

		const [, mainColumn] = layoutColumns;
		const activeItem = mainColumn.find((item) => item.active);

		formData.append(`${namespace}plids`, JSON.stringify(movedItems));
		formData.append(`${namespace}parentPlid`, parentItemId);

		if (activeItem) {
			formData.append(`${namespace}selPlid`, activeItem.id);
		}

		fetch(moveItemURL, {
			body: formData,
			method: 'POST',
		})
			.then((response) => response.json())
			.then(({errorMessage, layoutColumns: updatedLayoutColumns}) => {
				if (errorMessage) {
					openToast({
						message: errorMessage,
						type: 'danger',
					});
				}
				if (updatedLayoutColumns) {
					setLayoutColumns(updatedLayoutColumns);
				}
			});
	};

	const updateBreadcrumbs = (columns) => {
		const newBreadcrumbEntries = [breadcrumbEntries[0]];

		for (let i = 0; i < columns.length; i++) {
			const item = columns[i].items.find((item) => item.active);

			if (item) {
				newBreadcrumbEntries.push({
					title: item.title,
					url: item.url,
				});
			}
		}

		setBreadcrumbEntries(newBreadcrumbEntries);
	};

	return (
		<div ref={layoutRef}>
			<Breadcrumbs entries={breadcrumbEntries} />
			<MillerColumns
				actionHandlers={actionHandlers}
				initialColumns={layoutColumns}
				namespace={namespace}
				onColumnsChange={updateBreadcrumbs}
				onItemMove={saveData}
				onItemStayHover={getItemChildren}
				rtl={languageDirection[languageId] === 'rtl'}
				searchContainer={searchContainerElement}
			/>
		</div>
	);
};

export default function ({
	context: {namespace},
	props: {
		breadcrumbEntries,
		getItemChildrenURL,
		languageDirection,
		languageId,
		layoutColumns,
		moveItemURL,
		searchContainerId,
	},
}) {
	return (
		<Layout
			getItemChildrenURL={getItemChildrenURL}
			initialBreadcrumbEntries={breadcrumbEntries}
			initialLayoutColumns={layoutColumns}
			languageDirection={languageDirection}
			languageId={languageId}
			moveItemURL={moveItemURL}
			namespace={namespace}
			searchContainerId={searchContainerId}
		/>
	);
}
