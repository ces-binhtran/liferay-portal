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

package com.liferay.commerce.pricing.web.internal.display.context;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.currency.util.comparator.CommerceCurrencyPriorityComparator;
import com.liferay.commerce.frontend.model.HeaderActionModel;
import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.pricing.constants.CommercePricingPortletKeys;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.service.CommercePriceModifierService;
import com.liferay.commerce.pricing.type.CommercePriceModifierType;
import com.liferay.commerce.pricing.type.CommercePriceModifierTypeRegistry;
import com.liferay.commerce.pricing.web.internal.servlet.taglib.ui.constants.CommercePriceListScreenNavigationConstants;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.frontend.taglib.clay.data.set.servlet.taglib.util.ClayDataSetActionDropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionURL;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListDisplayContext
	extends BaseCommercePriceListDisplayContext {

	public CommercePriceListDisplayContext(
		CommerceCatalogService commerceCatalogService,
		CommerceCurrencyService commerceCurrencyService,
		ModelResourcePermission<CommercePriceList>
			commercePriceListModelResourcePermission,
		CommercePriceListService commercePriceListService,
		CommercePriceModifierService commercePriceModifierService,
		CommercePriceModifierTypeRegistry commercePriceModifierTypeRegistry,
		HttpServletRequest httpServletRequest) {

		super(
			commerceCatalogService, commercePriceListModelResourcePermission,
			commercePriceListService, httpServletRequest);

		_commerceCurrencyService = commerceCurrencyService;
		_commercePriceModifierService = commercePriceModifierService;
		_commercePriceModifierTypeRegistry = commercePriceModifierTypeRegistry;
	}

	public String getAddCommercePriceListRenderURL() throws Exception {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "addCommercePriceList");
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public String getAddCommercePriceModifierRenderURL() throws Exception {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "addCommercePriceModifier");
		portletURL.setParameter(
			"commercePriceListId", String.valueOf(getCommercePriceListId()));
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public List<CommerceCatalog> getCommerceCatalogs() throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return commerceCatalogService.searchCommerceCatalogs(
			themeDisplay.getCompanyId(), null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<CommerceCurrency> getCommerceCurrencies()
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _commerceCurrencyService.getCommerceCurrencies(
			themeDisplay.getCompanyId(), true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new CommerceCurrencyPriorityComparator(true));
	}

	public CommercePriceModifier getCommercePriceModifier()
		throws PortalException {

		if (_commercePriceModifier != null) {
			return _commercePriceModifier;
		}

		long commercePriceModifierId = ParamUtil.getLong(
			httpServletRequest, "commercePriceModifierId");

		if (commercePriceModifierId > 0) {
			_commercePriceModifier =
				_commercePriceModifierService.getCommercePriceModifier(
					commercePriceModifierId);
		}

		return _commercePriceModifier;
	}

	public long getCommercePriceModifierId() throws PortalException {
		CommercePriceModifier commercePriceModifier =
			getCommercePriceModifier();

		if (commercePriceModifier == null) {
			return 0;
		}

		return commercePriceModifier.getCommercePriceModifierId();
	}

	public List<CommercePriceModifierType> getCommercePriceModifierTypes() {
		return _commercePriceModifierTypeRegistry.
			getCommercePriceModifierTypes();
	}

	public List<HeaderActionModel> getHeaderActionModels()
		throws PortalException {

		List<HeaderActionModel> headerActionModels = new ArrayList<>();

		CPRequestHelper cpRequestHelper = new CPRequestHelper(
			httpServletRequest);

		RenderResponse renderResponse = cpRequestHelper.getRenderResponse();

		RenderURL cancelURL = renderResponse.createRenderURL();

		HeaderActionModel cancelHeaderActionModel = new HeaderActionModel(
			null, cancelURL.toString(), null, "cancel");

		headerActionModels.add(cancelHeaderActionModel);

		CommercePriceList commercePriceList = getCommercePriceList();

		ActionURL actionURL = renderResponse.createActionURL();

		actionURL.setParameter(
			ActionRequest.ACTION_NAME, "editCommercePriceList");

		String saveButtonLabel = "save";

		if ((commercePriceList == null) || commercePriceList.isDraft() ||
			commercePriceList.isApproved() || commercePriceList.isExpired() ||
			commercePriceList.isScheduled()) {

			saveButtonLabel = "save-as-draft";
		}

		HeaderActionModel saveAsDraftHeaderActionModel = new HeaderActionModel(
			null, liferayPortletResponse.getNamespace() + "fm",
			actionURL.toString(), null, saveButtonLabel);

		headerActionModels.add(saveAsDraftHeaderActionModel);

		String publishButtonLabel = "publish";

		if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				cpRequestHelper.getCompanyId(),
				cpRequestHelper.getScopeGroupId(),
				CommercePriceList.class.getName())) {

			publishButtonLabel = "submit-for-publication";
		}

		String additionalClasses = "btn-primary";

		if ((commercePriceList != null) && commercePriceList.isPending()) {
			additionalClasses = additionalClasses + " disabled";
		}

		HeaderActionModel publishHeaderActionModel = new HeaderActionModel(
			additionalClasses, liferayPortletResponse.getNamespace() + "fm",
			actionURL.toString(),
			liferayPortletResponse.getNamespace() + "publishButton",
			publishButtonLabel);

		headerActionModels.add(publishHeaderActionModel);

		return headerActionModels;
	}

	public String getModalContextTitle(String portletName) {
		String title = StringPool.BLANK;

		if (portletName.equals(
				CommercePricingPortletKeys.COMMERCE_PRICE_LIST)) {

			title = "create-new-price-list";
		}
		else if (portletName.equals(
					CommercePricingPortletKeys.COMMERCE_PROMOTION)) {

			title = "create-new-promotion";
		}

		return LanguageUtil.get(httpServletRequest, title);
	}

	public long getParentCommercePriceListId() throws PortalException {
		CommercePriceList commercePriceList = getCommercePriceList();

		if (commercePriceList == null) {
			return 0;
		}

		return commercePriceList.getParentCommercePriceListId();
	}

	public List<ClayDataSetActionDropdownItem>
			getPriceListClayDataSetActionDropdownItems()
		throws PortalException {

		RenderResponse renderResponse =
			commercePricingRequestHelper.getRenderResponse();

		RenderURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommercePriceList");
		portletURL.setParameter(
			"redirect", commercePricingRequestHelper.getCurrentURL());
		portletURL.setParameter("commercePriceListId", "{id}");
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CommercePriceListScreenNavigationConstants.CATEGORY_KEY_DETAILS);

		List<ClayDataSetActionDropdownItem> clayDataSetActionDropdownItems =
			getClayDataSetActionDropdownItems(portletURL.toString(), false);

		clayDataSetActionDropdownItems.add(
			new ClayDataSetActionDropdownItem(
				_getManagePriceListPermissionsURL(), null, "permissions",
				LanguageUtil.get(httpServletRequest, "permissions"), "get",
				"permissions", "modal-permissions"));

		return clayDataSetActionDropdownItems;
	}

	public CreationMenu getPriceListCreationMenu() throws Exception {
		CreationMenu creationMenu = new CreationMenu();

		if (hasPermission(
				CommercePriceListActionKeys.ADD_COMMERCE_PRICE_LIST)) {

			creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(getAddCommercePriceListRenderURL());
					dropdownItem.setLabel(
						LanguageUtil.get(
							httpServletRequest, "create-new-price-list"));
					dropdownItem.setTarget("modal-lg");
				});
		}

		return creationMenu;
	}

	public String getPriceListsApiUrl(String portletName) {
		StringBundler filterSB = new StringBundler(4);

		filterSB.append("type eq ");
		filterSB.append(StringPool.APOSTROPHE);
		filterSB.append(getCommercePriceListType(portletName));
		filterSB.append(StringPool.APOSTROPHE);

		String encodedFilter = URLCodec.encodeURL(filterSB.toString(), true);

		StringBundler apiUrlSB = new StringBundler(4);

		apiUrlSB.append(PortalUtil.getPortalURL(httpServletRequest));
		apiUrlSB.append("/o/headless-commerce-admin-pricing/v2.0/price-lists");
		apiUrlSB.append("?filter=");
		apiUrlSB.append(encodedFilter);

		return apiUrlSB.toString();
	}

	public String getPriceModifierCategoriesApiUrl() throws PortalException {
		return "/o/headless-commerce-admin-pricing/v2.0/price-modifiers/" +
			getCommercePriceModifierId() +
				"/price-modifier-categories?nestedFields=category";
	}

	public List<ClayDataSetActionDropdownItem>
		getPriceModifierCategoryClayDataSetActionDropdownItems() {

		return ListUtil.fromArray(
			new ClayDataSetActionDropdownItem(
				null, "trash", "remove",
				LanguageUtil.get(httpServletRequest, "remove"), "delete",
				"delete", "headless"));
	}

	public String getPriceModifierCPDefinitionApiUrl() throws PortalException {
		return "/o/headless-commerce-admin-pricing/v2.0/price-modifiers/" +
			getCommercePriceModifierId() +
				"/price-modifier-products?nestedFields=product";
	}

	public List<ClayDataSetActionDropdownItem>
		getPriceModifierCPDefinitionClayDataSetActionDropdownItems() {

		return ListUtil.fromArray(
			new ClayDataSetActionDropdownItem(
				null, "trash", "remove",
				LanguageUtil.get(httpServletRequest, "remove"), "delete",
				"delete", "headless"));
	}

	public List<ClayDataSetActionDropdownItem>
		getPriceModifierPricingClassClayDataSetActionDropdownItems() {

		return ListUtil.fromArray(
			new ClayDataSetActionDropdownItem(
				null, "trash", "remove",
				LanguageUtil.get(httpServletRequest, "remove"), "delete",
				"delete", "headless"));
	}

	public String getPriceModifierPricingClassesApiUrl()
		throws PortalException {

		return "/o/headless-commerce-admin-pricing/v2.0/price-modifiers/" +
			getCommercePriceModifierId() +
				"/price-modifier-product-groups?nestedFields=productGroup";
	}

	public CreationMenu getPriceModifiersCreationMenu() throws Exception {
		CreationMenu creationMenu = new CreationMenu();

		if (hasPermission(getCommercePriceListId(), ActionKeys.UPDATE)) {
			creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(
						getAddCommercePriceModifierRenderURL());
					dropdownItem.setLabel(
						LanguageUtil.get(
							httpServletRequest, "add-price-modifier"));
					dropdownItem.setTarget("modal-lg");
				});
		}

		return creationMenu;
	}

	public boolean hasCustomAttributesAvailable(String className, long classPK)
		throws Exception {

		return CustomAttributesUtil.hasCustomAttributes(
			commercePricingRequestHelper.getCompanyId(), className, classPK,
			null);
	}

	public boolean isSelectedCatalog(CommerceCatalog commerceCatalog)
		throws PortalException {

		CommercePriceList commercePriceList = getCommercePriceList();

		if (commerceCatalog.getGroupId() == commercePriceList.getGroupId()) {
			return true;
		}

		return false;
	}

	private String _getManagePriceListPermissionsURL() throws PortalException {
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			httpServletRequest,
			"com_liferay_portlet_configuration_web_portlet_" +
				"PortletConfigurationPortlet",
			ActionRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/edit_permissions.jsp");
		portletURL.setParameter(
			"redirect", commercePricingRequestHelper.getCurrentURL());
		portletURL.setParameter(
			"modelResource", CommercePriceList.class.getName());
		portletURL.setParameter("modelResourceDescription", "{name}");
		portletURL.setParameter("resourcePrimKey", "{id}");

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException windowStateException) {
			throw new PortalException(windowStateException);
		}

		return portletURL.toString();
	}

	private final CommerceCurrencyService _commerceCurrencyService;
	private CommercePriceModifier _commercePriceModifier;
	private final CommercePriceModifierService _commercePriceModifierService;
	private final CommercePriceModifierTypeRegistry
		_commercePriceModifierTypeRegistry;

}