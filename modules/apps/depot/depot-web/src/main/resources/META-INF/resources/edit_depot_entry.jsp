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

<clay:container-fluid
	cssClass="container-view"
>
	<c:if test="<%= GetterUtil.getBoolean(request.getAttribute(DepotAdminWebKeys.SHOW_BREADCRUMB)) %>">
		<liferay-ui:breadcrumb
			showLayout="<%= false %>"
		/>
	</c:if>

	<liferay-frontend:screen-navigation
		containerCssClass="col-lg-8"
		containerWrapperCssClass=""
		context="<%= (DepotEntry)request.getAttribute(DepotAdminWebKeys.DEPOT_ENTRY) %>"
		headerContainerCssClass=""
		key="<%= DepotScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY_DEPOT %>"
		menubarCssClass="menubar menubar-transparent menubar-vertical-expand-lg"
		navCssClass="col-lg-3"
		portletURL="<%= currentURLObj %>"
	/>
</clay:container-fluid>