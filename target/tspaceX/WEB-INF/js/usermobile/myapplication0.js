			var idapplication;
			function queryapplication0(cstate, applytime, audittime, password) {
				if (cstate == 0) stateinChinese="接受";
				if (cstate == 2) stateinChinese="待审核";
				if (cstate == 1) stateinChinese="拒绝";
				document.getElementById("cstate").innerHTML = stateinChinese;
				document.getElementById("capplytime").innerHTML = applytime;
				if (!audittime) {
					document.getElementById("caudittime").innerHTML = audittime;
				} else {
					document.getElementById("caudittime").innerHTML = "";
				}
				if (password != 'null') {
					document.getElementById("cpassword").innerHTML = password;
				} else {
					document.getElementById("cpassword").innerHTML = "";
				}
				$("#detailpopup").popup('open');
				return ;
			}
			
			function commitcancel(cidapplication) {
				// alert(cidapplication);
				// alert(typeof(cidapplication));
				idapplication = cidapplication;
				$("#cancelpopup").popup('open');
				return ;
			}
			
			function submitcancel() {
				// alert(idapplication);
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/cancelapplication",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"idapplication":idapplication}),
					success: function(data) {
						// alert("data="+data);
						if (data == 0) {
							window.location.reload();
							return ;
						}
						if (data == -3) {
							$("cancelfailpopup").popup('open');
							return ;
						}
					}
				});
			}
			function reloadwindow() {
				window.reload();
				return ;
			}
			var running0 = document.getElementById("running0");
			running0.setAttribute("class","ui-btn-active");
			var nav01 = document.getElementById("nav01");
			nav01.setAttribute("class","ui-btn-active");