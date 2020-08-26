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
CommercePricingClassDiscountDisplayContext commercePricingClassDiscountDisplayContext = (CommercePricingClassDiscountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

boolean hasPermission = commercePricingClassDiscountDisplayContext.hasPermission();

CommercePricingClass commercePricingClass = commercePricingClassDiscountDisplayContext.getCommercePricingClass();

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commercePricingClassId", String.valueOf(commercePricingClass.getCommercePricingClassId()));
%>

<c:if test="<%= hasPermission %>">
	<div class="col-12 pt-4">
		<commerce-ui:dataset-display
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES_DISCOUNTS %>"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES_DISCOUNTS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			style="stacked"
		/>
	</div>
</c:if>