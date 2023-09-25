<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/Main.css"/>
</head>
<body>
	<%@ include file="../../layout/header.jsp"%>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${post.id }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${post.title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${post.writer }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea readonly="readonly" rows="5">${post.content}</textarea>
			</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${post.createdDate }</td>
		</tr>
		<tr>
			<td>
				<a href="write?id=${post.id}">수정</a>
			</td>
		</tr>
	</table>
	<%@ include file="../../layout/footer.jsp"%>
</body>
</html>