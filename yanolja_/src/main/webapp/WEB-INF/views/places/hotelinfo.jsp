<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/places/hotelinfo.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k0qbrv0plh&submodules=geocoder"></script>

</head>
<body>
	<script src="${path}/js/places/hotelinfo.js"></script>
	<div class="infoContainer">
		<div class="infoWrapper">
			<div class="infochoice">
				<div class="infochangebtn" data-kindid="1">객실선택</div>
				<div class="infochangebtn" data-kindid="2">위치/교통</div>
				<div class="infochangebtn" data-kindid="3">안내/정책</div>
				<div class="infochangebtn" data-kindid="4">시설/서비스</div>
				<div class="infochangebtn" data-kindid="5">후기</div>
			</div>
		</div>
		<div class="changedinfo">바뀔 내용 들어갈 자리</div>
	</div>
</body>
</html>
