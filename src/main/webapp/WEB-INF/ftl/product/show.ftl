<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<div class="row-fluid">
	<div class="span4">
		<h2>Product</h2>
			<#if product??>
				<dl>
					<dt>Name</dt>
					<dd>${product.name!}&nbsp;</dd>
					<dt>Description</dt>
					<dd>${product.description!}&nbsp;</dd>
					<dt>atAGlance</dt>
					<dd>${product.atAGlance!}&nbsp;</dd>
					<dt>Other Information</dt>
					<dd>${product.otherInfo!}&nbsp;</dd>
					<dt>Price</dt>
					<dd>${product.price!}&nbsp;</dd>
					<dt>Category</dt>
					<dd><#if product.category??>${product.category.name!}&nbsp;</#if></dd>
					<dt>Tag</dt>
					<dd><#if product.tag??>${product.tag.name!}&nbsp;</#if></dd>
					<dt>Created</dt>
					<dd><#if product.created??>${product.created.time?date}&nbsp;</#if></dd>
					<dt>Modified</dt>
					<dd><#if product.modified??>${product.modified.time?date}</#if>&nbsp;
					</dd>
					<dt>Image</dt>
					<dd><#if product.images?? && product.images[0]??>
							<img src="<@spring.url "/${product.images[0].image.largeImagePath!}" />"/>
						</#if>&nbsp;
					</dd>
					<dt>Files</dt>
					<dd><#if product.file??><a target="_blank" href="${product.productFile}">${product.productFile}</a></#if>&nbsp;</dd>
				</dl>
			</#if>
	</div>
</div>