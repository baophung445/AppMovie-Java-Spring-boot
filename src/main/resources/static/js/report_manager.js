$(document).ready(function() {
	$('#reportManager').addClass('current-menu-item');
	$('.button-resolve').click(function() {
		var rpid = $(this).parent().next().text();
		var conf = confirm('Are you sure?');
		var hdnCurrentPage = $('#hdnCurrentPage').val();
		if (conf == true) {
			window.location.replace('/resolve-report/' + rpid + "/" + hdnCurrentPage);
		} else { 
			return;
		}
	});
});