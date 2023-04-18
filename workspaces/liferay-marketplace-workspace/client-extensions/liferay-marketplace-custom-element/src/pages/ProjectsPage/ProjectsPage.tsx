import React, {useState} from 'react';

import {CreateProjectModal} from '../../components/CreateProjectModal/CreateProjectModal';
import {
	DashboardTable,
	TableHeaders,
} from '../../components/DashboardTable/DashboardTable';
import {DashboardListItems} from '../DashBoardPage/DashboardPage';
import {DashboardPage} from '../DashBoardPage/DashboardPage';
import {ProjectsTableRow} from './ProjectsTableRow';

interface ProjectsPage {
	dashboardNavigationItems: DashboardListItems[];
}

const projectsTableHeaders: TableHeaders = [
	{
		title: 'Project Name',
	},
	{
		title: 'Created By',
	},
	{
		title: 'Type',
	},
	{
		title: 'End Date',
	},
	{
		title: 'Provisioning',
	},
	{
		title: 'Project',
	},
];

export function ProjectsPage({dashboardNavigationItems}: ProjectsPage) {
	const [visible, setVisible] = useState(false);

	return (
		<>
			<DashboardPage
				buttonMessage="+ New Project"
				dashboardNavigationItems={dashboardNavigationItems}
				messages={{
					description:
						'Manage projects to build and test your apps and solutions',
					title: 'Projects',
				}}
				onButtonClick={() => setVisible(true)}
			>
				<DashboardTable
					emptyStateMessage={{
						description1:
							'Publish projects and they will show up here.',
						description2: 'Click on “New Projects” to start.',
						title: 'No projects yet',
					}}
					items={[0, 2]}
					tableHeaders={projectsTableHeaders}
				>
					{() => <ProjectsTableRow />}
				</DashboardTable>
			</DashboardPage>

			{visible && (
				<CreateProjectModal handleClose={() => setVisible(false)} />
			)}
		</>
	);
}
