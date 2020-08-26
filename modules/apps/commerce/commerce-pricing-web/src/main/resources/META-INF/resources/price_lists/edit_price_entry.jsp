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
CommercePriceEntryDisplayContext commercePriceEntryDisplayContext = (CommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommercePriceEntry commercePriceEntry = commercePriceEntryDisplayContext.getCommercePriceEntry();

CommercePriceList commercePriceList = commercePriceEntry.getCommercePriceList();

long commercePriceEntryId = commercePriceEntryDisplayContext.getCommercePriceEntryId();
long commercePriceListId = commercePriceEntryDisplayContext.getCommercePriceListId();

boolean neverExpire = ParamUtil.getBoolean(request, "neverExpire", true);

if ((commercePriceEntry != null) && (commercePriceEntry.getExpirationDate() != null)) {
	neverExpire = false;
}
%>

<portlet:actionURL name="editCommercePriceEntry" var="editCommercePriceEntryActionURL" />

<commerce-ui:side-panel-content
	title='<%= LanguageUtil.get(request, "edit-price") %>'
>
	<aui:form action="<%= editCommercePriceEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commercePriceEntryId" type="hidden" value="<%= commercePriceEntryId %>" />
		<aui:input name="commercePriceListId" type="hidden" value="<%= commercePriceListId %>" />

		<aui:model-context bean="<%= commercePriceEntry %>" model="<%= CommercePriceEntry.class %>" />

		<div class="row">
			<div class="col-12">
				<%@ include file="/price_lists/price_entry/details.jspf" %>

				<%@ include file="/price_lists/price_entry/custom_fields.jspf" %>
			</div>

			<div class="col-12">
				<commerce-ui:panel
					bodyClasses="p-0"
					title='<%= LanguageUtil.get(request, "price-tiers") %>'
				>
					<div class="align-items-center d-flex justify-content-end px-3">
						<div class="mr-3">
							<aui:input checked="<%= commercePriceEntry.isBulkPricing() %>" label="bulk-pricing" name="bulkPricing" type="radio" value="<%= true %>" />
						</div>

						<div>
							<aui:input checked="<%= !commercePriceEntry.isBulkPricing() %>" label="tiered-pricing" name="bulkPricing" type="radio" value="<%= false %>" />
						</div>
					</div>

					<%
					Map<String, String> contextParams = new HashMap<>();

					contextParams.put("commercePriceEntryId", String.valueOf(commercePriceEntryId));
					%>

					<commerce-ui:dataset-display
						clayCreationMenu="<%= commercePriceEntryDisplayContext.getClayCreationMenu() %>"
						contextParams="<%= contextParams %>"
						dataProviderKey="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_TIER_PRICE_ENTRIES %>"
						formId="fm"
						id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_TIER_PRICE_ENTRIES %>"
						itemsPerPage="<%= 10 %>"
						namespace="<%= renderResponse.getNamespace() %>"
						pageNumber="<%= 1 %>"
						portletURL="<%= currentURLObj %>"
						showManagementBar="<%= true %>"
						showSearch="<%= false %>"
					/>
				</commerce-ui:panel>
			</div>
		</div>

		<aui:button-row cssClass="price-entry-button-row">
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</commerce-ui:side-panel-content>