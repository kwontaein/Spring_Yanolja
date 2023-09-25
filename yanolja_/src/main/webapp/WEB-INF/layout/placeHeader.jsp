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
				<a>&lt;</a>
			</div>

			<div>
				<b>${post.hotelname}</b>
			</div>
			<div>
				<a>집</a>
				<a>카</a>
			</div>
		</div>
	</header>
</body>
</html>