/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.internal.search.index;

import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.document.DocumentBuilder;
import com.liferay.portal.workflow.metrics.internal.sla.processor.WorkflowMetricsSLATaskResult;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.sla.processor.WorkflowMetricsSLAStatus;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(
	immediate = true,
	property = "workflow.metrics.index.entity.name=sla-task-result",
	service = {
		SLATaskResultWorkflowMetricsIndexer.class, WorkflowMetricsIndex.class
	}
)
public class SLATaskResultWorkflowMetricsIndexer
	extends BaseSLAWorkflowMetricsIndexer {

	public Document creatDefaultDocument(
		long companyId, long nodeId, long processId, String taskName) {

		WorkflowMetricsSLATaskResult workflowMetricsSLATaskResult =
			new WorkflowMetricsSLATaskResult();

		workflowMetricsSLATaskResult.setCompanyId(companyId);
		workflowMetricsSLATaskResult.setNodeId(nodeId);
		workflowMetricsSLATaskResult.setProcessId(processId);
		workflowMetricsSLATaskResult.setTaskName(taskName);

		return createDocument(workflowMetricsSLATaskResult);
	}

	public Document createDocument(
		WorkflowMetricsSLATaskResult workflowMetricsSLATaskResult) {

		DocumentBuilder documentBuilder = documentBuilderFactory.builder();

		if (workflowMetricsSLATaskResult.getAssigneeId() != null) {
			documentBuilder.setLong(
				"assigneeId", workflowMetricsSLATaskResult.getAssigneeId());
		}

		documentBuilder.setValue(
			"breached", workflowMetricsSLATaskResult.isBreached()
		).setLong(
			"companyId", workflowMetricsSLATaskResult.getCompanyId()
		);

		if (workflowMetricsSLATaskResult.getCompletionLocalDateTime() != null) {
			documentBuilder.setDate(
				"completionDate",
				formatLocalDateTime(
					workflowMetricsSLATaskResult.getCompletionLocalDateTime()));
		}

		if (workflowMetricsSLATaskResult.getCompletionUserId() != null) {
			documentBuilder.setLong(
				"completionUserId",
				workflowMetricsSLATaskResult.getCompletionUserId());
		}

		documentBuilder.setValue(
			"deleted", false
		).setValue(
			"instanceCompleted",
			workflowMetricsSLATaskResult.isInstanceCompleted()
		).setLong(
			"instanceId", workflowMetricsSLATaskResult.getInstanceId()
		);

		if (workflowMetricsSLATaskResult.getLastCheckLocalDateTime() != null) {
			documentBuilder.setDate(
				"lastCheckDate",
				formatLocalDateTime(
					workflowMetricsSLATaskResult.getLastCheckLocalDateTime()));
		}

		documentBuilder.setLong(
			"nodeId", workflowMetricsSLATaskResult.getNodeId()
		).setValue(
			"onTime", workflowMetricsSLATaskResult.isOnTime()
		).setLong(
			"processId", workflowMetricsSLATaskResult.getProcessId()
		).setLong(
			"slaDefinitionId", workflowMetricsSLATaskResult.getSLADefinitionId()
		);

		WorkflowMetricsSLAStatus workflowMetricsSLAStatus =
			workflowMetricsSLATaskResult.getWorkflowMetricsSLAStatus();

		if (workflowMetricsSLAStatus != null) {
			documentBuilder.setString(
				"status", workflowMetricsSLAStatus.name());
		}

		documentBuilder.setLong(
			"taskId", workflowMetricsSLATaskResult.getTaskId()
		).setString(
			"taskName", workflowMetricsSLATaskResult.getTaskName()
		).setString(
			"uid",
			digest(
				workflowMetricsSLATaskResult.getCompanyId(),
				workflowMetricsSLATaskResult.getInstanceId(),
				workflowMetricsSLATaskResult.getNodeId(),
				workflowMetricsSLATaskResult.getProcessId(),
				workflowMetricsSLATaskResult.getSLADefinitionId(),
				workflowMetricsSLATaskResult.getTaskId())
		);

		return documentBuilder.build();
	}

	@Override
	public String getIndexName(long companyId) {
		return _slaTaskResultWorkflowMetricsIndexNameBuilder.getIndexName(
			companyId);
	}

	@Override
	public String getIndexType() {
		return "WorkflowMetricsSLATaskResultType";
	}

	@Reference(target = "(workflow.metrics.index.entity.name=sla-task-result)")
	private WorkflowMetricsIndexNameBuilder
		_slaTaskResultWorkflowMetricsIndexNameBuilder;

}