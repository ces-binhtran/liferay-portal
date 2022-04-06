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

package com.liferay.batch.planner.rest.internal.resource.v1_0;

import com.liferay.batch.planner.model.BatchPlannerMapping;
import com.liferay.batch.planner.model.BatchPlannerPlan;
import com.liferay.batch.planner.model.BatchPlannerPolicy;
import com.liferay.batch.planner.rest.dto.v1_0.Mapping;
import com.liferay.batch.planner.rest.dto.v1_0.Plan;
import com.liferay.batch.planner.rest.dto.v1_0.Policy;
import com.liferay.batch.planner.rest.resource.v1_0.PlanResource;
import com.liferay.batch.planner.service.BatchPlannerMappingService;
import com.liferay.batch.planner.service.BatchPlannerPlanService;
import com.liferay.batch.planner.service.BatchPlannerPolicyService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Matija Petanjek
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/plan.properties",
	scope = ServiceScope.PROTOTYPE, service = PlanResource.class
)
public class PlanResourceImpl extends BasePlanResourceImpl {

	@Override
	public void deletePlan(Long id) throws Exception {
		_batchPlannerPlanService.deleteBatchPlannerPlan(id);
	}

	@Override
	public Plan getPlan(Long id) throws Exception {
		return _toPlan(_batchPlannerPlanService.getBatchPlannerPlan(id));
	}

	@Override
	public Page<Plan> getPlansPage(Pagination pagination) throws Exception {
		return Page.of(
			transform(
				_batchPlannerPlanService.getBatchPlannerPlans(
					contextCompany.getCompanyId(),
					pagination.getStartPosition(), pagination.getEndPosition()),
				this::_toPlan),
			pagination,
			_batchPlannerPlanService.getBatchPlannerPlansCount(
				contextCompany.getCompanyId()));
	}

	@Override
	public Plan patchPlan(Long id, Plan plan) throws Exception {
		BatchPlannerPlan batchPlannerPlan =
			_batchPlannerPlanService.updateBatchPlannerPlan(
				id, plan.getExternalType(), plan.getInternalClassName(),
				plan.getName());

		Mapping[] mappings = plan.getMappings();

		if (mappings != null) {
			for (Mapping mapping : plan.getMappings()) {
				_batchPlannerMappingService.updateBatchPlannerMapping(
					mapping.getId(), mapping.getExternalFieldName(),
					mapping.getExternalFieldType(), mapping.getScript());
			}
		}

		Policy[] policies = plan.getPolicies();

		if (policies != null) {
			for (Policy policy : plan.getPolicies()) {
				_batchPlannerPolicyService.updateBatchPlannerPolicy(
					id, policy.getName(), policy.getValue());
			}
		}

		return _toPlan(batchPlannerPlan);
	}

	@Override
	public Plan postPlan(Plan plan) throws Exception {
		BatchPlannerPlan batchPlannerPlan =
			_batchPlannerPlanService.addBatchPlannerPlan(
				plan.getExport(), plan.getExternalType(), plan.getExternalURL(),
				plan.getInternalClassName(), plan.getName(),
				plan.getTaskItemDelegateName(), plan.getTemplate());

		Mapping[] mappings = plan.getMappings();

		if (mappings != null) {
			for (Mapping mapping : mappings) {
				_batchPlannerMappingService.addBatchPlannerMapping(
					batchPlannerPlan.getBatchPlannerPlanId(),
					mapping.getExternalFieldName(),
					mapping.getExternalFieldType(),
					mapping.getInternalFieldName(),
					mapping.getInternalFieldType(), mapping.getScript());
			}
		}

		Policy[] policies = plan.getPolicies();

		if (policies != null) {
			for (Policy policy : plan.getPolicies()) {
				_batchPlannerPolicyService.addBatchPlannerPolicy(
					batchPlannerPlan.getBatchPlannerPlanId(), policy.getName(),
					policy.getValue());
			}
		}

		return _toPlan(batchPlannerPlan);
	}

	private Mapping _toMapping(BatchPlannerMapping batchPlannerMapping) {
		return new Mapping() {
			{
				externalFieldName = batchPlannerMapping.getExternalFieldName();
				externalFieldType = batchPlannerMapping.getExternalFieldType();
				id = batchPlannerMapping.getBatchPlannerMappingId();
				internalFieldName = batchPlannerMapping.getInternalFieldName();
				internalFieldType = batchPlannerMapping.getInternalFieldType();
				planId = batchPlannerMapping.getBatchPlannerPlanId();
				script = batchPlannerMapping.getScript();
			}
		};
	}

	private Plan _toPlan(BatchPlannerPlan batchPlannerPlan) throws Exception {
		return new Plan() {
			{
				active = batchPlannerPlan.isActive();
				export = batchPlannerPlan.isExport();
				externalType = batchPlannerPlan.getExternalType();
				externalURL = batchPlannerPlan.getExternalURL();
				id = batchPlannerPlan.getBatchPlannerPlanId();
				internalClassName = batchPlannerPlan.getInternalClassName();
				mappings = TransformUtil.transformToArray(
					_batchPlannerMappingService.getBatchPlannerMappings(
						batchPlannerPlan.getBatchPlannerPlanId()),
					batchPlannerMapping -> _toMapping(batchPlannerMapping),
					Mapping.class);
				name = batchPlannerPlan.getName();
				policies = TransformUtil.transformToArray(
					_batchPlannerPolicyService.getBatchPlannerPolicies(
						batchPlannerPlan.getBatchPlannerPlanId()),
					batchPlannerPolicy -> _toPolicy(batchPlannerPolicy),
					Policy.class);
				taskItemDelegateName =
					batchPlannerPlan.getTaskItemDelegateName();
				template = batchPlannerPlan.isTemplate();
			}
		};
	}

	private Policy _toPolicy(BatchPlannerPolicy batchPlannerPolicy) {
		return new Policy() {
			{
				id = batchPlannerPolicy.getBatchPlannerPolicyId();
				name = batchPlannerPolicy.getName();
				planId = batchPlannerPolicy.getBatchPlannerPlanId();
				value = batchPlannerPolicy.getValue();
			}
		};
	}

	@Reference
	private BatchPlannerMappingService _batchPlannerMappingService;

	@Reference
	private BatchPlannerPlanService _batchPlannerPlanService;

	@Reference
	private BatchPlannerPolicyService _batchPlannerPolicyService;

}