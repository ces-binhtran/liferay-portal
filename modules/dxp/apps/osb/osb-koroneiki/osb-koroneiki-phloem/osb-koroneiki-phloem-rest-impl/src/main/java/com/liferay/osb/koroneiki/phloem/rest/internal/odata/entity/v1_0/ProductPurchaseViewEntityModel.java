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

package com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0;

import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.DateTimeEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewEntityModel implements EntityModel {

	public static final String NAME = "ProductPurchaseView";

	public ProductPurchaseViewEntityModel() {
		_entityFieldsMap = Stream.of(
			new StringEntityField("accountId", locale -> "accountId"),
			new StringEntityField("accountKey", locale -> "accountKey"),
			new StringEntityField("cancelled", locale -> "cancelled"),
			new DateTimeEntityField(
				"endDate", locale -> "endDate", locale -> "endDate"),
			new StringEntityField("name", locale -> "name"),
			new StringEntityField("perpetual", locale -> "perpetual"),
			new CollectionEntityField(
				new StringEntityField(
					"productConsumptionIds",
					locale -> "productConsumptionIds")),
			new StringEntityField("productEntryId", locale -> "productEntryId"),
			new StringEntityField("productKey", locale -> "productKey"),
			new CollectionEntityField(
				new StringEntityField(
					"productPurchaseIds",
					locale -> "productPurchaseIds")),
			new DateTimeEntityField(
				"startDate", locale -> "startDate", locale -> "startDate")
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private final Map<String, EntityField> _entityFieldsMap;

}