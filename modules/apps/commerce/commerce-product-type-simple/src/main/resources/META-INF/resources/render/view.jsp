<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();
%>

<div class="container-fluid product-detail" id="<portlet:namespace /><%= cpCatalogEntry.getCPDefinitionId() %>ProductContent">
	<div class="product-detail-header">
		<div class="row">
			<div class="col-lg-6 col-md-7">
				<div class="row">
					<div class="col-lg-2 col-md-3 col-xs-2">
						<div id="<portlet:namespace />thumbs-container">

							<%
							for (CPMedia cpMedia : cpContentHelper.getImages(cpDefinitionId, themeDisplay)) {
							%>

								<div class="card thumb" data-url="<%= cpMedia.getUrl() %>">
									<img class="center-block img-responsive" src="<%= cpMedia.getUrl() %>" />
								</div>

							<%
							}
							%>

						</div>
					</div>

					<div class="col-lg-10 col-md-9 col-xs-10 full-image">
						<c:if test="<%= Validator.isNotNull(cpCatalogEntry.getDefaultImageFileUrl()) %>">
							<img class="center-block img-responsive" id="<portlet:namespace />full-image" src="<%= cpCatalogEntry.getDefaultImageFileUrl() %>" />
						</c:if>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-5">
				<h1><%= HtmlUtil.escape(cpCatalogEntry.getName()) %></h1>

				<c:choose>
					<c:when test="<%= cpSku != null %>">
						<h4 class="sku"><%= HtmlUtil.escape(cpSku.getSku()) %></h4>

						<div class="price"><liferay-commerce:price CPDefinitionId="<%= cpDefinitionId %>" CPInstanceId="<%= cpSku.getCPInstanceId() %>" /></div>

						<div class="subscription-info"><commerce-ui:product-subscription-info CPInstanceId="<%= cpSku.getCPInstanceId() %>" /></div>

						<div class="availability"><%= cpContentHelper.getAvailabilityLabel(request) %></div>

						<div class="availabilityEstimate"><%= cpContentHelper.getAvailabilityEstimateLabel(request) %></div>

						<div class="stockQuantity"><%= cpContentHelper.getStockQuantityLabel(request) %></div>
					</c:when>
					<c:otherwise>
						<h4 class="sku" data-text-cp-instance-sku=""></h4>

						<div class="price" data-text-cp-instance-price=""></div>

						<div class="subscription-info" data-text-cp-instance-subscription-info="" data-text-cp-instance-subscription-info-show></div>

						<div class="subscription-info" data-text-cp-instance-delivery-subscription-info="" data-text-cp-instance-delivery-subscription-info-show></div>

						<div class="availability" data-text-cp-instance-availability=""></div>

						<div class="availabilityEstimate" data-text-cp-instance-availability-estimate=""></div>

						<div class="stockQuantity" data-text-cp-instance-stock-quantity=""></div>
					</c:otherwise>
				</c:choose>

				<div class="row">
					<div class="col-md-12">
						<div class="options">
							<%= cpContentHelper.renderOptions(renderRequest, renderResponse) %>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<liferay-commerce:compare-product
							CPDefinitionId="<%= cpDefinitionId %>"
						/>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<liferay-util:dynamic-include key="com.liferay.commerce.product.content.web#/add_to_cart#" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<%
	List<CPDefinitionSpecificationOptionValue> cpDefinitionSpecificationOptionValues = cpContentHelper.getCPDefinitionSpecificationOptionValues(cpDefinitionId);
	List<CPOptionCategory> cpOptionCategories = cpContentHelper.getCPOptionCategories(company.getCompanyId());
	List<CPMedia> cpMediaEntries = cpContentHelper.getCPAttachmentFileEntries(cpDefinitionId, themeDisplay);
	%>

	<div class="row">
		<div class="product-detail-body w-100">
			<div class="nav-tabs-centered">
				<ul class="justify-content-center nav nav-tabs" role="tablist">
					<li class="nav-item" role="presentation">
						<a aria-controls="<portlet:namespace />description" aria-expanded="true" class="active nav-link" data-toggle="tab" href="#<portlet:namespace />description" role="tab">
							<%= LanguageUtil.get(resourceBundle, "description") %>
						</a>
					</li>

					<c:if test="<%= cpContentHelper.hasCPDefinitionSpecificationOptionValues(cpDefinitionId) %>">
						<li class="nav-item" role="presentation">
							<a aria-controls="<portlet:namespace />specification" aria-expanded="false" class="nav-link" data-toggle="tab" href="#<portlet:namespace />specification" role="tab">
								<%= LanguageUtil.get(resourceBundle, "specifications") %>
							</a>
						</li>
					</c:if>

					<c:if test="<%= !cpMediaEntries.isEmpty() %>">
						<li class="nav-item" role="presentation">
							<a aria-controls="<portlet:namespace />attachments" aria-expanded="false" class="nav-link" data-toggle="tab" href="#<portlet:namespace />attachments" role="tab">
								<%= LanguageUtil.get(resourceBundle, "attachments") %>
							</a>
						</li>
					</c:if>
				</ul>

				<div class="tab-content">
					<div class="active tab-pane" id="<portlet:namespace />description">
						<p><%= cpCatalogEntry.getDescription() %></p>
					</div>

					<c:if test="<%= cpContentHelper.hasCPDefinitionSpecificationOptionValues(cpDefinitionId) %>">
						<div class="tab-pane" id="<portlet:namespace />specification">
							<c:if test="<%= !cpDefinitionSpecificationOptionValues.isEmpty() %>">
								<div class="table-responsive">
									<table class="table table-bordered table-striped">

										<%
										for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : cpDefinitionSpecificationOptionValues) {
											CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();
										%>

											<tr>
												<td><%= HtmlUtil.escape(cpSpecificationOption.getTitle(languageId)) %></td>
												<td><%= HtmlUtil.escape(cpDefinitionSpecificationOptionValue.getValue(languageId)) %></td>
											</tr>

										<%
										}
										%>

									</table>
								</div>
							</c:if>

							<%
							for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
								List<CPDefinitionSpecificationOptionValue> categorizedCPDefinitionSpecificationOptionValues = cpContentHelper.getCategorizedCPDefinitionSpecificationOptionValues(cpDefinitionId, cpOptionCategory.getCPOptionCategoryId());
							%>

								<c:if test="<%= !categorizedCPDefinitionSpecificationOptionValues.isEmpty() %>">
									<div class="table-responsive">
										<table class="table table-bordered table-striped">
											<tr>
												<th><%= cpOptionCategory.getTitle(languageId) %></th>
												<th></th>
											</tr>

											<%
											for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : categorizedCPDefinitionSpecificationOptionValues) {
												CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();
											%>

												<tr>
													<td><%= HtmlUtil.escape(cpSpecificationOption.getTitle(languageId)) %></td>
													<td><%= HtmlUtil.escape(cpDefinitionSpecificationOptionValue.getValue(languageId)) %></td>
												</tr>

											<%
											}
											%>

										</table>
									</div>
								</c:if>

							<%
							}
							%>

						</div>
					</c:if>

					<c:if test="<%= !cpMediaEntries.isEmpty() %>">
						<div class="tab-pane" id="<portlet:namespace />attachments">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">

									<%
									for (CPMedia curCPMedia : cpMediaEntries) {
									%>

										<tr>
											<td>
												<span><%= curCPMedia.getTitle() %></span>

												<span>
													<aui:icon cssClass="icon-monospaced" image="download" markupView="lexicon" target="_blank" url="<%= curCPMedia.getDownloadUrl() %>" />
												</span>
											</td>
										</tr>

									<%
									}
									%>

								</table>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<aui:script>
	window.document.addEventListener('DOMContentLoaded', function () {
		var thumbElements = window.document.querySelectorAll('.thumb');

		Array.from(thumbElements).forEach(function (thumbElement) {
			thumbElement.addEventListener('click', function (event) {
				window.document
					.querySelector('#<portlet:namespace />full-image')
					.setAttribute(
						'src',
						event.currentTarget.getAttribute('data-url')
					);
			});
		});
	});
</aui:script>

<liferay-portlet:actionURL name="/cp_content_web/check_cp_instance" portletName="com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet" var="checkCPInstanceURL">
	<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionId) %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>" />
</liferay-portlet:actionURL>

<aui:script use="liferay-commerce-product-content">
	var productContent = new Liferay.Portlet.ProductContent({
		checkCPInstanceActionURL: '<%= checkCPInstanceURL %>',
		cpDefinitionId: <%= cpDefinitionId %>,
		fullImageSelector: '#<portlet:namespace />full-image',
		namespace: '<portlet:namespace />',
		productContentAuthToken:
			'<%= AuthTokenUtil.getToken(request, plid, CPPortletKeys.CP_CONTENT_WEB) %>',
		productContentSelector:
			'#<portlet:namespace /><%= cpDefinitionId %>ProductContent',
		thumbsContainerSelector: '#<portlet:namespace />thumbs-container',
		viewAttachmentURL:
			'<%= String.valueOf(cpContentHelper.getViewAttachmentURL(liferayPortletRequest, liferayPortletResponse)) %>',
	});

	Liferay.component(
		'<portlet:namespace /><%= cpDefinitionId %>ProductContent',
		productContent
	);
</aui:script>