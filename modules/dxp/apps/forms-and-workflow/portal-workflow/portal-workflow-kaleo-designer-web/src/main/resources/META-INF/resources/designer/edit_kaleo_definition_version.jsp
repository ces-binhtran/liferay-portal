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

<%@ include file="/designer/init.jsp" %>

<c:choose>
	<c:when test="<%= WorkflowEngineManagerUtil.isDeployed() %>">

		<%
		String mvcPath = ParamUtil.getString(request, "mvcPath", "/designer/edit_kaleo_definition_version.jsp");

		String redirect = ParamUtil.getString(request, "redirect");
		String closeRedirect = ParamUtil.getString(request, "closeRedirect");

		KaleoDefinitionVersion kaleoDefinitionVersion = (KaleoDefinitionVersion)request.getAttribute(KaleoDesignerWebKeys.KALEO_DRAFT_DEFINITION);

		KaleoDefinition kaleoDefinition = kaleoDesignerDisplayContext.getKaleoDefinition(kaleoDefinitionVersion);

		String name = BeanParamUtil.getString(kaleoDefinitionVersion, request, "name");
		String draftVersion = BeanParamUtil.getString(kaleoDefinitionVersion, request, "version");
		String content = BeanParamUtil.getString(kaleoDefinitionVersion, request, "content");

		String latestDraftVersion = StringPool.BLANK;
		int version = 0;

		KaleoDefinitionVersion latestKaleoDefinitionVersion = null;

		if (kaleoDefinitionVersion != null) {
			if (Validator.isNull(name)) {
				name = kaleoDefinitionVersion.getName();
			}

			draftVersion = kaleoDefinitionVersion.getVersion();

			latestKaleoDefinitionVersion = KaleoDefinitionVersionLocalServiceUtil.getLatestKaleoDefinitionVersion(themeDisplay.getCompanyId(), name);

			latestDraftVersion = latestKaleoDefinitionVersion.getVersion();

			if (kaleoDefinition != null) {
				version = kaleoDefinition.getVersion();
			}
		}

		portletDisplay.setShowBackIcon(true);
		portletDisplay.setURLBack(redirect);

		renderResponse.setTitle((kaleoDefinitionVersion == null) ? LanguageUtil.get(request, "new-workflow") : name);
		%>

		<c:if test="<%= kaleoDefinitionVersion != null %>">
			<aui:model-context bean="<%= kaleoDefinitionVersion %>" model="<%= KaleoDefinitionVersion.class %>" />

			<liferay-frontend:info-bar>
				<div class="container-fluid-1280">
					<div class="info-bar-item">
						<c:choose>
							<c:when test="<%= (kaleoDefinition != null) && kaleoDefinition.isActive() %>">
								<span class="label label-info label-lg ">
									<liferay-ui:message key="published" />
								</span>
							</c:when>
							<c:otherwise>
								<span class="label label-lg label-secondary">
									<liferay-ui:message key="not-published" />
								</span>
							</c:otherwise>
						</c:choose>
					</div>

					<%
					String userName = kaleoDesignerDisplayContext.getUserName(kaleoDefinitionVersion);
					%>

					<span>
						<c:choose>
							<c:when test="<%= userName == null %>">
								<%= dateFormatTime.format(kaleoDefinitionVersion.getModifiedDate()) %>
							</c:when>
							<c:otherwise>
								<liferay-ui:message arguments="<%= new String[] {dateFormatTime.format(kaleoDefinitionVersion.getModifiedDate()), userName} %>" key="x-by-x" translateArguments="<%= false %>" />
							</c:otherwise>
						</c:choose>
					</span>
				</div>

				<liferay-frontend:info-bar-buttons>
					<liferay-frontend:info-bar-sidenav-toggler-button
						icon="info-circle"
						label="info"
					/>
				</liferay-frontend:info-bar-buttons>
			</liferay-frontend:info-bar>
		</c:if>

		<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
			<c:if test="<%= kaleoDefinitionVersion != null %>">
				<div class="sidenav-menu-slider">
					<div class="sidebar sidebar-default sidenav-menu">
						<div class="sidebar-header">
							<aui:icon cssClass="icon-monospaced sidenav-close text-default visible-xs-inline-block" image="times" markupView="lexicon" url="javascript:;" />

							<h4>
								<%= kaleoDefinitionVersion.getName() %>
							</h4>
						</div>

						<liferay-ui:tabs cssClass="navbar-no-collapse panel panel-default" names="details,revision-history" refresh="<%= false %>" type="tabs nav-tabs-default">
							<c:if test="<%= kaleoDefinitionVersion != null %>">
								<liferay-ui:section>
									<div class="sidebar-list">

										<%
										String userName = kaleoDesignerDisplayContext.getUserName(kaleoDefinitionVersion);
										%>

										<div class="card-row-padded created-date">
											<div>
												<span class="info-title">
													<liferay-ui:message key="created" />
												</span>
											</div>

											<span class="info-content lfr-card-modified-by-text">
												<liferay-ui:message arguments="<%= new String[] {dateFormatTime.format(kaleoDesignerDisplayContext.getCreatedDate(kaleoDefinitionVersion)), userName} %>" key="x-by-x" translateArguments="<%= false %>" />
											</span>
										</div>

										<div class="card-row-padded last-modified">
											<div>
												<span class="info-title">
													<liferay-ui:message key="last-modified" />
												</span>
											</div>

											<span class="info-content lfr-card-modified-by-text">
												<liferay-ui:message arguments="<%= new String[] {dateFormatTime.format(kaleoDefinitionVersion.getModifiedDate()), userName} %>" key="x-by-x" translateArguments="<%= false %>" />
											</span>
										</div>

										<div class="card-row-padded">
											<div>
												<span class="info-title">
													<liferay-ui:message key="total-modifications" />
												</span>
											</div>

											<span class="info-content lfr-card-modified-by-text">
												<liferay-ui:message arguments='<%= new String[] {kaleoDesignerDisplayContext.getKaleoDefinitionVersionCount(kaleoDefinitionVersion) + ""} %>' key="x-revisions" translateArguments="<%= false %>" />
											</span>
										</div>
									</div>
								</liferay-ui:section>
							</c:if>

							<liferay-ui:section>
								<div class="sidebar-body workflow-definition-sidebar">
									<liferay-util:include page="/designer/view_kaleo_definition_version_history.jsp" servletContext="<%= application %>">
										<liferay-util:param name="redirect" value="<%= redirect %>" />
									</liferay-util:include>
								</div>
							</liferay-ui:section>
						</liferay-ui:tabs>
					</div>
				</div>
			</c:if>

			<div class="sidenav-content">
				<aui:form method="post" name="fm" onSubmit="event.preventDefault();">
					<aui:model-context bean="<%= kaleoDefinitionVersion %>" model="<%= KaleoDefinitionVersion.class %>" />

					<aui:input name="mvcPath" type="hidden" value="<%= mvcPath %>" />
					<aui:input name="closeRedirect" type="hidden" value="<%= closeRedirect %>" />
					<aui:input name="name" type="hidden" value="<%= name %>" />
					<aui:input name="content" type="hidden" value="<%= content %>" />
					<aui:input name="version" type="hidden" value="<%= String.valueOf(version) %>" />
					<aui:input name="draftVersion" type="hidden" value="<%= draftVersion %>" />
					<aui:input name="latestDraftVersion" type="hidden" value="<%= latestDraftVersion %>" />

					<%@ include file="/designer/edit_kaleo_definition_version_exceptions.jspf" %>

					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<liferay-ui:input-localized name="title" xml='<%= BeanPropertiesUtil.getString(kaleoDefinitionVersion, "title") %>' />
						</aui:fieldset>

						<c:if test="<%= kaleoDefinitionVersion != null %>">
							<liferay-ui:panel-container extended="<%= false %>" id="kaleoDesignerDetailsPanelContainer" persistState="<%= true %>">
								<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-portlet-workflowdesigner-panel" extended="<%= false %>" id="kaleoDesignerSectionPanel" markupView="lexicon" persistState="<%= true %>" title='<%= LanguageUtil.get(request, "details") %>'>
									<div class="lfr-portlet-workflowdesigner-details-view">
										<c:if test="<%= kaleoDefinition != null %>">
											<aui:field-wrapper cssClass="lfr-portlet-workflowdesigner-field-wrapper-first" helpMessage="the-definition-name-is-defined-in-the-workflow-definition-file" label="workflow-definition-name">
												<%= HtmlUtil.escape(kaleoDefinition.getName()) %>
											</aui:field-wrapper>

											<aui:field-wrapper label="workflow-definition-status">
												<%= kaleoDefinition.isActive() ? LanguageUtil.get(request, "active") : LanguageUtil.get(request, "not-active") %>
											</aui:field-wrapper>
										</c:if>

										<c:if test="<%= kaleoDefinitionVersion != null %>">
											<aui:field-wrapper label="draft-version">
												<div id="<portlet:namespace />draftVersionLabel">
													<%= draftVersion %>
												</div>
											</aui:field-wrapper>

											<aui:field-wrapper label="draft-history">
												<div class="lfr-portlet-workflowdesigner-toolbar" id="<portlet:namespace />kaleoDesignerToolbarContainer"></div>
											</aui:field-wrapper>
										</c:if>
									</div>
								</liferay-ui:panel>
							</liferay-ui:panel-container>
						</c:if>

						<aui:fieldset>
							<div class="diagram-builder property-builder" id="<portlet:namespace />propertyBuilder">
								<div class="property-builder-content" id="<portlet:namespace />propertyBuilderContent">
									<div class="tabbable">
										<div class="tabbable-content">
											<ul class="nav nav-tabs nav-tabs-default">
												<li class="active">
													<a href="javascript:;">
														<liferay-ui:message key="nodes" />
													</a>
												</li>
												<li>
													<a href="javascript:;">
														<liferay-ui:message key="settings" />
													</a>
												</li>
											</ul>

											<div class="tab-content">
												<div class="tab-pane"></div>

												<div class="tab-pane"></div>
											</div>
										</div>
									</div>

									<div class="property-builder-content-container">
										<div class="tabbable">
											<div class="tabbable-content">
												<ul class="nav nav-tabs nav-tabs-default">
													<li class="active">
														<a href="javascript:;">
															<liferay-ui:message key="view" />
														</a>
													</li>
													<li>
														<a href="javascript:;">
															<liferay-ui:message key="source" /> (Kaleo <liferay-ui:message key="xml" />)
														</a>
													</li>
												</ul>

												<div class="tab-content">
													<div class="tab-pane">
														<div class="property-builder-canvas">
															<div class="property-builder-drop-container"></div>
														</div>
													</div>

													<div class="tab-pane">
														<liferay-util:buffer var="importFileMark">
															<aui:a href="#" id="uploadLink">
																<%= StringUtil.toLowerCase(LanguageUtil.get(request, "import-a-file")) %>
															</aui:a>
														</liferay-util:buffer>

														<liferay-ui:message arguments="<%= importFileMark %>" key="write-your-definition-or-x" translateArguments="<%= false %>" />

														<input id="<portlet:namespace />upload" style="display:none" type="file" />

														<div class="lfr-template-editor-wrapper" id="<portlet:namespace />editorWrapper"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<aui:script>
								Liferay.provide(
									window,
									'<portlet:namespace />afterTabViewChange',
									function(event) {
										var A = AUI();

										var tabContentNode = event.newVal.get('boundingBox');

										var kaleoDesigner = <portlet:namespace />kaleoDesigner;

										if ((tabContentNode === kaleoDesigner.viewNode) && kaleoDesigner.editor) {
											setTimeout(
												function() {
													kaleoDesigner.set('definition', kaleoDesigner.editor.get('value'));
												},
												0
											);
										}
									},
									['aui-base']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />getKaleoDefinitionVersion',
									function(name, draftVersion, position, callback) {
										var A = AUI();

										var contentURL = Liferay.PortletURL.createResourceURL();

										contentURL.setParameter('name', name);
										contentURL.setParameter('draftVersion', draftVersion);
										contentURL.setParameter('position', position);
										contentURL.setPortletId('<%= portletDisplay.getId() %>');
										contentURL.setResourceId('kaleoDefinitionVersions');

										A.io.request(
											contentURL.toString(),
											{
												dataType: 'JSON',
												on: {
													success: callback
												}
											}
										);
									},
									['aui-base', 'liferay-portlet-url']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />getLatestKaleoDefinitionVersion',
									function(event, button) {
										var A = AUI();

										var button = event.target;

										if (!button.get('disabled')) {
											<portlet:namespace />navigateKaleoDefinitionVersion('latest');
										}
									},
									['aui-base']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />initKaleoDefinitionVersionToolbar',
									function() {
										var A = AUI();

										<portlet:namespace />kaleoDesignerToolbar = new A.Toolbar(
											{
												activeState: false,
												boundingBox: '#<portlet:namespace />kaleoDesignerToolbarContainer',
												children: [
													{
														icon: 'icon-arrow-left',
														id: '<portlet:namespace />undoButton',
														label: '<liferay-ui:message key="undo" />',
														on: {
															click: <portlet:namespace />undoKaleoDefinitionVersion
														}
													},
													{
														icon: 'icon-arrow-right',
														id: '<portlet:namespace />redoButton',
														label: '<liferay-ui:message key="redo" />',
														on: {
															click: <portlet:namespace />redoKaleoDefinitionVersion
														}
													},
													{
														icon: 'icon-forward',
														id: '<portlet:namespace />latestButton',
														label: '<liferay-ui:message key="latest-version" />',
														on: {
															click: <portlet:namespace />getLatestKaleoDefinitionVersion
														}
													}
												]
											}
										).render();

										<portlet:namespace />updateToolbarButtons();
									},
									['aui-toolbar']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />navigateKaleoDefinitionVersion',
									function(position) {
										var A = AUI();

										var draftVersion = A.one('#<portlet:namespace />draftVersion');
										var draftVersionLabel = A.one('#<portlet:namespace />draftVersionLabel');

										var name = A.one('#<portlet:namespace />name');
										var title = Liferay.component('<portlet:namespace />title');

										A.one('#<portlet:namespace />toolbarMessage').setContent('<liferay-ui:message key="loading-workflow-definition" />&hellip;');

										<portlet:namespace />getKaleoDefinitionVersion(
											name.val(),
											draftVersion.val(),
											position,
											function() {
												var responseData = this.get('responseData');

												if (responseData) {

													var kaleoDesigner = <portlet:namespace />kaleoDesigner;

													var content = responseData.content;

													draftVersion.val(responseData.draftVersion);

													draftVersionLabel.setContent(responseData.draftVersion);

													var locales = A.merge(
														A.Object.keys(responseData.title),
														title.get('translatedLanguages').values()
													);

													A.Object.keys(locales).forEach(
														function(item, index) {
															var locale = locales[index];

															title.updateInputLanguage(responseData.title[locale] || "", locale);
														}
													);

													title.selectFlag();

													<portlet:namespace />updateToolbarButtons();

													kaleoDesigner.set('definition', content);

													if (kaleoDesigner.editor) {
														kaleoDesigner.editor.set('value', content);
													}

													A.one('#<portlet:namespace />toolbarMessage').setContent('');
												}
											}
										);
									},
									['aui-base']
								);

								<c:if test="<%= KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), KaleoDesignerActionKeys.PUBLISH) %>">
									Liferay.provide(
										window,
										'<portlet:namespace />publishKaleoDefinitionVersion',
										function() {
											var A = AUI();

											<portlet:namespace />updateContent();

											<portlet:namespace />updateAction('<portlet:actionURL name="publishKaleoDefinitionVersion" />');

											submitForm(document.<portlet:namespace />fm);
										},
										['aui-base']
									);
								</c:if>

								Liferay.provide(
									window,
									'<portlet:namespace />redoKaleoDefinitionVersion',
									function(event) {
										var A = AUI();

										var button = event.target;

										if (!button.get('disabled')) {
											<portlet:namespace />navigateKaleoDefinitionVersion('next');
										}
									},
									['aui-base']
								);

								<c:if test="<%= ((kaleoDefinitionVersion == null) && KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), KaleoDesignerActionKeys.ADD_DRAFT)) || ((kaleoDefinitionVersion != null) && KaleoDefinitionVersionPermission.contains(permissionChecker, kaleoDefinitionVersion, ActionKeys.UPDATE)) %>">
									Liferay.provide(
										window,
										'<portlet:namespace />addKaleoDefinitionVersion',
										function() {
											var A = AUI();

											<portlet:namespace />updateContent();

											<portlet:namespace />updateAction('<portlet:actionURL name="addKaleoDefinitionVersion" />');

											submitForm(document.<portlet:namespace />fm);
										},
										['aui-base']
									);
								</c:if>

								Liferay.provide(
									window,
									'<portlet:namespace />undoKaleoDefinitionVersion',
									function(event) {
										var A = AUI();

										var button = event.target;

										if (!button.get('disabled')) {
											<portlet:namespace />navigateKaleoDefinitionVersion('prev');
										}
									},
									['aui-base']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />updateAction',
									function(action) {
										var A = AUI();

										var form = A.one(document.<portlet:namespace />fm);

										form.attr('action', action);
									},
									['aui-base']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />updateContent',
									function() {
										var A = AUI();

										var content = A.one('#<portlet:namespace />content');

										var activeTab = <portlet:namespace />kaleoDesigner.contentTabView.getActiveTab();

										if (activeTab === <portlet:namespace />kaleoDesigner.sourceNode) {
											content.val(<portlet:namespace />kaleoDesigner.editor.get('value'));
										}
										else {
											content.val(<portlet:namespace />kaleoDesigner.getContent());
										}
									},
									['aui-base']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />closeKaleoDialog',
									function() {
										var dialog = Liferay.Util.getWindow();

										if (dialog) {
											dialog.destroy();
										}
									},
									['aui-base']
								);

								Liferay.provide(
									window,
									'<portlet:namespace />updateToolbarButtons',
									function() {
										var A = AUI();

										var draftVersion = A.one('#<portlet:namespace />draftVersion').val();
										var latestDraftVersion = A.one('#<portlet:namespace />latestDraftVersion').val();

										var latestButton = <portlet:namespace />kaleoDesignerToolbar.item(2);
										var redoButton = <portlet:namespace />kaleoDesignerToolbar.item(1);
										var undoButton = <portlet:namespace />kaleoDesignerToolbar.item(0);

										latestButton.set('disabled', (draftVersion === latestDraftVersion));

										redoButton.set('disabled', (draftVersion === latestDraftVersion));

										undoButton.set('disabled', (draftVersion === '1.0'));
									},
									['aui-base']
								);

								<%
								String saveCallback = ParamUtil.getString(request, "saveCallback");
								%>

								<c:if test="<%= (kaleoDefinitionVersion != null) && Validator.isNotNull(saveCallback) %>">
									Liferay.Util.getOpener()['<%= HtmlUtil.escapeJS(saveCallback) %>']('<%= HtmlUtil.escapeJS(name) %>', version, <%= draftVersion %>);
								</c:if>
							</aui:script>

							<aui:script use="liferay-kaleo-designer-utils,liferay-portlet-kaleo-designer">
								var emptyFn = A.Lang.emptyFn;

								var MAP_ROLE_TYPES = {
									regular: 1,
									site: 2,
									organization: 3
								};

								<portlet:namespace />kaleoDesigner = new Liferay.KaleoDesigner(
									{

										<%
										String availableFields = ParamUtil.getString(request, "availableFields");
										%>

										<c:if test="<%= Validator.isNotNull(availableFields) %>">
											availableFields: A.Object.getValue(window, '<%= HtmlUtil.escapeJS(availableFields) %>'.split('.')),
										</c:if>

										<%
										String availablePropertyModels = ParamUtil.getString(request, "availablePropertyModels", "Liferay.KaleoDesigner.AVAILABLE_PROPERTY_MODELS.KALEO_FORMS_EDIT");
										%>

										<c:if test="<%= Validator.isNotNull(availablePropertyModels) %>">
											availablePropertyModels: A.Object.getValue(window, '<%= HtmlUtil.escapeJS(availablePropertyModels) %>'.split('.')),
										</c:if>

										boundingBox: '#<portlet:namespace />propertyBuilder',
										data: {

											<%
											long kaleoProcessId = ParamUtil.getLong(request, "kaleoProcessId");
											%>

											kaleoProcessId: '<%= kaleoProcessId %>'
										},

										<c:if test="<%= Validator.isNotNull(content) %>">
											definition: '<%= HtmlUtil.escapeJS(content) %>',
										</c:if>

										<%
										String propertiesSaveCallback = ParamUtil.getString(request, "propertiesSaveCallback");
										%>

										<c:if test="<%= Validator.isNotNull(propertiesSaveCallback) %>">
											on: {
												save: Liferay.Util.getOpener()['<%= HtmlUtil.escapeJS(propertiesSaveCallback) %>']
											},
										</c:if>

										portletNamespace: '<portlet:namespace />',

										<%
										String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace");
										%>

										portletResourceNamespace: '<%= HtmlUtil.escapeJS(portletResourceNamespace) %>',
										srcNode: '#<portlet:namespace />propertyBuilderContent'
									}
								).render();

								var uploadFile = $('#<portlet:namespace />upload');

								uploadFile.on(
									'change',
									function(evt) {
										var files = evt.target.files;

										if (files) {
											var reader = new FileReader();

											reader.onloadend = function(evt) {

												if (evt.target.readyState == FileReader.DONE) {
													<portlet:namespace />kaleoDesigner.setEditorContent(evt.target.result);
												}
											};

											reader.readAsText(files[0]);
										}
									}
								);

								var uploadLink = $('#<portlet:namespace />uploadLink');

								uploadLink.on(
									'click',
									function(event) {
										event.preventDefault();
										uploadFile.trigger('click');
									}
								);

								<portlet:namespace />kaleoDesigner.contentTabView.after(
									{
										selectionChange: <portlet:namespace />afterTabViewChange
									}
								);

								var fields = <portlet:namespace />kaleoDesigner.get('fields');

								if (fields.size() == 0) {
									<portlet:namespace />kaleoDesigner.set(
										'fields',
										[
											{
												name: 'StartNode',
												type: 'start',
												xy: [100, 40]
											},

											{
												actions: {
													description: [Liferay.KaleoDesignerStrings.approve],
													executionType: ['onEntry'],
													name: [Liferay.KaleoDesignerStrings.approve],
													script: ['com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(com.liferay.portal.kernel.workflow.WorkflowConstants.getLabelStatus("approved"), workflowContext);'],
													scriptLanguage: ['groovy']
												},
												name: 'EndNode',
												type: 'end',
												xy: [100, 500]
											}
										]
									);

									<portlet:namespace />kaleoDesigner.connect('StartNode', 'EndNode');
								}

								var createRoleAutocomplete = function(inputNode, resultTextLocator, selectFn) {
									var instance = this;

									var roleType = 0;
									var roleTypeNode = inputNode.previous('[name=roleType]');

									if (roleTypeNode) {
										roleType = roleTypeNode.val();
									}

									var type = MAP_ROLE_TYPES[roleType] || 0;

									var autocomplete = Liferay.KaleoDesignerAutoCompleteUtil.create('<portlet:namespace />', inputNode, '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="roles" />', null, resultTextLocator, selectFn);

									autocomplete.set('requestTemplate', '&<portlet:namespace />type=' + type + '&<portlet:namespace />keywords={query}');

									autocomplete.sendRequest('');
								};

								var createUserAutocomplete = function(inputNode, resultTextLocator, selectFn) {
									var autocomplete = Liferay.KaleoDesignerAutoCompleteUtil.create('<portlet:namespace />', inputNode, '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="users" />', null, resultTextLocator, selectFn);

									autocomplete.sendRequest('');
								};

								A.getDoc().delegate(
									'focus',
									function(event) {
										var inputNode = event.currentTarget;

										var inputName = inputNode.attr('name');

										if ((inputName == 'roleName') || (inputName == 'roleNameAC')) {
											createRoleAutocomplete(
												inputNode,
												null,
												function(event) {
													var data = event.result.raw;
													var roleId = inputNode.next('[name=roleId]');

													if (roleId) {
														roleId.val(data.roleId);
													}
												}
											);
										}
										else if (inputName == 'fullName') {
											createUserAutocomplete(
												inputNode,
												inputName,
												function(event) {
													var data = event.result.raw;

													A.each(
														data,
														function(item, index, collection) {
															var input = inputNode.siblings('[name=' + index + ']').first();

															if (input) {
																input.val(data[index]);
															}
														}
													);
												}
											);
										}
									},
									'.assignments-cell-editor-input'
								);

								<c:choose>
									<c:when test="<%= kaleoDefinitionVersion == null %>">
										var titleComponent = Liferay.component('<portlet:namespace />title');

										var titlePlaceholderInput = titleComponent.get('inputPlaceholder');

										if (titlePlaceholderInput) {
											titlePlaceholderInput.after(
												'change',
												function(event) {
													<portlet:namespace />kaleoDesigner.set('definitionName', titleComponent.getValue());
												}
											);
										}
									</c:when>
									<c:otherwise>
										<portlet:namespace />initKaleoDefinitionVersionToolbar();
									</c:otherwise>
								</c:choose>

								var dialog = Liferay.Util.getWindow();

								if (dialog) {
									dialog.on(
										'visibleChange',
										function(event) {
											if (!event.newVal) {
												<c:choose>
													<c:when test="<%= (kaleoDefinition != null) && !kaleoDefinition.isActive() %>">
														if (confirm('<liferay-ui:message key="do-you-want-to-publish-this-draft" />')) {
															event.halt();

															<portlet:namespace />publishKaleoDefinitionVersion();
														}
													</c:when>
													<c:otherwise>

														<%
														boolean refreshOpenerOnClose = ParamUtil.getBoolean(request, "refreshOpenerOnClose");
														%>

														<c:if test="<%= Validator.isNotNull(portletResourceNamespace) && refreshOpenerOnClose %>">

															<%
															String openerWindowName = ParamUtil.getString(request, "openerWindowName");
															%>

															var openerWindow = Liferay.Util.getTop();

															<c:if test="<%= Validator.isNotNull(openerWindowName) %>">
																var openerDialog = Liferay.Util.getWindow('<%= HtmlUtil.escapeJS(openerWindowName) %>');

																openerWindow = openerDialog.iframe.node.get('contentWindow').getDOM();
															</c:if>

															openerWindow.Liferay.Portlet.refresh('#p_p_id<%= HtmlUtil.escapeJS(portletResourceNamespace) %>');
														</c:if>
													</c:otherwise>
												</c:choose>
											}
										}
									);
								}
							</aui:script>
						</aui:fieldset>
					</aui:fieldset-group>

					<aui:button-row>
						<c:if test="<%= kaleoDesignerDisplayContext.isPublishKaleoDefinitionVersionButtonVisible(permissionChecker) %>">
							<aui:button
								cssClass="btn-lg"
								onClick='<%= renderResponse.getNamespace() + "publishKaleoDefinitionVersion();" %>'
								primary="<%= true %>"
								value="<%= kaleoDesignerDisplayContext.getPublishKaleoDefinitionVersionButtonLabel(kaleoDefinitionVersion) %>"
							/>
						</c:if>

						<c:if test="<%= kaleoDesignerDisplayContext.isSaveKaleoDefinitionVersionButtonVisible(permissionChecker, kaleoDefinitionVersion) %>">
							<aui:button
								cssClass="btn-lg"
								onClick='<%= renderResponse.getNamespace() + "addKaleoDefinitionVersion();" %>'
								value="save"
							/>
						</c:if>

						<span class="lfr-portlet-workflowdesigner-message" id="<portlet:namespace />toolbarMessage"></span>
					</aui:button-row>
				</aui:form>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="no-workflow-engine-is-deployed" />
		</div>
	</c:otherwise>
</c:choose>