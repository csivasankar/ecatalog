<#import "/spring.ftl" as spring />
<@spring.bind "group" />
<div class="groups form">
	<form action="<@spring.url "/groups"/>" id="GroupUpdateForm" method="post" enctype="application/x-www-form-urlencoded" accept-charset="utf-8" name="GroupUpdateForm">
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
			<div class="input text">
				<label for="GroupName">Name</label>
				<@spring.formInput "group.name", 'maxlength="100" class="requiredField"'/>
				<span class="text-error errorMessage" style="display:none;">may not be empty</span>
			</div>
			<@spring.formHiddenInput "group.id"/>
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
			var params = {ajax:true,name:$('#name').val(), id:$('#id').val()};
			$.ajax({
				  url: '<@spring.url "/groups/validateGroup"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=GroupUpdateForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
    });
});
</script>