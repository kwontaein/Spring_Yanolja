<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/places/location_modal.css" />
<script>
	var kind = `${kind}`;
	$(document).ready(function() {
		// li 클릭 이벤트 처리
		$(".Roc_list li").click(function() {
			// 클릭한 li의 텍스트 내용 가져오기
			var selectedText = $(this).text();

			// 모든 li에서 active 클래스 제거
			$(".Roc_list li").removeClass("active");

			// 클릭한 li에 active 클래스 추가
			$(this).addClass("active");
			// AJAX를 사용하여 다른 JSP 파일을 로드
			includefile(selectedText);
		});
	});
	function includefile(selectedText) {
		$.ajax({
			url : '/sub_main/selectlocation', // 다른 JSP 파일의 경로
			method : 'GET',
			data : {
				selectedText : selectedText,
				kind : kind
			}, // 선택한 텍스트를 전달할 수도 있음
			success : function(response) {
				// 로드된 JSP 파일의 내용을 특정 위치에 표시
				$(".detail_content").html(response);
			},
			error : function() {
				alert("오류 발생");
			}
		});
	}
</script>
</head>
<body>
	<div>
		<header class="headerloc">
			<div class="lochead">
				<div id="close_loc_modal">X</div>
				<div>
					<b>지역 선택</b>
				</div>
				<div>a</div>
			</div>
		</header>
		<div class="modalWrapper">
			<div class="Select_Roc">
				<div class="Roc_content">
					<ul class="Roc_list">
						<li data-location="seoul">서울</li>
						<li>부산</li>
						<li>제주</li>
						<li>경기</li>
						<li>인천</li>
						<li>강원</li>
						<li>경상</li>
						<li>전라</li>
						<li>충청</li>
					</ul>
					<div class="Detail_Roc">
						<div class="detail_content"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>