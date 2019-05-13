import fetch from '../../../test/mock/fetch';
import fetchFailure from '../../../test/mock/fetchFailure';
import { PendingItemsCard } from '../process-items/ProcessItemsCard';
import ProcessDashboard from '../ProcessDashboard';
import React from 'react';
import { MockRouter as Router } from '../../../test/mock/MockRouter';
import { withParams } from '../../../shared/components/router/routerUtil';
import WorkloadByStepCard from '../workload-by-step/WorkloadByStepCard';

beforeAll(() => {
	const vbody = document.createElement('div');

	vbody.innerHTML = `
		<div id="workflow">
			<div class="user-control-group">
				<div class="control-menu-icon"></div>
			</div>
		</div>
	`;
	document.body.appendChild(vbody);
});

test('Should render component with completed tab activated', () => {
	const component = mount(
		<Router client={fetchFailure()} initialPath="/dashboard/35315">
			<ProcessDashboard processId={35315} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render component with default tab activated', () => {
	const data = {
		id: 35315,
		instanceCount: 1,
		onTimeInstanceCount: 1,
		overdueInstanceCount: 0,
		title: 'Single Approver'
	};

	const component = mount(
		<Router client={fetch(data)} initialPath="/dashboard/35315">
			<ProcessDashboard processId={35315} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render component with failure state', () => {
	const component = mount(
		<Router client={fetchFailure()} initialPath="/dashboard/35315/completed">
			<ProcessDashboard processId={35315} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render dashboard route children', () => {
	const component = mount(
		<Router client={fetch({})}>
			{withParams(PendingItemsCard, WorkloadByStepCard)({
				match: {
					params: {
						processId: 35315
					}
				}
			})}
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should render with blocked SLA', () => {
	const component = mount(
		<Router client={fetchFailure()} initialPath="/dashboard/35315/completed">
			<ProcessDashboard processId="123" />
		</Router>
	);

	const instance = component.find(ProcessDashboard).instance();

	instance.setState({ blockedSLACount: 1 }, () => {
		expect(component).toMatchSnapshot();
	});
});