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
String redirect = ParamUtil.getString(request, "redirect");

CommerceWarehousesDisplayContext commerceWarehousesDisplayContext = (CommerceWarehousesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceWarehouse commerceWarehouse = commerceWarehousesDisplayContext.getCommerceWarehouse();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((commerceWarehouse == null) ? LanguageUtil.get(request, "add-warehouse") : LanguageUtil.format(request, "edit-x", commerceWarehouse.getName(), false));
%>

<portlet:actionURL name="editCommerceWarehouse" var="editCommerceWarehouseActionURL" />

<aui:form action="<%= editCommerceWarehouseActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceWarehouse();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceWarehouse == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceWarehouseId" type="hidden" value="<%= (commerceWarehouse == null) ? 0 : commerceWarehouse.getCommerceWarehouseId() %>" />

	<liferay-ui:form-navigator
		formModelBean="<%= commerceWarehouse %>"
		id="<%= CommerceWarehouseFormNavigatorConstants.FORM_NAVIGATOR_ID_COMMERCE_WAREHOUSE %>"
		markupView="lexicon"
	/>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceWarehouse() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>