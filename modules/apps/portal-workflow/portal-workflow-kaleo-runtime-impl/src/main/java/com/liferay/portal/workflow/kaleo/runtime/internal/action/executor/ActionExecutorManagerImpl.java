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

package com.liferay.portal.workflow.kaleo.runtime.internal.action.executor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorManager;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Leonardo Barros
 */
@Component(service = ActionExecutorManager.class)
public class ActionExecutorManagerImpl implements ActionExecutorManager {

	@Override
	public void executeKaleoAction(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws PortalException {

		String actionExecutorKey = _getActionExecutorKey(
			kaleoAction.getScriptLanguage(),
			StringUtil.trim(kaleoAction.getScript()));

		ActionExecutor actionExecutor = _actionExecutors.get(actionExecutorKey);

		if (actionExecutor == null) {
			throw new PortalException(
				"No action executor for " + actionExecutorKey);
		}

		actionExecutor.execute(kaleoAction, executionContext);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected synchronized void registerActionExecutor(
			ActionExecutor actionExecutor, Map<String, Object> properties)
		throws KaleoDefinitionValidationException {

		Object value = properties.get(
			"com.liferay.portal.workflow.kaleo.runtime.action.executor." +
				"language");

		String[] languages = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		for (String language : languages) {
			_actionExecutors.put(
				_getActionExecutorKey(
					language, ClassUtil.getClassName(actionExecutor)),
				actionExecutor);
		}
	}

	protected synchronized void unregisterActionExecutor(
			ActionExecutor actionExecutor, Map<String, Object> properties)
		throws KaleoDefinitionValidationException {

		Object value = properties.get(
			"com.liferay.portal.workflow.kaleo.runtime.action.executor." +
				"language");

		String[] languages = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		for (String language : languages) {
			_actionExecutors.remove(
				_getActionExecutorKey(
					language, ClassUtil.getClassName(actionExecutor)));
		}
	}

	private String _getActionExecutorKey(
			String language, String actionExecutorClassName)
		throws KaleoDefinitionValidationException {

		ScriptLanguage scriptLanguage = ScriptLanguage.parse(language);

		if (scriptLanguage.equals(ScriptLanguage.JAVA)) {
			return language + StringPool.COLON + actionExecutorClassName;
		}

		return language;
	}

	private final Map<String, ActionExecutor> _actionExecutors =
		new ConcurrentHashMap<>();

}