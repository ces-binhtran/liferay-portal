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

package com.liferay.project.templates.mvc.portlet;

import com.liferay.maven.executor.MavenExecutor;
import com.liferay.project.templates.BaseProjectTemplatesTestCase;
import com.liferay.project.templates.extensions.util.Validator;
import com.liferay.project.templates.util.FileTestUtil;

import java.io.File;

import java.net.URI;

import java.util.Arrays;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author Lawrence Lee
 */
@RunWith(Parameterized.class)
public class ProjectTemplatesMVCPortletNameTest
	implements BaseProjectTemplatesTestCase {

	@ClassRule
	public static final MavenExecutor mavenExecutor = new MavenExecutor();

	@Parameterized.Parameters(name = "Testcase-{index}: testing {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
			new Object[][] {
				{"7.0.6-2", "dxp"}, {"7.1.3-1", "dxp"}, {"7.2.1-1", "dxp"},
				{"7.3.7", "portal"}, {"7.4.3.36", "portal"}
			});
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		String gradleDistribution = System.getProperty("gradle.distribution");

		if (Validator.isNull(gradleDistribution)) {
			Properties properties = FileTestUtil.readProperties(
				"gradle-wrapper/gradle/wrapper/gradle-wrapper.properties");

			gradleDistribution = properties.getProperty("distributionUrl");
		}

		Assert.assertTrue(gradleDistribution.contains(GRADLE_WRAPPER_VERSION));

		_gradleDistribution = URI.create(gradleDistribution);
	}

	public ProjectTemplatesMVCPortletNameTest(
		String liferayVersion, String liferayProduct) {

		_liferayVersion = liferayVersion;
		_liferayProduct = liferayProduct;
	}

	@Test
	public void testBuildTemplateMVCPortlet() throws Exception {
		File gradleProjectDir = testBuildTemplatePortlet(
			temporaryFolder, "mvc-portlet", "portlet", "portlet",
			_liferayVersion, _liferayProduct, mavenExecutor,
			_gradleDistribution);

		testContains(
			gradleProjectDir,
			"src/main/java/portlet/constants/PortletPortletKeys.java",
			"public class PortletPortletKeys",
			"public static final String PORTLET",
			"\"portlet_PortletPortlet\";");
		testContains(
			gradleProjectDir,
			"src/main/java/portlet/portlet/PortletPortlet.java",
			"javax.portlet.name=\" + PortletPortletKeys.PORTLET",
			"public class PortletPortlet extends MVCPortlet {");
	}

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static URI _gradleDistribution;

	private final String _liferayProduct;
	private final String _liferayVersion;

}