// Swiper 슬라이드 초기화
var bottomSwiper = new Swiper('.swiper-container', {
	slidesPerView: '1',
	autoHeight: true,
	// Navigation arrows
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
	}
});

// 초기 상태에서 슬라이드 내용 로드
includeFile('Tophotellist', 'includedContent1'); // 첫 번째 슬라이드
includeFile('Tophotellist2', 'includedContent2'); // 두 번째 슬라이드
includeFile('Tophotellist3', 'includedContent3'); // 세 번째 슬라이드

function includeFile(filePath, slideId) {
	console.log("숙소구매 top 부분 콘솔")
	$.ajax({
		url: filePath,
		method: "GET",
		data:{
			kind : kind
		},
		success: function(response) {
			var slideContainer = "#" + slideId;
			$(slideContainer).append(response); // 해당 슬라이드 컨테이너에 추가
		},
		error: function() {
			alert("오류 발생");
		},
	});
}