<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Swiper Pagination Example</title>
</head>
<body>
<script type="text/javascript">

	function test(){

		var a = 0;

		<c:if test="${not empty username}">
			a = ${usernmae};
		</c:if>
		console.log(a);
	
	}
	
</script>
	<div>
		<button onclick="test()"></button>
	</div>
</body>
</html>
