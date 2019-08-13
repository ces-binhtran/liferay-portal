<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:search-container
	emptyResultsMessage="no-purchases-were-found"
	headerNames="name,"
	iteratorURL="<%= portletURL %>"
	total="<%= ProductPurchaseLocalServiceUtil.getProductPurchasesCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= ProductPurchaseLocalServiceUtil.getProductPurchases(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.koroneiki.trunk.model.ProductPurchase"
		escapedModel="<%= true %>"
		keyProperty="productPurchaseId"
		modelVar="productPurchase"
	>

		<%
		Account koroneikiAccount = productPurchase.getAccount();
		ProductEntry productEntry = productPurchase.getProductEntry();
		%>

		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_purchase" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="productPurchaseId" value="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="account"
		>
			<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
				<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
			</span>

			<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="product"
			value="<%= HtmlUtil.escape(productEntry.getName()) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="start-date"
		>
			<c:if test="<%= productPurchase.getStartDate() != null %>">
				<%= mediumDateFormatDate.format(productPurchase.getStartDate()) %>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="end-date"
		>
			<c:if test="<%= productPurchase.getEndDate() != null %>">
				<%= mediumDateFormatDate.format(productPurchase.getEndDate()) %>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="quantity"
			value="<%= String.valueOf(productPurchase.getQuantity()) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/products_admin/product_purchase_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>