<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp">
		<jsp:param name="pageName" value="header" />
	</jsp:include>

	<h1>내용</h1>

	<jsp:include page='../layout/footer.jsp'>
		<jsp:param name="pageName" value="footer" />
	</jsp:include>
</body>
</html>