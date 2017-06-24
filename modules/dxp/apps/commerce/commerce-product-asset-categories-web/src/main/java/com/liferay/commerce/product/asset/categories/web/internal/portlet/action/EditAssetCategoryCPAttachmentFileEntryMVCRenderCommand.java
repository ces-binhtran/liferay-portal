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

package com.liferay.commerce.product.asset.categories.web.internal.portlet.action;

import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesAdminPortletKeys;
import com.liferay.commerce.product.asset.categories.web.internal.display.context.CategoryCPAttachmentFileEntriesDisplayContext;
import com.liferay.commerce.product.definitions.web.configuration.AttachmentsConfiguration;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.exception.NoSuchCPAttachmentFileEntryException;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	configurationPid = "com.liferay.commerce.product.definitions.web.configuration.AttachmentsConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {
		"javax.portlet.name=" + AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN,
		"mvc.command.name=editAssetCategoryCPAttachmentFileEntry"
	},
	service = MVCRenderCommand.class
)
public class EditAssetCategoryCPAttachmentFileEntryMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(
				"/edit_image.jsp");

		try {
			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(renderRequest);
			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(renderResponse);

			CategoryCPAttachmentFileEntriesDisplayContext
				categoryCPAttachmentFileEntriesDisplayContext =
					new CategoryCPAttachmentFileEntriesDisplayContext(
						_actionHelper, _attachmentsConfiguration,
						_dlMimeTypeDisplayContext, httpServletRequest,
						_itemSelector);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				categoryCPAttachmentFileEntriesDisplayContext);

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchCPAttachmentFileEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/error.jsp";
			}
			else {
				throw new PortletException(e);
			}
		}

		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_attachmentsConfiguration = ConfigurableUtil.createConfigurable(
			AttachmentsConfiguration.class, properties);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.asset.categories.web)"
	)
	protected ServletContext servletContext;

	@Reference
	private ActionHelper _actionHelper;

	private volatile AttachmentsConfiguration _attachmentsConfiguration;

	@Reference
	private DLMimeTypeDisplayContext _dlMimeTypeDisplayContext;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private Portal _portal;

}