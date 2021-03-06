<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<script type="text/javascript" src="<@spring.url "/resources/scripts/jquery.cookies.2.2.0.js"/>"></script>
<script type="text/javascript">
var totalRecords = '${totalRecords}';
$(document).ready(function() {
	var initialPage = 1;
	var currentPage = '${currentPage}';
	var totalPages = Math.ceil(totalRecords/5);
	if (currentPage > 5 && totalPages > 5) {
		initialPage = currentPage - 4;
	}
	pageNumIncrease(initialPage, currentPage);
});

$(function() {
	$(".next,.prev").click(function() {
		if (!$(this).hasClass("disabled")) {
			var totalPages = Math.ceil(totalRecords/5);
			var iPage = $("#pagination_div .active").text();
			if (iPage) {
				iPage = parseInt(iPage);
			} else {
				iPage = 0;
			}
			if ($(this).hasClass("prev")) {
				if (iPage > 1) {
					iPage = iPage - 1;
				}
			} else if ($(this).hasClass("next")) {
				iPage = iPage + 1;
			}
			getListByCriteria(iPage);
		}
	});
});

function pageNumIncrease(initialPage, currentPage) {
	var cls = "";
	var poplink = '<div class="span4 pull-right">'
				+ '<div class="dataTables_paginate paging_bootstrap pagination">';
	if (currentPage > 1) {
		poplink += '<ul><li class="prev"><a href="#">&#8592; Previous</a></li>';
	} else {
		poplink += '<ul><li class="prev disabled"><a href="#">&#8592; Previous</a></li>';
	}
	var totalPages = Math.ceil(totalRecords/5);
	$.each([0,1,2,3,4], function(index, value) {
		if ((value+initialPage) == currentPage) {
			cls = "active";
		} else {
			cls = "";
		}
		if ((value+initialPage) <= totalPages) {
			poplink += '<li class="'+cls+'"><a class="page_'+(value+initialPage)+'" href="#" onclick="getListByCriteria('+(value+initialPage)+');">'+(value+initialPage)+'</a></li>';
		}
	});
	if (currentPage < totalPages) {
		poplink += '<li class="next"><a href="#">Next &#8594;</a></li></ul></div></div>';
	} else {
		poplink += '<li class="next disabled"><a href="#">Next &#8594;</a></li></ul></div></div>';
	}
	$("#pagination_div").html(poplink);
	var start = 0;
	var end = 0;
	if (currentPage > 0) {
		start = (currentPage*5 - 4);
		end = currentPage*5;
		if (end > totalRecords) {
			end = totalRecords;
		}
		if (start > totalRecords) {
			start = totalRecords;
		}
	}
	var pageInfo = '<div class="span6"><div class="dataTables_info" id="pagination_info">'
				+ 'Showing ' + start + ' to ' + end + ' of ' + totalRecords + ' entries</div></div>';
	$("#dup_pagination_div").html(pageInfo+poplink);
}

var getListByCriteria = function(iPage,params) {
	if (!$(".page_"+iPage).parent("li").hasClass("active")) {
		$("#pagination_div .active").removeClass("active");
		$(".page_"+iPage).parent("li").addClass("active");
	}
	// page restoring
	$.cookies.set("product_list", iPage);
	if (params && params.length > 0) {
		params = params + "&page="+iPage;
	} else {
		params = $(".sortColumn").attr("params") + "&page=" + iPage;
	}
	window.location = '<@spring.url "/products"/>'+params;
};

var sortColumn = function(sortStr) {
	getListByCriteria($("#pagination_div .active").text(), sortStr);
};
</script>
<title>Products</title>
</head>
<body>
	<div class="products index">
			<div class="row"><div class="span8"><h2>Products</h2></div><div class="span4" id="pagination_div"></div></div>
			<div class="row">
				<div class="span12">
					<div class="well">
						<div class="span2 align right">	<strong>Filter by Category:</strong></div>
						<div class="span6">
							<div class="btn-group"><button class="btn btn-inverse"><#if selectedCategory?exists>${selectedCategory.name}<#else>All</#if></button>
								<button class="btn btn-inverse  dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
								<ul class="dropdown-menu">
									<#if selectedCategory?exists>
										<li><a href="<@spring.url "/products?sort=${sort}&direction=${dir}"/>">All</a></li>
									</#if>
									<#if categoryList??>
									<#list categoryList as category>
									<li><a href="<@spring.url "/products/byCategory/${category.id?c}?sort=${sort}&direction=${dir}"/>">${category.name}</a></li>
									</#list>
									</#if>
								</ul>
							</div>
						</div>
						<div class="span2"><a href="<@spring.url "/products/add"/>" class="btn btn btn-success pull-right" data-title="Add new Product" method="GET"><i class="icon-upload"></i> Add</a></div>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover" id="product_pagination">
				<thead>
				<tr>
					<th></th>
					<th>
						<div class="btn-group">
							<button class="btn btn-primary">
								Change Sort:
								<#if dir = 'asc'> 
									<i class="icon-white icon-arrow-up"></i>
								<#else>
									<i class="icon-white icon-arrow-down"></i>
								</#if>
								<#if selectedCategory?exists>
									<#if sort = 'name'>
									<a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=name&direction=asc')"  class="sortColumn" params="/byCategory/${selectedCategory.id?c}?sort=name&direction=asc">Name</a>
									<#elseif sort = 'price'>
										<a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=price&direction=asc')"  class="sortColumn" params="/byCategory/${selectedCategory.id?c}?sort=price&direction=asc">Price</a>
									<#elseif sort = 'created'>
										<a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=created&direction=desc')"  class="sortColumn" params="/byCategory/${selectedCategory.id?c}?sort=created&direction=desc">Created</a>
									<#elseif sort = 'modified'>
										<a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=modified&direction=desc')"  class="sortColumn" params="/byCategory/${selectedCategory.id?c}?sort=modified&direction=desc">Modified</a>
									</#if>
								<#else> 
									<#if sort = 'name'>
										<a href="#" onclick="sortColumn('?sort=name&direction=${dir}')" class="sortColumn" params="?sort=name&direction=${dir}">Name</a>
									<#elseif sort = 'price'>
										<a href="#" onclick="sortColumn('?sort=price&direction=${dir}')" class="sortColumn" params="?sort=price&direction=${dir}">Price</a>
									<#elseif sort = 'created'>
										<a href="#" onclick="sortColumn('?sort=created&direction=${dir}')" class="sortColumn" params="?sort=created&direction=${dir}">Created</a>
									<#elseif sort = 'modified'>
										<a href="#" onclick="sortColumn('?sort=modified&direction=${dir}')" class="sortColumn" params="?sort=modified&direction=${dir}">Modified</a>
									</#if>
								</#if>
							</button>
							<button class="btn btn-primary  dropdown-toggle"
								data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<!-- dropdown menu links -->
								<#if selectedCategory?exists>
									<li><a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=name&direction=asc')"><i class="icon-white icon-arrow-up"></i> Name</a></li>
									<li><a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=price&direction=asc')"><i class="icon-white icon-arrow-up"></i> Price</a></li>
									<li><a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=created&direction=desc')"><i class="icon-white icon-arrow-down"></i> Created</a></li>
									<li><a href="#" onclick="sortColumn('/byCategory/${selectedCategory.id?c}?sort=modified&direction=desc')"><i class="icon-white icon-arrow-down"></i> Modified</a></li>
								<#else>
									<li><a href="#" onclick="sortColumn('?sort=name&direction=asc')"><i class="icon-white icon-arrow-up"></i> Name</a></li>
									<li><a href="#" onclick="sortColumn('?sort=price&direction=asc')"><i class="icon-white icon-arrow-up"></i> Price</a></li>
									<li><a href="#" onclick="sortColumn('?sort=created&direction=desc')"><i	class="icon-white icon-arrow-down"></i> Created</a></li>
									<li><a href="#" onclick="sortColumn('?sort=modified&direction=desc')"><i class="icon-white icon-arrow-down"></i> Modified</a></li>
								</#if>
							</ul>
						</div>
					</th>
					<th class="actions">Actions</th>
				</tr>
				</thead>
				<tbody>
				<#if productList??>
				<#list productList as product>
				<#if product??>
				<tr>
					<td>
						<div class="row">
							<div class="span3">
								<#if product.images[0]??>
									<img title=${product.images[0].image.name} style="width:175px;height:auto;" alt=${product.images[0].image.name} src="<@spring.url "/${product.images[0].image.thumbImagePath!}"/>"> </img>
								</#if>
							</div>
						</div>
					</td>
					<td>
						<div class="row">
							<div class="span6">
								<h3>${product.name!}</h3>
								<blockquote>${product.description!}</blockquote>
							</div>
						</div>

						<div class="row">

							<div class="span3 align">
								<h5 class="align">Price</h5>
								${product.price!}
							</div>
							<div class="span3 align">
								<h5 class="align">Tagged as</h5>
								<#if product.tag??>${product.tag.name!}</#if>
							</div>
						</div>

						<div class="row">
							<div class="span3 align">
								<h5>Created</h5>
								<#if product.created??>${product.created.time?date}</#if>
							</div>
							<div class="span3 align">
								<h5 class="align">Modified</h5>
								<#if product.modified??>${product.modified.time?date}</#if>
							</div>
						</div>
					</td>
					<td class="actions">
						<a class="btn btn btn-success ajaxModal" data-title="Add Images - ${product.name!}" href="<@spring.url "/products/images/${product.id?c}"/>">
							<i class="icon-picture"></i> Add image
						</a>
						<a class="btn btn btn-success ajaxModal" data-title="Add Pdf - ${product.name!}" href="<@spring.url "/products/file/${product.id?c}"/>">
							<i class="icon-picture"></i> Add Pdf
						</a>
						<a href="<@spring.url "/products/show/${product.id?c}"/>" data-title="View Product" class="btn btn btn-success"><i class="icon-eye-open"></i> View</a>
						<a class="btn btn btn-primary" data-title="Edit Product" href="<@spring.url "/products/edit/${product.id?c}"/>">
							<i class="icon-pencil icon-white"></i> Edit
						</a>
						<form method="post" id="product_${product.id?c}" name="product_${product.id?c}" style="display:none;" action="<@spring.url "/products/${product.id?c}"/>">
						<input type="hidden" value="DELETE" name="_method">
						</form>
						<a href="#"	onclick="if (confirm('Are you sure you want to delete ${product.name?html}?')) { document.product_${product.id?c}.submit();} event.returnValue = false; return false;">
							<button class="btn btn-danger" type="submit"><i class="icon-remove-circle icon-white"></i> Delete</button>
						</a>
					</td>
				</tr>
				</#if>
				</#list>
				</#if>
				</tbody>
			</table>
			<div class="row" id="dup_pagination_div"></div>			
		</div>
</body>
</html>