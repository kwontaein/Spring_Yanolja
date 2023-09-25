<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/layout/dateheader.css" />
</head>
<body>
	<header id="datehead">
		<div class="dateheader">
			<div id="headerClose">X</div>
			<div class="calendartitle">
				<h2>날짜 선택</h2>
				<span>
					이 숙소는 <b>최대 9박</b> 까지 예약 가능합니다.
				</span>
			</div>
			<div>초기화</div>

		</div>
		<div class="dateheader2">
			<div class="day">
				<div class="sunday">일</div>
				<div>월</div>
				<div>화</div>
				<div>수</div>
				<div>목</div>
				<div>금</div>
				<div class="saturday">토</div>
			</div>
		</div>
	</header>
</body>
</html>