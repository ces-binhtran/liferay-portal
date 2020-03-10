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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewAccountDisplayContext {

	public ViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			ProductPurchaseViewWebService productPurchaseViewWebService)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountReader = accountReader;
		_productPurchaseViewWebService = productPurchaseViewWebService;

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);

		_addPortletBreadcrumbEntries();
	}

	public Account getAccount() {
		return _account;
	}

	public SearchContainer getProductPurchaseViewSearchContainer()
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _renderResponse.createRenderURL(),
			Collections.emptyList(), "no-subscriptions-were-found");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				_account.getKey(), searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart());

		searchContainer.setResults(productPurchaseViews);

		int count =
			(int)_productPurchaseViewWebService.getProductPurchaseViewsCount(
				_account.getKey());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private void _addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = _renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			_httpServletRequest,
			LanguageUtil.get(_httpServletRequest, "accounts"),
			accountsPortletURL.toString());

		List<Account> ancestorAccounts = _accountReader.getAncestorAccounts(
			_account);

		for (Account account : ancestorAccounts) {
			PortletURL portletURL = _renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("accountKey", account.getKey());

			PortalUtil.addPortletBreadcrumbEntry(
				_httpServletRequest, account.getName(), portletURL.toString());
		}
	}

	private final Account _account;
	private final AccountReader _accountReader;
	private final HttpServletRequest _httpServletRequest;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}