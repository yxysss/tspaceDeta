	function goback() {
		if (document.referrer == "") {
			window.location.href = "/tspaceX/";
		} else {
			history.back(-1);
		}
		// window.location.href = document.referrer;
	}
	
	$("#submit").click(function() {
		var type = $("input[name='type']:checked").val();
		var contactmail = $("input[name=contactmail]").val();
		var detail = document.getElementById("detail").value+"";
		// alert(type);
		// alert(contactmail);
		// alert(detail);
		if (detail.length == "") {
			var notice = document.getElementById("notice");
			notice.innerText="*详细说明不可为空，请填写后提交！";
			return false;
		}
		if (detail.length > 100) {
			var notice = document.getElementById("notice");
			notice.innerText="*详细说明过长，请删减后提交！";
			return false;
		}
		$.ajax({
			type: "post",
			dataType: "json",
			url: "/tspaceX/customservicesubmit",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify({"contactmail":contactmail,"type":type,"detail":detail}),
			success: function(data) {
				// alert(data);
				if (data == 0) {
					// window.location.href="/tspaceX/registersuccess";
					$("#submitsuccess").popup('open');
					return false;
				} 
				if (data == -1) {
					// $("#fail").popup('open');
					$("#submitfail").popup('open');
					return false;
				}
			}
		});
	});