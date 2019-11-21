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

package com.liferay.osb.provisioning.message.service.parser;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ContactUtil;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.message.connector.WebServiceConnector;
import com.liferay.osb.provisioning.message.constants.SalesforceConstants;
import com.liferay.osb.provisioning.message.parser.MessageParser;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "type=dossiera.create",
	service = MessageParser.class
)
public class DossieraCreateMessageParser implements MessageParser {

	public void parse(String routingKey, JSONObject jsonObject) {
		try {
			doParse(jsonObject);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doParse(JSONObject jsonObject) throws PortalException {
		if (!hasOpportunityProductFamily(jsonObject)) {
			return;
		}

		String salesforceOpportunityStageName = jsonObject.getString(
			"_salesforceOpportunityStageName");
		int salesforceOpportunityType = getSalesforceOpportunityType(
			jsonObject.getString("_salesforceOpportunityType"));

		if (!_isValidOpportunity(
				salesforceOpportunityStageName, salesforceOpportunityType)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Opportunity is not closed won or a renewal that is " +
						"closed lost");
			}

			return;
		}

		PostalAddress postalAddress = parseAddress(jsonObject);
		List<Contact> contacts = parseContacts(jsonObject);
		ExternalLink[] externalLinks = parseExternalLinks(jsonObject);
		List<ProductPurchase> productPurchases = parseProducts(jsonObject);

		Account account = parseAccount(
			postalAddress, contacts.toArray(new Contact[0]), externalLinks,
			productPurchases.toArray(new ProductPurchase[0]), jsonObject);

		try {
			_webServiceConnector.postAccount(account.toString());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	protected PostalAddress getPostalAddress(JSONObject jsonObject) {
		String city = jsonObject.getString("_city");
		String countryName = jsonObject.getString("_country");
		String postalCode = jsonObject.getString("_postalCode");
		String regionName = jsonObject.getString("_region");
		String street = jsonObject.getString("_street");

		PostalAddress postalAddress = new PostalAddress();

		city = ModelHintsUtil.trimString(Address.class.getName(), "city", city);

		postalAddress.setAddressLocality(city);

		if (Validator.isNotNull(countryName)) {
			postalAddress.setAddressCountry(countryName);
			postalAddress.setAddressRegion(regionName);
		}

		String street1 = street;
		String street2 = StringPool.BLANK;
		String street3 = StringPool.BLANK;

		int maxLength = ModelHintsUtil.getMaxLength(
			Address.class.getName(), "street1");

		if (street1.length() > maxLength) {
			street1 = street1.substring(0, maxLength);

			street2 = street.substring(maxLength);

			if (street2.length() > maxLength) {
				street2 = street2.substring(0, maxLength);

				street3 = street.substring(maxLength * 2);

				if (street3.length() > maxLength) {
					street3 = street3.substring(0, maxLength);
				}
			}
		}

		postalAddress.setStreetAddressLine1(street1);
		postalAddress.setStreetAddressLine2(street2);
		postalAddress.setStreetAddressLine3(street3);

		postalAddress.setPostalCode(postalCode);

		return postalAddress;
	}

	protected int getSalesforceOpportunityType(
		String salesforceOpportunityTypeName) {

		if (StringUtil.equalsIgnoreCase(
				salesforceOpportunityTypeName, "Existing Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "New Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "Renewal")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName,
					"New Project Existing Business")) {

			return SalesforceConstants.
				OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS;
		}
		else {
			return 0;
		}
	}

	protected boolean hasOpportunityProductFamily(JSONObject jsonObject) {
		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (Validator.isNull(salesforceOpportunityProductFamily)) {
			return false;
		}

		String[] productFamilyTokens = {"E", "S"};

		for (String productFamilyToken : productFamilyTokens) {
			if (salesforceOpportunityProductFamily.contains(
					productFamilyToken)) {

				return true;
			}
		}

		return false;
	}

	protected Account parseAccount(
			PostalAddress postalAddress, Contact[] contacts,
			ExternalLink[] externalLinks, ProductPurchase[] productPurchases,
			JSONObject jsonObject)
		throws PortalException {

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		Account account = new Account();

		String accountName = accountJSONObject.getString("_name");

		account.setName(accountName);

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String projectName = projectJSONObject.getString("_name");

		account.setCode(_getCode(accountName, projectName));

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		account.setContactEmailAddress(
			ownerJSONObject.getString("_emailAddress"));

		account.setContacts(contacts);
		account.setExternalLinks(externalLinks);
		account.setIndustry(
			Account.Industry.create(accountJSONObject.getString("_industry")));
		account.setPostalAddresses(new PostalAddress[] {postalAddress});
		account.setProductPurchases(productPurchases);
		account.setSoldBy(jsonObject.getString("_salesforceOpportunitySoldBy"));

		return account;
	}

	protected PostalAddress parseAddress(JSONObject jsonObject) {
		JSONObject billingAddressJSONObject = jsonObject.getJSONObject(
			"_billingAddress");
		JSONObject shippingAddressJSONObject = jsonObject.getJSONObject(
			"_shippingAddress");

		PostalAddress postalAddress = null;

		if (shippingAddressJSONObject != null) {
			postalAddress = getPostalAddress(shippingAddressJSONObject);
		}
		else if (billingAddressJSONObject != null) {
			postalAddress = getPostalAddress(billingAddressJSONObject);
		}
		else {
			postalAddress = new PostalAddress();
		}

		if (Validator.isNull(postalAddress.getAddressLocality())) {
			postalAddress.setAddressLocality("N/A");
		}

		if (Validator.isNull(postalAddress.getStreetAddressLine1())) {
			postalAddress.setStreetAddressLine1("N/A");
		}

		if (Validator.isNull(postalAddress.getPostalCode())) {
			postalAddress.setPostalCode("N/A");
		}

		return postalAddress;
	}

	protected List<Contact> parseContacts(JSONObject jsonObject)
		throws PortalException {

		JSONArray contactsJSONArray = jsonObject.getJSONArray("_contacts");

		if (contactsJSONArray == null) {
			return new ArrayList<>();
		}

		ArrayList<Contact> contacts = new ArrayList<>(
			contactsJSONArray.length());

		for (int i = 0; i < contactsJSONArray.length(); i++) {
			JSONObject contactJSONObject = contactsJSONArray.getJSONObject(i);

			String emailAddress = contactJSONObject.getString("_emailAddress");

			try {
				Contact oktaContact = ContactUtil.toContact(
					_oktaContactIdentityProvider.fetchContactByEmailAddress(
						emailAddress));

				if (oktaContact == null) {
					continue;
				}
			}
			catch (Exception e) {
				throw new PortalException(e);
			}

			String firstName = contactJSONObject.getString("_firstName");
			String lastName = contactJSONObject.getString("_lastName");

			Contact contact = new Contact();

			contact.setFirstName(firstName);
			contact.setLastName(lastName);
			contact.setEmailAddress(emailAddress);

			String role = contactJSONObject.getString("_role");

			ContactRole contactRole = new ContactRole();

			contactRole.setName(role);
			contactRole.setType(ContactRole.Type.create("Account"));

			try {
				boolean createContactRole = true;

				JSONObject contactRolesJSONObject =
					_webServiceConnector.getContactRoles(role);

				JSONArray itemsJSONArray = contactRolesJSONObject.getJSONArray(
					"items");

				if (itemsJSONArray != null) {
					for (int j = 0; j < itemsJSONArray.length(); j++) {
						JSONObject contactRoleJSONObject =
							itemsJSONArray.getJSONObject(i);

						String name = contactRoleJSONObject.getString("name");

						if (name.equals(role)) {
							createContactRole = false;

							break;
						}
					}
				}

				if (createContactRole) {
					_webServiceConnector.postContactRole(
						contactRole.toString());
				}

				contact.setContactRoles(new ContactRole[] {contactRole});
			}
			catch (Exception e) {
				throw new PortalException(e);
			}

			contacts.add(contact);
		}

		return contacts;
	}

	protected ExternalLink[] parseExternalLinks(JSONObject jsonObject) {
		String salesforceAccountKey = jsonObject.getString(
			"_salesforceAccountKey");

		ExternalLink accountExternalLink = new ExternalLink();

		accountExternalLink.setDomain("salesforce");
		accountExternalLink.setEntityName("account");
		accountExternalLink.setEntityId(salesforceAccountKey);

		String salesforceProjectKey = jsonObject.getString(
			"_salesforceProjectKey");

		ExternalLink projectExternalLink = new ExternalLink();

		projectExternalLink.setDomain("salesforce");
		projectExternalLink.setEntityName("project");
		projectExternalLink.setEntityId(salesforceProjectKey);

		return new ExternalLink[] {accountExternalLink, projectExternalLink};
	}

	protected List<ProductPurchase> parseProducts(JSONObject jsonObject)
		throws PortalException {

		JSONArray bundledProductsJSONArray = jsonObject.getJSONArray(
			"_bundledProducts");

		if (bundledProductsJSONArray == null) {
			return Collections.emptyList();
		}

		List<ProductPurchase> productPurchases = new ArrayList<>();

		for (int i = 0; i < bundledProductsJSONArray.length(); i++) {
			JSONObject bundledProductJSONObject =
				bundledProductsJSONArray.getJSONObject(i);

			JSONArray purchasedProductsJSONArray =
				bundledProductJSONObject.getJSONArray("_purchasedProducts");

			for (int j = 0; j < purchasedProductsJSONArray.length(); j++) {
				JSONObject purchasedProductJSONObject =
					purchasedProductsJSONArray.getJSONObject(j);

				int endDateDay = purchasedProductJSONObject.getInt("_endDay");
				int endDateMonth =
					purchasedProductJSONObject.getInt("_endMonth") - 1;
				int endDateYear = purchasedProductJSONObject.getInt("_endYear");

				int startDateDay = purchasedProductJSONObject.getInt(
					"_startDay");
				int startDateMonth =
					purchasedProductJSONObject.getInt("_startMonth") - 1;
				int startDateYear = purchasedProductJSONObject.getInt(
					"_startYear");

				Date startDate = _portal.getDate(
					startDateMonth, startDateDay, startDateYear);

				Date endDate = _portal.getDate(
					endDateMonth, endDateDay, endDateYear);

				ProductPurchase productPurchase = new ProductPurchase();

				productPurchase.setEndDate(endDate);
				productPurchase.setStartDate(startDate);

				try {
					String name = purchasedProductJSONObject.getString("_name");

					JSONObject productResponse =
						_webServiceConnector.getProductByExternalLink(
							"salesforce", "product",
							purchasedProductJSONObject.getString("_name"));

					JSONArray itemsJSONArray = productResponse.getJSONArray(
						"items");

					if (itemsJSONArray != null) {
						JSONObject productJSONObject =
							itemsJSONArray.getJSONObject(0);

						String productKey = productJSONObject.getString("key");

						productPurchase.setProductKey(productKey);
					}
					else {
						JSONObject externalLinkJSONObject = JSONUtil.put(
							"domain", "salesforce"
						).put(
							"entityId", name
						).put(
							"entityName", "product"
						);

						JSONArray externalLinksJSONArray = JSONUtil.put(
							externalLinkJSONObject);

						JSONObject productJSONObject = JSONUtil.put(
							"externalLinks", externalLinksJSONArray
						).put(
							"name", name
						);

						productResponse = _webServiceConnector.postProduct(
							productJSONObject.toString());

						String productKey = productResponse.getString("key");

						productPurchase.setProductKey(productKey);
					}
				}
				catch (Exception e) {
					throw new PortalException(e);
				}

				Map<String, String> properties = new HashMap<>();

				String environment = purchasedProductJSONObject.getString(
					"_environment");
				String productType = purchasedProductJSONObject.getString(
					"_productType");
				int quantity = purchasedProductJSONObject.getInt("_quantity");
				String sizing = purchasedProductJSONObject.getString("_sizing");

				if (Validator.isNotNull(environment)) {
					properties.put("environment", environment);
				}

				if (Validator.isNotNull(productType)) {
					properties.put("productType", productType);
				}

				if (quantity > 0) {
					properties.put("quantity", String.valueOf(quantity));
				}

				if (Validator.isNotNull(sizing)) {
					properties.put("sizing", sizing);
				}

				if (!properties.isEmpty()) {
					productPurchase.setProperties(properties);
				}

				productPurchases.add(productPurchase);
			}
		}

		return productPurchases;
	}

	private String _getCode(String parentAccountName, String accountName) {
		String code = StringUtil.extractChars(parentAccountName);

		if (code.length() > 6) {
			code = code.substring(0, 6);
		}

		code += StringUtil.extractChars(accountName);

		if (code.length() > 12) {
			code = code.substring(0, 12);
		}

		return StringUtil.toUpperCase(code);
	}

	private boolean _isValidOpportunity(
		String salesforceOpportunityStageName, int salesforceOpportunityType) {

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_LOST) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON) &&
			(salesforceOpportunityType !=
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraCreateMessageParser.class);

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _oktaContactIdentityProvider;

	@Reference
	private Portal _portal;

	@Reference(target = "(system=koroneiki)")
	private WebServiceConnector _webServiceConnector;

}