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
	<div id="hotels">
		<div id="listbody">
			<div id="ulbody">
				<c:forEach items="${post}" var="hotel" varStatus="loop" begin="0" end="5">
					<ul id="hotellist">
						<a href="/places/View.do?hotelid=${hotel.hotelid}">
							<div class="hotelimg">
								<li><div>
										<span>${loop.index + 1}</span>
										<img class="hotel_img" src="data:image/png;base64,${hotel.base64Image}" alt="이미지">
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
</body>
</html>