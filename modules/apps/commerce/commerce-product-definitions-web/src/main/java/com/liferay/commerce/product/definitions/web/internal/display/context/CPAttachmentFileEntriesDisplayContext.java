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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.frontend.ClayCreationMenu;
import com.liferay.commerce.frontend.ClayCreationMenuActionItem;
import com.liferay.commerce.frontend.ClayMenuActionItem;
import com.liferay.commerce.product.configuration.AttachmentsConfiguration;
import com.liferay.commerce.product.ddm.DDMHelper;
import com.liferay.commerce.product.definitions.web.display.context.BaseCPDefinitionsDisplayContext;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPDefinitionScreenNavigationConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPAttachmentFileEntriesDisplayContext
	extends BaseCPDefinitionsDisplayContext {

	public CPAttachmentFileEntriesDisplayContext(
		ActionHelper actionHelper,
		AttachmentsConfiguration attachmentsConfiguration,
		CPAttachmentFileEntryService cpAttachmentFileEntryService,
		CPDefinitionOptionRelService cpDefinitionOptionRelService,
		CPInstanceHelper cpInstanceHelper, DDMHelper ddmHelper,
		DLMimeTypeDisplayContext dlMimeTypeDisplayContext,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector) {

		super(actionHelper, httpServletRequest);

		_attachmentsConfiguration = attachmentsConfiguration;
		_cpAttachmentFileEntryService = cpAttachmentFileEntryService;
		_cpDefinitionOptionRelService = cpDefinitionOptionRelService;
		_cpInstanceHelper = cpInstanceHelper;
		_ddmHelper = ddmHelper;
		_dlMimeTypeDisplayContext = dlMimeTypeDisplayContext;
		_itemSelector = itemSelector;
	}

	public String getAttachmentItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		FileItemSelectorCriterion fileItemSelectorCriterion =
			new FileItemSelectorCriterion();

		fileItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new FileEntryItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "addCPAttachmentFileEntry",
			fileItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public ClayCreationMenu getClayCreationMenu(int type) throws Exception {
		ClayCreationMenu clayCreationMenu = new ClayCreationMenu();

		RenderURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "editCPAttachmentFileEntry");
		portletURL.setParameter(
			"cpDefinitionId", String.valueOf(getCPDefinitionId()));
		portletURL.setParameter("type", String.valueOf(type));

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		clayCreationMenu.addClayCreationMenuActionItem(
			new ClayCreationMenuActionItem(
				portletURL.toString(), _getTypeLabel(type),
				ClayMenuActionItem.CLAY_MENU_ACTION_ITEM_TARGET_SIDE_PANEL));

		return clayCreationMenu;
	}

	public CPAttachmentFileEntry getCPAttachmentFileEntry()
		throws PortalException {

		if (_cpAttachmentFileEntry != null) {
			return _cpAttachmentFileEntry;
		}

		_cpAttachmentFileEntry = actionHelper.getCPAttachmentFileEntry(
			cpRequestHelper.getRenderRequest());

		return _cpAttachmentFileEntry;
	}

	public long getCPAttachmentFileEntryId() throws PortalException {
		CPAttachmentFileEntry cpAttachmentFileEntry =
			getCPAttachmentFileEntry();

		if (cpAttachmentFileEntry == null) {
			return 0;
		}

		return cpAttachmentFileEntry.getCPAttachmentFileEntryId();
	}

	public List<CPDefinitionOptionRel> getCPDefinitionOptionRels()
		throws PortalException {

		CPDefinition cpDefinition = getCPDefinition();

		if (cpDefinition == null) {
			return Collections.emptyList();
		}

		return _cpDefinitionOptionRelService.getCPDefinitionOptionRels(
			cpDefinition.getCPDefinitionId(), true);
	}

	public String getCssClassFileMimeType(FileEntry fileEntry) {
		if (fileEntry == null) {
			return StringPool.BLANK;
		}

		return _dlMimeTypeDisplayContext.getCssClassFileMimeType(
			fileEntry.getMimeType());
	}

	public String getFileEntryName() throws PortalException {
		CPAttachmentFileEntry cpAttachmentFileEntry =
			getCPAttachmentFileEntry();

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		if (fileEntry == null) {
			return StringPool.BLANK;
		}

		return fileEntry.getFileName();
	}

	public String[] getImageExtensions() {
		return _attachmentsConfiguration.imageExtensions();
	}

	public String getImageItemSelectorUrl() {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(
				cpRequestHelper.getRenderRequest());

		ImageItemSelectorCriterion imageItemSelectorCriterion =
			new ImageItemSelectorCriterion();

		imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new FileEntryItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "addCPAttachmentFileEntry",
			imageItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public long getImageMaxSize() {
		return _attachmentsConfiguration.imageMaxSize();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinition");
		portletURL.setParameter(
			"screenNavigationCategoryKey", getScreenNavigationCategoryKey());

		return portletURL;
	}

	@Override
	public String getScreenNavigationCategoryKey() {
		return CPDefinitionScreenNavigationConstants.CATEGORY_KEY_MEDIA;
	}

	public boolean hasCustomAttributes() throws Exception {
		return CustomAttributesUtil.hasCustomAttributes(
			cpRequestHelper.getCompanyId(),
			CPAttachmentFileEntry.class.getName(), getCPAttachmentFileEntryId(),
			null);
	}

	public boolean hasOptions() throws PortalException {
		int skuContributorCPDefinitionOptionRelCount =
			_cpDefinitionOptionRelService.getCPDefinitionOptionRelsCount(
				getCPDefinitionId(), true);

		if (skuContributorCPDefinitionOptionRelCount > 0) {
			return true;
		}

		return false;
	}

	public Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
			parseCPAttachmentFileEntry(long cpAttachmentFileEntryId)
		throws PortalException {

		if (cpAttachmentFileEntryId <= 0) {
			return Collections.emptyMap();
		}

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryService.fetchCPAttachmentFileEntry(
				cpAttachmentFileEntryId);

		if (cpAttachmentFileEntry == null) {
			return Collections.emptyMap();
		}

		return _cpInstanceHelper.getCPDefinitionOptionRelsMap(
			cpAttachmentFileEntry.getClassPK(),
			cpAttachmentFileEntry.getJson());
	}

	public String renderOptions(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		CPAttachmentFileEntry cpAttachmentFileEntry =
			getCPAttachmentFileEntry();

		String json = null;

		if (cpAttachmentFileEntry != null) {
			json = cpAttachmentFileEntry.getJson();
		}

		return _ddmHelper.renderCPAttachmentFileEntryOptions(
			getCPDefinitionId(), json, renderRequest, renderResponse,
			_cpInstanceHelper.getCPDefinitionOptionRelsMap(
				getCPDefinitionId(), true, false));
	}

	private String _getTypeLabel(int type) {
		if (type == CPAttachmentFileEntryConstants.TYPE_IMAGE) {
			return LanguageUtil.get(httpServletRequest, "add-image");
		}
		else if (type == CPAttachmentFileEntryConstants.TYPE_OTHER) {
			return LanguageUtil.get(httpServletRequest, "add-attachment");
		}

		return StringPool.BLANK;
	}

	private final AttachmentsConfiguration _attachmentsConfiguration;
	private CPAttachmentFileEntry _cpAttachmentFileEntry;
	private final CPAttachmentFileEntryService _cpAttachmentFileEntryService;
	private final CPDefinitionOptionRelService _cpDefinitionOptionRelService;
	private final CPInstanceHelper _cpInstanceHelper;
	private final DDMHelper _ddmHelper;
	private final DLMimeTypeDisplayContext _dlMimeTypeDisplayContext;
	private final ItemSelector _itemSelector;

}