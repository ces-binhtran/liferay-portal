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

package com.liferay.commerce.product.content.web.display.context;

import com.liferay.commerce.product.constants.CPContentContributorConstants;
import com.liferay.commerce.product.constants.CPOptionCategoryConstants;
import com.liferay.commerce.product.content.web.configuration.CPContentConfigurationHelper;
import com.liferay.commerce.product.content.web.configuration.CPContentPortletInstanceConfiguration;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.service.CPAttachmentFileEntryService;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.commerce.product.util.CPContentContributorRegistry;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPTypeDisplayContext {

	public CPTypeDisplayContext(
			CPAttachmentFileEntryService cpAttachmentFileEntryService,
			CPContentConfigurationHelper cpContentConfigurationHelper,
			CPContentContributorRegistry cpContentContributorRegistry,
			CPDefinition cpDefinition, CPInstanceHelper cpInstanceHelper,
			CPDefinitionSpecificationOptionValueService
				cpDefinitionSpecificationOptionValueService,
			CPOptionCategoryService cpOptionCategoryService,
			HttpServletRequest httpServletRequest, Portal portal)
		throws Exception {

		this.cpAttachmentFileEntryService = cpAttachmentFileEntryService;
		this.cpContentConfigurationHelper = cpContentConfigurationHelper;
		this.cpContentContributorRegistry = cpContentContributorRegistry;
		this.cpDefinition = cpDefinition;
		this.cpInstanceHelper = cpInstanceHelper;
		this.cpDefinitionSpecificationOptionValueService =
			cpDefinitionSpecificationOptionValueService;
		this.cpOptionCategoryService = cpOptionCategoryService;
		this.httpServletRequest = httpServletRequest;
		this.portal = portal;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		CPRequestHelper cpRequestHelper = new CPRequestHelper(
			httpServletRequest);

		liferayPortletResponse = cpRequestHelper.getLiferayPortletResponse();

		cpContentContributorRegistry.contribute(
			getDefaultCPInstance(), cpRequestHelper.getRenderRequest());

		cpContentPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CPContentPortletInstanceConfiguration.class);
	}

	public String getAvailabilityLabel() {
		String availability = (String)getCPContentContributorValue(
			CPContentContributorConstants.AVAILABILITY_NAME);

		if (Validator.isNull(availability)) {
			return StringPool.BLANK;
		}

		return LanguageUtil.format(
			httpServletRequest, "availability-x", availability, true);
	}

	public String getAvailabilityRangeLabel() {
		String availabilityRange = (String)getCPContentContributorValue(
			CPContentContributorConstants.AVAILABILITY_RANGE_NAME);

		if (Validator.isNull(availabilityRange)) {
			return StringPool.BLANK;
		}

		return LanguageUtil.format(
			httpServletRequest, "product-will-be-available-in-x",
			availabilityRange);
	}

	public List<CPDefinitionSpecificationOptionValue>
			getCategorizedCPDefinitionSpecificationOptionValues(
				long cpOptionCategoryId)
		throws PortalException {

		return cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValues(
				cpDefinition.getCPDefinitionId(), cpOptionCategoryId);
	}

	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries()
		throws PortalException {

		long classNameId = portal.getClassNameId(CPDefinition.class);

		int total =
			cpAttachmentFileEntryService.getCPAttachmentFileEntriesCount(
				classNameId, cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_OTHER,
				WorkflowConstants.STATUS_APPROVED);

		return cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, cpDefinition.getCPDefinitionId(),
			CPAttachmentFileEntryConstants.TYPE_OTHER,
			WorkflowConstants.STATUS_APPROVED, 0, total);
	}

	public CPDefinition getCPDefinition() {
		return cpDefinition;
	}

	public long getCPDefinitionId() {
		return cpDefinition.getCPDefinitionId();
	}

	public List<CPDefinitionSpecificationOptionValue>
			getCPDefinitionSpecificationOptionValues()
		throws PortalException {

		return cpDefinitionSpecificationOptionValueService.
			getCPDefinitionSpecificationOptionValues(
				cpDefinition.getCPDefinitionId(),
				CPOptionCategoryConstants.DEFAULT_CP_OPTION_CATEGORY_ID);
	}

	public List<CPOptionCategory> getCPOptionCategories() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return cpOptionCategoryService.getCPOptionCategories(
			themeDisplay.getScopeGroupId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public CPInstance getDefaultCPInstance() throws Exception {
		CPDefinition cpDefinition = getCPDefinition();

		if (cpDefinition == null) {
			return null;
		}

		if (!cpDefinition.isIgnoreSKUCombinations()) {
			return null;
		}

		return cpInstanceHelper.getCPInstance(
			cpDefinition.getCPDefinitionId(), null);
	}

	public CPAttachmentFileEntry getDefaultImage() throws PortalException {
		long classNameId = portal.getClassNameId(CPDefinition.class);

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			cpAttachmentFileEntryService.getCPAttachmentFileEntries(
				classNameId, cpDefinition.getCPDefinitionId(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE,
				WorkflowConstants.STATUS_APPROVED, 0, 1);

		if (cpAttachmentFileEntries.isEmpty()) {
			return null;
		}

		return cpAttachmentFileEntries.get(0);
	}

	public String getDisplayStyle() {
		return cpContentConfigurationHelper.getCPTypeDisplayStyle(
			cpContentPortletInstanceConfiguration,
			cpDefinition.getProductTypeName());
	}

	public long getDisplayStyleGroupId() {
		return cpContentConfigurationHelper.getCPTypeDisplayStyleGroupId(
			cpContentPortletInstanceConfiguration,
			cpDefinition.getProductTypeName());
	}

	public String getDownloadFileEntryURL(FileEntry fileEntry)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String downloadUrl = DLUtil.getDownloadURL(
			fileEntry, fileEntry.getLatestFileVersion(), themeDisplay,
			StringPool.BLANK, true, true);

		return downloadUrl;
	}

	public List<CPAttachmentFileEntry> getImages() throws PortalException {
		long classNameId = portal.getClassNameId(CPDefinition.class);

		return cpAttachmentFileEntryService.getCPAttachmentFileEntries(
			classNameId, cpDefinition.getCPDefinitionId(),
			CPAttachmentFileEntryConstants.TYPE_IMAGE,
			WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public String getImageURL(FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws Exception {

		return DLUtil.getDownloadURL(
			fileEntry, fileEntry.getFileVersion(), themeDisplay,
			StringPool.BLANK);
	}

	public String getLabel(Locale locale, String key) {
		return LanguageUtil.get(locale, key);
	}

	public String getStockQuantityLabel() {
		int stockQuantity = (int)getCPContentContributorValue(
			CPContentContributorConstants.STOCK_QUANTITY_NAME);

		if (stockQuantity <= 0) {
			return StringPool.BLANK;
		}

		return LanguageUtil.format(
			httpServletRequest, "stock-quantity-x", stockQuantity);
	}

	public ResourceURL getViewAttachmentURL() {
		ResourceURL resourceURL = liferayPortletResponse.createResourceURL();

		CPDefinition cpDefinition = getCPDefinition();

		if (cpDefinition != null) {
			resourceURL.setParameter(
				"cpDefinitionId", String.valueOf(getCPDefinitionId()));
		}

		resourceURL.setResourceID("viewCPAttachments");

		return resourceURL;
	}

	public boolean hasCPDefinitionSpecificationOptionValues()
		throws PortalException {

		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues =
				cpDefinitionSpecificationOptionValueService.
					getCPDefinitionSpecificationOptionValues(
						cpDefinition.getCPDefinitionId());

		return !cpDefinitionSpecificationOptionValues.isEmpty();
	}

	public String renderOptions(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		CPDefinition cpDefinition = getCPDefinition();

		if (cpDefinition == null) {
			return StringPool.BLANK;
		}

		return cpInstanceHelper.renderPublicStoreOptions(
			cpDefinition.getCPDefinitionId(), null,
			cpDefinition.getIgnoreSKUCombinations(), false, renderRequest,
			renderResponse);
	}

	protected Object getCPContentContributorValue(String contributorKey) {
		String key = CPContentContributorConstants.PREFIX + contributorKey;

		Object value = httpServletRequest.getAttribute(key);

		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		return value;
	}

	protected final CPAttachmentFileEntryService cpAttachmentFileEntryService;
	protected final CPContentConfigurationHelper cpContentConfigurationHelper;
	protected final CPContentContributorRegistry cpContentContributorRegistry;
	protected final CPContentPortletInstanceConfiguration
		cpContentPortletInstanceConfiguration;
	protected final CPDefinition cpDefinition;
	protected final CPDefinitionSpecificationOptionValueService
		cpDefinitionSpecificationOptionValueService;
	protected final CPInstanceHelper cpInstanceHelper;
	protected final CPOptionCategoryService cpOptionCategoryService;
	protected final HttpServletRequest httpServletRequest;
	protected final LiferayPortletResponse liferayPortletResponse;
	protected final Portal portal;

}