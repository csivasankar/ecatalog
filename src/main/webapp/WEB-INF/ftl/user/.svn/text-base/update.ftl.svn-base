<#import "/spring.ftl" as spring /> 
<@spring.bind "user" />
<div class="users form">
	<form id="UserUpdateForm" method="post" accept-charset="utf-8" action="<@spring.url "/users"/>" onsubmit="return validation();">
		<span class="text-error errorMessage" style="display:none;">Username/Password may not be empty</span>
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
		<@spring.formHiddenInput "user.id"/>
			<div class="input text required">
				<b>Username</b>
				<label for="UserUsername">${user.username}</label>
				<@spring.formHiddenInput "user.username", 'maxlength="255" id="UserUsername" class="requiredField"'/>
			</div>
			<div class="input password required">
				<label for="UserPassword">Password</label>
				<@spring.formPasswordInput "user.password", 'maxlength="40" id="UserPassword" class="requiredField"'/>
			</div>
			<div class="input select required">
				<label for="UserGroupId">Group</label>
				<@spring.formSingleSelect "user.group", groupOptions, 'id="UserGroupId"'/>
			</div>
		</fieldset>
		<div class="submit">
			<input class="btn" type="submit" value="Submit" />
		</div>
	</form>
</div>
