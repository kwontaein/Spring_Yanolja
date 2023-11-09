<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${path}/css/Viewlist/eventrolling.css" />
<meta charset="UTF-8">
<title>Swiper Pagination Example</title>
</head>
<body>
	<div class="swiper">
		<!-- Additional required wrapper -->
		<div class="swiper-wrapper">
			<!-- Slides -->
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/07/20/21/64b9a1bdd1de08.40630315.png" alt="[항공] 해외/항공 런칭 프로모션" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/08/28/16/64ecca969e92a3.52248444.png" alt="[숙마] 추석 연휴 3일 특가" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/07/31/16/64c7e0a12c4d98.46563654.png" alt="[공통] 8월 여름 캠페인 전사 A" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/08/16/17/64dd02ba369078.85729834.png" alt="[해외] 일본여행 초특가박람회" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/08/17/13/64de1dc92944d0.92692972.png" alt="[항공] 제주여행 트리플특가" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/08/18/15/64df8fba7b2da8.75186254.png" alt="[숙마] 국내숙소 특가모음" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/08/25/16/64e8dc8b906ed9.54994502.png" alt="[라이브] 에어서울 항공권 방송후" class="eventimg"></a>
			</div>
			<div class="swiper-slide">
				<a><img src="https://yaimg.yanolja.com/v5/2023/08/25/16/64e8d5c7a09221.97378909.png" alt="[호텔] 8월 5주차 호텔" class="eventimg"></a>
			</div>
		</div>
		<!-- If we need navigation buttons -->
		<div class="swiper-button-prev swiper-button-prev-1"></div>
		<div class="swiper-button-next swiper-button-next-1"></div>
	</div>
	<div class="swiper-controls">
		<button id="toggleAutoplay">Stop Autoplay</button>
	</div>
	<script src="${path}/js/slide/eventrolling.js?var=23-09-08"></script>
</body>
</html>
