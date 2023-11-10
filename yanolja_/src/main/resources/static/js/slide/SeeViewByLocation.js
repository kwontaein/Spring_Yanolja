
$(document).ready(function() {  // 세션 값을 JavaScript 변수에 할당
	const savedStartDate = sessionStorage.getItem('selectedStartDate');
	const savedEndDate = sessionStorage.getItem('selectedEndDate');

	let selectedStartDate = savedStartDate ? new Date(savedStartDate) : new Date();
	let selectedEndDate = savedEndDate ? new Date(savedEndDate) : new Date();
	if (selectedStartDate.getTime() === selectedEndDate.getTime()) {
		selectedEndDate.setDate(selectedEndDate.getDate() + 1); // 종료 날짜를 내일로 설정
	}

	// 선택한 시작 날짜를 원하는 형식으로 포맷
	const options = { month: '2-digit', day: '2-digit' };
	const formattedStartDate = selectedStartDate.toLocaleDateString("ko-KR", options);
	const formattedEndDate = selectedEndDate.toLocaleDateString("ko-KR", options);

	const datemodal = document.getElementById("datemodal");

	if (datemodal) {
		// datechoice 요소가 존재하는 경우에만 작업을 수행합니다.
		if (selectedStartDate != null && formattedEndDate != null) {
			// 포맷된 날짜를 표시할 요소에 추가
			// 기간을 계산합니다.
			const timeDifference = selectedEndDate.getTime() - selectedStartDate.getTime();
			const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
			dayDiff = daysDifference;
			// 버튼의 텍스트를 설정합니다.
			datemodal.textContent = `${formattedStartDate} ~ ${formattedEndDate} · ${daysDifference}박`;
		}
	}

	calendarAjax();

});

$('#datemodal').click(function() {
	$('.calendar_modal').css({
		'display': 'block',
		'height': '100%',
		'z-index': '1'
	});

	$('#head').css({
		'display': 'none'
	});

	$('body').css({
		'overflow': 'hidden'
	});

	$('.wrap').css({
		'z-index': '0'
	});
});


function calendarAjax() {
	$.ajax({
		type: 'get',
		url: '/calendar', // 서버 엔드포인트 URL
		success: function(data) {
			$('.calendar_modal').html(data);
			// 모달 표시
		}
	});
}

// 모달 닫기 버튼 클릭 시 모달 닫기
$('.calendar_modal').on('click', '#headerClose', function() {
	// 모달 닫기 애니메이션
	$('.calendar_modal').css('height', '0');
	setTimeout(function() {
		// 모달 숨기기
		$('.calendar_modal').css('display', 'none');
	}, 600); // 애니메이션 종료 후에 모달을 숨깁니다.
	$('#head').css({
		'display': 'inline-block'
	})
	$('body').css({
		'overflow': 'auto'
	})
	$('.wrap').css({
		'display': 'auto'
	});
});
