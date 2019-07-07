 		function fsubmit() {
            var form = document.getElementById("form1");
            var formData = new FormData(form);
            var oReq = new XMLHttpRequest();
            oReq.onreadystatechange = function(){
              if(oReq.readyState == 4) {
                if(oReq.status == 200) {
                	/*
                    var json=JSON.parse(oReq.responseText);
                    var result = '';
                    // result += 'name=' + ret['name'] + '<br>';
                    // result += 'gender=' + ret['gender'] + '<br>';
                     result += '<img src="' + json['photo'] + '" width="100">';
                     $('#result').html(result);
                     */                
                     var b = oReq.responseText;
                     // alert("b="+b);
                     if (b == -2) {
                     	alert("请完整填写所有信息");
                     	return ;
                     }
                     if (b == -4) {
                    	 alert("图片过大，请重新调整后再上传");
                    	 return ;
                     }
                     if (b == -1) {
                     	alert("房间添加失败");
                     	return ;
                     }
                     if (b == -5) {
                    	 alert("图片上传失败，请重新上传");
                    	 return ;
                     }
                     if (b == 0) {
                     	alert("房间添加成功");
                     	window.location.reload();
                     	return ;
                     }
                } else {
                	alert("网络出错,请检查信息填写是否正确");
                	return ;
                }
              }
            }
            oReq.open("POST","/tspaceX/a/insertroomsubmit");
            oReq.send(formData); 
            return false;
        } 
        
        function loadpreview(file){
	        var img = document.getElementById("preview");
	        var reader = new FileReader();
	        reader.onload = function(evt) {
	        	img.src = evt.target.result;
	        }
	        reader.readAsDataURL(file.files[0]);
        }