<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/auth/register.css" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>이메일 인증</title>
</head>
<body>
	<c:set var="isregister" value="true" />
	<c:set var="register_cnt" value="3" />
	<%@ include file="../../layout/header.jsp"%>
	<div class="phonecontainer">
		<form id="phone-form">
			<div class="phonediv">
				<!-- 이메일 입력 -->
				<div class="inputphone">
					<label for="phone">휴대폰 번호</label><input type="text" class="phoneinput" name="phone" id="phone">
				</div>
				<div id="warningMessage" class="alert alert-danger"></div>
				<div class="phonebtn">
					<!-- 본인인증 버튼 -->
					<button type="button" class="btn-primary" id="phone-Check-Btn">인증번호 전송</button>
				</div>
				<div id="phone-check-box">
					<!-- 인증번호 입력 -->
					<p id="timer">
						인증번호 유효시간:
						<span id="remaining-minutes">1</span>
						분
						<span id="remaining-seconds">30</span>
						초
					</p>
					<input class="phone-check-input" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
					<p>인증번호 발송에는 시간이 소요됩니다.</p>
					<p>인증번호는 문자메세지로 발송되며. 수신하지 못했다면 차단된 메세지를 확인해주세요.</p>
				</div>
				<span id="phone-check-warn"></span>
			</div>
		</form>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
	<script src="${path}/js/register/register3.js?var=23-09-05">
		
	</script>
</body>
</html>
