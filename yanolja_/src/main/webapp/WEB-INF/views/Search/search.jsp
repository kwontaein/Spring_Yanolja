<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/search/search.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k0qbrv0plh&submodules=geocoder"></script>
</head>
<body>
	<script src="${path}/js/Search/search.js"></script>
	<div>
		<header>
			<div class="headerContainer">
				<div class="headerWrapper">
					<div>
						<div class="headertop">
							<div class="left"><a href="javascript:window.history.back();">&lt;</a></div>
							<div class="middle">검색</div>
							<div class="right"><a href="/">홈</a></div>
						</div>
						<div class="headerbottom">
							<div class="active" id="domestic">국내숙소</div>
							<div id="leisureTicket">레저/티켓</div>
							<div id="transportation">교통/항공</div>
							<div id="internationalAccommodation">해외숙소</div>
						</div>

					</div>
				</div>
			</div>
		</header>
		<div class="contentWrapper">
			<div class="searchContent"></div>
		</div>
	</div>
</body>
</html>