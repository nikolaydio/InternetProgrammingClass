$(document).ready(function() {
	"use strict"
	console.log($("div#footer a:first").attr("title"));
	console.log($("#home .inscreen div:first p").text());
	$("#menu-top-level-menu").append($("<li>").html($("<a>").attr("id", "newbutton").text("new button")));

	$("div#footer").prepend($("<div>").attr("id", "dynamiccontent"));
	$("#dynamiccontent").append($("<input>").attr("id", "textinput"));
	$("#dynamiccontent").append($("<button>").attr("id", "addbutton").text("Button"));
	$("#dynamiccontent").append($("<ul>").attr("id", "posts"));

	$("#newbutton").click(function () {
		//alert("hello world");
	});
	$("#newbutton").click(function () {
		var first = $("#home .inscreen div:first").detach();
		var second = $("#home .inscreen div:first").detach();
		$("#home .inscreen").prepend(second, first);
	});

	//Handy function for adding elements in the list
	function add_element(id, title) {
		var x = $("<p>").text("X");
		var element = $("<li>")
			.append($("<p>").text(title))
			.append(x);
		x.click(function () {
			var confirmation = confirm('remove post?');
			if(confirmation) {
				$.ajax("http://jsonplaceholder.typicode.com/posts/" + id, {
					method: 'DELETE'
				}).then(function(data) {
					$(element).remove();
				});
			}
		});
		$("#posts").append(element);
	}

	//initial population of the list
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
		method: "GET"
	}).then(function(data) {
		var i = 0;
		$.each(data, function(key, val) {
			add_element(val.id, val.title);
			if(++i >= 5) {
				return false;
			}
		});
		
	});

	//addional population based on user requests
	$("#addbutton").click(function() {
		if($("#textinput").val().length == 0) {
			alert("you must enter text");
			return 0;
		}
		$.ajax('http://jsonplaceholder.typicode.com/posts', {
		  method: 'POST',
		  data: {
		    title: $("#textinput").val(),
		    body: 'bar',
		    userId: 104546
		  }
		}).then(function(data) {
			console.log("http://jsonplaceholder.typicode.com/posts/" + data.id);
			$.ajax("http://jsonplaceholder.typicode.com/posts/" + data.id, {
				method: "GET"
			}).then(function(ret_data) {
				add_element(data.id, ret_data.title);
			});


		});
	});

	//Task 17+. Filtering
	$('ul#posts').before('<input id="filterInput" type="text"/>');

	$('input#filterInput').change(function() {
		$.ajax("http://jsonplaceholder.typicode.com/posts?userId=" + $(this).val(), {
			method: "GET"
		}).then(function(ret_data) {
			$("#posts").html("")
			$.each(ret_data, function(key, val) {
				add_element(val.id, val.title);
			});
		})
	})
});