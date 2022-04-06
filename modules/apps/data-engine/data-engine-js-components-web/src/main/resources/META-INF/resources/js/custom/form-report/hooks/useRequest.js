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

import {useEffect, useState} from 'react';

import {request} from '../utils/client';

export default function useRequest(endpoint) {
	const [state, setState] = useState({
		error: null,
		isLoading: true,
		response: {},
	});

	useEffect(() => {
		if (endpoint === null) {
			return;
		}

		request({endpoint})
			.then((response) => {
				setState({
					error: null,
					isLoading: false,
					response,
				});
			})
			.catch((error) => {
				setState({
					error,
					isLoading: false,
					response: {},
				});
			});
	}, [endpoint]);

	return state;
}
