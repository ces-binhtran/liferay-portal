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

package com.liferay.lcs.security;

import java.security.KeyStore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Igor Beslic
 */
@RunWith(JUnit4.class)
public class KeyStoreFactoryTest extends BaseTest {

	@Test
	public void testGetInstance() throws Exception {
		KeyStore keyStore = KeyStoreFactory.getInstance(
			"keyStoreCustomPassword.jks", "JCEKS", "keystorepass");

		Assert.assertTrue(keyStore.containsAlias("testalias"));
	}

	@Test
	public void testGetInstanceDefaultPassword() throws Exception {
		KeyStore keyStore = KeyStoreFactory.getInstance(
			"keyStoreDefaultPassword.jks", "JCEKS");

		Assert.assertTrue(keyStore.containsAlias("testalias"));
	}

}