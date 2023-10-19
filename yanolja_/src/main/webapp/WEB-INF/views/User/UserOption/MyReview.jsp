<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/Review/MyReview.css" />
</head>
<body>
	<header>
		<div class="mrheader">
			<div class="mrhcontainer">
				<span>&lt;</span>
				<span>나의 후기(${room_cnt})</span>
				<span> </span>
			</div>
		</div>
	</header>
	<div class="mrContainer">
		<div class="mrWrapper">
			<div class="Reviewinfo">
				<ul>
					<li>후기는 작성 후 48시간 이내에 본문만 수정이 가능하며, 작성자는 현재 닉네임으로 변경됩니다. (사전 수정 불가)</li>
					<li>바른후기 정책 시행 이전에 작성된 후기는 노출되지 않습니다.</li>
					<li>삭제한 후기는 복구할 수 없으며 후기 작성으로 획득한 포인트 또는 코인은 회수될 수 있습니다.</li>
					<li>후기 삭제는 후기 작성일로부터 30일 이후에 가능합니다.</li>
				</ul>
			</div>
			<c:forEach items="${review}" var="rev">
				<div class="mr">
					<div class="mrHotel">
						<div>
							<div>
								<b>${rev.hotelname}</b>
							</div>
							<div>${rev.kindhotel}</div>
						</div>
						<div>수정 / 삭제</div>
					</div>
					<div class="mrReview">
						<div>뭐</div>
						<div>
							<div class="listrating">
								<span class="star">
									★★★★★
									<span style="width:${rev.rating * 20}%">★★★★★</span>
								</span>
							</div>
							<div>${rev.username}&nbsp;|&nbsp;${rev.roomname}•${rev.rentalType}</div>
						</div>
						<div class="date">${rev.ratingdate2}</div>
					</div>
					<div>${rev.reviewcontent}</div>
					<c:if test="${rev.base64Image != 'MA=='}">
						<div class="listphoto">
							<img src="data:image/png;base64,${rev.base64Image}" alt="이미지">
						</div>
					</c:if>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
</body>
</html>