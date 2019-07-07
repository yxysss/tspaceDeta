		function reload() {
			var pin = document.getElementById("pin");
			pin.src="/tspaceX/getforgetpasswordpin?d="+Math.random();
		}
		
		function goback() {
			if (document.referrer == "") {
				window.location.href = "/tspaceX/";
			} else {
				history.back(-1);
			}
			// window.location.href = document.referrer;
		}
		
		function getcode() {
			$.ajax({
				type: "post",
				dataType: "json",
				url: "/tspaceX/getforgetpasswordsmsverify",
				contentType: "application/json;charset=utf-8",
				success: function(data) {
					// alert(data);
					// alert(typeof data);
					if (data == 0) {
						// alert("短信发送成功！");
						$("#smssuccess").popup('open');
						var data = 60;
						codebtn.text=data+"秒后重新获取";
						codebtn.removeAttribute("onclick");
						var timer = setInterval(function() {
							data -= 1;
							if (data == 0) {
								clearInterval(timer);
								codebtn.text="点击获取短信验证码";
								codebtn.setAttribute("onclick","getcode()");
							} else {
								codebtn.text=data+"秒后重新获取";
							}
						}, 1000);
						return false;
					}
					if (data == -1) {
						// alert("短信发送失败！");
						$("#smsfailure").popup('open');
						return false;
					}
					if (data == -2) {
						// 图片验证码有效超时
						window.reload();
						return false;
					}
				}
			});
		}
		$("#submit").on("tap", function() {  
			var code = $("#code").val();
			
			// alert(username + "," + code + "," + password);		
			
			if (code == "") {
				$("#smsnull").popup('open');
				return false;
			}
			
			$.ajax({
				type: "post",
				dataType: "json",
				url: "/tspaceX/checkforgetpasswordsmsverify",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify({"forgetpasswordsmsverify":code}),
				success: function(data) {
					// alert(data);
					// alert(typeof data);
					if (data == 0) {
						window.location.href="/tspaceX/forgetpassword2";
						return false;
					}
					if (data == -1){
						$("#smserror").popup('open');
						return false;
					}
					if (data == -3) {
						$("#smsovertime").popup('open');
						return false;
					}
					
				}
			});
			
		});
		$("#verify").click(function() {
			var inputpin = $("input[name=inputpin]").val();
			if (inputpin.length != 4) {
				var notice = document.getElementById("notice");
				notice.innerText="*验证码格式不正确！";
				return false;
			}
			$.ajax({
				type: "post",
				dataType: "json",
				url: "/tspaceX/checkforgetpasswordpin",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify({"forgetpasswordpin":inputpin}),
				success: function(data) {
					// alert(data);
					// alert(typeof data);
					if (data == 0) {
						$("#smsblock").show();
						$("#submit").show();
						$("#pinblock").hide();
						$("#verify").hide();
						var notice = document.getElementById("notice");
						notice.innerText="";
						return ;
					} else {
						var notice = document.getElementById("notice");
						notice.innerText="*验证码有误！";
						return false;
					}
				}
			});
		});