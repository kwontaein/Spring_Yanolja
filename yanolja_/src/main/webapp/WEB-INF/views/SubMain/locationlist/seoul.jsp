<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	// <li> 요소를 클릭했을 때 이벤트 핸들러를 등록합니다.
	$('.seoul>li').click(function() {
		// 클릭한 <li> 요소의 텍스트 값을 가져옵니다.
		const selectedLocation = $(this).text();
		const kindhotel = '${kind}';
		var kind;
		if (kindhotel == 'hotel') {
			kind = '호텔';
		} else if (kindhotel == 'motel') {
			kind = '모텔';
		} else if (kindhotel == 'pension') {
			kind = '펜션';
		} else if (kindhotel == 'guest') {
			kind = '게스트하우스';
		}
		// 선택한 지역을 서버로 전송하거나 다른 동작을 수행할 수 있습니다.
		console.log('선택한 지역:', selectedLocation);
		console.log('kindhotel:', kindhotel);
		var data = {
			location : [ selectedLocation ],
			kindhotels : [ kind ]
		};
		// 여기에서 AJAX 요청을 보// JSON 문자열로 변환
		var jsonData = JSON.stringify(data);

		// encodeURIComponent를 사용하여 URL에 적합한 형태로 인코딩
		var encodedData = encodeURIComponent(jsonData);

		// location.href로 전송
		window.location.href = '/ViewAll?data=' + encodedData;

	});
</script>
</head>
<body>
	<a>
		<span>서울</span>
		<span>전체 &gt;</span>
	</a>
	<ul class="seoul">
		<li>종로구</li>
		<li>중구</li>
		<li>용산구</li>
		<li>성동구</li>
		<li>광진구</li>
		<li>동대문구</li>
		<li>중랑구</li>
		<li>성북구</li>
		<li>강북구</li>
		<li>도봉구</li>
		<li>노원구</li>
		<li>은평구</li>
		<li>서대문구</li>
		<li>마포구</li>
		<li>양천구</li>
		<li>강서구</li>
		<li>구로구</li>
		<li>금천구</li>
		<li>영등포구</li>
		<li>동작구</li>
		<li>관악구</li>
		<li>서초구</li>
		<li>강남구</li>
		<li>송파구</li>
		<li>강동구</li>
	</ul>
</body>
</html>
