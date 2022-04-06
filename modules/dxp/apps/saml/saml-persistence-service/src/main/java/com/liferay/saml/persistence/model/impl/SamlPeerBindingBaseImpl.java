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

package com.liferay.saml.persistence.model.impl;

import com.liferay.saml.persistence.model.SamlPeerBinding;
import com.liferay.saml.persistence.service.SamlPeerBindingLocalServiceUtil;

/**
 * The extended model base implementation for the SamlPeerBinding service. Represents a row in the &quot;SamlPeerBinding&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SamlPeerBindingImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlPeerBindingImpl
 * @see SamlPeerBinding
 * @generated
 */
public abstract class SamlPeerBindingBaseImpl
	extends SamlPeerBindingModelImpl implements SamlPeerBinding {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a saml peer binding model instance should use the <code>SamlPeerBinding</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SamlPeerBindingLocalServiceUtil.addSamlPeerBinding(this);
		}
		else {
			SamlPeerBindingLocalServiceUtil.updateSamlPeerBinding(this);
		}
	}

}