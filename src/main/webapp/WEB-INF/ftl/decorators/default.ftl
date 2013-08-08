<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title><@spring.message "application_name" /></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<@spring.url "/resources/img/favicon.ico"/>" type="image/x-icon" rel="icon" ></link>
	<link href="<@spring.url "/resources/img/favicon.ico"/>" type="image/x-icon" rel="shortcut icon" ></link>
    <link href="<@spring.url "/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<@spring.url "/resources/bootstrap/css/bootstrap-responsive.min.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<@spring.url "/resources/css/custom_l.css"/>" type="text/css" rel="stylesheet" />    
	${head}
</head>
<body>
<div id="wrapper">
    <div id="content" class="container">
    ${body}
    </div>
</div>
<script type="text/javascript" src="<@spring.url "/resources/scripts/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript" src="<@spring.url "/resources/bootstrap/js/bootstrap.js"/>"></script>
</body>
</html>