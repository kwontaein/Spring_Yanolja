<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/search/koreahotel.css" />
</head>

<body>
	<script src="${path}/js/Search/koreahotel.js"></script>
	<div class="ksearchContainer">
		<div class="ksearchWrapper">
			<div class="ksearchtop">
				<div class="ksearch">
					<button class="search_btn" type="submit">
						<img src="//yaimg.yanolja.com/joy/sunny/static/images/icon-search-black-line-1.svg" alt="검색">
					</button>
					<input class="ksearchinput" type="search" placeholder="지역, 숙소명, 테마 키워드로 찾아보세요" maxlength="50" value="">
				</div>
				<div class="kchoice">
					<c:choose>
						<c:when test="${sessionScope.selectedStartDate != null}">
							<button class="datechoice" id="datechoice2"></button>
						</c:when>
						<c:otherwise>
							<button class="datechoice" id="datechoice">${currentDate}~${tomorrowDate}·1박</button>
						</c:otherwise>
					</c:choose>
					<div class="manCnt" id="manCnt">인원수 자리</div>
				</div>
			</div>
			<div class="ksearchmid">
				<div>
					<h2>인기 검색어</h2>
				</div>
				<div>추후 추가 예정</div>
			</div>
			<div class="ksearchbottom"></div>
		</div>
	</div>
	<div class="calendar_modal"></div>
</body>
</html>