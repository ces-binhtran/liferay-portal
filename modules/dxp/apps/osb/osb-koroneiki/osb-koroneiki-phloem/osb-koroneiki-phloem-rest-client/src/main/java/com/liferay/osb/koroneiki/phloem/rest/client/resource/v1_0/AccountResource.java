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

package com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface AccountResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<Account> getAccountsPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getAccountsPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Account postAccount(String agentName, Account account)
		throws Exception;

	public HttpInvoker.HttpResponse postAccountHttpResponse(
			String agentName, Account account)
		throws Exception;

	public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountByExternalLinkDomainEntityNameEntityPageHttpResponse(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public void deleteAccount(String agentName, String accountKey)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountHttpResponse(
			String agentName, String accountKey)
		throws Exception;

	public Account getAccount(String accountKey) throws Exception;

	public HttpInvoker.HttpResponse getAccountHttpResponse(String accountKey)
		throws Exception;

	public Account putAccount(
			String agentName, String accountKey, Account account)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountHttpResponse(
			String agentName, String accountKey, Account account)
		throws Exception;

	public void deleteAccountAccountPermission(
			String agentName, String accountKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				AccountPermission accountPermission)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountAccountPermissionHttpResponse(
			String agentName, String accountKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				AccountPermission accountPermission)
		throws Exception;

	public void putAccountAccountPermission(
			String agentName, String accountKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				AccountPermission accountPermission)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountAccountPermissionHttpResponse(
			String agentName, String accountKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
				AccountPermission accountPermission)
		throws Exception;

	public void deleteAccountAssignedTeamTeamKeyRole(
			String agentName, String accountKey, String teamKey,
			String[] teamRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
				String agentName, String accountKey, String teamKey,
				String[] teamRoleKeys)
		throws Exception;

	public void putAccountAssignedTeamTeamKeyRole(
			String agentName, String accountKey, String teamKey,
			String[] teamRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putAccountAssignedTeamTeamKeyRoleHttpResponse(
				String agentName, String accountKey, String teamKey,
				String[] teamRoleKeys)
		throws Exception;

	public Page<Account> getAccountChildAccountsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse getAccountChildAccountsPageHttpResponse(
			String accountKey, Pagination pagination)
		throws Exception;

	public Account postAccountChildAccount(
			String agentName, String accountKey, Account account)
		throws Exception;

	public HttpInvoker.HttpResponse postAccountChildAccountHttpResponse(
			String agentName, String accountKey, Account account)
		throws Exception;

	public void deleteAccountContactByEmailAddres(
			String agentName, String accountKey, String[] contactEmailAddresses)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountContactByEmailAddresHttpResponse(
				String agentName, String accountKey,
				String[] contactEmailAddresses)
		throws Exception;

	public void putAccountContactByEmailAddres(
			String agentName, String accountKey, String[] contactEmailAddresses)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountContactByEmailAddresHttpResponse(
			String agentName, String accountKey, String[] contactEmailAddresses)
		throws Exception;

	public void deleteAccountContactByEmailAddresContactEmailAddressRole(
			String agentName, String accountKey, String contactEmailAddress,
			String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
				String agentName, String accountKey, String contactEmailAddress,
				String[] contactRoleKeys)
		throws Exception;

	public void putAccountContactByEmailAddresContactEmailAddressRole(
			String agentName, String accountKey, String contactEmailAddress,
			String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
				String agentName, String accountKey, String contactEmailAddress,
				String[] contactRoleKeys)
		throws Exception;

	public void deleteAccountContactByOkta(
			String agentName, String accountKey, String[] oktaIds)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountContactByOktaHttpResponse(
			String agentName, String accountKey, String[] oktaIds)
		throws Exception;

	public void putAccountContactByOkta(
			String agentName, String accountKey, String[] oktaIds)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountContactByOktaHttpResponse(
			String agentName, String accountKey, String[] oktaIds)
		throws Exception;

	public void deleteAccountContactByOktaRole(
			String agentName, String accountKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountContactByOktaRoleHttpResponse(
			String agentName, String accountKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception;

	public void putAccountContactByOktaRole(
			String agentName, String accountKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountContactByOktaRoleHttpResponse(
			String agentName, String accountKey, String oktaId,
			String[] contactRoleKeys)
		throws Exception;

	public void deleteAccountContactByUuid(
			String agentName, String accountKey, String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountContactByUuidHttpResponse(
			String agentName, String accountKey, String[] contactUuids)
		throws Exception;

	public void putAccountContactByUuid(
			String agentName, String accountKey, String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountContactByUuidHttpResponse(
			String agentName, String accountKey, String[] contactUuids)
		throws Exception;

	public void deleteAccountContactByUuidContactUuidRole(
			String agentName, String accountKey, String contactUuid,
			String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountContactByUuidContactUuidRoleHttpResponse(
				String agentName, String accountKey, String contactUuid,
				String[] contactRoleKeys)
		throws Exception;

	public void putAccountContactByUuidContactUuidRole(
			String agentName, String accountKey, String contactUuid,
			String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putAccountContactByUuidContactUuidRoleHttpResponse(
				String agentName, String accountKey, String contactUuid,
				String[] contactRoleKeys)
		throws Exception;

	public Page<Account> getTeamTeamKeyAssignedAccountsPage(
			String teamKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamTeamKeyAssignedAccountsPageHttpResponse(
				String teamKey, Pagination pagination)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public AccountResource build() {
			return new AccountResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class AccountResourceImpl implements AccountResource {

		public Page<Account> getAccountsPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = getAccountsPageHttpResponse(
				search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, AccountSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse getAccountsPageHttpResponse(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/accounts");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account postAccount(String agentName, Account account)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = postAccountHttpResponse(
				agentName, account);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse postAccountHttpResponse(
				String agentName, Account account)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(account.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/accounts");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountByExternalLinkDomainEntityNameEntityPageHttpResponse(
					domain, entityName, entityId, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, AccountSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getAccountByExternalLinkDomainEntityNameEntityPageHttpResponse(
					String domain, String entityName, String entityId,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/by-external-link/{domain}/{entityName}/{entityId}",
				domain, entityName, entityId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccount(String agentName, String accountKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = deleteAccountHttpResponse(
				agentName, accountKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteAccountHttpResponse(
				String agentName, String accountKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account getAccount(String accountKey) throws Exception {
			HttpInvoker.HttpResponse httpResponse = getAccountHttpResponse(
				accountKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse getAccountHttpResponse(
				String accountKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account putAccount(
				String agentName, String accountKey, Account account)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = putAccountHttpResponse(
				agentName, accountKey, account);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse putAccountHttpResponse(
				String agentName, String accountKey, Account account)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(account.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountAccountPermission(
				String agentName, String accountKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					AccountPermission accountPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountAccountPermissionHttpResponse(
					agentName, accountKey, accountPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteAccountAccountPermissionHttpResponse(
					String agentName, String accountKey,
					com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
						AccountPermission accountPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/account-permissions",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountAccountPermission(
				String agentName, String accountKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					AccountPermission accountPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountAccountPermissionHttpResponse(
					agentName, accountKey, accountPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putAccountAccountPermissionHttpResponse(
				String agentName, String accountKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					AccountPermission accountPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(accountPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/account-permissions",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountAssignedTeamTeamKeyRole(
				String agentName, String accountKey, String teamKey,
				String[] teamRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
					agentName, accountKey, teamKey, teamRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
					String agentName, String accountKey, String teamKey,
					String[] teamRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (teamRoleKeys != null) {
				for (int i = 0; i < teamRoleKeys.length; i++) {
					httpInvoker.parameter(
						"teamRoleKeys", String.valueOf(teamRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams/{teamKey}/roles",
				accountKey, teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountAssignedTeamTeamKeyRole(
				String agentName, String accountKey, String teamKey,
				String[] teamRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountAssignedTeamTeamKeyRoleHttpResponse(
					agentName, accountKey, teamKey, teamRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putAccountAssignedTeamTeamKeyRoleHttpResponse(
					String agentName, String accountKey, String teamKey,
					String[] teamRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(teamRoleKeys.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (teamRoleKeys != null) {
				for (int i = 0; i < teamRoleKeys.length; i++) {
					httpInvoker.parameter(
						"teamRoleKeys", String.valueOf(teamRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams/{teamKey}/roles",
				accountKey, teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getAccountChildAccountsPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountChildAccountsPageHttpResponse(accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, AccountSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse getAccountChildAccountsPageHttpResponse(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/child-accounts",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account postAccountChildAccount(
				String agentName, String accountKey, Account account)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postAccountChildAccountHttpResponse(
					agentName, accountKey, account);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse postAccountChildAccountHttpResponse(
				String agentName, String accountKey, Account account)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(account.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/child-accounts",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByEmailAddres(
				String agentName, String accountKey,
				String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByEmailAddresHttpResponse(
					agentName, accountKey, contactEmailAddresses);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteAccountContactByEmailAddresHttpResponse(
					String agentName, String accountKey,
					String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactEmailAddresses != null) {
				for (int i = 0; i < contactEmailAddresses.length; i++) {
					httpInvoker.parameter(
						"contactEmailAddresses",
						String.valueOf(contactEmailAddresses[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByEmailAddres(
				String agentName, String accountKey,
				String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByEmailAddresHttpResponse(
					agentName, accountKey, contactEmailAddresses);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putAccountContactByEmailAddresHttpResponse(
					String agentName, String accountKey,
					String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				contactEmailAddresses.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactEmailAddresses != null) {
				for (int i = 0; i < contactEmailAddresses.length; i++) {
					httpInvoker.parameter(
						"contactEmailAddresses",
						String.valueOf(contactEmailAddresses[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByEmailAddresContactEmailAddressRole(
				String agentName, String accountKey, String contactEmailAddress,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					agentName, accountKey, contactEmailAddress,
					contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					String agentName, String accountKey,
					String contactEmailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles",
				accountKey, contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByEmailAddresContactEmailAddressRole(
				String agentName, String accountKey, String contactEmailAddress,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					agentName, accountKey, contactEmailAddress,
					contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					String agentName, String accountKey,
					String contactEmailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRoleKeys.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles",
				accountKey, contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByOkta(
				String agentName, String accountKey, String[] oktaIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByOktaHttpResponse(
					agentName, accountKey, oktaIds);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteAccountContactByOktaHttpResponse(
				String agentName, String accountKey, String[] oktaIds)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (oktaIds != null) {
				for (int i = 0; i < oktaIds.length; i++) {
					httpInvoker.parameter(
						"oktaIds", String.valueOf(oktaIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-okta-id",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByOkta(
				String agentName, String accountKey, String[] oktaIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByOktaHttpResponse(
					agentName, accountKey, oktaIds);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putAccountContactByOktaHttpResponse(
				String agentName, String accountKey, String[] oktaIds)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(oktaIds.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (oktaIds != null) {
				for (int i = 0; i < oktaIds.length; i++) {
					httpInvoker.parameter(
						"oktaIds", String.valueOf(oktaIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-okta-id",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByOktaRole(
				String agentName, String accountKey, String oktaId,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByOktaRoleHttpResponse(
					agentName, accountKey, oktaId, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteAccountContactByOktaRoleHttpResponse(
					String agentName, String accountKey, String oktaId,
					String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-okta-id/{oktaId}/roles",
				accountKey, oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByOktaRole(
				String agentName, String accountKey, String oktaId,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByOktaRoleHttpResponse(
					agentName, accountKey, oktaId, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putAccountContactByOktaRoleHttpResponse(
				String agentName, String accountKey, String oktaId,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRoleKeys.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-okta-id/{oktaId}/roles",
				accountKey, oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByUuid(
				String agentName, String accountKey, String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByUuidHttpResponse(
					agentName, accountKey, contactUuids);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteAccountContactByUuidHttpResponse(
				String agentName, String accountKey, String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByUuid(
				String agentName, String accountKey, String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByUuidHttpResponse(
					agentName, accountKey, contactUuids);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putAccountContactByUuidHttpResponse(
				String agentName, String accountKey, String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactUuids.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByUuidContactUuidRole(
				String agentName, String accountKey, String contactUuid,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByUuidContactUuidRoleHttpResponse(
					agentName, accountKey, contactUuid, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteAccountContactByUuidContactUuidRoleHttpResponse(
					String agentName, String accountKey, String contactUuid,
					String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles",
				accountKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByUuidContactUuidRole(
				String agentName, String accountKey, String contactUuid,
				String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByUuidContactUuidRoleHttpResponse(
					agentName, accountKey, contactUuid, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putAccountContactByUuidContactUuidRoleHttpResponse(
					String agentName, String accountKey, String contactUuid,
					String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRoleKeys.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles",
				accountKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getTeamTeamKeyAssignedAccountsPage(
				String teamKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamTeamKeyAssignedAccountsPageHttpResponse(
					teamKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, AccountSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getTeamTeamKeyAssignedAccountsPageHttpResponse(
					String teamKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/assigned-accounts",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private AccountResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			AccountResource.class.getName());

		private Builder _builder;

	}

}