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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductPurchaseUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-purchase.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductPurchaseResource.class
)
public class ProductPurchaseResourceImpl
	extends BaseProductPurchaseResourceImpl {

	@Override
	public void deleteProductPurchase(String productPurchaseKey)
		throws Exception {

		_productPurchaseService.deleteProductPurchase(productPurchaseKey);
	}

	@Override
	public Page<ProductPurchase> getAccountAccountKeyProductPurchasesPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getAccountProductPurchases(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getAccountProductPurchasesCount(
				accountKey));
	}

	@Override
	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception {

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.getProductPurchase(productPurchaseKey));
	}

	@Override
	public ProductPurchase postAccountAccountKeyProductPurchase(
			String accountKey, ProductPurchase productPurchase)
		throws Exception {

		int quantity = getQuantity(productPurchase.getQuantity());

		List<ProductField> productFields = getProductFields(
			productPurchase.getProperties());

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.addProductPurchase(
				accountKey, productPurchase.getProductKey(),
				productPurchase.getStartDate(), productPurchase.getEndDate(),
				quantity, productFields));
	}

	@Override
	public ProductPurchase putProductPurchase(
			String productPurchaseKey, ProductPurchase productPurchase)
		throws Exception {

		int quantity = getQuantity(productPurchase.getQuantity());

		List<ProductField> productFields = getProductFields(
			productPurchase.getProperties());

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.updateProductPurchase(
				productPurchaseKey, productPurchase.getStartDate(),
				productPurchase.getEndDate(), quantity, productFields));
	}

	protected List<ProductField> getProductFields(
		Map<String, String> properties) {

		List<ProductField> productFields = new ArrayList<>();

		if (properties == null) {
			return productFields;
		}

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			ProductField productField =
				_productFieldLocalService.createProductField(0);

			productField.setName(entry.getKey());
			productField.setValue(entry.getValue());

			productFields.add(productField);
		}

		return productFields;
	}

	protected int getQuantity(Integer quantity) {
		if (quantity != null) {
			return quantity;
		}

		return 1;
	}

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseService _productPurchaseService;

}