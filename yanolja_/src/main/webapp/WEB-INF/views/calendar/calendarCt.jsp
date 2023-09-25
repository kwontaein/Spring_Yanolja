<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>캘린더 ct</title>
<link rel="stylesheet" href="${path}/css/calendar/calendarCt.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${path}/js/places/datemodal.js?var=2023_09_18"></script>
</head>
<body>
	<div class="calendardiv" id="calendardiv">
		<%@include file="../../layout/dateheader.jsp"%>
		<%@include file="./calendar.jsp"%>
	</div>
</body>
</html>