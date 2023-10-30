<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/Viewlist/Like_hotel.css" />
<title>Insert title here</title>
</head>
<body>
	<header style="display: flex; justify-content: center; background-color: white;">
		<div style="width: 768px; display: flex; justify-content: space-between; align-items: center; height: 48px;">
			<span onclick="window.history.back();">&lt;</span>
			<span>찜</span>
			<span></span>
		</div>
	</header>
	<div class="container">
		<c:choose>
			<c:when test="${not empty post}">
				<div id="hotels">
					<div id="listbody">
						<div id="ulbody">
							<c:forEach items="${post}" var="hotel" varStatus="loop" begin="0" end="49">
								<ul id="hotellist">
									<a href="/places/View.do?hotelid=${hotel.hotelid}">
										<div class="hotelimg">
											<li><div>
													<span>${loop.index + 1}</span>
												</div></li>
										</div>
										<div class="htcontent">
											<div class="info">
												<li><h3>${hotel.hotelname}</h3></li>
												<li>${hotel.rating}(${ hotel.reviewcount })</li>
											</div>
											<div class="price">
												<li><br> <b>${hotel.price}</b>원</li>
											</div>
										</div>
									</a>
								</ul>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div>찜한 목록이 없습니다</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>