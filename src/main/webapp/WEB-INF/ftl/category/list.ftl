<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<title>Categories</title>
</head>
<body>		
	<div class="categories index">
		<div class="row"><div class="span6"><h2>Categories</h2></div><div class="span4 pull-right" id="pagination_div"></div>
		<div class="span2"></br><a href="<@spring.url "/categories/add"/>" class="btn btn-success pull-right ajaxModal" data-title="Add New Category"><i class="icon-upload"></i> Add</a>
			</div>
		</div>
		<table class="table categories-table" id="pagination">
			<thead>
			<tr>
				<th>Order</th>
				<th>Name</th>
				<th>Modified</th>
				<th class="actions">Actions</th>
			</tr>
			</thead>
			<tbody>
			<#if categoryList??>
			<#list categoryList as category>
				<#if category??>
					<tr>  
						<td>${category.sortOrder?c!}</td>
						<td>${category.name!}</td>
						<td><#if category.modified??>${category.modified.time?date}</#if></td>
						<td class="actions">
							<a class="btn  btn-success ajaxModal" data-title="Add Images" href="<@spring.url "/categories/image/${category.id?c}"/>"><i class="icon-picture"></i> Add image</a> 
							<a href="<@spring.url "/categories/show/${category.id?c}"/>" data-title="View Category" class="btn btn btn-success"><i class="icon-eye-open"></i> View</a>
							<#if category.name??>
								<#if category.name != 'Workplace' && category.name != 'Promotions' && category.name != 'Cartoons'>
									<a href="<@spring.url "/categories/edit/${category.id?c}"/>" data-title="Edit Category" class="btn btn-primary ajaxModal"><i class="icon-pencil icon-white"></i> Edit</a>
									<form method="post" id="category_${category.id?c}" name="category_${category.id?c}" style="display:none;" action="<@spring.url "/categories/${category.id?c}"/>">
									<input type="hidden" value="DELETE" name="_method">
									</form>
									<a href="#" onclick="if(confirm('Are you sure you want to delete ${category.name}?')){document.category_${category.id?c}.submit();} event.returnValue = false; return false;" class="btn btn-danger">
									<i class="icon-remove-circle icon-white"></i> Delete</a>
								<#else>
									<form method="post" id="category_${category.id?c}" name="category_enable_${category.id?c}" style="display:none;" action="<@spring.url "/categories/workplace/${category.id?c}?enable="/><#if category.enable>false<#else>true</#if>">
									</form>
									<a href="#" onclick="if(confirm('Are you sure you want to <#if category.enable> Disable <#else> Enable </#if>?')){document.category_enable_${category.id?c}.submit();} event.returnValue = false; return false;"  class="btn btn-warning">
										<i class="icon-edit"></i> 
										<#if category.enable> Disable <#else> Enable </#if> </a>
									<#if category.name = 'Workplace'>	
									<a href="<@spring.url "/categories/file/${category.id?c}"/>" class="btn btn-success ajaxModal"><i class="icon-upload"></i> Add Zip</a>
									</#if>
								</#if>
							<#else>
									<a href="#" onclick="if(confirm('Are you sure you want to delete ${category.name?html}?')){document.category_${category.id?c}.submit();} event.returnValue = false; return false;;" class="btn btn-danger">
									<i class="icon-remove-circle icon-white"></i> Delete</a>							
							</#if>
						</td>
					</tr>
				</#if>
			</#list>
			</#if>
			</tbody>
		</table>
	</div>
	<#include "../pagination.ftl"/>	
</body>
</html>