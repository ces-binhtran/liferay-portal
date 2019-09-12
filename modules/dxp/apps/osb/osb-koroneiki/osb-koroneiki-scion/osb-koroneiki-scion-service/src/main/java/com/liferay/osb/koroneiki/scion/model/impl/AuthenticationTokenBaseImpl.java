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

package com.liferay.osb.koroneiki.scion.model.impl;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenLocalServiceUtil;

/**
 * The extended model base implementation for the AuthenticationToken service. Represents a row in the &quot;Koroneiki_AuthenticationToken&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AuthenticationTokenImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenImpl
 * @see AuthenticationToken
 * @generated
 */
public abstract class AuthenticationTokenBaseImpl
	extends AuthenticationTokenModelImpl implements AuthenticationToken {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a authentication token model instance should use the <code>AuthenticationToken</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			AuthenticationTokenLocalServiceUtil.addAuthenticationToken(this);
		}
		else {
			AuthenticationTokenLocalServiceUtil.updateAuthenticationToken(this);
		}
	}

}