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
			'z-index':'1'
		})
	});
});