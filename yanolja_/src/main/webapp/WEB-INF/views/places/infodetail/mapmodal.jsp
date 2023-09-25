<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//모달을 열고 닫기 위한 JavaScript
	var modal2 = document.getElementById('myModal');
	var mapDiv = document.getElementById('map');
	var headerCloseBtn = document.getElementById('headerClose'); // Header에 추가한 close 버튼

	// 모달 초기 높이를 0으로 설정
	modal2.style.height = '0';

	// NaverMap div를 클릭하면 모달을 엽니다.
	mapDiv.addEventListener('click', function() {
		window.addEventListener('scroll', noscroll);
		hideScrollbar();
		modal2.style.display = 'block';

		// 모달 높이를 증가하여 모달이 아래에서 올라오는 애니메이션 효과를 제공합니다.
		modal2.style.height = '100%';
	});

	// Header에 추가한 close 버튼을 클릭하면 모달을 닫습니다.
	headerCloseBtn.addEventListener('click', function() {
		window.removeEventListener('scroll', noscroll);

		// 모달 높이를 0으로 설정하여 모달이 아래로 내려가는 애니메이션 효과를 제공합니다.
		modal2.style.height = '0';
		setTimeout(function() {
			modal2.style.display = 'none';
			showScrollbar();
		}, 600); // 애니메이션 종료 후에 모달을 숨깁니다.
	});
	// 스크롤 이벤트를 무시하는 함수입니다.
	function noscroll() {
		window.scrollTo(0, 0);
	}

	// 스크롤바를 숨깁니다.
	function hideScrollbar() {
		document.documentElement.style.overflow = 'hidden'; // IE, Edge, Firefox
		document.body.scroll = 'no'; // Internet Explorer
		document.body.style.overflow = 'hidden'; // Chrome, Safari, Opera
	}

	// 스크롤바를 다시 표시합니다.
	function showScrollbar() {
		document.documentElement.style.overflow = 'auto'; // IE, Edge, Firefox
		document.body.scroll = 'yes'; // Internet Explorer
		document.body.style.overflow = 'auto'; // Chrome, Safari, Opera
	}
</script>
</head>
<body>
	<!-- 모달 HTML -->

	<div id="myModal" class="modal">
		<%@include file="../../../layout/MapHeader.jsp"%>
		<div class="modal-content">
			<div>
				<!-- 검색된 주소 정보를 표시할 테이블 -->
				<div>
					<div id="mapList2"></div>
				</div>
			</div>
			<div class="mapcontainer">
				<div class="locbtns">
					<button class="nowloc">
						<img src="data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3e %3cg fill='none' fill-rule='evenodd'%3e %3cpath fill='white' stroke='%23ABABAB' d='M.5.5h39v39H.5z' opacity='.8'/%3e %3cpath fill='%23DE2E5F' d='M20.952 11.96v4.23h-1.904v-4.23a8.099 8.099 0 0 0-7.088 7.088h4.23v1.904h-4.23a8.099 8.099 0 0 0 7.088 7.088v-4.23h1.904v4.23a8.099 8.099 0 0 0 7.088-7.088h-4.23v-1.904h4.23a8.099 8.099 0 0 0-7.088-7.088zM20 10c5.523 0 10 4.477 10 10s-4.477 10-10 10-10-4.477-10-10 4.477-10 10-10z'/%3e %3c/g%3e%3c/svg%3e" class="css-1wmvg74">
					</button>
					<button class="hotelloc">
						<img src="data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3e %3cg fill='none' fill-rule='evenodd'%3e %3cpath fill='white' stroke='%23ABABAB' d='M.5.5h39v39H.5z' opacity='.8'/%3e %3cpath fill='%23DE2E5F' d='M20 21.017c-2.054 0-3.719-1.637-3.719-3.657s1.665-3.658 3.719-3.658 3.719 1.637 3.719 3.658c0 2.02-1.665 3.657-3.719 3.657zM20 9c-4.694 0-8.5 3.743-8.5 8.36 0 3.588 2.302 6.639 5.53 7.824L20 32l2.97-6.815c3.228-1.186 5.53-4.237 5.53-7.825C28.5 12.743 24.695 9 20 9z'/%3e %3c/g%3e%3c/svg%3e" class="css-1wmvg74">
					</button>
				</div>
				<div class="map2" id="map2"></div>
			</div>
		</div>
	</div>
</body>
</html>
