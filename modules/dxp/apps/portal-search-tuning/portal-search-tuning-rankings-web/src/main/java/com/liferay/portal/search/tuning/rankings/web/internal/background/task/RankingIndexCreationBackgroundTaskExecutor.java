/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.search.tuning.rankings.web.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.tuning.rankings.web.internal.index.importer.SingleIndexToMultipleIndexImporter;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 */
@Component(
	property = "background.task.executor.class.name=com.liferay.portal.search.tuning.rankings.web.internal.background.task.RankingIndexCreationBackgroundTaskExecutor",
	service = {
		BackgroundTaskExecutor.class,
		RankingIndexCreationBackgroundTaskExecutor.class
	}
)
public class RankingIndexCreationBackgroundTaskExecutor
	extends BaseBackgroundTaskExecutor {

	@Override
	public BackgroundTaskExecutor clone() {
		return this;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		if (Objects.equals(
				_searchEngineInformation.getVendorString(), "Solr")) {

			return new BackgroundTaskResult(
				BackgroundTaskConstants.STATUS_CANCELLED);
		}

		_singleIndexToMultipleIndexImporter.importRankings();

		return BackgroundTaskResult.SUCCESS;
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return null;
	}

	@Reference
	private SearchEngineInformation _searchEngineInformation;

	@Reference
	private SingleIndexToMultipleIndexImporter
		_singleIndexToMultipleIndexImporter;

}