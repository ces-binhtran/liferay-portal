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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ContactUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ContactEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.KoroneikiPhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactService;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
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

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/contact.properties",
	scope = ServiceScope.PROTOTYPE, service = ContactResource.class
)
public class ContactResourceImpl
	extends BaseContactResourceImpl implements EntityModelResource {

	@Override
	public void deleteContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByEmailAddress(emailAddress);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public void deleteContactByOkta(String oktaId) throws Exception {
		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByOktaId(oktaId);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public void deleteContactByUuidContactUuid(String contactUuid)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.getContactByUuid(contactUuid);

		_contactService.deleteContact(contact.getContactId());
	}

	@Override
	public Page<Contact> getAccountAccountKeyContactsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_contactService.getAccountContacts(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				contact -> ContactUtil.toContact(contact)),
			pagination, _contactService.getAccountContactsCount(accountKey));
	}

	@Override
	public Contact getContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception {

		return ContactUtil.toContact(
			_contactService.getContactByEmailAddress(emailAddress));
	}

	@Override
	public Contact getContactByOkta(String oktaId) throws Exception {
		return ContactUtil.toContact(
			_contactService.getContactByOktaId(oktaId));
	}

	@Override
	public Contact getContactByUuidContactUuid(String contactUuid)
		throws Exception {

		return ContactUtil.toContact(
			_contactService.getContactByUuid(contactUuid));
	}

	@Override
	public Page<Contact> getContactsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.Contact.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ContactUtil.toContact(
				_contactLocalService.getContact(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Contact postContact(Contact contact) throws Exception {
		return ContactUtil.toContact(
			_contactService.addContact(
				contact.getUuid(), contact.getOktaId(), contact.getFirstName(),
				contact.getMiddleName(), contact.getLastName(),
				contact.getEmailAddress(), contact.getLanguageId()));
	}

	@Override
	public void postContactByOktaContactPermission(
			String oktaId, String operation,
			ContactPermission contactPermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.fetchContactByOktaId(oktaId);

		postContactPermission(contact, operation, contactPermission);
	}

	@Override
	public void postContactByUuidContactUuidContactPermission(
			String contactUuid, String operation,
			ContactPermission contactPermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact contact =
			_contactLocalService.fetchContactByUuid(contactUuid);

		postContactPermission(contact, operation, contactPermission);
	}

	@Override
	public Contact putContactByEmailAddresEmailAddress(
			String emailAddress, Contact contact)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByEmailAddress(emailAddress);

		String uuid = GetterUtil.getString(
			contact.getUuid(), curContact.getUuid());
		String oktaId = GetterUtil.getString(
			contact.getOktaId(), curContact.getOktaId());
		String middleName = GetterUtil.getString(
			contact.getMiddleName(), curContact.getMiddleName());
		String languageId = GetterUtil.getString(
			contact.getLanguageId(), curContact.getLanguageId());

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), uuid, oktaId, contact.getFirstName(),
				middleName, contact.getLastName(), contact.getEmailAddress(),
				languageId));
	}

	@Override
	public Contact putContactByOkta(String oktaId, Contact contact)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByOktaId(oktaId);

		String uuid = GetterUtil.getString(
			contact.getUuid(), curContact.getUuid());
		String middleName = GetterUtil.getString(
			contact.getMiddleName(), curContact.getMiddleName());
		String languageId = GetterUtil.getString(
			contact.getLanguageId(), curContact.getLanguageId());

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), uuid, curContact.getOktaId(),
				contact.getFirstName(), middleName, contact.getLastName(),
				contact.getEmailAddress(), languageId));
	}

	@Override
	public Contact putContactByUuidContactUuid(
			String contactUuid, Contact contact)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Contact curContact =
			_contactLocalService.getContactByUuid(contactUuid);

		String oktaId = GetterUtil.getString(
			contact.getOktaId(), curContact.getOktaId());
		String middleName = GetterUtil.getString(
			contact.getMiddleName(), curContact.getMiddleName());
		String languageId = GetterUtil.getString(
			contact.getLanguageId(), curContact.getLanguageId());

		return ContactUtil.toContact(
			_contactService.updateContact(
				curContact.getContactId(), curContact.getUuid(), oktaId,
				contact.getFirstName(), middleName, contact.getLastName(),
				contact.getEmailAddress(), languageId));
	}

	protected void postContactPermission(
			com.liferay.osb.koroneiki.taproot.model.Contact contact,
			String operation, ContactPermission contactPermission)
		throws Exception {

		_contactPermission.check(
			PermissionThreadLocal.getPermissionChecker(), contact,
			"PERMISSIONS");

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(contactPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(contactPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(contactPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(contactPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		KoroneikiPhloemPermissionUtil.persistModelPermission(
			actionIds, contextCompany, contact.getContactId(), operation,
			com.liferay.osb.koroneiki.taproot.model.Contact.class.getName(),
			_resourcePermissionLocalService, _roleLocalService,
			contactPermission.getRoleNames(), 0);
	}

	private static final EntityModel _entityModel = new ContactEntityModel();

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.ContactPermission
		_contactPermission;

	@Reference
	private ContactService _contactService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}