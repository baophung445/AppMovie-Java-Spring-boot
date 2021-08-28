$(document).ready(function() {

	if (localStorage.getItem('EditCategory')) {
		Swal.fire({
			position: 'center',
			icon: 'success',
			title: 'Cập nhật thành công !',
			showConfirmButton: false,
			timer: 1500
		})
		localStorage.removeItem('EditCategory');
	}

	if (localStorage.getItem('Success')) {
		Swal.fire({
			position: 'center',
			icon: 'success',
			title: 'Tạo thể loại phim thành công!',
			showConfirmButton: false,
			timer: 1500
		})
		localStorage.removeItem('Success');
	}
	
	if (localStorage.getItem('Error')) {
		Swal.fire({
			position: 'center',
			icon: 'error',
			title: 'Tạo thể loại phim thất bai!',
			showConfirmButton: false,
			timer: 1500
		})
		localStorage.removeItem('Error');
	}



	$('#adminFeature').addClass('current-menu-item');
	$('#btnEdit').click(function() {
		var chosenIndex = $('#chosenIndex').val();
		var categoryId = $('#categoryId' + chosenIndex).text();
		var newCategoryName = $('#newCategoryName').val();

		if (newCategoryName == '' || newCategoryName == null) {
			alert('Category name is invalid.');
			return;
		}
		localStorage.setItem('EditCategory', 'true');
		window.location.replace('editCategory/' + categoryId + '/' + newCategoryName);
	})
	$('#btnDelete').click(function() {
		var chosenIndex = $('#chosenIndex').val();
		var categoryId = $('#categoryId' + chosenIndex).text();
		
		window.location.replace('deleteCategory/' + categoryId);
	})
	$('#btnCreate').click(function() {
		var categoryCode = $('#categoryCode').val();
		var categoryName = $('#categoryName').val();
		var token = $('#_csrf').attr('content');
		var header = $('#_csrf_header').attr('content');
		var webDomain = $("#webDomain").val();

		if (categoryName === '' || categoryName === null) {
			alert('Category name is invalid');
			return
		}
		if (categoryCode === '' || categoryCode === null) {
			alert('Category code is invalid');
			return
		}
		var categoryObject = {
			'cid': categoryCode,
			'cname': categoryName
		}

		$.ajax({
			type: "POST",
			headers: {
				Accept: "application/json; charset=utf-8",
				"Content-Type": "application/json; charset=utf-8"
			},
			url: webDomain + 'admin/api/createCategory',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
				console.warn(xhr.responseText)
			},
			data: JSON.stringify(categoryObject),
			success: function(result) {
				localStorage.setItem('Success', 'true');
				window.location.replace('');
				return result;
			},
			error: function(data, status, er) {
				localStorage.setItem('Error', 'true');
			}
		});
	})
});
function onclickDeleteFunction(index) {
	$('#chosenIndex').val(index);
}

function onclickEditFunction(index) {
	$('#chosenIndex').val(index);

	var currentCategoryName = $('#categoryName' + index).text();

	$('#newCategoryName').val(currentCategoryName);
}