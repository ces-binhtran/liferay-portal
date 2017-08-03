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

SamlSpIdpConnection samlSpIdpConnection = (SamlSpIdpConnection)request.getAttribute(SamlWebKeys.SAML_SP_IDP_CONNECTION);

String samlIdpEntityId = samlProviderConfiguration.defaultIdPEntityId();

long clockSkew = GetterUtil.getLong(request.getAttribute(SamlWebKeys.SAML_CLOCK_SKEW), samlProviderConfiguration.clockSkew());
%>

<portlet:actionURL name="/admin/updateIdentityProviderConnection" var="updateIdentityProviderConnectionURL">
	<portlet:param name="tabs1" value="identity-provider-connection" />
</portlet:actionURL>

<aui:form action="<%= updateIdentityProviderConnectionURL %>" enctype="multipart/form-data">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= DuplicateSamlSpIdpConnectionSamlIdpEntityIdException.class %>" message="please-enter-a-unique-identity-provider-entity-id" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionMetadataUrlException.class %>" message="please-enter-a-valid-metadata-endpoint-url" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionMetadataXmlException.class %>" message="please-enter-a-valid-metadata-xml" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionSamlIdpEntityIdException.class %>" message="please-enter-a-valid-identity-provider-entity-id" />

	<aui:model-context bean="<%= samlSpIdpConnection %>" model="<%= SamlSpIdpConnection.class %>" />

	<aui:input name="samlSpIdpConnectionId" type="hidden" />

	<liferay-util:dynamic-include key="com.liferay.saml.web#/admin/edit_identity_provider_connection.jsp#pre" />

	<aui:fieldset label="general">
		<aui:input name="name" required="<%= true %>" />

		<aui:input helpMessage="identity-provider-connection-entity-id-help" label="saml-entity-id" name="samlIdpEntityId" required="<%= true %>" value="<%= samlIdpEntityId %>" />

		<aui:input helpMessage="saml-sp-clock-skew-description" label="saml-sp-clock-skew" name="clockSkew" value="<%= String.valueOf(clockSkew) %>" />

		<aui:input helpMessage="force-authn-help" name="forceAuthn" />
	</aui:fieldset>

	<aui:fieldset helpMessage="identity-provider-metadata-help" label="metadata">
		<aui:input name="metadataUrl" />

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "uploadMetadataXml();" %>' value="upload-metadata-xml" />
		</aui:button-row>

		<div class="hide" id="<portlet:namespace />uploadMetadataXmlForm">
			<aui:fieldset label="upload-metadata">
				<aui:input name="metadataXml" type="file" />
			</aui:fieldset>
		</div>
	</aui:fieldset>

	<aui:fieldset label="name-identifier">
		<aui:select label="name-identifier-format" name="nameIdFormat">
			<aui:option label="email-address" value="<%= nameIdTypeValues.getEmail() %>" />
			<aui:option label="encrypted" value="<%= nameIdTypeValues.getEncrypted() %>" />
			<aui:option label="entity" value="<%= nameIdTypeValues.getEntity() %>" />
			<aui:option label="kerberos" value="<%= nameIdTypeValues.getKerberos() %>" />
			<aui:option label="persistent" value="<%= nameIdTypeValues.getPersistent() %>" />
			<aui:option label="transient" value="<%= nameIdTypeValues.getTransient() %>" />
			<aui:option label="unspecified" value="<%= nameIdTypeValues.getUnspecified() %>" />
			<aui:option label="windows-domain-qualified-name" value="<%= nameIdTypeValues.getWinDomainQualified() %>" />
			<aui:option label="x509-subject-name" value="<%= nameIdTypeValues.getX509Subject() %>" />
		</aui:select>
	</aui:fieldset>

	<aui:fieldset label="attributes">
		<aui:input helpMessage="attribute-mapping-help" label="attribute-mapping" name="userAttributeMappings" />
	</aui:fieldset>

	<liferay-util:dynamic-include key="com.liferay.saml.web#/admin/edit_identity_provider_connection.jsp#post" />

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />uploadMetadataXml',
		function() {
			var A = AUI();

			var uploadMetadataXmlForm = A.one('#<portlet:namespace />uploadMetadataXmlForm');

			if (uploadMetadataXmlForm) {
				uploadMetadataXmlForm.show();
			}
		},
		['aui-base']
	);
</aui:script>