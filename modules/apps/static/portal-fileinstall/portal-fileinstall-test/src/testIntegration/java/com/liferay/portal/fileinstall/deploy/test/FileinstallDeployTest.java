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

package com.liferay.portal.fileinstall.deploy.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PropsValues;

import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Dictionary;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.Version;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationEvent;
import org.osgi.service.cm.ConfigurationListener;

/**
 * @author Matthew Tambara
 */
@RunWith(Arquillian.class)
public class FileinstallDeployTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() {
		Bundle bundle = FrameworkUtil.getBundle(FileinstallDeployTest.class);

		_bundleContext = bundle.getBundleContext();
	}

	@Test
	public void testConfiguration() throws Throwable {
		Path path = Paths.get(
			PropsValues.MODULE_FRAMEWORK_CONFIGS_DIR,
			_CONFIGURATION_PID.concat(".config"));

		try {
			_waitForConfiguration(
				() -> {
					String content = StringBundler.concat(
						_TEST_KEY, StringPool.EQUAL, _TEST_VALUE_1);

					Files.write(path, content.getBytes());
				});

			Configuration configuration = _configurationAdmin.getConfiguration(
				_CONFIGURATION_PID, StringPool.QUESTION);

			Dictionary<String, Object> properties =
				configuration.getProperties();

			Assert.assertEquals(_TEST_VALUE_1, properties.get(_TEST_KEY));

			_waitForConfiguration(
				() -> {
					String content = StringBundler.concat(
						_TEST_KEY, StringPool.EQUAL, _TEST_VALUE_2);

					Files.write(path, content.getBytes());
				});

			configuration = _configurationAdmin.getConfiguration(
				_CONFIGURATION_PID, StringPool.QUESTION);

			properties = configuration.getProperties();

			Assert.assertEquals(_TEST_VALUE_2, properties.get(_TEST_KEY));

			_waitForConfiguration(() -> Files.delete(path));

			configuration = _configurationAdmin.getConfiguration(
				_CONFIGURATION_PID, StringPool.QUESTION);

			Assert.assertNull(configuration.getProperties());
		}
		finally {
			Files.deleteIfExists(path);
		}
	}

	@Test
	public void testDeployAndDelete() throws Exception {
		Path path = Paths.get(
			PropsValues.MODULE_FRAMEWORK_MODULES_DIR, _TEST_JAR_NAME);

		CountDownLatch installCountDownLatch = new CountDownLatch(1);

		CountDownLatch updateCountDownLatch = new CountDownLatch(1);

		CountDownLatch deleteCountDownLatch = new CountDownLatch(1);

		BundleListener bundleListener = new BundleListener() {

			@Override
			public void bundleChanged(BundleEvent bundleEvent) {
				Bundle bundle = bundleEvent.getBundle();

				if (!Objects.equals(
						bundle.getSymbolicName(), _TEST_JAR_SYMBOLIC_NAME)) {

					return;
				}

				int type = bundleEvent.getType();

				if (type == BundleEvent.STARTED) {
					if (installCountDownLatch.getCount() != 1) {
						updateCountDownLatch.countDown();
					}

					installCountDownLatch.countDown();
				}
				else if (type == BundleEvent.UNINSTALLED) {
					deleteCountDownLatch.countDown();
				}
			}

		};

		_bundleContext.addBundleListener(bundleListener);

		Version baseVersion = new Version(1, 0, 0);

		Version updateVersion = new Version(2, 0, 0);

		Bundle bundle = null;

		try {
			_createJAR(
				path, _TEST_JAR_SYMBOLIC_NAME, new Version(1, 0, 0), null);

			installCountDownLatch.await(30, TimeUnit.SECONDS);

			for (Bundle currentBundle : _bundleContext.getBundles()) {
				if (Objects.equals(
						currentBundle.getSymbolicName(),
						_TEST_JAR_SYMBOLIC_NAME)) {

					bundle = currentBundle;

					break;
				}
			}

			Assert.assertNotNull(bundle);

			Assert.assertEquals(Bundle.ACTIVE, bundle.getState());
			Assert.assertEquals(baseVersion, bundle.getVersion());

			_createJAR(path, _TEST_JAR_SYMBOLIC_NAME, updateVersion, null);

			updateCountDownLatch.await();

			Assert.assertEquals(Bundle.ACTIVE, bundle.getState());
			Assert.assertEquals(updateVersion, bundle.getVersion());

			Files.delete(path);

			deleteCountDownLatch.await();

			Assert.assertEquals(Bundle.UNINSTALLED, bundle.getState());
		}
		finally {
			_bundleContext.removeBundleListener(bundleListener);

			Files.deleteIfExists(path);
		}
	}

	private void _createJAR(
			Path path, String symbolicName, Version version,
			String fragmentHost)
		throws IOException {

		try (FileOutputStream fileOutputStream = new FileOutputStream(
				path.toFile())) {

			try (JarOutputStream jarOutputStream = new JarOutputStream(
					fileOutputStream)) {

				Manifest manifest = new Manifest();

				Attributes attributes = manifest.getMainAttributes();

				attributes.putValue(Constants.BUNDLE_MANIFESTVERSION, "2");
				attributes.putValue(
					Constants.BUNDLE_SYMBOLICNAME, symbolicName);
				attributes.putValue(
					Constants.BUNDLE_VERSION, version.toString());

				if (fragmentHost != null) {
					attributes.putValue(Constants.FRAGMENT_HOST, fragmentHost);
				}

				attributes.putValue("Manifest-Version", "2");

				jarOutputStream.putNextEntry(
					new ZipEntry(JarFile.MANIFEST_NAME));

				manifest.write(jarOutputStream);

				jarOutputStream.closeEntry();
			}
		}
	}

	private void _waitForConfiguration(UnsafeRunnable runnable)
		throws Throwable {

		CountDownLatch countDownLatch = new CountDownLatch(1);

		ConfigurationListener configurationListener =
			(ConfigurationEvent configurationEvent) -> {
				if (Objects.equals(
						configurationEvent.getPid(), _CONFIGURATION_PID)) {

					countDownLatch.countDown();
				}
			};

		ServiceRegistration<ConfigurationListener> serviceRegistration =
			_bundleContext.registerService(
				ConfigurationListener.class, configurationListener, null);

		String checkpointPid = FileinstallDeployTest.class.getName();

		CountDownLatch eventCountDownLatch = new CountDownLatch(1);

		ConfigurationListener checkpointConfigurationListener =
			(ConfigurationEvent configurationEvent) -> {
				if (Objects.equals(
						configurationEvent.getPid(), checkpointPid)) {

					eventCountDownLatch.countDown();
				}
			};

		ServiceRegistration<ConfigurationListener>
			checkpointServiceRegistration = _bundleContext.registerService(
				ConfigurationListener.class, checkpointConfigurationListener,
				null);

		try {
			runnable.run();

			countDownLatch.await();

			Configuration configuration = _configurationAdmin.getConfiguration(
				checkpointPid, StringPool.QUESTION);

			configuration.update();

			configuration.delete();

			eventCountDownLatch.await();
		}
		finally {
			checkpointServiceRegistration.unregister();

			serviceRegistration.unregister();
		}
	}

	private static final String _CONFIGURATION_PID =
		"com.liferay.portal.fileinstall.deploy.test." +
			"FileinstallDeployTestConfiguration";

	private static final String _TEST_JAR_NAME;

	private static final String _TEST_JAR_SYMBOLIC_NAME;

	private static final String _TEST_KEY = "testKey";

	private static final String _TEST_VALUE_1 = "testValue1";

	private static final String _TEST_VALUE_2 = "testValue2";

	@Inject
	private static ConfigurationAdmin _configurationAdmin;

	static {
		Package pkg = FileinstallDeployTest.class.getPackage();

		_TEST_JAR_SYMBOLIC_NAME = pkg.getName();

		_TEST_JAR_NAME = _TEST_JAR_SYMBOLIC_NAME.concat(".jar");
	}

	private BundleContext _bundleContext;

}