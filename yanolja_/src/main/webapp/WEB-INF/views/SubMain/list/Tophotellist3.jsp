<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/List/Tophotellist.css" />
</head>
<body>
	<div id="hotels">
		<div id="listbody">
			<div id="ulbody">
				<c:forEach items="${post}" var="hotel" varStatus="loop" begin="12" end="17">
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
</body>
</html>
