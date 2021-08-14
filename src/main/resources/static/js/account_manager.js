$(document)
	.ready(
		function() {
			$('#adminFeature').addClass('current-menu-item');

			/* Xóa*/
			$('#btnDelete').click(function() {
				var chosenIndex = $('#chosenIndex').val();
				var userId = $('#userId' + chosenIndex).text();

				swal("Good job!", "Bạn đã xóa tài khoản thành công!", "success");

				setTimeout(function() {
					window.location.replace('deleteAccount/' + userId);
				}, 3000);


			})

			/* Sửa */
			$('#btnEdit').click(
				function() {
					var chosenIndex = $('#chosenIndex').val();
					var userId = $('#userId' + chosenIndex).text();
					var newUserName = $('#newUserName').val();
					var newPassword = $('#newPassword').val();

					if (newUserName == '' || newUserName == null) {
						alert('User name không được để trống.');
						return;
					}

					if (newPassword == '' || newPassword == null) {
						alert('Password không được để trống.');
						return;
					}

					swal("Good job!", "Bạn đã chỉnh sửa thông tin Account thành công!", "success");

					setTimeout(function() {

						window.location.replace('editAccount/' + userId
							+ '/' + newUserName + '/' + newPassword);

					}, 3000);



				})

			/* Thêm*/
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
							'email': email,
							'password': password,
							'uname': uname
						}

						if (email == '' || email == null) {
							alert('Email không được để trống.');
							return;
						}

						if (password == '' || password == null) {
							alert('Password không được để trống.');
							return;
						}

						if (uname == '' || uname == null) {
							alert('User name không được để trống.');
							return;
						}


						if (password !== rePassword) {
							alert("Repeat password không đúng!");
							return;
						}

						$
							.ajax({
								type: "POST",
								headers: {
									Accept: "application/json; charset=utf-8",
									"Content-Type": "application/json; charset=utf-8"
								},
								url: webDomain
									+ 'admin/api/createAccount',
								beforeSend: function(xhr) {
									xhr.setRequestHeader(
										header, token);
									console
										.warn(xhr.responseText)
								},
								data: JSON
									.stringify(userObject),
								success: function(result) {
									if (result != null) {

										swal("Good job!", "Bạn đã tạo User thành công!", "success");

										/*alert("Bạn đã tạo User thành công!");*/
										setTimeout(function() {
											window.location.replace('');
										}, 3000);


									}
									return result;
								},
								error: function(data,
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

function showPassword() {
	var x = document.getElementById('newPassword');
	if (x.type === 'password') {
		x.type = 'text';
	} else {
		x.type = 'password';
	}
}