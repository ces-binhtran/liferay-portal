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

package com.liferay.object.dynamic.data.mapping.form.field.type.internal.object.relationship;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.object.dynamic.data.mapping.form.field.type.constants.ObjectDDMFormFieldTypeConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.rest.context.path.RESTContextPathResolver;
import com.liferay.object.rest.context.path.RESTContextPathResolverRegistry;
import com.liferay.object.scope.ObjectScopeProvider;
import com.liferay.object.scope.ObjectScopeProviderRegistry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.system.SystemObjectDefinitionManager;
import com.liferay.object.system.SystemObjectDefinitionManagerRegistry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	property = "ddm.form.field.type.name=" + ObjectDDMFormFieldTypeConstants.OBJECT_RELATIONSHIP,
	service = DDMFormFieldTemplateContextContributor.class
)
public class ObjectRelationshipDDMFormFieldTemplateContextContributor
	implements DDMFormFieldTemplateContextContributor {

	@Override
	public Map<String, Object> getParameters(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		return HashMapBuilder.<String, Object>put(
			"apiURL", _getAPIURL(ddmFormField, ddmFormFieldRenderingContext)
		).put(
			"inputName", ddmFormField.getName()
		).put(
			"labelKey", _getLabelKey(ddmFormField)
		).put(
			"objectDefinitionId",
			GetterUtil.getLong(ddmFormField.getProperty("objectDefinitionId"))
		).put(
			"objectEntryId",
			GetterUtil.getLong(
				ddmFormFieldRenderingContext.getProperty("objectEntryId"))
		).put(
			"parameterObjectFieldName",
			GetterUtil.getString(
				ddmFormField.getProperty("parameterObjectFieldName"))
		).put(
			"placeholder",
			() -> {
				LocalizedValue localizedValue =
					(LocalizedValue)ddmFormField.getProperty("placeholder");

				if (localizedValue == null) {
					return null;
				}

				return GetterUtil.getString(
					localizedValue.getString(
						ddmFormFieldRenderingContext.getLocale()));
			}
		).put(
			"value",
			() -> {
				String value = ddmFormFieldRenderingContext.getValue();

				if (Objects.equals(value, "0")) {
					return StringPool.BLANK;
				}

				return value;
			}
		).put(
			"valueKey", _getValueKey(ddmFormField)
		).build();
	}

	protected String getValue(String valueString) {
		try {
			JSONArray jsonArray = _jsonFactory.createJSONArray(valueString);

			return GetterUtil.getString(jsonArray.get(0));
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException);
			}
		}

		return valueString;
	}

	private String _getAPIURL(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		String apiURL = GetterUtil.getString(
			ddmFormField.getProperty("apiURL"));

		if (Validator.isNotNull(apiURL)) {
			return apiURL;
		}

		apiURL = _portal.getPortalURL(
			ddmFormFieldRenderingContext.getHttpServletRequest());

		ObjectDefinition objectDefinition = _getObjectDefinition(ddmFormField);

		if (objectDefinition == null) {
			return apiURL;
		}

		RESTContextPathResolver restContextPathResolver =
			_restContextPathResolverRegistry.getRESTContextPathResolver(
				objectDefinition.getClassName());

		String restContextPath = restContextPathResolver.getRESTContextPath(
			_getGroupId(ddmFormFieldRenderingContext, objectDefinition));

		return apiURL + _portal.getPathContext() + restContextPath;
	}

	private long _getGroupId(
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext,
		ObjectDefinition objectDefinition) {

		if (StringUtil.startsWith(
				ddmFormFieldRenderingContext.getPortletNamespace(),
				_portal.getPortletNamespace(
					DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM))) {

			return GetterUtil.getLong(
				ddmFormFieldRenderingContext.getProperty("groupId"));
		}

		try {
			ObjectScopeProvider objectScopeProvider =
				_objectScopeProviderRegistry.getObjectScopeProvider(
					objectDefinition.getScope());

			return objectScopeProvider.getGroupId(
				ddmFormFieldRenderingContext.getHttpServletRequest());
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return 0L;
		}
	}

	private String _getLabelKey(DDMFormField ddmFormField) {
		String labelKey = GetterUtil.getString(
			ddmFormField.getProperty("labelKey"));

		if (Validator.isNotNull(labelKey)) {
			return labelKey;
		}

		ObjectDefinition objectDefinition = _getObjectDefinition(ddmFormField);

		if ((objectDefinition != null) &&
			(objectDefinition.getTitleObjectFieldId() > 0)) {

			ObjectField objectField = _objectFieldLocalService.fetchObjectField(
				objectDefinition.getTitleObjectFieldId());

			if (objectField != null) {
				String objectFieldName = objectField.getName();

				objectFieldName = StringUtil.replace(
					objectFieldName, "createDate", "dateCreated");
				objectFieldName = StringUtil.replace(
					objectFieldName, "modifiedDate", "dateModified");

				return objectFieldName;
			}
		}

		return "id";
	}

	private ObjectDefinition _getObjectDefinition(DDMFormField ddmFormField) {
		return _objectDefinitionLocalService.fetchObjectDefinition(
			GetterUtil.getLong(
				getValue(
					GetterUtil.getString(
						ddmFormField.getProperty("objectDefinitionId")))));
	}

	private String _getValueKey(DDMFormField ddmFormField) {
		ObjectDefinition objectDefinition = _getObjectDefinition(ddmFormField);

		SystemObjectDefinitionManager systemObjectDefinitionManager =
			_systemObjectDefinitionManagerRegistry.
				getSystemObjectDefinitionManager(objectDefinition.getName());

		if (systemObjectDefinitionManager == null) {
			return "id";
		}

		return systemObjectDefinitionManager.getRESTDTOIdPropertyName();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectRelationshipDDMFormFieldTemplateContextContributor.class);

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private ObjectScopeProviderRegistry _objectScopeProviderRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private RESTContextPathResolverRegistry _restContextPathResolverRegistry;

	@Reference
	private SystemObjectDefinitionManagerRegistry
		_systemObjectDefinitionManagerRegistry;

}