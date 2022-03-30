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

package com.liferay.portal.file.install.internal;

import com.liferay.portal.file.install.FileInstaller;

import java.io.File;

import java.net.URI;
import java.net.URL;

/**
 * @author Matthew Tambara
 */
public class DefaultJarInstaller implements FileInstaller {

	@Override
	public boolean canTransformURL(File artifact) {
		String name = artifact.getName();

		if (name.endsWith(".jar")) {
			return true;
		}

		return false;
	}

	@Override
	public URL transformURL(File file) throws Exception {
		URI uri = file.toURI();

		return uri.toURL();
	}

	@Override
	public void uninstall(File file) {
	}

}