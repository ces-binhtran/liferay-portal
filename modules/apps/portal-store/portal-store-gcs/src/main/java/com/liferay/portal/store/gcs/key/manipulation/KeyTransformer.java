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

package com.liferay.portal.store.gcs.key.manipulation;

/**
 * @author Shanon Mathai
 */
public interface KeyTransformer {

	public String getDirectoryKey(
		long companyId, long repositoryId, String folderName);

	public String getFileKey(
		long companyId, long repositoryId, String fileName);

	public String getFileVersionKey(
		long companyId, long repositoryId, String fileName,
		String versionLabel);

	public String getRepositoryKey(long companyId, long repositoryId);

}