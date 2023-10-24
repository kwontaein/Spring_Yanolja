<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function logout() {
		// 1. 카카오 로그아웃 URL로 이동
		window.location.href = "https://kauth.kakao.com/oauth/logout?client_id=${KAKAO_CLIENT_ID}&logout_redirect_uri=${KAKAO_LOGOUT_REDIRECT_URL}";
	}
</script>
<style type="text/css">
body {
	background-color: #f2f2f2;
}

.setting_container {
	display: flex;
	justify-content: center;
}

.setting_wrapper {
	width: 768px;
	height:80vh;
	background-color: white;
}
.acontainer{
	padding :20px;
	border: 1px solid black;
	border-radius: 5px;
}
a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
	<div class="setting_container">
		<div class="setting_wrapper">
			<div class="acontainer">
				<a href="javascript:logout()">로그아웃</a>
			</div>
		</div>
	</div>
</body>
</html>