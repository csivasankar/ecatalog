<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Promotions</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="Promotions index">
			<div class="row"><div class="span6"><h2>Promotions</h2></div><div class="span4 pull-right" id="pagination_div"></div></div>
			<div class="row">
			<div class="span12">
					<div class="well">
						<div class="span2 align right">
							<strong>Filter by Category:</strong>
						</div>
						<div class="span6">
							<div class="btn-group">
								<button class="btn btn-inverse">
								<#if selectedCategory?exists>
	  							${selectedCategory.name!}
								<#else>
								  All
								</#if>
								</button>
								<button class="btn btn-inverse  dropdown-toggle"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<#if selectedCategory?exists>
										<li><a href="<@spring.url "/promotions"/>">All</a></li>
									</#if>
									<#if categoryList?exists>
									<#list categoryList as category>
									<li><a href="<@spring.url "/promotions/byCategory/${category.id?c}"/>">${category.name!}</a></li>
									</#list>
									</#if>
								</ul>
							</div>
						</div>
						<div class="span2">
							<a class="btn btn-success pull-right"
								data-title="Add new Promotion" href="<@spring.url "/promotions/add"/>"><i class="icon-upload"></i> Add</a>
						</div>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover" id="pagination">
				<thead>
				<tr>
					<th>Category</th>
					<th>Offer</th>
					<th>Overview</th>
					<th>Availability</th>
					<th>ePromo</th>
					<th>End Date</th>
					<th class="actions">Actions</th>
				</tr>
				</thead>
				<tbody>
				<#if promotionList??>
				<#list promotionList as promotion>
				<#if promotion??>
				<tr>
					<td><#if promotion.category??>${promotion.category.name!}</#if></td>
					<td>${promotion.offer!}</td>
					<td>${promotion.overview!}</td>
					<td>${promotion.availability!}</td>
					<td>${promotion.epromo!}</td>
					<td><#if promotion.endDate??>${promotion.endDate.time?date}</#if></td>
					<td class="actions span4">
					<a href="<@spring.url "/promotions/show/${promotion.id?c}"/>" class="btn btn btn-success"><i class="icon-eye-open"></i> View</a>
					<a class="btn btn btn-primary"
						data-title="Edit Promotion" href="<@spring.url "/promotions/edit/${promotion.id?c}"/>" data-toggle="modal"><i class="icon-pencil icon-white"></i> Edit</a>
						<form name="promotions_${promotion.id?c}" id="promotions_${promotion.id?c}" style="display: none;" method="post" action="<@spring.url "/promotions/${promotion.id?c}"/>">
							<input type="hidden" name="_method" value="DELETE" />
						</form>
						<a href="#"	onclick="if (confirm('Are you sure you want to delete ${promotion.offer?html}?')) { document.promotions_${promotion.id?c}.submit(); } event.returnValue = false; return false;">
						<button class="btn btn-danger" type="submit">
								<i class="icon-remove-circle icon-white"></i> Delete
						</button>
						</a>
						</td>
				</tr>
				</#if>
				</#list>
				</#if>
				</tbody>
			</table>
	<#include "../pagination.ftl"/>	
</body>
</html>