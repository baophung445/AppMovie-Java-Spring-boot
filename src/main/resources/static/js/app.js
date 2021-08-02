(function($, document, window) {

	$(document).ready(function() {


		$('#btnSearch').keyup(function(e) {
			var textSearch = $('#menu-item-search input').val();

			/*$('#btnSearch').keyup(function() {
		var textSearch = $('.menu-item-search input:text').val();*/

			/*if (e.keyCode == 13) {
				if (textSearch === '' || textSearch === null) {
					if ($('.menu-item-search input:text').is(":visible")) {
						$('.menu-item-search input:text').hide();
						$('#btnSearch').show();
					}
					else {
						$('.menu-item-search input:text').show();
						$('#btnSearch').hide();
						/*$('.menu-item-search input:text').focus();
					}
					return;
				}
			} */
			if (e.keyCode == 13) {
				if (textSearch === '' || textSearch === null){
					return;
				}
				window.location.replace("/search?text=" + textSearch);
			}
		});


		/*$('.menu-item-search input:text').keyup(function(e){
			var textSearch = $('.menu-item-search input:text').val();
			
			if(e.keyCode == 13)
			{
				if (textSearch === '' || textSearch === null){
					return;
				}
				window.location.replace("/search?text=" + textSearch);
			}	
		});
		
		$('.menu-item-search input:text').blur(function(){
			$('.menu-item-search input:text').val('');
			$('.menu-item-search input:text').hide();
			$('#btnSearch').show();
		});*/


		// Cloning main navigation for mobile menu
		$(".mobile-navigation").append($(".main-navigation .menu").clone());

		// Mobile menu toggle 
		$(".menu-toggle").click(function() {
			$(".mobile-navigation").slideToggle();
		});
		$(".search-form button").click(function() {
			$(this).toggleClass("active");
			var $parent = $(this).parent(".search-form");

			$parent.find("input").toggleClass("active").focus();
		});


		$(".slider").flexslider({
			controlNav: false,
			prevText: '<i class="fa fa-chevron-left"></i>',
			nextText: '<i class="fa fa-chevron-right"></i>',
		});
		if ($(".map").length) {
			$('.map').gmap3({
				map: {
					options: {
						maxZoom: 14
					}
				},
				marker: {
					address: "40 Sibley St, Detroit",
				}
			},
				"autofit");

		}
	});

	$(window).load(function() {

	});

})(jQuery, document, window);