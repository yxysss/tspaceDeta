			function goback() {
				if (document.referrer == "") {
					window.location.href = "/tspaceX/";
				} else {
					history.back(-1);
				}
				// window.location.href = document.referrer;
			}
			$("#submit").on("tap", function() {  
				var username = $("#username").val();
			
				if (username == "") {
					$("#unnull").popup('open');
					return false;
				}
				
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/checkforgetpasswordusername",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"username":username}),
					success: function(data) {
						// alert(data);
						if (data == 0) {
							window.location.href="/tspaceX/forgetpassword1"
							return ;
						} 
						if (data == -1) {
							$("#unerror").popup('open');
							$("#username").val("");
							return false;
						}
					}
				});  
			});