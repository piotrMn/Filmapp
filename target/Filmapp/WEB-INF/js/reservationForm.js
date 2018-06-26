$(function() {
	
	$("#seats-input").on("input", function(){
		var priceString = $("#price").text();
		var price = parseFloat(priceString);
		var seatsString = $("#seats-input").val();
		var seats = parseInt(seatsString);
		var priceTotal = price * seats;
		$("#price-total").text(priceTotal.toFixed(2));
	})
});


