$(function() {

	$("#seats-input").on("change", function() {
		var priceString = $("#price").text();
		var price = parseFloat(priceString);
		var seatsString = $("#seats-input").val();
		var seats = parseInt(seatsString);
		var priceTotal = price * seats;
		$("#price-total").text(priceTotal.toFixed(2));
	})

	$("td.free").on("click", function(event) {
		$(this).toggleClass("booked");
		if ($("td.booked").length <= 6) {
			$(this).change();
		} else {
			$(this).removeClass("booked");
		}

	})

	$("td.free").on("change", function(event) {
		var booked = $("td.booked").length;
		$("#seats-input").val(booked);
		$("#seats-input").change();
		$("#booked-seats").empty();
		$("#seats-codes").val("");
		$("td.booked").each(function(index, element) {
			var row = $(element).parent().data("row");
			var seat = $(element).data("seat");
			var text = "szereg: " + row + " miejsce: " + seat;
			var newLi = $("<li>");
			$(newLi).text(text);
			$(newLi).appendTo("#booked-seats");
			var seatCodes = $("#seats-codes").val() + row + ":" + seat + "-";
			$("#seats-codes").val(seatCodes);
		})
	})
});
