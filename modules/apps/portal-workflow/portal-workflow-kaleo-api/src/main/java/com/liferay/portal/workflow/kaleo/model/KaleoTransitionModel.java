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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the KaleoTransition service. Represents a row in the &quot;KaleoTransition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransition
 * @generated
 */
@ProviderType
public interface KaleoTransitionModel
	extends BaseModel<KaleoTransition>, GroupedModel, LocalizedModel, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo transition model instance should use the {@link KaleoTransition} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo transition.
	 *
	 * @return the primary key of this kaleo transition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo transition.
	 *
	 * @param primaryKey the primary key of this kaleo transition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this kaleo transition.
	 *
	 * @return the mvcc version of this kaleo transition
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this kaleo transition.
	 *
	 * @param mvccVersion the mvcc version of this kaleo transition
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the kaleo transition ID of this kaleo transition.
	 *
	 * @return the kaleo transition ID of this kaleo transition
	 */
	public long getKaleoTransitionId();

	/**
	 * Sets the kaleo transition ID of this kaleo transition.
	 *
	 * @param kaleoTransitionId the kaleo transition ID of this kaleo transition
	 */
	public void setKaleoTransitionId(long kaleoTransitionId);

	/**
	 * Returns the group ID of this kaleo transition.
	 *
	 * @return the group ID of this kaleo transition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo transition.
	 *
	 * @param groupId the group ID of this kaleo transition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo transition.
	 *
	 * @return the company ID of this kaleo transition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo transition.
	 *
	 * @param companyId the company ID of this kaleo transition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo transition.
	 *
	 * @return the user ID of this kaleo transition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo transition.
	 *
	 * @param userId the user ID of this kaleo transition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo transition.
	 *
	 * @return the user uuid of this kaleo transition
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kaleo transition.
	 *
	 * @param userUuid the user uuid of this kaleo transition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo transition.
	 *
	 * @return the user name of this kaleo transition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kaleo transition.
	 *
	 * @param userName the user name of this kaleo transition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo transition.
	 *
	 * @return the create date of this kaleo transition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo transition.
	 *
	 * @param createDate the create date of this kaleo transition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo transition.
	 *
	 * @return the modified date of this kaleo transition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo transition.
	 *
	 * @param modifiedDate the modified date of this kaleo transition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the kaleo definition ID of this kaleo transition.
	 *
	 * @return the kaleo definition ID of this kaleo transition
	 */
	public long getKaleoDefinitionId();

	/**
	 * Sets the kaleo definition ID of this kaleo transition.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID of this kaleo transition
	 */
	public void setKaleoDefinitionId(long kaleoDefinitionId);

	/**
	 * Returns the kaleo definition version ID of this kaleo transition.
	 *
	 * @return the kaleo definition version ID of this kaleo transition
	 */
	public long getKaleoDefinitionVersionId();

	/**
	 * Sets the kaleo definition version ID of this kaleo transition.
	 *
	 * @param kaleoDefinitionVersionId the kaleo definition version ID of this kaleo transition
	 */
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId);

	/**
	 * Returns the kaleo node ID of this kaleo transition.
	 *
	 * @return the kaleo node ID of this kaleo transition
	 */
	public long getKaleoNodeId();

	/**
	 * Sets the kaleo node ID of this kaleo transition.
	 *
	 * @param kaleoNodeId the kaleo node ID of this kaleo transition
	 */
	public void setKaleoNodeId(long kaleoNodeId);

	/**
	 * Returns the name of this kaleo transition.
	 *
	 * @return the name of this kaleo transition
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this kaleo transition.
	 *
	 * @param name the name of this kaleo transition
	 */
	public void setName(String name);

	/**
	 * Returns the label of this kaleo transition.
	 *
	 * @return the label of this kaleo transition
	 */
	public String getLabel();

	/**
	 * Returns the localized label of this kaleo transition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized label of this kaleo transition
	 */
	@AutoEscape
	public String getLabel(Locale locale);

	/**
	 * Returns the localized label of this kaleo transition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this kaleo transition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getLabel(Locale locale, boolean useDefault);

	/**
	 * Returns the localized label of this kaleo transition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized label of this kaleo transition
	 */
	@AutoEscape
	public String getLabel(String languageId);

	/**
	 * Returns the localized label of this kaleo transition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this kaleo transition
	 */
	@AutoEscape
	public String getLabel(String languageId, boolean useDefault);

	@AutoEscape
	public String getLabelCurrentLanguageId();

	@AutoEscape
	public String getLabelCurrentValue();

	/**
	 * Returns a map of the locales and localized labels of this kaleo transition.
	 *
	 * @return the locales and localized labels of this kaleo transition
	 */
	public Map<Locale, String> getLabelMap();

	/**
	 * Sets the label of this kaleo transition.
	 *
	 * @param label the label of this kaleo transition
	 */
	public void setLabel(String label);

	/**
	 * Sets the localized label of this kaleo transition in the language.
	 *
	 * @param label the localized label of this kaleo transition
	 * @param locale the locale of the language
	 */
	public void setLabel(String label, Locale locale);

	/**
	 * Sets the localized label of this kaleo transition in the language, and sets the default locale.
	 *
	 * @param label the localized label of this kaleo transition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setLabel(String label, Locale locale, Locale defaultLocale);

	public void setLabelCurrentLanguageId(String languageId);

	/**
	 * Sets the localized labels of this kaleo transition from the map of locales and localized labels.
	 *
	 * @param labelMap the locales and localized labels of this kaleo transition
	 */
	public void setLabelMap(Map<Locale, String> labelMap);

	/**
	 * Sets the localized labels of this kaleo transition from the map of locales and localized labels, and sets the default locale.
	 *
	 * @param labelMap the locales and localized labels of this kaleo transition
	 * @param defaultLocale the default locale
	 */
	public void setLabelMap(Map<Locale, String> labelMap, Locale defaultLocale);

	/**
	 * Returns the description of this kaleo transition.
	 *
	 * @return the description of this kaleo transition
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this kaleo transition.
	 *
	 * @param description the description of this kaleo transition
	 */
	public void setDescription(String description);

	/**
	 * Returns the source kaleo node ID of this kaleo transition.
	 *
	 * @return the source kaleo node ID of this kaleo transition
	 */
	public long getSourceKaleoNodeId();

	/**
	 * Sets the source kaleo node ID of this kaleo transition.
	 *
	 * @param sourceKaleoNodeId the source kaleo node ID of this kaleo transition
	 */
	public void setSourceKaleoNodeId(long sourceKaleoNodeId);

	/**
	 * Returns the source kaleo node name of this kaleo transition.
	 *
	 * @return the source kaleo node name of this kaleo transition
	 */
	@AutoEscape
	public String getSourceKaleoNodeName();

	/**
	 * Sets the source kaleo node name of this kaleo transition.
	 *
	 * @param sourceKaleoNodeName the source kaleo node name of this kaleo transition
	 */
	public void setSourceKaleoNodeName(String sourceKaleoNodeName);

	/**
	 * Returns the target kaleo node ID of this kaleo transition.
	 *
	 * @return the target kaleo node ID of this kaleo transition
	 */
	public long getTargetKaleoNodeId();

	/**
	 * Sets the target kaleo node ID of this kaleo transition.
	 *
	 * @param targetKaleoNodeId the target kaleo node ID of this kaleo transition
	 */
	public void setTargetKaleoNodeId(long targetKaleoNodeId);

	/**
	 * Returns the target kaleo node name of this kaleo transition.
	 *
	 * @return the target kaleo node name of this kaleo transition
	 */
	@AutoEscape
	public String getTargetKaleoNodeName();

	/**
	 * Sets the target kaleo node name of this kaleo transition.
	 *
	 * @param targetKaleoNodeName the target kaleo node name of this kaleo transition
	 */
	public void setTargetKaleoNodeName(String targetKaleoNodeName);

	/**
	 * Returns the default transition of this kaleo transition.
	 *
	 * @return the default transition of this kaleo transition
	 */
	public boolean getDefaultTransition();

	/**
	 * Returns <code>true</code> if this kaleo transition is default transition.
	 *
	 * @return <code>true</code> if this kaleo transition is default transition; <code>false</code> otherwise
	 */
	public boolean isDefaultTransition();

	/**
	 * Sets whether this kaleo transition is default transition.
	 *
	 * @param defaultTransition the default transition of this kaleo transition
	 */
	public void setDefaultTransition(boolean defaultTransition);

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
	public KaleoTransition cloneWithOriginalValues();

}