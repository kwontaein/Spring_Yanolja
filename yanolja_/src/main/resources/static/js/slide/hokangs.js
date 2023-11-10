$(document).ready(function() {
	 // 페이지 로드 시 기본 내용 표시 (예: 서울)
	 changeHkContent('인천', '호텔'); // regionid를 숫자로 전달
	 // 동적으로 li 요소 생성
	 createhkLi('인천', '호텔');
	 createhkLi('부산', '호텔');
	 createhkLi('경상', '호텔');
	 createhkLi('전라', '호텔');

	 // 처음 로드 시 서울 li를 활성화 상태로 표시
	 $("#hkList li:first-child").addClass("active");

	 // li 클릭 이벤트 처리
	 $("#hkList li").click(function() {
		 // 모든 li에서 활성화 클래스를 제거
		 $("#hkList li").removeClass("active");
		 // 클릭한 li에 활성화 클래스 추가
		 $(this).addClass("active");
	 });
 });

function changeHkContent(hkregionname, hkkindhotel) {
	// AJAX를 사용하여 내용을 동적으로 변경
	$.ajax({
		url: 'Nonslidelist', // 변경할 내용을 제공하는 JSP 페이지
		method: "GET",
		data: {
			regionname: hkregionname,
			kindhotel: '호텔',
			// 숫자로 된 regionid를 전달
		},
		success: function(response) {
			$(".hkcontents").html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}

function createhkLi( hkregion, hkkindhotel) {
	// 동적으로 li 요소 생성
	var liElement = '<li data-regionid="' + hkregion + '" onclick="changeHkContent(\'' + hkregion +
		'\', \'' + hkkindhotel + '\')">' + hkregion + '</li>';
	$("#hkList").append(liElement);
}