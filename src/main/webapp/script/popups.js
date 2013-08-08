(function($){
	$('a.ajaxModal').on('click',function(e){
        e.preventDefault();
        $('#ModalWin').modal();
        //console.log(e.target);
        $('#data_target_modal').html('');
        $('#loading_img').show();
        //console.log('modal');
        $('#modalTitle').text($(this).data("title"));
        var params = {ajax:true};
        $.ajax({
			  url: this.href,
			  data : params,
			  success : function(content){ 
				  //console.log('dialog');
				  $('#loading_img').hide();
				  $('#data_target_modal').html(content);
				  setTimeout(function() {
					  $('#data_target_modal').find("input[type=text]:first").focus();
				  }, 500);
			  },
			  error : function(){
				  $('#loading_img').hide();
				  $('#data_target_modal').addClass('text-error').html('<b>Error while loading the content.</b>');
			  }
		});
        
    });  
	$('#ModalWin').on('show',function(e){
		//console.log('show');
	});
    $('#ModalWin').on('shown',function(e){
    	//console.log('shown');
		$(this).find("input[type=text]:first").focus();
    });
    $('#ModalWin').on('hidden', function (e) {});
    $("#ModalWin").keypress(function(event) {
        if (event.which == 13 || event.keyCode == 13) {
                $('#submitBtn').trigger('click');
                return false;
        }
    });
    $('.closeImage').live('click',function(e){
        e.preventDefault();
        $.post(this.href,{
                data:{ id : 'false'}
        });
        $(this).parent().parent().remove();
    });
    $('.closeFile').live('click',function(e){
        e.preventDefault();
        $.post(this.href,{
            data:{ id : 'false'}
        });
        $(this).parent().parent().remove();
    });
})(jQuery);
var validation = function(){
	var action = true;
	$(".requiredField").each(function(){
		if($(this).val().trim().length == 0){
			$(".errorMessage").show();
			action = false;
		}
	});
	return action;
};