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

package com.liferay.journal.internal.upgrade.v3_4_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * @author Binh Tran
 */
public class TextAreaContentImageStyleUpgradeProcess extends UpgradeProcess {

	protected String addWidthHeightAttributeForImageTag(String content)
		throws Exception {

		Document contentDocument = SAXReaderUtil.read(content);

		contentDocument = contentDocument.clone();

		XPath xPath = SAXReaderUtil.createXPath(
			"//dynamic-element[@type='text_area']");

		List<Node> textAreaNodes = xPath.selectNodes(contentDocument);

		for (Node textAreaNode : textAreaNodes) {
			Element textAreaElement = (Element)textAreaNode;

			List<Element> dynamicContentElements = textAreaElement.elements(
				"dynamic-content");

			for (Element dynamicContentElement : dynamicContentElements) {
				Object cData = dynamicContentElement.getData();

				org.jsoup.nodes.Document htmlDocument = Jsoup.parseBodyFragment(
					cData.toString());

				Elements imageElements = htmlDocument.getElementsByTag("img");

				for (org.jsoup.nodes.Element imageElement : imageElements) {
					String style = imageElement.attr(_STYLE);

					if ((style == null) || style.equals("")) {
						continue;
					}

					Map<String, String[]> styleMap = _getStyleMap(style);

					String[] width = styleMap.get(_WIDTH);

					if ((width != null) && (width.length > 0)) {
						imageElement.attr(_WIDTH, _getAttributeValue(width[0]));
					}

					String[] height = styleMap.get(_HEIGHT);

					if ((height != null) && (height.length > 0)) {
						imageElement.attr(
							_HEIGHT, _getAttributeValue(height[0]));
					}

					String removedInlineWidthAndHeightStyle =
						_removeInlineWidthAndHeight(styleMap);

					if (removedInlineWidthAndHeightStyle.equals("")) {
						imageElement.removeAttr(_STYLE);
					}
					else {
						imageElement.attr(
							_STYLE, removedInlineWidthAndHeightStyle);
					}
				}

				dynamicContentElement.clearContent();

				org.jsoup.nodes.Element body = htmlDocument.body();

				Elements children = body.children();

				dynamicContentElement.addCDATA(children.toString());
			}
		}

		return contentDocument.formattedString();
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateTextAreaAttribute();
	}

	protected void updateTextAreaAttribute() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select id_, content from JournalArticle where content like " +
					"?")) {

			ps1.setString(1, "%type=\"text_area\"%");

			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {
				long id = rs1.getLong(1);

				String content = rs1.getString(2);

				String newContent = addWidthHeightAttributeForImageTag(content);

				try (PreparedStatement ps2 =
						AutoBatchPreparedStatementUtil.concurrentAutoBatch(
							connection,
							"update JournalArticle set content = ? where id_ " +
								"= ?")) {

					ps2.setString(1, newContent);
					ps2.setLong(2, id);

					ps2.executeUpdate();
				}
			}
		}
	}

	private String _getAttributeValue(String value) {
		return StringUtil.removeSubstring(value, "px");
	}

	private Map<String, String[]> _getStyleMap(String styleStr) {
		Map<String, String[]> keymaps = new HashMap<>();
		String[] list = styleStr.split(":|;");

		for (int i = 0; i < list.length; i += 2) {
			String property = list[i].trim();
			String value = list[i + 1].trim();

			keymaps.put(property, value.split(" "));
		}

		return keymaps;
	}

	private String _removeInlineWidthAndHeight(Map<String, String[]> styleMap) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, String[]> entry : styleMap.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();

			if (key.equals(_WIDTH) || key.equals(_HEIGHT)) {
				continue;
			}

			sb.append(key);
			sb.append(": ");

			for (int i = 0; i < values.length; i++) {
				sb.append(values[i]);

				if (i == (values.length - 1)) {
					sb.append("; ");
				}
				else {
					sb.append(" ");
				}
			}
		}

		return sb.toString();
	}

	private static final String _HEIGHT = "height";

	private static final String _STYLE = "style";

	private static final String _WIDTH = "width";

}