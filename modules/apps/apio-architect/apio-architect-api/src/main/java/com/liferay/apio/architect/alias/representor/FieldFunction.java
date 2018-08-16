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

package com.liferay.apio.architect.alias.representor;

import java.util.function.Function;

/**
 * Represents the key that represents a model's field and the function to
 * calculate that field.
 *
 * @author Alejandro Hernández
 */
public interface FieldFunction<T, S> extends Function<T, S> {

	/**
	 * Returns the field key.
	 *
	 * @return the field key
	 */
	public String getKey();

}