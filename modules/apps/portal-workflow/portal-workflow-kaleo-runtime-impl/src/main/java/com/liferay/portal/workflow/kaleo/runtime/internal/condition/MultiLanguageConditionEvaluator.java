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

package com.liferay.portal.workflow.kaleo.runtime.internal.condition;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(service = ConditionEvaluator.class)
public class MultiLanguageConditionEvaluator implements ConditionEvaluator {

	@Override
	public String evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws PortalException {

		String conditionEvaluatorKey = _getConditionEvaluatorKey(
			kaleoCondition.getScriptLanguage(),
			StringUtil.trim(kaleoCondition.getScript()));

		ConditionEvaluator conditionEvaluator = _conditionEvaluators.get(
			conditionEvaluatorKey);

		if (conditionEvaluator == null) {
			throw new IllegalArgumentException(
				"No condition evaluator found for script language " +
					conditionEvaluatorKey);
		}

		return conditionEvaluator.evaluate(kaleoCondition, executionContext);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(scripting.language=*)"
	)
	protected void addConditionEvaluator(
			ConditionEvaluator conditionEvaluator,
			Map<String, Object> properties)
		throws KaleoDefinitionValidationException {

		String[] scriptingLanguages = _getScriptingLanguages(
			conditionEvaluator, properties);

		for (String scriptingLanguage : scriptingLanguages) {
			_conditionEvaluators.put(
				_getConditionEvaluatorKey(
					scriptingLanguage,
					ClassUtil.getClassName(conditionEvaluator)),
				conditionEvaluator);
		}
	}

	protected void removeConditionEvaluator(
			ConditionEvaluator conditionEvaluator,
			Map<String, Object> properties)
		throws KaleoDefinitionValidationException {

		String[] scriptingLanguages = _getScriptingLanguages(
			conditionEvaluator, properties);

		for (String scriptingLanguage : scriptingLanguages) {
			_conditionEvaluators.remove(
				_getConditionEvaluatorKey(
					scriptingLanguage,
					ClassUtil.getClassName(conditionEvaluator)));
		}
	}

	private String _getConditionEvaluatorKey(
			String language, String conditionEvaluatorClassName)
		throws KaleoDefinitionValidationException {

		ScriptLanguage scriptLanguage = ScriptLanguage.parse(language);

		if (scriptLanguage.equals(ScriptLanguage.JAVA)) {
			return language + StringPool.COLON + conditionEvaluatorClassName;
		}

		return language;
	}

	private String[] _getScriptingLanguages(
		ConditionEvaluator conditionEvaluator, Map<String, Object> properties) {

		Object value = properties.get("scripting.language");

		String[] scriptingLanguages = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		if (ArrayUtil.isEmpty(scriptingLanguages)) {
			throw new IllegalArgumentException(
				"The property \"scripting.language\" is invalid for " +
					ClassUtil.getClassName(conditionEvaluator));
		}

		return scriptingLanguages;
	}

	private final Map<String, ConditionEvaluator> _conditionEvaluators =
		new HashMap<>();

}