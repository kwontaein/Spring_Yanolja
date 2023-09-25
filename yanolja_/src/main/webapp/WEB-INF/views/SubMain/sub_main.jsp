<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>board</title>
<link rel="stylesheet" href="${path}/css/Main/submain.css" />
</head>
<body>
	<%@ include file="../../layout/header.jsp"%>
	<div class="subMaindiv">
		<%@ include file="./select_location.jsp"%>
		<%@ include file="./hotel_event_list.jsp"%>
		<%@ include file="../ads/ad3.jsp"%>
		<%@ include file="./sub_slide.jsp"%>
		<%@ include file="./list/4list.jsp"%>
		<%@ include file="./list/4list2.jsp"%>
		<%@ include file="./list/4list3.jsp"%>
	</div>
</body>
</html>