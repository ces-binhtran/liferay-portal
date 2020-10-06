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

package com.liferay.dispatch.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dispatch.exception.DispatchLogStartDateException;
import com.liferay.dispatch.exception.DispatchLogStatusException;
import com.liferay.dispatch.exception.NoSuchTriggerException;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.service.DispatchLogLocalService;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.dispatch.service.test.util.DispatchLogTestUtil;
import com.liferay.dispatch.service.test.util.DispatchTriggerTestUtil;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Beslic
 */
@DataGuard(scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class DispatchLogLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Test
	public void testAddDispatchLogExceptions() throws Exception {
		Company company = CompanyTestUtil.addCompany();

		User user = UserTestUtil.addUser(company);

		Class<?> exceptionClass = Exception.class;

		DispatchLog dispatchLog = DispatchLogTestUtil.randomDispatchLog(
			user, BackgroundTaskConstants.STATUS_IN_PROGRESS);

		try {
			_dispatchLogLocalService.addDispatchLog(
				user.getUserId(), 444, dispatchLog.getEndDate(),
				dispatchLog.getError(), dispatchLog.getOutput(),
				dispatchLog.getStartDate(), dispatchLog.getStatus());
		}
		catch (Exception exception) {
			exceptionClass = exception.getClass();
		}

		Assert.assertEquals(
			"Add dispatch log with no parent dispatch trigger",
			NoSuchTriggerException.class, exceptionClass);

		DispatchTrigger dispatchTrigger = _addDispatchTrigger(
			DispatchTriggerTestUtil.randomDispatchTrigger(user, 1));

		try {
			Date startDate = dispatchLog.getStartDate();

			_dispatchLogLocalService.addDispatchLog(
				user.getUserId(), dispatchTrigger.getDispatchTriggerId(),
				new Date(startDate.getTime() - 60000), dispatchLog.getError(),
				dispatchLog.getOutput(), startDate, dispatchLog.getStatus());
		}
		catch (Exception exception) {
			exceptionClass = exception.getClass();
		}

		Assert.assertEquals(
			"Add dispatch log with invalid end date",
			DispatchLogStartDateException.class, exceptionClass);

		try {
			_dispatchLogLocalService.addDispatchLog(
				user.getUserId(), dispatchTrigger.getDispatchTriggerId(), null,
				null, null, null, dispatchLog.getStatus());
		}
		catch (Exception exception) {
			exceptionClass = exception.getClass();
		}

		Assert.assertEquals(
			"Add dispatch log with illegal start date",
			DispatchLogStartDateException.class, exceptionClass);

		try {
			_dispatchLogLocalService.addDispatchLog(
				user.getUserId(), dispatchTrigger.getDispatchTriggerId(),
				dispatchLog.getEndDate(), dispatchLog.getError(),
				dispatchLog.getOutput(), dispatchLog.getStartDate(),
				BackgroundTaskConstants.STATUS_QUEUED);
		}
		catch (Exception exception) {
			exceptionClass = exception.getClass();
		}

		Assert.assertEquals(
			"Add dispatch log with invalid status",
			DispatchLogStatusException.class, exceptionClass);
	}

	@Test
	public void testGetDispatchLogs() throws Exception {
		int logsCount = RandomTestUtil.randomInt(10, 40);

		Company company = CompanyTestUtil.addCompany();

		User user = UserTestUtil.addUser(company);

		DispatchTrigger dispatchTrigger = _addDispatchTrigger(
			DispatchTriggerTestUtil.randomDispatchTrigger(user, 1));

		for (int i = 0; i < logsCount; i++) {
			final DispatchLog dispatchLog =
				DispatchLogTestUtil.randomDispatchLog(
					user, BackgroundTaskConstants.STATUS_IN_PROGRESS);

			_dispatchLogLocalService.addDispatchLog(
				user.getUserId(), dispatchTrigger.getDispatchTriggerId(),
				dispatchLog.getEndDate(), dispatchLog.getError(),
				dispatchLog.getOutput(), dispatchLog.getStartDate(),
				dispatchLog.getStatus());
		}

		List<DispatchLog> dispatchLogs =
			_dispatchLogLocalService.getDispatchLogs(
				dispatchTrigger.getDispatchTriggerId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		Assert.assertEquals(
			"Dispatch logs count", logsCount, dispatchLogs.size());
	}

	@Test
	public void testUpdateDispatchLog() throws Exception {
		Company company = CompanyTestUtil.addCompany();

		User user = UserTestUtil.addUser(company);

		DispatchTrigger dispatchTrigger = _addDispatchTrigger(
			DispatchTriggerTestUtil.randomDispatchTrigger(user, 1));

		DispatchLog expectedDispatchLog = DispatchLogTestUtil.randomDispatchLog(
			user, BackgroundTaskConstants.STATUS_FAILED);

		DispatchLog dispatchLog = _dispatchLogLocalService.addDispatchLog(
			user.getUserId(), dispatchTrigger.getDispatchTriggerId(), null,
			null, null, expectedDispatchLog.getStartDate(),
			BackgroundTaskConstants.STATUS_IN_PROGRESS);

		Assert.assertNull(dispatchLog.getEndDate());

		Assert.assertNull(dispatchLog.getError());

		Assert.assertNull(dispatchLog.getOutput());

		Assert.assertEquals(
			BackgroundTaskConstants.STATUS_IN_PROGRESS,
			dispatchLog.getStatus());

		dispatchLog = _dispatchLogLocalService.updateDispatchLog(
			dispatchLog.getDispatchLogId(), expectedDispatchLog.getEndDate(),
			expectedDispatchLog.getError(), expectedDispatchLog.getOutput(),
			expectedDispatchLog.getStatus());

		Assert.assertEquals(
			dispatchTrigger.getDispatchTriggerId(),
			dispatchLog.getDispatchTriggerId());

		_assertEquals(expectedDispatchLog, dispatchLog);
	}

	@Test
	public void testUpdateDispatchLogExceptions() throws Exception {
		Company company = CompanyTestUtil.addCompany();

		User user = UserTestUtil.addUser(company);

		DispatchTrigger dispatchTrigger = _addDispatchTrigger(
			DispatchTriggerTestUtil.randomDispatchTrigger(user, 1));

		DispatchLog expectedDispatchLog = DispatchLogTestUtil.randomDispatchLog(
			user, BackgroundTaskConstants.STATUS_IN_PROGRESS);

		Class<?> exceptionClass = Exception.class;

		DispatchLog dispatchLog = _dispatchLogLocalService.addDispatchLog(
			user.getUserId(), dispatchTrigger.getDispatchTriggerId(), null,
			null, null, expectedDispatchLog.getStartDate(),
			expectedDispatchLog.getStatus());

		try {
			Date startDate = expectedDispatchLog.getStartDate();

			_dispatchLogLocalService.updateDispatchLog(
				dispatchLog.getDispatchLogId(),
				new Date(startDate.getTime() - 60000), null, null,
				BackgroundTaskConstants.STATUS_SUCCESSFUL);
		}
		catch (Exception exception) {
			exceptionClass = exception.getClass();
		}

		Assert.assertEquals(
			"Update dispatch log with illegal end date",
			DispatchLogStartDateException.class, exceptionClass);

		try {
			_dispatchLogLocalService.updateDispatchLog(
				dispatchLog.getDispatchLogId(),
				expectedDispatchLog.getEndDate(), null, null,
				BackgroundTaskConstants.STATUS_QUEUED);
		}
		catch (Exception exception) {
			exceptionClass = exception.getClass();
		}

		Assert.assertEquals(
			"Update dispatch log with illegal status value",
			DispatchLogStatusException.class, exceptionClass);
	}

	private DispatchTrigger _addDispatchTrigger(DispatchTrigger dispatchTrigger)
		throws Exception {

		return _dispatchTriggerLocalService.addDispatchTrigger(
			dispatchTrigger.getUserId(), dispatchTrigger.getName(),
			dispatchTrigger.isSystem(),
			dispatchTrigger.getTaskSettingsUnicodeProperties(),
			dispatchTrigger.getTaskType());
	}

	private void _assertEquals(
		DispatchLog expectedDispatchLog, DispatchLog actualDispatchLog) {

		Assert.assertEquals(
			expectedDispatchLog.getCompanyId(),
			actualDispatchLog.getCompanyId());
		Assert.assertEquals(
			expectedDispatchLog.getUserId(), actualDispatchLog.getUserId());
		Assert.assertEquals(
			expectedDispatchLog.getEndDate(), actualDispatchLog.getEndDate());
		Assert.assertEquals(
			expectedDispatchLog.getError(), actualDispatchLog.getError());
		Assert.assertEquals(
			expectedDispatchLog.getOutput(), actualDispatchLog.getOutput());
		Assert.assertEquals(
			expectedDispatchLog.getStartDate(),
			actualDispatchLog.getStartDate());
		Assert.assertEquals(
			expectedDispatchLog.getStatus(), actualDispatchLog.getStatus());
	}

	@Inject
	private DispatchLogLocalService _dispatchLogLocalService;

	@Inject
	private DispatchTriggerLocalService _dispatchTriggerLocalService;

}