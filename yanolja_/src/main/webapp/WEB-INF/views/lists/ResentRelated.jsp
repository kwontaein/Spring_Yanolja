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
<link rel="stylesheet" href="${path}/css/List/ResentRelated.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</head>
<body>

	<script>
		var mySwiper${review.reviewid} = new Swiper('.swiper-container_related', {
			direction : 'horizontal',
			slidesPerView: '5',
			loop : false,
			pagination : {
			el : '.swiper-pagination',
			},
		});
	</script>
	<h2>최근 본 상품의 연관상품</h2>
	<div id="hotel_Related">
		<div id="Related_listbody" class="swiper-container_related">
			<div id="Related_ulbody" class="swiper-wrapper">
				<c:forEach items="${post}" var="hotel" varStatus="loop" begin="0" end="20">
					<ul id="Related_hotellist" class="swiper-slide" style="width: 140px;">
						<a href="/places/View.do?hotelid=${hotel.hotelid}">
							<div class="Related_hotelimg"></div>
							<div class="Related_content">
								<div class="info">
									<li>
										<h3>${hotel.hotelname}</h3>
									</li>
									<li>${hotel.rating}(${ hotel.reviewcount })</li>
								</div>
								<div class="Related_price">
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