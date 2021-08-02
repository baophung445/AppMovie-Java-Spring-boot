$(document).ready(function() {
	$('#requestManager').addClass('current-menu-item');
});
function addRowHandlers() {
	var table = document.getElementById("tableId");
	var rows = table.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		var currentRow = table.rows[i];
		var createClickHandler = function(row) {
			return function() {
				var cell = row.getElementsByTagName("td")[4];
				var id = cell.innerHTML;
				window.location.href = "/review-has-role/" + id;
			};
		};
		currentRow.onclick = createClickHandler(currentRow);
	}
}
window.onload = addRowHandlers();