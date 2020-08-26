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

package com.liferay.commerce.machine.learning.internal.forecast.search.index;

import com.liferay.commerce.machine.learning.internal.search.api.CommerceMLIndexer;
import com.liferay.commerce.machine.learning.internal.search.index.CommerceMLSearchEngineHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = CommerceMLIndexer.class)
public class CommerceMLForecastIndexer implements CommerceMLIndexer {

	@Override
	public void createIndex(long companyId) {
		_commerceMLSearchEngineHelper.createIndex(
			getIndexName(companyId), _INDEX_MAPPING_FILE_NAME);
	}

	@Override
	public void dropIndex(long companyId) {
		_commerceMLSearchEngineHelper.dropIndex(getIndexName(companyId));
	}

	@Override
	public String getDocumentType() {
		return "CommerceMLForecastDocumentType";
	}

	@Override
	public String getIndexName(long companyId) {
		return String.format(_INDEX_NAME_PATTERN, companyId);
	}

	private static final String _INDEX_MAPPING_FILE_NAME =
		"/META-INF/search/commerce-ml-forecast-document-type.json";

	private static final String _INDEX_NAME_PATTERN = "commerce-ml-forecast-%s";

	@Reference
	private CommerceMLSearchEngineHelper _commerceMLSearchEngineHelper;

}