$(document)
		.ready(
				function() {
					$('#addfilm').addClass('current-menu-item');
					$('#urlRaw1').change(function() {
						var rawUrl = $('#urlRaw1').val();
						var urlEmbed = YouTubeGetID(rawUrl);
						$('#url1').val(urlEmbed);
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

					});
					$('#selectForm').change(function() {
						var val = $("#selectForm option:selected").text();
						if (val == 'Phim lẻ') {
							location.replace("/add-new-video");
						}
						if (val == 'Phim bộ') {
							location.replace("/add-new-group-video");
						}
					});
					$('#btnAddUrl')
							.click(
									function() {
										var urlIndex = parseInt($('#urlIndex')
												.val());
										var rawUrlId = 'urlRaw' + urlIndex;
										var urlId = 'url' + urlIndex;
										$('#urlPlace')
												.append(
														'<input type="text" style="margin-bottom: 2%" placeholder="Video '
																+ urlIndex
																+ ' url" id="'
																+ rawUrlId
																+ '" class="form-control" required="required"/>');
										$('#urlPlace').append(
												'<input type="hidden" name="listFilmUrl" id="'
														+ urlId + '"/>');
										rawUrlId = '#' + rawUrlId;
										urlId = '#' + urlId;

										$(rawUrlId)
												.change(
														function() {
															var rawUrlIndex = $(
																	rawUrlId)
																	.val();
															var urlEmbedIndex = YouTubeGetID(rawUrlIndex);
															$(urlId)
																	.val(
																			urlEmbedIndex);
														});
										$('#urlIndex').val(urlIndex + 1);
									});
					$('#btnDeleteUrl').click(function() {
						var urlIndex = parseInt($('#urlIndex').val()) - 1;
						if (urlIndex == 1) {
							return;
						}
						var rawUrlId = 'urlRaw' + urlIndex;
						var urlId = 'url' + urlIndex;

						rawUrlId = '#' + rawUrlId;
						urlId = '#' + urlId;

						$('input').remove(rawUrlId);
						$('input').remove(urlId);

						$('#urlIndex').val(urlIndex);
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