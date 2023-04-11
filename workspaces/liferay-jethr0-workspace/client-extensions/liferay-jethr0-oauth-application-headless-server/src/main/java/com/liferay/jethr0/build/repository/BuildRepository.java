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

package com.liferay.jethr0.build.repository;

import com.liferay.jethr0.build.Build;
import com.liferay.jethr0.build.dalo.BuildDALO;
import com.liferay.jethr0.build.dalo.BuildToBuildParametersDALO;
import com.liferay.jethr0.dalo.BuildToBuildRunsDALO;
import com.liferay.jethr0.dalo.BuildToTasksDALO;
import com.liferay.jethr0.entity.dalo.EntityDALO;
import com.liferay.jethr0.entity.repository.BaseEntityRepository;
import com.liferay.jethr0.project.Project;
import com.liferay.jethr0.project.dalo.ProjectToBuildsDALO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Hashimoto
 */
@Configuration
public class BuildRepository extends BaseEntityRepository<Build> {

	@Override
	public EntityDALO<Build> getEntityDALO() {
		return _buildDALO;
	}

	@Override
	protected Build updateEntityRelationshipsFromDatabase(Build build) {
		for (Project project : _projectToBuildsDALO.getParentEntities(build)) {
			build.setProject(project);

			project.addBuild(build);
		}

		build.addBuildParameters(
			_buildToBuildParametersDALO.getChildEntities(build));
		build.addBuildRuns(_buildToBuildRunsDALO.retrieveBuildRuns(build));
		build.addTasks(_buildToTasksDALO.retrieveTasks(build));

		return build;
	}

	@Override
	protected Build updateEntityRelationshipsInDatabase(Build build) {
		_buildToBuildParametersDALO.updateChildEntities(build);
		_buildToBuildRunsDALO.updateRelationships(build);
		_buildToTasksDALO.updateRelationships(build);
		_projectToBuildsDALO.updateParentEntities(build);

		return build;
	}

	@Autowired
	private BuildDALO _buildDALO;

	@Autowired
	private BuildToBuildParametersDALO _buildToBuildParametersDALO;

	@Autowired
	private BuildToBuildRunsDALO _buildToBuildRunsDALO;

	@Autowired
	private BuildToTasksDALO _buildToTasksDALO;

	@Autowired
	private ProjectToBuildsDALO _projectToBuildsDALO;

}