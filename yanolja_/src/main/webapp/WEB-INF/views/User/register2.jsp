<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/css/auth/register.css" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>비밀번호 생성</title>
</head>
<body>

	<c:set var="isregister" value="true" />
	<c:set var="register_cnt" value="2" />
	<%@ include file="../../layout/header.jsp"%>
	<div class="passwordcontainer">
		<form id="password-form">
			<div class="passworddiv">
				<!-- 이메일 입력 -->
				<div class="inputpassword">
					<label for="userpassword">비밀번호</label><input type="password" class="passwordinput" name="userpassword" id="userpassword" maxlength='20'>
				</div>
				<div class="inputpassword2">
					<label for="userpassword2">비밀번호 확인</label><input type="password" class="passwordinput" name="userpassword2" id="userpassword2" maxlength='20'>
				</div>
				<div id="warningMessage" class="alert alert-danger2"></div>
			</div>
		</form>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
	<script src="${path}/js/register/register2.js?var=23-09-05">
		
	</script>

</body>
</html>