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

<%@ include file="/admin/init.jsp" %>

<%
RemoteAppAdminDisplayContext remoteAppAdminDisplayContext = (RemoteAppAdminDisplayContext)renderRequest.getAttribute(RemoteAppAdminWebKeys.REMOTE_APP_ADMIN_DISPLAY_CONTEXT);
%>

<frontend-data-set:classic-display
	actionParameterName="remoteAppEntryId"
	creationMenu="<%= remoteAppAdminDisplayContext.getCreationMenu() %>"
	dataProviderKey="<%= RemoteAppAdminFDSNames.REMOTE_APP_ENTRIES %>"
	formName="fm"
	id="<%= RemoteAppAdminFDSNames.REMOTE_APP_ENTRIES %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= liferayPortletResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= remoteAppAdminDisplayContext.getCurrentPortletURL() %>"
	selectedItemsKey="remoteAppEntryId"
	selectionType="multiple"
	style="fluid"
/>