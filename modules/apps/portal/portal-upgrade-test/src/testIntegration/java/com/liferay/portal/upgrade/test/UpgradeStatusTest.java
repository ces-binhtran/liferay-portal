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

package com.liferay.portal.upgrade.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.events.StartupHelperUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStatus;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.version.Version;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.tools.DBUpgrader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luis Ortiz
 */
@RunWith(Arquillian.class)
public class UpgradeStatusTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		_originalErrorMessages = ReflectionTestUtil.getFieldValue(
			_upgradeStatus, "_errorMessages");
		_originalSchemaVersionsMap = ReflectionTestUtil.getFieldValue(
			_upgradeStatus, "_schemaVersionsMap");
		_originalUpgradeProcessMessages = ReflectionTestUtil.getFieldValue(
			_upgradeStatus, "_upgradeProcessMessages");
		_originalWarningMessages = ReflectionTestUtil.getFieldValue(
			_upgradeStatus, "_warningMessages");

		_originalStopWatch = ReflectionTestUtil.getFieldValue(
			DBUpgrader.class, "_stopWatch");
	}

	@AfterClass
	public static void tearDownClass() {
		ReflectionTestUtil.setFieldValue(
			DBUpgrader.class, "_stopWatch", _originalStopWatch);
	}

	@Before
	public void setUp() {
		_originalErrorMessages.clear();
		_originalSchemaVersionsMap.clear();
		_originalUpgradeProcessMessages.clear();
		_originalWarningMessages.clear();

		ReflectionTestUtil.setFieldValue(DBUpgrader.class, "_stopWatch", null);
	}

	@Test
	public void testErrorUpgrade() throws Exception {
		StartupHelperUtil.setUpgrading(true);

		ErrorUpgradeProcess upgradeProcess = new ErrorUpgradeProcess();

		upgradeProcess.doUpgrade();

		StartupHelperUtil.setUpgrading(false);

		Assert.assertEquals("failure", _upgradeStatus.getState());

		Assert.assertEquals("no upgrade", _upgradeStatus.getType());
	}

	@Test
	public void testFailureUpgradesPending() {
		List<Release> releases = _releaseLocalService.getReleases(0, 1);

		Release release = releases.get(0);

		String schemaVersion = release.getSchemaVersion();

		try {
			release.setSchemaVersion("0.0.0");

			release = _releaseLocalService.updateRelease(release);

			StartupHelperUtil.setUpgrading(true);

			StartupHelperUtil.setUpgrading(false);
		}
		finally {
			release.setSchemaVersion(schemaVersion);

			_releaseLocalService.updateRelease(release);
		}

		Assert.assertEquals("failure", _upgradeStatus.getState());

		Assert.assertEquals("no upgrade", _upgradeStatus.getType());
	}

	@Test
	public void testMajorUpgrade() {
		_testUpgradeType("major");

		Assert.assertEquals("major", _upgradeStatus.getType());
	}

	@Test
	public void testMicroUpgrade() {
		_testUpgradeType("micro");

		Assert.assertEquals("micro", _upgradeStatus.getType());
	}

	@Test
	public void testMinorUpgrade() {
		_testUpgradeType("minor");

		Assert.assertEquals("minor", _upgradeStatus.getType());
	}

	@Test
	public void testNoUpgrades() {
		StartupHelperUtil.setUpgrading(true);

		StartupHelperUtil.setUpgrading(false);

		Assert.assertEquals("success", _upgradeStatus.getState());

		Assert.assertEquals("no upgrade", _upgradeStatus.getType());
	}

	@Test
	public void testQualifierUpgrade() {
		_testUpgradeType("qualifier");

		Assert.assertEquals("micro", _upgradeStatus.getType());
	}

	@Test
	public void testWarningUpgrade() throws Exception {
		StartupHelperUtil.setUpgrading(true);

		WarningUpgradeProcess upgradeProcess = new WarningUpgradeProcess();

		upgradeProcess.doUpgrade();

		StartupHelperUtil.setUpgrading(false);

		Assert.assertEquals("warning", _upgradeStatus.getState());

		Assert.assertEquals("no upgrade", _upgradeStatus.getType());
	}

	private void _testUpgradeType(String type) {
		List<Release> releases = _releaseLocalService.getReleases(0, 4);

		Release majorRelease = releases.get(0);
		Release microRelease = releases.get(1);
		Release minorRelease = releases.get(2);
		Release qualifierRelease = releases.get(3);

		Version majorSchemaVersion = Version.parseVersion(
			majorRelease.getSchemaVersion());

		Version minorSchemaVersion = Version.parseVersion(
			minorRelease.getSchemaVersion());

		Version microSchemaVersion = Version.parseVersion(
			microRelease.getSchemaVersion());

		String qualifierSchemaVersion = qualifierRelease.getSchemaVersion();

		try {
			qualifierRelease.setSchemaVersion(
				qualifierSchemaVersion + ".step-2");

			qualifierRelease = _releaseLocalService.updateRelease(
				qualifierRelease);

			StartupHelperUtil.setUpgrading(true);

			if (type.equals("major")) {
				majorRelease.setSchemaVersion(
					StringBundler.concat(
						String.valueOf(majorSchemaVersion.getMajor() + 1),
						StringPool.PERIOD,
						String.valueOf(majorSchemaVersion.getMinor()),
						StringPool.PERIOD,
						String.valueOf(majorSchemaVersion.getMicro())));

				majorRelease = _releaseLocalService.updateRelease(majorRelease);
			}

			if (type.equals("minor")) {
				minorRelease.setSchemaVersion(
					StringBundler.concat(
						String.valueOf(minorSchemaVersion.getMajor()),
						StringPool.PERIOD,
						String.valueOf(minorSchemaVersion.getMinor() + 1),
						StringPool.PERIOD,
						String.valueOf(minorSchemaVersion.getMicro())));

				minorRelease = _releaseLocalService.updateRelease(minorRelease);
			}

			if (type.equals("micro")) {
				microRelease.setSchemaVersion(
					StringBundler.concat(
						String.valueOf(microSchemaVersion.getMajor()),
						StringPool.PERIOD,
						String.valueOf(microSchemaVersion.getMinor()),
						StringPool.PERIOD,
						String.valueOf(microSchemaVersion.getMicro() + 1)));

				microRelease = _releaseLocalService.updateRelease(microRelease);
			}

			qualifierRelease.setSchemaVersion(qualifierSchemaVersion);

			_releaseLocalService.updateRelease(qualifierRelease);

			StartupHelperUtil.setUpgrading(false);
		}
		finally {
			majorRelease.setSchemaVersion(majorSchemaVersion.toString());
			microRelease.setSchemaVersion(microSchemaVersion.toString());
			minorRelease.setSchemaVersion(minorSchemaVersion.toString());

			_releaseLocalService.updateRelease(majorRelease);
			_releaseLocalService.updateRelease(microRelease);
			_releaseLocalService.updateRelease(minorRelease);
		}
	}

	private static Map<String, Map<String, Integer>> _originalErrorMessages;
	private static Map<String, Object> _originalSchemaVersionsMap;
	private static StopWatch _originalStopWatch;
	private static Map<String, ArrayList<String>>
		_originalUpgradeProcessMessages;
	private static Map<String, Map<String, Integer>> _originalWarningMessages;

	@Inject
	private static UpgradeStatus _upgradeStatus;

	@Inject
	private ReleaseLocalService _releaseLocalService;

	private class ErrorUpgradeProcess extends UpgradeProcess {

		@Override
		protected void doUpgrade() throws Exception {
			_log.error("Error on upgrade");
		}

		private final Log _log = LogFactoryUtil.getLog(
			ErrorUpgradeProcess.class);

	}

	private class WarningUpgradeProcess extends UpgradeProcess {

		@Override
		protected void doUpgrade() throws Exception {
			if (_log.isWarnEnabled()) {
				_log.warn("Warn on upgrade");
			}
		}

		private final Log _log = LogFactoryUtil.getLog(
			WarningUpgradeProcess.class);

	}

}