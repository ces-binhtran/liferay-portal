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

<clay:container-fluid
	cssClass="container-view"
>
	<clay:row>
		<clay:content-row
			noGutters="true"
			verticalAlign="center"
		>
			<clay:content-col
				expand="<%= true %>"
			>
				<span>We use cookies to deliver personalized content, analyze trends, administer the site, track user movements on the site, and collect demographic information about our user base as a whole. Accept all cookies for the best possible experience on our website or manage your preferences. Visit our Privacy Policy.</span>
			</clay:content-col>

			<clay:content-col>
				<clay:button
					cssClass="cookies-banner-button-configuration"
					displayType="link"
					label="Configuration"
					small="<%= true %>"
				/>
			</clay:content-col>

			<clay:content-col>
				<clay:button
					cssClass="cookies-banner-button-accept"
					displayType="secondary"
					label="Accept all"
					small="<%= true %>"
				/>
			</clay:content-col>

			<clay:content-col>
				<clay:button
					cssClass="cookies-banner-button-decline"
					displayType="primary"
					label="Decline All"
					small="<%= true %>"
				/>
			</clay:content-col>
		</clay:content-row>
	</clay:row>
</clay:container-fluid>