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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceShippingFixedOptionRel commerceShippingFixedOptionRel = (CommerceShippingFixedOptionRel)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= CommercePermission.contains(permissionChecker, commerceShippingFixedOptionRel.getGroupId(), CommerceActionKeys.MANAGE_COMMERCE_SHIPPING_METHODS) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceShippingFixedOptionRel" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceShippingMethodId" value="<%= String.valueOf(commerceShippingFixedOptionRel.getCommerceShippingMethodId()) %>" />
			<portlet:param name="commerceShippingFixedOptionRelId" value="<%= String.valueOf(commerceShippingFixedOptionRel.getCommerceShippingFixedOptionRelId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCommerceShippingFixedOption" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceShippingFixedOptionRelId" value="<%= String.valueOf(commerceShippingFixedOptionRel.getCommerceShippingFixedOptionId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>