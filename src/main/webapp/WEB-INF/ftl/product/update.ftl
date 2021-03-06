<#import "/spring.ftl" as spring />
<@spring.bind "product" />
<@spring.bind "categoryOptions" />
<@spring.bind "tagOptions" />
<div class="products form">
<br/>
	<form accept-charset="utf-8" method="post" id="ProductAddForm" action="<@spring.url "/products/edit/${product.id?c}"/>">
		<fieldset>
			<@spring.formHiddenInput "product.state"/>
			<@spring.formHiddenInput "product.id"/>
			<@spring.formHiddenInput "product.sortOrder"/>
			<div class="input text required">
				<label for="ProductName">Name</label>
				<@spring.formInput "product.name", 'maxlength="45" class="span6"'/>
				<@spring.showErrors "product.name", "text-error"/>
			</div>
			<div class="input textarea">
				<label for="ProductDescription">Description</label>
					<@spring.formTextarea "product.description", 'rows="6" cols="30" style="display: none;"'/>
			</div>
			<div class="input textarea">
				<label for="ProductAtAGlance">At A Glance</label>
					<@spring.formTextarea "product.atAGlance", 'rows="6" cols="30" style="display: none;"'/>
			</div>
			<div class="input textarea">
				<label for="ProductOtherInfo">Other Info</label>
					<@spring.formTextarea "product.otherInfo", 'rows="6" cols="30" style="display: none;"'/>
			</div>
			<div class="input textarea">
				<label for="ProductOptionsAvailable">Options Available</label>
					<@spring.formTextarea "product.optionsAvailable", 'rows="6" cols="30" style="display: none;"'/>
			</div>
			<div class="row">
				<div class="span3">
					<label for="CategoryCategory">Category</label>
					<@spring.formSingleSelect "product.category", categoryOptions, 'class="span3 categorySelect"'/>
					<@spring.showErrors "product.category", "text-error"/>
				</div>
				<div class="span3">
					<label for="TagTag">Tag</label>
					<@spring.formSingleSelect "product.tag", tagOptions, 'class="span3 tagSelect"'/>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<div class="input text">
						<label for="ProductUrl">Url</label>
						<@spring.formInput "product.url", 'id="ProductUrl" maxlength="255" class="span6"'/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="span2">
					<label for="ProductPrice">Price</label>
					<div class="input-prepend">
						<span style="color: #444" class="add-on">$</span>
						<@spring.formInput "product.price", 'id="ProductPrice" maxlength="45" class="span1"'/> 
					</div>
				</div>
			</div>
		</fieldset>
		<div class="submit">
			<input type="submit" value="Submit" class="btn btn-primary">
			<a href="<@spring.url "/products"/>" data-title="Cancel" class="btn btn-danger"><i class="icon-remove"></i> Cancel</a>
		</div>
	</form>
</div>
<script type="text/javascript">    
    $(document).ready(function() {
        var buttons = ['formatting', '|', 'bold', 'italic', '|', 'unorderedlist', 'orderedlist', 'outdent', 'indent', '|', 'table','link' , '|', 'horizontalrule'];
        $('textarea').redactor({
            focus: false, 
            buttons: buttons
        });
        <#if product.category??>
        $(".categorySelect option[value=${product.category.id?c}]").attr('selected', 'selected');
        </#if>
        <#if product.tag??>
        $(".tagSelect option[value=${product.tag.id?c}]").attr('selected', 'selected');
        </#if>
         $('#ProductAddForm').find("input[type=text]:first").focus();
   });
</script>