$(document).ready(function() {
	// 페이지 로딩 시 자동으로 AJAX 요청 보내기

	var hotelid = getParameterByName('hotelid');
	if (hotelid) {
		sendAjaxRequest(hotelid);
	}

	$(".infochangebtn").click(function() {
		// 클릭한 버튼의 정보를 사용하여 AJAX 요청 보내기
		var kindid = $(this).data("kindid");
		sendAjaxRequest(hotelid, kindid);
	});
	$("#viewroadmap").click(function() {
		// 클릭한 버튼의 정보를 사용하여 AJAX 요청 보내기
		sendAjaxRequest(hotelid, 2);
		fnMove();
	});
	$("#viewrating").click(function() {
		// 클릭한 버튼의 정보를 사용하여 AJAX 요청 보내기
		sendAjaxRequest(hotelid, 5);
		fnMove();
	});
	function fnMove() {
		var offset = $(".changedinfo").offset();
		$('html, body').animate({ scrollTop: offset.top }, 400);
	}
});

// URL에서 쿼리 매개변수 가져오는 함수
function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
		.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}

// AJAX 요청을 보내는 함수
function sendAjaxRequest(hotelid, kindid) {
	// AJAX 요청 보내기 
	var hotelid = getParameterByName('hotelid');
	$.ajax({
		url: getAjaxURL(kindid), // 서버 요청을 처리할 URL 설정 getAjaxURL
		method: "GET", // 또는 POST, 서버 요청 방법 설정
		data: {
			hotelid: hotelid
		}, // 서버로 전송할 데이터 설정 (URL에서 가져온 hotelid 정보 전달)
		success: function(data) {
			$(".changedinfo").html(data); // 응답 데이터를 changedinfo 요소에 삽입
		},
		error: function() {
			alert("요청 실패");
		}
	});
}

// 고유한 URL을 가져오는 함수
function getAjaxURL(kindid) {
	// hotelid에 따라 다른 URL을 반환
	switch (kindid) {
		case 1:
			return "../roomlist"; // 1번 버튼에 대한 페이지 URL
		case 2:
			return "../locationtraffic"; // 2번 버튼에 대한 페이지 URL
		case 3:
			return "../InfoPolicy"; // 3번 버튼에 대한 페이지 URL
		case 4:
			return "../facility"; // 4번 버튼에 대한 페이지 URL
		case 5:
			return "../Review"; // 5번 버튼에 대한 페이지 URL
		default:
			return "../roomlist"; // 기본 페이지 URL 또는 오류 처리
	}
}