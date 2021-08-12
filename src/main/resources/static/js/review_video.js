$(document).ready(function() {
	$('#urlRaw').change(function() {
		var rawUrl = $('#urlRaw').val();
		var urlEmbed = YouTubeGetID(rawUrl);
		$('#url').val(urlEmbed);
	});
	$('#btnAddEpisode').click(function() {
		var episode = $('#episodeNumber').val();
		var furl = $('#url').val();
		var fid = $('#fid').val();
		var emailContact = $('#emailContact').val();
		var webDomain = $('#webDomain').val();
		var token = $('#_csrf').attr('content');
		var header = $('#_csrf_header').attr('content');

		if (episode === '' || episode === null || episode === 0) {
			alert("Video episode is invalid.")
			return;
		}

		if (furl === '' || furl === null) {
			alert("Url is invalid.")
			return;
		}

		var filmDetailObject = {
			'episode' : episode,
			'furl' : furl,
			'requestEmail' : emailContact
		}

		$.ajax({
			type : "POST",
			headers : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			url : webDomain + 'api/video/addVideoRequest/' + fid,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
				console.warn(xhr.responseText)
			},
			data : JSON.stringify(filmDetailObject),
			success : function(result) {
				if (result != null) {
					alert('Request OK!');
					$('#addEpisode').modal('toggle');
				}

				return result;
			},
			error : function(data, status, er) {
				alert('Request Fail!');
			}
		});
	})
});
function YouTubeGetID(url) {
	url = url.split(/(vi\/|v%3D|v=|\/v\/|youtu\.be\/|\/embed\/)/);
	var id = undefined !== url[2] ? url[2].split(/[^0-9a-z_\-]/i)[0] : url[0];

	return 'https://www.youtube.com/embed/' + id;
}

function ratingVideo(id) {
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
}