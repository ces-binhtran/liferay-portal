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
List<Group> selectedGroups = editAssetListDisplayContext.getSelectedGroups();
%>

<aui:input name="TypeSettingsProperties--groupIds--" type="hidden" value="<%= StringUtil.merge(editAssetListDisplayContext.getSelectedGroupIds()) %>" />

<liferay-util:buffer
	var="removeLinkIcon"
>
	<liferay-ui:icon
		icon="times-circle"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	emptyResultsMessage="none"
	headerNames="name,type,options"
	iteratorURL="<%= editAssetListDisplayContext.getPortletURL() %>"
	total="<%= selectedGroups.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= selectedGroups %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Group"
		keyProperty="groupId"
		modelVar="group"
	>
		<liferay-ui:search-container-column-text
			name="name"
			truncate="<%= true %>"
			value="<%= group.getScopeDescriptiveName(themeDisplay) %>"
		/>

		<liferay-ui:search-container-column-text
			name="type"
			value="<%= LanguageUtil.get(request, group.getScopeLabel(themeDisplay)) %>"
		/>

		<c:if test="<%= !editAssetListDisplayContext.isLiveGroup() %>">
			<liferay-ui:search-container-column-text
				name="options"
			>
				<clay:button
					cssClass="modify-link"
					data-rowId="<%= group.getGroupId() %>"
					displayType="unstyled"
					icon="times-circle"
					monospaced="<%= true %>"
					small="<%= true %>"
				/>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>

<c:if test="<%= !editAssetListDisplayContext.isLiveGroup() %>">

	<%
	ScopeActionDropdownItemsProvider scopeActionDropdownItemsProvider = new ScopeActionDropdownItemsProvider(editAssetListDisplayContext, liferayPortletRequest);
	%>

	<clay:dropdown-menu
		aria-label='<%= LanguageUtil.get(request, "select-site") %>'
		cssClass="btn btn-secondary"
		dropdownItems="<%= scopeActionDropdownItemsProvider.getActionDropdownItems() %>"
		label='<%= LanguageUtil.get(request, "select") %>'
		propsTransformer="js/ScopeDefaultPropsTransformer"
	/>
</c:if>

<aui:script use="liferay-search-container">
	const searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />groupsSearchContainer'
	);

	searchContainer.get('contentBox').delegate(
		'click',
		(event) => {
			const link = event.currentTarget;

			const tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));

			searchContainer.updateDataStore();

			const groupIds = document.getElementById(
				'<portlet:namespace />groupIds'
			);

			if (groupIds) {
				const searchContainerData = searchContainer.getData();

				groupIds.setAttribute('value', searchContainerData.split(','));

				submitForm(document.<portlet:namespace />fm);
			}
		},
		'.modify-link'
	);
</aui:script>