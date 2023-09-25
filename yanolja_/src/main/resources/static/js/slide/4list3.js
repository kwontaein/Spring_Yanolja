/**
 * 
 *//**
* 
*/
/**
 * 
 */
$(document).ready(function() {
	// 페이지 로드 시 기본 내용 표시 (예: 서울)
	changermContent(1, '서울'); // regionid를 숫자로 전달
	// 동적으로 li 요소 생성
	creatermLi(1, '서울경인', '');
	creatermLi(2, '부산경상', '');
	creatermLi(3, '제주전라', '');
	creatermLi(4, '강원충청', '');

	// 처음 로드 시 서울 li를 활성화 상태로 표시
	$("#rmList li:first-child").addClass("active");

	// li 클릭 이벤트 처리
	$("#rmList li").click(function() {
		// 모든 li에서 활성화 클래스를 제거
		$("#rmList li").removeClass("active");
		// 클릭한 li에 활성화 클래스 추가
		$(this).addClass("active");
		// 선택한 지역으로 내용 변경
		var rmregionid = $(this).data("regionid"); // data-regionid 속성 사용
		changermContent(rmregionid);
	});
});

function changermContent(rmregionid) {
	// AJAX를 사용하여 내용을 동적으로 변경
	$.ajax({
		url: 'list', // 변경할 내용을 제공하는 JSP 페이지
		method: "GET",
		data: {
			regionid: rmregionid,
			// 숫자로 된 regionid를 전달
		},
		success: function(response) {
			$(".rmcontents").html(response);
		},
		error: function() {
			alert("오류 발생");
		}
	});
}

function creatermLi(rmregionid, rmregion, rmkindhotel) {
	// 동적으로 li 요소 생성
	var liElement = '<li data-regionid="' + rmregionid + '" onclick="changermContent(' + rmregionid +
		')">' + rmregion + '</li>';
	$("#rmList").append(liElement);
}