<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${path}/css/auth/Login.css" />
<link rel="stylesheet" href="${path}/css/layout/button.css" />
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
		<%@ include file="../../layout/header.jsp"%>
		<div class="login_container">
			<form action="/login_p" method="post">
				<div class="input_container">
					<label for="username" class="form-label">Email</label>
					<input type="Email" name="username" id="username" class="form-control" value="${_csrf.token}">
				</div>
				<div class="input_container">
					<label for="password" class="form-label">비밀번호</label>
					<input type="password" name="password" id="password" class="form-control" value="${_csrf.token}">
				</div>
				<span>
					<p id="valid" class="alert-danger">${exception}</p>
				</span>
				<div id="warningMessage" class="alert alert-danger"></div>
				<button type="submit" class="button_login" onclick="validateForm()">로그인</button>
			</form>
			<div class="login_to_find">
				<a href="/findpassword">비밀번호 찾기</a>
				|
				<a href="/findpassword">회원가입</a>
			</div>
		</div>
		<%@ include file="../../layout/footer.jsp"%>
		<script src="${path}/js/login/login.js?var=23-09-11"></script>
	</c:if>
</body>
</html>