<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Include necessary CSS and JavaScript libraries -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${path}/js/swiper.js"></script>
</head>
<body>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<!-- Slides go here -->
			<div class="swiper-slide">
				<!-- Content for slide 1 -->
				<div id="includedContent1"></div>
				<div id="includedContent2"></div>
				<div id="includedContent3"></div>
				<div id="includedContent4"></div>
			</div>
		</div>

		<!-- Pagination -->
		<div class="pagination_bullet"></div>

		<!-- Navigation buttons -->
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
	</div>

</body>
</html>
