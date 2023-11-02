<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/Reserve/Reserve_history.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>예약번호로 예약 조회</title>
<script>
	$(document).ready(function() {
		var previousURL = document.referrer; // 이전 URL 주소 가져오기

		$("#customBackButton").click(function() {
			// "/Reserve"와 파라미터를 포함한 URL 패턴을 정의
			if (previousURL.includes('/Reserve') && previousURL.includes('?')) {
				// 이전 URL이 "/Reserve"와 파라미터를 포함하면 뒤로가기를 막음
				window.close();
			} else {
				window.history.back(); // 그렇지 않으면 일반적인 뒤로가기 동작 수행
			}
		});
	});
</script>
</head>
<body>
	<header style="height: 48px;">
		<div class="head">
			<div class="top">
				<span id="customBackButton">&times;</span>
				<span>예약내역</span>
				<span></span>
			</div>
		</div>
	</header>
	<div class="rh_container">
		<div class="rh_wrapper">
			<div class="rh_list">
				<c:forEach items="${reserve}" var="reserve">
					<div>사용자 이름 : ${reserve.user_name}</div>
					<div>숙소 이름 : ${reserve.hotelname}</div>
					<div>방 이름 : ${reserve.roomname}</div>
					<div>체크인 날짜 : ${reserve.reserveDate2}</div>
					<hr>
				</c:forEach>
				<div>
					주문번호는 ${ordernumber} 입니다.
					<p>비회원 예약의 경우 예약 확인을 위해 해당 정보를 스크린샷 및 메모해 두시기 바랍니다</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>