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

<%@ include file="/document_library/init.jsp" %>

<%
DLCopyFolderDisplayContext dlCopyFolderDisplayContext = new DLCopyFolderDisplayContext(liferayPortletRequest, liferayPortletResponse);
%>

<portlet:actionURL name="/document_library/copy_folder" var="copyActionURL">
	<portlet:param name="mvcRenderCommandName" value="/document_library/copy_folder" />
</portlet:actionURL>

<liferay-frontend:edit-form
	action="<%= copyActionURL %>"
	name="fm"
>
	<liferay-frontend:edit-form-body>
		<aui:input name="redirect" type="hidden" value="<%= dlCopyFolderDisplayContext.getRedirect() %>" />
		<aui:input name="sourceFolderId" type="hidden" value="<%= dlCopyFolderDisplayContext.getSourceFolderId() %>" />
		<aui:input name="sourceRepositoryId" type="hidden" value="<%= dlCopyFolderDisplayContext.getSourceRepositoryId() %>" />
		<aui:input name="destinationParentFolderId" type="hidden" value="-1" />
		<aui:input name="destinationRepositoryId" type="hidden" value="-1" />

		<liferay-ui:error exception="<%= DuplicateFolderNameException.class %>" message="the-folder-you-selected-already-has-an-entry-with-this-name.-please-select-a-different-folder" />

		<liferay-ui:error exception="<%= InvalidFolderException.class %>">

			<%
			InvalidFolderException invalidFolderException = (InvalidFolderException)errorException;
			%>

			<liferay-ui:message arguments="<%= invalidFolderException.getMessageArgument(locale) %>" key="<%= invalidFolderException.getMessageKey() %>" />
		</liferay-ui:error>

		<liferay-frontend:fieldset>
			<liferay-ui:message key="document-library-copy-folder-help" />

			<br /><br />

			<aui:input label="source-folder" name="sourceFolderName" type="resource" value="<%= dlCopyFolderDisplayContext.getSourceFolderName() %>" />

			<aui:input label="target-folder" name="destinationParentFolderName" type="resource" value="" />

			<aui:button name="selectFolderButton" value="select" />
		</liferay-frontend:fieldset>

		<aui:script sandbox="<%= true %>">
			var selectFolderButton = document.getElementById(
				'<portlet:namespace />selectFolderButton'
			);

			if (selectFolderButton) {
				selectFolderButton.addEventListener('click', (event) => {
					Liferay.Util.openSelectionModal({
						selectEventName: '<portlet:namespace />folderSelected',
						multiple: false,
						onSelect: function (selectedItem) {
							if (!selectedItem) {
								return;
							}

							var form = document.<portlet:namespace />fm;

							Liferay.Util.setFormValues(form, {
								destinationParentFolderId: selectedItem.folderid,
								destinationParentFolderName: selectedItem.foldername,
								destinationRepositoryId: selectedItem.repositoryid,
							});

							Liferay.Util.toggleDisabled(
								'#<portlet:namespace />submitButton',
								false
							);
						},
						title: '<liferay-ui:message arguments="folder" key="select-x" />',
						url:
							'<%= HtmlUtil.escapeJS(dlCopyFolderDisplayContext.getSelectFolderURL()) %>',
					});
				});
			}
		</aui:script>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
			redirect="<%= dlCopyFolderDisplayContext.getRedirect() %>"
			submitDisabled="<%= true %>"
			submitId="submitButton"
			submitLabel="copy"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>