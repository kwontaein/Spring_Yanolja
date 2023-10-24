var data = {
	location: ['서울', '경기도', '강원도', '인천'],
	kindhotels: ['호텔', '호텔', '호텔', '호텔']
};
var text = [];
for (var i = 0; i < 4; i++) {
	text[i] = [data.location[i] + " " + data.kindhotels[i]]
};
console.log(text);
var bottomSwiper = new Swiper('.swiper-bottom', {
	slidesPerView: '1',
	autoHeight: true,
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


// JSON 문자열로 변환
var jsonData = JSON.stringify(data);

// encodeURIComponent를 사용하여 URL에 적합한 형태로 인코딩
var encodedData = encodeURIComponent(jsonData);

$('#Viewall').click(function() {
	// location.href로 전송
	console.log("encodedData" + encodedData);
	debugger;
	window.location.href = '/ViewAll?data=' + encodedData;
	
})

// 초기 상태에서 슬라이드 내용 로드
includeFile('Hotellist', 1, '호텔');
includeFile('Hotellist', 2, '호텔');
includeFile('Hotellist', 3, '호텔');
includeFile('Hotellist', 4, '호텔');

// 페이지 버튼 클릭 이벤트 처리
$('.swiper-pagination-custom-bullet').click(function() {
	var index = $(this).index() + 1; // 페이지 번호는 1부터 시작
	includeFile('Hotellist', index, '호텔');
});

function includeFile(filePath, regionid, kindhotel) {
	$.ajax({
		url: filePath,
		method: "GET",
		data: {
			regionid: regionid,
			kindhotel: kindhotel
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