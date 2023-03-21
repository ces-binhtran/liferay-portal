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

package com.liferay.jethr0.builds;

import com.liferay.jethr0.builds.parameter.BuildParameter;
import com.liferay.jethr0.project.Project;
import com.liferay.jethr0.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author Michael Hashimoto
 */
public abstract class BaseBuild implements Build {

	@Override
	public void addBuildParameter(BuildParameter buildParameter) {
		addBuildParameters(Arrays.asList(buildParameter));
	}

	@Override
	public void addBuildParameters(List<BuildParameter> buildParameters) {
		buildParameters.removeAll(Collections.singleton(null));

		for (BuildParameter buildParameter : buildParameters) {
			_buildParameters.put(buildParameter.getName(), buildParameter);
		}
	}

	@Override
	public void addTask(Task task) {
		addTasks(Arrays.asList(task));
	}

	@Override
	public void addTasks(List<Task> tasks) {
		for (Task task : tasks) {
			if (_tasks.contains(task)) {
				continue;
			}

			_tasks.add(task);
		}
	}

	@Override
	public String getBuildName() {
		return _buildName;
	}

	@Override
	public BuildParameter getBuildParameter(String name) {
		return _buildParameters.get(name);
	}

	@Override
	public List<BuildParameter> getBuildParameters() {
		return new ArrayList<>(_buildParameters.values());
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public String getJobName() {
		return _jobName;
	}

	@Override
	public JSONObject getJSONObject() {
		State state = getState();

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"buildName", getBuildName()
		).put(
			"id", getId()
		).put(
			"jobName", getJobName()
		).put(
			"state", state.getJSONObject()
		);

		return jsonObject;
	}

	@Override
	public Project getProject() {
		return _project;
	}

	@Override
	public State getState() {
		return _state;
	}

	@Override
	public List<Task> getTasks() {
		return _tasks;
	}

	@Override
	public void removeBuildParameter(BuildParameter buildParameter) {
		_buildParameters.remove(buildParameter.getName());
	}

	@Override
	public void removeBuildParameters(List<BuildParameter> buildParameters) {
		for (BuildParameter buildParameter : buildParameters) {
			removeBuildParameter(buildParameter);
		}
	}

	@Override
	public void removeTask(Task task) {
		_tasks.remove(task);
	}

	@Override
	public void removeTasks(List<Task> tasks) {
		_tasks.removeAll(tasks);
	}

	@Override
	public void setJobName(String jobName) {
		_jobName = jobName;
	}

	@Override
	public void setState(State state) {
		_state = state;
	}

	protected BaseBuild(Project project, JSONObject jsonObject) {
		_project = project;

		_buildName = jsonObject.getString("buildName");
		_id = jsonObject.getLong("id");
		_jobName = jsonObject.getString("jobName");
		_state = State.get(jsonObject.getJSONObject("state"));
	}

	private final String _buildName;
	private final Map<String, BuildParameter> _buildParameters =
		new HashMap<>();
	private final long _id;
	private String _jobName;
	private final Project _project;
	private State _state;
	private final List<Task> _tasks = new ArrayList<>();

}