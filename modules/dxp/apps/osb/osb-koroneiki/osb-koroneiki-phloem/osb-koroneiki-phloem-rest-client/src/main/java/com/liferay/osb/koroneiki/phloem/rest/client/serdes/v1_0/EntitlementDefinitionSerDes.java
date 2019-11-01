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

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class EntitlementDefinitionSerDes {

	public static EntitlementDefinition toDTO(String json) {
		EntitlementDefinitionJSONParser entitlementDefinitionJSONParser =
			new EntitlementDefinitionJSONParser();

		return entitlementDefinitionJSONParser.parseToDTO(json);
	}

	public static EntitlementDefinition[] toDTOs(String json) {
		EntitlementDefinitionJSONParser entitlementDefinitionJSONParser =
			new EntitlementDefinitionJSONParser();

		return entitlementDefinitionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(EntitlementDefinition entitlementDefinition) {
		if (entitlementDefinition == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (entitlementDefinition.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					entitlementDefinition.getDateCreated()));

			sb.append("\"");
		}

		if (entitlementDefinition.getDateModified() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					entitlementDefinition.getDateModified()));

			sb.append("\"");
		}

		if (entitlementDefinition.getDefinition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"definition\": ");

			sb.append("\"");

			sb.append(_escape(entitlementDefinition.getDefinition()));

			sb.append("\"");
		}

		if (entitlementDefinition.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(entitlementDefinition.getDescription()));

			sb.append("\"");
		}

		if (entitlementDefinition.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(entitlementDefinition.getKey()));

			sb.append("\"");
		}

		if (entitlementDefinition.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(entitlementDefinition.getName()));

			sb.append("\"");
		}

		if (entitlementDefinition.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(entitlementDefinition.getStatus());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EntitlementDefinitionJSONParser entitlementDefinitionJSONParser =
			new EntitlementDefinitionJSONParser();

		return entitlementDefinitionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		EntitlementDefinition entitlementDefinition) {

		if (entitlementDefinition == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		map.put(
			"dateCreated",
			liferayToJSONDateFormat.format(
				entitlementDefinition.getDateCreated()));

		map.put(
			"dateModified",
			liferayToJSONDateFormat.format(
				entitlementDefinition.getDateModified()));

		if (entitlementDefinition.getDefinition() == null) {
			map.put("definition", null);
		}
		else {
			map.put(
				"definition",
				String.valueOf(entitlementDefinition.getDefinition()));
		}

		if (entitlementDefinition.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description",
				String.valueOf(entitlementDefinition.getDescription()));
		}

		if (entitlementDefinition.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(entitlementDefinition.getKey()));
		}

		if (entitlementDefinition.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(entitlementDefinition.getName()));
		}

		if (entitlementDefinition.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put(
				"status", String.valueOf(entitlementDefinition.getStatus()));
		}

		return map;
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		string = string.replace("\\", "\\\\");

		return string.replace("\"", "\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EntitlementDefinitionJSONParser
		extends BaseJSONParser<EntitlementDefinition> {

		@Override
		protected EntitlementDefinition createDTO() {
			return new EntitlementDefinition();
		}

		@Override
		protected EntitlementDefinition[] createDTOArray(int size) {
			return new EntitlementDefinition[size];
		}

		@Override
		protected void setField(
			EntitlementDefinition entitlementDefinition,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setDateModified(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "definition")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setDefinition(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setDescription(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					entitlementDefinition.setStatus(
						EntitlementDefinition.Status.create(
							(String)jsonParserFieldValue));
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}