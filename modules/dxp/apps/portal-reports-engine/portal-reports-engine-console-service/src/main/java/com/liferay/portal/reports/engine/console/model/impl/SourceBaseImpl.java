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

package com.liferay.portal.reports.engine.console.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.SourceLocalServiceUtil;

/**
 * The extended model base implementation for the Source service. Represents a row in the &quot;Reports_Source&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SourceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SourceImpl
 * @see Source
 * @generated
 */
@ProviderType
public abstract class SourceBaseImpl extends SourceModelImpl implements Source {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a source model instance should use the {@link Source} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SourceLocalServiceUtil.addSource(this);
		}
		else {
			SourceLocalServiceUtil.updateSource(this);
		}
	}
}