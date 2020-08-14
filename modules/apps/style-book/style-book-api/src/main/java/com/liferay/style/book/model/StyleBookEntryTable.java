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

package com.liferay.style.book.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;StyleBookEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see StyleBookEntry
 * @generated
 */
public class StyleBookEntryTable extends BaseTable<StyleBookEntryTable> {

	public static final StyleBookEntryTable INSTANCE =
		new StyleBookEntryTable();

	public final Column<StyleBookEntryTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<StyleBookEntryTable, Long> headId = createColumn(
		"headId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Boolean> head = createColumn(
		"head", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Long> styleBookEntryId =
		createColumn(
			"styleBookEntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<StyleBookEntryTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Boolean> defaultStyleBookEntry =
		createColumn(
			"defaultStyleBookEntry", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Clob> frontendTokensValues =
		createColumn(
			"frontendTokensValues", Clob.class, Types.CLOB,
			Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, Long> previewFileEntryId =
		createColumn(
			"previewFileEntryId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<StyleBookEntryTable, String> styleBookEntryKey =
		createColumn(
			"styleBookEntryKey", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);

	private StyleBookEntryTable() {
		super("StyleBookEntry", StyleBookEntryTable::new);
	}

}