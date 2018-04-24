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

package com.liferay.commerce.product.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPInstanceConstants;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CPOptionLocalService;
import com.liferay.commerce.product.service.CPOptionValueLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.product.type.simple.constants.SimpleCPTypeConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 *
 * Test CPDefinition in 4 different ways,
 * based on ignoreSKUCombinations and hasDefaultInstance parameters,
 * split in 5 tests.
 */
@RunWith(Arquillian.class)
public class CPDefinitionAddTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	/**
	 * testAddCPDefinition, where both ignoreSKUCombinations
	 * and hasDefaultInstance are set to false
	 * The expected result is: the CPDefinition status is set to Draft
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddCPDefinition() throws Exception {
		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), SimpleCPTypeConstants.NAME, false, false);

		Assert.assertEquals(
			WorkflowConstants.STATUS_DRAFT, cpDefinition.getStatus());
	}

	/**
	 * testAddCPDefinitionWithDefaultInstance, where ignoreSKUCombinations
	 * is set to false and hasDefaultInstance is set to true
	 * The expected result is: the CPDefinition status is set to Draft and
	 * the default CPInstance is set to Inactive
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddCPDefinitionWithDefaultInstance() throws Exception {
		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), SimpleCPTypeConstants.NAME, false, true);

		Assert.assertEquals(
			WorkflowConstants.STATUS_DRAFT, cpDefinition.getStatus());

		List<CPInstance> cpInstances =
			_cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinition.getCPDefinitionId());

		Assert.assertEquals(cpInstances.toString(), 1, cpInstances.size());

		CPInstance cpInstance = cpInstances.get(0);

		Assert.assertEquals(
			WorkflowConstants.STATUS_INACTIVE, cpInstance.getStatus());
	}

	/**
	 * testAddCPDefinitionWithDefaultInstanceAndSKUs, like the one before,
	 * but with some SKUs (CPInstance) associated to the CPDefinition.
	 * The SKUs are created by setting up some CPOptions for the CPDefinition
	 * with the skuContributor set to true
	 * The expected result is: the CPDefinition status is set to Approved and
	 * the default CPInstance is set to Inactive
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddCPDefinitionWithDefaultInstanceAndSKUs()
		throws Exception {

		int numberOfOptions = 2;
		int numberOfValues = 2;

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), SimpleCPTypeConstants.NAME, false, true);

		for (int i = 0; i < numberOfOptions; i++) {
			CPOption cpOption = CPTestUtil.addCPOption(
				_group.getGroupId(), true);

			_createOptionsValues(cpOption.getCPOptionId(), numberOfValues);

			CPTestUtil.addCPDefinitionOptionRel(
				_group.getGroupId(), cpDefinition.getCPDefinitionId(),
				cpOption.getCPOptionId());
		}

		int cpOptionCount = _cpOptionLocalService.getCPOptionsCount(
			_group.getGroupId());

		Assert.assertEquals(numberOfOptions, cpOptionCount);

		int cpDefinitionOptionRelCount =
			_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRelsCount(
				cpDefinition.getCPDefinitionId());

		Assert.assertEquals(numberOfOptions, cpDefinitionOptionRelCount);

		CPTestUtil.buildCPInstances(
			_group.getGroupId(), cpDefinition.getCPDefinitionId());

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, cpDefinition.getStatus());

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpDefinition.getCPDefinitionId(), CPInstanceConstants.DEFAULT_SKU);

		Assert.assertEquals(
			WorkflowConstants.STATUS_INACTIVE, cpInstance.getStatus());
	}

	/**
	 * testAddCPDefinitionWithIgnoreSKUCombinations, where ignoreSKUCombinations
	 * is set to true and hasDefaultInstance is set to false
	 * The expected result is: the CPDefinition status is set to Draft and
	 * there is no CPInstance associated to it
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddCPDefinitionWithIgnoreSKUCombinations()
		throws Exception {

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), SimpleCPTypeConstants.NAME, true, false);

		Assert.assertEquals(
			WorkflowConstants.STATUS_DRAFT, cpDefinition.getStatus());

		int count = _cpInstanceLocalService.getCPDefinitionInstancesCount(
			cpDefinition.getCPDefinitionId(), WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(0, count);
	}

	/**
	 * testAddCPDefinitionWithIgnoreSKUCombinationsAndDefaultInstance,
	 * where both ignoreSKUCombinations and hasDefaultInstance are set to true
	 * The expected result is: the CPDefinition status is set to Approved and
	 * the default CPInstance status is set to Approved
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddCPDefinitionWithIgnoreSKUCombinationsAndDefaultInstance()
		throws Exception {

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), SimpleCPTypeConstants.NAME, true, true);

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, cpDefinition.getStatus());

		List<CPInstance> cpInstances =
			_cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinition.getCPDefinitionId());

		int onlyOneApproved = 0;

		for (CPInstance cpInstance : cpInstances) {
			if (cpInstance.getStatus() == WorkflowConstants.STATUS_APPROVED) {
				onlyOneApproved++;
			}
		}

		Assert.assertEquals(1, onlyOneApproved);
	}

	private void _createOptionsValues(long cpOptionId, int numberOfValues)
		throws Exception {

		for (int i = 0; i < numberOfValues; i++) {
			CPTestUtil.addCPOptionValue(_group.getGroupId(), cpOptionId);
		}
	}

	@Inject
	private static CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Inject
	private static CPDefinitionOptionValueRelLocalService
		_cpDefinitionOptionValueRelLocalService;

	@Inject
	private static CPInstanceLocalService _cpInstanceLocalService;

	@Inject
	private static CPOptionLocalService _cpOptionLocalService;

	@Inject
	private static CPOptionValueLocalService _cpOptionValueLocalService;

	@DeleteAfterTestRun
	private Group _group;

}