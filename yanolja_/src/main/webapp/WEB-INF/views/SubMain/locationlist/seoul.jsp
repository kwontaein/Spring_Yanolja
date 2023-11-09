<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	// <li> 요소를 클릭했을 때 이벤트 핸들러를 등록합니다.
	$('.seoul>ul').click(function() {
		// 클릭한 <li> 요소의 텍스트 값을 가져옵니다.
		const selectedLocation = $(this).text();

		const kindhotel = '${kind}';

		const selectedLis = $(this).find('li'); // <ul> 내의 <li> 요소들을 선택

		const liTexts = selectedLis.map(function() {
			return $(this).text();
		}).get(); // <li> 요소의 텍스트를 배열로 추출

		console.log(liTexts);

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
		console.log('선택한 지역:', liTexts);
		console.log('kindhotel:', kindhotel);
		var data = {
			location : liTexts,
			kindhotels : [ kind ]
		};
		// 여기에서 AJAX 요청을 보// JSON 문자열로 변환
		var jsonData = JSON.stringify(data);

		// encodeURIComponent를 사용하여 URL에 적합한 형태로 인코딩
		var encodedData = encodeURIComponent(jsonData);

		console.log('kindhotel:', encodedData);
		// location.href로 전송
		window.location.href = '/ViewAll?data=' + encodedData;

	});
	$('#viewAllLink').click(function() {
		// 클릭한 <li> 요소의 텍스트 값을 가져옵니다.
		const selectedLocation = $('#all').text();

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
			location : [selectedLocation],
			kindhotels : [ kind ]
		};
		// 여기에서 AJAX 요청을 보// JSON 문자열로 변환
		var jsonData = JSON.stringify(data);

		// encodeURIComponent를 사용하여 URL에 적합한 형태로 인코딩
		var encodedData = encodeURIComponent(jsonData);

		console.log('kindhotel:', encodedData);
		// location.href로 전송
		window.location.href = '/ViewAll?data=' + encodedData;

	});
	
</script>
</head>
<body>
	<div id="viewAllLink">
		<span id="all">${selectedText}</span>
		<span>전체 &gt;</span>
	</div>
	<ul class="seoul">
		<c:forEach items="${RDList}" var="region" varStatus="loop">
			<c:if test="${loop.index % 3 == 0}">
				<ul style="list-style: none; display: flex; padding: 0; margin: 0;">
			</c:if>
			<li>${region}</li>
			<c:if test="${loop.index % 3 == 2 || loop.last}">
	</ul>
	</c:if>
	<c:if test="${not loop.last && loop.index % 3 != 2}">
		<span>&nbsp;/&nbsp;</span>
	</c:if>
	</c:forEach>
	</ul>
</body>
</html>
