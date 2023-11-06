$(document).ready(function(data) {

	const options = {
		year: 'numeric',
		month: '2-digit',
		day: '2-digit',
		weekday: 'short'
	};

	function getParameterByName(name, url = window.location.href) {
		name = name.replace(/[\[\]]/g, '\\$&');
		var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
			results = regex.exec(url);
		if (!results) return null;
		if (!results[2]) return '';
		return decodeURIComponent(results[2].replace(/\+/g, ' '));
	}

	// 날짜값이 같은 요소를 선택하고 스타일을 변경
	$('.calendar-day').each(function() {
		const date = new Date($(this).data('date'));
		for (const comparison of comparisonList) {
			const comparisonDate = new Date(comparison.reserveDate);

			// 날짜값이 같은 경우
			if (date.toISOString().split('T')[0] === comparisonDate.toISOString().split('T')[0]) {
				if (comparison.hasRoom == true) {
					// isHasRoom()이 false인 경우 스타일을 변경
					$(this).addClass('no-room');
				}
			}
		}
	});

	$('.able-day').click(function() {
		hideToast(); // 토스트 숨기기 함수 호출
	});
	$('.no-room').click(function() {
		// 클릭 시 토스트 메시지를 표시합니다.
		toast("판매완료된 날짜가 포함되어 구매가 불가능 할 수 있습니다.");
	});
	// 선택한 시작 날짜와 종료 날짜를 세션에서 불러옵니다.
	const savedStartDate = sessionStorage.getItem('sessionDate1');
	const savedEndDate = sessionStorage.getItem('sessionDate2');

	// 가져온 값이 있는지 확인하고, 있을 경우 Date 객체로 변환합니다.
	let sessionDate1 = savedStartDate ? new Date(savedStartDate) : new Date();
	let sessionDate2 = savedEndDate ? new Date(savedEndDate) : new Date();


	if (sessionDate1.getTime() === sessionDate2.getTime()) {
		sessionDate2.setDate(sessionDate1.getDate() + 1); // 종료 날짜를 내일로 설정
	}


	// 만약 세션에 저장된 값이 없다면 기본값을 설정합니다.
	if (!sessionDate1 || isNaN(sessionDate1.getTime())) {
		sessionDate1 = new Date();
	}

	if (!sessionDate2 || isNaN(sessionDate2.getTime())) {
		sessionDate2 = new Date();
		sessionDate2.setDate(sessionDate1.getDate() + 1);
	}

	saveDateToSession(sessionDate1, sessionDate2);

	const maxSelectableDate = new Date();
	maxSelectableDate.setMonth(maxSelectableDate.getMonth() + 6);


	var newDate = sessionDate1;
	newDate.setDate(newDate.getDate() + 1);

	var newEndDate = sessionDate2;
	newEndDate.setDate(newEndDate.getDate() + 1);

	var todayElement = $(`[data-date="${newDate.toISOString().split('T')[0]}"]`);
	todayElement.addClass('selected-start');

	var endDateElement = $(`[data-date="${newEndDate.toISOString().split('T')[0]}"]`);
	endDateElement.addClass('selected-end');

	newDate.setDate(newDate.getDate() - 1);
	newEndDate.setDate(newEndDate.getDate() - 1);

	updateDateRangeButtonText(sessionDate1, sessionDate2);

	// 초기 로딩 시 중간의 날짜에 selected-between 클래스 추가
	if (sessionDate1 && sessionDate2) {
		$('.calendar-day').each(function() {
			const date = new Date($(this).data('date'));
			if (date > sessionDate1 && date < sessionDate2) {
				$(this).addClass('selected-between'); // 중간 날짜 스타일
			}
		});
	}

	// 모든 .calendar-day 클래스를 가진 요소를 선택하고 각각에 이벤트 리스너를 추가합니다.
	$('.calendar-day').click(function() {
		// 클릭한 날짜를 가져와 Date 객체로 변환합니다.
		const clickedDate = new Date($(this).data('date'));
		// 클릭한 날짜가 선택 가능한 범위 내에 있는지 확인
		const maxSelectableEndDate = new Date(sessionDate1);
		maxSelectableEndDate.setDate(sessionDate1.getDate() + 9);

		if (!$(this).hasClass('past-day')) {
			if (!sessionDate1 || clickedDate < sessionDate1 || clickedDate > maxSelectableEndDate) {
				// 시작 날짜가 선택되지 않았거나 클릭한 날짜가 시작 날짜보다 이전인 경우
				// 클릭한 날짜를 새로운 시작 날짜로 설정하고 이전의 시작 날짜를 지웁니다.
				$('.calendar-day').removeClass('selected-start selected-end selected-between selected-start-only selected-end-only');
				sessionDate1 = clickedDate;
				sessionDate2 = null;
				$(this).addClass('selected-start'); // 시작 날짜 스타일
				$(this).addClass('selected-start-only'); // 시작 날짜 스타일

				updateDateRangeButtonText(sessionDate1, sessionDate2);

			} else if (!sessionDate2 && clickedDate > sessionDate1 && clickedDate <= maxSelectableEndDate) {
				// 종료 날짜가 선택되지 않았고, 클릭한 날짜가 시작 날짜보다 이후이며 최대 선택 가능한 날짜 범위 내에 있는 경우
				// 클릭한 날짜를 종료 날짜로 설정하고 중간의 날짜 스타일을 추가합니다.
				sessionDate2 = clickedDate;
				$(this).addClass('selected-end'); // 종료 날짜 스타일

				// 중간에 있는 날짜들에 대한 스타일을 설정합니다.
				$('.calendar-day').each(function() {
					const date = new Date($(this).data('date'));
					if (date > sessionDate1 && date < sessionDate2) {
						$(this).addClass('selected-between'); // 중간 날짜 스타일
					}
				});
			} else if (sessionDate1 && sessionDate2 && clickedDate !== sessionDate1) {
				// 시작 날짜와 종료 날짜가 이미 선택되었고, 클릭한 날짜가 시작 날짜와 다를 때
				// 클릭한 날짜를 새로운 시작 날짜로 설정하고 종료 날짜를 초기화합니다.
				sessionDate1 = clickedDate;
				sessionDate2 = null;

				// 모든 날짜의 스타일을 초기화합니다.
				$('.calendar-day').removeClass('selected-start selected-end selected-between selected-start-only selected-end-only');
				$(this).addClass('selected-start'); // 시작 날짜 스타일
			} else if (sessionDate1 && sessionDate2 && clickedDate > sessionDate2) {
				// 종료 날짜가 선택된 상태에서 클릭한 날짜가 종료 날짜 이후인 경우
				// 클릭한 날짜를 새로운 시작 날짜로 설정하고 종료 날짜를 초기화합니다.
				sessionDate1 = clickedDate;
				sessionDate2 = null;

				// 모든 날짜의 스타일을 초기화합니다.
				$('.calendar-day').removeClass('selected-start selected-end selected-between selected-start-only selected-end-only');
				$(this).addClass('selected-start'); // 시작 날짜 스타일
			}

			// 클릭한 날짜가 시작 날짜와 다르며 종료 날짜가 설정되지 않았거나 시작 날짜와 같으면
			if (sessionDate1 && !sessionDate2) {
				$(this).addClass('selected-start-only'); // 시작 날짜만 선택된 경우 스타일
			}

			// 두 날짜가 모두 선택된 경우 시작 날짜만 스타일 제거
			if (sessionDate1 && sessionDate2) {
				$('.calendar-day').removeClass('selected-start-only');
			}

			// 선택된 기간을 버튼에 표시
			if (sessionDate1 && sessionDate2) {
				const formattedStartDate = sessionDate1.toLocaleDateString("ko-KR", options);
				const formattedEndDate = sessionDate2.toLocaleDateString("ko-KR", options);
				const timeDifference = sessionDate2.getTime() - sessionDate1.getTime();
				const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
				$('#dateRangeButton').text(`${formattedStartDate} - ${formattedEndDate} (${daysDifference}박)`);

				// 선택한 날짜를 세션에 저장합니다.
			} else if (sessionDate1) {
				const formattedStartDate = sessionDate1.toLocaleDateString("ko-KR", options);
				$('#dateRangeButton').text(`${formattedStartDate} 체크인 검색`);

				// 선택한 날짜를 세션에서 제거합니다.
				sessionStorage.removeItem('sessionDate1');
				sessionStorage.removeItem('sessionDate2');
			}
		}
	});


	// 페이지 로딩 시 종료 날짜와 기간을 표시합니다.
	// 종료 날짜의 Calendar Day 요소를 가져옵니다.
	endDateElement = $(`[data-date="${sessionDate2.toISOString().split('T')[0]}"]`);
	// 시작 날짜와 종료 날짜를 세션에서 가져오기 (없으면 기본값 사용)
	const storedStartDate = sessionStorage.getItem('sessionDate1');
	const storedEndDate = sessionStorage.getItem('sessionDate2');

	if (storedStartDate) {
		sessionDate1 = new Date(storedStartDate);
		var newDate = sessionDate1;
		newDate.setDate(newDate.getDate() + 1);

		// 시작 날짜 스타일 적용
		const startElement = $(`[data-date="${newDate.toISOString().split('T')[0]}"]`);
		startElement.addClass('selected-start'); 
		newDate.setDate(newDate.getDate() - 1);
		if (storedEndDate) {
			sessionDate2 = new Date(storedEndDate);
			var newEndDate = sessionDate2;
			newEndDate.setDate(newEndDate.getDate() + 1);
			// 종료 날짜 스타일 적용
			const endElement = $(`[data-date="${newEndDate.toISOString().split('T')[0]}"]`);
			endElement.addClass('selected-end');
			newEndDate.setDate(newEndDate.getDate() - 1);
		}

		// 선택된 기간 버튼 업데이트
		updateDateRangeButtonText(sessionDate1, sessionDate2);
	} else {
		// 세션에 시작 날짜가 없는 경우, 오늘로 초기화
		sessionDate1 = new Date();
		saveDateToSession(sessionDate1, sessionDate2);
	}
	// 종료 날짜에 selected-end 클래스를 추가합니다.
	/*	if (endDateElement.length > 0) {
			endDateElement.addClass('selected-end');
		}*/

	// 선택된 기간을 버튼에 표시합니다.
	updateDateRangeButtonText(sessionDate1, sessionDate2);

	function saveDateToSession(startDate, endDate) {
		sessionStorage.setItem('sessionDate1', startDate.toLocaleDateString("ko-KR", options));

		if (endDate) {
			sessionStorage.setItem('sessionDate2', endDate.toLocaleDateString("ko-KR", options));
			// 기간을 계산합니다.
			const timeDifference = endDate.getTime() - startDate.getTime();
			const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
			sessionStorage.setItem('totalDate', daysDifference);

		} else {
			sessionStorage.removeItem('sessionDate2');
		}
	};

	// 선택된 기간을 버튼에 표시하는 함수
	function updateDateRangeButtonText(startDate, endDate) {
		const dateRangeButton = $('#dateRangeButton');

		if (startDate && endDate) {
			const formattedStartDate = startDate.toLocaleDateString("ko-KR", options);
			const formattedEndDate = endDate.toLocaleDateString("ko-KR", options);

			// 기간을 계산합니다.
			const timeDifference = endDate.getTime() - startDate.getTime();
			const daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));

			dateRangeButton.text(`${formattedStartDate} - ${formattedEndDate} (${daysDifference}박)`);
		} else if (startDate) {
			const formattedStartDate = startDate.toLocaleDateString("ko-KR", options);
			dateRangeButton.text(`${formattedStartDate} 체크인 검색`);
		}
		saveDateToSession(sessionDate1, sessionDate2);
	};

	let removeToast;

	function toast(string) {
		const toast = document.getElementById("toast");

		toast.classList.add("reveal");
		toast.innerText = string;
	};

	function hideToast() {
		const toast = document.getElementById("toast");
		if (toast.classList.contains("reveal")) {
			toast.classList.remove("reveal");
		}
	}

	// 버튼 클릭 이벤트 리스너
	$('#dateRangeButton').click(function() {
		if (sessionDate1 && sessionDate2) {
			saveDateToSession(sessionDate1, sessionDate2);
		} else if (sessionDate1 && !sessionDate2) {
			sessionDate2 = new Date(sessionDate1);
			sessionDate2.setDate(sessionDate1.getDate() + 1);
			saveDateToSession(sessionDate1, sessionDate2);
		}
		var hotelid = getParameterByName('hotelid');
		var roomid = getParameterByName('roomid');
		console.log(sessionDate1 , sessionDate2);
		// AJAX 요청을 사용하여 Spring 컨트롤러로 값을 전달
		$.ajax({
			url: '/calendar', // Spring 컨트롤러 URL
			type: 'get',
			data: {
				selectedStartDate: sessionDate1.toLocaleDateString("ko-KR", options),
				selectedEndDate: sessionDate2.toLocaleDateString("ko-KR", options),
				hotelid: hotelid,
				roomid: roomid
			},
			success: function(data) {
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
				var hotelid = getParameterByName('hotelid');
				var roomid = getParameterByName('roomid');
				if (hotelid != null)
					sendAjaxRequest(hotelid, 1);// 페이지 새로고침
				if (roomid != null)
					location.reload();
			},
			error: function(error) {
				// 요청이 실패한 경우 처리할 내용
			}
		});
	});
});
