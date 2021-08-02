$( document ).ready(function() {
		    	$('#adminFeature').addClass('current-menu-item');
		    	$('#btnEdit').click(function() {
		    		var chosenIndex = $('#chosenIndex').val();
		    		var countryId = $('#countryId' + chosenIndex).text();
		    		var newCountryName = $('#newCountryName').val();
		    		
		    		if (newCountryName == '' || newCountryName == null){
		    			alert('Country name is invalid.');
		    			return;
		    		}
		    		
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
		    		
		    		if (countryName === '' || countryName === null){
		    			alert('Country name is invalid');
		    			return
		    		}
		    		
		    		if (countryCode === '' || countryCode === null){
		    			alert('Country code is invalid');
		    			return
		    		}
		    		
		    		var countryObject = {
		    				'coid' : countryCode,
		    				'coname' : countryName
		    		}
		    		
		    		$.ajax({
						type : "POST",
						headers : {
							Accept : "application/json; charset=utf-8",
							"Content-Type" : "application/json; charset=utf-8"
						},
						url : webDomain + 'admin/api/createCountry',
						beforeSend: function(xhr) {
		                    xhr.setRequestHeader(header, token);
		                    console.warn(xhr.responseText)
		                },
						data: JSON.stringify(countryObject),
						success : function(result) {
							alert("Rquest OK!");
							window.location.replace('');
							return result; 
						},
						error : function(data, status, er) {
							alert("Request Fail!");
						}
					});
		    	})
			});
			function onclickDeleteFunction(index){
				$('#chosenIndex').val(index);
			}
			
			function onclickEditFunction(index){
				$('#chosenIndex').val(index);
				
				var currentCountryName = $('#countryName' + index).text();
				
				$('#newCountryName').val(currentCountryName);
			}