function toggleCheckboxes(checkbox) {
	var checkboxes = document
		.getElementsByClassName("individualCheckbox");
	for (var i = 0; i < checkboxes.length; i++) {
		checkboxes[i].checked = checkbox.checked;
	}
	// 업데이트된 체크박스 상태를 기반으로 check_cnt 갱신
	var checkedCheckboxes = document.querySelectorAll('.individualCheckbox:checked');
	check_cnt = checkedCheckboxes.length;

	// choice_cnt DOM 요소에 실시간으로 갯수를 업데이트
	document.getElementById('choice_cnt').textContent = check_cnt;
	// totalPrice 초기화
	totalPrice = 0;

	// 체크된 체크박스에 대한 totalPrice를 다시 계산
	$(".individualCheckbox:checked").each(function() {
		var checkboxId = $(this).attr("id");
		var loopIndex = checkboxId.substring(5); // "check" 제외한 나머지 부분을 가져옴
		var priceId = "price" + loopIndex;
		// 해당 loop.index에 해당하는 price의 값을 추출하여 총 가격에 더함
		var price = parseFloat($("#" + priceId).text().replace(/[^\d.-]+/g, ''));

		totalPrice += price;
	});

	// totalPrice를 HTML 요소에 출력
	document.getElementById('expected_price').textContent = totalPrice + "원"; //결과를 원하는 DOM 요소에 표시
	document.getElementById('expected_price2').textContent = totalPrice + "원"; //결과를 원하는 DOM 요소에 표시
	document.getElementById('final_price').textContent = totalPrice + "원"; //결과를 원하는 DOM 요소에 표시

	// 'reserve' ID를 가진 요소에 스타일을 변경
	var reserveElement = document.getElementById('reserve');
	if (check_cnt > 0) {
		// 체크된 체크박스가 한 개 이상인 경우 스타일을 변경
		reserveElement.style.backgroundColor = 'deeppink';
		reserveElement.style.color = 'white';
	} else {
		// 아무 체크박스도 선택되지 않은 경우 스타일 초기화
		reserveElement.style.backgroundColor = ''; // 빈 문자열은 초기 스타일로 돌아가게 합니다.
		reserveElement.style.color = '';
	}
}
// deleteCartItem 함수 정의
function deleteCartItem(selectedIds) {
	$.ajax({
		type: "Post",
		url: "/removeFromCart",
		data: { roomid: selectedIds }, // 'roomid'를 쿼리 매개변수로 추가
		success: function(data) {
			alert(data);
			// 성공적으로 삭제되었을 때, 페이지 새로고침 또는 업데이트 등을 수행할 수 있습니다.
			location.reload(); // 예시로 페이지를 새로고침합니다.
		},
		error: function() {
			alert("삭제 중 오류가 발생했습니다.");
		},
	});
}
$(document).ready(function() {

	var seeBottomDiv = $("#seebottom");
	var topDiv = $("#mid");
	// 스크롤 이벤트 감지
	$(window).scroll(function() {
		// 스크롤이 최하단에 위치하면 seeBottomDiv를 보이게 하고 그렇지 않으면 숨깁니다.
		if (isScrolledToBottom()) {
			seeBottomDiv.show();
		} else {
			seeBottomDiv.hide();
		}
		if (isScrolledToTop()) {
			topDiv.show();
		} else {
			topDiv.hide();
		}
	});

	// 스크롤이 최하단에 도달했는지 확인하는 함수
	function isScrolledToBottom() {
		var documentHeight = $(document).height();
		var windowHeight = $(window).height();
		var scrollTop = $(window).scrollTop();

		// 스크롤 위치가 (문서 전체 높이 - 창 높이)와 거의 같을 때, 스크롤이 최하단에 위치한 것으로 판단
		return (documentHeight - windowHeight - scrollTop) < 10;
	}

	function isScrolledToTop() {
		var scrollTop = $(window).scrollTop();

		// 스크롤 위치가 0일 때, 스크롤이 최상단에 위치한 것으로 판단
		return scrollTop === 0;
	}

	var totalCheckboxes = $(".individualCheckbox").length;
	document.getElementById('cnt_cart').textContent = totalCheckboxes;

	var totalPrice = 0; // 총 가격을 저장할 변수
	// 체크된 체크박스 수를 초기화
	var check_cnt = 0;

	// 카운터와 "전체 선택" 체크박스 상태를 업데이트하는 함수
	function updateCheckCounter() {
		check_cnt = $(".individualCheckbox:checked").length;

		// 'reserve' ID를 가진 요소에 스타일을 변경
		var reserveElement = document.getElementById('reserve');
		if (check_cnt > 0) {
			reserveElement.classList.add('reserve');
			reserveElement.classList.remove('not-clickable');

		} else {
			// 아무 체크박스도 선택되지 않은 경우 스타일 초기화
			reserveElement.classList.remove('reserve');
			reserveElement.classList.add('not-clickable');
		}

		document.getElementById('choice_cnt').textContent = check_cnt;
		document.getElementById('cnt_pay').textContent = check_cnt;

		// 체크된 체크박스 수에 따라 "전체 선택" 체크박스 상태 업데이트
		if (check_cnt === totalCheckboxes) {
			$("#selectAll").prop("checked", true);
		} else {
			$("#selectAll").prop("checked", false);
		}
	}

	// 카운터 초기 업데이트
	updateCheckCounter();

	// 체크박스 변경 이벤트
	$(".individualCheckbox").change(function() {
		updateCheckCounter();
		totalPrice = 0; // 총 가격 초기화

		$(".individualCheckbox:checked").each(function() {
			var checkboxId = $(this).attr("id");
			var loopIndex = checkboxId.substring(5); // "check" 제외한 나머지 부분을 가져옴
			var priceId = "price" + loopIndex;
			// 해당 loop.index에 해당하는 price의 값을 추출하여 총 가격에 더함
			var price = parseFloat($("#" + priceId).text().replace(/[^\d.-]+/g, ''));

			totalPrice += price;
		});

		document.getElementById('expected_price').textContent = totalPrice + "원"; //결과를 원하는 DOM 요소에 표시
		document.getElementById('expected_price2').textContent = totalPrice + "원"; //결과를 원하는 DOM 요소에 표시

	});

	// "전체 선택" 체크박스 변경 이벤트
	$("#selectAll").change(function() {
		var selectAllChecked = $(this).is(":checked");
		$(".individualCheckbox").prop("checked", selectAllChecked);
		updateCheckCounter();
	});

	// 삭제 버튼 클릭 이벤트
	$("#deleteSelected").click(function() {
		var selectedRoomIds = [];

		// 체크된 체크박스의 ID 수집
		$(".individualCheckbox:checked").each(function() {
			selectedRoomIds.push($(this).data("roomid"));

		});

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

	$("#reserve").click(function() {

		var selectedRoomIds = [];

		// 체크된 체크박스의 ID 수집
		$(".individualCheckbox:checked").each(function() {
			selectedRoomIds.push($(this).data("roomid"));

		});
		// 선택된 roomids 배열을 URL 매개변수로 추가
		var url = "/Reserve?roomids=" + selectedRoomIds.join(',');

		// 페이지 이동
		window.location.href = url;
		/*
				$.ajax({
					type: "GET", // 또는 GET, 서버 요청 방식에 따라 변경 가능
					url: "/Reserve", // 서버의 요청 핸들러 URL에 맞게 변경
					data: {
						roomids: selectedRoomIds
					},
					success: function(data) {
						// 성공적으로 삭제되었을 때, 페이지 새로고침 또는 업데이트 등을 수행할 수 있습니다.
						$('body').html(data);
					},
					error: function() {
						alert("오류가 발생했습니다.");
					},
				});*/
	});
});