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

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %>

<liferay-ui:header
	showBackURL="<%= false %>"
	title="error"
/>

<c:if test="<%= SessionErrors.contains(request, PrincipalException.class.getName()) %>">
	<liferay-ui:message key="you-do-not-have-permission-to-view-this-page" />
</c:if>

<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-permission-to-view-this-page" />