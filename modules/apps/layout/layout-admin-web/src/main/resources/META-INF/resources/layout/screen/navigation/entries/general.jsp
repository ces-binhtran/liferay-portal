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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	backURL = PortalUtil.getLayoutFullURL(layoutsAdminDisplayContext.getSelLayout(), themeDisplay);
}

String portletResource = ParamUtil.getString(request, "portletResource");

Group selGroup = (Group)request.getAttribute(WebKeys.GROUP);

Group group = layoutsAdminDisplayContext.getGroup();

Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(selLayout);

String layoutSetBranchName = StringPool.BLANK;

boolean incomplete = false;

if (layoutRevision != null) {
	long layoutSetBranchId = layoutRevision.getLayoutSetBranchId();

	incomplete = StagingUtil.isIncomplete(selLayout, layoutSetBranchId);

	if (incomplete) {
		LayoutSetBranch layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutSetBranchId);

		layoutSetBranchName = layoutSetBranch.getName();

		if (LayoutSetBranchConstants.MASTER_BRANCH_NAME.equals(layoutSetBranchName)) {
			layoutSetBranchName = LanguageUtil.get(request, layoutSetBranchName);
		}

		portletDisplay.setShowStagingIcon(false);
	}
}

if (Validator.isNotNull(backURL)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);
}

renderResponse.setTitle(layoutsAdminDisplayContext.getConfigurationTitle(selLayout, locale));
%>

<c:choose>
	<c:when test="<%= incomplete %>">
		<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(selLayout.getName(locale)), HtmlUtil.escape(layoutSetBranchName)} %>" key="the-page-x-is-not-enabled-in-x,-but-is-available-in-other-pages-variations" translateArguments="<%= false %>" />

		<aui:button-row>
			<aui:button id="enableLayoutButton" name="enableLayout" value='<%= LanguageUtil.format(request, "enable-in-x", HtmlUtil.escape(layoutSetBranchName), false) %>' />

			<aui:button cssClass="remove-layout" id="deleteLayoutButton" name="deleteLayout" value="delete-in-all-pages-variations" />

			<script>
				(function () {
					var enableLayoutButton = document.getElementById(
						'<portlet:namespace />enableLayoutButton'
					);

					if (enableLayoutButton) {
						enableLayoutButton.addEventListener('click', (event) => {
							<portlet:actionURL name="/layout_admin/enable_layout" var="enableLayoutURL">
								<portlet:param name="redirect" value="<%= String.valueOf(layoutsAdminDisplayContext.getLayoutScreenNavigationPortletURL()) %>" />
								<portlet:param name="incompleteLayoutRevisionId" value="<%= String.valueOf(layoutRevision.getLayoutRevisionId()) %>" />
							</portlet:actionURL>

							submitForm(document.hrefFm, '<%= enableLayoutURL %>');
						});
					}

					var deleteLayoutButton = document.getElementById(
						'<portlet:namespace />deleteLayoutButton'
					);

					if (deleteLayoutButton) {
						deleteLayoutButton.addEventListener('click', (event) => {
							<portlet:actionURL name="/layout_admin/delete_layout" var="deleteLayoutURL">
								<portlet:param name="redirect" value="<%= String.valueOf(layoutsAdminDisplayContext.getLayoutScreenNavigationPortletURL()) %>" />
								<portlet:param name="selPlid" value="<%= String.valueOf(layoutsAdminDisplayContext.getSelPlid()) %>" />
								<portlet:param name="layoutSetBranchId" value="0" />
							</portlet:actionURL>

							submitForm(document.hrefFm, '<%= deleteLayoutURL %>');
						});
					}
				})();
			</script>
		</aui:button-row>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="/layout_admin/edit_layout" var="editLayoutURL">
			<portlet:param name="mvcRenderCommandName" value="/layout_admin/edit_layout" />
		</portlet:actionURL>

		<liferay-frontend:edit-form
			action='<%= HttpComponentsUtil.addParameter(editLayoutURL, "refererPlid", plid) %>'
			enctype="multipart/form-data"
			method="post"
			name="editLayoutFm"
			onSubmit="event.preventDefault();"
		>
			<aui:input name="redirect" type="hidden" value="<%= String.valueOf(layoutsAdminDisplayContext.getLayoutScreenNavigationPortletURL()) %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />
			<aui:input name="groupId" type="hidden" value="<%= layoutsAdminDisplayContext.getGroupId() %>" />
			<aui:input name="liveGroupId" type="hidden" value="<%= layoutsAdminDisplayContext.getLiveGroupId() %>" />
			<aui:input name="stagingGroupId" type="hidden" value="<%= layoutsAdminDisplayContext.getStagingGroupId() %>" />
			<aui:input name="selPlid" type="hidden" value="<%= layoutsAdminDisplayContext.getSelPlid() %>" />
			<aui:input name="privateLayout" type="hidden" value="<%= layoutsAdminDisplayContext.isPrivateLayout() %>" />
			<aui:input name="layoutId" type="hidden" value="<%= layoutsAdminDisplayContext.getLayoutId() %>" />
			<aui:input name="type" type="hidden" value="<%= selLayout.getType() %>" />
			<aui:input name="<%= PortletDataHandlerKeys.SELECTED_LAYOUTS %>" type="hidden" />

			<c:if test="<%= layoutsAdminDisplayContext.isLayoutPageTemplateEntry() || ((selLayout.isTypeAssetDisplay() || selLayout.isTypeContent()) && layoutsAdminDisplayContext.isDraft()) %>">

				<%
				for (Locale availableLocale : LanguageUtil.getAvailableLocales(group.getGroupId())) {
				%>

					<aui:input name='<%= "nameMapAsXML_" + LocaleUtil.toLanguageId(availableLocale) %>' type="hidden" value="<%= selLayout.getName(availableLocale) %>" />

				<%
				}
				%>

			</c:if>

			<h2 class="mb-4 text-7"><liferay-ui:message key="general" /></h2>

			<liferay-frontend:edit-form-body>
				<liferay-ui:success key="layoutAdded" message="the-page-was-created-successfully" />

				<liferay-ui:error exception="<%= LayoutTypeException.class %>">

					<%
					LayoutTypeException lte = (LayoutTypeException)errorException;

					String type = BeanParamUtil.getString(selLayout, request, "type");
					%>

					<c:if test="<%= lte.getType() == LayoutTypeException.FIRST_LAYOUT %>">
						<liferay-ui:message arguments='<%= Validator.isNull(lte.getLayoutType()) ? type : "layout.types." + lte.getLayoutType() %>' key="the-first-page-cannot-be-of-type-x" />
					</c:if>

					<c:if test="<%= lte.getType() == LayoutTypeException.FIRST_LAYOUT_PERMISSION %>">
						<liferay-ui:message key="you-cannot-delete-this-page-because-the-next-page-is-not-viewable-by-unauthenticated-users-and-so-cannot-be-the-first-page" />
					</c:if>

					<c:if test="<%= lte.getType() == LayoutTypeException.NOT_INSTANCEABLE %>">
						<liferay-ui:message arguments="<%= type %>" key="pages-of-type-x-cannot-be-selected" />
					</c:if>

					<c:if test="<%= lte.getType() == LayoutTypeException.NOT_PARENTABLE %>">
						<liferay-ui:message arguments="<%= type %>" key="pages-of-type-x-cannot-have-child-pages" />
					</c:if>
				</liferay-ui:error>

				<liferay-ui:error exception="<%= LayoutNameException.class %>" message="please-enter-a-valid-name" />

				<liferay-ui:error exception="<%= RequiredLayoutException.class %>">

					<%
					RequiredLayoutException rle = (RequiredLayoutException)errorException;
					%>

					<c:if test="<%= rle.getType() == RequiredLayoutException.AT_LEAST_ONE %>">
						<liferay-ui:message key="you-must-have-at-least-one-page" />
					</c:if>
				</liferay-ui:error>

				<liferay-ui:error exception="<%= RequiredSegmentsExperienceException.MustNotDeleteSegmentsExperienceReferencedBySegmentsExperiments.class %>" message="this-page-cannot-be-deleted-because-it-has-ab-tests-in-progress" />

				<c:if test="<%= layoutRevision != null %>">
					<aui:input name="layoutSetBranchId" type="hidden" value="<%= layoutRevision.getLayoutSetBranchId() %>" />
				</c:if>

				<c:if test="<%= !group.isLayoutPrototype() %>">
					<c:if test="<%= selGroup.hasLocalOrRemoteStagingGroup() && !selGroup.isStagingGroup() %>">
						<div class="alert alert-warning">
							<liferay-ui:message key="changes-are-immediately-available-to-end-users" />
						</div>
					</c:if>

					<%
					Group selLayoutGroup = selLayout.getGroup();
					%>

					<c:choose>
						<c:when test="<%= !SitesUtil.isLayoutUpdateable(selLayout) %>">
							<div class="alert alert-warning">
								<liferay-ui:message key="this-page-cannot-be-modified-because-it-is-associated-with-a-site-template-does-not-allow-modifications-to-it" />
							</div>
						</c:when>
						<c:when test="<%= !SitesUtil.isLayoutDeleteable(selLayout) %>">
							<div class="alert alert-warning">
								<liferay-ui:message key="this-page-cannot-be-deleted-and-cannot-have-child-pages-because-it-is-associated-with-a-site-template" />
							</div>
						</c:when>
					</c:choose>

					<c:if test="<%= (selLayout.getGroupId() != layoutsAdminDisplayContext.getGroupId()) && selLayoutGroup.isUserGroup() %>">

						<%
						UserGroup userGroup = UserGroupLocalServiceUtil.getUserGroup(selLayoutGroup.getClassPK());
						%>

						<div class="alert alert-warning">
							<liferay-ui:message arguments="<%= HtmlUtil.escape(userGroup.getName()) %>" key="this-page-cannot-be-modified-because-it-belongs-to-the-user-group-x" translateArguments="<%= false %>" />
						</div>
					</c:if>
				</c:if>

				<liferay-frontend:form-navigator
					fieldSetCssClass="panel-group panel-group-flush"
					formModelBean="<%= selLayout %>"
					id="<%= FormNavigatorConstants.FORM_NAVIGATOR_ID_LAYOUT %>"
					showButtons="<%= false %>"
					type="<%= FormNavigatorConstants.FormNavigatorType.SHEET_SECTIONS %>"
				/>
			</liferay-frontend:edit-form-body>

			<liferay-frontend:edit-form-footer>
				<c:if test="<%= (selLayout.getGroupId() == layoutsAdminDisplayContext.getGroupId()) && SitesUtil.isLayoutUpdateable(selLayout) && LayoutPermissionUtil.contains(permissionChecker, selLayout, ActionKeys.UPDATE) %>">
					<liferay-frontend:edit-form-buttons
						redirect="<%= backURL %>"
					/>
				</c:if>
			</liferay-frontend:edit-form-footer>
		</liferay-frontend:edit-form>
	</c:otherwise>
</c:choose>

<aui:script>
	var form = document.getElementById('<portlet:namespace />editLayoutFm');

	form.addEventListener('submit', (event) => {
		var applyLayoutPrototype = document.getElementById(
			'<portlet:namespace />applyLayoutPrototype'
		);

		if (!applyLayoutPrototype || applyLayoutPrototype.value === 'false') {
			submitForm(form);
		}
		else if (applyLayoutPrototype && applyLayoutPrototype.value === 'true') {
			Liferay.Util.openConfirmModal({
				message:
					'<%= UnicodeLanguageUtil.get(request, "reactivating-inherited-changes-may-update-the-page-with-the-possible-changes-that-could-have-been-made-in-the-original-template") %>',
				onConfirm: (isConfirm) => {
					if (isConfirm) {
						submitForm(form);
					}
				},
			});
		}
	});
</aui:script>