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
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.currency.configuration.CommerceCurrencyConfiguration" %><%@
page import="com.liferay.commerce.currency.exception.CommerceCurrencyCodeException" %><%@
page import="com.liferay.commerce.currency.exception.CommerceCurrencyFractionDigitsException" %><%@
page import="com.liferay.commerce.currency.exception.CommerceCurrencyNameException" %><%@
page import="com.liferay.commerce.currency.model.CommerceCurrency" %><%@
page import="com.liferay.commerce.currency.web.internal.display.context.CommerceCurrenciesDisplayContext" %><%@
page import="com.liferay.commerce.currency.web.internal.servlet.taglib.ui.constants.CommerceCurrencyScreenNavigationConstants" %><%@
page import="com.liferay.petra.portlet.url.builder.PortletURLBuilder" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.math.BigDecimal" %><%@
page import="java.math.RoundingMode" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />