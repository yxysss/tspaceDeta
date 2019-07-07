			function exit() {
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/logout",
					contentType: "application/json;charset=utf-8",
					success: function(data) {
						// alert(data);
						// alert(typeof data);
						if (data == true) {
							window.location.href="/tspaceX/";
						} else {
							return false;
						}
					}
				});  
			}
			var nav2 = document.getElementById("nav2");
			nav2.setAttribute("class", "ui-btn-active");