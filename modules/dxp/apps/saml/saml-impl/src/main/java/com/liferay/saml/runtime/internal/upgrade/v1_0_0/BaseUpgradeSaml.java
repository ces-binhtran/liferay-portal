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

package com.liferay.saml.runtime.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.saml.runtime.internal.constants.LegacySamlPropsKeys;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Stian Sigvartsen
 * @author Tomas Polesovsky
 */
public abstract class BaseUpgradeSaml extends UpgradeProcess {

	protected String getDefaultValue(String key) {
		return _defaultValues.get(key);
	}

	protected String getPropsValue(Props props, String key, Filter filter) {
		String value = null;

		if ((filter != null) &&
			ArrayUtil.contains(LegacySamlPropsKeys.SAML_KEYS_FILTERED, key)) {

			value = props.get(key, filter);
		}

		if (value == null) {
			value = props.get(key);
		}

		return value;
	}

	private static final Map<String, String> _defaultValues = new HashMap<>();

	static {
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_IDP_ASSERTION_LIFETIME, "1800");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE,
			"emailAddress");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT,
			"urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_IDP_SSO_SESSION_CHECK_INTERVAL, "60");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_IDP_SSO_SESSION_MAX_AGE, "86400000");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_KEYSTORE_MANAGER_IMPL,
			"com.liferay.saml.credential.FileSystemKeyStoreManagerImpl");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_KEYSTORE_PASSWORD, "liferay");
		_defaultValues.put(LegacySamlPropsKeys.SAML_KEYSTORE_TYPE, "jks");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_METADATA_MAX_REFRESH_DELAY, "14400000");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_METADATA_MIN_REFRESH_DELAY, "300000");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_METADATA_REFRESH_INTERVAL, "30");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_REPLAY_CACHE_DURATION, "3600000");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_SP_AUTH_REQUEST_CHECK_INTERVAL, "60");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_SP_AUTH_REQUEST_MAX_AGE, "86400000");
		_defaultValues.put(LegacySamlPropsKeys.SAML_SP_CLOCK_SKEW, "3000");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_SP_MESSAGE_CHECK_INTERVAL, "60");
		_defaultValues.put(
			LegacySamlPropsKeys.SAML_SP_NAME_ID_FORMAT,
			"urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress");
	}

}