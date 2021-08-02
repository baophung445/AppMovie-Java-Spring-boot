$(document)
		.ready(
				function() {
					$("#divUpdate").prop('disabled', true);
					$("#divUpdateMember").prop('disabled', true);
					$("#divDelete").prop('disabled', true);

					$('#tableId').on(
							'click',
							function(e) {
								$(e.target).closest('tr').find(
										'input[name="chosenFilmDetail"]').prop(
										"checked", true);
							});

					$('#adminFeature').addClass('current-menu-item');

					$('#searchCondition').keyup(
							function(e) {
								var searchCondition = $('#searchCondition')
										.val();
								var filmId = $('#filmId').val();

								if (e.keyCode == 13) {
									window.location.replace('' + filmId
											+ '?searchCondition='
											+ searchCondition);
								}
							});

					$('#btnEdit')
							.click(
									function() {
										var filmDetailId = $(
												'input[name="chosenFilmDetail"]:checked')
												.val();

										var newFilmDetailEpisode = $(
												'#newFilmDetailEpisode').val();
										var newFilmDetailUrl = $(
												'#newFilmDetailUrl').val();
										var newFilmDetailDeploy = $(
												'#newFilmDetailDeploy').val();
										var newFilmDetailRequestEmail = $(
												'#newFilmDetailRequestEmail')
												.val();
										var newFilmDetailAcceptAccount = $(
												'#newFilmDetailAcceptAccount')
												.val();

										var token = $('#_csrf').attr('content');
										var header = $('#_csrf_header').attr(
												'content');
										var webDomain = $("#webDomain").val();

										if (newFilmDetailEpisode == ''
												|| newFilmDetailEpisode == null) {
											alert('Vide episode is invalid');
											return;
										}
										if (newFilmDetailUrl == ''
												|| newFilmDetailUrl == null) {
											alert('Url is invalid');
											return;
										}
										if (newFilmDetailDeploy == ''
												|| newFilmDetailDeploy == null) {
											alert('Deploy status is invalid');
											return;
										}
										if (newFilmDetailRequestEmail == ''
												|| newFilmDetailRequestEmail == null) {
											alert('Request email is invalid');
											return;
										}
										if (newFilmDetailAcceptAccount == ''
												|| newFilmDetailAcceptAccount == null) {
											alert('Accept account is invalid');
											return;
										}

										var filmDetailObject = {
											'episode' : newFilmDetailEpisode,
											'furl' : newFilmDetailUrl,
											'fddeploy' : newFilmDetailDeploy,
											'requestEmail' : newFilmDetailRequestEmail,
											'acceptAccount' : newFilmDetailAcceptAccount
										};

										$
												.ajax({
													type : "POST",
													headers : {
														Accept : "application/json; charset=utf-8",
														"Content-Type" : "application/json; charset=utf-8"
													},
													url : webDomain
															+ 'admin/editVideoDetail/'
															+ filmDetailId,
													beforeSend : function(xhr) {
														xhr.setRequestHeader(
																header, token);
														console
																.warn(xhr.responseText)
													},
													data : JSON
															.stringify(filmDetailObject),
													success : function(result) {
														alert("Rquest OK!");
														window.location
																.replace('');
														return result;
													},
													error : function(data,
															status, er) {
														alert("Request Fail!");
													}
												});
									});
					$('#btnEditMember')
					.click(
							function() {
								var filmDetailId = $(
										'input[name="chosenFilmDetail"]:checked')
										.val();

								var newFilmDetailEpisode = $(
										'#newFilmDetailEpisode').val();
								var newFilmDetailUrl = $(
										'#newFilmDetailUrl').val();
								var newFilmDetailDeploy = $(
										'#newFilmDetailDeploy').val();
								
								var token = $('#_csrf').attr('content');
								var header = $('#_csrf_header').attr(
										'content');
								var webDomain = $("#webDomain").val();

								if (newFilmDetailEpisode == ''
										|| newFilmDetailEpisode == null) {
									alert('Vide episode is invalid');
									return;
								}
								if (newFilmDetailUrl == ''
										|| newFilmDetailUrl == null) {
									alert('Url is invalid');
									return;
								}
								if (newFilmDetailDeploy == ''
										|| newFilmDetailDeploy == null) {
									alert('Deploy status is invalid');
									return;
								}			

								var filmDetailObject = {
									'episode' : newFilmDetailEpisode,
									'furl' : newFilmDetailUrl,
									'fddeploy' : newFilmDetailDeploy
								};

								$
										.ajax({
											type : "POST",
											headers : {
												Accept : "application/json; charset=utf-8",
												"Content-Type" : "application/json; charset=utf-8"
											},
											url : webDomain
													+ 'member/editVideoDetail/'
													+ filmDetailId,
											beforeSend : function(xhr) {
												xhr.setRequestHeader(
														header, token);
												console
														.warn(xhr.responseText)
											},
											data : JSON
													.stringify(filmDetailObject),
											success : function(result) {
												alert("Rquest OK!");
												window.location
														.replace('');
												return result;
											},
											error : function(data,
													status, er) {
												alert("Request Fail!");
											}
										});
							});
					$('#btnDelete')
							.click(
									function() {
										var filmDetailId = $(
												'input[name="chosenFilmDetail"]:checked')
												.val();

										window.location
												.replace('deleteVideoDetail/'
														+ filmDetailId);
									});
				});
function onclickDeleteFunction() {
	var index = $('input[name="chosenFilmDetail"]:checked').val();
	if (typeof index == 'undefined') {
		alert("Please chosen video!");
	} else {
		$("#divDelete").prop('disabled', false);
	}
}

function onclickEditFunction() {

	var index = $('input[name="chosenFilmDetail"]:checked').val();
	if (typeof index == 'undefined') {
		alert("Please chosen video!");
	} else {

		$('#newFilmDetailEpisode').val($('#filmDetailEpisode' + index).text());
		$('#newFilmDetailUrl').val($('#filmDetailUrl' + index).text());
		$('#newFilmDetailDeploy').val($('#flimDetailDeploy' + index).text());
		$('#newFilmDetailRequestEmail').val(
				$('#filmDetailRequestEmail' + index).text());
		$('#newFilmDetailAcceptAccount').val(
				$('#filmDetailAcceptAccount' + index).text());

		$("#divUpdate").prop('disabled', false);
		$("#divUpdateMember").prop('disabled', false);
	}
}

function searchFilm() {
	var searchCondition = $('#searchCondition').val();
	var filmId = $('#filmId').val();

	window.location
			.replace('' + filmId + '?searchCondition=' + searchCondition);
}