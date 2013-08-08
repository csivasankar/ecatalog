<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cartoons</title>
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
					<h2>Cartoons</h2>					
				</span>
				<div class="span3"></br>
					<a href="<@spring.url "/cartoons/add"/>" class="btn btn-success ajaxModal" data-title="Add New Cartoon"><i class="icon-upload"></i> Add</a>
				</div>						
			</div>
			<table class="table">
				<tr>
					<th>Sort Order</th>
					<th>Name</th>
					<th>Modified</th>					
				</tr>
				<tr>
					<td colspan="3" style="padding:10px 0px 0px 0px;">
					<ul id="sortable" style="cursor:pointer">
					<#list cartoonList as cartoon>
					  <li class="ui-state-default" id="${cartoon.id?c}">
					  	<table class="sortable-data-table"><tr>
					  	<td width="300px">
					  		<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
					  		<div class="listData">${cartoon.sortOrder?c}</div>
					  	</td>
					  	<td width="300px">${cartoon.name!}</td>
					  	<td width="300px"><#if cartoon.modified??>${cartoon.modified.time?date}</#if></td>						
						</tr></table>
					</li>
					</#list>
					</ul>
					</td>
				</tr>
			</table>
			<input type="hidden" id="sortPageURL" value="<@spring.url "/cartoons/sort"/>"/>
		</div>
		<script type="text/javascript" src="<@spring.url "/resources/script/jquery.ui.js"/>"></script>
		<script type="text/javascript" src="<@spring.url "/resources/scripts/sort.js"/>"></script>
</body>
</html>