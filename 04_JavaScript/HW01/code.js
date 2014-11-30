$(document).ready(function() {
	$("p").click(function() {
		$("ul").append($("<li>").text("another piece of text"))
	});
});