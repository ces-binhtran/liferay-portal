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

package com.liferay.portal.reports.engine.console.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface SourceFinder {
	public int countByG_N_DU(long groupId, String name, String driverUrl,
		boolean andOperator);

	public int filterCountByG_N_DU(long groupId, String name, String driverUrl,
		boolean andOperator);

	public java.util.List<com.liferay.portal.reports.engine.console.model.Source> filterFindByG_N_DU(
		long groupId, String name, String driverUrl, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.reports.engine.console.model.Source> orderByComparator);

	public java.util.List<com.liferay.portal.reports.engine.console.model.Source> findByG_N_DU(
		long groupId, String name, String driverUrl, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.reports.engine.console.model.Source> orderByComparator);
}