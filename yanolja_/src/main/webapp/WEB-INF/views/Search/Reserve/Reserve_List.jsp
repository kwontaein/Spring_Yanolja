<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:forEach items="${Book}" var="book">
			<div>
				<div>${book.hotelname}</div>
				<div>${book.roomname}</div>
				<div>${book.user_name}</div>
				<div>${book.reservedate}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>