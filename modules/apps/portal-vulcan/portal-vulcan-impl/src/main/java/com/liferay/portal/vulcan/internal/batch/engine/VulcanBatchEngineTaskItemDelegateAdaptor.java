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

package com.liferay.portal.vulcan.internal.batch.engine;

import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.batch.engine.VulcanBatchEngineTaskItemDelegate;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Preston Crary
 */
public class VulcanBatchEngineTaskItemDelegateAdaptor<T>
	implements BatchEngineTaskItemDelegate<T> {

	public VulcanBatchEngineTaskItemDelegateAdaptor(
		VulcanBatchEngineTaskItemDelegate<T>
			vulcanBatchEngineTaskItemDelegate) {

		_vulcanBatchEngineTaskItemDelegate = vulcanBatchEngineTaskItemDelegate;
	}

	@Override
	public void create(
			Collection<T> items, Map<String, Serializable> parameters)
		throws Exception {

		_vulcanBatchEngineTaskItemDelegate.create(items, parameters);
	}

	@Override
	public void delete(
			Collection<T> items, Map<String, Serializable> parameters)
		throws Exception {

		_vulcanBatchEngineTaskItemDelegate.delete(items, parameters);
	}

	@Override
	public Class<T> getItemClass() {
		Class<? extends VulcanBatchEngineTaskItemDelegate> clazz =
			_vulcanBatchEngineTaskItemDelegate.getClass();

		Class<?> superclass = clazz.getSuperclass();

		return _getItemClassFromGenericInterfaces(
			superclass.getGenericInterfaces());
	}

	@Override
	public EntityModel getEntityModel(Map<String, List<String>> multivaluedMap)
		throws Exception {

		return _vulcanBatchEngineTaskItemDelegate.getEntityModel(
			multivaluedMap);
	}

	@Override
	public Page<T> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		com.liferay.portal.vulcan.pagination.Page<T> page =
			_vulcanBatchEngineTaskItemDelegate.read(
				filter,
				com.liferay.portal.vulcan.pagination.Pagination.of(
					pagination.getPage(), pagination.getPageSize()),
				sorts, parameters, search);

		return Page.of(page.getItems(), pagination, page.getTotalCount());
	}

	@Override
	public void setContextCompany(Company contextCompany) {
		_vulcanBatchEngineTaskItemDelegate.setContextCompany(contextCompany);
	}

	@Override
	public void setContextUser(User contextUser) {
		_vulcanBatchEngineTaskItemDelegate.setContextUser(contextUser);
	}

	@Override
	public void setLanguageId(String languageId) {
		_vulcanBatchEngineTaskItemDelegate.setLanguageId(languageId);
	}

	@Override
	public void update(
			Collection<T> items, Map<String, Serializable> parameters)
		throws Exception {

		_vulcanBatchEngineTaskItemDelegate.update(items, parameters);
	}

	private Class<T> _getItemClassFromGenericInterfaces(
		Type[] genericInterfaceTypes) {

		for (Type genericInterfaceType : genericInterfaceTypes) {
			if (genericInterfaceType instanceof ParameterizedType) {
				ParameterizedType parameterizedType =
					(ParameterizedType)genericInterfaceType;

				if (parameterizedType.getRawType() !=
						VulcanBatchEngineTaskItemDelegate.class) {

					continue;
				}

				Type[] genericTypes =
					parameterizedType.getActualTypeArguments();

				return (Class<T>)genericTypes[0];
			}
		}

		return null;
	}

	private final VulcanBatchEngineTaskItemDelegate<T>
		_vulcanBatchEngineTaskItemDelegate;

}