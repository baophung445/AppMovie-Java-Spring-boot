$(document).ready(function() {
	$('#signin').addClass('current-menu-item');
});

function showP() {
	var x = document.getElementById('password');
	if (x.type === 'password') {
		x.type = 'text';
	} else {
		x.type = 'password';
	}
}