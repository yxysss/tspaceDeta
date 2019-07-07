			function query(idroom) {
				window.location.href="/tspaceX/roomdisplay/idroom="+idroom;
			}
			$("#myCarousel").swipeleft(function() {
				$(this).carousel('next');
			});
			$("#myCarousel").swiperight(function() {
				$(this).carousel('prev');
			});
			var nav0 = document.getElementById("nav0");
			nav0.setAttribute("class","ui-btn-active");