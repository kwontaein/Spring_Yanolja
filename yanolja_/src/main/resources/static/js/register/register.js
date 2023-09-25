$(document).ready(function() {
	let code; // 인증번호를 저장할 변수
	let timer; // 타이머 변수

	// 인증번호 입력 창 숨기기
	$('#mail-check-box').hide();

	// 이메일 주소 입력 후 본인인증 버튼 클릭 시
	$('#mail-Check-Btn').click(function() {
		const email = $('#email').val(); // 이메일 주소값 얻어오기
		console.log('이메일: ' + email); // 이메일 확인

		// 서버로 이메일을 전송하여 인증번호 요청
		$.ajax({
			type: 'get',
			url: '/mailCheck', // 서버 엔드포인트 URL
			data: {
				email: email
			},
			success: function(data) {
				$('#mail-check-box').show();
				console.log('data: ' + data);
				code = data; // 서버에서 받은 인증번호 저장
				// 타이머 시작 (600초)
				startTimer(10);
			}
		});
	});

	// 타이머 시작 함수
	function startTimer(minutes, seconds) {
		let remainingMinutes = minutes;
		let remainingSeconds = 0;

		// 1초마다 남은 시간을 업데이트하고 확인
		timer = setInterval(function() {
			$('#remaining-minutes').text(remainingMinutes);
			$('#remaining-seconds').text(remainingSeconds);

			if (remainingMinutes === 0 && remainingSeconds === 0) {
				clearInterval(timer); // 타이머 중지
				$('.mail-check-input').prop('disabled', true); // 입력 필드 비활성화
				$('#timer').text('인증번호 유효시간이 초과되었습니다.'); // 시간 초과 메시지
			} else {
				if (remainingSeconds === 0) {
					remainingMinutes--;
					remainingSeconds = 59;
				} else {
					remainingSeconds--;
				}
			}
		}, 1000);
	}


	// 인증번호 비교
	$('.mail-check-input').blur(function() {
		const inputCode = $(this).val();
		const $resultMsg = $('#mail-check-warn');

		if (inputCode === code) { // 입력한 인증번호와 서버에서 받은 인증번호 비교
			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color', 'green');
			$('#mail-Check-Btn').prop('disabled', true); // 버튼 비활성화
			$('.regifoot').css('background-color', 'deeppink');
			clearInterval(timer); // 타이머 중지
		} else {
			$resultMsg.html('인증번호가 일치하지 않습니다. 다시 확인해주세요.');
			$resultMsg.css('color', 'red');
		}
	});

	// 초기에 본인인증 버튼 비활성화
	$('#mail-Check-Btn').prop('disabled', true);

	// 이메일 주소 입력 필드에서 입력 내용이 변경될 때 유효성 검사 및 버튼 상태 업데이트
	$('#email').on('input', function() {
		const email = $(this).val();
		const warningMessage = $('#warningMessage');

		// 이메일 입력 여부 확인
		if (email.trim() === '') {
			warningMessage.html('이메일 주소를 입력해주세요.');
			warningMessage.css('color', 'red');
			$('#mail-Check-Btn').prop('disabled', true); // 본인인증 버튼 비활성화
		} else {
			// 유효한 이메일일 경우 버튼 활성화
			if (isEmailValid(email)) {
				// 서버로 중복 검사 요청
				$.ajax({
					type: 'get',
					url: '/emailCheck', // 서버 엔드포인트 URL
					data: {
						email: email
					},
					success: function(data) {
						console.log(data);
						if (data === 'Y') {
							warningMessage.html('중복된 이메일 주소입니다.');
							warningMessage.css('color', 'red');
							$('#mail-Check-Btn').prop('disabled', true); // 본인인증 버튼 비활성화
						} else {
							warningMessage.html('올바른 이메일 형식입니다.');
							warningMessage.css('color', 'green');
							$('#mail-Check-Btn').prop('disabled', false); // 본인인증 버튼 활성화
						}
					}
				});
			} else {
				warningMessage.html('유효하지 않은 이메일 주소입니다.');
				warningMessage.css('color', 'red');
				$('#mail-Check-Btn').prop('disabled', true); // 본인인증 버튼 비활성화
			}
		}
	});


	// 이메일 유효성 검사 함수
	function isEmailValid(email) {
		const emailRegex = /^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
		return emailRegex.test(email);
	}

	// "다음" 버튼 클릭 시 페이지를 동적으로 로드
	$('.regifoot').click(function() {
		$.ajax({
			type: 'get',
			url: '/register2', // 다음 페이지를 반환하는 Spring 컨트롤러 엔드포인트
			success: function(data) {
				$('body').html(data); // 현재 페이지의 콘텐츠를 대체
			}
		});
	});
});
