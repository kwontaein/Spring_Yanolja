$(document).ready(function() {
	// 페이지 로드 시 기본 내용 표시 (예: 서울)
	changefkContent(2, ''); // regionid를 숫자로 전달
	// 동적으로 li 요소 생성
	createfkLi(2, '도심힐링', '');
	createfkLi(3, '바다낭만', '');
	createfkLi(4, '남쪽여행', '');
	createfkLi(1, '리조트', '');

	// 처음 로드 시 서울 li를 활성화 상태로 표시
	$("#fkList li:first-child").addClass("active");

	// li 클릭 이벤트 처리
	$("#fkList li").click(function() {
		// 모든 li에서 활성화 클래스를 제거
		$("#fkList li").removeClass("active");
		// 클릭한 li에 활성화 클래스 추가
		$(this).addClass("active");
		// 선택한 지역으로 내용 변경
		var fkregionid = $(this).data("regionid"); // data-regionid 속성 사용
		changefkContent(fkregionid);
	});
});

function changefkContent(fkregionid) {
	// AJAX를 사용하여 내용을 동적으로 변경
	$.ajax({
		url: 'list', // 변경할 내용을 제공하는 JSP 페이지
		method: "GET",
		data: {
			regionid: fkregionid,
			kind : kind
			// 숫자로 된 regionid를 전달
		},
		success: function(response) {
			$(".fkcontents").html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}

function createfkLi(fkregionid, fkregion) {
	// 동적으로 li 요소 생성
	var liElement = '<li data-regionid="' + fkregionid + '" onclick="changefkContent(' + fkregionid +
		')">' + fkregion  + '</li>';
	$("#fkList").append(liElement);
}