<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="${path}/css/Main.css"/>
</head>
<body>
<%-- 	<%@ include file="../../layout/header.jsp"%> --%>
	<div id="listbody">
	<a href="write">글쓰기</a>
	<br>
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>등록일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${post}" var="board">
				<tr>
					<td>${board.id}</td>
					<td>
						<a href="/board/view.do?id=${board.id}"> ${board.title} </a>
					</td>
					<td>${board.writer}</td>
					<td>${ board.createdDate }</td>
					<td>${board.viewCnt}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	</div>
	<%@ include file="../../layout/footer.jsp"%>
</body>
</html>