function toggleCheckboxes(checkbox) {
	var checkboxes = document
		.getElementsByClassName("individualCheckbox");
	for (var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked = checkbox.checked;
	}
}
// deleteCartItem 함수 정의
function deleteCartItem(selectedIds) {
	$.ajax({
		type: "GET",
		url: "/removeFromCart",
		data: { roomid: selectedIds }, // 'roomid'를 쿼리 매개변수로 추가
		success: function(data) {
			// 성공적으로 삭제되었을 때, 페이지 새로고침 또는 업데이트 등을 수행할 수 있습니다.
			location.reload(); // 예시로 페이지를 새로고침합니다.
		},
		error: function() {
			alert("삭제 중 오류가 발생했습니다.");
		},
	});
}

// 삭제 버튼에 클릭 이벤트 바인딩
$(document).ready(function() {

	$("#deleteSelected").click(function() {
		var selectedRoomIds = []; // 선택된 체크박스의 roomid를 저장할 배열

		// 모든 체크박스를 순회하면서 선택된 것을 확인하고 roomid를 배열에 추가
		$(".individualCheckbox").each(function() {
			if ($(this).is(":checked")) {
				selectedRoomIds.push($(this).data("roomid"));
			}
		});

		// 선택된 roomid 배열을 JSON 형태로 변환
		var selectedRoomIdsJson = JSON.stringify(selectedRoomIds);

		// AJAX를 사용하여 선택된 roomid 배열을 서버에 전송
		$.ajax({
			type: "GET", // 또는 GET, 서버 요청 방식에 따라 변경 가능
			url: "/removeFromCart?roomid=", // 서버의 요청 핸들러 URL에 맞게 변경
			data: {
				roomid: selectedRoomIds
			},
			success: function(data) {
				// 성공적으로 삭제되었을 때, 페이지 새로고침 또는 업데이트 등을 수행할 수 있습니다.
				location.reload(); // 예시로 페이지를 새로고침합니다.
			},
			error: function() {
				alert("삭제 중 오류가 발생했습니다.");
			},
		});
	});
});