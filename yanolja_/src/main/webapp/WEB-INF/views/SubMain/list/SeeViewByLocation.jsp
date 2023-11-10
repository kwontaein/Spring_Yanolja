<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swiper with Dynamic Content</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path}/css/Viewlist/SeeViewByLocation.css" />
<link rel="stylesheet" href="${path}/css/Viewlist/Viewhotels.css" />
</head>
<body>
	<header>
		<div class="svbl_header">
			<div>
				<c:forEach items="${region}" var="rg" varStatus="loop">
					<span>${rg}</span>
					<c:if test="${not loop.last}">/</c:if>
				</c:forEach>
			</div>
			<div></div>
		</div>
	</header>
	<div class="svbl_container">
		<div class="svbl_wrapper">
			<div class="svbl_content">
				<c:forEach items="${post}" var="hotel">
					<ul id="hotellist">
						<a href="/places/View.do?hotelid=${hotel.hotelid}">
							<div class="hotelimg">
								<li><div>
										<img class="resent_hotelimg_img" src="data:image/png;base64,${hotel.base64Image}" alt="이미지">
									</div></li>
							</div>
							<div class="htcontent">
								<div class="info">
									<li><h3>${hotel.hotelname}</h3></li>
									<li><span style="color: orange;">★</span>${hotel.rating}(${ hotel.reviewcount })</li>
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
	<script src="${path}/js/slide/SeeViewByLocation.js?var=23-09-08"></script>
	<!-- <div class="calendar_modal"></div> -->
</body>
</html>
