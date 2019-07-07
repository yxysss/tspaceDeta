			function goback() {
				if (document.referrer == "") {
					window.location.href = "/tspaceX/";
				} else {
					history.back(-1);
				}
				// window.location.href = document.referrer;
			}

			function pwblur() {
				var pwnotice = document.getElementById("pwnotice");
				pwnotice.innerText="";
			}
			
			function pwfocus() {
				var pwnotice = document.getElementById("pwnotice");
				pwnotice.innerText="*为了保证账户安全，密码长度需不少于11位";
			}
			
			$("#register").click(function() {
				var school = $("#school").val();
				// alert(school);
				var identity = $("input[name='identity']:checked").val();
				// alert(identity);
				var username = $("input[name=username]").val();
				var password = $("input[name=password]").val();
				var confirmpassword = $("input[name=confirmpassword]").val();
				var name = $("input[name=name]").val();
				if (school == 0) {
					var notice = document.getElementById("notice");
					notice.innerText="*请选择学校！";
					return false;
				}
				if (username=="" || password=="" || name=="" || confirmpassword=="") {
					var notice = document.getElementById("notice");
					notice.innerText="*请完整填写所有信息！";
					return false;
				}
				if ((identity == 0 && username.length != 11) || (identity == 1 && username.length != 4)) {
					var notice = document.getElementById("notice");
					notice.innerText="*用户名有误，请重新输入！";
					return false;
				}
				if (password.length < 11) {
					var notice = document.getElementById("notice");
					notice.innerText="密码长度不足，请重新输入！";
					return false;
				}
				if (password != confirmpassword) {
					var notice = document.getElementById("notice");
					notice.innerText="*两次密码输入不一致，请重新输入！";
					return false;
				} else{
					var notice = document.getElementById("notice");
					notice.innerText="";
					var p = hex_md5(hex_md5(hex_md5(password)));
					$.ajax({
						type: "post",
						dataType: "json",
						url: "/tspaceX/registersubmit1",
						contentType: "application/json;charset=utf-8",
						data: JSON.stringify({"school":school,"identity":identity,"username":username,"password":p,"name":name}),
						success: function(data) {
							// alert(data);
							// alert(typeof data);
							if (data == 0) {
								window.location.href="/tspaceX/registersuccess";
								return ;
							} 
							if (data == -1) {
								$("#fail").popup('open');
								return false;
							}
							if (data == -3) {
								$("#duplicate").popup('open');
								return false;
							}
						}
					});
				}
			});