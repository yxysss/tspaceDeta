		function queryId(roomid) {
			window.open("/tspaceX/a/updateroom?idroom=" + encodeURI(roomid));
		}
		
		function deleteId(roomid, roomname) {
			var r = confirm("确定要删除房间"+roomname+"吗?");
			if (r == true) {
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/a/deleteroom",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"idroom":roomid}),
					success: function(data) {
						// alert(data);
						// alert(typeof data);
						if (data == 0) {
							alert("房间删除成功");
							window.location.reload();
						} else {
							alert("房间删除失败！");
							return false;
						}
					}
				});
			}
		}