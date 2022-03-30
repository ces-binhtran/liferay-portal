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

package com.liferay.expando.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.TypedModel;
import com.liferay.portal.kernel.model.change.tracking.CTModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ExpandoTable service. Represents a row in the &quot;ExpandoTable&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.expando.model.impl.ExpandoTableImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoTable
 * @generated
 */
@ProviderType
public interface ExpandoTableModel
	extends BaseModel<ExpandoTable>, CTModel<ExpandoTable>, MVCCModel,
			ShardedModel, TypedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a expando table model instance should use the {@link ExpandoTable} interface instead.
	 */

	/**
	 * Returns the primary key of this expando table.
	 *
	 * @return the primary key of this expando table
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this expando table.
	 *
	 * @param primaryKey the primary key of this expando table
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this expando table.
	 *
	 * @return the mvcc version of this expando table
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this expando table.
	 *
	 * @param mvccVersion the mvcc version of this expando table
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the ct collection ID of this expando table.
	 *
	 * @return the ct collection ID of this expando table
	 */
	@Override
	public long getCtCollectionId();

	/**
	 * Sets the ct collection ID of this expando table.
	 *
	 * @param ctCollectionId the ct collection ID of this expando table
	 */
	@Override
	public void setCtCollectionId(long ctCollectionId);

	/**
	 * Returns the table ID of this expando table.
	 *
	 * @return the table ID of this expando table
	 */
	public long getTableId();

	/**
	 * Sets the table ID of this expando table.
	 *
	 * @param tableId the table ID of this expando table
	 */
	public void setTableId(long tableId);

	/**
	 * Returns the company ID of this expando table.
	 *
	 * @return the company ID of this expando table
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this expando table.
	 *
	 * @param companyId the company ID of this expando table
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this expando table.
	 *
	 * @return the fully qualified class name of this expando table
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this expando table.
	 *
	 * @return the class name ID of this expando table
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this expando table.
	 *
	 * @param classNameId the class name ID of this expando table
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the name of this expando table.
	 *
	 * @return the name of this expando table
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this expando table.
	 *
	 * @param name the name of this expando table
	 */
	public void setName(String name);

	@Override
	public ExpandoTable cloneWithOriginalValues();

}