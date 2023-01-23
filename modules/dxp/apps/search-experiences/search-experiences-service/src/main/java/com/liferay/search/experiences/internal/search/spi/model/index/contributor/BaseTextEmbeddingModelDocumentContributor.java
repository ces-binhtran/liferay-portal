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

package com.liferay.search.experiences.internal.search.spi.model.index.contributor;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.search.experiences.configuration.SemanticSearchConfiguration;
import com.liferay.search.experiences.rest.dto.v1_0.EmbeddingProviderConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;

import org.osgi.service.component.annotations.Activate;

/**
 * @author Petteri Karttunen
 */
public abstract class BaseTextEmbeddingModelDocumentContributor
	<T extends BaseModel<T>> {

	@Activate
	protected void activate(Map<String, Object> properties) {
		semanticSearchConfiguration = ConfigurableUtil.createConfigurable(
			SemanticSearchConfiguration.class, properties);
	}

	protected void addLocalizedTextEmbeddings(
		T baseModel, BiFunction<String, String, Double[]> biFunction,
		long companyId, Document document) {

		EmbeddingProviderConfiguration embeddingProviderConfiguration =
			_getEmbeddingProviderConfiguration(baseModel);

		if (embeddingProviderConfiguration == null) {
			return;
		}

		List<String> languageIds = Arrays.asList(
			embeddingProviderConfiguration.getLanguageIds());

		for (Locale locale :
				LanguageUtil.getCompanyAvailableLocales(companyId)) {

			String languageId = LocaleUtil.toLanguageId(locale);

			if (!languageIds.contains(languageId)) {
				continue;
			}

			Double[] textEmbedding = _getTextEmbedding(
				biFunction, embeddingProviderConfiguration.getProviderName(),
				getText(baseModel, languageId));

			if (textEmbedding.length == 0) {
				return;
			}

			_addTextEmbeddingField(document, languageId, textEmbedding);
		}
	}

	protected void addTextEmbeddings(
		T baseModel, long companyId, Document document,
		BiFunction<String, String, Double[]> biFunction) {

		EmbeddingProviderConfiguration embeddingProviderConfiguration =
			_getEmbeddingProviderConfiguration(baseModel);

		if (embeddingProviderConfiguration == null) {
			return;
		}

		Double[] textEmbedding = _getTextEmbedding(
			biFunction, embeddingProviderConfiguration.getProviderName(),
			getText(baseModel));

		if (textEmbedding.length == 0) {
			return;
		}

		List<String> languageIds = Arrays.asList(
			embeddingProviderConfiguration.getLanguageIds());

		for (Locale locale :
				LanguageUtil.getCompanyAvailableLocales(companyId)) {

			String languageId = LocaleUtil.toLanguageId(locale);

			if (!languageIds.contains(languageId)) {
				continue;
			}

			_addTextEmbeddingField(document, languageId, textEmbedding);
		}
	}

	protected String getText(T baseModel) {
		return StringPool.BLANK;
	}

	protected String getText(T baseModel, String languageId) {
		return StringPool.BLANK;
	}

	protected volatile SemanticSearchConfiguration semanticSearchConfiguration;

	private void _addTextEmbeddingField(
		Document document, String languageId, Double[] textEmbedding) {

		Field field = new Field(
			_getFieldName(textEmbedding.length, languageId));

		field.setNumeric(true);
		field.setNumericClass(Double.class);
		field.setTokenized(false);
		field.setValues(ArrayUtil.toStringArray(textEmbedding));

		document.add(field);
	}

	private EmbeddingProviderConfiguration _getEmbeddingProviderConfiguration(
		T baseModel) {

		if (!GetterUtil.getBoolean(PropsUtil.get("feature.flag.LPS-163688")) ||
			!semanticSearchConfiguration.textEmbeddingsEnabled() ||
			!_isIndexableStatus(baseModel)) {

			return null;
		}

		Class<?> clazz = baseModel.getClass();

		try {
			for (String configurationJSON :
					semanticSearchConfiguration.
						textEmbeddingProviderConfigurations()) {

				EmbeddingProviderConfiguration embeddingProviderConfiguration =
					EmbeddingProviderConfiguration.unsafeToDTO(
						configurationJSON);

				if (ArrayUtil.contains(
						embeddingProviderConfiguration.getModelClassNames(),
						clazz.getName())) {

					continue;
				}

				if (ArrayUtil.isNotEmpty(
						embeddingProviderConfiguration.getLanguageIds())) {

					return embeddingProviderConfiguration;
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return null;
	}

	private String _getFieldName(int dimensions, String languageId) {
		return StringBundler.concat(
			"text_embedding_", dimensions, StringPool.UNDERLINE, languageId);
	}

	private Double[] _getTextEmbedding(
		BiFunction<String, String, Double[]> function, String providerName,
		String text) {

		try {
			return function.apply(providerName, text);
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return new Double[0];
	}

	private boolean _isIndexableStatus(T baseModel) {
		if (baseModel instanceof WorkflowedModel) {
			WorkflowedModel workflowedModel = (WorkflowedModel)baseModel;

			if (workflowedModel.getStatus() !=
					WorkflowConstants.STATUS_APPROVED) {

				return false;
			}
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseTextEmbeddingModelDocumentContributor.class);

}