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

package com.liferay.portal.search.internal.buffer;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.util.ClassUtil;

import java.lang.reflect.Method;

import java.util.Objects;

/**
 * @author André de Oliveira
 * @author Michael C. Han
 */
public class IndexerRequest {

	public IndexerRequest(
		Method method, ClassedModel classedModel, Indexer<?> indexer) {

		_method = method;
		_classedModel = classedModel;

		_indexer = new NoAutoCommitIndexer<>(indexer);

		_modelClassName = classedModel.getModelClassName();
		_modelPrimaryKey = (Long)classedModel.getPrimaryKeyObj();
	}

	public IndexerRequest(
		Method method, Indexer<?> indexer, String modelClassName,
		Long modelPrimaryKey) {

		_method = method;
		_indexer = new NoAutoCommitIndexer<>(indexer);
		_modelClassName = modelClassName;
		_modelPrimaryKey = modelPrimaryKey;

		_classedModel = null;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof IndexerRequest)) {
			return false;
		}

		IndexerRequest indexerRequest = (IndexerRequest)object;

		if (Objects.equals(_indexer, indexerRequest._indexer) &&
			(Objects.equals(_method, indexerRequest._method) ||
			 (Objects.equals(
				 _method.getName(), indexerRequest._method.getName()) &&
			  Objects.equals(
				  _modelPrimaryKey, indexerRequest._modelPrimaryKey))) &&
			Objects.equals(_modelClassName, indexerRequest._modelClassName)) {

			return true;
		}

		return false;
	}

	public void execute() throws Exception {
		Class<?>[] parameterTypes = _method.getParameterTypes();

		if (parameterTypes.length == 1) {
			_method.invoke(_indexer, _classedModel);
		}
		else {
			_method.invoke(_indexer, _modelClassName, _modelPrimaryKey);
		}
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, _method.getName());

		hashCode = HashUtil.hash(hashCode, _modelClassName);
		hashCode = HashUtil.hash(hashCode, _modelPrimaryKey);

		return hashCode;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{classModel=", _classedModel, ", indexer=",
			ClassUtil.getClassName(_indexer), ", method=", _method,
			", modelClassName=", _modelClassName, ", modelPrimaryKey=",
			_modelPrimaryKey, "}");
	}

	private final ClassedModel _classedModel;
	private final Indexer<?> _indexer;
	private final Method _method;
	private final String _modelClassName;
	private final Long _modelPrimaryKey;

}