$(document).ready(
		function() {
			$('#adminFeature').addClass('current-menu-item');
			$('#btnEdit').click(
					function() {
						var chosenIndex = $('#chosenIndex').val();
						var webId = $('#webId' + chosenIndex).text();
						var newWebName = $('#newWebName').val();
						var newWebTitle = $('#newWebTitle').val();

						if (newWebName == '' || newWebName == null) {
							alert('Web name is invalid.');
							return;
						}

						if (newWebTitle == '' || newWebTitle == null) {
							alert('Web title is invalid.');
							return null;
						}

						window.location.replace('editWeb/' + webId + '/'
								+ newWebName + '/' + newWebTitle);
					})
			$('#btnDelete').click(function() {
				var chosenIndex = $('#chosenIndex').val();
				var webId = $('#webId' + chosenIndex).text();

				window.location.replace('deleteWeb/' + webId);
			})
			$('#btnUse').click(function() {
				var chosenIndex = $('#chosenIndex').val();
				var webId = $('#webId' + chosenIndex).text();

				window.location.replace('useWeb/' + webId);
			})
			$('#btnCreate').click(function() {
				var chosenIndex = $('#chosenIndex').val();
				var webId = $('#webId' + chosenIndex).text();

				window.location.replace('useWeb/' + webId);
			})
		});
function onclickDeleteFunction(index) {
	$('#chosenIndex').val(index);
}

function onclickEditFunction(index) {
	$('#chosenIndex').val(index);

	var currentWebName = $('#webName' + index).text();
	var currentWebTitle = $('#webTitle' + index).text();

	$('#newWebName').val(currentWebName);
	$('#newWebTitle').val(currentWebTitle);
}
function onclickUseFunction(index) {
	$('#chosenIndex').val(index);
}