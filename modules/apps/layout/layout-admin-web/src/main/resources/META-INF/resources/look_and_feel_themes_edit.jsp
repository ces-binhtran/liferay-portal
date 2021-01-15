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
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

Theme selTheme = null;

if (selLayout != null) {
	selTheme = selLayout.getTheme();
}
else {
	selTheme = selLayoutSet.getTheme();
}
%>

<h1 class="h4 text-default"><liferay-ui:message key="current-theme" /></h1>

<div class="card-horizontal main-content-card">
	<div class="card-body">
		<div id="<portlet:namespace />currentThemeContainer">
			<liferay-util:include page="/look_and_feel_theme_details.jsp" servletContext="<%= application %>" />
		</div>

		<aui:input label="insert-custom-css-that-is-loaded-after-the-theme" name="regularCss" placeholder="css" type="textarea" value="<%= (selLayout != null) ? selLayout.getCssText() : selLayoutSet.getCss() %>" />
	</div>
</div>

<aui:button cssClass="btn btn-secondary" id="changeTheme" value="change-current-theme" />

<aui:script use="aui-parse-content">
	var Util = Liferay.Util;

	var selThemeId = '<%= selTheme.getThemeId() %>';

	var themeContainer = A.one('#<portlet:namespace />currentThemeContainer');

	<portlet:renderURL var="selectThemeURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/select_theme.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	A.one('#<portlet:namespace />changeTheme').on('click', function (event) {
		event.preventDefault();

		Util.openSelectionModal({
			onSelect: function (selectedItem) {
				var themeId = selectedItem.themeid;

				if (themeId && selThemeId != themeId) {
					themeContainer.html('<div class="loading-animation"></div>');

					var data = Util.ns('<portlet:namespace />', {
						themeId: themeId,
					});

					Liferay.Util.fetch(
						'<portlet:renderURL copyCurrentRenderParameters="<%= true %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/look_and_feel_theme_details.jsp" /></portlet:renderURL>',
						{
							body: Liferay.Util.objectToFormData(data),
							method: 'POST',
						}
					)
						.then(function (response) {
							return response.text();
						})
						.then(function (responseData) {
							themeContainer.plug(A.Plugin.ParseContent);

							themeContainer.setContent(responseData);

							var newCheckboxNames = [];

							var checkboxInputs = themeContainer.all(
								'input[type=checkbox]'
							);

							checkboxInputs.each(function (item, index) {
								var checkboxName = item.attr('name');
								newCheckboxNames.push(
									checkboxName.substring(
										'<portlet:namespace />'.length
									)
								);
							});

							document.querySelector(
								'#<portlet:namespace />checkboxNames'
							).value = newCheckboxNames.join(',');

							selThemeId = selectedItem;
						});
				}
			},
			selectEventName: '<portlet:namespace />selectTheme',
			title: '<liferay-ui:message key="available-themes" />',
			url: Util.addParams(
				'<portlet:namespace />themeId=' + selThemeId,
				'<%= selectThemeURL %>'
			),
		});
	});
</aui:script>