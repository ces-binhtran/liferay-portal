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

package com.liferay.journal.uad.exporter;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleWrapper;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.user.associated.data.exporter.UADExporter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Balázs Sáfrány-Kovalik
 */
@Component(immediate = true, service = UADExporter.class)
public class JournalArticleUADExporter extends BaseJournalArticleUADExporter {

	@Override
	protected String toXmlString(JournalArticle journalArticle) {
		return super.toXmlString(
			new JournalArticleWrapper(journalArticle) {

				@Override
				public String getContent() {
					return StringUtil.replace(
						super.getContent(), "]]><", "]]]]><![CDATA[><");
				}

			});
	}

}