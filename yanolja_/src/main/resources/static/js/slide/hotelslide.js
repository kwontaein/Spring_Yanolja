var data = {
	location: ['서울', '경기도', '강원도', '인천'],
	kindhotels: ['호텔', '호텔', '호텔', '호텔']
};
var text = [];

for (var i = 0; i < 4; i++) {
	text[i] = [data.location[i] + " " + data.kindhotels[i]]
	includeFile('Hotellist', data.location[i], data.kindhotels[i], i);
};

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



function includeFile(filePath, regionname, kindhotel, index) {
	$.ajax({
		url: filePath,
		method: "GET",
		data: {
			regionname: regionname,
			kindhotel: kindhotel
		},
		success: function(response) {
			var slideId = "#includedContent" + (index + 1);
			$(slideId).html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}