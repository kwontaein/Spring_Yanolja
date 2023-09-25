/**
 * 
 */
$(document).ready(function() {
	let code; // 인증번호를 저장할 변수
	let timer; // 타이머 변수

	// 인증번호 입력 창 숨기기
	$('#phone-check-box').hide();

	// 전화번호 입력 후 본인인증 버튼 클릭 시
	$('#phone-Check-Btn').click(function() {
		const phone = $('#phone').val(); // 전화번호 주소값 얻어오기
		console.log('휴대폰번호: ' + phone); // 전화번호 확인

		// 서버로 전화번호를을 전송하여 인증번호 요청
		$.ajax({
			type: 'post', // 이 부분을 'post'로 변경
			url: '/phoneCheck', // 서버 엔드포인트 URL
			data: {
				phone: phone
			},
			success: function(response) {
				if (response.error) { //실패시 
					console.log("에러 발생");
				} else {            //성공시      
					$('#phone-check-box').show();
					console.log('response: ' + response.checkNum);
					code = response.checkNum; // 서버에서 받은 인증번호 저장
					// 타이머 시작 (600초)
					startTimer(2, 30);
					alert("휴대폰 전송.")
				}
			}, error: function(xhr, textStatus, errorThrown) {
				console.log("AJAX 오류 발생: " + errorThrown);
			}
		});
	});

	// 타이머 시작 함수
	function startTimer(minutes, seconds) {
		let remainingMinutes = minutes;
		let remainingSeconds = seconds;

		// 1초마다 남은 시간을 업데이트하고 확인
		timer = setInterval(function() {
			$('#remaining-minutes').text(remainingMinutes);
			$('#remaining-seconds').text(remainingSeconds);

			if (remainingMinutes === 0 && remainingSeconds === 0) {
				clearInterval(timer); // 타이머 중지
				$('.phone-check-input').prop('disabled', true); // 입력 필드 비활성화
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
	$('.phone-check-input').blur(function() {
		const inputCode = $(this).val();
		const $resultMsg = $('#phone-check-warn');

		if (inputCode == code) { // 입력한 인증번호와 서버에서 받은 인증번호 비교
			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color', 'green');
			$('#phone-Check-Btn').prop('disabled', true); // 버튼 비활성화
			$('.regifoot').css('background-color', 'deeppink');
			clearInterval(timer); // 타이머 중지
		} else {
			$resultMsg.html('인증번호가 일치하지 않습니다. 다시 확인해주세요.');
			$resultMsg.css('color', 'red');
		}
	});

	// 초기에 본인인증 버튼 비활성화
	$('#phone-Check-Btn').prop('disabled', true);

	// 전화번호 입력 필드에서 입력 내용이 변경될 때 유효성 검사 및 버튼 상태 업데이트
	$('#phone').on('input', function() {
		const phone = $(this).val();
		const warningMessage = $('#warningMessage');

		// 전화번호 입력 여부 확인
		if (phone.trim() === '') {
			warningMessage.html('휴대폰 번호를 입력해주세요.');
			warningMessage.css('color', 'red');
			$('#phone-Check-Btn').prop('disabled', true); // 본인인증 버튼 비활성화
		} else {
			// 유효한 전화번호일 경우 버튼 활성화
			if (isphoneValid(phone)) {
				warningMessage.html('올바른 전화번호 형식입니다.');
				warningMessage.css('color', 'green');
				$('#phone-Check-Btn').prop('disabled', false); // 본인인증 버튼 활성화
			} else {
				warningMessage.html('번호를 제대로 입력해주세요.');
				warningMessage.css('color', 'red');
				$('#phone-Check-Btn').prop('disabled', true); // 본인인증 버튼 비활성화
			}
		}
	});

	// 전화번호 유효성 검사 함수
	function isphoneValid(phone) {
		const koreanPhoneNumberRegex = /^(01[016789])-?(\d{3,4})-?(\d{4})$/;

		// 전화번호가 정규식과 일치하는지 확인
		return koreanPhoneNumberRegex.test(phone);
	}


	$('.regifoot').click(function() {
		const phoneNumber = $('#phone').val(); // 휴대폰 번호 얻기
		// 서버에 입력한 인증번호를 전송하여 확인
		$.ajax({
			type: 'post', // POST 요청으로 변경
			url: '/register.do', // 서버 엔드포인트 URL (인증번호 확인)
			data: {
				phone: phoneNumber // 휴대폰 번호도 함께 전송
			},
			success: function(response) {
				console.log(response);
				window.location.href = '/login'; // 
			}
		});
	});

});
