<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<title>Groups</title>
</head>
<body>
	<div class="groups index">
			<div class="row">
				<div class="span6"><h2>Groups</h2></div><div class="span4 pull-right" id="pagination_div"></div>
				<div class="span2"></br><a class="btn btn-success pull-right ajaxModal" data-title="Add new Group" href="<@spring.url "/groups/add"/>" data-title="Add New Group">
					<i class="icon-upload"></i> Add</a>
				</div>	
			</div>
			<br/>
			<table class="table table-striped table-hover" id="pagination">
				<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Created</th>
					<th>Modified</th>
					<th class="actions">Actions</th>
				</tr>
				</thead>
				<tbody>
				<#if groupList??>
				<#list groupList as group>
				<#if group??>
				<tr>
					<td>${group.id?c!}</td>
					<td>${group.name!}</td>
					<td><#if group.created??>${group.created.time?date}</#if></td>
					<td><#if group.modified??>${group.modified.time?date}</#if></td>
					<td class="actions">
						<a href="<@spring.url "/groups/edit/${group.id?c}"/>" data-title="Edit Group" class="btn btn-warning ajaxModal"><i class="icon-edit"></i> Edit</a>
						<form method="post" id="group_${group.id?c}" name="group_${group.id?c}" style="display:none;" action="<@spring.url "/groups/${group.id?c}"/>">
						<input type="hidden" value="DELETE" name="_method">
						</form>
						<a href="#" onclick="if(confirm('Are you sure you want to delete ${group.name?html}?')){document.group_${group.id?c}.submit();} event.returnValue = false; return false;;" class="btn btn-danger">
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