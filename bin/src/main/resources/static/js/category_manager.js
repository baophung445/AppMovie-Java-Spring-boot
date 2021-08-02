$( document ).ready(function() {
		    	$('#adminFeature').addClass('current-menu-item');
		    	$('#btnEdit').click(function() {
		    		var chosenIndex = $('#chosenIndex').val();
		    		var categoryId = $('#categoryId' + chosenIndex).text();
		    		var newCategoryName = $('#newCategoryName').val();
		    		
		    		if (newCategoryName == '' || newCategoryName == null){
		    			alert('Category name is invalid.');
		    			return;
		    		}
		    		
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
		    		
		    		if (categoryName === '' || categoryName === null){
		    			alert('Category name is invalid');
		    			return
		    		}
		    		
		    		if (categoryCode === '' || categoryCode === null){
		    			alert('Category code is invalid');
		    			return
		    		}
		    		
		    		var categoryObject = {
		    				'cid' : categoryCode,
		    				'cname' : categoryName
		    		}
		    		
		    		$.ajax({
						type : "POST",
						headers : {
							Accept : "application/json; charset=utf-8",
							"Content-Type" : "application/json; charset=utf-8"
						},
						url : webDomain + 'admin/api/createCategory',
						beforeSend: function(xhr) {
		                    xhr.setRequestHeader(header, token);
		                    console.warn(xhr.responseText)
		                },
						data: JSON.stringify(categoryObject),
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
				
				var currentCategoryName = $('#categoryName' + index).text();
				
				$('#newCategoryName').val(currentCategoryName);
			}