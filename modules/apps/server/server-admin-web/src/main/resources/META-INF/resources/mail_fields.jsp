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
long companyId = ParamUtil.getLong(request, "preferencesCompanyId");

PortletPreferences companyPreferences = PrefsPropsUtil.getPreferences(companyId);

PortletPreferences systemPreferences = PrefsPropsUtil.getPreferences(0L);

Function<String, String> getPreferenceFunction = (String key) -> companyPreferences.getValue(key, systemPreferences.getValue(key, PropsUtil.get(key)));
%>

<aui:fieldset-group markupView="lexicon">
	<aui:fieldset>
		<aui:input name="preferencesCompanyId" type="hidden" value="<%= companyId %>" />

		<aui:input cssClass="lfr-input-text-container" label="incoming-pop-server" name="pop3Host" type="text" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_POP3_HOST) %>" />

		<aui:input cssClass="lfr-input-text-container" label="incoming-port" name="pop3Port" type="text" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_POP3_PORT) %>" />

		<aui:input label="use-a-secure-network-connection" name="pop3Secure" type="checkbox" value='<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_STORE_PROTOCOL).equals("pop3s") %>' />

		<aui:input autocomplete="new-password" cssClass="lfr-input-text-container" label="user-name" name="pop3User" type="text" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_POP3_USER) %>" />

		<%
		String pop3Password = getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_POP3_PASSWORD);

		if (Validator.isNotNull(pop3Password)) {
			pop3Password = Portal.TEMP_OBFUSCATION_VALUE;
		}
		%>

		<aui:input autocomplete="new-password" cssClass="lfr-input-text-container" label="password" name="pop3Password" type="password" value="<%= pop3Password %>" />

		<aui:input cssClass="lfr-input-text-container" label="outgoing-smtp-server" name="smtpHost" type="text" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_SMTP_HOST) %>" />

		<aui:input cssClass="lfr-input-text-container" label="outgoing-port" name="smtpPort" type="text" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_SMTP_PORT) %>" />

		<aui:input label="use-a-secure-network-connection" name="smtpSecure" type="checkbox" value='<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL).equals("smtps") %>' />

		<aui:input label="enable-starttls" name="smtpStartTLSEnable" type="checkbox" value="<%= GetterUtil.getBoolean(getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_SMTP_STARTTLS_ENABLE)) %>" />

		<aui:input autocomplete="new-password" cssClass="lfr-input-text-container" label="user-name" name="smtpUser" type="text" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_SMTP_USER) %>" />

		<%
		String smtpPassword = getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_SMTP_PASSWORD);

		if (Validator.isNotNull(smtpPassword)) {
			smtpPassword = Portal.TEMP_OBFUSCATION_VALUE;
		}
		%>

		<aui:input autocomplete="new-password" cssClass="lfr-input-text-container" label="password" name="smtpPassword" type="password" value="<%= smtpPassword %>" />

		<aui:input cssClass="lfr-textarea-container" label="manually-specify-additional-javamail-properties-to-override-the-above-configuration" name="advancedProperties" type="textarea" value="<%= getPreferenceFunction.apply(PropsKeys.MAIL_SESSION_MAIL_ADVANCED_PROPERTIES) %>" />
	</aui:fieldset>
</aui:fieldset-group>