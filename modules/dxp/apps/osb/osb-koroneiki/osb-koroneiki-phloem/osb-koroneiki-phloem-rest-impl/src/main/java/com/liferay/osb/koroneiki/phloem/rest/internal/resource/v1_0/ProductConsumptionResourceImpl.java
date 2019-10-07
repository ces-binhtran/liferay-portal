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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductConsumptionUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductConsumptionEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.KoroneikiPhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-consumption.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductConsumptionResource.class
)
public class ProductConsumptionResourceImpl
	extends BaseProductConsumptionResourceImpl implements EntityModelResource {

	@Override
	public void deleteProductConsumption(String productConsumptionKey)
		throws Exception {

		_productConsumptionService.deleteProductConsumption(
			productConsumptionKey);
	}

	@Override
	public Page<ProductConsumption> getAccountAccountKeyProductConsumptionsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productConsumptionService.getAccountProductConsumptions(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductConsumptionUtil::toProductConsumption),
			pagination,
			_productConsumptionService.getAccountProductConsumptionsCount(
				accountKey));
	}

	@Override
	public Page<ProductConsumption> getContactByOktaProductConsumptionsPage(
			String oktaId, Pagination pagination)
		throws Exception {

		return _getContactProductConsumptionsPage(
			_contactLocalService.getContactByOktaId(oktaId), pagination);
	}

	@Override
	public Page<ProductConsumption>
			getContactByUuidContactUuidProductConsumptionsPage(
				String contactUuid, Pagination pagination)
		throws Exception {

		return _getContactProductConsumptionsPage(
			_contactLocalService.getContactByUuid(contactUuid), pagination);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		long classNameId = _classNameLocalService.getClassNameId(
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class);

		return new ProductConsumptionEntityModel(
			_productFieldLocalService.getProductFieldNames(classNameId));
	}

	@Override
	public ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws Exception {

		return ProductConsumptionUtil.toProductConsumption(
			_productConsumptionService.getProductConsumption(
				productConsumptionKey));
	}

	@Override
	public Page<ProductConsumption>
			getProductConsumptionByExternalLinkDomainEntityNameEntity(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productConsumptionService.getProductConsumptions(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductConsumptionUtil::toProductConsumption),
			pagination,
			_productConsumptionService.getProductConsumptionsCount(
				domain, entityName, entityId));
	}

	@Override
	public Page<ProductConsumption> getProductConsumptionsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter,
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductConsumptionUtil.toProductConsumption(
				_productConsumptionLocalService.getProductConsumption(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public ProductConsumption postAccountAccountKeyProductConsumption(
			String accountKey, ProductConsumption productConsumption)
		throws Exception {

		List<ProductField> productFields = getProductFields(
			productConsumption.getProperties());

		return ProductConsumptionUtil.toProductConsumption(
			_productConsumptionService.addProductConsumption(
				accountKey, productConsumption.getProductKey(), productFields));
	}

	@Override
	public void postProductConsumptionProductConsumptionPermission(
			String productConsumptionKey, String operation,
			ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			productConsumption =
				_productConsumptionLocalService.getProductConsumption(
					productConsumptionKey);

		_productConsumptionPermission.check(
			PermissionThreadLocal.getPermissionChecker(), productConsumption,
			"PERMISSIONS");

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(productConsumptionPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(
				productConsumptionPermission.getPermissions())) {

			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(productConsumptionPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(productConsumptionPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		KoroneikiPhloemPermissionUtil.persistModelPermission(
			actionIds, contextCompany,
			productConsumption.getProductConsumptionId(), operation,
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption.class.
				getName(),
			_resourcePermissionLocalService, _roleLocalService,
			productConsumptionPermission.getRoleNames(), 0);
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

	private Page<ProductConsumption> _getContactProductConsumptionsPage(
			Contact contact, Pagination pagination)
		throws PortalException {

		return Page.of(
			transform(
				_productConsumptionService.getContactProductConsumptions(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductConsumptionUtil::toProductConsumption),
			pagination,
			_productConsumptionService.getContactProductConsumptionsCount(
				contact.getContactId()));
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private
		com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission
			_productConsumptionPermission;

	@Reference
	private ProductConsumptionService _productConsumptionService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}