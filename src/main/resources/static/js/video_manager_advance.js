$(document)
	.ready(
		function() {
			$("#divUpdate").prop('disabled', true);
			$("#divDelete").prop('disabled', true);
			$("#divFilmDetail").prop('disabled', true);

			$('#tableId').on(
				'click',
				function(e) {
					$(e.target).closest('tr').find(
						'input[name="chosenFilm"]').prop(
							"checked", true);
				});

			$('#adminFeature').addClass('current-menu-item');

			$('#searchCondition')
				.keyup(
					function(e) {
						var searchCondition = $(
							'#searchCondition').val();

						if (e.keyCode == 13) {
							window.location
								.replace('videoManagerAdvance?searchCondition='
									+ searchCondition);
						}
					});

			$('#btnEdit')
				.click(
					function() {
						var form = $('#fileUploadForm')[0];

						var formData = new FormData(form);

						var filmId = $(
							'input[name="chosenFilm"]:checked')
							.val();

						var newFilmName = $('#newFilmName')
							.val();
						var newYear = $('#newYear').val();
						var newLength = $('#newLength').val();
						var newIntroduction = $(
							'#newIntroduction').val();
						var newDeployStatus = $(
							'#newDeployStatus').val();
						var newRequestStatus = $(
							'#newRequestStatus').val();
						var newEpisodeCount = $(
							'#newEpisodeCount').val();
						var newRequestEmail = $(
							'#newRequestEmail').val();
						var newAcceptAccount = $(
							'#newAcceptAccount').val();

						var token = $('#_csrf').attr('content');
						var header = $('#_csrf_header').attr(
							'content');
						var webDomain = $("#webDomain").val();

						if (newFilmName == ''
							|| newFilmName == null) {
							alert('Video name is invalid');
							return;
						}
						if (newYear == '' || newYear == null) {
							alert('Year is invalid');
							return;
						}
						if (newLength == ''
							|| newLength == null) {
							alert('Length is invalid');
							return;
						}
						if (newIntroduction == ''
							|| newIntroduction == null) {
							alert('Introduction is invalid');
							return;
						}
						if (newDeployStatus == ''
							|| newDeployStatus == null) {
							alert('Deploy status is invalid');
							return;
						}
						if (newRequestStatus == ''
							|| newRequestStatus == null) {
							alert('Request status is invalid');
							return;
						}
						if (newEpisodeCount == ''
							|| newRequestStatus == null) {
							alert('Request status is invalid');
							return;
						}
						if (newRequestEmail == ''
							|| newRequestEmail == null) {
							alert('Request email is invalid');
							return;
						}
						if (newAcceptAccount == ''
							|| newAcceptAccount == null) {
							alert('Accept account is invalid');
							return;
						}

						$
							.ajax({
								type: "POST",
								method: 'POST',
								data: formData,
								cache: false,
								contentType: false,
								processData: false,
								enctype: 'multipart/form-data',
								url: webDomain
									+ 'admin/editVideo/'
									+ filmId,
								beforeSend: function(xhr) {
									xhr.setRequestHeader(header, token);
									console.warn(xhr.responseText);
								},
								success: function(result) {
									
									swal("Good job!", "Bạn đã chỉnh sửa bộ phim này thành công!", "success");


									/*alert("Rquest OK!");*/
						setTimeout(function() {window.location
										.replace('');}, 2000);

									

									return result;
								},
								error: function(data,
									status, er) {


									alert("Request Fail!");
								}
							});
					});
			$('#btnDelete')
				.click(
					function() {
						var filmId = $(
							'input[name="chosenFilm"]:checked')
							.val();
						swal("Good job!", "Bạn đã xóa bộ phim này thành công!", "success");
						setTimeout(function() {
							window.location.replace('deleteFilm/'
								+ filmId);
						}, 2000);

					});
		});
function onclickDeleteFunction() {
	var index = $('input[name="chosenFilm"]:checked').val();
	if (typeof index == 'undefined') {
		swal("", "Vui lòng chọn video để xóa!", "warning");
		/*alert("Please chosen video!");*/
	} else {
		$("#divDelete").prop('disabled', false);
	}
}

function onclickEditFunction() {

	var index = $('input[name="chosenFilm"]:checked').val();
	if (typeof index == 'undefined') {
		swal("", "Vui lòng chọn video để sửa!", "warning");
		/*alert("Please chosen video!");*/
	} else {

		$('#newFilmName').val($('#filmName' + index).text());
		$('#newYear').val($('#filmYear' + index).text());
		$('#newLength').val($('#filmLength' + index).text());
		$('#newIntroduction').val($('#filmIntroduction' + index).text());
		$('#newDeployStatus').val($('#filmDeploy' + index).text());
		$('#newRequestStatus').val($('#flimRequest' + index).text());
		$('#newEpisodeCount').val($('#filmEpisodeCount' + index).text());
		$('#newRequestEmail').val($('#filmRequestEmail' + index).text());
		$('#newAcceptAccount').val($('#filmAcceptAccount' + index).text());

		$("#divUpdate").prop('disabled', false);
	}
}

function onclickFilmDetail() {
	var index = $('input[name="chosenFilm"]:checked').val();
	if (typeof index == 'undefined') {
		swal("", "Vui lòng chọn video để xem chi tiết!", "warning");
		/*alert("Please chosen video!");*/
	} else {
		window.location.replace('videoDetail/' + index + '?searchCondition=');
	}
}
function searchFilm() {
	var searchCondition = $('#searchCondition').val();

	window.location.replace('videoManagerAdvance?searchCondition='
		+ searchCondition);
}