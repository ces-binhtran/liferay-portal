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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.term.constants.CommerceTermEntryConstants" %><%@
page import="com.liferay.commerce.term.entry.type.CommerceTermEntryType" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryExpirationDateException" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryNameException" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryPriorityException" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryTypeException" %><%@
page import="com.liferay.commerce.term.exception.DuplicateCommerceTermEntryRelException" %><%@
page import="com.liferay.commerce.term.exception.NoSuchTermEntryException" %><%@
page import="com.liferay.commerce.term.model.CommerceTermEntry" %><%@
page import="com.liferay.commerce.term.web.internal.dao.search.AccountEntryCommerceTermEntryDisplaySearchContainerFactory" %><%@
page import="com.liferay.commerce.term.web.internal.display.CommerceAccountEntryDisplay" %><%@
page import="com.liferay.commerce.term.web.internal.display.CommerceTermEntryDisplay" %><%@
page import="com.liferay.commerce.term.web.internal.display.context.CommerceTermEntryDisplayContext" %><%@
page import="com.liferay.commerce.term.web.internal.display.context.CommerceTermEntryQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.term.web.internal.entry.constants.CommerceTermEntryClayDataSetDisplayNames" %><%@
page import="com.liferay.commerce.term.web.internal.entry.constants.CommerceTermEntryScreenNavigationEntryConstants" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

<%@ page import="java.util.Objects" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String redirect = ParamUtil.getString(request, "redirect");
%>