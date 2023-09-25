<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#maphead {
	z-index: 10;
	position: fixed;
	top: 0;
	background-color: white;
	box-shadow: inset;
	font-weight: bold;
	width: 100%;
	height: 56px;
	display: flex;
	justify-content: center;
	text-align: center;
}

a {
	text-decoration-line: none;
	color: black;
}

.mapheader {
	align-items: center;
	width: 768px;
	height: 48px;
	padding-top: 8px;
	margin: 0 auto;
	display: flex;
	align-content: center;
}

#maphead>a {
	font-size: small;
	text-decoration: none;
}

.headerclose {
	position: fixed;
	cursor: pointer;
}

.headerclose>button {
	background-color: white;
	border: none;
	font-size: 24px;
	cursor: pointer;
}

.maptitle {
	width: 768px;
	text-align: center;
}
</style>
</head>
<body>
	<header id="maphead">
		<div class="mapheader">
			<!-- 다른 경우에 대한 처리 -->
			<div class="headerclose">
				<button id="headerClose">&times;</button>
			</div>

			<div class="maptitle">
				<b>지도</b>
			</div>
		</div>
	</header>
</body>
</html>