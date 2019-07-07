		function pwblur() {
			var pwnotice = document.getElementById("pwnotice");
			pwnotice.innerText="";
		}
			
		function pwfocus() {
			var pwnotice = document.getElementById("pwnotice");
			pwnotice.innerText="*为了保证账户安全，密码长度需不少于11位";
		}
		
		function gologin() {
			window.location.href="/tspaceX/login";
		}
		
		function goback() {
			if (document.referrer == "") {
				window.location.href = "/tspaceX/";
			} else {
				history.back(-1);
			}
			// window.location.href = document.referrer;
		}
		
		$("#submit").on("tap", function() {  
			var password = $("#password").val();
			var confirmpw = $("#confirmpw").val();
			if (password == "") {
				var notice = document.getElementById("notice");
				notice.innerText="*请输入密码！";
				return false;
			}
			
			if (password.length < 11) {
				var notice = document.getElementById("notice");
				notice.innerText="*密码长度不少于11位，请重新输入！";
				return false;
			}
			
			if (password != confirmpw) {
				var notice = document.getElementById("notice");
				notice.innerText="*两次密码输入不一致，请重新输入！";
				return false;
			}
				
			var p = hex_md5(hex_md5(hex_md5(password)));
			
			$.ajax({
				type: "post",
				dataType: "json",
				url: "/tspaceX/forgetpasswordsubmit2",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify({"newpassword":p}),
				success: function(data) {
					alert(data);
					if (data == 0) {
						$("#success").popup('open');
						return false;
					} 
					if (data == -1) {
						$("#fail").popup('open');
						$("#password").val("");
						$("#confirmpw").val("");
						return false;
					}
					return ;
				}
			});  
		});