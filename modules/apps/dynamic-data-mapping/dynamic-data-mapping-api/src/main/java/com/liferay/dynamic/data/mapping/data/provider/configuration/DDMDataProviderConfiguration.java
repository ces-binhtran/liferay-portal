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

package com.liferay.dynamic.data.mapping.data.provider.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Leonardo Barros
 */
@ExtendedObjectClassDefinition(
	category = "data-providers",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.dynamic.data.mapping.data.provider.configuration.DDMDataProviderConfiguration",
	localization = "content/Language",
	name = "ddm-data-provider-configuration-name"
)
public interface DDMDataProviderConfiguration {

	@Meta.AD(
		deflt = "false", description = "access-local-network-description",
		name = "access-local-network-name", required = false
	)
	public boolean accessLocalNetwork();

	@Meta.AD(
		deflt = "false",
		description = "trust-self-signed-certificates-description",
		name = "trust-self-signed-certificates-name", required = false
	)
	public default boolean trustSelfSignedCertificates() {
		return false;
	}

}