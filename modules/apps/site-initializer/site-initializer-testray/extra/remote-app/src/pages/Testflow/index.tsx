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

import {Link} from 'react-router-dom';

import {AvatarGroup} from '../../components/Avatar';
import Container from '../../components/Layout/Container';
import ProgressBar from '../../components/ProgressBar';
import Table from '../../components/Table';
import {routines} from '../../util/mock';

const TestFlow = () => {
	return (
		<Container title="Task">
			<Table
				columns={[
					{
						key: 'status',
						render: (value: string) => (
							<Link
								to={`/project/${value
									.toLowerCase()
									.replace(' ', '_')}/routines`}
							>
								<span className="label label-inverse-secondary">
									{value}
								</span>
							</Link>
						),
						value: 'Status',
					},
					{key: 'startDate', value: 'Start Date'},
					{key: 'task', value: 'Task'},
					{key: 'projectName', value: 'Project Name'},
					{key: 'routineName', value: 'Routine Name'},
					{key: 'buildName', value: 'Build Name'},
					{
						key: 'score',
						render: ({passed, total}: any) => {
							return `${passed} / ${total}, ${Math.ceil(
								(passed * 100) / total
							)}%`;
						},
						value: 'Score',
					},
					{
						key: 'score',
						render: ({passed, total}: any) => {
							return (
								<ProgressBar
									incomplete={total - passed}
									passed={passed}
								/>
							);
						},
						value: 'Progress',
					},
					{
						key: 'assigned',
						render: (assigned: any) => (
							<AvatarGroup assignedUsers={assigned} />
						),
						value: 'Assigned',
					},
				]}
				items={routines}
			/>
		</Container>
	);
};

export default TestFlow;
