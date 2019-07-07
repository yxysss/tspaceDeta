		function acceptapplication(idapplication, i) {
			
			var r = confirm("确认同意序号为"+i+"的申请吗？");
			if (r == true) {
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/a/acceptapplication",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"idapplication":idapplication}),
					success: function(data) {
						
						// alert("data="+data);
						if (data == 0) {
							alert("申请通过成功");
							window.location.reload();
						} else {
							alert("申请通过失败");
							return false;
						}
					}
				});
			}
		}
		
		function decline(idapplication, i) {
			var r = confirm("确认拒绝序号为"+i+"的申请吗？");
			if (r == true) {
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/a/declineapplication",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"idapplication":idapplication}),
					success: function(data) {
						
						// alert("data="+data);
						if (data == 0) {
							alert("申请拒绝成功");
							window.location.reload();
						} else {
							alert("申请拒绝失败");
							return false;
						}
					}
				});
			}
		}	
		
		function conflict(idapplication, page) {
			window.location.href="/tspaceX/a/auditapplication?idapplication="+idapplication+"&page="+page;
		}