<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel="stylesheet" href="${path}/css/Viewlist/hotelslide.css" />
<link rel="stylesheet" href="${path}/css/List/hotellist.css" />
</head>
<body>
	<div class="slider-1">
		<div class= "slide_title">
			<div class="slidetitle">
				<h2>이 지역은 이 숙소!</h2>
				<span>관심 지역 근처의 구매 많은 순 추천</span>
			</div>
			<div class="viewAll">
				<a id="Viewall">전체보기</a>
			</div>
		</div>
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
									<div class="contents_box">
										<div>
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
	</div>

	<script src="${path}/js/slide/hotelslide.js?var=23-09-04"></script>
</body>
</html>
