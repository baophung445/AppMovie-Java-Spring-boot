$(document).ready(function() {
	$('#addfilm').addClass('current-menu-item');
	$('#urlRaw').change(function() {
		var rawUrl = $('#urlRaw').val();
		var urlEmbed = YouTubeGetID(rawUrl);
		$('#url').val(urlEmbed);
	});
	$('#btnCommit').click(function() {
		var email = $('#requestEmail').val();
		checked = $("input[type=checkbox]:checked").length;

		if (!validateEmail(email)) {
			alert("Invalid email address.");
			return false;
		}

		if (!checked) {
			alert("You must check at least one checkbox.");
			return false;
		}
		
		if ($('#url').val() === null) {
			var rawUrl = $('#urlRaw').val();
			var urlEmbed = YouTubeGetID(rawUrl);
			$('#url').val(urlEmbed);
		}
	});
	$('#selectForm').change(function() {
		var val = $("#selectForm option:selected").text();
		if (val == 'Video') {
			location.replace("/add-new-video");
		}
		if (val == 'Group Video') {
			location.replace("/add-new-group-video");
		}
	});
});
function YouTubeGetID(url) {
	url = url.split(/(vi\/|v%3D|v=|\/v\/|youtu\.be\/|\/embed\/)/);
	var id = undefined !== url[2] ? url[2].split(/[^0-9a-z_\-]/i)[0] : url[0];

	return 'https://www.youtube.com/embed/' + id;
}
function validateEmail(email) {
	const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase());
}