var text = ['첫번째', '두번째', '세번째', '네번째'];
var bottomSwiper = new Swiper('.swiper-bottom', {
	slidesPerView: '1',
	pagination: {
		el: ".swiper-pagination-custom",
		clickable: true,
		bulletClass: "custom_bullet",
		bulletActiveClass: "swiper-pagination-custom-bullet-active",
		renderBullet: function(index, className) {
			return '<div class="' + className + '"><span>'
				+ (text[index]) + '</span></div>';
		},
	},
});

// 초기 상태에서 1번 슬라이드 내용 로드
includeFile('hotel', 1, '호텔');
includeFile('hotel', 2, '호텔');
includeFile('hotel', 3, '호텔');
includeFile('hotel', 4, '호텔');

// 페이지 버튼 클릭 이벤트 처리
$('.swiper-pagination-custom-bullet').click(function() {
	var index = $(this).index() + 1; // 페이지 번호는 1부터 시작
	includeFile('hotel', index, '호텔');
});

function includeFile(filePath, regionid) {
	$.ajax({
		url: filePath,
		method: "GET",
		data: {
			regionid: regionid
		},
		success: function(response) {
			var slideId = "#includedContent" + regionid;
			$(slideId).html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}