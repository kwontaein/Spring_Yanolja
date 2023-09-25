<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<c:if test="${empty pageContext.request.userPrincipal}">
		<a href="<c:url value="/oauth2/authorization/kakao"/>">Login with Kakao</a>
	</c:if>
</html>