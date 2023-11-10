$(document).ready(function() {
	 // 페이지 로드 시 기본 내용 표시 (예: 서울)
	 changeHokContent(1, '서울'); // regionid를 숫자로 전달
	 // 동적으로 li 요소 생성
	 createhokLi(1, '휴가에딱');
	 createhokLi(2, '경인강원');
	 createhokLi(3, '서울');
	 createhokLi(4, '전국인기');

	 // 처음 로드 시 서울 li를 활성화 상태로 표시
	 $("#hokList li:first-child").addClass("active");

	 // li 클릭 이벤트 처리
	 $("#hokList li").click(function() {
		 // 모든 li에서 활성화 클래스를 제거
		 $("#hokList li").removeClass("active");
		 // 클릭한 li에 활성화 클래스 추가
		 $(this).addClass("active");
		 // 선택한 지역으로 내용 변경
		 var hokregionid = $(this).data("regionid"); // data-regionid 속성 사용
		 changeHokContent(hokregionid);
	 });
 });

function changeHokContent(hokregionid) {
	
	console.log("4리스트 내용 콘솔")
	// AJAX를 사용하여 내용을 동적으로 변경
	$.ajax({
		url: 'list', // 변경할 내용을 제공하는 JSP 페이지
		method: "GET",
		data: {
			regionid: hokregionid,
			kind : kind
			// 숫자로 된 regionid를 전달
		},
		success: function(response) {
			$(".hokcontents").html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}

function createhokLi(hokregionid, hokregion) {
	// 동적으로 li 요소 생성
	var liElement = '<li data-regionid="' + hokregionid + '" onclick="changeHokContent(' + hokregionid +
		')">' + hokregion + '</li>';
	$("#hokList").append(liElement);
}