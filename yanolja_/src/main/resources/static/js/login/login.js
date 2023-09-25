/**
 * 
 */function showWarningMessage(message) {
	var warningMessageDiv = document.getElementById("warningMessage");
	warningMessageDiv.innerHTML = message;
	warningMessageDiv.style.display = "block"; // 경고 메시지를 화면에 표시
}

function clearWarningMessage() {
	var warningMessageDiv = document.getElementById("warningMessage");
	warningMessageDiv.innerHTML = ""; // 경고 메시지 내용 지우기
	warningMessageDiv.style.display = "none"; // 경고 메시지를 숨기기
}
function validateForm(event) {
	var email = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var warningMessage = document.getElementById("warningMessage");
	console.log(email);
	console.log(password);
	if (email === "" || password === "") {
		showWarningMessage("이메일과 비밀번호를 모두 입력하세요.");
		event.preventDefault(); // 폼 제출을 막습니다.
	} else {
		clearWarningMessage(); // 경고 메시지를 숨깁니다.
	}
}

// 폼 제출 이벤트 핸들러 등록
var loginForm = document.querySelector("form");
loginForm.addEventListener("submit", validateForm);