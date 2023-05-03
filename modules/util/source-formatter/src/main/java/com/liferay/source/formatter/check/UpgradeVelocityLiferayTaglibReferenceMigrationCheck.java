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

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.check.constants.VelocityMigrationConstants;
import com.liferay.source.formatter.check.util.SourceUtil;

/**
 * @author Nícolas Moura
 */
public class UpgradeVelocityLiferayTaglibReferenceMigrationCheck
	extends BaseUpgradeVelocityMigrationCheck {

	@Override
	protected String migrateContent(String content) {
		String[] lines = StringUtil.splitLines(content);

		for (int i = 0; i < lines.length; i++) {
			String newLine = lines[i];

			if (newLine.contains(_VELOCITY_LIFERAY_LANGUAGE_FORMAT)) {
				lines[i] = StringUtil.replace(
					newLine,
					new String[] {
						_VELOCITY_LIFERAY_LANGUAGE_FORMAT +
							_VELOCITY_LIFERAY_LANGUAGE_FORMAT_ARGUMENTS,
						_VELOCITY_LIFERAY_LANGUAGE_FORMAT + CharPool.SPACE +
							_VELOCITY_LIFERAY_LANGUAGE_FORMAT_ARGUMENTS
					},
					new String[] {
						_FREEMARKER_LIFERAY_LANGUAGE_FORMAT,
						_FREEMARKER_LIFERAY_LANGUAGE_FORMAT
					});
			}
			else if (newLine.contains(_VELOCITY_LIFERAY_LANGUAGE)) {
				newLine = StringUtil.replace(
					newLine,
					new String[] {
						_VELOCITY_LIFERAY_LANGUAGE + CharPool.OPEN_PARENTHESIS,
						_VELOCITY_LIFERAY_LANGUAGE + CharPool.SPACE +
							CharPool.OPEN_PARENTHESIS
					},
					new String[] {
						_FREEMARKER_LIFERAY_LANGUAGE_KEY,
						_FREEMARKER_LIFERAY_LANGUAGE_KEY
					});

				int languageKeyIndex = newLine.indexOf(
					_FREEMARKER_LIFERAY_LANGUAGE_KEY);

				String endLine = newLine.substring(languageKeyIndex);

				String newEndLine = StringUtil.replaceFirst(
					endLine, CharPool.CLOSE_PARENTHESIS,
					CharPool.SPACE +
						VelocityMigrationConstants.FREEMARKER_TAG_END);

				lines[i] = StringUtil.replace(newLine, endLine, newEndLine);
			}
			else if (newLine.contains(_VELOCITY_LIFERAY_BREADCRUMBS)) {
				lines[i] = StringUtil.replace(
					newLine,
					new String[] {
						_VELOCITY_LIFERAY_BREADCRUMBS +
							CharPool.OPEN_PARENTHESIS +
								CharPool.CLOSE_PARENTHESIS,
						StringBundler.concat(
							_VELOCITY_LIFERAY_BREADCRUMBS, StringPool.SPACE,
							StringPool.OPEN_PARENTHESIS,
							StringPool.CLOSE_PARENTHESIS)
					},
					new String[] {
						_FREEMARKER_LIFERAY_BREADCRUMBS,
						_FREEMARKER_LIFERAY_BREADCRUMBS
					});
			}
			else if (newLine.contains(_VELOCITY_LIFERAY_THEME_INCLUDE)) {
				newLine = StringUtil.replace(
					newLine,
					new String[] {
						_VELOCITY_LIFERAY_THEME_INCLUDE +
							CharPool.OPEN_PARENTHESIS + CharPool.DOLLAR,
						StringBundler.concat(
							_VELOCITY_LIFERAY_THEME_INCLUDE, StringPool.SPACE,
							StringPool.OPEN_PARENTHESIS, StringPool.DOLLAR)
					},
					new String[] {
						_FREEMARKER_LIFERAY_THEME_INCLUDE,
						_FREEMARKER_LIFERAY_THEME_INCLUDE
					});

				lines[i] = StringUtil.replaceLast(
					newLine, CharPool.CLOSE_PARENTHESIS,
					CharPool.SPACE +
						VelocityMigrationConstants.FREEMARKER_TAG_END);
			}
			else if (newLine.contains(_VELOCITY_LIFERAY_THEME_WRAP)) {
				newLine = StringUtil.replace(
					newLine,
					new String[] {
						_VELOCITY_LIFERAY_THEME_WRAP +
							CharPool.OPEN_PARENTHESIS,
						_VELOCITY_LIFERAY_THEME_WRAP + CharPool.SPACE +
							CharPool.OPEN_PARENTHESIS
					},
					new String[] {
						_FREEMARKER_LIFERAY_THEME_WRAP,
						_FREEMARKER_LIFERAY_THEME_WRAP
					});

				int newStartIndex = newLine.indexOf(
					_FREEMARKER_LIFERAY_THEME_WRAP);

				String indent = SourceUtil.getIndent(newLine);

				newLine = StringUtil.replace(newLine, ".vm", ".ftl");
				newLine = StringUtil.replaceFirst(
					newLine, StringPool.CLOSE_PARENTHESIS,
					StringBundler.concat(
						StringPool.SPACE,
						VelocityMigrationConstants.FREEMARKER_TAG_END,
						StringPool.NEW_LINE, indent, "</@>"),
					newStartIndex);
				newLine = StringUtil.replaceFirst(
					newLine, StringPool.COMMA,
					StringBundler.concat(
						StringPool.GREATER_THAN + StringPool.NEW_LINE, indent,
						StringPool.TAB, _FREEMARKER_LIFERAY_THEME_INCLUDE),
					newStartIndex);

				newLine = StringUtil.replace(
					newLine,
					new String[] {
						StringPool.EQUAL + StringPool.DOLLAR,
						StringBundler.concat(
							StringPool.EQUAL, StringPool.SPACE,
							StringPool.DOLLAR)
					},
					new String[] {StringPool.EQUAL, StringPool.EQUAL});

				lines[i] = newLine;
			}
		}

		return com.liferay.petra.string.StringUtil.merge(
			lines, StringPool.NEW_LINE);
	}

	private static final String _FREEMARKER_LIFERAY_BREADCRUMBS =
		"<@liferay.breadcrumbs />";

	private static final String _FREEMARKER_LIFERAY_LANGUAGE_FORMAT =
		"<@liferay.language_format arguments=\"${site_name}\" " +
			"key=\"go-to-x\" />";

	private static final String _FREEMARKER_LIFERAY_LANGUAGE_KEY =
		"<@liferay.language key=";

	private static final String _FREEMARKER_LIFERAY_THEME_INCLUDE =
		"<@liferay_util[\"include\"] page=";

	private static final String _FREEMARKER_LIFERAY_THEME_WRAP =
		"<@liferay_theme[\"wrap-portlet\"] page=";

	private static final String _VELOCITY_LIFERAY_BREADCRUMBS = "#breadcrumbs";

	private static final String _VELOCITY_LIFERAY_LANGUAGE = "#language";

	private static final String _VELOCITY_LIFERAY_LANGUAGE_FORMAT =
		"#language_format";

	private static final String _VELOCITY_LIFERAY_LANGUAGE_FORMAT_ARGUMENTS =
		"(\"go-to-x\", [$site_name])";

	private static final String _VELOCITY_LIFERAY_THEME_INCLUDE =
		"$theme.include";

	private static final String _VELOCITY_LIFERAY_THEME_WRAP =
		"$theme.wrapPortlet";

}