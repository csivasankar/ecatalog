<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cartoons</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="categories index">
			<div class="row"><div class="span6"><h2>Cartoons</h2></div><div class="span4 pull-right" id="pagination_div"></div>
				<div class="span2 pull-right"></br>
					<a href="<@spring.url "/cartoons/add"/>" class="btn btn-success pull-right ajaxModal" data-title="Add New Cartoon"><i class="icon-upload"></i> Add</a>
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
				<#if cartoonList??>
				<#list cartoonList as cartoon>
				<#if cartoon??>
				<tr>
				  	<td>				  		
				  		<div class="listData">${cartoon.sortOrder?c}</div>
				  	</td>
				  	<td>${cartoon.name!}</td>
				  	<td><#if cartoon.modified??>${cartoon.modified.time?date}</#if></td>
					<td class="actions" width="500px">
							<a class="btn btn btn-success add images ajaxModal" data-title="Add Icon - ${cartoon.name!}" href="<@spring.url "/cartoons/icon/${cartoon.id?c}"/>">
							<i class="icon-picture"></i> Add Icon</a>
							<a class="btn btn btn-success add images ajaxModal" data-title="Add Cartoon - ${cartoon.name!}" href="<@spring.url "/cartoons/image/${cartoon.id?c}"/>">
							<i class="icon-picture"></i> Add Cartoon</a> 
							<a href="<@spring.url "/cartoons/show/${cartoon.id?c}"/>" data-title="View Cartoon" class="btn btn btn-success"><i class="icon-eye-open"></i> View</a>
							<a href="<@spring.url "/cartoons/edit/${cartoon.id?c}"/>" data-title="Edit Cartoon" class="btn btn-primary ajaxModal"><i class="icon-pencil icon-white"></i> Edit</a>
							<form method="post" id="cartoon_${cartoon.id?c}" name="cartoon_${cartoon.id?c}" style="display:none;" action="<@spring.url "/cartoons/${cartoon.id?c}"/>">
							<input type="hidden" value="DELETE" name="_method">
							</form>
							<a href="#" onclick="if(confirm('Are you sure you want to delete ${cartoon.name?html}?')){document.cartoon_${cartoon.id?c}.submit();} event.returnValue = false; return false;;" class="btn btn-danger">
							<i class="icon-remove-circle icon-white"></i> Delete</a>
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