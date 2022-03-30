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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMStructureLink service. Represents a row in the &quot;DDMStructureLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMStructureLinkImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLink
 * @generated
 */
@ProviderType
public interface DDMStructureLinkModel
	extends AttachedModel, BaseModel<DDMStructureLink>,
			CTModel<DDMStructureLink>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm structure link model instance should use the {@link DDMStructureLink} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm structure link.
	 *
	 * @return the primary key of this ddm structure link
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm structure link.
	 *
	 * @param primaryKey the primary key of this ddm structure link
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm structure link.
	 *
	 * @return the mvcc version of this ddm structure link
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm structure link.
	 *
	 * @param mvccVersion the mvcc version of this ddm structure link
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this ddm structure link.
	 *
	 * @return the ct collection ID of this ddm structure link
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this ddm structure link.
	 *
	 * @param ctCollectionId the ct collection ID of this ddm structure link
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the structure link ID of this ddm structure link.
	 *
	 * @return the structure link ID of this ddm structure link
	 */
	public long getStructureLinkId();

	/**
	 * Sets the structure link ID of this ddm structure link.
	 *
	 * @param structureLinkId the structure link ID of this ddm structure link
	 */
	public void setStructureLinkId(long structureLinkId);

	/**
	 * Returns the company ID of this ddm structure link.
	 *
	 * @return the company ID of this ddm structure link
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm structure link.
	 *
	 * @param companyId the company ID of this ddm structure link
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this ddm structure link.
	 *
	 * @return the fully qualified class name of this ddm structure link
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this ddm structure link.
	 *
	 * @return the class name ID of this ddm structure link
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this ddm structure link.
	 *
	 * @param classNameId the class name ID of this ddm structure link
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this ddm structure link.
	 *
	 * @return the class pk of this ddm structure link
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this ddm structure link.
	 *
	 * @param classPK the class pk of this ddm structure link
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the structure ID of this ddm structure link.
	 *
	 * @return the structure ID of this ddm structure link
	 */
	public long getStructureId();

	/**
	 * Sets the structure ID of this ddm structure link.
	 *
	 * @param structureId the structure ID of this ddm structure link
	 */
	public void setStructureId(long structureId);

	@Override
	public DDMStructureLink cloneWithOriginalValues();

}