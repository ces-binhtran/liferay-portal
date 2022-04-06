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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the RegionLocalization service. Represents a row in the &quot;RegionLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.RegionLocalizationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.RegionLocalizationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionLocalization
 * @generated
 */
@ProviderType
public interface RegionLocalizationModel
	extends BaseModel<RegionLocalization>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a region localization model instance should use the {@link RegionLocalization} interface instead.
	 */

	/**
	 * Returns the primary key of this region localization.
	 *
	 * @return the primary key of this region localization
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this region localization.
	 *
	 * @param primaryKey the primary key of this region localization
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this region localization.
	 *
	 * @return the mvcc version of this region localization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this region localization.
	 *
	 * @param mvccVersion the mvcc version of this region localization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the region localization ID of this region localization.
	 *
	 * @return the region localization ID of this region localization
	 */
	public long getRegionLocalizationId();

	/**
	 * Sets the region localization ID of this region localization.
	 *
	 * @param regionLocalizationId the region localization ID of this region localization
	 */
	public void setRegionLocalizationId(long regionLocalizationId);

	/**
	 * Returns the company ID of this region localization.
	 *
	 * @return the company ID of this region localization
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this region localization.
	 *
	 * @param companyId the company ID of this region localization
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the region ID of this region localization.
	 *
	 * @return the region ID of this region localization
	 */
	public long getRegionId();

	/**
	 * Sets the region ID of this region localization.
	 *
	 * @param regionId the region ID of this region localization
	 */
	public void setRegionId(long regionId);

	/**
	 * Returns the language ID of this region localization.
	 *
	 * @return the language ID of this region localization
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this region localization.
	 *
	 * @param languageId the language ID of this region localization
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the title of this region localization.
	 *
	 * @return the title of this region localization
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this region localization.
	 *
	 * @param title the title of this region localization
	 */
	public void setTitle(String title);

	@Override
	public RegionLocalization cloneWithOriginalValues();

}