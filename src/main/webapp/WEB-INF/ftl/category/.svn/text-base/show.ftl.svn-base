<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<!DOCTYPE html>
<div class="row-fluid">
		<div class="span10 offset1" style="margin-top: 10px"></div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<h2>Category</h2>			
			<#if category??>
			<dl>
			<dt>Id</dt>
			<dd>${category.id?c!}&nbsp;</dd>
			<dt>Name</dt>
			<dd>${category.name!}&nbsp;</dd>
			<dt>Modified</dt>
			<dd><#if category.modified??>${category.modified.time?date}</#if>&nbsp;</dd>
			<dt>Image</dt>
			<dd><#if category.image??><img src="<@spring.url "/${category.image.randomLargeImagePath!}" />"></img></#if>&nbsp;</dd>
			<dt>File</dt>
			<dd><#if category.file??><a target="_blank" href="${category.randomCategoryFile}">${category.categoryFile}</a></#if>&nbsp;</dd>
			</dl>
			</#if>
		</div>
	</div>
</div>