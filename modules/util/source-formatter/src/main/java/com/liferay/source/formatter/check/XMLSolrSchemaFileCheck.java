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

package com.liferay.source.formatter.check;

import com.liferay.source.formatter.check.comparator.ElementComparator;
import com.liferay.source.formatter.check.util.SourceUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * @author Hugo Huijser
 */
public class XMLSolrSchemaFileCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws DocumentException {

		if (fileName.endsWith("/schema.xml") && absolutePath.contains("solr")) {
			_checkSolrSchemaXML(fileName, content);
		}

		return content;
	}

	private void _checkSolrSchemaXML(String fileName, String content)
		throws DocumentException {

		Document document = SourceUtil.readXML(content);

		Element rootElement = document.getRootElement();

		checkElementOrder(
			fileName, rootElement.element("fields"), "field", null,
			new ElementComparator());
		checkElementOrder(
			fileName, rootElement.element("types"), "fieldType", null,
			new ElementComparator());
	}

}