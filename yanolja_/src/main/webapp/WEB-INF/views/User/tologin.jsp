<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${path}/css/auth/Login.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<body>
	<c:if test="${not empty username}">
		<script type="text/javascript">
			$(document).ready(function() {
				alert("잘못된 접근입니다.");
				window.history.back();
			});
		</script>
	</c:if>
	<c:if test="${empty username}">
		<c:set var="istologin" value="true" />
		<%@ include file="../../layout/header.jsp"%>
		<div class="login_container">
			<div class="tologin_container">
				<div class="center_container">
					<div class="space_bet">
						<img src="//yaimg.yanolja.com/joy/sunny/static/images/logo-login-pink.svg" class="yanoljaimg" alt="야놀자 로고">
					</div>
					<div class="space_bet">
						<button onclick="openKakaoLoginPopup()" class="kakaobtn">
							<img src="//yaimg.yanolja.com/joy/sunny/static/images/img-login-kakao-52.svg" class="kakaoimg" alt="카카오로 로그인하기">
						</button>
						<button onclick="openKakaoLoginPopup()" class="kakaobtn">
							<img src="//yaimg.yanolja.com/joy/sunny/static/images/img-login-kakao-52.svg" class="kakaoimg" alt="카카오로 로그인하기">
						</button>
					</div>
					<div class="space_bet">
						<a href="/login">
							<div id="tologin">이메일로 로그인</div>
						</a>
					</div>
				</div>
			</div>
			<div class="space_bet2">
				<span>아직 야놀자 회원이 아.니.신.가.요?</span>
				<a href="/register">
					<b>회원가입 ></b>
				</a>
			</div>
			<%@ include file="../ads/ad1.jsp"%>
		</div>
		<script src="${path}/js/login/tologin.js?var=23-09-11"></script>
		<script>
			const kakaoLoginUrl = '${kakaoUrl}';
		</script>
	</c:if>
</body>
</html>
