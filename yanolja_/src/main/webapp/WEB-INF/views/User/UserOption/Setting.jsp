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
html {
	margin: 0;
	padding: 0;
}

body {
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.setting_container {
    padding-top: 48px;
	display: flex;
	justify-content: center;
}

.setting_wrapper {
	width: 768px;
	height: 80vh;
	background-color: white;
}

.acontainer {
	padding: 20px;
	border: 1px solid black;
	border-radius: 5px;
}

a {
	text-decoration: none;
	color: black;
}

header {
	position: fixed;
	background-color: white;
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 48px;
	z-index: 10;
}

.setting_header {
	width: 768px;
}

.setting_hcontainer {
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: relative;
	width: 100%;
	height: 48px;
	line-height: 2.7rem;
	font-size: 18px;
	font-weight: bold;
	color: #1a1a1a;
	max-width: 768px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<header>
		<div class="setting_header">
			<div class="setting_hcontainer">
				<span onclick="window.history.back();">&lt;</span>
				<span>나의 후기(${room_cnt})</span>
				<span> </span>
			</div>
		</div>
	</header>
	<div class="setting_container">
		<div class="setting_wrapper">
			<div class="acontainer">
				<a href="javascript:logout()">로그아웃</a>
			</div>
		</div>
	</div>
</body>
</html>