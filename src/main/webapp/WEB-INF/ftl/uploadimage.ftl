<#import "/spring.ftl" as spring />
<div rel="${key?c}" id="photos" class="upload">
	<div class="qq-uploader">
		<div class="qq-upload-drop-area" style="display: none;">
			<span>Drop images here to upload</span>
		</div>
		<div class="qq-upload-button btn btn-large btn-block btn-primary" style="position: relative; overflow: hidden; direction: ltr;">
		Upload
		<input type="file" <#if page = 'product'>multiple="multiple"</#if> name="file" id="file" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;">
		</div>
		<hr style="margin:10px 0">
		<ul class="qq-upload-list unstyled">
		</ul>
	</div>
</div>
<#if page = 'product'>
<a id="sortByName" class="sort_by_name btn" href="javascript:void(0);">sort by name</a>
</#if> 
   <h2><#if page = 'icon'>Icon<#else>Images</#if></h2>
      <ul id="sortableImages" class="thumbnails ui-sortable">
      	<#if page = 'product'>
      		<#list imageObj as img>
      			<#if img.image??>
	      		 <li class="span2" id="${img.id?c}" title="${img.image.name!}">
	      		 	        <div class="thumbnail">
	                            <a href="<@spring.url "/appimages/delete/${page}/${key?c}/${img.id?c}"/>" class="close closeImage">x</a>
	                            <img src="<@spring.url "/${img.image.thumbImagePath!}"/>"/>
	                        </div>    
	              </li>
	              </#if>
            </#list>
          <#else>      		 
      		 	<#if imageObj??>
 	      		 <li class="span2" id="${imageObj.id?c}" title="${imageObj.name!}">
	      		 	        <div class="thumbnail">
	                            <a href="<@spring.url  "/appimages/delete/${page}/${key?c}/${imageObj.id?c}"/>" class="close closeImage">x</a>
	                            <img src="<@spring.url "/${imageObj.thumbImagePath!}"/>"/>
	                        </div>    
	              </li>
	              </#if>
          </#if> 
      </ul>      
<script type="text/javascript">
	$(function() {
		var refreshPage = false;
	    var uploader = [];
	    $('.upload').each(function(i){
			 var id = $(this).attr('rel');
			 uploader[i] = new qq.FileUploader({
					    element:this,
					    multiple:<#if page = 'product'>true<#else>false</#if>,
					    action: '<@spring.url "/upload/image/${page}/${key?c}"/>',
					    onComplete: function(id, fileName, responseJSON){
                            //console.log(responseJSON);
                            var deleteUrl = '<@spring.url "/appimages/delete/${page}/${key?c}/"/>';
                            var showUrl = '<@spring.url "/"/>';
                            var page = '${page}';
							if(responseJSON.success){
									var img = responseJSON.image; 
							        if(page === "category" || page === "cartoon" || page === "icon" || page === "tag"){
							        	$( "#sortableImages li" ).remove();
							        }
							        var li = $('<li id="' + img.id +'" class="span2" title="' + img.name + '">').html(
							                 '<div class="thumbnail">'
							             +   '	 <a href="' + deleteUrl + img.id + '" class="close closeImage">x</a>'
							             +   '   <img src="'+ showUrl + img.path + '"/>'
							             +   '</div>'
							             );
							        $( "#sortableImages" ).append(li);
							        refreshPage = true;
						    }
					    }
					});
		});
		$( "#sortableImages" ).sortable({
			placeholder: "highlight span2",
			update : function(event, ui) {
				$.ajax({
				  type: "POST",
				  url: '<@spring.url "/appimages/sort/${page}/${key?c}" />',
				  data: {"sortedData": $("#sortableImages").sortable('toArray').toString()}
				});
			}
		});
	    
		<#if page = 'product'>	    
	    $("#sortByName").on("click", function(){
	    	$('ul#sortableImages > li').tsort({attr:'title'});
	    	var sortedData = $("#sortableImages").sortable('toArray').toString();
	    	if(sortedData.length > 0) {
	    	$.ajax({
				  type: "POST",
				  url: '<@spring.url "/appimages/sort/${page}/${key?c}" />',
				  data: {"sortedData": sortedData }
			});
	    	}
	    });
	    </#if>
	    /* $(".modal-backdrop, .modal-header .close").click(function() {
	    	if (refreshPage) {
	    		window.location.reload();
	    	}
		}); */
	});
</script>