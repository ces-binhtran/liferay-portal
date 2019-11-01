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

package com.liferay.osb.koroneiki.phytohormone.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Entitlement service. Represents a row in the &quot;Koroneiki_Entitlement&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementImpl"
)
@ProviderType
public interface Entitlement extends EntitlementModel, PersistedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Entitlement, Long> ENTITLEMENT_ID_ACCESSOR =
		new Accessor<Entitlement, Long>() {

			@Override
			public Long get(Entitlement entitlement) {
				return entitlement.getEntitlementId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Entitlement> getTypeClass() {
				return Entitlement.class;
			}

		};

	public EntitlementDefinition getEntitlementDefinition()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getEntitlementDefinitionKey()
		throws com.liferay.portal.kernel.exception.PortalException;

}