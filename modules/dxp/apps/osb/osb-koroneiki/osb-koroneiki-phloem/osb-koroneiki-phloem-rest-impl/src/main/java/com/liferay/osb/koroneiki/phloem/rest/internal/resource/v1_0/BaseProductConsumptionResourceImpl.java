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
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseProductConsumptionResourceImpl
	implements ProductConsumptionResource {

	@Override
	@GET
	@Operation(description = "Retrieves the account's product consumptions.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/product-consumptions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public Page<ProductConsumption> getAccountAccountKeyProductConsumptionsPage(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "accountKey")}
	)
	@Path("/accounts/{accountKey}/product-consumptions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public ProductConsumption postAccountAccountKeyProductConsumption(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			ProductConsumption productConsumption)
		throws Exception {

		return new ProductConsumption();
	}

	@Override
	@GET
	@Operation(description = "Retrieves the contacts product consumptions.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "oktaId"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/contacts/by-okta-id/{oktaId}/product-consumptions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public Page<ProductConsumption> getContactByOktaProductConsumptionsPage(
			@NotNull @Parameter(hidden = true) @PathParam("oktaId") String
				oktaId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@GET
	@Operation(description = "Retrieves the contacts product consumptions.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/contacts/by-uuid/{contactUuid}/product-consumptions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public Page<ProductConsumption>
			getContactByUuidContactUuidProductConsumptionsPage(
				@NotNull @Parameter(hidden = true) @PathParam("contactUuid")
					String contactUuid,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@GET
	@Operation(
		description = "Retrieves the product consumptions. Results can be paginated, filtered, searched, and sorted."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "search"),
			@Parameter(in = ParameterIn.QUERY, name = "filter"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize"),
			@Parameter(in = ParameterIn.QUERY, name = "sort")
		}
	)
	@Path("/product-consumptions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public Page<ProductConsumption> getProductConsumptionsPage(
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Filter filter, @Context Pagination pagination,
			@Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@GET
	@Operation(
		description = "Retrieves the product consumptions by the external link."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "domain"),
			@Parameter(in = ParameterIn.PATH, name = "entityName"),
			@Parameter(in = ParameterIn.PATH, name = "entityId"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path(
		"/product-consumptions/by-external-link/{domain}/{entityName}/{entityId}"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public Page<ProductConsumption>
			getProductConsumptionByExternalLinkDomainEntityNameEntity(
				@NotNull @Parameter(hidden = true) @PathParam("domain") String
					domain,
				@NotNull @Parameter(hidden = true) @PathParam("entityName")
					String entityName,
				@NotNull @Parameter(hidden = true) @PathParam("entityId") String
					entityId,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "productConsumptionKey")
		}
	)
	@Path("/product-consumptions/{productConsumptionKey}")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public void deleteProductConsumption(
			@NotNull @Parameter(hidden = true)
			@PathParam("productConsumptionKey") String productConsumptionKey)
		throws Exception {
	}

	@Override
	@GET
	@Operation(description = "Retrieves the product consumption.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "productConsumptionKey")
		}
	)
	@Path("/product-consumptions/{productConsumptionKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductConsumption")})
	public ProductConsumption getProductConsumption(
			@NotNull @Parameter(hidden = true)
			@PathParam("productConsumptionKey") String productConsumptionKey)
		throws Exception {

		return new ProductConsumption();
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	public void setContextUser(User contextUser) {
		this.contextUser = contextUser;
	}

	protected void preparePatch(
		ProductConsumption productConsumption,
		ProductConsumption existingProductConsumption) {
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	@Context
	protected AcceptLanguage contextAcceptLanguage;

	@Context
	protected Company contextCompany;

	@Context
	protected UriInfo contextUriInfo;

	@Context
	protected User contextUser;

}