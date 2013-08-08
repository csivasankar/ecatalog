<#import "/spring.ftl" as spring />
<@spring.bind "category" />
<div class="categories form">
	<form action="<@spring.url "/categories"/>" id="CategoryAddForm" method="post" enctype="application/x-www-form-urlencoded" accept-charset="utf-8" name="CategoryAddForm">
		<fieldset>
			<div class="input text">
				<label for="CategoryName">Name</label>
				<@spring.formInput "category.name", 'maxlength="45" class="requiredField"'/>
				<span class="text-error errorMessage" style="display:none;">may not be empty</span>
			</div>
		</fieldset>
		<div class="submit">
			<input id="submitBtn" class="btn" type="button" value="Submit" />
		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $('#submitBtn').click(function(){
    	$(".errorMessage").html("");
    	if($('.requiredField').val().trim().length == 0){
			$(".errorMessage").html("may not be empty").show();
		} else {
			var params = {ajax:true,name:$('#name').val()};
			$.ajax({
				  url: '<@spring.url "/categories/validateCategory"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=CategoryAddForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
    });
});
</script>