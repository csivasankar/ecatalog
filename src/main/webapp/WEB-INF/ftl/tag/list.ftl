<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<title>Tags</title>
</head>
<body>
	<div class="tags index">
		<div class="row"><div class="span6"><h2>Tags</h2></div><div class="span3 pull-right" id="pagination_div"></div>
			<div class="span2"></br>
					<a class="btn btn-success pull-right ajaxModal" data-title="Add new Tag" href="<@spring.url "/tags/add"/>"><i class="icon-upload"></i> Add</a>
			</div>						
		</div>
		<table class="table categories-table" id="pagination">
			<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Modified</th>
				<th>Add Image</th>
				<th class="actions">Actions</th>
			</tr>
			</thead>
			<tbody>
			<#if tagList??>
			<#list tagList as tag>
			<#if tag??>
			<tr>
				<td>${tag.id?c}</td>
				<td>${tag.name!}</td>
				<td><#if tag.modified??>${tag.modified.time?date}</#if></td>
				<td>
					<a class="btn btn btn-success ajaxModal" data-title="Add Images" href="<@spring.url "/tags/image/${tag.id?c}"/>">
					 <i	class="icon-picture"></i> Add image
				</a></td>
				<td class="actions">
					<a href="<@spring.url "/tags/show/${tag.id?c}"/>" class="btn btn btn-success"><i class="icon-eye-open"></i> View</a> 
					<a href="<@spring.url "/tags/edit/${tag.id?c}"/>" data-title="Edit Tag" class="btn btn-primary ajaxModal"><i class="icon-pencil icon-white"></i> Edit</a>
					<form action="<@spring.url "/tags/${tag.id?c}"/>" name="tag_${tag.id?c}" id="tag_${tag.id?c}" style="display: none;" method="post">
						<input type="hidden" name="_method" value="DELETE" />
					</form> 
					<a class="btn btn-danger" href="#" onclick="if (confirm('Are you sure you want to delete ${tag.name?html}?')) { document.tag_${tag.id?c}.submit();} event.returnValue = false; return false;"><i class="icon-remove-circle icon-white"></i> Delete</a>
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
