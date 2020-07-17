<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ProductBundle productBundle = (ProductBundle)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE);
List<Product> products = (List<Product>)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE_PRODUCTS);

String productKeys = null;

if (products != null) {
	List<String> allProductKeys = TransformUtil.transform(products, product -> product.getKey());

	productKeys = StringUtil.merge(allProductKeys, ",");
}
%>

<portlet:actionURL name="/product_bundles/edit_product_bundle" var="editProductBundleURL">
	<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="productBundleId" value="<%= (productBundle != null) ? String.valueOf(productBundle.getProductBundleId()) : null %>" />
</portlet:actionURL>

<aui:form action="<%= editProductBundleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="productKeys" type="hidden" value="<%= productKeys %>" />

	<liferay-ui:error exception="<%= ProductBundleNameException.MustNotBeDuplicate.class %>">

		<%
		ProductBundleNameException.MustNotBeDuplicate productBundleNameException = (ProductBundleNameException.MustNotBeDuplicate)errorException;
		%>

		<%= productBundleNameException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-select-at-least-one-product" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="name" value='<%= (productBundle != null) ? productBundle.getName() : "" %>'>
				<aui:validator name="required" />
			</aui:input>

			<h5><liferay-ui:message key="products" /></h5>

			<div id="<portlet:namespace />productName">

				<%
				if (products != null) {
				%>

					<table class="table table-list">
						<thead>
							<tr>
								<th>
									<liferay-ui:message key="name" />
								</th>
								<th>
								</th>
							</tr>
						</thead>

						<tbody>

							<%
							for (Product product : products) {
							%>

								<tr>
									<td>
										<%= product.getName() %>
									</td>
									<td class=" text-right" id="<%= product.getKey() %>">
										<button class="btn" onclick="removeName(this)" type="button">remove</button>
									</td>
								</tr>

							<%
							}
							%>

						</tbody>
					</table>

					<br />

				<%
				}
				%>

			</div>

			<aui:button onClick='<%= renderResponse.getNamespace() + "assignProducts();" %>' value="select" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />assignProducts',
		function() {
			<portlet:renderURL var="assignProductsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/product_bundles/assign_products" />
			</portlet:renderURL>

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />assignProducts',
				title: '<liferay-ui:message key="select-products" />',
				url: '<%= assignProductsURL %>'
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var selectedItems = event.newVal;

				if (selectedItems) {
					var productKeys = [];

					var display =
						'<table class="table table-list"><thead><tr><th><liferay-ui:message key="name" /></th><th></th></tr></thead><tbody>';

					for (var i = 0; i < selectedItems.length; i++) {
						var selectItem = selectedItems[i];

						productKeys.push(selectItem[0]);

						display +=
							'<tr><td>' +
							selectItem[1] +
							'</td><td class=" text-right" id="' +
							selectItem[0] +
							'"><button type="button" class="btn" onclick="removeName(this)">remove</button></td></tr>';
					}

					display += '</tbody></table><br />';

					A.one('#<portlet:namespace />productName').html(display);
					A.one('#<portlet:namespace />productKeys').val(
						productKeys.join(',')
					);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);
</aui:script>

<aui:script>
	function removeKey(key, value) {
		return key !== value;
	}

	function removeName(object) {
		var productKeys = document.getElementById(
			'<portlet:namespace />productKeys'
		);

		if (productKeys) {
			productKeys.value = productKeys.value
				.split(',')
				.filter(removeKey.bind(this, object.parentElement.id))
				.join(',');
		}

		if (productKeys && productKeys.value == '') {
			document.getElementById('<portlet:namespace />productName').innerHTML =
				'';
		}
		else {
			object.parentElement.parentElement.remove();
			object.parentElement.remove();
		}
	}
</aui:script>