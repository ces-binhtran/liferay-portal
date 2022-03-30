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

package com.liferay.external.data.source.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the TestEntity service. Represents a row in the &quot;TestEntity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.external.data.source.test.model.impl.TestEntityModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.external.data.source.test.model.impl.TestEntityImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestEntity
 * @generated
 */
@ProviderType
public interface TestEntityModel extends BaseModel<TestEntity> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a test entity model instance should use the {@link TestEntity} interface instead.
	 */

	/**
	 * Returns the primary key of this test entity.
	 *
	 * @return the primary key of this test entity
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this test entity.
	 *
	 * @param primaryKey the primary key of this test entity
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ID of this test entity.
	 *
	 * @return the ID of this test entity
	 */
	public long getId();

	/**
	 * Sets the ID of this test entity.
	 *
	 * @param id the ID of this test entity
	 */
	public void setId(long id);

	/**
	 * Returns the data of this test entity.
	 *
	 * @return the data of this test entity
	 */
	@AutoEscape
	public String getData();

	/**
	 * Sets the data of this test entity.
	 *
	 * @param data the data of this test entity
	 */
	public void setData(String data);

	@Override
	public TestEntity cloneWithOriginalValues();

}