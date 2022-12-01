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

package com.liferay.client.extension.upgrade.v3_0_1.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.client.extension.constants.ClientExtensionEntryConstants;
import com.liferay.client.extension.service.ClientExtensionEntryLocalService;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUID;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import java.util.Collections;
import java.util.HashMap;

import javax.portlet.Portlet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Binh Tran
 */
@RunWith(Arquillian.class)
public class UpgradePortletIdTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		Bundle bundle = FrameworkUtil.getBundle(UpgradePortletIdTest.class);

		_bundleContext = bundle.getBundleContext();
	}

	@Before
	public void setUp() throws Exception {
		_clientExtensionEntryErc = _portalUUID.generate() + "-TEST";

		UserTestUtil.setUser(TestPropsValues.getUser());

		_group = GroupTestUtil.addGroup();

		_layout = LayoutTestUtil.addTypePortletLayout(_group.getGroupId());

		String languageId = UpgradeProcessUtil.getDefaultLanguageId(
			_layout.getCompanyId());

		_clientExtensionEntryLocalService.addClientExtensionEntry(
			_clientExtensionEntryErc, TestPropsValues.getUserId(),
			StringPool.BLANK,
			Collections.singletonMap(
				LocaleUtil.fromLanguageId(languageId),
				RandomTestUtil.randomString()),
			StringPool.BLANK, StringPool.BLANK,
			ClientExtensionEntryConstants.TYPE_CUSTOM_ELEMENT,
			UnicodePropertiesBuilder.create(
				true
			).put(
				"htmlElementName", "valid-html-element-name"
			).put(
				"instanceable", false
			).put(
				"urls", "http://" + RandomTestUtil.randomString() + ".com"
			).buildString());

		_upgradeStepRegistrator.register(
			(fromSchemaVersionString, toSchemaVersionString, upgradeSteps) -> {
				for (UpgradeStep upgradeStep : upgradeSteps) {
					Class<?> clazz = upgradeStep.getClass();

					String className = clazz.getName();

					if (className.contains(
							"com.liferay.client.extension.web.internal." +
								"upgrade.v3_0_1.UpgradePortletId")) {

						_upgradeProcess = (UpgradeProcess)upgradeStep;
					}
				}
			});
	}

	@After
	public void tearDown() throws Exception {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Test
	public void testUpdateClientExtensionEntryPortletIdInPortletReference()
		throws Exception {

		String portletIdWithoutCompanyId =
			_PORTLET_ID_PREFIX +
				_replaceNonwordCharacterToUnderline(_clientExtensionEntryErc);

		_registerPortlet(portletIdWithoutCompanyId);

		LayoutTestUtil.addPortletToLayout(
			TestPropsValues.getUserId(), _layout, portletIdWithoutCompanyId,
			"column-1", new HashMap<>());

		PortletPreferences portletPreferencesBeforeUpgrade =
			_portletPreferencesLocalService.fetchPortletPreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				portletIdWithoutCompanyId);

		Assert.assertNotNull(portletPreferencesBeforeUpgrade);

		Assert.assertEquals(
			"Assert the portletPreferences portletId before upgrade",
			portletIdWithoutCompanyId,
			portletPreferencesBeforeUpgrade.getPortletId());

		_upgradeProcess.upgrade();

		String newPortletIdWithCompanyId = StringBundler.concat(
			_PORTLET_ID_PREFIX, _layout.getCompanyId(), StringPool.UNDERLINE,
			_replaceNonwordCharacterToUnderline(_clientExtensionEntryErc));

		_portletPreferencesPersistence.clearCache();

		PortletPreferences portletPreferencesWithOldId =
			_portletPreferencesLocalService.fetchPortletPreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				portletIdWithoutCompanyId);

		PortletPreferences portletPreferencesWithNewId =
			_portletPreferencesLocalService.fetchPortletPreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _layout.getPlid(),
				newPortletIdWithCompanyId);

		Assert.assertNotNull(portletPreferencesWithNewId);

		Assert.assertNull(portletPreferencesWithOldId);

		Assert.assertEquals(
			"Assert the portletPreferences portletId after upgrade",
			newPortletIdWithCompanyId,
			portletPreferencesWithNewId.getPortletId());
	}

	private void _registerPortlet(String portletName) {
		_serviceRegistration = _bundleContext.registerService(
			Portlet.class, new MVCPortlet(),
			HashMapDictionaryBuilder.<String, Object>put(
				"com.liferay.portlet.preferences-company-wide", "false"
			).put(
				"com.liferay.portlet.preferences-owned-by-group", false
			).put(
				"com.liferay.portlet.preferences-unique-per-layout", true
			).put(
				"javax.portlet.name", portletName
			).build());
	}

	private String _replaceNonwordCharacterToUnderline(
		String externalReferenceCode) {

		if (Validator.isNotNull(externalReferenceCode)) {
			return externalReferenceCode.replaceAll(
				"\\W", StringPool.UNDERLINE);
		}

		return externalReferenceCode;
	}

	private static final String _PORTLET_ID_PREFIX =
		"com_liferay_client_extension_web_internal_portlet_" +
			"ClientExtensionEntryPortlet_";

	private static BundleContext _bundleContext;

	private String _clientExtensionEntryErc;

	@Inject
	private ClientExtensionEntryLocalService _clientExtensionEntryLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private Layout _layout;

	@Inject
	private PortalUUID _portalUUID;

	@Inject
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Inject
	private PortletPreferencesPersistence _portletPreferencesPersistence;

	private ServiceRegistration<Portlet> _serviceRegistration;
	private UpgradeProcess _upgradeProcess;

	@Inject(
		filter = "component.name=com.liferay.client.extension.web.internal.upgrade.registry.ClientExtensionWebUpgradeStepRegistrator"
	)
	private UpgradeStepRegistrator _upgradeStepRegistrator;

}