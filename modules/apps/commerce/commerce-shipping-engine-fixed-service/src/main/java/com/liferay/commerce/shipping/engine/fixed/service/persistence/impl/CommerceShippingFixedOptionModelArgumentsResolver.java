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

package com.liferay.commerce.shipping.engine.fixed.service.persistence.impl;

import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionTable;
import com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionImpl;
import com.liferay.commerce.shipping.engine.fixed.model.impl.CommerceShippingFixedOptionModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The arguments resolver class for retrieving value from CommerceShippingFixedOption.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceShippingFixedOptionModelArgumentsResolver
	implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		CommerceShippingFixedOptionModelImpl
			commerceShippingFixedOptionModelImpl =
				(CommerceShippingFixedOptionModelImpl)baseModel;

		long columnBitmask =
			commerceShippingFixedOptionModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				commerceShippingFixedOptionModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					commerceShippingFixedOptionModelImpl.getColumnBitmask(
						columnName);
			}

			if (finderPath.isBaseModelResult() &&
				(CommerceShippingFixedOptionPersistenceImpl.
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

				finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				commerceShippingFixedOptionModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CommerceShippingFixedOptionImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CommerceShippingFixedOptionTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		CommerceShippingFixedOptionModelImpl
			commerceShippingFixedOptionModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					commerceShippingFixedOptionModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] =
					commerceShippingFixedOptionModelImpl.getColumnValue(
						columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

	private static final long _ORDER_BY_COLUMNS_BITMASK;

	static {
		long orderByColumnsBitmask = 0;

		orderByColumnsBitmask |=
			CommerceShippingFixedOptionModelImpl.getColumnBitmask("priority");

		_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
	}

}