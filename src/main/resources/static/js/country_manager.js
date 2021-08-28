$(document).ready(function() {

	if (localStorage.getItem('EditCountry')) {
		Swal.fire({
			position: 'center',
			icon: 'success',
			title: 'Cập nhật thành công !',
			showConfirmButton: false,
			timer: 1500
		})
		localStorage.removeItem('EditCountry');
	}
	
	if (localStorage.getItem('Success')) {
		Swal.fire({
			position: 'center',
			icon: 'success',
			title: 'Tạo quốc gia thành công!',
			showConfirmButton: false,
			timer: 1500
		})
		localStorage.removeItem('Success');
	}
	
	if (localStorage.getItem('Error')) {
		Swal.fire({
			position: 'center',
			icon: 'error',
			title: 'Tạo quốc gia thất bai!',
			showConfirmButton: false,
			timer: 1500
		})
		localStorage.removeItem('Error');
	}


	$('#adminFeature').addClass('current-menu-item');
	$('#btnEdit').click(function() {
		var chosenIndex = $('#chosenIndex').val();
		var countryId = $('#countryId' + chosenIndex).text();
		var newCountryName = $('#newCountryName').val();

		if (newCountryName == '' || newCountryName == null) {
			alert('Country name is invalid.');
			return;
		}
		localStorage.setItem('EditCountry', 'true');
		window.location.replace('editCountry/' + countryId + '/' + newCountryName);



	})
	$('#btnDelete').click(function() {
		var chosenIndex = $('#chosenIndex').val();
		var countryId = $('#countryId' + chosenIndex).text();

		window.location.replace('deleteCountry/' + countryId);
	})
	$('#btnCreate').click(function() {
		var countryCode = $('#countryCode').val();
		var countryName = $('#countryName').val();
		var token = $('#_csrf').attr('content');
		var header = $('#_csrf_header').attr('content');
		var webDomain = $("#webDomain").val();

		if (countryName === '' || countryName === null) {
			alert('Country name is invalid');
			return
		}

		if (countryCode === '' || countryCode === null) {
			alert('Country code is invalid');
			return
		}

		var countryObject = {
			'coid': countryCode,
			'coname': countryName
		}

		$.ajax({
			type: "POST",
			headers: {
				Accept: "application/json; charset=utf-8",
				"Content-Type": "application/json; charset=utf-8"
			},
			url: webDomain + 'admin/api/createCountry',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
				console.warn(xhr.responseText)
			},
			data: JSON.stringify(countryObject),
			success: function(result) {
				/*alert("Rquest OK!");*/
				localStorage.setItem('Success', 'true');
				window.location.replace('');
				return result;
			},
			error: function(data, status, er) {
				localStorage.setItem('Error', 'true');
				/*alert("Request Fail!");*/
			}
		});
	})
});
function onclickDeleteFunction(index) {
	$('#chosenIndex').val(index);
}

function onclickEditFunction(index) {
	$('#chosenIndex').val(index);

	var currentCountryName = $('#countryName' + index).text();

	$('#newCountryName').val(currentCountryName);
}