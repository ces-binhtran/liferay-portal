<#assign count = 0 />

<#if entries?has_content>
	<div class="row">
		<#assign total = commerceCartContentMiniDisplayContext.getCommerceCartTotal() />

		<div class="col-md-12">
			<strong>Total: ${total}</strong>
		</div>

		<#list entries as curCommerceCartItem>
			<#assign
				cpDefinition = curCommerceCartItem.getCPDefinition()

				image = cpDefinition.getDefaultImageThumbnailSrc(themeDisplay)

				productURL = commerceCartContentMiniDisplayContext.getCPDefinitionURL(cpDefinition.getCPDefinitionId(), themeDisplay)

				title = cpDefinition.getTitle(locale)

				price = commerceCartContentMiniDisplayContext.getFormattedPrice(curCommerceCartItem)
			/>

			<div class="col-md-6">
				<div class="row">
					<img src="${image}">
				</div>

				<div class="row">
					<a href="${productURL}">
						<strong>${title}</strong>
					</a>
				</div>

				<div class="row">
					${price}
				</div>
			</div>

			<#assign count = count + 1 />

			<#if count gte 2>
				</div>

				<div class="row">

				<#assign count = 0 />
			</#if>
		</#list>
	</div>
</#if>