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
String cmd = ParamUtil.getString(request, Constants.CMD);

CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAddress shippingAddress = null;

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();

if ((commerceOrder != null) && Validator.isNull(cmd)) {
	shippingAddress = commerceOrder.getShippingAddress();
}

long commerceCountryId = BeanParamUtil.getLong(shippingAddress, request, "commerceCountryId");
long commerceRegionId = BeanParamUtil.getLong(shippingAddress, request, "commerceRegionId");
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderShippingAddressActionURL" />

<commerce-ui:modal-content
	title='<%= (shippingAddress == null) ? LanguageUtil.get(request, "add-shipping-address") : LanguageUtil.get(request, "edit-shipping-address") %>'
>
	<aui:form action="<%= editCommerceOrderShippingAddressActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value='<%= (shippingAddress == null) ? "addShippingAddress" : "updateShippingAddress" %>' />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

		<aui:model-context bean="<%= shippingAddress %>" model="<%= CommerceAddress.class %>" />

		<aui:input name="name" wrapperCssClass="form-group-item" />

		<aui:input name="phoneNumber" wrapperCssClass="form-group-item" />

		<aui:input name="street1" wrapperCssClass="form-group-item" />

		<aui:select label="country" name="commerceCountryId" wrapperCssClass="form-group-item" />

		<aui:input name="zip" wrapperCssClass="form-group-item" />

		<aui:input name="city" wrapperCssClass="form-group-item" />

		<aui:select label="region" name="commerceRegionId" wrapperCssClass="form-group-item" />
	</aui:form>
</commerce-ui:modal-content>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect([
		{
			select: '<portlet:namespace />commerceCountryId',
			selectData: function(callback) {
				function injectCountryPlaceholder(list) {
					callback([
						{
							commerceCountryId: '0',
							nameCurrentValue:
								'- <liferay-ui:message key="select-country" />'
						},
						...list
					]);
				}

				Liferay.Service(
					'/commerce.commercecountry/get-shipping-commerce-countries',
					{
						active: true,
						companyId: <%= company.getCompanyId() %>,
						shippingAllowed: true
					},
					injectCountryPlaceholder
				);
			},
			selectDesc: 'nameCurrentValue',
			selectId: 'commerceCountryId',
			selectNullable: <%= false %>,
			selectSort: '<%= true %>',
			selectVal: '<%= commerceCountryId %>'
		},
		{
			select: '<portlet:namespace />commerceRegionId',
			selectData: function(callback, selectKey) {
				function injectRegionPlaceholder(list) {
					callback([
						{
							commerceRegionId: '0',
							name: '- <liferay-ui:message key="select-region" />'
						},
						...list
					]);
				}

				Liferay.Service(
					'/commerce.commerceregion/get-commerce-regions',
					{
						active: true,
						commerceCountryId: Number(selectKey)
					},
					injectRegionPlaceholder
				);
			},
			selectDesc: 'name',
			selectId: 'commerceRegionId',
			selectNullable: <%= false %>,
			selectVal: '<%= commerceRegionId %>'
		}
	]);
</aui:script>