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

package com.liferay.portal.search.internal.facet;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.FacetPostProcessor;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;

import java.util.Collection;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
@Component(service = FacetPostProcessor.class)
public class FacetPostProcessorImpl implements FacetPostProcessor {

	@Override
	public void exclude(Collection<Document> documents, Facet facet) {
		FacetCollector facetCollector = facet.getFacetCollector();

		if (facetCollector == null) {
			return;
		}

		FacetDiscounter facetDiscounter = new FacetDiscounter(facet);

		facetDiscounter.discount(documents);
	}

}