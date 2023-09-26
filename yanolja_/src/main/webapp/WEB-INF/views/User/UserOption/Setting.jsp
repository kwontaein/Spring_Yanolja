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
</head>
<body>
	<a href="javascript:logout()">로그아웃</a>
</body>
</html>