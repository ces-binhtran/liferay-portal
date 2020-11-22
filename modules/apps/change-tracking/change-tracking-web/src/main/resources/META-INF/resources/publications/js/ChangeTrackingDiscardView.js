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

import {ClayPaginationBarWithBasicItems} from '@clayui/pagination-bar';
import ClayTable from '@clayui/table';
import React, {useState} from 'react';

const ChangeTrackingDiscardView = ({
	ctEntriesJSONArray,
	renderURL,
	spritemap,
	typeNames,
	userInfo,
}) => {
	const [delta, setDelta] = useState(20);
	const [page, setPage] = useState(1);

	const ctEntries = ctEntriesJSONArray.slice(0);

	for (let i = 0; i < ctEntries.length; i++) {
		const entry = ctEntries[i];

		const entryUserInfo = userInfo[entry.userId.toString()];

		entry.portraitURL = entryUserInfo.portraitURL;
		entry.userName = entryUserInfo.userName;

		entry.typeName = typeNames[entry.modelClassNameId.toString()];
	}

	ctEntries.sort((a, b) => {
		const titleA = a.title;
		const titleB = b.title;
		const typeNameA = a.typeName.toUpperCase();
		const typeNameB = b.typeName.toUpperCase();

		if (typeNameA < typeNameB) {
			return -1;
		}

		if (typeNameA > typeNameB) {
			return 1;
		}

		if (titleA < titleB) {
			return -1;
		}

		if (titleA > titleB) {
			return 1;
		}

		return 0;
	});

	const filterDisplayEntries = (entries) => {
		if (entries.length > 5) {
			entries = entries.slice(delta * (page - 1), delta * page);
		}

		return entries;
	};

	const getRenderURL = (entry) => {
		const portletURL = Liferay.PortletURL.createURL(renderURL);

		portletURL.setParameter('ctEntryId', entry.ctEntryId);

		return portletURL.toString();
	};

	const getTableRows = () => {
		const rows = [];

		let currentTypeName = '';

		const entries = filterDisplayEntries(ctEntries);

		for (let i = 0; i < entries.length; i++) {
			const entry = entries[i];

			if (entry.typeName !== currentTypeName) {
				currentTypeName = entry.typeName;

				rows.push(
					<ClayTable.Row divider>
						<ClayTable.Cell colSpan={2}>
							{entry.typeName}
						</ClayTable.Cell>
					</ClayTable.Row>
				);
			}

			const cells = [];

			if (entry.portraitURL) {
				cells.push(
					<ClayTable.Cell>
						<span
							className="lfr-portal-tooltip"
							title={entry.userName}
						>
							<span className="rounded-circle sticker sticker-primary">
								<span className="sticker-overlay">
									<img
										alt="thumbnail"
										className="img-fluid"
										src={entry.portraitURL}
									/>
								</span>
							</span>
						</span>
					</ClayTable.Cell>
				);
			}
			else {
				let userPortraitCss =
					'sticker sticker-circle sticker-light user-icon-color-';

				userPortraitCss += entry.userId % 10;

				cells.push(
					<ClayTable.Cell>
						<span
							className="lfr-portal-tooltip"
							title={entry.userName}
						>
							<span className={userPortraitCss}>
								<span className="inline-item">
									<svg className="lexicon-icon">
										<use href={spritemap + '#user'} />
									</svg>
								</span>
							</span>
						</span>
					</ClayTable.Cell>
				);
			}

			cells.push(
				<ClayTable.Cell>
					<button className="change-row-button">
						<div className="publication-name">{entry.title}</div>
						<div className="publication-description">
							{entry.description}
						</div>
					</button>
				</ClayTable.Cell>
			);

			rows.push(<ClayTable.Row>{cells}</ClayTable.Row>);
		}

		return rows;
	};

	const renderPagination = () => {
		if (ctEntries.length <= 5) {
			return '';
		}

		return (
			<ClayPaginationBarWithBasicItems
				activeDelta={delta}
				activePage={page}
				deltas={[4, 8, 20, 40, 60].map((size) => ({
					label: size,
				}))}
				ellipsisBuffer={3}
				onDeltaChange={(newDelta) => {
					setDelta(newDelta);
					setPage(1);
				}}
				onPageChange={(newPage) => setPage(newPage)}
				totalItems={ctEntries.length}
			/>
		);
	};

	return (
		<>
			<ClayTable className="publications-table" hover={false}>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTable.Cell headingCell style={{width: '5%'}}>
							{Liferay.Language.get('user')}
						</ClayTable.Cell>

						<ClayTable.Cell headingCell style={{width: '95%'}}>
							{Liferay.Language.get('change')}
						</ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>{getTableRows()}</ClayTable.Body>
			</ClayTable>

			{renderPagination()}
		</>
	);
};

export default function (props) {
	return <ChangeTrackingDiscardView {...props} />;
}
