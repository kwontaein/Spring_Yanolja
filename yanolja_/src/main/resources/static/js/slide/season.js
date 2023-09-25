$(document).ready(function() {
	// 페이지 로드 시 기본 내용 표시 (예: 서울)
	changeSeasonContent(1, '서울'); // regionid를 숫자로 전달
	// 동적으로 li 요소 생성
	createDynamicLi(1, '서울', '호텔');
	createDynamicLi(2, '제주', '호텔');
	createDynamicLi(3, '강원', '호텔');
	createDynamicLi(4, '전라', '호텔');

	// 처음 로드 시 서울 li를 활성화 상태로 표시
	$("#dynamicList li:first-child").addClass("active");

	// li 클릭 이벤트 처리
	$("#dynamicList li").click(function() {
		// 모든 li에서 활성화 클래스를 제거
		$("#dynamicList li").removeClass("active");
		// 클릭한 li에 활성화 클래스 추가
		$(this).addClass("active");
		// 선택한 지역으로 내용 변경
		var regionid = $(this).data("regionid"); // data-regionid 속성 사용
		var kindhotel = '호텔';
		changeSeasonContent(regionid, kindhotel);
	});
});

function changeSeasonContent(regionid, kindhotel) {
	// AJAX를 사용하여 내용을 동적으로 변경
	$.ajax({
		url: 'Nonslidelist', // 변경할 내용을 제공하는 JSP 페이지
		method: "GET",
		data: {
			regionid: regionid,
			kindhotel: '호텔',
			// 숫자로 된 regionid를 전달
		},
		success: function(response) {
			$(".seasoncontents").html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}

function createDynamicLi(regionid, region, kindhotel) {
	// 동적으로 li 요소 생성
	var liElement = '<li data-regionid="' + regionid + '" onclick="changeSeasonContent(' + regionid +
		', \'' + kindhotel + '\')">' + region + '</li>';
	$("#dynamicList").append(liElement);
}