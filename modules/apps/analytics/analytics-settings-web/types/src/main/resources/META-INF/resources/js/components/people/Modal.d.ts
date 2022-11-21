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

import React from 'react';
import {TQueries} from '../../utils/request';
import {TColumn} from '../table/Table';
import {EPeople} from './People';
export interface ICommonModalProps {
	observer: any;
	onCloseModal: () => void;
	syncAllAccounts: boolean;
	syncAllContacts: boolean;
	syncedIds: {
		[key in EPeople]: string[];
	};
}
interface IModalProps {
	columns: TColumn[];
	emptyStateTitle: string;
	name: EPeople;
	noResultsTitle: string;
	observer: any;
	onCloseModal: () => void;
	requestFn: (params: TQueries) => Promise<any>;
	syncAllAccounts: boolean;
	syncAllContacts: boolean;
	syncedIds: {
		[key in EPeople]: string[];
	};
	title: string;
}
declare const Modal: React.FC<IModalProps>;
export default Modal;
