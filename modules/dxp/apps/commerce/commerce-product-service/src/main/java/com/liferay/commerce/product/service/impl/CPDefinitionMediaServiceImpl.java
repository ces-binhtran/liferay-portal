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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CPDefinitionMedia;
import com.liferay.commerce.product.model.CPMediaType;
import com.liferay.commerce.product.service.base.CPDefinitionMediaServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 */
public class CPDefinitionMediaServiceImpl
	extends CPDefinitionMediaServiceBaseImpl {

	@Override
	public CPDefinitionMedia addCPDefinitionMedia(
			long cpDefinitionId, long fileEntryId, String ddmContent,
			int priority, long cpMediaTypeId, ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionMediaLocalService.addCPDefinitionMedia(
			cpDefinitionId, fileEntryId, ddmContent, priority, cpMediaTypeId,
			serviceContext);
	}

	@Override
	public CPMediaType deleteCPMediaType(long cpMediaTypeId)
		throws PortalException {

		return cpDefinitionMediaLocalService.deleteCPMediaType(cpMediaTypeId);
	}

	@Override
	public List<CPDefinitionMedia> getDefinitionMedias(
		long cpDefinitionId, int start, int end) {

		return cpDefinitionMediaLocalService.getDefinitionMedias(
			cpDefinitionId, start, end);
	}

	@Override
	public List<CPDefinitionMedia> getDefinitionMedias(
		long cpDefinitionId, int start, int end,
		OrderByComparator<CPDefinitionMedia> orderByComparator) {

		return cpDefinitionMediaLocalService.getDefinitionMedias(
			cpDefinitionId, start, end, orderByComparator);
	}

	@Override
	public int getDefinitionMediasCount(long cpDefinitionId) {
		return cpDefinitionMediaLocalService.getDefinitionMediasCount(
			cpDefinitionId);
	}

	@Override
	public Hits search(SearchContext searchContext) {
		return cpDefinitionMediaLocalService.search(searchContext);
	}

	@Override
	public BaseModelSearchResult<CPDefinitionMedia> searchCPDefinitionMedias(
			long companyId, long groupId, long cpDefinitionId, String keywords,
			int start, int end, Sort sort)
		throws PortalException {

		return cpDefinitionMediaLocalService.searchCPDefinitionMedias(
			companyId, groupId, cpDefinitionId, keywords, start, end, sort);
	}

	@Override
	public CPDefinitionMedia updateCPDefinitionMedia(
			long cpDefinitionMediaId, String ddmContent, int priority,
			long cpMediaTypeId, ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionMediaLocalService.updateCPDefinitionMedia(
			cpDefinitionMediaId, ddmContent, priority, cpMediaTypeId,
			serviceContext);
	}

}