

$(document).ready(
	function() {

		if (localStorage.getItem('AcceptVideo')) {
			
			Swal.fire({
				position: 'center',
				icon: 'success',
				title: 'Bộ phim đã được Accept !',
				showConfirmButton: false,
				timer: 2000
			})
			
			localStorage.removeItem('AcceptVideo');
		}

		$('#btnAccept').click(function() {
			localStorage.setItem('AcceptVideo', 'true');
		})

		$('#btndelete').click(function() {

			setTimeout(() => {
				Swal.fire({
					position: 'center',
					icon: 'success',
					title: 'Bộ phim đã được Xóa !',
					showConfirmButton: false,
					timer: 500
				})
			}, 1000)

		})

	});