/**
 * 
 */
$(function() {

	$("#emaildupcheck").click(
		function() {

			let email = $("#email").val();
			if (email == null) {
				alert("아이디를 입력하세요")
			}
			$.ajax({
				type: 'post', //post 형식으로 controller 에 보내기위함!!
				url: "/emailCheck", // 컨트롤러로 가는 mapping 입력
				data: {
					"email": email
				}, // 원하는 값을 중복확인하기위해서  JSON 형태로 DATA 전송
				success: function(data) {
					if (data == "N") { // 만약 성공할시
						result = "사용 가능한 아이디입니다.";
						$("#result_checkId").html(result).css("color",
							"green");
						$("#password").trigger("focus");

					} else { // 만약 실패할시
						result = "이미 사용중인 아이디입니다.";
						$("#result_checkId").html(result).css("color",
							"red");
						$("#email").val("").trigger("focus");
					}

				},
				error: function(error) {
					alert(error);
				}
			});

		});

	$("form").submit(function(event) {
		var name = $("#name").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var phone = $("#phone").val();
		var warningMessage = $("#warningMessage");

		if (name === "") {
			showWarningMessage("이름을 입력하세요.");
			event.preventDefault(); // 폼 제출을 막습니다.
		} else if (email === "") {
			showWarningMessage("이메일을 입력하세요.");
			event.preventDefault(); // 폼 제출을 막습니다.
		} else if (password === "") {
			showWarningMessage("비밀번호를 입력하세요.");
			event.preventDefault(); // 폼 제출을 막습니다.
		} else if (phone === "") {
			showWarningMessage("휴대폰 번호를 입력하세요.");
			event.preventDefault(); // 폼 제출을 막습니다.
		} else {
			clearWarningMessage(); // 경고 메시지를 숨깁니다.
		}
	});

	function showWarningMessage(message) {
		var warningMessageDiv = document.getElementById("warningMessage");
		warningMessageDiv.innerHTML = message;
		warningMessageDiv.style.display = "block"; // 경고 메시지를 화면에 표시
	}

	function clearWarningMessage() {
		var warningMessageDiv = document.getElementById("warningMessage");
		warningMessageDiv.innerHTML = ""; // 경고 메시지 내용 지우기
		warningMessageDiv.style.display = "none"; // 경고 메시지를 숨기기
	}
});
