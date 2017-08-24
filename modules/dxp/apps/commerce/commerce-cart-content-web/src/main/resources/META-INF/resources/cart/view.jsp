<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
int type = ParamUtil.getInteger(request, "type", CommerceConstants.COMMERCE_CART_TYPE_CART);

CommerceCartContentDisplayContext commerceCartContentDisplayContext = (CommerceCartContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceCartItem> commerceCartItemSearchContainer = commerceCartContentDisplayContext.getSearchContainer();

PortletURL portletURL = commerceCartContentDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "commerceCartItems");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<div class="container-fluid-1280" id="<portlet:namespace />cartItemsContainer">
	<div class="col-md-9 commerce-cart-items-container" id="<portlet:namespace />entriesContainer">
		<liferay-ui:search-container
			id="commerceCartItems"
			iteratorURL="<%= portletURL %>"
			searchContainer="<%= commerceCartItemSearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.model.CommerceCartItem"
				cssClass="entry-display-style"
				keyProperty="CommerceCartItemId"
				modelVar="commerceCartItem"
			>

				<%
				CPDefinition cpDefinition = commerceCartItem.getCPDefinition();

				String thumbnailSrc = cpDefinition.getDefaultImageThumbnailSrc(themeDisplay);
				%>

				<liferay-ui:search-container-column-image
					cssClass="table-cell-content"
					name="product"
					src="<%= thumbnailSrc %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					name="description"
				>
					<a href="<%= commerceCartContentDisplayContext.getCPDefinitionURL(cpDefinition.getCPDefinitionId(), themeDisplay) %>">
						<%= HtmlUtil.escape(cpDefinition.getTitle(languageId)) %>
					</a>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					name="price"
					value="<%= String.valueOf(commerceCartContentDisplayContext.getCommerceCartItemPrice(commerceCartItem.getCommerceCartItemId())) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
				>
					<portlet:actionURL name="editCommerceCartItem" var="deleteURL">
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="commerceCartItemId" value="<%= String.valueOf(commerceCartItem.getCommerceCartItemId()) %>" />
						<portlet:param name="type" value="<%= String.valueOf(type) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete
						label="<%= true %>"
						url="<%= deleteURL %>"
					/>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					cssClass="table-cell-content"
					name="quantity"
					path="/cart/cart_item_quantity_select.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator displayStyle="list" markupView="lexicon" searchContainer="<%= commerceCartItemSearchContainer %>" />
		</liferay-ui:search-container>
	</div>

	<div class="col-md-3">
		<h4><strong><liferay-ui:message key="total" /> <%= HtmlUtil.escape(String.valueOf(commerceCartContentDisplayContext.getCommerceCartTotal())) %></strong></h4>
	</div>
</div>