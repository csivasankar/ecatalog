<#import "/spring.ftl" as spring />
<div class="tags view">
		<h2>Tag</h2>
		<dl>
			<dt>Id</dt>
			<dd>${tag.id?c!}&nbsp;</dd>
			<dt>Name</dt>
			<dd>${tag.name!}&nbsp;</dd>
			<dt>Modified</dt>
			<dd><#if tag.modified??>${tag.modified.time?date}</#if>&nbsp;</dd>
			<dt>Image</dt>
			<dd><#if tag.image??>
					<img src="<@spring.url "/${tag.image.largeImagePath!}"/>"/>
				</#if>
			</dd>
		</dl>
	</div>
</div>