<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/layout/footer.css" />
</style>
</head>
<body>
	<header id="footer">
		<div class="footer">
			<c:choose>
				<c:when test="${isregister}">
						<a><div class="regifoot">다음</div></a>
				</c:when>
				<c:otherwise>
					<a><div>지역</div></a>
					<a><div>내주변</div></a>
					<a href="/"><div id="gohome"></div></a>
					<a><div>찜</div></a>
					<a href="/mypage"><div>마이</div></a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>