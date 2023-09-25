<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/places/locationtraffic.css" />
<!-- 네이버 맵 API 및 jQuery 라이브러리 로드 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k0qbrv0plh&submodules=geocoder"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var location2 = "${post.roadloc}";
	</script>
	<!-- 지도를 불러오는 js -->
	<script src="${path}/js/NaverMap/locationtraffic.js?var=23-09-15"></script>
	<!-- 지도를 표시할 div -->
	<div class="ltContainer">
		<div class="ltWrapper">
			<div class="NaverMap">
				<div class="map" id="map"></div>
				<div>
					<!-- 검색된 주소 정보를 표시할 테이블 -->
					<div>
						<div id="mapList"></div>
					</div>
				</div>

			</div>
			<div class="parking">
				<div>
					<b>주차안내</b>
				</div>
				<div>
					<div>${post.parking}</div>
					<div></div>
				</div>
			</div>
			<div class="wayinfo">
				<div>
					<b>길안내</b>
				</div>
				<div>
					<div>${post.roadinfo}</div>
					<div></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./mapmodal.jsp"%>
</body>
</html>
