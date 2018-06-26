$(function() {
	$("#datepick").datepicker({
		dateFormat : 'dd-mm-yy',
		inline : true,
		showOtherMonths : true,
		dayNamesMin : [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
	});

	$("#past").on("click", function() {
		$("#past-res").toggleClass("hide");
	})
	
	$("#cancelled").on("click", function() {
		$("#cancelled-res").toggleClass("hide");
	})
	
	$("#upcoming").on("click", function() {
		$("#upcoming-res").toggleClass("hide");
	})

	$(".show-details").on("click", function() {
		$(this).parent().parent().next().toggleClass("hide");
	})

	$("#rep-btn").on("click", function() {
		$("#repertoire").toggle();
		
	})
	$("#cont-btn").on("click", function() {
		$("#contact").toggle();
		
	})
	$("#about-btn").on("click", function() {
		$("#about").toggle();
	})
	
	$(".rep").on("click", function(event){
		$(".topnav-content").hide();
		var title = $(event.target).parent().prev().prev().prev().prev().prev().text();
		$("#title-input").val(title);
	})
	
});
