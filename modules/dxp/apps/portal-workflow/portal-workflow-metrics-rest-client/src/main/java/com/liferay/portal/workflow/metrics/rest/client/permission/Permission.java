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

package com.liferay.portal.workflow.metrics.rest.client.permission;

import com.liferay.portal.workflow.metrics.rest.client.json.BaseJSONParser;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class Permission {

	public String[] getActionIds() {
		return _actionIds;
	}

	public String getRoleName() {
		return _roleName;
	}

	public void setActionIds(String[] actionIds) {
		this._actionIds = actionIds;
	}

	public void setRoleName(String roleName) {
		this._roleName = roleName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (_actionIds != null) {
			sb.append("\"actionIds\": [");

			for (int i = 0; i < _actionIds.length; i++) {
				sb.append("\"");
				sb.append(_actionIds[i]);
				sb.append("\"");

				if ((i + 1) < _actionIds.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (_roleName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleName\": \"");
			sb.append(_roleName);
			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	private String[] _actionIds;
	private String _roleName;

	private static class PermissionJSONParser<T>
		extends BaseJSONParser<Permission> {

		@Override
		protected Permission createDTO() {
			return new Permission();
		}

		@Override
		protected Permission[] createDTOArray(int size) {
			return new Permission[size];
		}

		@Override
		protected void setField(
			Permission permission, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actionIds")) {
				if (jsonParserFieldValue != null) {
					permission.setActionIds((String[])jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleName")) {
				if (jsonParserFieldValue != null) {
					permission.setRoleName((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}