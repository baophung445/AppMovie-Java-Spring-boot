$(document)
	.ready(
		function() {

			if (localStorage.getItem('createUser')) {
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Bạn đã tạo User thành công !',
					showConfirmButton: false,
					/*timer: 1500*/
				})
				localStorage.removeItem('createUser');
			}


			$('#adminFeature').addClass('current-menu-item');

			/* Xóa*/
			$('#btnDelete').click(function() {
				var chosenIndex = $('#chosenIndex').val();
				var userId = $('#userId' + chosenIndex).text();

				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Bạn đã xóa User thành công !',
					showConfirmButton: false,
					timer: 1500
				})

				setTimeout(function() {
					window.location.replace('deleteAccount/' + userId);
				}, 500);
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

					Swal.fire({
						position: 'center',
						icon: 'success',
						title: 'Bạn đã sửa User thành công !',
						showConfirmButton: false,
						/*timer: 1500*/
					})

					setTimeout(function() {

						window.location.replace('editAccount/' + userId
							+ '/' + newUserName + '/' + newPassword);

					}, 500);
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

										localStorage.setItem('createUser', 'true');
										window.location.replace('');

									}
									return result;
								},
								error: function(data,
									status, er) {
									alert("Tạo User thất bại"
									);
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