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

/// <reference types="react" />

import {TQueries} from '../../utils/request';
export declare type TColumn = {
	expanded: boolean;
	label: string;
	show?: boolean;
	sortable?: boolean;
	value: string;
};
export declare type TItem = {
	checked: boolean;
	columns: {
		label: string;
		show?: boolean;
	}[];
	disabled: boolean;
	id: string;
};
export declare type TFormattedItems = {
	[key: string]: TItem;
};
interface ITableProps<TRawItem> {
	columns: TColumn[];
	disabled?: boolean;
	emptyStateTitle: string;
	mapperItems: (items: TRawItem[]) => TItem[];
	noResultsTitle: string;
	onItemsChange?: (items: TFormattedItems) => void;
	requestFn: (params: TQueries) => Promise<any>;
}
declare function TableWrapper<TRawItem>(
	props: ITableProps<TRawItem>
): JSX.Element;
export default TableWrapper;
