<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/Reserve/Reserve_history.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header style="height: 48px;">
		<div class="head">
			<div class="top">
				<span>&lt;</span>
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