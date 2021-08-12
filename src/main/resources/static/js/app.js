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
				if (textSearch === '' || textSearch === null) {
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

	/*function ratingVideo(id) {
	var ratingPoint = 0;
	var fid = $('#fid').val();
	var webDomain = $('#webDomain').val();

	switch (id) {
	case 'starhalf':
		ratingPoint = 0.5;
		break;
	case 'star1':
		ratingPoint = 1;
		break;
	case 'star1half':
		ratingPoint = 1.5;
		break;
	case 'star2':
		ratingPoint = 2;
		break;
	case 'star2half':
		ratingPoint = 2.5;
		break;
	case 'star3':
		ratingPoint = 3;
		break;
	case 'star3half':
		ratingPoint = 3.5;
		break;
	case 'star4':
		ratingPoint = 4;
		break;
	case 'star4half':
		ratingPoint = 4.5;
		break;
	case 'star5':
		ratingPoint = 5;
		break;
	}

	$
			.ajax({
				type : "GET",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : webDomain + 'api/video/ratingVideo/' + fid + '/'
						+ ratingPoint,
				success : function(result) {
					return result;
				},
				error : function(data, status, er) {
					alert('error: ' + data.tenhang + ' status: ' + status
							+ ' er:' + er);
				}
			});
	$('#rating :input').attr('disabled', true);
	$('#rating').hide();
	$('#yourPoint').text(ratingPoint);
}*/


	


})(jQuery, document, window);