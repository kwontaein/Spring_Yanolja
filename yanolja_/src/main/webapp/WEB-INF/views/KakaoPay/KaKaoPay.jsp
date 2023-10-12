<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<!-- 페이지 로드 시 자동으로 폼을 제출하는 스크립트 -->
	<script>
		$(document).ready(
				function() {
					// 폼 엘리먼트 선택
					var form = document.forms[0]; // 첫 번째 폼 선택

					// KakaoPayPage.jsp에서 받은 room 데이터
					var roomDataUrlEncoded = new URLSearchParams(
							window.location.search).get('roomData');

					// URL 매개변수를 디코딩하고 JSON으로 파싱
					var roomData = JSON
							.parse(decodeURIComponent(roomDataUrlEncoded));
					console.log(roomData.userPhone);
					// roomdata 객체 생성 (예제 데이터)
					var roomdata = {
						hotelname : roomData.hotelname,
						roomname : roomData.roomname,
						roomid : roomData.roomid,
						price : roomData.price,
						username : roomData.username,
						userPhone : roomData.userphone,
					// 기타 room 데이터 속성에 대한 추가
					};

					// hidden input 필드를 동적으로 생성하고 roomdata를 설정
					var hiddenInput = document.createElement("input");
					hiddenInput.type = "hidden";
					hiddenInput.name = "roomdata";
					hiddenInput.value = JSON.stringify(roomdata); // roomdata를 JSON 문자열로 변환하여 설정
					// form에 hidden input 필드 추가
					form.appendChild(hiddenInput);

					// 폼을 자동으로 제출
					form.submit();
			
				});
	</script>
	<form method="post" action="/kakaoPay"></form>
</body>
</html>