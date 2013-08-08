<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Promotions</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="promotions view">
		<h2>Promotion</h2>
		<dl>
			<dt>Id</dt>
			<dd>${promotion.id?c!}&nbsp;</dd>
			<dt>Category</dt>
			<dd><#if promotion.category??>${promotion.category.name!}</#if>&nbsp;</dd>
			<dt>Offer</dt>
			<dd>${promotion.offer!}&nbsp;</dd>
			<dt>Overview</dt>
			<dd>${promotion.overview!}&nbsp;</dd>
			<dt>Availability</dt>
			<dd>${promotion.availability!}&nbsp;</dd>
			<dt>ePromo</dt>
			<dd>${promotion.epromo!}&nbsp;</dd>
			<dt>End Date</dt>
			<dd><#if promotion.endDate??>${promotion.endDate.time?date}</#if>&nbsp;</dd>			
		</dl>
	</div>
</div>
</body>
</html>