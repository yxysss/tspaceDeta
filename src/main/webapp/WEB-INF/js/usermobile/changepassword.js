			function goback() {
				if (document.referrer == "") {
					window.location.href="/tspaceX/";
				} else {
					history.back(-1);
				}
				return ;
			}
			
			$("#submit").on("tap", function() {  
				var oldpassword = $("#oldpassword").val();
				var newpassword = $("#newpassword").val();
				var confirmnpw = $("#confirmnpw").val();
				
				/* alert(oldpassword + "," + oldpassword + "," + confirmnpw); */		
				
				if (oldpassword == "") {
					// $("#operror").popup('open');
					var notice = document.getElementById("notice");
					notice.innerText="*请输入原密码！";
					return false;
				}
					
				if (newpassword == "") {
					// $("#nperror").popup('open');
					var notice = document.getElementById("notice");
					notice.innerText="*请输入新密码！";
					return false;
				}
				
				if (newpassword.length < 11) {
					// $("#nplengtherror").popup('open');
					var notice = document.getElementById("notice");
					notice.innerText="*为确保账户安全，密码长度请至少填写11位！";
					return false;
				}
				
				if (newpassword != confirmnpw) {
					// $("#differror").popup('open');
					var notice = document.getElementById("notice");
					notice.innerText="*两次新密码输入不一致，请重新输入！";
					return false;
				}
					
				var op = hex_md5(hex_md5(hex_md5(oldpassword)));
				var np = hex_md5(hex_md5(hex_md5(newpassword)));
				
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/changepasswordsubmit",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"oldpassword":op,"newpassword":np}),
					success: function(data) {
						/* alert(data); */
						if (data == 0) {
							$("#success").popup('open');
							return false;
						} 
						if (data == -1) {
							$("#opfalse").popup('open');
							$("#newpassword").val("");
							$("#confirmnpw").val("");
							return false;
						}
						if (data == -3) {
							$("#error").popup('open');
							$("#newpassword").val("");
							$("#confirmnpw").val("");
							return false;
						}
						if (data == -2) {
							// 无效访问-信息错误
							// alert(data);
							return false;
						}
					}
				});  
			});