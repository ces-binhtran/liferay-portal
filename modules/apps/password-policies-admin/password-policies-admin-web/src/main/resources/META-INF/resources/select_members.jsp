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
String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2", "users");

String redirect = ParamUtil.getString(request, "redirect");

long passwordPolicyId = ParamUtil.getLong(request, "passwordPolicyId");

PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(passwordPolicyId);

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectMember");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/select_members.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("passwordPolicyId", String.valueOf(passwordPolicy.getPasswordPolicyId()));
portletURL.setParameter("eventName", eventName);

String[] orderColumns = new String[] {"first-name", "screen-name"};
RowChecker rowChecker = new UserPasswordPolicyChecker(renderResponse, passwordPolicy);
SearchContainer searchContainer = new UserSearch(renderRequest, portletURL);
String searchContainerId = "users";

if (tabs2.equals("organizations")) {
	orderColumns = new String[] {"name", "type"};
	rowChecker = new OrganizationPasswordPolicyChecker(renderResponse, passwordPolicy);
	searchContainer = new OrganizationSearch(renderRequest, portletURL);
	searchContainerId = "organizations";
}
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="<%= tabs2 %>" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL.toString() %>" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="<%= searchContainerId %>"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= searchContainer.getOrderByCol() %>"
			orderByType="<%= searchContainer.getOrderByType() %>"
			orderColumns="<%= orderColumns %>"
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<aui:form cssClass="container-fluid-1280" name="selectMemberFm">
	<liferay-ui:search-container
		id="<%= searchContainerId %>"
		rowChecker="<%= rowChecker %>"
		searchContainer="<%= searchContainer %>"
		var="memberSearchContainer"
	>
		<c:choose>
			<c:when test='<%= tabs2.equals("users") %>'>
				<%@ include file="/user_search_columns.jspf" %>
			</c:when>
			<c:when test='<%= tabs2.equals("organizations") %>'>
				<%@ include file="/organization_search_columns.jspf" %>
			</c:when>
		</c:choose>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />' + '<%= searchContainerId %>');

	searchContainer.on(
		'rowToggled',
		function(event) {
			var selectedItems = event.elements.allSelectedElements;

			var result = {};

			if (selectedItems.size() > 0) {
				result = {
					memberType: '<%= tabs2 %>',
					item: selectedItems.attr('value').join(',')
				};
			}

			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(eventName) %>',
				{
					data: result
				}
			);
		}
	);
</aui:script>