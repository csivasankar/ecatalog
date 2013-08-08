<#import "/spring.ftl" as spring />
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner ">
			<ul class="nav">
				<li class="active"><a href="<@spring.url "/products"/>">Home</a></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Products <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/products"/>">Products List</a></li>
						<li><a href="<@spring.url "/products/add"/>" data-title="Add New Product">Add New Product</a></li>
						<li><a href="<@spring.url "/products/reorder"/>">Reorder Products</a></li>
					</ul></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Promotions<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/promotions"/>">Promotions List</a></li>
						<li><a href="<@spring.url "/promotions/add"/>" data-title="Add New Promotion">Add New Promotion</a></li>
					</ul></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Cartoons<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/cartoons"/>">Cartoon List</a></li>
						<li><a href="<@spring.url "/cartoons/add"/>" class="ajaxModal" data-title="Add New Cartoon">Add New Cartoon</a></li>
						<li><a href="<@spring.url "/cartoons/reorder"/>">Reorder Cartoons</a></li>						
					</ul></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Categories<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/categories"/>">Categories List</a></li>
						<li><a href="<@spring.url "/categories/add"/>" class="ajaxModal" data-title="Add New Category">Add New Category</a></li>
						<li><a href="<@spring.url "/categories/reorder"/>">Reorder Categories</a></li>
					</ul>
				</li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Tags<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/tags"/>">Tag List</a></a></li>
						<li><a href="<@spring.url "/tags/add"/>" class="ajaxModal" data-title="Add New Tag">Add New Tag</a></li>
					</ul></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Users <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/users"/>">List Users</a></li>
						<li><a href="<@spring.url "/users/add"/>" class="ajaxModal" data-title="Add New User">Add New User</a></li>
						<li class="divider"></li>
						<li class="nav-header">Groups</li>
						<li><a href="<@spring.url "/groups"/>">List Groups</a></li>
						<li><a href="<@spring.url "/groups/add"/>" class="ajaxModal" data-title="Add New Group">Add New Group</a></li>
					</ul></li>
				<!--  <li><a href="<@spring.url "/resources/j_spring_security_logout"/>">Logout</a></li> -->
			</ul>
		</div>
	</div>