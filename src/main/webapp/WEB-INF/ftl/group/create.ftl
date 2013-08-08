<#import "/spring.ftl" as spring />
<@spring.bind "group" />
<div class="groups form">
	<form action="<@spring.url "/groups"/>" id="GroupAddForm" method="post" enctype="application/x-www-form-urlencoded" accept-charset="utf-8" name="GroupAddForm">
		<fieldset>
			<div class="input text">
				<label for="GroupName">Name</label>
				<@spring.formInput "group.name", 'maxlength="100" class="requiredField"'/>
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
				  url: '<@spring.url "/groups/validateGroup"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=GroupAddForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
    });
});
</script>