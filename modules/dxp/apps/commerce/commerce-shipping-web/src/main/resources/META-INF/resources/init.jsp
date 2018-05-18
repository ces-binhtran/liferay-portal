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
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.commerce.admin.constants.CommerceAdminWebKeys" %><%@
page import="com.liferay.commerce.configuration.CommerceShippingGroupServiceConfiguration" %><%@
page import="com.liferay.commerce.constants.CommerceActionKeys" %><%@
page import="com.liferay.commerce.exception.CommerceShippingMethodNameException" %><%@
page import="com.liferay.commerce.exception.CommerceWarehouseActiveException" %><%@
page import="com.liferay.commerce.exception.CommerceWarehouseNameException" %><%@
page import="com.liferay.commerce.model.CommerceAddressRestriction" %><%@
page import="com.liferay.commerce.model.CommerceCountry" %><%@
page import="com.liferay.commerce.model.CommerceShippingMethod" %><%@
page import="com.liferay.commerce.model.CommerceShippingOriginLocator" %><%@
page import="com.liferay.commerce.service.permission.CommercePermission" %><%@
page import="com.liferay.commerce.shipping.web.admin.ShippingMethodsCommerceAdminModule" %><%@
page import="com.liferay.commerce.shipping.web.internal.display.context.CommerceShippingMethodRestrictionsDisplayContext" %><%@
page import="com.liferay.commerce.shipping.web.internal.display.context.CommerceShippingMethodsDisplayContext" %><%@
page import="com.liferay.commerce.shipping.web.internal.display.context.CommerceShippingSettingsDisplayContext" %><%@
page import="com.liferay.commerce.shipping.web.internal.servlet.taglib.ui.CommerceShippingFormNavigatorConstants" %><%@
page import="com.liferay.commerce.shipping.web.servlet.taglib.ui.CommerceShippingScreenNavigationConstants" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.ActionRequest" %><%@
page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String commerceAdminModuleKey = ShippingMethodsCommerceAdminModule.KEY;
%>