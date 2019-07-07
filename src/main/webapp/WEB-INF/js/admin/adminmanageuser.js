		function deleteuser(mobile) {
			
			/*
			var html = "<div style='color:red'>确认删除学号/工号为"+username+"的用户吗</div>";
            var button ="<input type='button' value='确定' /><input type='button' value='取消' />";

            var win = new Window({
                
                width : 400, //宽度
                height : 300, //高度
                title : '确认删除', //标题
                content : html, //内容
                isMask : false, //是否遮罩
                buttons : button, //按钮
                isDrag:true, //是否移动
            });
            */
			var r=confirm("确认将手机号为"+mobile+"的用户加入黑名单吗？");
			if (r==true) {
				$.ajax({
					type: "post",
					dataType: "json",
					url: "/tspaceX/a/adduserintoblacklist",
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({"mobile":mobile}),
					success: function(data) {
						
						if (data == 0) {
							alert("将手机号为"+mobile+"的用户加入黑名单成功");
							window.location.reload();
						} else {
							alert("将手机号为"+mobile+"的用户加入黑名单失败");
						}
						
					}
				});
			}
            /*
			$.ajax({
				type: "post",
				dataType: "json",
				url: "/tspace/Servletadmin?op=deleteuser",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify({"username":username}),
				success: function(data) {
					
					alert("data="+data);
					window.location.reload();
				}
			});
			*/
		}	
		$("#search").click(function() {
			var searchuser = $("input[name=searchuser]").val();
			window.location.href="/tspaceX/a/manageuser?searchentry="+searchuser;
		});