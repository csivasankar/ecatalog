<#import "/spring.ftl" as spring />
<@spring.bind "tag" />
<div class="tag form">
	<form action="<@spring.url "/tags"/>" id="TagUpdateForm" method="POST" enctype="application/x-www-form-urlencoded" accept-charset="utf-8" name="TagUpdateForm">
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
			<div class="input text">
				<label for="TagName">Name</label>
				<@spring.formInput "tag.name", 'maxlength="45" class="requiredField"'/>
				<span class="text-error errorMessage" style="display:none;">may not be empty</span>
			</div>
			<@spring.formHiddenInput "tag.id"/>
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
				  url: '<@spring.url "/tags/validateTag"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=TagUpdateForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
    });
});
</script>