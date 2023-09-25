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
	<c:set var="register_cnt" value="1" />
	<%@ include file="../../layout/header.jsp"%>
	<div class="emailcontainer">
		<form id="email-form">
			<div class="emaildiv">
				<!-- 이메일 입력 -->
				<div class="inputemail">
					<label for="email">Email</label><input type="text" class="emailinput" name="email" id="email">
				</div>
				<div id="warningMessage" class="alert alert-danger"></div>
				<div class="checkthis">
					<span>회원 가입시 ID는 반드시 본인 소유의 연락 가능한 이메일 주소를 사용하여야 합니다.</span>
				</div>
				<div class="emailbtn">
					<!-- 본인인증 버튼 -->
					<button type="button" class="btn-primary" id="mail-Check-Btn">인증번호 전송</button>
				</div>
				<div id="mail-check-box">
					<!-- 인증번호 입력 -->
					<input class="mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
					<p id="timer">
						인증번호 유효시간:
						<span id="remaining-minutes">10</span>
						분
						<span id="remaining-seconds">0</span>
						초
					</p>
					<p>인증번호 발송에는 시간이 소요되며 하루 최대 5회까지 전송할 수 있습니다.</p>
					<p>인증번호는 입력한 이메일 주소로 발송됩니다. 수신하지 못했다면 스팸함 또는 해당 이메일 서비스의 설정을 확인해주세요.</p>
				</div>
				<span id="mail-check-warn"></span>
			</div>
		</form>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
	<script src="${path}/js/register/register.js?var=23-09-05">
		
	</script>
</body>
</html>
