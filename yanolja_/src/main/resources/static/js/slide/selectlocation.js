$(document).ready(function() {
	$('#selectbtn').click(function() {
		$('.Rocmodal').css({
			'display': 'block',
			'height': '100%',
			'z-index': '13'
		});

		$('#head').css({
			'display': 'none'
		});

		$('body').css({
			'overflow': 'hidden'
		});
		$('.swiper-button-next, .swiper-button-prev').css({
			'z-index': '1'
		})
	});
	$("#close_loc_modal").click(function(){
		$('.Rocmodal').css({
			'display': 'none',
			'z-index': '0'
		});
			$('#head').css({
			'display': 'inline-block'
		});

		$('body').css({
			'overflow': 'auto'
		});
		$('.swiper-button-next, .swiper-button-prev').css({
			'z-index': '10'
		})
	});
});