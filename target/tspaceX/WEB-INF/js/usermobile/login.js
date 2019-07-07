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
				var password = $("#password").val();
				
				// alert(username + "," + password);		
				
				if (username == "") {
					$("#usernameerror").popup('open');
					return ;
				}
					
				if (password == "") {
					$("#passworderror").popup('open');
					return ;
				}
					
				var p = hex_md5(hex_md5(hex_md5(password)));
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/loginsubmit",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"username":username,"password":p}),
					success: function(data) {
						// alert(data);
						// alert(typeof data);
						if (data == 0) {
							// alert(document.referrer);
							var str = document.referrer;
							var index = str.lastIndexOf("?");
							if (index != -1 && str.substr(index+1, 6) == "idroom") {
								// 申请页面跳转，保留表单中的内容
								history.back(-1);
							} else {
								if (str == "") {
									window.location.href="/tspaceX";
								} else {
									window.location.href=str;
								}
							}
							/*
							var n1 = str.length;
							var n2 = str.lastIndexOf("/");
							var s = str.substr(n2+1, n1-n2);
							if (str == "" || s == "" || s == "index.jsp") {
								// window.location.href 跳转时会刷新页面
								window.location.href="/tspaceX";
							} else {
								if (s == "mytspace") {
									window.location.href="/tspaceX/mytspace";
								} else {
									// window.history 返回上级页面时不会刷新
									window.history.go(-1);
								}
							}
							*/
							return ;
						}
						if (data == -1) {
							// 用户名或密码错误
							$("#unperror").popup('open');
							return ;
						}					
					}
				});  
			});
		$('forgetpassword').on('click', function() {
			window.location.href="/tspaceX/forgetpassword";
		});
			
		$('register').on('click', function() {
			window.location.href="/tspaceX/register0";
		});