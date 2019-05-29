<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Project project = (Project)request.getAttribute(TaprootWebKeys.PROJECT);

long projectId = BeanParamUtil.getLong(project, request, "projectId");
%>

<portlet:actionURL name="/projects_admin/assign_project_team" var="assignProjectTeamURL">
	<portlet:param name="projectId" value="<%= String.valueOf(project.getProjectId()) %>" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= assignProjectTeamURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="projectId" type="hidden" value="<%= projectId %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:select label="team" name="teamId">
				<aui:option value="" />

				<%
				for (Team team : TeamLocalServiceUtil.getTeams(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
				%>

					<aui:option label="<%= team.getName() %>" value="<%= team.getTeamId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select label="role" name="teamRoleId">
				<aui:option value="" />

				<%
				for (TeamRole teamRole : TeamRoleLocalServiceUtil.getTeamRoles(TeamRoleType.PROJECT, QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
				%>

					<aui:option label="<%= teamRole.getName() %>" value="<%= teamRole.getTeamRoleId() %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>