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

package com.liferay.portal.reports.engine.console.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Source service. Represents a row in the &quot;Reports_Source&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.reports.engine.console.model.impl.SourceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.reports.engine.console.model.impl.SourceImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Source
 * @generated
 */
@ProviderType
public interface SourceModel
	extends BaseModel<Source>, LocalizedModel, ShardedModel,
			StagedGroupedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a source model instance should use the {@link Source} interface instead.
	 */

	/**
	 * Returns the primary key of this source.
	 *
	 * @return the primary key of this source
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this source.
	 *
	 * @param primaryKey the primary key of this source
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this source.
	 *
	 * @return the uuid of this source
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this source.
	 *
	 * @param uuid the uuid of this source
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the source ID of this source.
	 *
	 * @return the source ID of this source
	 */
	public long getSourceId();

	/**
	 * Sets the source ID of this source.
	 *
	 * @param sourceId the source ID of this source
	 */
	public void setSourceId(long sourceId);

	/**
	 * Returns the group ID of this source.
	 *
	 * @return the group ID of this source
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this source.
	 *
	 * @param groupId the group ID of this source
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this source.
	 *
	 * @return the company ID of this source
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this source.
	 *
	 * @param companyId the company ID of this source
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this source.
	 *
	 * @return the user ID of this source
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this source.
	 *
	 * @param userId the user ID of this source
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this source.
	 *
	 * @return the user uuid of this source
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this source.
	 *
	 * @param userUuid the user uuid of this source
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this source.
	 *
	 * @return the user name of this source
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this source.
	 *
	 * @param userName the user name of this source
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this source.
	 *
	 * @return the create date of this source
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this source.
	 *
	 * @param createDate the create date of this source
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this source.
	 *
	 * @return the modified date of this source
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this source.
	 *
	 * @param modifiedDate the modified date of this source
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the last publish date of this source.
	 *
	 * @return the last publish date of this source
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this source.
	 *
	 * @param lastPublishDate the last publish date of this source
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the name of this source.
	 *
	 * @return the name of this source
	 */
	public String getName();

	/**
	 * Returns the localized name of this source in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this source
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this source in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this source. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this source in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this source
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this source in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this source
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this source.
	 *
	 * @return the locales and localized names of this source
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this source.
	 *
	 * @param name the name of this source
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this source in the language.
	 *
	 * @param name the localized name of this source
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this source in the language, and sets the default locale.
	 *
	 * @param name the localized name of this source
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this source from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this source
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this source from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this source
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the driver class name of this source.
	 *
	 * @return the driver class name of this source
	 */
	@AutoEscape
	public String getDriverClassName();

	/**
	 * Sets the driver class name of this source.
	 *
	 * @param driverClassName the driver class name of this source
	 */
	public void setDriverClassName(String driverClassName);

	/**
	 * Returns the driver url of this source.
	 *
	 * @return the driver url of this source
	 */
	@AutoEscape
	public String getDriverUrl();

	/**
	 * Sets the driver url of this source.
	 *
	 * @param driverUrl the driver url of this source
	 */
	public void setDriverUrl(String driverUrl);

	/**
	 * Returns the driver user name of this source.
	 *
	 * @return the driver user name of this source
	 */
	@AutoEscape
	public String getDriverUserName();

	/**
	 * Sets the driver user name of this source.
	 *
	 * @param driverUserName the driver user name of this source
	 */
	public void setDriverUserName(String driverUserName);

	/**
	 * Returns the driver password of this source.
	 *
	 * @return the driver password of this source
	 */
	@AutoEscape
	public String getDriverPassword();

	/**
	 * Sets the driver password of this source.
	 *
	 * @param driverPassword the driver password of this source
	 */
	public void setDriverPassword(String driverPassword);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Source cloneWithOriginalValues();

}