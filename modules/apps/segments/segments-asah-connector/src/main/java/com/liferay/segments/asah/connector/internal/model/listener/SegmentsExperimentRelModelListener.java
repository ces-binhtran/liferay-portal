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

package com.liferay.segments.asah.connector.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.asah.connector.internal.client.AsahFaroBackendClientFactory;
import com.liferay.segments.asah.connector.internal.processor.AsahSegmentsExperimentProcessor;
import com.liferay.segments.model.SegmentsExperimentRel;
import com.liferay.segments.service.SegmentsEntryLocalService;
import com.liferay.segments.service.SegmentsExperienceLocalService;
import com.liferay.segments.service.SegmentsExperimentRelLocalService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 */
@Component(immediate = true, service = ModelListener.class)
public class SegmentsExperimentRelModelListener
	extends BaseModelListener<SegmentsExperimentRel> {

	@Override
	public void onAfterCreate(SegmentsExperimentRel segmentsExperimentRel)
		throws ModelListenerException {

		try {
			if (segmentsExperimentRel.isControl()) {
				return;
			}

			_asahSegmentsExperimentProcessor.processUpdateSegmentsExperimentRel(
				segmentsExperimentRel.getSegmentsExperimentKey(),
				_segmentsExperimentRelLocalService.getSegmentsExperimentRels(
					segmentsExperimentRel.getSegmentsExperimentId()));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to create segments experiment rel " +
						segmentsExperimentRel.getSegmentsExperimentRelId(),
					e);
			}
		}
	}

	@Override
	public void onAfterUpdate(SegmentsExperimentRel segmentsExperimentRel)
		throws ModelListenerException {

		try {
			_asahSegmentsExperimentProcessor.processUpdateSegmentsExperimentRel(
				segmentsExperimentRel.getSegmentsExperimentKey(),
				_segmentsExperimentRelLocalService.getSegmentsExperimentRels(
					segmentsExperimentRel.getSegmentsExperimentId()));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to update segments experiment rel " +
						segmentsExperimentRel.getSegmentsExperimentRelId(),
					e);
			}
		}
	}

	@Activate
	protected void activate() {
		_asahSegmentsExperimentProcessor = new AsahSegmentsExperimentProcessor(
			_asahFaroBackendClientFactory, _companyLocalService,
			_groupLocalService, _layoutLocalService, _portal,
			_segmentsEntryLocalService, _segmentsExperienceLocalService);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsExperimentRelModelListener.class);

	@Reference
	private AsahFaroBackendClientFactory _asahFaroBackendClientFactory;

	private AsahSegmentsExperimentProcessor _asahSegmentsExperimentProcessor;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	@Reference
	private SegmentsExperimentRelLocalService
		_segmentsExperimentRelLocalService;

}