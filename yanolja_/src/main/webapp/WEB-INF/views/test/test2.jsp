<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swiper with Dynamic Content</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path}/css/swiper.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
</head>
<body>
	<%@ include file="../../layout/header.jsp"%>
	<div class="wrap">
		<div class="custom_slide_wrap">
			<div class="swiper-pagination-custom mt-40 mb-30"></div>
		</div>
		<div class="swiper-bottom">
			<div class="swiper-wrapper">
				<c:forEach var="i" begin="1" end="4">
					<div class="swiper-slide">
						<div class="bh_item_inner">
							<div class="bh_content">
								<p>Slide ${i}</p>
								<div class="contents_box">
									<div>
										<!-- Include된 파일을 여기에 표시 -->
										<div id="includedContent${i}"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script src="${path}/js/test.js?var=23-09-08"></script>
</body>
</html>
