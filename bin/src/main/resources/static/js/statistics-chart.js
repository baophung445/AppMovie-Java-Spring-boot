var MONTHS = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July',
		'August', 'September', 'October', 'November', 'December' ];
var color = Chart.helpers.color;
var webDomain = document.getElementById("webDomain").value;

var barChartData;
var lineChartData;
var doughnutChartData;

window.onload = function() {

	$('#selectYears').change(function() {
		var year = $(this).val();
		changeChartsWithYear(year);
	})

	$('#selectMember').change(function() {
		var userId = $(this).val();
		changeChartsWithUserId(userId)
	})

	// Bar Chart
	$.ajax({
		url : webDomain + 'admin/chart/getAcceptVideoChart',
		success : function(result) {
			barChartData = {
				labels : [ 'January', 'February', 'March', 'April', 'May',
						'June', 'July', 'August', 'September', 'October',
						'November', 'December' ],
				datasets : [
						{
							label : 'Video',
							backgroundColor : color(window.chartColors.red)
									.alpha(0.5).rgbString(),
							borderColor : window.chartColors.red,
							borderWidth : 1,
							data : [ result.videoChart.vJanurary,
									result.videoChart.vFebruary,
									result.videoChart.vMarch,
									result.videoChart.vApril,
									result.videoChart.vMay,
									result.videoChart.vJune,
									result.videoChart.vJuly,
									result.videoChart.vAugust,
									result.videoChart.vSeptember,
									result.videoChart.vOctober,
									result.videoChart.vNovember,
									result.videoChart.vDecember ]
						},
						{
							label : 'Group Video',
							backgroundColor : color(window.chartColors.blue)
									.alpha(0.5).rgbString(),
							borderColor : window.chartColors.blue,
							borderWidth : 1,
							data : [ result.groupVideoChart.vgJanurary,
									result.groupVideoChart.vgFebruary,
									result.groupVideoChart.vgMarch,
									result.groupVideoChart.vgApril,
									result.groupVideoChart.vgMay,
									result.groupVideoChart.vgJune,
									result.groupVideoChart.vgJuly,
									result.groupVideoChart.vgAugust,
									result.groupVideoChart.vgSeptember,
									result.groupVideoChart.vgOctober,
									result.groupVideoChart.vgNovember,
									result.groupVideoChart.vgDecember ]
						} ]

			};

			var ctxAcceptVideoChart = document.getElementById(
					'acceptVideoChart').getContext('2d');
			window.myBar = new Chart(ctxAcceptVideoChart, {
				type : 'bar',
				data : barChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Accept Video Chart'
					}
				}
			});
		}
	});
	// Line Chart
	$.ajax({
		url : webDomain + 'admin/chart/getCommentChart',
		success : function(result) {
			lineChartData = {
				labels : [ 'January', 'February', 'March', 'April', 'May',
						'June', 'July', 'August', 'September', 'October',
						'November', 'December' ],
				datasets : [ {
					label : 'Comment',
					backgroundColor : window.chartColors.blue,
					borderColor : window.chartColors.blue,
					borderWidth : 1,
					data : [ result.commentChart.cJanurary,
							result.commentChart.cFebruary,
							result.commentChart.cMarch,
							result.commentChart.cApril,
							result.commentChart.cMay,
							result.commentChart.cJune,
							result.commentChart.cJuly,
							result.commentChart.cAugust,
							result.commentChart.cSeptember,
							result.commentChart.cOctober,
							result.commentChart.cNovember,
							result.commentChart.cDecember ],
					fill : false,
				} ]

			};

			var ctxCommentChart = document.getElementById('commentChart')
					.getContext('2d');
			window.myBar = new Chart(ctxCommentChart, {
				type : 'line',
				data : lineChartData,
				options : {
					responsive : true,
					title : {
						display : true,
						text : 'Comment Chart'
					},
					tooltips : {
						mode : 'index',
						intersect : false,
					},
					hover : {
						mode : 'nearest',
						intersect : true
					},

				}
			});
		}
	});

	// Doughnut Chart
	$
			.ajax({
				url : webDomain + 'admin/chart/getReportMemberChart',
				success : function(result) {
					doughnutChartData = {
						datasets : [ {
							data : [ result.memberReportChart.rTypeError,
									result.memberReportChart.rTypeWarning,
									result.memberReportChart.rTypeOk ],
							backgroundColor : [ window.chartColors.red,
									window.chartColors.yellow,
									window.chartColors.blue ],
							label : 'Report'
						} ],
						labels : [ 'Error', 'Warning', 'Ok' ]
					};

					var ctxMemberReportChart = document.getElementById(
							'memberReportChart').getContext('2d');
					window.myBar = new Chart(ctxMemberReportChart, {
						type : 'doughnut',
						data : doughnutChartData,
						options : {
							responsive : true,
							legend : {
								position : 'top',
							},
							title : {
								display : true,
								text : 'Member Report Chart'
							},
							animation : {
								animateScale : true,
								animateRotate : true
							}
						}
					});
				}
			});

	document.getElementById('adminFeature').classList.add("current-menu-item");
}

function changeChartsWithYear(year) {

	// Reset Canvas
	$('#acceptVideoChart').replaceWith(
			'<canvas id="acceptVideoChart"></canvas>');
	$('#commentChart').replaceWith('<canvas id="commentChart"></canvas>');
	$('#memberReportChart').replaceWith(
			'<canvas id="memberReportChart"></canvas>');

	var userId = $('#selectMember').val();

	// Bar chart
	$.ajax({
		url : webDomain + 'admin/chart/getAcceptVideoChart/' + year,
		success : function(result) {
			barChartData = {
				labels : [ 'January', 'February', 'March', 'April', 'May',
						'June', 'July', 'August', 'September', 'October',
						'November', 'December' ],
				datasets : [
						{
							label : 'Video',
							backgroundColor : color(window.chartColors.red)
									.alpha(0.5).rgbString(),
							borderColor : window.chartColors.red,
							borderWidth : 1,
							data : [ result.videoChart.vJanurary,
									result.videoChart.vFebruary,
									result.videoChart.vMarch,
									result.videoChart.vApril,
									result.videoChart.vMay,
									result.videoChart.vJune,
									result.videoChart.vJuly,
									result.videoChart.vAugust,
									result.videoChart.vSeptember,
									result.videoChart.vOctober,
									result.videoChart.vNovember,
									result.videoChart.vDecember ]
						},
						{
							label : 'Group Video',
							backgroundColor : color(window.chartColors.blue)
									.alpha(0.5).rgbString(),
							borderColor : window.chartColors.blue,
							borderWidth : 1,
							data : [ result.groupVideoChart.vgJanurary,
									result.groupVideoChart.vgFebruary,
									result.groupVideoChart.vgMarch,
									result.groupVideoChart.vgApril,
									result.groupVideoChart.vgMay,
									result.groupVideoChart.vgJune,
									result.groupVideoChart.vgJuly,
									result.groupVideoChart.vgAugust,
									result.groupVideoChart.vgSeptember,
									result.groupVideoChart.vgOctober,
									result.groupVideoChart.vgNovember,
									result.groupVideoChart.vgDecember ]
						} ]

			};

			var ctxAcceptVideoChart = document.getElementById(
					'acceptVideoChart').getContext('2d');
			window.myBar = new Chart(ctxAcceptVideoChart, {
				type : 'bar',
				data : barChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Accept Video Chart'
					}
				}
			});
		}
	});

	// Line Chart
	$.ajax({
		url : webDomain + 'admin/chart/getCommentChart/' + year,
		success : function(result) {
			lineChartData = {
				labels : [ 'January', 'February', 'March', 'April', 'May',
						'June', 'July', 'August', 'September', 'October',
						'November', 'December' ],
				datasets : [ {
					label : 'Comment',
					backgroundColor : window.chartColors.blue,
					borderColor : window.chartColors.blue,
					borderWidth : 1,
					data : [ result.commentChart.cJanurary,
							result.commentChart.cFebruary,
							result.commentChart.cMarch,
							result.commentChart.cApril,
							result.commentChart.cMay,
							result.commentChart.cJune,
							result.commentChart.cJuly,
							result.commentChart.cAugust,
							result.commentChart.cSeptember,
							result.commentChart.cOctober,
							result.commentChart.cNovember,
							result.commentChart.cDecember ],
					fill : false,
				} ]

			};

			var ctxCommentChart = document.getElementById('commentChart')
					.getContext('2d');
			window.myBar = new Chart(ctxCommentChart, {
				type : 'line',
				data : lineChartData,
				options : {
					responsive : true,
					title : {
						display : true,
						text : 'Comment Chart'
					},
					tooltips : {
						mode : 'index',
						intersect : false,
					},
					hover : {
						mode : 'nearest',
						intersect : true
					}	
				}
			});
		}
	});

	var ajaxUrl;
	if (userId === null) {
		ajaxUrl = webDomain + 'admin/chart/getReportMemberChart/' + year;
	} else {
		ajaxUrl = webDomain + 'admin/chart/getReportMemberChart/' + year + '/' + userId;
	}
	// Doughnut Chart
	$.ajax({
		url : ajaxUrl,
		success : function(result) {
			doughnutChartData = {
				datasets : [ {
					data : [ result.memberReportChart.rTypeError,
							result.memberReportChart.rTypeWarning,
							result.memberReportChart.rTypeOk ],
					backgroundColor : [ window.chartColors.red,
							window.chartColors.yellow,
							window.chartColors.blue ],
					label : 'Report'
				} ],
				labels : [ 'Error', 'Warning', 'Ok' ]
			};

			var ctxMemberReportChart = document.getElementById(
					'memberReportChart').getContext('2d');
			window.myBar = new Chart(ctxMemberReportChart, {
				type : 'doughnut',
				data : doughnutChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Member Report Chart'
					},
					animation : {
						animateScale : true,
						animateRotate : true
					}
				}
			});
		}
	});
}

function changeChartsWithUserId(userId) {

	// Reset Canvas
	$('#memberReportChart').replaceWith(
			'<canvas id="memberReportChart"></canvas>');

	var year = $('#selectYears').val();

	var ajaxUrl;
	if (year === null) {
		ajaxUrl = webDomain + 'admin/chart/getReportMemberChartWithUser/' + userId;
	} else {
		ajaxUrl =  webDomain + 'admin/chart/getReportMemberChart/' + year + '/' + userId;
	}
	// Doughnut Chart
	$.ajax({
		url : ajaxUrl,
		success : function(result) {
			doughnutChartData = {
				datasets : [ {
					data : [ result.memberReportChart.rTypeError,
							result.memberReportChart.rTypeWarning,
							result.memberReportChart.rTypeOk ],
					backgroundColor : [ window.chartColors.red,
							window.chartColors.yellow,
							window.chartColors.blue ],
					label : 'Report'
				} ],
				labels : [ 'Error', 'Warning', 'Ok' ]
			};

			var ctxMemberReportChart = document.getElementById(
					'memberReportChart').getContext('2d');
			window.myBar = new Chart(ctxMemberReportChart, {
				type : 'doughnut',
				data : doughnutChartData,
				options : {
					responsive : true,
					legend : {
						position : 'top',
					},
					title : {
						display : true,
						text : 'Member Report Chart'
					},
					animation : {
						animateScale : true,
						animateRotate : true
					}
				}
			});
		}
	});
}