<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${post.hotelname}사진,가격,위치소개|야놀자</title>
<link rel="stylesheet" href="${path}/css/places/Viewplace.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k0qbrv0plh&submodules=geocoder"></script>
</head>
<body>
	<script type="text/javascript">
	$(document).ready(
			function() {
				const like=document.getElementById('like');
				like.addEventListener('click',function(){
					if(${userid} !== null){
						$.ajax({
							url: '/Like_hotel.do', // 서버의 엔드포인트 URL
							method: 'post',     // HTTP GET 요청
							data:{
								hotelid : ${post.hotelid},
								userid : ${userid}
							},
							success: function(data) {
								// 서버로부터 받은 데이터를 화면에 표시
								alert(data);
							},
							error: function() {
								// 에러 처리
								alert("에러");
							}
						});
					}else{
						windows.loaction.href="/tologin"
					}
					
				});
	});
</script>
	<c:set var="isViewplace" value="${pageName == 'Viewplace'}" />
	<%@include file="../../layout/placeHeader.jsp"%>
	<div class="ViewplaceContainer">
		<div class="ViewPlaceWrapper">
			<div class="ViewPlace">
				<div class="hotelimg"></div>
				<div class="hoteltitle">
					<div class="hotel_g">호텔 종류</div>
					<div class="hotel_n">
						<div class="hotel_name">
							<b>${post.hotelname}</b>
						</div>
						<div class="interact">
							<div id="like">찜</div>
							<div>공유</div>
						</div>
					</div>
					<div class="hotel_l">
						<div id="viewroadmap">${post.roadloc}&gt;</div>
					</div>
					<div class="hotel_r">
						<div id="viewrating">
							<span class="star2">★</span>
							<span>${post.rating} (${post.reviewcount})&gt;</span>
						</div>
					</div>
				</div>
				<div class="coupon">
					<div class="couponinfo">쿠폰정보</div>
				</div>
				<%@ include file="../ads/ad4.jsp"%>
				<div class="facil">
					<div>인기시설 및 서비스</div>
					<div>
						<a>전체보기</a>
					</div>
				</div>
			</div>
			<%@include file="./hotelinfo.jsp"%>
			<div class="infoSeller">
				<span>판매자 정보</span>
				<span>&gt;</span>
			</div>
			<div class="infoSeller">
				<span>이 지역 다른 숙소</span>
				<span>전체보기</span>
			</div>
			<div class="infoSeller">
				<span>이 숙소 주변 놀거리</span>
				<span>전체보기</span>
			</div>
		</div>
	</div>
</body>
</html>