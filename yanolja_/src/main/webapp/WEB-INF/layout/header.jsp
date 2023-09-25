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
			<c:choose>
				<c:when test="${isMypage}">
					<c:set var="istologin" value="false" />
					<c:set var="isregister" value="false" />
					<a>
						<div>마이페이지</div>
					</a>
					<a href="/login">
						<div>검색창</div>
					</a>
					<a>
						<div>장바구니</div>
					</a>
				</c:when>
				<c:when test="${istologin}">
					<c:set var="isMyPage" value="false" />
					<c:set var="isregister" value="false" />
					<a href="/mypage">
						<div>&lt;</div>
					</a>
					<a href="/login">
						<div>검색창</div>
					</a>
					<a>
						<div>장바구니</div>
					</a>
				</c:when>
				<c:when test="${isregister}">
					<c:set var="isMyPage" value="false" />
					<c:set var="istologin" value="false" />
					<a href="/tologin">
						<div>&lt;</div>
					</a>
					<div>회원가입 (${register_cnt}/3)</div>
				</c:when>
				<c:otherwise>
					<!-- 다른 경우에 대한 처리 -->
					<a>
						<div>메뉴바</div>
					</a>
					<a href="/login">
						<div>검색창</div>
					</a>
					<a>
						<div>장바구니</div>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>