<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 예약 목록 불러오기</title>
<style type="text/css">
.book_container {
	width: 768px;
}

.book_wrapper {
	padding: 50px;
}

.book_conts {
	padding: 10px 0;
}

.book_conts>div {
	width: 100%;
}

.title {
	padding: 0 10px;
}

h3 {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div class="book_container">
		<div class="book_wrapper">
			<h3>${username}님의예약내역</h3>
			<hr>
			<c:forEach items="${Book}" var="book">
				<div class="book_conts">
					<div>
						<span class="title">호텔명 : </span>
						<span>${book.hotelname}</span>
					</div>
					<div>
						<span class="title">객실명 : </span>
						<span>${book.roomname}</span>
					</div>
					<div>
						<span class="title">사용자 : </span>
						<span>${book.user_name}</span>
					</div>
					<div>
						<span class="title">예약일 : </span>
						<span>${book.reservedate2}</span>
					</div>
					<br>
					<c:choose>
						<c:when test="${book.canreview eq '후기 작성하기' && book.review_cnt == 0}">
							<div style="width: 100%; height: 40px; background-color: deeppink; display: flex; align-items: center; justify-content: center;">
								<a style="color: white; text-decoration: none;" href="/writeReview?roomid=${book.roomid}&bookid=${book.bookid}">
									<span>${book.canreview}</span>
								</a>
							</div>
						</c:when>
						<c:otherwise>
							<div style="width: 100%; height: 40px; background-color: gray; display: flex; align-items: center; justify-content: center;">
								<a style="color: white; text-decoration: none; cursor:not-allowed;">
									<span>${book.canreview}</span>
								</a>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
</body>
</html>