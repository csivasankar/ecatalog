<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<!DOCTYPE html>
<div class="row-fluid">
		<div class="span10 offset1" style="margin-top: 10px"></div>
	</div>
	<h2>Cartoon</h2>
	<dl>
			<dt>Name</dt>
			<dd>${cartoon.name!}&nbsp;</dd>
			<dt>Modified</dt>
			<dd><#if cartoon.modified??>${cartoon.modified.time?date}</#if></dd>
			</dl>
			
	<div class="row-fluid">
		<div class="span4">	
			<h2>Icon</h2>			
			<#if cartoon??>			
			<div class="thumbnails span12">
				<div class="thumbnail span12" id="icon">
					<#if cartoon.icon??><img src="<@spring.url "/${cartoon.icon.largeImagePath!}" />"></img></#if>
				</div>
			</div>
			</#if>
		</div>

		<div class="span4 offset3">
			<h2>Cartoon</h2>
			<#if cartoon??>			
			<div class="thumbnails span12">
				<div class="thumbnail span12" id="image">
					<#if cartoon.image??><img src="<@spring.url "/${cartoon.image.largeImagePath!}" />"></img></#if>
				</div>
			</div>
			</#if>
		</div>
</div>