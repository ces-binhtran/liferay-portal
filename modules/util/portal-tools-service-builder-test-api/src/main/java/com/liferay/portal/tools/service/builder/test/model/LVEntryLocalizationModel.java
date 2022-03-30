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

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.version.VersionedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LVEntryLocalization service. Represents a row in the &quot;LVEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryLocalizationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.LVEntryLocalizationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LVEntryLocalization
 * @generated
 */
@ProviderType
public interface LVEntryLocalizationModel
	extends BaseModel<LVEntryLocalization>, MVCCModel, ShardedModel,
			VersionedModel<LVEntryLocalizationVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a lv entry localization model instance should use the {@link LVEntryLocalization} interface instead.
	 */

	/**
	 * Returns the primary key of this lv entry localization.
	 *
	 * @return the primary key of this lv entry localization
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this lv entry localization.
	 *
	 * @param primaryKey the primary key of this lv entry localization
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this lv entry localization.
	 *
	 * @return the mvcc version of this lv entry localization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this lv entry localization.
	 *
	 * @param mvccVersion the mvcc version of this lv entry localization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the head ID of this lv entry localization.
	 *
	 * @return the head ID of this lv entry localization
	 */
	@Override
	public long getHeadId();

	/**
	 * Sets the head ID of this lv entry localization.
	 *
	 * @param headId the head ID of this lv entry localization
	 */
	@Override
	public void setHeadId(long headId);

	/**
	 * Returns the lv entry localization ID of this lv entry localization.
	 *
	 * @return the lv entry localization ID of this lv entry localization
	 */
	public long getLvEntryLocalizationId();

	/**
	 * Sets the lv entry localization ID of this lv entry localization.
	 *
	 * @param lvEntryLocalizationId the lv entry localization ID of this lv entry localization
	 */
	public void setLvEntryLocalizationId(long lvEntryLocalizationId);

	/**
	 * Returns the company ID of this lv entry localization.
	 *
	 * @return the company ID of this lv entry localization
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this lv entry localization.
	 *
	 * @param companyId the company ID of this lv entry localization
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the lv entry ID of this lv entry localization.
	 *
	 * @return the lv entry ID of this lv entry localization
	 */
	public long getLvEntryId();

	/**
	 * Sets the lv entry ID of this lv entry localization.
	 *
	 * @param lvEntryId the lv entry ID of this lv entry localization
	 */
	public void setLvEntryId(long lvEntryId);

	/**
	 * Returns the language ID of this lv entry localization.
	 *
	 * @return the language ID of this lv entry localization
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this lv entry localization.
	 *
	 * @param languageId the language ID of this lv entry localization
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the title of this lv entry localization.
	 *
	 * @return the title of this lv entry localization
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this lv entry localization.
	 *
	 * @param title the title of this lv entry localization
	 */
	public void setTitle(String title);

	/**
	 * Returns the content of this lv entry localization.
	 *
	 * @return the content of this lv entry localization
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this lv entry localization.
	 *
	 * @param content the content of this lv entry localization
	 */
	public void setContent(String content);

	@Override
	public LVEntryLocalization cloneWithOriginalValues();

}