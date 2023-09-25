/**
 * 
 */
$(document).ready(function() {
	// 비밀번호 입력 필드와 비밀번호 확인 입력 필드의 값을 가져옵니다.
	const passwordInput = $('#userpassword');
	const passwordConfirmInput = $('#userpassword2');
	const warningMessage = $('#warningMessage');
	const specialCharacterRegex = /[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/;


	// 비밀번호 확인 입력 필드에서 입력 내용이 변경될 때 비밀번호 일치 여부를 검사합니다.
	passwordConfirmInput.on('input', function() {
		const password = passwordInput.val();
		const passwordConfirm = $(this).val();

		// 비밀번호의 길이와 특수 문자 포함 여부를 검사합니다.
		if (password.length < 7) {
			warningMessage.html('비밀번호가 너무 짧습니다.');
			warningMessage.css('color', 'red');
		} else if (!specialCharacterRegex.test(password)) {
			warningMessage.html('특수문자를 하나 이상 포함해주세요.');
			warningMessage.css('color', 'red');
		} else if (password !== passwordConfirm) {
			warningMessage.html('비밀번호가 일치하지 않습니다.');
			warningMessage.css('color', 'red');
		} else {
			warningMessage.html('비밀번호가 일치합니다.');
			warningMessage.css('color', 'green');
			$('.regifoot').css('background-color', 'deeppink')
		}
	});

	$('.regifoot').click(function() {
		const password = $('#userpassword').val(); // 비밀번호 값을 가져옴
		$.ajax({
			type: 'get',
			url: '/register3', // 다음 페이지를 반환하는 Spring 컨트롤러 엔드포인트
			data: {
				password: password // 비밀번호 값을 전달
			},
			success: function(data) {
				$('body').html(data); // 현재 페이지의 콘텐츠를 대체
			}
		});
	});
});