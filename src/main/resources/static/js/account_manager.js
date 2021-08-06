$(document)
		.ready(
				function() {
					$('#adminFeature').addClass('current-menu-item');
					$('#btnDelete').click(function() {
						var chosenIndex = $('#chosenIndex').val();
						var userId = $('#userId' + chosenIndex).text();

						window.location.replace('deleteAccount/' + userId);
					})
					$('#btnEdit').click(
							function() {
								var chosenIndex = $('#chosenIndex').val();
								var userId = $('#userId' + chosenIndex).text();
								var newUserName = $('#newUserName').val();
								var newPassword = $('#newPassword').val();

								if (newUserName == '' || newUserName == null) {
									alert('User name is invalid.');
									return;
								}
								
								if (newPassword == '' || newPassword == null) {
									alert('Password is invalid.');
									return;
								}

								window.location.replace('editAccount/' + userId
										+ '/' + newUserName + '/' + newPassword);
							})
					$('#btnCreate')
							.click(
									function() {
										var email = $('#email').val();
										var password = $('#password').val();
										var rePassword = $('#rePassword').val();
										var uname = $('#uname').val();
										var token = $('#_csrf').attr('content');
										var header = $('#_csrf_header').attr(
												'content');
										var webDomain = $("#webDomain").val();

										var userObject = {
											'email' : email,
											'password' : password,
											'uname' : uname
										}

										if (password !== rePassword) {
											alert("Repeat password not match!");
											return;
										}

										$
												.ajax({
													type : "POST",
													headers : {
														Accept : "application/json; charset=utf-8",
														"Content-Type" : "application/json; charset=utf-8"
													},
													url : webDomain
															+ 'admin/api/createAccount',
													beforeSend : function(xhr) {
														xhr.setRequestHeader(
																header, token);
														console
																.warn(xhr.responseText)
													},
													data : JSON
															.stringify(userObject),
													success : function(result) {
														if (result != null) {
															alert("Bạn đã tạo User thành công!");
															window.location.replace('');
														}
														return result;
													},
													error : function(data,
															status, er) {
														alert("error: "
																+ data.tenhang
																+ " status: "
																+ status
																+ " er:" + er);
													}
												});

									})
				});
function onclickDeleteFunction(index) {
	$('#chosenIndex').val(index);
}

function onclickEditFunction(index) {
	$('#chosenIndex').val(index);
	var currentUserName = $('#userName' + index).text();

	$('#newUserName').val(currentUserName);
}

function showPassword(){
	var x = document.getElementById('newPassword');
	  if (x.type === 'password') {
	    x.type = 'text';
	  } else {
	    x.type = 'password';
	  }
}