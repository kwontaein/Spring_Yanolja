<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/Main/linkto.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="linkto">
		<div class="link1">
			<a href="/sub_main/hotel" class="linklist">
				<img src="https://yaimg.yanolja.com/v5/2023/06/27/09/649aacead85c97.61002371.png" alt="호텔/리조트">
				<p>호텔/리조트</p>
			</a>
			<a href="/sub_main/pension">
				<img src="https://yaimg.yanolja.com/v5/2023/06/27/09/649aad07c31cd2.77643370.png" alt="펜션/풀빌라">
				<p>펜션/풀빌라</p>
			</a>
			<a href="/sub_main/familly">
				<img src="https://yaimg.yanolja.com/v5/2023/07/27/15/64c290a7eb9872.07779709.png" alt="가족형숙소">
				<p>가족형숙소</p>
			</a>
			<a href="/sub_main/motel">
				<img src="https://yaimg.yanolja.com/v5/2023/06/27/09/649aacb4a86f94.23844748.png" alt="모텔">
				<p>모텔</p>
			</a>
		</div>
		<div class="link1">
			<a href="/sub_main/air">
				<img src="https://yaimg.yanolja.com/v5/2023/07/27/16/64c29586c73624.40707876.png" alt="항공">
				<p>항공</p>
			</a>
			<a href="/sub_main/forein">
				<img src="https://yaimg.yanolja.com/v5/2023/08/16/16/64dcf795ddc358.78374281.png" alt="해외숙소">
				<p>해외숙소</p>
			</a>
			<a href="/sub_main/trapic">
				<img src="https://yaimg.yanolja.com/v5/2023/06/27/09/649aae23bc8167.29642353.png" alt="교통">
				<p>교통</p>
			</a>
			<a href="/sub_main/ticket">
				<img src="https://yaimg.yanolja.com/v5/2023/06/27/09/649aad9f639889.94009206.png" alt="레저/티켓">
				<p>레저/티켓</p>
			</a>
		</div>
		<%@ include file="./eventrolling.jsp"%>
		<div class="link2">
			<a href="/login">
				<img src="https://yaimg.yanolja.com/v5/2023/07/06/16/64a6e77f327c88.37002893.png" alt="매일응모하기">
				<p>매일응모하기</p>
			</a>
			<a href="/login">
				<img src="https://yaimg.yanolja.com/v5/2023/08/16/20/64dd3600cb6229.18956994.png" alt="일본여행특가">
				<p>일본여행특가</p>
			</a>
			<a href="/login">
				<img src="https://yaimg.yanolja.com/v5/2023/02/03/17/63dd47e78d1df0.12470208.png" alt="최저가보상제">
				<p>최저가보상제</p>
			</a>
			<a href="/login">
				<img src="https://yaimg.yanolja.com/v5/2023/03/27/14/6421aea02596d8.59100852.png" alt="8월혜택모음">
				<p>8월혜택모음</p>
			</a>
		</div>
		<div>
			<c:if test="${not empty resentViewHotelid}">
				<div id="Resent"></div>
				<script>
					$(document).ready(function() {
						// AJAX 요청을 만듭니다.
						$.ajax({
							url : "/ResentRelated",
							type : "GET",
							success : function(data) {
								// AJAX 요청이 성공하면 응답을 받아서 Resent 요소에 추가합니다.
								$("#Resent").html(data);
							},
							error : function() {
								console.log("AJAX 요청 실패");
							}
						});
					});
				</script>
			</c:if>
		</div>
	</div>
</body>
</html>