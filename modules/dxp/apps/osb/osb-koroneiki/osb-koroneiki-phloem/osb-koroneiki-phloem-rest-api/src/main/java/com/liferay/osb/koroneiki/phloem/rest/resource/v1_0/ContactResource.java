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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/koroneiki-rest/v1.0
 *
 * @author Amos Fong
 * @generated
 */
@Generated("")
@ProviderType
public interface ContactResource {

	public Page<Contact> getAccountAccountKeyContactsPage(
			String accountKey, String[] includes, Pagination pagination)
		throws Exception;

	public Page<Contact> getContactsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Contact postContact(Contact contact) throws Exception;

	public void deleteContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception;

	public Contact getContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception;

	public Contact putContactByEmailAddresEmailAddress(
			String emailAddress, Contact contact)
		throws Exception;

	public void deleteContactByOkta(String oktaId) throws Exception;

	public Contact getContactByOkta(String oktaId) throws Exception;

	public Contact putContactByOkta(String oktaId, Contact contact)
		throws Exception;

	public void deleteContactByUuid(String uuid) throws Exception;

	public Contact getContactByUuid(String uuid) throws Exception;

	public Contact putContactByUuid(String uuid, Contact contact)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

	public void setContextUser(User contextUser);

}