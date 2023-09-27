<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${path}/css/Viewlist/saleEvent.css" />
</head>
<body>
	<div class="sale_container">
		<div class="sale_contain">
			<div class="sale_title">
				<h2>특가야놀자</h2>
			</div>
			<div class="swiper sale_swiper">
				<!-- Additional required wrapper -->
				<div class="swiper-wrapper sale_swiper_wrapper">
					<!-- Slides -->
					<div class="swiper-slide sale_slide">
						<a>
							<img src="https://yaimg.yanolja.com/v5/2023/08/30/14/64ef57b732db94.08976394.png" alt="테마_놀거리" class="swiper-lazy">
						</a>
					</div>
					<div class="swiper-slide sale_slide">
						<a>
							<img src="https://yaimg.yanolja.com/v5/2023/08/28/14/64ecabdfa5a939.38351400.png" alt="테마_계이득" class="swiper-lazy">
						</a>
					</div>
					<div class="swiper-slide sale_slide">
						<a>
							<img src="https://yaimg.yanolja.com/v5/2023/08/29/15/64ee0a8c9e2014.74892747.png" alt="테마_호텔" class="swiper-lazy">
						</a>
					</div>
					<div class="swiper-slide sale_slide">
						<a>
							<img src="https://yaimg.yanolja.com/v5/2023/08/28/16/64eccc1b1bcd69.00844920.png" alt="테마_펜션" class="swiper-lazy">
						</a>
					</div>
					<div class="swiper-slide sale_slide">
						<a>
							<img src="https://yaimg.yanolja.com/v5/2023/08/30/14/64ef5842590759.46305849.png" alt="테마_항공" class="swiper-lazy">
						</a>
					</div>
				</div>
				<!-- If we need navigation buttons -->
				<div class="swiper-button-prev swiper-button-prev-2"></div>
				<div class="swiper-button-next swiper-button-next-2"></div>
			</div>
		</div>
	</div>
	<script src="${path}/js/slide/saleEvent.js?var=23-09-11"></script>
</body>
</html>
