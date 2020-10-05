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

package com.liferay.frontend.token.definition.internal.frontend.css.variables;

import com.liferay.frontend.css.variables.ScopedCSSVariables;
import com.liferay.frontend.css.variables.ScopedCSSVariablesProvider;
import com.liferay.frontend.token.definition.FrontendTokenDefinition;
import com.liferay.frontend.token.definition.FrontendTokenDefinitionRegistry;
import com.liferay.frontend.token.definition.parsed.FrontendToken;
import com.liferay.frontend.token.definition.parsed.FrontendTokenCategory;
import com.liferay.frontend.token.definition.parsed.FrontendTokenMapping;
import com.liferay.frontend.token.definition.parsed.FrontendTokenSet;
import com.liferay.frontend.token.definition.parsed.ParsedFrontendTokenDefinition;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Chema Balsas
 */
@Component(
	property = "service.ranking:Integer=" + Integer.MAX_VALUE,
	service = ScopedCSSVariablesProvider.class
)
public class DefaultThemeScopedCSSVariablesProvider
	implements ScopedCSSVariablesProvider {

	@Override
	public Collection<ScopedCSSVariables> getScopedCSSVariablesCollection(
		HttpServletRequest httpServletRequest) {

		Map<String, String> cssVariables = new HashMap<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		LayoutSet layoutSet = _layoutSetLocalService.fetchLayoutSet(
			themeDisplay.getSiteGroupId(), false);

		FrontendTokenDefinition frontendTokenDefinition =
			_frontendTokenDefinitionRegistry.getFrontendTokenDefinition(
				layoutSet.getThemeId());

		ParsedFrontendTokenDefinition parsedFrontendTokenDefinition =
			frontendTokenDefinition.getParsedFrontendTokenDefinition(
				themeDisplay.getLocale());

		Collection<FrontendTokenCategory> frontendTokenCategories =
			parsedFrontendTokenDefinition.getFrontendTokenCategories();

		for (FrontendTokenCategory frontendTokenCategory :
				frontendTokenCategories) {

			for (FrontendTokenSet frontendTokenSet :
					frontendTokenCategory.getFrontendTokenSets()) {

				for (FrontendToken frontendToken :
						frontendTokenSet.getFrontendTokens()) {

					for (FrontendTokenMapping frontendTokenMapping :
							frontendToken.getFrontendTokenMappings()) {

						String type = frontendTokenMapping.getType();

						if (!type.equals("cssVariable")) {
							continue;
						}

						cssVariables.put(
							frontendTokenMapping.getValue(),
							frontendToken.getDefaultValue());
					}
				}
			}
		}

		return Collections.singletonList(
			new ScopedCSSVariables() {

				@Override
				public Map<String, String> getCSSVariables() {
					return cssVariables;
				}

				@Override
				public String getScope() {
					return ":root";
				}

			});
	}

	@Reference
	private FrontendTokenDefinitionRegistry _frontendTokenDefinitionRegistry;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

}