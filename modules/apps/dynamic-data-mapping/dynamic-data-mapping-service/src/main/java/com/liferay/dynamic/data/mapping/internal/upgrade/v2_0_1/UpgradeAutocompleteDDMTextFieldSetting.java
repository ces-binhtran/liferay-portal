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

package com.liferay.dynamic.data.mapping.internal.upgrade.v2_0_1;

import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeResponse;
import com.liferay.dynamic.data.mapping.io.DDMFormSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;
import java.util.Objects;

/**
 * @author Lino Alves
 */
public class UpgradeAutocompleteDDMTextFieldSetting extends UpgradeProcess {

	public UpgradeAutocompleteDDMTextFieldSetting(
		DDMFormDeserializer ddmFormDeserializer,
		DDMFormSerializer ddmFormSerializer) {

		_ddmFormDeserializer = ddmFormDeserializer;
		_ddmFormSerializer = ddmFormSerializer;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("select DDMStructure.structureId, DDMStructure.definition ");
		sb.append("from DDMFormInstance inner join DDMStructure on ");
		sb.append("DDMFormInstance.structureId = DDMStructure.structureId ");
		sb.append("where DDMStructure.definition like '%\"type\":\"text\"%'");

		try (PreparedStatement ps1 = connection.prepareStatement(sb.toString());
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructure set definition = ? where " +
						"structureId = ?");
			PreparedStatement ps3 = connection.prepareStatement(
				"select structureVersionId, definition from " +
					"DDMStructureVersion where structureId = ?");
			PreparedStatement ps4 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructureVersion set definition = ? where " +
						"structureVersionId = ?")) {

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					String definition = rs.getString(2);

					String newDefinition = upgradeDDMFormInstanceStructure(
						definition);

					if (Objects.equals(definition, newDefinition)) {
						continue;
					}

					ps2.setString(1, newDefinition);

					long structureId = rs.getLong(1);

					ps2.setLong(2, structureId);

					ps2.addBatch();

					ps3.setLong(1, structureId);

					try (ResultSet rs2 = ps3.executeQuery()) {
						while (rs2.next()) {
							definition = rs2.getString("definition");

							newDefinition = upgradeDDMFormInstanceStructure(
								definition);

							if (Objects.equals(definition, newDefinition)) {
								continue;
							}

							ps4.setString(1, newDefinition);

							long structureVersionId = rs2.getLong(
								"structureVersionId");

							ps4.setLong(2, structureVersionId);

							ps4.addBatch();
						}
					}
				}

				ps2.executeBatch();

				ps4.executeBatch();
			}
		}
	}

	protected boolean isAutocompleteEnabled(Map<String, Object> properties) {
		String dataSourceType = (String)properties.get("dataSourceType");

		if (Validator.isNotNull(dataSourceType)) {
			return true;
		}

		return false;
	}

	protected String upgradeDDMFormInstanceStructure(String definition)
		throws Exception {

		DDMFormDeserializerDeserializeRequest.Builder deserializerBuilder =
			DDMFormDeserializerDeserializeRequest.Builder.newBuilder(
				definition);

		DDMFormDeserializerDeserializeResponse
			ddmFormDeserializerDeserializeResponse =
				_ddmFormDeserializer.deserialize(deserializerBuilder.build());

		Exception exception =
			ddmFormDeserializerDeserializeResponse.getException();

		if (exception != null) {
			throw new UpgradeException(exception);
		}

		DDMForm ddmForm = ddmFormDeserializerDeserializeResponse.getDDMForm();

		for (DDMFormField ddmFormField : ddmForm.getDDMFormFields()) {
			if (Objects.equals(ddmFormField.getType(), "text")) {
				Map<String, Object> properties = ddmFormField.getProperties();

				if (!properties.containsKey("autocomplete")) {
					properties.put(
						"autocomplete", isAutocompleteEnabled(properties));
				}
			}
		}

		DDMFormSerializerSerializeRequest.Builder serializerBuilder =
			DDMFormSerializerSerializeRequest.Builder.newBuilder(ddmForm);

		DDMFormSerializerSerializeResponse ddmFormSerializerSerializeResponse =
			_ddmFormSerializer.serialize(serializerBuilder.build());

		return ddmFormSerializerSerializeResponse.getContent();
	}

	private final DDMFormDeserializer _ddmFormDeserializer;
	private final DDMFormSerializer _ddmFormSerializer;

}