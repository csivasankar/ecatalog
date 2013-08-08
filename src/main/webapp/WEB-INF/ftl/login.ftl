<#import "/spring.ftl" as spring />
<html>
<head>
<title><@spring.message "application_name" /></title>
</head>
<body onload='document.f.j_username.focus();'>
	<div class="row-fluid">
		<div class="span6 offset3">
			<h2><@spring.message "application_name" /></h2>
			<#if RequestParameters.login_error?exists>
			<div class="alert alert-error">
				<@spring.message "security_login_unsuccessful" /> <b>${SPRING_SECURITY_LAST_EXCEPTION.message}</b>
			</div>
			</#if>
			<div class="well" style="display: block;">
				<form name='f'
					action='<@spring.url "/resources/j_spring_security_check"/>'
					method='POST'>
					<div class="input text required">
						<label for="Username">Username</label> <input name="j_username"
							class="span12" maxlength="255" type="text" id="j_username" />
					</div>
					<div class="input password required">
						<label for="UserPassword">Password</label> <input
							name="j_password" class="span12" type="password" id="j_password" />
					</div>
					<div class="submit">
						<button type="submit" class="btn">
							<i class="icon-play-circle"></i> Login
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
