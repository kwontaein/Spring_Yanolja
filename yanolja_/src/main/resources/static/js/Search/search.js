$(document).ready(function() {
	// 문서 로드 시 기본으로 "국내숙소"를 보여줌
	loadPageContent("국내숙소");

	// 각 div를 클릭할 때 AJAX 요청을 보냄
	$(".headerbottom div").click(function() {
		// 모든 div의 클래스를 제거
		$(".headerbottom div").removeClass("active");
		// 클릭한 div에 "active" 클래스 추가
		$(this).addClass("active");

		// 클릭한 div의 텍스트를 가져와서 loadPageContent 함수에 전달
		var category = $(this).text();
		loadPageContent(category);
	});

	// AJAX 요청을 보내고 응답을 처리하는 함수
	function loadPageContent(category) {
		$.ajax({
			type: "GET",
			url: "/Searchdetail", // 요청할 페이지 URL
			data: { category: category }, // 요청 파라미터
			success: function(response) {
				// 서버로부터의 응답을 표시하는 동적으로 페이지 내용 변경
				$(".searchContent").html(response);
			},
			error: function() {
				console.error("데이터를 불러오는 동안 오류가 발생했습니다.");
			}
		});
	}
});
