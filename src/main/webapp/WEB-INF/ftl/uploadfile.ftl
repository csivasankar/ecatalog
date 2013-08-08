<#import "/spring.ftl" as spring />
<div rel="${key?c}" id="photos" class="upload">
	<div class="qq-uploader">
		<div class="qq-upload-drop-area" style="display: none;">
			<span>Drop files here to upload</span>
		</div>
		<div class="qq-upload-button btn btn-large btn-block btn-primary" style="position: relative; overflow: hidden; direction: ltr;">
		Upload
		<input type="file" name="file" id="file" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;">
		</div>
		<hr style="margin:10px 0">
		<ul class="qq-upload-list unstyled">
		</ul>
	</div>
</div>
   <h2>File</h2>
      <ul id="sortableImages"  class="thumbnails ui-sortable">
      		 <#if fileObj??>
	      		 <li class="span2" id="file_${fileObj.id?c}" title="${fileObj.name!}">
	      		 	        <div class="thumbnail">
	                            <a href="<@spring.url "/upload/delete/${page}/${key?c}/${fileObj.id?c}"/>" class="close closeFile">x</a>
	                            <#if page = 'category'>
	                            	<h4><@spring.url "/resources/${fileObj.pathByName!}"/> </h4>
	                            <#else>
	                            	<h4><@spring.url "/resources/${fileObj.filePath!}"/> </h4>
	                            </#if>
	                        </div>    
	              </li>
	              </#if>
      </ul>      
<script type="text/javascript">
	$(function() {
	    var uploader = [];
	    $('.upload').each(function(i){
			 var id = $(this).attr('rel');
			 uploader[i] = new qq.FileUploader({
					    element:this,
					    multiple: false,
					    action: '<@spring.url "/upload/file/${page}/${key?c}"/>',
					    onComplete: function(id, fileName, responseJSON){
					    	//console.log(responseJSON);
					    	var deleteUrl = '<@spring.url "/upload/delete/${page}/${key?c}/"/>';
							if(responseJSON.success){
							        var file = responseJSON.file; 
							        $( "#sortableImages li" ).remove();
							        var li = $('<li id=file_"' + file.id +'" class="span2" title="' + file.name + '">').html(
							                 '<div class="thumbnail">'
							             +   '   <a href="' + deleteUrl + file.id + '" class="close closeFile">x</a>'
							             +   '   <h4>'+ '<@spring.url "/resources/"/>'+ file.path + '</h4>'
							             +   '</div>'
							             );
							        $( "#sortableImages" ).append(li);
						    }
					    }
					});
		});
	});
</script>