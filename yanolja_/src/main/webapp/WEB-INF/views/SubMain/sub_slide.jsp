<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="${path}/css/Viewlist/subslide.css" />
</head>
<body>
	<div class="swiper-container">
		<div class="slidetitle">
			<h2>숙소 구매 TOP</h2>
			<span>최근 한 주간 구매 많은 순 추천</span>
		</div>
		<div class="swiper-wrapper">
			<c:forEach var="i" begin="1" end="3">
				<div class="swiper-slide">
					<div class="bh_item_inner">
						<div class="bh_content">
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
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
	</div>
	<script src="${path}/js/slide/sub_slide.js?var=23-09-12">
		
	</script>
</body>
</html>