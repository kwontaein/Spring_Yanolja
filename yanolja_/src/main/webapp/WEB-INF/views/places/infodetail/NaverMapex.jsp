<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<!-- 네이버 맵 API 및 jQuery 라이브러리 로드 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k0qbrv0plh&submodules=geocoder"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>

<body>
	<div class="search">
		<!-- 주소 검색을 위한 입력 필드 및 검색 버튼 -->
		<input id="address" type="text" placeholder="검색할 주소"> <input id="submit" type="button" value="주소검색">
	</div>
	<!-- 지도를 표시할 div -->
	<div id="map" style="width: 1000px; height: 500px;"></div>
	<div>
		<!-- 검색된 주소 정보를 표시할 테이블 -->
		<table>
			<thead>
				<tr>
					<th>주소</th>
					<th>위도</th>
					<th>경도</th>
				</tr>
			</thead>
			<tbody id="mapList"></tbody>
		</table>
	</div>
</body>
<script>
	// 페이지 로드 시 지도를 그려주는 함수 실행
	selectMapList();

	// 검색한 주소의 정보를 insertAddress 함수로 넘겨준다.
	function searchAddressToCoordinate(address) {
		naver.maps.Service
				.geocode(
						{
							query : address
						},
						function(status, response) {
							if (status === naver.maps.Service.Status.ERROR) {
								return alert('Something Wrong!');
							}
							if (response.v2.meta.totalCount === 0) {
								return alert('올바른 주소를 입력해주세요.');
							}
							var htmlAddresses = [], item = response.v2.addresses[0], point = new naver.maps.Point(
									item.x, item.y);
							if (item.roadAddress) {
								htmlAddresses.push('[도로명 주소] '
										+ item.roadAddress);
							}
							if (item.jibunAddress) {
								htmlAddresses.push('[지번 주소] '
										+ item.jibunAddress);
							}
							if (item.englishAddress) {
								htmlAddresses.push('[영문명 주소] '
										+ item.englishAddress);
							}

							// 검색된 주소 정보를 테이블과 지도에 추가
							insertAddress(item.roadAddress, item.x, item.y);

						});
	}

	// 주소 검색의 이벤트 핸들러
	$('#address').on('keydown', function(e) {
		var keyCode = e.which;
		if (keyCode === 13) { // Enter Key
			searchAddressToCoordinate($('#address').val());
		}
	});
	$('#submit').on('click', function(e) {
		e.preventDefault();
		searchAddressToCoordinate($('#address').val());
	});

	// 네이버 지도 스타일맵 초기화
	naver.maps.Event.once(map, 'init_stylemap', initGeocoder);

	// 검색 정보를 테이블로 작성하고 지도에 마커를 추가하는 함수
	function insertAddress(address, latitude, longitude) {
		var mapList = "";
		mapList += "<tr>"
		mapList += "	<td>" + address + "</td>"
		mapList += "	<td>" + latitude + "</td>"
		mapList += "	<td>" + longitude + "</td>"
		mapList += "</tr>"

		$('#mapList').append(mapList);

		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(longitude, latitude),
			zoom : 14
		});
		var marker = new naver.maps.Marker({
			map : map,
			position : new naver.maps.LatLng(longitude, latitude),
		});
	}

	// 초기 지도를 그리는 함수
	function selectMapList() {
		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.3595704, 127.105399),
			zoom : 10
		});
	}

	// 지도를 이동시키는 함수
	function moveMap(len, lat) {
		var mapOptions = {
			center : new naver.maps.LatLng(len, lat),
			zoom : 15,
			mapTypeControl : true
		};
		var map = new naver.maps.Map('map', mapOptions);
		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(len, lat),
			map : map
		});
	}
</script>
</html>
