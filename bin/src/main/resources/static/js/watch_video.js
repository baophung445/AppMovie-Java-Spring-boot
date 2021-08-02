$(document).ready(function() {
	$('#1').attr('class', 'btn btn-warning');
	$('#btnCreate').click(function() {
		var type = $('#selectTypeReport option:selected').text().trim();
		var description = $('#reportDescription').val().trim();
		var file = $('input[type="file"]').val().trim();
		if (type && description && (file || type == "It's good")) {
			var form = $('#fileUploadForm')[0];

			var formData = new FormData(form);

			var token = $('#_csrf').attr('content');
			var header = $('#_csrf_header').attr('content');
			var webDomain = $("#webDomain").val();

			$.ajax({
				type : "POST",
				method : 'POST',
				data : formData,
				cache : false,
				contentType : false,
				processData : false,
				enctype : 'multipart/form-data',
				url : webDomain + 'report/api/createReport',
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
					console.warn(xhr.responseText)
				},
				success : function(result) {
					alert("Rquest OK!");
					$('#createModal').modal('toggle');
					return result;
				},
				error : function(data, status, er) {
					alert("Request Fail!");
				}
			});
		} else {
			alert('Please fill in all field.');
			return;
		}
	});
});

function addComment() {
	var webDomain = $("#webDomain").val();
	var fid = $("#fid").val();
	var content = $('#contentComment').val();
	var commentShow = $('#commentShow').val();
	var countComment = $('#countComment').val();
	var cCount = $('#cCount').text();
	cCount = parseInt(cCount) + 1;

	var wguestAvatartUrl = $('#wguestAvatartUrl').val();
	var wmemberAvatartUrl = $('#wmemberAvatartUrl').val();
	var wadminAvatarUrl = $('#wadminAvatarUrl').val();

	if (content === '' || !content || /^\s*$/.test(content)) {
		alert("Please enter something!")
		return;
	}

	var token = $('#_csrf').attr('content');
	var header = $('#_csrf_header').attr('content');

	var comment = {
		'uname' : 'Guest',
		'content' : content,
		'fid' : parseInt(fid),
		'commentShow' : commentShow
	};

	$
			.ajax({
				type : "POST",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : webDomain + 'api/comment/addComment',
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
					console.warn(xhr.responseText)
				},
				data : JSON.stringify(comment),
				success : function(result) {
					if (result != null) {
						var newHtml = '';
						$
								.each(
										result,
										function(index, comment) {
											if (index + 1 > commentShow) {
												return;
											}
											newHtml += '<li class="list-group-item">';

											if (comment.uname == 'Guest') {
												newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
														+ '/image/'
														+ wguestAvatartUrl
														+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
												newHtml += '<span class="unameComment">'
														+ comment.uname
														+ '</span>';
											} else if (comment.uname == 'Admin') {
												newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
														+ '/image/'
														+ wadminAvatarUrl
														+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
												newHtml += '<span class="unameCommentAdmin">'
														+ comment.uname
														+ '</span>';
											} else {
												newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
														+ '/image/'
														+ wmemberAvatartUrl
														+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
												newHtml += '<span class="unameCommentMember">'
														+ comment.uname
														+ '</span>';
											}
											newHtml += '<div class="mic-info">On: <span>'
													+ comment.ctime
													+ '</span></div></div>';
											newHtml += '<div class="comment-text"><span class="commentContent">'
													+ comment.content
													+ '</span></div>';
											if (comment.haveRole == true) {
												newHtml += '<div class="action"><div class="btn btn-danger btn-xs" id="'
														+ comment.cmid
														+ '" onclick="deleteComment('
														+ comment.cmid
														+ ')" title="Delete"><span class="glyphicon glyphicon-trash"></span></div></div></div></div>';
											} else {
												newHtml += '</div></div>';
											}
											newHtml += '</li>';
										});
						newHtml += '';
						$('#listComment').html(newHtml);
					}
					if (commentShow >= countComment) {
						$('#btnMore').hide();
					} else {
						$('#btnMore').show();
					}
					$('#contentComment').val('');
					$('#cCount').text(cCount);
					return result;
				},
				error : function(data, status, er) {
					alert("error: " + data.tenhang + " status: " + status
							+ " er:" + er);
				}
			});
}

function resetCssClassAllEpisodeButton() {
	var listButton = $('.container span button');
	listButton.attr('class', 'btn btn-primary');
}

function changeVideo(id) {
	var webDomain = $("#webDomain").val();
	var fid = $("#fid").val();

	$
			.ajax({
				type : "GET",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : webDomain + 'api/video/getVideoDetailById/' + fid + '/'
						+ id,
				success : function(result) {
					if (result != null) {
						var newHtml = '<iframe class="embed-responsive-item" src="'
								+ result.furl
								+ '" allowfullscreen="allowfullscreen"></iframe>';
						$('#myVideo').html(newHtml);
					}
					resetCssClassAllEpisodeButton();
					$('#' + id).attr('class', 'btn btn-warning');
					return result;
				},
				error : function(data, status, er) {
					alert('error: ' + data.tenhang + ' status: ' + status
							+ ' er:' + er);
				}
			});
}
function deleteComment(id) {
	var webDomain = $("#webDomain").val();
	var c = confirm("Do you want to delete this comment?");
	var commentShow = $('#commentShow').val();
	var countComment = $('#countComment').val();
	var cCount = $('#cCount').text();
	cCount = parseInt(cCount) - 1;

	var wguestAvatartUrl = $('#wguestAvatartUrl').val();
	var wmemberAvatartUrl = $('#wmemberAvatartUrl').val();
	var wadminAvatarUrl = $('#wadminAvatarUrl').val();

	if (c == true) {
		$
				.ajax({
					type : "GET",
					headers : {
						Accept : "application/json; charset=utf-8",
						"Content-Type" : "application/json; charset=utf-8"
					},
					url : webDomain + 'api/comment/deleteComment/' + id + '/'
							+ commentShow,
					success : function(result) {
						if (result != null) {
							var newHtml = '';
							$
									.each(
											result,
											function(index, comment) {
												if (index + 1 > commentShow) {
													return;
												}
												newHtml += '<li class="list-group-item">';

												if (comment.uname == 'Guest') {
													newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
															+ '/image/'
															+ wguestAvatartUrl
															+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
													newHtml += '<span class="unameComment">'
															+ comment.uname
															+ '</span>';
												} else if (comment.uname == 'Admin') {
													newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
															+ '/image/'
															+ wadminAvatarUrl
															+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
													newHtml += '<span class="unameCommentAdmin">'
															+ comment.uname
															+ '</span>';
												} else {
													newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
															+ '/image/'
															+ wmemberAvatartUrl
															+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
													newHtml += '<span class="unameCommentMember">'
															+ comment.uname
															+ '</span>';
												}
												newHtml += '<div class="mic-info">On: <span>'
														+ comment.ctime
														+ '</span></div></div>';
												newHtml += '<div class="comment-text"><span class="commentContent">'
														+ comment.content
														+ '</span></div>';
												if (comment.haveRole == true) {
													newHtml += '<div class="action"><div class="btn btn-danger btn-xs" id="'
															+ comment.cmid
															+ '" onclick="deleteComment('
															+ comment.cmid
															+ ')" title="Delete"><span class="glyphicon glyphicon-trash"></span></div></div></div></div>';
												} else {
													newHtml += '</div></div>';
												}
												newHtml += '</li>';
											});
							newHtml += '';
							$('#listComment').html(newHtml);
						}
						if (commentShow >= countComment) {
							$('#btnMore').hide();
						} else {
							$('#btnMore').show();
						}
						$('#contentComment').val('');
						$('#cCount').text(cCount);
						return result;
					},
					error : function(data, status, er) {
						alert('error: ' + data.tenhang + ' status: ' + status
								+ ' er:' + er);
					}
				});
	} else {
		return;
	}
}

function setHiddenFieldModal() {
	var fid = $("#fid").val();
	var episode = $('.btn-warning').text();
	$('#hiddenEpisode').val(episode);
	$('#hiddenFilmId').val(fid);
}

function moreComments() {
	var webDomain = $('#webDomain').val();
	var commentShow = $('#commentShow').val();
	var countComment = $('#countComment').val();
	var fid = $("#fid").val();

	var wguestAvatartUrl = $('#wguestAvatartUrl').val();
	var wmemberAvatartUrl = $('#wmemberAvatartUrl').val();
	var wadminAvatarUrl = $('#wadminAvatarUrl').val();

	commentShow = parseInt(commentShow) + 10;

	$
			.ajax({
				type : "GET",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : webDomain + 'api/comment/moreComment/' + fid + '/'
						+ commentShow,
				success : function(result) {
					if (result != null) {

						var newHtml = '';
						$
								.each(
										result,
										function(index, comment) {
											if (index + 1 > commentShow) {
												return;
											}
											newHtml += '<li class="list-group-item">';

											if (comment.uname == 'Guest') {
												newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
														+ '/image/'
														+ wguestAvatartUrl
														+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
												newHtml += '<span class="unameComment">'
														+ comment.uname
														+ '</span>';
											} else if (comment.uname == 'Admin') {
												newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
														+ '/image/'
														+ wadminAvatarUrl
														+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
												newHtml += '<span class="unameCommentAdmin">'
														+ comment.uname
														+ '</span>';
											} else {
												newHtml += '<div class="row"><div class="col-xs-2 col-md-1"><img src="'
														+ '/image/'
														+ wmemberAvatartUrl
														+ '" class="img-circle img-responsive" alt="" /></div><div class="col-xs-10 col-md-11"><div>';
												newHtml += '<span class="unameCommentMember">'
														+ comment.uname
														+ '</span>';
											}
											newHtml += '<div class="mic-info">On: <span>'
													+ comment.ctime
													+ '</span></div></div>';
											newHtml += '<div class="comment-text"><span class="commentContent">'
													+ comment.content
													+ '</span></div>';
											if (comment.haveRole == true) {
												newHtml += '<div class="action"><div class="btn btn-danger btn-xs" id="'
														+ comment.cmid
														+ '" onclick="deleteComment('
														+ comment.cmid
														+ ')" title="Delete"><span class="glyphicon glyphicon-trash"></span></div></div></div></div>';
											} else {
												newHtml += '</div></div>';
											}
											newHtml += '</li>';
										});
						newHtml += '';
						$('#listComment').html(newHtml);

					}
					if (commentShow >= countComment) {
						$('#btnMore').hide();
					} else {
						$('#btnMore').show();
					}
					$('#contentComment').val('');
					$('#commentShow').val(commentShow);
					return result;
				},
				error : function(data, status, er) {
					alert("error: " + data.tenhang + " status: " + status
							+ " er:" + er);
				}
			});
}