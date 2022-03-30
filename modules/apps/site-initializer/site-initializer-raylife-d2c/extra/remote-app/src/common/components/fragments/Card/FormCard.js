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

import ClayCard from '@clayui/card';
import React from 'react';

export default function FormCard({children}) {
	return (
		<div className="col-12">
			<ClayCard className="d-flex flex-column m-auto px-3 px-lg-6 px-md-6 px-sm-3 py-5 py-lg-6 py-md-6 py-sm-5 rounded shadow-lg">
				{children}
			</ClayCard>
		</div>
	);
}
