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

import yupSchema from '../../schema/yup';
import {waitTimeout} from '../../util';
import {searchUtil} from '../../util/search';
import {SubTaskStatuses} from '../../util/statuses';
import {Liferay} from '../liferay';
import {liferayMessageBoardImpl} from './LiferayMessageBoard';
import Rest from './Rest';
import {testrayCaseResultImpl} from './TestrayCaseResult';
import {testrayIssueImpl} from './TestrayIssues';
import {testraySubtaskCaseResultImpl} from './TestraySubtaskCaseResults';
import {testraySubtaskIssuesImpl} from './TestraySubtaskIssues';
import {TestraySubTask} from './types';

type SubtaskForm = typeof yupSchema.subtask.__outputType & {
	projectId: number;
};

class TestraySubtaskImpl extends Rest<SubtaskForm, TestraySubTask> {
	public UNASSIGNED_USER_ID = 0;

	constructor() {
		super({
			adapter: ({
				dueStatus,
				errors,
				mbMessageId,
				mbThreadId,
				mergedToSubtaskId: r_mergedToTestraySubtask_c_subtaskId,
				name,
				score,
				taskId: r_taskToSubtasks_c_taskId,
				userId: r_userToSubtasks_userId,
			}) => ({
				dueStatus,
				errors,
				mbMessageId,
				mbThreadId,
				name,
				r_mergedToTestraySubtask_c_subtaskId,
				r_taskToSubtasks_c_taskId,
				r_userToSubtasks_userId,
				score,
			}),
			nestedFields: 'tasks,users,subtask',
			transformData: (subTask) => ({
				...subTask,
				mergedToSubtaskId: subTask.r_mergedToTestraySubtask_c_subtaskId,
				task: subTask.r_taskToSubtasks_c_task,
				user: subTask.r_userToSubtasks_user,
			}),
			uri: 'subtasks',
		});
	}

	private async getCaseResultsFromSubtask(subTaskId: number) {
		const subTaskCaseResultResponse = await testraySubtaskCaseResultImpl.getAll(
			searchUtil.eq('subtaskId', subTaskId)
		);

		if (!subTaskCaseResultResponse) {
			return [];
		}

		const subTaskCaseResults =
			testraySubtaskCaseResultImpl.transformDataFromList(
				subTaskCaseResultResponse
			)?.items || [];

		return subTaskCaseResults;
	}

	public async assignTo(subTask: TestraySubTask, userId: number) {
		const caseResults = await this.getCaseResultsFromSubtask(subTask.id);

		const caseResultIds = caseResults.map((caseResult) =>
			Number(caseResult.caseResult?.id)
		);

		await this.update(subTask.id, {
			dueStatus: SubTaskStatuses.IN_ANALYSIS,
			userId,
		});

		await testrayCaseResultImpl.updateBatch(
			caseResultIds,
			caseResultIds.map(() => ({
				userId,
			}))
		);
	}

	public async assignToMe(subTask: TestraySubTask) {
		await this.update(subTask.id, {
			dueStatus: SubTaskStatuses.IN_ANALYSIS,
			userId: Number(Liferay.ThemeDisplay.getUserId()),
		});

		const caseResults = await this.getCaseResultsFromSubtask(subTask.id);

		const caseResultIds = caseResults.map((caseResult) =>
			Number(caseResult.caseResult?.id)
		);

		const userId = Number(Liferay.ThemeDisplay.getUserId());

		await testrayCaseResultImpl.updateBatch(
			caseResultIds,
			caseResultIds.map(() => ({userId}))
		);
	}

	private async addComment(data: Partial<SubtaskForm>) {
		try {
			const message = data.comment as string;
			let mbThreadId = data.mbThreadId;

			if (!mbThreadId) {
				const mbThread = await liferayMessageBoardImpl.createMbThread(
					message
				);

				mbThreadId = mbThread.id;

				await waitTimeout(1500);
			}

			const mbMessage = await liferayMessageBoardImpl.createMbMessage(
				message,
				mbThreadId as number
			);

			return {mbMessage, mbThreadId};
		}
		catch {
			return {};
		}
	}

	public async complete(
		dueStatus: string,
		issues: string[],
		subTaskcomment: Partial<SubtaskForm>,
		subTaskId: number
	) {
		const subtaskIssuesResponse = await testraySubtaskIssuesImpl.getAll(
			searchUtil.eq('subtaskId', subTaskId)
		);

		for (const issue of issues) {
			const testrayIssue = await testrayIssueImpl.createIfNotExist(issue);

			await testraySubtaskIssuesImpl.createIfNotExist({
				issueId: testrayIssue?.id,
				name: `${issue}-${subTaskId}`,
				subTaskId,
			});
		}

		if (subtaskIssuesResponse?.items) {
			const caseResultIssuesTransform = await testraySubtaskIssuesImpl.transformDataFromList(
				subtaskIssuesResponse
			);

			const subtaskIssueIdsToRemove = caseResultIssuesTransform.items
				.filter(({issue}) => !issues.includes(issue?.name || ''))
				.map(({id}) => id);

			for (const caseResultIssueId of subtaskIssueIdsToRemove) {
				await testraySubtaskIssuesImpl.remove(caseResultIssueId);
			}
		}

		if (subTaskcomment.comment) {
			const {mbMessage, mbThreadId} = await this.addComment(
				subTaskcomment
			);

			subTaskcomment.mbMessageId = mbMessage.id;
			subTaskcomment.mbThreadId = mbThreadId;
		}

		if (!subTaskcomment.comment && subTaskcomment.mbMessageId) {
			subTaskcomment.mbMessageId = 0;
		}

		await this.update(subTaskId, {
			dueStatus: SubTaskStatuses.COMPLETE,
			mbMessageId: subTaskcomment.mbMessageId,
			mbThreadId: subTaskcomment.mbThreadId,
		});

		const caseResults = await this.getCaseResultsFromSubtask(subTaskId);

		const caseResultIds = caseResults.map((caseResult) =>
			Number(caseResult.caseResult?.id)
		);

		await testrayCaseResultImpl.updateBatch(
			caseResultIds,
			caseResultIds.map(() => ({
				defaultMessageId: subTaskcomment.mbMessageId,
				dueStatus,
				mbMessageId: subTaskcomment.mbMessageId,
				mbThreadId: subTaskcomment.mbThreadId,
			}))
		);

		for (const caseResultId of caseResultIds) {
			await testrayCaseResultImpl.assignCaseResultIssue(
				caseResultId,
				issues
			);
		}
	}

	public returnToOpen(subTask: TestraySubTask) {
		return this.update(subTask.id, {
			dueStatus: SubTaskStatuses.OPEN,
			userId: this.UNASSIGNED_USER_ID,
		});
	}

	public async mergedToSubtask(subTasks: TestraySubTask[]) {
		const [parentTestraySubtask, ...childTestraySubtasks] = subTasks.sort(
			({score: scoreA}, {score: scoreB}) => scoreB - scoreA
		);

		let sumScore = parentTestraySubtask.score ?? 0;

		for (const testraySubTask of childTestraySubtasks) {
			await this.update(Number(testraySubTask.id), {
				dueStatus: SubTaskStatuses.MERGED,
				mergedToSubtaskId: parentTestraySubtask.id,
				score: 0,
			});

			const caseResults = await this.getCaseResultsFromSubtask(
				testraySubTask.id
			);

			for (const caseResult of caseResults) {
				sumScore += caseResult?.caseResult?.case?.priority || 0;

				await testraySubtaskCaseResultImpl.update(
					Number(caseResult.id),
					{
						name: `${parentTestraySubtask.id}`,
						subtaskId: parentTestraySubtask.id,
					}
				);
			}
		}

		await this.update(Number(parentTestraySubtask.id), {
			score: sumScore,
		});
	}
}

export const testraySubTaskImpl = new TestraySubtaskImpl();
