<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Products Reorder</title>
<style>
	#sortable_products {
	  	list-style-type: none; 
	  	margin: 0; 
	  	padding: 0; 
	  	width: 100%;
	}
	#sortable_products li {
	  	margin: 0 3px 3px 3px;
	  	padding-left: 1.5em;
	}
	#sortable_products li span {
	  	position: absolute;
	  	margin-left: -1.3em;
	}
	.sortable-data-table tr td{
		border-top:none;
	}
	.products-table th{
		padding-left:10px;
	}
	.listData{
		margin-left:20px;
	}
 </style>
</head>
<body>
		
		<div class="categories index">
			<div class="row">
				<span class="span8">
					<h2>Reorder Products</h2>
				</span>		
				<div class="span2"></br>
					<div class="btn-group"><button class="btn btn-inverse"><#if selectedCategory?exists><span id="selectedCategory" var="${selectedCategory.id?c}">${selectedCategory.name}</span></#if></button>
								<button class="btn btn-inverse dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
								<ul class="dropdown-menu">
									<#if categoryList??>
									<#list categoryList as category>
									<li><a href="#" onclick="reloadProductsByCategory(${category.id?c});">${category.name}</a></li>
									</#list>
									</#if>
								</ul>
					</div>
				</div>
				<div class="span2 pull-right"></br>
					<a class="btn btn btn-primary" href="#" data-title="Save Products Order" onclick="saveProductsOrder();">
						<i class="icon-upload icon-white"></i> Save
					</a>
				</div>						
			</div>
			<table class="table products-table">
				<tr>
					<th width="8%">Order</th>
					<th width="35%">Name</th>
					<th width="10%">Price</th>
					<th width="15%">Tagged as</th>
					<th width="15%">Created</th>
					<th>Modified</th>
				</tr>
				<tr>
					<td colspan="6" style="padding:10px 0px 0px 0px;">
					<ul id="sortable_products">
					<#list productList as product>
					<#if product??>
					  <li class="ui-state-default" style="cursor:pointer" id="${product.id?c!}">
					  	<table class="sortable-data-table"><tr>
					  	<td width="5%">
					  		<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
					  		${product.sortOrder?c!}
					  	</td>
					  	<td width="40%">${product.name!}</td>
					  	<td width="10%">${product.price!}</td>
					  	<td width="15%"><#if product.tag??>${product.tag.name!}</#if></td>
					  	<td width="16%"><#if product.created??>${product.created.time?date}</#if></td>
					  	<td><#if product.modified??>${product.modified.time?date}</#if></td>						
						</tr></table>
					</li>
					</#if>
					</#list>
					</ul>
					</td>
				</tr>
			</table>
			<#if productList?has_content>
			<div class="row">
				<span class="span10"></span>
				<span>
					<a class="btn btn btn-primary" href="#" data-title="Save Products Order" onclick="saveProductsOrder();">
						<i class="icon-upload icon-white"></i> Save
					</a>
					<a href="#"	onclick="reloadProductsByCategory(<#if selectedCategory?exists>${selectedCategory.id?c}<#else>0</#if>); event.returnValue = false; return false;">
						<button class="btn btn-danger" type="submit"><i class="icon-remove-circle icon-white"></i> Cancel</button>
					</a>
				</span>
			</div>
			<#else>
			No records found.
			</#if>
			<input type="hidden" id="sortPageURL" value="<@spring.url "/products/sort"/>"/>
			<input type="hidden" id="reloadPageURL" value="<@spring.url "/products/reorder"/>"/>
		</div>
	<script type="text/javascript" src="<@spring.url "/resources/script/jquery.ui.js"/>"></script>
	<script type="text/javascript" src="<@spring.url "/resources/scripts/sort.js"/>"></script>	
</body>
</html>