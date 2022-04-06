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

package com.liferay.message.boards.change.tracking.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.test.util.BaseTableReferenceDefinitionTestCase;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBDiscussionLocalService;
import com.liferay.message.boards.test.util.MBTestUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.change.tracking.CTModel;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Cheryl Tang
 */
@RunWith(Arquillian.class)
public class MBDiscussionTableReferenceDefinitionTest
	extends BaseTableReferenceDefinitionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_mbMessage = MBTestUtil.addMessage(
			group.getGroupId(), TestPropsValues.getUserId(),
			MBMessage.class.getSimpleName(), MBMessage.class.getName());
	}

	@Override
	protected CTModel<?> addCTModel() throws Exception {
		return _mbDiscussionLocalService.addDiscussion(
			TestPropsValues.getUserId(), group.getGroupId(),
			_classNameLocalService.getClassNameId(User.class),
			TestPropsValues.getUserId(), _mbMessage.getThreadId(),
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject
	private MBDiscussionLocalService _mbDiscussionLocalService;

	private MBMessage _mbMessage;

}