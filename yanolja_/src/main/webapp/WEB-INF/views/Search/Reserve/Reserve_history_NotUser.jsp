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
	<div class="rh_container">
		<div class="rh_wrapper">
			<div class="NoMember">
				<div class="Notuser">
					<h3>예약정보</h3>
					<hr>
					<c:forEach items="${pesonal_reserve}" var="pr">
						<div class="Nus">호텔명 : ${pr.hotelname}</div>
						<div class="Nus">객실명 : ${pr.roomname}</div>
						<div class="Nus">예약 기간: ${pr.reserveDate2} ~ ${pr.reserveEndDate2}</div>
						<div class="Nus">주문번호 : ${pr.order_number}</div>
						<hr>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>