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

package com.liferay.commerce.bom.model.impl;

import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.CommerceBOMEntryLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceBOMEntry service. Represents a row in the &quot;CommerceBOMEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceBOMEntryImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntryImpl
 * @see CommerceBOMEntry
 * @generated
 */
public abstract class CommerceBOMEntryBaseImpl
	extends CommerceBOMEntryModelImpl implements CommerceBOMEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce bom entry model instance should use the <code>CommerceBOMEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceBOMEntryLocalServiceUtil.addCommerceBOMEntry(this);
		}
		else {
			CommerceBOMEntryLocalServiceUtil.updateCommerceBOMEntry(this);
		}
	}

}