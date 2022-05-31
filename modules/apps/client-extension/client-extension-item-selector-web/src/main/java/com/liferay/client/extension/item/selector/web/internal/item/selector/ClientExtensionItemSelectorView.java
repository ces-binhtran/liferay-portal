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

package com.liferay.client.extension.item.selector.web.internal.item.selector;

import com.liferay.client.extension.item.selector.ClientExtensionItemSelectorReturnType;
import com.liferay.client.extension.item.selector.criterion.ClientExtensionItemSelectorCriterion;
import com.liferay.client.extension.type.factory.CETFactory;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(immediate = true, service = ItemSelectorView.class)
public class ClientExtensionItemSelectorView
	implements ItemSelectorView<ClientExtensionItemSelectorCriterion> {

	@Override
	public Class<? extends ClientExtensionItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return ClientExtensionItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "extensions");
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			ClientExtensionItemSelectorCriterion itemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		_itemSelectorViewDescriptorRenderer.renderHTML(
			servletRequest, servletResponse, itemSelectorCriterion, portletURL,
			itemSelectedEventName, true,
			new ClientExtensionItemSelectorViewDescriptor(
				_cetFactory, itemSelectorCriterion,
				(HttpServletRequest)servletRequest, portletURL));
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.singletonList(
			new ClientExtensionItemSelectorReturnType());

	@Reference
	private CETFactory _cetFactory;

	@Reference
	private ItemSelectorViewDescriptorRenderer
		<ClientExtensionItemSelectorCriterion>
			_itemSelectorViewDescriptorRenderer;

}