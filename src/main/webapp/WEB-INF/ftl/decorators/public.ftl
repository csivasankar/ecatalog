<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title><#if title != ''><@spring.message "application_name" /> - ${title}<#else><@spring.message "application_name" /></#if> </title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<@spring.url "/resources/img/favicon.ico"/>" type="image/x-icon" rel="icon" ></link>
	<link href="<@spring.url "/resources/img/favicon.ico"/>" type="image/x-icon" rel="shortcut icon" ></link>
    <link href="<@spring.url "/resources/css/bootstrap.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<@spring.url "/resources/css/bootstrap-responsive.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<@spring.url "/resources/css/custom.css"/>" type="text/css" rel="stylesheet" />
	<link href="<@spring.url "/resources/css/uploadify.css"/>" type="text/css" rel="stylesheet"/>
	<link href="<@spring.url "/resources/css/redactor.css"/>" type="text/css" rel="stylesheet"/>
	<link href="<@spring.url "/resources/css/jquery-ui.css"/>" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<@spring.url "/resources/scripts/jquery-1.7.1.min.js"/>"></script>
	<script type="text/javascript" src="<@spring.url "/resources/bootstrap/js/bootstrap.min.js"/>"></script>
	<!-- <style type="text/css">
		body { padding-top: 60px;}
	</style> -->
	${head}
</head>
<body>
<div id="wrapper">
	<#include "../header.ftl"/>
    <div id="content" class="container">
    ${body}
    </div>
	<#include "../footer.ftl"/>
</div>

<script type="text/javascript" src="<@spring.url "/resources/script/fileuploader.js"/>"></script>
<script type="text/javascript" src="<@spring.url "/resources/script/redactor.js"/>"></script>
<script type="text/javascript" src="<@spring.url "/resources/script/tsort.js"/>" ></script>
<script type="text/javascript" src="<@spring.url "/resources/script/popups.js"/>"></script>
<script type="text/javascript" src="<@spring.url "/resources/script/jquery.ui.js"/>"></script>
</body>
</html>