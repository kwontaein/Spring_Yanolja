<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
	<div id="hotel_season">
		<div id="season_listbody">
			<div id="season_ulbody">
				<c:forEach items="${post}" var="hotel" varStatus="loop" begin="0" end="3">
					<ul id="season_hotellist">
						<a href="/places/View.do?hotelid=${hotel.hotelid}">
							<div class="season_hotelimg">
								<img class="season_hotelimg_img" src="data:image/png;base64,${hotel.base64Image}" alt="이미지">
							</div>
							<div class="season_content">
								<div class="info">
									<li>
										<h3>${hotel.hotelname}</h3>
									</li>
									<li><span style="color: orange;">★</span>${hotel.rating}(${ hotel.reviewcount })</li>
								</div>
								<div class="season_price">
									<li><b>${hotel.price}</b>원</li>
								</div>
							</div>
						</a>
					</ul>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>