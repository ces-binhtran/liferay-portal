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

package com.liferay.lcs.task;

import com.liferay.lcs.management.MBeanServerService;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.lcs.util.LCSConnectionManager;

/**
 * @author Riccardo Ferrari
 */
public interface ServerMetricsTask extends ScheduledTask {

	public boolean isCurrentThreadsMetricsEnabled();

	public boolean isJDBCConnectionPoolsMetricsEnabled();

	public void setKeyGenerator(KeyGenerator keyGenerator);

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager);

	public void setMBeanServerService(MBeanServerService mBeanServerService);

}