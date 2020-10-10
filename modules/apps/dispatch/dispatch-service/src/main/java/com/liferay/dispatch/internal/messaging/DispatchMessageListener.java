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

package com.liferay.dispatch.internal.messaging;

import com.liferay.dispatch.constants.DispatchConstants;
import com.liferay.dispatch.executor.ScheduledTaskExecutor;
import com.liferay.dispatch.executor.TaskStatus;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.service.DispatchLogLocalServiceUtil;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.Date;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
@Component(
	property = "destination.name=" + DispatchConstants.EXECUTOR_DESTINATION_NAME,
	service = MessageListener.class
)
public class DispatchMessageListener extends BaseMessageListener {

	@Override
	public void doReceive(Message message) throws Exception {
		String payload = (String)message.getPayload();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(payload);

		long dispatchTriggerId = jsonObject.getLong("dispatchTriggerId");

		DispatchTrigger dispatchTrigger =
			_dispatchTriggerLocalService.getDispatchTrigger(dispatchTriggerId);

		if (!dispatchTrigger.isOverlapAllowed()) {
			DispatchLog dispatchLog =
				DispatchLogLocalServiceUtil.fetchLatestDispatchLog(
					dispatchTriggerId);

			if ((dispatchLog != null) &&
				(TaskStatus.valueOf(dispatchLog.getStatus()) ==
					TaskStatus.IN_PROGRESS)) {

				DispatchLogLocalServiceUtil.addDispatchLog(
					dispatchTrigger.getUserId(),
					dispatchTrigger.getDispatchTriggerId(), null, null, null,
					new Date(), TaskStatus.CANCELED);

				return;
			}
		}

		ScheduledTaskExecutor scheduledTaskExecutor =
			_scheduledTaskExecutorServiceTrackerMap.getService(
				dispatchTrigger.getTaskType());

		scheduledTaskExecutor.execute(dispatchTriggerId);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_scheduledTaskExecutorServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, ScheduledTaskExecutor.class,
				"scheduled.task.executor.type");
	}

	@Deactivate
	protected void deactivate() {
		_scheduledTaskExecutorServiceTrackerMap.close();
	}

	@Reference
	private DispatchTriggerLocalService _dispatchTriggerLocalService;

	private ServiceTrackerMap<String, ScheduledTaskExecutor>
		_scheduledTaskExecutorServiceTrackerMap;

}