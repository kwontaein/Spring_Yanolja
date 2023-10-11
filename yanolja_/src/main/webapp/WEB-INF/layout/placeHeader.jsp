<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/layout/header.css" />
</head>
<body>
	<header id="head">
		<div class="header">
			<!-- 다른 경우에 대한 처리 -->
			<div>
				<a href="javascript:window.history.back();">&lt;</a>
			</div>

			<div>
				<b>${post.hotelname}</b>
			</div>
			<div>
				<a href="/">집</a>
				<a href="/cart">카</a>
			</div>
		</div>
	</header>
</body>
</html>