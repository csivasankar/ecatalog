$(function(){
	$( "#sortable" ).sortable({
		update : function(event, ui) {
			$.ajax({
			  type: "POST",
			  url: $("#sortPageURL").val(),
			  data: {"sortedData": $("#sortable").sortable('toArray').toString()},
			  success:function(data){
				 location.reload();
			  }
			});
		}
	});
	$("#sortable").disableSelection();
	$( "#sortable_products" ).sortable();
	$("#sortable_products").disableSelection();
});

var saveProductsOrder = function() {
	$.ajax({
		type: "POST",
		url: $("#sortPageURL").val(),
		data: {"sortedData": $("#sortable_products").sortable('toArray').toString()},
		success:function(data){
			reloadProductsByCategory($("#selectedCategory").attr("var"));
		}
	});
};

var reloadProductsByCategory = function(categoryId) {
	window.location = $("#reloadPageURL").val() +"?category_id="+ categoryId;
};