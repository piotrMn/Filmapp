$(function() {
	$("#datepick").datepicker({
		dateFormat : 'dd-mm-yy',
		inline : true,
		showOtherMonths : true,
		dayNamesMin : [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
	});

	var resultRows = $(".result-row");
	for (let i = 0; i < resultRows.length; i++) {
		if (i > 10) {
			resultRows.eq(i).hide();
		}
	}
	
	$("#next-btn").click(function() {
		var rows = $(".result-row").length;
		console.log(rows);
		var firstVisIndex = $("tr.result-row:visible").first().data("row");
		var lastVisIndex = $("tr.result-row:visible").last().data("row");
		if (rows == lastVisIndex) {
			for (let i = 0; i < 10; i++) {
				$(".result-row").eq(i).show();
			}
			for (let i = 10; i < rows; i++) {
				$(".result-row").eq(i).hide();
			}
		} else {
			for (let i = 0; i < lastVisIndex; i++) {
				$(".result-row").eq(i).hide();
			}
			for (let i = lastVisIndex; i < lastVisIndex + 10; i++) {
				$(".result-row").eq(i).show()
			}
		}
	})

	$("#prev-btn").click(function() {
		var rows = $(".result-row").length;
		var firstVisIndex = $("tr.result-row:visible").first().data("row");
		var lastVisIndex = $("tr.result-row:visible").last().data("row");
		if (firstVisIndex < 10) {
			for (let i = 0; i < 10; i++) {
				$(".result-row").eq(i).show();
			}
			for (let i = 10; i < rows; i++) {
				$(".result-row").eq(i).hide();
			}
		} else {
			for (let i = firstVisIndex - 1; i < rows; i++) {
				$(".result-row").eq(i).hide();
			}
			for (let i = firstVisIndex - 11; i < firstVisIndex - 1; i++) {
				if (i >= 0) {
					$(".result-row").eq(i).show();
				}
			}
		}
	})

	$("#past-btn").click(function() {
		$(".menu-items").not("#past-res").hide();
		$("#past-res").toggle();
	})

	$("#cancelled-btn").click(function() {
		$(".menu-items").not("#cancelled-res").hide();
		$("#cancelled-res").toggle();
	})

	$("#upcoming-btn").click(function() {
		$(".menu-items").not("#upcoming-res").hide();
		$("#upcoming-res").toggle();
	})

	$("#rep-btn").click(function() {
		$(".menu-items").not("#repertoire").hide();
		$("#repertoire").toggle();

	})
	$("#contact-btn").click(function() {
		$(".menu-items").not("#contact").hide();
		$("#contact").toggle();

	})
	$("#about-btn").click(function() {
		$(".menu-items").not("#about").hide();
		$("#about").toggle();
	})

	$("#main-btn").click(function() {
		$("#search-results").hide();
		$("#repertoire").hide();
	})
	
	$(".rep-book").click(function(event) {
		var title = $(event.target).parent().parent().children().eq(1).text();
		$("#title-select").val(title);
		$("#title-select").change();
	})
	
});
