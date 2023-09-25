// 페이지 로드 시 지도를 그려주는 함수 실행
$(document).ready(function() {

	searchAddressToCoordinate(location2);
	// 검색한 주소의 정보를 insertAddress 함수로 넘겨준다.
	function searchAddressToCoordinate(location2) {
		naver.maps.Service
			.geocode(
				{
					query: location2
				},
				function(status, response) {
					if (status === naver.maps.Service.Status.ERROR) {
						return alert('Something Wrong!');
					}
					if (response.v2.meta.totalCount === 0) {
						return alert('올바르지 않은 주소입니다 호텔 혹은 관리자에게 문의하세요.');
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

					if (navigator.geolocation) {
						navigator.geolocation.getCurrentPosition(function(position) {
							var Nowlatitude = position.coords.latitude;
							var Nowlongitude = position.coords.longitude;
							insertAddress(location2, item.y, item.x, Nowlatitude, Nowlongitude);
						});
					} else {
						alert('브라우저가 Geolocation을 지원하지 않습니다.');
					}

					// 호텔의 주소를 복사
					$('#copybtn1').click(function() {
						copyToClipboard(location2);
						alert('주소를 복사하였습니다');
					});
					// 호텔의 주소를 복사
					$('#copybtn2').click(function() {
						copyToClipboard(location2);
						alert('주소를 복사하였습니다');
					});
				});
	}


	// 검색 정보를 테이블로 작성하고 지도에 마커를 추가하는 s함수
	function insertAddress(location2, latitude, longitude, Nowlatitude, Nowlongitude) {
		console.log(latitude, longitude);
		console.log(Nowlatitude, Nowlongitude);
		var mapList = "";
		mapList += "<div class=\"roadinfo\">"
		mapList += "	<span>" + location2 + "</span>"
		mapList += "</div>"
		mapList += "<div class=\"copyroad\">"
		mapList += "<button id=\"copybtn1\" onclick=\"copyToClipboard();\" title=\"주소 복사\">주소 복사</button>"
		mapList += "</div>"

		$('#mapList').append(mapList);

		var map = new naver.maps.Map('map', {
			center: new naver.maps.LatLng(latitude, longitude),
			zoom: 14,
			draggable: false, // 이동 비활성화
			scrollWheel: false
		});

		var marker = new naver.maps.Marker({
			map: map,
			position: new naver.maps.LatLng(latitude, longitude),
		});


		var mapList2 = "";
		mapList2 += "<div class=\"roadinfo\">"
		mapList2 += "	<span>" + location2 + "</span>"
		mapList2 += "</div>"
		mapList2 += "<div class=\"copyroad2\">"
		mapList2 += "<button id=\"copybtn2\" onclick=\"copyToClipboard();\" title=\"주소 복사\">주소 복사</button>"
		mapList2 += "</div>"

		$('#mapList2').append(mapList2);

		var map2 = new naver.maps.Map('map2', {
			center: new naver.maps.LatLng(latitude, longitude),
			zoom: 14
		});

		var marker2 = new naver.maps.Marker({
			map: map2,
			position: new naver.maps.LatLng(latitude, longitude),
		});


		var marker3 = new naver.maps.Marker({
			map: map2,
			position: new naver.maps.LatLng(Nowlatitude, Nowlongitude),
		});

		// 버튼 클릭 시 marker3로 지도 포커스
		$('.nowloc').click(function() {
			map2.setCenter(marker3.getPosition()); // marker3의 위치로 지도 중심 이동
		});

		// 버튼 클릭 시 marker2로 지도 포커스
		$('.hotelloc').click(function() {
			map2.setCenter(marker2.getPosition()); // marker3의 위치로 지도 중심 이동
		});

	}
});

function copyToClipboard(textToCopy) {
	var t = document.createElement("textarea");
	document.body.appendChild(t);
	t.value = textToCopy;
	t.select();
	document.execCommand('copy');
	document.body.removeChild(t);
}
