<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Categories</title>
<style>
	#sortable {
	  	list-style-type: none; 
	  	margin: 0; 
	  	padding: 0; 
	  	width: 100%;
	}
	#sortable li {
	  	margin: 0 3px 3px 3px;
	  	padding-left: 1.5em;
	}
	#sortable li span {
	  	position: absolute;
	  	margin-left: -1.3em;
	}
	.sortable-data-table tr td{
		border-top:none;
	}
	.categories-table th{
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
				<span class="span9">
					<h2>Categories</h2>					
				</span>
				<div class="span3"></br>
					<a href="<@spring.url "/categories/add"/>" class="btn btn-success ajaxModal" data-title="Add New Category"><i class="icon-upload"></i> Add</a>
				</div>						
			</div>
			<table class="table categories-table">
				<tr>
					<th>Order</th>
					<th>Name</th>
					<th>Modified</th>
				</tr>
				<tr>
					<td colspan="3" style="padding:10px 0px 0px 0px;">
					<ul id="sortable">
					<#list categoryList as category>
					<#if category??>
					  <li class="ui-state-default" style="cursor:pointer" id="${category.id?c!}">
					  	<table class="sortable-data-table"><tr>
					  	<td  width="300px">
					  		<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
					  		<div class="listData">${category.sortOrder?c!}</div>
					  	</td>
					  	<td  width="300px">${category.name!}</td>
					  	<td width="300px"><#if category.modified??>${category.modified.time?date}</#if></td>						
						</tr></table>
					</li>
					</#if>
					</#list>
					</ul>
					</td>
				</tr>
			</table>
			<input type="hidden" id="sortPageURL" value="<@spring.url "/categories/sort"/>"/>			
		</div>
	<script type="text/javascript" src="<@spring.url "/resources/script/jquery.ui.js"/>"></script>
	<script type="text/javascript" src="<@spring.url "/resources/scripts/sort.js"/>"></script>	
</body>
</html>